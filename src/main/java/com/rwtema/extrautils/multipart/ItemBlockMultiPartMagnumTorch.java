// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.multipart;

import codechicken.lib.vec.BlockCoord;
import codechicken.multipart.TMultiPart;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBlockMultiPartMagnumTorch extends ItemBlockMultiPart {
    public ItemBlockMultiPartMagnumTorch(final Block par1) {
        super(par1);
        this.hasBlockMetadata = false;
    }

    @Override
    public TMultiPart createMultiPart(final World world, final BlockCoord pos, final ItemStack item, final int side) {
        return new MagnumTorchPart();
    }
}


