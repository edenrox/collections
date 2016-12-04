package com.hopkins.collections;

public interface List<T> extends Collection<T> {
    int INDEX_NOT_FOUND = -1;
    
    void add(int index, T element);
    
    boolean addAll(int index, Collection<? extends T> c);
    
    T get(int index);
    
    int indexOf(Object o);
    
    int lastIndexOf(Object o);
    
    T remove(int index);
    
    T set(int index, T element);
    
    //List<E> subList(int fromIndex, int toIndex);
}
