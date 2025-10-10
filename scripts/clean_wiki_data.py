"""
饥荒Wiki数据清洗脚本 - 移除噪音数据
"""
import os
import re
from pathlib import Path
import shutil

def clean_wiki_markdown(content):
    """清洗wiki markdown内容 - 深度版"""
    
    print("  - 清洗前字符数:", len(content))
    
    # 1. 删除base64图片占位符
    content = re.sub(r'!\[.*?\]\(data:image/[^)]+\)', '', content)
    
    # 2. 删除所有角色引用（包括引用文字）
    # 方式A: 带图片的完整引用
    content = re.sub(
        r'!\[\]\(data:image[^)]+\)\s*\*\*"?\*\*.*?\*\*"?\*\*\s*–\[.*?\]\([^)]*\)',
        '',
        content,
        flags=re.DOTALL
    )
    
    # 方式B: 删除角色引用标记
    content = re.sub(
        r'\n\*\*[""""][^*\n]+\*\*[""""]\s*\n+–\[[^\]]+\]\([^)]*\)\s*\n',
        '\n',
        content
    )
    
    # 方式C: 删除残留的角色标记
    content = re.sub(r'\n+–\[[^\]]+\]\([^)]*\)\s*\n', '\n', content)
    
    # 方式D: 删除所有角色引用文字（精确匹配版本）
    # 格式：**"**文字内容**"**
    lines = content.split('\n')
    cleaned_lines = []
    for line in lines:
        stripped = line.strip()
        # 精确匹配角色引用格式
        if stripped.startswith('**"**') and stripped.endswith('**"**') and len(stripped) > 10:
            continue  # 跳过角色引用行
        cleaned_lines.append(line)
    content = '\n'.join(cleaned_lines)
    
    # 3. 删除Trivia章节（奇闻异事，对游戏攻略无用）
    content = re.sub(
        r'## (?:Placeholder )?Trivia\[\].*?(?=##|\Z)',
        '',
        content,
        flags=re.DOTALL
    )
    
    # 4. 删除底部巨大的导航表格
    content = re.sub(
        r'\|\s+\*{3}Don\'t Starve.*?\[Craftable\].*$',
        '',
        content,
        flags=re.DOTALL
    )
    
    # 5. 删除空的引用链接
    content = re.sub(r'\*\s+\[(?:DS|RoG|SW|Ham|DST)\]\(#\)\s*\n', '', content)
    
    # 6. 删除 Gallery 章节
    content = re.sub(
        r'## (?:Blueprint )?Gallery\[\].*?(?=##|\Z)',
        '',
        content,
        flags=re.DOTALL
    )
    
    # 7. 删除 References 章节
    content = re.sub(
        r'## References\[\].*?(?=##|\Z)',
        '',
        content,
        flags=re.DOTALL
    )
    
    # 8. 删除图片URL残留（See also行）
    content = re.sub(
        r'\*https://static\.wikia\.nocookie\.net/[^\s]+\s+See also:.*?\*',
        '',
        content
    )
    
    # 9. 将表格转换为自然语言
    content = convert_tables_to_text(content)
    
    # 10. 清理多余空行（超过2个连续换行）
    content = re.sub(r'\n{3,}', '\n\n', content)
    
    # 11. 清理表格中的空行和空列
    content = re.sub(r'\|\s+\|\s*\n', '', content)
    
    # 12. 删除重复的标题（同名标题在前10行内重复）
    lines = content.split('\n')
    seen_headers = {}
    cleaned = []
    for i, line in enumerate(lines):
        stripped = line.strip()
        
        # 跳过角色引用（防止被重新加回）
        if stripped.startswith('**"**') and stripped.endswith('**"**') and len(stripped) > 10:
            continue
        
        # 检查重复标题
        if line.startswith('## '):
            header = line.strip()
            if header in seen_headers and i - seen_headers[header] < 15:
                continue  # 跳过重复标题
            seen_headers[header] = i
        
        cleaned.append(line)
    content = '\n'.join(cleaned)
    
    print("  - 清洗后字符数:", len(content))
    
    return content.strip()

