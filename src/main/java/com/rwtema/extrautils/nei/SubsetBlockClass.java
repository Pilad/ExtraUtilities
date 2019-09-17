// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.nei;

import codechicken.nei.api.ItemFilter;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class SubsetBlockClass implements ItemFilter {
    public Class<? extends Block> base;

    public SubsetBlockClass(final Class<? extends Block> base) {
        this.base = base;
    }

    public boolean matches(final ItemStack item) {
        return this.base.equals(Block.getBlockFromItem(item.getItem()).getClass());
    }
}


