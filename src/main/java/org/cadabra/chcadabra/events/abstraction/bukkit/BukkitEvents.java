package org.cadabra.chcadabra.events.abstraction.bukkit;

import com.laytonsmith.abstraction.*;
import com.laytonsmith.abstraction.blocks.MCBlock;
import com.laytonsmith.abstraction.blocks.MCBlockFace;
import com.laytonsmith.abstraction.blocks.MCMaterial;
import com.laytonsmith.abstraction.bukkit.BukkitMCItemStack;
import com.laytonsmith.abstraction.bukkit.blocks.BukkitMCBlock;
import com.laytonsmith.abstraction.bukkit.blocks.BukkitMCMaterial;
import com.laytonsmith.abstraction.bukkit.entities.BukkitMCPlayer;
import com.laytonsmith.abstraction.enums.bukkit.BukkitMCBlockFace;
import com.laytonsmith.annotations.abstraction;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.cadabra.chcadabra.events.abstraction.MCPlayerBucketEmptyEvent;
import org.cadabra.chcadabra.events.abstraction.MCPlayerBucketFillEvent;
import org.cadabra.chcadabra.events.abstraction.MCPlayerItemBreakEvent;

public class BukkitEvents {

    @abstraction(type = Implementation.Type.BUKKIT)
    public static class BukkitPlayerBucketEmptyEvent implements MCPlayerBucketEmptyEvent {

        PlayerBucketEmptyEvent e;

        public BukkitPlayerBucketEmptyEvent(Event e) {
            this.e = (PlayerBucketEmptyEvent) e;
        }

        @Override
        public MCBlock getBlock() {
            return new BukkitMCBlock(e.getBlock());
        }

        @Override
        public MCBlock getBlockClicked() {
            return new BukkitMCBlock(e.getBlockClicked());
        }

        @Override
        public MCBlockFace getBlockFace() {
            return BukkitMCBlockFace.getConvertor().getAbstractedEnum(e.getBlockFace());
        }

        @Override
        public MCMaterial getBucket() {
            return new BukkitMCMaterial(e.getBucket());
        }

        @Override
        public MCItemStack getItemStack() {
            return new BukkitMCItemStack(e.getItemStack());
        }

        @Override
        public void setMCItemStack(MCItemStack itemStack) {
            e.setItemStack((ItemStack) itemStack.getHandle());
        }

        @Override
        public Object _GetObject() {
            return e;
        }
    }

    @abstraction(type = Implementation.Type.BUKKIT)
    public static class BukkitPlayerBucketFillEvent implements MCPlayerBucketFillEvent {

        PlayerBucketFillEvent e;

        public BukkitPlayerBucketFillEvent(Event e) {
            this.e = (PlayerBucketFillEvent) e;
        }

        @Override
        public MCBlock getBlock() {
            return new BukkitMCBlock(e.getBlock());
        }

        @Override
        public MCBlock getBlockClicked() {
            return new BukkitMCBlock(e.getBlockClicked());
        }

        @Override
        public MCBlockFace getBlockFace() {
            return BukkitMCBlockFace.getConvertor().getAbstractedEnum(e.getBlockFace());
        }

        @Override
        public MCMaterial getBucket() {
            return new BukkitMCMaterial(e.getBucket());
        }

        @Override
        public MCItemStack getItemStack() {
            return new BukkitMCItemStack(e.getItemStack());
        }

        @Override
        public void setMCItemStack(MCItemStack itemStack) {
            e.setItemStack((ItemStack) itemStack.getHandle());
        }

        @Override
        public Object _GetObject() {
            return e;
        }
    }

    @abstraction(type = Implementation.Type.BUKKIT)
    public static class BukkitPlayerItemBreakEvent implements MCPlayerItemBreakEvent {
        PlayerItemBreakEvent e;

        public BukkitPlayerItemBreakEvent(Event e) {
            this.e = (PlayerItemBreakEvent) e;
        }

        @Override
        public MCItemStack getBrokenItem() {
            return new BukkitMCItemStack(e.getBrokenItem());
        }

        @Override
        public MCPlayer getPlayer() {
            return new BukkitMCPlayer(e.getPlayer());
        }

        @Override
        public Object _GetObject() {
            return e;
        }
    }
}
