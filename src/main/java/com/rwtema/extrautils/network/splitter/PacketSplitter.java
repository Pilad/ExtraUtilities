// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.network.splitter;

import com.rwtema.extrautils.LogHelper;
import com.rwtema.extrautils.helper.XUHelper;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.ArrayList;
import java.util.List;

public class PacketSplitter {
    public static final int maxSize = 2007136;
    static int curSendingIndex;

    static {
        PacketSplitter.curSendingIndex = XUHelper.rand.nextInt();
    }

    public static boolean shouldSplit(final FMLProxyPacket packet) {
        return packet.payload().readableBytes() >= 2007136;
    }

    public static List<XUPacketSplit> splitPacket(final FMLProxyPacket packet) {
        final List<XUPacketSplit> out = new ArrayList<XUPacketSplit>();
        final ByteBuf buf = packet.payload().copy();
        int n = buf.readableBytes() / 2007136;
        if (n * 2007136 < buf.readableBytes()) {
            ++n;
        }
        ++PacketSplitter.curSendingIndex;
        LogHelper.debug("Splitting packet to " + n + " packets");
        for (int i = 0; i < n; ++i) {
            final int s = (buf.readableBytes() < 2007136) ? buf.readableBytes() : 2007136;
            final ByteBuf o = Unpooled.buffer(s);
            buf.readBytes(o, s);
            out.add(new XUPacketSplit(buf, PacketSplitter.curSendingIndex, i, n));
        }
        return out;
    }
}


