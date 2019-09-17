// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.crafting;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class RecipeFilterInvert extends ShapelessRecipes implements IRecipe {
    private final ItemStack filter;
    Item key;
    String keyName;

    public RecipeFilterInvert(final Item key, final String keyName, final ItemStack filter) {
        super(makeResult(key, keyName, filter.copy()), makeRecipes(key, keyName, filter.copy()));
        this.key = key;
        this.keyName = keyName;
        this.filter = filter;
    }

    public static ItemStack makeResult(final Item key, final String keyName, final ItemStack filter) {
        final NBTTagCompound tags = new NBTTagCompound();
        tags.setBoolean(keyName, true);
        filter.setTagCompound(tags);
        return filter;
    }

    public static List makeRecipes(final Item key, final String keyName, final ItemStack filter) {
        final List items = new ArrayList();
        items.add(new ItemStack(key, 1, 1));
        items.add(filter.copy());
        return items;
    }

    public boolean matches(final InventoryCrafting inv, final World world) {
        return this.getCraftingResult(inv) != null;
    }

    public ItemStack getCraftingResult(final InventoryCrafting inv) {
        ItemStack filter = null;
        boolean hasItem = false;
        for (int i = 0; i < inv.getSizeInventory(); ++i) {
            final ItemStack item = inv.getStackInSlot(i);
            if (item != null) {
                if (item.getItem() == this.filter.getItem() && item.getItemDamage() == this.filter.getItemDamage()) {
                    if (filter != null) {
                        return null;
                    }
                    filter = item;
                }
                if (item.getItem() == this.key) {
                    if (hasItem) {
                        return null;
                    }
                    hasItem = true;
                }
            }
        }
        if (hasItem && filter != null) {
            final ItemStack newFilter = filter.copy();
            newFilter.stackSize = 1;
            NBTTagCompound tags = newFilter.getTagCompound();
            if (tags == null) {
                tags = new NBTTagCompound();
            }
            if (tags.hasKey(this.keyName) && tags.getBoolean(this.keyName)) {
                tags.removeTag(this.keyName);
                if (tags.hasNoTags()) {
                    tags = null;
                }
            } else {
                tags.setBoolean(this.keyName, true);
            }
            newFilter.setTagCompound(tags);
            return newFilter;
        }
        return null;
    }

    public int getRecipeSize() {
        return 2;
    }

    public ItemStack getRecipeOutput() {
        return makeResult(this.key, this.keyName, this.filter);
    }
}


