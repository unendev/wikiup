---
game_version: dst
category: Uncategorized
source_url: https://dontstarve.fandom.com/wiki/Guides/Don’t_Starve_Together_Dedicated_Servers
---

|  |  |
| --- | --- |
|  | This article may contain **instructional** language and subjective recommendations. Readers should read the content carefully, and follow accordingly. |

**NOTE: As of December 5, 2015, this guide applies to versions of *[Don't Starve Together](/wiki/Don%27t_Starve_Together "Don't Starve Together")* (DST) prior to the official release of caves into the main release branch. If you're looking for a guide to running the latest release with caves, visit the guides for all systems**[[1]](http://steamcommunity.com/id/ToNiO44/myworkshopfiles/?section=guides&appid=322330).

A **Dedicated Server** is an optimized version of *[Don't Starve Together](/wiki/Don%27t_Starve_Together "Don't Starve Together")* (DST) that has been designed to be run for longer periods of time, with none of the graphical overhead. Users can host one themselves as shown or rent one from a Game Server provider that will host the server for them; thus becoming Game Server Administrators (GSAs).

The server is available through [Steam](https://en.wikipedia.org/wiki/Steam_(software) "wikipedia:Steam (software)") and [SteamCMD](https://developer.valvesoftware.com/wiki/SteamCMD) (appID 343050) on both [Windows](https://en.wikipedia.org/wiki/Microsoft_Windows "wikipedia:Microsoft Windows") and [Linux](https://en.wikipedia.org/wiki/Linux "wikipedia:Linux").

For a simpler guide to setting up a dedicated server through Steam on a normal computer, see [Guides/Simple Dedicated Server Setup](/wiki/Guides/Simple_Dedicated_Server_Setup "Guides/Simple Dedicated Server Setup"). Otherwise, the article below covers most other cases.

## Contents

* [1 Requirements](#Requirements)
* [2 Installing](#Installing)
  + [2.1 Downloading the Software](#Downloading_the_Software)
  + [2.2 Downloading through SteamCMD](#Downloading_through_SteamCMD)
  + [2.3 On Windows](#On_Windows)
  + [2.4 On Linux (Debian)](#On_Linux_(Debian))
  + [2.5 On Linux (Gentoo x64)](#On_Linux_(Gentoo_x64))
  + [2.6 Downloading through Steam (Only on Windows or “Desktop Linux” = requires a GUI)](#Downloading_through_Steam_(Only_on_Windows_or_“Desktop_Linux”_=_requires_a_GUI))
  + [2.7 Automated Installation via AMP](#Automated_Installation_via_AMP)
* [3 Updating](#Updating)
  + [3.1 Windows](#Windows)
  + [3.2 Linux](#Linux)
* [4 Configuration](#Configuration)
  + [4.1 [network]](#[network])
  + [4.2 [account]](#[account])
  + [4.3 Command Line Options](#Command_Line_Options)
* [5 Server Tokens](#Server_Tokens)
* [6 Running](#Running)
* [7 Multiple Servers on the Same Machine](#Multiple_Servers_on_the_Same_Machine)
* [8 Customizing the World Map](#Customizing_the_World_Map)
  + [8.1 Creating the Map](#Creating_the_Map)
* [9 Mods](#Mods)
* [10 Administration](#Administration)
* [11 FAQ](#FAQ)
  + [11.1 \* Is it possible to run a dedicated server in LAN mode?](#*_Is_it_possible_to_run_a_dedicated_server_in_LAN_mode?)
  + [11.2 \* Is it possible to host multiple servers on a single (powerful) machine?](#*_Is_it_possible_to_host_multiple_servers_on_a_single_(powerful)_machine?)
  + [11.3 \* Which save slot does the dedicated server use?](#*_Which_save_slot_does_the_dedicated_server_use?)
* [12 Troubleshooting](#Troubleshooting)
* [13 Other Notes](#Other_Notes)

## Requirements[]

GSAs must own a copy of *Don’t Starve Together* to host an online Dedicated Servers, however a single copy of DST can host many Dedicated Server instances.

Dedicated Servers do not use the same punch-through technology that [Klei Entertainment Inc.](/wiki/Klei_Entertainment "Klei Entertainment") (Klei) client hosted servers use. GSAs must add proper port forwarding for their server to be reachable by game clients. The server uses UDP traffic on port 10999 by default.

GSAs will need knowledge of navigating file systems, and use of a text editor if they choose to run their server on Windows.

For Linux GSAs will need to know how to use their package management system, [chmod](https://en.wikipedia.org/wiki/chmod "wikipedia:chmod"), and the [VIM text editor](https://en.wikipedia.org/wiki/VIM_(text_editor) "wikipedia:VIM (text editor)"); in addition to file system navigation.

To create a reliable/performant Dedicated Server experience GSAs will want to ensure that their system has the following properties:

* Internet (Upload) = 8Kbytes / player / s
* Ram =  around 65Mbytes/player
* CPU = N/A
* VCRedist\_2008 (x86)

## Installing[]

### Downloading the Software[]

Klei currently offers two methods of downloading their server :

### Downloading through SteamCMD[]

```
Learn more about SteamCMD here: https://developer.valvesoftware.com/wiki/SteamCMD

```

### On Windows[]

```
login anonymous
force_install_dir C:\path\to\directory
app_update 343050 validate

```

### On Linux (Debian)[]

```
sudo dpkg --add-architecture i386 # If running a 64bit OS
sudo apt-get update
sudo apt-get install lib32gcc1    # If running a 64bit OS

```

```
sudo apt-get install lib32stdc++6 # If running a 64bit OS
sudo apt-get install libgcc1      # If running a 32bit OS
sudo apt-get install libcurl4-gnutls-dev:i386
sudo useradd -m steam
chmod a+rw `tty`  # Note those are backticks, not single quotes
sudo su - steam
mkdir ~/steamcmd
cd ~/steamcmd
wget https://steamcdn-a.akamaihd.net/client/installer/steamcmd_linux.tar.gz
tar -xvzf steamcmd_linux.tar.gz
./steamcmd.sh
login anonymous
force_install_dir /home/steam/steamapps/DST (or whatever absolute path is wanted)
app_update 343050 validate
quit
cd /home/steam/steamapps/DST/bin/
screen -S "DST Server" ./dontstarve_dedicated_server_nullrenderer

```

**Note:** If the GSAs server runs Debian stable (debian 7 / Debian Wheezy), the GSA will not be able to launch the server because of the old libc version. The error will be :

```
./dontstarve_dedicated_server_nullrenderer: /lib/i386-linux-gnu/i686/cmov/libc.so.6: version `GLIBC_2.15' not found (required by ./dontstarve_dedicated_server_nullrenderer)

```

Here is a hacky workaround (using the git repo) : <https://github.com/dgibbs64/linuxgameservers/tree/master/Insurgency/dependencies> )

```
mkdir ~/dst_lib && cd ~/dst_lib
wget https://github.com/dgibbs64/linuxgameservers/raw/master/Insurgency/dependencies/libc.so.6
wget https://github.com/dgibbs64/linuxgameservers/raw/master/Insurgency/dependencies/libpthread.so.0
wget https://github.com/dgibbs64/linuxgameservers/raw/master/Insurgency/dependencies/librt.so.1

```

And to run the server :

```
cd /home/steam/steamapps/DST/bin/
screen -S "DST Server" bash -c 'LD_LIBRARY_PATH=~/dst_lib ./dontstarve_dedicated_server_nullrenderer'

```

The server will start, then the GSA can press CTRL+A and then D to allow them to exit to a shell. If the GSA wants to see it again, just type:

```
screen -r

```

### On Linux (Gentoo x64)[]

To run a dedicated server GSAs should enable Multilib system without emul packages, read the [official manual](http://wiki.gentoo.org/wiki/Multilib_System_without_emul-linux_Packages) on how to make it.

After the GSA's system updates they will have to add the following lines into */etc/portage/package.use:*

```
net-misc/curl curl_ssl_gnutls -curl_ssl_openssl abi_x86_32

```

and rebuild the curl with

```
emerge -av curl

```

After curl merge is successful the GSA would probably need to link their */usr/lib32/libcurl.so.4* to */usr/lib32/libcurl-gnutls.so.4* to make DST work.

```
ln -s /usr/lib32/libcurl.so.4 /usr/lib32/libcurl-gnutls.so.4 && ldconfig

```

### Downloading through Steam (Only on Windows or “Desktop Linux” = requires a [GUI](https://en.wikipedia.org/wiki/GUI "wikipedia:GUI"))[]

If the GSA owns Don’t Starve Together, the Dedicated Server download will appear in the ‘tools’ subsection of their library.

### Automated Installation via AMP[]

 https://static.wikia.nocookie.net/dont-starve-game/images/f/f1/CreatingServer.png/revision/latest?cb=20230504151654 

Creating a server using AMP

 

[AMP](https://cubecoders.com/AMP/DontStarve?utm_source=fandom&utm_term=dontstarve) is a popular game server management panel you can run on your own PC/server that supports Don't Starve Together and makes it easier to get the server going from its web interface. You can run it on both Windows and Linux.

Once you have AMP itself up and running ([Tutorial and Guide](https://cubecoders.com/AMPInstall?utm_source=fandom&utm_term=dontstarve)), click "Create Instance" and then select "Don't Starve Together" from the list of games. Once you've done that, select "Create Instance".

After that, double click the new Don't Starve Together instance to manage it and select "Update" from the status screen. This will download the Don't Starve Together server and required files.

Once the download is complete you can hit Start and after a few moments the server will start up. Once the startup has completed you can connect as normal.

## Updating[]

### Windows[]

Create a .bat file (for example *updateddst.bat*) -- this is just a text file—with the contents:

```
steamcmd +login anonymous +force_install_dir D:\Servers\DST\ +app_update 343050 validate +quit

```

Where *D:\Servers\DST\* is an example of where the GSA might have their DST Dedicated Server install

### Linux[]

To create an update script, stop the server and type:

```
$ vim /home/steam/update_dst.sh

```

Then add this to the file *update\_dst.sh* and don't forget to change the game path or steamcmd path as needed.

```
#!/bin/sh

/home/steam/steamcmd/steamcmd.sh +@ShutdownOnFailedCommand 1 +@NoPromptForPassword 1 +login anonymous +force_install_dir /home/steam/steamapps/DST +app_update 343050 validate +quit

/home/steam/steamapps/DST/bin/dontstarve_dedicated_server_nullrenderer -only_update_server_mods

```

This script will update the DST application from Steam, then upate any mods the GSA installed.
Make it executable:

```
$ chmod +x update_dst.sh

```

When a GSA wants to update their server; reconnect to the screen session, do a *c\_save()* to save the server, then do a *c\_shutdown*. Once the server has shutdown it will return the GSA to the command line.

Then do the following

```
$ /home/steam/update_dst.sh

```

When finished GSAs will see "*Shutting down*" and the GSA will be returned to the CLI.

Then to start the server, if the GSA has used the startup scripts just type *./start\_dst.sh*. (See below if the *start\_dst.sh* file is still not created)

## Configuration[]

By now the GSA should have two different directories:

The steam directory where the GSA has their executable server file, *dontstarve\_dedicated\_server\_nullrenderer.exe* on Windows and *dontstarve\_dedicated\_server\_nullrenderer* on Linux. The directory should look like that:

Windows:

```
C:/DontStarve/bin/dontstarve_dedicated_server_nullrenderer.exe

```

Linux:

```
/home/steam/steamapps/DST/bin/dontstarve_dedicated_server_nullrenderer

```

The second directory is where the GSA has all their server settings. Those are the default directories:

On Windows:

```
Documents/Klei/DoNotStarveTogether/

```

On Linux:

```
~/.klei/DoNotStarveTogether/

```

**Note that if the GSA will be playing DST from the same computer they are hosting the dedicated server on, they should use a different configuration directory for the dedicated server.** This can be done by using the launch option *-conf\_dir myDSTserver*, which will change it to use this folder:

```
Documents/Klei/myDSTserver/

```

Should the GSA want to store the configuration files elsewhere, you can use a combination of the *-persistent\_storage\_root* and *-conf\_dir* launch options to point the directory elsewhere.

For example,

```
-persistent_storage_root C:\dstserver -conf_dir dstserver_config

```

would store your config files in

```
C:\dstserver\dstserver_config\

```

The main method for configuring their dedicated server is through the *settings.ini* file. If the *settings.ini* file is not there, **they will have to make it with a text editor of their choice.**

On Windows:

```
Documents/Klei/DoNotStarveTogether/Settings.ini

```

On Linux:

```
~/.klei/DoNotStarveTogether/settings.ini

```

Settings are divided by different categories,  make sure to put changes under the appropriate category:

### [network][]

```
default_server_name = A unique server name
default_server_description = A very nice server description
server_port = 10999
server_password = password
max_players = 6 [1 - 64]
pvp = true | false
game_mode = endless | survival | wilderness
server_intention = cooperative | social | competitive | madness

```

Snapshots allow GSAs the ability to fully roll back a server to an older state.  This feature is pretty new and has been observed to cause problems on some servers.

```
 enable_snapshots = [true | false]

```

The auto saver creates a save at the start of each day, which will be resumed from when the server restarts.

```
 enable_autosaver = [true | false]

```

Tick rate determines the quality of the server.  A higher tick rate consumes more bandwidth and CPU, but can result in smooth gameplay for clients.

```
tick_rate = 30 [ 10 | 15 | 30 | 60 ]

```

A time in milliseconds that the server should wait before dropping a non responsive client.

```
connection_timeout = 8000

```

Which save slot should the dedicated server load

```
server_save_slot = 1..5

```

When no admin is present, vote kick allows users to vote to kick players that are disrupting the game.

```
vote_kick_enabled = [true | false]

```

When the dedicated server is empty, pause the simulation. This is especially useful for private dedicated servers, when a GSA wants to resume where they left off with a group of friends. Defaults to false.

```
pause_when_empty = [ true | false ]

```

### [account][]

Enable LAN only server:

```
dedicated_lan_server = [ true | false ]

```

### Command Line Options[]

Command line options override settings.ini options.

Force the server to use a specific port

```
-port [1024 .. 65535]

```

Force the server to run at a specific tick rate

```
-tick [15 .. 60]

```

Force the maximum number of players allowed in the server.

```
-players [1 .. 64]

```

Enable a command line console input mechanism. Allows GSAs to execute privileged lua directly in the server window.

```
-console

```

Force the server into lan mode. The server will not require token authentication, and will not appear in the server listings. Only users on the same network will be able to join.

```
-lan

```

Force the server to load save and settings data from an alternative directory. Note that this is the directory*name*, not the path. For example, a directory name of *DoNotStarveServerDirectory* would translate into a path of *~/.klei/DoNotStarveServerDirectory.*

```
-conf_dir DoNotStarveServerDirectory

```

Setting the root directory of persistent storage. E.g. on Windows this defaults to the current user documents folder / klei. This is used in conjunction with conf\_dir to generate the path where files are stored.

```
-persistent_storage_root <AltPersistentStorageRoot>

```

## Server Tokens[]

In order to run a public Dedicated Server, GSAs are required to enter their “cluster token”. This token proves ownership of *Don’t Starve Together* and allows Klei to moderate servers that violate their terms of service.

To generate a cluster token, do the following:

Run *Don’t Starve Together* (the actual game, not the server that is being set-up. GSAs need to log into steam). Click *Play*.

Press tilde (~) (or ù on Azerty keyboards) to open the developer console and type:

```
TheNet:GenerateClusterToken()

```

This command will generate the cluster token under the name *cluster\_token.txt*. This file located in:

On Windows:

```
/My Documents/Klei/DoNotStarveTogether/cluster_token.txt

```

On Linux:

```
 ~/.klei/DoNotStarveTogether/cluster_token.txt

```

On [Mac OS X](https://en.wikipedia.org/wiki/OS_X "wikipedia:OS X"):

```
~/Documents/Klei/DoNotStarveTogether/cluster_token.txt

```

In order to use this token you have to move this file into your cluster folder. E.g. by default your server uses cluster 1 (first save slot in game), and to use this cluster for server you have to copy-paste cluster\_token.txt inside folder ../My Documents/Klei/DoNotStarveTogether/Cluster\_1/

## Running[]

After setting the server, GSAs can run it with this command:

Windows:

```
dontstarve_dedicated_server_nullrenderer.exe [-conf_dir <AlternativeDirectory>]

```

Linux:

Create a shell script to start screen and run the DST server. Once the shell script is running GSAd can press and release CTRL+A then press D and it will return them to their shell and continue running DST.

```
$ vim start_dst.sh

```

Put the following in the file *start\_dst.sh*. The *-console* option can be added to allow commands from the DST console, *c\_save()*, *c\_shutdown()* etc...

What this script does it to start the screen utility which will allow GSAs to detach from the shell and continue running, even when logged out, and reconnect as needed. Screen is told to start a shell and run the DST application.

```
#!/bin/sh
cd /home/steam/steamapps/DST/bin
/usr/bin/screen -S "DST" /bin/sh -c './dontstarve_dedicated_server_nullrenderer -console'

```

If the GSA needs the additional library path *LD\_LIBRARY\_PATH=~/dst\_lib* when starting DST do the following.

```
#!/bin/bash

cd /home/steam/steamapps/DST/bin

/usr/bin/screen -S "DST" bash -c 'LD_LIBRARY_PATH=~/dst_lib ./dontstarve_dedicated_server_nullrenderer -console'

```

Save and exit VIM with : *wq then chmod 700 start\_dst.sh*.

GSAs now can start their server from the command line logged in as a Steam user by typing *./start\_dst.sh*

If they wish keep the server running and return to the shell prompt they can press and release CTRL+A then press D and their server will keep running and they can log out.

To return to their running DST server login as their user account and do a *sudo su - steam* command. Then type *screen -r* and it will reopen the screen they detached from previously, they will now be at the console of their dedicated DST server.

## Multiple Servers on the Same Machine[]

If GSAs wish to run multiple servers on the same machine, or have their server co-exist with the client, they will want to launch their server with an alternative configuration directory using the *-conf\_dir* command line parameter.

To use an alternative configuration directory, they can launch the server (or client) with this command line argument (on Steam they can do this under Properties -> Set Launch Options):

```
-conf_dir DoNotStarveServerDirectory

```

This would change their configuration directory to *Documents/Klei/DoNotStarveServerDirectory/* (or *~/.klei/DoNotStarveServerDirectory* on Linux). GSAs will then have a unique set of save slots, *log.txt* and *settings.ini* files.

## Customizing the World Map[]

To generate a custom map with a dedicated server, GSAs will have to create a "lua" file in their configuration directory (*~/Klei/DoNotStarveServerDirectory*) named *worldgenoverride.lua*. This file should return a table containing a key "*override\_enabled*" assigned to true or false, and other keys containing tables of settings. An example of this is:

```
return {
	override_enabled = true,
	misc = { season="shortboth", world_size="huge", season_start="summer" },
	resources = { flint="never", grass="never", sapling="never", trees="never" }
}

```

Here is the table of options and their potential settings:

```
 return {
 
 override_enabled = true,
       unprepared = { -- "never", "rare", "default", "often", "always"
               berrybush = "default",
               cactus = "default",
               carrot = "default",
               mushroom = "default",
       },
       misc = {
               task_set = "default", -- Biomes: "classic", "cave_default"
               start_location = "default", -- Start: "plus", "darkness", "caves"
               autumn = "default", -- "noseason", "veryshortseason", "shortseason", "default", "longseason", "verylongseason", "random"
               boons = "default", -- "never", "rare", "default", "often", "always"
               branching = "default", -- "never", "least", "default", "most"
               day = "default", -- "default", "longday", "longdusk", "longnight", "noday", "nodusk", "nonight", "onlyday", "onlydusk", "onlynight"
               frograin = "default", -- "never", "rare", "default", "often", "always"
               lightning = "default", -- "never", "rare", "default", "often", "always"
               loop = "default", -- "never", "default", "always"
               season_start = "default", -- "default", "winter", "spring", "summer", "random"
               spring = "default", -- "noseason", "veryshortseason", "shortseason", "default", "longseason", "verylongseason", "random"
               summer = "default", -- "noseason", "veryshortseason", "shortseason", "default", "longseason", "verylongseason", "random"
               touchstone = "default", -- "never", "rare", "default", "often", "always"
               weather = "default", -- "never", "rare", "default", "often", "always"
               wildfires = "default", -- "never", "rare", "default", "often", "always"
               winter = "default", -- "noseason", "veryshortseason", "shortseason", "default", "longseason", "verylongseason", "random"
               world_size = "default", -- "default", "medium", "large", "huge"
       },
       animals = { -- "never", "rare", "default", "often", "always"
               alternatehunt = "default",
               angrybees = "default",
               beefalo = "default",
               beefaloheat = "default",
               bees = "default",
               birds = "default",
               butterfly = "default",
               buzzard = "default",
               catcoon = "default",
               frogs = "default",
               hunt = "default",
               lightninggoat = "default",
               moles = "default",
               penguins = "default",
               perd = "default",
               pigs = "default",
               rabbits = "default",
               tallbirds = "default",
       },
       monsters = { -- "never", "rare", "default", "often", "always"
               bearger = "default",
               chess = "default",
               deciduousmonster = "default",
               deerclops = "default",
               dragonfly = "default",
               goosemoose = "default",
               houndmound = "default",
               hounds = "default",
               krampus = "default",
               liefs = "default",
               lureplants = "default",
               merm = "default",
               spiders = "default",
               tentacles = "default",
               walrus = "default",
       },
       resources = { -- "never", "rare", "default", "often", "always"
               flint = "default",
               flowers = "default",
               grass = "default",
               marshbush = "default",
               meteorshowers = "default",
               meteorspawner = "default",
               reeds = "default",
               rock = "default",
               rock_ice = "default",
               sapling = "default",
               trees = "default",
               tumbleweed = "default",
       },
}

```

**To customize the world from the Don't Starve Together graphical client for a dedicated server, GSAs can follow the following steps. This is the legacy method.**

### Creating the Map[]

To create a custom map for a dedicated server, launch the *Don't Starve Together* client and select the option to Host Server on the server selection screen. Take note of which slot is used to generate the world, as GSAs will need to set its number in the dedicated server's *settings.ini*. Then they can select the option to *Edit World* on the right.

On the *Edit World* options screen, GSAs may select the world options for the new world they wish to run as a dedicated server. It is not necessary to save their settings as a preset, though they may do so if they wish to reuse the settings later. When finished, select the option to *Apply* the settings.

Back on the server creation screen, enter a world name and description. This is required, but note that these settings are overridden in the persistent server's *settings.ini* file, so enter any legal values. The server will briefly appear in Klei's server selection screen, so it's not advised to choose a vulgar or obscene name. Once ready, select the *Create Server* option on the left.

GSAs will then see the *Generating World* screen while the map is generated, and next be placed on the *Character Selection* screen. At this point, the map is generated and there is no need to actually play on it, so select the option to *Disconnect* and confirm the option to shut down the server (*Do it!*) when prompted. They may now exit the *Don't Starve Together* graphical client.

In their *Don't Starve Together* directory, which is *Documents\Klei\DoNotStarveTogether\save* on Windows, or *~/.klei/DoNotStarveTogether/save* on Linux, there will now be a file named *saveindex*, and a folder named *server\_temp*. Copy both of these servers into the configuration directory for the dedicated server (or leave them in the same directory if they are not using *-conf\_dir* (this is not recommended!)).

In the Dedicated Server's s*ettings.ini* file, ensure that the *server\_save\_slot* parameter in the *[Network]* section matches up with the save slot number GSAs will use. For example, if they are using slot 2, they should have this in their *settings.ini* file:

```
[Network]
server_save_slot = 2

```

## Mods[]

Dedicated servers support [Mods](/wiki/Mods "Mods") too, and can be automatically installed via *dedicated\_server\_mods\_setup.lua* in the main mods directory (*"server install folder"\mods* ; if the file is missing from the build it has to be created manually). This file is run on boot and will download any mods or collection of mods that are setup in the file. An example of this file would be.

**Note:** The mods folder if you used steam to download the dedicated server is located at \steam\steamapps\common\Don't Starve Together Dedicated Server\mods

```
ServerModSetup("345692228")
ServerModSetup("346968521")
ServerModSetup("352373173")
ServerModCollectionSetup("379114180")

```

The mods only update when the version changes on the [Workshop](https://en.wikipedia.org/wiki/Steam_Workshop "wikipedia:Steam Workshop"), so server boot times should be reasonable and only pickup mod changes as they get posted to the Workshop. These mods will be downloaded and installed to the GSA's mod folder, but they aren't enabled by default.

There are two command line options for controlling how the *dedicated\_server\_mods\_setup.lua* is used. They are meant to be used by hosts that are running several dedicated servers on a single machine.

```
-skip_update_server_mods - skips the download of any mods or collections in the file
-only_update_server_mods - quits once the downloads are finished.

```

**Enabling Mods**

To enable mods on dedicated servers, there are two ways.

The first is to force-enable the mods. While simpler, it does not allow you to configure the mod's settings and all mods will use default configuration. To do this, the file *modsettings.lua* will need to be modified to force enable the mods they desire. For example, this would enable three mods from the Workshop.

```
ForceEnableMod("workshop-345692228")
ForceEnableMod("workshop-346968521")
ForceEnableMod("workshop-352373173")

```

To ensure that clients will be able to download the corresponding mods from the Workshop, GSAs must not change the mod directory names.

The second option is using a *modoverrides.lua* file. Dedicated servers can now enable mods and set mod configurations via a file in the configuration directory named *modoverrides.lua* (*\Documents\Klei\DoNotStarveTogether\modoverrides.lua*). An example of the contents of this file is

```
return {
    ["workshop-350811795"] = { enabled = true },
    ["workshop-387028143"] = { enabled = true },
    ["workshop-361336115"] = { enabled = true,
        configuration_options =
        {
            hunt_time = 6,
            ["String Phrase Option Name"] = "some value",
        }
    },
    ["workshop-336882447"] = { enabled = true }
}

```

In order to [retrieve the *configuration\_options*](http://forums.kleientertainment.com/topic/59506-dedicated-server-wmods-problem/) for the mod, you will need to browse to the mod directory and open the *modinfo.lua* file. Copy the entire *configuration\_options* table and paste in your *modoverrides.lua*. Then configure to your liking. If there is no configuration\_options table, then the mod is not configurable.

**Note:** Dedicated Servers (and normal clients) can override the mod config options to non-valid options using this method. In this example, 6 is not a normal option for the *hunt\_time* in the Hunt game mode mod.

## Administration[]

Dedicated Servers can be remotely administered through the game client. The user who generated the server token is automatically granted administrator privileges. An administrator has the ability to kick and ban users in the game, as well as to execute arbitrary lua on the server.

**WARNING**: Only grant administrator rights to those who the GSA would trust with full access to their server / machine.  Klei may add a moderator mode with reduced powers in the future.

If the GSA wishes to provide administrator privileges to additional users, they need to add an *adminlist.txt* file to their save directory:

On Windows:

```
Documents/Klei/DoNotStarveTogether/Save/

```

On Linux:

```
~/.klei/DoNotStarveTogether/Save/

```

In this directory add the user identifiers for the users the GSA wishes to grant adminship to. List the KU\_’s one per line.

As an admin, the they may issue a remote command to the server by doing the following:

* connect to the world through the game client
* open the developer console (~)
* press left CTRL to enter remote execution mode (REMOTE: appears to the left)
* The command they enter will be executed on the server, assuming they have sufficient administrator rights
* For a full list of console commands, see [Don't Starve Together Commands](/wiki/Console/Don%27t_Starve_Together_Commands "Console/Don't Starve Together Commands")

## FAQ[]

### \* Is it possible to run a dedicated server in LAN mode?[]

Yes,  LAN mode can be configured in settings.ini by adding this line under the [account] heading:

```
dedicated_lan_server = true

```

**Please remember LAN servers do not appear in the online server listings. But the GSA CAN view them by setting the "Show LAN" option in the servers list in Don't Starve Together to on.**

### \* Is it possible to host multiple servers on a single (powerful) machine?[]

Yes, in order to host multiple servers GSAs will want to have multiple configurations.

GSAs can launch a server with a different configuration directory by passing in this command line option:

```
 -conf_dir <NewDirectoryName>

```

For example, if the GSA enters *-conf\_dir MyDedi* then it will use the folder *Documents/Klei/MyDedi* for all saving, loading, and configuration that is normally stored in *Documents/Klei/DoNotStarveTogether*.

### \* Which save slot does the dedicated server use?[]

By default, 1. GSA's can override the slot through the settings.ini option:

```
[network]
server_save_slot = [1..5]

```

## Troubleshooting[]

* If the GSA ran the dedicated server before they put a token in the settings.ini they may need to delete their world data.  Shutdown the server or client,

In *C:\Users\Mark\Documents\Klei\DoNotStarveTogether\save\* delete *survival\_1*.
When they restart a new world will be generated

* If the server is running correctly, but it still can’t be seen. Confirm it’s listing here: [http://dstserverlist.appspot.com](https://dstserverlist.appspot.com/). Users will have to manually update the listing at the bottom of the page. If it’s still not visible from inside the game client, it’s because the client can’t ping the server. Assure that the GSA made sure to forward UDP traffic on port 10999 to their machine.

* If the GSA gets the error *Account Failed (6): "E\_EXPIRED\_TOKEN"* they will want to verify that they saved the *settings.ini* file with the server token after they closed the game client, or their changes will get overwritten. Also, make sure that there are no extra characters at the end of the file, even invisible ones.

## Other Notes[]

* To find ones own dedicated server when searching in game, the LAN games option must be turned on, as the server is hosted on the same machine and/or network. This does not necessarily mean that it is a LAN game, only that it is running on the same machine and/or network.
* A great recommendation is to use Notepad++ or another such suitable program for editing the code as it offers more options and is generally more helpful to edit these files than the basic notepad or similar word program as it is tailored to these files and coding.
* As mentioned above in command lines, windows users can easily control their servers through the black screen by opening the properties of a shortcut to the server's executable and then typing -console at the end of the target line. i.e. (~\bin\dontstarve\_dedicated\_server\_nullrenderer.exe -console)

|  |  |
| --- | --- |
| **Game Guides** [view](/wiki/Template:Guide "Template:Guide") | |
| **Basic** | [Getting Started](/wiki/Guides/Getting_Started_Guide "Guides/Getting Started Guide")  •  [The Big Picture](/wiki/Guides/The_Big_Picture "Guides/The Big Picture")  •  [All About Night](/wiki/Guides/All_About_Night "Guides/All About Night") [Blitzkrieging with Don't Starve's world](/wiki/Guides/Blitzkrieging_with_Don%27t_Starve%27s_world "Guides/Blitzkrieging with Don't Starve's world")  •  [Golden Rules of Don't Starve](/wiki/Guides/Golden_Rules_of_Don%27t_Starve "Guides/Golden Rules of Don't Starve")  •  [Marking and Revisiting Areas](/wiki/Guides/Marking_and_Revisiting_Areas "Guides/Marking and Revisiting Areas")  •  [Starting Out: A Guide For Newbies](/wiki/Guides/Starting_Out:_A_Guide_For_Newbies "Guides/Starting Out: A Guide For Newbies")  •  [Taming a Beefalo](/wiki/Guides/Taming_a_Beefalo "Guides/Taming a Beefalo")  •  [Thulecite](/wiki/Guides/Thulecite "Guides/Thulecite") |
| **DLC** | [Don't Starve in Reign of Giants](/wiki/Guides/Don%27t_Starve_in_Reign_of_Giants "Guides/Don't Starve in Reign of Giants")  •  [The Ultimate Reign of Giants Starting Guide](/wiki/Guides/The_Ultimate_Reign_Of_Giants_Starting_Guide "Guides/The Ultimate Reign Of Giants Starting Guide")  •  [Summer Guide](/wiki/Guides/Summer_Guide "Guides/Summer Guide")  •  [Surviving Winter](/wiki/Guides/Surviving_Winter "Guides/Surviving Winter")  •  [Survive A Year In Reign Of Giants](/wiki/Guides/Survive_A_Year_In_Reign_Of_Giants "Guides/Survive A Year In Reign Of Giants")  •  [Surviving Shipwrecked](/wiki/Guides/Surviving_Shipwrecked "Guides/Surviving Shipwrecked")  •  [Surviving the hurricane and monsoon seasons in Shipwrecked](/wiki/Guides/Surviving_a_year_in_Shipwrecked "Guides/Surviving a year in Shipwrecked")  •  [Making Aquatic Bases in Shipwrecked](/wiki/Guides/Making_Aquatic_Bases_in_Shipwrecked "Guides/Making Aquatic Bases in Shipwrecked")  •  [Using the Seaworthy](/wiki/Guides/From_SW_to_RoG_via_the_Seaworthy! "Guides/From SW to RoG via the Seaworthy!")  •  [Guide to Survive in Volcano](/wiki/Guides/Guide_to_Survive_in_Volcano "Guides/Guide to Survive in Volcano")  •  [Getting Started in Hamlet](/wiki/Guides/Getting_Started_in_Hamlet "Guides/Getting Started in Hamlet")  •  [Exploring the Ruins in Hamlet](/wiki/Guides/Exploring_the_Ruins_in_Hamlet "Guides/Exploring the Ruins in Hamlet")  •  [Ham Temperate Season](/wiki/Guides/Ham_Temperate_Season "Guides/Ham Temperate Season")  •  [Hamlet Economy](/wiki/Guides/Hamlet_Economy "Guides/Hamlet Economy")  •  [Hamlet Survival Guide](/wiki/Guides/Hamlet_Survival_Guide "Guides/Hamlet Survival Guide")  •  [Making you own Hamlet](/wiki/Guides/Making_you_own_Hamlet "Guides/Making you own Hamlet") |
| **Camping** | [Base Camp](/wiki/Guides/Base_Camp_Guide "Guides/Base Camp Guide")  •  [Frog Pond Camp](/wiki/Guides/Frog_Pond_Camp_Guide "Guides/Frog Pond Camp Guide")  •  [Self-sustaining Settlement](/wiki/Guides/Self-sustaining_Settlement_Guide "Guides/Self-sustaining Settlement Guide")  •  [Marsh Camp](/wiki/Guides/Marsh_Camp_Guide "Guides/Marsh Camp Guide")  •  [Camping Underground](/wiki/Guides/Camping_Underground "Guides/Camping Underground")  •  [Summer Cave Base](/wiki/Guides/Summer_Cave_Base "Guides/Summer Cave Base")  •  [Organizing your time in space](/wiki/Guides/Organizing_your_time_in_space "Guides/Organizing your time in space")  •  [Protected Fortress](/wiki/Guides/Protected_Fortress "Guides/Protected Fortress")  •  [The Best Biomes for Camp](/wiki/Guides/The_Best_Biomes_for_Camp "Guides/The Best Biomes for Camp") |
| **Farming** | [Gold Nugget Farm](/wiki/Guides/Gold_Nugget_Farm_Guide "Guides/Gold Nugget Farm Guide")  •  [Renewable Farming](/wiki/Guides/Renewable_Farming "Guides/Renewable Farming")  •  [Farming](/wiki/Guides/Farming "Guides/Farming")  •  [Nightmare Fuel Farming](/wiki/Guides/Nightmare_Fuel_Farming "Guides/Nightmare Fuel Farming")  •  [Slurtle Slime](/wiki/Guides/Slurtle_Slime_Guide "Guides/Slurtle Slime Guide")  •  [Krampii](/wiki/Guides/Managing_Naughtiness "Guides/Managing Naughtiness")  •  [Fire Farm](/wiki/Guides/Fire_Farm "Guides/Fire Farm")  •  [Pig Farming](/wiki/Guides/Pig_Farming "Guides/Pig Farming")  •  [Berries and Turkey Farm](/wiki/Guides/Incredible_Inedible "Guides/Incredible Inedible")  •  [MeatFarm](/wiki/Guides/MeatFarm "Guides/MeatFarm") |
| **Survival** | [Adventure Mode](/wiki/Guides/Adventure_Guide "Guides/Adventure Guide")  •  [Mob Killing](/wiki/Guides/Mob_Killing_Guide "Guides/Mob Killing Guide")  •  [How To not starve](/wiki/Guides/How_to_not_starve "Guides/How to not starve")  •  [How to survive](/wiki/Guides/How_to_Survive "Guides/How to Survive")  •  [Hound Wave Survival](/wiki/Guides/Hound_Wave_Survival_Guide "Guides/Hound Wave Survival Guide")  •  [Mushroom Guide](/wiki/Guides/Mushroom_Guide "Guides/Mushroom Guide")  •  [Boss Drops](/wiki/Guides/What_To_Do_With_Boss_Drops "Guides/What To Do With Boss Drops")  •  [Spelunking Guide](/wiki/Guides/Spelunking_Guide "Guides/Spelunking Guide")  •  [Winter Guide](/wiki/Guides/Winter_Guide "Guides/Winter Guide")  •  [Crock Pot Dishes](/wiki/Guides/Crock_Pot_Dishes "Guides/Crock Pot Dishes")  •  [Panic Room Guide](/wiki/Guides/Panic_Room "Guides/Panic Room")  •  [Just Spawned](/wiki/Guides/You_Have_Just_Spawned,_Now_What%3F%3F "Guides/You Have Just Spawned, Now What??")  •  [Advanced World](/wiki/Guides/Advanced_World "Guides/Advanced World")  •  [World vs Caves](/wiki/Guides/World_vs_Caves "Guides/World vs Caves")  •  [Preparing for Deerclops](/wiki/Guides/Preparing_for_Deerclops "Guides/Preparing for Deerclops")  •  [Advanced Hound Protection](/wiki/Guides/Advanced_Hound_Protection "Guides/Advanced Hound Protection")  •  [Combatting Bosses](/wiki/Guides/Combatting_Bosses "Guides/Combatting Bosses")  •  [Killing Dragonfly DST](/wiki/Guides/Killing_Dragonfly_DST "Guides/Killing Dragonfly DST")  •  [Making your Overworld a better place.](/wiki/Guides/Making_your_Overworld_a_better_place. "Guides/Making your Overworld a better place.")  •  [Maximum Efficiency Day 13 Base DST Guide](/wiki/Guides/Maximum_Efficiency_Day_13_Base_DST_Guide "Guides/Maximum Efficiency Day 13 Base DST Guide")  •  [Picking your armor](/wiki/Guides/Picking_your_armor "Guides/Picking your armor")  •  [Surviving the winter in DST](/wiki/Guides/Surviving_the_winter_in_DST "Guides/Surviving the winter in DST")  •  [The Forge](/wiki/Guides/The_Forge "Guides/The Forge")  •  [Making Bigger and Better Weapons](/wiki/Guides/Making_Bigger_and_Better_Weapons "Guides/Making Bigger and Better Weapons")  (*[Surviving in a New Server](/wiki/Guides/Surviving_in_a_New_Server "Guides/Surviving in a New Server"))* |
| **Technical** | [Physical Damage Absorption](/wiki/Guides/Physical_Damage_Absorption "Guides/Physical Damage Absorption")  •  [Console Commands](/wiki/Guides/Console "Guides/Console")  (*[Automatically Start Dedicated Server (Linux)](/wiki/Guides/Automatically_Start_Dedicated_Server_(Linux) "Guides/Automatically Start Dedicated Server (Linux)")  •  [Simple Dedicated Server Setup](/wiki/Guides/Simple_Dedicated_Server_Setup "Guides/Simple Dedicated Server Setup")  •  **Don’t Starve Together Dedicated Servers*** Don't Starve Together) |
| **Characters** | [Surviving 101 With Wilson P. Higgsbury (And Other People)](/wiki/Guides/Surviving_101_With_Wilson_P._Higgsbury_(And_Other_People) "Guides/Surviving 101 With Wilson P. Higgsbury (And Other People)")  •  [Willow](/wiki/Guides/Character_guide-Willow "Guides/Character guide-Willow")  •  [Wolfgang](/wiki/Guides/Character_guide_-_Wolfgang,_The_Strongman "Guides/Character guide - Wolfgang, The Strongman")  •  [Wendy](/wiki/Guides/Character_guides-Wendy "Guides/Character guides-Wendy")  •  [WX-78 survival](/wiki/Guides/WX-78_survival "Guides/WX-78 survival")  •  [Wickerbottom](/wiki/Guides/Wickerbottom "Guides/Wickerbottom")  •  [Wickerbottom's Books](/wiki/Guides/Character_guide_-_Wickerbottom%27s_Books "Guides/Character guide - Wickerbottom's Books")  •  [Woodie's Curse](/wiki/Guides/Woodie%27s_Curse "Guides/Woodie's Curse")  •  [Wigfrid](/wiki/User_blog:Cmshaw/Adventure_Mode_with_Wigfrid_Guide "User blog:Cmshaw/Adventure Mode with Wigfrid Guide")  •  [Maxwell](/wiki/Guides/Character_Guide_-_Maxwell "Guides/Character Guide - Maxwell")  •  [Guide to Don't Starve Lore](/wiki/Guides/Guide_to_Don%27t_Starve_Lore "Guides/Guide to Don't Starve Lore") |