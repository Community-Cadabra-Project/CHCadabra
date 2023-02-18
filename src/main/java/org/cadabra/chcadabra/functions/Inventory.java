package org.cadabra.chcadabra.functions;

import org.bukkit.inventory.ItemStack;

import com.laytonsmith.abstraction.MCItemStack;
import com.laytonsmith.abstraction.MCPlayer;
import com.laytonsmith.abstraction.MCPlayerInventory;
import com.laytonsmith.abstraction.bukkit.BukkitMCItemStack;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.ArgumentValidation;
import com.laytonsmith.core.MSVersion;
import com.laytonsmith.core.ObjectGenerator;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.CArray;
import com.laytonsmith.core.constructs.CBoolean;
import com.laytonsmith.core.constructs.CDouble;
import com.laytonsmith.core.constructs.CInt;
import com.laytonsmith.core.constructs.CNull;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.CVoid;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.CommandHelperEnvironment;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.exceptions.CRE.CRECastException;
import com.laytonsmith.core.exceptions.CRE.CREFormatException;
import com.laytonsmith.core.exceptions.CRE.CRENotFoundException;
import com.laytonsmith.core.exceptions.CRE.CRERangeException;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.functions.AbstractFunction;
import com.laytonsmith.core.natives.interfaces.Mixed;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import de.tr7zw.nbtapi.NBTList;
import de.tr7zw.nbtapi.NBTType;
import de.tr7zw.nbtapi.iface.ReadWriteNBT;

public class Inventory {

    public static String docs() {
        return "Allows you to manipulate items in player inventory";
    }

    static String mcItemStackToString(MCItemStack is) {
        ReadWriteNBT nbt = NBT.itemStackToNBT(((ItemStack) is.getHandle()));
        return nbt.toString();
    }

    static MCItemStack stringToMCItemStack(String s) {
        ReadWriteNBT nbt = NBT.parseNBT(s);
        ItemStack is = NBT.itemStackFromNBT(nbt);
        return new BukkitMCItemStack(is);
    }

    @api
    public static class set_pinv_extend extends AbstractFunction {

        public set_pinv_extend() {
        }

        public String getName() {
            return "set_pinv_extend";
        }

        public Integer[] numArgs() {
            return new Integer[] { 1, 2, 3 };
        }

        public String docs() {
            return "void {[player [slot]], array} Same as set_pinv, but provide custom data";
        }

        public Class<? extends CREThrowable>[] thrown() {
            return new Class[] { CRECastException.class, CREFormatException.class };
        }

        public boolean isRestricted() {
            return true;
        }

        public MSVersion since() {
            return MSVersion.V3_3_5;
        }

        public Mixed exec(Target t, com.laytonsmith.core.environments.Environment env, Mixed... args)
                throws ConfigRuntimeException {
            MCPlayer m;
            Mixed arg;
            if (args.length == 3) {
                // Single item
                m = Static.GetPlayer(args[0], t);
                MCItemStack is = mixedToItem(args[2], t);
                if (args[1] instanceof CNull) {
                    m.setItemInHand(is);
                } else {
                    setInvSlot(m.getInventory(), ArgumentValidation.getInt32(args[1], t), is);
                }
                return CVoid.VOID;
            } else if (args.length == 1) {
                m = env.getEnv(CommandHelperEnvironment.class).GetPlayer();
                Static.AssertPlayerNonNull(m, t);
                arg = args[0];
            } else {
                m = Static.GetPlayer(args[0], t);
                arg = args[1];
            }
            if (!(arg.isInstanceOf(CArray.TYPE))) {
                throw new CRECastException("Expecting an array as the last argument.", t);
            }

            CArray array = (CArray) arg;
            MCPlayerInventory inv = m.getInventory();
            for (String key : array.stringKeySet()) {
                if (key.isEmpty() || key.equals("null")) {
                    // It was a null key
                    MCItemStack is = mixedToItem(array.get("", t), t);
                    m.setItemInHand(is);
                } else {
                    try {
                        int index = Integer.parseInt(key);
                        MCItemStack is = mixedToItem(array.get(index, t), t);
                        setInvSlot(inv, index, is);
                    } catch (NumberFormatException e) {
                        ConfigRuntimeException.DoWarning("Expecting integer value for key in array passed to"
                                + " set_pinv(), but \"" + key + "\" was found. Ignoring.");
                    }
                }
            }

            return CVoid.VOID;
        }

