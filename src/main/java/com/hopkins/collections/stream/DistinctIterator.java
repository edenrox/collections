package com.hopkins.collections.stream;

import com.hopkins.collections.HashSet;
import com.hopkins.collections.Iterator;
import com.hopkins.collections.NoSuchElementException;
import com.hopkins.collections.Set;

final class DistinctIterator<T> implements Iterator<T> {
    private final Iterator<T> source;
    private final Set<T> seen;
    private T next;

    DistinctIterator(Iterator<T> source) {
        this.source = source;
        this.seen = new HashSet<>();
    }

    @Override
    public boolean hasNext() {
        return next != null || moveToNext();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        T value = next;
        next = null;
        return value;
    }

    private boolean moveToNext() {
        while (source.hasNext()) {
            T item = source.next();
            if (!seen.contains(item)) {
                seen.add(item);
                next = item;
                return true;
            }
        }
        return false;
    }
}
