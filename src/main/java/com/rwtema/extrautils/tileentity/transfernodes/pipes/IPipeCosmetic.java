// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.tileentity.transfernodes.pipes;

import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;

public interface IPipeCosmetic {
    IIcon baseTexture();

    IIcon pipeTexture(final ForgeDirection p0, final boolean p1);

    IIcon invPipeTexture(final ForgeDirection p0);

    IIcon socketTexture(final ForgeDirection p0);

    float baseSize();
}


