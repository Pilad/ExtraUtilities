// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.network.packets;

import com.rwtema.extrautils.nei.ping.ParticlePing;
import com.rwtema.extrautils.network.XUPacketBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.ChunkPosition;

import java.util.ArrayList;
import java.util.List;

public class PacketNEIPong extends XUPacketBase {
    public static final int MAX_SIZE = 20;
    List<ChunkPosition> positionList;

    public PacketNEIPong() {
    }

    public PacketNEIPong(final List<ChunkPosition> positionList) {
        this.positionList = positionList;
    }

    @Override
    public void writeData(final ByteBuf data) throws Exception {
        data.writeByte(this.positionList.size());
        for (final ChunkPosition pos : this.positionList) {
            data.writeInt(pos.chunkPosX);
            data.writeByte(pos.chunkPosY);
            data.writeInt(pos.chunkPosZ);
        }
    }

    @Override
    public void readData(final EntityPlayer player, final ByteBuf data) {
        int n = data.readUnsignedByte();
        if (n > 20) {
            n = 20;
        }
        this.positionList = new ArrayList<ChunkPosition>(n);
        for (int i = 0; i < n; ++i) {
            final int x = data.readInt();
            final int y = data.readUnsignedByte();
            final int z = data.readInt();
            this.positionList.add(new ChunkPosition(x, y, z));
        }
    }

    @Override
    public void doStuffServer(final ChannelHandlerContext ctx) {
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void doStuffClient() {
        Minecraft.getMinecraft().thePlayer.closeScreen();
        for (final ChunkPosition chunkPosition : this.positionList) {
            for (int i = 0; i < 20; ++i) {
                Minecraft.getMinecraft().effectRenderer.addEffect(new ParticlePing(Minecraft.getMinecraft().theWorld, chunkPosition));
            }
        }
    }

    @Override
    public boolean isValidSenderSide(final Side properSenderSide) {
        return properSenderSide == Side.SERVER;
    }
}


