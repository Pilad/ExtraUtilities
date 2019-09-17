// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.core;

import com.google.common.base.Throwables;
import com.rwtema.extrautils.ExtraUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Objects;

public class NSafe {
    static HashMap<Tuple<Class<?>, String>, Field> cache;

    static {
        NSafe.cache = new HashMap<Tuple<Class<?>, String>, Field>();
    }

    public static Field getField(final Class<?> clazz, final String fieldName) {
        final Tuple<Class<?>, String> key = new Tuple<Class<?>, String>(clazz, fieldName);
        Field val = NSafe.cache.get(key);
        if (val == null) {
            try {
                final Field f = clazz.getDeclaredField(fieldName);
                f.setAccessible(true);
                val = f;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            NSafe.cache.put(key, val);
        }
        return val;
    }

    public static <K> K get(final Object object, final String fieldName) {
        if (object == null) {
            return null;
        }
        final Field field = getField(object.getClass(), fieldName);
        if (field == null) {
            return null;
        }
        K result = null;
        try {
            result = (K) field.get(object);
        } catch (IllegalAccessException e) {
            throw Throwables.propagate(e);
        }
        return result;
    }

    public static <T> T set(final T object, final String value, final Objects... param) {
        final String s = get(ExtraUtils.wateringCan, "iconString");
        return object;
    }
}


