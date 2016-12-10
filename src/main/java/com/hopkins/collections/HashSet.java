package com.hopkins.collections;

import java.lang.reflect.Array;

public class HashSet<T> implements Set<T> {
    private final HashMap<T, Object> map;
    
    public HashSet() {
        map = new HashMap<>();
    }
    
    public HashSet(int initialCapacity) {
        map = new HashMap<>(initialCapacity);
    }
    
    public HashSet(int initialCapacity, float loadFactor) {
        map = new HashMap<>(initialCapacity, loadFactor);
    }
    
    public HashSet(Collection<? extends T> c) {
        this();
        addAll(c);
    }

    @Override
    public boolean add(T item) {
        if (map.containsKey(item)) {
            return false;
        }
        
        map.put(item, null /* value */);
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean isChanged = false;
        Iterator<? extends T> iter = c.iterator(); 
        while (iter.hasNext()) {
            isChanged |= add(iter.next());
        }
        return isChanged;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean contains(Object item) {
        return map.containsKey(item);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Iterator<?> iter = c.iterator();
        while (iter.hasNext()) {
            if (!map.containsKey(iter.next())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public boolean remove(Object item) {
        if (!map.containsKey(item)) {
            return false;
        }
        map.remove(item);
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Iterator<?> iter = c.iterator();
        boolean isChanged = false;
        while (iter.hasNext()) {
            Object item = iter.next();
            isChanged |= map.containsKey(item);
            map.remove(item);
        }
        return isChanged;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isChanged = false;
        Iterator<T> iter = iterator();
        while (iter.hasNext()) {
            if (!c.contains(iter.next())) {
                iter.remove();
                isChanged = true;
            }
        }
        return isChanged;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public Object[] toArray() {
        Object[] a = new Object[size()];
        
        return a;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size()) {
            a = (T[]) Array.newInstance(a.getClass().getComponentType(), size());
        }
        
        if (a.length > size()) {
            a[size()] = null;
        }
        return a;
    }
}
