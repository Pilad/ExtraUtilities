// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.item;

import com.rwtema.extrautils.ExtraUtils;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemBlockGenerator extends ItemBlockMetadata {
    public ItemBlockGenerator(final Block par1) {
        super(par1);
    }

    @Override
    public String getUnlocalizedName(final ItemStack par1ItemStack) {
        if (this.field_150939_a != ExtraUtils.generator) {
            return Item.getItemFromBlock(ExtraUtils.generator).getUnlocalizedName(par1ItemStack);
        }
        return super.getUnlocalizedName(par1ItemStack);
    }
}


