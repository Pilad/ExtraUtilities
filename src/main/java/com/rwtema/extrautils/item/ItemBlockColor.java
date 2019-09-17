// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.item;

import com.rwtema.extrautils.block.BlockColor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemBlockColor extends ItemBlockMetadata {
    public ItemBlockColor(final Block par1) {
        super(par1);
    }

    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(final ItemStack item, final int p_82790_2_) {
        final float[] col = BlockColor.initColor[item.getItemDamage() & 0xF];
        return (int) (col[0] * 255.0f) << 16 | (int) (col[1] * 255.0f) << 8 | (int) (col[2] * 255.0f);
    }

    public String getItemStackDisplayName(final ItemStack p_77653_1_) {
        final Block bc = ((BlockColor) this.field_150939_a).baseBlock;
        final Item i = Item.getItemFromBlock(bc);
        String name;
        if (i == null) {
            name = bc.getUnlocalizedName();
            if (name != null) {
                name = StatCollector.translateToLocal(name);
                name = ("" + StatCollector.translateToLocal(name + ".name")).trim();
            } else {
                name = "";
            }
        } else {
            name = new ItemStack(i, 1, 0).getDisplayName();
        }
        return StatCollector.translateToLocal("tile.extrautils:colorBlock." + p_77653_1_.getItemDamage() + ".name").replaceAll("BLOCKNAME", name);
    }
}


