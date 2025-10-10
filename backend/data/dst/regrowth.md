---
game_version: dst
category: Uncategorized
source_url: https://dontstarve.fandom.com/wiki/Regrowth
---

*Exclusive to:* Don't Starve Together

**Regrowth** is a new mechanic in *[Don't Starve Together](/wiki/Don%27t_Starve_Together "Don't Starve Together")*. There are four main mechanisms for regrowth, which work slightly differently: **Regrowth**[[1]](#cite_note-1), **Offspring Regrowth**[[2]](#cite_note-2), **Desolation Regrowth**[[3]](#cite_note-3), and **Resource Renewal**[[4]](#cite_note-4). Only the first three are configurable. On the [World Customization Screen](/wiki/World_Customization_Don%27t_Starve_Together "World Customization Don't Starve Together"), the regrowth setting sets a multiplier on how quickly each of these regrowths happen (from Very Slow to Very Fast, 0.15, 0.33, 1, 3, 7). For all types of regrowth, it only decreases the timer for the regrowth time while that entity is in its regrowth season. For example, desolation regrowth time for a [Blue Mushtree](/wiki/Mushtree "Mushtree") is 50 days, and their [season](/wiki/Season "Season") is Winter, so it will only try to spawn once every 3.33 winters.

## Contents

* [1 Regrowth](#Regrowth)
* [2 Offspring Regrowth](#Offspring_Regrowth)
* [3 Desolation Regrowth](#Desolation_Regrowth)
* [4 Resource Renewal](#Resource_Renewal)
* [5 Tips](#Tips)
* [6 References](#References)

## Regrowth[]

After one of these entities is destroyed (Burnt or dug out and not replanted within the regrowth period), the game will try to place another one after the regrowth time has passed. It will try somewhere in a 5-tile radius of where they were originally; if it tried to place the entity too close to another entity, or within 5 tiles of a base structure or wall, it will try again after the regrowth time has passed once more. If a player is within 16 tiles, it will wait for them to leave before attempting to place the entity.

| Entity | Regrowth Time | Regrowth Season |
| --- | --- | --- |
| Carrot | 12.5 days | Not Winter or night. |
| Flower | 30 seconds | While the ground is very wet, but not during [rain](/wiki/Rain "Rain"), night, or Winter. Double rate during Spring. |
| Rabbit Hole Rabbit Hole | 5 days | Summer. |
| Light Flower Light Flower | 5 days | Any time. |
| Reeds Reeds | 5 days | Spring. |

Note that although flowers do not regrow during the rain, new flowers still spawn during rain like they do in single-player Reign of Giants.

## Offspring Regrowth[]

This is intended to give the effect of forests growing and reclaiming land. Each tree has a timer after which it will try to spawn a sapling of the same type nearby. If there are fewer than 5 other trees of the same type in the radius that would have 5 such trees at worldgen, then it will succeed in spawning one. Offspring Regrowth doesn't occur within a 2-tile distance of structures, or around a 0.75-tile radius of any object.

| Tree | Regrowth Time | Regrowth Season |
| --- | --- | --- |
| Evergreen Evergreen | 5 days | Not Winter. Doubled rate in Summer. |
| Lumpy Evergreen Lumpy Evergreen | 8 days | Any season. |
| Twiggy Tree Twiggy Tree | 8 days | Any season. |
| Evergreen Evergreen | 3 days | Spring. |
| Mushtree Mushtree | 3 days | Winter. |
| Mushtree Mushtree | 3 days | Summer. |
| Mushtree Mushtree | 3 days | Spring. |
| Lune Lune | 3 days | Highest during full moon. |
| Lunar Mushtree Lunar Mushtree | 3 days | Winter. |

## Desolation Regrowth[]

Desolation regrowth is intended to spawn trees in areas they belong in after the area has had all or almost all of its trees destroyed, such as by an out of control forest fire. Desolation regrowth cannot occur if there are any of the same type of tree within a 10-tile radius, and base structures or walls within 5 tiles, or any entities at all within a tile.

| Tree | Regrowth Time | Regrowth Season |
| --- | --- | --- |
| Evergreen Evergreen | 50 days | Not Winter. Doubled rate in Summer. |
| Lumpy Evergreen Lumpy Evergreen | 50 days | Any season. |
| Twiggy Tree Twiggy Tree | 50 days | Any season. |
| Evergreen Evergreen | 50 days | Spring. |
| Mushtree Mushtree | 50 days | Winter. |
| Mushtree Mushtree | 50 days | Summer. |
| Mushtree Mushtree | 50 days | Spring. |
| Lune Lune | 50 days | Highest at the full moon. |

## Resource Renewal[]

Resource Renewal is a mechanic limited to *Wilderness* and *Endless* [game modes](/wiki/Don%27t_Starve_Together#Game_Modes "Don't Starve Together") and is intended to guarantee that essential resources are available for spawned-in players. There are 4 groups of resources, usually with multiple prefabs. Periodically (every ca. 15 minutes) the game checks an area around spawn points (these are used to respawn players in Wilderness mode, and are actually fixed in place during world generation, and present in all game modes). If none of the matching prefabs are found within a 60-unit (15-tile) radius around the point, the game attempts to spawn a randomly-picked prefab from the spawn set. This will only succeed if there are no players within a 240-unit (60-tile) radius of the spawn point.
On the surface, spawn points are plentiful in [Grasslands](/wiki/Grasslands "Grasslands") and rare in [Forest](/wiki/Forest "Forest") biomes. In the Caves, spawn points are placed only at the cave exit staircases.

| Match set | Spawn set |
| --- | --- |
| Flint | Flint |
| SaplingTwigsTwiggy Tree | SaplingTwiggy Tree |
| Grass TuftGrass TuftCut GrassGrass Gekko | Grass Tuft |
| Berry BushBerry BushJuicy Berry Bush | Berry BushJuicy Berry Bush |

## Prototype Tips[]

* World Regrowth is a long-term way of gaining infinite berry bushes which are mostly limited and usually can barely sustain the player through the year.
* Due to world regrowth, it's completely fine to dig rabbit holes for a quick snack when in a pinch.
* Trees won't regrow unless their stump is removed.

## References[]

1. [↑](#cite_ref-1) scripts/components/regrowthmanager.lua
2. [↑](#cite_ref-2) scripts/components/plantregrowth.lua
3. [↑](#cite_ref-3) scripts/components/desolationspawner.lua
4. [↑](#cite_ref-4) scripts/components/forestresourcespawner.lua

|  |  |
| --- | --- |
| **Gameplay Mechanics** [view](/wiki/Template:Gameplay "Template:Gameplay") | |
| **Activities** | Cooking [Cooking](/wiki/Cooking "Cooking") • Crafting [Crafting](/wiki/Crafting "Crafting") • Farming [Farming](/wiki/Farming "Farming") • Combat [Combat](/wiki/Combat "Combat") • Fishing [Fishing](/wiki/Fishing "Fishing") • [Sleeping](/wiki/Sleeping "Sleeping") • Beefalo [Beefalo Riding](/wiki/Beefalo "Beefalo")  (*Boats [Boating](/wiki/Boats "Boats")* Don't Starve: Shipwrecked) (*Scrapbooking [Scrapbooking](/wiki/Scrapbooking "Scrapbooking")* Don't Starve Together) |
| **Environment** | Day-Night Cycle [Day-Night Cycle](/wiki/Day-Night_Cycle "Day-Night Cycle") • Moon Cycle [Moon Cycle](/wiki/Moon_Cycle "Moon Cycle") • Nightmare Cycle [Nightmare Cycle](/wiki/Nightmare_Cycle "Nightmare Cycle") • Earthquake [Earthquake](/wiki/Earthquake "Earthquake") • Lightning [Lightning](/wiki/Lightning "Lightning") • Rain [Rain](/wiki/Rain "Rain")  (*[Strong Winds](/wiki/Strong_Winds "Strong Winds") • [Fog](/wiki/Fog "Fog") • [Waves](/wiki/Waves "Waves") • [Flooding](/wiki/Flooding "Flooding") • [Volcanic Eruption](/wiki/Volcano/Object#Eruptions "Volcano/Object")* Don't Starve: Shipwrecked) (*[Fog](/wiki/Fog#Hamlet "Fog")* Hamlet) (*[Moonstorm](/wiki/Moonstorm "Moonstorm") • [Sandstorm](/wiki/Sandstorm "Sandstorm")* Don't Starve Together) |
| **[Seasons](/wiki/Seasons "Seasons")** | [Summer](/wiki/Seasons/Summer "Seasons/Summer") • [Winter](/wiki/Seasons/Winter "Seasons/Winter") • (*[Autumn](/wiki/Seasons/Autumn "Seasons/Autumn")* • *[Spring](/wiki/Seasons/Spring "Seasons/Spring")* Reign of Giants)  (*[Mild Season](/wiki/Seasons/Mild "Seasons/Mild") • [Hurricane Season](/wiki/Seasons/Hurricane "Seasons/Hurricane") • [Monsoon Season](/wiki/Seasons/Monsoon "Seasons/Monsoon") • [Dry Season](/wiki/Seasons/Dry "Seasons/Dry")* Don't Starve: Shipwrecked) (*[Temperate Season](/wiki/Seasons/Temperate "Seasons/Temperate") • [Humid Season](/wiki/Seasons/Humid "Seasons/Humid") • [Lush Season](/wiki/Seasons/Lush "Seasons/Lush")* Hamlet) |
| **Mechanics** | [Beard](/wiki/Beard "Beard") • [Biome](/wiki/Biome "Biome") • [Characters](/wiki/Characters "Characters") • [Charlie](/wiki/Charlie_(Night_Monster) "Charlie (Night Monster)") • [Controls](/wiki/Controls "Controls") • [Death](/wiki/Death "Death") • [Durability](/wiki/Durability "Durability") • [Experience](/wiki/Experience "Experience") • [Fire](/wiki/Fire "Fire") • [Food Spoilage](/wiki/Food#Food_Spoilage "Food") • [Freezing](/wiki/Freezing "Freezing") • [Health](/wiki/Health "Health") • [Hunger](/wiki/Hunger "Hunger") • [Inventory](/wiki/Inventory "Inventory") • [Light](/wiki/Light "Light") • [Map](/wiki/Map "Map") • [Naughtiness](/wiki/Krampus#Naughtiness "Krampus") • [Non-renewable resources](/wiki/Non-renewable_resources "Non-renewable resources") • [Sanity](/wiki/Sanity "Sanity") • [Saving](/wiki/Saving "Saving") • [Structures](/wiki/Structures "Structures")  (*[Wetness](/wiki/Wetness "Wetness")* Reign of GiantsShipwreckedHamlet) (*[Overheating](/wiki/Overheating "Overheating")* Reign of GiantsShipwrecked) (*[Poison](/wiki/Poison "Poison")* ShipwreckedHamlet) (*[Hay Fever](/wiki/Hay_Fever "Hay Fever") • [Peculiar Objects](/wiki/Peculiar_Objects "Peculiar Objects") • [Aporkalypse](/wiki/Aporkalypse "Aporkalypse") • [Pig Fiesta](/wiki/Pig_Fiesta "Pig Fiesta")* Hamlet) (*[Enlightenment](/wiki/Enlightenment "Enlightenment") • [Events](/wiki/Category:Events "Category:Events") • [Disease](/wiki/Disease "Disease") • [Ghosts](/wiki/Ghost_Characters "Ghost Characters") • **World Regrowth** • [Skins](/wiki/Skins "Skins")* Don't Starve Together) |
| **Mode** | [Survival Mode](/wiki/Survival_Mode "Survival Mode") • [Adventure Mode](/wiki/Adventure_Mode "Adventure Mode") • [Caves](/wiki/Caves "Caves") • [Ruins](/wiki/Ruins "Ruins") • [Volcano](/wiki/Volcano "Volcano") • [World Customization](/wiki/World_Customization "World Customization") |
| **Others** | [Pig Village](/wiki/Pig_Village "Pig Village") • [Road](/wiki/Road "Road") (*[Trail](/wiki/Trail "Trail")*) • [Graveyard](/wiki/Graveyard "Graveyard") • [Ocean](/wiki/Ocean "Ocean") • [Abyss](/wiki/Abyss "Abyss") • [Bridge](/wiki/Bridge "Bridge") • [Set Piece](/wiki/Set_Piece "Set Piece") • [Things](/wiki/Things "Things") • [Morgue](/wiki/Morgue "Morgue") |