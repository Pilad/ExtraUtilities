// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.network;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

@ChannelHandler.Sharable
@SideOnly(Side.CLIENT)
public class PacketHandlerClient extends PacketHandler {
    @Override
    protected void channelRead0(final ChannelHandlerContext ctx, final XUPacketBase msg) throws Exception {
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
            msg.doStuffServer(ctx);
        } else {
            msg.doStuffClient();
        }
    }
}


