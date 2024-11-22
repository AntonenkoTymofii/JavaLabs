package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CacheTest {

    private Cache<String, String> cache;

    @BeforeEach
    public void setUp() {
        cache = new Cache<>(2);
    }

    @Test
    public void testPutAndGet() {
        cache.put("a", "1");
        assertEquals("1", cache.get("a"));
    }

    @Test
    public void testEviction() {
        cache.put("a", "1");
        cache.put("b", "2");
        cache.put("c", "3"); // Виселяє "a"
        assertNull(cache.get("a"));
        assertEquals("2", cache.get("b"));
        assertEquals("3", cache.get("c"));
    }

    @Test
    public void testContainsKey() {
        cache.put("a", "1");
        assertTrue(cache.containsKey("a"));
        assertFalse(cache.containsKey("b"));
    }

    @Test
    public void testSize() {
        cache.put("a", "1");
        cache.put("b", "2");
        assertEquals(2, cache.size());
    }
}