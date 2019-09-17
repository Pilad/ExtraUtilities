// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderItemLawSword implements IItemRenderer {
    public static final ResourceLocation temaBlade;

    static {
        temaBlade = new ResourceLocation("extrautils", "textures/rwtemaBlade.png");
    }

    public boolean handleRenderType(final ItemStack item, final IItemRenderer.ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(final IItemRenderer.ItemRenderType type, final ItemStack item, final IItemRenderer.ItemRendererHelper helper) {
        return true;
    }

    public void renderItem(final IItemRenderer.ItemRenderType type, final ItemStack item, final Object... data) {
        double offset = -0.5;
        if (!(item.getItem() instanceof ItemLawSword)) {
            return;
        }
        final boolean firstPerson = type == IItemRenderer.ItemRenderType.EQUIPPED || type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON;
        GL11.glPushMatrix();
        if (type == IItemRenderer.ItemRenderType.EQUIPPED || type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
            offset = 0.0;
        } else if (type == IItemRenderer.ItemRenderType.ENTITY) {
            GL11.glScalef(0.5f, 0.5f, 0.5f);
        }
        GL11.glTranslated(offset, offset, offset);
        if (type == IItemRenderer.ItemRenderType.EQUIPPED) {
            GL11.glTranslated(0.5, 0.5, 0.5);
            GL11.glRotated(50.0, -1.0, 0.0, 1.0);
            GL11.glTranslated(-0.5, -0.5, -0.5);
        }
        GL11.glTranslatef(0.5f, 0.5f, 0.5f);
        final RenderBlocks renderer = (RenderBlocks) data[0];
        renderer.overrideBlockBounds(0.375, 0.0, 0.375, 0.625, 1.0, 0.625);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glDisable(2884);
        Minecraft.getMinecraft().renderEngine.bindTexture(RenderItemLawSword.temaBlade);
        final Tessellator t = Tessellator.instance;
        final float h = 87.0f;
        final float h2 = 20.0f;
        final float w = 18.0f;
        final float w2 = 5.0f;
        final float w3 = 13.0f;
        final double u = w2 / w;
        final float h3 = h2 / h;
        GL11.glScalef(1.7f / h, 1.7f / h, 1.7f / h);
        if (firstPerson) {
            if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
                GL11.glRotated(220.0, 0.0, 1.0, 0.0);
            } else {
                GL11.glRotated(-50.0, 0.0, 1.0, 0.0);
            }
            GL11.glScalef(2.7f, 2.7f, 2.7f);
            GL11.glTranslatef(0.0f, h * 0.3f, 0.0f);
        }
        GL11.glTranslatef(-w2 / 2.0f, -h / 2.0f, 0.0f);
        GL11.glPushMatrix();
        t.startDrawingQuads();
        t.setNormal(0.0f, 0.0f, -1.0f);
        t.addVertexWithUV(0.0, h2, w2 / 2.0f, 0.0, h3);
        t.addVertexWithUV(0.0, h, w2 / 2.0f, 0.0, 1.0);
        t.addVertexWithUV(w2, h, w2 / 2.0f, u, 1.0);
        t.addVertexWithUV(w2, h2, w2 / 2.0f, u, h3);
        t.setNormal(0.0f, 0.0f, -1.0f);
        t.addVertexWithUV(0.0, 0.0, 0.0, 0.0, 0.0);
        t.addVertexWithUV(0.0, h2, 0.0, 0.0, h3);
        t.addVertexWithUV(w2, h2, 0.0, u, h3);
        t.addVertexWithUV(w2, 0.0, 0.0, u, 0.0);
        t.setNormal(0.0f, 0.0f, 1.0f);
        t.addVertexWithUV(w2, 0.0, w2, u, 0.0);
        t.addVertexWithUV(w2, h2, w2, u, h3);
        t.addVertexWithUV(0.0, h2, w2, 0.0, h3);
        t.addVertexWithUV(0.0, 0.0, w2, 0.0, 0.0);
        t.setNormal(1.0f, 0.0f, 0.0f);
        t.addVertexWithUV(w2, 0.0, w2, u, 0.0);
        t.addVertexWithUV(w2, h2, w2, u, h3);
        t.addVertexWithUV(w2, h2, 0.0, 0.0, h3);
        t.addVertexWithUV(w2, 0.0, 0.0, 0.0, 0.0);
        t.setNormal(-1.0f, 0.0f, 0.0f);
        t.addVertexWithUV(0.0, 0.0, 0.0, u, 0.0);
        t.addVertexWithUV(0.0, h2, 0.0, u, h3);
        t.addVertexWithUV(0.0, h2, w2, 0.0, h3);
        t.addVertexWithUV(0.0, 0.0, w2, 0.0, 0.0);
        t.setNormal(0.0f, -1.0f, 0.0f);
        t.addVertexWithUV(0.0, 0.0, 0.0, 9.0f / w, 4.0f / h);
        t.addVertexWithUV(w2, 0.0, 0.0, 13.0f / w, 8.0f / h);
        t.addVertexWithUV(w2, 0.0, w2, 13.0f / w, 8.0f / h);
        t.addVertexWithUV(0.0, 0.0, w2, 9.0f / w, 4.0f / h);
        t.setNormal(0.0f, -1.0f, 0.0f);
        t.addVertexWithUV(-3.0, 16.0, -3.0, 6.0f / w, 18.0f / h);
        t.addVertexWithUV(8.0, 16.0, -3.0, 17.0f / w, 18.0f / h);
        t.addVertexWithUV(8.0, 16.0, 8.0, 17.0f / w, 29.0f / h);
        t.addVertexWithUV(-3.0, 16.0, 8.0, 6.0f / w, 29.0f / h);
        t.setNormal(0.0f, 1.0f, 0.0f);
        t.addVertexWithUV(-3.0, 20.0, -3.0, 6.0f / w, 1.0f / h);
        t.addVertexWithUV(8.0, 20.0, -3.0, 17.0f / w, 1.0f / h);
        t.addVertexWithUV(8.0, 20.0, 8.0, 17.0f / w, 12.0f / h);
        t.addVertexWithUV(-3.0, 20.0, 8.0, 6.0f / w, 12.0f / h);
        t.setNormal(0.0f, 0.0f, -1.0f);
        t.addVertexWithUV(-3.0, 16.0, -3.0, u, 12.0f / h);
        t.addVertexWithUV(-3.0, 20.0, -3.0, u, 17.0f / h);
        t.addVertexWithUV(8.0, 20.0, -3.0, 1.0, 17.0f / h);
        t.addVertexWithUV(8.0, 16.0, -3.0, 1.0, 12.0f / h);
        t.setNormal(0.0f, 0.0f, 1.0f);
        t.addVertexWithUV(-3.0, 16.0, 8.0, u, 12.0f / h);
        t.addVertexWithUV(-3.0, 20.0, 8.0, u, 17.0f / h);
        t.addVertexWithUV(8.0, 20.0, 8.0, 1.0, 17.0f / h);
        t.addVertexWithUV(8.0, 16.0, 8.0, 1.0, 12.0f / h);
        t.setNormal(1.0f, 0.0f, 0.0f);
        t.addVertexWithUV(8.0, 16.0, 8.0, u, 12.0f / h);
        t.addVertexWithUV(8.0, 20.0, 8.0, u, 17.0f / h);
        t.addVertexWithUV(8.0, 20.0, -3.0, 1.0, 17.0f / h);
        t.addVertexWithUV(8.0, 16.0, -3.0, 1.0, 12.0f / h);
        t.setNormal(-1.0f, 0.0f, 0.0f);
        t.addVertexWithUV(-3.0, 16.0, 8.0, u, 12.0f / h);
        t.addVertexWithUV(-3.0, 20.0, 8.0, u, 17.0f / h);
        t.addVertexWithUV(-3.0, 20.0, -3.0, 1.0, 17.0f / h);
        t.addVertexWithUV(-3.0, 16.0, -3.0, 1.0, 12.0f / h);
        t.draw();
        GL11.glPopMatrix();
        renderer.unlockBlockBounds();
        renderer.setRenderBounds(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
        GL11.glPopMatrix();
    }
}


