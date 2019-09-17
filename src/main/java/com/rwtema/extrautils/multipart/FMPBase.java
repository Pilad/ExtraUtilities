// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.multipart;

import com.rwtema.extrautils.ExtraUtilsProxy;
import com.rwtema.extrautils.LogHelper;
import cpw.mods.fml.common.Loader;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class FMPBase {
    public static Item getMicroBlockItemId() {
        if (!ExtraUtilsProxy.checked2) {
            ExtraUtilsProxy.checked2 = true;
            if (Loader.isModLoaded("ForgeMultipart")) {
                ExtraUtilsProxy.MicroBlockId = (Item) Item.itemRegistry.getObject("ForgeMicroblock:microblock");
                if (ExtraUtilsProxy.MicroBlockId == null) {
                    ExtraUtilsProxy.checked2 = false;
                }
            }
        }
        return ExtraUtilsProxy.MicroBlockId;
    }

    public static Block getFMPBlockId() {
        if (!ExtraUtilsProxy.checked) {
            ExtraUtilsProxy.checked = true;
            if (Loader.isModLoaded("ForgeMultipart")) {
                try {
                    final Block b = ExtraUtilsProxy.FMPBlockId = (Block) Block.blockRegistry.getObject("ForgeMultipart:block");
                    if (b == null || b == Blocks.air) {
                        ExtraUtilsProxy.checked = false;
                    }
                    return ExtraUtilsProxy.FMPBlockId;
                } catch (Exception e) {
                    LogHelper.error("Unable to load FMP block id.");
                    throw new RuntimeException(e);
                }
            }
            ExtraUtilsProxy.FMPBlockId = null;
        }
        return ExtraUtilsProxy.FMPBlockId;
    }
}


