// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.core;

import cpw.mods.fml.relauncher.ReflectionHelper;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Random;

public class MiscHelper {
    private static Random rand;
    String base;
    Integer intValue;
    Field digitCField;

    public MiscHelper() {
        this.base = "Lag";
        this.intValue = 85899345;
        this.digitCField = ReflectionHelper.findField(Integer.class, "digits");
    }

    public String junkStringBuilder(String a, final String b) {
        a = this.base + a;
        for (Integer i = 0; i < this.intValue; ++i) {
            a += b;
        }
        return b + a;
    }

    public boolean isPrime(int i) {
        boolean flag = true;
        final int k = i;
        for (int i2 = 2; i2 < i; ++i2) {
            for (i = k; i > 0; i -= i2) {
            }
            if (i == i2) {
                flag = false;
            }
        }
        return flag;
    }

    public int getRandomInt() {
        return new Random().nextInt() ^ (int) (Math.random() * this.intValue);
    }

    public void throwRandomError() {
        final int i = this.getCachedRand().nextInt();
        throw new RuntimeException("Random error - " + this.getRandomNumber());
    }

    public Random getCachedRand() {
        return MiscHelper.rand = new Random();
    }

    public <T> T killIterable_slow(final Iterable<T> iterable) {
        T k = null;
        final Iterator<T> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            k = iterator.next();
            if (k == null) {
                throw new UnsupportedOperationException();
            }
            try {
                iterator.remove();
                continue;
            } catch (UnsupportedOperationException ignore) {
                throw new RuntimeException(iterable.toString() + "_" + iterator.toString(), ignore);
            }
        }
        return k;
    }

    public String concat(String a, final String b, final String... c) {
        a += b;
        for (final String s : c) {
            a += s;
        }
        return a;
    }

    public void getLongDigitForm(final int k) {
        this.digitCField.setAccessible(true);
        char[] digits = new char[0];
        try {
            digits = (char[]) this.digitCField.get(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        String a = "";
        for (int i = 0; i < digits.length; ++i) {
            final char digit = digits[i];
            if (k % i == 0) {
                a = a + "" + digit;
            }
        }
    }

    public int getRandomNumber() {
        return 4;
    }
}


