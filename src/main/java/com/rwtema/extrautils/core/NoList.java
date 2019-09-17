// 
// ExtraUtilities decompiled and fixed by Robotia https://github.com/Robotia
// 

package com.rwtema.extrautils.core;

import java.util.AbstractList;
import java.util.List;

public class NoList<E> extends AbstractList<E> implements List<E> {
    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean contains(final Object o) {
        return false;
    }

    @Override
    public boolean add(final E e) {
        return false;
    }

    @Override
    public boolean remove(final Object o) {
        return false;
    }

    @Override
    public void clear() {
    }

    @Override
    public E get(final int index) {
        return null;
    }

    @Override
    public E set(final int index, final E element) {
        return null;
    }

    @Override
    public void add(final int index, final E element) {
    }

    @Override
    public E remove(final int index) {
        return null;
    }
}


