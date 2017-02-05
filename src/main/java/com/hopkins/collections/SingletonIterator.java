package com.hopkins.collections;

/**
 * An {@link Iterator} over a single element.
 */
final class SingletonIterator<T> implements Iterator<T> {
    private final T data;
    private boolean hasNext;
    
    SingletonIterator(T data) {
        this.data = data;
        this.hasNext = true;
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public T next() {
        if (!hasNext) {
            throw new NoSuchElementException();
        }
        hasNext = false;
        return data;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
