// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.crafting;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.util.ArrayList;

public class RecipeCustomOres extends ShapedOreRecipe {
    public RecipeCustomOres(final ItemStack result, final ItemStack toReplace, final ArrayList<ItemStack> customOres, final Object... recipe) {
        super(result, recipe);
        this.replace(toReplace, customOres);
    }

    public static ShapedOreRecipe replace(final ShapedOreRecipe recipe, final ItemStack toReplace, final ArrayList<ItemStack> customOres) {
        final Object[] input = recipe.getInput();
        for (int i = 0; i < input.length; ++i) {
            if (input[i] instanceof ItemStack && toReplace.isItemEqual((ItemStack) input[i])) {
                input[i] = customOres;
            }
        }
        return recipe;
    }

    public RecipeCustomOres replace(final ItemStack toReplace, final ArrayList<ItemStack> customOres) {
        return (RecipeCustomOres) replace(this, toReplace, customOres);
    }
}


