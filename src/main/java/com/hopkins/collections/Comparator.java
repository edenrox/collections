package com.hopkins.collections;

public interface Comparator<T> {
    
    /** Compares its two arguments for order. */
    int compare(T o1, T o2);
}
