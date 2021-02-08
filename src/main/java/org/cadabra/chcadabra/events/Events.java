package org.cadabra.chcadabra.events;

import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.abstraction.MCItemStack;
import com.laytonsmith.abstraction.MCLivingEntity;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.ArgumentValidation;
import com.laytonsmith.core.MSVersion;
import com.laytonsmith.core.ObjectGenerator;
import com.laytonsmith.core.constructs.*;
import com.laytonsmith.core.events.AbstractEvent;
import com.laytonsmith.core.events.BindableEvent;
import com.laytonsmith.core.events.Driver;
import com.laytonsmith.core.events.Prefilters;
import com.laytonsmith.core.exceptions.EventException;
import com.laytonsmith.core.exceptions.PrefilterNonMatchException;
import com.laytonsmith.core.natives.interfaces.Mixed;
import org.cadabra.chcadabra.events.abstraction.MCBlockFadeEvent;
import org.cadabra.chcadabra.events.abstraction.MCEntityPotionEffectEvent;
import org.cadabra.chcadabra.events.abstraction.MCPlayerBucketEmptyEvent;
import org.cadabra.chcadabra.events.abstraction.MCPlayerBucketFillEvent;

import java.util.Map;

public class Events {
    public static String docs() {
        return "This augments CommandHelper with my events from bukkit";
    }

    @api
    public static class block_form extends AbstractEvent {

        @Override
        public String getName() {
            return "block_form";
        }

        @Override
        public String docs() {
            return "{block: <string match>} "
                    + "Called when a block is formed or spreads based on world conditions. "
                    + "Cancelling this event block will not be formed."
                    + "{location: The location the block formed "
                    + " | block: Type of old block state"
                    + " | newblock: Type of new block state}"
                    + "{} "
                    + "{}";
        }

        @Override
        public Driver driver() {
            return Driver.EXTENSION;
        }

        @Override
        public BindableEvent convert(CArray manualObject, Target t) {
            return null;
        }

        @Override
        public Version since() {
            return MSVersion.V3_3_4;
        }

        @Override
        public boolean matches(Map<String, Mixed> prefilter, BindableEvent e) throws PrefilterNonMatchException {
            if (e instanceof MCBlockFadeEvent) {
                MCBlockFadeEvent event = (MCBlockFadeEvent)e;

                Prefilters.match(prefilter, "newblock", event.getNewState().getType().getName(), Prefilters.PrefilterType.STRING_MATCH);
                Prefilters.match(prefilter, "block", event.getBlock().getType().getName(), Prefilters.PrefilterType.STRING_MATCH);

                return true;
            }

            return false;
        }

        @Override
        public Map<String, Mixed> evaluate(BindableEvent e) throws EventException {
            if (!(e instanceof MCBlockFadeEvent)) {
                throw new EventException("Cannot convert event to MCBlockFadeEvent");
            } else {
                MCBlockFadeEvent event = (MCBlockFadeEvent) e;
                Target t = Target.UNKNOWN;
                Map<String, Mixed> mapEvent = this.evaluate_helper(e);
                mapEvent.put("block", new CString(event.getBlock().getType().getName(), t));
                mapEvent.put("newblock", new CString(event.getNewState().getType().getName(), t));
                mapEvent.put("location", ObjectGenerator.GetGenerator().location(event.getBlock().getLocation(), false));
                return mapEvent;
            }
        }

        @Override
        public boolean modifyEvent(String key, Mixed value, BindableEvent e) {
            return false;
        }
    }

    @api
    public static class player_bucket_empty extends AbstractEvent {

        @Override
        public String getName() {
            return "player_bucket_empty";
        }

        @Override
        public String docs() {
            return "{dropitem: <string match>"
                    + " | bucket: <string match>"
                    + " | facing: <string match>}"
                    + "Called when a player empties a bucket."
                    + "{block: The block to be emptied "
                    + " | blockclicked: The block clicked with the bucket"
                    + " | location: The location of emptied block"
                    + " | clicked: Сlick location"
                    + " | dropitem: The item that will be dropped after the action"
                    + " | facing: The side of the clicked block"
                    + " | bucket: Bucket name}"
                    + "{} "
                    + "{}";
        }

        @Override
        public Driver driver() {
            return Driver.EXTENSION;
        }

        @Override
        public BindableEvent convert(CArray manualObject, Target t) {
            return null;
        }

        @Override
        public Version since() {
            return MSVersion.V3_3_4;
        }

