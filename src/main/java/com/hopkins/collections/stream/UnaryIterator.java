package com.hopkins.collections.stream;

import com.hopkins.collections.Iterator;

import java.util.function.UnaryOperator;

final class UnaryIterator<T> implements Iterator<T> {
    private final UnaryOperator<T> function;
    private T next;

    UnaryIterator(T seed, UnaryOperator<T> function) {
        this.next = seed;
        this.function = function;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public T next() {
        T item = next;
        next = function.apply(next);
        return item;
    }
}
