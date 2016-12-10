package com.hopkins.collections;

/** Compares two objects of type {@code T} for order. */
public interface Comparator<T> {
    
    /** Compares its two arguments for order. */
    int compare(T a, T b);
}
