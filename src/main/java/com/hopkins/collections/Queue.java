package com.hopkins.collections;

public interface Queue<T> extends Collection<T> {
    
    /** Returns (but does not remove) the item at the head of the queue. */
    T element();
    
    /** Insert the specified item into the queue. */
    boolean offer(T e);
    
    /** Returns (but does not remove) the item at the head of the queue. */
    T peek();
    
    /** Returns and removes the item at the head of the queue. */
    T poll();
    
    /** Returns and removes the item at the head of the queue. */
    T remove();
}
