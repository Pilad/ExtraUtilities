// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.tileentity.enderconstructor;

import com.rwtema.extrautils.dynamicgui.DynamicContainer;
import com.rwtema.extrautils.dynamicgui.DynamicGui;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class DynamicGuiEnderConstructor extends DynamicGui {
    private static final ResourceLocation texBackground;
    private static final ResourceLocation texWidgets;

    static {
        texBackground = new ResourceLocation("extrautils", "textures/guiBase2.png");
        texWidgets = new ResourceLocation("extrautils", "textures/guiWidget2.png");
    }

    public DynamicGuiEnderConstructor(final DynamicContainer container) {
        super(container);
    }

    @Override
    public ResourceLocation getBackground() {
        return DynamicGuiEnderConstructor.texBackground;
    }

    @Override
    public ResourceLocation getWidgets() {
        return DynamicGuiEnderConstructor.texWidgets;
    }
}


