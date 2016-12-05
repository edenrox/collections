package com.hopkins.collections;

/** 
 * An implementation of a {@link Set} that is backed by an {@link ArrayList}. 
 */
public class ArraySet<T> implements Set<T> {
    
    private final ArrayList<T> list;
    
    public ArraySet() {
        list = new ArrayList<>();
    }
    
    public ArraySet(Collection<? extends T> c) {
        list = new ArrayList<>();
        addAll(c);
    }

    @Override
    public boolean add(T item) {
        if (list.contains(item)) {
            return false;
        }
        list.add(item);
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Iterator<? extends T> iter = c.iterator();
        boolean modified = false;
        while (iter.hasNext()) {
            modified |= add(iter.next());
        }
        return modified;
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean contains(Object item) {
        return list.contains(item);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public boolean remove(Object item) {
        return list.remove(item);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return list.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return list.retainAll(c);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return list.toArray(a);
    }
}
