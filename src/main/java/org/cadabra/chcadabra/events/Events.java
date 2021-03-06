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
import org.cadabra.chcadabra.events.abstraction.MCPlayerBucketEmptyEvent;
import org.cadabra.chcadabra.events.abstraction.MCPlayerBucketFillEvent;

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
}
