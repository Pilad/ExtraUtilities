// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.multipart.microblock;

import codechicken.lib.vec.BlockCoord;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Vector3;
import codechicken.microblock.MicroMaterialRegistry;
import codechicken.microblock.MicroblockRender;
import codechicken.multipart.MultipartGenerator;
import codechicken.multipart.TMultiPart;
import codechicken.multipart.TileMultipart;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class PartWall extends PartConnecting implements IWall {
    public static final Cuboid6[] partCuboids;
    public static final Cuboid6[] renderCuboids1;
    public static final Cuboid6[] renderCuboids2;
    public static final String type = "extrautils:wall";

    static {
        partCuboids = new Cuboid6[]{new Cuboid6(0.25, 0.0, 0.25, 0.75, 1.0, 0.75), new Cuboid6(0.25, 0.0, 0.25, 0.75, 1.5, 0.75), new Cuboid6(0.25, 0.0, 0.0, 0.75, 1.5, 0.5), new Cuboid6(0.25, 0.0, 0.5, 0.75, 1.5, 1.0), new Cuboid6(0.0, 0.0, 0.25, 0.5, 1.5, 0.75), new Cuboid6(0.5, 0.0, 0.25, 1.0, 1.5, 0.75)};
        renderCuboids1 = new Cuboid6[]{null, null, new Cuboid6(0.3125, 0.0, 0.0, 0.6875, 0.8125, 0.25), new Cuboid6(0.3125, 0.0, 0.75, 0.6875, 0.8125, 1.0), new Cuboid6(0.0, 0.0, 0.3125, 0.25, 0.8125, 0.6875), new Cuboid6(0.75, 0.0, 0.3125, 1.0, 0.8125, 0.6875)};
        renderCuboids2 = new Cuboid6[]{null, null, new Cuboid6(0.3125, 0.0, 0.0, 0.6875, 0.8125, 0.5), new Cuboid6(0.3125, 0.0, 0.5, 0.6875, 0.8125, 1.0), new Cuboid6(0.0, 0.0, 0.3125, 0.5, 0.8125, 0.6875), new Cuboid6(0.5, 0.0, 0.3125, 1.0, 0.8125, 0.6875)};
    }

    public PartWall() {
    }

    public PartWall(final int material) {
        super(material);
    }

    @Override
    public String getType() {
        return "extrautils:wall";
    }

    @Override
    public Cuboid6 getBounds() {
        return PartWall.partCuboids[0];
    }

    @Override
    public Iterable<Cuboid6> getCollisionBoxes() {
        final List<Cuboid6> t = new ArrayList<Cuboid6>();
        if (this.isEthereal()) {
            return t;
        }
        if ((this.connectionMask & 0x2) != 0x0) {
            t.add(PartWall.partCuboids[1].copy());
        }
        for (int i = 2; i < 6; ++i) {
            if ((this.connectionMask & 1 << i) != 0x0) {
                t.add(PartWall.partCuboids[i].copy());
            }
        }
        return t;
    }

    @Override
    public boolean shouldConnect(final int x, final int y, final int z, final int direction) {
        final Block l = this.world().getBlock(x, y, z);
        if (this.world().getTileEntity(x, y, z) instanceof IWall && this.tile().canAddPart(PartWallDummy.dummyArms[direction])) {
            return ((TileMultipart) this.world().getTileEntity(x, y, z)).canAddPart(PartWallDummy.dummyArms[Facing.oppositeSide[direction]]);
        }
        return l.getMaterial().isOpaque() && l.renderAsNormalBlock() && this.tile().canAddPart(PartWallDummy.dummyArms[direction]);
    }

    public void registerPassThroughs() {
        MultipartGenerator.registerPassThroughInterface(IWall.class.getName());
    }

    @Override
    public void reloadShape() {
        final int prevMask = this.connectionMask;
        this.connectionMask = 0;
        for (int i = 2; i < 6; ++i) {
            if (this.shouldConnect(this.x() + Facing.offsetsXForSide[i], this.y() + Facing.offsetsYForSide[i], this.z() + Facing.offsetsZForSide[i], i)) {
                this.connectionMask |= 1 << i;
            }
        }
        if (!this.world().isAirBlock(this.x(), this.y() + 1, this.z()) || (this.connectionMask != 12 && this.connectionMask != 48)) {
            this.connectionMask |= 0x2;
        }
        if (prevMask != this.connectionMask) {
            this.tile().notifyPartChange(this);
            this.tile().markRender();
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean renderStatic(final Vector3 pos, final int pass) {
        this.reloadShape();
        if (this.mat == null) {
            this.mat = MicroMaterialRegistry.getMaterial(this.material);
        }
        final boolean hasCenter = (this.connectionMask & 0x2) != 0x0;
        if (this.mat != null && this.mat.canRenderInPass(pass)) {
            if (hasCenter) {
                MicroblockRender.renderCuboid(new Vector3(this.x(), this.y(), this.z()), this.mat, pass, this.getRenderBounds(), 0);
            }
            for (int i = 2; i < 6; ++i) {
                if ((this.connectionMask & 1 << i) != 0x0) {
                    MicroblockRender.renderCuboid(new Vector3(this.x(), this.y(), this.z()), this.mat, pass, hasCenter ? PartWall.renderCuboids1[i] : PartWall.renderCuboids2[i], 1 << Facing.oppositeSide[i] | 1 << i);
                }
            }
            return true;
        }
        return false;
    }

    public int getMetadata() {
        return 2;
    }

    public TMultiPart newPart(final boolean client) {
        return new PartWall();
    }

    public TMultiPart placePart(final ItemStack stack, final EntityPlayer player, final World world, final BlockCoord pos, final int side, final Vector3 arg5, final int materialID) {
        return new PartWall(materialID);
    }

    public void renderItem(final ItemStack item, final MicroMaterialRegistry.IMicroMaterial material) {
        MicroblockRender.renderCuboid(new Vector3(0.0, 0.0, 0.0), material, -1, PartWall.partCuboids[0], 0);
        MicroblockRender.renderCuboid(new Vector3(0.0, 0.0, 0.0), material, -1, PartWall.renderCuboids1[2], 0);
        MicroblockRender.renderCuboid(new Vector3(0.0, 0.0, 0.0), material, -1, PartWall.renderCuboids1[3], 0);
    }
}


