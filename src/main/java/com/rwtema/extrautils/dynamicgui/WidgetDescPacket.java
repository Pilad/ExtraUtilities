// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.dynamicgui;

import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

public abstract class WidgetDescPacket extends WidgetBase {
    public WidgetDescPacket() {
        super(0, 0, 0, 0);
    }

    @Override
    public void renderForeground(final TextureManager manager, final DynamicGui gui, final int guiLeft, final int guiTop) {
    }

    @Override
    public void renderBackground(final TextureManager manager, final DynamicGui gui, final int guiLeft, final int guiTop) {
    }

    @Override
    public List<String> getToolTip() {
        return null;
    }

    @Override
    public abstract NBTTagCompound getDescriptionPacket(final boolean p0);

    @Override
    public abstract void handleDescriptionPacket(final NBTTagCompound p0);
}


