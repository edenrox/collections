package com.hopkins.collections;

public interface Collection<T> {
    /** Add the specified item to the collection. */
    boolean add(T item);
    
    /** Add the items in the specified collection to this collection. */ 
    boolean addAll(Collection<? extends T> c);
    
    /** Clear all the elements from this collection. */
    void clear();
    
    /** Returns {@code true} if this collection contains the specified item. */
    boolean contains(Object item);
    
    /** 
     * Returns {@code true} if this collection contains all items in the 
     * specified collection. 
     */
    boolean containsAll(Collection<?> c);
    
    /** Returns {@code true} if this collection contains no elements. */
    boolean isEmpty();
    
    /** Returns an {@link Iterator} over the elements in this collection. */
    Iterator<T> iterator();
    
    /** 
     * Removes a single instance of the specified item from this collection 
     * if present. 
     */
    boolean remove(Object item);
    
    /** Remove all the the */
    boolean removeAll(Collection<?> c);
    
    /** 
     * Retain only the items in this collection that are also in the specified 
     * collection. 
     */
    boolean retainAll(Collection<?> c);

    /** Returns the number of elements in this collection. */
    int size();
    
    /** Returns an array containing all the items in this collection, */
    Object[] toArray();
    
    /** Returns an array containing all the items in this collection. */
    T[] toArray(T[] a);
}
