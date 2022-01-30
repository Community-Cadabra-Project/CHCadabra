package org.cadabra.chcadabra.abstraction.enums.bukkit;

import com.laytonsmith.abstraction.Implementation;
import com.laytonsmith.abstraction.enums.EnumConvertor;
import com.laytonsmith.annotations.abstractionenum;
import org.bukkit.entity.AbstractArrow;
import org.cadabra.chcadabra.abstraction.enums.MCArrowPickupStatus;

@abstractionenum(
        implementation = Implementation.Type.BUKKIT,
        forAbstractEnum = MCArrowPickupStatus.class,
        forConcreteEnum = AbstractArrow.PickupStatus.class
)
public class BukkitMCArrowPickupStatus extends EnumConvertor<MCArrowPickupStatus, AbstractArrow.PickupStatus> {
    private static BukkitMCArrowPickupStatus instance;

    public BukkitMCArrowPickupStatus() {
    }

    public static BukkitMCArrowPickupStatus getConvertor() {
        if (instance == null) {
            instance = new BukkitMCArrowPickupStatus();
        }

        return instance;
    }
}
