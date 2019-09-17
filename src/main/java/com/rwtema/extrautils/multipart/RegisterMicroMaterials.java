// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.multipart;

import codechicken.microblock.BlockMicroMaterial;
import codechicken.microblock.MicroMaterialRegistry;
import com.rwtema.extrautils.LogHelper;
import com.rwtema.extrautils.block.BlockColor;
import com.rwtema.extrautils.block.BlockGreenScreen;
import net.minecraft.block.Block;

public class RegisterMicroMaterials {
    public static void registerBlock(final Block block) {
        if (block != null) {
            BlockMicroMaterial.createAndRegister(block, 0);
        }
    }

    public static void registerFullBright(final BlockGreenScreen block) {
        if (block != null) {
            for (int m = 0; m < 16; ++m) {
                MicroMaterialRegistry.registerMaterial(new FullBrightMicroMaterial(block, m), block.getUnlocalizedName() + ((m > 0) ? ("_" + m) : ""));
            }
        }
    }

    public static void registerColorBlock(final BlockColor block) {
        if (block != null) {
            for (int m = 0; m < 16; ++m) {
                MicroMaterialRegistry.registerMaterial(new ColoredBlockMicroMaterial(block, m), block.getUnlocalizedName() + ((m > 0) ? ("_" + m) : ""));
            }
        }
    }

    public static void registerConnectedTexture(final Block block, final int m) {
        if (block != null) {
            try {
                MicroMaterialRegistry.registerMaterial(new ConnectedTextureMicroMaterial(block, m), block.getUnlocalizedName() + ((m > 0) ? ("_" + m) : ""));
            } catch (Throwable err) {
                Throwable e;
                for (e = err; e != null; e = e.getCause()) {
                    LogHelper.info("-------");
                    LogHelper.info(e.getMessage());
                    for (final Throwable f : e.getSuppressed()) {
                        LogHelper.info("Found supressed error");
                        f.printStackTrace();
                    }
                    for (final StackTraceElement f2 : e.getStackTrace()) {
                        LogHelper.info(f2.getClassName() + " " + f2.getMethodName() + " " + f2.getFileName() + " " + f2.getLineNumber() + " " + f2.isNativeMethod());
                    }
                }
                throw new RuntimeException(e);
            }
        }
    }

    public static void registerBlock(final Block block, final int m) {
        if (block != null) {
            MicroMaterialRegistry.registerMaterial(new BlockMicroMaterial(block, m), block.getUnlocalizedName() + ((m > 0) ? ("_" + m) : ""));
        }
    }

    public static void registerBlock(final Block block, final int from, final int to) {
        for (int i = from; i < to; ++i) {
            registerBlock(block, i);
        }
    }
}


