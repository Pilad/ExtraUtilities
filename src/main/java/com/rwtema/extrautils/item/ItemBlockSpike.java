// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.item;

import com.google.common.collect.Multimap;
import com.rwtema.extrautils.block.BlockSpike;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.HashSet;

public class ItemBlockSpike extends ItemBlock {
    public static final HashSet<Item> itemHashSet;

    static {
        itemHashSet = new HashSet<Item>();
    }

    public final BlockSpike spike;

    public ItemBlockSpike(final Block p_i45328_1_) {
        super(p_i45328_1_);
        this.spike = (BlockSpike) p_i45328_1_;
        this.spike.itemSpike = this;
        ItemBlockSpike.itemHashSet.add(this);
    }

    public int getItemEnchantability() {
        return this.spike.swordStack.getItem().getItemEnchantability(this.spike.swordStack);
    }

    public int cofh_canEnchantApply(final ItemStack stack, final Enchantment ench) {
        if (ench.canApply(this.spike.swordStack.copy())) {
            return 1;
        }
        return -1;
    }

    public boolean isItemTool(final ItemStack p_77616_1_) {
        return p_77616_1_.stackSize == 1;
    }

    public Multimap getAttributeModifiers(final ItemStack stack) {
        return this.spike.swordStack.copy().getAttributeModifiers();
    }

    public boolean isBookEnchantable(final ItemStack stack, final ItemStack book) {
        return stack.stackSize == 1;
    }
}


