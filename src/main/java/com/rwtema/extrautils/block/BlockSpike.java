// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.block;

import com.rwtema.extrautils.ExtraUtils;
import com.rwtema.extrautils.ExtraUtilsProxy;
import com.rwtema.extrautils.tileentity.TileEntityEnchantedSpike;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.common.util.ForgeDirection;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockSpike extends Block {
    public static final Field experienceField;

    static {
        experienceField = ReflectionHelper.findField(EntityLiving.class, "experienceValue", "field_70728_aV");
    }

    public final ItemStack swordStack;
    public Item itemSpike;
    protected IIcon ironIcon;

    public BlockSpike() {
        this(Material.iron, new ItemStack(Items.iron_sword));
    }

    public BlockSpike(final ItemStack swordStack) {
        this(Material.iron, swordStack);
    }

    public BlockSpike(final Material material, final ItemStack swordStack) {
        super(material);
        this.setBlockName("extrautils:spike_base");
        this.setBlockTextureName("extrautils:spike_base");
        this.setHardness(5.0f);
        this.setResistance(500.0f);
        this.setCreativeTab(ExtraUtils.creativeTabExtraUtils);
        this.setStepSound(Block.soundTypeMetal);
        this.swordStack = swordStack;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(final IIconRegister par1IIconRegister) {
        super.registerBlockIcons(par1IIconRegister);
        this.ironIcon = par1IIconRegister.registerIcon("extrautils:spike_side");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(final int par1, final int par2) {
        final int side = par2 % 6;
        if (par1 == Facing.oppositeSide[side]) {
            return this.blockIcon;
        }
        return this.ironIcon;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public int getRenderType() {
        return ExtraUtilsProxy.spikeBlockID;
    }

    public int onBlockPlaced(final World par1World, final int par2, final int par3, final int par4, int par5, final float par6, final float par7, final float par8, final int par9) {
        final int meta = par5 % 6;
        final int flag = 0;
        final ForgeDirection side = ForgeDirection.getOrientation(meta);
        if (!par1World.isSideSolid(par2 - side.offsetX, par3 - side.offsetY, par4 - side.offsetZ, side.getOpposite())) {
            for (final ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
                if (side != dir) {
                    if (par1World.isSideSolid(par2 - dir.offsetX, par3 - dir.offsetY, par4 - dir.offsetZ, dir.getOpposite())) {
                        return flag + dir.ordinal();
                    }
                    if (par1World.getBlock(par2 - dir.offsetX, par3 - dir.offsetY, par4 - dir.offsetZ) == this) {
                        par5 = par1World.getBlockMetadata(par2 - dir.offsetX, par3 - dir.offsetY, par4 - dir.offsetZ) % 6;
                    }
                }
            }
        }
        return flag + par5;
    }

    public boolean removedByPlayer(final World world, final EntityPlayer player, final int x, final int y, final int z, final boolean willHarvest) {
        if (player.capabilities.isCreativeMode || !this.canHarvestBlock(player, world.getBlockMetadata(x, y, z))) {
            return super.removedByPlayer(world, player, x, y, z, willHarvest);
        }
        final ArrayList<ItemStack> items = this.getDrops(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
        if (world.setBlockToAir(x, y, z)) {
            if (!world.isRemote) {
                for (final ItemStack item : items) {
                    this.dropBlockAsItem(world, x, y, z, item);
                }
            }
            return true;
        }
        return false;
    }

    public void harvestBlock(final World par1World, final EntityPlayer par2EntityPlayer, final int par3, final int par4, final int par5, final int par6) {
        par2EntityPlayer.addStat(StatList.mineBlockStatArray[getIdFromBlock(this)], 1);
        par2EntityPlayer.addExhaustion(0.025f);
    }

    public ItemStack getPickBlock(final MovingObjectPosition target, final World world, final int x, final int y, final int z) {
        return this.getDrops(world, x, y, z, world.getBlockMetadata(x, y, z), 0).get(0);
    }

    public ItemStack getPickBlock(final MovingObjectPosition target, final World world, final int x, final int y, final int z, final EntityPlayer player) {
        return this.getDrops(world, x, y, z, world.getBlockMetadata(x, y, z), 0).get(0);
    }

    protected ItemStack createStackedBlock(final int p_149644_1_) {
        return new ItemStack(this.itemSpike, 1, 0);
    }

    public ArrayList<ItemStack> getDrops(final World world, final int x, final int y, final int z, final int metadata, final int fortune) {
        final ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        final Item item = this.getItemDropped(metadata, world.rand, fortune);
        final ItemStack stack = new ItemStack(item, 1, this.damageDropped(metadata));
        final TileEntity tile = world.getTileEntity(x, y, z);
        if (tile instanceof TileEntityEnchantedSpike) {
            stack.setTagCompound(((TileEntityEnchantedSpike) tile).getEnchantmentTagList());
        }
        ret.add(stack);
        return ret;
    }

    public void onBlockPlacedBy(final World world, final int x, final int y, final int z, final EntityLivingBase entity, final ItemStack stack) {
        final NBTTagList enchantments = stack.getEnchantmentTagList();
        if (enchantments != null) {
            final int newmeta = 6 + world.getBlockMetadata(x, y, z) % 6;
            world.setBlock(x, y, z, this, newmeta, 2);
            final TileEntity tile = world.getTileEntity(x, y, z);
            if (tile instanceof TileEntityEnchantedSpike) {
                ((TileEntityEnchantedSpike) tile).setEnchantmentTagList(enchantments);
            }
        }
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(final World par1World, final int par2, final int par3, final int par4) {
        final double h = 0.0625;
        final int side = par1World.getBlockMetadata(par2, par3, par4) % 6;
        switch (side) {
            case 0: {
                return AxisAlignedBB.getBoundingBox(par2 + h, par3 + h, par4 + h, par2 + 1.0 - h, par3 + 1.0, par4 + 1.0 - h);
            }
            case 1: {
                return AxisAlignedBB.getBoundingBox(par2 + h, par3, par4 + h, par2 + 1.0 - h, par3 + 1.0 - h, par4 + 1.0 - h);
            }
            case 2: {
                return AxisAlignedBB.getBoundingBox(par2 + h, par3 + h, par4 + h, par2 + 1.0 - h, par3 + 1.0 - h, par4 + 1.0);
            }
            case 3: {
                return AxisAlignedBB.getBoundingBox(par2 + h, par3 + h, par4, par2 + 1.0 - h, par3 + 1.0 - h, par4 + 1.0 - h);
            }
            case 4: {
                return AxisAlignedBB.getBoundingBox(par2 + h, par3 + h, par4 + h, par2 + 1.0, par3 + 1.0 - h, par4 + 1.0 - h);
            }
            case 5: {
                return AxisAlignedBB.getBoundingBox(par2, par3 + h, par4 + h, par2 + 1.0 - h, par3 + 1.0 - h, par4 + 1.0 - h);
            }
            default: {
                return AxisAlignedBB.getBoundingBox(par2 + h, par3 + h, par4 + h, par2 + 1.0 - h, par3 + 1.0 - h, par4 + 1.0 - h);
            }
        }
    }

    public void onEntityCollidedWithBlock(final World world, final int x, final int y, final int z, final Entity target) {
        if (world.isRemote || !(world instanceof WorldServer)) {
            return;
        }
        final FakePlayer fakeplayer = FakePlayerFactory.getMinecraft((WorldServer) world);
        if (!(target instanceof EntityLivingBase)) {
            return;
        }
        final TileEntity tile = world.getTileEntity(x, y, z);
        final float damage = this.getDamageMultipliers(4.0f, tile, (EntityLivingBase) target);
        final float h = ((EntityLivingBase) target).getHealth();
        boolean flag;
        if (h > damage || target instanceof EntityPlayer) {
            flag = target.attackEntityFrom(DamageSource.cactus, damage - 0.001f);
        } else if (world.rand.nextInt(5) == 0) {
            flag = this.doPlayerLastHit(world, target, tile);
        } else {
            flag = target.attackEntityFrom(DamageSource.cactus, 400.0f);
        }
        if (flag) {
            this.doPostAttack(world, target, tile, x, y, z);
            if (target instanceof EntityLiving) {
                try {
                    BlockSpike.experienceField.setInt(target, 0);
                } catch (IllegalAccessException ex) {
                }
            }
        }
    }

    public void doPostAttack(final World world, final Entity target, final TileEntity tile, final int x, final int y, final int z) {
        if (!(tile instanceof TileEntityEnchantedSpike)) {
            return;
        }
        final ItemStack stack = this.swordStack.copy();
        stack.setTagCompound(((TileEntityEnchantedSpike) tile).getEnchantmentTagList());
        final int i = EnchantmentHelper.getEnchantmentLevel(Enchantment.knockback.effectId, stack);
        if (i > 0) {
            final int m = world.getBlockMetadata(x, y, z) % 6;
            final float dx = Facing.offsetsXForSide[m];
            final float dz = Facing.offsetsZForSide[m];
            target.addVelocity(-dx * i * 0.5f, 0.1, -dz * i * 0.5f);
        }
        final int j = EnchantmentHelper.getEnchantmentLevel(Enchantment.fireAspect.effectId, stack);
        if (j > 0) {
            target.setFire(j * 4);
        }
    }

    public Item getSwordItem() {
        return Items.iron_sword;
    }

    public float getDamageMultipliers(float f, final TileEntity tile, final EntityLivingBase target) {
        if (!(tile instanceof TileEntityEnchantedSpike)) {
            return f;
        }
        final ItemStack swordStack = new ItemStack(this.getSwordItem(), 1, 0);
        swordStack.setTagCompound(((TileEntityEnchantedSpike) tile).getEnchantmentTagList());
        final float f2 = EnchantmentHelper.func_152377_a(swordStack, target.getCreatureAttribute());
        if (f2 > 0.0f) {
            f += f2;
        }
        return f;
    }

    public void addCollisionBoxesToList(final World par1World, final int par2, final int par3, final int par4, final AxisAlignedBB par5AxisAlignedBB, final List par6List, final Entity par7Entity) {
        if (par7Entity instanceof EntityItem) {
            final AxisAlignedBB axisalignedbb1 = AxisAlignedBB.getBoundingBox(par2, par3, par4, par2 + 1, par3 + 1, par4 + 1);
            if (axisalignedbb1 != null && par5AxisAlignedBB.intersectsWith(axisalignedbb1)) {
                par6List.add(axisalignedbb1);
            }
        } else {
            super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
        }
    }

    public boolean isSideSolid(final IBlockAccess world, final int x, final int y, final int z, final ForgeDirection side) {
        return world.getBlockMetadata(x, y, z) % 6 == side.getOpposite().ordinal();
    }

    public boolean canPlaceTorchOnTop(final World world, final int x, final int y, final int z) {
        return world.getBlockMetadata(x, y, z) % 6 == 1 || super.canPlaceTorchOnTop(world, x, y, z);
    }

    public boolean hasTileEntity(final int metadata) {
        return metadata >= 6;
    }

    public TileEntity createTileEntity(final World world, final int metadata) {
        return new TileEntityEnchantedSpike();
    }

    public boolean doPlayerLastHit(final World world, final Entity target, final TileEntity tile) {
        final FakePlayer fakePlayer = FakePlayerFactory.getMinecraft((WorldServer) world);
        try {
            final ItemStack stack = this.swordStack.copy();
            if (tile instanceof TileEntityEnchantedSpike) {
                stack.setTagCompound(((TileEntityEnchantedSpike) tile).getEnchantmentTagList());
            }
            fakePlayer.setCurrentItemOrArmor(0, stack);
            boolean b = target.attackEntityFrom(DamageSource.causePlayerDamage(fakePlayer), 400.0f);
            fakePlayer.setCurrentItemOrArmor(0, null);
            b |= target.attackEntityFrom(DamageSource.causePlayerDamage(fakePlayer), 400.0f);
            b |= target.attackEntityFrom(DamageSource.cactus, 400.0f);
            return b;
        } finally {
            fakePlayer.setCurrentItemOrArmor(0, null);
        }
    }

    public Item getItemDropped(final int p_149650_1_, final Random p_149650_2_, final int p_149650_3_) {
        return this.itemSpike;
    }

    public static class DamageSourceSpike extends EntityDamageSource {
        public DamageSourceSpike(final String p_i1567_1_, final Entity p_i1567_2_) {
            super(p_i1567_1_, p_i1567_2_);
        }
    }
}


