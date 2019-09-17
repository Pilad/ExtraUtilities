// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.network.splitter;

import com.rwtema.extrautils.network.NetworkHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

@ChannelHandler.Sharable
public class PacketRecombiner extends SimpleChannelInboundHandler<XUPacketSplit> {
    public static Map<Integer, ByteBuf[]> map;

    static {
        PacketRecombiner.map = new HashMap<Integer, ByteBuf[]>();
    }

    protected void channelRead0(final ChannelHandlerContext ctx, final XUPacketSplit msg) throws Exception {
        ByteBuf[] b = PacketRecombiner.map.get(msg.packetIndex);
        if (b == null || b.length != msg.total) {
            b = new ByteBuf[msg.total];
        }
        b[msg.seq] = msg.data;
        boolean flag = true;
        int s = 0;
        for (int i = 0; i < b.length && flag; ++i) {
            if (b[i] != null) {
                flag = false;
                s += b[i].readableBytes();
            }
        }
        if (flag) {
            final ByteBuf data = Unpooled.buffer(s);
            for (int j = 0; j < b.length; ++j) {
                data.writeBytes(b[j]);
            }
            final FMLProxyPacket proxy = new FMLProxyPacket(data, NetworkHandler.channels.get(Side.CLIENT).attr(NetworkRegistry.FML_CHANNEL).get());
            NetworkHandler.channels.get(FMLCommonHandler.instance().getEffectiveSide()).writeInbound(proxy);
            PacketRecombiner.map.remove(msg.packetIndex);
            if (PacketRecombiner.map.size() > 1024) {
                PacketRecombiner.map.clear();
            }
        } else {
            PacketRecombiner.map.put(msg.packetIndex, b);
        }
    }
}


