package com.hopkins.collections.stream;

import com.hopkins.collections.*;
import com.hopkins.collections.Iterable;

import java.util.function.*;

public class Stream<T> {
    private static final Stream EMPTY = new Stream<>(Collections.emptyIterator());

    public static <R> Stream<R> concat(Stream<R> first, Stream<R> second) {
        return new Stream<>(new ConcatIterator<>(first.source, second.source));
    }

    public static <R> Stream<R> empty() {
        return EMPTY;
    }

    public static <R> Stream<R> generate(Supplier<R> supplier) {
        return new Stream<>(new SupplierIterator<>(supplier));
    }

    public static <R> Stream<R> iterator(R seed, UnaryOperator<R> function) {
        return new Stream<>(new UnaryIterator<>(seed, function));
    }

    public static <R> Stream<R> of(Iterable<R> iterable) {
        return new Stream<R>(iterable.iterator());
    }

    @SafeVarargs
    public static <R> Stream<R> of(R... array) {
        return new Stream<R>(new ArrayIterator<>(array));
    }

    private final Iterator<T> source;

    private Stream(Iterator<T> source) {
        this.source = source;
    }

    public boolean anyMatch(Predicate<T> predicate) {
        while (source.hasNext()) {
            if (predicate.test(source.next())) {
                return true;
            }
        }
        return false;
    }

    public boolean allMatch(Predicate<T> predicate) {
        while (source.hasNext()) {
            if (!predicate.test(source.next())) {
                return false;
            }
        }
        return true;
    }

    public boolean noneMatch(Predicate<T> predicate) {
        while (source.hasNext()) {
            if (predicate.test(source.next())) {
                return false;
            }
        }
        return true;
    }

    public Optional<T> findFirst() {
        if (source.hasNext()) {
            return Optional.of(source.next());
        }
        return Optional.empty();
    }

    public Optional<T> findAny() {
        return findFirst();
    }

    public long count() {
        long count = 0;
        while (source.hasNext()) {
            source.next();
            count++;
        }
        return count;
    }

    public Stream<T> distinct() {
        return new Stream<>(new DistinctIterator<>(source));
    }

    public Stream<T> sorted() {
        return sorted(ComparableComparator.INSTANCE);
    }

    public Stream<T> sorted(Comparator<T> comparator) {
        return new Stream<>(new SortedIterator<>(source, comparator));
    }

    public <R> Stream<R> flatMap(Function<T, Stream<R>> mapper) {

    }

    public Stream<T> limit(int numToKeep) {
        if (numToKeep < 0) {
            throw new IllegalArgumentException();
        } else if (numToKeep == 0) {
            return empty();
        }
        return new Stream<>(new LimitIterator<>(source, numToKeep));
    }

    public Stream<T> skip(int numToSkip) {
        return new Stream<>(new SkipIterator<>(source, numToSkip));
    }

    public Stream<T> filter(Predicate<T> predicate) {
        return new Stream<>(new FilterIterator<>(source, predicate));
    }

    public <R> Stream<R> map(Function<T, R> mapper) {
        return new Stream<>(new TransformIterator<>(source, mapper));
    }

    public Stream<T> peek(Consumer<T> consumer) {
        return new Stream<>(new PeekIterator<>(source, consumer));
    }

    public <R, A> R collect(Collector<T, A, R> collector) {
        A mutableValue = collector.supplier().get();
        BiConsumer<A, T> accumulator = collector.accumulator();
        while (source.hasNext()) {
            T item = source.next();
            accumulator.accept(mutableValue, item);
        }
        return collector.finisher().apply(mutableValue);
    }

    public void forEach(Consumer<T> consumer) {
        while (source.hasNext()) {
            consumer.accept(source.next());
        }
    }

    public Object[] toArray() {

    }

    public <A> A[] toArray(IntFunction<A[]> generator) {

    }
}
