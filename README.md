# Events

This augments CommandHelper with my events from bukkit

|**Event Name**| **Description** | **Prefilters** | **Event Data** | **Mutable Fields** |
|---|---|---|---|---|
block\_form | Called when a block is formed or spreads based on world conditions. Cancelling this event block will not be formed. | **block**: \<String Match\> | **location**: The location the block formed<br>**block**: Type of old block state<br>**newblock**: Type of new block state |  |
|entity\_potion|Called when a potion effect is modified on an entity. If the event is cancelled, no change will be made on the entity.| **action**: \<String Match\><br>**cause**: \<String Match\>|**action**: The action which will be performed on the potion effect type<br>**cause**: The cause why the effect has changed<br>**newPotion**: The new potion effect of the changed type which will be applied<br>**oldPotion**: The old potion effect of the changed type, which will be removed<br>**id**: UUID entity| |
|player\_bucket\_empty|Called when a player empties a bucket.|**dropitem**: \<String Match\>  <br>**bucket**: \<String Match\>  <br>**facing**: \<String Match\>|**block**: The block to be emptied  <br>**blockclicked**: The block clicked with the bucket  <br>**location**: The location of emptied block  <br>**clicked**: Сlick location  <br>**dropitem**: The item that will be dropped after the action  <br>**facing**: The side of the clicked block  <br>**bucket**: Bucket name| |
|player\_bucket\_fill|Called when a player empties a bucket.|**dropitem**: \<String Match\>  <br>**bucket**: \<String Match\>  <br>**facing**: \<String Match\>|**block**: The block to be emptied  <br>**blockclicked**: The block clicked with the bucket  <br>**location**: The location of emptied block  <br>**clicked**: Сlick location  <br>**dropitem**: The item that will be dropped after the action  <br>**facing**: The side of the clicked block  <br>**bucket**: Bucket name|**dropitem**|
