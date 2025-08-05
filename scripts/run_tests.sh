#!/bin/bash
echo "运行《饥荒》知识探索平台测试..."
echo

cd ../backend
echo "1. 测试中文嵌入模型..."
mvn test -Dtest=EmbeddingServiceTest
echo

echo "2. 测试向量检索引擎..."
mvn test -Dtest=VectorDBServiceTest
echo

echo "3. 测试DeepSeek API连接..."
mvn test -Dtest=LLMServiceTest
echo

echo "所有测试完成！" 