package com.example.ragservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ai.djl.translate.TranslateException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.util.Arrays;

@Service
public class VectorDBService {
    private static final Logger logger = LoggerFactory.getLogger(VectorDBService.class);
    private final List<VectorEntry> vectorDb = new ArrayList<>();
    private final EmbeddingService embeddingService;
    
    // 静态内部类替代record类型 (Java 8兼容)
    public static class VectorEntry implements Serializable {
        private static final long serialVersionUID = 1L;
        
        private final String id;
        private final String text;
        private final float[] vector;
        private final Map<String, String> metadata;
        
        public VectorEntry(String id, String text, float[] vector, Map<String, String> metadata) {
            this.id = id;
            this.text = text;
            this.vector = vector;
            this.metadata = metadata;
        }
        
        public String id() { return id; }
        public String text() { return text; }
        public float[] vector() { return vector; }
        public Map<String, String> metadata() { return metadata; }
    }
    
    public static class SearchResult {
        private final String id;
        private final String text;
        private final double score;
        private final Map<String, String> metadata;
        
        public SearchResult(String id, String text, double score, Map<String, String> metadata) {
            this.id = id;
            this.text = text;
            this.score = score;
            this.metadata = metadata;
        }
        
        public String id() { return id; }
        public String text() { return text; }
        public double score() { return score; }
        public Map<String, String> metadata() { return metadata; }
    }
    
    // 添加配置项
    @Value("${knowledge.base.path:data/dst}")
    private String knowledgeBasePath;
    
    @Value("${vector.similarity.threshold:0.6}")
    private double similarityThreshold;
    
    @Value("${text.chunk.size:500}")
    private int chunkSize;
    
    @Value("${text.chunk.overlap:100}")
    private int chunkOverlap;
    
    @Value("${vector.db.file:data/vector_db.dat}")
    private String vectorDbFile;
    
    @Value("${vector.db.use.disk:true}")
    private boolean useVectorDbDisk;
    
    @Autowired
    public VectorDBService(EmbeddingService embeddingService) {
        this.embeddingService = embeddingService;
    }
    
    @PostConstruct
    public void init() {
        try {
            // 尝试从磁盘加载向量数据库
            if (useVectorDbDisk && loadVectorDbFromDisk()) {
                logger.info("Successfully loaded vector database from disk: {} entries", vectorDb.size());
            } else {
                // 如果没有磁盘数据或加载失败，则处理文档
                loadDocuments();
                // 处理完成后保存到磁盘
                if (useVectorDbDisk) {
                    saveVectorDbToDisk();
                }
            }
        } catch (Exception e) {
            logger.error("Failed to initialize vector database", e);
        }
    }
    
