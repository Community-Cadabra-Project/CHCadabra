package org.cadabra.chcadabra.functions;

import com.laytonsmith.abstraction.MCLocation;
import com.laytonsmith.abstraction.MCPlayer;
import com.laytonsmith.abstraction.blocks.MCBlock;
import com.laytonsmith.abstraction.bukkit.BukkitMCLocation;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.MSVersion;
import com.laytonsmith.core.ObjectGenerator;
import com.laytonsmith.core.constructs.CArray;
import com.laytonsmith.core.constructs.CInt;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.CommandHelperEnvironment;
import com.laytonsmith.core.exceptions.CRE.*;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.AbstractFunction;
import com.laytonsmith.core.natives.interfaces.Mixed;
import org.bukkit.block.EndGateway;

public class World {
    public static String docs() {
        return "Allows you to manipulate the environment around the player";
    }

    @api
    public static class get_end_gateway_data extends AbstractFunction {

        public get_end_gateway_data() {
        }

        public String getName() {
            return "get_end_gateway_data";
        }

        public Integer[] numArgs() {
            return new Integer[]{1};
        }

        public String docs() {
            return "mixed {locationArray} Return EndGateway block data";
        }

        public Class<? extends CREThrowable>[] thrown() {
            return new Class[]{CRECastException.class, CREFormatException.class};
        }

        public boolean isRestricted() {
            return true;
        }

        public MSVersion since() {
            return MSVersion.V3_3_5;
        }

        public Mixed exec(Target t, com.laytonsmith.core.environments.Environment env, Mixed... args) throws ConfigRuntimeException {
            MCPlayer p = (env.getEnv(CommandHelperEnvironment.class)).GetPlayer();
            MCLocation l = ObjectGenerator.GetGenerator().location(args[0], p == null ? null : p.getWorld(), t);
            MCBlock b = l.getBlock();
            Object o = b.getState().getHandle();
            EndGateway endGateway;
            if(o instanceof EndGateway) {
                endGateway = (EndGateway) o;
            } else {
                throw new CREIllegalArgumentException(String.format("Block %s in present location isn't EndGateway", o.getClass().getName()), t);
            }
            CArray data = CArray.GetAssociativeArray(t);
            data.set("Age", new CInt(endGateway.getAge(), t), t);
            data.set("ExitPortal", ObjectGenerator.GetGenerator().location(new BukkitMCLocation(endGateway.getExitLocation())), t);

            return data;
        }

        public Boolean runAsync() {
            return null;
        }
    }
}