        @Override
        public boolean matches(Map<String, Mixed> prefilter, BindableEvent e) throws PrefilterNonMatchException {
            if (e instanceof MCPlayerBucketEmptyEvent) {
                MCPlayerBucketEmptyEvent event = (MCPlayerBucketEmptyEvent) e;

                Prefilters.match(prefilter, "dropitem", event.getItemStack().getType().getName(), Prefilters.PrefilterType.STRING_MATCH);
                Prefilters.match(prefilter, "bucket", event.getBucket().getName(), Prefilters.PrefilterType.STRING_MATCH);
                Prefilters.match(prefilter, "facing", event.getBlockFace().toString(), Prefilters.PrefilterType.STRING_MATCH);

                return true;
            }

            return false;
        }

        @Override
        public Map<String, Mixed> evaluate(BindableEvent e) throws EventException {
            if (!(e instanceof MCPlayerBucketEmptyEvent)) {
                throw new EventException("Cannot convert event to MCPlayerBucketEmptyEvent");
            } else {
                MCPlayerBucketEmptyEvent event = (MCPlayerBucketEmptyEvent) e;
                Target t = Target.UNKNOWN;
                Map<String, Mixed> mapEvent = this.evaluate_helper(e);
                mapEvent.put("block", new CString(event.getBlock().getType().getName(), t));
                mapEvent.put("blockclicked", new CString(event.getBlockClicked().getType().getName(), t));
                mapEvent.put("location", ObjectGenerator.GetGenerator().location(event.getBlock().getLocation(), false));
                mapEvent.put("clicked", ObjectGenerator.GetGenerator().location(event.getBlockClicked().getLocation(), false));
                mapEvent.put("dropitem", ObjectGenerator.GetGenerator().item(event.getItemStack(), t));
                mapEvent.put("facing", new CString(event.getBlockFace().toString(), t));
                mapEvent.put("bucket", new CString(event.getBucket().getName(), t));                                
                return mapEvent;
            }
        }

        @Override
        public boolean modifyEvent(String key, Mixed value, BindableEvent e) {
            return false;
        }
    }

    @api
    public static class player_bucket_fill extends AbstractEvent {

        @Override
        public String getName() {
            return "player_bucket_fill";
        }

        @Override
        public String docs() {
            return "{dropitem: <string match>"
                    + " | bucket: <string match>"
                    + " | facing: <string match>}"
                    + "Called when a player empties a bucket."
                    + "{block: The block to be emptied "
                    + " | blockclicked: The block clicked with the bucket"
                    + " | location: The location of emptied block"
                    + " | clicked: Сlick location"
                    + " | dropitem: The item that will be dropped after the action"
                    + " | facing: The side of the clicked block"
                    + " | bucket: Bucket name}"
                    + "{dropitem} "
                    + "{}";
        }

        @Override
        public Driver driver() {
            return Driver.EXTENSION;
        }

        @Override
        public BindableEvent convert(CArray manualObject, Target t) {
            return null;
        }

        @Override
        public Version since() {
            return MSVersion.V3_3_4;
        }

        @Override
        public boolean matches(Map<String, Mixed> prefilter, BindableEvent e) throws PrefilterNonMatchException {
            if (e instanceof MCPlayerBucketFillEvent) {
                MCPlayerBucketFillEvent event = (MCPlayerBucketFillEvent)e;

                Prefilters.match(prefilter, "dropitem", event.getItemStack().getType().getName(), Prefilters.PrefilterType.STRING_MATCH);
                Prefilters.match(prefilter, "bucket", event.getBucket().getName(), Prefilters.PrefilterType.STRING_MATCH);
                Prefilters.match(prefilter, "facing", event.getBlockFace().toString(), Prefilters.PrefilterType.STRING_MATCH);

                return true;
            }

            return false;
        }

        @Override
        public Map<String, Mixed> evaluate(BindableEvent e) throws EventException {
            if (!(e instanceof MCPlayerBucketFillEvent)) {
                throw new EventException("Cannot convert event to MCPlayerBucketFillEvent");
            } else {
                MCPlayerBucketFillEvent event = (MCPlayerBucketFillEvent) e;
                Target t = Target.UNKNOWN;
                Map<String, Mixed> mapEvent = this.evaluate_helper(e);
                mapEvent.put("block", new CString(event.getBlock().getType().getName(), t));
                mapEvent.put("blockclicked", new CString(event.getBlockClicked().getType().getName(), t));
                mapEvent.put("location", ObjectGenerator.GetGenerator().location(event.getBlock().getLocation(), false));
                mapEvent.put("clicked", ObjectGenerator.GetGenerator().location(event.getBlockClicked().getLocation(), false));
                mapEvent.put("dropitem", ObjectGenerator.GetGenerator().item(event.getItemStack(), t));
                mapEvent.put("facing", new CString(event.getBlockFace().toString(), t));
                mapEvent.put("bucket", new CString(event.getBucket().getName(), t));                
                return mapEvent;
            }
        }

