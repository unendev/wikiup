---
game_version: dst
category: Uncategorized
source_url: https://dontstarve.fandom.com/wiki/Guides/Simple_Dedicated_Server_Setup
---

|  |  |
| --- | --- |
|  | The article may contain **outdated and inaccurate information**. You may want to help improve the content by adding updated information. |

|  |  |
| --- | --- |
|  | This article may contain **instructional** language and subjective recommendations. Readers should read the content carefully, and follow accordingly. |

This is a complicated and outdated guide to setting up a dedicated server. To make things easiest, it'll be running the dedicated server through Steam on a computer that you can already use normally (i.e. not a server that you access through command line or anything).

**Please follow this guide instead :** <https://forums.kleientertainment.com/forums/topic/64212-dedicated-server-quick-setup-guide-windows/>

## Contents

* [1 Installing](#Installing)
  + [1.1 Old Steam Client](#Old_Steam_Client)
  + [1.2 New Steam Client](#New_Steam_Client)
* [2 Running pre-configured servers](#Running_pre-configured_servers)
* [3 Configuring](#Configuring)
* [4 Port Forwarding](#Port_Forwarding)
* [5 Playing](#Playing)
* [6 Optional Steps](#Optional_Steps)
  + [6.1 World Customization](#World_Customization)
  + [6.2 Mods](#Mods)

## Installing[]

### Old Steam Client[]

* In Steam, hover over the **Library** tab and select **Tools**.
* Scroll to **Don't Starve Together Dedicated Server**, right-click and select **Install Game...**.
* Follow the steps to install like any other Steam game.

### New Steam Client[]

* Click the **Library** tab
* Click the **Game** dropdown menu on the left panel above all your games.
* Check the **Tools** Filter
* Scroll to **Don't Starve Together Dedicated Server**, right-click and select **Install Game...**.
* Follow the steps to install like any other Steam game.

## Running pre-configured servers[]

In the [Caves Are Now Live!](http://steamcommunity.com/games/322330/announcements/detail/768223740893904309) update some major changes for dedicated server networking was announced:

* Caves for **Don't Starve Together** can only be hosted on a dedicated server
* Each world layer (Overworld and every [Cave](/wiki/Caves "Caves")) must be run on a different dedicated server

But there is a good news! There is a pre-configured server for hosting caves which can be run from a steam directly.

Official, up-to-date guides:

[Hosting Caves with the Preconfigured Servers](http://forums.kleientertainment.com/topic/59171-hosting-caves-with-the-preconfigured-servers/)

[Understanding Shards and Migration Portals](http://forums.kleientertainment.com/topic/59174-understanding-shards-and-migration-portals/)

## Configuring[]

* First, right-click **Don't Starve Together Dedicated Server** in Steam and select **Properties**.
* Click **Set launch options...** at the bottom and enter this: "`-console -conf_dir DoNotStarveTogetherDedicated`". This will let you type console commands in the dedicated server window, and make it save to a different folder from the normal game.
* Right-click **Don't Starve Together Dedicated Server** and select **Play game...**. After it stops printing stuff out, close the window it makes (this lets it generate the save folder).
* Launch **Don't Starve Together** normally, and open the console with the tilde (~) key. Enter "TheNet:GenerateClusterToken()", hit enter, and close the game.
* Find the folder ~/**Documents/Klei** folder. It should now have both the **DoNotStarveTogetherDedicated** and the **DoNotStarveTogether** folders inside. Copy both **settings.ini** and **server\_token.txt** from **DoNotStarveTogether** folder into **DoNotStarveTogetherDedicated**.
* Open the copied **settings.ini** in a text editor (Notepad, TextEdit, Notepad++, etc-- not a rich text editor like Word).
* Make sure you have the following lines under "`[network]`":

```
default_server_name = A unique server name
default_server_description = A very nice server description
server_port = 10999
server_password = password
max_players = 1 .. 64
pvp = true | false
game_mode = endless | survival | wilderness
enable_snapshots = true
pause_when_empty = false
server_save_slot = 1..5

```

* Change the parts on the right to what you want. Note that `game_mode` is case-sensitive, so don't put in "Wilderness". For example, (a PvP Wilderness server with no password):

```
default_server_name = Supervillain PvP
default_server_description = Do you have what it takes?
server_port = 10998
server_password = 
max_players = 6
pvp = true
game_mode =  wilderness
enable_snapshots = true
pause_when_empty = true
server_save_slot = 1

```

* If you want to come back and change these later, make sure you do it while the dedicated server is not running. If you change it while the server is running, it'll rewrite it with the old settings when it gets closed.

## Port Forwarding[]

You might not have to port forward, but you probably will (if you need to, but haven't yet, this will make it so that nobody else can see your server in the list and connect to it; because you're on the same computer, you should be able to see it despite that). Every router is different, but the basic idea is that you have a network in your house, with IP addresses for the computers there. You have to get your computer's local IP address (look up how to do this; you'll want to include your operating system in the search, such as ["find local IP in windows 8"](http://windows7themes.net/en-us/how-to-find-your-ip-address-in-windows-8/)), and then tell your router to forward connections on the port to your computer (look up instructions for your router, such as ["asus rt-n65u port forwarding"](http://portforward.com/english/routers/port_forwarding/Asus/RT-N65U/Terraria.htm)).

## Playing[]

Now, when you want to play on the server, you can launch it through the **Library** > **Tools** section of Steam, then join it through **Don't Starve Together** normally.

## Optional Steps[]

You might want to customize your world generation settings or add mods. These both have to be done differently.

### World Customization[]

To have custom world generation settings, you need to make a text file called **worldgenoverride.lua** in your **DoNotStarveTogetherDedicated** folder. Inside this file, you can set individual settings like this:

```
return {
	override_enabled = true,
	season="shortboth", world_size="huge", season_start="summer",
	flint="never", grass="never", sapling="never", trees="never"
}

```

The `override_enabled` part determines whether custom settings are used at all. Change "true" to "false" there if you want to turn off all customization (or delete the file)-- this won't affect worlds that have already been generated, though. Here are all possible options :

```
return {
    override_enabled = true,
    preset = "SURVIVAL_TOGETHER",     -- "SURVIVAL_TOGETHER", "MOD_MISSING", "SURVIVAL_TOGETHER_CLASSIC", "SURVIVAL_DEFAULT_PLUS", "COMPLETE_DARKNESS", "DST_CAVE", "DST_CAVE_PLUS"
    overrides = {
        -- MISC
        task_set = "default",           -- "classic", "default", "cave_default"
        start_location = "default",     -- "caves", "default", "plus", "darkness"
        world_size = "default",         -- "small", "medium", "default", "huge"
        branching = "default",          -- "never", "least", "default", "most"
        loop = "default",               -- "never", "default", "always"
        specialevent = "default",       -- "none", "default", "hallowed_nights", "winters_feast", "year_of_the_gobbler"
        autumn = "default",             -- "noseason", "veryshortseason", "shortseason", "default", "longseason", "verylongseason", "random"
        winter = "default",             -- "noseason", "veryshortseason", "shortseason", "default", "longseason", "verylongseason", "random"
        spring = "default",             -- "noseason", "veryshortseason", "shortseason", "default", "longseason", "verylongseason", "random"
        summer = "default",             -- "noseason", "veryshortseason", "shortseason", "default", "longseason", "verylongseason", "random"
        season_start = "default",       -- "default", "winter", "spring", "summer", "autumnorspring", "winterorsummer", "random"
        day = "default",                -- "default", "longday", "longdusk", "longnight", "noday", "nodusk", "nonight", "onlyday", "onlydusk", "onlynight"
        weather = "default",            -- "never", "rare", "default", "often", "always"
        earthquakes = "default",        -- "never", "rare", "default", "often", "always"
        lightning = "default",          -- "never", "rare", "default", "often", "always"
        frograin = "default",           -- "never", "rare", "default", "often", "always"
        wildfires = "default",          -- "never", "rare", "default", "often", "always"
        regrowth = "default",           -- "veryslow", "slow", "default", "fast", "veryfast"
        touchstone = "default",         -- "never", "rare", "default", "often", "always"
        boons = "default",              -- "never", "rare", "default", "often", "always"
        cavelight = "default",          -- "veryslow", "slow", "default", "fast", "veryfast"
        disease_delay = "default",      -- "none", "random", "long", "default", "short"
        prefabswaps_start = "default",  -- "classic", "default", "highly random"
        petrification = "default",      -- "none", "few", "default", "many", "max"

        -- RESOURCES
        flowers = "default",            -- "never", "rare", "default", "often", "always"
        grass = "default",              -- "never", "rare", "default", "often", "always"
        sapling = "default",            -- "never", "rare", "default", "often", "always"
        marshbush = "default",          -- "never", "rare", "default", "often", "always"
        tumbleweed = "default",         -- "never", "rare", "default", "often", "always"
        reeds = "default",              -- "never", "rare", "default", "often", "always"
        trees = "default",              -- "never", "rare", "default", "often", "always"
        flint = "default",              -- "never", "rare", "default", "often", "always"
        rock = "default",               -- "never", "rare", "default", "often", "always"
        rock_ice = "default",           -- "never", "rare", "default", "often", "always"
        meteorspawner = "default",      -- "never", "rare", "default", "often", "always"
        meteorshowers = "default",      -- "never", "rare", "default", "often", "always"
        mushtree = "default",           -- "never", "rare", "default", "often", "always"
        fern = "default",               -- "never", "rare", "default", "often", "always"
        flower_cave = "default",        -- "never", "rare", "default", "often", "always"
        wormlights = "default",         -- "never", "rare", "default", "often", "always"

        -- UNPREPARED
        berrybush = "default",          -- "never", "rare", "default", "often", "always"
        carrot = "default",             -- "never", "rare", "default", "often", "always"
        mushroom = "default",           -- "never", "rare", "default", "often", "always"
        cactus = "default",             -- "never", "rare", "default", "often", "always"
        banana = "default",             -- "never", "rare", "default", "often", "always"
        lichen = "default",             -- "never", "rare", "default", "often", "always"

        -- ANIMALS
        rabbits = "default",            -- "never", "rare", "default", "often", "always"
        moles = "default",              -- "never", "rare", "default", "often", "always"
        butterfly = "default",          -- "never", "rare", "default", "often", "always"
        birds = "default",              -- "never", "rare", "default", "often", "always"
        buzzard = "default",            -- "never", "rare", "default", "often", "always"
        catcoon = "default",            -- "never", "rare", "default", "often", "always"
        perd = "default",               -- "never", "rare", "default", "often", "always"
        pigs = "default",               -- "never", "rare", "default", "often", "always"
        lightninggoat = "default",      -- "never", "rare", "default", "often", "always"
        beefalo = "default",            -- "never", "rare", "default", "often", "always"
        beefaloheat = "default",        -- "never", "rare", "default", "often", "always"
        hunt = "default",               -- "never", "rare", "default", "often", "always"
        alternatehunt = "default",      -- "never", "rare", "default", "often", "always"
        penguins = "default",           -- "never", "rare", "default", "often", "always"
        cave_ponds = "default",         -- "never", "rare", "default", "often", "always"
        ponds = "default",              -- "never", "rare", "default", "often", "always"
        bees = "default",               -- "never", "rare", "default", "often", "always"
        angrybees = "default",          -- "never", "rare", "default", "often", "always"
        tallbirds = "default",          -- "never", "rare", "default", "often", "always"
        slurper = "default",            -- "never", "rare", "default", "often", "always"
        bunnymen = "default",           -- "never", "rare", "default", "often", "always"
        slurtles = "default",           -- "never", "rare", "default", "often", "always"
        rocky = "default",              -- "never", "rare", "default", "often", "always"
        monkey = "default",             -- "never", "rare", "default", "often", "always"

        -- MONSTERS
        spiders = "default",            -- "never", "rare", "default", "often", "always"
        cave_spiders = "default",       -- "never", "rare", "default", "often", "always"
        hounds = "default",             -- "never", "rare", "default", "often", "always"
        houndmound = "default",         -- "never", "rare", "default", "often", "always"
        merm = "default",               -- "never", "rare", "default", "often", "always"
        tentacles = "default",          -- "never", "rare", "default", "often", "always"
        chess = "default",              -- "never", "rare", "default", "often", "always"
        lureplants = "default",         -- "never", "rare", "default", "often", "always"
        walrus = "default",             -- "never", "rare", "default", "often", "always"
        liefs = "default",              -- "never", "rare", "default", "often", "always"
        deciduousmonster = "default",   -- "never", "rare", "default", "often", "always"
        krampus = "default",            -- "never", "rare", "default", "often", "always"
        bearger = "default",            -- "never", "rare", "default", "often", "always"
        deerclops = "default",          -- "never", "rare", "default", "often", "always"
        goosemoose = "default",         -- "never", "rare", "default", "often", "always"
        dragonfly = "default",          -- "never", "rare", "default", "often", "always"
        antliontribute = "default",     -- "never", "rare", "default", "often", "always"
        bats = "default",               -- "never", "rare", "default", "often", "always"
        fissure = "default",            -- "never", "rare", "default", "often", "always"
        wormattacks = "default",        -- "never", "rare", "default", "often", "always"
        worms = "default",              -- "never", "rare", "default", "often", "always"
    },
}

```

### Mods[]

For mods, you need to make two files. One, **dedicated\_server\_mods\_setup.lua**, tells the dedicated server which mods it needs to download and update. The other, **modoverrides.lua**, tells it which to have active and what configuration options to use for them.

The first one needs to be created in the game files for the dedicated server. Open up the **Library** > **Tools** tab in Steam and right-click on **Don't Starve Together Dedicated Server**, select **Properties**, then go to the **Local Files** tab in the popup and click **Browse local files...**. In the window that opens, go to the **mods** folder, and inside, create the text file **dedicated\_server\_mods\_setup.lua** if it does not exist already, then open it in a text editor. For each mod you want, go to their page on the Steam Workshop and get the URL of that page (if it doesn't show at the top, you can right-click anywhere and select **Copy Page URL**). There will be a number at the end of the URL; for example, Always On Status's number is 376333686. Inside **dedicated\_server\_mods\_setup.lua**, add a line for each mod that looks like this:

```
ServerModSetup("376333686") -- Write the name of the mod here so you remember

```

The second one needs to be created in your **DoNotStarveTogetherDedicated** folder. Make the text file **modoverrides.lua** with this inside:

```
return {

}

```

For each mod you want to enable, add a line in the middle for it like this (make sure it has the comma at the end):

```
return {
    ["workshop-427002021"] = { enabled = true }, -- put the mod name here so you remember
}

```

If you want to change options for the mod, you'll have to look at that mod's files. Go back to the **mods** folder where you made **dedicated\_server\_mods\_setup.lua**. If you haven't run the dedicated server since last editing that file, run it so it downloads the mods. Then, you should have a folder like **workshop-427002021** for each mod. Look inside, and open the **modinfo.lua** file in a text editor. Usually you'll find options listed like this:

```
configuration_options =
{
    {
        name = "starting_day",
        label = "Starting day",
        hover = "After this day, this mod will start to block certain \nactions from limited players.",
        options =
        {
            {description = "10", data = 10},
            {description = "15", data = 14},
            {description = "21", data = 20},
            {description = "36", data = 35}
        },
        default = 10,
    },
    {
        name = "min_days",
        label = "Minimum player days",
        hover = "Players need to spend this amount of days to no \nlonger be limited.",
        options =
        {
            {description = "1", data = 1},
            {description = "2", data = 2},
            {description = "3", data = 3},
            {description = "5", data = 5}
        },
        default = 1,
    },
}

```

**label** shows the name for the option that you'd see on the mods page, and for each option **description** is what that choice is called, and **data** is the actual value that the mod uses for that option. Using that information, you can change the line in **modoverrides.lua** to look like this:

```
	["workshop-378160973"] = { enabled = true,
		configuration_options =
		{
			description1 = data1,
			description2 = data2,
		},
	}, -- mod name here so you remember

```

For example, this is a configuration for Global Positions:

```
	["workshop-378160973"] = { enabled = true,
		configuration_options =
		{
			SHOWPLAYERSOPTIONS = 1,
			SHOWPLAYERICONS = false,
			FIREOPTIONS = 1,
			SHOWFIREICONS = true,
			SHAREMINIMAPPROGRESS = false,
		},
	}, -- Global Positions

```

Made by Builder

|  |  |
| --- | --- |
| **Game Guides** [view](/wiki/Template:Guide "Template:Guide") | |
| **Basic** | [Getting Started](/wiki/Guides/Getting_Started_Guide "Guides/Getting Started Guide")  •  [The Big Picture](/wiki/Guides/The_Big_Picture "Guides/The Big Picture")  •  [All About Night](/wiki/Guides/All_About_Night "Guides/All About Night") [Blitzkrieging with Don't Starve's world](/wiki/Guides/Blitzkrieging_with_Don%27t_Starve%27s_world "Guides/Blitzkrieging with Don't Starve's world")  •  [Golden Rules of Don't Starve](/wiki/Guides/Golden_Rules_of_Don%27t_Starve "Guides/Golden Rules of Don't Starve")  •  [Marking and Revisiting Areas](/wiki/Guides/Marking_and_Revisiting_Areas "Guides/Marking and Revisiting Areas")  •  [Starting Out: A Guide For Newbies](/wiki/Guides/Starting_Out:_A_Guide_For_Newbies "Guides/Starting Out: A Guide For Newbies")  •  [Taming a Beefalo](/wiki/Guides/Taming_a_Beefalo "Guides/Taming a Beefalo")  •  [Thulecite](/wiki/Guides/Thulecite "Guides/Thulecite") |
| **DLC** | [Don't Starve in Reign of Giants](/wiki/Guides/Don%27t_Starve_in_Reign_of_Giants "Guides/Don't Starve in Reign of Giants")  •  [The Ultimate Reign of Giants Starting Guide](/wiki/Guides/The_Ultimate_Reign_Of_Giants_Starting_Guide "Guides/The Ultimate Reign Of Giants Starting Guide")  •  [Summer Guide](/wiki/Guides/Summer_Guide "Guides/Summer Guide")  •  [Surviving Winter](/wiki/Guides/Surviving_Winter "Guides/Surviving Winter")  •  [Survive A Year In Reign Of Giants](/wiki/Guides/Survive_A_Year_In_Reign_Of_Giants "Guides/Survive A Year In Reign Of Giants")  •  [Surviving Shipwrecked](/wiki/Guides/Surviving_Shipwrecked "Guides/Surviving Shipwrecked")  •  [Surviving the hurricane and monsoon seasons in Shipwrecked](/wiki/Guides/Surviving_a_year_in_Shipwrecked "Guides/Surviving a year in Shipwrecked")  •  [Making Aquatic Bases in Shipwrecked](/wiki/Guides/Making_Aquatic_Bases_in_Shipwrecked "Guides/Making Aquatic Bases in Shipwrecked")  •  [Using the Seaworthy](/wiki/Guides/From_SW_to_RoG_via_the_Seaworthy! "Guides/From SW to RoG via the Seaworthy!")  •  [Guide to Survive in Volcano](/wiki/Guides/Guide_to_Survive_in_Volcano "Guides/Guide to Survive in Volcano")  •  [Getting Started in Hamlet](/wiki/Guides/Getting_Started_in_Hamlet "Guides/Getting Started in Hamlet")  •  [Exploring the Ruins in Hamlet](/wiki/Guides/Exploring_the_Ruins_in_Hamlet "Guides/Exploring the Ruins in Hamlet")  •  [Ham Temperate Season](/wiki/Guides/Ham_Temperate_Season "Guides/Ham Temperate Season")  •  [Hamlet Economy](/wiki/Guides/Hamlet_Economy "Guides/Hamlet Economy")  •  [Hamlet Survival Guide](/wiki/Guides/Hamlet_Survival_Guide "Guides/Hamlet Survival Guide")  •  [Making you own Hamlet](/wiki/Guides/Making_you_own_Hamlet "Guides/Making you own Hamlet") |
| **Camping** | [Base Camp](/wiki/Guides/Base_Camp_Guide "Guides/Base Camp Guide")  •  [Frog Pond Camp](/wiki/Guides/Frog_Pond_Camp_Guide "Guides/Frog Pond Camp Guide")  •  [Self-sustaining Settlement](/wiki/Guides/Self-sustaining_Settlement_Guide "Guides/Self-sustaining Settlement Guide")  •  [Marsh Camp](/wiki/Guides/Marsh_Camp_Guide "Guides/Marsh Camp Guide")  •  [Camping Underground](/wiki/Guides/Camping_Underground "Guides/Camping Underground")  •  [Summer Cave Base](/wiki/Guides/Summer_Cave_Base "Guides/Summer Cave Base")  •  [Organizing your time in space](/wiki/Guides/Organizing_your_time_in_space "Guides/Organizing your time in space")  •  [Protected Fortress](/wiki/Guides/Protected_Fortress "Guides/Protected Fortress")  •  [The Best Biomes for Camp](/wiki/Guides/The_Best_Biomes_for_Camp "Guides/The Best Biomes for Camp") |
| **Farming** | [Gold Nugget Farm](/wiki/Guides/Gold_Nugget_Farm_Guide "Guides/Gold Nugget Farm Guide")  •  [Renewable Farming](/wiki/Guides/Renewable_Farming "Guides/Renewable Farming")  •  [Farming](/wiki/Guides/Farming "Guides/Farming")  •  [Nightmare Fuel Farming](/wiki/Guides/Nightmare_Fuel_Farming "Guides/Nightmare Fuel Farming")  •  [Slurtle Slime](/wiki/Guides/Slurtle_Slime_Guide "Guides/Slurtle Slime Guide")  •  [Krampii](/wiki/Guides/Managing_Naughtiness "Guides/Managing Naughtiness")  •  [Fire Farm](/wiki/Guides/Fire_Farm "Guides/Fire Farm")  •  [Pig Farming](/wiki/Guides/Pig_Farming "Guides/Pig Farming")  •  [Berries and Turkey Farm](/wiki/Guides/Incredible_Inedible "Guides/Incredible Inedible")  •  [MeatFarm](/wiki/Guides/MeatFarm "Guides/MeatFarm") |
| **Survival** | [Adventure Mode](/wiki/Guides/Adventure_Guide "Guides/Adventure Guide")  •  [Mob Killing](/wiki/Guides/Mob_Killing_Guide "Guides/Mob Killing Guide")  •  [How To not starve](/wiki/Guides/How_to_not_starve "Guides/How to not starve")  •  [How to survive](/wiki/Guides/How_to_Survive "Guides/How to Survive")  •  [Hound Wave Survival](/wiki/Guides/Hound_Wave_Survival_Guide "Guides/Hound Wave Survival Guide")  •  [Mushroom Guide](/wiki/Guides/Mushroom_Guide "Guides/Mushroom Guide")  •  [Boss Drops](/wiki/Guides/What_To_Do_With_Boss_Drops "Guides/What To Do With Boss Drops")  •  [Spelunking Guide](/wiki/Guides/Spelunking_Guide "Guides/Spelunking Guide")  •  [Winter Guide](/wiki/Guides/Winter_Guide "Guides/Winter Guide")  •  [Crock Pot Dishes](/wiki/Guides/Crock_Pot_Dishes "Guides/Crock Pot Dishes")  •  [Panic Room Guide](/wiki/Guides/Panic_Room "Guides/Panic Room")  •  [Just Spawned](/wiki/Guides/You_Have_Just_Spawned,_Now_What%3F%3F "Guides/You Have Just Spawned, Now What??")  •  [Advanced World](/wiki/Guides/Advanced_World "Guides/Advanced World")  •  [World vs Caves](/wiki/Guides/World_vs_Caves "Guides/World vs Caves")  •  [Preparing for Deerclops](/wiki/Guides/Preparing_for_Deerclops "Guides/Preparing for Deerclops")  •  [Advanced Hound Protection](/wiki/Guides/Advanced_Hound_Protection "Guides/Advanced Hound Protection")  •  [Combatting Bosses](/wiki/Guides/Combatting_Bosses "Guides/Combatting Bosses")  •  [Killing Dragonfly DST](/wiki/Guides/Killing_Dragonfly_DST "Guides/Killing Dragonfly DST")  •  [Making your Overworld a better place.](/wiki/Guides/Making_your_Overworld_a_better_place. "Guides/Making your Overworld a better place.")  •  [Maximum Efficiency Day 13 Base DST Guide](/wiki/Guides/Maximum_Efficiency_Day_13_Base_DST_Guide "Guides/Maximum Efficiency Day 13 Base DST Guide")  •  [Picking your armor](/wiki/Guides/Picking_your_armor "Guides/Picking your armor")  •  [Surviving the winter in DST](/wiki/Guides/Surviving_the_winter_in_DST "Guides/Surviving the winter in DST")  •  [The Forge](/wiki/Guides/The_Forge "Guides/The Forge")  •  [Making Bigger and Better Weapons](/wiki/Guides/Making_Bigger_and_Better_Weapons "Guides/Making Bigger and Better Weapons")  (*[Surviving in a New Server](/wiki/Guides/Surviving_in_a_New_Server "Guides/Surviving in a New Server"))* |
| **Technical** | [Physical Damage Absorption](/wiki/Guides/Physical_Damage_Absorption "Guides/Physical Damage Absorption")  •  [Console Commands](/wiki/Guides/Console "Guides/Console")  (*[Automatically Start Dedicated Server (Linux)](/wiki/Guides/Automatically_Start_Dedicated_Server_(Linux) "Guides/Automatically Start Dedicated Server (Linux)")  •  **Simple Dedicated Server Setup**  •  [Don’t Starve Together Dedicated Servers](/wiki/Guides/Don%E2%80%99t_Starve_Together_Dedicated_Servers "Guides/Don’t Starve Together Dedicated Servers")* Don't Starve Together) |
| **Characters** | [Surviving 101 With Wilson P. Higgsbury (And Other People)](/wiki/Guides/Surviving_101_With_Wilson_P._Higgsbury_(And_Other_People) "Guides/Surviving 101 With Wilson P. Higgsbury (And Other People)")  •  [Willow](/wiki/Guides/Character_guide-Willow "Guides/Character guide-Willow")  •  [Wolfgang](/wiki/Guides/Character_guide_-_Wolfgang,_The_Strongman "Guides/Character guide - Wolfgang, The Strongman")  •  [Wendy](/wiki/Guides/Character_guides-Wendy "Guides/Character guides-Wendy")  •  [WX-78 survival](/wiki/Guides/WX-78_survival "Guides/WX-78 survival")  •  [Wickerbottom](/wiki/Guides/Wickerbottom "Guides/Wickerbottom")  •  [Wickerbottom's Books](/wiki/Guides/Character_guide_-_Wickerbottom%27s_Books "Guides/Character guide - Wickerbottom's Books")  •  [Woodie's Curse](/wiki/Guides/Woodie%27s_Curse "Guides/Woodie's Curse")  •  [Wigfrid](/wiki/User_blog:Cmshaw/Adventure_Mode_with_Wigfrid_Guide "User blog:Cmshaw/Adventure Mode with Wigfrid Guide")  •  [Maxwell](/wiki/Guides/Character_Guide_-_Maxwell "Guides/Character Guide - Maxwell")  •  [Guide to Don't Starve Lore](/wiki/Guides/Guide_to_Don%27t_Starve_Lore "Guides/Guide to Don't Starve Lore") |