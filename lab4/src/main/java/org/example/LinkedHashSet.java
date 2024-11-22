package org.example;
import java.util.Collection;
import java.util.HashMap;

public class LinkedHashSet<E> {
    private final HashMap<E, Object> map;
    private static final Object DUMMY = new Object();

    public LinkedHashSet() {
        map = new HashMap<>();
    }

    public void add(E e) {
        map.put(e, DUMMY);
    }

    public boolean addAll(Collection<? extends E> c) {
        boolean modified = false;
        for (E e : c) {
            if (map.put(e, DUMMY) == null) {
                modified = true;
            }
        }
        return modified;
    }

    public boolean contains(E e) {
        return map.containsKey(e);
    }

    public boolean remove(E e) {
        return map.remove(e) != null;
    }

    public boolean removeAll(Collection<? super E> c) {
        boolean modified = false;
        for (Object e : c) {
            if (map.remove(e) != null) {
                modified = true;
            }
        }
        return modified;
    }

    public int size() {
        return map.size();
    }
}
