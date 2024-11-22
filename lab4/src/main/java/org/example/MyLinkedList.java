package org.example;

import java.util.Collection;

public class MyLinkedList<E> implements MyList<E> {
    private class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }

    private Node<E> head;
    private int size = 0;

    @Override
    public void add(E e) {
        if (head == null) {
            head = new Node<>(e);
        } else {
            Node<E> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node<>(e);
        }
        size++;
    }

    @Override
    public void add(int index, E element) {
        Node<E> newNode = new Node<>(element);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node<E> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
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
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    @Override
    public E remove(int index) {
        if (index == 0) {
            Node<E> oldHead = head;
            head = head.next;
            size--;
            return oldHead.data;
        } else {
            Node<E> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            Node<E> nodeToRemove = current.next;
            current.next = nodeToRemove.next;
            size--;
            return nodeToRemove.data;
        }
    }

    @Override
    public void set(int index, E element) {
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.data = element;
    }

    @Override
    public int indexOf(Object o) {
        Node<E> current = head;
        int index = 0;
        while (current != null) {
            if (o.equals(current.data)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            result[i] = current.data;
            current = current.next;
        }
        return result;
    }
}
