package com.hopkins.collections.stream;

import com.hopkins.collections.Iterator;

import java.util.function.Supplier;

final class SupplierIterator<T> implements Iterator<T> {
    private final Supplier<T> supplier;

    SupplierIterator(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public T next() {
        return supplier.get();
    }
}
