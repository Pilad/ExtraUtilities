// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// Very Pretty Blocks m9
// 

package cofh.api.block;

import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public interface IBlockAppearance {
    Block getVisualBlock(final IBlockAccess p0, final int p1, final int p2, final int p3, final ForgeDirection p4);

    int getVisualMeta(final IBlockAccess p0, final int p1, final int p2, final int p3, final ForgeDirection p4);

    boolean supportsVisualConnections();
}


