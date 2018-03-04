package com.hopkins.collections;

public interface Iterable<T> {

  /**
   * Returns an {@link Iterator} over a set of elements of type {@code T}.
   */
  Iterator<T> iterator();
}
