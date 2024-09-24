package org.example;

public class MyLinkedList implements MyList {
    private Node head;
    private int size;

    private static class Node {
        Object data;
        Node next;

        Node(Object data) {
            this.data = data;
        }
    }

    @Override
    public void add(Object e) {
        if (head == null) {
            head = new Node(e);
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node(e);
        }
        size++;
    }

    @Override
    public void add(int index, Object element) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        if (index == 0) {
            Node newNode = new Node(element);
            newNode.next = head;
            head = newNode;
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            Node newNode = new Node(element);
            newNode.next = current.next;
            current.next = newNode;
        }
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
        for (int i = 0; i < c.length; i++) {
            add(index + i, c[i]);
        }
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    @Override
    public Object remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Object removedData;
        if (index == 0) {
            removedData = head.data;
            head = head.next;
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            removedData = current.next.data;
            current.next = current.next.next;
        }
        size--;
        return removedData;
    }

    @Override
    public void set(int index, Object element) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.data = element;
    }

    @Override
    public int indexOf(Object o) {
        Node current = head;
        for (int i = 0; i < size; i++) {
            if (current.data.equals(o)) return i;
            current = current.next;
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node current = head;
        for (int i = 0; i < size; i++) {
            array[i] = current.data;
            current = current.next;
        }
        return array;
    }
}