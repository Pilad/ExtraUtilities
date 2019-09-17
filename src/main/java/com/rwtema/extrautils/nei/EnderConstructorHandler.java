// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.nei;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.recipe.ShapedRecipeHandler;
import codechicken.nei.recipe.TemplateRecipeHandler;
import com.rwtema.extrautils.tileentity.enderconstructor.DynamicGuiEnderConstructor;
import com.rwtema.extrautils.tileentity.enderconstructor.EnderConstructorRecipesHandler;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.ShapedOreRecipe;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class EnderConstructorHandler extends ShapedRecipeHandler {
    private static final ResourceLocation texWidgets;

    static {
        texWidgets = new ResourceLocation("extrautils", "textures/guiQED_NEI.png");
    }

    public Class<? extends GuiContainer> getGuiClass() {
        return DynamicGuiEnderConstructor.class;
    }

    public void drawBackground(final int recipe) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GuiDraw.changeTexture(EnderConstructorHandler.texWidgets);
        GuiDraw.drawTexturedModalRect(0, 0, 5, 11, 166, 65);
    }

    public String getOverlayIdentifier() {
        return "qedcrafting";
    }

    public void drawForeground(final int recipe) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glDisable(2896);
        GuiDraw.changeTexture(EnderConstructorHandler.texWidgets);
        this.drawExtras(recipe);
    }

    public void loadTransferRects() {
        this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(84, 23, 24, 18), this.getOverlayIdentifier()));
    }

    public String getRecipeName() {
        return "QED Recipes";
    }

    public void drawExtras(final int recipe) {
        this.drawProgressBar(85, 24, 176, 0, 22, 15, 48, 0);
    }

    public void loadUsageRecipes(final ItemStack ingredient) {
        for (final IRecipe irecipe : EnderConstructorRecipesHandler.recipes) {
            ShapedRecipeHandler.CachedShapedRecipe recipe = null;
            if (irecipe instanceof ShapedRecipes) {
                recipe = new ShapedRecipeHandler.CachedShapedRecipe((ShapedRecipes) irecipe);
            } else if (irecipe instanceof ShapedOreRecipe) {
                recipe = this.forgeShapedRecipe((ShapedOreRecipe) irecipe);
            }
            if (recipe != null) {
                if (!recipe.contains(recipe.ingredients, ingredient.getItem())) {
                    continue;
                }
                recipe.computeVisuals();
                if (!recipe.contains(recipe.ingredients, ingredient)) {
                    continue;
                }
                recipe.setIngredientPermutation(recipe.ingredients, ingredient);
                this.arecipes.add(recipe);
            }
        }
    }

    public void loadCraftingRecipes(final String outputId, final Object... results) {
        if (outputId.equals(this.getOverlayIdentifier())) {
            for (final IRecipe irecipe : EnderConstructorRecipesHandler.recipes) {
                ShapedRecipeHandler.CachedShapedRecipe recipe = null;
                if (irecipe instanceof ShapedRecipes) {
                    recipe = new ShapedRecipeHandler.CachedShapedRecipe((ShapedRecipes) irecipe);
                } else if (irecipe instanceof ShapedOreRecipe) {
                    recipe = this.forgeShapedRecipe((ShapedOreRecipe) irecipe);
                }
                if (recipe == null) {
                    continue;
                }
                recipe.computeVisuals();
                this.arecipes.add(recipe);
            }
        } else if (outputId.equals("item")) {
            this.loadCraftingRecipes((ItemStack) results[0]);
        }
    }

    public void loadCraftingRecipes(final ItemStack result) {
        for (final IRecipe irecipe : EnderConstructorRecipesHandler.recipes) {
            if (NEIServerUtils.areStacksSameTypeCrafting(irecipe.getRecipeOutput(), result)) {
                ShapedRecipeHandler.CachedShapedRecipe recipe = null;
                if (irecipe instanceof ShapedRecipes) {
                    recipe = new ShapedRecipeHandler.CachedShapedRecipe((ShapedRecipes) irecipe);
                } else if (irecipe instanceof ShapedOreRecipe) {
                    recipe = this.forgeShapedRecipe((ShapedOreRecipe) irecipe);
                }
                if (recipe == null) {
                    continue;
                }
                recipe.computeVisuals();
                this.arecipes.add(recipe);
            }
        }
    }
}


