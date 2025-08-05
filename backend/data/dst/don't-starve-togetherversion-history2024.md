---
game_version: dst
category: Uncategorized
source_url: https://dontstarve.fandom.com/wiki/Don't_Starve_Together/Version_History/2024
---

[Overview](/wiki/Don%27t_Starve_Together/Version_History "Don't Starve Together/Version History")  
[2014](/wiki/Don%27t_Starve_Together/Version_History/2014 "Don't Starve Together/Version History/2014")
[2015](/wiki/Don%27t_Starve_Together/Version_History/2015 "Don't Starve Together/Version History/2015")
[2016](/wiki/Don%27t_Starve_Together/Version_History/2016 "Don't Starve Together/Version History/2016")
[2017](/wiki/Don%27t_Starve_Together/Version_History/2017 "Don't Starve Together/Version History/2017")
[2018](/wiki/Don%27t_Starve_Together/Version_History/2018 "Don't Starve Together/Version History/2018")
[2019](/wiki/Don%27t_Starve_Together/Version_History/2019 "Don't Starve Together/Version History/2019")
[2020](/wiki/Don%27t_Starve_Together/Version_History/2020 "Don't Starve Together/Version History/2020")
[2021](/wiki/Don%27t_Starve_Together/Version_History/2021 "Don't Starve Together/Version History/2021")
[2022](/wiki/Don%27t_Starve_Together/Version_History/2022 "Don't Starve Together/Version History/2022")
[2023](/wiki/Don%27t_Starve_Together/Version_History/2023 "Don't Starve Together/Version History/2023")
**2024**

## Contents

