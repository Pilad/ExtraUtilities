// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.block;

import com.rwtema.extrautils.ExtraUtilsProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;

public abstract class BlockMultiBlock extends Block implements IMultiBoxBlock {
    public BlockMultiBlock(final Material xMaterial) {
        super(xMaterial);
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public int getRenderType() {
        return ExtraUtilsProxy.multiBlockID;
    }

    public void addCollisionBoxesToList(final World par1World, final int par2, final int par3, final int par4, final AxisAlignedBB par5AxisAlignedBB, final List par6List, final Entity par7Entity) {
        final List models = this.getWorldModel(par1World, par2, par3, par4);
        if (models == null || models.size() == 0) {
            return;
        }
        for (final Object model : models) {
            final Box b = (Box) model;
            final AxisAlignedBB axisalignedbb1 = AxisAlignedBB.getBoundingBox(par2 + b.offsetx + b.minX, par3 + b.offsety + b.minY, par4 + b.offsetz + b.minZ, par2 + b.offsetx + b.maxX, par3 + b.offsety + b.maxY, par4 + b.offsetz + b.maxZ);
            if (axisalignedbb1 != null && par5AxisAlignedBB.intersectsWith(axisalignedbb1)) {
                par6List.add(axisalignedbb1);
            }
        }
    }

    public void setBlockBoundsBasedOnState(final IBlockAccess par1IBlockAccess, final int x, final int y, final int z) {
        final Box bounds = BoxModel.boundingBox(this.getWorldModel(par1IBlockAccess, x, y, z));
        if (bounds != null) {
            this.setBlockBounds(bounds.minX, bounds.minY, bounds.minZ, bounds.maxX, bounds.maxY, bounds.maxZ);
        }
    }
}


