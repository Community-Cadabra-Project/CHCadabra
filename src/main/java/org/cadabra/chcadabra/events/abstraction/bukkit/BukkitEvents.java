package org.cadabra.chcadabra.events.abstraction.bukkit;

import com.laytonsmith.abstraction.Implementation;
import com.laytonsmith.abstraction.blocks.MCBlock;
import com.laytonsmith.abstraction.blocks.MCBlockState;
import com.laytonsmith.abstraction.bukkit.blocks.BukkitMCBlock;
import com.laytonsmith.abstraction.bukkit.blocks.BukkitMCBlockState;
import com.laytonsmith.annotations.abstraction;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockFormEvent;
import org.cadabra.chcadabra.events.abstraction.MCBlockFadeEvent;

public class BukkitEvents {

    @abstraction(type = Implementation.Type.BUKKIT)
    public static class BukkitBlockFormEventEvent implements MCBlockFadeEvent {
        BlockFormEvent e;

        public BukkitBlockFormEventEvent(Event e) {
            this.e = (BlockFormEvent) e;
        }

        @Override
        public MCBlock getBlock() {
            return new BukkitMCBlock(e.getBlock());
        }

        @Override
        public MCBlockState getNewState() {
            return new BukkitMCBlockState(e.getNewState());
        }

        @Override
        public Object _GetObject() {
            return e;
        }

    }
}
