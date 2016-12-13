package com.hopkins.collections;

/**
 *
 */
public interface ListIterator<T> extends Iterator<T> {
    
    void add(T item);
    
    boolean hasPrevious();
    
    int nextIndex();
    
    T previous();
    
    int previousIndex();
    
    void set(T item);
}
