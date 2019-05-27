package com.hopkins.collections.stream;

import com.hopkins.collections.Comparator;
import com.hopkins.collections.Iterator;

final class SortedIterator<T> implements Iterator<T> {
    private final Iterator<T> source;
    private final Comparator<T> comparator;

    SortedIterator(Iterator<T> source, Comparator<T> comparator) {
        this.source = source;
        this.comparator = comparator;
    }

    @Override
    public boolean hasNext() {
        return false;


    }

    @Override
    public T next() {
        return null;
    }
}
