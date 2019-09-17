// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.multipart.microblock;

import codechicken.lib.data.MCDataInput;
import codechicken.lib.data.MCDataOutput;
import codechicken.lib.raytracer.IndexedCuboid6;
import codechicken.lib.vec.BlockCoord;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Rotation;
import codechicken.lib.vec.Vector3;
import codechicken.microblock.IMicroMaterialRender;
import codechicken.microblock.ISidedHollowConnect;
import codechicken.microblock.MicroMaterialRegistry;
import codechicken.microblock.MicroblockRender;
import codechicken.multipart.*;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;

public class PartPipeJacket extends PartMicroBlock implements JIconHitEffects, IMicroMaterialRender, IMicroBlock, JPartialOcclusion {
    public static final String type = "extrautils:pipeJacket";
    public static Cuboid6[] axisCubes;

    static {
        PartPipeJacket.axisCubes = null;
    }

    public MicroMaterialRegistry.IMicroMaterial mat;
    public int connectionMask;
    public double pipeSize;
    public TMultiPart centerPart;
    int material;
    private MicroMaterialRegistry.IMicroMaterial wool;

    public PartPipeJacket() {
        this.mat = null;
        this.connectionMask = 0;
        this.pipeSize = 0.3;
        this.centerPart = null;
    }

    public PartPipeJacket(final int material) {
        this.mat = null;
        this.connectionMask = 0;
        this.pipeSize = 0.3;
        this.centerPart = null;
        this.material = material;
    }

    @Override
    public void harvest(final MovingObjectPosition hit, final EntityPlayer player) {
        super.harvest(hit, player);
    }

    @Override
    public boolean occlusionTest(final TMultiPart npart) {
        return !npart.getClass().equals(this.getClass());
    }

    @Override
    public void writeDesc(final MCDataOutput packet) {
        packet.writeInt(this.material);
    }

    @Override
    public void readDesc(final MCDataInput packet) {
        this.material = packet.readInt();
    }

    @Override
    public void save(final NBTTagCompound tag) {
        super.save(tag);
        tag.setString("mat", MicroMaterialRegistry.materialName(this.material));
    }

    @Override
    public void load(final NBTTagCompound tag) {
        super.load(tag);
        this.material = MicroMaterialRegistry.materialID(tag.getString("mat"));
    }

    public String getType() {
        return "extrautils:pipeJacket";
    }

    public Cuboid6 getBounds() {
        return new Cuboid6(0.5 - this.pipeSize, 0.5 - this.pipeSize, 0.5 - this.pipeSize, 0.5 + this.pipeSize, 0.5 + this.pipeSize, 0.5 + this.pipeSize);
    }

    @Override
    public Iterable<Cuboid6> getCollisionBoxes() {
        final ArrayList<Cuboid6> boxes = new ArrayList<Cuboid6>();
        if (this.isEthereal()) {
            return boxes;
        }
        boxes.add(this.getRenderBounds());
        for (int i = 0; i < 6; ++i) {
            if ((this.connectionMask & 1 << i) != 0x0) {
                boxes.add(new Cuboid6(0.5 - this.pipeSize, 0.5 + this.pipeSize, 0.5 - this.pipeSize, 0.5 + this.pipeSize, 1.0, 0.5 + this.pipeSize).apply(Rotation.sideRotations[Facing.oppositeSide[i]].at(new Vector3(0.5, 0.5, 0.5))));
            }
        }
        return boxes;
    }

    @Override
    public ItemStack getItemDrop() {
        final ItemStack item = new ItemStack(ItemMicroBlock.instance, 1);
        final NBTTagCompound tag = new NBTTagCompound();
        tag.setString("mat", MicroMaterialRegistry.materialName(this.material));
        item.setTagCompound(tag);
        return item;
    }

    @Override
    public Iterable<ItemStack> getDrops() {
        return Arrays.asList(this.getItemDrop());
    }

