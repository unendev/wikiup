---
game_version: dst
category: Uncategorized
source_url: https://dontstarve.fandom.com/wiki/How_to_extract_animations_from_the_game
---

Original post by [MMueck](/wiki/User_blog:MMueck/How_to_extract_animations_from_the_game "User blog:MMueck/How to extract animations from the game")

## Contents

* [1 1. Programs and Downloads](#1._Programs_and_Downloads)
* [2 2. Get the files](#2._Get_the_files)
* [3 3. Get krane to work](#3._Get_krane_to_work)
* [4 4. In Spriter](#4._In_Spriter)

## 1. Programs and Downloads[]

First, you need to download and install **Spriter**, a free 2D animation program from BrashMonkey. It can be used to access the sprite animations and export them for example. You can find and download the newest versions [here](https://brashmonkey.com/download_spriter/).

Second, you need to download Simplex' **ktools** to be able to convert the game files into Spriter project files. These are found [here](http://forums.kleientertainment.com/files/file/583-ktools-cross-platform-modding-tools-for-dont-starve/), but you need to have an user account at the [Klei Forums](http://forums.kleientertainment.com/) to be allowed to download them. Their readme with more detailed information can be found [here](https://github.com/nsimplex/ktools/blob/master/README.md).

ktools contains two programs: **ktech** and **krane**. ktech can be used to convert bidirectionally between [TEX](/wiki/TEX "TEX") files (without animations) and PNG, but this can also be more easily done using Matt's **[TEX Tools](http://forums.kleientertainment.com/files/file/73-matts-tools/)**. So krane is actually what we are looking for. It has recently been updated to support animations with more than four sides (for example the eight-faced walls in DST and SW).

## 2. Get the files[]

krane needs three input files in order to create a working Spriter project file. These can be found packed in zip files in:

*C:\Programs\Steam\steamapps\common\Don't Starve\data\anim*

or

*C:\Programs\Steam\steamapps\common\Don't Starve Together\data\anim*

Copy the zip file of your choice into another folder (always create a safety backup!), then unzip it. You should now have three files:

* *anim.bin*
* *atlas-0.tex*
* *build.bin*

If you are missing the *anim* file, then this object/mob/etc. you're trying to extract may have other versions or states in a different zip file. For example, Snow and Shadow Chester have their own *atlas* and *build*, but use the *anim* of the standard version, because their motions are the same. So you may need to copy another zip file and unzip it, but only use the *anim* file, be careful not to overwrite the other two.

## 3. Get krane to work[]

Now it's getting a bit weird. Open **cmd.exe** and enter the following input all in one line:

*Directory\Path\To\ktools\krane*

*Directory\Path\To\anim.bin*

*Directory\Path\To\build.bin*

*Directory\Path\To\Output\Folder\*

For example:

*C:\Users\MMueck>Downloads\ktools\krane*

*Downloads\Sample\anim.bin*

*Downloads\Sample\build.bin*

*Downloads\Sample\Output\*

Then execute. If the output folder doesn't exist yet, it will be created automatically.

If everything worked and krane says "Done.", you will find a bunch of folders in your chosen output folder and a **scml file**. This is the project file you can now open in Spriter.

## 4. In Spriter[]

Opening the project file, you should now see the object in the main animation window. In the down right corner you should see a list of available animations.

Let's say, if you need to extract only one frame for a wiki page for example, choose the *Idle* animation, then go to

*File > Export Animation To PNG/GIF...*

Set *Source frames > images* to **1** and then hit *Export*. You should now get a PNG file of the first frame of the *Idle* animation.

Of course, you could also export animations to GIF files and what not, but I have to admit, that this is all the experience I had with Spriter so far. So as I am not an expert, if there is any way to make this process easier or shorter, please let me know. Also if there are any questions or problems, please don't hesitate to ask. Catcoon

|  |  |
| --- | --- |
| **Resources for Creators** [view](/wiki/Template:Navbox "Template:Navbox") | |
| **Resources** | [Image Resources](/wiki/Image_Resources "Image Resources") • [Generic Quotes](/wiki/Generic_Quotes "Generic Quotes") |
| **Tutorials** | **How to extract animations from the game** |