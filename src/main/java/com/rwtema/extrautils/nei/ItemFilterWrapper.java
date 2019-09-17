// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.nei;

import codechicken.nei.api.ItemFilter;
import com.rwtema.extrautils.item.filters.Matcher;
import net.minecraft.item.ItemStack;

public class ItemFilterWrapper implements ItemFilter {
    private final Matcher base;
    private final boolean invert;

    public ItemFilterWrapper(final Matcher base, final boolean invert) {
        this.base = base;
        this.invert = invert;
    }

    public ItemFilterWrapper(final Matcher matcher) {
        this(matcher, false);
    }

    public boolean matches(final ItemStack itemStack) {
        return this.base.matchItem(itemStack) != this.invert;
    }
}


