package com.hopkins.collections;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class Stream<T> {

    public static final Stream<T> of(Iterable<T> iterable) {
        return new Stream<>(iterable.iterator());
    }

    private final Iterator<T> iterator;

    private Stream(Iterator<T> iterator) {
        if (iterator == null) {
            throw new NullPointerException();
        }
        this.iterator = iterator;
    }

    public Stream<T> filter(Predicate<T> predicate) {

    }

    public void forEach(Consumer<T> consumer) {
        while (iterator.hasNext()) {
            consumer.accept(iterator.next());
        }
    }
}
