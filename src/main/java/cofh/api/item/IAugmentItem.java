// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package cofh.api.item;

import net.minecraft.item.ItemStack;

import java.util.Set;

public interface IAugmentItem {
    int getAugmentLevel(final ItemStack p0, final String p1);

    Set<String> getAugmentTypes(final ItemStack p0);
}


