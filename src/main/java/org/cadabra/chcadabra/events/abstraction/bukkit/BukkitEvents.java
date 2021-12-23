package org.cadabra.chcadabra.events.abstraction.bukkit;

import com.laytonsmith.abstraction.*;
import com.laytonsmith.abstraction.blocks.MCBlock;
import com.laytonsmith.abstraction.blocks.MCBlockFace;
import com.laytonsmith.abstraction.blocks.MCMaterial;
import com.laytonsmith.abstraction.bukkit.BukkitMCItemStack;
import com.laytonsmith.abstraction.bukkit.blocks.BukkitMCBlock;
import com.laytonsmith.abstraction.bukkit.blocks.BukkitMCMaterial;
import com.laytonsmith.abstraction.bukkit.entities.BukkitMCEntity;
import com.laytonsmith.abstraction.bukkit.entities.BukkitMCLivingEntity;
import com.laytonsmith.abstraction.bukkit.entities.BukkitMCPlayer;
import com.laytonsmith.abstraction.enums.bukkit.BukkitMCBlockFace;
import com.laytonsmith.annotations.abstraction;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.cadabra.chcadabra.events.abstraction.MCEntityBreedEvent;
import org.cadabra.chcadabra.events.abstraction.MCPlayerBucketEmptyEvent;
import org.cadabra.chcadabra.events.abstraction.MCPlayerBucketFillEvent;
import org.cadabra.chcadabra.events.abstraction.MCPlayerItemBreakEvent;

import java.util.List;
import java.util.Optional;

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

    @abstraction(type = Implementation.Type.BUKKIT)
    public static class BukkitEntityBreedEvent implements MCEntityBreedEvent {
        EntityBreedEvent e;

        public BukkitEntityBreedEvent(EntityBreedEvent e) {
            this.e = e;
        }

        @Override
        public MCLivingEntity getChild() {
            return new BukkitMCLivingEntity(e.getEntity());
        }

        @Override
        public MCLivingEntity getMother() {
            return new BukkitMCLivingEntity(e.getMother());
        }

        @Override
        public MCLivingEntity getFather() {
            return new BukkitMCLivingEntity(e.getFather());
        }

        @Override
        public Optional<MCLivingEntity> getBreeder() {
            return Optional.ofNullable(e.getBreeder())
                    .map(BukkitMCLivingEntity::new);
        }

        @Override
        public Optional<MCItemStack> getBredWith() {
            return Optional.ofNullable(e.getBredWith())
                    .map(BukkitMCItemStack::new);
        }

        @Override
        public int getExperience() {
            return e.getExperience();
        }

        @Override
        public void setExperience(int experience) {
            e.setExperience(experience);
        }

        @Override
        public Object _GetObject() {
            return e;
        }
    }
}
