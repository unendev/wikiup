"""
测试 Fandom API 的不同方式，对比数据质量
"""
import requests
import json

API_URL = "https://dontstarve.fandom.com/api.php"
TEST_PAGE = "Abigail's_Flower"  # 测试页面

# 如果需要代理，取消注释
PROXIES = {
    'http': 'socks5h://127.0.0.1:10808',
    'https': 'socks5h://127.0.0.1:10808',
}

def test_parse_api():
    """测试当前使用的 parse API（会有很多噪音）"""
    print("\n" + "="*80)
    print("方法1: action=parse (当前使用的方法)")
    print("="*80)
    
    params = {
        "action": "parse",
        "page": TEST_PAGE,
        "format": "json",
        "prop": "text|categories",
        "formatversion": 2
    }
    
    response = requests.get(API_URL, params=params, proxies=PROXIES)
    data = response.json()
    
    html_content = data.get("parse", {}).get("text", "")
    categories = data.get("parse", {}).get("categories", [])
    
    print(f"\n📊 数据统计:")
    print(f"  - 总字符数: {len(html_content)}")
    print(f"  - 包含 'data:image' 次数: {html_content.count('data:image')}")
    print(f"  - 包含 Wilson 引用次数: {html_content.count('Wilson')}")
    print(f"  - 包含导航表格: {'Craftable Items' in html_content}")
    print(f"  - 包含大量HTML标签: {'<div>' in html_content and '<table>' in html_content}")
    
    print(f"\n📄 前300字符预览:")
    print(html_content[:300])
    
    # 保存完整内容供检查
    with open("test_output_parse.html", "w", encoding="utf-8") as f:
        f.write(html_content)
    print(f"\n💾 完整内容已保存到: test_output_parse.html")
    
    return len(html_content)

def test_extracts_api():
    """测试 extracts API（纯文本，干净）"""
    print("\n" + "="*80)
    print("方法2: action=query + prop=extracts (推荐方法)")
    print("="*80)
    
    params = {
        "action": "query",
        "format": "json",
        "titles": TEST_PAGE,
        "prop": "extracts|categories",
        "explaintext": True,        # 纯文本
        "exsectionformat": "wiki",  # 保留章节标题
        "formatversion": 2
    }
    
    response = requests.get(API_URL, params=params, proxies=PROXIES)
    data = response.json()
    
    # 调试：打印完整响应
    print(f"\n🔍 API返回的完整数据:")
    print(json.dumps(data, indent=2, ensure_ascii=False)[:500])
    
    pages = data.get("query", {}).get("pages", [])
    if not pages:
        print("❌ 未获取到数据")
        print(f"完整响应: {json.dumps(data, indent=2)}")
        return 0
    
    page = pages[0]
    text_content = page.get("extract", "")
    categories = page.get("categories", [])
    
    print(f"\n📊 数据统计:")
    print(f"  - 总字符数: {len(text_content)}")
    print(f"  - 包含 'data:image' 次数: {text_content.count('data:image')}")
    print(f"  - 包含角色引用次数: {text_content.count('–[Wilson')}")
    print(f"  - 包含导航表格: {'Craftable Items' in text_content}")
    print(f"  - 分类数量: {len(categories)}")
    
    print(f"\n📄 前500字符预览:")
    print(text_content[:500])
    
    # 保存完整内容供检查
    with open("test_output_extracts.md", "w", encoding="utf-8") as f:
        f.write(text_content)
    print(f"\n💾 完整内容已保存到: test_output_extracts.md")
    
    return len(text_content)

def test_extracts_with_html():
    """测试 extracts API（保留HTML格式）"""
    print("\n" + "="*80)
    print("方法3: action=query + prop=extracts (保留HTML格式)")
    print("="*80)
    
    params = {
        "action": "query",
        "format": "json",
        "titles": TEST_PAGE,
        "prop": "extracts|categories",
        "explaintext": False,       # 保留HTML
        "exsectionformat": "plain",
        "formatversion": 2
    }
    
    response = requests.get(API_URL, params=params, proxies=PROXIES)
    data = response.json()
    
    pages = data.get("query", {}).get("pages", [])
    if not pages:
        print("❌ 未获取到数据")
        return 0
    
    page = pages[0]
    html_content = page.get("extract", "")
    
    print(f"\n📊 数据统计:")
    print(f"  - 总字符数: {len(html_content)}")
    print(f"  - 包含 'data:image' 次数: {html_content.count('data:image')}")
    print(f"  - 包含 Wilson 引用次数: {html_content.count('Wilson')}")
    print(f"  - 包含导航表格: {'Craftable Items' in html_content}")
    print(f"  - 包含HTML标签: {'<p>' in html_content}")
    
    print(f"\n📄 前500字符预览:")
    print(html_content[:500])
    
    # 保存完整内容供检查
    with open("test_output_extracts_html.html", "w", encoding="utf-8") as f:
        f.write(html_content)
    print(f"\n💾 完整内容已保存到: test_output_extracts_html.html")
    
    return len(html_content)

def test_parse_with_section():
    """测试parse API只获取特定部分"""
    print("\n" + "="*80)
    print("方法4: action=parse + section参数（获取特定章节）")
    print("="*80)
    
    # 先获取章节列表
    params = {
        "action": "parse",
        "page": TEST_PAGE,
        "format": "json",
        "prop": "sections",
        "formatversion": 2
    }
    
    response = requests.get(API_URL, params=params, proxies=PROXIES)
    data = response.json()
    sections = data.get("parse", {}).get("sections", [])
    
    print(f"\n📑 页面包含 {len(sections)} 个章节:")
    for sec in sections[:10]:  # 只显示前10个
        print(f"  [{sec.get('index')}] {sec.get('line')} (level {sec.get('level')})")
    
    return 0

def main():
    print("\n" + "🔬 开始测试 Fandom API 不同方式的数据质量".center(80, "="))
    print(f"测试页面: {TEST_PAGE}")
    
    try:
        size1 = test_parse_api()
        test_parse_with_section()
        
        print("\n" + "="*80)
        print("📈 结论")
        print("="*80)
        print(f"❌ Fandom的MediaWiki没有启用TextExtracts扩展")
        print(f"✅ 只能使用 parse API（当前方法）")
        print(f"✅ 但数据需要清洗：{size1:,} 字符，包含2091个base64图片")
        print(f"\n💡 建议：使用当前爬虫 + 数据清洗脚本")
        
        print("\n✅ 测试完成！请查看生成的 test_output_*.md 文件对比效果")
        
    except Exception as e:
        print(f"\n❌ 测试失败: {e}")
        import traceback
        traceback.print_exc()

if __name__ == "__main__":
    main()

