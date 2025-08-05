# 数据源规范 (Data Source Schema) - V1.0

## 核心原则

- **KISS (Keep It Simple, Stupid):** 优先使用广泛接受的 `Markdown + YAML Front Matter` 组合。
- **解析优先 (Parse-First):** 规范的首要目标是让机器能轻松、无歧义地解析成结构化数据，渲染的美观性是次要目标。
- **结构与内容分离:** 游戏的核心结构化数据（如属性、掉落物）在YAML中定义，大段的描述性文本在Markdown中编写。

## 文件结构

- 每个游戏一个目录，例如 `data/dont-starve/`。
- 目录中包含多个 `.md` 文件，每个文件代表一个游戏内的"实体"（Entity），例如一个生物、一个物品或一个概念。
- 文件名使用英文，用 `-` 连接，例如 `beefalo.md`, `chester.md`。

## YAML Front Matter 规范

每个 `.md` 文件的开头都必须包含一个YAML Front Matter块。

```yaml
---
# 基础元数据 (必须)
name: "牛（Beefalo）"  # 实体在游戏中的官方中文名
type: "Creature"      # 实体类型 (Creature, Item, Food, Character, Concept, etc.)
game: "Don't Starve" # 所属游戏

# 结构化数据 (可选，根据type变化)
# 示例：对于一个生物 (Creature)
attributes:
  health: 500
  damage: 34
  attack_period: 3
  running_speed: 7
  special_abilities:
    - "发情期具有攻击性"
    - "可以被驯养"
drops: # 掉落物列表
  - item: "Meat"
    quantity: 4
    probability: 1.0
  - item: "Beefalo Wool"
    quantity: 3
    probability: 1.0
---
```

## Markdown 正文规范

- Markdown正文用于描述该实体的非结构化信息。
- 使用标准的Markdown标题 (`#`, `##`, `###`) 来组织内容。这些标题将作为"语义分块"的依据。
- 一级标题 (`#`) 应该与文件名/实体名保持一致。

### 示例 (`beefalo.md`)

````markdown
---
name: "牛（Beefalo）"
type: "Creature"
game: "Don't Starve"
attributes:
  health: 500
  damage: 34
  attack_period: 3
  running_speed: 7
  special_abilities:
    - "发情期具有攻击性"
    - "可以被驯养"
drops:
  - item: "肉"
    quantity: 4
    probability: 1.0
  - item: "牛毛"
    quantity: 3
    probability: 1.0
---

# 牛 (Beefalo)

牛是一种中立生物，通常成群出现在热带草原生态系统中。

## 行为

它们在一天中的大部分时间里都在吃草和睡觉。当玩家靠近时，它们会盯着玩家，但不会主动攻击，除非被激怒或在发情期。

### 发情期

在发情期，牛的尾巴会竖起，后端变成红色。此时它们会对任何靠近的生物表现出攻击性。

## 驯养

玩家可以通过喂食草或树枝来驯养牛。被驯养的牛可以作为坐骑，并提供牛毛。
```` 