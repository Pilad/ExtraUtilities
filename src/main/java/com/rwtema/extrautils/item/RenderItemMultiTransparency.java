// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderItemMultiTransparency implements IItemRenderer {
    public boolean handleRenderType(final ItemStack item, final IItemRenderer.ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(final IItemRenderer.ItemRenderType type, final ItemStack item, final IItemRenderer.ItemRendererHelper helper) {
        return helper == IItemRenderer.ItemRendererHelper.ENTITY_ROTATION || helper == IItemRenderer.ItemRendererHelper.ENTITY_BOBBING;
    }

    public void renderItem(final IItemRenderer.ItemRenderType type, final ItemStack item, final Object... data) {
        if (!(item.getItem() instanceof IItemMultiTransparency)) {
            return;
        }
        GL11.glEnable(2896);
        GL11.glEnable(2929);
        final IItemMultiTransparency itemTrans = (IItemMultiTransparency) item.getItem();
        if (type == IItemRenderer.ItemRenderType.INVENTORY) {
            GL11.glScalef(16.0f, 16.0f, 1.0f);
        } else if (type == IItemRenderer.ItemRenderType.ENTITY) {
            GL11.glTranslatef(-0.5f, -0.25f, 0.0f);
            GL11.glDisable(2884);
        }
        final Tessellator tessellator = Tessellator.instance;
        for (int i = 0; i < itemTrans.numIcons(item); ++i) {
            final IIcon icon = itemTrans.getIconForTransparentRender(item, i);
            final float trans = itemTrans.getIconTransparency(item, i);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            GL11.glEnable(3008);
            if (trans < 1.0f) {
                GL11.glBlendFunc(770, 771);
                GL11.glEnable(3042);
                GL11.glDisable(3008);
                GL11.glShadeModel(7425);
                GL11.glColor4f(1.0f, 1.0f, 1.0f, trans);
                if (type != IItemRenderer.ItemRenderType.INVENTORY) {
                    GL11.glEnable(32826);
                    ItemRenderer.renderItemIn2D(tessellator, icon.getMaxU(), icon.getMinV(), icon.getMinU(), icon.getMaxV(), icon.getIconWidth(), icon.getIconHeight(), 0.0625f);
                    GL11.glDisable(32826);
                } else {
                    tessellator.startDrawingQuads();
                    tessellator.addVertexWithUV(0.0, 0.0, 0.03125, icon.getMinU(), icon.getMinV());
                    tessellator.addVertexWithUV(0.0, 1.0, 0.03125, icon.getMinU(), icon.getMaxV());
                    tessellator.addVertexWithUV(1.0, 1.0, 0.03125, icon.getMaxU(), icon.getMaxV());
                    tessellator.addVertexWithUV(1.0, 0.0, 0.03125, icon.getMaxU(), icon.getMinV());
                    tessellator.draw();
                }
                GL11.glShadeModel(7424);
                GL11.glEnable(3008);
                GL11.glDisable(3042);
            } else if (type != IItemRenderer.ItemRenderType.INVENTORY) {
                GL11.glEnable(32826);
                ItemRenderer.renderItemIn2D(tessellator, icon.getMaxU(), icon.getMinV(), icon.getMinU(), icon.getMaxV(), icon.getIconWidth(), icon.getIconHeight(), 0.0625f);
                GL11.glDisable(32826);
            } else {
                tessellator.startDrawingQuads();
                tessellator.addVertexWithUV(0.0, 0.0, 0.0, icon.getMinU(), icon.getMinV());
                tessellator.addVertexWithUV(0.0, 1.0, 0.0, icon.getMinU(), icon.getMaxV());
                tessellator.addVertexWithUV(1.0, 1.0, 0.0, icon.getMaxU(), icon.getMaxV());
                tessellator.addVertexWithUV(1.0, 0.0, 0.0, icon.getMaxU(), icon.getMinV());
                tessellator.draw();
            }
        }
        if (type == IItemRenderer.ItemRenderType.INVENTORY) {
            GL11.glScalef(0.0625f, 0.0625f, 1.0f);
        } else if (type == IItemRenderer.ItemRenderType.ENTITY) {
            GL11.glTranslatef(0.5f, 0.25f, 0.0f);
            GL11.glEnable(2884);
        }
    }
}


