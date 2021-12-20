package org.cadabra.chcadabra.functions;

import com.laytonsmith.abstraction.MCEntity;
import com.laytonsmith.abstraction.MCWorld;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.ArgumentValidation;
import com.laytonsmith.core.MSVersion;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.CBoolean;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.*;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.AbstractFunction;
import com.laytonsmith.core.natives.interfaces.Mixed;

public class Entity {
    public static String docs() {
        return "A set of functions for control entities.";
    }

    @api
    public static class entity_exists_in_world extends AbstractFunction {

        public entity_exists_in_world() {
        }

        public String getName() {
            return "entity_exists_in_world";
        }

        public Integer[] numArgs() {
            return new Integer[]{1, 2};
        }

        public String docs() {
            return "boolean {entityUUID, [world]} Checks if an entity exists in the world.";
        }

        public Class<? extends CREThrowable>[] thrown() {
            return new Class[]{CRECastException.class, CREInvalidWorldException.class};
        }

        public boolean isRestricted() {
            return true;
        }

        public MSVersion since() {
            return MSVersion.V3_3_4;
        }

        public Mixed exec(Target t, Environment env, Mixed... args) throws ConfigRuntimeException {
            String entity = ArgumentValidation.getStringObject(args[0], t);
            if(args.length == 1) {
                for(MCWorld w : Static.getServer().getWorlds()) {
                    for(MCEntity e : w.getEntities()) {
                        if(e.getUniqueId().toString().equalsIgnoreCase(entity)) {
                            return CBoolean.TRUE;
                        }
                    }
                }
            } else {
                MCWorld w = Static.getServer().getWorld(args[0].val());
                if(w == null) {
                    throw new CREInvalidWorldException("Unknown world: " + args[0].val(), t);
                }
                for(MCEntity e : w.getEntities()) {
                    if(e.getUniqueId().toString().equalsIgnoreCase(entity)) {
                        return CBoolean.TRUE;
                    }
                }
            }

            return CBoolean.FALSE;
        }

        public Boolean runAsync() {
            return null;
        }
    }
}

