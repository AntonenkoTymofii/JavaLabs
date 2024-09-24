package org.example;

import java.util.HashMap;

public class LinkedHashSet {
    private final HashMap<Object, Object> map;
    private static final Object DUMMY = new Object();

    public LinkedHashSet() {
        map = new HashMap<>();
    }

    public void add(Object e) {
        map.put(e, DUMMY);
    }

    public boolean contains(Object e) {
        return map.containsKey(e);
    }

    public boolean remove(Object e) {
        return map.remove(e) != null;
    }

    public int size() {
        return map.size();
    }
}
