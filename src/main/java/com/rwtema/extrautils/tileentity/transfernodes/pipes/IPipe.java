// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.tileentity.transfernodes.pipes;

import com.rwtema.extrautils.tileentity.transfernodes.nodebuffer.INodeBuffer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;

public interface IPipe {
    ArrayList<ForgeDirection> getOutputDirections(final IBlockAccess p0, final int p1, final int p2, final int p3, final ForgeDirection p4, final INodeBuffer p5);

    boolean transferItems(final IBlockAccess p0, final int p1, final int p2, final int p3, final ForgeDirection p4, final INodeBuffer p5);

    boolean canInput(final IBlockAccess p0, final int p1, final int p2, final int p3, final ForgeDirection p4);

    boolean canOutput(final IBlockAccess p0, final int p1, final int p2, final int p3, final ForgeDirection p4);

    int limitTransfer(final TileEntity p0, final ForgeDirection p1, final INodeBuffer p2);

    IInventory getFilterInventory(final IBlockAccess p0, final int p1, final int p2, final int p3);

    boolean shouldConnectToTile(final IBlockAccess p0, final int p1, final int p2, final int p3, final ForgeDirection p4);

    String getPipeType();
}


