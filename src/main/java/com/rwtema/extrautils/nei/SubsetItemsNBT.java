// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.nei;

import codechicken.nei.api.ItemFilter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SubsetItemsNBT implements ItemFilter {
    public Item item;

    public SubsetItemsNBT(final Item item) {
        this.item = item;
    }

    public boolean matches(final ItemStack item) {
        return item.hasTagCompound() && this.item.equals(item.getItem());
    }
}


