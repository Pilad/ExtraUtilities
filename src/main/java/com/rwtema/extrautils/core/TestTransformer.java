// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.core;

import com.rwtema.extrautils.LogHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;

public class TestTransformer {
    static {
        performTest();
        FMLCommonHandler.instance().exitJava(0, true);
    }

    public static void performTest() {
        for (final String s : FMLDeobfuscatingRemapper.INSTANCE.getObfedClasses()) {
            LogHelper.info(s + FMLDeobfuscatingRemapper.INSTANCE.map(s));
        }
    }
}


