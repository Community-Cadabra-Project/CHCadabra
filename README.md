# Functions

## Env

A set of functions for manipulate the environment: export, import, clear.

### void x\_clear\_env():

Removes all variables and procedures from the environment. Convenient before initializing the environment to be exported.

### boolean x\_export\_env(string key):

Exports the current key-bound environment.

### boolean x\_import\_env(string key):

Imports the environment associated with this key. Can overwrite current procedures and variables!

### array x\_keys\_env():

Returns all keys associated with environments.

### boolean x\_remove\_env(string key):

Deletes the exported environment by key.

## Executes

A set of functions for specific execution of closures

### mixed execute\_current([values...], closure):

Executes the given closure like execute(), but with current environment. WARNING: the closure must use already defined variables and procedures to avoid compilation errors.

## Threading

A set of functions for interacting with threads.

### array dump\_keys\_threads():

Returns an array of all threads keys that are currently running in the JVM.

### void x\_safe\_execute([values...], closure):

Executes the given closure. You can also send arguments to the closure, which it may or may not use, depending on the particular closure's definition. Unlike closure, it returns only void and will be executed even if the thread was stopped by a functionx_stop_thread().

### boolean x\_stop\_thread(string id):

Stopping tracked thread named 'id'. If successful, returns true, else false. If the thread performs a x_safe_execute() function, the interrupting thread will wait for execution.

# Events

## MyEvents

This augments CommandHelper with my events from bukkit

|**Event Name**| **Description** | **Prefilters** | **Event Data** | **Mutable Fields** |
|---|---|---|---|---|
block\_form | Called when a block is formed or spreads based on world conditions. Cancelling this event block will not be formed. | **block**: \<String Match\> | **location**: The location the block formed<br>**block**: Type of old block state<br>**newblock**: Type of new block state |  |
|entity\_potion|Called when a potion effect is modified on an entity. If the event is cancelled, no change will be made on the entity.| **action**: \<String Match\><br>**cause**: \<String Match\>|**action**: The action which will be performed on the potion effect type<br>**cause**: The cause why the effect has changed<br>**newPotion**: The new potion effect of the changed type which will be applied<br>**oldPotion**: The old potion effect of the changed type, which will be removed<br>**id**: UUID entity| |
|player\_bucket\_empty|Called when a player empties a bucket.|**dropitem**: \<String Match\>  <br>**bucket**: \<String Match\>  <br>**facing**: \<String Match\>|**block**: The block to be emptied  <br>**blockclicked**: The block clicked with the bucket  <br>**location**: The location of emptied block  <br>**clicked**: Сlick location  <br>**dropitem**: The item that will be dropped after the action  <br>**facing**: The side of the clicked block  <br>**bucket**: Bucket name| |
|player\_bucket\_fill|Called when a player empties a bucket.|**dropitem**: \<String Match\>  <br>**bucket**: \<String Match\>  <br>**facing**: \<String Match\>|**block**: The block to be emptied  <br>**blockclicked**: The block clicked with the bucket  <br>**location**: The location of emptied block  <br>**clicked**: Сlick location  <br>**dropitem**: The item that will be dropped after the action  <br>**facing**: The side of the clicked block  <br>**bucket**: Bucket name|**dropitem**|
