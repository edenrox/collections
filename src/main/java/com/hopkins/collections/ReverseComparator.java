package com.hopkins.collections;

final class ReverseComparator<T> implements Comparator<T> {
  private final Comparator<T> comparator;

  public ReverseComparator(Comparator<T> comparator) {
    this.comparator = comparator;
  }

  @Override
  public int compare(T a, T b) {
    return comparator.compare(b, a);
  }
}
