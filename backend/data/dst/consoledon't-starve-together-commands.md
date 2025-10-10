---
game_version: dst
category: Uncategorized
source_url: https://dontstarve.fandom.com/wiki/Console/Don't_Starve_Together_Commands
---

*Exclusive to:* Don't Starve Together

[Don't Starve](/wiki/Console/Commands "Console/Commands")
**Don't Starve Together**

|  |  |
| --- | --- |
|  | **Please note:** This page only contains console commands for *[Don't Starve Together](/wiki/Don%27t_Starve_Together "Don't Starve Together")*. For a list of console commands for the [base game](/wiki/Don%27t_Starve "Don't Starve") (or its [DLCs](/wiki/DLC "DLC")), refer to [Console Commands](/wiki/Console/Commands "Console/Commands"). |

The **Command Console** is enabled by default. If for whatever reason it is not, you first need to do some tweaking in your settings.ini of your save. This can be found in the "DoNotStarveTogether" folder, located in the following paths :

```
 Windows, Mac: <Documents>\Klei\DoNotStarveTogether\client.ini

 Linux:         ~/.klei/DoNotStarve/client.ini

```

Locate the settings.ini and open it with Notepad or other text editors. Locate the `[MISC]` section, and make sure you are having `console_enabled = true` under it. If it appears to be `false`, change and save it.

The default key to open the console in-game on English keyboards is "~". This can be changed at any time in the controls menu.

Notes:

* You have to re-enter almost every command after loading a world.
* Lua supports variable numbers of arguments to functions, so if, for example, you only want to spawn one of a prefab, you can leave out the "amount": "c\_spawn('beefalo')".
* If you are not the host (or it's a dedicated server that you're an admin for), most commands need to be run remotely. Pressing ctrl with the console open switches to remote command mode. A few commands (such as revealing the map) are still done locally.

Many [Don't Starve Commands](/wiki/Console/Commands "Console/Commands") are still available in DST, so you can also read it.

## Contents

* [1 Simple commands](#Simple_commands)
* [2 Player commands](#Player_commands)
* [3 Other Player Commands](#Other_Player_Commands)
* [4 World commands](#World_commands)
* [5 Network/Server Commands](#Network/Server_Commands)
* [6 Skill Tree Commands](#Skill_Tree_Commands)
* [7 Miscellaneous Commands](#Miscellaneous_Commands)

## Simple commands[]

**Spawn prefab**

```
c_spawn("prefab",amount)

```

*Improved DebugSpawn("prefab"), spawns amount of selected "prefab" under the mouse cursor.*

---

**Give Item**

```
c_give("prefab",amount)

```

*Spawns amount of selected "prefab" in your inventory. Only works with [Backpacks](/wiki/Backpack "Backpack") and [Items](/wiki/Items "Items") that can be stored in the inventory.*

---

**Go Adventuring**

```
c_goadventuring()

```

*Gives a set of starting items to the player.*

---

**Scenario**

```
c_doscenario(scenario)

```

*Apply a scenario script to the selection and run it.*

---

**Set** **Your** **Health**

```
c_sethea​lth(percent)

```

*Sets your health to a selected percentage. Note: Use fractional numbers 0.90 = 90%.*

---

**Set Your Sanity**

```
c_setsanit​y(percent)

```

*Sets your sanity to a selected percentage. Note: Use fractional numbers 0.90 = 90%.*

---

**Set** **Your** **Hunger**

```
c_sethunger(pe​rcent)

```

*Sets your hunger to a selected percentage. Note: Use fractional numbers 0.90 = 90%.*

---

**Set Your Moisture**

```
c_setmoisture(pe​rcent)

```

*Sets your wetness to a selected percentage. Note: Use fractional numbers 0.90 = 90%.*

---

**Temperature**

```
c_settemperature(degrees)

```

*Sets your temperature to a selected percentage. Note: Use fractional numbers 0.90 = 90%.*

---

**God Mode**

```
c_godmode()

```

*It won't drain [Sanity](/wiki/Sanity "Sanity"), [Hunger](/wiki/Hunger "Hunger") or [Health](/wiki/Health "Health") when attacked anymore. You cannot change your stats with commands while in godmode. If you are dead, revives you(Reviving via c\_godmode() doesn't put you in godmode once revived).* To deactivate God Mode use the command again.

---

**Super** **God Mode**

```
c_supergodmode()

```

*Same as God Mode but also sets all your stats to full, even when turning it off. Similar to c\_godmode(), it revives you as well(Reviving via c\_supergodmode() doesn't put you in godmode once revived).*To deactivate God Mode use the command again.

---

**Maintain Your Health**

```
c_maintainhealth(ThePlayer)

```

*Similar to supergodmode, but constantly regenerates your health. To cancel, enter c\_cancelmaintaintasks(ThePlayer).*

---

**Maintain Your Sanity**

```
c_maintainsanity(ThePlayer)

```

*Similar to supergodmode, but constantly regenerates your sanity. To cancel, enter c\_cancelmaintaintasks(ThePlayer).*

---

**Maintain Your Hunger**

```
c_maintainhunger(ThePlayer)

```

*Similar to supergodmode, but constantly regenerates your hunger. To cancel, enter c\_cancelmaintaintasks(ThePlayer).*

---

**Maintain Your Temperature**

```
c_maintaintemperature(ThePlayer)

```

*Similar to supergodmode, but constantly regenerates your temperature. To cancel, enter c\_cancelmaintaintasks(ThePlayer).*

---

**Maintain Your Moisture**

```
c_maintainmoisture(ThePlayer)

```

*Similar to supergodmode, but constantly regenerates your moisture. To cancel, enter c\_cancelmaintaintasks(ThePlayer).*

---

**Maintain All**

```
c_maintainall(ThePlayer)

```

*Similar to supergodmode, but constantly regenerates all stats. To cancel, enter c\_cancelmaintaintasks(ThePlayer).*

---

**Mob Invisibility**

```
c_makeinvisible()

```

*Prevents mobs from targeting the player in combat. Does not work in all circumstances.*

---

**Set Your Running Speed**

```
c_speedmult(multiplier)

```

*Standard bonus runspeed is 1. 2 makes you twice as fast and with 12 or more you can easily walk through walls and "over" water.*

---

**Note:** There's many other commands, but most are hard to use and not very useful.

## Player commands[]

**Note:** Most of them will not work if you are a client and not an admin sending a remote control ( switching to remote mode with Ctrl ) .

**Action Prediction**

```
ThePlayer:EnableMovementPrediction(enable)

```

*Enabled by default, setting it to "false" will exchange rubberbanding for choppiness, but is often helpful for combat. This only does anything if executed by a client, hosts do not have prediction.*

---

**Creative Mode**

```
c_freecrafting()

```

```
GetPlayer().components.builder:GiveAllRecipes()

```

*You can craft everything. Enter command again to disable creative mode.*

---

**Maximum Health**

```
ThePlayer.components.health:SetMaxHealth(value)

```

*Change the Maximum Health of your [character](/wiki/Characters "Characters")*

---

**Maximum Sanity**

```
ThePlayer.components.sanity:SetMax(value)

```

*Change the Maximum [Sanity](/wiki/Sanity "Sanity") of your [character](/wiki/Characters "Characters")*

---

**Maximum Hunger**

```
ThePlayer.components.hunger:SetMax(value)

```

*Change the Maximum [Hunger](/wiki/Hunger "Hunger") of your [character](/wiki/Characters "Characters")*

---

**Pause Hunger**

```
ThePlayer.components.hunger:Pause(true)

```

*Your [character](/wiki/Characters "Characters") won't starve anymore.*

---

**Change the** **Damage Multiplier**

```
ThePlayer.components.combat.damagemultiplier=(value)

```

*Changes the Damage Multiplier of your [character](/wiki/Characters "Characters").*

---

**Werebeaver Transformations**

```
c_setbeaverness(percentage)

```

*Turn [Woodie](/wiki/Woodie "Woodie") into the [Werebeaver](/wiki/Woodie#Curse "Woodie"). Set to 1 to transform to a [Werebeaver](/wiki/Woodie#Curse "Woodie"). Set to 0 if you want to transform back to [Woodie](/wiki/Woodie "Woodie").*

## Other Player Commands[]

**Note: Many of the following commands that are normally applied to your player, godmode or health changing commands, can be applied to other players by using c\_select(AllPlayers[number]) first. So you will have the need for a playerlist to get the player numbers:**
**List all players with username and player number.**

```
c_listallplayers()

```

This seems to not work so well anymore. If you don't get a full list try this command, it will print the player list to the chat.

```
for i, v in ipairs(AllPlayers) do TheNet:SystemMessage(tostring(i) .. ": " .. v.name, false) end

```

---

**Get a certain player**

```
AllPlayers[number]

```

*AllPlayers[1] will get ThePlayer if you are the host. Other players should have numbers as shown on the scoreboard (In certain situations, the number may be wrong. You can be more precise by using c\_listallplayers() first to see the username and character for each player number.. Most of ThePlayer commands can be used with AllPlayers[number] instead of ThePlayer.*

---

**Apply a command to all players**

```
for k,v in pairs(AllPlayers) do command end

```

*Replace command with another command, using "v" instead of AllPlayers[number]. For example, "for k,v in pairs(AllPlayers) do c\_move(v) end" will move all players to the mouse position.*

---

**Teleport another player to you**

```
c_move(AllPlayers[number])

```

*Moves the player to the cursor position.*

---

**Kill a player**

```
AllPlayers[number]:PushEvent('death')

```

*Kills the player.*

---

**Resurrect a player**

```
AllPlayers[number]:PushEvent('respawnfromghost')

```

*Resurrects the player.*

---

**Give creative mode to a player**

```
AllPlayers[number].components.builder:GiveAllRecipes()

```

*Give the creative mode to the player.*

---

**Teleport to a player**

```
c_goto(AllPlayers[number])

```

*Teleports you to the player corresponding to the player number from c\_listallplayers().*

---

**Drop a player's inventory items**

```
AllPlayers[number].components.inventory:DropEverything()

```

*Drop everything from player's inventory corresponding to the player number from c\_listallplayers()*

---

**Despawn a player's character (returns to selection screen for repicking)**

```
c_despawn(AllPlayers[number])

```

*This will delete their items, so it is recommended that you kill them first to drop their items, or let them DropEverything() as shown above.*

---

As of June 25th, 2016, a new set of commands are introduced to call players. You can call a player by their player name instead of player number UserToPlayer('PlayerName'). For example, to kill a player called 'PlayerA' with player number 5, instead of doing:

```
AllPlayers[5]:PushEvent('death')

```

*This requires you to first run c\_listallplayers() and figure out player number.*

you can simply do this:

```
UserToPlayer('PlayerA'):PushEvent('death')

```

## World commands[]

**Teleport to [Prefab](/wiki/Console/Prefab_List "Console/Prefab List")**

```
c_gonext("prefab")

```

*After pressing enter, it teleports you to the first numerical instance of the named [prefab](/wiki/Console/Prefab_List "Console/Prefab List"). If multiple iterations of the prefab exist, a list of the entity numbers will be displayed in the console log, and each subsequent execution of the same command will transport the player from entity to entity in the order they were generated in the world.*

---

**Delete Item Under Mouse**

```
ConsoleWorldEntityUnderMouse():Remove()
c_select():Remove()

```

*After pressing enter, it deletes the item under your mouse. Use the second command on dedicated servers, the first command will not work.*

---

**Delete All**

```
for k,v in pairs(Ents) do if v.prefab == "prefab" then v:Remove() end end

```

*After pressing enter, it deletes every instance of whatever item prefab is entered. Useful for long-running servers with lots of clutter.*

---

**Reveal Map - Self**

```
minimap = TheSim:FindFirstEntityWithTag("mini­map")
TheWorld.minimap.MiniMap:ShowArea (0,0,0,10000)

```

```
for x=-1600,1600,35 do
	for y=-1600,1600,35 do
		ThePlayer.player_classified.MapExplorer:RevealArea(x,0,y)
	end
end

```

*This is a local-only command and will not work if you are a client.*

---

**Reveal Map - All Players**

```
for k,v in pairs(AllPlayers) do for x=-1600,1600,35 do for y=-1600,1600,35 do v.player_classified.MapExplorer:RevealArea(x,0,y) end end end

```

*This is a remote-only command and will not work if you are a client.*

---

**Skip time units and update**

```
LongUpdate(X)

```

*Skips X time units and performs the "LongUpdate" function on world objects*

***Note**:* There are 30 time units per segment. To skip a whole day one can either use LongUpdate(480) or use multiplicative values such as LongUpdate(X\*16\*30) or LongUpdate(X\*TUNING.TOTAL\_DAY\_TIME), with X=days to skip.

---

**Speed up simulation**

```
c_speedup()

```

*Speed up the simulation of world. some events are determined by time past in game, and hence can be triggered to happen earlier through this speed up. Skipping would not bring forward those events.*

```
TheSim:SetTimeScale(X)

```

*Set the time scale of the simulation to a specific value X. The normal time scale corresponds to the value 1. The value 0 pauses the game. Note that console can not be used while game is paused preventing to remove pause.*

---

**Skip time**

```
TheWorld.net.components.clock:OnUpdate(16*30*x)

```

*Skips x days. Change x to skip more days or parts of days (e.g. 16\*30\*4.5 to skip 4.5 days)*
***WARNING**:* Too big values may freeze the game. (**Depending on computer speed**) - Note that this is here so you can manually change the rate of time skip. Use c\_skip(x) to skip x amount

---

**Skip Day**

```
c_skip(num)

```

---

**Skip to the Next Day Cycle**

```
​TheWorld:PushEvent("ms_nextcycle")

```

---

**Skip phase**

```
TheWorld:PushEvent("ms_nextphase")

```

*Skips the current Day Cycle phase.*

---

**Set phase**

```
​TheWorld:PushEvent("ms_setphase",PHASE NAME)

```

*Changes to specific Day Cycle phase. Phase names can be "day", "dusk" or "night".*

---

**Set segments**

```
TheWorld:PushEvent("ms_setclocksegs", {day=x,dusk=y,night=z})

```

*Sets amount of segments. Errors if x + y + z adds up to over 16. Note that this will get reset the next day.*  
Example:

```
TheWorld:PushEvent("ms_setclocksegs", {day=14,dusk=1,night=1})

```

*Very long day, very short dusk and night (one segment for dusk and one for night)*

---

**Skip to the Next Nightmare Cycle**

```
​TheWorld:PushEvent("ms_nextnightmarecycle")

```

---

**Skip nightmare phase**

```
TheWorld:PushEvent("ms_nextnightmarephase")

```

*Skips the current [Nightmare Cycle](/wiki/Nightmare_Cycle "Nightmare Cycle") phase.*

---

**Set nightmare phase**

```
​TheWorld:PushEvent("ms_setnightmarephase",PHASE NAME)

```

*Changes to specific Nightmare Cycle phase. Phase names can be "calm", "warn", "wild" or "dawn".*

---

**Set nightmare segments**

```
TheWorld:PushEvent("ms_setnightmaresegs", {calm=A,warn=B,wild=C,dawn=D})

```

*Sets amount of nightmare segments. A, B, C and D can be any number. This will not get reset at the next cycle.*  
Example:

```
TheWorld:PushEvent("ms_setnightmaresegs", {calm=20,warn=1,wild=1,dawn=1})

```

*Very long calm phase, very short warn, wild and dawn phase (The gane default setting is {calm=12,warn=3,wild=5,dawn=2})*

---

**Lock nightmare phase**

```
​TheWorld:PushEvent("ms_locknightmarephase",PHASE NAME)

```

*Lock at specific Nightmare Cycle phase. Phase names can be "calm", "warn", "wild" or "dawn".*

---

**Stop locking nightmare phase**

```
​TheWorld:PushEvent("ms_locknightmarephase")

```

---

**Set season segments**

```
​TheWorld:PushEvent("ms_setseasonclocksegs", {summer={day=sx,dusk=sy,night=sz}, winter={day=wx,dusk=wy,night=wz}})

```

*Sets amount of segments. Errors if x + y + z adds up to over 16. Unlike setclocksegs, this is permanent.*  
Example:

```
TheWorld:PushEvent("ms_setseasonclocksegs", {summer={day=14,dusk=1,night=1}, winter={day=13,dusk=1,night=2}})

```

*Very long day, very short dusk and night (one segment for dusk and one for night), with a slightly longer night in winter.*

---

**Set season lengths**

```
TheWorld:PushEvent("ms_setseasonlength", {season="summer", length=15})

```

*Sets the lengths of the seasons.*

---

**Start season**

```
TheWorld:PushEvent("ms_setseason", "winter")
TheWorld:PushEvent("ms_setseason", "spring")
TheWorld:PushEvent("ms_setseason", "summer")
TheWorld:PushEvent("ms_setseason", "autumn")

```

---

**Start Rain**

```
TheWorld:PushEvent("ms_forceprecipitation")

```

*Start rain.*

---

**Stop Rain**

```
TheWorld:PushEvent("ms_forceprecipitation", false)

```

*Stop rain. This also includes [Frog Rain](/wiki/Frog_Rain "Frog Rain").*

---

**Do Lightning Strike**

```
TheWorld:PushEvent("ms_sendlightningstrike", ConsoleWorldPosition())

```

*Lightning strike on mouse cursor. Will hit lightning rod instead if there is one near*

---

**Meteor Strike**

```
c_spawn("shadowmeteor", 1)

```

*Meteor strike on mouse cursor. Spawns different kind of rocks randomly.*

(Warning it may crash the game)

---

**Activate all Events**

```
for k, v in pairs(SPECIAL_EVENTS) do if v ~= SPECIAL_EVENTS.NONE then local tech = TECH[k] if tech ~= nil then tech.SCIENCE = 0 end end end function IsSpecialEventActive(event) return true end

```

*Activates all [Events](/wiki/Category:Events "Category:Events") on the same time. Doesnt work if in world gen the events section is set to Auto.*

---

**Measure Distance**

```
print(math.sqrt(ThePlayer:GetDistanceSqToInst(ConsoleWorldEntityUnderMouse())))

```

*Prints the distance between player and object under mouse to the console log (displayed with Ctrl + L by default).*

## Network/Server Commands[]

**Kick/Ban a player**

```
TheNet:Kick(userid)
TheNet:Ban(userid)

```

*Note that this can be accomplished more easily through the scoreboard. The userid can be obtained from the AllPlayers table. Once you've found the number of the player you want to kick (as shown above in the [Other Player Commands section](#Other_Player_Commands)), you can use AllPlayers[#].userid.*

*Example:*

*c\_listallplayers()  
[1] (KU\_aabbccdd) Player1 <wolfgang>*

*[2] (KU\_AABBCCDD) Player2 <wolfgang>*

*if Player2 is going to be banned from the server, console command can be written as*

*TheNet:Ban(AllPlayers[2].userid)*

*or*

*TheNet:Ban("KU\_AABBCCDD")*

---

**Temporarily Ban a player**

```
TheNet:BanForTime(userid,time_in_seconds)

```

*Player can be banned for a short period of time. userid stands for the KU id of the player to be banned. Once it is obtained it can be put in this format:*

TheNet:BanForTime(*"KU\_aabbccdd"*, 120).

*This will ban the player with specified KU id for 120 seconds. This command should be entered to both servers if server has multi-level option (caves and overworld).*

---

**Connect to a server**

```
c_connect("IP address", port, "password")

```

*If connecting conventionally doesn't work, it is possible to connect directly to an IP address. By default, the port is 10999. If there is no password, you can leave that part out: c\_connect("10.0.0.8", 10999)*

---

**Reload the world**

```
c_reset()

```

*Reloads the world without saving. This command may crash your game if you are a client (unless you send it as a remote command).*

---

**Regenerate the world**

```
c_regenerateworld()

```

*Regenerates specified items in a world.*

---

**Regenerate a world shard**

```
c_regenerateshard()

```

*Regenerates specified items in a shard.*

---

**Save the server**

```
c_save()

```

*Forces the server to save immediately (servers normally autosave whenever night finishes).*

---

**Shut down the server**

```
c_shutdown( true / false)

```

*true will save the game, false will exit without saving. c\_shutdown() is the same as c\_shutdown(true).*

---

**Roll back the server**

```
c_rollback(count)

```

*Rolls back a server by the given number of saves. c\_rollback() will roll it back by one, while c\_rollback(3) will roll it back three.*

---

**Enable/Disable new player joining**

```
TheNet:SetAllowIncomingConnections( true / false )

```

*Setting it to true is the default behavior (people can join). Setting it to false prevents anyone from joining.*

---

**Make a server announcement (for dedicated server console)**

```
c_announce("announcement")

```

*This allows you to announce server shutdowns/restarts so players do not just get disconnected without warning.*

---

**Drop a player's inventory items**

```
AllPlayers[number].components.inventory:DropEverything()

```

---

**Move another player**

```
c_move(AllPlayers[number])

```

---

**Despawn a player's character (returns to selection screen for repicking)**

```
c_despawn(AllPlayers[number])

```

---

**Stop a vote**

```
c_stopvote()

```

## Skill Tree Commands[]

**Give character max Insight Points**

```
TheSkillTree:AddSkillXP(10000, "wilson")

```

*Instead of "wilson" you can write any character name you want all Insight Points for (for Maxwell use "waxwell", for Wigfrid "wathgrithr").*

---

**Give the current character the status for killing Ancient Fuelweaver**

```
TheGenericKV:SetKV("fuelweaver_killed", "1")

```

*For the case you want to unlock a skill that's locked behind this prerequisite.*

---

**Give the current character the status for killing Celestial Champion**

```
TheGenericKV:SetKV("celestialchampion_killed", "1")

```

*For the case you want to unlock a skill that's locked behind this prerequisite.*

## Miscellaneous Commands[]

**Change something's length, width, and height**

```
c_select().Transform:SetScale(x, y, z)

```

(For Mobs and Players. Mouse must be on Entity. X is Width, Y is height.)

These commands will only affect your vision. Other players will not see the changes.

This can be done on mobs, items, naturally generated things, pretty much anything.

If you want to simply make something bigger, you must make x and y the same number or they will look stretched.

**Clear the morgue**

```
ErasePersistentString("morgue")

```

*Clears the morgue. Requires closing and reopening the game for changes to be seen.*

---

**Count the number of something in the entire world, and return the value to your character**

```
ThePlayer.components.talker:Say(tostring(c_countprefabs("prefab")))

```

*Send the command using remote. This returns the total number of something in the entire world, and returns the value as though your character is talking.*

---

**Count the number of something in the world**

```
c_countprefabs("prefab")

```

*When sending the command using local, it returns the number of prefabs in your active area only. When sending the command using remote, it returns the number of prefabs in the world; the result is printed on the server console for dedicated server.*

---

**Spawn Wormhole or Cave Entrance/Exit**
Put mouse where you want your first hole:

```
worm1 = c_spawn("wormhole")

Put mouse on second hole:
worm2 = c_spawn("wormhole")

```

For caves, instead use (doesn't matter whether you do entrance or exit first):

```
worm1 = c_spawn("cave_exit")
worm2 = c_spawn("cave_entrance")

```

Create connections back and forth

```
worm1.components.teleporter.targetTeleporter = worm2
worm2.components.teleporter.targetTeleporter = worm1

```

---

**Spawn a Domesticated Beefalo**

```
function spawn_beef(tendency) local beef = c_spawn("beefalo"); beef.components.hunger:DoDelta(400); beef.components.domesticatable:DeltaTendency(tendency, 1); beef:SetTendency(); beef.components.domesticatable.domestication = 1; beef.components.domesticatable:BecomeDomesticated(); end

```

To spawn a Domesticated Beefalo after inputting the above command enter: spawn\_beef("DEFAULT")

*To spawn a Beefalo with other tendencies, replace "DEFAULT" with any of the following: "RIDER", "ORNERY", "PUDGY"*

**Caution: This command is outdated and will crash the server**

---

**Teleport Items to the player**
This command will teleport the nearest flower to player number 1

```
c_find("flower").Transform:SetPosition(AllPlayers[1]:GetPosition():Get())

```

---

**Alter player size**

```
ThePlayer.Transform:SetScale(number,number,number)

```

---

**Alter size of prefab under mouse**

```
c_select().Transform:SetScale(number,number,number)

```

---

**Alter player color/transparency**

```
ThePlayer.AnimState:SetMultColour(number,number,number,number)

```

---

**Alter color/transparency of prefab under mouse**

```
c_select().AnimState:SetMultColour(number,number,number,number)

```

---

**Makes prefab under mouse pick-up-able**
This command does usually crash the game if mods are enabled

```
c_select():AddComponent("inventoryitem")

```

*Picking up a player does cause the game to crash for the player being picked up*

---

**Change a character's body, face, hand and other**

```
for k,v in pairs(Ents) do if v.AnimState and v.AnimState:GetBuild() == "character'sname" then v.AnimState:OverrideSymbol("bodypart", "enothercharacter'sname","bodypart") end end

```

*Where "character'sname", write the name of the character whose body part you want to change. For instance: "webber", "wilson", "wx78" (if you need Maxwell, then use "waxwell", and for Wigfrid "wathgrithr")*

*Where "bodypart", write the part of the body part that you want to change. You need to enter two identical body parts into one command. The body part that you can change: "arm\_lower", "arm\_upper", "arm\_upper\_skin", "cheeks", "face", "foot", "hair", "hair\_hat", "hairpigtails", "hairfront", "hand", "headbase", "headbase\_hat", "leg", "skirt", "tail", "torso".*

*Where "enothercharacter'sname", write the name of the character whose body part you want to copy. For instance: "webber", "wilson", "wx78" (if you need Maxwell, then use "waxwell", and for Wigfrid "wathgrithr")*

---

**Fully unlock the scrapbook**

```
TheScrapbookPartitions:DebugUnlockEverything()

```