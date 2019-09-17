// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package cofh.api.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public interface IBlockConfigGui {
    boolean openConfigGui(final IBlockAccess p0, final int p1, final int p2, final int p3, final ForgeDirection p4, final EntityPlayer p5);
}


