// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.crafting;

import com.mojang.authlib.GameProfile;
import com.rwtema.extrautils.ExtraUtils;
import com.rwtema.extrautils.helper.XUHelper;
import com.rwtema.extrautils.helper.XURandom;
import com.rwtema.extrautils.item.ItemLawSword;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.BlockFlower;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.DimensionManager;

import java.util.List;
import java.util.Random;

public class LawSwordCraftHandler {
    private final Random rand;
    private final String[] strings;
    private final String flowerType = "tulipPink";
    private final int pinkColor;
    private final double pinkR;
    private final double pinkG;
    private final double pinkB;

    public LawSwordCraftHandler() {
        this.rand = XURandom.getInstance();
        this.strings = new String[]{"I feel pretty", "Oh, so pretty", "I feel pretty", "and witty and bright!", "All you need is Wuv!"};
        this.pinkColor = 15892389;
        this.pinkR = (this.pinkColor >> 16 & 0xFF) / 255.0;
        this.pinkG = (this.pinkColor >> 8 & 0xFF) / 255.0;
        this.pinkB = (this.pinkColor & 0xFF) / 255.0;
    }

    public static void init() {
        FMLCommonHandler.instance().bus().register(new LawSwordCraftHandler());
    }

    public static ItemStack createNewStack() {
        return ItemLawSword.newSword();
    }

    public static ItemStack newRoll() {
        return XUHelper.addLore(XUHelper.addEnchant(new ItemStack(Items.record_13, 1, 101).setStackDisplayName("Rick Astley - Never gonna give you up!"), Enchantment.unbreaking, 1), "Awesome music to exercise to.", "The greatest gift a pretty fairy could ask for.", "Were you expecting something else?");
    }

    public static boolean shouldTroll(final EntityPlayer player) {
        final long seed = DimensionManager.getWorld(0).getSeed();
        int hash = (int) (seed ^ seed >>> 32);
        hash = hash * 31 + ExtraUtils.versionHash;
        final GameProfile gameProfile = player.getGameProfile();
        if (gameProfile == null || gameProfile.getId() == null || gameProfile.getName() == null) {
            return true;
        }
        hash = hash * 31 + gameProfile.getId().hashCode();
        hash = hash * 31 + gameProfile.getName().hashCode();
        return new Random(hash).nextBoolean();
    }

    @SubscribeEvent
    public void event(final TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            return;
        }
        if (event.side == Side.CLIENT) {
            for (int i = 1; i < 5; ++i) {
                final ItemStack a = event.player.getEquipmentInSlot(i);
                if (a == null || !a.hasTagCompound()) {
                    return;
                }
                if (!(a.getItem() instanceof ItemArmor)) {
                    return;
                }
                final ItemArmor b = (ItemArmor) a.getItem();
                if (b.getColor(a) != this.pinkColor) {
                    return;
                }
                if (!this.strings[4 - i].equals(a.getDisplayName())) {
                    return;
                }
            }
            final AxisAlignedBB bb = event.player.boundingBox;
            for (int j = 0; j < 5; ++j) {
                event.player.worldObj.spawnParticle("reddust", bb.minX + XUHelper.rand.nextFloat() * (bb.maxX - bb.minX), bb.minY + XUHelper.rand.nextFloat() * (bb.maxY - bb.minY) * (1 + j) / 5.0, bb.minZ + XUHelper.rand.nextFloat() * (bb.maxZ - bb.minZ), this.pinkR, this.pinkG, this.pinkB);
            }
            return;
        }
        if (this.rand.nextInt(XUHelper.deObf ? 20 : 800) != 0) {
            return;
        }
        if (this.isPlayerCreative(event)) {
            return;
        }
        if (!this.isPlayerSprinting(event)) {
            return;
        }
        if (this.isPlayerAlone()) {
            return;
        }
        for (int i = 0; i < 5; ++i) {
            final ItemStack a = event.player.getEquipmentInSlot(i);
            if (a == null) {
                return;
            }
            if (i == 0) {
                if (!this.isPinkFlower(a)) {
                    return;
                }
            } else {
                if (!(a.getItem() instanceof ItemArmor)) {
                    return;
                }
                final ItemArmor b = (ItemArmor) a.getItem();
                if (b.getColor(a) != this.pinkColor) {
                    return;
                }
            }
            if (!this.strings[4 - i].equals(a.getDisplayName())) {
                return;
            }
        }
        this.handlePlayer(event.player);
    }

    public boolean isPinkFlower(final ItemStack a) {
        return a.getItem() == Item.getItemFromBlock(BlockFlower.func_149857_e("tulipPink")) && a.getItemDamage() == BlockFlower.func_149856_f("tulipPink");
    }

    public boolean isPlayerCreative(final TickEvent.PlayerTickEvent event) {
        return event.player.capabilities.isCreativeMode;
    }

    public boolean isPlayerSprinting(final TickEvent.PlayerTickEvent event) {
        return event.player.isSprinting();
    }

    public boolean isPlayerAlone() {
        return !XUHelper.deObf && MinecraftServer.getServer().getConfigurationManager().playerEntityList.size() <= 1;
    }

    public boolean handlePlayer(final EntityPlayer player) {
        for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
            final ItemStack item = player.inventory.getStackInSlot(i);
            if (item != null && item.getItem() == ExtraUtils.ethericSword && player.inventory.decrStackSize(i, 1) != null) {
                final boolean troll = shouldTroll(player);
                player.inventory.addItemStackToInventory(troll ? newRoll() : createNewStack());
                final List<EntityPlayer> playerList = (List<EntityPlayer>) MinecraftServer.getServer().getConfigurationManager().playerEntityList;
                for (final EntityPlayer p : playerList) {
                    p.addChatComponentMessage(new ChatComponentText(player.getCommandSenderName()).appendText(" is the Prettiest Pink Princess"));
                    if (troll) {
                        p.addChatComponentMessage(new ChatComponentText("but sadly not a very lucky one"));
                    }
                }
                player.addChatComponentMessage(new ChatComponentText("Thanks to your commitment to exercise and prettiness. You have been granted the greatest gift!"));
                return true;
            }
        }
        return false;
    }
}


