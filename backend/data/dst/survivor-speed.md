---
game_version: dst
category: Uncategorized
source_url: https://dontstarve.fandom.com/wiki/Survivor_Speed
---

:   *For entities and mob speeds, see [Mobs](/wiki/Mobs "Mobs").*

**“**An efficient way to adventure.**”**

–[Wheeler](/wiki/Wheeler "Wheeler")

**Player Speed** is a statistic in the game which defines the speed a playable [Character](/wiki/Characters "Characters") can move. Most characters have a base movement speed of 6, but some have different movement speeds, and some items and environmental effects can affect the player's movement speed. The effect of items and attachments is calculated by applying a multiplier to base movement speed. If a character has perks or disadvantages that affect movement speed (e.g. [Wolfgang](/wiki/Wolfgang "Wolfgang")'s different forms, [WX-78](/wiki/WX-78 "WX-78")'s SYSTEM OVERLOAD, or [Wilbur](/wiki/Wilbur "Wilbur")'s different locomotion methods), any external speed modifier will be applied to the current value.

## Contents

* [1 Console Commands](#Console_Commands)
* [2 Character Base Movement Speed](#Character_Base_Movement_Speed)
* [3 Food/Object Modifiers](#Food/Object_Modifiers)
* [4 Total paralysis](#Total_paralysis)
* [5 Boats and attachments](#Boats_and_attachments)
* [6 Other](#Other)
* [7 Calculating Movement Speed](#Calculating_Movement_Speed)
  + [7.1 Example](#Example)
* [8 Bugs](#Bugs)
* [9 Trivia](#Trivia)
* [10 Additional Notes](#Additional_Notes)

## Gears Art Console Commands[]

In *[Don't Starve](/wiki/Don%27t_Starve "Don't Starve")* and any [DLC](/wiki/DLC "DLC"), the player's movement speed can be modified through the following [console command](/wiki/Console/Commands "Console/Commands"):

```
c_speed(value)

```

The "`value"` parameter has a maximum value of 40 and a minimum value of 1.[Verification Needed] Note that the speed used here does not correlate to the speed used when [calculating movement speed](/wiki/Player_Speed#Calculating_Movement_Speed "Player Speed").

In *[Don't Starve Together](/wiki/Don%27t_Starve_Together "Don't Starve Together")*, the player can modify their character's movement speed through the following [console command](/wiki/Console/Don%27t_Starve_Together_Commands "Console/Don't Starve Together Commands"):

```
c_speedmult(multiplier)

```

## [Character](/wiki/Characters "Characters") Base Movement Speed[]

| Character (Ability) | Base Movement Speed |
| --- | --- |
| Wilson Willow Wolfgang Wendy WX-78 Wickerbottom Woodie Wes Maxwell Wagstaff Wigfrid Webber Walani Woodlegs Warly Wilba Winona Wortox Wurt | 6 |
| Wolfgang Wimpy form (Don't Starve) Woodie/Cursed Moose form | 5.4 (0.9x) |
| Wilbur Walking | 5.5 |
| Wormwood Bloom stage 1 | 6.36 (1.06x) |
| Woodie/Cursed Beaver form | 6.6 (1.1x) |
| Wormwood Bloom stage 2 | 6.78 (1.13x) |
| Wheeler | 6.3 up to 7.02 (1.05x up to 1.17x) |
| Wormwood Bloom stage 3 | 7.2 (1.2x) |
| Wolfgang Mighty form (Don't Starve) WX-78 Acceleration Circuit (Don't Starve Together) WX-78 1xSuper-Acceleration Circuit (Don't Starve Together) | 7.5 (1.25x) |
| Wurt Walking in [marsh](/wiki/Marsh_Turf "Marsh Turf") tiles Character when activating [Living Artifact](/wiki/Living_Artifact "Living Artifact") (https://static.wikia.nocookie.net/dont-starve-game/images/0/09/Hamlet icon.png/revision/latest?cb=20190111111719) | 7.8 (1.3x) |
| Wilbur Running | 8 |
| Woodie/Cursed Goose form WX-78 2xSuper-Acceleration Circuit (Don't Starve Together) | 8.4 (1.4x) |
| WX-78 SYSTEM OVERLOAD (Don't Starve) WX-78 3xSuper-Acceleration Circuit (Don't Starve Together) Wilba Werepig form | 9 (1.5x) |
| [Ghost character form](/wiki/Ghost_Characters "Ghost Characters") (Don't Starve Together) | 3.6 |

## Coffee [Food](/wiki/Food "Food")/Object Modifiers[]

There are some items, objects and foods that when equipped, walked on or eaten, increase or decrease speed. Many of them can stack, and some are mutually exclusive. See [Calculating Movement Speed](/wiki/Player_Speed#Calculating_Movement_Speed "Player Speed") for more information on how they affect a character's speed.

| Object | Effect | Type | Duration |
| --- | --- | --- | --- |
| [Thulecite Club](/wiki/Thulecite_Club "Thulecite Club") | +10% | Hand Item | While equipped |
| [Magiluminescence](/wiki/Magiluminescence "Magiluminescence") | +20% | Chest Item | 8 min (1 day) |
| [Lazy Explorer](/wiki/Lazy_Explorer "Lazy Explorer") | +25% | Hand Item | While equipped |
| [Walking Cane](/wiki/Walking_Cane "Walking Cane") | +25% | Hand Item | While equipped |
| [Sleek Hat](/wiki/Sleek_Hat "Sleek Hat") Shipwrecked | +25% | Hat Item | 3 days |
| [Roads](/wiki/Road "Road"), [Cobblestones](/wiki/Cobblestones "Cobblestones") And [Stone Road Turf](/wiki/Stone_Road_Turf "Stone Road Turf") | +30% | Object | Walking over |
| [Ice Cube](/wiki/Ice_Cube "Ice Cube") | -10% | Hat Item | 8 days |
| [Limestone Suit](/wiki/Limestone_Suit "Limestone Suit") Shipwrecked | -10% | Chest Item | While equipped |
| [Piggyback](/wiki/Piggyback "Piggyback") | -20%[[1]](#cite_note-1) ( -10%[[2]](#cite_note-2)) | Chest Item | While equipped |
| [Marble Suit](/wiki/Marble_Suit "Marble Suit") | -30% | Chest Item | While equipped |
| [Sticky Webbing](/wiki/Sticky_Webbing "Sticky Webbing") | -40% | Object | Walking over |
| [Antlion Sinkhole](/wiki/Antlion_Sinkhole "Antlion Sinkhole") Don't Starve Together | -70% | Object | Walking over |
| [Honey Trail](/wiki/Honey_Trail "Honey Trail") Don't Starve Together | -60% | Object | Walking over |
| [Roasted Coffee Beans](/wiki/Roasted_Coffee_Beans "Roasted Coffee Beans") Shipwrecked | +5[[3]](#cite_note-:0-3) | Food | 30 seconds |
| [Coffee](/wiki/Coffee "Coffee") Shipwrecked | +5[[3]](#cite_note-:0-3) | Food | 4 minutes (1/2 day) |
| [Tropical Bouillabaisse](/wiki/Tropical_Bouillabaisse "Tropical Bouillabaisse") Shipwrecked | +3[[4]](#cite_note-:1-4) | Food | 30 seconds |
| [Purple Grouper](/wiki/Purple_Grouper "Purple Grouper") Shipwrecked | +2[[4]](#cite_note-:1-4) | Food | 30 seconds |
| [Pierrot Fish](/wiki/Pierrot_Fish "Pierrot Fish") Shipwrecked | +1[[4]](#cite_note-:1-4) | Food | 30 seconds |
| [Tar Trap](/wiki/Tar_Slick "Tar Slick") Shipwrecked | -65% | Object | Walking over |
| [Flood](/wiki/Flooding "Flooding") Shipwrecked | -20% | Object | Walking over |
| [Ink Patch](/wiki/Quacken "Quacken") Shipwrecked | -70% | Object | Sailing over |
| [Quackering Ram](/wiki/Quackering_Ram "Quackering Ram") Shipwrecked | +??? | Boat Attachment | One second after equipping it |
| [Tea](/wiki/Tea "Tea") Hamlet | +2.5[[3]](#cite_note-:0-3) | Food | 2 minutes (1/4 day) |
| [Iced Tea](/wiki/Iced_Tea "Iced Tea") Hamlet | +1.6[[3]](#cite_note-:0-3) | Food | 80 seconds |
| [Stalking Stick](/wiki/Stalking_Stick "Stalking Stick") Hamlet | +30% | Hand Item | 3 days |
| [Heavy Fog](/wiki/Heavy_Fog "Heavy Fog") Hamlet | -40% up to -60% | World Effect | 3 minutes or until rain |
| [Sandstorm](/wiki/Sandstorm "Sandstorm") Don't Starve Together | -60% | World Effect | Walking inside it |
| [Tin Suit](/wiki/Tin_Suit "Tin Suit") Hamlet | -20% | Hat Item | While equipped |
| [Fancy Helmet](/wiki/Fancy_Helmet "Fancy Helmet") Hamlet | -20% | Chest Item | While equipped |

## Ice Total paralysis[]

There are a few mobs and effects that paralyze players completely, making it impossible to move while under the effect.

| Mob | Effect | Time |
| --- | --- | --- |
| [Blue Hound](/wiki/Blue_Hound "Blue Hound") | Freeze | 5 seconds or until attacked[[5]](#cite_note-:2-5) or until death |
| [Ewecus](/wiki/Ewecus "Ewecus") | Mucus spit attack | 10 seconds or when freed by a befriended [Pigmen](/wiki/Pigmen "Pigmen") or another player |
| [Sealnado](/wiki/Sealnado "Sealnado") Shipwrecked | Caught in tornado attack | 5-10 seconds |
| [Deerclops](/wiki/Deerclops "Deerclops") | Freeze | 5 seconds or until attacked[[5]](#cite_note-:2-5) or until death |
| Gaze from [Pugalisk](/wiki/Pugalisk "Pugalisk") | Freeze | 5 seconds or until attacked[[5]](#cite_note-:2-5) or until death |
| [Jellyfish](/wiki/Jellyfish "Jellyfish") Shipwrecked | Stunlock | 2 seconds |
| [Lightning](/wiki/Lightning "Lightning") | Stunlock | 2 seconds |
| [Night Hands](/wiki/Shadow_Creature "Shadow Creature") created by activating the [Teleportato](/wiki/Wooden_Thing "Wooden Thing"), [Maxwell's Door](/wiki/Maxwell%27s_Door "Maxwell's Door") or the [Nightmare Throne](/wiki/Nightmare_Throne "Nightmare Throne") | Caught | ??? |
| Screaming of the [Moose/Goose](/wiki/Moose/Goose "Moose/Goose") and the [Queen Womant](/wiki/Queen_Womant "Queen Womant") | Stopped | 0.5 seconds[[6]](#cite_note-6) |
| [Bearger](/wiki/Bearger "Bearger") | Sleep | 10+ seconds or until attacked |

## Cloth Sail Boats and attachments[]

In the *[Shipwrecked](/wiki/Don%27t_Starve:_Shipwrecked "Don't Starve: Shipwrecked")* and *[Hamlet](/wiki/Don%27t_Starve:_Hamlet "Don't Starve: Hamlet")* DLCs, [Boats](/wiki/Boats "Boats") have different speed base values. These values replace the [base speed for characters](/wiki/Player_Speed#Character_Base_Movement_Speed "Player Speed"), and any modifiers will be based on the boat's base speed instead of the character's. Unlike players, boats do not instantly reach top speed, but will instead accelerate slowly until that speed has been reached.

| Boat | Base Speed | Attachments |
| --- | --- | --- |
| [Encrusted boat](/wiki/Encrusted_Boat "Encrusted Boat") Shipwrecked | 4 | Yes |
| [Log Raft](/wiki/Log_Raft "Log Raft") Shipwrecked | 4 | No |
| [Cork Bowl Canoe](/wiki/Cork_Bowl_Canoe "Cork Bowl Canoe") Hamlet | 4 | Yes |
| [Raft](/wiki/Raft "Raft") | 5 | No |
| [Cargo Boat](/wiki/Cargo_Boat "Cargo Boat") | 5 | Yes |
| [The Sea Legs](/wiki/The_%27Sea_Legs%27 "The 'Sea Legs'") Shipwrecked Woodlegs | 6 | No |
| [Row Boat](/wiki/Row_Boat "Row Boat") | 6 | Yes |
| [Armoured Boat](/wiki/Armoured_Boat "Armoured Boat") Shipwrecked | 6 | Yes |
| [Surfboard](/wiki/Surfboard "Surfboard") Shipwrecked | 6.5 | No |

Some boats have [sail](/wiki/Sail "Sail") attachments. When equipped, these grant additional movement speed while the equipment still has durability.

| Attachment | Speed Boost | Duration |
| --- | --- | --- |
| [Thatch Sail](/wiki/Thatch_Sail "Thatch Sail") Shipwrecked | +20% | 2 days |
| [Snakeskin Sail](/wiki/Snakeskin_Sail "Snakeskin Sail") Shipwrecked | +25% | 4 days |
| [Cloth Sail](/wiki/Cloth_Sail "Cloth Sail") Shipwrecked | +30% | 3 days |
| [Feather Lite Sail](/wiki/Feather_Lite_Sail "Feather Lite Sail") Shipwrecked | +40% | 2 days |
| [Iron Wind](/wiki/Iron_Wind "Iron Wind") Shipwrecked | +50% | 4 days |
| [Trawl Net](/wiki/Trawl_Net "Trawl Net") Shipwrecked | Starts at 0%, penalty increases by approx. -2.8% for each item caught, up to -25% | Until full or detached manually |

## Other[]

There are other ways to modify speed with variable duration periods. Some methods require certain criteria and can either slow down or speed up the character.

| Medium | Criteria |
| --- | --- |
| [Waves](/wiki/Waves "Waves") Shipwrecked | Sailing in the same direction briefly increases speed by 25 ([Surfboard](/wiki/Surfboard "Surfboard")), 20 ([Encrusted Boat](/wiki/Encrusted_Boat "Encrusted Boat")), or 5 (All Other Boats) Sailing in the opposite direction damages the boat and reduces movement speed |
| [Big Waves](/wiki/Waves "Waves") Shipwrecked | Sailing in the same direction on a [Surfboard](/wiki/Surfboard "Surfboard") briefly increases speed by 35 Sailing with any other boat damages the boat and reduces movement speed |
| [Strong Winds](/wiki/Strong_Winds "Strong Winds") Shipwreckedhttps://static.wikia.nocookie.net/dont-starve-game/images/0/09/Hamlet icon.png/revision/latest?cb=20190111111719 | Walking or sailing in the same direction as the wind increases speed by +40% Walking or sailing in the opposite direction reduces speed by -40% |
| [Sail Stick](/wiki/Sail_Stick "Sail Stick") Shipwrecked | While held, controls the direction of Strong Winds when present |
| [Howling Conch](/wiki/Howling_Conch "Howling Conch") Shipwrecked | On use, Strong Winds begin and last for half a day |
| [Windbreaker](/wiki/Windbreaker "Windbreaker") Shipwrecked | Prevents the player from being slowed by the wind |

## Walking Cane Calculating Movement Speed[]

When the game calculates the player's movement speed, it starts with the base movement speed for the character and first adds any flat additions to movement speed (such as the +3 modifier from [WX-78's](/wiki/WX-78 "WX-78") SYSTEM OVERLOAD, or the +5 modifier from [Coffee](/wiki/Coffee "Coffee")). It then sums any percentage-based modifiers to speed from items or other effects (such as the +25% modifier from a [Walking Cane](/wiki/Walking_Cane "Walking Cane"), the -10% modifier from [Wolfgang's](/wiki/Wolfgang "Wolfgang") wimpy form or the -30% modifier from a [Marble Suit](/wiki/Marble_Suit "Marble Suit")) and multiplies the above total by the multiplier.

#### Example[]

[Wilson](/wiki/Wilson "Wilson") is running down a [Road](/wiki/Road "Road") while wearing a [Football Helmet](/wiki/Football_Helmet "Football Helmet") and a [Marble Suit](/wiki/Marble_Suit "Marble Suit"). He's holding a [Walking Cane](/wiki/Walking_Cane "Walking Cane"), and has recently eaten [Coffee](/wiki/Coffee "Coffee"). Using info from the tables above, movement speed is calculated as follows:

1. Flat movement speed is 6 (From [Wilson's](/wiki/Wilson "Wilson") base speed) + 5 (From the [Coffee](/wiki/Coffee "Coffee")) = 11
2. Speed multiplier is 1 (Default) + 0.25 (+25% from [Walking Cane](/wiki/Walking_Cane "Walking Cane")) + 0.3 (+30% from the [Road](/wiki/Road "Road")) - 0.3 (-30% from [Marble Suit](/wiki/Marble_Suit "Marble Suit")) = x1.25 or +25% (The [Football Helmet](/wiki/Football_Helmet "Football Helmet") has no effect on speed)
3. Final Player Speed is 11 x 1.25 = **13.75,** or 129% faster than without any modifiers

*[Don't Starve Together](/wiki/Don%27t_Starve_Together "Don't Starve Together")* may calculate movement speed differently.[Verification Needed]

## Mosquito Bugs[]

* If a player uses console commands to set the player's speed to a very high value, walking over NPCs can cause a game crash.[Verification Needed]
* If a piece of armor breaks while the player is moving, the player may continue to slide in the direction they were moving until the damage animation ends. This also occurs if the player would otherwise be immune to stuns.

## Placeholder Trivia[]

* The fastest speed a player can have without console commands or exploits is about 45. This can be done while [WX-78](/wiki/WX-78 "WX-78") is in SYSTEM OVERLOAD, is riding a [Row Boat](/wiki/Row_Boat "Row Boat") or [Armored Boat](/wiki/Armored_Boat "Armored Boat") equipped with an [Iron Wind](/wiki/Sail "Sail"), is wearing a [Sleek Hat](/wiki/Sleek_Hat "Sleek Hat") and a [Magiluminescence](/wiki/Magiluminescence "Magiluminescence"), is holding a [Stalking Stick](/wiki/Stalking_Stick "Stalking Stick"), has recently consumed [Coffee](/wiki/Coffee "Coffee"), and [Tropical Bouillabaisse](/wiki/Tropical_Bouillabaisse "Tropical Bouillabaisse"), and is sailing into [Strong Winds](/wiki/Strong_Winds "Strong Winds").
  + Without Strong Winds, this combination of bonuses results in a player speed of 38.25.
  + Additional speed can be gained by riding [Waves](/wiki/Waves "Waves") and/or repeatedly equipping a [Quackering Ram](/wiki/Quackering_Ram "Quackering Ram"), although the burst nature of these speed boosts makes it difficult to ascertain exactly how much more of a bonus they provide.
  + If traveling over land using a [Road](/wiki/Road "Road") or [Cobblestones](/wiki/Cobblestones "Cobblestones") rather than by sea, the relevant bonuses above will still give you a 41.65 movement speed, or 34.85 without Strong Winds.
* The slowest speed a player can have without console commands is 1.0368.[Verification Needed] This can be done as [Wolfgang](/wiki/Wolfgang "Wolfgang") while in his wimpy form, if he is wearing a Marble Suit, while walking on a [Sinkhole](/wiki/Antlion "Antlion") inside a [Sandstorm](/wiki/Sandstorm "Sandstorm").
* Despite each character having their own base speed on land, when boarding a boat in *Shipwrecked*, the character's base movement speed will become that of the boat.
* Changing the movement speed with the [console](/wiki/Console/Commands "Console/Commands") by entering `c_speed(value)` to a value higher than 20, the player character can walk through the world's barriers and walk on water. Above 41, the game may crash.[Verification Needed]
* Walking on [lava](/wiki/Volcano "Volcano") or over water in the *[Shipwrecked](/wiki/Don%27t_Starve:_Shipwrecked "Don't Starve: Shipwrecked")* DLC by using [console commands](/wiki/Console/Commands "Console/Commands") will instantly kill the player.

## Feather Pencil Additional Notes[]

1. [↑](#cite_ref-1) Only while playing [*Don't Starve*](/wiki/Don%27t_Starve "Don't Starve").
2. [↑](#cite_ref-2) Only while playing in any [DLC](/wiki/DLC "DLC") and in [*Don't Starve Together*](/wiki/Don%27t_Starve_Together "Don't Starve Together").
3. ↑ [3.0](#cite_ref-:0_3-0) [3.1](#cite_ref-:0_3-1) [3.2](#cite_ref-:0_3-2) [3.3](#cite_ref-:0_3-3) [Roasted Coffee Beans](/wiki/Coffee_Beans "Coffee Beans"), [Coffee](/wiki/Coffee "Coffee"), [Tea](/wiki/Tea "Tea"), and [Iced Tea](/wiki/Iced_Tea "Iced Tea") have mutually exclusive benefits - if one is consumed, a previously eaten alternative will no longer give benefits.
4. ↑ [4.0](#cite_ref-:1_4-0) [4.1](#cite_ref-:1_4-1) [4.2](#cite_ref-:1_4-2) The speed boost from [Purple Groupers](/wiki/Purple_Grouper "Purple Grouper") and [Pierrot Fish](/wiki/Pierrot_Fish "Pierrot Fish") will stack with each other, but do not stack with [Tropical Bouillabaisse](/wiki/Tropical_Bouillabaisse "Tropical Bouillabaisse").
5. ↑ [5.0](#cite_ref-:2_5-0) [5.1](#cite_ref-:2_5-1) [5.2](#cite_ref-:2_5-2) If the player has stun immunity when they are frozen, such as from wearing the [Vortex Cloak](/wiki/Vortex_Cloak "Vortex Cloak") or from the force field produced by the [Thulecite Crown](/wiki/Thulecite_Crown "Thulecite Crown"), the player will still be frozen after being attacked.
6. [↑](#cite_ref-6) The stun effect from the [Queen Womant](/wiki/Queen_Womant "Queen Womant") can be avoided using [Rabbit Earmuffs](/wiki/Rabbit_Earmuffs "Rabbit Earmuffs").