        private void setInvSlot(MCPlayerInventory inv, Integer index, MCItemStack is) {
            if (index >= 0 && index <= 35) {
                inv.setItem(index, is);
            } else if (index == 100) {
                inv.setBoots(is);
            } else if (index == 101) {
                inv.setLeggings(is);
            } else if (index == 102) {
                inv.setChestplate(is);
            } else if (index == 103) {
                inv.setHelmet(is);
            } else if (index == -106) {
                inv.setItemInOffHand(is);
            } else {
                ConfigRuntimeException.DoWarning("Ignoring out of range slot (" + index + ") passed to set_pinv().");
            }
        }

        public Boolean runAsync() {
            return null;
        }
    }

    @api
    public static class pinv_extend extends AbstractFunction {

        public pinv_extend() {
        }

        public String getName() {
            return "pinv_extend";
        }

        public Integer[] numArgs() {
            return new Integer[] { 0, 1, 2 };
        }

        public String docs() {
            return "array {[player [slot]]} Same as pinv, but provide custom data";
        }

        public Class<? extends CREThrowable>[] thrown() {
            return new Class[] { CRECastException.class, CREFormatException.class };
        }

        public boolean isRestricted() {
            return true;
        }

        public MSVersion since() {
            return MSVersion.V3_3_5;
        }

        public Mixed exec(Target t, com.laytonsmith.core.environments.Environment env, Mixed... args)
                throws ConfigRuntimeException {
            Integer index = -1;
            boolean all;
            MCPlayer m;
            if (args.length == 0) {
                all = true;
                m = env.getEnv(CommandHelperEnvironment.class).GetPlayer();
                Static.AssertPlayerNonNull(m, t);
            } else if (args.length == 1) {
                all = true;
                m = Static.GetPlayer(args[0], t);
            } else {
                if (args[1] instanceof CNull) {
                    index = null;
                } else {
                    index = ArgumentValidation.getInt32(args[1], t);
                }
                all = false;
                m = Static.GetPlayer(args[0], t);
            }
            if (all) {
                CArray ret = CArray.GetAssociativeArray(t);
                for (int i = 0; i < 36; i++) {
                    ret.set(i, itemToMixed(getInvSlot(m, i, t), t), t);
                }
                for (int i = 100; i < 104; i++) {
                    ret.set(i, itemToMixed(getInvSlot(m, i, t), t), t);
                }
                ret.set(-106, itemToMixed(getInvSlot(m, -106, t), t), t);
                return ret;
            } else {
                return itemToMixed(getInvSlot(m, index, t), t);
            }
        }

        private MCItemStack getInvSlot(MCPlayer m, Integer slot, Target t) {
            MCPlayerInventory inv = m.getInventory();
            MCItemStack is;
            if (inv == null) {
                throw new CRENotFoundException(
                        "Could not find the inventory of the given player (are you running in cmdline mode?)", t);
            } else if (slot == null) {
                is = inv.getItemInMainHand();
            } else {
                if (slot.equals(36)) {
                    slot = 100;
                }
                if (slot.equals(37)) {
                    slot = 101;
                }
                if (slot.equals(38)) {
                    slot = 102;
                }
                if (slot.equals(39)) {
                    slot = 103;
                }

                if (slot >= 0 && slot <= 35) {
                    is = inv.getItem(slot);
                } else if (slot.equals(100)) {
                    is = inv.getBoots();
                } else if (slot.equals(101)) {
                    is = inv.getLeggings();
                } else if (slot.equals(102)) {
                    is = inv.getChestplate();
                } else if (slot.equals(103)) {
                    is = inv.getHelmet();
                } else if (slot.equals(-106)) {
                    is = inv.getItemInOffHand();
                } else {
                    throw new CRERangeException("Slot index must be 0-35, or 100-103, or -106", t);
                }
            }
            return is;
        }

        public Boolean runAsync() {
            return null;
        }
    }

    public static Mixed itemToMixed(MCItemStack is, Target t) {
        Construct result = ObjectGenerator.GetGenerator().item(is, t);
        if(result.isInstanceOf(CArray.TYPE)) {
            ItemStack item = (ItemStack) is.getHandle();
            ((CArray) result).set("tag", customDataToTag(new NBTItem(item), t), t);

            return result;
        }

        return result;
    }

