package org.example;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cache<K, V> {
    private final LinkedHashMap<K, V> map;
    private final int capacity;

    public Cache(int capacity) {
        this.capacity = capacity;
        this.map = new LinkedHashMap<>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > Cache.this.capacity;
            }
        };
    }

    public V get(K key) {
        return map.get(key);
    }

    public void put(K key, V value) {
        map.put(key, value);
    }

    public boolean putAll(Map<? extends K, ? extends V> m) {
        boolean modified = false;
        for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
            map.put(entry.getKey(), entry.getValue());
            modified = true;
        }
        return modified;
    }

    public int size() {
        return map.size();
    }

    public boolean containsKey(K key) {
        return map.containsKey(key);
    }
}

