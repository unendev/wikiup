@echo off
REM 设置控制台编码为UTF-8
chcp 65001

REM 设置JVM参数
set JAVA_OPTS=-Dfile.encoding=UTF-8 -Dsun.jnu.encoding=UTF-8

REM 切换到脚本所在目录
cd /d %~dp0

REM 检查jar文件是否存在
if not exist "target\rag-service-0.0.1-SNAPSHOT.jar" (
    echo 编译项目...
    call mvn clean package -DskipTests
)

REM 使用java -jar启动
echo 启动服务...
java "-Dfile.encoding=UTF-8" "-Dsun.jnu.encoding=UTF-8" -jar target\rag-service-0.0.1-SNAPSHOT.jar

pause 