    private static CArray customDataToTag(NBTCompound comp, Target t) {
        CArray arr = new CArray(t);
        for (String key : comp.getKeys()) {
            NBTType type = comp.getType(key);
            Mixed val;
            switch (type) {
                case NBTTagByte:
                    val = new CInt(comp.getByte(key), t);
                    break;
                case NBTTagShort:
                    val = new CInt(comp.getShort(key), t);
                    break;
                case NBTTagInt:
                    val = new CInt(comp.getInteger(key), t);
                    break;
                case NBTTagLong:
                    val = new CInt(comp.getLong(key), t);
                    break;
                case NBTTagFloat:
                    val = new CDouble(comp.getFloat(key), t);
                    break;
                case NBTTagDouble:
                    val = new CDouble(comp.getDouble(key), t);
                    break;
                case NBTTagByteArray:
                    CArray bArr = new CArray(t);
                    byte[] byteArr = comp.getByteArray(key);
                    for (int i = 0; i < byteArr.length; i++) {
                        bArr.set(i, new CInt(byteArr[i], t), t);
                    }
                    val = bArr;
                    break;
                case NBTTagIntArray:
                    CArray iArr = new CArray(t);
                    int[] intArr = comp.getIntArray(key);
                    for (int i = 0; i < intArr.length; i++) {
                        iArr.set(i, new CInt(intArr[i], t), t);
                    }
                    val = iArr;
                    break;
                case NBTTagString:
                    val = new CString(comp.getString(key), t);
                    break;
                case NBTTagList:
                    NBTType listType = comp.getListType(key);
                    if (!listType.equals(NBTType.NBTTagString)) {
                        throw new CRECastException(
                                String.format("Can not cast nbt list with type %s by key %s", listType, key), t);
                    }
                    NBTList<String> list = comp.getStringList(key);
                    CArray sArr = new CArray(t);
                    for (int i = 0; i < list.size(); i++) {
                        String s = list.get(i);
                        sArr.set(i, new CString(s, t), t);
                    }
                    val = sArr;
                    break;
                case NBTTagCompound:
                    val = customDataToTag(comp.getCompound(key), t);
                    break;
                default:
                    throw new CREFormatException(String.format("Unknown nbt tag type %s", type), t);
            }
            arr.set(key, val, t);
        }

        return arr;
    }

    public static MCItemStack mixedToItem(Mixed arg, Target t) {
        MCItemStack result = ObjectGenerator.GetGenerator().item(arg, t);
        if (!arg.isInstanceOf(CArray.TYPE) || result.isEmpty()) {
            return result;
        }
        CArray arr = (CArray) arg;
        if(arr.containsKey("tag")) {
            Mixed v = arr.get("tag", t);
            CArray tag;
            if(!v.isInstanceOf(CArray.TYPE) || !(tag = (CArray)v).isAssociative()) {
                throw new CREFormatException("Tag key must contains associative array", t);
            }
            ItemStack is = (ItemStack) result.getHandle();
            NBTItem nbtItem = new NBTItem(is);
            tagToCustomData(tag, nbtItem, t);
        }

        return result;
    }

    private static void tagToCustomData(CArray arr, NBTCompound comp, Target t) {
        for (String key : arr.stringKeySet()) {
            Mixed val = arr.get(key, t);
            if (val instanceof CString) {
                comp.setString(key, val.toString());

            } else if (val instanceof CInt) {
                comp.setLong(key, ((CInt) val).getInt());

            } else if (val instanceof CDouble) {
                comp.setDouble(key, ((CDouble) val).getDouble());

            } else if (val instanceof CArray) {
                CArray innerArr = (CArray) val;
                if (innerArr.isAssociative()) {
                    NBTCompound innerComp = comp.addCompound(key);
                    tagToCustomData(innerArr, innerComp, t);
                } else {
                    if (!innerArr.isEmpty()) {
                        Mixed first = innerArr.get(0, t);
                        if (first instanceof CString) {
                            int[] iArr = new int[(int) innerArr.size()];
                            for (int i = 0; i < arr.size(); i++) {
                                Mixed v = arr.get(i, t);
                                if (!(v instanceof CInt)) {
                                    throw new CREFormatException(String.format(
                                            "Not associative array must incude only integers or strings, not %s",
                                            v.typeof().getSimpleName()), t);
                                }
                                iArr[i] = (int) ((CInt) v).getInt();
                            }
                            comp.setIntArray(key, iArr);

                        }
                        if (first instanceof CInt) {
                            NBTList<String> list = comp.getStringList(key);
                            innerArr.forEach(v -> list.add(v.toString()));

                        } else {
                            throw new CREFormatException(
                                    String.format("Not associative array must incude only integers or strings, not %s",
                                            first.typeof().getSimpleName()),
                                    t);
                        }
                    }
                }

            } else if (val instanceof CBoolean) {
                comp.setBoolean(key, ((CBoolean) val).getBoolean());

            } else {
                throw new CREFormatException(
                        String.format("Unsupported type %s for converting", val.typeof().getSimpleName()), t);
            }
        }
    }
}
