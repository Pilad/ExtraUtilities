// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package cofh.api.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public interface IToolHammer {
    boolean isUsable(final ItemStack p0, final EntityLivingBase p1, final int p2, final int p3, final int p4);

    void toolUsed(final ItemStack p0, final EntityLivingBase p1, final int p2, final int p3, final int p4);
}


