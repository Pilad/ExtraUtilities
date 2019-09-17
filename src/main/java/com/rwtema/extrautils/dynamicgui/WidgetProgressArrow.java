// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.dynamicgui;

import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

public abstract class WidgetProgressArrow extends WidgetBase implements IWidget {
    byte curWidth;

    public WidgetProgressArrow(final int x, final int y) {
        super(x, y, 22, 17);
        this.curWidth = -1;
    }

    public abstract int getWidth();

    @Override
    public NBTTagCompound getDescriptionPacket(final boolean changesOnly) {
        NBTTagCompound tag = null;
        final byte newWidth = this.getAdjustedWidth(this.getWidth());
        if (!changesOnly || this.curWidth != newWidth) {
            tag = new NBTTagCompound();
            tag.setByte("a", newWidth);
        }
        this.curWidth = newWidth;
        return tag;
    }

    private byte getAdjustedWidth(int a) {
        if (a < 0) {
            a = 0;
        } else if (a > 22) {
            a = 22;
        }
        return (byte) a;
    }

    @Override
    public void handleDescriptionPacket(final NBTTagCompound packet) {
        if (packet.hasKey("a")) {
            this.curWidth = packet.getByte("a");
        }
    }

    @Override
    public void renderForeground(final TextureManager manager, final DynamicGui gui, final int guiLeft, final int guiTop) {
        manager.bindTexture(gui.getWidgets());
        gui.drawTexturedModalRect(guiLeft + this.getX(), guiTop + this.getY(), 98, 16, this.curWidth, 16);
    }

    @Override
    public void renderBackground(final TextureManager manager, final DynamicGui gui, final int guiLeft, final int guiTop) {
        manager.bindTexture(gui.getWidgets());
        gui.drawTexturedModalRect(guiLeft + this.getX(), guiTop + this.getY(), 98, 0, 22, 16);
    }

    @Override
    public List<String> getToolTip() {
        return null;
    }
}


