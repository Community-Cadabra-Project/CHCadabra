package org.cadabra.chcadabra.events;

import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.abstraction.MCItemStack;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.ArgumentValidation;
import com.laytonsmith.core.MSVersion;
import com.laytonsmith.core.ObjectGenerator;
import com.laytonsmith.core.constructs.*;
import com.laytonsmith.core.events.AbstractEvent;
import com.laytonsmith.core.events.BindableEvent;
import com.laytonsmith.core.events.Driver;
import com.laytonsmith.core.events.prefilters.*;
import com.laytonsmith.core.exceptions.EventException;
import com.laytonsmith.core.natives.interfaces.Mixed;
import org.cadabra.chcadabra.events.abstraction.MCEntityBreedEvent;
import org.cadabra.chcadabra.events.abstraction.MCPlayerBucketEmptyEvent;
import org.cadabra.chcadabra.events.abstraction.MCPlayerBucketFillEvent;
import org.cadabra.chcadabra.events.abstraction.MCPlayerItemBreakEvent;

import java.util.Map;

public class Events {
    public static String docs() {
        return "This augments CommandHelper with my events from bukkit";
    }

    @api
    public static class player_bucket_empty extends AbstractEvent {

        @Override
        public String getName() {
            return "player_bucket_empty";
        }

        @Override
        public String docs() {
            return "{} "
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
            return MSVersion.V3_3_5;
        }

        @Override
        protected PrefilterBuilder getPrefilterBuilder() {
            return new PrefilterBuilder<MCPlayerBucketEmptyEvent>()
                    .set("dropitem", "The item that will be dropped after the action.", new ItemStackPrefilterMatcher<MCPlayerBucketEmptyEvent>() {
                        @Override
                        protected MCItemStack getItemStack(MCPlayerBucketEmptyEvent event) {
                            return event.getItemStack();
                        }
                    })
                    .set("bucket", "Bucket name.", new StringPrefilterMatcher<MCPlayerBucketEmptyEvent>() {
                        @Override
                        protected String getProperty(MCPlayerBucketEmptyEvent event) {
                            return event.getBucket().getName();
                        }
                    })
                    .set("facing", "The side of the clicked block.", new StringPrefilterMatcher<MCPlayerBucketEmptyEvent>() {
                        @Override
                        protected String getProperty(MCPlayerBucketEmptyEvent event) {
                            return event.getBlockFace().toString();
                        }
                    });
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
            return "{} "
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
            return MSVersion.V3_3_5;
        }

        @Override
        protected PrefilterBuilder getPrefilterBuilder() {
            return new PrefilterBuilder<MCPlayerBucketFillEvent>()
                    .set("dropitem", "The item that will be dropped after the action.", new ItemStackPrefilterMatcher<MCPlayerBucketFillEvent>() {
                        @Override
                        protected MCItemStack getItemStack(MCPlayerBucketFillEvent event) {
                            return event.getItemStack();
                        }
                    })
                    .set("bucket", "Bucket name.", new StringPrefilterMatcher<MCPlayerBucketFillEvent>() {
                        @Override
                        protected String getProperty(MCPlayerBucketFillEvent event) {
                            return event.getBucket().getName();
                        }
                    })
                    .set("facing", "The side of the clicked block.", new StringPrefilterMatcher<MCPlayerBucketFillEvent>() {
                        @Override
                        protected String getProperty(MCPlayerBucketFillEvent event) {
                            return event.getBlockFace().toString();
                        }
                    });
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
    public static class item_break extends AbstractEvent {

        @Override
        public String getName() {
            return "item_break";
        }

        @Override
        public String docs() {
            return "{} "
                    + "Fired when a player's item breaks (such as a shovel or flint and steel). "
                    + "After this event, the item's amount will be set to item amount - 1 and its durability will be reset to 0. "
                    + "{player: The player | item: An item array of the item being broke}"
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
            return MSVersion.V3_3_5;
        }

        @Override
        protected PrefilterBuilder getPrefilterBuilder() {
            return new PrefilterBuilder<MCPlayerItemBreakEvent>()
                    .set("itemname", "The name of the item being broke.", new StringPrefilterMatcher<MCPlayerItemBreakEvent>() {
                        @Override
                        protected String getProperty(MCPlayerItemBreakEvent event) {
                            return event.getBrokenItem().getType().getName();
                        }
                    })
                    .set("player", "The player holding item.", new PlayerPrefilterMatcher<>());
        }

        @Override
        public Map<String, Mixed> evaluate(BindableEvent e) throws EventException {
            if (e instanceof MCPlayerItemBreakEvent) {
                MCPlayerItemBreakEvent event = (MCPlayerItemBreakEvent) e;
                Map<String, Mixed> map = evaluate_helper(e);

                map.put("player", new CString(event.getPlayer().getName(), Target.UNKNOWN));
                map.put("item", ObjectGenerator.GetGenerator().item(event.getBrokenItem(), Target.UNKNOWN));

                return map;
            }
            throw new EventException("Cannot convert e to ItemBreakEvent");
        }

        @Override
        public boolean modifyEvent(String key, Mixed value, BindableEvent e) {
            return false;
        }
    }

    @api
    public static class entity_breed extends AbstractEvent {

        @Override
        public String getName() {
            return "entity_breed";
        }

        @Override
        public String docs() {
            return "{} "
                    + "Called when one Entity breeds with another Entity."
                    + "{child: The child UUID | mother: The mother UUID | father: The father UUID"
                    + "| breeder: The UUID of breeder responsible for breeding. Breeder is null for spontaneous conception."
                    + "| item: The ItemStack that was used to initiate breeding, if present."
                    + "| xp}"
                    + "{xp: the amount of xp to drop} "
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
            return MSVersion.V3_3_5;
        }

        @Override
        protected PrefilterBuilder getPrefilterBuilder() {
            return new PrefilterBuilder<MCEntityBreedEvent>()
                    .set("type", "The type of entities", new MacroPrefilterMatcher<MCEntityBreedEvent>() {
                        @Override
                        protected Object getProperty(MCEntityBreedEvent event) {
                            return event.getFather().getType().name();
                        }
                    });
        }

        @Override
        public Map<String, Mixed> evaluate(BindableEvent e) throws EventException {
            if (e instanceof MCEntityBreedEvent) {
                MCEntityBreedEvent event = (MCEntityBreedEvent) e;
                Map<String, Mixed> map = evaluate_helper(e);
                final Target t = Target.UNKNOWN;

                map.put("child", new CString(event.getChild().getUniqueId().toString(), t));
                map.put("mother", new CString(event.getMother().getUniqueId().toString(), t));
                map.put("father", new CString(event.getFather().getUniqueId().toString(), t));
                event.getBreeder().ifPresent(breeder ->
                        map.put("breeder", new CString(breeder.getUniqueId().toString(), t))
                );
                event.getBredWith().ifPresent(item ->
                        map.put("item", ObjectGenerator.GetGenerator().item(item, t))
                );
                map.put("xp", new CInt(event.getExperience(), t));

                return map;
            }
            throw new EventException("Cannot convert e to EntityBreedEvent");
        }

        @Override
        public boolean modifyEvent(String key, Mixed value, BindableEvent e) {
            MCEntityBreedEvent event = (MCEntityBreedEvent) e;

            if(key.equals("xp")) {
                int exp = ArgumentValidation.getInt32(value, value.getTarget());
                if(exp >= 0) {
                    event.setExperience(exp);
                    return true;
                }
            }

            return false;
        }
    }
}
