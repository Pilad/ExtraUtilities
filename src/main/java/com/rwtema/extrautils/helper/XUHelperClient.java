// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.helper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.Locale;

@SideOnly(Side.CLIENT)
public class XUHelperClient {
    public static EntityPlayer clientPlayer() {
        return Minecraft.getMinecraft().thePlayer;
    }

    public static World clientWorld() {
        return Minecraft.getMinecraft().theWorld;
    }

    public static IIcon registerCustomIcon(final String texture, final IIconRegister register, final TextureAtlasSprite sprite) {
        IIcon result = ((TextureMap) register).getTextureExtry(texture);
        if (result == null) {
            result = sprite;
            ((TextureMap) register).setTextureEntry(texture, sprite);
        }
        return result;
    }

    public static String commaDelimited(final int n) {
        return String.format(Locale.ENGLISH, "%,d", n);
    }
}


