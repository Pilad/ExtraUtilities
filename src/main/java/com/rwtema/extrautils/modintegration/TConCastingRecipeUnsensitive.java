// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.modintegration;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import tconstruct.library.client.FluidRenderProperties;
import tconstruct.library.crafting.CastingRecipe;

public class TConCastingRecipeUnsensitive extends CastingRecipe {
    public TConCastingRecipeUnsensitive(final ItemStack replacement, final FluidStack metal, final ItemStack cast, final boolean consume, final int delay, final FluidRenderProperties props) {
        super(replacement, metal, cast, consume, delay, props);
    }

    public TConCastingRecipeUnsensitive(final CastingRecipe recipe) {
        super(recipe.output.copy(), recipe.castingMetal.copy(), recipe.cast.copy(), recipe.consumeCast, recipe.coolTime, recipe.fluidRenderProperties);
    }

    public boolean matches(final FluidStack metal, final ItemStack inputCast) {
        return this.castingMetal != null && this.castingMetal.isFluidEqual(metal) && inputCast != null && this.cast != null && inputCast.getItem() == this.cast.getItem() && (this.cast.getItemDamage() == 32767 || this.cast.getItemDamage() == inputCast.getItemDamage());
    }
}


