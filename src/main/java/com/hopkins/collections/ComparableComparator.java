package com.hopkins.collections;

/**
 * A {@link Comparator} for comparing objects that implement {@link Comparable}.
 */
final class ComparableComparator<T extends Comparable<? super T>>
    implements Comparator<T> {
  static final ComparableComparator INSTANCE = new ComparableComparator();

  private ComparableComparator() {
  }

  @Override
  public int compare(T a, T b) {
    return a.compareTo(b);
  }
}
