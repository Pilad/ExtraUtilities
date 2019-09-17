// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.gui;

import com.rwtema.extrautils.tileentity.TileEntityTrashCan;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiTrashCan extends GuiContainer {
    private static final ResourceLocation texture;

    static {
        texture = new ResourceLocation("extrautils", "textures/guiTrashCan.png");
    }

    private IInventory player;
    private TileEntityTrashCan trashCan;

    public GuiTrashCan(final IInventory player, final TileEntityTrashCan trashCan) {
        super(new ContainerTrashCan(player, trashCan));
        this.trashCan = trashCan;
        this.player = player;
        this.xSize = 176;
        this.ySize = 222;
    }

    protected void drawGuiContainerBackgroundLayer(final float f, final int i, final int j) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.renderEngine.bindTexture(GuiTrashCan.texture);
        final int k = (this.width - this.xSize) / 2;
        final int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }
}


