package org.cadabra.chcadabra.events.abstraction.bukkit;

import com.laytonsmith.commandhelper.CommandHelperPlugin;
import com.laytonsmith.core.events.Driver;
import com.laytonsmith.core.events.EventUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;

public class BukkitListener implements Listener {

    private static BukkitListener listener;

    public static void register() {
        if (listener == null) {
            listener = new BukkitListener();
        }
        CommandHelperPlugin.self.registerEvents(listener);
    }

    public static void unregister() {
        BlockFormEvent.getHandlerList().unregister(listener);
        PlayerBucketEmptyEvent.getHandlerList().unregister(listener);
        PlayerBucketFillEvent.getHandlerList().unregister(listener);
        EntityPotionEffectEvent.getHandlerList().unregister(listener);
        PlayerItemBreakEvent.getHandlerList().unregister(listener);
        EntityBreedEvent.getHandlerList().unregister(listener);
    }

    @EventHandler(priority= EventPriority.LOWEST)
    public void onPlayerBucketEmpty(PlayerBucketEmptyEvent event) {
        BukkitEvents.BukkitPlayerBucketEmptyEvent empty = new BukkitEvents.BukkitPlayerBucketEmptyEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "player_bucket_empty", empty);
    }

    @EventHandler(priority= EventPriority.LOWEST)
    public void onPlayerBucketFill(PlayerBucketFillEvent event) {
        BukkitEvents.BukkitPlayerBucketFillEvent fill = new BukkitEvents.BukkitPlayerBucketFillEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "player_bucket_fill", fill);
    }

    @EventHandler(priority= EventPriority.LOWEST)
    public void onPlayerBucketFill(PlayerItemBreakEvent event) {
        BukkitEvents.BukkitPlayerItemBreakEvent itemBreak = new BukkitEvents.BukkitPlayerItemBreakEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "item_break", itemBreak);
    }

    @EventHandler(priority= EventPriority.LOWEST)
    public void onEntityBreed(EntityBreedEvent event) {
        BukkitEvents.BukkitEntityBreedEvent entityBreed = new BukkitEvents.BukkitEntityBreedEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "entity_breed", entityBreed);
    }
}
