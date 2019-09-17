// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.nei;

import codechicken.nei.recipe.DefaultOverlayHandler;
import codechicken.nei.recipe.RecipeInfo;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiCrafting;

public class NEIHelper {
    public static boolean isCraftingGUI(final GuiContainer gui) {
        return gui.getClass() == GuiCrafting.class || (RecipeInfo.hasOverlayHandler(gui, "crafting") && RecipeInfo.getOverlayHandler(gui, "crafting").getClass() == DefaultOverlayHandler.class);
    }
}


