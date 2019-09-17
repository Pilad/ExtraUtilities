// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiFilterPipe extends GuiContainer {
    private static final ResourceLocation texture;

    static {
        texture = new ResourceLocation("extrautils", "textures/guiSortingPipe.png");
    }

    public GuiFilterPipe(final IInventory player, final IInventory pipe) {
        super(new ContainerFilterPipe(player, pipe));
        this.xSize = 175;
        this.ySize = 192;
    }

    protected void drawGuiContainerBackgroundLayer(final float f, final int i, final int j) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.renderEngine.bindTexture(GuiFilterPipe.texture);
        final int k = (this.width - this.xSize) / 2;
        final int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }
}


