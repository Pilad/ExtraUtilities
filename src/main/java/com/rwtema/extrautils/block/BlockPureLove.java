// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.block;

import com.rwtema.extrautils.helper.XURandom;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.Random;

public class BlockPureLove extends Block {
    Random rand;

    public BlockPureLove() {
        super(Material.iron);
        this.rand = XURandom.getInstance();
        this.setBlockName("extrautils:pureLove");
        this.setBlockTextureName("extrautils:heart");
        this.setHardness(1.0f);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(final World p_149668_1_, final int p_149668_2_, final int p_149668_3_, final int p_149668_4_) {
        final float f = 0.125f;
        return AxisAlignedBB.getBoundingBox(p_149668_2_, p_149668_3_, p_149668_4_, p_149668_2_ + 1, p_149668_3_ + 1 - f, p_149668_4_ + 1);
    }

    public void onEntityCollidedWithBlock(final World world, final int x, final int y, final int z, final Entity entity) {
        if (this.rand.nextInt(5) > 0) {
            return;
        }
        if (entity instanceof EntityAnimal) {
            final EntityAnimal animal = (EntityAnimal) entity;
            if (animal.getGrowingAge() < 0) {
                animal.addGrowth(this.rand.nextInt(40));
            } else if (animal.getGrowingAge() > 0) {
                int j = animal.getGrowingAge();
                j -= this.rand.nextInt(40);
                if (j < 0) {
                    j = 0;
                }
                animal.setGrowingAge(j);
            } else if (!animal.isInLove()) {
                if (world.getEntitiesWithinAABB(entity.getClass(), entity.boundingBox.expand(8.0, 8.0, 8.0)).size() > 32) {
                    return;
                }
                animal.func_146082_f(null);
            }
        }
    }

    public MapColor getMapColor(final int p_149728_1_) {
        return MapColor.pinkColor;
    }
}


