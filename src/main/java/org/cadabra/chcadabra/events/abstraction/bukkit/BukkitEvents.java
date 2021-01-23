package org.cadabra.chcadabra.events.abstraction.bukkit;

import com.laytonsmith.abstraction.*;
import com.laytonsmith.abstraction.blocks.MCBlock;
import com.laytonsmith.abstraction.blocks.MCBlockFace;
import com.laytonsmith.abstraction.blocks.MCBlockState;
import com.laytonsmith.abstraction.blocks.MCMaterial;
import com.laytonsmith.abstraction.bukkit.BukkitConvertor;
import com.laytonsmith.abstraction.bukkit.BukkitMCItemStack;
import com.laytonsmith.abstraction.bukkit.blocks.BukkitMCBlock;
import com.laytonsmith.abstraction.bukkit.blocks.BukkitMCBlockState;
import com.laytonsmith.abstraction.bukkit.blocks.BukkitMCMaterial;
import com.laytonsmith.abstraction.bukkit.entities.BukkitMCLivingEntity;
import com.laytonsmith.abstraction.bukkit.events.BukkitEntityEvents;
import com.laytonsmith.abstraction.enums.bukkit.BukkitMCBlockFace;
import com.laytonsmith.abstraction.enums.bukkit.BukkitMCEntityType;
import com.laytonsmith.abstraction.enums.bukkit.BukkitMCPotionEffectType;
import com.laytonsmith.annotations.abstraction;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.cadabra.chcadabra.events.abstraction.MCBlockFadeEvent;
import org.cadabra.chcadabra.events.abstraction.MCEntityPotionEffectEvent;
import org.cadabra.chcadabra.events.abstraction.MCPlayerBucketEmptyEvent;
import org.cadabra.chcadabra.events.abstraction.MCPlayerBucketFillEvent;

import java.util.Optional;

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
    public static class BukkitEntityPotionEffectEvent implements MCEntityPotionEffectEvent {

        EntityPotionEffectEvent e;

        public BukkitEntityPotionEffectEvent(Event e) {
            this.e = (EntityPotionEffectEvent) e;
        }

        @Override
        public MCLivingEntity getEntity() {
            return new BukkitMCLivingEntity(e.getEntity());
        }

        @Override
        public EntityPotionEffectEvent.Action getAction() {
            return e.getAction();
        }

        @Override
        public EntityPotionEffectEvent.Cause getCause() {
            return e.getCause();
        }

        @Override
        public Optional<MCLivingEntity.MCEffect> getNewEffect() {
            PotionEffect pe = e.getNewEffect();
            return getEffect(pe);
        }

        @Override
        public Optional<MCLivingEntity.MCEffect> getOldEffect() {
            PotionEffect pe = e.getOldEffect();
            return getEffect(pe);
        }

        private Optional<MCLivingEntity.MCEffect> getEffect(PotionEffect pe) {
            return pe == null
                    ? Optional.empty()
                    : Optional.of(
                    new MCLivingEntity.MCEffect(
                            BukkitMCPotionEffectType.valueOfConcrete(
                                    pe.getType()
                            ),
                            pe.getAmplifier(),
                            pe.getDuration(),
                            pe.isAmbient(),
                            pe.hasParticles(),
                            pe.hasIcon()
                    ));
        }

        @Override
        public Object _GetObject() {
            return e;
        }
    }
}
