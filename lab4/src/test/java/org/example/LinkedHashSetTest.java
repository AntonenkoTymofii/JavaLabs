package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LinkedHashSetTest {

    private LinkedHashSet set;

    @BeforeEach
    public void setUp() {
        set = new LinkedHashSet();
    }

    @Test
    public void testAdd() {
        set.add("a");
        assertTrue(set.contains("a"));
        assertEquals(1, set.size());
    }

    @Test
    public void testAddDuplicate() {
        set.add("a");
        set.add("a");
        assertEquals(1, set.size());
    }

    @Test
    public void testRemove() {
        set.add("a");
        set.remove("a");
        assertFalse(set.contains("a"));
        assertEquals(0, set.size());
    }

    @Test
    public void testContains() {
        set.add("a");
        assertTrue(set.contains("a"));
        assertFalse(set.contains("b"));
    }

    @Test
    public void testSize() {
        set.add("a");
        set.add("b");
        assertEquals(2, set.size());
    }
}