def convert_tables_to_text(content):
    """将markdown表格转换为自然语言"""
    
    # 1. 转换Sanity属性表格
    content = re.sub(
        r'\|\s*Sanity\s*\|\s*\n\|\s*---\s*\|\s*\n\|\s*([^|\n]+)\s*\|',
        r'**Sanity Cost:** \1',
        content
    )
    
    # 2. 转换Effects表格（DST版本）
    content = re.sub(
        r'\|\s*Sanity\s*\|\s*\n\|\s*---\s*\|\s*\n\|\s*\+(\d+)\s+Summon\s+-(\d+)\s+Unsummon\s*\|',
        r'**Sanity:** +\1 when summoning, -\2 when unsummoning',
        content
    )
    
    # 3. 转换Recipe表格
    content = re.sub(
        r'\|\s*\[Recipe\][^\|]*\|\s*\n\|\s*---\s*\|\s*\n\|\s*([^*\|]+)\*\*×(\d+)\*\*\s+([^*\|]+)\*\*×(\d+)\*\*\s*\|',
        r'**Recipe:** \1 (×\2) + \3 (×\4)',
        content
    )
    
    # 4. 转换Perk表格
    content = re.sub(
        r'Perk\s*\n+\|\s*---\s*\|\s*\n\|\s*([^|\n]+)\s*\|',
        r'**Ability:** \1',
        content
    )
    
    # 5. 转换Code表格
    content = re.sub(
        r'\[Code\][^\n]*\n+\|\s*---\s*\|\s*\n\|\s*`"([^"]+)"`\s*\|',
        r'**Console Code:** `"\1"`',
        content
    )
    
    # 6. 转换Fuel Value
    content = re.sub(
        r'### \[Fuel Value\][^\n]*\n+(\d+)/(\d+) sec',
        r'**Fuel Value:** \1 seconds (\2 sec when wet)',
        content
    )
    
    # 7. 转换Stacks属性
    content = re.sub(
        r'### Stacks up to\s*\n+Does not stack',
        r'**Stacking:** Does not stack',
        content
    )
    
    # 8. 删除空表格标题行
    content = re.sub(r'\|\s*\|\s*\n\|\s*---\s*\|', '', content)
    content = re.sub(r'\|\s*Inventory/Crafting\s*\|\s*\n\|\s*---\s*\|', '', content)
    
    # 9. 清理Effects和Crafting等孤立标题
    content = re.sub(r'\nEffects\s*\n+\*\*', r'\n**', content)
    content = re.sub(r'\nCrafting\s*\n+\*\*', r'\n**', content)
    content = re.sub(r'\nIcons\s*\n+', r'\n', content)
    
    return content

def clean_single_file(input_file, output_file):
    """清洗单个文件"""
    print(f"\n处理: {input_file.name}")
    
    try:
        with open(input_file, 'r', encoding='utf-8') as f:
            content = f.read()
        
        # 分离 frontmatter 和正文
        if content.startswith('---'):
            parts = content.split('---', 2)
            if len(parts) >= 3:
                frontmatter = f"---{parts[1]}---"
                body = parts[2]
            else:
                frontmatter = ""
                body = content
        else:
            frontmatter = ""
            body = content
        
        # 清洗正文
        cleaned_body = clean_wiki_markdown(body)
        
        # 重组
        final_content = f"{frontmatter}\n\n{cleaned_body}" if frontmatter else cleaned_body
        
        # 保存
        with open(output_file, 'w', encoding='utf-8') as f:
            f.write(final_content)
        
        print(f"  ✅ 成功保存到: {output_file}")
        
        # 返回统计信息
        original_size = len(content)
        cleaned_size = len(final_content)
        reduction = (1 - cleaned_size / original_size) * 100 if original_size > 0 else 0
        
        return {
            'file': input_file.name,
            'original_size': original_size,
            'cleaned_size': cleaned_size,
            'reduction': reduction
        }
        
    except Exception as e:
        print(f"  ❌ 处理失败: {e}")
        return None

def clean_directory(input_dir, output_dir, limit=None):
    """批量清洗目录下的所有md文件"""
    input_path = Path(input_dir)
    output_path = Path(output_dir)
    
    if not input_path.exists():
        print(f"❌ 输入目录不存在: {input_dir}")
        return
    
    # 创建输出目录
    output_path.mkdir(parents=True, exist_ok=True)
    
    # 获取所有md文件
    md_files = list(input_path.glob('*.md'))
    
    if limit:
        md_files = md_files[:limit]
        print(f"\n🔬 试点模式：只处理前 {limit} 个文件")
    
    total_files = len(md_files)
    print(f"\n📁 找到 {total_files} 个 markdown 文件")
    print(f"📂 输入目录: {input_dir}")
    print(f"📂 输出目录: {output_dir}")
    print("="*80)
    
    stats = []
    
    for i, md_file in enumerate(md_files, 1):
        print(f"\n[{i}/{total_files}]", end=" ")
        output_file = output_path / md_file.name
        
        result = clean_single_file(md_file, output_file)
        if result:
            stats.append(result)
    
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
    
    print("\n✅ 清洗完成！")

def main():
    import argparse
    
    parser = argparse.ArgumentParser(description='清洗饥荒Wiki数据')
    parser.add_argument('--input', '-i', 
                       default='backend/data/dst',
                       help='输入目录（默认: backend/data/dst）')
    parser.add_argument('--output', '-o',
                       default='backend/data/dst_cleaned',
                       help='输出目录（默认: backend/data/dst_cleaned）')
    parser.add_argument('--limit', '-l',
                       type=int,
                       help='只处理前N个文件（试点模式）')
    parser.add_argument('--test', '-t',
                       action='store_true',
                       help='测试模式：只处理10个文件')
    
    args = parser.parse_args()
    
    limit = args.limit
    if args.test:
        limit = 10
    
    clean_directory(args.input, args.output, limit=limit)

if __name__ == '__main__':
    main()

