// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.network.splitter;

import io.netty.buffer.ByteBuf;

public class XUPacketSplit {
    ByteBuf data;
    int seq;
    int packetIndex;
    int total;

    public XUPacketSplit() {
    }

    public XUPacketSplit(final ByteBuf data, final int packetIndex, final int seq, final int total) {
        this.data = data;
        this.packetIndex = packetIndex;
        this.seq = seq;
        this.total = total;
    }
}