* [1 April 2, 2024](#April_2,_2024)
* [2 March 28, 2024](#March_28,_2024)
* [3 March 27, 2024](#March_27,_2024)
* [4 March 27, 2024 - Scrappy Scavengers](#March_27,_2024_-_Scrappy_Scavengers)
* [5 March 22, 2024](#March_22,_2024)
* [6 March 21, 2024](#March_21,_2024)
* [7 March 19, 2024](#March_19,_2024)
* [8 March 15, 2024](#March_15,_2024)
* [9 March 14, 2024](#March_14,_2024)
* [10 March 8, 2024](#March_8,_2024)
* [11 March 7, 2024 - Scrappy Scavengers Beta](#March_7,_2024_-_Scrappy_Scavengers_Beta)
* [12 February 27, 2024](#February_27,_2024)
* [13 February 8, 2024](#February_8,_2024)
* [14 February 8, 2024 - Year of the Dragonfly](#February_8,_2024_-_Year_of_the_Dragonfly)
* [15 January 19, 2024](#January_19,_2024)
* [16 January 12, 2024](#January_12,_2024)

## April 2, 2024[]

(Rev. 600904, Hotfix)

**Changes**

* “Infinite” stacks are now capped at 4,294,967,296, for safety reasons.
* Using the Scaled Furnace to destroy creatures is naughty.
* W.O.B.O.T. now only stores items in chest-like containers (chests, ice boxes, bookshelves).
* W.O.B.O.T.'s deploy helper will now only show up when positioning a crafting recipe.
* Adjusted boat placement with controllers to reduce flickering.

**Bug Fixes**

* Fixed W.O.B.O.T. trying to store items indefinitely into Crock Pots or Sunken Chests.
* Fixed bug with not being able to swap ammo or tackle with the “Set Ammo” or “Attach Tackle” actions.
* Fixed Scaled Furnace's destroy feature not being accessible on controllers.
* Fixed Scaled Furnace not being able to destroy empty containers.
* Fixed Living Logs not screaming when destroyed in the Scaled Furnace.
* Fixed crash when chopping a Scared Stiff Knobbly Tree.
* Fixed a bug with the spawning of Wagstaff’s Abandoned Junk.
* Junk Piles will drop loot correctly when destroyed by Werebeaver’s Tail Slap.
* Fixed Scrap Wall item not fixing the Scrap Wall.
* Added missing footsteps during Scrappy Werepig’s tackle.
* Fixed Scrappy Werepig respawn timer restarting during a world reload when it is fully buried.
* Fixed bug where Scrappy Werepig was sometimes not blocking teleports after being defeated.
* Fixed bug where Scrappy Werepig would permanently forget how to rummage for multiple weapons at once after being teleported at low health.
* Fixed bug where Frostjaw can sometimes get stuck if they dive under the ice right as they are defeated.
* Treeguard are no longer immune to Lunar Spores.
* Lunar Fun Cap added to scrapbook.
* Fixed missing moon Scared Stiff Sapling item name.
* Fixed Pirate Monkeys not steering right.
* Fixed Ancient Key position being hidden when you drop a Thulecite Medallion even if you have another one in your inventory.
* Fixed Friendly Fruit Fly from getting stuck when interrupted while dancing.

**Notes to Modders**

* SetIncineratedSound has been deprecated and removed use inst.incineratesound or the other murdersound variables on health and murderable components.

## March 28, 2024[]

Rev. 600680 (Hotfix)

**Changes**

* Support Pillar and Dreadstone Pillar's deploy helper now only show up in the caves.
* Living Logs now scream when eaten.
* Some creatures will make sounds when incinerated.

**Bug Fixes**

* Improved Willow’s Shadow Fire targeting.
* Scrap Walls can now be repaired by Scrap and Gears.
* Fixed animation bug with certain Junky Fences turning invisible when hit.
* Fixed missing Brambleshade Armor scrapbook entry and proper crafting tooltip.
* Fixed missing Scared Stiff Knobbly Tree’s ripples and underwater roots.
* When murdering critters stacked inside an upgraded chest, you will now murder one regular sized stack at a time.
* When dropping items stacked inside an upgraded chest, you will now drop one regular sized stack at a time.
* Fixed bug where items stacked inside an upgraded chest may drop all over the ground when they perish.
* Broken-down Chest no longer forms in the ocean.
* Fixed crash when a boat with a Broken-down Chest sinks.
* Fixed a bug with loading custom control mappings.

**Note**

We did a quick fix for a spider crash that was introduced in the original 600629 build.

## March 27, 2024[]

Rev. 600454 (Hotfix)

**Changes**

* Scrappy Werepig can now be slept.
* When upgraded chests are destroyed or deconstructed, and there are too many item stacks to drop, a Broken-down Chest is left behind instead. Rebuilding it will restore the original chest as well as the remaining items within it.

**Bug Fixes**

* Fixed a crash related to Frostjaw spawning.
* Fixed Rosy Red Cane rarity corrected to Loyal.
* Fixed missing string for Abigail’s The Supernatural.

## March 27, 2024 - Scrappy Scavengers[]

Rev. 600267 (Release)

**Scrappy Scavengers**

One person's junk is another's armament. An old foe from below returns, hungry for a new source of power. Have you enough courage to challenge him for it?

**Highlights Include:**

* Skill Tree updates for Wigfrid, Willow, and Wormwood.
* Performance improvements and item management features for late game and megabases.
* Two new Moon Shroom items.
* Acid rain update
* New location with a new NPC encounter
* Much more!

**New Skins!**

“Moonbound Survivors Chest, Part II” ($4.99usd)

“This Moonbound Chest contains skins for: Warly, Wes, and Willow.”

**New Streaming Drops!**

The Rosy Red Cane joins the Rose collection. Check out the post for more details

A big thanks to everybody who participated in the beta for this update, we appreciate all your feedback and bug reports.

We're already working on our next update, but I don't have information to share about it yet. Once we do, we'll be sure to share it here as soon as possible. But for now, we hope you enjoy the Scrappy Scavengers update.

**Change List:**

**Skill trees**

* The time to accumulate points for skill trees has been reduced.
* Wigfrid:
  + Fighting Words skill: the cooldown of Battle Stingers has been decreased.
  + Startling Soliloquy panic duration increased to 6 (from 4).
* Wormwood:
  + Lunar Guardian I skill adds the Brambleshade armor to Wormwood's crafting.
  + Summoning 2 Saladmanders will give them a health boost.
  + Summoning more Lightbugs increases each bug’s light radius.
  + Photosynthesis health rate increased.
  + Full Bloom skill changed from longer bloom time to +30% bloom increase when fertilizing Wormwood.
  + Wormwood no longer gets drowsy when releasing Moon Shroom Clouds.
* Willow
  + Shadow Fire-Raiser flame will focus on a single target better when just one target is available.
  + Shadow Fire-Raiser flame does 50% more damage.
  + Doubled Patch Up I and Patch Up II regen amount.
  + Lunar Fire-Raiser ability can now be directed without moving

**Two new Moon Shroom items**

* Lunar Mushhat.
* Stuffed Mystery Meat.

**Moonstorms**

* Wagstaff will find the Restrained Static Experiment faster after the Celestial Champion has been defeated at least once in the world.
* Lunar Siphonator construction requires fewer Restrained Statics and now needs some Scrap.
* Moongleams can be more rapidly collected
* Lightning strikes in the moonstorm will create Charged Glassy Rocks.

**Shadow Rifts**

* Miasma will now spread quicker to have a more imposing presence.
* Acid Repellent has been added to provide Acid Rain immunity for its effective duration.
* Rifts are visible on the map even through the fog of view.
* Added world setting to disable Acid Rain.
* Acid Rain will no longer happen in the Lunar Grotto nor the Archives.
* Acid Bats are very Nitre hungry and will start seeking out Nitre hoarders.
* Added Embalming Spritz, an item that can be used on plants to remove all life from them, making them unproductive, fireproof and not targetable by Deadly Brightshade infestations, essentially decorative.

**Lunar Rifts**

* Rifts are visible on the map even through the fog of view.
* Added world setting to adjust lunar hail frequency.

**Performance**

* Performance increases for bases with many items collected in a small area.
* Rot will now dissolve in the rain while on the ground.
* An upgrade item for chests and scaled chests has been added to let stack sizes inside of the chest get infinitely large using late game materials.
* The Scaled Furnace can be used to destroy items, for getting rid of garbage.

**Boats**

* Jumping on and off of boats will now have a small delay to allow for better handling. This will not affect boat to boat hopping for boat bridges.

**Celestial Orb**

* Will now have increased odds of arriving depending on the following factors.
  + How friendly the Crabby Hermit is.
  + How many Celestial Altars are fully built.
  + Will no longer be blocked from spawning if there are too many boulders in the area.

**Odd Skeleton**

* The odds to build the correct form is now guaranteed while near both an active Ancient Gateway and a Shadow Atrium. Hammer stocks have declined as a result.

**Sawhorse**

* Logs may be refined as stacks using the Sawhorse.

**Slippery Ice**

* Pengull’s Ice and frozen over Ponds are now slippery. Mind your speed.

**Broken Shells**

* Increased stack size to 40.
* Shell Bells now have a 30% chance of dropping another Broken Shell when smashed.
* Shell Bell Bundle now contains 8 Shell Bells.

**Ocean rebalance:**

* Cookie Cutters now have a 25% chance of dropping another Cookie Cutter Shell.
* Rockjaw now also drops Barnacles, Flint and Rocks.
* Perl now trades an Empty Bottle for 3 Fish Foods, instead of one.
* Seedshell now does more damage.

**Junk Yard**

* New location added to the world that has searchable junk piles.
* Added W.O.B.O.T, a curious robot that can be found in Junkyard.
* Reappearance of the Werepig.

**Miscellaneous**

* Added a “Caves or no Caves” option to the flow of starting a new world.
* Containers that can go into the player’s inventory and backpack may now be opened from there directly without having the item drop to the ground.
* Crispy Skeletons found in the Dragonfly arena may now be hammered.
* The “Light” action of the Fire Staff and Fiery Pen is now secondary.
* Lureplants and Spider Dens can now be trampled by creatures that can trample through obstacles and structures.
* The Creature's Chat setting has been added. It causes some non-Survivor speech lines to display in Chat. The setting is currently used by Pearl, Wagstaff, the Ancient Fuelweaver, the Nightmare & Scrappy Werepig, and Frostjaw.
* A new option has been added in the advanced options for setting the boat hop delay to be able to remove it or make it longer.
* The Player will now enter a stationary state when opening a portable container in their inventory.  The container will stay open when using items from inside of it.  However, it will automatically close if the Player moves, performs other actions, or is interrupted.
* Adjusted teleporting destination handling for Wortox and The Lazy Explorer for controllers to allow for further ocean gap hops and to try to make it feel more intuitive to use.
* Guardian's Horn now stacks up to 10.
* Down Feathers and Malbatross Feathers now stack up to 20, from 10.
* Portable containers, such as the Spectackler Box, will now stay open in your inventory when performing crafting actions, as well as when Wormwood uses Rot from them.
* We’ve also made some controller improvements aimed at helping the Ancient Fuelweaver fight:
* Players will no longer Telepoof across the entire arena when escaping Ancient Fuelweaver’s snare.
* Weather Pain’s targeting has been improved for controllers.
* Equipping items (using DPAD) will swap your currently equipped item back into the same slot as the new item being equipped.

**Bug Fixes**

* Fixed clients seeing wrong stack sizes in many cases in the inventory, open containers, and crafting.
* Fixed players getting stuck on a chair when clicking to do an action away from the chair.
* Fixed players turning invisible for a frame when toggling prediction while on a chair, and when performing certain actions while on a chair.
* Fixed being able to read the Codex Umbra and stoke Ethereal Embers when not playing with Maxwell and Willow respectively.
* Fixed being able to use the Shadow Reaper when it is broken.
* Fixed bug where inventory items wetness did not cap at the maximum value.
* Fixed bug where things on land could become wetter than things in the ocean.
* Fixed Skill Tree data erroneously logging issues on dedicated servers during startup.
* Fixed being unable to pick up thrown torches while mounted and using the action key.
* Fixed duplicate sound when clicking on a day in the rollback tab on the host world screen.
* Fixed Dragonfly Shrine missing from “Prototypers & Stations” and “Structures” crafting filters.
* Fixed Golden Buoy and Thorny Buoy non-looping idle animation.
* Fixed the game getting into a bad state after disabling mods on a crash screen.
* Fixed beards going invisible in some cases when slipping on ice.
* Fixed Petals and Dark Petals appearing too far down on the ground.
* Fixed some snapping animations for Clay Varg.
* Fixed inventory items not showing the correct actions when the server pauses or when the player opens and closes a container.
* Fixed Wanda’s Masquerader skin missing the clock in her hair while moving upwards.
* Fixed Dragonfly Light dropping Deck Illuminator’s loot when the mast it’s attached is hammered.
* Fixed not being able to inspect things while walking.
* Fixed creatures trying to take the Deerclops Eyeball from Ice Crystaleyezers.
* Fixed bug causing clients with lag compensation disabled to slip off boats sometimes.
* Fixed Varg and Clay Varg running animation snapping to idle.
* Fixed some instances of entities not removing themselves on save.
* Fixed player vision overlays on clients breaking when changing equipment.
* Fixed a crash related to the Bramble Husk while on a Punching Bag.
* Fixed a crash related to the Bramble Trap hitting hostile creatures.
* Fixed networking bug when taking items out of certain containers.
* Fixed logs having the tradable component on the client side.
* Fixed Restrained Static non-looping animation.
* Fixed a crash related to creatures stealing items.
* Fixed a crash related to the Frostjaw chatting.
* Fixed inventory items not showing the correct actions when the server pauses or when the player opens and closes a container.
* Added two spikes in the Atrium to help prevent creatures getting caught in them.
* Fixed Lunar Rifts accumulating entities around the world when they spawn.
* Fixed a bug where Stoke Embers action sometimes does not appear until you pick up the Ethereal Embers and put them back into your inventory slot again.
* Fixed the clouds overlay not scaling in large display resolutions.
* Spider Dens can be properly trampled by creatures that can trample through obstacles and structures.
* Fixed an issue with the boat hop delay setting bypassing game desired limits for boats, like the ones found in the Year of the Dragonfly races.
* Fixed Polar Bearger Bin sound loops sometimes not stopping when closed.
* Willow no longer strafes when Absorbing Fire with her Lighter.

**Notes for Modders**

* The mod release ID for this patch is R33\_QOL\_SPRINGCLEANING.
* SetOnHealFn was added for the healer component.
* PICKUPSOUNDS table has been moved into a global table defined in constants; the old method of a reference stored on the player instance is retained for mods that used it.
* The bat prefab OnIsAcidRaining has moved into a new component acidinfusible and is fully deprecated.
* Minimap atlas handling has been changed to support two atlases from the base game; the original minimap atlas will handle all old mods. You are encouraged to use GetMinimapAtlas if you need to access the base game atlases directly as seen in bootleg. This change will be seamless in other use cases.
* Scrapbook entries typo fix “picakble” is now “pickable”.
* Fonts are now safe to use TheSim:LoadFont multiple times it is advised to add your font to the GLOBAL.FONTS table with table.insert and call AddSimPostInit(GLOBAL.LoadFonts) with the appropriate Assets table definition in modmain to have your font asset loaded.
* Pickable components changes:
* Fixed some cases where it would spawn items at the world’s origin.
* Deprecated event “pickedbyworld”. Event “picked” will now always fire and the picker parameter will tell you who did it.
* Any picker without an inventory will now spawn the loot at the inst and no longer just the world.
* A nil picker will fully prevent loot from dropping.
* We updated all existing calls to pickable:Pick to account for these.
* The pickup range now takes into account the target’s physics radius for range checks.
* Added eating animations for Clay Varg build.

**Changes since the last beta hotfix**

**Some of these changes will come to consoles in a hotfix later.**

**Changes**

* Temporarily removed spawning multiple Frostjaws.
* Added Scrappy Werepig and Frostjaw respawn rate to world settings.
* Added a minimap icon for the Teetering Junk Pile.
* Adjusted teleporting destination handling for Wortox and The Lazy Explorer for controllers to allow for further ocean gap hops and to try to make it feel more intuitive to use.
* Boat placement for controllers have been improved.
* Interaction target selection for controllers will filter out things behind the player. This focus is tightened while on a boat.

**Bug Fixes**

* Fixed a rare crash related to slipping on ice.
* Added two spikes in the Atrium to help prevent creatures getting caught in them.
* Fixed Lunar Rifts accumulating entities around the world when they spawn.
* Fixed W.O.B.O.T. working offscreen and teleporting around when inside a container.

## March 22, 2024[]

Rev. 599792 (Test)

**Changes**

* Teetering Junk Pile is now a Point of Interest.

**Bug Fixes**

* Fixed a crash when trying to wax a Poison Birchnut Tree.
* Fixed a crash with Acid Rain particles.
* Fixed Shadow Fire cast failing when Ethereal Ember are split into small stacks.
* Thrown Junk will no longer form Junk Piles in the ocean.
* Fixed a bug where Stoke Embers action sometimes does not appear until you pick up the Ethereal Embers and put them back into your inventory slot again.
* Fixed the Survivor’s inspection quotes for the Scrappy Chapauldron.

## March 21, 2024[]

Rev. 599666 (Test)

**Changes**

* Knobbly Tree and Spiky Bush may now be waxed using the Embalming Spritz.
* Embalming Spritz uses have been increased from 50 to 75.
* Added support for the Elastispacer to work on Scaled Chests.
* Upgraded chests now drop an Enlightened Shard when broken.
* Acid Bats will emit sounds before arriving for their nitre.
* Guardian's Horn now stacks up to 10.
* Down Feathers and Malbatross Feathers now stack up to 20, from 10.
* Nightmare/Scrappy Werepig respawn times have been lowered from 20 days to 10 days.
* Added speech lines for Scrappy Werepig.
* Scrappy Werepig will no longer tolerate having Junk Piles pilfered by players.
* Scrappy Werepig is now burnable and freezable. (Sleeping will be added soon as well.)
* Portable containers, such as the Spectackler Box, will now stay open in your inventory when performing crafting actions, as well as when Wormwood uses Rot from them.
* We’ve also made some controller improvements aimed at helping the Ancient Fuelweaver fight:
  + Players will no longer Telepoof across the entire arena when escaping Ancient Fuelweaver’s snare.
  + Weather Pain’s targeting has been improved for controllers.
  + Equipping items (using DPAD) will swap your currently equipped item back into the same slot as the new item being equipped.

**Bug Fixes**

* Fixed crashes when certain NPCs try to talk.
* Fixed the clouds overlay not scaling in large display resolutions.
* Added missing scrapbook storing capability for Scrappy Chapauldron.
* Spider Dens can be properly trampled by creatures that can trample through obstacles and structures.
* Fixed an issue with the boat hop delay setting bypassing game desired limits for boats, like the ones found in the Year of the Dragonfly races.
* Fixed Teetering Junk Pile not generating items in some cases.
* Fixed Polar Bearger Bin sound loops sometimes not stopping when closed.
* Fixed a bug with the range of the thorn procs from Bramble Husk and Brambleshade Armor.
* Willow no longer strafes when Absorbing Fire with her Lighter.

## March 19, 2024[]

Rev. 599141 (Test)

**Changes**

* Wagstaff’s Abandoned Junk won’t fail as much to spawn in now.
* The Enlightened Crown UI now stays closed after joining a world or going to the caves.
* The W.O.B.O.T. now works offscreen.
* The Nightmare/Scrappy Werepig will no longer spawn in both the Forest and Cave at the same time. Some beta worlds may still have both active until they are defeated.
* Lureplants can now be trampled by creatures that can trample through obstacles and structures.
* The Creature's Chat setting has been added. It causes some non-Survivor speech lines to display in Chat. The setting is currently used by Pearl, Wagstaff, the Ancient Fuelweaver, the Nightmare & Scrappy Werepig, and Frostjaw.
* Creature’s Chat will now try to avoid repeating the same message in a short time period.
* A new option has been added in the advanced options for setting the boat hop delay to be able to remove it or make it longer.
* The Player will now enter a stationary state when opening a portable container in their inventory. The container will stay open when using items from inside of it. However, it will automatically close if the Player moves, performs other actions, or is interrupted.

**Bug Fixes**

* Fixed Dragonfly Furnace being able to destroy the Eternal Fruitcake. Not even this heat can destroy this present.
* Fixed Dragonfly Furnace not dropping items when being hit with a hammer or other work items.
* Fixed a crash related to creatures stealing items.
* Fixed a crash related to the Frostjaw chatting.
* Fixed an issue related to the Junkyard fences not spawning in correctly from position or rotation.
* Some worlds where this was seen will need to hammer the fences to remove. They are harmless otherwise.
* Fixed Rifts minimap icon not disappearing after the Rift closes.
* Fixed W.O.B.O.T. sometimes consuming durability while offscreen, causing it to break much faster than it should.
* Fixed inventory items not showing the correct actions when the server pauses or when the player opens and closes a container.

**Update Note**

* There was a feature to do with Lunar Hail mentioned in our live stream. We decided it was not complete enough to come out in this update. So look for it in a future release.
* The spawning mechanic of the Nightmare/Scrappy Werepig has been modified to what we had originally planned and now that players have experienced the fight it has been changed to reflect that.

## March 15, 2024[]

Rev. 598753 (Test)

**Changes**

* Willow’s Shadow Fire will now find targets the same way the player’s Auto Attack command.
* Wormwood no longer gets drowsy when releasing Moon Shroom Cloud.
* Brambleshade Armor can only be equipped by Wormwood.
* The Scaled Furnace now may be used to destroy items.
* Retrofitting will try harder to place the Junkyard set piece, spawn in a missing W.O.B.O.T., and spawn in a missing marker for Wagstaff to make use of the Junkyard.

**Bug Fixes**

* Fixed Wanda’s Masquerader skin missing the clock in her hair while moving upwards.
* Fixed Dragonfly Light dropping Deck Illuminator’s loot when the mast it’s attached is hammered.
* Fixed Lunar Funcap crafting recipe showing up in crafting filters it wasn't supposed to show.
* Fixed not being able to inspect things while walking.
* Fixed creatures trying to take the Deerclops Eyeball from Ice Crystaleyezers.
* Fixed Scrap Wall not showing up in the “Decorations” crafting filter.
* Fixed Brambleshade Armor showing the wrong art.
* Fixed Brambleshade Armor not protecting against thorn procs.
* Fixed Brambleshade Armor procs still activating after unequipped.
* Fixed overlapping Charged Glassy Rocks due to lightning strikes.
* Fixed bug causing clients with lag compensation disabled to slip off boats sometimes.

## March 14, 2024[]

Rev. 598446 (Test)

**Changes**

* Junk Pile rewards for Frazzled Wires and Electrical Doodads have been slightly decreased to match up with other similar items.
* Wagstaff will not use the Junkyard as his workshop if it is occupied by Scrappy Werepig. He will find another location.
* Wagstaff will rebuild his Junkyard fences when placing his workshop there.
* When blueprints for Wagstaff’s creations are dropped, they will more likely be ones that nearby players have not learned yet.
* Added blueprints for Wagstaff’s creations to Scrappy Werepig’s loot table after rifts are opened. (Pre-rifts, he will drop Auto-Mat-O-Chanic instead.)
* Updated Scrappy Werepig’s pathing behavior around Junk Piles, and his tackle attack will now destroy fences that he collides with.
* Scrappy Werepig will use his old pounce attack if caught in Shadow Prison before he can grab a weapon.
* Scrappy Werepig may start rummaging for more than one weapon at a time halfway into the fight.
* Added Scrappy Chapauldron. (Hint: This item was added for flavor, and can be obtained during the Scrappy Werepig fight.)
* Revised Wormwood’s Lunar Guardian I skill: Bramble Husk buff replaced by new Brambleshade armor.
* Moongleams can be harvested more rapidly.
* Charged Glassy Rock can be created by generating lightning in a moonstorm.

**Bug Fixes**

* Fixed an issue with extra items inside of an upgraded chest dropping out when using the Set Ammo action.
* Fixed Varg and Clay Varg running animation snapping to idle.
* Fixed Wanda being unable to use the Slimy Salve for its secondary benefits.
* Fixed some instances of entities not removing themselves on save.
* Fixed player vision overlays on clients breaking when changing equipment.

**Notes for Modders**

* Added eating animations for Clay Varg build.
* Prefab fence\_junk\_pre\_rotator has been deprecated entirely and placement data is embedded inside the wagpunk\_manager component to support rebuilding of the Junkyard.

## March 8, 2024[]

Rev. 597704 (Test)

**Changes**

* Scrappy Werepig can no longer be teleported away from the Junk Yard.
* Celestial Orb spawn chances will have one additional roll for each meteor shower starting, relying solely on the player’s influence with Pearl and the Altars, to guarantee its spawn for that shower wave.
* Maxwell’s Shadow Servants will no longer destroy Scared Stiff decor.
* Horizon Expandinator may now be deconstructed.

**Bug Fixes**

* Fixed a crash related to the Bramble Husk while on a Punching Bag.
* Fixed a crash related to the Bramble Trap hitting hostile creatures.
* Fixed a crash related to the W.O.B.O.T. and building structures.
* Fixed the Stuffed Night Cap cookbook image.
* Fixed bug preventing Scrappy Werepig from respawning.
* Fixed networking bug when taking items out of certain containers.
* Fixed rot disappearing in rain when in containers and inventories.
* Fixed the Scrappy Werepig not respawning.
* Fixed logs having the tradable component on the client side.
* Fixed Scared Stiff Giant Crops not showing the correct animation.
* Fixed Acid Rain not affecting spiders and Hutch.
* Fixed Restrained Static non-looping animation.
* Fixed a crash related to Poison Birchnut Trees and the Embalming Spritz.

## March 7, 2024 - Scrappy Scavengers Beta[]

Rev. 597344 (Test)

New Beta is active with the **Scrappy Scavengers** update.

If you don't know how to access the beta you can go here.

**Things in the update:**

**Skill trees**

* The time to accumulate points for skill trees has been reduced.
* Wigfrid:
  + Fighting Words skill: the cooldown of Battle Stingers has been decreased.
  + Startling Soliloquy panic duration increased to 6 (from 4).
* Wormwood:
  + Having the Lunar Guardian I skill will buff Wormwoods equipped Bramble Husks with some Planar Defence and Damage.
  + Summoning 2 Saladmanders will give them a health boost.
  + Summoning more Lightbugs increases each bug’s light radius.
  + Photosynthesis health rate increased.
  + Full Bloom skill changed from longer bloom time to +30% bloom increase when fertilizing Wormwood.
* Willow
  + Shadow Fire-Raiser flame will focus on a single target better when just one target is available.
  + Shadow Fire-Raiser flame does 50% more damage.
  + Doubled Patch Up I and Patch Up II regen amount.
  + Lunar Fire-Raiser ability can now be directed without moving

**Two new Moon Shroom items**

* Lunar Mushhat.
* Stuffed Mystery Meat.

**Moonstorms**

* Wagstaff will find the Restrained Static Experiment faster after the Celestial Champion has been defeated at least once in the world.
* Lunar Siphonator construction requires fewer Restrained Statics and now needs some Scrap.

**Shadow Rifts**

* Miasma will now spread quicker to have a more imposing presence.
* Acid Repellent has been added to provide Acid Rain immunity for its effective duration.
* Rifts are visible on the map even through the fog of view.
* Added world setting to disable Acid Rain.
* Acid Rain will no longer happen in the Lunar Grotto nor the Archives.
* Acid Bats are very Nitre hungry and will start seeking out Nitre hoarders.
* Added Embalming Spritz, an item that can be used on plants to remove all life from them, making them unproductive, fireproof and not targetable by Deadly Brightshade infestations, essentially decorative.

**Lunar Rifts**

* Rifts are visible on the map even through the fog of view.
* Added world setting to adjust lunar hail frequency.

**Performance**

* Performance increases for bases with many items collected in a small area.
* Rot will now dissolve in the rain while on the ground.
* An upgrade item for chests has been added to let stack sizes inside of the chest get infinitely large using late game materials.

**Boats**

* Jumping on and off of boats will now have a small delay to allow for better handling. This will not affect boat to boat hopping for boat bridges.

**Celestial Orb**

* Will now have increased odds of arriving depending on the following factors.
* How friendly the Crabby Hermit is.
* How many Celestial Altars are fully built.
* Will no longer be blocked from spawning if there are too many boulders in the area.

**Odd Skeleton**

* The odds to build the correct form is now guaranteed while near both an active Ancient Gateway and a Shadow Atrium. Hammer stocks have declined as a result.

**Carpentry Station**

* Logs may be refined as stacks using the Carpentry Station.

**Slippery Ice**

* Pengull’s Ice and frozen over Ponds are now slippery. Mind your speed.

**Broken Shells**

* Increased stack size to 40.
* Shell Bells now have a 30% chance of dropping another Broken Shell when smashed.
* Shell Bell Bundle now contains 8 Shell Bells.

**Ocean rebalance:**

* Cookie Cutters now have a 25% chance of dropping another Cookie Cutter Shell.
* Rockjaw now also drops Barnacles, Flint and Rocks.
* Perl now trades an Empty Bottle for 3 Fish Foods, instead of one.
* Seedshell now does more damage.

**Junk Yard**

* New location added to the world that has searchable junk piles.
* Added W.O.B.O.T, a curious robot that can be found in Junkyard.
* Reappearance of the Werepig.

**Miscellaneous**

* Added a “Caves or no Caves” option to the flow of starting a new world.
* Containers that can go into the player’s inventory and backpack may now be opened from there directly without having the item drop to the ground.
* Crispy Skeletons found in the Dragonfly arena may now be hammered.
* The “Light” action of the Fire Staff and Fiery Pen is now secondary.

**Bug Fixes**

* Fixed clients seeing wrong stack sizes in many cases in the inventory, open containers, and crafting.
* Fixed players getting stuck on a chair when clicking to do an action away from the chair.
* Fixed players turning invisible for a frame when toggling prediction while on a chair, and when performing certain actions while on a chair.
* Fixed being able to read the Codex Umbra and stoke Ethereal Embers when not playing with Maxwell and Willow respectively.
* Fixed being able to use the Shadow Reaper when it is broken.
* \*\*\* wetness/moisture [591813]
* Fixed Skill Tree data erroneously logging issues on dedicated servers during startup.
* Fixed being unable to pick up thrown torches while mounted and using the action key.
* Fixed duplicate sound when clicking on a day in the rollback tab on the host world screen.
* Fixed Dragonfly Shrine missing from “Prototypers & Stations” and “Structures” crafting filters.
* Fixed Golden Buoy and Thorny Buoy non-looping idle animation.
* Fixed the game getting into a bad state after disabling mods on a crash screen.
* Fixed beards going invisible in some cases when slipping on ice.
* Fixed Petals and Dark Petals appearing too far down on the ground.
* Fixed some snapping animations for Clay Varg.

**Notes for Modders**

* The mod release ID for this patch is R33\_QOL\_SPRINGCLEANING.
* SetOnHealFn was added for the healer component.
* PICKUPSOUNDS table has been moved into a global table defined in constants; the old method of a reference stored on the player instance is retained for mods that used it.
* The bat prefab OnIsAcidRaining has moved into a new component acidinfusible and is fully deprecated.
* Minimap atlas handling has been changed to support two atlases from the base game; the original minimap atlas will handle all old mods. You are encouraged to use GetMinimapAtlas if you need to access the base game atlases directly as seen in bootleg. This change will be seamless in other use cases.
* Scrapbook entries typo fix “picakble” is now “pickable”.
* Fonts are now safe to use TheSim:LoadFont multiple times it is advised to add your font to the GLOBAL.FONTS table with table.insert and call AddSimPostInit(GLOBAL.LoadFonts) with the appropriate Assets table definition in modmain to have your font asset loaded.
* Pickable components changes:
* Fixed some cases where it would spawn items at the world’s origin.
* Deprecated event “pickedbyworld”. Event “picked” will now always fire and the picker parameter will tell you who did it.
* Any picker without an inventory will now spawn the loot at the inst and no longer just the world.
* A nil picker will fully prevent loot from dropping.
* We updated all existing calls to pickable:Pick to account for these.
* The pickup range now takes into account the target’s physics radius for range checks.

## February 27, 2024[]

Rev. 595714 (Release)

**Changes**

* Clay Varg and Clay Hound have been made HD to match the other Varg and Hound variants.

**Bug Fixes**

* Fixed a critical issue related to opening save folders.
* Fixed Kitcoons floating on the main menu.
* Added the Heart Spade to the Heart Collection for the item menus.
* Wurt’s Swamp Lily Gown’s collar has been adjusted.

## February 8, 2024[]

Rev. 593204 (Release)

**Changes**

* Charlie’s Racer will now firefight.

**Bug Fixes**

* Race can be retried now if it failed because there were no boats in the start circle.
* Wilson’s Masquerader Beard has its colour corrected to match Wilson’s hair like his other skins.
* Fixed a crash with the Dragonfly Boat and Whirlportals.
* Fixed a crash when attacking the Thorny Buoy.
* Fixed a typo with Wilson’s Masquerader Beard.
* Removed some stray pixels from Nouveau Tent.

## February 8, 2024 - Year of the Dragonfly[]

Rev. 593060 (Release)

**The Year of the Dragonfly Event is Now Available!**

It's Lunar New Year, and we're off to the races! You'd better quit dragon your feet and climb aboard a decked-out Dragonfly boat if you want a shot at winning gold!

**NOTE:** This is delayed on Xbox and PlayStation until early next week, hopefully Monday. We apologize for the inconvenience.

**Highlights**

* Make offers to the Dragonfly Shrine to start building Dragon Boat Races.
* Compete against friends or Charlie's shadow agent in daring high seas competitions of speed and maneuverability.
* Construct longer and/or more elaborate checkpoint configurations to really test your mettle.

**New Skins!**

*Masquerader Survivors Chest, Part II* ($8.99usd)

“This Masquerader Chest contains skins for: Wanda, Wigfrid, Wurt, Maxwell, and Wilson.”

**Twitch Drops!**

The Nouveau Tent joins the Art Nouveau collection. Check out the post for more details

**And Away We Go!**

That's it for now. For those of you who have read the 2024 Roadmap, we want to thank you for your kind words and support. We're all very optimistic that this is going to be a great year and we welcome more of your feedback, keep it coming! If you haven't seen the 2024 roadmap yet, check it out here.

We know you're all very eager for more information about the next update and we will have information for you soon.

We'll see you next time.

**Change List:**

**New Content**

* Year of the Dragonfly Boat Race Event

**Bug Fixes**

* Fixed Frogs standing still in the day.
* Fixed Spoiled Fish ending up with fractions of an item under certain hammer conditions.

**Notes for Modders**

* Burnable component IsControlledBurn will now return true or false instead of the underlying table.

## January 19, 2024[]

Rev. 590113 (Release)

**Bug Fixes**

* Fixed Grass Gator’s shallow water finding logic.
* Fixed player’s face going invisible when equipping a Brightshade Helm during Enlightened Crown’s sanity threshold state change animation.
* Fixed a bug where more than one Deerclops can be spawned by using a Hostile Flare while another Deerclops Corpse is about to be mutated.
* Fixed Bright-Eyed Frogs holding still in the night.
* Fixed Pearl giving out rewards for players repeatedly for cleaning the ocean and it is now a one time quest.
* Fixed a crash related to Wes and chairs.

## January 12, 2024[]

Rev. 588693 (Release)

**Changes**

* Controllers will now focus the nearest Crab King claw if there are multiple claws having split focus.
* You will no longer get right-click actions for “Examine” or “Walk To” when you have a right-click weapon ability available (e.g. Elding Spear’s Lightning Strike).
* Willow’s Flame Cast ability will now activate Burning Bernie.

**Bug Fixes**

* Fixed ice boats catching on fire.
* Fixed floating fx not eroding when its owner erodes.
* Fixed some crashes and desyncs related to teleporting off of boats.
* Fixed BERNIE! attacking players when hit with Brightshade Bombs.
* Fixed some art bugs with BERNIE!
* Willow’s Shadow Fire will no longer target non-hostile shadow creatures.
* Fixed range on Willow’s Lighter sfx when it stops absorbing fire.
* Fixed range on Varg’s sleeping sfx.
* Fixed bug where you would attack with a Howlitzer while mounted even when it has no ammo.
* Fixed bug where Immaculate Lamb Idol may drop an extra Charcoal every time the world is loaded.
* Fixed double-click not working in the Browse Games screen.
* Fixed auto-repeat crafting not working for pinned recipe tabs.
* Fixed an integrated backpack UI crash sometimes when crafting out of a container.
* Fixed Willow being able to unlock multiple skills bypassing the skill tree locks.
* Fixed Willow’s Masquerade skin having red teeth when yawning.
* Fixed Fused Shadelings leaving behind entities on death.
* Fixed typo for receive in some strings.
* Fixed Wanda’s Naturalist Tunic skin not stretching wider when Wanda is old.
* Fixed Walter’s Crooked Cap skin having an overhang in the front view.
* Fixed Wigfrid’s Straw-Stuffed skin having hair stick out through some hats.
* Fixed Woodie’s Masquerade skin having hair stick out through some hats.
* Fixed art on the Ice Boats and the Ice Fishing Hole having different border colours to the Ice Island.
* Fixed Pearl starting two timers when sitting on a chair.

**Notes for Modders**

* Fixed SetPreventUnequipping not setting the netvar correctly when it was false.
* Fixed a node name in Pearl’s brain for “stop umbrella” which is now properly “stop coat”.