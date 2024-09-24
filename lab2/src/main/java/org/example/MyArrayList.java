package org.example;

import java.util.Arrays;
import java.util.RandomAccess;

public class MyArrayList implements MyList, RandomAccess {
    private Object[] elements;
    private int size;

    public MyArrayList() {
        elements = new Object[10];
        size = 0;
    }

    @Override
    public void add(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    @Override
    public void add(int index, Object element) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        ensureCapacity();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    @Override
    public void addAll(Object[] c) {
        for (Object e : c) {
            add(e);
        }
    }

    @Override
    public void addAll(int index, Object[] c) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        ensureCapacity(c.length);
        System.arraycopy(elements, index, elements, index + c.length, size - index);
        System.arraycopy(c, 0, elements, index, c.length);
        size += c.length;
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        return elements[index];
    }

    @Override
    public Object remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Object oldElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return oldElement;
    }

    @Override
    public void set(int index, Object element) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        elements[index] = element;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) return i;
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

    private void ensureCapacity(int extraCapacity) {
        if (size + extraCapacity > elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2 + extraCapacity);
        }
    }
}
