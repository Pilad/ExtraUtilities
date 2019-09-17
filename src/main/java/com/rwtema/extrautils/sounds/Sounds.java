// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.sounds;

import com.rwtema.extrautils.ExtraUtilsMod;
import com.rwtema.extrautils.tileentity.generators.TileEntityGenerator;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundManager;

import java.lang.reflect.Field;
import java.util.Map;

@SideOnly(Side.CLIENT)
public class Sounds {
    private static Field playingSounds;
    private static Field soundMgr;

    static {
        Sounds.playingSounds = null;
    }

    public static void registerSoundTile(final ISoundTile soundTile) {
        if (ExtraUtilsMod.proxy.isClientSideAvailable()) {
            registerSoundTileDo(soundTile);
        }
    }

    public static void addGenerator(final TileEntityGenerator gen) {
        if (ExtraUtilsMod.proxy.isClientSideAvailable()) {
            XUSoundTileGenerators.addGenerator(gen);
        }
    }

    public static void refresh() {
        if (ExtraUtilsMod.proxy.isClientSideAvailable()) {
            XUSoundTileGenerators.refresh();
        }
    }

    private static void registerSoundTileDo(final ISoundTile soundTile) {
        tryAddSound(new XUSoundTile(soundTile));
    }

    public static void tryAddSound(final ISound sound) {
        if (canAddSound(sound)) {
            Minecraft.getMinecraft().getSoundHandler().playSound(sound);
        }
    }

    public static boolean canAddSound(final ISound sound) {
        if (Sounds.playingSounds == null) {
            Sounds.playingSounds = ReflectionHelper.findField(SoundManager.class, "playingSounds", "field_148629_h");
            Sounds.soundMgr = ReflectionHelper.findField(SoundHandler.class, "sndManager", "field_147694_f");
        }
        try {
            final SoundManager manager = (SoundManager) Sounds.soundMgr.get(Minecraft.getMinecraft().getSoundHandler());
            final Map map = (Map) Sounds.playingSounds.get(manager);
            return !map.containsValue(sound);
        } catch (IllegalAccessException e) {
            return false;
        }
    }
}


