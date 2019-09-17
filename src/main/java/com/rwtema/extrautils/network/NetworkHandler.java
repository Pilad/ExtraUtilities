// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.network;

import com.rwtema.extrautils.ExtraUtils;
import com.rwtema.extrautils.ExtraUtilsMod;
import com.rwtema.extrautils.helper.XUHelper;
import com.rwtema.extrautils.item.scanner.ItemScanner;
import com.rwtema.extrautils.network.packets.PacketParticle;
import com.rwtema.extrautils.network.packets.PacketParticleEvent;
import com.rwtema.extrautils.network.packets.PacketPlaySound;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;

import java.util.Collection;
import java.util.EnumMap;

public class NetworkHandler {
    public static EnumMap<Side, FMLEmbeddedChannel> channels;

    public static void register() {
        NetworkHandler.channels = NetworkRegistry.INSTANCE.newChannel("XU|Pkt", new ChannelHandler[]{new PacketCodec(), ExtraUtilsMod.proxy.getNewPacketHandler()});
    }

    public static void checkPacket(final XUPacketBase packet, final Side properSenderSide) {
        if (!packet.isValidSenderSide(properSenderSide)) {
            throw new RuntimeException("Sending packet class" + packet.getClass().getSimpleName() + " from wrong side");
        }
    }

    public static void sendToAllPlayers(final XUPacketBase packet) {
        checkPacket(packet, Side.SERVER);
        NetworkHandler.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALL);
        NetworkHandler.channels.get(Side.SERVER).writeOutbound(packet);
    }

    public static void sendPacketToPlayer(final XUPacketBase packet, final EntityPlayer player) {
        checkPacket(packet, Side.SERVER);
        if (XUHelper.isPlayerFake(player)) {
            return;
        }
        NetworkHandler.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.PLAYER);
        NetworkHandler.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(player);
        NetworkHandler.channels.get(Side.SERVER).writeOutbound(packet);
    }

    public static void sendToAllAround(final XUPacketBase packet, final int dimension, final double x, final double y, final double z, final double range) {
        sendToAllAround(packet, new NetworkRegistry.TargetPoint(dimension, x, y, z, range));
    }

    public static void sendToAllAround(final XUPacketBase packet, final NetworkRegistry.TargetPoint point) {
        checkPacket(packet, Side.SERVER);
        NetworkHandler.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALLAROUNDPOINT);
        NetworkHandler.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(point);
        NetworkHandler.channels.get(Side.SERVER).writeAndFlush(packet).addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
    }

    public static void sendPacketToServer(final XUPacketBase packet) {
        checkPacket(packet, Side.CLIENT);
        NetworkHandler.channels.get(Side.CLIENT).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.TOSERVER);
        NetworkHandler.channels.get(Side.CLIENT).writeOutbound(packet);
    }

    public static void sendParticle(final World world, final String p, final double x, final double y, final double z, final double vx, final double vy, final double vz, final boolean scannersOnly) {
        final int maxDistance = 32;
        if (scannersOnly && ExtraUtils.scanner == null) {
            return;
        }
        final PacketParticle packet = new PacketParticle(p, x, y, z, vx, vy, vz);
        if (scannersOnly && !ItemScanner.scannerOut) {
            return;
        }
        final boolean noScanners = true;
        for (int j = 0; j < world.playerEntities.size(); ++j) {
            final EntityPlayerMP player = (EntityPlayerMP) world.playerEntities.get(j);
            if ((!scannersOnly || (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == ExtraUtils.scanner)) && Math.abs(player.posX - x) <= maxDistance && Math.abs(player.posY - y) <= maxDistance && Math.abs(player.posZ - z) <= maxDistance) {
                sendPacketToPlayer(packet, player);
            }
        }
    }

    public static void sendParticleEvent(final World world, final int type, final int x, final int y, final int z) {
        final int maxDistance = 24;
        if (type < 0) {
            return;
        }
        final PacketParticleEvent packet = new PacketParticleEvent(x, y, z, (byte) type);
        sendToAllAround(packet, world.provider.dimensionId, x, y, z, maxDistance);
    }

    public static void sendSoundEvent(final World world, final int type, final float x, final float y, final float z) {
        final int maxDistance = 32;
        if (type < 0) {
            return;
        }
        final PacketPlaySound packet = new PacketPlaySound((short) type, x, y, z);
        sendToAllAround(packet, world.provider.dimensionId, x, y, z, maxDistance);
    }

    public static void sendToAllAround(final XUPacketBase packet, final int chunkX, final int chunkZ) {
        final ChunkCoordIntPair chunkCoordIntPair = new ChunkCoordIntPair(chunkX, chunkZ);
        for (final EntityPlayerMP player : (Collection<EntityPlayerMP>) FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().playerEntityList) {
            if (player.loadedChunks.contains(chunkCoordIntPair)) {
                sendPacketToPlayer(packet, player);
            }
        }
    }
}


