# Events

This augments CommandHelper with my events from bukkit

|**Event Name**| **Description** | **Prefilters** | **Event Data** | **Mutable Fields** |
|---|---|---|---|---|
|player\_bucket\_empty|Called when a player empties a bucket.|**dropitem**: \<String Match\>  <br>**bucket**: \<String Match\>  <br>**facing**: \<String Match\>|**block**: The block to be emptied  <br>**blockclicked**: The block clicked with the bucket  <br>**location**: The location of emptied block  <br>**clicked**: Сlick location  <br>**dropitem**: The item that will be dropped after the action  <br>**facing**: The side of the clicked block  <br>**bucket**: Bucket name| |
|player\_bucket\_fill|Called when a player empties a bucket.|**dropitem**: \<String Match\>  <br>**bucket**: \<String Match\>  <br>**facing**: \<String Match\>|**block**: The block to be emptied  <br>**blockclicked**: The block clicked with the bucket  <br>**location**: The location of emptied block  <br>**clicked**: Сlick location  <br>**dropitem**: The item that will be dropped after the action  <br>**facing**: The side of the clicked block  <br>**bucket**: Bucket name|**dropitem**|
|item\_break|Fired when a player's item breaks (such as a shovel or flint and steel).<br>After this event, the item's amount will be set to item amount - 1 and its durability will be reset to 0.| **player**: \<string match\><br>**itemname**: \<string match\>|**player**: The player<br>**item**: An item array of the item being broke||

# Functions

## Entities

### boolean entity_exists_in_world(entityUUID, [world])

Checks if an entity exists in the world.
