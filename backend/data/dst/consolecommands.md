---
game_version: dst
category: Uncategorized
source_url: https://dontstarve.fandom.com/wiki/Console/Commands
---

*Exclusive to:* Don't StarveReign of GiantsShipwreckedHamlet

**Don't Starve**
[Don't Starve Together](/wiki/Console/Don%27t_Starve_Together_Commands "Console/Don't Starve Together Commands")

|  |  |
| --- | --- |
|  | **Attention:** This page is for console commands from the [Base Game](/wiki/Don%27t_Starve "Don't Starve") (along with its DLCs). If you are trying to use console commands for [Don't Starve Together](/wiki/Don%27t_Starve_Together "Don't Starve Together") then please use this [Page](/wiki/Console/Don%27t_Starve_Together_Commands "Console/Don't Starve Together Commands"). |

The **Command Console** is enabled by default. If for whatever reason it is not, you first need to do some tweaking in your settings.ini of your save. This can be found in the "donotstarve" folder, located in the following paths :

```
Windows: %userprofile%\Documents\Klei\DoNotstarve\settings.ini
Mac:     <Documents>\Klei\DoNotstarve\settings.ini

Linux:         ~/.klei/DoNotStarve/settings.ini

```

Locate the settings.ini and open it with Notepad and where it says [MISC]ENABLECONSOLE = false and rewrite it as [MISC]ENABLECONSOLE = true then save the document.

Then to open the console while in game by pressing "~" by default on English keyboards. This can be changed at any time in the controls menu. Also you can hide the console menu again by pressing Ctrl + L.

You will need to run this command once to enable console commands:

```
RunS​cript("consolecommands")

```

**Note:** Like many other command-line interfaced programs, the user can **press the upper arrow key** to get access to previously entered commands, with no need to re-type them manually again. However, the player will still have to re-enter every command after re-entering a world.

## Contents

* [1 Simple commands](#Simple_commands)
* [2 Player commands](#Player_commands)
* [3 World commands](#World_commands)
* [4 Profile Commands](#Profile_Commands)
* [5 Miscellaneous Commands](#Miscellaneous_Commands)

## Simple commands[]

**Spawn prefab**

```
c_spawn("prefab", amount)

```

*Improved DebugSpawn("prefab"), spawns amount of selected "prefab" under the mouse cursor.*

---

**Give Item**

```
c_give("prefab", amount)

```

*Spawns amount of selected "prefab" in your inventory. Only works with [Backpacks](/wiki/Backpack "Backpack") and [Items](/wiki/Items "Items") that can be stored in the inventory.*

---

**Scenario (Not tested)**

```
c_doscenario(scenario)

```

*Apply a scenario script to the selection and run it.*

---

**Health**

```
c_sethea​lth(percent)

```

*Sets your health to selected percentage. Note: Use fractional numbers 0.90 = 90%.*

---

**Sanity**

```
c_setsanit​y(percent)

```

*Sets your sanity to selected percentage. Note: Use fractional numbers 0.90 = 90%.*

---

**Hunger**

```
c_sethunger(pe​rcent)

```

*Sets your hunger to selected percentage. Note: Use fractional numbers 0.90 = 90%.*

---

**God Mode**

```
c_godmode()
c_supergodmode()

```

*It won't drain [Sanity](/wiki/Sanity "Sanity"), [Hunger](/wiki/Hunger "Hunger") or [Health](/wiki/Health "Health") when attacked anymore. Note that you will need to turn it off and on again after passing through a Worm Hole, sleeping, Telepoofing, entering or exiting a cave system, eating mandrakes, dying/respawning, or using complicated commands too many times. (In the* Hamlet *DLC, entering/exiting interiors will not break it, but being transported by the BFB will.*)

---

**Save the game**

```
c_save()

```

---

**Rollback**

```
c_reset()

```

*Rollback to the last save / reloads frontend.*

---

**Reload**

```
c_reload()

```

---

**Regenerate world**

```
c_regeneratecurrentworld()
c_regenerateallworlds()

```

*Regenerates current or all worlds in the saveslot.*

---

**Regenerate cave**

```
c_regeneratecave()

```

*Hover the cave entrance to Regenerate cave.*

---

**Set running speed**

```
c_speed(value)

```

*Standard runspeed is 1. 10 makes the player a bit faster and with 40 they can walk through walls and "over" water.*

---

**Set running speed (Don't Starve Together)**

```
c_speedmult(multiplier)

```

Does the same before but for Don't Starve Together.

---

**Note:** There's some other commands, but they're hard to use and not very useful.

## Player commands[]

**Hide and show HUD**

```
​GetPlayer().HUD:Hide()
GetPlayer().HUD:Show()​

```

*Hide or show (after hiding) the full HUD of the game.*

---

**Creative mode**

```
c_freecrafting()
GetPlayer().components.builder:GiveAllRecipes()​

```

*You can craft everything for free. This includes items you never crafted before. After switching levels, you will need to re-enter the command.*

---

**Maximum health**

```
​​GetPlayer().components.health:SetMaxHealth(value)​

```

*Change the Maximum [Health](/wiki/Health "Health") of your [Characters](/wiki/Characters "Characters")*

---

**Maximum sanity**

```
​GetPlayer().components.sanity:SetMax(value)​

```

*Change the Maximum [Sanity](/wiki/Sanity "Sanity") of your [Characters](/wiki/Characters "Characters")*

---

**Maximum hunger**

```
​GetPlayer().components.hunger:SetMax(value)​

```

*Change the Maximum [Hunger](/wiki/Hunger "Hunger") of your [Characters](/wiki/Characters "Characters")*

---

**Pause hunger**

```
​GetPlayer().components.hunger:Pause(true)​

```

*Your [Characters](/wiki/Characters "Characters") won't starve anymore.*

---

**Werebeaver**

```
​GetPlayer().components.beaverness:SetPercent(1)​

```

*Turn [Woodie](/wiki/Woodie "Woodie") into the [Werebeaver](/wiki/Woodie#Curse "Woodie").*

## World commands[]

**Teleport to Prefab**

```
​c_gonext("prefab")​
c_warp("prefab")

```

*After pressing enter, it teleports you to the first numerical instance of the named prefab. If multiple iterations of the prefab exist, a list of the entity numbers will be displayed in the console log, and each subsequent execution of the same command will transport the player from entity to entity in the order they were generated in the world.*

---

**Delete Entity Under Mouse**

```
​TheInput:GetWorldEntityUnderMouse():Remove()​

```

*After pressing enter, it deletes the item under your mouse*

---

**Remove all of one prefab**

```
c_removeall("prefab")

```

---

**Count prefabs**

```
c_countprefabs("prefab")

```

---

**Count all prefabs**

```
c_countallprefabs("prefab")

```

---

**Reveal Map**

```
GetWorld().minimap.MiniMap:ShowArea(0,0,0,10000)

```

```
c_revealmap() 

```

*Note: the map will return to normal after exiting the game or changing maps, but any new areas will remain explored.*

---

**Skip day**

```
​GetClock():MakeNextDay()

```

---

**Reset the clock back to day 1**

```
GetClock():Reset()

```

*Skips the current day.*

---

**Skip more days**

```
 ​c_skipdays(x)

```

```
 ​c_skip(x)

```

Skips x days.

***WARNING**:* Too big values may freeze the game. (**Depending on computer speed**)

---

**Skip time units and update**

```
​LongUpdate(X)​

```

*Skips X time units and performs the "LongUpdate" function on world objects*

***Note**:* There are 30 time units per segment. To skip a whole day one can either use LongUpdate(480) or use multiplicative values such as LongUpdate(X\*16\*30) or LongUpdate(X\*TUNING.TOTAL\_DAY\_TIME), with X=days to skip.

---

**Set segments**
Usage:

```
​GetClock():SetSegs(day,dusk,night)

```

*Sets amount of segments. Errors if adds up to over 16.*
Example:

```
​GetClock():SetSegs(14,1,1)

```

*Very long day, very short dusk and night (one segment for dusk and one for night)*

---

**Skip phase**

```
​GetClock():NextPhase()​

```

*Skips the current phase.*

---

**Start Summer**

```
​GetSeasonManager():StartSummer()​

```

*Start summer*

---

**Start Winter**

```
​GetSeasonManager():StartWinter()​

```

*Start winter*

---

**Start Autumn** Reign of Giants

```
GetSeasonManager():StartAutumn()

```

*Start autumn*

---

**Start Spring** Reign of Giants

```
GetSeasonManager():StartSpring()

```

*Start spring*

---

**Start Mild Season** Shipwrecked

```
GetSeasonManager():StartMild()

```

*Start mild season*

---

**Start Hurricane Season** Shipwrecked

```
GetSeasonManager():StartWet()

```

*Start hurricane season*

---

**Start Monsoon Season** Shipwrecked

```
GetSeasonManager():StartGreen()

```

*Start monsoon season*

---

**Start Dry Season** Shipwrecked

```
GetSeasonManager():StartDry()

```

*Start dry season*

---

**Start Temperate Season** Hamlet

```
GetSeasonManager():StartTemperate()

```

*Start Temperate season*

---

**Start Humid Season** Hamlet

```
GetSeasonManager():StartHumid()

```

*Start Humid season*

---

**Start Lush Season** Hamlet

```
GetSeasonManager():StartLush()

```

*Start Lush season*

---

**Start Aporkalypse** Hamlet

```
GetSeasonManager():StartAporkalypse()
GetAporkalypse():BeginAporkalypse()

```

*Start Aporkalypse*

---

**Stop Aporkalypse** Hamlet

```
GetAporkalypse():EndAporkalypse()

```

*Stop Aporkalypse*

---

**Start Pig Fiesta** Hamlet

```
GetAporkalypse():BeginFiesta()

```

*Start Pig Fiesta*

---

**Stop Pig Fiesta** Hamlet

```
GetAporkalypse():EndFiesta()

```

*Stop Pig Fiesta*

---

**Start Rain**

```
​GetSeasonManager():StartPrecip()​

```

*Start rain*

---

**Stop Rain**

```
​GetSeasonManager():StopPrecip()​

```

*Stop rain*

---

**Do Lightning Strike**

```
​GetSeasonManager():DoLightningStrike(Vector3(GetPlayer().Transform:GetWorldPosition()))​

```

*Lightning strike on player. Will hit lightning rod instead if there is one near*

---

**Do Volcano Eruption** Shipwrecked

```
GetVolcanoManager():StartEruption(smokeduration,ashduration,firerainduration,firerockspersecond)

```

*Do a volcano eruption. Note that the fire rain duration is measured in seconds.*

---

**Measure Distance**

```
​print(math.sqrt(GetPlayer():GetDistanceSqToInst(TheInput:GetWorldEntityUnderMouse())))​

```

*Prints the distance between player and object under mouse to the console log (displayed with Ctrl + L by default).*

## Profile Commands[]

While profile commands are permanent and do not need to be done each time, they do need to be performed while in a game.  You can unlock multiple characters at a time without requiring the line including the save until the very last step.

**Change Character**

```
c_swapcharacter(character)

```

*Changes the current Character to the one entered in the command (prefab name, i.e. for Maxwell it's waxwell). It's required to save and quit the game for the change to take effect.*

* Unlock [Willow](/wiki/Willow "Willow")

```
Profile:UnlockCharacter("willow")
Profile:Save()

```

*Unlocks Willow.  Does not need to be re-entered after the first time unless you lose your profile settings.*

* Unlock [Wolfgang](/wiki/Wolfgang "Wolfgang")

```
Profile:UnlockCharacter("wolfgang")
Profile:Save()

```

*Unlocks Wolfgang.  Does not need to be re-entered after the first time unless you lose your profile settings.*

* Unlock [Wendy](/wiki/Wendy "Wendy")

```
Profile:UnlockCharacter("wendy")
Profile:Save()

```

*Unlocks Wendy.  Does not need to be re-entered after the first time unless you lose your profile settings.*

* Unlock [WX-78](/wiki/WX-78 "WX-78")

```
Profile:UnlockCharacter("wx78")
Profile:Save()

```

*Unlocks WX-78.  Does not need to be re-entered after the first time unless you lose your profile settings.*

* Unlock [Wickerbottom](/wiki/Wickerbottom "Wickerbottom")

```
Profile:UnlockCharacter("wickerbottom")
Profile:Save()

```

*Unlocks Wickerbottom.  Does not need to be re-entered after the first time unless you lose your profile settings.*

* Unlock [Woodie](/wiki/Woodie "Woodie")

```
Profile:UnlockCharacter("woodie")
Profile:Save()

```

*Unlocks Woodie.  Does not need to be re-entered after the first time unless you lose your profile settings.*

* Unlock [Wes](/wiki/Wes "Wes")

```
Profile:UnlockCharacter("wes")
Profile:Save()

```

*Unlocks Wes.  Does not need to be re-entered after the first time unless you lose your profile settings.*

* Unlock [Maxwell (character)](/wiki/Maxwell_(character) "Maxwell (character)")

```
Profile:UnlockCharacter("waxwell")
Profile:Save()

```

*Unlocks Maxwell.  Does not need to be re-entered after the first time unless you lose your profile settings.*

* Unlock [Wagstaff](/wiki/Wagstaff "Wagstaff")

```
Profile:UnlockCharacter("wagstaff")
Profile:Save()

```

*Unlocks Wagstaff.  Does not need to be re-entered after the first time unless you lose your profile settings. Additionally, does not need to be used unless manually locked, as Wagstaff is automatically unlocked.*

* Unlock [Wigfrid](/wiki/Wigfrid "Wigfrid") Reign of Giants

```
Profile:UnlockCharacter("wathgrithr")
Profile:Save()

```

*Unlocks Wigfrid.  Does not need to be re-entered after the first time unless you lose your profile settings.*

* Unlock [Webber](/wiki/Webber "Webber") Reign of Giants

```
Profile:UnlockCharacter("webber")
Profile:Save()

```

*Unlocks Webber.  Does not need to be re-entered after the first time unless you lose your profile settings.*

* Unlock [Walani](/wiki/Walani "Walani") Shipwrecked

```
Profile:UnlockCharacter("walani")
Profile:Save()

```

*Unlocks Walani.  Does not need to be re-entered after the first time unless you lose your profile settings.*

* Unlock [Warly](/wiki/Warly "Warly") Shipwrecked

```
Profile:UnlockCharacter("warly")
Profile:Save()

```

*Unlocks Warly.  Does not need to be re-entered after the first time unless you lose your profile settings.*

* Unlock [Wilbur](/wiki/Wilbur "Wilbur") Shipwrecked

```
Profile:UnlockCharacter("wilbur")
Profile:Save()

```

*Unlocks Wilbur.  Does not need to be re-entered after the first time unless you lose your profile settings.*

* Unlock [Woodlegs](/wiki/Woodlegs "Woodlegs") Shipwrecked

```
Profile:UnlockCharacter("woodlegs")
Profile:Save()

```

*Unlocks Woodlegs.  Does not need to be re-entered after the first time unless you lose your profile settings.*

* Unlock [Warbucks](/wiki/Warbucks "Warbucks") Hamlet

```
Profile:UnlockCharacter("warbucks")
Profile:Save()

```

*Unlocks Warbucks.  Does not need to be re-entered after the first time unless you lose your profile settings; however, he will need to be removed from the retired character list every time you want to make a new world with him.*

* Unlock [Wilba](/wiki/Wilba "Wilba") Hamlet

```
Profile:UnlockCharacter("wilba")
Profile:Save()

```

*Unlocks Wilba.  Does not need to be re-entered after the first time unless you lose your profile settings.*

* Unlock [Wormwood](/wiki/Wormwood "Wormwood") Hamlet

```
Profile:UnlockCharacter("wormwood")
Profile:Save()

```

*Unlocks Wormwood.  Does not need to be re-entered after the first time unless you lose your profile settings. Additionally, does not need to be used unless manually locked, as Wormwood is automatically unlocked with the [Hamlet](/wiki/Don%27t_Starve:_Hamlet "Don't Starve: Hamlet") DLC installed.*

* Unlock [Wheeler](/wiki/Wheeler "Wheeler") Hamlet

```
Profile:UnlockCharacter("wheeler")
Profile:Save()

```

*Unlocks Wheeler.  Does not need to be re-entered after the first time unless you lose your profile settings.*

* Unlock Everything (Currently all characters)

```
Profile:UnlockEverything()

```

## Miscellaneous Commands[]

**Clear the morgue**

```
​ErasePersistentString("morgue")​

```

*Clears the morgue. Requires closing and reopening the game for changes to be seen.*

---

**Re-lock any unlockable character**

```
Profile.persistdata.unlocked_characters["character"] = false
Profile:Save()

```

*Re-locks any unlockable character that was entered in the command (prefab name, i.e. for Maxwell it's waxwell). Cannot lock [Wilson](/wiki/Wilson "Wilson"), but will lock [Wagstaff](/wiki/Wagstaff "Wagstaff") and [Wormwood](/wiki/Wormwood "Wormwood"). Replacing "false" with "true" will unlock characters, however the above section provides a simpler command.*

---

**Re-lock all unlockable characters**

```
ErasePersistentString("profile")

```

*Re-locks all unlockable players, such as Willow, Wes, Webber, Walani, etc. Requires closing and reopening the game for changes to be seen.*

---

**Empty retired character list**

```
RETIRED_CHARACTERLIST = {}

```

*Unless the list has already been edited, this will only make [Warbucks](/wiki/Warbucks "Warbucks") available; if the list has been edited, then the characters in the retired character list will become available again. Note that when the game is closed, all edits to the retired character list will be lost, and the command must be used again to repeat the prior results.*

---

**Edit the retired character list**

```
RETIRED_CHARACTERLIST = {"willow"}

```

*This will remove [Willow](/wiki/Willow "Willow") from the character selection, and due to not being listed will make [Warbucks](/wiki/Warbucks "Warbucks") available. This command cannot be used to hide [Wilson](/wiki/Wilson "Wilson"), but can hide [Wagstaff](/wiki/Wagstaff "Wagstaff") and [Wormwood](/wiki/Wormwood "Wormwood"). Note that when the game is closed, all edits to the retired character list will be lost, and the command must be used again to repeat the prior results.*

```
RETIRED_CHARACTERLIST = {"willow", "warbucks"}

```

*This will remove [Willow](/wiki/Willow "Willow") from the character selection, while leaving [Warbucks](/wiki/Warbucks "Warbucks") still hidden. This can also be used for other pairs, triples, etc. of characters, regardless of whether Warbucks is included or not. This command cannot be used to hide [Wilson](/wiki/Wilson "Wilson"), but can hide [Wagstaff](/wiki/Wagstaff "Wagstaff") and [Wormwood](/wiki/Wormwood "Wormwood"). Note that when the game is closed, all edits to the retired character list will be lost, and the command must be used again to repeat the prior results.*