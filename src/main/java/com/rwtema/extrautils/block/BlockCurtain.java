// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.block;

import com.rwtema.extrautils.ExtraUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;

public class BlockCurtain extends BlockMultiBlock {
    public BlockCurtain() {
        super(Material.cloth);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setLightOpacity(8);
        this.setCreativeTab(ExtraUtils.creativeTabExtraUtils);
        this.setBlockName("extrautils:curtains");
        this.setBlockTextureName("extrautils:curtains");
    }

    @SideOnly(Side.CLIENT)
    public String getItemIconName() {
        return "extrautils:curtains";
    }

    public void prepareForRender(final String label) {
    }

    public BoxModel getClothModel(final IBlockAccess world, final int x, final int y, final int z, final float width) {
        final BoxModel model = new BoxModel();
        for (int i = 2; i < 6; ++i) {
            final ForgeDirection dir = ForgeDirection.getOrientation(i);
            final Block id = world.getBlock(x + dir.offsetX, y, z + dir.offsetZ);
            if (id == this || id.isOpaqueCube()) {
                model.add(new Box(0.0f, 0.0f, 0.5f - width, 1.0f, 0.5f + width, 0.5f + width).rotateToSide(dir));
            }
        }
        if (model.isEmpty()) {
            model.add(new Box(0.0f, 0.0f, 0.5f - width, 1.0f, 1.0f, 0.5f + width));
            model.add(new Box(0.5f - width, 0.0f, 0.0f, 0.5f + width, 1.0f, 1.0f));
        }
        return model;
    }

    public BoxModel getWorldModel(final IBlockAccess world, final int x, final int y, final int z) {
        final float width = 0.025f;
        return this.getClothModel(world, x, y, z, width);
    }

    public BoxModel getInventoryModel(final int metadata) {
        return new BoxModel(0.0f, 0.0f, 0.0f, 0.05f, 1.0f, 1.0f);
    }

    @Override
    public void addCollisionBoxesToList(final World par1World, final int par2, final int par3, final int par4, final AxisAlignedBB par5AxisAlignedBB, final List par6List, final Entity par7Entity) {
    }

    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(final World par1World, final int par2, final int par3, final int par4) {
        final Box b = BoxModel.boundingBox(this.getClothModel(par1World, par2, par3, par4, 0.1875f));
        return AxisAlignedBB.getBoundingBox(par2 + b.minX, par3 + b.minY, par4 + b.minZ, par2 + b.maxX, par3 + b.maxY, par4 + b.maxZ);
    }

    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getCollisionBoundingBoxFromPool(final World par1World, final int par2, final int par3, final int par4) {
        return this.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
    }
}


