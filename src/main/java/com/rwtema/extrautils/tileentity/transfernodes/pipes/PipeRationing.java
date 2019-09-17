// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.tileentity.transfernodes.pipes;

import com.rwtema.extrautils.tileentity.transfernodes.BlockTransferPipe;
import com.rwtema.extrautils.tileentity.transfernodes.InvHelper;
import com.rwtema.extrautils.tileentity.transfernodes.nodebuffer.INodeBuffer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;

public class PipeRationing extends PipeBase {
    public PipeRationing() {
        super("Rationing");
    }

    @Override
    public int limitTransfer(final TileEntity dest, final ForgeDirection side, final INodeBuffer buffer) {
        if (buffer.getBuffer() instanceof ItemStack && dest instanceof IInventory) {
            final ItemStack item = (ItemStack) buffer.getBuffer();
            final IInventory inv = (IInventory) dest;
            int n = Math.min(inv.getInventoryStackLimit(), item.getMaxStackSize());
            for (final int i : InvHelper.getSlots(inv, side.ordinal())) {
                if (inv.getStackInSlot(i) != null && InvHelper.canStack(inv.getStackInSlot(i), item)) {
                    n -= inv.getStackInSlot(i).stackSize;
                    if (n <= 0) {
                        return 0;
                    }
                }
            }
            return (n < 0) ? 0 : n;
        }
        return -1;
    }

    @Override
    public IIcon baseTexture() {
        return BlockTransferPipe.pipes_supply;
    }

    @Override
    public IIcon invPipeTexture(final ForgeDirection dir) {
        return BlockTransferPipe.pipes_supply;
    }
}


