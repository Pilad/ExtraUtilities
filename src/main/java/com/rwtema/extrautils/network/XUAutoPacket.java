// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

import java.lang.reflect.Field;

public abstract class XUAutoPacket extends XUPacketBase {
    boolean init;

    public XUAutoPacket() {
        this.init = false;
    }

    public void getReflectData() {
        this.getClass().getDeclaredFields();
    }

    public void writeField(final Field f, final ByteBuf data) throws IllegalAccessException {
        final Class type = f.getType();
        if (String.class.equals(type)) {
            this.writeString(data, (String) f.get(this));
        } else if (Byte.TYPE.equals(type)) {
            data.writeByte((Byte) f.get(this));
        } else if (Short.TYPE.equals(type)) {
            data.writeShort((Short) f.get(this));
        } else if (Integer.TYPE.equals(type)) {
            data.writeInt((Integer) f.get(this));
        } else if (Long.TYPE.equals(type)) {
            data.writeDouble((Long) f.get(this));
        } else if (Float.TYPE.equals(type)) {
            data.writeFloat((Float) f.get(this));
        } else if (Double.TYPE.equals(type)) {
            data.writeDouble((Double) f.get(this));
        } else if (NBTTagCompound.class.equals(type)) {
            this.writeNBT(data, (NBTTagCompound) f.get(this));
        }
    }

    public void readField(final Field f, final ByteBuf data) throws IllegalAccessException {
        final Class type = f.getType();
        if (String.class.equals(type)) {
            f.set(this, this.readString(data));
        } else if (Byte.TYPE.equals(type)) {
            f.set(this, data.readByte());
        } else if (Short.TYPE.equals(type)) {
            f.set(this, data.readShort());
        } else if (Integer.TYPE.equals(type)) {
            f.set(this, data.readInt());
        } else if (Long.TYPE.equals(type)) {
            f.set(this, data.readLong());
        } else if (Float.TYPE.equals(type)) {
            f.set(this, data.readFloat());
        } else if (Double.TYPE.equals(type)) {
            f.set(this, data.readDouble());
        } else if (NBTTagCompound.class.equals(type)) {
            f.set(this, this.readNBT(data));
        }
    }

    @Override
    public void writeData(final ByteBuf data) throws Exception {
    }

    @Override
    public void readData(final EntityPlayer player, final ByteBuf data) {
    }
}


