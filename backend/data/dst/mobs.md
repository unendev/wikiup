---
game_version: dst
category: Uncategorized
source_url: https://dontstarve.fandom.com/wiki/Mobs
---

**“**Simple creatures. They exist only to sleep and feed.**”**

–[Maxwell](/wiki/Maxwell "Maxwell")

**Mobs**, or "Mobiles", are game entities which move around, technically creatures including [Monsters](/wiki/Monsters "Monsters") and [Animals](/wiki/Animals "Animals"). The term has been a part of video game jargon since 1978; it's not unique to *[Don't Starve](/wiki/Don%27t_Starve "Don't Starve")*.

Every Mob has its own unique behaviors, movements, attacks, drops, and uses. As such, learning the characteristics of each and every Mob becomes important to survival, done either through direct study and interaction or through external research.

Many mobs [can be put to sleep](/wiki/Sleeping#Sleeping_mobs "Sleeping") by the pan flute and other features in the game.

## Classification[]

* **Hostile** Mobs are always aggressive. These mobs attack anything nearby which they deem threatening.
* **Neutral** Mobs are non-aggressive until provoked. Their threat is very often getting attacked, but it might be just about anything except proximity.
* **Passive** Mobs are always non-aggressive, even when attacked. These mobs will typically avoid the player, but exceptions exist.

These relationships are classified with regards to each others. A Mob which is hostile/neutral/passive towards all other Mobs is a true hostile/neutral/passive Mob.

For monstrous player characters ([Webber](/wiki/Webber "Webber") and [Wortox](/wiki/Wortox "Wortox")) some mobs are categorized differently. [Bunnymen](/wiki/Bunnymen "Bunnymen"), [Pigs](/wiki/Pig "Pig") and [Catcoons](/wiki/Catcoon "Catcoon") will be hostile to them since these mobs attack monsters on sight, though [Spiders](/wiki/Spiders "Spiders") will be neutral to Webber. Pigs will also be hostile to [Wurt](/wiki/Wurt "Wurt") due to her being a [Merm](/wiki/Merm "Merm"), but Bunnymen and Catcoons will be neutral.

## Characteristics[]

### Health[]

Health is measured in "Hit Points" (HP), an arbitrary unit invented to coordinate with damage.
Each hit taken from the player decreases its health by:

```
attack damage * character damage multiplier

```

The number of hits required to kill a Mob is:

```
Hits = mob health / attack damage

```

### Damage (DMG)[]

Damage is a subtraction of health from a Mob, measured in HP.
A hit against any Mob deals:

```
attack damage = base attack damage / mob's armor

```

### Attack Period (AP)[]

The time from the start of one attack to the start of the next one, here measured in *seconds*.
A Mob's damage per second (or DPS) is calculated by either of the following

```
DPS = total damage done / time taken

```

```
DPS = attack damage / attack period

```

### Attack Range (AR)[]

 https://static.wikia.nocookie.net/dont-starve-game/images/3/31/Deerclops Attack Range.png/revision/latest?cb=20171218115644 https://static.wikia.nocookie.net/dont-starve-game/images/3/31/Deerclops Attack Range.png/revision/latest?cb=20171218115644 

The [Deerclops](/wiki/Deerclops "Deerclops")'s AoE attack can hit everything within 6 units of its target. Note that the Walls are placed diagonally.

 

The distance an attack can hit a target from, here measured in "units" which are equal to the horizontal thickness of a [Wall](/wiki/Wall "Wall"), with the diagonal thickness of a Wall being √2 units.

### Sanity Aura (SA)[]

The amount of sanity a player gains or loses per minute when standing near the Mob.

### Walk Speed (WS) and Run Speed (RS)[]

Walk Speed is a mob's speed while idly wandering the world, and Run Speed is a mob's speed while either engaged in combat or fleeing. They are measured in *units per second*. The default Run Speed of most characters is 6.

The speed is affected by the mob's scaled size (determined by looking for inst.Transform:SetScale(X, X, X) in the mob's lua file), so the in-game speed is calculated as:

```
WS/RS = speed * scale value

```

## Mobs[]

