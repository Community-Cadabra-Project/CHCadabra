package org.cadabra.chcadabra.events;

import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.MSVersion;
import com.laytonsmith.core.ObjectGenerator;
import com.laytonsmith.core.constructs.CArray;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.events.AbstractEvent;
import com.laytonsmith.core.events.BindableEvent;
import com.laytonsmith.core.events.Driver;
import com.laytonsmith.core.events.Prefilters;
import com.laytonsmith.core.exceptions.EventException;
import com.laytonsmith.core.exceptions.PrefilterNonMatchException;
import com.laytonsmith.core.natives.interfaces.Mixed;
import org.cadabra.chcadabra.events.abstraction.MCBlockFadeEvent;

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

}
