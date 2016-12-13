package com.hopkins.collections;

/** 
 * An implementation of {@link Set} that wraps an underlying {@link Set} and
 * does not allow modification.
 */
final class UnmodifiableSet<T> implements Set<T> {
    private final Set<T> set;
    
    UnmodifiableSet(Set<T> set) {
        if (set == null) {
            throw new NullPointerException();
        }
        this.set = set;
    }

    @Override
    public boolean add(T item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object item) {
        return set.contains(item);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return set.containsAll(c);
    }

    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return new UnmodifiableIterator(set.iterator());
    }

    @Override
    public boolean remove(Object item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return set.size();
    }

    @Override
    public Object[] toArray() {
        return set.toArray();
    }

    @Override
    public T[] toArray(T[] a) {
        return set.toArray(a);
    }
}
