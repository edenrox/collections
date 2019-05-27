package com.hopkins.collections.stream;

import com.hopkins.collections.Iterator;
import com.hopkins.collections.NoSuchElementException;

final class SkipIterator<T> implements Iterator<T> {
    private final Iterator<T> source;
    private int numToSkip;

    SkipIterator(Iterator<T> source, int numToSkip) {
        this.source = source;
        this.numToSkip = numToSkip;
    }

    @Override
    public boolean hasNext() {
        while (numToSkip > 0 && source.hasNext()) {
            source.next();
            numToSkip--;
        }
        return source.hasNext();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return source.next();
    }
}
