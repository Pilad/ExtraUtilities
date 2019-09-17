// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.tileentity.transfernodes.pipes;

import com.rwtema.extrautils.tileentity.transfernodes.BlockTransferPipe;
import com.rwtema.extrautils.tileentity.transfernodes.InvHelper;
import com.rwtema.extrautils.tileentity.transfernodes.TNHelper;
import com.rwtema.extrautils.tileentity.transfernodes.nodebuffer.INodeBuffer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;

public class PipeSorting extends PipeBase {
    public PipeSorting() {
        super("Sorting");
    }

    @Override
    public int limitTransfer(final TileEntity dest, final ForgeDirection side, final INodeBuffer buffer) {
        if (buffer.getBuffer() instanceof ItemStack && dest instanceof IInventory) {
            final ItemStack item = (ItemStack) buffer.getBuffer();
            final IInventory inv = TNHelper.getInventory(dest);
            boolean empty = true;
            for (final int i : InvHelper.getSlots(inv, side.ordinal())) {
                if (inv.getStackInSlot(i) != null) {
                    empty = false;
                    if (InvHelper.sameType(inv.getStackInSlot(i), item)) {
                        return -1;
                    }
                }
            }
            return empty ? -1 : 0;
        }
        return -1;
    }

    @Override
    public IIcon baseTexture() {
        return BlockTransferPipe.pipes_grouping;
    }

    @Override
    public IIcon invPipeTexture(final ForgeDirection dir) {
        return BlockTransferPipe.pipes_grouping;
    }
}


