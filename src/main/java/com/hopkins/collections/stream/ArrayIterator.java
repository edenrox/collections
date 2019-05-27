package com.hopkins.collections.stream;

import com.hopkins.collections.Iterator;
import com.hopkins.collections.NoSuchElementException;

final class ArrayIterator<T> implements Iterator<T> {
    private final T[] array;
    private int next;

    ArrayIterator(T[] array) {
        this.array = array;
        this.next = 0;
    }

    @Override
    public boolean hasNext() {
        return next < array.length;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return array[next++];
    }
}
