"""
使用Gemini API清洗饥荒Wiki数据
只保留属性、攻略等游玩相关内容
"""
import os
import re
import time
import json
from pathlib import Path
from typing import Optional
import google.generativeai as genai

# Gemini API配置
GEMINI_API_KEY = "AIzaSyCV3RYFs_9DAeRkFBxFUfcMifYx3PIz8ds"
GEMINI_MODEL = "gemini-1.5-flash"

# 清洗提示词
CLEANING_PROMPT = """You are a game wiki content curator for Don't Starve Together. 
Your task is to clean the following wiki markdown content by ONLY keeping gameplay-relevant information.

**KEEP these sections:**
- Properties (stats, attributes, effects, durability)
- Crafting recipes and requirements
- Mechanics and how items/characters work
- Strategies, tips, and tactics
- Locations and spawn information
- Item tables (prices, stats, loot tables)
- Combat information
- Game mechanics explanations
- Resource gathering information

**REMOVE these sections:**
- All character quotes (e.g. "Wilson says...")
- Base64 image placeholders and image URLs
- Gallery sections
- Trivia sections
- References sections
- Navigation tables (large tables with crafting tabs)
- Blueprint Gallery
- Empty tables and empty sections
- Social media links
- Version history
- Bug sections

**Additional cleaning rules:**
1. Keep the frontmatter (YAML metadata between ---) unchanged
2. Remove all base64 image data: ![](data:image/...)
3. Remove character quotes that start with **"** and end with **"**
4. Remove attribution lines like "–[Wilson](/wiki/Wilson)"
5. Convert tables to clean readable format when possible
6. Keep markdown formatting for readability
7. Remove empty headings (## Heading[] with no content)

Please output ONLY the cleaned markdown content, preserving the structure and formatting of gameplay-relevant information.

---
ORIGINAL CONTENT:
"""

def init_gemini():
    """初始化Gemini API"""
    genai.configure(api_key=GEMINI_API_KEY)
    model = genai.GenerativeModel(GEMINI_MODEL)
    return model

def clean_with_gemini(content: str, model) -> Optional[str]:
    """使用Gemini清洗内容"""
    try:
        # 如果内容太短，直接返回
        if len(content) < 100:
            return content
        
        # 发送请求到Gemini
        prompt = CLEANING_PROMPT + content
        
        # 配置生成参数
        generation_config = {
            "temperature": 0.1,  # 低温度以获得更一致的输出
            "top_p": 0.95,
            "top_k": 40,
            "max_output_tokens": 8192,
        }
        
        response = model.generate_content(
            prompt,
            generation_config=generation_config
        )
        
        # 获取清洗后的内容
        cleaned_content = response.text
        
        # 后处理：确保frontmatter存在
        if content.startswith('---') and not cleaned_content.startswith('---'):
            # 提取原始frontmatter
            parts = content.split('---', 2)
            if len(parts) >= 3:
                frontmatter = f"---{parts[1]}---"
                cleaned_content = f"{frontmatter}\n\n{cleaned_content}"
        
        return cleaned_content
        
    except Exception as e:
        print(f"    ❌ Gemini API错误: {e}")
        return None

def clean_single_file(input_file: Path, output_file: Path, model) -> dict:
    """清洗单个文件"""
    print(f"\n处理: {input_file.name}")
    
    try:
        # 读取原始内容
        with open(input_file, 'r', encoding='utf-8') as f:
            content = f.read()
        
        original_size = len(content)
        print(f"  - 原始大小: {original_size:,} 字符")
        
        # 使用Gemini清洗
        print(f"  - 正在调用Gemini API清洗...")
        cleaned_content = clean_with_gemini(content, model)
        
        if cleaned_content is None:
            print(f"  ❌ 清洗失败，跳过")
            return None
        
        cleaned_size = len(cleaned_content)
        reduction = (1 - cleaned_size / original_size) * 100 if original_size > 0 else 0
        
        print(f"  - 清洗后大小: {cleaned_size:,} 字符")
        print(f"  - 减少: {reduction:.1f}%")
        
        # 保存清洗后的内容
        with open(output_file, 'w', encoding='utf-8') as f:
            f.write(cleaned_content)
        
        print(f"  ✅ 成功保存到: {output_file.name}")
        
        return {
            'file': input_file.name,
            'original_size': original_size,
            'cleaned_size': cleaned_size,
            'reduction': reduction,
            'success': True
        }
        
    except Exception as e:
        print(f"  ❌ 处理失败: {e}")
        return {
            'file': input_file.name,
            'success': False,
            'error': str(e)
        }

