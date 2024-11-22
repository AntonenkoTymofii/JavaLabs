package org.example;

import java.util.Arrays;
import java.util.Collection;

public class MyArrayList<E> implements MyList<E> {
    private Object[] elements;
    private int size = 0;

    public MyArrayList() {
        elements = new Object[10];
    }

    @Override
    public void add(E e) {
        ensureCapacity();
        elements[size++] = e;
    }

    @Override
    public void add(int index, E element) {
        ensureCapacity();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    @Override
    public void addAll(E[] c) {
        for (E e : c) {
            add(e);
        }
    }

    @Override
    public void addAll(int index, E[] c) {
        for (E e : c) {
            add(index++, e);
        }
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E e : c) {
            add(e);
        }
        return true;
    }

    @Override
    public E get(int index) {
        return (E) elements[index];
    }

    @Override
    public E remove(int index) {
        E oldValue = (E) elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null;
        return oldValue;
    }

    @Override
    public void set(int index, E element) {
        elements[index] = element;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
    }
}