// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.item;

import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public interface IItemMultiTransparency {
    int numIcons(final ItemStack p0);

    IIcon getIconForTransparentRender(final ItemStack p0, final int p1);

    float getIconTransparency(final ItemStack p0, final int p1);
}


