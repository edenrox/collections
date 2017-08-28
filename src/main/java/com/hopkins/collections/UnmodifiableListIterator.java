package com.hopkins.collections;

/**
 * An implementation of {@link ListIterator} that wraps an underlying 
 * {@link ListIterator} and does not allow modification.
 */
final class UnmodifiableListIterator<T> implements ListIterator<T> {
    
    private final ListIterator<T> listIterator;

    UnmodifiableListIterator(ListIterator<T> listIterator) {
        if (listIterator == null) {
            throw new NullPointerException();
        }
        this.listIterator = listIterator;
    }

    @Override
    public void add(T item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasPrevious() {
        return listIterator.hasPrevious();
    }

    @Override
    public int nextIndex() {
        return listIterator.nextIndex();
    }

    @Override
    public T previous() {
        return listIterator.previous();
    }

    @Override
    public int previousIndex() {
        return listIterator.previousIndex();
    }

    @Override
    public void set(T item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasNext() {
        return listIterator.hasNext();
    }

    @Override
    public T next() {
        return listIterator.next();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
