// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.multipart.microblock;

import codechicken.lib.vec.BlockCoord;
import codechicken.lib.vec.Vector3;
import codechicken.microblock.MicroMaterialRegistry;
import codechicken.multipart.TMultiPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IMicroBlock {
    int getMetadata();

    String getType();

    TMultiPart newPart(final boolean p0);

    TMultiPart placePart(final ItemStack p0, final EntityPlayer p1, final World p2, final BlockCoord p3, final int p4, final Vector3 p5, final int p6);

    void registerPassThroughs();

    void renderItem(final ItemStack p0, final MicroMaterialRegistry.IMicroMaterial p1);

    boolean hideCreativeTab();
}


