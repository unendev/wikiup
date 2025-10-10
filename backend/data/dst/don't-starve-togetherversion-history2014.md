---
game_version: dst
category: Uncategorized
source_url: https://dontstarve.fandom.com/wiki/Don't_Starve_Together/Version_History/2014
---

[Overview](/wiki/Don%27t_Starve_Together/Version_History "Don't Starve Together/Version History")  
**2014**
[2015](/wiki/Don%27t_Starve_Together/Version_History/2015 "Don't Starve Together/Version History/2015")
[2016](/wiki/Don%27t_Starve_Together/Version_History/2016 "Don't Starve Together/Version History/2016")
[2017](/wiki/Don%27t_Starve_Together/Version_History/2017 "Don't Starve Together/Version History/2017")
[2018](/wiki/Don%27t_Starve_Together/Version_History/2018 "Don't Starve Together/Version History/2018")
[2019](/wiki/Don%27t_Starve_Together/Version_History/2019 "Don't Starve Together/Version History/2019")
[2020](/wiki/Don%27t_Starve_Together/Version_History/2020 "Don't Starve Together/Version History/2020")
[2021](/wiki/Don%27t_Starve_Together/Version_History/2021 "Don't Starve Together/Version History/2021")
[2022](/wiki/Don%27t_Starve_Together/Version_History/2022 "Don't Starve Together/Version History/2022")
[2023](/wiki/Don%27t_Starve_Together/Version_History/2023 "Don't Starve Together/Version History/2023")
[2024](/wiki/Don%27t_Starve_Together/Version_History/2024 "Don't Starve Together/Version History/2024")

## Contents

