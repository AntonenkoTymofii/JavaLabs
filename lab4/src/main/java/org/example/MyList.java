package org.example;

import java.util.Collection;

public interface MyList<E> {

    void add(E e);

    void add(int index, E element);

    void addAll(E[] c);

    void addAll(int index, E[] c);

    boolean addAll(Collection<? extends E> c);

    E get(int index);

    E remove(int index);

    void set(int index, E element);

    int indexOf(Object o);

    int size();

    Object[] toArray();
}