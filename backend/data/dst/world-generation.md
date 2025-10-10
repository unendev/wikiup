---
game_version: dst
category: Uncategorized
source_url: https://dontstarve.fandom.com/wiki/World_Generation
---

[World](/wiki/World "World") generation is the process that generates the layout and contents of the world. The general flow of how a world generation run goes is:

1. For a given **Preset**, **Tasks** are selected; some mandatory, and a few optional ones.
2. Each task generates a set of **Rooms**, and then linked together.
3. Tasks get linked together based on **Locks** and **Keys**. Tasks that don't get a key for each of their locks get discarded.
4. Background rooms are added to each task.
5. Set pieces (both mandatory and optional) and teleportato pieces get assigned to rooms.
6. The world generates a spatial arrangement from the graph, and converts it to tiles.
7. Fixes islands in caves, and checks that rooms have enough space (restarting if not).
8. Entities are added at each of the nodes, and roads are drawn, avoiding certain rooms.

## Contents

* [1 Presets](#Presets)
* [2 Tasks](#Tasks)
* [3 Rooms](#Rooms)
* [4 Locks and Keys](#Locks_and_Keys)
* [5 Tags](#Tags)
* [6 Detailed Generation Description](#Detailed_Generation_Description)

## Presets[]

Each world generation preset has a different configuration that causes different contents. [Caves](/wiki/Caves "Caves"), [Ruins](/wiki/Ruins "Ruins"), and [Adventure Mode](/wiki/Adventure_Mode "Adventure Mode") levels have their own presets as well. Each preset is composed of *Tasks* (some mandatory, some optional) and *Set Pieces*.

* Survival (Don't Starve)
* Survival ([Reign of Giants](/wiki/Don%27t_Starve:_Reign_of_Giants "Don't Starve: Reign of Giants"))
* Survival ([Don't Starve Together](/wiki/Don%27t_Starve_Together "Don't Starve Together"))
* [Cave](/wiki/Cave "Cave")
* Ruins
* [A Cold Reception](/wiki/Adventure_Mode "Adventure Mode")
* [King of Winter](/wiki/Adventure_Mode "Adventure Mode")
* [The Game is Afoot](/wiki/World_Generation_Screen "World Generation Screen")
* [Archipelago](/wiki/Adventure_Mode "Adventure Mode")
* [Two Worlds](/wiki/Adventure_Mode "Adventure Mode")
* [Darkness](/wiki/Adventure_Mode "Adventure Mode")
* [Checkmate](/wiki/Adventure_Mode "Adventure Mode")

## Tasks[]

**Tasks** are written like "story elements"—overall goals for what the world should have. Each task specifies **Locks**, **Keys**, a selection of **Rooms** (including how many of each room can occur), and a background [turf](/wiki/Turf "Turf"), which determines what entities are **not** allowed there (e.g. no grass in a task with a rocky background). For example, a "story" snippet might be:

:   *Kill all the spiders; the pig village is in trouble! To get to the pig village, you must pass through a mountain pass; boulders block your path, so you must build a pickaxe. You need enough meat to persuade the pigs to help you fight the spiders, so that must be nearby as well.*

To satisfy this story, it would make two areas. The first would contain spiders, a pig village, a meat source; a **Lock** corresponding to this "story element" would exist in this **Task**. The second area would contain a rock source, a twigs and grass source, and a starting area (which contains some flint); these are generated as **Rooms**, but also represented by **Keys**. This provides you with the resources you need to make a pickaxe, which you can use to collect rocks, after which you'll find the pig village in peril, collect some meat, hire some pigs, and fight off the evil spiders.

## Rooms[]

**Rooms** are small biomes that contain certain contents; there are several rooms for each biome type, producing variation in the types of biomes encountered (for example, bee lands with or without killer beehives). When a **Room** is generated, a set of **Nodes** are created as well, spaced out from each other to ensure that objects in the room are distributed relatively evenly. **Rooms** specify **Tags**, specific numbers of certain entities, and probabilities for other entities to spawn at a given **Node**, and have a particular turf (or [Mosaic](/wiki/Mosaic "Mosaic"), a random mixture of most turfs).

## Locks and Keys[]

**Locks** and **Keys** specify how tasks can be connected to each other. Each **Key** can unlock one or more **Locks**. When linking the tasks together, it will try to add a task such that the tasks so far have keys for each of the new task's locks. However, if this is not possible, it will add a task at random, so the lock-and-key system is just used for prioritizing tasks. Tasks added later are more likely to be at edges of the world, and these will usually require keys provided by the center of the world.

## Tags[]

**Tags** are attached to rooms in order to mark them for the addition of certain special objects that require more attention than simply being spawned. For example, tags handle mazes in the [Ruins](/wiki/Ruins "Ruins"), mark areas as not being allowed to have [Roads](/wiki/Road "Road"), requiring extra or less connectivity with surrounding areas, being able to have the [Eye Bone](/wiki/Eye_Bone "Eye Bone"), or for certain kinds of wormholes.

## Detailed Generation Description[]

1. A **Preset** is used.
2. The **Tasks** are chosen for the world; all of the required tasks, and a random selection of the specified number of optional tasks (four of them for normal worlds).
   1. A certain number of random set pieces (five for normal worlds) are assigned to random tasks.
   2. For the mandatory set pieces, the specified number are each attached to one of the tasks they're allowed to attach to (specified in the preset data).
3. For each **Task** in the preset, a [graph](http://en.wikipedia.org/wiki/Graph_(mathematics)) of **Task Nodes** is created.
   1. Starting with one of the **Rooms** (an "entrance room" if specified by the task, sometimes with a probability, otherwise a room at random), it compiles a list of rooms, including duplicates as specified by the task.
   2. While there are rooms left to choose from, it removes a room from the list and generates its contents, attaching it to the last generated room in a (currently linear) graph of **Room Nodes** within the task.
   3. If the task specifies that it should have a loop, the linear graph is connected at the ends to make a loop.
   4. Some number of links between rooms are added, based on the task's degree of cross-linking (which defaults to 1).
      1. For up to 20 iterations, two room nodes are picked at random.
      2. If they are different, not already connected, and neither is an entrance room, then they are connected and the cross-linking degree is reduced by one.
      3. If this brings cross-linking degree to zero, then cross-linking stops.
4. If any tasks have no **Locks**, then they are added to a list; one of these is picked as the first task. Otherwise, a task is picked at random. Starting with this task, a loop is run until no tasks are left. The rest of the tasks are compiled into a list of unused tasks in a random order.
   1. It makes a table of the **Keys** the currently attached tasks provide, and which tasks provides them (at first, only the start node provides keys).
   2. It looks through each of the unused tasks until it finds one that can be fully unlocked. If no task got fully unlocked, then it picks one at random.
   3. The selected task is removed from the list of unused tasks and attached to one of the already added tasks, depending on the degree of land branching set (because the connected tasks are arranged in a [tree](http://en.wikipedia.org/wiki/Tree_(graph_theory)), it calculates the depth of the task—how many steps into the tree it is from the first one—and the higher the branching is set, the closer to the first node it attaches it).
   4. Based on the island chance, it may separate the task it just added from land, as long as it's not the entrance task.
5. Based on a chance to loop the land (defaulting to 50%), a loop is added to the task node graph (which is otherwise a tree), by linking the first task to the last task. Note that this loop is closed by a LOOP\_BLANK task, which is composed entirely of impassable tiles (water in the surface world, emptiness in caves/ruins). So this will produce horseshoe-shaped land, rather than a circle.
6. The backgrounds of each of the tasks are filled in. For each room node within a task, 0 to 2 background rooms are used, by default (certain presets may define their own numbers of background rooms). Entrance tasks, however, always use 1-2 BLOCKER\_BLANK rooms instead, which are composed of water (or empty space).
7. For each of the tasks, if a set piece had been assigned to them earlier, it will try to assign the set piece to a given room (rejecting entrance and blank rooms, and in some cases requiring that it be a background room). It's possible for this assignment to fail, in which case the set piece is not used.
8. Teleportato parts are assigned to room nodes if relevant.
9. The overall physical arrangement of the map is generated as a [Voronoi Map](http://en.wikipedia.org/wiki/Voronoi_diagram). This can fail, but will attempt five times before giving up.
   1. The world is broken down into tiles.
   2. Islands are detected, and joined to the mainland if it's a cave.
   3. Room sizes are checked to make sure they're large enough to fit what they need to. If not, world generation is restarted.
   4. Entities are filled in at each of their assigned nodes, now that they have physical positions.
   5. Roads are drawn, avoiding rooms that are marked as having "road poison".