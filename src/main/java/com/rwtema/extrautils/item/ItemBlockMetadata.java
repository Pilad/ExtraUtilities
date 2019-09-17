// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemBlockMetadata extends ItemBlockTooltip {
    protected boolean hasBlockMetadata;
    IBlockLocalization blockLocalizationMetadata;
    private int blockId;

    public ItemBlockMetadata(final Block par1) {
        super(par1);
        this.hasBlockMetadata = true;
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        if (par1 instanceof IBlockLocalization) {
            this.blockLocalizationMetadata = (IBlockLocalization) par1;
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(final int par1) {
        if (!this.hasBlockMetadata) {
            return super.getIconFromDamage(par1);
        }
        return this.field_150939_a.getIcon(2, par1);
    }

    public int getMetadata(final int par1) {
        if (!this.hasBlockMetadata) {
            return super.getMetadata(par1);
        }
        return par1;
    }

    public String getUnlocalizedName(final ItemStack par1ItemStack) {
        if (this.blockLocalizationMetadata != null) {
            return this.blockLocalizationMetadata.getUnlocalizedName(par1ItemStack);
        }
        if (!this.hasBlockMetadata) {
            return super.getUnlocalizedName(par1ItemStack);
        }
        return super.getUnlocalizedName() + "." + par1ItemStack.getItemDamage();
    }
}


