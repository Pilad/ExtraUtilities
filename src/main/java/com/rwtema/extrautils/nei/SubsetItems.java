// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.nei;

import codechicken.nei.api.ItemFilter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class SubsetItems implements ItemFilter {
    public ArrayList<Item> items;

    public SubsetItems(final Item... items) {
        this.items = new ArrayList<Item>();
        for (final Item i : items) {
            if (i != null) {
                this.items.add(i);
            }
        }
    }

    public SubsetItems addItem(final Item item) {
        this.items.add(item);
        return this;
    }

    public boolean matches(final ItemStack item) {
        for (final Item i : this.items) {
            if (i.equals(item.getItem())) {
                return true;
            }
        }
        return false;
    }
}


