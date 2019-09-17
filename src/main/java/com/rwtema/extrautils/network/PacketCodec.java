// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.network;

import com.rwtema.extrautils.ExtraUtilsMod;
import com.rwtema.extrautils.LogHelper;
import cpw.mods.fml.common.network.FMLIndexedMessageToMessageCodec;
import cpw.mods.fml.common.network.NetworkRegistry;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

@ChannelHandler.Sharable
public class PacketCodec extends FMLIndexedMessageToMessageCodec<XUPacketBase> {
    public static HashMap<String, Class<? extends XUPacketBase>> classes;

    static {
        PacketCodec.classes = new HashMap<String, Class<? extends XUPacketBase>>();
    }

    public PacketCodec() {
        final ArrayList<String> t = new ArrayList<String>();
        t.addAll(PacketCodec.classes.keySet());
        Collections.sort(t);
        for (int i = 0; i < t.size(); ++i) {
            LogHelper.fine("Registering Packet class " + t.get(i) + " with discriminator: " + i);
            this.addDiscriminator(i, (Class) PacketCodec.classes.get(t.get(i)));
        }
    }

    public static void addClass(final Class clazz) {
        PacketCodec.classes.put(clazz.getSimpleName(), clazz);
    }

    public void encodeInto(final ChannelHandlerContext ctx, final XUPacketBase msg, final ByteBuf target) throws Exception {
        final INetHandler netHandler = ctx.channel().attr(NetworkRegistry.NET_HANDLER).get();
        final EntityPlayer player = ExtraUtilsMod.proxy.getPlayerFromNetHandler(netHandler);
        msg.writeData(target);
    }

    public void decodeInto(final ChannelHandlerContext ctx, final ByteBuf source, final XUPacketBase msg) {
        final INetHandler netHandler = ctx.channel().attr(NetworkRegistry.NET_HANDLER).get();
        final EntityPlayer player = ExtraUtilsMod.proxy.getPlayerFromNetHandler(netHandler);
        msg.readData(player, source);
    }
}


