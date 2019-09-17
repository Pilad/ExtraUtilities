// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.block.render;

import com.rwtema.extrautils.ExtraUtilsProxy;
import com.rwtema.extrautils.item.ItemBlockDrum;
import com.rwtema.extrautils.texture.LiquidColorRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fluids.FluidStack;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderBlockDrum implements ISimpleBlockRenderingHandler {
    public static final double numTex = 3.0;
    public static double w;
    static float base_w;

    static {
        RenderBlockDrum.w = 0.5;
        RenderBlockDrum.base_w = 0.425f;
    }

    public static double du(final double i) {
        return i % 3.0 / 3.0;
    }

    public static double du2(final double i) {
        return (i % 3.0 + 1.0) / 3.0;
    }

    public static double dx(final double i) {
        return 0.5 + ddx(i) * RenderBlockDrum.w;
    }

    public static double dz(final double i) {
        return 0.5 + ddz(i) * RenderBlockDrum.w;
    }

    public static float ddx(final double i) {
        return (float) Math.cos(-(0.5 + i) / 8.0 * 2.0 * 3.141592653589793);
    }

    public static float ddz(final double i) {
        return (float) Math.sin(-(0.5 + i) / 8.0 * 2.0 * 3.141592653589793);
    }

    public static void drawInvBlock(final Block block, final ItemStack item) {
        final float h = 0.97f;
        final float d = 0.2f;
        final float h2 = 0.3125f;
        int l = 16777215;
        final int meta = item.getItemDamage();
        if (item.hasTagCompound() && item.stackTagCompound.hasKey("color")) {
            l = item.stackTagCompound.getInteger("color");
        }
        final FluidStack fluid = ((ItemBlockDrum) item.getItem()).getFluid(item);
        l = LiquidColorRegistry.getFluidColor(fluid);
        final float f = (l >> 16 & 0xFF) / 255.0f;
        final float f2 = (l >> 8 & 0xFF) / 255.0f;
        final float f3 = (l & 0xFF) / 255.0f;
        final Tessellator t = Tessellator.instance;
        t.setColorOpaque_F(1.0f, 1.0f, 1.0f);
        GL11.glTranslatef(-0.0f, -0.5f, -0.0f);
        IIcon icon = block.getIcon(2, meta);
        float wu = icon.getMaxU() - icon.getMinU();
        float wv = icon.getMaxV() - icon.getMinV();
        final float ddv = wv * 0.3125f;
        for (int i = 0; i < 8; ++i) {
            RenderBlockDrum.w = RenderBlockDrum.base_w * h;
            t.startDrawingQuads();
            t.setNormal(ddx(i + 0.5), 0.0f, ddz(i + 0.5));
            t.setColorOpaque_F(f, f2, f3);
            t.addVertexWithUV(dx(i + 1), 0.0, dz(i + 1), icon.getMinU() + du2(i) * wu, icon.getMaxV());
            t.addVertexWithUV(dx(i + 1), h2, dz(i + 1), icon.getMinU() + du2(i) * wu, icon.getMaxV() - ddv);
            t.addVertexWithUV(dx(i), h2, dz(i), icon.getMinU() + du(i) * wu, icon.getMaxV() - ddv);
            t.addVertexWithUV(dx(i), 0.0, dz(i), icon.getMinU() + du(i) * wu, icon.getMaxV());
            t.addVertexWithUV(dx(i + 1), h2 * 2.0f, dz(i + 1), icon.getMinU() + du2(i) * wu, icon.getMinV() + ddv);
            t.addVertexWithUV(dx(i + 1), 1.0, dz(i + 1), icon.getMinU() + du2(i) * wu, icon.getMinV());
            t.addVertexWithUV(dx(i), 1.0, dz(i), icon.getMinU() + du(i) * wu, icon.getMinV());
            t.addVertexWithUV(dx(i), h2 * 2.0f, dz(i), icon.getMinU() + du(i) * wu, icon.getMinV() + ddv);
            t.draw();
            t.startDrawingQuads();
            t.setColorOpaque_F(1.0f, 1.0f, 1.0f);
            t.setNormal(ddx(i + 0.5), 0.0f, ddz(i + 0.5));
            t.addVertexWithUV(dx(i + 1), h2, dz(i + 1), icon.getMinU() + du2(i) * wu, icon.getMaxV() - ddv);
            t.addVertexWithUV(dx(i + 1), h2 * 2.0f, dz(i + 1), icon.getMinU() + du2(i) * wu, icon.getMinV() + ddv);
            t.addVertexWithUV(dx(i), h2 * 2.0f, dz(i), icon.getMinU() + du(i) * wu, icon.getMinV() + ddv);
            t.addVertexWithUV(dx(i), h2, dz(i), icon.getMinU() + du(i) * wu, icon.getMaxV() - ddv);
            t.draw();
            RenderBlockDrum.w = RenderBlockDrum.base_w;
            t.startDrawingQuads();
            t.setColorOpaque_F(0.6f, 0.6f, 0.6f);
            t.setNormal(ddx(i + 0.5), 0.0f, ddz(i + 0.5));
            t.addVertexWithUV(dx(i + 1), 0.0, dz(i + 1), icon.getMinU() + du2(i) * wu, icon.getMaxV());
            t.addVertexWithUV(dx(i + 1), 0.05, dz(i + 1), icon.getMinU() + du2(i) * wu, icon.getMaxV() - wu * 0.05);
            t.addVertexWithUV(dx(i), 0.05, dz(i), icon.getMinU() + du(i) * wu, icon.getMaxV() - wu * 0.05);
            t.addVertexWithUV(dx(i), 0.0, dz(i), icon.getMinU() + du(i) * wu, icon.getMaxV());
            t.addVertexWithUV(dx(i + 1), 0.95, dz(i + 1), icon.getMinU() + du2(i) * wu, icon.getMinV() + wu * 0.05);
            t.addVertexWithUV(dx(i + 1), 1.0, dz(i + 1), icon.getMinU() + du2(i) * wu, icon.getMinV());
            t.addVertexWithUV(dx(i), 1.0, dz(i), icon.getMinU() + du(i) * wu, icon.getMinV());
            t.addVertexWithUV(dx(i), 0.95, dz(i), icon.getMinU() + du(i) * wu, icon.getMinV() + wu * 0.05);
            RenderBlockDrum.w = RenderBlockDrum.base_w * h * 0.9;
            t.addVertexWithUV(1.0 - dx(i), 0.95, 1.0 - dz(i), icon.getMinU() + du(i) * wu, icon.getMaxV());
            t.addVertexWithUV(1.0 - dx(i), 1.0, 1.0 - dz(i), icon.getMinU() + du(i) * wu, icon.getMaxV() - wu * 0.05);
            t.addVertexWithUV(1.0 - dx(i + 1), 1.0, 1.0 - dz(i + 1), icon.getMinU() + du2(i) * wu, icon.getMaxV() - wu * 0.05);
            t.addVertexWithUV(1.0 - dx(i + 1), 0.95, 1.0 - dz(i + 1), icon.getMinU() + du2(i) * wu, icon.getMaxV());
            t.addVertexWithUV(1.0 - dx(i), 0.0, 1.0 - dz(i), icon.getMinU() + du(i) * wu, icon.getMinV());
            t.addVertexWithUV(1.0 - dx(i), 0.05, 1.0 - dz(i), icon.getMinU() + du(i) * wu, icon.getMinV() + wu * 0.05);
            t.addVertexWithUV(1.0 - dx(i + 1), 0.05, 1.0 - dz(i + 1), icon.getMinU() + du2(i) * wu, icon.getMinV() + wu * 0.05);
            t.addVertexWithUV(1.0 - dx(i + 1), 0.0, 1.0 - dz(i + 1), icon.getMinU() + du2(i) * wu, icon.getMinV());
            t.draw();
        }
        RenderBlockDrum.w = RenderBlockDrum.base_w;
        icon = block.getIcon(1, meta);
        wu = icon.getMaxU() - icon.getMinU();
        wv = icon.getMaxV() - icon.getMinV();
        for (int i = 0; i < 8; ++i) {
            t.startDrawingQuads();
            t.setColorOpaque_F(0.8f, 0.8f, 0.8f);
            t.setNormal(0.0f, 1.0f, 0.0f);
            RenderBlockDrum.w = RenderBlockDrum.base_w;
            t.addVertexWithUV(dx(i), 1.0, dz(i), icon.getMinU() + dx(i) * wu, icon.getMaxV() - dz(i) * wv);
            t.addVertexWithUV(dx(i + 1), 1.0, dz(i + 1), icon.getMinU() + dx(i + 1) * wu, icon.getMaxV() - dz(i + 1) * wv);
            RenderBlockDrum.w = RenderBlockDrum.base_w * h * 0.9;
            t.addVertexWithUV(dx(i + 1), 1.0, dz(i + 1), icon.getMinU() + dx(i + 1) * wu, icon.getMaxV() - dz(i + 1) * wv);
            t.addVertexWithUV(dx(i), 1.0, dz(i), icon.getMinU() + dx(i) * wu, icon.getMaxV() - dz(i) * wv);
            t.draw();
            t.startDrawingQuads();
            t.setColorOpaque_F(1.0f, 1.0f, 1.0f);
            t.setNormal(0.0f, 1.0f, 0.0f);
            RenderBlockDrum.w = RenderBlockDrum.base_w * h * 0.9;
            t.addVertexWithUV(dx(i), h, dz(i), icon.getMinU() + dx(i) * wu, icon.getMaxV() - dz(i) * wv);
            t.addVertexWithUV(dx(i + 1), h, dz(i + 1), icon.getMinU() + dx(i + 1) * wu, icon.getMaxV() - dz(i + 1) * wv);
            RenderBlockDrum.w = 0.0;
            t.addVertexWithUV(dx(i + 1), h, dz(i + 1), icon.getMinU() + dx(i + 1) * wu, icon.getMaxV() - dz(i + 1) * wv);
            t.addVertexWithUV(dx(i), h, dz(i), icon.getMinU() + dx(i) * wu, icon.getMaxV() - dz(i) * wv);
            t.draw();
        }
        icon = block.getIcon(0, meta);
        wu = icon.getMaxU() - icon.getMinU();
        wv = icon.getMaxV() - icon.getMinV();
        for (int i = 0; i < 8; ++i) {
            t.startDrawingQuads();
            t.setColorOpaque_F(0.8f, 0.8f, 0.8f);
            t.setNormal(0.0f, -1.0f, 0.0f);
            RenderBlockDrum.w = RenderBlockDrum.base_w * h * 0.9;
            t.addVertexWithUV(dx(i), 0.0, dz(i), icon.getMinU() + dx(i) * wu, icon.getMaxV() - dz(i) * wv);
            t.addVertexWithUV(dx(i + 1), 0.0, dz(i + 1), icon.getMinU() + dx(i + 1) * wu, icon.getMaxV() - dz(i + 1) * wv);
            RenderBlockDrum.w = RenderBlockDrum.base_w;
            t.addVertexWithUV(dx(i + 1), 0.0, dz(i + 1), icon.getMinU() + dx(i + 1) * wu, icon.getMaxV() - dz(i + 1) * wv);
            t.addVertexWithUV(dx(i), 0.0, dz(i), icon.getMinU() + dx(i) * wu, icon.getMaxV() - dz(i) * wv);
            t.draw();
            t.startDrawingQuads();
            t.setColorOpaque_F(1.0f, 1.0f, 1.0f);
            t.setNormal(0.0f, 1.0f, 0.0f);
            RenderBlockDrum.w = 0.0;
            t.addVertexWithUV(dx(i), 1.0f - h, dz(i), icon.getMinU() + dx(i) * wu, icon.getMaxV() - dz(i) * wv);
            t.addVertexWithUV(dx(i + 1), 1.0f - h, dz(i + 1), icon.getMinU() + dx(i + 1) * wu, icon.getMaxV() - dz(i + 1) * wv);
            RenderBlockDrum.w = RenderBlockDrum.base_w * h * 0.9;
            t.addVertexWithUV(dx(i + 1), 1.0f - h, dz(i + 1), icon.getMinU() + dx(i + 1) * wu, icon.getMaxV() - dz(i + 1) * wv);
            t.addVertexWithUV(dx(i), 1.0f - h, dz(i), icon.getMinU() + dx(i) * wu, icon.getMaxV() - dz(i) * wv);
            t.draw();
        }
        GL11.glTranslatef(0.0f, 0.5f, 0.0f);
    }

    public void renderInventoryBlock(final Block block, final int metadata, final int modelID, final RenderBlocks renderer) {
    }

    public boolean renderWorldBlock(final IBlockAccess world, final int x, final int y, final int z, final Block block, final int modelId, final RenderBlocks renderer) {
        final int brightness = block.getMixedBrightnessForBlock(world, x, y, z);
        final int meta = world.getBlockMetadata(x, y, z);
        final float h = 0.97f;
        final float d = 0.2f;
        final float h2 = 0.3125f;
        final int l = block.colorMultiplier(world, x, y, z);
        final float f = (l >> 16 & 0xFF) / 255.0f;
        final float f2 = (l >> 8 & 0xFF) / 255.0f;
        final float f3 = (l & 0xFF) / 255.0f;
        final Tessellator t = Tessellator.instance;
        t.setBrightness(brightness);
        t.setColorOpaque_F(1.0f, 1.0f, 1.0f);
        t.setColorRGBA_F(1.0f, 1.0f, 1.0f, 1.0f);
        IIcon icon = block.getIcon(2, meta);
        if (renderer.overrideBlockTexture != null) {
            icon = renderer.overrideBlockTexture;
        }
        float wu = icon.getMaxU() - icon.getMinU();
        float wv = icon.getMaxV() - icon.getMinV();
        final float ddv = wv * 0.3125f;
        for (int i = 0; i < 8; ++i) {
            RenderBlockDrum.w = RenderBlockDrum.base_w * h;
            this.setB(i + 1 - d, 1.0f, f, f2, f3);
            t.addVertexWithUV(x + dx(i + 1), y, z + dz(i + 1), icon.getMinU() + du2(i) * wu, icon.getMaxV());
            t.addVertexWithUV(x + dx(i + 1), y + h2, z + dz(i + 1), icon.getMinU() + du2(i) * wu, icon.getMaxV() - ddv);
            this.setB(i + d, 0.9f, f, f2, f3);
            t.addVertexWithUV(x + dx(i), y + h2, z + dz(i), icon.getMinU() + du(i) * wu, icon.getMaxV() - ddv);
            t.addVertexWithUV(x + dx(i), y, z + dz(i), icon.getMinU() + du(i) * wu, icon.getMaxV());
            this.setB(i + 1 - d, 1.0f, f, f2, f3);
            t.addVertexWithUV(x + dx(i + 1), y + h2 * 2.0f, z + dz(i + 1), icon.getMinU() + du2(i) * wu, icon.getMinV() + ddv);
            t.addVertexWithUV(x + dx(i + 1), y + 1, z + dz(i + 1), icon.getMinU() + du2(i) * wu, icon.getMinV());
            this.setB(i + d, 0.9f, f, f2, f3);
            t.addVertexWithUV(x + dx(i), y + 1, z + dz(i), icon.getMinU() + du(i) * wu, icon.getMinV());
            t.addVertexWithUV(x + dx(i), y + h2 * 2.0f, z + dz(i), icon.getMinU() + du(i) * wu, icon.getMinV() + ddv);
            this.setB(i + 1 - d, 1.0f);
            t.addVertexWithUV(x + dx(i + 1), y + h2, z + dz(i + 1), icon.getMinU() + du2(i) * wu, icon.getMaxV() - ddv);
            t.addVertexWithUV(x + dx(i + 1), y + h2 * 2.0f, z + dz(i + 1), icon.getMinU() + du2(i) * wu, icon.getMinV() + ddv);
            this.setB(i + d, 1.0f);
            t.addVertexWithUV(x + dx(i), y + h2 * 2.0f, z + dz(i), icon.getMinU() + du(i) * wu, icon.getMinV() + ddv);
            t.addVertexWithUV(x + dx(i), y + h2, z + dz(i), icon.getMinU() + du(i) * wu, icon.getMaxV() - ddv);
            t.setColorOpaque_F(0.65f, 0.65f, 0.65f);
            RenderBlockDrum.w = RenderBlockDrum.base_w;
            this.setB(i + 1 - d, 0.6f);
            t.addVertexWithUV(x + dx(i + 1), y, z + dz(i + 1), icon.getMinU() + du2(i) * wu, icon.getMaxV());
            t.addVertexWithUV(x + dx(i + 1), y + 0.05, z + dz(i + 1), icon.getMinU() + du2(i) * wu, icon.getMaxV() - wu * 0.05);
            this.setB(i + d, 0.6f);
            t.addVertexWithUV(x + dx(i), y + 0.05, z + dz(i), icon.getMinU() + du(i) * wu, icon.getMaxV() - wu * 0.05);
            t.addVertexWithUV(x + dx(i), y, z + dz(i), icon.getMinU() + du(i) * wu, icon.getMaxV());
            this.setB(i + 1 - d, 0.6f);
            t.addVertexWithUV(x + dx(i + 1), y + 0.95, z + dz(i + 1), icon.getMinU() + du2(i) * wu, icon.getMinV() + wu * 0.05);
            t.addVertexWithUV(x + dx(i + 1), y + 1, z + dz(i + 1), icon.getMinU() + du2(i) * wu, icon.getMinV());
            this.setB(i + d, 0.6f);
            t.addVertexWithUV(x + dx(i), y + 1, z + dz(i), icon.getMinU() + du(i) * wu, icon.getMinV());
            t.addVertexWithUV(x + dx(i), y + 0.95, z + dz(i), icon.getMinU() + du(i) * wu, icon.getMinV() + wu * 0.05);
            RenderBlockDrum.w = RenderBlockDrum.base_w * h * 0.9;
            this.setB(i + d, 0.6f);
            t.addVertexWithUV(x + 1 - dx(i), y + 0.95, z + 1 - dz(i), icon.getMinU() + du(i) * wu, icon.getMaxV());
            t.addVertexWithUV(x + 1 - dx(i), y + 1, z + 1 - dz(i), icon.getMinU() + du(i) * wu, icon.getMaxV() - wu * 0.05);
            this.setB(i + 1 - d, 0.6f);
            t.addVertexWithUV(x + 1 - dx(i + 1), y + 1, z + 1 - dz(i + 1), icon.getMinU() + du2(i) * wu, icon.getMaxV() - wu * 0.05);
            t.addVertexWithUV(x + 1 - dx(i + 1), y + 0.95, z + 1 - dz(i + 1), icon.getMinU() + du2(i) * wu, icon.getMaxV());
            this.setB(i + d, 0.6f);
            t.addVertexWithUV(x + 1 - dx(i), y, z + 1 - dz(i), icon.getMinU() + du(i) * wu, icon.getMinV());
            t.addVertexWithUV(x + 1 - dx(i), y + 0.05, z + 1 - dz(i), icon.getMinU() + du(i) * wu, icon.getMinV() + wu * 0.05);
            this.setB(i + 1 - d, 0.6f);
            t.addVertexWithUV(x + 1 - dx(i + 1), y + 0.05, z + 1 - dz(i + 1), icon.getMinU() + du2(i) * wu, icon.getMinV() + wu * 0.05);
            t.addVertexWithUV(x + 1 - dx(i + 1), y, z + 1 - dz(i + 1), icon.getMinU() + du2(i) * wu, icon.getMinV());
        }
        RenderBlockDrum.w = RenderBlockDrum.base_w;
        icon = block.getIcon(1, meta);
        if (renderer.overrideBlockTexture != null) {
            icon = renderer.overrideBlockTexture;
        }
        wu = icon.getMaxU() - icon.getMinU();
        wv = icon.getMaxV() - icon.getMinV();
        for (int i = 0; i < 8; ++i) {
            t.setColorOpaque_F(0.8f, 0.8f, 0.8f);
            RenderBlockDrum.w = RenderBlockDrum.base_w;
            t.addVertexWithUV(x + dx(i), y + 1, z + dz(i), icon.getMinU() + dx(i) * wu, icon.getMaxV() - dz(i) * wv);
            t.addVertexWithUV(x + dx(i + 1), y + 1, z + dz(i + 1), icon.getMinU() + dx(i + 1) * wu, icon.getMaxV() - dz(i + 1) * wv);
            RenderBlockDrum.w = RenderBlockDrum.base_w * h * 0.9;
            t.addVertexWithUV(x + dx(i + 1), y + 1, z + dz(i + 1), icon.getMinU() + dx(i + 1) * wu, icon.getMaxV() - dz(i + 1) * wv);
            t.addVertexWithUV(x + dx(i), y + 1, z + dz(i), icon.getMinU() + dx(i) * wu, icon.getMaxV() - dz(i) * wv);
            t.setColorOpaque_F(1.0f, 1.0f, 1.0f);
            RenderBlockDrum.w = RenderBlockDrum.base_w * h * 0.9;
            t.addVertexWithUV(x + dx(i), y + h, z + dz(i), icon.getMinU() + dx(i) * wu, icon.getMaxV() - dz(i) * wv);
            t.addVertexWithUV(x + dx(i + 1), y + h, z + dz(i + 1), icon.getMinU() + dx(i + 1) * wu, icon.getMaxV() - dz(i + 1) * wv);
            RenderBlockDrum.w = 0.0;
            t.addVertexWithUV(x + dx(i + 1), y + h, z + dz(i + 1), icon.getMinU() + dx(i + 1) * wu, icon.getMaxV() - dz(i + 1) * wv);
            t.addVertexWithUV(x + dx(i), y + h, z + dz(i), icon.getMinU() + dx(i) * wu, icon.getMaxV() - dz(i) * wv);
        }
        t.setColorOpaque_F(0.5f, 0.5f, 0.5f);
        icon = block.getIcon(0, meta);
        if (renderer.overrideBlockTexture != null) {
            icon = renderer.overrideBlockTexture;
        }
        wu = icon.getMaxU() - icon.getMinU();
        wv = icon.getMaxV() - icon.getMinV();
        for (int i = 0; i < 8; ++i) {
            t.setColorOpaque_F(0.5f, 0.5f, 0.5f);
            RenderBlockDrum.w = RenderBlockDrum.base_w * h * 0.9;
            t.addVertexWithUV(x + dx(i), y, z + dz(i), icon.getMinU() + dx(i) * wu, icon.getMaxV() - dz(i) * wv);
            t.addVertexWithUV(x + dx(i + 1), y, z + dz(i + 1), icon.getMinU() + dx(i + 1) * wu, icon.getMaxV() - dz(i + 1) * wv);
            RenderBlockDrum.w = RenderBlockDrum.base_w;
            t.addVertexWithUV(x + dx(i + 1), y, z + dz(i + 1), icon.getMinU() + dx(i + 1) * wu, icon.getMaxV() - dz(i + 1) * wv);
            t.addVertexWithUV(x + dx(i), y, z + dz(i), icon.getMinU() + dx(i) * wu, icon.getMaxV() - dz(i) * wv);
            t.setColorOpaque_F(0.6f, 0.6f, 0.6f);
            RenderBlockDrum.w = 0.0;
            t.addVertexWithUV(x + dx(i), y + 1 - h, z + dz(i), icon.getMinU() + dx(i) * wu, icon.getMaxV() - dz(i) * wv);
            t.addVertexWithUV(x + dx(i + 1), y + 1 - h, z + dz(i + 1), icon.getMinU() + dx(i + 1) * wu, icon.getMaxV() - dz(i + 1) * wv);
            RenderBlockDrum.w = RenderBlockDrum.base_w * h * 0.9;
            t.addVertexWithUV(x + dx(i + 1), y + 1 - h, z + dz(i + 1), icon.getMinU() + dx(i + 1) * wu, icon.getMaxV() - dz(i + 1) * wv);
            t.addVertexWithUV(x + dx(i), y + 1 - h, z + dz(i), icon.getMinU() + dx(i) * wu, icon.getMaxV() - dz(i) * wv);
        }
        return false;
    }

    public void setB(final float i, final float p, final float r, final float g, final float b) {
        final float brightness = (float) (p * (0.7 + Math.cos(((i + 0.5) / 4.0 * 2.0 + 1.0) * 3.141592653589793) * 0.1));
        Tessellator.instance.setColorOpaque_F(brightness * r, brightness * g, brightness * b);
    }

    public void setB(final float i, final float p) {
        this.setB(i, p, 1.0f, 1.0f, 1.0f);
    }

    public boolean shouldRender3DInInventory(final int modelId) {
        return false;
    }

    public int getRenderId() {
        return ExtraUtilsProxy.drumRendererID;
    }
}


