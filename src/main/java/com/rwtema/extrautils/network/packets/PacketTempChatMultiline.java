// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.network.packets;

import com.rwtema.extrautils.helper.XUHelper;
import com.rwtema.extrautils.network.NetworkHandler;
import com.rwtema.extrautils.network.XUPacketBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

import java.util.ArrayList;
import java.util.List;

public class PacketTempChatMultiline extends XUPacketBase {
    static final int START_ID = 983423323;
    static int lastNum;
    static ThreadLocal<List<IChatComponent>> chatComponents;

    static {
        PacketTempChatMultiline.lastNum = 0;
        PacketTempChatMultiline.chatComponents = new ThreadLocal<List<IChatComponent>>() {
            @Override
            protected List<IChatComponent> initialValue() {
                return new ArrayList<IChatComponent>();
            }
        };
    }

    IChatComponent[] chat;

    public PacketTempChatMultiline() {
    }

    public PacketTempChatMultiline(final List<String> chat) {
        this.chat = new IChatComponent[chat.size()];
        for (int i = 0; i < chat.size(); ++i) {
            this.chat[i] = new ChatComponentText(chat.get(i));
        }
    }

    public PacketTempChatMultiline(final IChatComponent[] chat) {
        this.chat = chat;
    }

    private static synchronized void addChat(final IChatComponent[] chat) {
        final GuiNewChat chatGUI = Minecraft.getMinecraft().ingameGUI.getChatGUI();
        for (int i = 0; i < chat.length; ++i) {
            final IChatComponent iChatComponent = chat[i];
            chatGUI.printChatMessageWithOptionalDeletion(iChatComponent, 983423323 + i);
        }
        for (int i = chat.length; i < PacketTempChatMultiline.lastNum; ++i) {
            chatGUI.deleteChatLine(983423323 + i);
        }
        PacketTempChatMultiline.lastNum = Math.max(PacketTempChatMultiline.lastNum, chat.length);
    }

    public static void addChatComponentMessage(final IChatComponent chatComponentText) {
        PacketTempChatMultiline.chatComponents.get().add(chatComponentText);
    }

    public static void sendCached(final EntityPlayer player) {
        final List<IChatComponent> componentList = PacketTempChatMultiline.chatComponents.get();
        if (componentList.isEmpty()) {
            return;
        }
        if (!XUHelper.isPlayerFake(player)) {
            final IChatComponent[] iChatComponents = componentList.toArray(new IChatComponent[componentList.size()]);
            if (player.worldObj.isRemote) {
                addChat(iChatComponents);
            } else {
                NetworkHandler.sendPacketToPlayer(new PacketTempChatMultiline(iChatComponents), player);
            }
        }
        componentList.clear();
    }

    @Override
    public void writeData(final ByteBuf data) throws Exception {
        data.writeShort(this.chat.length);
        for (final IChatComponent iChatComponent : this.chat) {
            this.writeChatComponent(data, iChatComponent);
        }
    }

    @Override
    public void readData(final EntityPlayer player, final ByteBuf data) {
        this.chat = new IChatComponent[data.readUnsignedShort()];
        for (int i = 0; i < this.chat.length; ++i) {
            this.chat[i] = this.readChatComponent(data);
        }
    }

    @Override
    public void doStuffServer(final ChannelHandlerContext ctx) {
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void doStuffClient() {
        addChat(this.chat);
    }

    @Override
    public boolean isValidSenderSide(final Side properSenderSide) {
        return properSenderSide == Side.SERVER;
    }
}


