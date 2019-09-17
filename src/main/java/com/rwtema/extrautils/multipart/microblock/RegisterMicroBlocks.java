// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.multipart.microblock;

import codechicken.multipart.MultiPartRegistry;
import codechicken.multipart.TMultiPart;

import java.util.HashMap;
import java.util.Map;

public class RegisterMicroBlocks implements MultiPartRegistry.IPartFactory {
    public static RegisterMicroBlocks instance;
    public static Map<Integer, IMicroBlock> mParts;
    public static Map<String, Integer> mIds;

    static {
        RegisterMicroBlocks.instance = new RegisterMicroBlocks();
        RegisterMicroBlocks.mParts = new HashMap<Integer, IMicroBlock>();
        RegisterMicroBlocks.mIds = new HashMap<String, Integer>();
        register(new PartPipeJacket());
        register(new PartFence());
        register(new PartWall());
        register(new PartSphere());
    }

    public static void register(final IMicroBlock block) {
        RegisterMicroBlocks.mParts.put(block.getMetadata(), block);
        RegisterMicroBlocks.mIds.put(block.getType(), block.getMetadata());
    }

    public static void register() {
        final String[] s = new String[RegisterMicroBlocks.mParts.size()];
        for (int i = 0; i < RegisterMicroBlocks.mParts.size(); ++i) {
            s[i] = RegisterMicroBlocks.mParts.get(i).getType();
            RegisterMicroBlocks.mParts.get(i).registerPassThroughs();
        }
        MultiPartRegistry.registerParts(RegisterMicroBlocks.instance, s);
    }

    public TMultiPart createPart(final String arg0, final boolean arg1) {
        return RegisterMicroBlocks.mParts.get(RegisterMicroBlocks.mIds.get(arg0)).newPart(arg1);
    }
}


