package com.hopkins.collections.stream;

import com.hopkins.collections.Iterator;
import com.hopkins.collections.NoSuchElementException;

final class LimitIterator<T> implements Iterator<T> {
    private final Iterator<T> source;
    private int numToKeep;

    LimitIterator(Iterator<T> source, int numToKeep) {
        this.source = source;
        this.numToKeep = numToKeep;
    }

    @Override
    public boolean hasNext() {
        return numToKeep > 0 && source.hasNext();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        numToKeep--;
        return source.next();
    }
}
