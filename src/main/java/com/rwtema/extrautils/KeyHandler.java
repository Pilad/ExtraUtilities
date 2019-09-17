// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils;

import com.google.common.base.Throwables;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.IntHashMap;

import java.lang.reflect.Field;

public class KeyHandler {
    public static final String CTRL_DESCRIPTION = "key.xu.special";
    public static final int CTRL_CODE = 29;
    public static final String CTRL_CATEGORY = "key.categories.gameplay";
    public static KeyHandler INSTANCE;
    public static KeyBinding CTRL;
    private static IntHashMap hash;

    static {
        KeyHandler.INSTANCE = new KeyHandler();
        for (final Field field : KeyBinding.class.getDeclaredFields()) {
            if (field.getType() == IntHashMap.class) {
                field.setAccessible(true);
                try {
                    KeyHandler.hash = (IntHashMap) field.get(null);
                } catch (IllegalAccessException e) {
                    throw Throwables.propagate(e);
                }
            }
        }
    }

    public static boolean getIsKeyPressed(final KeyBinding key) {
        final KeyBinding lookup = (KeyBinding) KeyHandler.hash.lookup(key.getKeyCode());
        return lookup != null && lookup.getIsKeyPressed();
    }

    public void register() {
    }
}


