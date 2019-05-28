package com.hopkins.collections;

/**
 * Compares two objects of type {@code T} for order.
 */
public interface Comparator<T> {
  Comparator NATURAL_ORDER = (a, b) -> ((Comparable) a).compareTo((Comparable) b);
  Comparator REVERSE_ORDER = (a, b) -> ((Comparable) b).compareTo((Comparable) a);

  /**
   * Compares its two arguments for order.
   */
  int compare(T a, T b);

  static <T extends Comparable<? extends T>> Comparator<T> naturalOrder() {
    return NATURAL_ORDER;
  }

  static <T extends Comparable<? extends T>> Comparator<T> reversedOrder() {
    return REVERSE_ORDER;
  }
}
