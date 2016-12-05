package com.hopkins.collections;

final class ComparableComparator<T extends Comparable>
        implements Comparator<T> {
    static final ComparableComparator INSTANCE = new ComparableComparator();

    @Override
    public int compare(T a, T b) {
        return a.compareTo(b);
    }
}