* [1 **December 24, 2014**](#December_24,_2014)
* [2 **December 23, 2014**](#December_23,_2014)
* [3 **December 17, 2014**](#December_17,_2014)
* [4 **December 16, 2014**](#December_16,_2014)
* [5 Closed Beta (since October 3, 2014)](#Closed_Beta_(since_October_3,_2014))
* [6 **December 14, 2014**](#December_14,_2014)
* [7 **December 12, 2014**](#December_12,_2014)
* [8 **December 11, 2014**](#December_11,_2014)
* [9 **December 10, 2014**](#December_10,_2014)
* [10 **December 9, 2014**](#December_9,_2014)
* [11 **December 5, 2014**](#December_5,_2014)
* [12 **December 4, 2014**](#December_4,_2014)
* [13 **December 3, 2014**](#December_3,_2014)
* [14 **December 2, 2014 - Stuck in the Middle with Ewe**](#December_2,_2014_-_Stuck_in_the_Middle_with_Ewe)
* [15 **November 28, 2014**](#November_28,_2014)
* [16 **November 26, 2014**](#November_26,_2014)
* [17 **November 24, 2014**](#November_24,_2014)
* [18 **November 21, 2014**](#November_21,_2014)
* [19 **November 17, 2014**](#November_17,_2014)
* [20 **November 7, 2014**](#November_7,_2014)
* [21 **November 6, 2014**](#November_6,_2014)
* [22 **November 5, 2014 - ...In with the New**](#November_5,_2014_-_...In_with_the_New)
* [23 **October 28, 2014**](#October_28,_2014)
* [24 **October 23, 2014**](#October_23,_2014)
* [25 **October 21, 2014**](#October_21,_2014)
* [26 **October 17, 2014**](#October_17,_2014)
* [27 **October 8, 2014**](#October_8,_2014)
* [28 **October 7, 2014**](#October_7,_2014)

## **December 24, 2014**[]

Rev. 122029

* Fixed wall pathfinding on clients
* Shadow Skittish hallucinations will appear properly for clients now
* Resolution switching fixed in full screen mode.

## **December 23, 2014**[]

Rev. 121979

**Changes**

* People you have played with now appear in the Steam view players feature.
* Added a color cube effect for player ghosts.
* Game mode data has now been refactored into gamemodes.lua.
* Game modes can be added from mods via AddGameMode.

**Bug Fixes**

* Fixed bug where lightning flashes sometimes causes a permanently darkened screen
* Creepy Eyes and Shadow Watcher hallucinations will appear properly for clients now
* Maxwell Statue will appear properly for clients now
* Fixed crash when disabling mods that was caused by frontend assets not being reloaded. Future mods should refrain from overriding assets in the global prefab as they are unable to be unloaded when disabling mods.

## **December 17, 2014**[]

Rev. 121105

**Changes**

* Notify users if they are running an unsupported version
* Reduced update frequency to the listing server

**Bug Fixes**

* Fixed a bug where some players were being assigned black or transparent color for their user name
* Removed unused\_index code from client table
* Fixed blocking Curl requests

## **December 16, 2014**[]

Rev. 120707

**Changes**

* Customization option for alternate beasts
* Removed resurrection items and recipes from Wilderness game mode

**Bug Fixes**

* Fixed bug where bush hat sometimes cancels immediately after it is activated
* Fixed bug where spiders sometimes fail to investigate in a timely fashion when a player walks onto creep
* Fixed memory leak in json parsing

## Closed Beta (since October 3, 2014)[]

## **December 14, 2014**[]

Rev. 120547

**Changes**

* Players can only be fed while they are idle
* Added ability to disable snapshots through settings.ini
  + [network] enable\_snapshots = false
* Speed improvements to the server listing screen:
  + No longer ping servers you can’t join
  + Populate ping field with cached ping result

## **December 12, 2014**[]

Rev. 120483

**Changes**

* Inventory animation is now shown when players give you an item
* Added a new Wilderness game mode where player ghosts are disabled and players are spawned to random locations. Players are returned to the character select screen upon death.

**Bug Fixes**

* Fixed crash that occurs when a user joins a server during shutdown
* Fixed client crash when a container is open during a world reset

Rev. 120363

**Changes**

* Vote Kick option added to Player Status screen. Only available on dedicated servers when no remote admin is present. Currently disabled by default but can be enabled by changing VOTE\_KICK\_ENABLED in constants.lua
* Permission lists (blocklist.txt, adminlist.txt) now support lines containing User ID only, without requiring empty separator sections
* Block list now supports banning players by Steam ID in addition to User ID. Steam IDs can be manually added to the end of blocklist.txt, one per line.
* Improved download speed of server listings
* World reset can be cancelled by another live player spawning in, if the countdown was started upon resuming a server (e.g. Host was a ghost)
* Eating animation is now shown when players are fed
* Players can no longer be fed while sleeping

**Bug Fixes**

* Fix text offset for “Remote:” to allow for translation
* Fixed a bug where servers would not relist after a Steam outage

## **December 11, 2014**[]

Rev. 120293

**Bug Fixes**

* Fixed issue causing a crash when failing to join a server (invalid password, wrong version, etc.)

Rev. 120265

**Changes**

* Added c\_announce console command for servers
* Saving widget is now shown on clients
* GetWorld and GetPlayer return more details about where they are being called from
* Reduced network data overhead for entity construction
* Join a friends game through Steam friends interface

**Bug Fixes**

* Fixed crash on server when a player disconnects while sleeping
* Player skeletons will now show the correct description when examined repeatedly
* More accurate spider creep testing.
* Fixed crash shutting down servers
* Fixed details not loading in server listing screen
* Fixed crash clicking server mods in server listing screen

## **December 10, 2014**[]

Rev. 120034

**Changes**

* Spiderqueen has become more fertile, to deal with the human threat.
* Ewecus focuses more on a single target.
* Hound spawning rebalance for multiplayer.

**Bug Fixes**

* Fixed disconnect on trying to auto-download more than one mod.
* Fixed client Physics errors that would sometimes cause desyncs
* Light from Miner Hat and Thermal Stone can now be seen by all clients
* Fixed weird hair sometimes when wearing hats.
* Fix crash in Spat when its target disappears
* Profile/Mute icons no longer show up for dedicated servers
* Optional adminlist.txt support added to allow remote administration of dedicated servers
* Fixed cropping issue when using the lua console in ‘execute’ mode

## **December 9, 2014**[]

Rev. 119851

**Changes**

* Mod character avatar textures and atlases are now set via images/avatars/avatar\_prefabname.xml and avatar\_prefabname.tex.
* Many creatures will send out more defenders if you attack their homes.
* Maxwell's influence can now be found in newly generated worlds.
* More creatures have beefed up health for multiplayer.
* Add separate messages for creating and resuming offline worlds.
* Reduced network data overhead.
* Abigail now stays with Wendy even after she dies.
* Abigail’s flower now displays correctly for Wendy in containers.
* Abigail’s flower will not bloom if it is held by or placed on the ground by any character other than Wendy.

**Bug Fixes**

* Armor will no longer load with greater than 100% durability.
* TheNet:LookupPlayerInstByUserID is now fault tolerant and will return nil if the player is not connected or out of range (client only).
* Survival mode will now persist after world reset (rather than reverting to endless mode)
* Server settings will now persist after world reset in survival mode
* Ewecus will be unfrozen by attacking it
* Players can no longer craft items while stuck by Ewecus
* Fixed a bug in the server selection screen that made it difficult to join a server while refreshing
* Fixed SaveIndex:Load crash when joining some servers
* Fixed bug where clients can stay in game after getting kicked, banned or dropped.
* Fixed crash when trying to join expired servers

## **December 5, 2014**[]

Rev. 119632

**Changes**

* Temporarily disabled Steam “join on friend” feature
* Fixed incompatible version filtering
* Talking no longer cancels bush hat

Rev. 119587

**Changes**

* Added warning on mutually exclusive flags in mod’s modinfo.lua.
* Mods list now displays version mismatch message when it can’t display the list.
* Reduce the size of RPC packets
* Increase health of many of the "fight" creatures.
* Copy over the Deerclops Freezing Hit from RoG.
* Players can now be Frozen.
* Fix helmet/armor absorption stacking so it's not so powerful.
* Improve stunlocking so that multiple players can't trivially pin down a target.
* Increase armor absorption, but decrease durability instead.
* Increase player base attack value back to original value.

**Bug Fixes**

* Objects and creatures with names will display properly when clients mouse over them.
* Clients can now cancel bush hat by unequipping the hat or by moving
* Players can no longer move around the world after they’ve already been disconnected.
* Fixed certain spots on the ground that were incorrectly blocking deployables and plantables.
* Hounds will not try to follow ghost players
* If Steam Overlays are disabled, VisitURL opens an external browser
* Fixed a crash in the server listing screen when refreshing
* Fixed crashes that occurred when a client joined a server and didn’t have/wasn’t able to download the required mods.

## **December 4, 2014**[]

Rev. 119400

**Changes**

* Make it so you can take only one copy of each character specific item out of the server with you (and zero copies if the character specific item is for a different character).
* Make haunted spiders always drop glands.
* Add whisper to the chat log for players within range at the time of sending.
* Adding dst\_compatible = true to a mod’s modinfo.lua file now prevents compatibility warnings from showing up when enabling a mod.
* Add support for custom mod characters to specify their avatar texture and atlas. This avatar will appear in the player status screen as well as in the target indicator. In the character prefab’s common\_postinit function, you can specify these via:
  + inst.avatar\_tex = "avatar\_charactername.tex"
  + inst.avatar\_atlas = "images/avatars/avatar\_charactername.xml"
  + inst.avatar\_ghost\_tex = "avatar\_ghost\_charactername.tex"
  + inst.avatar\_ghost\_atlas = "images/avatars/avatar\_ghost\_charactername.xml"

**Bug Fixes**

* Fix cancel tip widget strings to not assume English syntax.
* Fix for player badge crash when playing with modded characters.
* Fix LAN mode not being saved properly.
* Minor optimizations to RPC calls.

## **December 3, 2014**[]

Rev. 119235

**Changes**

* Auto-subscribe to server mods option added to options screen. Defaults to false.
* Added “<” and “>” characters to chat input
* AddAction now backwards compatible with old api.
* Items with the “irreplaceable” tag will now drop from your inventory, unless they are your own character specific items, when you disconnect.
* Adding client\_only\_mod = true to a mod’s modinfo.lua file now prevents client only mods from showing up in the server's mod list.

**Bug Fixes**

* Abigail can no longer be dispelled with one hit by players other than the owner
* Host player is no longer immune to all Abigail auras
* Tentacles will no longer target invisible entities
* Fixed ghost badge not showing up sometimes when joining a Survival game in progress
* Fixed crash sometimes when hammering an occupied bird cage
* Fixed hard crash when refreshing server listings
* Fixed issue causing long running servers to disappear from the server listing
* Fixed crash sometimes when loading a saved game

## **December 2, 2014 - Stuck in the Middle with Ewe**[]

 https://static.wikia.nocookie.net/dont-starve-game/images/6/6c/Ewecus.gif/revision/latest?cb=20141203222205 



 

Rev. ?

**New Stuff**

* Added a timer to Survival Mode that starts when all players are dead. A new world will generate with the same settings when it expires. Survival Mode differs from Endless Mode, where in the latter the world never resets.
* Added a [Jury-Rigged Portal](/wiki/Jury-Rigged_Portal "Jury-Rigged Portal").
* Added the [Ewecus](/wiki/Ewecus "Ewecus").
* Added the [Varg](/wiki/Varg "Varg").
* Added Wendy as a fully supported character.
* Added the ability to search for servers.
* Added server tags.
* Added the ability to only play with people from one's Steam Friend's list.
* Added support for LAN/Offline Mode.
* Steam Workshop was enabled.
* Added Automatic Mod Handling.
* Added Mod Uploader.

**Changes**

* Items with the “nonpotatoable” tag (i.e. things that can’t be taken through the [Teleportato](/wiki/Teleportato "Teleportato")) will now drop from a player's inventory when they disconnect.
* Telltale Heart recipe, health penalty, and description changed.
* [WX-78](/wiki/WX-78 "WX-78") will now lose its upgrades on death and drop a random number of [gears](/wiki/Gears "Gears") based on its level.
* Server name is now limited to 80 characters.
* Account creation now uses the embedded Steam overlay browser.
* Reduce network data for sound events.
* Log file will now display more info when dumping a stack trace.
* AddAction and AddComponentAction changed to work with multiple mods.
* Game now pauses on a Lua assert.
* Removed LuaSocket (for mod safety).
* Improved sandboxing (for mod safety).

**Bug Fixes**

* Resurrection health penalties are now saved correctly.
* Fires started by Willow’s low sanity can now be seen by all clients.
* Announcements now use the correct player colors.
* Fixed inconsistent behavior when spamming attack controls.
* Fixed occasional hang on world load when it is raining.
* Fixed LAN characters sometimes not resuming properly.
* Unjoinable servers will no longer appear in the server list.
* Fixed various components not going to sleep when their entity goes to sleep.
* Optimized networking code sleepcheck.
* All “logging in” strings moved to strings.lua.
* Fixed issue with crashes that occurred in coroutines not being correctly handled.
* Fixed issue where hound wave could start immediately if the world was started and ticked without any player spawned.
* Fixed sanity animations on server.

Rev. 119113

* Fixed Abigail crash on dedicated servers
* Fixed a bug that would prevent players from joining dedicated servers with only one free slot
* Fixed a crash in penguin herd

## **November 28, 2014**[]

Rev. 118446

* Fixed an issue causing Don’t Starve Together to occasionally crash when loading into a world.

## **November 26, 2014**[]

Rev. 118152

**Changes**

* The “Manage Bans” screen has become the Server Administration screen
* Added automatic snapshotting of world state based on the daily autosave. The interval between snapshots (in days) and maximum number of simultaneous snapshots can be configured per world. Snapshots can be managed in the Server Administration screen.

**Bug Fixes**

* Opening the pause menu, map, or chat bar no longer cancels movement
* Players can no longer feed or give items to ghosts (except for the Telltale Heart)

## **November 24, 2014**[]

Rev. 117876

* Fixed issue where player profiles did not correctly load
* Fixed issue where server listing may not refresh correctly
* Fixed crash sometimes when haunting
* Fixed combat timing bugs

## **November 21, 2014**[]

Rev. 117660

**Changes**

* Renamed “Ban List” to “Manage Bans”

**Bug Fixes**

* Fixed physics bug when jumping out of wormholes
* Fixed slow camera shake from distant meteor showers
* Fixed various traps to not assert on dedicated servers and to act on the correct player on non dedicated servers.

Rev. 117414

**Changes**

* Client movement prediction for player ghosts
* Clients now have support for camera shakes
* Improve meteor shower tuning (less stuff will accrue over time, which will reduce performance issues)
* Add support for mod chars in playerbadge and targetindicator
* Optimized network packet size
* Player bans are now persistent rather than being cleared on shutdown. They can be managed through a new screen accessible from the server hosting screen, or by editing blocklist.txt (in the save folder) directly.

**Bug Fixes**

* Fix bug where players will not stop sliding if they were revived while moving
* Fix bug where clients may rubberband if the pause menu, map, or chat bar is opened while the player is moving
* Fix missing name on some boulders spawned by meteors
* Fix server listing refresh button not graying out properly
* Fix assert when trying to spawn pengulls after a player left

## **November 17, 2014**[]

Rev. 116842

**Changes**

* Feeding is now fully supported on clients
* Feeding on PVP-disabled servers is restricted to non-harmful foods (health and sanity)
* Optimized network serialization and deserialization
* Reduced number of network packets being sent as well as overhead per packet
* Add meteor spawners (burnt mark icon: density in the world of meteor showers) and meteor showers (meteor icon: frequency of actual showers) to the world gen customization options
* Meteors of different sizes now do different amounts of work to entities near impact point
* Clients can now interrupt their emote animations by moving or performing another action
* Disabled Steam Cloud because it caused the game not to save properly. This may cause your saved worlds to become invisible(\*).

**Bug Fixes**

* Correct actions are now shown on mouseovers for clients
* Fixed bug where spawn point may change when a server is resumed (requires generating a new world)
* Fixed item dupe bug when disconnecting with an item on your cursor
* Fixed bug where sometimes not all items in your inventory drop upon death
* Fixed McTusk and hunting party duplication issue
* Fixed assert with members of hunting party going to sleep at unanticipated moments.
* Fixed a number of stale component references that would ultimately lead to asserts.
* Fixed some badge avatars for ghosts not adhering to naming conventions.
* Fixed a case that causes clients to sometimes get stuck in desync
* Fixed bug where lightning can hit WX-78 even when lightning rods are around

## **November 7, 2014**[]

Rev. 116129

* Fixed bug where some actions would stop responding to mouse clicks
* Fixed rare crash with Deerclops
* Reduce CPU overhead with ping requests

## **November 6, 2014**[]

Rev. 115992

**Changes**

* The minimap is now treated as a proper front end screen.
* Add support to the scoreboard screen for servers > 4 players.
* Increase max number of players to on a server to 6.
* Move the setting for max number of players for server-creation to the tuning.lua file.
* Variable is called MAX\_SERVER\_SIZE.
* Make food with negative health values only give-able (not feed-able) when PvP is off.

**Bug Fixes**

* Fixed bug in Willow’s talking animation
* Fixed clients sometimes desyncing when running against the edge of the world
* Fix crash when mods are enabled and no mods exist in the mods directory.
* Fix server details and mods list not being refreshed after user has clicked on report server or view mods.

## **November 5, 2014 - ...In with the New**[]

 https://static.wikia.nocookie.net/dont-starve-game/images/7/74/Wolfgang In With The New.gif/revision/latest?cb=20141108195514 



 

Rev. ?

**New Stuff**

* Add Wolfgang as an officially supported character.
* Add meteor showers and associated resources and craftable.
* Add slash commands (emotes).
* Add Slash Command binding. Pressing [ / ] (default) will bring up chat with a “/” already entered.
* Add ability to feed other players directly (rather than giving them food that goes into their inventory).
* Add an indication of the host’s performance to the scoreboard screen.
* Add an icon that appears in the top left when you (as a client) get desynced.
* Add ability to join friends’ games via Steam (right click on their name in your Steam Friends list and click “Join game”) while already in a game or when Don’t Starve Together isn’t open.
* Added Ban button to the scoreboard screen. Bans reset when the server restarts.
* Added main menu widget to show login progress.
* Add hover text for all details icons in the server browser.
* Add friends icon to the server browser details column (appears for games hosted by Steam Friends).
* Add filter for servers hosted by Steam Friends to the server browser screen.
* Add mods icon to the server browser details column.
* Add button to display list of mods enabled on a server to the server detail panel.
* Add a warning when joining a modded server.
* Add a list of mods enabled on the server to the bottom of the scoreboard screen.

**Changes**

* Make default world size large for all presets.
* Version mismatch between you and servers will make that server’s listing red on the server browser screen.
* Increase PvP damage to .5 of normal (up from .25).
* Increase distance between hunts that happen simultaneously.
* MODS\_ENABLED is now defined in settings.ini (which can be found in the Documents\Klei\DoNotStarveTogether folder). To enable mods, open settings.ini and delete the “#” before the “MODS\_ENABLED = true” line.
* Change the chat font to match the characters’ dialog font.
* Make the disconnected message for players who have been kicked more informative.
* Put server name in report prompt.
* Make server browser and creation screens say Back not Cancel.
* Add new animation for exiting wormholes.
* Make the default player color gray (rather than using one of the colors in the set of player colors).
* Flattened the folder architecture: the DLC0002 folder is gone.

**Bug Fixes**

* Improve combat responsiveness on clients.
* Improve bug net responsiveness on clients.
* Optimize netcode.
* Improve (local game’s) performance.
* Fix a crash that could happen with workable things getting haunted and breaking.
* Fix crash that would occur when joining incompatible servers: now handled with a could-not-connect dialog.
* Add missing characters to the (chat and character dialog) font.
* WX-78’s upgrades are no longer capped at 255 (health: 400, hunger: 200, sanity: 300).
* WX-78 now properly saves upgrades and overcharge state.
* Prevent a currently-haunted thing from doing its haunt reaction again.
* Fix client visual artifacts when digging up/placing turf.
* Fix insanity monsters flickering on client when host is insane.
* Fix erroneous hunt-failed announcement dialog.
* Fix capital and lowercase server names with the same start getting sorted separately.
* Fix animation issues that would occur sometimes after a client stops moving.
* Fix sounds sometimes playing out of sync with their animation for clients.
* Fix clients hearing double footsteps.
* Fix sound not playing on character select screen.
* Fix tallbird egg using wrong anim after being cooked.

## **October 28, 2014**[]

Rev. 114937

**Changes**

* Simple account ban messaging. Notify users who have been globally banned from Don’t Starve Together for breaking our terms of service.
* Ignore crash reports from games that are not authenticated.
* Crash reports from games running with mods now include details about the mods that were enabled.

**Bug Fixes**

* Fixed graphical glitches with fishing
* Disable revealing minimap as ghost
* Fixed crash bug at server shutdown
* Fixed minor memory leak in networking
* Fixed crash when trying to use fallback haunt shader
* Fixed hounds permanently howling and not attacking

## **October 23, 2014**[]

Rev. 114581

**Changes**

* Moved report server button to the left hand side of the server listing screen
* View Steam profiles from the player status screen
* Filter out old incompatible servers from the server listings page
* Note: You may see an empty server listing right after the patch goes live, this means no one is currently running a compatible server. We will address this in a future patch.

**Bug Fixes**

* Fix crash sometimes when two people haunt the same object
* Fix crash sometimes when haunting a workable object
* Fix crash on player status screen
* Make report button use actual server name in prompt
* Fix crash when two people use drying rack at the same time
* Fix crash when igniting explosives such as gunpowder
* Disable player controller when minimap is up
* You can now properly rotate minimap
* Fixed WX sparks sometimes showing at the wrong position
* Fix bug where clients would sometimes drop animations

## **October 21, 2014**[]

Rev. 114414

**Changes**

* Scoreboard now shows players’ correct ages
* Improved reliability of accounts and server broadcasting
* View server description in-game
* Increased minimap reveal radius

**Bug Fixes**

* Fixed map and camera button tooltips to reference their correct key bindings
* Fixed crash when shift-clicking blowdarts in containers
* Fixed bug where client actions were being incorrectly dropped by the server
* Implemented fallback to the haunt shaders for graphics cards that don’t support them
* Fixed bug where player badge is broken for some characters on the scoreboard
* Fixed target indicator to support mod characters
* Fixed bug where clients sometimes crash when the host shuts down the server
* Fixed animation bug when transitioning between player and ghost

## **October 17, 2014**[]

Rev. 114221

**Changes**

* Added a scoreboard screen.
* Shows server name and server day.
* Shows player’s characters, color, days survived and ping.
* Host can kick players.
* All players can mute other players’ chat messages (known bug: mute only mutes global chat, not chat displayed above heads).
* Set TAB key bound to show Scoreboard (hold to display). Control is bindable.
* Set M key bound to show Map display (toggle display). Control is bindable.
* Add a widget to the top left corner of the front end that displays if you’re connected to Steam.
* Display a “left game” message when someone leaves the server.
* Disable the Refresh button on the server browser screen while a search is in progress.

**Bug Fixes**

* Fix event announcer widget moving around the screen when the window gets resized.
* Fix a server creation screen crash with server data having a nil day.
* Fix a crash that would occur if you plugged a controller in after startup.
* Fix players sometimes turning their back after picking up an object.
* Fix crash from haunting Mactusk’s hounds.

## **October 8, 2014**[]

Rev. 113281

* Server passwords are now limited to alphanumeric characters
* Fix crash in inventorybar for some players loading into game

Rev. 113201

**Balance**

* Heat rock needs to go to level 4 heat before it can degrade so oscillation doesn't happen during fall

**Bug Fixes**

* Fixed hard crash when steam goes down
* Fixed melee combat range issue
* Fixed server crash when a ghost disconnects
* Fixed clock hover string at dusk
* Book crafting is now available for Wickerbottom on clients as well as on host
* All players will now receive their proper starting inventory when starting a new character (e.g. Willow’s lighter)
* Fix soundemitter crash when haunting certain objects
* Fixed server browser lua crash when changing filters with an empty list
* Fix tilde bringing up the console while in chat mode
* Removed mandrakes from build as they are currently unsupported
* Removed unsupported world sizes from generation options
* Fixed crash from picking up staff at hounds set piece
* Fixed crash from crafting a tooth trap
* Fix crash when inspecting a compass
* Fix crash when re-joining a server that has pigs with hats
* Fix crash when haunting blowdarts
* Ice and blood UI overlay fixes for client
* Disable spawning of unsupported items from generation options ( chess pieces, mandrake and caves )
* Improved stability for starting or resuming on a world with slower connections
* Fixed screen flash when spawning into a game

## **October 7, 2014**[]

Rev. 113005

* Fixed issue with too many chesters appearing on map.
* Fixed issue with characters sometimes going invisible when punching
* Fix for authentication on some systems
* Improved reliability of network data for slower connections
* Resolved crash issue when signing up for account.