package com.hopkins.collections;

public interface Comparator<T> {
    
    /** Compares its two arguments for order. */
    int compare(T a, T b);
}
