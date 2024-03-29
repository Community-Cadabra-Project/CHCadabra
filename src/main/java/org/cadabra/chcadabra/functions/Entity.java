package org.cadabra.chcadabra.functions;

import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Animals;
import org.cadabra.chcadabra.abstraction.enums.MCArrowPickupStatus;
import org.cadabra.chcadabra.abstraction.enums.bukkit.BukkitMCArrowPickupStatus;

import com.laytonsmith.abstraction.MCEntity;
import com.laytonsmith.abstraction.MCWorld;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.ArgumentValidation;
import com.laytonsmith.core.MSVersion;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.CBoolean;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.CVoid;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.exceptions.CRE.CREBadEntityException;
import com.laytonsmith.core.exceptions.CRE.CRECastException;
import com.laytonsmith.core.exceptions.CRE.CREFormatException;
import com.laytonsmith.core.exceptions.CRE.CREInvalidWorldException;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
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
            return "boolean {entityUUID, [world]} Checks if an entity exists in the world";
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
            if (args.length == 1) {
                for (MCWorld w : Static.getServer().getWorlds()) {
                    for (MCEntity e : w.getEntities()) {
                        if (e.getUniqueId().toString().equalsIgnoreCase(entity)) {
                            return CBoolean.TRUE;
                        }
                    }
                }
            } else {
                MCWorld w = Static.getServer().getWorld(args[0].val());
                if (w == null) {
                    throw new CREInvalidWorldException("Unknown world: " + args[0].val(), t);
                }
                for (MCEntity e : w.getEntities()) {
                    if (e.getUniqueId().toString().equalsIgnoreCase(entity)) {
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

    @api
    public static class entity_in_love_mode extends AbstractFunction {

        public entity_in_love_mode() {
        }

        public String getName() {
            return "entity_in_love_mode";
        }

        public Integer[] numArgs() {
            return new Integer[]{1};
        }

        public String docs() {
            return "boolean {entityUUID} Checks if an entity in love mode";
        }

        public Class<? extends CREThrowable>[] thrown() {
            return new Class[]{CREBadEntityException.class};
        }

        public boolean isRestricted() {
            return true;
        }

        public MSVersion since() {
            return MSVersion.V3_3_4;
        }

        public Mixed exec(Target t, Environment env, Mixed... args) throws ConfigRuntimeException {
            MCEntity mcEntity = Static.getEntity(args[0], t);
            Object entity = mcEntity.getHandle();
            if (entity instanceof Animals) {
                return CBoolean.get(((Animals) entity).isLoveMode());
            }

            return CBoolean.FALSE;
        }

        public Boolean runAsync() {
            return null;
        }
    }

    @api
    public static class set_love_mode_ticks extends AbstractFunction {

        public set_love_mode_ticks() {
        }

        public String getName() {
            return "set_love_mode_ticks";
        }

        public Integer[] numArgs() {
            return new Integer[]{2};
        }

        public String docs() {
            return "void {entityUUID, ticks} Set the amount of ticks for which this entity should be in love mode. "
                    + "Setting the love mode ticks to 600 is the equivalent of a player feeding the entity their breeding item of choice";
        }

        public Class<? extends CREThrowable>[] thrown() {
            return new Class[]{CREBadEntityException.class, CRECastException.class};
        }

        public boolean isRestricted() {
            return true;
        }

        public MSVersion since() {
            return MSVersion.V3_3_4;
        }

        public Mixed exec(Target t, Environment env, Mixed... args) throws ConfigRuntimeException {
            String id = ArgumentValidation.getString(args[0], t);
            int ticks = ArgumentValidation.getInt32(args[1], t);
            MCEntity mcEntity = Static.getEntity(args[0], t);
            Object entity = mcEntity.getHandle();
            if (entity instanceof Animals) {
                ((Animals) entity).setLoveModeTicks(ticks);
            } else {
                throw new CREBadEntityException("That entity (UUID: " + id + ") is not animal.", t);
            }

            return CVoid.VOID;
        }

        public Boolean runAsync() {
            return null;
        }
    }

    @api
    public static class set_arrow_pickup extends AbstractFunction {

        public set_arrow_pickup() {
        }

        public String getName() {
            return "set_arrow_pickup";
        }

        public Integer[] numArgs() {
            return new Integer[]{2};
        }

        public String docs() {
            return "void {entityUUID, status} Sets the current pickup status of this arrow";
        }

        public Class<? extends CREThrowable>[] thrown() {
            return new Class[]{CREBadEntityException.class, CRECastException.class};
        }

        public boolean isRestricted() {
            return true;
        }

        public MSVersion since() {
            return MSVersion.V3_3_4;
        }

        public Mixed exec(Target t, Environment env, Mixed... args) throws ConfigRuntimeException {
            MCEntity mcEntity = Static.getEntity(args[0], t);
            Object entity = mcEntity.getHandle();
            MCArrowPickupStatus pickup;

            try {
                pickup = MCArrowPickupStatus.valueOf(args[1].val().toUpperCase());
            } catch (IllegalArgumentException var7) {
                throw new CREFormatException("The pickup status \"" + args[1].val() + "\" does not exist.", t);
            }

            if (entity instanceof AbstractArrow) {
                ((AbstractArrow) entity).setPickupStatus(BukkitMCArrowPickupStatus.getConvertor().getConcreteEnum(pickup));
            } else {
                throw new CREBadEntityException("That entity (UUID: " + mcEntity.getUniqueId() + ", TYPE: " + mcEntity.getType() + ") is not abstract arrow.", t);
            }

            return CVoid.VOID;
        }

        public Boolean runAsync() {
            return null;
        }
    }

    @api
    public static class get_arrow_pickup extends AbstractFunction {

        public get_arrow_pickup() {
        }

        public String getName() {
            return "get_arrow_pickup";
        }

        public Integer[] numArgs() {
            return new Integer[]{1};
        }

        public String docs() {
            return "string {entityUUID} Gets the current pickup status of this arrow";
        }

        public Class<? extends CREThrowable>[] thrown() {
            return new Class[]{CREBadEntityException.class, CRECastException.class};
        }

        public boolean isRestricted() {
            return true;
        }

        public MSVersion since() {
            return MSVersion.V3_3_4;
        }

        public Mixed exec(Target t, Environment env, Mixed... args) throws ConfigRuntimeException {
            MCEntity mcEntity = Static.getEntity(args[0], t);
            Object entity = mcEntity.getHandle();

            if (entity instanceof AbstractArrow) {
                return new CString(
                        BukkitMCArrowPickupStatus.getConvertor().getAbstractedEnum(
                                ((AbstractArrow) entity).getPickupStatus()
                        ).toString(), t
                );
            } else {
                throw new CREBadEntityException("That entity (UUID: " + mcEntity.getUniqueId() + ", TYPE: " + mcEntity.getType() + ") is not abstract arrow.", t);
            }
        }

        public Boolean runAsync() {
            return null;
        }
    }
}
