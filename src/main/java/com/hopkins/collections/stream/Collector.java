package com.hopkins.collections.stream;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public interface Collector<T, A, R> {

    static <T, A, R> Collector<T, A, R> of(
            Supplier<A> supplier, BiConsumer<A, T> accumulator, Function<A, R> finisher) {
        return new CollectorImpl<T, A, R>(supplier, accumulator, finisher);
    }

    static <T, A> Collector<T, A, A> of(Supplier<A> supplier, BiConsumer<A, T> accumulator) {
        return new CollectorImpl<T, A, A>(supplier, accumulator, Function.identity());
    }

    BiConsumer<A, T> accumulator();

    Function<A, R> finisher();

    Supplier<A> supplier();

    final class CollectorImpl<T, A, R> implements Collector<T, A, R> {
        private final Supplier<A> supplier;
        private final BiConsumer<A, T> accumulator;
        private final Function<A, R> finisher;

        CollectorImpl(Supplier<A> supplier, BiConsumer<A, T> accumulator, Function<A, R> finisher) {
            this.supplier = supplier;
            this.accumulator = accumulator;
            this.finisher = finisher;
        }

        @Override
        public BiConsumer<A, T> accumulator() {
            return accumulator;
        }

        @Override
        public Function<A, R> finisher() {
            return finisher;
        }

        @Override
        public Supplier<A> supplier() {
            return supplier;
        }
    }
}