| Image | Name | D | T | Health (HP) | Dmg (HP) | AP (sec) | SA (Sanity/min) | WS | RS | Loot |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| Abigail | [Abigail](/wiki/Abigail "Abigail") |  | N | 600 | 10 (day) 20 (dusk) 40 (night) | 1.5 |  | 5 | 5 | Abigail's Flower |
| Abigail | [Abigail (DST)](/wiki/Abigail#Don't_Starve_Together "Abigail") | Don't Starve Together | N | 150 (Lv. 1) 300 (Lv. 2) 600 (Lv. 1) | 15 (day) 25 (dusk) 40 (night) | 1 |  | 5 | 5 |  |
| Ancient Guardian | [Ancient Guardian](/wiki/Ancient_Guardian "Ancient Guardian") |  | H | 2500 10000Don't Starve Together | 100 | 2 |  | 5 | 17 | Meat×8, Rhino Horn, Ornate Chest, (Sketch for Chess PiecesDon't Starve Together) |
| Batilisk | [Batilisk](/wiki/Batilisk "Batilisk") |  | H | 50 | 20 | 1 |  | 6 | 6 | Batilisk Wing15% (25%Don't Starve Together), Monster Meat10% and Guano15% |
| Bee | [Bee](/wiki/Bee "Bee") |  | N | 100 | 10 | 2 |  | 5 | 5 | Honey16.7% or Stinger83.3% |
| Killer Bee | [Killer Bee](/wiki/Killer_Bee "Killer Bee") |  | H | 100 | 10 | 2 |  | 5 | 5 | Honey16.7% or Stinger83.3% |
| Butterfly | [Butterfly](/wiki/Butterfly "Butterfly") |  | P | 1 |  |  |  | 5 | 5 | Butterfly Wings98% or Butter2% |
| Beefalo | [Beefalo](/wiki/Beefalo "Beefalo") |  | N | 500 1000Don't Starve Together | 34 | 4 |  | 1.5 | 7 | Meat×4, Beefalo Wool×3, Beefalo Horn33% |
| Teen Beefalo | [Teen Beefalo](/wiki/Teen_Beefalo "Teen Beefalo") |  | P | 300 |  |  |  | 2 | 9 | Meat×3, Beefalo Wool×2 |
| Toddler Beefalo | [Toddler Beefalo](/wiki/Toddler_Beefalo "Toddler Beefalo") |  | P | 300 |  |  |  | 2 | 9 | Morsel×4, Beefalo Wool×2 |
| Baby Beefalo | [Baby Beefalo](/wiki/Baby_Beefalo "Baby Beefalo") |  | P | 300 |  |  |  | 2 | 9 | Morsel×3, Beefalo Wool |
| Birds Birds Birds | [Birds](/wiki/Birds "Birds") |  | P | 25 |  |  |  |  |  | Morsel50% or Jet Feather Crimson Feather Azure Feather50% |
| Bunnyman | [Bunnyman](/wiki/Bunnyman "Bunnyman") |  | N | 200 | 40 | 2 | +25 (if allied) | 3 | 6 | Carrot×2, Bunny Puff25% or Meat75% Carrot(37.5%), Bunny Puff(25%), Meat(37.5%) Don't Starve Together |
| Beardlord | [Beardlord](/wiki/Beardlord "Beardlord") |  | N | 200 | 60 | 1 | -40 | 3 | 6 | Monster Meat, Beard Hair×2 |
| Charlie (Night Monster) | [Charlie (Night Monster)](/wiki/Charlie_(Night_Monster) "Charlie (Night Monster)") |  | H | N/A | 100 | 5–11 | -20 (on hit) |  |  | Cannot be killed |
| Chester | [Chester](/wiki/Chester "Chester") |  | P | 450 |  |  |  | 3 | 7 | Items inside of Chester. |
| Clockwork Bishop | [Clockwork Bishop](/wiki/Clockwork_Bishop "Clockwork Bishop") |  | H | 300 900Don't Starve Together | 40 | 4 |  | 5 | 5 | Gears×2, Purple Gem |
| Clockwork Bishop | [Damaged Bishop](/wiki/Clockwork_Bishop#Damaged_Bishop "Clockwork Bishop") |  | H | 300 900Don't Starve Together | 40 | 4 |  | 5 | 5 | Purple Gem, Nightmare Fuel60%, Thulecite Fragments50% |
| Clockwork Knight | [Clockwork Knight](/wiki/Clockwork_Knight "Clockwork Knight") |  | H | 300 900Don't Starve Together | 40 | 2 |  | 5 | 5 | Gears×2 |
| Clockwork Knight | [Damaged Knight](/wiki/Clockwork_Knight#Damaged_Knight "Clockwork Knight") |  | H | 300 900Don't Starve Together | 40 | 2 |  | 5 | 5 | Gears, Nightmare Fuel60%, Thulecite Fragments50% |
| Clockwork Rook | [Clockwork Rook](/wiki/Clockwork_Rook "Clockwork Rook") |  | H | 300 900Don't Starve Together | 45 | 2 |  | 5 | 16 | Gears×2 |
| Clockwork Rook | [Damaged Rook](/wiki/Clockwork_Rook#Damaged_Rook "Clockwork Rook") |  | H | 300 900Don't Starve Together | 45 | 2 |  | 5 | 16 | Gears, Nightmare Fuel60%, Thulecite Fragments50% |
| Deerclops | [Deerclops](/wiki/Deerclops "Deerclops") |  | H | 2000 4000Don't Starve Together | 150(75) | 3 | -400 | 3 | 3 | Meat×8, Deerclops Eyeball |
| Depths Worm | [Depths Worm](/wiki/Depths_Worm "Depths Worm") |  | H | 900 | 75 | 4 | -25 | 4 | 4 | Monster Meat×4,Glow Berry |
| Ewecus | [Ewecus](/wiki/Ewecus "Ewecus") |  | H | 500 800Don't Starve Together | 60 |  |  |  |  | Meat×4, Steel Wool×2-3, Phlegm×1-2 |
| Frog | [Frog](/wiki/Frog "Frog") |  | H | 100 | 10 | 1.0 |  | 4 | 8 | Frog Legs |
| Fireflies | [Fireflies](/wiki/Fireflies "Fireflies") |  | P |  |  |  |  | 0 | 0 | Fireflies (when captured with Bug Net) |
| Ghost | [Ghost](/wiki/Ghost "Ghost") |  | H | 200 | 15 | 1.2 | -40 | 2 | 2 |  |
| Gobbler | [Gobbler](/wiki/Gobbler "Gobbler") |  | P | 50 |  |  |  | 3 | 8 | Drumstick×2 |
| Hound | [Hound](/wiki/Hound "Hound") |  | H | 150 | 20 | 2 | -40 | 10 | 10 | Monster Meat, Hound's Tooth12.5% |
| Red Hound | [Red Hound](/wiki/Red_Hound "Red Hound") |  | H | 100 | 30 | 2 | -40 | 10 | 10 | Monster Meat, Hound's Tooth, Ashes, Red Gem20% |
| Blue Hound | [Blue Hound](/wiki/Blue_Hound "Blue Hound") |  | H | 100 | 30 | 2 | -40 | 10 | 10 | Monster Meat,Hound's Tooth×2, Blue Gem20% |
| Houndius Shootius | [Houndius Shootius](/wiki/Houndius_Shootius "Houndius Shootius") |  | N | 1000 | 65 | 3 | -2 | 0 | 0 |  |
| Koalefant | [Koalefant](/wiki/Koalefant "Koalefant") |  | N | 500 1000Don't Starve Together | 50 | 4 |  | 1.5 | 7 | Meat×8, Koalefant Trunk |
| Winter Koalefant | [Winter Koalefant](/wiki/Winter_Koalefant "Winter Koalefant") |  | N | 500 1000Don't Starve Together | 50 | 4 |  | 1.5 | 7 | Meat×8, Winter Koalefant Trunk |
| Krampus | [Krampus](/wiki/Krampus "Krampus") |  | N | 200 300Don't Starve Together | 50 | 1.2 |  | 7 | 7 | Monster Meat, Charcoal×2 and Krampus Sack1%, Any stolen items in its inventory |
| MacTusk | [MacTusk](/wiki/MacTusk "MacTusk") |  | H | 150 300Don't Starve Together | 33 | 3 |  | 3 | 6 | Meat, Blow Dart, Walrus Tusk50% and Tam o' Shanter25% |
| Wee MacTusk | [Wee MacTusk](/wiki/Wee_MacTusk "Wee MacTusk") |  | H | 100 | 22 | 5.1 |  | 3 | 5 | Meat |
| Mandrake | [Mandrake](/wiki/Mandrake "Mandrake") |  | P | 20 |  |  |  | 6 | 6 | Mandrake |
| Merm | [Merm](/wiki/Merm "Merm") |  | H | 250 500Don't Starve Together | 30 | 3 |  | 3 | 8 | Fish, Frog Legs |
| Meat Bulb (Lureplant) | [Lureplant](/wiki/Meat_Bulb_(Lureplant) "Meat Bulb (Lureplant)") |  | P | 300 |  |  |  | 0 | 0 | Fleshy Bulb, Leafy Meat, and any undigested items |
| Lureplant | [Eyeplant](/wiki/Lureplant#Eyeplant "Lureplant") |  | H | 30 | 20 | 1 |  | 0 | 0 |  |
| Mosquito | [Mosquito](/wiki/Mosquito "Mosquito") |  | H | 100 | 3 | 2 |  | 8 | 12 | Mosquito Sack50% |
| https://static.wikia.nocookie.net/dont-starve-game/images/9/94/Pengull.png/revision/latest?cb=20220331010559 | [Pengull](/wiki/Pengull "Pengull") |  | N | 150 | 33 | 3 |  | 0.75 | 6 | Jet Feather20%, Drumstick10% and Morsel10%, Egg (If hidden) |
| Pig | [Pig](/wiki/Pig "Pig") |  | N | 250 | 33 | 3 | +25 (if allied) | 3 | 5 | Meat75% or Pig Skin25% |
| Guardian Pig | [Guardian Pig](/wiki/Guardian_Pig "Guardian Pig") |  | H | 300 600Don't Starve Together | 33 | 1.5 |  | 3 | 5 | Meat75% or Pig Skin25% |
| Werepig | [Werepig](/wiki/Werepig "Werepig") |  | H | 350 525Don't Starve Together | 40 | 2 | -100 | 3 | 7 | Meat×2, Pig Skin |
| Pig King | [Pig King](/wiki/Pig_King "Pig King") |  | P |  |  |  |  | 0 | 0 |  |
| Rabbit | [Rabbit](/wiki/Rabbit "Rabbit") |  | P | 25 |  |  |  | 5 | 5 | Morsel |
| Beardling | [Beardling](/wiki/Beardling "Beardling") |  | P | 25 |  |  | -40 | 5 | 5 | Monster Meat40%, Nightmare Fuel40% or Beard Hair20% |
| Rock Lobster | [Rock Lobster](/wiki/Rock_Lobster "Rock Lobster") |  | N | 1250–1800 2250-3600Don't Starve Together | 62.5–90 | 1 |  | 2 | 2 | Rocks×2, Flint×2, Meat |
| Crawling Horror | [Crawling Horror](/wiki/Shadow_Creature#Crawling_Horror "Shadow Creature") |  | H | 300 | 20 | 2.5 | -100 | 3 | 3 | Nightmare Fuel and Nightmare Fuel 50%, +15 Sanity |
| Terrorbeak | [Terrorbeak](/wiki/Shadow_Creature#Terrorbeak "Shadow Creature") |  | H | 400 | 50 | 1.5 | -100 | 7 | 7 | Nightmare Fuel and Nightmare Fuel50%, +33 Sanity |
| Slurper | [Slurper](/wiki/Slurper "Slurper") |  | H | 200 | 30 | 5 | -25 | 9 | 9 | Light Bulb×2, Slurper Pelt50% |
| Slurtle | [Slurtle](/wiki/Slurtle "Slurtle") |  | N | 600 1200Don't Starve Together | 25 | 4 |  | 3 | 3 | Slurtle Slime×2, Broken Shell90% or Shelmet10% |
| Snurtle | [Snurtle](/wiki/Snurtle "Snurtle") |  | P | 200 |  |  |  | 4 | 4 | Slurtle Slime×2, Broken Shell 25% or Snurtle Shell Armor 75% |
| Splumonkey | [Splumonkey](/wiki/Splumonkey "Splumonkey") |  | N | 125 | 20 | 2 |  | 7 | 7 | Morsel, Cave Banana, Nightmare Fuel50% |
| Splumonkey | [Shadow Splumonkey](/wiki/Splumonkey#Shadow_Splumonkey "Splumonkey") |  | H | 125 | 20 | 2 |  | 7 | 7 | Cave Banana, Beard Hair,Nightmare Fuel50%, Any stolen items in its Inventory |
| Spider | [Spider](/wiki/Spider "Spider") |  | H | 100 | 20 | 3 | -25 | 3 | 5 | Monster Meat50% or Silk25% or Spider Gland25% |
| Spider Warrior | [Spider Warrior](/wiki/Spider_Warrior "Spider Warrior") |  | H | 200 400Don't Starve Together | 20 | 4-6 | -40 | 4 | 5 | Monster Meat50% or Silk25% or Spider Gland25% |
| Spider Queen | [Spider Queen](/wiki/Spider_Queen "Spider Queen") |  | H | 1250 2500Don't Starve Together | 80 | 3 | -400 | 1.75 | 1.75 | Monster Meat×4, Silk×4, Spider Eggs, Spiderhat |
| Cave Spider | [Cave Spider](/wiki/Cave_Spider "Cave Spider") |  | H | 150 225Don't Starve Together | 20 | 3 | -15 | 3 | 5 | Monster Meat50% or Silk25% or Spider Gland25% |
| Spitter | [Spitter](/wiki/Spitter "Spitter") |  | H | 175 350Don't Starve Together | 20 | 6 | -15 | 4 | 5 | Monster Meat50% or Silk25% or Spider Gland25% |
| Dangling Depth Dweller | [Dangling Depth Dweller](/wiki/Dangling_Depth_Dweller "Dangling Depth Dweller") |  | H | 200 400Don't Starve Together | 20 | 2 | -25 | 3 | 5 | Monster Meat50% or Silk25% or Spider Gland25% |
| Tallbird | [Tallbird](/wiki/Tallbird "Tallbird") |  | H | 400 800Don't Starve Together | 50 | 2 |  | 7 | 7 | Meat×2 |
| Smallish Tallbird | [TeenBird](/wiki/Smallish_Tallbird "Smallish Tallbird") |  | N | 300 600Don't Starve Together | 37.5 | 2 |  | 6 | 6 | Meat |
| Smallbird | [Smallbird](/wiki/Smallbird "Smallbird") |  | P | 50 |  |  |  | 6 | 6 | Morsel |
| Tentacle | [Tentacle](/wiki/Tentacle "Tentacle") |  | H | 500 750Don't Starve Together | 34 | 2 | -40 | 0 | 0 | Monster Meat×2, Tentacle Spike 50% and Tentacle Spots20% |
| Big Tentacle | [Big Tentacle](/wiki/Big_Tentacle "Big Tentacle") |  | P | 500 |  |  |  | 0 | 0 | Light Bulb80%, Tentacle Spike50%, Tentacle Spots40%, Marsh Turf25%, Slurtle Slime10%, Skeleton10%, Rocks10% |
| Tentacle | [Baby Tentacle](/wiki/Tentacle#Baby_Tentacle "Tentacle") |  | H | 20 | 5 | 3 |  | 0 | 0 |  |
| Thulecite Club | [Shadow Tentacle](/wiki/Thulecite_Club "Thulecite Club") |  | H | 500 | 34 | 2 | -40 | 0 | 0 |  |
| Treeguard | [Treeguard (Tall)](/wiki/Treeguard "Treeguard") |  | N | 2500 3750Don't Starve Together | 186(62) | 3 | -100 | 1.875 | 1.875 | Monster Meat, Living Log×6 |
| Treeguard | [Treeguard (Normal)](/wiki/Treeguard "Treeguard") |  | N | 2000 3000Don't Starve Together | 150(50) | 3 | -100 | 1.5 | 1.5 | Monster Meat, Living Log×6 |
| Treeguard | [Treeguard (Short)](/wiki/Treeguard "Treeguard") |  | N | 1400 2100Don't Starve Together | 105(35) | 3 | -100 | 1.05 | 1.05 | Monster Meat, Living Log×6 |
| Bearger | [Bearger](/wiki/Bearger "Bearger") | Reign of Giants | H | 3000 6000Don't Starve Together | 200(100) | 3 | -400 | 3 (6) | 10 | Meat×8, Thick Fur |
| Buzzard | [Buzzard](/wiki/Buzzard "Buzzard") | Reign of Giants | N | 125 250Don't Starve Together | 15 | 2 |  | 4 | 8 | Drumstick, Morsel33% , Feather33% |
| Catcoon | [Catcoon](/wiki/Catcoon "Catcoon") | Reign of Giants | N | 150 | 25 | 2 |  | 3 | 3 | Meat, Cat Tail33% |
| Dragonfly | [Dragonfly](/wiki/Dragonfly "Dragonfly") | Reign of Giants | H | 2750 | 150(75)--Swipe 75(37.5) x3--Ring of Fire | 2.5 |  | 4 | 4 | Meat×8, Scales |
| Dragonfly | [Dragonfly (DST)](/wiki/Dragonfly#Don't_Starve_Together "Dragonfly") | Don't Starve Together | H | 27500 | 150(75)--Swipe 75(37.5) x3--Ring of Fire double when enraged | 4 3 (enraged) |  | 6.5 9.1 (enraged) | 6.5 9.1 (enraged) | Scales, Blueprint for Scaled Furnace, Lavae Eggx0-1, Meatx6, Gold Nuggetx4-8, Red Gemx2, Blue Gemx2, Purple Gemx1-2, Orange Gemx1-2, Yellow Gemx1-2, Green Gemx1-2, Sketch for Chess Pieces |
| Glommer | [Glommer](/wiki/Glommer "Glommer") | Reign of Giants | P | 100 |  |  | +6.25 (if allied) | 6 | 6 | Monster Meat×3, Glommer's Wings, Glommer's Goop×2 |
| Moleworm | [Moleworm](/wiki/Moleworm "Moleworm") | Reign of Giants | P | 25 |  |  |  | 2.75 | 2.75 | Morsel, Any stolen items |
| Moose/Goose | [Moose/Goose](/wiki/Moose/Goose "Moose/Goose") | Reign of Giants | H | 3000 6000Don't Starve Together | 150(75) | 3 |  | 8 | 12 | Meat×6 Drumstick×2, Down Feather×3-5 |
| Mosling | [Mosling](/wiki/Mosling "Mosling") | Reign of Giants | N | 350 525Don't Starve Together | 50 | 3 |  | 5 | 5 | Meat, Drumstick, Down Feather×2-3 133% |
| Poison Birchnut Tree | [Poison Birchnut Tree](/wiki/Birchnut_Tree "Birchnut Tree") | Reign of Giants | H | 50 | 30 | 1.5 |  | 0 | 0 | Birchnut×3, Living Log×2 (+1 stump), Nightmare Fuel |
| Birchnutter | [Birchnutter](/wiki/Birchnutter "Birchnutter") | Reign of Giants | H | 50 | 5 | 2 |  | 3.5 | 3.5 | Birchnut40%, Twigs60% |
| Varg | [Varg](/wiki/Varg "Varg") | Reign of Giants | H | 600 1800Don't Starve Together | 50 | 3 |  | 5.5 | 5.5 | Monster Meat×4-6, Hound's Tooth1-3 |
| Volt Goat | [Volt Goat](/wiki/Volt_Goat "Volt Goat") | Reign of Giants | N | 350 700Don't Starve Together | 25 | 2 |  | 4 | 8 | Meat×3, Volt Goat Horn25% |
| Volt Goat | [Charged Volt Goat](/wiki/Volt_Goat "Volt Goat") | Reign of Giants | H | 350 700Don't Starve Together | 37.5 | 2 |  | 4 | 8 | Meat×3, Electric Milk, Volt Goat Horn25% |
| Blue Whale | [Blue Whale](/wiki/Blue_Whale "Blue Whale") | Shipwrecked | N | 650 | 50 | 3.5 |  | 2 | 4 | Blue Whale Carcass |
| Bottlenose Ballphin | [Bottlenose Ballphin](/wiki/Bottlenose_Ballphin "Bottlenose Ballphin") | Shipwrecked | N | 200 | 25 | 6 |  | 5 | 8 | Fish Morsel×2, Empty Bottle50% |
| Cormorant | [Cormorant](/wiki/Cormorant "Cormorant") | Shipwrecked | P | 25 |  |  |  |  |  | Morsel50%, Jet Feather50% |
| Crabbit | [Crabbit](/wiki/Crabbit "Crabbit") | Shipwrecked | P | 50 |  |  |  | 1.5 | 5 | Fish Morsel |
| Crocodog | [Crocodog](/wiki/Crocodog "Crocodog") | Shipwrecked | H | 150 | 20 | 2 | -40 |  | 10 | Monster Meat, Hound's Tooth×2 12.5% |
| Crocodog | [Blue Crocodog](/wiki/Blue_Crocodog "Blue Crocodog") | Shipwrecked | H | 100 | 30 | 2 | -40 |  | 10 | Monster Meat, Hound's Tooth×2, Seaweed20% |
| Crocodog | [Yellow Crocodog](/wiki/Yellow_Crocodog "Yellow Crocodog") | Shipwrecked | H | 100 | 30 | 2 | -40 |  | 10 | Monster Meat, Hound's Tooth, Venom Gland20% |
| Dogfish | [Dogfish](/wiki/Dogfish "Dogfish") | Shipwrecked | P | 100 |  |  |  | 5 | 8 | Dead Dogfish |
| Doydoy | [Doydoy](/wiki/Doydoy "Doydoy") | Shipwrecked | P | 100 |  |  |  | 2 |  | Meat, Drumstick×2, Doydoy Feather×2 |
| Doydoy | [Teen Doydoy](/wiki/Doydoy "Doydoy") | Shipwrecked | P | 75 |  |  |  | 1.5 |  | Drumstick, Doydoy Feather×2 |
| Doydoy | [Baby Doydoy](/wiki/Doydoy "Doydoy") | Shipwrecked | P | 25 |  |  |  | 5 |  | Morsel, Doydoy Feather |
| Dragoon | [Dragoon](/wiki/Dragoon "Dragoon") | Shipwrecked | H | 300 | 25 | 1 |  | 3 | 15 | Monster Meat, Dragoon Heart10% |
| Fishermerm | [Fishermerm](/wiki/Fishermerm "Fishermerm") | Shipwrecked | P | 150 |  |  |  |  |  | Tropical Fish |
| Floaty Boaty Knight | [Floaty Boaty Knight](/wiki/Floaty_Boaty_Knight "Floaty Boaty Knight") | Shipwrecked | H | 500 | 50 | 6 |  | 3 | 6 | Gears×1-3 |
| Flup | [Flup](/wiki/Flup "Flup") | Shipwrecked | H | 100 | 25 |  |  |  |  | Monster Meat, Eyeshot25% |
| Jellyfish | [Jellyfish](/wiki/Jellyfish "Jellyfish") | Shipwrecked | P | 50 | 5 |  |  | 2 |  | Dead Jellyfish |
| Packim Baggims | [Packim Baggims](/wiki/Packim_Baggims "Packim Baggims") | Shipwrecked | P | 450 |  |  |  | 10 |  |  |
| Palm Treeguard | [Palm Treeguard](/wiki/Palm_Treeguard "Palm Treeguard") | Shipwrecked | N | 750 | 50 | 3 | -100 | 1.5 |  | Coconut, Living Log |
| Parrot | [Parrot](/wiki/Parrot "Parrot") | Shipwrecked | P | 25 |  |  |  |  |  | Morsel50%, Crimson Feather50% |
| Parrot Pirate | [Parrot Pirate](/wiki/Parrot_Pirate "Parrot Pirate") | Shipwrecked | P | 25 |  |  | +25 |  |  | Morsel50%, Crimson Feather50% |
| Poison Snake | [Poison Snake](/wiki/Poison_Snake "Poison Snake") | Shipwrecked | H | 100 | 10 | 3 |  | 3 |  | Monster Meat20%, Venom Gland20%, Snakeskin10%, Snake Oil0.2% |
| Poison Mosquito | [Poison Mosquito](/wiki/Poison_Mosquito "Poison Mosquito") | Shipwrecked | H | 100 | 3 | 2 |  | 8 | 12 | Yellow Mosquito Sack50%, Venom Gland |
| Prime Ape | [Prime Ape](/wiki/Prime_Ape "Prime Ape") | Shipwrecked | N | 125 | 20 | 2 |  |  | 7 | Morsel or Banana, Manure, Any stolen items |
| Quacken | [Quacken](/wiki/Quacken "Quacken") | Shipwrecked | H | 1000 | 50 | 15 |  | 0 | 0 | Chest of the Depths, Booty Bag, Iron Key |
| Quacken | [Quacken Tentacle](/wiki/Quacken "Quacken") | Shipwrecked | H | 90 | 50 | 8 |  | 0 | 0 | Tentacle Spike5%, Tentacle Spots10% |
| Rainbow Jellyfish | [Rainbow Jellyfish](/wiki/Rainbow_Jellyfish "Rainbow Jellyfish") | Shipwrecked | P | 50 |  |  |  | 3 |  | Dead Rainbow Jellyfish |
| Sea Hound | [Sea Hound](/wiki/Sea_Hound "Sea Hound") | Shipwrecked | H | 150 | 20 | 1.5 | -40 |  | 10 | Monster Meat×1, Hound's Tooth12.5%, Shark Fin12.5% |
| Seagull | [Seagull](/wiki/Seagull "Seagull") | Shipwrecked | P | 25 |  |  |  |  |  | Morsel50%, Azure Feather50% |
| Seal | [Seal](/wiki/Seal "Seal") | Shipwrecked | P | 10 |  |  |  |  |  | Meat×4, Magic Seal |
| Sealnado | [Sealnado](/wiki/Sealnado "Sealnado") | Shipwrecked | H | 3000 | 75 (250) | 3 |  | 5 | 13 | Turbine Blades |
| Sharkitten | [Sharkitten](/wiki/Sharkitten "Sharkitten") | Shipwrecked | P | 150 |  |  |  |  |  | Raw Fish×2-3, Shark Gills×0-2 |
| Snake | [Snake](/wiki/Snake "Snake") | Shipwrecked | H | 100 | 10 | 3 |  | 3 |  | Monster Meat33%, Snakeskin16%, Snake Oil0.33% |
| Spider Warrior (Venomous) | [Spider Warrior (Venomous)](/wiki/Spider_Warrior_(Venomous) "Spider Warrior (Venomous)") | Shipwrecked | H | 200 | 20 | 4-6 | -40 | 4 | 5 | Meat50%, Silk25%, Venom Gland25% |
| Stink Ray | [Stink Ray](/wiki/Stink_Ray "Stink Ray") | Shipwrecked | H | 50 | 0 | 1 |  | 8 |  | Monster Meat66.7%, Venom Gland33.3% |
| Swordfish | [Swordfish](/wiki/Swordfish "Swordfish") | Shipwrecked | H | 200 | 30 | 2 |  | 5 | 8 | Dead Swordfish |
| Tiger Shark | [Tiger Shark](/wiki/Tiger_Shark "Tiger Shark") | Shipwrecked | N | 2500 | 100 (225) | 3 |  | 8 | 12 | Raw Fish×8, Eye of the Tiger Shark×1-2, Shark Gills×2-4 |
| Toucan | [Toucan](/wiki/Toucan "Toucan") | Shipwrecked | P | 25 |  |  |  |  |  | Morsel50%, Jet Feather50% |
| Water Beefalo | [Water Beefalo](/wiki/Water_Beefalo "Water Beefalo") | Shipwrecked | N | 500 | 34 | 5 |  | 1.5 | 7.5 | Meat×4, Horn33% |
| White Whale | [White Whale](/wiki/White_Whale "White Whale") | Shipwrecked | H | 800 | 75 | 3 |  | 2.5 | 5 | White Whale Carcass |
| Wildbore | [Wildbore](/wiki/Wildbore "Wildbore") | Shipwrecked | N | 250 | 33 | 3 | +25 (if allied) | 3 | 5 | Meat75%, Pig Skin25% |
| Wobster | [Wobster](/wiki/Wobster "Wobster") | Shipwrecked | P | 25 |  |  |  | 1.5 | 4 | Dead Wobster |
| Yaarctopus | [Yaarctopus](/wiki/Yaarctopus "Yaarctopus") | Shipwrecked | P |  |  |  |  | 0 | 0 | Cannot be killed |
| Ancient Herald | [Ancient Herald](/wiki/Ancient_Herald "Ancient Herald") | Hamlet | H | 2000 | 50 | 4 | -6.6 | 2 |  | Dark Tatters×5, Nightmare Fuel×2-3, Blueprint |
| Ancient Spirit | [Ancient Spirit](/wiki/Ancient_Spirit "Ancient Spirit") | Hamlet | H | 200 | 15 | 1.5 | -40 |  | 2 |  |
| BFB | [BFB](/wiki/BFB "BFB") | Hamlet | N |  | 1000 |  |  |  |  | Dung Pile (while flying) |
| Dung Beetle | [Dung Beetle](/wiki/Dung_Beetle "Dung Beetle") | Hamlet | P | 30 |  |  |  | 3.5 | 6 | Monster Meat, Chitin50% |
| Elder Mandrake | [Elder Mandrake](/wiki/Elder_Mandrake "Elder Mandrake") | Hamlet | H | 200 | 40 | 2 |  | 3 | 6 | Living Log×2 |
| Giant Grub | [Giant Grub](/wiki/Giant_Grub "Giant Grub") | Hamlet | H | 600 | 44 | 3 |  | 2 |  | Monster Meat |
| Glowfly | [Glowfly](/wiki/Glowfly "Glowfly") | Hamlet | P | 1 |  |  |  | 6 | 8 | Light Bulb10% |
| Glowfly | [Glowfly Cocoon](/wiki/Glowfly#Life_Cycle "Glowfly") | Hamlet | P | 300 |  |  |  | 0 |  | Light Bulb 10% |
| Rabid Beetle | [Rabid Beetle](/wiki/Rabid_Beetle "Rabid Beetle") | Hamlet | H | 60 | 10 | 2 |  |  | 12 | Light Bulb 10%, Chitin 20% |
| Gnat Swarm | [Gnat Swarm](/wiki/Gnat_Swarm "Gnat Swarm") | Hamlet | H | 1 | 1 | 1 |  |  |  |  |
| Hanging Vine | [Hanging Vine](/wiki/Hanging_Vine "Hanging Vine") | Hamlet | H | 100 | 10 | 1 |  | 4 | 8 | Rope40%, Leafy Meat40% |
| Hippopotamoose | [Hippopotamoose](/wiki/Hippopotamoose "Hippopotamoose") | Hamlet | N | 500 | 50 | 2 |  | 5 | 6 | Meat×4, Hippopotamoose Antler |
| Iron Hulk | [Iron Hulk (Ribs)](/wiki/Iron_Hulk "Iron Hulk") | Hamlet | N |  | 60 | 4 |  | 2/3/4 |  | Iron Ore(Pickaxe) |
| Iron Hulk | [Iron Hulk (Arm)](/wiki/Iron_Hulk "Iron Hulk") | Hamlet | N |  | 34 | 4 |  | 2/3/4 |  | Iron Ore(Pickaxe) |
| Iron Hulk | [Iron Hulk (Leg)](/wiki/Iron_Hulk "Iron Hulk") | Hamlet | N |  | 60 | 4 |  | 2/3/4 |  | Iron Ore(Pickaxe) |
| Iron Hulk | [Iron Hulk (Head)](/wiki/Iron_Hulk "Iron Hulk") | Hamlet | N |  | 68 | 4 |  | 2/3/4 |  | Iron Ore(Pickaxe) |
| Large Iron Hulk | [Large Iron Hulk](/wiki/Large_Iron_Hulk "Large Iron Hulk") | Hamlet | H | 3000 | 100, 160, 200, 360 | 3 | -100 -400 | 3 | 10 | Iron Ore×2-6, Infused Iron×6-7, Blueprint (Living Artifact) |
| Kingfisher | [Kingfisher](/wiki/Kingfisher "Kingfisher") | Hamlet | P | 25 |  |  |  |  |  | Morsel 50%, Azure Feather 50% |
| Parrot (Hamlet) | [Parrot](/wiki/Parrot_(Hamlet) "Parrot (Hamlet)") | Hamlet | P | 25 |  |  |  |  |  | Morsel 50%, Crimson Feather 50% |
| Pigeon | [Pigeon](/wiki/Pigeon "Pigeon") | Hamlet | P | 25 |  |  |  |  |  | Morsel 50%, Azure Feather 50% |
| Mant | [Mant](/wiki/Mant "Mant") | Hamlet | H | 250 | 22.5 | 3 | +25 (if allied) | 3 | 5 | Monster Meat 75%, Chitin 25% |
| Pangolden | [Pangolden](/wiki/Pangolden "Pangolden") | Hamlet | P | 500 |  |  |  | 2.5 | 8 | Meat×3 |
| Peagawk | [Peagawk](/wiki/Peagawk "Peagawk") | Hamlet | P | 50 |  |  |  | 3 | 8 | Peagawk Plume}, Drumstick×2 |
| Piko Piko | [Piko](/wiki/Piko "Piko") | Hamlet | N | 100 | 2 | 2 |  | 2 | 4 | Morsel |
| Platapine | [Platapine](/wiki/Platapine "Platapine") | Hamlet | N | 250 | 17 |  |  | 5 | 8 | Meat, Platapine Quill×2-3 |
| Pog | [Pog](/wiki/Pog "Pog") | Hamlet | N | 150 | 25 | 2 |  | 2 | 4.5 | Morsel |
| Poison Dartfrog | [Poison Dartfrog](/wiki/Poison_Dartfrog "Poison Dartfrog") | Hamlet | H | 100 | 10 | 1 |  | 3 | 4 | Poison Dartfrog Legs 100%, Venom Gland 50% |
| Pugalisk | [Pugalisk](/wiki/Pugalisk "Pugalisk") | Hamlet | H | 3000 | 200(150) 100(50) 22.5 | 3 3 1 | -100 | 4.5 | 4.5 | Monster Meat×6-16, Snake Bone×42-55, Bone Shards×22-46, Red Gem×0-3, Blue Gem×0-3, Spoiled Fish×1-4, Petrifying Bones |
| Queen Womant | [Queen Womant](/wiki/Queen_Womant "Queen Womant") | Hamlet | H | 3500 | 0 |  |  |  |  | Monster Meat×5, Honey×5, Chitin×4, Royal Crown, Blueprint for Bundling Wrap, Stalacmite Throne |
| Ro Bin | [Ro Bin](/wiki/Ro_Bin "Ro Bin") | Hamlet | P | 450 |  |  |  | 5 | 10 |  |
| Scorpion | [Scorpion](/wiki/Scorpion "Scorpion") | Hamlet | H | 200 | 20 | 3 | -10 | 3 | 5 | Monster Meat 100%, Venom Gland 30%, Stinger 30%, Chitin 30% |
| SnaptoothSnaptoothSnaptooth | [Snaptooth Seedling](/wiki/Snaptooth_Seedling "Snaptooth Seedling") | Hamlet | H | 250 (Lv.1) 300 (Lv.2) 350 (Lv.3) | 15 (Lv.1) 20 (Lv.2) 25 (Lv.3) | 3 | -25 | 4 (Lv.1) 3.5 (Lv.2) 3(Lv.3) |  | Leafy Meat 100%, Vine 50%, Nectar 30% |
| Snaptooth Flytrap | [Snaptooth Flytrap](/wiki/Snaptooth_Flytrap "Snaptooth Flytrap") | Hamlet | H | 400 | 30 |  | -40 | 0 |  | Leafy Meat 150%, Vine 150%, Nectar 130%, Flytrap Stalk 100% |
| Spider Monkey | [Spider Monkey](/wiki/Spider_Monkey "Spider Monkey") | Hamlet | H | 550 | 60 | 2 | -40 | 5.5 | 5.5 | Monster Meat×2, Spider Gland 75%, Beard Hair×3, Silk 25% |
| Thunderbird | [Thunderbird](/wiki/Thunderbird "Thunderbird") | Hamlet | H | 50 | 10-20 |  |  | 2 | 5.5 | Drumstick×2, Thunder Feather |
| Vampire Bat | [Vampire Bat](/wiki/Vampire_Bat "Vampire Bat") | Hamlet | H | 130 | 25 | 1.8 | -40 | 10 |  | Monster Meat 50%, Pig Skin 50%, Batilisk Wing 10% |
| Viper | [Viper](/wiki/Viper "Viper") | Hamlet | H | 100 | 10 | 3 | -25 | 3 | 3 | Monster Meat 33%, Snakeskin (Hamlet) 16%, Snake Oil 0.33% |
| Weevole | [Weevole](/wiki/Weevole "Weevole") | Hamlet | H | 150 | 6 | 1.5 |  | 5 |  | Monster Meat 25%, Weevole Carapace 100% |
| Antlion | [Antlion](/wiki/Antlion "Antlion") | Don't Starve Together | N | 6000 | 50, 75, 100 | 4 | -40 | 0 | 0 | Desert Stone×6-8, Meat×4, Rocks×2-4, Trinkets, Blueprint |
| Bee Queen | [Bee Queen](/wiki/Bee_Queen "Bee Queen") | Don't Starve Together | H | 22500 | 120(60) | 2 |  | 4 | 4 | Honey×3-4, Honeycomb×1-2, Stinger×1, Royal Jelly×6-7, Bee Queen Crown, Blueprint |
| Carrat | [Carrat](/wiki/Carrat "Carrat") | Don't Starve Together | P | 25 |  |  |  | 7 | 7 | Leafy Meat, Carrot Seeds33% |
| Crab King | [Crab King](/wiki/Crab_King "Crab King") | Don't Starve Together | H | 12000 |  |  | 0 | 0 |  | Meat×7, Soprano Shell Bell×4-6, Alto Shell Bell×3-5, Baritone Shell Bell×2-3, Blueprint (rare) for Strident Trident,  Sketch for Crab King Figure (Marble),  (Cracked Pearl, Inactive Celestial Tribute if Pearl's Pearl is socketed) |
| Cookie Cutter | [Cookie Cutter](/wiki/Cookie_Cutter "Cookie Cutter") | Don't Starve Together | N | 100 | 20 | 2.5 | -40 |  | 2 | Monster Meat, Cookie Cutter Shell25% |
| Crustashine | [Crustashine](/wiki/Crustashine "Crustashine") | Don't Starve Together | P | 25 |  |  |  | 1 | 6.1 | Broken Shell50% or Fish Morsel25% or Light Bulb25% |
| Extra-Adorable Lavae | [Extra-Adorable Lavae](/wiki/Extra-Adorable_Lavae "Extra-Adorable Lavae") | Don't Starve Together | P | 250 |  |  |  |  |  |  |
| Gestalt | [Gestalt](/wiki/Gestalt "Gestalt") | Don't Starve Together | N |  | 10 Enlightenment |  |  | 1.5 |  | Cannot be killed |
| Klaus | [Klaus](/wiki/Klaus "Klaus") | Don't Starve Together | H | 15000 (total) | 75 | 3 | -100 (phase2) | 2.75 |  | Charcoal×1, Stag Antler×1, Monster Meat×1, Sketch for Klaus Figure (Marble), Candy Cane×2 (during Winter's Feast) |
| Gem Deer Gem Deer | [Gem Deer](/wiki/Gem_Deer "Gem Deer") | Don't Starve Together | H | 1500 | 25 | 2 |  | 2.5 | 8 | Meat×2, Red Gem×1, Blue Gem×1 |
| Grass Gekko | [Grass Gekko](/wiki/Grass_Gekko "Grass Gekko") | Don't Starve Together | P | 150 |  |  |  | 1 | 10 | Cut Grass, Leafy Meat |
| Grumble Bee | [Grumble Bee](/wiki/Grumble_Bee "Grumble Bee") | Don't Starve Together | H | 180 | 15 | 2 |  | 3 | 8 | Stinger |
| Horror Hound | [Horror Hound](/wiki/Horror_Hound "Horror Hound") | Don't Starve Together | H | 100 | 25 | 2.5 | -40 | 10 | 10 | Monster Meat,Hound's Tooth×2 |
| Hutch | [Hutch](/wiki/Hutch "Hutch") | Don't Starve Together | P | 450 |  |  |  |  |  |  |
| Lavae | [Lavae](/wiki/Lavae "Lavae") | Don't Starve Together | H | 500 | 50 |  |  |  |  | Rocks×1-5 (if frozen) |
| No-Eyed Deer | [No-Eyed Deer](/wiki/No-Eyed_Deer "No-Eyed Deer") | Don't Starve Together | P | 700 |  |  |  | 2.5 | 8 | Meat×1-2, Bone Shards×1-2 |
| Malbatross | [Malbatross](/wiki/Malbatross "Malbatross") | Don't Starve Together | N | 5000 | 150(75) | 4 |  | 3 |  | Meat×7, Malbatross Bill, Malbatross Feather×3-29  Blue Gem×2.3, Yellow Gem (5%),  Blueprint (rare) for Winged Sail and Feathery Canvas,   Malbatross Feather (on hit and swoop),  Sketch for Malbatross Figure (Marble) |
| Moonrock Pengull | [Moonrock Pengull](/wiki/Moonrock_Pengull "Moonrock Pengull") | Don't Starve Together | H | 100 | 20 | 3 | -25 | 0.75 | 6 | Monster Meat25%,Ice50% |
| SaladmanderSaladmander | [Saladmander](/wiki/Saladmander "Saladmander") | Don't Starve Together | N | 900 | 30, 50 | 2 |  | 0.5 | 2 | Leafy Meat×1, Dragon Fruit×1 |
| Shattered Spider | [Shattered Spider](/wiki/Shattered_Spider "Shattered Spider") | Don't Starve Together | H | 250 | 25, 10 | 3 | -20 | 3.75 | 6.25 | Monster Meat50% or Silk25% or Spider Gland25% |
| Toadstool | [Toadstool](/wiki/Toadstool "Toadstool") | Don't Starve Together | H | 52500 | 100, 120, 150, 250 | 3.5, 3, 2.5, 2 |  | 0.6, 0.8, 1.2, 3.2 |  | Frog Legs×1, Meat×3.75, Mushrooms×1.66, Mushrooms×1.66, Mushrooms×1.66, Shroom Skin×1, Blueprint×2, Blue Spore / Green Spore / Red Spore×2.5 |
| Toadstool | [Misery Toadstool](/wiki/Misery_Toadstool "Misery Toadstool") | Don't Starve Together | H | 99999 | 150, 175, 225, 300 | 3.5, 3, 2.5, 2 |  | 0.6, 0.8, 1.2, 3.2 |  | Frog Legs×1, Meat×3.75, Mushrooms×1.66, Mushrooms×1.66, Mushrooms×1.66, Shroom Skin×1, Blueprint×3, Blue Spore / Green Spore / Red Spore×2.5 |
| Shadow Pieces Shadow Pieces Shadow Pieces | [Shadow Bishop](/wiki/Shadow_Pieces "Shadow Pieces") | Don't Starve Together | H | 800 (Lv.1) 2500 (Lv.2) 7500 (Lv.3) | 20 (Lv.1) 35 (Lv.2) 60 (Lv.3) | 15 (Lv.1) 14 (Lv.2) 12 (Lv.3) | -100 |  | 3 (Lv.1) 5 (Lv.2) 7 (Lv.3) | Nightmare Fuel×1-2 (Lv.1) Nightmare Fuel×3-5 (Lv.2) Nightmare Fuel×4-6, Night Armor, Dark Sword, Shadow Atrium (Lv.3) |
| Shadow Pieces Shadow Pieces Shadow Pieces | [Shadow Knight](/wiki/Shadow_Pieces "Shadow Pieces") | Don't Starve Together | H | 900 (Lv.1) 2700 (Lv.2) 8100 (Lv.3) | 40 (Lv.1) 90 (Lv.2) 150 (Lv.3) | 3 (Lv.1) 3.2 (Lv.2) 2 (Lv.3) | -100 |  | 7 (Lv.1) 9 (Lv.2) 12 (Lv.3) | Nightmare Fuel×1-2 (Lv.1) Nightmare Fuel×3-5 (Lv.2) Nightmare Fuel×4-6, Night Armor, Dark Sword, Shadow Atrium (Lv.3) |
| Shadow Pieces Shadow Pieces Shadow Pieces | [Shadow Rook](/wiki/Shadow_Pieces "Shadow Pieces") | Don't Starve Together | H | 1000 (Lv.1) 4000 (Lv.2) 10000(Lv.3) | 45 (Lv.1) 100 (Lv.2) 165 (Lv.3) | 6 (Lv.1) 5.5 (Lv.2) 5 (Lv.3) | -100 |  | 7 (Lv.1) 8 (Lv.2) 11 (Lv.3) | Nightmare Fuel×1-2 (Lv.1) Nightmare Fuel×3-5 (Lv.2) Nightmare Fuel×4-6, Night Armor, Dark Sword, Shadow Atrium (Lv.3) |
| Reanimated Skeleton Forest | [Reanimated Skeleton (Forest)](/wiki/Reanimated_Skeleton "Reanimated Skeleton") | Don't Starve Together | P | 4000 |  |  | -400 | 2.4 |  | Shadow Atrium, Fossils×8, Nightmare Fuel×2-4 |
| Reanimated Skeleton Caves | [Reanimated Skeleton (Cave)](/wiki/Reanimated_Skeleton#Cave "Reanimated Skeleton") | Don't Starve Together | H | 4000 | 100 | 4 | -400 | 4.2 |  | Shadow Atrium, Fossils×8, Nightmare Fuel×2-4 |
| Ancient Fuelweaver | [Ancient Fuelweaver](/wiki/Ancient_Fuelweaver "Ancient Fuelweaver") | Don't Starve Together | H | 16000 | 100 | 3 | -400 | 4.2 |  | Fossils×8, Nightmare Fuel×2-4, Shadow Thurible, Bone Helm, Bone Armor, Flower  Sketch for Ancient Fuelweaver Figure (Marble) (if player kills it)  Shadow Atrium (if player leaves the fight) |
| Woven Shadow | [Woven Shadow](/wiki/Woven_Shadow "Woven Shadow") | Don't Starve Together | P | 1 |  |  | -40 | 3 | 3 |  |
| Woven Shadow | [Woven Shadow](/wiki/Woven_Shadow "Woven Shadow") | Don't Starve Together | P | 1 |  |  | -40 | 1.5 | 1.5 |  |
| Moon Moth | [Moon Moth](/wiki/Moon_Moth "Moon Moth") | Don't Starve Together | P | 1 |  |  |  | 2.5 |  | Moon Moth Wings |
| Loyal Merm Guard | [Loyal Merm Guard](/wiki/Loyal_Merm_Guard "Loyal Merm Guard") | Don't Starve Together | N | 200 660(King of the Merms) | 20 50(King of the Merms) | 3 |  | 3 | 8 | Freshwater Fish, Frog Legs |
| Pipspook | [Pipspook](/wiki/Pipspook "Pipspook") | Don't Starve Together | P |  |  |  | -40 | 2 | 6 |  |
| Skittersquid | [Skittersquid](/wiki/Skittersquid "Skittersquid") | Don't Starve Together | N | 170 | 6.67 | 4 |  | 4 | 6 | Monster Meat, Light Bulb33% |
| Crabby Hermit | [Crabby Hermit](/wiki/Crabby_Hermit "Crabby Hermit") | Don't Starve Together | P |  |  |  |  |  |  |  |
| WobyWoby Big | [Woby](/wiki/Woby "Woby") | Don't Starve Together | P |  |  |  |  | 1.5 | 8-10 |  |
| Gnarwail | [Gnarwail](/wiki/Gnarwail "Gnarwail") | Don't Starve Together | N | 1000 | 50 |  |  | 1.875 | 5   10 (dive) | Fish Meat×4, Gnarwail Horn |
| Rockjaw | [Rockjaw](/wiki/Rockjaw "Rockjaw") | Don't Starve Together | H | 1000 | 30x3 |  |  | 1 | 7 | Fish Meat×4 |
| Sea Weed Sea Weed Sea Weed | [Sea Weed](/wiki/Sea_Weed "Sea Weed") | Don't Starve Together | N | 500 | 68 | 5 2.5 (yellow) |  | 0 | 0 | Seedshell×2-3 (3-5 Yellow), Sea Sprout Starter |
| Terrorclaw | [Terrorclaw](/wiki/Terrorclaw "Terrorclaw") | Don't Starve Together | H | 400 | 50 | 3 | -100 |  | 2 | Nightmare Fuel×1-2 |
| Wavey Jones | [Wavey Jones](/wiki/Wavey_Jones "Wavey Jones") | Don't Starve Together | H |  |  |  |  |  |  |  |
| Ancient Sentrypede | [Ancient Sentrypede](/wiki/Ancient_Sentrypede "Ancient Sentrypede") | Don't Starve Together | H | 1200 | 40 20 (AoE) | 5 |  | 4 |  | Sentrypede Husk |
| Sentrypede Husk | [Sentrypede Husk](/wiki/Sentrypede_Husk "Sentrypede Husk") | Don't Starve Together | P | 300 |  |  |  | 0 | 0 |  |
| Dust Moth | [Dust Moth](/wiki/Dust_Moth "Dust Moth") | Don't Starve Together | P | 200 |  |  |  | 2.6 |  | Morsel |
| Greater Gestalt | [Greater Gestalt](/wiki/Greater_Gestalt "Greater Gestalt") | Don't Starve Together | N |  | 180, 135, 42.5 |  | +40 | 4 |  |  |
| Mush Gnome | [Mush Gnome](/wiki/Mush_Gnome "Mush Gnome") | Don't Starve Together | N | 600 | 10 (Lunar Spore) | 8 | -25  -40 (in fight) | 2 |  | Living Log×1-2, Lunar Spore×3-5, Moon Shroom× 2 |
| Naked Mole Bat | [Naked Mole Bat](/wiki/Naked_Mole_Bat "Naked Mole Bat") | Don't Starve Together | H | 150 | 30 | 2 |  | 5 |  | Monster Meat66%, Naked Nostrils33% |
| Lord of the Fruit Flies | [Lord of the Fruit Flies](/wiki/Lord_of_the_Fruit_Flies "Lord of the Fruit Flies") | Don't Starve Together | H | 1500 | 25 | 2 | -25 | 8 |  | Friendly Fruit Fly Fruit, Leafy Meat, Plant Seeds×4-8 |
| Fruit Fly | [Fruit Fly](/wiki/Fruit_Fly "Fruit Fly") | Don't Starve Together | H | 100 | 5 | 2 | -10 | 8 |  | Seeds 10% |
| Friendly Fruit Fly | [Friendly Fruit Fly](/wiki/Friendly_Fruit_Fly "Friendly Fruit Fly") | Don't Starve Together | P | 100 |  |  | +6.25 |  |  |  |
| Grainy Transmission | [Grainy Transmission](/wiki/Grainy_Transmission "Grainy Transmission") | Don't Starve Together | P |  |  |  |  |  |  |  |
| Misshapen Bird | [Misshapen Bird](/wiki/Misshapen_Bird "Misshapen Bird") | Don't Starve Together | H | 10 | 20 | 6 | +40 | 4 | 4 |  |
| Moonblind Crow | [Moonblind Crow](/wiki/Moonblind_Crow "Moonblind Crow") | Don't Starve Together | H | 10 | 20 | 6 | +40 | 4 | 4 |  |
| Celestial Champion | [Celestial Champion (Phase 1)](/wiki/Celestial_Champion "Celestial Champion") | Don't Starve Together | H | 10000 | 167(125)--Roll 67(50)--AoE | 7.5 | +400 | 5 |  | Moon Rock×5-7 |
| Celestial Champion | [Celestial Champion (Phase 2)](/wiki/Celestial_Champion#Phase_2 "Celestial Champion") | Don't Starve Together | H | 13000 | 133(100)--chop 133(100)--spin 50(37.5)--spike 135--gestalt | 4.75 | +400 | 4.5 |  | Moon Shard×4-6, Moon Rock×2-4 |
| Celestial Champion | [Celestial Champion (Phase 3)](/wiki/Celestial_Champion#Phase_3 "Celestial Champion") | Don't Starve Together | H | 14000 | 150(112)--stab 150(112)--laser 50(37.5)--snare 180--gestalt | 5 | +400 | 6 |  | Moon Shard×13-26, Moon Rock×8-14, Infused Moon Shard×6-12, Sketch for Chess Pieces Celestial Altar Pieces, Celestial Altar Pieces, Celestial Altar Pieces, Celestial Sanctum Pieces, Celestial Sanctum Pieces, Inactive Celestial Tribute, Celestial Orb, Enlightened Crown |
| Nurse Spider | [Nurse Spider](/wiki/Nurse_Spider "Nurse Spider") | Don't Starve Together | H | 400 | 10 | 3 | -25 | 3 | 5 | Monster Meat 50%, Spider Gland 25%, Silk 25% |
| Sea Strider | [Sea Strider](/wiki/Sea_Strider "Sea Strider") | Don't Starve Together | H | 200 | 20 | 3 | -30 | 3   1.5 (ocean) | 5   10 (ocean) | Monster Meat 50%, Spider Gland 25%, Silk 25% |
| Grass Gator | [Grass Gator](/wiki/Grass_Gator "Grass Gator") | Don't Starve Together | N | 1000 | 50 | 4 |  | 1.5 | 6.5   3.5 (ocean) | Leafy Meat×7, Cut Grass×2, Twigs×2 |
| Eye of Terror | [Eye of Terror](/wiki/Eye_of_Terror "Eye of Terror") | Don't Starve Together | H | 5000 | 125(62.5) |  | -100 | 4.5 | 15 (P1) 18.75 (P2) | Monster Meat×2-4, Milky Whites×3-5, Eye Mask, Sketch for Eye of Terror Figure (Marble) |
| Suspicious Peeper | [Suspicious Peeper](/wiki/Suspicious_Peeper "Suspicious Peeper") | Don't Starve Together | H | 200 | 20 | 3 |  | 4 |  |  |
| Twins of Terror | [Retinazor](/wiki/Twins_of_Terror "Twins of Terror") | Don't Starve Together | H | 10000 | 250(125) |  | -100 | 4.5 | 20 (P1) 22 (P2) | Yellow Gem, Gears×3-5, Frazzled Wires×1-2, Electrical Doodad×2-3, if Spazmatism is defeated: Shield of Terror, Sketch for Twins of Terror Figure (Marble) |
| Twins of Terror | [Spazmatism](/wiki/Twins_of_Terror "Twins of Terror") | Don't Starve Together | H | 10000 | 250(125) |  | -100 | 4.5 | 23 (P1) 25 (P2) | Green Gem, Gears×3-5, Frazzled Wires×1-2, Electrical Doodad×2-3, if Retinazor is defeated: Shield of Terror, Sketch for Twins of Terror Figure (Marble) |

### Mob Interactions[]

 https://static.wikia.nocookie.net/dont-starve-game/images/8/86/Aggro Table V1.png/revision/latest?cb=20130604203844 https://static.wikia.nocookie.net/dont-starve-game/images/8/86/Aggro Table V1.png/revision/latest?cb=20130604203844 

Mob Chart

 

This chart details the outcome of hostility if one mob is to meet another, the left vertical side of the column are the "spotters", and the straight horizontal side are the "spotted". When a Spotter meets a Spotted, the interactions are detailed. Special thanks to Spazmatic on the *Don't Starve* Forums for providing the graph.

A Spreadsheet Table can be found here: [The Aggro Table](https://docs.google.com/spreadsheet/ccc?key=0AktErFcEFQVtdHpjWGE4Mi1IelFPellqc2poZlZQY3c#gid=0).

* [DS](#)
* [RoG](#)
* [SW](#)
* [Ham](#)
* [DST](#)
* [All](#)

|  |  |
| --- | --- |
| ****Mobs**** [view](/wiki/Template:Mobs_DS "Template:Mobs DS") | |
|  | **Attention:** The following categories are only for the players look up the page based on their first impression, they have nothing to do with the actual categories/behavior/function/tags of these creatures |
| **Hostile Creatures** | BatiliskKiller BeeClockwork BishopDamaged BishopClockwork KnightDamaged KnightClockwork RookDamaged RookDepths WormEwecusFrogGhostHoundRed HoundBlue HoundMacTuskWee MacTuskMermEyeplantMosquitoCrawling HorrorTerrorbeakShadow HandSlurperShadow SplumonkeySpiderSpider WarriorCave SpiderSpitterDangling Depth DwellerTallbirdTentacleBaby TentacleShadow Tentacle |
| **Neutral Animals** | BeeBeefaloBunnymanBeardlordHoundius ShootiusKoalefantKoalefantKrampusPengullPigGuardian PigWerepigRock LobsterSlurtleSplumonkeyTeenbird |
| **Passive Animals** | ButterflyBaby BeefaloCrowRedbirdSnowbirdFirefliesGobblerMandrakeLureplantRabbitBeardlingSnurtleSmallbirdBig TentacleShadow HandMr SkitsShadow WatcherFishEelWorm HoleSick Worm Hole |
| **Boss** | Spider QueenAncient GuardianDeerclopsTreeguardTreeguard |
| **Follower Animals** | AbigailChesterShadow Puppet |
| **Traders** | Pig King |
| **NPC** | Maxwell (NPC)Sunken Boat |

|  |  |
| --- | --- |
| ****Mobs**** [view](/wiki/Template:Mobs_RoG "Template:Mobs RoG") | |
|  | **Attention:** The following categories are only for the players look up the page based on their first impression, they have nothing to do with the actual categories/behavior/function/tags of these creatures |
| **Hostile Creatures** | BatiliskKiller BeeClockwork BishopDamaged BishopClockwork KnightDamaged KnightClockwork RookDamaged RookDepths WormEwecusFrogGhostHoundRed HoundBlue HoundMacTuskWee MacTuskMermEyeplantMosquitoCrawling HorrorTerrorbeakShadow HandSlurperShadow SplumonkeySpiderSpider WarriorCave SpiderSpitterDangling Depth DwellerTallbirdTentacleBaby TentacleShadow TentaclePoison Birchnut TreeBirchnutterVargCharged Volt GoatBigfoot |
| **Neutral Animals** | BeeBeefaloBunnymanBeardlordHoundius ShootiusKoalefantKoalefantKrampusPengullPigGuardian PigWerepigRock LobsterSlurtleSplumonkeyTeenbirdBuzzardCatcoonMoslingVolt Goat |
| **Passive Animals** | ButterflyBaby BeefaloCrowRedbirdSnowbirdFirefliesGobblerMandrakeLureplantRabbitBeardlingSnurtleSmallbirdBig TentacleMr SkitsShadow WatcherFishEelMolewormWorm Hole |
| **Boss** | Spider QueenAncient GuardianDeerclopsTreeguardTreeguardBeargerDragonflyMoose/Goose |
| **Follower Animals** | AbigailChesterGlommerShadow Puppet |
| **Traders** | Pig King |
| **NPC** | Maxwell (NPC) |

|  |  |
| --- | --- |
| ****Mobs**** [view](/wiki/Template:Mobs_SW "Template:Mobs SW") | |
|  | **Attention:** The following categories are only for the players look up the page based on their first impression, they have nothing to do with the actual categories/behavior/function/tags of these creatures |
| **Hostile Creatures** | Killer BeeCrocodogBlue CrocodogYellow CrocodogDragoonFrogFloaty Boaty KnightFlupMermEyeplantCrawling HorrorTerrorbeakSwimming HorrorShadow HandSpiderSpider WarriorSnakePoison SnakePoison MosquitoSea HoundStink RaySwordfishWhite Whale |
| **Neutral Animals** | BeeBlue WhaleBottlenose BallphinKrampusPrime ApeWater BeefaloBaby Water BeefaloWildbore |
| **Passive Animals** | ButterflyCormorantCrabbitCrabbitDogfishDoydoyTeen DoydoyBaby DoydoyFishermermFirefliesGobblerJellyfishRainbow JellyfishMandrakeLureplantParrotParrot PirateSeagullSealSharkittenToucanWobsterMr SkitsMr. SkittishShadow WatcherTropical FishPierrot FishNeon QuattroPurple Grouper |
| **Boss** | Spider QueenPalm TreeguardQuackenSealnadoTiger Shark |
| **Follower Animals** | AbigailPackim BaggimsShadow Puppet |
| **Traders** | Yaarctopus |
| **NPC** | WoodlegsWilbur |

|  |  |
| --- | --- |
| ****Mobs**** [view](/wiki/Template:Mobs_Ham "Template:Mobs Ham") | |
|  | **Attention:** The following categories are only for the players look up the page based on their first impression, they have nothing to do with the actual categories/behavior/function/tags of these creatures |
| **Hostile Creatures** | Ancient SpiritElder MandrakeGiant GrubRabid BeetleGnat SwarmHanging VineMantMant WarriorMant WarriorPoison DartfrogScorpionSnaptooth SeedlingSnaptooth SeedlingSnaptooth SeedlingSpider MonkeyThunderbirdVampire BatViperWeevoleCrawling HorrorTerrorbeakSwimming HorrorShadow HandMasked PigPog |
| **Neutral Animals** | BFBHippopotamooseIron Hulk RibsIron Hulk ArmIron Hulk LegIron Hulk HeadPikoPikoPlatapinePogRoyal Guard |
| **Passive Animals** | ButterflyDung BeetleGlowflyKingfisherParrotPigeonPangoldenPeagawkMr SkitsShadow SkittishShadow WatcherFishGummy SlugBean Bugs |
| **Boss** | Ancient HeraldIron HulkPugaliskQueen Womant |
| **Follower Animals** | AbigailRo BinShadow Puppet |
| **Traders** | Pig HatmakerPig MinerPig ProfessorPig UsherPig BankerPig BeauticianPig CollectorPig EruditePig FarmerPig FloristPig ShopkeepPig WorkerPig Queen |
| **NPC** | MaxameleonWilba |

|  |  |
| --- | --- |
| ****Mobs**** [view](/wiki/Template:Mobs_DST "Template:Mobs DST") | |
|  | **Attention:** The following categories are only for the players look up the page based on their first impression, they have nothing to do with the actual categories/behavior/function/tags of these creatures |
| **Hostile Creatures** | BatiliskKiller BeeClockwork BishopDamaged BishopClockwork KnightDamaged KnightClockwork RookDamaged RookDepths WormEwecusFrogGhostHoundRed HoundBlue HoundMacTuskWee MacTuskMermEyeplantMosquitoCrawling HorrorTerrorbeakShadow HandSlurperShadow SplumonkeySpiderSpider WarriorCave SpiderSpitterDangling Depth DwellerTallbirdTentacleBaby TentacleShadow TentaclePoison Birchnut TreeBirchnutterVargCharged Volt GoatGem DeerGem DeerGrumble BeeHorror HoundLavaeMoonrock PengullShattered SpiderShadow BishopShadow BishopShadow KnightShadow KnightShadow RookShadow RookReanimated Skeleton CavesRockjawTerrorclawWavey JonesAncient SentrypedeNaked Mole BatFruit FlyMisshapen BirdMoonblind CrowNurse SpiderSea StriderSuspicious PeeperBriar WolfVargletPowder MonkeyPrime MateGrazerDeadly BrightshadeFused ShadelingDread MiteShriekRaspJitters Gingerbread VargClay VargClay HoundGem DeerGem Deer |
| **Neutral Animals** | BeeBeefaloBunnymanBeardlordHoundius ShootiusKoalefantKoalefantKrampusPengullPigGuardian PigWerepigRock LobsterSlurtleSplumonkeyTeenbirdBuzzardCatcoonMoslingVolt GoatCookie CutterGestaltGreater GestaltSaladmanderSaladmander RipeLoyal Merm GuardLoyal Merm GuardSkittersquidGnarwailSea WeedSea WeedSea WeedMush GnomeGrass GatorStagehandCozy BunnymanMossling |
| **Passive Animals** | ButterflyBaby BeefaloCrowRedbirdSnowbirdFirefliesGobblerMandrakeLureplantRabbitBeardlingSnurtleSmallbirdBig TentacleMr SkitsShadow WatcherFishEelMolewormCarratExtra-Adorable LavaeGrass GekkoNo-Eyed DeerReanimated Skeleton ForestWoven ShadowWoven ShadowMoon MothPipspookDust MothCanaryYear of the Pig KingYear of the Pig KingYear of the Pig KingYear of the Pig KingRunty GuppyNeedlenosed SquirtBitty BaitfishSmolt FryPopperfishFallounderBloomfin TunaScorching SunfishSpittlefishMudfishDeep BassDandy LionfishBlack CatfishCorn CodIce BreamSweetish FishStagehandBulbous LightbugShadow PuppetParasitic ShadelingBrightshade GestaltResting HorrorYear of the CarratYear of the CarratYear of the CarratYear of the CarratYear of the CarratYear of the CarratYear of the CarratYear of the CarratTotally Normal BatsCrow KidCrow KidCrow KidGingerbread PigKitcoonKitcoonKitcoonKitcoonKitcoonKitcoonKitcoonKitcoonKitcoonTicoon |
| **Boss** | Spider QueenAncient GuardianDeerclopsTreeguardTreeguardBeargerDragonflyDragonflyMoose/GooseAntlionBee QueenCrab KingKlausMalbatrossToadstoolMisery ToadstoolShadow BishopShadow KnightShadow RookAncient FuelweaverLord of the Fruit FliesCelestial ChampionCelestial ChampionCelestial ChampionEye of TerrorRetinazorSpazmatismNightmare WerepigKlausDeerclopsMoose/GooseBeargerDragonflyDragonfly |
| **Follower Animals** | AbigailChesterGlommerHutchWobyWobyFriendly Fruit FlyBernieBernieBio ScanalyzerKittykitVarglingEweletBroodlingGlomglomGibletMothlingFriendly PeeperPolly Roger |
| **Traders** | Pig KingKing of the MermsQueen of Moon QuayCorvus GoodfeatherPig King Event |
| **NPC** | Crabby HermitGrainy TransmissionMocking BirdCharlieJudge's Booth |
| **[The Forge](/wiki/The_Forge "The Forge")** | Battlemaster PugnaPit PigCrocommanderSnortoiseScorpeonBoarillaGrand Forge BoarriorRhinocebroRhinocebroInfernal SwineclopsBoaraudienceBoaraudienceBoaraudienceBoaraudienceBaby spiderMagma GolemMagma Golem |
| **[The Gorge](/wiki/The_Gorge "The Gorge")** | MumsyBillySammyPiptonSwamp PigSwamp Pig ElderPebble CrabPigeonOld BeefaloSalmon |

|  |  |
| --- | --- |
| ****Mobs**** [view](/wiki/Template:Mobs_All "Template:Mobs All") | |
|  | **Attention:** The following categories are only for the players look up the page based on their first impression, they have nothing to do with the actual categories/behavior/function/tags of these creatures |
| **Spiders https://static.wikia.nocookie.net/dont-starve-game/images/8/84/Spider.png/revision/latest?cb=20220330043816** | SpiderSpider WarriorDangling Depth DwellerCave SpiderSpitterSpider QueenSpider WarriorSpider MonkeyNurse SpiderSea StriderShattered SpiderBaby spider |
| **Hounds https://static.wikia.nocookie.net/dont-starve-game/images/c/cc/Hound.png/revision/latest?cb=20231211174228** | HoundRed HoundBlue HoundVargCrocodogBlue CrocodogYellow CrocodogSea HoundDogfishPugaliskPogPogVarglingVargletWobyWobyBriar WolfHorror HoundClay VargClay HoundGingerbread Varg |
| **Mechanisms https://static.wikia.nocookie.net/dont-starve-game/images/f/f7/Clockwork Knight.png/revision/latest?cb=20230325193045** | Clockwork BishopDamaged BishopClockwork RookDamaged RookClockwork KnightDamaged KnightFloaty Boaty KnightIron HulkIron HulkIron HulkIron HulkLarge Iron HulkBio ScanalyzerAncient SentrypedeRetinazorSpazmatismRetinazorSpazmatism |
| **Bugs and Worms https://static.wikia.nocookie.net/dont-starve-game/images/5/56/Bee.png/revision/latest?cb=20121215144349** | FirefliesBeeKiller BeeMosquitoButterflyMolewormDepths WormDragonflyGlommerPoison MosquitoButterflyGlowflyRabid BeetleWeevoleDung BeetleBean BugsGiant GrubMantMant WarriorMant WarriorQueen WomantGnat SwarmAntlionGrumble BeeBee QueenLavaeDragonflyBroodlingFruit FlyFriendly Fruit FlyLord of the Fruit FliesDust MothBulbous LightbugMoon MothMothlingGlomglomWorm HoleSick Worm HoleDragonflyDragonfly |
| **Сrustaceans https://static.wikia.nocookie.net/dont-starve-game/images/c/c2/Wobster Land.png/revision/latest?cb=20200418191157** | Rock LobsterCrabbitCrabbitWobsterBarnaclesScorpionCrab KingCrabby HermitWobsterLunar WobsterCrustashineScorpeonPebble Crab |
| **Ungulates (no pigs) https://static.wikia.nocookie.net/dont-starve-game/images/e/e4/Beefalo.png/revision/latest?cb=20220330044722** | EwecusBeefaloBaby BeefaloKoalefantKoalefantDeerclopsChesterChesterChesterAncient GuardianAncient GuardianKrampusMoose/GooseMosslingMosslingVolt GoatVolt GoatKrampusWater BeefaloBaby Water BeefaloHippopotamooseKlausKlausNo-Eyed DeerNo-Eyed DeerGem DeerGem DeerEweletKlausKlausGem DeerGem DeerMoose/GooseMosslingMosslingDeerclopsRhinocebroRhinocebroMumsyBillyOld Beefalo |
| **Rodents https://static.wikia.nocookie.net/dont-starve-game/images/f/fd/Rabbit.png/revision/latest?cb=20150107100643** | MolewormRabbitRabbitRabbitBatiliskSlurperBunnymanBunnymanBeargerPikoPikoPlatapinePangoldenVampire BatNaked Mole BatCarratYear of the CarratYear of the CarratYear of the CarratYear of the CarratYear of the CarratYear of the CarratYear of the CarratCharlie's CarratCozy BunnymanTotally Normal BatsBearger |
| **Cats https://static.wikia.nocookie.net/dont-starve-game/images/5/5e/Catcoon.png/revision/latest?cb=20230824225321** | CatcoonSharkittenTiger SharkKittykitKitcoonKitcoonKitcoonKitcoonKitcoonKitcoonKitcoonKitcoonKitcoonTicoon |
| **Birds https://static.wikia.nocookie.net/dont-starve-game/images/7/77/Gobbler.png/revision/latest?cb=20231022213142** | CrowRed BirdSnowbirdPengullTallbirdTeenbirdSmallbirdGobblerBuzzardMoose/GooseMosslingMosslingParrotParrot PirateSeagullToucanCormorantPackim BaggimsPackim BaggimsPackim BaggimsDoydoyTeen DoydoyBaby DoydoyKingfisherPigeon (Hamlet)Parrot (Blue)PeagawkThunderbirdRo BinBFBPuffinCanaryMisshapen BirdMoonblind CrowPolly RogerMoonrock PengullGibletMalbatrossMocking BirdCorvus GoodfeatherCrow KidCrow KidCrow KidMoose/GooseMosslingMosslingPigeonThe Screecher |
| **Amphibians https://static.wikia.nocookie.net/dont-starve-game/images/1/15/Merm.png/revision/latest?cb=20230713052608** | MermSlurtleSnurtleFrogBigfootCrocodogBlue CrocodogYellow CrocodogDragoonFishermermSnakePoison SnakeViperPugaliskPoison DartfrogMaxameleonGrass GekkoGrass GatorHutchHutchHutchLoyal Merm GuardLoyal Merm GuardKing of the MermsToadstoolToadstoolSaladmanderSaladmanderCrocommanderSnortoiseSammyPipton |
| **Mollusca https://static.wikia.nocookie.net/dont-starve-game/images/d/d2/Snurtle.png/revision/latest?cb=20130520063441** | SnurtleSlurtleTentacleBig TentacleBaby TentacleShadow TentacleLimpetsYaarctopusQuackenGummy SlugSkittersquid |
| **Pinnipedia https://static.wikia.nocookie.net/dont-starve-game/images/6/6e/MacTusk.png/revision/latest?cb=20220331005048** | MacTuskWeeTuskSealnadoSealnado |
| **Underwater Сreatures https://static.wikia.nocookie.net/dont-starve-game/images/3/38/Freshwater Fish.png/revision/latest?cb=20191206205353** | Freshwater FishEelPengullTropical FishPierrot FishNeon QuattroPurple GrouperSwordfishDogfishSea HoundWobsterJellyfishRainbow JellyfishFlupSharkittenTiger SharkCrocodogBlue CrocodogYellow CrocodogQuackenYaarctopusBottlenose BallphinBlue WhaleWhite WhaleStink RayFishRunty GuppyNeedlenosed SquirtBitty BaitfishSmolt FryPopperfishFallounderBloomfin TunaScorching SunfishSpittlefishMudfishDeep BassDandy LionfishBlack CatfishCorn CodIce BreamSweetish FishCookie CutterWobsterLunar WobsterSkittersquidMoonrock PengullMalbatrossCrab KingGnarwailRockjawDappled KoiGolden KoiSalmon |
| **Terraria https://static.wikia.nocookie.net/dont-starve-game/images/1/1f/Suspicious Peeper.png/revision/latest?cb=20230330075618** | Friendly PeeperSuspicious PeeperEye of TerrorEye of TerrorRetinazorRetinazorSpazmatismSpazmatism |
| **Living plantshttps://static.wikia.nocookie.net/dont-starve-game/images/3/34/Mandrake Mob.png/revision/latest?cb=20231011213839** | MandrakeDepths WormTreeguardTreeguardLureplantEyeplantPoison Birchnut TreeBirchnutterPalm TreeguardPeagawkSnaptooth SeedlingSnaptooth SeedlingSnaptooth SeedlingHanging VineElder MandrakeGrass GekkoGrass GatorToadstoolToadstoolBulbous LightbugMush GnomeCorn CodPopperfishBloomfin TunaFallounderSea WeedSea WeedSea WeedSaladmanderSaladmanderBriar WolfDeadly BrightshadeCarratYear of the CarratYear of the CarratYear of the CarratYear of the CarratYear of the CarratYear of the CarratYear of the CarratCharlie's Carrat |
| **Pigs https://static.wikia.nocookie.net/dont-starve-game/images/3/30/Pig.png/revision/latest?cb=20200503112602** | PigGuardian PigWerepigPig KingPig KingAncient SpiritMasked PigRoyal GuardPig HatmakerPig MinerPig ProfessorPig UsherPig BankerPig BeauticianPig CollectorPig EruditePig FarmerPig FloristPig ShopkeepPig WorkerPig QueenWilbaNightmare WerepigYear of the Pig KingYear of the Pig KingYear of the Pig KingYear of the Pig KingYear of the Pig KingGingerbread PigBoaraudienceBoaraudienceBoaraudienceBoaraudiencePit PigGrand Forge BoarriorInfernal SwineclopsSwamp PigSwamp Pig Elder |
| **Monkeys https://static.wikia.nocookie.net/dont-starve-game/images/8/8f/Splumonkey.png/revision/latest?cb=20231023005542** | SplumonkeySplumonkeyPrime ApeWilburSpider MonkeyPowder MonkeyPrime MateQueen of Moon QuayBoarilla |
| **Golems https://static.wikia.nocookie.net/dont-starve-game/images/c/ce/Rock Lobster.png/revision/latest?cb=20130607055500** | Rock LobsterHoundius ShootiusCrab KingRockjawClay VargClay HoundMagma GolemMagma Golem |
| **Ghosts https://static.wikia.nocookie.net/dont-starve-game/images/7/7f/Ghost Build.png/revision/latest?cb=20231112212135** | GhostAbigailPirate GhostAncient SpiritPipspookPipspookPipspookPipspook |
| **Lunar Сreatures https://static.wikia.nocookie.net/dont-starve-game/images/d/db/Gestalt.png/revision/latest?cb=20201218040543** | Shattered SpiderHorror HoundMoonrock PengullSaladmanderSaladmanderCarratMoon MothLunar WobsterMisshapen BirdMoonblind CrowBulbous LightbugMush GnomeGestaltGreater GestaltBrightshade GestaltGrazerDeadly BrightshadeCelestial ChampionCelestial ChampionCelestial ChampionCrab KingYear of the CarratYear of the CarratYear of the CarratYear of the CarratYear of the CarratYear of the CarratYear of the CarratKitcoon |
| **Shadow Сreatures https://static.wikia.nocookie.net/dont-starve-game/images/5/58/Terrorbeak.png/revision/latest?cb=20130831104603** | Shadow EyesTerrorbeakCrawling HorrorMr. SkitsShadow HandShadow WatcherSplumonkeyShadow TentacleShadow PuppetDamaged BishopDamaged RookDamaged KnightShadow SkittishSwimming HorrorWavey JonesTerrorclawParasitic ShadelingShadow KnightShadow KnightShadow KnightShadow BishopShadow BishopShadow BishopShadow RookShadow RookShadow RookStagehandStagehandReanimated SkeletonReanimated SkeletonReanimated SkeletonWoven ShadowWoven ShadowAncient GuardianNightmare WerepigFused ShadelingDread MiteShriekRaspJittersResting HorrorAncient HeraldCharlieCharlie's Carrat |
| **Follower [Animals](/wiki/Animals "Animals") https://static.wikia.nocookie.net/dont-starve-game/images/0/06/Chester.png/revision/latest?cb=20220331003130** | AbigailChesterChesterChesterGlommerPackim BaggimsFat Packim BaggimsFire Packim BaggimsRo BinHutchFugu HutchMusic Box HutchPolly RogerFriendly Fruit FlyBernieBernieBio ScanalyzerWobyWobyKittykitVarglingEweletBroodlingGlomglomGibletMothlingFriendly Peeper |
| **Boss https://static.wikia.nocookie.net/dont-starve-game/images/8/85/Deerclops.png/revision/latest?cb=20231211194522** | DeerclopsAncient GuardianAncient GuardianSpider QueenTreeguardBeargerDragonflyMoose/GooseQuackenSealnadoTiger SharkPalm TreeguardAncient HeraldLarge Iron HulkQueen WomantPugaliskDragonflyAntlionBee QueenKlausKlausMalbatrossReanimated SkeletonShadow PiecesShadow PiecesShadow PiecesCrab KingToadstoolToadstoolCelestial ChampionCelestial ChampionCelestial ChampionLord of the Fruit FliesNightmare WerepigEye of TerrorEye of TerrorRetinazorRetinazorSpazmatismSpazmatismKlausKlausDeerclopsMoose/GooseBeargerDragonflyDragonflyGrand Forge BoarriorInfernal Swineclops |
| **NPC https://static.wikia.nocookie.net/dont-starve-game/images/f/f6/Grainy Transmission.png/revision/latest?cb=20230108183457** | Maxwell NPCMaxwell NPCMaxwell NPCMaxwell NPCMaxwell NPCParrot PirateWilburWoodlegsMaxameleonWilbaCrabby HermitGrainy TransmissionMocking BirdCharlieJudge's BoothBattlemaster PugnaMumsyBillySammyPipton |
| **[The Forge](/wiki/The_Forge "The Forge") https://static.wikia.nocookie.net/dont-starve-game/images/b/b3/Battlemaster Pugna.png/revision/latest?cb=20171129233244** | Battlemaster PugnaPit PigCrocommanderSnortoiseScorpeonBoarillaGrand Forge BoarriorRhinocebroRhinocebroInfernal SwineclopsBoaraudienceBoaraudienceBoaraudienceBoaraudienceBaby spiderMagma GolemMagma GolemBernieAbigail |
| **[The Gorge](/wiki/The_Gorge "The Gorge") https://static.wikia.nocookie.net/dont-starve-game/images/3/35/The Gorge Mumsy.png/revision/latest?cb=20180616060109** | MumsyBillySammyPiptonSwamp PigSwamp Pig ElderPebble CrabPigeonOld BeefaloSalmonRabbit |
| **Event Mobs https://static.wikia.nocookie.net/dont-starve-game/images/5/5a/Clay Varg.png/revision/latest?cb=20240305023315** | Clay VargClay HoundYear of the Pig KingYear of the CarratYear of the CarratYear of the CarratYear of the CarratYear of the CarratYear of the CarratYear of the CarratCharlie's CarratJudge's BoothKitcoonKitcoonKitcoonKitcoonKitcoonKitcoonKitcoonKitcoonKitcoonTicoonCozy BunnymanCorvus GoodfeatherCrow KidCrow KidCrow KidTotally Normal BatsKlausKlausGem DeerGem DeerDeerclopsMoose/GooseBeargerDragonflyDragonflyMosslingMosslingGingerbread VargGingerbread Pig |