    @Override
    public ItemStack pickItem(final MovingObjectPosition hit) {
        return this.getItemDrop();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getBreakingIcon(final Object subPart, final int side) {
        return this.getBrokenIcon(side);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getBrokenIcon(final int side) {
        if (this.mat != null) {
            return this.mat.getBreakingIcon(side);
        }
        return Blocks.stone.getIcon(0, 0);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addHitEffects(final MovingObjectPosition hit, final EffectRenderer effectRenderer) {
        IconHitEffects.addHitEffects(this, hit, effectRenderer);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addDestroyEffects(final MovingObjectPosition hit, final EffectRenderer effectRenderer) {
        IconHitEffects.addDestroyEffects(this, effectRenderer, false);
    }

    @Override
    public Cuboid6 getRenderBounds() {
        return this.getBounds();
    }

    @Override
    public int getLightValue() {
        return MicroMaterialRegistry.getMaterial(this.material).getLightValue();
    }

    @Override
    public void onNeighborChanged() {
        if (!this.world().isRemote) {
            this.dropIfCantStay();
        } else {
            this.reloadShape();
        }
    }

    public boolean canStay() {
        return this.tile().partMap(6) != null;
    }

    public boolean dropIfCantStay() {
        if (!this.canStay()) {
            this.drop();
            return true;
        }
        this.reloadShape();
        return false;
    }

    @Override
    public void drop() {
        TileMultipart.dropItem(this.getItemDrop(), this.world(), Vector3.fromTileEntityCenter(this.tile()));
        this.tile().remPart(this);
    }

    @Override
    public void onPartChanged(final TMultiPart part) {
        if (!this.world().isRemote) {
            this.dropIfCantStay();
        }
    }

    @Override
    public Iterable<IndexedCuboid6> getSubParts() {
        final ArrayList<IndexedCuboid6> boxes = new ArrayList<IndexedCuboid6>();
        this.overEthereal = true;
        for (final Cuboid6 cuboid6 : this.getCollisionBoxes()) {
            boxes.add(new IndexedCuboid6(0, cuboid6));
        }
        this.overEthereal = false;
        return boxes;
    }

    @Override
    public void onWorldJoin() {
        this.reloadShape();
        super.onWorldJoin();
    }

    @Override
    public void reloadShape() {
        final int prevMask = this.connectionMask;
        this.centerPart = this.tile().partMap(6);
        final double prevSize = this.pipeSize;
        this.pipeSize = 0.26;
        this.connectionMask = 0;
        if (this.centerPart != null) {
            if (this.centerPart instanceof ISidedHollowConnect) {
                for (int side = 0; side < 6; ++side) {
                    this.pipeSize = Math.max(this.pipeSize, (((ISidedHollowConnect) this.centerPart).getHollowSize(side) + 1) / 32.0f);
                }
            }
            for (int i = 0; i < 6; ++i) {
                for (final Cuboid6 cuboid6 : this.centerPart.getCollisionBoxes()) {
                    if (cuboid6.intersects(new Cuboid6(this.pipeSize, 0.0, this.pipeSize, 1.0 - this.pipeSize, 0.01, 1.0 - this.pipeSize).apply(Rotation.sideRotations[i].at(new Vector3(0.5, 0.5, 0.5))))) {
                        this.connectionMask |= 1 << i;
                    }
                }
            }
        }
        if (prevMask != this.connectionMask || prevSize != this.pipeSize) {
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
        if (this.mat != null && this.mat.canRenderInPass(pass)) {
            MicroblockRender.renderCuboid(new Vector3(this.x(), this.y(), this.z()), this.mat, pass, this.getRenderBounds(), this.connectionMask);
            for (int i = 0; i < 6; ++i) {
                if ((this.connectionMask & 1 << i) != 0x0) {
                    MicroblockRender.renderCuboid(new Vector3(this.x(), this.y(), this.z()), this.mat, pass, new Cuboid6(0.5 - this.pipeSize, 0.5 + this.pipeSize, 0.5 - this.pipeSize, 0.5 + this.pipeSize, 1.0, 0.5 + this.pipeSize).apply(Rotation.sideRotations[Facing.oppositeSide[i]].at(new Vector3(0.5, 0.5, 0.5))), 1 << Facing.oppositeSide[i]);
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void render(final Vector3 pos, final int pass) {
    }

    @Override
    public float getStrength(final MovingObjectPosition hit, final EntityPlayer player) {
        return this.getMaterial().getStrength(player) * 4.0f;
    }

    public int getMetadata() {
        return 0;
    }

    public TMultiPart newPart(final boolean client) {
        return new PartPipeJacket();
    }

    public TMultiPart placePart(final ItemStack stack, final EntityPlayer player, final World world, final BlockCoord pos, final int side, final Vector3 arg5, final int materialID) {
        final TileMultipart tile = TileMultipart.getOrConvertTile(world, new BlockCoord(pos.x, pos.y, pos.z));
        if (tile == null) {
            return null;
        }
        final TMultiPart part = tile.partMap(6);
        if (part != null) {
            return new PartPipeJacket(materialID);
        }
        return null;
    }

    public void registerPassThroughs() {
    }

    public void renderItem(final ItemStack item, final MicroMaterialRegistry.IMicroMaterial material) {
        if (this.wool == null) {
            this.wool = MicroMaterialRegistry.getMaterial(Blocks.wool.getUnlocalizedName());
        }
        MicroblockRender.renderCuboid(new Vector3(0.0, 0.0, 0.0), this.wool, -1, new Cuboid6(0.1995, 0.4, 0.4, 0.8005, 0.6, 0.6), 15);
        MicroblockRender.renderCuboid(new Vector3(0.0, 0.0, 0.0), material, -1, new Cuboid6(0.2, 0.25, 0.25, 0.8, 0.75, 0.75), 0);
    }

    @Override
    public Iterable<Cuboid6> getOcclusionBoxes() {
        return Arrays.asList(new Cuboid6[0]);
    }

    public Iterable<Cuboid6> getPartialOcclusionBoxes() {
        return Arrays.asList(new Cuboid6[0]);
    }

    public boolean allowCompleteOcclusion() {
        return true;
    }
}


