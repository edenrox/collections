package com.hopkins.collections;

/**
 * An implementation of {@link Set} that is backed by a {@link TreeMap}.
 */
public class TreeSet<T> implements Set<T> {
    private final TreeMap<T, Object> map;
    
    public TreeSet() {
        map = new TreeMap<>();
    }
    
    public TreeSet(Comparator<T> comparator) {
        map = new TreeMap<>(comparator);
    }
    
    public TreeSet(Collection<T> c) {
        map = new TreeMap<>();
        addAll(c);
    }

    @Override
    public boolean add(T item) {
        if (map.containsKey(item)) {
            return false;
        }
        map.put(item, null);
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean isChanged = false;
        Iterator<? extends T> iter = c.iterator();
        while(iter.hasNext()) {
            if (add(iter.next())) {
                isChanged = true;
            }
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
        while(iter.hasNext()) {
            if (!contains(iter.next())) {
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
        boolean isChanged = false;
        Iterator<?> iter = c.iterator();
        while(iter.hasNext()) {
            if (remove(iter.next())) {
                isChanged = true;
            }
        }
        return isChanged;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public Object[] toArray() {
        return map.keySet().toArray();
    }

    @Override
    public T[] toArray(T[] a) {
        return map.keySet().toArray(a);
    }
}
