// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils;

import cpw.mods.fml.client.CustomModLoadingErrorDisplayException;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiErrorScreen;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class CustomErrorWGui extends CustomModLoadingErrorDisplayException {
    String cause;
    String[] message;

    public CustomErrorWGui(final String cause, final String... message) {
        this.cause = cause;
        this.message = message;
    }

    public void initGui(final GuiErrorScreen errorScreen, final FontRenderer fontRenderer) {
    }

    public void drawScreen(final GuiErrorScreen errorScreen, final FontRenderer fontRenderer, final int mouseRelX, final int mouseRelY, final float tickTime) {
        errorScreen.drawDefaultBackground();
        final List t = new ArrayList();
        for (final String m : this.message) {
            if (m != null) {
                t.addAll(fontRenderer.listFormattedStringToWidth(m, errorScreen.width));
            }
        }
        int offset = Math.max(85 - t.size() * 10, 10);
        errorScreen.drawCenteredString(fontRenderer, this.cause, errorScreen.width / 2, offset, 16777215);
        offset += 10;
        for (final Object aT : t) {
            errorScreen.drawCenteredString(fontRenderer, (String) aT, errorScreen.width / 2, offset, 16777215);
            offset += 10;
        }
    }
}


