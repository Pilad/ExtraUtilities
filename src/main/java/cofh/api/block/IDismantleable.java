// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package cofh.api.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;

public interface IDismantleable {
    ArrayList<ItemStack> dismantleBlock(final EntityPlayer p0, final World p1, final int p2, final int p3, final int p4, final boolean p5);

    boolean canDismantle(final EntityPlayer p0, final World p1, final int p2, final int p3, final int p4);
}


