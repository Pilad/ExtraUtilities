// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.tileentity.generators;

import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class InventoryGeneric extends InventoryBasic {
    public InventoryGeneric(final String par1Str, final boolean par2, final int par3) {
        super(par1Str, par2, par3);
    }

    public void writeToNBT(final NBTTagCompound nbt) {
        final NBTTagCompound tag = new NBTTagCompound();
        for (int i = 0; i < this.getSizeInventory(); ++i) {
            final ItemStack item = this.getStackInSlot(i);
            if (item != null) {
                final NBTTagCompound itemtag = new NBTTagCompound();
                item.writeToNBT(itemtag);
                tag.setTag("item_" + i, itemtag);
            }
        }
        nbt.setTag("items", tag);
    }

    public void readFromNBT(final NBTTagCompound nbt) {
        if (!nbt.hasKey("items")) {
            for (int i = 0; i < this.getSizeInventory(); ++i) {
                this.setInventorySlotContents(i, null);
            }
        } else {
            final NBTTagCompound tag = nbt.getCompoundTag("items");
            for (int j = 0; j < this.getSizeInventory(); ++j) {
                if (tag.hasKey("item_" + j)) {
                    this.setInventorySlotContents(j, ItemStack.loadItemStackFromNBT(tag.getCompoundTag("item_" + j)));
                } else {
                    this.setInventorySlotContents(j, null);
                }
            }
        }
    }
}


