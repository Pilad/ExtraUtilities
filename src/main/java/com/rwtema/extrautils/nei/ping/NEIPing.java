// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.nei.ping;

import codechicken.nei.LayoutManager;
import codechicken.nei.NEIClientConfig;
import codechicken.nei.api.API;
import codechicken.nei.guihook.GuiContainerManager;
import codechicken.nei.guihook.IContainerInputHandler;
import com.rwtema.extrautils.network.NetworkHandler;
import com.rwtema.extrautils.network.packets.PacketNEIPing;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;

public class NEIPing implements IContainerInputHandler {
    public static final String KEY_IDENTIFIER = "gui.xu_ping";

    public static void init() {
        API.addKeyBind("gui.xu_ping", 20);
        GuiContainerManager.addInputHandler(new NEIPing());
    }

    public boolean keyTyped(final GuiContainer guiContainer, final char c, final int i) {
        final int keyBinding = NEIClientConfig.getKeyBinding("gui.xu_ping");
        if (i != keyBinding) {
            return false;
        }
        final LayoutManager layout = LayoutManager.instance();
        if (layout == null || LayoutManager.itemPanel == null || NEIClientConfig.isHidden()) {
            return false;
        }
        final ItemStack stackMouseOver = GuiContainerManager.getStackMouseOver(guiContainer);
        if (stackMouseOver == null || stackMouseOver.getItem() == null) {
            return false;
        }
        NetworkHandler.sendPacketToServer(new PacketNEIPing(stackMouseOver));
        return true;
    }

    public void onKeyTyped(final GuiContainer guiContainer, final char c, final int i) {
    }

    public boolean lastKeyTyped(final GuiContainer guiContainer, final char c, final int i) {
        return false;
    }

    public boolean mouseClicked(final GuiContainer guiContainer, final int i, final int i1, final int i2) {
        return false;
    }

    public void onMouseClicked(final GuiContainer guiContainer, final int i, final int i1, final int i2) {
    }

    public void onMouseUp(final GuiContainer guiContainer, final int i, final int i1, final int i2) {
    }

    public boolean mouseScrolled(final GuiContainer guiContainer, final int i, final int i1, final int i2) {
        return false;
    }

    public void onMouseScrolled(final GuiContainer guiContainer, final int i, final int i1, final int i2) {
    }

    public void onMouseDragged(final GuiContainer guiContainer, final int i, final int i1, final int i2, final long l) {
    }
}


