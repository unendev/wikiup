@echo off
REM 设置控制台编码为UTF-8
chcp 65001

REM 设置JVM参数
set JAVA_OPTS=-Dfile.encoding=UTF-8 -Dsun.jnu.encoding=UTF-8 -Dspring.output.ansi.enabled=ALWAYS

REM 启动Spring Boot应用
mvn spring-boot:run

pause 