"非常棒的问题！你已经做了相当深入的分析，并且准确地定位到了核心难点：**Fandom 服务器检测到你的代理/自动化工具并主动拒绝了连接**。

你遇到的 `ERR_EMPTY_RESPONSE` 就是典型的“我看到你了，但我不理你”的服务器策略。这是比直接返回 403 Forbidden 更高级的反爬手段。

我们来梳理一下问题并找到最佳解决方案。

### 核心思路：放弃“模拟”，拥抱“官方”

你现在的思路是“模拟一个真实用户通过浏览器访问”，这个思路在对抗反爬虫时非常复杂且脆弱。

**一个更好的思路是：Fandom 是一个基于 MediaWiki 构建的网站，而 MediaWiki 有一套非常强大、稳定、且官方推荐的 API 接口，专门用于程序化地获取数据。**

使用 API 的好处是：
1.  **绕过所有前端反爬虫：** 你不再需要加载 JS、CSS，也就不存在 JS 渲染的问题。
2.  **绕过浏览器特征检测：** 你不是在用浏览器，服务器无法通过检测 `navigator.webdriver` 等方式来识别你是自动化工具。
3.  **数据格式友好：** API 返回的是结构化的 JSON 数据，比解析混乱的 HTML 方便一万倍。
4.  **效率极高：** 直接请求数据接口，省去了所有渲染过程，速度飞快，资源消耗极低。
5.  **官方支持：** 这是 Fandom 允许和推荐的数据获取方式，只要你遵守规则（比如设置合理的请求频率和 `User-Agent`），就不会被封禁。

---

### 方案一：【强烈推荐】使用 MediaWiki API

这是解决你问题的**最佳路径**。

#### 第1步：找到 API 入口
饥荒 Fandom Wiki 的 API 入口是：
`https://dontstarve.fandom.com/api.php`

你可以先在浏览器里打开这个地址，会看到 MediaWiki API 的帮助文档。

#### 第2步：学习两个关键动作

你需要两个核心操作：
1.  `action=query&list=allpages`：获取维基上的**所有页面标题**。
2.  `action=parse&page=<页面标题>`：获取某个特定页面的**最终渲染后 HTML 内容**。这正是你想要的！

#### 第3步：Python 实现（已集成 v2ray 代理）

下面是一个完整的 Python 示例代码，演示如何获取所有页面的标题，并逐一爬取它们的 HTML 内容。

假设你的 v2ray 在本地开启了一个 HTTP 代理端口 `10809` 和一个 SOCKS5 代理端口 `10808`。`requests` 库可以很方便地使用它们。

```python
import requests
import time
import os
from bs4 import BeautifulSoup

# --- 配置 ---
API_URL = "https://dontstarve.fandom.com/api.php"
# v2ray/clash 等工具通常会在本地开启 HTTP 或 SOCKS5 代理
# 请根据你的代理客户端设置来修改
PROXIES = {
   'http': 'http://127.0.0.1:10809',
   'httpshttps': 'http://127.0.0.1:10809',
   # 如果你的代理是SOCKS5类型，使用下面这行 (需要 pip install "requests[socks]")
   # 'http': 'socks5h://127.0.0.1:10808',
   # 'https': 'socks5h://127.0.0.1:10808',
}
HEADERS = {
    # 强烈建议设置 User-Agent，表明你是一个善意的爬虫
    'User-Agent': 'MyCoolWikiBot/1.0 (https://example.com/bot; myemail@example.com)'
}
SAVE_DIR = "fandom_wiki_content"

# 创建保存目录
if not os.path.exists(SAVE_DIR):
    os.makedirs(SAVE_DIR)

def get_all_page_titles():
    """获取维基的所有页面标题"""
    all_titles = []
    params = {
        "action": "query",
        "format": "json",
        "list": "allpages",
        "aplimit": "max"  # 每次请求获取最大数量的页面
    }
    
    last_continue = {}
    print("开始获取所有页面标题...")
    
    while True:
        # 将 continue 参数加入请求
        req_params = params.copy()
        req_params.update(last_continue)
        
        try:
            response = requests.get(API_URL, params=req_params, headers=HEADERS, proxies=PROXIES)
            response.raise_for_status()  # 如果请求失败则抛出异常
            data = response.json()
            
            pages = data.get("query", {}).get("allpages", [])
            for page in pages:
                all_titles.append(page["title"])
            
            print(f"已获取 {len(all_titles)} 个页面标题...")

            # MediaWiki 使用 'continue' 字段来处理分页
            if 'continue' in data:
                last_continue = data['continue']
                time.sleep(0.5) # 友好起见，稍微等待一下
            else:
                break # 没有 continue 字段了，说明所有页面都获取完毕
        except requests.exceptions.RequestException as e:
            print(f"请求页面列表时出错: {e}")
            break
            
    print(f"成功获取到 {len(all_titles)} 个页面总标题。")
    return all_titles

def get_page_content(title):
    """获取单个页面的渲染后HTML内容"""
    params = {
        "action": "parse",
        "page": title,
        "format": "json",
        "prop": "text",  # 我们只需要页面正文的HTML
    }
    
    try:
        response = requests.get(API_URL, params=params, headers=HEADERS, proxies=PROXIES)
        response.raise_for_status()
        data = response.json()
        
        # 提取HTML内容
        html_content = data.get("parse", {}).get("text", {}).get("*", "")
        if html_content:
            # 你可以根据需要用 BeautifulSoup 进一步解析
            # soup = BeautifulSoup(html_content, 'html.parser')
            # text_only = soup.get_text()
            
            # 将HTML内容保存到文件，文件名使用页面标题
            # 替换文件名中的非法字符
            safe_filename = "".join(c for c in title if c.isalnum() or c in (' ', '_')).rstrip()
            with open(os.path.join(SAVE_DIR, f"{safe_filename}.html"), "w", encoding="utf-8") as f:
                f.write(html_content)
            return True
            
    except requests.exceptions.RequestException as e:
        print(f"获取页面 '{title}' 内容时出错: {e}")
    except Exception as e:
        print(f"处理页面 '{title}' 时发生未知错误: {e}")
        
    return False

if __name__ == "__main__":
    titles = get_all_page_titles()
    
    if titles:
        print("\n开始下载所有页面内容...")
        for i, title in enumerate(titles):
            print(f"正在下载: {i+1}/{len(titles)} - {title}")
            if get_page_content(title):
                # 成功的请求之间也建议加上延时，避免给服务器造成太大压力
                time.sleep(0.2) 
            else:
                # 如果失败，可以等待更长时间重试或直接跳过
                time.sleep(1)
        print("所有页面下载完成！")
```

