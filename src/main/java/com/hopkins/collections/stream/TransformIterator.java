package com.hopkins.collections.stream;

import com.hopkins.collections.Iterator;

import java.util.function.Function;

final class TransformIterator<T, R> implements Iterator<R> {
    private final Iterator<T> source;
    private final Function<T, R> mapper;

    TransformIterator(Iterator<T> source, Function<T, R> mapper) {
        this.source = source;
        this.mapper = mapper;
    }

    @Override
    public boolean hasNext() {
        return source.hasNext();
    }

    @Override
    public R next() {
        return mapper.apply(source.next());
    }
}
