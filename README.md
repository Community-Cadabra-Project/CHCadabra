# Events

This augments CommandHelper with my events from bukkit

|**Event Name**| **Description** | **Prefilters** | **Event Data** | **Mutable Fields** |
|---|---|---|---|---|
|player\_bucket\_empty|Called when a player empties a bucket.|**dropitem**: \<String Match\>  <br>**bucket**: \<String Match\>  <br>**facing**: \<String Match\>|**block**: The block to be emptied  <br>**blockclicked**: The block clicked with the bucket  <br>**location**: The location of emptied block  <br>**clicked**: Сlick location  <br>**dropitem**: The item that will be dropped after the action  <br>**facing**: The side of the clicked block  <br>**bucket**: Bucket name| |
|player\_bucket\_fill|Called when a player empties a bucket.|**dropitem**: \<String Match\>  <br>**bucket**: \<String Match\>  <br>**facing**: \<String Match\>|**block**: The block to be emptied  <br>**blockclicked**: The block clicked with the bucket  <br>**location**: The location of emptied block  <br>**clicked**: Сlick location  <br>**dropitem**: The item that will be dropped after the action  <br>**facing**: The side of the clicked block  <br>**bucket**: Bucket name|**dropitem**|
|item\_break|Fired when a player's item breaks (such as a shovel or flint and steel).<br>After this event, the item's amount will be set to item amount - 1 and its durability will be reset to 0.| **player**: \<string match\><br>**itemname**: \<string match\>|**player**: The player<br>**item**: An item array of the item being broke||
|entity\_breed|Fired when one Entity breeds with another Entity.|**type**: \<String match\>|**child**: The child UUID <br> **mother**: The mother UUID <br> **father**: The father UUID <br> **breeder**: The UUID of breeder responsible for breeding. Breeder is null for spontaneous conception <br> **item**: The ItemStack that was used to initiate breeding, if present <br> **xp**| **xp**: the amount of xp to drop|

# Functions

## Entities

### boolean entity_exists_in_world(entityUUID, [world])

Checks if an entity exists in the world

#### boolean entity_in_love_mode(entityUUID)

Checks if an entity in love mode

#### void set_love_mode_ticks(entityUUID, ticks)

Set the amount of ticks for which this entity should be in love mode. Setting the love mode ticks to 600 is the equivalent of a player feeding the entity their breeding item of choice

#### void set_arrow_pickup(entityUUID, pickup)

Sets the current pickup status of this arrow

#### string get_arrow_pickup(entityUUID)

Gets the current pickup status of this arrow.