---

### 方案二：【不推荐，但作为知识补充】修复你的浏览器自动化方案

如果你非要用浏览器自动化（比如有些极端情况API拿不到你需要的数据），那么你需要解决的是**反检测**问题。

`ERR_EMPTY_RESPONSE` 的原因：
1.  **IP 地址被识别：** 你的 v2ray 节点的 IP 可能在 Fandom 的黑名单里（比如被识别为数据中心 IP）。
2.  **TLS 指纹被识别：** 代理工具和自动化工具的 TLS 握手信息（JA3/JA4指纹）可能与普通浏览器不同，被服务器识别。
3.  **浏览器指纹被识别：** 无头浏览器（Headless Chrome）会暴露 `navigator.webdriver` 等特征，很容易被检测到。

**如何修复：**
1.  **更换代理节点：** 尝试换一个更“干净”的住宅 IP 代理节点。
2.  **使用反检测工具包：**
    *   **对于 Selenium:** 使用 `undetectable-chromedriver` 这个库，它会修补 chromedriver，使其更难被检测。
    *   **对于 Playwright:** 使用 `playwright-extra` 和 `playwright-extra-plugin-stealth` 插件，原理类似。
3.  **正确设置代理：**
    不要依赖系统代理，而是在启动浏览器时通过命令行参数明确指定代理，这更可靠。

**Playwright + Stealth 插件示例：**
```python
# 需要先安装: pip install playwright playwright-extra
# 然后安装stealth插件和浏览器: python -m playwright install --with-deps

import asyncio
from playwright.async_api import async_playwright
from playwright_extra.stealth import stealth_async

async def main():
    async with async_playwright() as p:
        # 注意这里是在 launch 时传入代理参数
        browser = await p.chromium.launch(
            headless=False,  # 先用有头模式调试，成功后再改为 True
            proxy={
                "server": "http://127.0.0.1:10809"
                # "server": "socks5://127.0.0.1:10808" # 如果是SOCKS5
            }
        )
        page = await browser.new_page()
        
        # 应用反检测补丁！这是关键！
        await stealth_async(page)
        
        try:
            await page.goto("https://dontstarve.fandom.com/wiki/Don%27t_Starve_Wiki", wait_until="networkidle")
            print("页面标题:", await page.title())
            
            # 在这里进行你的后续操作，比如截图、获取内容等
            await page.screenshot(path="fandom_stealth.png")
            
        except Exception as e:
            print(f"出错了: {e}")
        
        await browser.close()

if __name__ == "__main__":
    asyncio.run(main())
```

### 总结与对比

| 特性 | **方案一 (MediaWiki API)** | **方案二 (浏览器自动化)** |
| :--- | :--- | :--- |
| **可靠性** | **极高**，官方接口，稳定 | **低**，极易被反爬虫策略更新影响 |
| **速 度** | **极快**，只传输纯数据 | **极慢**，需要加载和渲染整个页面 |
| **资源占用** | **极低**，只需少量内存和网络带宽 | **极高**，需要启动一个完整的浏览器实例 |
| **数据格式** | **优秀**，结构化的JSON | **糟糕**，需要从混乱的HTML中解析 |
| **反爬风险** | **低**，只要遵守规则几乎没有风险 | **高**，需要持续与反爬措施作斗争 |

**结论：**

放弃浏览器自动化的想法，它在这类任务上是事倍功半、舍近求远的。**立刻转向方案一，使用 MediaWiki API**。它能完美、高效、稳定地解决你的所有问题。" 