def clean_directory(input_dir: str, output_dir: str, limit: Optional[int] = None, 
                    delay: float = 1.0, resume_from: Optional[str] = None):
    """批量清洗目录下的所有md文件"""
    
    input_path = Path(input_dir)
    output_path = Path(output_dir)
    
    if not input_path.exists():
        print(f"❌ 输入目录不存在: {input_dir}")
        return
    
    # 创建输出目录
    output_path.mkdir(parents=True, exist_ok=True)
    
    # 初始化Gemini
    print("🔧 初始化Gemini API...")
    model = init_gemini()
    print("✅ Gemini API已初始化")
    
    # 获取所有md文件
    md_files = sorted(list(input_path.glob('*.md')))
    
    # 如果指定了resume_from，跳过已处理的文件
    if resume_from:
        try:
            resume_idx = [f.name for f in md_files].index(resume_from)
            md_files = md_files[resume_idx:]
            print(f"\n📌 从文件 '{resume_from}' 恢复处理")
        except ValueError:
            print(f"\n⚠️  找不到恢复点文件 '{resume_from}'，从头开始")
    
    if limit:
        md_files = md_files[:limit]
        print(f"\n🔬 限制模式：只处理 {limit} 个文件")
    
    total_files = len(md_files)
    print(f"\n📁 共找到 {total_files} 个 markdown 文件")
    print(f"📂 输入目录: {input_dir}")
    print(f"📂 输出目录: {output_dir}")
    print(f"⏱️  API调用间隔: {delay}秒")
    print("="*80)
    
    stats = []
    failed_files = []
    
    for i, md_file in enumerate(md_files, 1):
        print(f"\n[{i}/{total_files}]", end=" ")
        output_file = output_path / md_file.name
        
        # 检查是否已经处理过
        if output_file.exists():
            print(f"⏭️  跳过已存在: {md_file.name}")
            continue
        
        result = clean_single_file(md_file, output_file, model)
        
        if result:
            if result.get('success'):
                stats.append(result)
            else:
                failed_files.append(result)
        
        # API限速：避免触发配额限制
        if i < total_files:
            print(f"  ⏳ 等待 {delay} 秒...")
            time.sleep(delay)
    
    # 打印总结
    print("\n" + "="*80)
    print("📊 清洗统计")
    print("="*80)
    
    if stats:
        total_original = sum(s['original_size'] for s in stats)
        total_cleaned = sum(s['cleaned_size'] for s in stats)
        avg_reduction = sum(s['reduction'] for s in stats) / len(stats)
        
        print(f"✅ 成功处理: {len(stats)}/{total_files} 个文件")
        print(f"📉 原始总大小: {total_original:,} 字符 ({total_original/1024/1024:.2f} MB)")
        print(f"📉 清洗后大小: {total_cleaned:,} 字符 ({total_cleaned/1024/1024:.2f} MB)")
        print(f"📉 平均减少: {avg_reduction:.1f}%")
        print(f"💾 节省空间: {(total_original-total_cleaned)/1024/1024:.2f} MB")
        
        # 显示减少最多的前5个文件
        print(f"\n🏆 减少最多的文件:")
        top5 = sorted(stats, key=lambda x: x['reduction'], reverse=True)[:5]
        for s in top5:
            print(f"  - {s['file']}: {s['reduction']:.1f}% ({s['original_size']:,} → {s['cleaned_size']:,})")
    
    if failed_files:
        print(f"\n❌ 失败的文件 ({len(failed_files)}):")
        for f in failed_files[:10]:  # 只显示前10个
            print(f"  - {f['file']}: {f.get('error', 'Unknown error')}")
        if len(failed_files) > 10:
            print(f"  ... 还有 {len(failed_files) - 10} 个失败")
    
    # 保存统计信息
    stats_file = output_path / '_cleaning_stats.json'
    with open(stats_file, 'w', encoding='utf-8') as f:
        json.dump({
            'total_files': total_files,
            'successful': len(stats),
            'failed': len(failed_files),
            'stats': stats,
            'failed_files': failed_files
        }, f, indent=2, ensure_ascii=False)
    print(f"\n📄 统计信息已保存到: {stats_file}")
    
    print("\n✅ 清洗完成！")

def main():
    import argparse
    
    parser = argparse.ArgumentParser(
        description='使用Gemini API清洗饥荒Wiki数据',
        formatter_class=argparse.RawDescriptionHelpFormatter,
        epilog="""
示例用法:
  # 测试模式：只处理5个文件
  python gemini_clean_wiki.py --test
  
  # 处理所有文件
  python gemini_clean_wiki.py
  
  # 处理前50个文件
  python gemini_clean_wiki.py --limit 50
  
  # 从特定文件恢复处理
  python gemini_clean_wiki.py --resume "some-file.md"
  
  # 自定义输入输出目录
  python gemini_clean_wiki.py -i backend/data/dst -o backend/data/dst_deep
        """
    )
    
    parser.add_argument('--input', '-i', 
                       default='backend/data/dst',
                       help='输入目录（默认: backend/data/dst）')
    parser.add_argument('--output', '-o',
                       default='backend/data/dst_deep',
                       help='输出目录（默认: backend/data/dst_deep）')
    parser.add_argument('--limit', '-l',
                       type=int,
                       help='只处理前N个文件')
    parser.add_argument('--test', '-t',
                       action='store_true',
                       help='测试模式：只处理5个文件')
    parser.add_argument('--delay', '-d',
                       type=float,
                       default=1.0,
                       help='API调用间隔（秒，默认1.0）')
    parser.add_argument('--resume', '-r',
                       type=str,
                       help='从指定文件名恢复处理')
    
    args = parser.parse_args()
    
    limit = args.limit
    if args.test:
        limit = 5
    
    clean_directory(
        args.input, 
        args.output, 
        limit=limit,
        delay=args.delay,
        resume_from=args.resume
    )

if __name__ == '__main__':
    main()



