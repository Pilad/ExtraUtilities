// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.item;

import com.rwtema.extrautils.ICreativeTabSorting;
import com.rwtema.extrautils.block.IBlockTooltip;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemBlockTooltip extends ItemBlock implements ICreativeTabSorting {
    public ItemBlockTooltip(final Block par1) {
        super(par1);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(final ItemStack par1ItemStack, final EntityPlayer par2EntityPlayer, final List par3List, final boolean par4) {
        if (this.field_150939_a instanceof IBlockTooltip) {
            ((IBlockTooltip) this.field_150939_a).addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
        }
    }

    public String getSortingName(final ItemStack par1ItemStack) {
        if (this.field_150939_a instanceof ICreativeTabSorting) {
            return ((ICreativeTabSorting) this.field_150939_a).getSortingName(par1ItemStack);
        }
        return par1ItemStack.getDisplayName();
    }
}


