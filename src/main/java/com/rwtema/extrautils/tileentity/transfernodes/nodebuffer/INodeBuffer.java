// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.tileentity.transfernodes.nodebuffer;

import com.rwtema.extrautils.tileentity.transfernodes.pipes.IPipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public interface INodeBuffer {
    boolean transfer(final TileEntity p0, final ForgeDirection p1, final IPipe p2, final int p3, final int p4, final int p5, final ForgeDirection p6);

    Object getBuffer();

    void setBuffer(final Object p0);

    String getBufferType();

    boolean isEmpty();

    boolean shouldSearch();

    void readFromNBT(final NBTTagCompound p0);

    void writeToNBT(final NBTTagCompound p0);

    INode getNode();

    void setNode(final INode p0);

    boolean transferTo(final INodeBuffer p0, final int p1);

    Object recieve(final Object p0);

    void markDirty();
}


