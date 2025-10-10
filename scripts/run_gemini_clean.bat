@echo off
chcp 65001 >nul
echo ========================================
echo 饥荒Wiki数据清洗 - Gemini版
echo ========================================
echo.

REM 检查Python是否安装
python --version >nul 2>&1
if errorlevel 1 (
    echo [错误] 未找到Python，请先安装Python
    pause
    exit /b 1
)

REM 切换到脚本目录
cd /d "%~dp0"

REM 检查是否已安装依赖
python -c "import google.generativeai" >nul 2>&1
if errorlevel 1 (
    echo [提示] 正在安装Gemini API依赖...
    pip install -r requirements_gemini.txt
    echo.
)

echo [提示] 启动清洗脚本...
echo.
echo 使用方法:
echo   1. 测试模式（5个文件）: python gemini_clean_wiki.py --test
echo   2. 处理所有文件: python gemini_clean_wiki.py
echo   3. 处理前50个: python gemini_clean_wiki.py --limit 50
echo   4. 查看帮助: python gemini_clean_wiki.py --help
echo.
echo ========================================
echo.

REM 默认运行测试模式
python gemini_clean_wiki.py %*

echo.
echo ========================================
echo 清洗完成！按任意键退出...
pause >nul