    /**
     * 从磁盘加载向量数据库
     * @return 是否成功加载
     */
    public boolean loadVectorDbFromDisk() {
        Path dbFilePath = Paths.get(vectorDbFile);
        if (!Files.exists(dbFilePath)) {
            logger.info("Vector database file does not exist: {}", vectorDbFile);
            return false;
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(dbFilePath))) {
            // 清空当前向量库
            vectorDb.clear();
            
            // 读取向量数量
            int size = ois.readInt();
            logger.info("Loading {} vector entries from disk...", size);
            
            // 读取所有向量条目
            for (int i = 0; i < size; i++) {
                VectorEntry entry = (VectorEntry) ois.readObject();
                vectorDb.add(entry);
                
                // 定期显示加载进度
                if ((i + 1) % 1000 == 0 || i == size - 1) {
                    logger.info("Loaded {}/{} vector entries", i + 1, size);
                }
            }
            
            logger.info("Vector database loaded from disk successfully");
            return true;
        } catch (Exception e) {
            logger.error("Failed to load vector database from disk", e);
            // 加载失败时清空向量库，确保不会有部分加载的数据
            vectorDb.clear();
            return false;
        }
    }
    
    /**
     * 将向量数据库保存到磁盘
     */
    public void saveVectorDbToDisk() {
        if (vectorDb.isEmpty()) {
            logger.warn("Vector database is empty, nothing to save");
            return;
        }
        
        Path dbFilePath = Paths.get(vectorDbFile);
        // 确保父目录存在
        try {
            Files.createDirectories(dbFilePath.getParent());
        } catch (IOException e) {
            logger.error("Failed to create directories for vector database file", e);
            return;
        }
        
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(dbFilePath))) {
            // 写入向量数量
            oos.writeInt(vectorDb.size());
            logger.info("Saving {} vector entries to disk...", vectorDb.size());
            
            // 写入所有向量条目
            int count = 0;
            for (VectorEntry entry : vectorDb) {
                oos.writeObject(entry);
                count++;
                
                // 定期显示保存进度
                if (count % 1000 == 0 || count == vectorDb.size()) {
                    logger.info("Saved {}/{} vector entries", count, vectorDb.size());
                }
            }
            
            logger.info("Vector database saved to disk successfully: {}", vectorDbFile);
        } catch (IOException e) {
            logger.error("Failed to save vector database to disk", e);
        }
    }
    
    public void loadDocuments() throws IOException, TranslateException {
        logger.info("Loading documents from {}", knowledgeBasePath);
        vectorDb.clear();
        
        Path dirPath = Paths.get(knowledgeBasePath);
        if (!Files.exists(dirPath)) {
            logger.warn("Knowledge base directory does not exist: {}", knowledgeBasePath);
            return;
        }
        
        try (Stream<Path> paths = Files.walk(dirPath)) {
            List<Path> mdFiles = paths
                .filter(Files::isRegularFile)
                .filter(path -> path.toString().endsWith(".md"))
                .collect(Collectors.toList());
            
            int total = mdFiles.size();
            int processed = 0;
            
            logger.info("Found {} markdown files to process", total);
            
            for (Path file : mdFiles) {
                processFile(file);
                processed++;
                
                // 显示处理进度
                if (processed % 50 == 0 || processed == total) {
                    logger.info("Processed {}/{} files ({}%)", processed, total, 
                                String.format("%.2f", (processed * 100.0 / total)));
                }
            }
        }
        
        logger.info("Loaded {} entries into vector database", vectorDb.size());
    }
    
    private void processFile(Path file) throws IOException, TranslateException {
        String fileName = file.getFileName().toString();
        // Java 8兼容的文件读取方式
        String content = new String(Files.readAllBytes(file), StandardCharsets.UTF_8);
        
        // 提取文档标题（如果有）
        String title = extractTitle(content);
        
        // 创建元数据
        Map<String, String> metadata = new HashMap<>();
        metadata.put("source", fileName);
        metadata.put("title", title != null ? title : fileName);
        metadata.put("path", file.toString());
        
        // 改进的文本分块策略：考虑语义完整性
        List<String> chunks = splitTextIntoChunks(content);
        
        for (int i = 0; i < chunks.size(); i++) {
            String chunk = chunks.get(i);
            if (chunk.trim().isEmpty()) continue;
            
            // 提取该块中的最相关章节
            String sectionTitle = extractSectionTitle(chunk, title);
            
            // 生成嵌入向量
            float[] vector = embeddingService.embed(chunk);
            
            // 添加块特定的元数据
            Map<String, String> chunkMetadata = new HashMap<>(metadata);
            chunkMetadata.put("chunk_index", String.valueOf(i));
            chunkMetadata.put("section", sectionTitle);
            
            // 创建唯一ID并存储
            String id = fileName + "-" + UUID.randomUUID().toString();
            vectorDb.add(new VectorEntry(id, chunk, vector, chunkMetadata));
        }
    }
    
    // 提取文档标题
    private String extractTitle(String content) {
        Pattern pattern = Pattern.compile("^#\\s+(.+)$", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(content);
        return matcher.find() ? matcher.group(1).trim() : null;
    }
    
    // 提取块中的最相关章节标题
    private String extractSectionTitle(String chunk, String docTitle) {
        // 正则模式匹配二级和三级标题
        String[] patterns = {
            "^##\\s+(.+)$",  // 二级标题
            "^###\\s+(.+)$"  // 三级标题
        };
        
        for (String patternStr : patterns) {
            Pattern pattern = Pattern.compile(patternStr, Pattern.MULTILINE);
            Matcher matcher = pattern.matcher(chunk);
            if (matcher.find()) {
                String section = matcher.group(1).trim();
                logger.debug("Found section title: {}", section);
                return section;
            }
        }
        
        // 如果没有找到二级或三级标题，返回文档标题
        return docTitle;
    }
    
    // 改进的文本分块方法，考虑语义完整性
    private List<String> splitTextIntoChunks(String text) {
        List<String> chunks = new ArrayList<>();
        
        // 使用标题作为分割点
        Pattern headingPattern = Pattern.compile("^(#{1,4})\\s+(.+)$", Pattern.MULTILINE);
        Matcher headingMatcher = headingPattern.matcher(text);
        
        // 记录所有标题的位置和级别
        List<Integer> headingPositions = new ArrayList<>();
        List<Integer> headingLevels = new ArrayList<>();
        List<String> headingTexts = new ArrayList<>();
        
        while (headingMatcher.find()) {
            headingPositions.add(headingMatcher.start());
            headingLevels.add(headingMatcher.group(1).length());  // 标题级别 (# = 1, ## = 2, ### = 3)
            headingTexts.add(headingMatcher.group(2).trim());
        }
        
        // 添加文档结尾位置
        headingPositions.add(text.length());
        headingLevels.add(0);
        headingTexts.add("");
        
        // 优先保留重要内容的关键词
        List<String> importantKeywords = Arrays.asList(
            "usage", "function", "behavior", "mating", "breeding", "effect", "特性", "功能", 
            "用途", "行为", "繁殖", "发情", "效果", "作用", "特点", "ability", "能力", "属性"
        );
        
        if (headingPositions.size() <= 1) {
            // 如果没有找到标题或只有一个标题，则使用简单的段落分块
            return splitByParagraphs(text);
        }
        
        // 按标题分块
        for (int i = 0; i < headingPositions.size() - 1; i++) {
            int start = headingPositions.get(i);
            int end = headingPositions.get(i + 1);
            int level = headingLevels.get(i);
            String headingText = headingTexts.get(i);
            
            // 提取当前标题和内容
            String section = text.substring(start, end);
            
            // 检查本节是否包含重要关键词
            boolean containsImportantContent = importantKeywords.stream()
                .anyMatch(keyword -> section.toLowerCase().contains(keyword.toLowerCase()));
            
            // 如果本节包含重要内容或不超过最大块大小，则作为一个块
            if (section.length() <= chunkSize || containsImportantContent) {
                chunks.add(section);
            } else {
                // 否则将节分成多个块，但保留标题
                String heading = text.substring(start, text.indexOf("\n", start) + 1);
                String content = text.substring(text.indexOf("\n", start) + 1, end);
                
                // 为大的内容块创建子块，保持段落完整性
                List<String> contentChunks = splitByParagraphsPreservingContext(content);
                
                // 为每个子块添加适当的标题
                for (int j = 0; j < contentChunks.size(); j++) {
                    StringBuilder chunkBuilder = new StringBuilder();
                    chunkBuilder.append(heading);
                    if (j > 0) {
                        chunkBuilder.append("(续 ").append(j + 1).append(")\n");
                    }
                    chunkBuilder.append(contentChunks.get(j));
                    chunks.add(chunkBuilder.toString());
                }
            }
        }
        
        return chunks;
    }
    
    // 改进的段落分块方法，优先保持相关段落在一起
    private List<String> splitByParagraphsPreservingContext(String text) {
        List<String> chunks = new ArrayList<>();
        
        // 使用空行分割段落
        String[] paragraphs = text.split("\n\n");
        
        // 查找可能的关联段落（比如列表项、相关描述等）
        List<List<String>> groupedParagraphs = new ArrayList<>();
        List<String> currentGroup = new ArrayList<>();
        
        for (String para : paragraphs) {
            para = para.trim();
            if (para.isEmpty()) continue;
            
            // 如果当前段落看起来像是列表的一部分或者与前一个段落紧密相关
            boolean isListItem = para.matches("^\\s*[\\*\\-\\d+\\.)].*$");
            boolean isRelatedToPrevious = !currentGroup.isEmpty() && 
                (currentGroup.get(currentGroup.size()-1).endsWith(":") || 
                 para.startsWith("such as") || para.startsWith("for example") ||
                 para.startsWith("e.g.") || para.startsWith("i.e."));
            
            if (isListItem || isRelatedToPrevious) {
                // 添加到当前组
                currentGroup.add(para);
            } else if (!currentGroup.isEmpty()) {
                // 保存当前组并开始新组
                groupedParagraphs.add(new ArrayList<>(currentGroup));
                currentGroup.clear();
                currentGroup.add(para);
            } else {
                // 开始新组
                currentGroup.add(para);
            }
        }
        
        // 添加最后一组
        if (!currentGroup.isEmpty()) {
            groupedParagraphs.add(currentGroup);
        }
        
        // 现在根据组来构建块
        StringBuilder currentChunk = new StringBuilder();
        
        for (List<String> group : groupedParagraphs) {
            // 计算这个组的总长度
            int groupLength = group.stream().mapToInt(String::length).sum() + 
                             (group.size() - 1) * 2; // 考虑段落间的换行
            
            // 如果添加这个组会导致块过大，并且当前块不为空
            if (currentChunk.length() > 0 && currentChunk.length() + groupLength > chunkSize) {
                // 保存当前块
                chunks.add(currentChunk.toString());
                
                // 处理重叠
                if (chunkOverlap > 0 && currentChunk.length() > chunkOverlap) {
                    int lastParaStart = findLastParagraphStart(currentChunk.toString(), chunkOverlap);
                    if (lastParaStart >= 0) {
                        currentChunk = new StringBuilder(currentChunk.substring(lastParaStart));
                    } else {
                        currentChunk = new StringBuilder();
                    }
                } else {
                    currentChunk = new StringBuilder();
                }
            }
            
            // 添加当前组的所有段落
            boolean first = true;
            for (String para : group) {
                if (!first && currentChunk.length() > 0) {
                    currentChunk.append("\n\n");
                }
                currentChunk.append(para);
                first = false;
            }
        }
        
        // 添加最后一个块
        if (currentChunk.length() > 0) {
            chunks.add(currentChunk.toString());
        }
        
        return chunks;
    }
    
    // 保留旧的段落分割方法作为后备
    private List<String> splitByParagraphs(String text) {
        List<String> chunks = new ArrayList<>();
        
        // 按段落分割
        String[] paragraphs = text.split("\n\n");
        StringBuilder currentChunk = new StringBuilder();
        
        for (String paragraph : paragraphs) {
            paragraph = paragraph.trim();
            if (paragraph.isEmpty()) continue;
            
            // 检查是否添加此段落会导致块过大
            if (currentChunk.length() > 0 && currentChunk.length() + paragraph.length() + 2 > chunkSize) {
                // 保存当前块
                chunks.add(currentChunk.toString());
                
                // 如果需要重叠，保留部分前一个块的内容
                if (chunkOverlap > 0 && currentChunk.length() > chunkOverlap) {
                    // 找到最后一个完整段落的起始位置
                    int lastParaStart = findLastParagraphStart(currentChunk.toString(), chunkOverlap);
                    if (lastParaStart >= 0) {
                        currentChunk = new StringBuilder(currentChunk.substring(lastParaStart));
                    } else {
                        currentChunk = new StringBuilder();
                    }
                } else {
                    currentChunk = new StringBuilder();
                }
            }
            
            // 添加当前段落
            if (currentChunk.length() > 0) {
                currentChunk.append("\n\n");
            }
            currentChunk.append(paragraph);
        }
        
        // 添加最后一个块
        if (currentChunk.length() > 0) {
            chunks.add(currentChunk.toString());
        }
        
        return chunks;
    }
    
    // 找到最后一个段落的起始位置
    private int findLastParagraphStart(String text, int maxLookback) {
        if (text.length() <= maxLookback) {
            return 0;
        }
        
        int searchStart = text.length() - maxLookback;
        int paraStart = text.indexOf("\n\n", searchStart);
        
        if (paraStart >= 0) {
            return paraStart + 2; // 跳过换行符
        } else {
            // 没有找到段落边界，尝试句子边界
            int sentenceStart = Math.max(
                text.lastIndexOf(". ", text.length() - 1),
                Math.max(
                    text.lastIndexOf("! ", text.length() - 1),
                    text.lastIndexOf("? ", text.length() - 1)
                )
            );
            
            if (sentenceStart > searchStart) {
                return sentenceStart + 2; // 跳过句号和空格
            }
        }
        
        // 如果没有找到合适的边界，返回最大回溯位置
        return searchStart;
    }
    
    public List<SearchResult> search(float[] queryVector, int k) {
        logger.info("Searching vector DB, threshold: {}, entries count: {}", similarityThreshold, vectorDb.size());
        
        List<SearchResult> results = vectorDb.stream()
            .map(entry -> {
                double similarity = cosineSimilarity(queryVector, entry.vector());
                // 添加详细的日志，显示每个文档的相似度得分和简要内容
                String previewText = entry.text().length() > 50 
                    ? entry.text().substring(0, 50) + "..." 
                    : entry.text();
                logger.debug("Document: {}, similarity: {}, content: {}", 
                    entry.metadata().getOrDefault("source", "unknown") + "/" + 
                    entry.metadata().getOrDefault("title", "unknown"), 
                    String.format("%.4f", similarity), 
                    previewText);
                
                return new SearchResult(entry.id(), entry.text(), similarity, entry.metadata());
            })
            // 添加相关度阈值过滤
            .filter(result -> {
                boolean matched = result.score() >= similarityThreshold;
                if (matched) {
                    logger.info("Matched document: {}, similarity: {}", 
                        result.metadata().getOrDefault("source", "unknown") + "/" + 
                        result.metadata().getOrDefault("title", "unknown"),
                        String.format("%.4f", result.score()));
                }
                return matched;
            })
            .sorted(Comparator.comparingDouble(SearchResult::score).reversed())
            .limit(k)
            .collect(Collectors.toList());
        
        logger.info("Search results: found {} relevant documents", results.size());
        return results;
    }
    
    private double cosineSimilarity(float[] vectorA, float[] vectorB) {
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        
        for (int i = 0; i < vectorA.length; i++) {
            dotProduct += vectorA[i] * vectorB[i];
            normA += vectorA[i] * vectorA[i];
            normB += vectorB[i] * vectorB[i];
        }
        
        if (normA == 0 || normB == 0) return 0;
        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }
    
    // 获取当前阈值
    public double getSimilarityThreshold() {
        return similarityThreshold;
    }
    
    // 设置新阈值
    public void setSimilarityThreshold(double threshold) {
        this.similarityThreshold = threshold;
    }
    
    // 获取向量数量
    public int getVectorCount() {
        return vectorDb.size();
    }
    
    // 重新加载知识库并保存到磁盘的便捷方法
    public void reloadAndSave() throws IOException, TranslateException {
        loadDocuments();
        if (useVectorDbDisk) {
            saveVectorDbToDisk();
        }
    }
} 