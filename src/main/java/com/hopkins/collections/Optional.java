package com.hopkins.collections;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public final class Optional<T> {
    private static final Optional EMPTY = new Optional(null);

    public static <T> Optional<T> empty() {
        return EMPTY;
    }

    public static <T> Optional<T> of(T item) {
        Objects.requireNonNull(item);
        return new Optional<>(item);
    }

    public static <T> Optional<T> ofNullable(T item) {
        if (item == null) {
            return EMPTY;
        }
        return new Optional<>(item);
    }

    private T item;

    private Optional(T item) {
        this.item = item;
    }

    public T get() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return item;
    }

    public boolean isEmpty() {
        return item == null;
    }

    public boolean isPresent() {
        return item != null;
    }

    public void ifPresent(Consumer<? super T> consumer) {
        if (isPresent()) {
            consumer.accept(item);
        }
    }

    public Optional<T> filter(Predicate<? super T> predicate) {
        if (isEmpty() || !predicate.test(item)) {
            return EMPTY;
        }
        return this;
    }

    public <R> Optional<R> map(Function<T, R> mapper) {
        return isEmpty() ? EMPTY : Optional.of(mapper.apply(item));
    }

    public T orElse(T other) {
        return isPresent() ? item : other;
    }

    public T orElseGet(Supplier<? extends T> supplier) {
        return isPresent() ? item : supplier.get();
    }

    public <X extends Throwable> T orElseThrow(Supplier<? extends X> supplier) throws X {
        if (isPresent()) {
            return item;
        } else {
            throw supplier.get();
        }
    }

    @Override
    public int hashCode() {
        return isEmpty() ? 0 : item.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Optional) {
            Optional that = (Optional) obj;
            if (this.isEmpty() != that.isEmpty()) {
                return false;
            }
            return this.item.equals(that.item);
        }
        return false;
    }

    @Override
    public String toString() {
        return isEmpty() ? "Optional {EMPTY}" : "Optional {" + item + "}";
    }
}