        @Override
        public boolean modifyEvent(String key, Mixed value, BindableEvent e) {
            MCPlayerBucketFillEvent event = (MCPlayerBucketFillEvent) e;

            if(key.equals("dropitem")) {
                CArray item = ArgumentValidation.getArray(value, value.getTarget());
                MCItemStack itemStack = ObjectGenerator.GetGenerator().item(item, value.getTarget());

                event.setMCItemStack(itemStack);

                return true;
            }

            return false;
        }
    }

    @api
    public static class entity_potion extends AbstractEvent {

        @Override
        public String getName() {
            return "entity_potion";
        }

        @Override
        public String docs() {
            return "{action: <string match>"
                    + " | cause: <string match>}"
                    + "Called when a potion effect is modified on an entity. "
                    + "If the event is cancelled, no change will be made on the entity. "
                    + "{action: The action which will be performed on the potion effect type "
                    + " | cause: The cause why the effect has changed "
                    + " | newPotion: The new potion effect of the changed type which will be applied "
                    + " | oldPotion: The old potion effect of the changed type, which will be removed "
                    + " | id: UUID entity}"
                    + "{} "
                    + "{}";
        }

        @Override
        public Driver driver() {
            return Driver.EXTENSION;
        }

        @Override
        public BindableEvent convert(CArray manualObject, Target t) {
            return null;
        }

        @Override
        public Version since() {
            return MSVersion.V3_3_4;
        }

        @Override
        public boolean matches(Map<String, Mixed> prefilter, BindableEvent e) throws PrefilterNonMatchException {
            if (e instanceof MCEntityPotionEffectEvent) {
                MCEntityPotionEffectEvent event = (MCEntityPotionEffectEvent) e;

                Prefilters.match(prefilter, "action", event.getAction().toString(), Prefilters.PrefilterType.STRING_MATCH);
                Prefilters.match(prefilter, "cause", event.getCause().toString(), Prefilters.PrefilterType.STRING_MATCH);

                return true;
            }

            return false;
        }

        @Override
        public Map<String, Mixed> evaluate(BindableEvent e) throws EventException {
            if (!(e instanceof MCEntityPotionEffectEvent)) {
                throw new EventException("Cannot convert event to MCEntityPotionEffectEvent");
            } else {
                MCEntityPotionEffectEvent event = (MCEntityPotionEffectEvent) e;
                Target t = Target.UNKNOWN;
                Map<String, Mixed> mapEvent = this.evaluate_helper(e);
                mapEvent.put("id", new CString(event.getEntity().getUniqueId().toString(), t));
                mapEvent.put("newPotion", event.getNewEffect().map(ef -> (Mixed) potion(ef, t)).orElse(CNull.NULL));
                mapEvent.put("oldPotion", event.getOldEffect().map(ef -> (Mixed) potion(ef, t)).orElse(CNull.NULL));
                mapEvent.put("action", new CString(event.getAction().toString().toLowerCase(), t));
                mapEvent.put("cause", new CString(event.getCause().toString().toLowerCase(), t));
                return mapEvent;
            }
        }

        private CArray potion(MCLivingEntity.MCEffect effect, Target t) {
            CArray potion = CArray.GetAssociativeArray(t);
            potion.set("type", effect.getPotionEffectType().name().toLowerCase(), t);
            potion.set("id", new CInt((long) effect.getPotionEffectType().getId(), t), t);
            potion.set("strength", new CInt((long) effect.getStrength(), t), t);
            potion.set("seconds", new CDouble((double) effect.getTicksRemaining() / 20.0D, t), t);
            potion.set("ambient", CBoolean.get(effect.isAmbient()), t);
            potion.set("particles", CBoolean.get(effect.hasParticles()), t);

            return potion;
        }

        @Override
        public boolean modifyEvent(String key, Mixed value, BindableEvent e) {
            return false;
        }
    }
}
