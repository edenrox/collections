package com.hopkins.collections;

public interface Iterator<T> {

  /**
   * Returns {@code true} if the iteration has more elements.
   */
  boolean hasNext();

  /**
   * Returns the next element in the iteration.
   */
  T next();

  /**
   * Removes from the underlying collection the last element returned by
   * this iterator (optional operation).
   */
  void remove();
}
