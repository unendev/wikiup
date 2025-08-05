---
game_version: dst
category: Uncategorized
source_url: https://dontstarve.fandom.com/wiki/Console/Prefab_List
---

**Prefabs**, or **pre-fabricated**, are objects in the game. This lists the codes used in DebugSpawn.

To use them, open the console with tilde (See [console](/wiki/Console "Console")) and enter DebugSpawn("*spawncode*").

All tables on this page are sortable by name. Just click the up/down arrows in the column header to change sort attributes from ascending to descending and back. Each table also has a *Collapse* button in the **"Prefab"** column header. Clicking this will temporarily hide that table from view, which can help make page navigation easier. The tables are expanded by default to allow for quick page searching using your browsers "Find" or "Find on page" command.

## Contents

* [1 Spawning Items](#Spawning_Items)
* [2 Monsters](#Monsters)
* [3 Characters](#Characters)
* [4 Items](#Items)
* [5 Animals](#Animals)
* [6 Others](#Others)
* [7 Forest](#Forest)
* [8 Marsh](#Marsh)
* [9 Cave](#Cave)
* [10 Blueprints](#Blueprints)
* [11 Objects](#Objects)
* [12 Fx](#Fx)
* [13 Interface](#Interface)
* [14 Unlisted Content](#Unlisted_Content)
* [15 Shipwrecked](#Shipwrecked)

## Spawning Items[]

There are two approaches in spawn items:

Using c\_spawn in Console

It spawns an item or a mob at the position of mouse click immediately.

:   First example: spawn a mob - 1 [Beefalo](/wiki/Beefalo "Beefalo").

```
c_spawn("beefalo")

```

:   Second example: spawn multiple mobs - 2 [Deerclops](/wiki/Deerclops "Deerclops").

```
c_spawn("deerclops",2)

```

:   Third example: spawn an item - 1 [Gears](/wiki/Gears "Gears").

```
c_spawn("gears")

```

:   Fourth example: spawn multiple items - 2 [Fire Staff](/wiki/Fire_Staff "Fire Staff").

```
c_spawn("firestaff",2)

```

:   Fifth example: spawn a structure - 1 [Science Machine](/wiki/Science_Machine "Science Machine").

```
c_spawn("researchlab")

```

:   Sixth example: spawn multiple structures - 2 [Science Machine](/wiki/Science_Machine "Science Machine").

```
Only a single structure can be spawned at once.

```

Using c\_give in Console
:   It directly gives an item to the player's inventory immediately.
:   First example: spawn an item - 1 [Meaty Stew](/wiki/Meaty_Stew "Meaty Stew").

```
c_give("bonestew",1)

```

:   Second example: spawn multiple items - 2 [Walking Cane](/wiki/Walking_Cane "Walking Cane").

```
c_give("cane",2)

```

## Monsters[]

| **Name** | **Spawn Code** | **Prefab location** |
| --- | --- | --- |
| [Abigail](/wiki/Abigail "Abigail") | abigail | common/monsters/abigail |
| [Bee](/wiki/Bee "Bee") | bee | forest/monsters/bee |
| [Killer Bee](/wiki/Killer_Bee "Killer Bee") | killerbee | forest/monsters/killerbee |
| [Beehive](/wiki/Beehive "Beehive") | beehive | forest/monsters/beehive |
| [Cave Spider](/wiki/Cave_Spider "Cave Spider") | spider\_hider | cave/monsters/spider\_hider |
| [Spitter](/wiki/Spitter "Spitter") | spider\_spitter | cave/monsters/spider\_spitter |
| [Deerclops](/wiki/Deerclops "Deerclops") | deerclops | common/monsters/deerclops |
| [Ghost](/wiki/Ghost "Ghost") | ghost | common/monsters/ghost |
| [Hound](/wiki/Hound "Hound") | hound | monsters/hound |
| [Red Hound](/wiki/Red_Hound "Red Hound") | firehound | monsters/firehound |
| [Blue Hound](/wiki/Blue_Hound "Blue Hound") | icehound | monsters/icehound |
| [Hound Fire](/wiki/Fire "Fire") | houndfire | monsters/houndfire |
| [Hound Bone](/wiki/Bones "Bones") | houndbone | forest/monsters/houndbone |
| [Hound Mound](/wiki/Hound_Mound "Hound Mound") | houndmound | forest/monsters/houndmound |
| [Krampus](/wiki/Krampus "Krampus") | krampus | monsters/krampus |
| [Mosquito](/wiki/Mosquito "Mosquito") | mosquito | forest/monsters/mosquito |
| [Crawling Horror](/wiki/Shadow_Creature#Crawling_Horror "Shadow Creature") | crawlinghorror | monsters/crawlinghorror |
| [Terrorbeak](/wiki/Shadow_Creature#Terrorbeak "Shadow Creature") | terrorbeak | monsters/terrorbeak |
| [Spider](/wiki/Spider "Spider") | spider | forest/monsters/spider |
| [Spider Warrior](/wiki/Spider_Warrior "Spider Warrior") | spider\_warrior | forest/monsters/spider\_warrior |
| [Spider Den](/wiki/Spider_Den "Spider Den") | spiderden | forest/monsters/spiderden |
| [Spider Den (Level 2)](/wiki/Spider_Den#Sizes "Spider Den") | spiderden\_2 | forest/monsters/spiderden\_2 |
| [Spider Den (Level 3)](/wiki/Spider_Den#Sizes "Spider Den") | spiderden\_3 | forest/monsters/spiderden\_3 |
| [Spider Queen](/wiki/Spider_Queen "Spider Queen") | spiderqueen | forest/monsters/spiderqueen |
| [Tallbird](/wiki/Tallbird "Tallbird") | tallbird | forest/monsters/tallbird |
| [Treeguard](/wiki/Treeguard "Treeguard") | leif | forest/monsters/leif |
| [Tentacle](/wiki/Tentacle "Tentacle") | tentacle | marsh/monsters/tentacle |
| [Tentapillar](/wiki/Tentapillar "Tentapillar") | tentacle\_pillar |  |
| [Killer Bee Hive](/wiki/Killer_Bee_Hive "Killer Bee Hive") | wasphive | forest/monsters/wasphive |
| [Lureplant](/wiki/Lureplants "Lureplants") | lureplant |  |
| Moose/[Goose](/wiki/Moose/Goose "Moose/Goose") | moose |  |
| [Dragonfly](/wiki/Dragonfly "Dragonfly") | dragonfly |  |
| [Klaus](/wiki/Klaus "Klaus") | klaus |  |
| [Bearger](/wiki/Bearger "Bearger") | bearger |  |
| [Reanimated Skeleton](/wiki/Reanimated_Skeleton "Reanimated Skeleton") | stalker |  |
| [Antlion](/wiki/Antlion "Antlion") | antlion |  |

## Characters[]

| **Name** | **Spawn Code** | **Prefab location** |
| --- | --- | --- |
| [Bunnyman](/wiki/Bunnyman "Bunnyman") | bunnyman | common/characters/bunnyman |
| [Rock Lobster](/wiki/Rock_Lobster "Rock Lobster") | rocky | common/characters/rocky |
| [Maxwell (character)](/wiki/Maxwell_(character) "Maxwell (character)") | waxwell | common/characters/maxwell |
| [Maxwell (End Game)](/wiki/Nightmare_Throne "Nightmare Throne") | maxwellendgame | characters/maxwellendgame |
| [Maxwell's Head](/wiki/Maxwell%27s_Head "Maxwell's Head") | maxwellhead | common/characters/maxwellhead |
| [Maxwell (Intro)](/wiki/Maxwell_(NPC) "Maxwell (NPC)") | maxwellintro | common/characters/maxwellintro |
| [Maxwell (Throne)](/wiki/Nightmare_Throne "Nightmare Throne") | maxwellthrone | common/characters/maxwellthrone |
| [Pig](/wiki/Pig "Pig") | pigman | common/characters/pigman |
| [Puppet Wilson](/wiki/Nightmare_Throne "Nightmare Throne") | puppet\_wilson | characters/puppet\_wilson |
| [Puppet Wendy](/wiki/Nightmare_Throne "Nightmare Throne") | puppet\_wendy | characters/puppet\_wendy |
| [Puppet Willow](/wiki/Nightmare_Throne "Nightmare Throne") | puppet\_willow | characters/puppet\_willow |
| [Puppet Wickerbottom](/wiki/Nightmare_Throne "Nightmare Throne") | puppet\_wickerbottom | characters/puppet\_wickerbottom |
| [Puppet Wolfgang](/wiki/Nightmare_Throne "Nightmare Throne") | puppet\_wolfgang | characters/puppet\_wolfgang |
| [Puppet Woodie](/wiki/Nightmare_Throne "Nightmare Throne") | puppet\_woodie | characters/puppet\_woodie |
| [Puppet Wes](/wiki/Nightmare_Throne "Nightmare Throne") | puppet\_wes | characters/puppet\_wes |
| [Puppet WX-78](/wiki/Nightmare_Throne "Nightmare Throne") | puppet\_wx78 | characters/puppet\_wx78 |
| [Splumonkey](/wiki/Splumonkey "Splumonkey") | monkey | common/characters/monkey |
| [Wilton](/wiki/Unimplemented_Characters#Wilton "Unimplemented Characters") | wilton | characters/wilton |
| [Woodie](/wiki/Woodie "Woodie") | woodie | characters/woodie |
| [Maxwell](/wiki/Waxwell "Waxwell") | waxwell | characters/maxwell |
| [Wendy](/wiki/Wendy "Wendy") | wendy | characters/wendy |
| [Wes](/wiki/Wes "Wes") | wes | characters/wes |
| [Wickerbottom](/wiki/Wickerbottom "Wickerbottom") | wickerbottom | characters/wickerbottom |
| [Willow](/wiki/Willow "Willow") | willow | characters/willow |
| [Wilson](/wiki/Wilson "Wilson") | wilson | characters/wilson |
| [Wolfgang](/wiki/Wolfgang "Wolfgang") | wolfgang | characters/wolfgang |
| [WX-78](/wiki/Wx78 "Wx78") | wx78 | characters/wx78 |

## Items[]

| **Name** | **Spawn Code** | **Prefab location** |
| --- | --- | --- |
| [Life Giving Amulet](/wiki/Life_Giving_Amulet "Life Giving Amulet") | amulet | common/inventory/amulet |
| [Booster Shot](/wiki/Booster_Shot "Booster Shot") | lifeinjector |  |
| [Grass Suit](/wiki/Grass_Suit "Grass Suit") | armorgrass | common/inventory/armorgrass |
| [Marble Suit](/wiki/Marble_Suit "Marble Suit") | armormarble | common/inventory/armormarble |
| [Night Armor](/wiki/Night_Armour "Night Armour") | armor\_sanity | common/inventory/armor\_sanity |
| [Snurtle Shell Armor](/wiki/Snurtle_Shell_Armor "Snurtle Shell Armor") | armorsnurtleshell | common/inventory/armorsnurtleshell |
| [Log Suit](/wiki/Log_Suit "Log Suit") | armorwood | common/inventory/armorwood |
| [Ash](/wiki/Ash "Ash") | ash | common/inventory/ash |
| [Axe](/wiki/Axe "Axe") | axe | common/inventory/axe |
| [Luxury Axe](/wiki/Luxury_Axe "Luxury Axe") | goldenaxe | common/inventory/goldenaxe |
| [Backpack](/wiki/Backpack "Backpack") | backpack | common/inventory/backpack |
| [Honey Poultice](/wiki/Honey_Poultice "Honey Poultice") | bandage | common/inventory/bandage |
| [Beard Hair](/wiki/Beard_Hair "Beard Hair") | beardhair | common/inventory/beardhair |
| [Fur Roll](/wiki/Fur_Roll "Fur Roll") | bedroll\_furry | common/inventory/bedroll\_furry |
| [Straw Roll](/wiki/Straw_Roll "Straw Roll") | bedroll\_straw | common/inventory/bedroll\_straw |
| [Beefalo Wool](/wiki/Beefalo_Wool "Beefalo Wool") | beefalowool | common/inventory/beefalowool |
| [Bee Mine](/wiki/Bee_Mine "Bee Mine") | beemine | common/inventory/beemine |
| [Maxwell's Mosquito Trap](/wiki/Maxwell%27s_Mosquito_Trap "Maxwell's Mosquito Trap") | beemine\_maxwell | common/inventory/beemine\_maxwell |
| [Bird Trap](/wiki/Bird_Trap "Bird Trap") | birdtrap | common/inventory/birdtrap |
| [Bishop Charge (Projectile)](/wiki/Clockwork_Bishop "Clockwork Bishop") | bishop\_charge | common/inventory/bishop\_charge |
| [Sleep Dart](/wiki/Sleep_Dart "Sleep Dart") | blowdart\_sleep | common/inventory/blowdart\_sleep |
| [Fire Dart](/wiki/Fire_Dart "Fire Dart") | blowdart\_fire | common/inventory/blowdart\_fire |
| [Blow Dart](/wiki/Blow_Dart "Blow Dart") | blowdart\_pipe | common/inventory/blowdart\_pipe |
| [Blue Gem](/wiki/Blue_Gem "Blue Gem") | bluegem | common/inventory/bluegem |
| [Boards](/wiki/Boards "Boards") | boards | common/inventory/boards |
| [Boomerang](/wiki/Boomerang "Boomerang") | boomerang | common/inventory/boomerang |
| [Broken Tool](/wiki/Tools "Tools") | brokentool | common/inventory/brokentool |
| [Bug Net](/wiki/Bug_Net "Bug Net") | bugnet | common/inventory/bugnet |
| [Butter](/wiki/Butter "Butter") | butter | common/inventory/butter |
| [Butterfly Wings](/wiki/Butterfly_Wings "Butterfly Wings") | butterflywings | common/inventory/butterflywings |
| [Walking Cane](/wiki/Walking_Cane "Walking Cane") | cane | common/inventory/cane |
| [Carrot (Planted)](/wiki/Carrot "Carrot") | carrot\_planted | common/inventory/carrot\_planted |
| [Charcoal](/wiki/Charcoal "Charcoal") | charcoal | common/inventory/charcoal |
| [Eye Bone](/wiki/Eye_Bone "Eye Bone") | chester\_eyebone | common/inventory/chester\_eyebone |
| [Cut Grass](/wiki/Cut_Grass "Cut Grass") | cutgrass | common/inventory/cutgrass |
| [Cut Reeds](/wiki/Cut_Reeds "Cut Reeds") | cutreeds | common/inventory/cutreeds |
| [Cut Stone](/wiki/Cut_Stone "Cut Stone") | cutstone | common/inventory/cutstone |
| [Deadly Feast](/wiki/Deadly_Feast "Deadly Feast") | deadlyfeast | common/inventory/deadlyfeast |
| [Deerclops Eyeball](/wiki/Deerclops_Eyeball "Deerclops Eyeball") | deerclops\_eyeball | common/inventory/deerclops\_eyeball |
| [Divining Rod](/wiki/Divining_Rod "Divining Rod") | diviningrod | common/inventory/diviningrod |
| [Egg (Bird)](/wiki/Egg "Egg") | bird\_egg | common/inventory/bird\_egg |
| [Cooked Egg (Bird)](/wiki/Cooked_Egg "Cooked Egg") | bird\_egg\_cooked | common/inventory/bird\_egg\_cooked |
| [Rotten Egg](/wiki/Rotten_Egg "Rotten Egg") | rottenegg | common/inventory/rottenegg |
| [Crow Feather](/wiki/Crow_Feather "Crow Feather") | feather\_crow | common/inventory/feather\_crow |
| [Crimson Feather](/wiki/Crimson_Feather "Crimson Feather") | feather\_robin | common/inventory/feather\_robin |
| [Azure Feather](/wiki/Azure_Feather "Azure Feather") | feather\_robin\_winter | common/inventory/feather\_robin\_winter |
| [Fire Staff](/wiki/Fire_Staff "Fire Staff") | firestaff | common/inventory/firestaff |
| [Fish](/wiki/Fish "Fish") | fish | common/inventory/fish |
| [Cooked Fish](/wiki/Cooked_Fish "Cooked Fish") | fish\_cooked | common/inventory/fish\_cooked |
| [Fishing Rod](/wiki/Fishing_Rod "Fishing Rod") | fishingrod | common/inventory/fishingrod |
| [Flint](/wiki/Flint "Flint") | flint | common/inventory/flint |
| [Frog Legs](/wiki/Frog_Legs "Frog Legs") | froglegs | common/inventory/froglegs |
| [Cooked Frog Legs](/wiki/Cooked_Frog_Legs "Cooked Frog Legs") | froglegs\_cooked | common/inventory/froglegs\_cooked |
| [Gears](/wiki/Gears "Gears") | gears | common/inventory/gears |
| [Gold Nugget](/wiki/Gold_Nugget "Gold Nugget") | goldnugget | common/inventory/goldnugget |
| [Guano](/wiki/Guano "Guano") | guano | common/inventory/guano |
| [Gunpowder](/wiki/Gunpowder "Gunpowder") | gunpowder | common/inventory/gunpowder |
| [Ham Bat](/wiki/Ham_Bat "Ham Bat") | hambat | common/inventory/hambat |
| [Hammer](/wiki/Hammer "Hammer") | hammer | common/inventory/hammer |
| [Straw Hat](/wiki/Straw_Hat "Straw Hat") | strawhat | common/inventory/strawhat |
| [Top Hat](/wiki/Top_Hat "Top Hat") | tophat | common/inventory/tophat |
| [Beefalo Hat](/wiki/Beefalo_Hat "Beefalo Hat") | beefalohat | common/inventory/beefalohat |
| [Feather Hat](/wiki/Feather_Hat "Feather Hat") | featherhat | common/inventory/featherhat |
| [Beekeeper Hat](/wiki/Beekeeper_Hat "Beekeeper Hat") | beehat | common/inventory/beehat |
| [Miner hat](/wiki/Miner_Hat "Miner Hat") | minerhat | common/inventory/minerhat |
| [Spiderhat](/wiki/Spiderhat "Spiderhat") | spiderhat | common/inventory/spiderhat |
| [Football Helmet](/wiki/Football_Helmet "Football Helmet") | footballhat | common/inventory/footballhat |
| [Rabbit Earmuffs](/wiki/Rabbit_Earmuffs "Rabbit Earmuffs") | earmuffshat | common/inventory/earmuffshat |
| [Winter Hat](/wiki/Winter_Hat "Winter Hat") | winterhat | common/inventory/winterhat |
| [Bush Hat](/wiki/Bush_Hat "Bush Hat") | bushhat | common/inventory/bushhat |
| [Garland](/wiki/Garland "Garland") | flowerhat | common/inventory/flowerhat |
| [Tam o' Shanter](/wiki/Tam_o%27_Shanter "Tam o' Shanter") | walrushat | common/inventory/walrushat |
| [Shelmet](/wiki/Shelmet "Shelmet") | slurtlehat | common/inventory/slurtlehat |
| [Healing Salve](/wiki/Healing_Salve "Healing Salve") | healingsalve | common/inventory/healingsalve |
| [Thermal Stone](/wiki/Thermal_Stone "Thermal Stone") | heatrock | common/inventory/heatrock |
| [Honey](/wiki/Honey "Honey") | honey | common/inventory/honey |
| [Honeycomb](/wiki/Honeycomb "Honeycomb") | honeycomb | common/inventory/honeycomb |
| [Beefalo Horn](/wiki/Beefalo_Horn "Beefalo Horn") | horn | common/inventory/horn |
| [Hound's Tooth](/wiki/Hound%27s_Tooth "Hound's Tooth") | houndstooth | common/inventory/houndstooth |
| [Red Gem](/wiki/Red_Gem "Red Gem") | redgem | common/inventory/redgem |
| [Ice Staff](/wiki/Ice_Staff "Ice Staff") | icestaff | common/inventory/icestaff |
| [Marble](/wiki/Marble "Marble") | marble | common/inventory/marble |
| [Rocks](/wiki/Rocks "Rocks") | rocks | common/inventory/rocks |
| [Krampus Sack](/wiki/Krampus_Sack "Krampus Sack") | krampus\_sack | common/inventory/krampus\_sack |
| [Light Bulb](/wiki/Light_Bulb "Light Bulb") | lightbulb | common/inventory/lightbulb |
| [Living Log](/wiki/Living_Log "Living Log") | livinglog | common/inventory/livinglog |
| [Log](/wiki/Log "Log") | log | common/inventory/log |
| [Bunny Puff](/wiki/Bunny_Puff "Bunny Puff") | manrabbit\_tail | common/inventory/manrabbit\_tail |
| [Shadow Key](/wiki/Shadow_Key "Shadow Key") | maxwellkey | common/inventory/maxwellkey |
| [Meat](/wiki/Meat "Meat") | meat | common/inventory/meat |
| [Cooked Meat](/wiki/Cooked_Meat "Cooked Meat") | cookedmeat | common/inventory/cookedmeat |
| [Jerky](/wiki/Jerky "Jerky") | meat\_dried | common/inventory/meat\_dried |
| [Monster Meat](/wiki/Monster_Meat "Monster Meat") | monstermeat | common/inventory/monstermeat |
| [Cooked Monster Meat](/wiki/Cooked_Monster_Meat "Cooked Monster Meat") | cookedmonstermeat | common/inventory/cookedmonstermeat |
| [Monster Jerky](/wiki/Monster_Jerky "Monster Jerky") | monstermeat\_dried | common/inventory/monstermeat\_dried |
| [Morsel](/wiki/Morsel "Morsel") | smallmeat | common/inventory/smallmeat |
| [Cooked Morsel](/wiki/Cooked_Morsel "Cooked Morsel") | cookedsmallmeat | common/inventory/cookedsmallmeat |
| [Small Jerky](/wiki/Small_Jerky "Small Jerky") | smallmeat\_dried | common/inventory/smallmeat\_dried |
| [Drumstick](/wiki/Drumstick "Drumstick") | drumstick | common/inventory/drumstick |
| [Fried Drumstick](/wiki/Fried_Drumstick "Fried Drumstick") | drumstick\_cooked | common/inventory/drumstick\_cooked |
| [Batilisk Wing](/wiki/Batilisk_Wing "Batilisk Wing") | batwing | common/inventory/batwing |
| [Cooked Batilisk Wing](/wiki/Batilisk_Wing "Batilisk Wing") | batwing\_cooked | common/inventory/batwing\_cooked |
| [Lantern](/wiki/Lantern "Lantern") | lantern | common/inventory/lantern |
| [Red Cap](/wiki/Red_Cap "Red Cap") | red\_cap | common/inventory/red\_cap |
| [Cooked Red Cap](/wiki/Cooked_Red_Cap "Cooked Red Cap") | red\_cap\_cooked | common/inventory/red\_cap\_cooked |
| [Green Cap](/wiki/Green_Cap "Green Cap") | green\_cap | common/inventory/green\_cap |
| [Cooked Green Cap](/wiki/Cooked_Green_Cap "Cooked Green Cap") | green\_cap\_cooked | common/inventory/green\_cap\_cooked |
| [Blue Cap](/wiki/Blue_Cap "Blue Cap") | blue\_cap | common/inventory/blue\_cap |
| [Cooked Blue Cap](/wiki/Cooked_Blue_Cap "Cooked Blue Cap") | blue\_cap\_cooked | common/inventory/blue\_cap\_cooked |
| [Nightmare Fuel](/wiki/Nightmare_Fuel "Nightmare Fuel") | nightmarefuel | common/inventory/nightmarefuel |
| [Dark Sword](/wiki/Dark_Sword "Dark Sword") | nightsword | common/inventory/nightsword |
| [Nitre](/wiki/Nitre "Nitre") | nitre | common/inventory/nitre |
| [One-man Band](/wiki/One-man_Band "One-man Band") | onemanband | common/inventory/onemanband |
| [Pan Flute](/wiki/Pan_Flute "Pan Flute") | panflute | common/inventory/panflute |
| [Papyrus](/wiki/Papyrus "Papyrus") | papyrus | common/inventory/papyrus |
| [Petals](/wiki/Petals "Petals") | petals | common/inventory/petals |
| [Dark Petals](/wiki/Dark_Petals "Dark Petals") | petals\_evil | common/inventory/petals\_evil |
| [Pickaxe](/wiki/Pickaxe "Pickaxe") | pickaxe | common/inventory/pickaxe |
| [Opulent Pickaxe](/wiki/Opulent_Pickaxe "Opulent Pickaxe") | goldenpickaxe | common/inventory/goldenpickaxe |
| [Piggyback](/wiki/Piggyback "Piggyback") | piggyback | common/inventory/piggyback |
| [Pig Skin](/wiki/Pig_Skin "Pig Skin") | pigskin | common/inventory/pigskin |
| [Pine Cone](/wiki/Pine_Cone "Pine Cone") | pinecone | common/inventory/pinecone |
| [Pitchfork](/wiki/Pitchfork "Pitchfork") | pitchfork | common/inventory/pitchfork |
| [Poop](/wiki/Poop "Poop") | poop | common/inventory/poop |
| [Dragonpie](/wiki/Dragonpie "Dragonpie") | dragonpie | common/inventory/dragonpie |
| [Waffles](/wiki/Waffles "Waffles") | waffles | common/inventory/waffles |
| [Ratatouille](/wiki/Ratatouille "Ratatouille") | ratatouille | common/inventory/ratatouille |
| [Fruit Medley](/wiki/Fruit_Medley "Fruit Medley") | fruitmedley | common/inventory/fruitmedley |
| [Monster Lasagna](/wiki/Monster_Lasagna "Monster Lasagna") | monsterlasagna | common/inventory/monsterlasagna |
| [Powcake](/wiki/Powcake "Powcake") | powcake | common/inventory/powcake |
| [Froggle Bunwich](/wiki/Froggle_Bunwich "Froggle Bunwich") | frogglebunwich | common/inventory/frogglebunwich |
| [Pumpkin Cookie](/wiki/Pumpkin_Cookie "Pumpkin Cookie") | pumpkincookie | common/inventory/pumpkincookie |
| [Honey Ham](/wiki/Honey_Ham "Honey Ham") | honeyham | common/inventory/honeyham |
| [Meatballs](/wiki/Meatballs "Meatballs") | meatballs | common/inventory/meatballs |
| [Wet Goop](/wiki/Wet_Goop "Wet Goop") | wetgoop | common/inventory/wetgoop |
| [Stuffed Eggplant](/wiki/Stuffed_Eggplant "Stuffed Eggplant") | stuffedeggplant | common/inventory/stuffedeggplant |
| [Taffy](/wiki/Taffy "Taffy") | taffy | common/inventory/taffy |
| [Honey Nuggets](/wiki/Honey_Nuggets "Honey Nuggets") | honeynuggets | common/inventory/honeynuggets |
| [Turkey Dinner](/wiki/Turkey_Dinner "Turkey Dinner") | turkeydinner | common/inventory/turkeydinner |
| [Fishsticks](/wiki/Fishsticks "Fishsticks") | fishsticks | common/inventory/fishsticks |
| [Fist Full of Jam](/wiki/Fist_Full_of_Jam "Fist Full of Jam") | jammypreserves | common/inventory/jammypreserves |
| [Fish Tacos](/wiki/Fish_Tacos "Fish Tacos") | fishtacos | common/inventory/fishtacos |
| [Butter Muffin](/wiki/Butter_Muffin "Butter Muffin") | butterflymuffin | common/inventory/butterflymuffin |
| [Pierogi](/wiki/Pierogi "Pierogi") | perogies | common/inventory/perogies |
| [Kabobs](/wiki/Kabobs "Kabobs") | kabobs | common/inventory/kabobs |
| [Meaty Stew](/wiki/Meaty_Stew "Meaty Stew") | bonestew | common/inventory/bonestew |
| [Bacon and Eggs](/wiki/Bacon_and_Eggs "Bacon and Eggs") | baconeggs | common/inventory/baconeggs |
| [Mandrake Soup](/wiki/Mandrake_Soup "Mandrake Soup") | mandrakesoup | common/inventory/mandrakesoup |
| [Purple Gem](/wiki/Purple_Gem "Purple Gem") | purplegem | common/inventory/purplegem |
| [Razor](/wiki/Razor "Razor") | razor | common/inventory/razor |
| [Red Gem](/wiki/Red_Gem "Red Gem") | redgem | common/inventory/redgem |
| [Rope](/wiki/Rope "Rope") | rope | common/inventory/rope |
| [Seeds](/wiki/Seeds "Seeds") | seeds | common/inventory/seeds |
| [Toasted Seeds](/wiki/Toasted_Seeds "Toasted Seeds") | seeds\_cooked | common/inventory/seeds\_cooked |
| [Sewing Kit](/wiki/Sewing_Kit "Sewing Kit") | sewing\_kit | common/inventory/sewing\_kit |
| [Shovel](/wiki/Shovel "Shovel") | shovel | common/inventory/shovel |
| [Regal Shovel](/wiki/Regal_Shovel "Regal Shovel") | goldenshovel | common/inventory/goldenshovel |
| [Silk](/wiki/Silk "Silk") | silk | common/inventory/silk |
| [Slurtle Slime](/wiki/Slurtle_Slime "Slurtle Slime") | slurtleslime | common/inventory/slurtleslime |
| [Broken Shells](/wiki/Broken_Shells "Broken Shells") | slurtle\_shellpieces | common/inventory/slurtle\_shellpieces |
| [Spear](/wiki/Spear "Spear") | spear | common/inventory/spear |
| [Spider Eggs](/wiki/Spider_Eggs "Spider Eggs") | spidereggsack | common/inventory/spidereggsack |
| [Spider Gland](/wiki/Spider_Gland "Spider Gland") | spidergland | common/inventory/spidergland |
| [Spitter Projectile](/wiki/Spitter "Spitter") | spider\_web\_spit | common/inventory/spider\_web\_spit |
| [Ice Projectile](/wiki/Ice_Staff "Ice Staff") | ice\_projectile | common/inventory/ice\_projectile |
| [Fire Projectile](/wiki/Fire_Staff "Fire Staff") | fire\_projectile | common/inventory/fire\_projectile |
| [Stinger](/wiki/Stinger "Stinger") | stinger | common/inventory/stinger |
| [Dapper Vest](/wiki/Dapper_Vest "Dapper Vest") | sweatervest | common/inventory/sweatervest |
| [Thulecite Club](/wiki/Thulecite_Club "Thulecite Club") | ruins\_bat | common/inventory/ruins\_bat |
| [Tallbird Egg](/wiki/Tallbird_Egg "Tallbird Egg") | tallbirdegg | common/inventory/tallbirdegg |
| [Hatching Tallbird Egg](/wiki/Hatching_Tallbird_Egg "Hatching Tallbird Egg") | tallbirdegg\_cracked | common/inventory/tallbirdegg\_cracked |
| [Fried Tallbird Egg](/wiki/Fried_Tallbird_Egg "Fried Tallbird Egg") | tallbirdegg\_cooked | common/inventory/tallbirdegg\_cooked |
| [Ring Thing](/wiki/Ring_Thing "Ring Thing") | teleportato\_ring | common/inventory/teleportato\_ring |
| [Wooden Thing](/wiki/Wooden_Thing "Wooden Thing") | teleportato\_base | common/inventory/teleportato\_base |
| [Box Thing](/wiki/Box_Thing "Box Thing") | teleportato\_box | common/inventory/teleportato\_box |
| [Crank Thing](/wiki/Crank_Thing "Crank Thing") | teleportato\_crank | common/inventory/teleportato\_crank |
| [Metal Potato Thing](/wiki/Metal_Potato_Thing "Metal Potato Thing") | teleportato\_potato | common/inventory/teleportato\_potato |
| [Tentacle Spike](/wiki/Tentacle_Spike "Tentacle Spike") | tentaclespike | common/inventory/tentaclespike |
| [Tentacle Spots](/wiki/Tentacle_Spots "Tentacle Spots") | tentaclespots | common/inventory/tentaclespots |
| [Torch](/wiki/Torch "Torch") | torch | common/inventory/torch |
| [Trap](/wiki/Trap "Trap") | trap | common/inventory/trap |
| [Tooth Trap](/wiki/Tooth_Trap "Tooth Trap") | trap\_teeth | common/inventory/trap\_teeth |
| [Maxwell's Tooth Trap](/wiki/Maxwell%27s_Tooth_Trap "Maxwell's Tooth Trap") | trap\_teeth\_maxwell | common/inventory/trap\_teeth\_maxwell |
| [Melty Marbles](/wiki/Melty_Marbles "Melty Marbles") | trinket\_1 | common/inventory/trinket\_1 |
| [Fake Kazoo](/wiki/Fake_Kazoo "Fake Kazoo") | trinket\_2 | common/inventory/trinket\_2 |
| [Gord's Knot](/wiki/Gord%27s_Knot "Gord's Knot") | trinket\_3 | common/inventory/trinket\_3 |
| [Gnome](/wiki/Gnome "Gnome") | trinket\_4 | common/inventory/trinket\_4 |
| [Tiny Rocketship](/wiki/Tiny_Rocketship "Tiny Rocketship") | trinket\_5 | common/inventory/trinket\_5 |
| [Frazzled Wires](/wiki/Frazzled_Wires "Frazzled Wires") | trinket\_6 | common/inventory/trinket\_6 |
| [Ball and Cup](/wiki/Ball_and_Cup "Ball and Cup") | trinket\_7 | common/inventory/trinket\_7 |
| [Hardened Rubber Bung](/wiki/Hardened_Rubber_Bung "Hardened Rubber Bung") | trinket\_8 | common/inventory/trinket\_8 |
| [Mismatched Buttons](/wiki/Mismatched_Buttons "Mismatched Buttons") | trinket\_9 | common/inventory/trinket\_9 |
| [Second-hand Dentures](/wiki/Second-hand_Dentures "Second-hand Dentures") | trinket\_10 | common/inventory/trinket\_10 |
| [Lying Robot](/wiki/Lying_Robot "Lying Robot") | trinket\_11 | common/inventory/trinket\_11 |
| [Dessicated Tentacle](/wiki/Dessicated_Tentacle "Dessicated Tentacle") | trinket\_12 | common/inventory/trinket\_12 |
| [Koalefant Trunk](/wiki/Koalefant_Trunk "Koalefant Trunk") | trunk\_summer | common/inventory/trunk\_summer |
| [Winter Koalefant Trunk](/wiki/Winter_Koalefant_Trunk "Winter Koalefant Trunk") | trunk\_winter | common/inventory/trunk\_winter |
| [Koalefant Trunk Steak](/wiki/Koalefant_Trunk_Steak "Koalefant Trunk Steak") | trunk\_cooked | common/inventory/trunk\_cooked |
| [Breezy Vest](/wiki/Breezy_Vest "Breezy Vest") | trunkvest\_summer | common/inventory/trunkvest\_summer |
| [Puffy Vest](/wiki/Puffy_Vest "Puffy Vest") | trunkvest\_winter | common/inventory/trunkvest\_winter |
| [Twigs](/wiki/Twigs "Twigs") | twigs | common/inventory/twigs |
| [Umbrella](/wiki/Umbrella "Umbrella") | umbrella | common/inventory/umbrella |
| [Carrot](/wiki/Carrot "Carrot") | carrot | common/inventory/carrot |
| [Roasted Carrot](/wiki/Roasted_Carrot "Roasted Carrot") | carrot\_cooked | common/inventory/carrot\_cooked |
| [Carrot Seeds](/wiki/Carrot_Seeds "Carrot Seeds") | carrot\_seeds | common/inventory/carrot\_seeds |
| [Corn](/wiki/Corn "Corn") | corn | common/inventory/corn |
| [Popcorn](/wiki/Popcorn "Popcorn") | corn\_cooked | common/inventory/corn\_cooked |
| [Corn Seeds](/wiki/Corn_Seeds "Corn Seeds") | corn\_seeds | common/inventory/corn\_seeds |
| [Berries](/wiki/Berries "Berries") | berries | common/inventory/berries |
| [Roasted Berries](/wiki/Roasted_Berries "Roasted Berries") | berries\_cooked | common/inventory/berries\_cooked |
| [Pumpkin](/wiki/Pumpkin "Pumpkin") | pumpkin | common/inventory/pumpkin |
| [Hot Pumpkin](/wiki/Hot_Pumpkin "Hot Pumpkin") | pumpkin\_cooked | common/inventory/pumpkin\_cooked |
| [Pumpkin Seeds](/wiki/Pumpkin_Seeds "Pumpkin Seeds") | pumpkin\_seeds | common/inventory/pumpkin\_seeds |
| [Pomegranate](/wiki/Pomegranate "Pomegranate") | pomegranate | common/inventory/pomegranate |
| [Sliced Pomegranate](/wiki/Sliced_Pomegranate "Sliced Pomegranate") | pomegranate\_cooked | common/inventory/pomegranate\_cooked |
| [Pomegranate Seeds](/wiki/Pomegranate_Seeds "Pomegranate Seeds") | pomegranate\_seeds | common/inventory/pomegranate\_seeds |
| [Eggplant](/wiki/Eggplant "Eggplant") | eggplant | common/inventory/eggplant |
| [Braised Eggplant](/wiki/Braised_Eggplant "Braised Eggplant") | eggplant\_cooked | common/inventory/eggplant\_cooked |
| [Eggplant Seeds](/wiki/Eggplant_Seeds "Eggplant Seeds") | eggplant\_seeds | common/inventory/eggplant\_seeds |
| [Durian](/wiki/Durian "Durian") | durian | common/inventory/durian |
| [Extra Smelly Durian](/wiki/Extra_Smelly_Durian "Extra Smelly Durian") | durian\_cooked | common/inventory/durian\_cooked |
| [Durian Seeds](/wiki/Durian_Seeds "Durian Seeds") | durian\_seeds | common/inventory/durian\_seeds |
| [Dragon Fruit](/wiki/Dragon_Fruit "Dragon Fruit") | dragonfruit | common/inventory/dragonfruit |
| [Prepared Dragon Fruit](/wiki/Prepared_Dragon_Fruit "Prepared Dragon Fruit") | dragonfruit\_cooked | common/inventory/dragonfruit\_cooked |
| [Dragon Fruit Seeds](/wiki/Dragon_Fruit_Seeds "Dragon Fruit Seeds") | dragonfruit\_seeds | common/inventory/dragonfruit\_seeds |
| [Walrus Dart (Projectile)](/wiki/MacTusk "MacTusk") | walrus\_dart | common/inventory/walrus\_dart |
| [Walrus Tusk](/wiki/Walrus_Tusk "Walrus Tusk") | walrus\_tusk | common/inventory/walrus\_tusk |
| [Lucy the Axe](/wiki/Lucy_the_Axe "Lucy the Axe") | lucy | common/inventory/lucy |
| [Telelocator Staff](/wiki/Telelocator_Staff "Telelocator Staff") | telestaff | common/inventory/telestaff |
| [Foliage](/wiki/Foliage "Foliage") | foliage | common/inventory/foliage |
| [Fleshy Bulb](/wiki/Fleshy_Bulb "Fleshy Bulb") | lureplantbulb | common/inventory/lureplantbulb |
| [Science Machine](/wiki/Science_Machine "Science Machine") | researchlab | common/inventory/researchlab |
| [Green Gem](/wiki/Green_Gem "Green Gem") | greengem | common/inventory/greengem |
| [Orange Gem](/wiki/Orange_Gem "Orange Gem") | orangegem | common/inventory/orangegem |
| [Yellow Gem](/wiki/Yellow_Gem "Yellow Gem") | yellowgem | common/inventory/yellowgem |
| [Thulecite](/wiki/Thulecite "Thulecite") | thulecite | common/inventory/thulecite |
| [Nightmare Amulet](/wiki/Nightmare_Amulet "Nightmare Amulet") | purpleamulet | common/inventory/purpleamulet |
| [Construction Amulet](/wiki/Construction_Amulet "Construction Amulet") | greenamulet | common/inventory/greenamulet |
| [Slurper Pelt](/wiki/Slurper_Pelt "Slurper Pelt") | slurper\_pelt | common/inventory/slurper\_pelt |
| [Ice](/wiki/Ice "Ice") | ice |  |
| [Mini Glacier](/wiki/Mini_Glacier "Mini Glacier") | rock\_ice |  |
| [Scales](/wiki/Scales "Scales") | dragon\_scales |  |
| [Scalemail](/wiki/Scalemail "Scalemail") | armordragonfly |  |

Telltale Heart reviver

## Animals[]

| **Name** | **Spawn Code** | **Prefab location** |
| --- | --- | --- |
| [Baby Beefalo](/wiki/Baby_Beefalo "Baby Beefalo") | babybeefalo | forest/animals/babybeefalo |
| [Beefalo](/wiki/Beefalo "Beefalo") | beefalo | forest/animals/beefalo |
| [Beefalo (Herd)](/wiki/Beefalo "Beefalo") | beefaloherd | forest/animals/beefaloherd |
| [Crow](/wiki/Crow "Crow") | crow | forest/animals/crow |
| [Redbird](/wiki/Redbird "Redbird") | robin | forest/animals/robin |
| [Snowbird](/wiki/Snowbird "Snowbird") | robin\_winter | forest/animals/robin\_winter |
| [Frog](/wiki/Frog "Frog") | frog | forest/animals/frog |
| [Koalefant (Summer)](/wiki/Koalefant "Koalefant") | koalefant\_summer | forest/animals/koalefant\_summer |
| [Koalefant (Winter)](/wiki/Koalefant "Koalefant") | koalefant\_winter | forest/animals/koalefant\_winter |
| [Merm](/wiki/Merm "Merm") | merm | forest/animals/merm |
| [Pengull](/wiki/Pengull "Pengull") | penguin | forest/animals/penguin |
| [Gobbler](/wiki/Gobbler "Gobbler") | perd | forest/animals/perd |
| [Rabbit](/wiki/Rabbit "Rabbit") | rabbit | forest/animals/rabbit |
| [MacTusk](/wiki/MacTusk "MacTusk") | walrus | forest/animals/walrus |
| [Wee MacTusk](/wiki/Wee_MacTusk "Wee MacTusk") | little\_walrus | forest/animals/little\_walrus |

## Others[]

| **Name** | **Spawn Code** | **Prefab location** |
| --- | --- | --- |
| [Maxwell's Door](/wiki/Maxwell%27s_Door "Maxwell's Door") | adventure\_portal | common/adventure\_portal |
| [Bee Box (Silhouette)](/wiki/Bee_Box "Bee Box") | beebox\_placer | common/beebox\_placer |
| [Birdcage](/wiki/Birdcage "Birdcage") | birdcage | common/birdcage |
| [Birdcage (Silhouette)](/wiki/Birdcage "Birdcage") | birdcage\_placer | common/birdcage\_placer |
| [Clockwork Bishop](/wiki/Clockwork_Bishop "Clockwork Bishop") | bishop | chessboard/bishop |
| [Flower (Silhouette)](/wiki/Flower "Flower") | butterfly\_placer | common/butterfly\_placer |
| [Campfire (Silhouette)](/wiki/Campfire "Campfire") | campfire\_placer | common/campfire\_placer |
| [Chester](/wiki/Chester "Chester") | chester | common/chester |
| [Crock Pot](/wiki/Crock_Pot "Crock Pot") | cookpot | common/cookpot |
| [Crock Pot (Silhouette)](/wiki/Crock_Pot "Crock Pot") | cookpot\_placer | common/cookpot\_placer |
| [Eyes](/wiki/Shadow_Creature#Eyes "Shadow Creature") | creepyeyes | common/creepyeyes |
| [Divining Rod Holder (Wooden Thing)](/wiki/Divining_Rod "Divining Rod") | diviningrodbase | common/diviningrodbase |
| [Divining Rod Holder](/wiki/Divining_Rod "Divining Rod") | diviningrodstart | common/diviningrodstart |
| [Basic Farm (Silhouette)](/wiki/Basic_Farm "Basic Farm") | farmplot\_placer | common/farmplot\_placer |
| [Fire Pit (Silhouette)](/wiki/Fire_Pit "Fire Pit") | firepit\_placer | common/firepit\_placer |
| Global | global | common/global |
| [Grid (Silhouette)](/wiki/Pitchfork "Pitchfork") | gridplacer | common/gridplacer |
| [Sign (Silhouette)](/wiki/Sign "Sign") | homesign\_placer | common/homesign\_placer |
| [Ice Box](/wiki/Ice_Box "Ice Box") | icebox | common/icebox |
| [Ice Box (Silhouette)](/wiki/Ice_Box "Ice Box") | icebox\_placer | common/icebox\_placer |
| [Clockwork Knight](/wiki/Clockwork_Knight "Clockwork Knight") | knight | chessboard/knight |
| [Tree Guard](/wiki/Tree_Guard "Tree Guard") | leif | common/leif |
| [Tree Guard (Sparse)](/wiki/Tree_Guard "Tree Guard") | leif\_sparse | common/leif\_sparse |
| [Lightning Rod (Silhouette)](/wiki/Lightning_Rod "Lightning Rod") | lightning\_rod\_placer | common/lightning\_rod\_placer |
| [Wes (Locked)](/wiki/Wes "Wes") | lockedwes | common/lockedwes |
| [Mandrake](/wiki/Mandrake "Mandrake") | mandrake | common/mandrake |
| [Cooked Mandrake](/wiki/Cooked_Mandrake "Cooked Mandrake") | cookedmandrake | common/cookedmandrake |
| [Nightmare Lock](/wiki/Nightmare_Lock "Nightmare Lock") | maxwelllock | common/maxwelllock |
| [Drying Rack (Silhouette)](/wiki/Drying_Rack "Drying Rack") | meatrack\_placer | common/meatrack\_placer |
| [Night Light (Silhouette)](/wiki/Night_Light "Night Light") | nightlight\_placer | common/nightlight\_placer |
| [Pig House (Silhouette)](/wiki/Pig_House "Pig House") | pighouse\_placer | common/pighouse\_placer |
| [Guardian Pig](/wiki/Guardian_Pig "Guardian Pig") | pigguard | common/character/pigguard |
| [Planted Pine Cone (Silhouette)](/wiki/Tree "Tree") | pinecone\_placer | common/pinecone\_placer |
| [Dry Berry Bush (Silhouette)](/wiki/Berry_Bush "Berry Bush") | dug\_berrybush\_placer | common/dug\_berrybush\_placer |
| [Dry Berry Bush (Silhouette)](/wiki/Berry_Bush "Berry Bush") | dug\_berrybush2\_placer | common/dug\_berrybush2\_placer |
| [Sapling (Silhouette)](/wiki/Sapling "Sapling") | dug\_sapling\_placer | common/dug\_sapling\_placer |
| [Grass (Silhouette)](/wiki/Grass "Grass") | dug\_grass\_placer | common/dug\_grass\_placer |
| [Rabbit Hutch (Silhouette)](/wiki/Rabbit_Hutch "Rabbit Hutch") | rabbithouse\_placer | common/rabbithouse\_placer |
| [Rainometer (Silhouette)](/wiki/Rainometer "Rainometer") | rainometer\_placer | common/rainometer\_placer |
| [Science Machine (Silhouette)](/wiki/Science_Machine "Science Machine") | researchlab\_placer | common/researchlab\_placer |
| [Alchemy Engine (Silhouette)](/wiki/Alchemy_Engine "Alchemy Engine") | researchlab2\_placer | common/researchlab2\_placer |
| [Shadow Manipulator (Silhouette)](/wiki/Shadow_Manipulator "Shadow Manipulator") | researchlab3\_placer | common/researchlab3\_placer |
| [Meat Effigy (Silhouette)](/wiki/Meat_Effigy "Meat Effigy") | resurrectionstatue\_placer | common/resurrectionstatue\_placer |
| [Night Hand](/wiki/Night_Hand "Night Hand") | shadowhand | common/shadowhand |
| [Night Hand (Arm)](/wiki/Night_Hand "Night Hand") | shadowhand\_arm | common/shadowhand\_arm |
| [Mr. Skitts](/wiki/Mr._Skitts "Mr. Skitts") | shadowskittish | common/shadowskittish |
| [Shadow Watcher](/wiki/Shadow_Creature#Shadow_Watcher "Shadow Creature") | shadowwatcher | common/shadowwatcher |
| [Smallbird](/wiki/Smallbird "Smallbird") | smallbird | common/smallbird |
| [Teenbird](/wiki/Teenbird "Teenbird") | teenbird | common/teenbird |
| [Sound Icon](/wiki/Guides/Getting_Started_Guide#Game_Screen "Guides/Getting Started Guide") | sounddebugicon | debug/sounddebugicon |
| [Spawn Point](/wiki/Death#Resurrection "Death") | spawnpoint | common/spawnpoint |
| [Spider Den (Silhouette)](/wiki/Spider_Den "Spider Den") | spidereggsack\_placer | common/spidereggsack\_placer |
| [Sticky Webbing](/wiki/Spider_Den#Sticky_Webbing "Spider Den") | spider\_web\_spit\_creep | common/spider\_web\_spit\_creep |
| [Rot](/wiki/Rot "Rot") | spoiled\_food | common/spoiled\_food |
| [Telelocator Focus](/wiki/Telelocator_Focus "Telelocator Focus") | teleportlocation | common/teleportlocation |
| [Tent (Silhouette)](/wiki/Tent "Tent") | tent\_placer | common/tent\_placer |
| [Chest](/wiki/Chest "Chest") | treasurechest | common/treasurechest |
| [Chest (Silhouette)](/wiki/Chest "Chest") | treasurechest\_placer | common/treasurechest\_placer |
| [Ornate Chest](/wiki/Chest "Chest") | pandoraschest | common/pandoraschest |
| [Skull Chest](/wiki/Chest "Chest") | skullchest | common/skullchest |
| [Stone Wall](/wiki/Stone_Wall "Stone Wall") | wall\_stone | common/wall\_stone |
| [Stone Wall (Item)](/wiki/Stone_Wall "Stone Wall") | wall\_stone\_item | common/wall\_stone\_item |
| [Stone Wall (Silhouette)](/wiki/Stone_Wall "Stone Wall") | wall\_stone\_placer | common/wall\_stone\_placer |
| [Wood Wall](/wiki/Wood_Wall "Wood Wall") | wall\_wood | common/wall\_wood |
| [Wood Wall (Item)](/wiki/Wood_Wall "Wood Wall") | wall\_wood\_item | common/wall\_wood\_item |
| [Wood Wall (Silhouette)](/wiki/Wood_Wall "Wood Wall") | wall\_wood\_placer | common/wall\_wood\_placer |
| [Hay Wall](/wiki/Hay_Wall "Hay Wall") | wall\_hay | common/wall\_hay |
| [Hay Wall (Item)](/wiki/Hay_Wall "Hay Wall") | wall\_hay\_item | common/wall\_hay\_item |
| [Hay Wall (Silhouette)](/wiki/Hay_Wall "Hay Wall") | wall\_hay\_placer | common/wall\_hay\_placer |
| [Fire (Willow)](/wiki/Fire "Fire") | willowfire | common/willowfire |
| [Winterometer (Silhouette)](/wiki/Winterometer "Winterometer") | winterometer\_placer | common/winterometer\_placer |
| [World](/wiki/World "World") | world | world |
| [Wormhole](/wiki/Wormhole "Wormhole") | wormhole | common/wormhole |
| [Sick Worm Hole](/wiki/Sick_Worm_Hole "Sick Worm Hole") | wormhole\_limited\_1 | common/wormhole\_limited\_1 |
| [Ice Flingomatic](/wiki/Ice_Flingomatic "Ice Flingomatic") | firesuppressor | common/firesuppressor |

## Forest[]

| **Name** | **Spawn Code** | **Prefab location** |
| --- | --- | --- |
| [Animal Track](/wiki/Suspicious_Dirt_Pile "Suspicious Dirt Pile") | animal\_track | forest/objects/animal\_track |
| [Basalt](/wiki/Basalt "Basalt") | basalt | forest/objects/basalt |
| [Basalt Pillar](/wiki/Basalt "Basalt") | basalt\_pillar | forest/objects/basalt\_pillar |
| [Butterfly](/wiki/Butterfly "Butterfly") | butterfly | forest/common/butterfly |
| [Dirtpile](/wiki/Suspicious_Dirt_Pile "Suspicious Dirt Pile") | dirtpile | forest/objects/dirtpile |
| [Evergreen](/wiki/Evergreen "Evergreen") | evergreen | forest/objects/trees/evergreen |
| Evergreen Normal | evergreen\_normal | forest/objects/trees/evergreen\_normal |
| Evergreen Tall | evergreen\_tall | forest/objects/trees/evergreen\_tall |
| Evergreen Short | evergreen\_short | forest/objects/trees/evergreen\_short |
| Evergreen Sparse | evergreen\_sparse | forest/objects/trees/evergreen\_sparse |
| Evergreen Sparse Normal | evergreen\_sparse\_normal | forest/objects/trees/evergreen\_sparse\_normal |
| Evergreen Sparse Tall | evergreen\_sparse\_tall | forest/objects/trees/evergreen\_sparse\_tall |
| Evergreen Sparse Short | evergreen\_sparse\_short | forest/objects/trees/evergreen\_sparse\_short |
| Evergreen Burnt | evergreen\_burnt | forest/objects/trees/evergreen\_burnt |
| Evergreen Stump | evergreen\_stump | forest/objects/trees/evergreen\_stump |
| Farmrock | farmrock | forest/objects/farmdecor/farmrock |
| Farmrocktall | farmrocktall | forest/objects/farmdecor/farmrocktall |
| Farmrockflat | farmrockflat | forest/objects/farmdecor/farmrockflat |
| Stick | stick | forest/objects/farmdecor/stick |
| Stickright | stickright | forest/objects/farmdecor/stickright |
| Stickleft | stickleft | forest/objects/farmdecor/stickleft |
| Signleft | signleft | forest/objects/farmdecor/signleft |
| Fencepost | fencepost | forest/objects/farmdecor/fencepost |
| Fencepostright | fencepostright | forest/objects/farmdecor/fencepostright |
| Signright | signright | forest/objects/farmdecor/signright |
| [Flower](/wiki/Flower "Flower") | flower | forest/objects/flower |
| [Flower Evil](/wiki/Flower#Evil_Flower "Flower") | flower\_evil | forest/objects/flower\_evil |
| [Forest](/wiki/Forest "Forest") | forest | forest |
| [Grass](/wiki/Grass "Grass") | grass | forest/objects/grass |
| Depleted Grass | depleted\_grass | forest/objects/depleted\_grass |
| Horizontal Maxwelllight | horizontal\_maxwelllight | forest/objects/horizontal\_maxwelllight |
| Insanityrock | insanityrock | forest/objects/rocks/insanityrock |
| Marblepillar | marblepillar | forest/objects/marblepillar |
| Marbletree | marbletree | forest/objects/marbletree |
| Marbletree 1 | marbletree\_1 | forest/objects/marbletree\_1 |
| Marbletree 2 | marbletree\_2 | forest/objects/marbletree\_2 |
| Marbletree 3 | marbletree\_3 | forest/objects/marbletree\_3 |
| Marbletree 4 | marbletree\_4 | forest/objects/marbletree\_4 |
| Maxwellhead Trigger | maxwellhead\_trigger | forest/objects/maxwellhead\_trigger |
| Mistarea | mistarea | common/forest/mistarea |
| [Red Mushroom](/wiki/Red_Mushroom "Red Mushroom") | red\_mushroom | forest/objects/red\_mushroom |
| [Green Mushroom](/wiki/Green_Mushroom "Green Mushroom") | green\_mushroom | forest/objects/green\_mushroom |
| [Blue Mushroom](/wiki/Blue_Mushroom "Blue Mushroom") | blue\_mushroom | forest/objects/blue\_mushroom |
| [Obelisk](/wiki/Obelisk "Obelisk")(insane up) | insanityrock | forest/objects/rocks/insanityrock |
| [Obelisk](/wiki/Obelisk "Obelisk") (insane down) | sanityrock | forest/objects/rocks/sanityrock |
| Horizontal Maxwelllight | horizontal\_maxwelllight | forest/objects/horizontal\_maxwelllight |
| Vertical Maxwelllight | vertical\_maxwelllight | forest/objects/vertical\_maxwelllight |
| Quad Maxwelllight | quad\_maxwelllight | forest/objects/quad\_maxwelllight |
| Pigtorch | pigtorch | forest/objects/pigtorch |
| Pigtorch Fuel | pigtorch\_fuel | forest/object/pigtorch\_fuel |
| [Reeds](/wiki/Reeds "Reeds") | reeds | forest/objects/reeds |
| [Touch Stone](/wiki/Touch_Stone "Touch Stone") | resurrectionstone | forest/objects/resurrectionstone |
| Rock1 | rock1 | forest/objects/rocks/rock1 |
| Rock2 | rock2 | forest/objects/rocks/rock2 |
| Rock Flintless | rock\_flintless | forest/objects/rocks/rock\_flintless |
| Sanityrock | sanityrock | forest/objects/rocks/sanityrock |
| [Sapling](/wiki/Sapling "Sapling") | sapling | forest/objects/sapling |
| [Sinkhole](/wiki/Sinkhole "Sinkhole") | sinkhole | forest/objects/sinkhole |
| Cave Stairs | cave\_stairs | forest/objects/cave\_stairs |
| Statueharp | statueharp | forest/objects/statueharp |
| [Maxwell Statue](/wiki/Maxwell_Statue "Maxwell Statue") | statuemaxwell | forest/objects/statuemaxwell |
| Pighead | pighead | forest/objects/pighead |
| Mermhead | mermhead | forest/objects/mermhead |
| Sunkboat | sunkboat | forest/objects/sunkboat |
| Turf Forest | turf\_forest | common/objects/turf\_forest |

## Marsh[]

| **Name** | **Spawn Code** | **Prefab location** |
| --- | --- | --- |
| [Marsh Bush](/wiki/Spiky_Bush "Spiky Bush") | marsh\_bush | marsh/objects/marsh\_bush |
| [Plant](/wiki/Plant "Plant") | marsh\_plant | marsh/objects/marsh\_plant |
| [Marsh Tree](/wiki/Tree#Spiky_Tree "Tree") | marsh\_tree | marsh/objects/marsh\_tree |
| Dug Marsh Bush | dug\_marsh\_bush | common/objects/dug\_marsh\_bush |
| Dug Marsh Bush Placer | dug\_marsh\_bush\_placer | common/dug\_marsh\_bush\_placer |
| [Pond](/wiki/Pond "Pond") | pond | marsh/objects/pond |
| Pond Mos | pond\_mos | marsh/objects/pond\_mos |
| Turf Marsh | turf\_marsh | common/objects/turf\_marsh |

## Cave[]

| **Name** | **Spawn Code** | **Prefab location** |
| --- | --- | --- |
| [Bat](/wiki/Bat "Bat") | bat | cave/bat |
| [Cave](/wiki/Cave "Cave") | cave | cave |
| Cavelight | cavelight | common/cavelight |
| Cave Entrance | cave\_entrance | common/cave\_entrance |
| Cave Exit | cave\_exit | common/cave\_exit |
| Ceiling | ceiling | caves/ceiling |
| Flower Cave | flower\_cave | cave/objects/flower\_cave |
| Flower Cave Double | flower\_cave\_double | cave/objects/flower\_cave\_double |
| Flower Cave Triple | flower\_cave\_triple | cave/objects/flower\_cave\_triple |
| Mushtree Tall | mushtree\_tall | cave/objects/mushtree\_tall |
| [Slurtle](/wiki/Slurtle "Slurtle") | slurtle | cave/slurtle |
| [Snurtle](/wiki/Snurtle "Snurtle") | snurtle | cave/snurtle |
| Slurtlehole | slurtlehole | cave/objects/slurtlehole |
| Spiderhole | spiderhole | cave/objects/spiderhole |
| Stalagmite Full | stalagmite\_full | cave/objects/stalagmite\_full |
| Stalagmite Med | stalagmite\_med | cave/objects/stalagmite\_med |
| Stalagmite Low | stalagmite\_low | cave/objects/stalagmite\_low |
| [Stalagmite](/wiki/Stalagmite "Stalagmite") | stalagmite | cave/objects/stalagmite |
| Teamleader | teamleader | cave/objects/teamleader |
| Turf Cave | turf\_cave | common/objects/turf\_cave |
| [Cave Banana Tree](/wiki/Cave_Banana_Tree "Cave Banana Tree") | cave\_banana\_tree | cave/objects/cave\_banana\_tree |
| [Fern](/wiki/Fern "Fern") | cave\_fern | cave/objects/cave\_fern |
| [Depths Worm](/wiki/Depths_Worm "Depths Worm") | worm | cave/worm |

## Blueprints[]

| **Name** | **Spawn Code** | **Prefab location** |
| --- | --- | --- |
| [Blueprint](/wiki/Blueprint "Blueprint") | blueprint | common/inventory/blueprint |
| Improved Farm Blueprint | food\_blueprint | common/inventory/food\_blueprint |
| Structures Blueprint | structures\_blueprint | common/inventory/structures\_blueprint |
| Tools Blueprint | tools\_blueprint | common/inventory/tools\_blueprint |
| Dress Blueprint | dress\_blueprint | common/inventory/dress\_blueprint |
| Fight Blueprint | fight\_blueprint | common/inventory/fight\_blueprint |
| Magic Blueprint | magic\_blueprint | common/inventory/magic\_blueprint |
| Refine Blueprint | refine\_blueprint | common/inventory/refine\_blueprint |
| Science Blueprint | science\_blueprint | common/inventory/science\_blueprint |
| Light Blueprint | light\_blueprint | common/inventory/light\_blueprint |
| Survival Blueprint | survival\_blueprint | common/inventory/survival\_blueprint |
| Axe Blueprint | axe\_blueprint | common/inventory/axe\_blueprint |
| [Opulent Pickaxe](/wiki/Pickaxe#Opulent_Pickaxe "Pickaxe") Blueprint | goldenpickaxe\_blueprint | common/inventory/goldenpickaxe\_blueprint |
| [Pumpkin Lantern](/wiki/Pumpkin_Lantern "Pumpkin Lantern") Blueprint | pumpkin\_lantern\_blueprint | common/inventory/pumpkin\_lantern\_blueprint |
| [Rabbit Earmuff](/wiki/Rabbit_Earmuff "Rabbit Earmuff") Blueprint | earmuffshat\_blueprint | common/inventory/earmuffshat\_blueprint |
| [Meat Effigy](/wiki/Meat_Effigy "Meat Effigy") Blueprint | resurrectionstatue\_blueprint | common/inventory/resurrectionstatue\_blueprint |
| [Cut Stone](/wiki/Cut_Stone "Cut Stone") Blueprint | cutstone\_blueprint | common/inventory/cutstone\_blueprint |
| [Puffy Vest](/wiki/Puffy_Vest "Puffy Vest") Blueprint | trunkvest\_winter\_blueprint | common/inventory/trunkvest\_winter\_blueprint |
| [Cobblestones](/wiki/Cobblestones "Cobblestones") Blueprint | turf\_road\_blueprint | common/inventory/turf\_road\_blueprint |
| [Dark Sword](/wiki/Dark_Sword "Dark Sword") Blueprint | nightsword\_blueprint | common/inventory/nightsword\_blueprint |
| [Carpeted Flooring](/wiki/Carpeted_Flooring "Carpeted Flooring") Blueprint | turf\_carpetfloor\_blueprint | common/inventory/turf\_carpetfloor\_blueprint |
| [Honey Poultice](/wiki/Honey_Poultice "Honey Poultice") Blueprint | bandage\_blueprint | common/inventory/bandage\_blueprint |
| [Hay Wall](/wiki/Wall#Hay_Wall "Wall") Blueprint | wall\_hay\_item\_blueprint | common/inventory/wall\_hay\_item\_blueprint |
| [Lightning Rod](/wiki/Lightning_Rod "Lightning Rod") Blueprint | lightning\_rod\_blueprint | common/inventory/lightning\_rod\_blueprint |
| [Pickaxe](/wiki/Pickaxe "Pickaxe") Blueprint | pickaxe\_blueprint | common/inventory/pickaxe\_blueprint |
| [Pitchfork](/wiki/Pitchfork "Pitchfork") Blueprint | pitchfork\_blueprint | common/inventory/pitchfork\_blueprint |
| [Fire Pit](/wiki/Fire_Pit "Fire Pit") Blueprint | firepit\_blueprint | common/inventory/firepit\_blueprint |
| [Straw Roll](/wiki/Bed_Roll "Bed Roll") Blueprint | bedroll\_straw\_blueprint | common/inventory/bedroll\_straw\_blueprint |
| Armor Sanity Blueprint | armor\_sanity\_blueprint | common/inventory/armor\_sanity\_blueprint |
| Bedroll Furry Blueprint | bedroll\_furry\_blueprint | common/inventory/bedroll\_furry\_blueprint |
| Blowdart Fire Blueprint | blowdart\_fire\_blueprint | common/inventory/blowdart\_fire\_blueprint |
| Campfire Blueprint | campfire\_blueprint | common/inventory/campfire\_blueprint |
| Tent Blueprint | tent\_blueprint | common/inventory/tent\_blueprint |
| Spear Blueprint | spear\_blueprint | common/inventory/spear\_blueprint |
| Cookpot Blueprint | cookpot\_blueprint | common/inventory/cookpot\_blueprint |
| Goldenaxe Blueprint | goldenaxe\_blueprint | common/inventory/goldenaxe\_blueprint |
| Rabbithouse Blueprint | rabbithouse\_blueprint | common/inventory/rabbithouse\_blueprint |
| Armormarble Blueprint | armormarble\_blueprint | common/inventory/armormarble\_blueprint |
| Icestaff Blueprint | icestaff\_blueprint | common/inventory/icestaff\_blueprint |
| Researchlab3 Blueprint | researchlab3\_blueprint | common/inventory/researchlab3\_blueprint |
| Pighouse Blueprint | pighouse\_blueprint | common/inventory/pighouse\_blueprint |
| Boards Blueprint | boards\_blueprint | common/inventory/boards\_blueprint |
| Birdtrap Blueprint | birdtrap\_blueprint | common/inventory/birdtrap\_blueprint |
| Treasurechest Blueprint | treasurechest\_blueprint | common/inventory/treasurechest\_blueprint |
| Rope Blueprint | rope\_blueprint | common/inventory/rope\_blueprint |
| Trunkvest Summer Blueprint | trunkvest\_summer\_blueprint | common/inventory/trunkvest\_summer\_blueprint |
| Fast Farmplot Blueprint | fast\_farmplot\_blueprint | common/inventory/fast\_farmplot\_blueprint |
| Shovel Blueprint | shovel\_blueprint | common/inventory/shovel\_blueprint |
| Strawhat Blueprint | strawhat\_blueprint | common/inventory/strawhat\_blueprint |
| Flowerhat Blueprint | flowerhat\_blueprint | common/inventory/flowerhat\_blueprint |
| Gunpowder Blueprint | gunpowder\_blueprint | common/inventory/gunpowder\_blueprint |
| Razor Blueprint | razor\_blueprint | common/inventory/razor\_blueprint |
| Cane Blueprint | cane\_blueprint | common/inventory/cane\_blueprint |
| Tophat Blueprint | tophat\_blueprint | common/inventory/tophat\_blueprint |
| Winterhat Blueprint | winterhat\_blueprint | common/inventory/winterhat\_blueprint |
| Footballhat Blueprint | footballhat\_blueprint | common/inventory/footballhat\_blueprint |
| Armorgrass Blueprint | armorgrass\_blueprint | common/inventory/armorgrass\_blueprint |
| Icebox Blueprint | icebox\_blueprint | common/inventory/icebox\_blueprint |
| Beehat Blueprint | beehat\_blueprint | common/inventory/beehat\_blueprint |
| Beefalohat Blueprint | beefalohat\_blueprint | common/inventory/beefalohat\_blueprint |
| Sweatervest Blueprint | sweatervest\_blueprint | common/inventory/sweatervest\_blueprint |
| Minerhat Blueprint | minerhat\_blueprint | common/inventory/minerhat\_blueprint |
| Backpack Blueprint | backpack\_blueprint | common/inventory/backpack\_blueprint |
| Sewing Kit Blueprint | sewing\_kit\_blueprint | common/inventory/sewing\_kit\_blueprint |
| Turf Checkerfloor Blueprint | turf\_checkerfloor\_blueprint | common/inventory/turf\_checkerfloor\_blueprint |
| Beebox Blueprint | beebox\_blueprint | common/inventory/beebox\_blueprint |
| Fishingrod Blueprint | fishingrod\_blueprint | common/inventory/fishingrod\_blueprint |
| Trap Teeth Blueprint | trap\_teeth\_blueprint | common/inventory/trap\_teeth\_blueprint |
| Featherhat Blueprint | featherhat\_blueprint | common/inventory/featherhat\_blueprint |
| Goldenshovel Blueprint | goldenshovel\_blueprint | common/inventory/goldenshovel\_blueprint |
| Boomerang Blueprint | boomerang\_blueprint | common/inventory/boomerang\_blueprint |
| Hammer Blueprint | hammer\_blueprint | common/inventory/hammer\_blueprint |
| Umbrella Blueprint | umbrella\_blueprint | common/inventory/umbrella\_blueprint |
| Piggyback Blueprint | piggyback\_blueprint | common/inventory/piggyback\_blueprint |
| Blowdart Pipe Blueprint | blowdart\_pipe\_blueprint | common/inventory/blowdart\_pipe\_blueprint |
| Blowdart Sleep Blueprint | blowdart\_sleep\_blueprint | common/inventory/blowdart\_sleep\_blueprint |
| Meatrack Blueprint | meatrack\_blueprint | common/inventory/meatrack\_blueprint |
| Bushhat Blueprint | bushhat\_blueprint | common/inventory/bushhat\_blueprint |
| Nightlight Blueprint | nightlight\_blueprint | common/inventory/nightlight\_blueprint |
| Rainometer Blueprint | rainometer\_blueprint | common/inventory/rainometer\_blueprint |
| Hambat Blueprint | hambat\_blueprint | common/inventory/hambat\_blueprint |
| Healingsalve Blueprint | healingsalve\_blueprint | common/inventory/healingsalve\_blueprint |
| Nightmarefuel Blueprint | nightmarefuel\_blueprint | common/inventory/nightmarefuel\_blueprint |
| [Lantern Blueprint](/wiki/Lantern_Blueprint "Lantern Blueprint") | lantern\_blueprint | common/inventory/lantern\_blueprint |
| Wall Stone Item Blueprint | wall\_stone\_item\_blueprint | common/inventory/wall\_stone\_item\_blueprint |
| Papyrus Blueprint | papyrus\_blueprint | common/inventory/papyrus\_blueprint |
| Firestaff Blueprint | firestaff\_blueprint | common/inventory/firestaff\_blueprint |
| Armorwood Blueprint | armorwood\_blueprint | common/inventory/armorwood\_blueprint |
| Turf Cave Blueprint | turf\_cave\_blueprint | common/inventory/turf\_cave\_blueprint |
| Onemanband Blueprint | onemanband\_blueprint | common/inventory/onemanband\_blueprint |
| Homesign Blueprint | homesign\_blueprint | common/inventory/homesign\_blueprint |
| Turf Mud Blueprint | turf\_mud\_blueprint | common/inventory/turf\_mud\_blueprint |
| Wall Wood Item Blueprint | wall\_wood\_item\_blueprint | common/inventory/wall\_wood\_item\_blueprint |
| Panflute Blueprint | panflute\_blueprint | common/inventory/panflute\_blueprint |
| Winterometer Blueprint | winterometer\_blueprint | common/inventory/winterometer\_blueprint |
| Diviningrod Blueprint | diviningrod\_blueprint | common/inventory/diviningrod\_blueprint |
| Researchlab2 Blueprint | researchlab2\_blueprint | common/inventory/researchlab2\_blueprint |
| Amulet Blueprint | amulet\_blueprint | common/inventory/amulet\_blueprint |
| Researchlab Blueprint | researchlab\_blueprint | common/inventory/researchlab\_blueprint |
| Turf Underrock Blueprint | turf\_underrock\_blueprint | common/inventory/turf\_underrock\_blueprint |
| Trap Blueprint | trap\_blueprint | common/inventory/trap\_blueprint |
| Birdcage Blueprint | birdcage\_blueprint | common/inventory/birdcage\_blueprint |
| Beemine Blueprint | beemine\_blueprint | common/inventory/beemine\_blueprint |
| Turf Sinkhole Blueprint | turf\_sinkhole\_blueprint | common/inventory/turf\_sinkhole\_blueprint |
| Turf Fungus Blueprint | turf\_fungus\_blueprint | common/inventory/turf\_fungus\_blueprint |
| Torch Blueprint | torch\_blueprint | common/inventory/torch\_blueprint |
| Purplegem Blueprint | purplegem\_blueprint | common/inventory/purplegem\_blueprint |
| Turf Woodfloor Blueprint | turf\_woodfloor\_blueprint | common/inventory/turf\_woodfloor\_blueprint |
| Bugnet Blueprint | bugnet\_blueprint | common/inventory/bugnet\_blueprint |
| Heatrock Blueprint | heatrock\_blueprint | common/inventory/heatrock\_blueprint |
| Slow Farmplot Blueprint | slow\_farmplot\_blueprint | common/inventory/slow\_farmplot\_blueprint |

## Objects[]

| **Name** | **Spawn Code** | **Prefab location** |
| --- | --- | --- |
| [Bee Box](/wiki/Bee_Box "Bee Box") | beebox | common/objects/beebox |
| [Berry Bush](/wiki/Berry_Bush "Berry Bush") | berrybush | common/objects/berrybush |
| [Berrybush2](/wiki/Berry_Bush "Berry Bush") | berrybush2 | common/objects/berrybush2 |
| [Bonfire](/wiki/Bonfire "Bonfire") | bonfire | common/objects/bonfire |
| [Campfire](/wiki/Campfire "Campfire") | campfire | common/objects/campfire |
| [Cave Banana Tree](/wiki/Cave_Banana_Tree "Cave Banana Tree") | cave\_banana\_tree | caves/objects/cave\_banana\_tree |
| [Slow Farmplot](/wiki/Basic_Farm "Basic Farm") | slow\_farmplot | common/objects/slow\_farmplot |
| [Fast Farmplot](/wiki/Improved_Farm "Improved Farm") | fast\_farmplot | common/objects/fast\_farmplot |
| [Fern](/wiki/Fern "Fern") | cave\_fern | cave/objects/cave\_fern |
| [Fireflies](/wiki/Fireflies "Fireflies") | fireflies | common/objects/fireflies |
| [Fire Pit](/wiki/Fire_Pit "Fire Pit") | firepit | common/objects/firepit |
| Flies | flies | common/objects/flies |
| [Gravestone](/wiki/Headstone "Headstone") | gravestone | common/objects/gravestone |
| Homesign | homesign | common/objects/homesign |
| [Lightning Rod](/wiki/Lightning_Rod "Lightning Rod") | lightning\_rod | common/objects/lightning\_rod |
| [Maxwelllight](/wiki/Maxwell%27s_Light "Maxwell's Light") | maxwelllight | common/objects/maxwelllight |
| [Maxwelllight Area](/wiki/Maxwell%27s_Light "Maxwell's Light") | maxwelllight\_area | common/objects/maxwelllight\_area |
| Maxwellphonograph | maxwellphonograph | common/objects/maxwellphonograph |
| [Meatrack](/wiki/Drying_Rack "Drying Rack") | meatrack | common/objects/meatrack |
| [Mermhouse](/wiki/Rundown_House "Rundown House") | mermhouse | common/objects/mermhouse |
| Mound | mound | common/objects/mound |
| [Nightlight](/wiki/Night_Light "Night Light") | nightlight | common/objects/nightlight |
| Phonograph Gears | phonograph\_gears | common/objects/treasure/phonograph\_gears |
| Phonograph Box | phonograph\_box | common/objects/treasure/phonograph\_box |
| Phonograph Crank | phonograph\_crank | common/objects/treasure/phonograph\_crank |
| Phonograph Cone | phonograph\_cone | common/objects/treasure/phonograph\_cone |
| Phonograph Complete | phonograph\_complete | common/objects/treasure/phonograph\_complete |
| Pighouse | pighouse | common/objects/pighouse |
| Pigking | pigking | common/objects/pigking |
| Dug Berrybush | dug\_berrybush | common/objects/dug\_berrybush |
| Dug Berrybush2 | dug\_berrybush2 | common/objects/dug\_berrybush2 |
| Dug Sapling | dug\_sapling | common/objects/dug\_sapling |
| Dug Grass | dug\_grass | common/objects/dug\_grass |
| Plant Normal | plant\_normal | common/objects/plant\_normal |
| Portal Home | portal\_home | common/objects/portal\_home |
| Portal Level | portal\_level | common/objects/portal\_level |
| [Pumpkin Lantern](/wiki/Pumpkin_Lantern "Pumpkin Lantern") | pumpkin\_lantern | common/objects/pumpkin\_lantern |
| Rabbithole | rabbithole | common/objects/rabbithole |
| Rabbithouse | rabbithouse | common/objects/rabbithouse |
| [Rainometer](/wiki/Rainometer "Rainometer") | rainometer | common/objects/rainometer |
| Researchlab | researchlab | common/objects/researchlab |
| Researchlab2 | researchlab2 | common/objects/researchlab2 |
| Researchlab3 | researchlab3 | common/objects/researchlab3 |
| [Meat Effigy](/wiki/Meat_Effigy "Meat Effigy") | resurrectionstatue | common/objects/resurrectionstatue |
| [Skeleton](/wiki/Skeleton "Skeleton") | skeleton | common/objects/skeleton |
| Basic Shop | basic\_shop | common/objects/shop/basic\_shop |
| Tallbirdnest | tallbirdnest | common/objects/tallbirdnest |
| [Teleportato Base](/wiki/Teleportato_Base "Teleportato Base") | teleportato\_base | common/objects/teleportato\_base |
| [Teleportato Checkmate](/wiki/Teleportato_Checkmate "Teleportato Checkmate") | teleportato\_checkmate | common/objects/teleportato\_checkmate |
| [Tent](/wiki/Tent "Tent") | tent | common/objects/tent |
| Treeclump | treeclump | common/objects/treeclump |
| Tumbleweed spawn point | tumbleweedspawner |  |
| Turf Road | turf\_road | common/objects/turf\_road |
| Turf Rocky | turf\_rocky | common/objects/turf\_rocky |
| Turf Grass | turf\_grass | common/objects/turf\_grass |
| Turf Savanna | turf\_savanna | common/objects/turf\_savanna |
| Turf Dirt | turf\_dirt | common/objects/turf\_dirt |
| Turf Woodfloor | turf\_woodfloor | common/objects/turf\_woodfloor |
| Turf Carpetfloor | turf\_carpetfloor | common/objects/turf\_carpetfloor |
| Turf Checkerfloor | turf\_checkerfloor | common/objects/turf\_checkerfloor |
| Turf Fungus | turf\_fungus | common/objects/turf\_fungus |
| Turf Sinkhole | turf\_sinkhole | common/objects/turf\_sinkhole |
| Turf Underrock | turf\_underrock | common/objects/turf\_underrock |
| Turf Mud | turf\_mud | common/objects/turf\_mud |
| [Walrus Camp](/wiki/Walrus_Camp "Walrus Camp") | walrus\_camp | common/objects/walrus\_camp |
| [Winterometer](/wiki/Winterometer "Winterometer") | winterometer | common/objects/winterometer |

## Fx[]

| **Name** | **Spawn Code** | **Prefab location** |
| --- | --- | --- |
| Campfirefire | campfirefire | common/fx/campfirefire |
| Character Fire | character\_fire | common/fx/character\_fire |
| [Fire](/wiki/Fire "Fire") | fire | common/fx/fire |
| Frostbreath | frostbreath | common/fx/frostbreath |
| Ground Chunks Breaking | ground\_chunks\_breaking | common/fx/ground\_chunks\_breaking |
| Impact | impact | common/fx/impact |
| Lanternfire | lanternfire | common/fx/lanternfire |
| Maxwelllight Flame | maxwelllight\_flame | common/fx/maxwelllight\_flame |
| Mist | mist | common/fx/mist |
| Nightlight Flame | nightlight\_flame | common/fx/nightlight\_flame |
| Pigtorch Flame | pigtorch\_flame | common/fx/pigtorch\_flame |
| Poopcloud | poopcloud | common/fx/poopcloud |
| [Rain](/wiki/Rain "Rain") | rain | common/fx/rain |
| Raindrop | raindrop | common/fx/raindrop |
| Shatter | shatter | common/fx/shatter |
| [Snow](/wiki/Snow "Snow") | snow | common/fx/snow |
| Splash Spiderweb | splash\_spiderweb | common/fx/splash\_spiderweb |
| Collapse Big | collapse\_big | fx/collapse\_big |
| Collapse Small | collapse\_small | fx/collapse\_small |
| [Torchfire](/wiki/Torchfire "Torchfire") | torchfire | common/fx/torchfire |
| Warningshadow | warningshadow | common/fx/warningshadow |

## Interface[]

| **Name** | **Spawn Code** | **Prefab location** |
| --- | --- | --- |
| Frontend | frontend | UI/interface/frontend |
| Hud | hud | UI/interface/hud |
| Minimap | minimap | common/interface/hud/minimap |

## Unlisted Content[]

This section is meant as a temporary home for newly added content to be added quickly before it is categorized as well as to list items not yet on the list for other users to find and add.

| **Name** | **Spawn Code** | **Prefab location** |
| --- | --- | --- |
| Moonrock |  |  |
| Moonstone |  |  |
|  |  |  |
|  |  |  |

## Shipwrecked icon Shipwrecked[]

| Name | Spawn Code | Prefab Location |
| --- | --- | --- |
| Antivenom | antivenom |  |
| Lifejacket | armor\_lifejacket |  |
| Snakeskin Jacket | armor\_snakeskin |  |
| Windbreaker | armor\_windbreaker |  |
| Cactus Armor | armorcactus |  |
| Limestone Suit | armorlimestone |  |
| Obsidian Armor | armorobsidian |  |
| Seashell Suit | armorseashell |  |
| Armoured Boat | armouredboat |  |
| Bottlenose Ballphin | ballphin |  |
| Bamboo | bamboo |  |
| Bamboo Tree | bambootree |  |
| Banana Pop | bananapop |  |
| Electric Isosceles | bermudatriangle |  |
| Bioluminescence | bioluminescence |  |
| Bisque | bisque |  |
| Poison Dart | blowdart\_poison |  |
| Boat Lantern | boat\_lantern |  |
| Boat Torch | boat\_torch |  |
| Boat Cannon | boatcannon |  |
| Boat Repairkit | boatrepairkit |  |
| Bottle Lantern | bottlelantern |  |
| Brain Of Thought | brainjellyhat |  |
| Net | bugnet |  |
| Buoy | buoy |  |
| Buried Treasure | buriedtreasure |  |
| Viney Bush | bush\_vine |  |
| Capain Hat | captainhat |  |
| Cargo Boat | cargoboat |  |
| Ceviche | ceviche |  |
| Chiminea | chiminea |  |
| Cloth Sail | clothsail |  |
| Coconade | coconade |  |
| Coconut | coconut |  |
| Cooked Coconut | coconut\_cooked |  |
| Coffee | coffee |  |
| Coffee Beans | coffeebeans |  |
| Cooked Coffee Beans | coffeebeans\_cooked |  |
| Coffee Plant | coffeebush |  |
| Coral | coral |  |
| Brainy Matter | coral\_brain |  |
| Brainy Sprout | coral\_brain\_rock |  |
| Coral Reef | coralreef |  |
| Crabbit | crab |  |
| Crabbit Den | crabhole |  |
| Crate | crate |  |
| Cutlass Supreme | cutlass |  |
| Lichen | cutlichen |  |
| Dead Swordfish | dead\_swordfish |  |
| Dumbrella | double\_umbrellahat |  |
| Doydoy | doydoy |  |
| Baby Doydoy | doydoybaby |  |
| Doydoy Egg | doydoyegg |  |
| Cooked Doydoy Egg | doydoyegg\_cooked |  |
| Doydoy Feather | doydoyfeather |  |
| Doydoy Nest | doydoynest |  |
| Dragoon | dragoon |  |
| Dragoon Den | dragoonden |  |
| Dragoon Egg | dragoonegg |  |
| Dragoon Heart | dragoonheart |  |
| Dubloon | dubloon |  |
| Bamboo Root | dug\_bambootree |  |
| Viney Bush Root | dug\_bush\_vine |  |
| Coffeebush Root | dug\_coffeebush |  |
| Eel | eel |  |
| Cooked Eel | eel\_cooked |  |
| Feather Lite Sail | feathersail |  |
| Dogfish | fish\_med |  |
| Fish Steak | fish\_med\_cooked |  |
| Raw Fish | fish\_raw |  |
| Fish Morsel | fish\_raw\_small |  |
| Cooked Fish Morsel | fish\_raw\_small\_cooked |  |
| Fishing Rod | fishingrod |  |
| Fishsticks | fishsticks |  |
| Fishtacos | fishtacos |  |
| Krissure | flamegeyser |  |
| Flup | flup |  |
| Luxury Machete | goldenmachete |  |
| Hail | hail |  |
| Harpoon | harpoon |  |
| Ice Maker 3000 | icemaker |  |
| Iron Wind | ironwind |  |
| Jellyfish | jellyfish |  |
| Dead Jellyfish | jellyfish\_dead |  |
| Dried Jellyfish | jellyjerky |  |
| Jelly-O Pop | jellyopop |  |
| Jungle Tree | jungletree |  |
| Jungle Tree Seed | jungletreeseed |  |
| Floaty Boaty Knight | knightboat |  |
| Quacken | kraken |  |
| Chest Of The Depths | krakenchest |  |
| Limpets | limpets |  |
| Cooked Limpets | limpets\_cooked |  |
| Wobster | lobster |  |
| Dead Wobster | lobster\_dead |  |
| Lobster Bisque | lobsterbisque |  |
| Lobster Dinner | lobsterdinner |  |
| Wobster Den | lobsterhole |  |
| Log Raft | lograft |  |
| Steamer Trunk | luggagechest |  |
| Machete | machete |  |
| Magic Seal | magic\_seal |  |
| Magma Pile | magmarock |  |
| Magma Pile (Gold) | magmarock\_gold |  |
| Mangrove | mangrovetree |  |
| Fishermerm | mermfisher |  |
| Fishermerm's Hut | mermhouse\_fisher |  |
| Message in a Bottle | messagebottle |  |
| Empty Bottle | messagebottleempty |  |
| Mussel | mussel |  |
| Cooked Mussel | mussel\_cooked |  |
| Mussels | mussel\_farm |  |
| Mussel Stick | mussel\_stick |  |
| Obsidian | obsidian |  |
| Obsidian Workbench | obsidian\_workbench |  |
| Obsidian Axe | obsidianaxe |  |
| Obsidian Coconade | obsidiancoconade |  |
| Obsidian Fire Pit | obsidianfirepit |  |
| Obsidian Machete | obsidianmachete |  |
| Octopus Chest | octopuschest |  |
| Yaarctopus | octopusking |  |
| Water Beefalo | ox |  |
| Dripple Pipes | ox\_flute |  |
| Horn | ox\_horn |  |
| Horned Helmet | oxhat |  |
| Packim Baggims | packim |  |
| Fishbone | packim\_fishbone |  |
| Palm Leaf | palmleaf |  |
| Palm Leaf Hut | palmleaf\_hut |  |
| Tropical Parasol | palmleaf\_umbrella |  |
| Palm Tree | palmtree |  |
| Parrot | parrot |  |
| Pirate Parrot | parrot\_pirate |  |
| Pirate Ghost | pirateghost |  |
| Pirate Hat | piratehat |  |
| Piratihatitator | piratihatitator |  |
| Poisonous Hole | poisonhole |  |
| Seaworthy | portal\_shipwrecked |  |
| Prime Ape | primeape |  |
| Raft | raft |  |
| Row Boat | rowboat |  |
| Sand | sand |  |
| Sand Castle | sand\_castle |  |
| Sandbag | sandbag |  |
| Sandy Pile | sandhill |  |
| Seafood Gumbo | seafoodgumbo |  |
| Seagull | seagull |  |
| Sea Sack | seasack |  |
| Seashell | seashell |  |
| Sea Trap | seatrap |  |
| Seaweed | seaweed |  |
| Roasted Seaweed | seaweed\_cooked |  |
| Dried Seaweed | seaweed\_dried |  |
| Seaweed (Plant) | seaweed\_planted |  |
| Shark Fin | shark\_fin |  |
| Shark Gills | shark\_gills |  |
| Shark Tooth Crown | shark\_teethhat |  |
| Shark Fin Soup | sharkfinsoup |  |
| Sharkitten | sharkitten |  |
| Sea Hound | sharx |  |
| Slot Machine | slotmachine |  |
| Snake | snake |  |
| Poison Snake | snake\_poison |  |
| Snake Oil | snakeoil |  |
| Snakeskin | snakeskin |  |
| Snakeskin Hat | snakeskinhat |  |
| Snakeskin Sail | snakeskinsail |  |
| Obsidian Spear | spear\_obsidian |  |
| Poison Spear | spear\_poison |  |
| Speargun | speargun |  |
| Poison Speargun | speargun\_poison |  |
| Stink Ray | stungray |  |
| Super Spyglass | supertelescope |  |
| Surf 'n' Turf | surfnturf |  |
| Sweet Potato | sweet\_potato |  |
| Cooked Sweet Potato | sweet\_potato\_cooked |  |
| Swimming Horror | swimminghorror |  |
| Swordfish | swordfish |  |
| Spyglass | telescope |  |
| Eye of the Tiger Shark | tigereye |  |
| Tiger Shark | tigershark |  |
| Toucan | toucan |  |
| Trawl Net | trawlnet |  |
| Tropical Fish | tropical\_fish |  |
| Tropical Fan | tropicalfan |  |
| "Ballphin Free" Tuna | tunacan |  |
| Turbine Blades | turbine\_blades |  |
| Ashy Turf | turf\_ash |  |
| Beach Turf | turf\_beach |  |
| Jungle Turf | turf\_jungle |  |
| Magma Turf | turf\_magmafield |  |
| Snakeskin Floor | turf\_snakeskinfloor |  |
| Volcano Turf | turf\_volcano |  |
| Sealnado | twister |  |
| Seal | twister\_seal |  |
| Venom Gland | venomgland |  |
| Vine | vine |  |
| Volcano | volcano |  |
| volcanostaff | volcanostaff |  |
| Limestone Wall | wall\_limestone |  |
| Watery Grave | waterygrave |  |
| Blue Whale | whale\_blue |  |
| Blue Whale Carcass | whale\_carcass\_blue |  |
| White Whale Cracass | whale\_carcass\_white |  |
| White Whale | whale\_white |  |
| Wildbore | wildbore |  |
| Wildbore House | wildborehouse |  |
| The "Sea Legs" | woodlegsboat |  |
| Wreck | wreck |  |