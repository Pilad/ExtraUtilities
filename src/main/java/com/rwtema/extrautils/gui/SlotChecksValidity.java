// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.gui;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotChecksValidity extends Slot {
    public SlotChecksValidity(final IInventory par1iInventory, final int par2, final int par3, final int par4) {
        super(par1iInventory, par2, par3, par4);
    }

    public boolean isItemValid(final ItemStack itemstack) {
        return this.inventory.isItemValidForSlot(this.slotNumber, itemstack);
    }
}


