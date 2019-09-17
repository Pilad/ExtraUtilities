// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.block;

import com.rwtema.extrautils.ExtraUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockAngelBlock extends Block {
    public BlockAngelBlock() {
        super(Material.rock);
        this.setBlockName("extrautils:angelBlock");
        this.setBlockTextureName("extrautils:angelBlock");
        this.setCreativeTab(ExtraUtils.creativeTabExtraUtils);
        this.setHardness(1.0f);
        this.setStepSound(Block.soundTypeStone);
    }
}


