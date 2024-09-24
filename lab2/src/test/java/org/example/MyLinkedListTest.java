package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MyLinkedListTest {

    private MyLinkedList list;

    @BeforeEach
    public void setUp() {
        list = new MyLinkedList();
    }

    @Test
    public void testAdd() {
        list.add("a");
        assertEquals(1, list.size());
        assertEquals("a", list.get(0));
    }

    @Test
    public void testAddAtIndex() {
        list.add("a");
        list.add("b");
        list.add(1, "c");
        assertEquals("a", list.get(0));
        assertEquals("c", list.get(1));
        assertEquals("b", list.get(2));
    }

    @Test
    public void testAddAll() {
        list.addAll(new Object[]{"a", "b", "c"});
        assertEquals(3, list.size());
        assertEquals("a", list.get(0));
        assertEquals("b", list.get(1));
        assertEquals("c", list.get(2));
    }

    @Test
    public void testRemove() {
        list.add("a");
        list.add("b");
        list.remove(0);
        assertEquals(1, list.size());
        assertEquals("b", list.get(0));
    }

    @Test
    public void testSet() {
        list.add("a");
        list.set(0, "b");
        assertEquals("b", list.get(0));
    }

    @Test
    public void testIndexOf() {
        list.add("a");
        list.add("b");
        assertEquals(1, list.indexOf("b"));
        assertEquals(-1, list.indexOf("c"));
    }

    @Test
    public void testToArray() {
        list.addAll(new Object[]{"a", "b"});
        Object[] array = list.toArray();
        assertArrayEquals(new Object[]{"a", "b"}, array);
    }
}
