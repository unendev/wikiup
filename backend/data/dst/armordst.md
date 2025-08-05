---
game_version: dst
category: Uncategorized
source_url: https://dontstarve.fandom.com/wiki/Armor/DST
---

**Armor** is a type of in-game [Item](/wiki/Items "Items") that mitigates damage and protects the character from harm. It works by absorbing a portion of incoming hit damage in place of the wearer's (character or mob) [Health](/wiki/Health "Health"). And loses [Durability](/wiki/Durability "Durability") based on damage it blocked.

Armor generally has two stats called **Protection** and **Planar Defense**, which distribute (general) [Damage](/wiki/Damage "Damage") and [Planar Damage](/wiki/Planar_Damage "Planar Damage") by hit for the [Mob](/wiki/Mobs "Mobs") or [Character](/wiki/Characters "Characters") they are equipped with.

Note that armor can only share physical Damage and Planar Damage, other sources of damage such as [Food](/wiki/Food "Food"), [Fire](/wiki/Fire "Fire"), [Freezing](/wiki/Freezing "Freezing"), [Overheating](/wiki/Overheating "Overheating") or [Crafting](/wiki/Crafting "Crafting") cannot be absorbed by armor. And armor isn't the only way to reduce health losses.

## Contents

* [1 Calculations](#Calculations)
  + [1.1 Protection](#Protection)
  + [1.2 Planar Defense](#Planar_Defense)
  + [1.3 Example](#Example)
  + [1.4 True durability](#True_durability)
* [2 Chest armor](#Chest_armor)
* [3 Head armor](#Head_armor)
* [4 Hand armor](#Hand_armor)

## Calculations[]

### Protection[]

1. Armor absorbs physical damage from hits **equal proportion** to their Protection value.
2. If wearing more than one piece of armor, only the highest Protection value is taken into account.
3. Damage is split among all of the worn armors, weighed by their Protection value.
4. Unabsorbed physical damage is then subtracted from the character's or mob's Health, subject to further damage reduction from sources like [Garlic Powder](/wiki/Garlic_Powder "Garlic Powder") or [Wigfrid](/wiki/Wigfrid "Wigfrid")'s natural defense.

`Damage Taken = Physical Damage * (1 - Highest Protection)`

### Planar Defense[]

Attention: Planar Defense is not [Planar Entity Protection](/wiki/Planar_Entity_Protection "Planar Entity Protection")!

1. Armor absorbs Planar Damage from hits **equal quantity** to their Planar Defense value.
2. Damage is evenly distributed to each armor until it reaches the Planar Defense they have.
3. Unabsorbed planar damage is then subtracted from the character's or mob's Health.

`Damage Taken = Planar Damage - Sum of all Planar Defense`

For mobs that deal a low amount of planar damage, such as the [Grazer](/wiki/Grazer "Grazer"), the amount of planar damage dealt can be reduced into the negatives with this formula. A negative amount of total planar damage received simply means the target takes no planar damage.

### Example[]

**Example:** Attack damage of a [Spider Queen](/wiki/Spider_Queen "Spider Queen") (80 general + 0 planar) to a character equipped with a Grass Suit and a Football Helmet will be distributed as follows:

* 60% ＜ 80%
* 80 × 80% = 64 damage is absorbed by two armor
  + 64 \* 60%/(80%+60%) ≈ 28 is absorbed by Grass Suit
  + 64 \* 80%/(80%+60%) ≈ 37 is absorbed by Football Helmet
* 0+0 = 0 planar damage is absorbed by two armor
  + 0 planar damage is absorbed by Grass Suit
  + 0 planar damage is absorbed by Football Helmet
* (80 − 64) + (0 - 0) = 16 damage is take by the character's Health

**Another example:** Attack damage of a [Rasp](/wiki/Rasp "Rasp") (20 general + 20 planar) to a character equipped with a Brightshade Armor and a Dreadstone Helm will be distributed as follows:

* 80% ＜ 90%
* 20 × 90% = 18 damage is absorbed by two armor.
  + 18 \* 80%/(90%+80%) ≈ 8.47 is absorbed by Brightshade Armor
  + 18 \* 90%/(90%+80%) ≈ 9.53 is absorbed by Dreadstone Helm
* 10+5 = 15 planar damage is absorbed by two armor.
  + 10 planar damage is absorbed by Brightshade Armor
  + 5 planar damage is absorbed by Dreadstone Helm
* (20 − 18) + (20 - 15) = 7 damage is take by the character's Health

### True durability[]

**`True durability = Armor durability * 100 / Armor protection.`**

True durability is the amount of total damage you can receive before your armor breaks. While durability of the armor is a good representation of how much the armor protects you it can be deceptive when it comes to how long will the armor actually last.

**Example:** [Thulecite suit](/wiki/Thulecite_Suit "Thulecite Suit") has 1260 durability, [Scalemail](/wiki/Scalemail "Scalemail") has 945 durability. This would lead you to believe Scalemail breaks a lot faster, however their protection values are different. When using True Durability you can actually see that the will last about the same amount of time. To break the Scalemail a [spider](/wiki/Spider "Spider") needs to hit you 68 times. To break the Thulecite suit it will take him 70 hits, even though the suit has 33% more durability.

Thulecite Suit- 1400 (1260 \*100/90)

Scalemail - 1350 (945 \*100/70)

Of course the amount of health lost with this armor on is also different. After breaking the Thulecite suit your HP will be down by 140, but using the Scalemail you will have lost over 400 HP. True durability can a be a good metric to plan out you use of armor. For example farming spiders with plenty of healing food you should opt for a more efficient armor with higher True durability to efectively use your resources. On the other hand when fighting bosses or playing a low health character like [Maxwell](/wiki/Maxwell/Don%27t_Starve_Together "Maxwell/Don't Starve Together") or [Wes](/wiki/Wes "Wes") you might need the higher protection values.

**Another** **Example:** [Dragon fly](/wiki/Dragonfly "Dragonfly") deals 75 damage per hit, Log Suitand Football Helmethave total 786 true durability (315 \* 100 / 80 (393) + 315 \* 100 / 80 (393))

786/75=11 hits from [dragon fly](/wiki/Dragonfly "Dragonfly") will break this armor. Thulecite Suithas exactly double the durability of these two, but will only take 19 hits to break. This means that two Log Suit and two Football Helmet will actually give you protection for 3 extra hits, but will require you to heal a bunch of times to survive the 300 dmg done to you. On the other hand Thulecite Suit will break sooner, but a few characters should even be able to survive until then without needing any healing at all since only 140 dmg will be done to you.

## Chest armor[]

| Item | Name | Durability | True durability | Protection | Planar Defense | [Sanity](/wiki/Sanity "Sanity") | [Crafting](/wiki/Crafting "Crafting") | Notes |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| Grass Suit | [Grass Suit](/wiki/Grass_Suit "Grass Suit") | 157.5 | 262.5 | 60% | 0 |  | Cut Grassx10 Twigsx2 | Take 17 more damage from [Werebeaver](/wiki/Werebeaver "Werebeaver"). |
| Log Suit | [Log Suit](/wiki/Log_Suit "Log Suit") | 315 | 393 | 80% | 0 |  | Logx8 Ropex2 | Take 17 more damage from [Werebeaver](/wiki/Werebeaver "Werebeaver"). |
| Night Armour | [Night Armor](/wiki/Night_Armor "Night Armor") | 525 | 552.6 | 95% | 0 | -10/min | Nightmare Fuelx5 Papyrusx3 | Decreases Sanity by 10% of total damage (Damage absorbed by the armor + damage let through the armor). |
| Marble Suit | [Marble Suit](/wiki/Marble_Suit "Marble Suit") | 735 | 773 | 95% | 0 |  | Marblex6 Ropex2 | -30% Movement Speed [Knockback](/wiki/Knockback "Knockback") Resistance |
| Snurtle Shell Armour | [Snurtle Shell Armor](/wiki/Snurtle_Shell_Armor "Snurtle Shell Armor") | 735 | 1225 | 60% | 0 |  |  | Players can hide inside the armor. It absorbs 100% damage while hiding inside. Snurtle Shell Armor has a 75% chance to drop from [Snurtles](/wiki/Snurtle_Shell_Armor "Snurtle Shell Armor"). |
| Thulecite Suit | [Thulecite Suit](/wiki/Thulecite_Suit "Thulecite Suit") | 1260 | 1400 | 90% | 0 | +3.3/min | Thulecitex6 Nightmare Fuelx4 |  |
| Scalemail | [Scalemail](/wiki/Scalemail "Scalemail") | 945 | 1350 | 70% | 0 | +3.3/min | Scalesx1 Log Suitx1 Pig Skinx3 | When worn, it provides immunity to [fire](/wiki/Fire "Fire") damage, ignites enemies attacking the player. |
| Bone Armor | [Bone Armor](/wiki/Bone_Armor "Bone Armor") | 16 uses | 16 uses | 0 | 0 |  |  | Given a shield every 5 seconds that blocks all attack damage. [Nightmare Fuel](/wiki/Nightmare_Fuel "Nightmare Fuel") can be used to refuel 25% of its durability. |
| Dreadstone Armor | [Dreadstone Armor](/wiki/Dreadstone_Armor "Dreadstone Armor") | 840 | 933.3 | 90% | 5 | -20/min (while repairing) | Dreadstonex6 Pure Horrorx4 | When worn restores durability every second based on the character's sanity amount. |
| Brightshade Armor | [Brightshade Armor](/wiki/Brightshade_Armor "Brightshade Armor") | 830 | 1037.5 | 80% | 10 |  | Pure Brilliance×4 Brightshade Husk×4 | When an attack comes from [Lunar Aligned](/wiki/Lunar_Aligned "Lunar Aligned"), both the wearer and the armor take 10% less damage (or set bonus: total 25%) |
| Void Robe | [Void Robe](/wiki/Void_Robe "Void Robe") | 830 | 1037.5 | 80% | 10 |  | Pure Horror×4 Dark Tatters×4 | Blocks negative sanity auras |
| Bramble Husk | [Bramble Husk](/wiki/Bramble_Husk "Bramble Husk") | 525 | 656.2 | 80% | 0 |  | Living Logx2 Stingerx4 (Wormwood) | Prevents damage from [Brambles](/wiki/Bramble "Bramble"), [Bramble Blooms](/wiki/Bramble_Bloom "Bramble Bloom") and [Bramble Traps](/wiki/Bramble_Trap "Bramble Trap"). Deals 22.5 damage to every mob around wearer when hit. Only available to the *Hamlet* character [Wormwood](/wiki/Wormwood "Wormwood"). |

## Head armor[]

| Item | Name | Durability | True durability | Protection | Planar Defense | [Water Resistance](/wiki/Wetness "Wetness") | [Crafting](/wiki/Crafting "Crafting") | Notes |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| Beekeeper Hat | [Beekeeper Hat](/wiki/Beekeeper_Hat "Beekeeper Hat") | 1050 | 1312.5 | 0% (80% vs. Bee Bee Grumble Bee Bee Queen) | 0 | 20% | Silkx8 Ropex1 | Only absorbs damage taken from [Bees](/wiki/Bee "Bee"), [Killer Bees](/wiki/Killer_Bee "Killer Bee"), [Grumble Bees](/wiki/Grumble_Bee "Grumble Bee") and the [Bee Queen](/wiki/Bee_Queen "Bee Queen"), ineffective against everything else. |
| Hardwood Hat | [Hardwood Hat](/wiki/Hardwood_Hat "Hardwood Hat") | 262.5 | 375 | 70% | 0 | 0 | Lucy the Axe×1 Log×6 Pine Conex1 (Woodie Portrait) | Woodie can craft this with the proper skill tree points |
| Cookie Cutter Cap | [Cookie Cutter Cap](/wiki/Cookie_Cutter_Cap "Cookie Cutter Cap") | 525 | 725 | 70% | 0 | 35% | Cookie Cutter Shellx4 Ropex1 |  |
| Bee Queen Crown | [Bee Queen Crown](/wiki/Bee_Queen_Crown "Bee Queen Crown") | 945 | 1350 | 70% | 0 | 20% |  | Converts insanity auras into sanity auras. Triggers the Bow Emote for nearby players. Dropped by the [Bee Queen](/wiki/Bee_Queen "Bee Queen") and sometimes found in [Loot Stashes](/wiki/Loot_Stash "Loot Stash"). |
| Bone Helm | [Bone Helm](/wiki/Bone_Helm "Bone Helm") | 945 | 1350 | 70% | 0 | 20% |  | Induces insanity effects. Prevents [Shadow Creatures](/wiki/Shadow_Creature "Shadow Creature") from attacking. Dropped by the [Ancient Fuelweaver](/wiki/Ancient_Fuelweaver "Ancient Fuelweaver"). |
| Football Helmet | [Football Helmet](/wiki/Football_Helmet "Football Helmet") | 315 | 393 | 80% | 0 | 20% | Pig Skinx1 Ropex1 | If given to [Pig](/wiki/Pigman "Pigman") or [Bunnyman](/wiki/Bunnyman "Bunnyman") it provides them a damage absorption effect |
| Battle Helm | [Battle Helm](/wiki/Battle_Helm "Battle Helm") | 525 | 656.2 | 80% | 0 | 20% | Gold Nuggetx2 Rocksx2 (Wigfrid) | Only available to the *Reign of Giants* character [Wigfrid](/wiki/Wigfrid "Wigfrid"). |
| Commander's Helm | [Commander's Helm](/wiki/Commander%27s_Helm "Commander's Helm") | 682.5 | 853.1 | 80% | 8 (once unlocked) | 35% | Gold Nuggetx2 Beefalo Woolx2 Marblex1 (Wigfrid) | Unlocked in [Wigfrid](/wiki/Wigfrid "Wigfrid")'s skill tree. Provides 60 insulation and reduces knockback. Additional skill points provide 8 planar defense and auto repair (when worn by Wigfrid). |
| Brightshade Helm | [Brightshade Helm](/wiki/Brightshade_Helm "Brightshade Helm") | 830 | 1037.5 | 80% | 10 | 20% | Pure Brilliance×4 Brightshade Husk×2 | When an attack comes from [Lunar Aligned](/wiki/Lunar_Aligned "Lunar Aligned"), both the wearer and the armor take 10% less damage (or set bonus: total 25%) |
| Void Cowl | [Void Cowl](/wiki/Void_Cowl "Void Cowl") | 830 | 1037.5 | 80% | 10 | 0 | Pure Horror×4 Dark Tatters×2 | Physical damage dealt by Shadow Reaper's is increased by 10% and planar damage is increased by 8. Increases Shadow Reaper's damage by 4 per hit, up to 28 total additional planar damage. |
| Eye Mask | [Eye Mask](/wiki/Eye_Mask "Eye Mask") | 315 | 393 | 80% | 0 | 20% |  | Absorbs 80% of peneral damage. Feed food to repair. |
| Shelmet | [Shelmet](/wiki/Shelmet "Shelmet") | 525 | 583 | 90% | 0 | 20% |  | Has a 10% chance to drop from [Slurtles](/wiki/Slurtle "Slurtle"). |
| Thulecite Crown | [Thulecite Crown](/wiki/Thulecite_Crown "Thulecite Crown") | 840 | 933.3 | 90% | 0 | 0 | Thulecitex4 Nightmare Fuelx4 | Has a 33% chance to activate a force field which absorbs 100% of damage and prevents the player from getting stunned; While the force field is active 5% of the damage that would have been dealt is instead reduced from Sanity. |
| Dreadstone Helm | [Dreadstone Helm](/wiki/Dreadstone_Helm "Dreadstone Helm") | 840 | 933.3 | 90% | 5 | 20% | Dreadstonex4 Pure Horrorx4 | When worn restores durability every second based on the character's sanity amount. Drains 20 sanity/min while repairing. |

## Hand armor[]

Both shields count as armor and weapons.

| Item | Name | Durability | True durability | Protection | Planar Defense | Damage | Notes |
| --- | --- | --- | --- | --- | --- | --- | --- |
| Shield of Terror | [Shield of Terror](/wiki/Shield_of_Terror "Shield of Terror") | 315 | 393 | 80% | 0 | 51 | Feed food to repair. |
| Battle Rönd | [Battle Rönd](/wiki/Battle_R%C3%B6nd "Battle Rönd") | 420 | 494 | 85% (base) | 0 | 51 | [Wigfrid](/wiki/Wigfrid "Wigfrid") only. Absorbs 100% of general damage when block activated. Additional skill points add block time and riposte damage. |