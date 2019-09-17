// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils;

import com.rwtema.extrautils.item.ItemAngelRing;
import com.rwtema.extrautils.network.PacketHandler;
import com.rwtema.extrautils.network.packets.PacketUseItemAlt;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class ExtraUtilsProxy {
    public static ExtraUtilsProxy INSTANCE;
    public static int colorBlockID;
    public static int fullBrightBlockID;
    public static int multiBlockID;
    public static int spikeBlockID;
    public static int drumRendererID;
    public static int connectedTextureID;
    public static int connectedTextureEtheralID;
    public static Block FMPBlockId;
    public static boolean checked;
    public static Item MicroBlockId;
    public static boolean checked2;

    static {
        ExtraUtilsProxy.colorBlockID = 0;
        ExtraUtilsProxy.fullBrightBlockID = 0;
        ExtraUtilsProxy.multiBlockID = 0;
        ExtraUtilsProxy.spikeBlockID = 0;
        ExtraUtilsProxy.drumRendererID = 0;
        ExtraUtilsProxy.connectedTextureID = 0;
        ExtraUtilsProxy.connectedTextureEtheralID = 0;
        ExtraUtilsProxy.FMPBlockId = null;
        ExtraUtilsProxy.checked = false;
        ExtraUtilsProxy.MicroBlockId = null;
        ExtraUtilsProxy.checked2 = false;
    }

    public ExtraUtilsProxy() {
        ExtraUtilsProxy.INSTANCE = this;
    }

    public void postInit() {
    }

    public void registerEventHandler() {
        MinecraftForge.EVENT_BUS.register(new EventHandlerServer());
        MinecraftForge.EVENT_BUS.register(new EventHandlerSiege());
        MinecraftForge.EVENT_BUS.register(new EventHandlerEntityItemStealer());
        MinecraftForge.EVENT_BUS.register(new EventHandlerChunkLoad());
    }

    public void registerRenderInformation() {
    }

    public EntityPlayer getPlayerFromNetHandler(final INetHandler handler) {
        if (handler instanceof NetHandlerPlayServer) {
            return ((NetHandlerPlayServer) handler).playerEntity;
        }
        return null;
    }

    public void throwLoadingError(final String cause, final String... message) {
        String concat = cause + ": ";
        for (final String m : message) {
            concat = concat + " - " + m;
        }
        throw new RuntimeException(concat);
    }

    public EntityPlayer getClientPlayer() {
        throw new RuntimeException("getClientPlayer called on server");
    }

    public World getClientWorld() {
        throw new RuntimeException("getClientWorld called on server");
    }

    public boolean isClientSideAvailable() {
        return false;
    }

    public void newServerStart() {
        if (ExtraUtils.angelRingEnabled) {
            ItemAngelRing.curFlyingPlayers.clear();
        }
    }

    public void registerClientCommands() {
    }

    public PacketHandler getNewPacketHandler() {
        return new PacketHandler();
    }

    public void exectuteClientCode(final IClientCode clientCode) {
    }

    public void sendUsePacket(final PlayerInteractEvent event) {
    }

    public void sendUsePacket(final int x, final int y, final int z, final int face, final ItemStack item, final float f, final float f1, final float f2) {
    }

    public void sendAltUsePacket(final int x, final int y, final int z, final int face, final ItemStack item, final float f, final float f1, final float f2) {
    }

    public void sendAltUsePacket(final ItemStack item) {
    }

    public boolean isAltSneaking() {
        return PacketUseItemAlt.altPlace.get() == Boolean.TRUE;
    }

    public <F, T> T apply(final ISidedFunction<F, T> func, final F input) {
        return func.applyServer(input);
    }
}


