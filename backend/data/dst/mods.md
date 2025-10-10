---
game_version: dst
category: Uncategorized
source_url: https://dontstarve.fandom.com/wiki/Mods
---

|  |
| --- |
| The content of this page is not supported by [**Klei Entertainment**](http://kleientertainment.com/), the **[Don't Starve Wiki](/wiki/Don%27t_Starve_Wiki "Don't Starve Wiki")**, or the [**Don't Starve Forum**](http://forums.kleientertainment.com/forumdisplay.php?20-Don-t-Starve-Beta/). |

**Mods**, or **modifications**, are alterations or additions to the original content of a video game. They may present new features, player experiences, and/or tweaks to the game, making the game more enjoyable, or less enjoyable and either easier or harder.

Mods for Don't Starve are generally developed by the Don't Starve community.

## Contents

* [1 Installation](#Installation)
  + [1.1 Simple](#Simple)
  + [1.2 Manual](#Manual)
  + [1.3 Dedicated Servers](#Dedicated_Servers)
* [2 Removal](#Removal)
* [3 Creation](#Creation)
  + [3.1 Basic API Info](#Basic_API_Info)
  + [3.2 Modding Tools](#Modding_Tools)
* [4 Language Packs](#Language_Packs)
* [5 Don't Starve Together](#Don't_Starve_Together)

## Installation[]

:   *Disclaimer: Klei is not able to help the player should issues arise while using mods. Use with caution!*

### Simple[]

The simplest way to download and install mods is to subscribe to them in the Steam Community Workshop. This will automatically download the mods into the game. The player can then enable/disable the installed mods by going into the "Mods" menu from the main menu, and ticking the mods' respective checkboxes.

* [Don't Starve Steam Workshop](https://steamcommunity.com/workshop/browse/?appid=219740)
* [Don't Starve Together Steam Workshop](https://steamcommunity.com/app/322330/workshop/)

### Manual[]

Mods can also be downloaded as files from [Klei's official website](http://forums.kleientertainment.com/files/), which must be manually placed into the appropriate "/mods/" directory of the game. The website contains downloadable files categorized into sections: *Modding Tools*, *Game Modifications*, *Language Packs*, *Custom Maps and New Characters*, etc. The same mods can usually be found from the Steam Community Workshop.

Find the *mods* folder under:

```
Windows: C:/Program Files/Steam/steamapps/common/Don't Starve/mods/
OS X: ~/Library/Application Support/steamapps/common/Don't Starve/dont_starve.app/Contents/mods/
Linux/SteamOS:  ~/.steam/steam/steamapps/common/Don't Starve/mods/

```

If your release has nothing to do with Steam, Find the *mods* folder under:

```
Windows: C:/Program Files/Don't Starve/mods/

```

Change *dont\_starve* to *dontstarve\_steam* and *Don't Starve* to *Don't Starve Together* for [Don't Starve Together](/wiki/Don%27t_Starve_Together "Don't Starve Together").
For Windows, look under `Program Files (x86)`. If you have Steam installed elsewhere, look there. For OS X, right-click on the application and click *show package contents* or use a terminal to `cd` to get in the app. `~` means your [home directory](https://en.wikipedia.org/wiki/home_directory "wikipedia:home directory").

### Dedicated Servers[]

There are alternate steps to take when installing and enabling mods on dedicated servers.

* [Don’t Starve Together Dedicated Servers#Mods](/wiki/Don%E2%80%99t_Starve_Together_Dedicated_Servers#Mods "Don’t Starve Together Dedicated Servers")

## Removal[]

If the player has installed mods incorrectly, then the best thing to do is to use the backup players have made. However, there is an alternative method if the player forget to create a backup.

If the mod installed is a broken mod from the Steam Workshop, unsubscribe from it. Tell the author about the crash and include a list of all mods in the folder.

If it comes from a zip file that has been unpacked, go to the *mods* folder mentioned above, then delete that mod folder, or move it elsewhere. In 99% of cases so far, players should only have to delete their scripts folder. Now, some of the players may realize that this is like dropping a bomb to kill a spider, but that's okay. It just guarantees that all traces of mods are removed.

Finally go to the Steam Library, Right-Click on the game and choose properties. In the tab **Local Files**, choose **verify integrity of game cache**. Steam will take care of the whole thing.

## Creation[]

Before creating new mods, players should read the [Guidelines](http://www.dontstarvegame.com/player-creation-guidelines). Players are recommended to be familiar with the [Lua](https://en.wikipedia.org/wiki/Lua_(programming_language)) language and the [TEX](/wiki/TEX "TEX") file format before creating any mods. There are also [tutorials](http://forums.kleientertainment.com/topic/28021-getting-started-guides-tutorials-and-examples/) on the forum. The API doc (see below) also has one.

### Basic API Info[]

The mod system takes two most important files, `modinfo.lua` and `modmain.lua`. The earlier contains multiple info about the mod, like the name, version, and version compatibility. The latter is like the `main()` function in other languages—that is, where the mod system invoke and loads the mod. It has an environment itself.

The `data/scripts` folder under the game's program directory contains lua code for the vanilla game itself. You can refer to them for some API info. An [unofficial API doc](http://dontstarveapi.com) is also available.

### Modding Tools[]

| Name | Author | Version | Description |
| --- | --- | --- | --- |
| [Klei Studio](http://forums.kleientertainment.com/showthread.php?12434-REL-Klei-Studio-(-TEXTool-)-Underground) | Handsome Matt | Hungry for your hunger | Edit [TEX](/wiki/TEX "TEX") and animation files of Don't Starve. |
| [Mod Library](http://forums.kleientertainment.com/showthread.php?14337-REL-Mod-Library-v1-3-END-UPDATE) | WrathOf | The End is Nigh | Provide support for modders and additional functions. |
| [Don't Starve Mod Tools](https://steamdb.info/app/245850/) | Klei Enterteinment | 1965676 | Allow Modders to Upload to the Steam Workshop |

Since the mod creator will be writing code, they may want a [text editor](https://en.wikipedia.org/wiki/text_editor "wikipedia:text editor") that supports [syntax highlighting](https://en.wikipedia.org/wiki/syntax_highlighting "wikipedia:syntax highlighting") in [Lua](https://en.wikipedia.org/wiki/Lua "wikipedia:Lua"), so they can see the code clearly. [Notepad++](https://en.wikipedia.org/wiki/Notepad%2B%2B "wikipedia:Notepad++"), [Vim](https://en.wikipedia.org/wiki/Vim "wikipedia:Vim") and [Atom](https://en.wikipedia.org/wiki/Atom_(text_editor) "wikipedia:Atom (text editor)") are often popular choices. They could also use [Integrated development environments](https://en.wikipedia.org/wiki/integrated_development_environment "wikipedia:integrated development environment") (IDE), since they may be even smarter.

## Language Packs[]

Players may download a language pack if their language is not supported by the game. Search through them [here](http://forums.kleientertainment.com/downloads.php?categoryid=2).

## Don't Starve Together icon Don't Starve Together[]

In DST, there are three kinds of mods:

* **client-only**, which are enabled by each player individually and used on any server. These usually make changes to the interface, and do not change the game for other players.
* **server-only**, which are enabled by the host of a server. Joining players to this server do not need to download the mod. Server mods usually make small changes to game mechanics, such as changing the timing of things or scaling creature stats.
* **all-clients**, which all players on a server need to have. The Steam Workshop automatically handles downloading and enabling these for players when they join a server with an all-clients mod enabled. These mods can change almost anything about the game, including custom characters and enemies.