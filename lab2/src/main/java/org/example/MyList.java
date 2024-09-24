package org.example;

public interface MyList {
    void add(Object e);
    void add(int index, Object element);
    void addAll(Object[] c);
    Object get(int index);
    Object remove(int index);
    void set(int index, Object element);
    int indexOf(Object o);
    int size();
    Object[] toArray();
}