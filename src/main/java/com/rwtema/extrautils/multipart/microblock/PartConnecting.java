// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.multipart.microblock;

import codechicken.lib.data.MCDataInput;
import codechicken.lib.data.MCDataOutput;
import codechicken.lib.raytracer.IndexedCuboid6;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Vector3;
import codechicken.microblock.IMicroMaterialRender;
import codechicken.microblock.MicroMaterialRegistry;
import codechicken.multipart.*;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;

import java.util.Arrays;

public abstract class PartConnecting extends PartMicroBlock implements JIconHitEffects, IMicroMaterialRender, JNormalOcclusion, IMicroBlock {
    public MicroMaterialRegistry.IMicroMaterial mat;
    public int connectionMask;
    int material;

    public PartConnecting() {
        this.mat = null;
        this.connectionMask = 0;
    }

    public PartConnecting(final int material) {
        this.mat = null;
        this.connectionMask = 0;
        this.material = material;
    }

    @Override
    public boolean occlusionTest(final TMultiPart npart) {
        return NormalOcclusionTest.apply(this, npart);
    }

    @Override
    public Iterable<Cuboid6> getOcclusionBoxes() {
        return Arrays.asList(this.getBounds());
    }

    @Override
    public void harvest(final MovingObjectPosition hit, final EntityPlayer player) {
        super.harvest(hit, player);
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

    public abstract String getType();

    public abstract Cuboid6 getBounds();

    @Override
    public abstract Iterable<Cuboid6> getCollisionBoxes();

    @Override
    public ItemStack getItemDrop() {
        final ItemStack item = new ItemStack(ItemMicroBlock.instance, 1, this.getMetadata());
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

    @Override
    public MicroMaterialRegistry.IMicroMaterial getMaterial() {
        if (this.mat == null) {
            this.mat = MicroMaterialRegistry.getMaterial(this.material);
        }
        return this.mat;
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
        this.reloadShape();
    }

    @Override
    public void drop() {
        TileMultipart.dropItem(this.getItemDrop(), this.world(), Vector3.fromTileEntityCenter(this.tile()));
        this.tile().remPart(this);
    }

    @Override
    public void onPartChanged(final TMultiPart part) {
        this.reloadShape();
    }

    @Override
    public Iterable<IndexedCuboid6> getSubParts() {
        final IndexedCuboid6 box = new IndexedCuboid6(0, new Cuboid6(0.5, 0.5, 0.5, 0.5, 0.5, 0.5));
        this.overEthereal = true;
        for (final Cuboid6 cuboid6 : this.getCollisionBoxes()) {
            box.enclose(new IndexedCuboid6(0, cuboid6));
        }
        this.overEthereal = false;
        box.max.y = 1.0;
        return Arrays.asList(box);
    }

    @Override
    public void onWorldJoin() {
        this.reloadShape();
        super.onWorldJoin();
    }

    public abstract boolean shouldConnect(final int p0, final int p1, final int p2, final int p3);

    @Override
    public abstract void reloadShape();

    @SideOnly(Side.CLIENT)
    @Override
    public abstract boolean renderStatic(final Vector3 p0, final int p1);

    @SideOnly(Side.CLIENT)
    @Override
    public void render(final Vector3 pos, final int pass) {
        this.renderStatic(pos, pass);
    }

    @Override
    public float getStrength(final MovingObjectPosition hit, final EntityPlayer player) {
        return this.getMaterial().getStrength(player);
    }
}


