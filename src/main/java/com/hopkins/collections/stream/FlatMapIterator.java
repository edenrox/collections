package com.hopkins.collections.stream;

import com.hopkins.collections.Iterator;

import java.util.function.Function;

final class FlatMapIterator<T, R> implements Iterator<R> {
    private final Iterator<T> source;
    private final Function<T, Stream<R>> mapper;

    FlatMapIterator(Iterator<T> source, Function<T, Stream<R>> mapper) {
        this.source = source;
        this.mapper = mapper;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public R next() {
        return null;
    }
}
