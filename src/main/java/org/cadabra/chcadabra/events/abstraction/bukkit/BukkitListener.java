package org.cadabra.chcadabra.events.abstraction.bukkit;

import com.laytonsmith.commandhelper.CommandHelperPlugin;
import com.laytonsmith.core.events.Driver;
import com.laytonsmith.core.events.EventUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFormEvent;

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
    }

    @EventHandler(priority= EventPriority.LOWEST)
    public void onBlockForm(BlockFormEvent event) {
        BukkitEvents.BukkitBlockFormEventEvent form = new BukkitEvents.BukkitBlockFormEventEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "block_form", form);
    }
}
