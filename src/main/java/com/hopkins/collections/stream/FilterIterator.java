package com.hopkins.collections.stream;

import com.hopkins.collections.Iterator;
import com.hopkins.collections.NoSuchElementException;

import java.util.function.Predicate;

final class FilterIterator<T> implements Iterator<T> {
    private final Iterator<T> source;
    private final Predicate<T> predicate;
    private T next;

    FilterIterator(Iterator<T> source, Predicate<T> predicate) {
        this.source = source;
        this.predicate = predicate;
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
        return next;
    }

    private boolean moveToNext() {
        while (source.hasNext()) {
            T item = source.next();
            if (predicate.test(item)) {
                next = item;
                return true;
            }
        }
        return false;
    }

}
