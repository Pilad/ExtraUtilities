// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.item.scanner;

import com.rwtema.extrautils.ExtraUtils;
import com.rwtema.extrautils.network.packets.PacketTempChatMultiline;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;

public class ItemScanner extends Item {
    public static boolean scannerOut;

    static {
        ItemScanner.scannerOut = false;
    }

    public ItemScanner() {
        this.setTextureName("extrautils:scanner");
        this.setUnlocalizedName("extrautils:scanner");
        this.setCreativeTab(ExtraUtils.creativeTabExtraUtils);
        this.setMaxStackSize(1);
    }

    public void onUpdate(final ItemStack par1ItemStack, final World par2World, final Entity par3Entity, final int par4, final boolean par5) {
        ItemScanner.scannerOut = true;
        super.onUpdate(par1ItemStack, par2World, par3Entity, par4, par5);
    }

    public boolean onItemUse(final ItemStack stack, final EntityPlayer player, final World world, final int x, final int y, final int z, final int side, final float hitX, final float hitY, final float hitZ) {
        if (world.isRemote) {
            return true;
        }
        final Block b = world.getBlock(x, y, z);
        PacketTempChatMultiline.addChatComponentMessage(new ChatComponentText("~~~~~Scan~~~~~"));
        PacketTempChatMultiline.addChatComponentMessage(new ChatComponentText("Block name: " + b.getUnlocalizedName()));
        PacketTempChatMultiline.addChatComponentMessage(new ChatComponentText("Block metadata: " + world.getBlockMetadata(x, y, z)));
        final Object tile = world.getTileEntity(x, y, z);
        if (tile == null) {
            PacketTempChatMultiline.sendCached(player);
            return false;
        }
        final ForgeDirection dir = ForgeDirection.getOrientation(side);
        final List<String> data = ScannerRegistry.getData(tile, dir, player);
        for (final String aData : data) {
            PacketTempChatMultiline.addChatComponentMessage(new ChatComponentText(aData));
        }
        PacketTempChatMultiline.sendCached(player);
        return true;
    }

    public boolean itemInteractionForEntity(final ItemStack par1ItemStack, final EntityPlayer player, final EntityLivingBase entity) {
        if (player.worldObj.isRemote) {
            return true;
        }
        if (entity == null) {
            return false;
        }
        final List<String> data = ScannerRegistry.getData(entity, ForgeDirection.UP, player);
        for (final String aData : data) {
            PacketTempChatMultiline.addChatComponentMessage(new ChatComponentText(aData));
        }
        PacketTempChatMultiline.sendCached(player);
        return true;
    }
}


