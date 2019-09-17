// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.block;

import com.rwtema.extrautils.ExtraUtils;
import com.rwtema.extrautils.ExtraUtilsProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;

public class BlockGreenScreen extends Block {
    private static final float[][] cols;

    static {
        cols = new float[][]{{1.0f, 1.0f, 1.0f}, {1.0f, 0.5f, 0.0f}, {1.0f, 0.0f, 1.0f}, {0.0f, 0.5f, 0.85f}, {1.0f, 1.0f, 0.0f}, {0.0f, 1.0f, 0.0f}, {1.0f, 0.6f, 0.65f}, {0.5f, 0.5f, 0.5f}, {0.8f, 0.8f, 0.8f}, {0.0f, 1.0f, 1.0f}, {0.7f, 0.2f, 1.0f}, {0.0f, 0.0f, 1.0f}, {0.5f, 0.2f, 0.0f}, {0.0f, 0.6f, 0.0f}, {1.0f, 0.0f, 0.0f}, {0.0f, 0.0f, 0.0f}};
    }

    public BlockGreenScreen() {
        super(Material.cloth);
        this.setCreativeTab(ExtraUtils.creativeTabExtraUtils);
        this.setBlockName("extrautils:greenscreen");
        this.setBlockTextureName("extrautils:greenscreen");
        this.setLightOpacity(0);
        this.setHardness(1.0f);
        this.setResistance(10.0f);
    }

    public static int getLightLevel(final int metadata) {
        return (int) ((BlockGreenScreen.cols[metadata][0] + BlockGreenScreen.cols[metadata][1] + BlockGreenScreen.cols[metadata][2]) / 3.0f * 15.0f);
    }

    public int getLightValue(final IBlockAccess world, final int x, final int y, final int z) {
        if (world instanceof World && !((World) world).blockExists(x, y, z)) {
            return 0;
        }
        return getLightLevel(world.getBlockMetadata(x, y, z));
    }

    @SideOnly(Side.CLIENT)
    public int getRenderColor(final int p_149741_1_) {
        if (p_149741_1_ == 15) {
            return 0;
        }
        final float[] col = BlockGreenScreen.cols[p_149741_1_ & 0xF];
        return (int) (col[0] * 255.0f) << 16 | (int) (col[1] * 255.0f) << 8 | (int) (col[2] * 255.0f);
    }

    @SideOnly(Side.CLIENT)
    public int colorMultiplier(final IBlockAccess world, final int x, final int y, final int z) {
        return this.getRenderColor(world.getBlockMetadata(x, y, z));
    }

    public int damageDropped(final int p_149692_1_) {
        return p_149692_1_;
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(final Item p_149666_1_, final CreativeTabs p_149666_2_, final List p_149666_3_) {
        for (int i = 0; i < 16; ++i) {
            p_149666_3_.add(new ItemStack(p_149666_1_, 1, i));
        }
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean isNormalCube(final IBlockAccess world, final int x, final int y, final int z) {
        return true;
    }

    public int getRenderType() {
        return ExtraUtilsProxy.fullBrightBlockID;
    }
}


