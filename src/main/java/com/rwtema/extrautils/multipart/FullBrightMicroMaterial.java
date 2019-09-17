// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.multipart;

import codechicken.lib.render.CCRenderPipeline;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.ColourMultiplier;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Vector3;
import codechicken.microblock.BlockMicroMaterial;
import com.rwtema.extrautils.block.BlockGreenScreen;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FullBrightMicroMaterial extends BlockMicroMaterial {
    public FullBrightMicroMaterial(final BlockGreenScreen block, final int meta) {
        super(block, meta);
    }

    @SideOnly(Side.CLIENT)
    public int getColour(final int pass) {
        return this.block().getRenderColor(this.meta()) << 8 | 0xFF;
    }

    public int getLightValue() {
        return BlockGreenScreen.getLightLevel(this.meta());
    }

    @SideOnly(Side.CLIENT)
    public void renderMicroFace(final Vector3 pos, final int pass, final Cuboid6 bounds) {
        final CCRenderPipeline.PipelineBuilder builder = CCRenderState.pipeline.builder();
        builder.add(pos.translation()).add(this.icont());
        builder.add(ColourMultiplier.instance(this.getColour(pass)));
        builder.add(Lighting.instance);
        builder.render();
    }

    public static class Lighting implements CCRenderState.IVertexOperation {
        public static Lighting instance;
        public static int id;

        static {
            Lighting.instance = new Lighting();
            Lighting.id = CCRenderState.registerOperation();
        }

        public boolean load() {
            if (!CCRenderState.computeLighting) {
                return false;
            }
            CCRenderState.pipeline.addDependency(CCRenderState.colourAttrib);
            CCRenderState.pipeline.addDependency(CCRenderState.lightCoordAttrib);
            return true;
        }

        public void operate() {
            CCRenderState.setBrightness(16711935);
        }

        public int operationID() {
            return Lighting.id;
        }
    }
}


