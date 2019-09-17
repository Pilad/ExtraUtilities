// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.gui;

import com.rwtema.extrautils.tileentity.TileEntityFilingCabinet;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotFilingCabinet extends Slot {
    public static boolean drawing;

    static {
        SlotFilingCabinet.drawing = false;
    }

    public SlotFilingCabinet(final IInventory par1iInventory, final int par2, final int par3, final int par4) {
        super(par1iInventory, par2, par3, par4);
    }

    public ItemStack getStack() {
        if (SlotFilingCabinet.drawing && this.getSlotIndex() >= ((TileEntityFilingCabinet) this.inventory).itemSlots.size()) {
            return null;
        }
        return super.getStack();
    }

    public boolean isItemValid(final ItemStack item) {
        return this.inventory.isItemValidForSlot(this.getSlotIndex(), item);
    }
}


