package com.hopkins.collections.stream;

import com.hopkins.collections.Arrays;
import com.hopkins.collections.Comparator;
import com.hopkins.collections.Iterator;

final class SortedIterator<T> implements Iterator<T> {
  private final Iterator<T> source;
  private final Comparator<? super T> comparator;
  private T[] items;
  private int nextIndex;

  SortedIterator(Iterator<T> source, Comparator<? super T> comparator) {
    this.source = source;
    this.comparator = comparator;
  }

  @Override
  public boolean hasNext() {
    if (items == null) {
      moveToFirst();
    }
    return nextIndex < items.length;

  }

  @Override
  public T next() {
    return (T) items[nextIndex++];
  }

  private void moveToFirst() {
    SpinedBuffer<T> buffer = new SpinedBuffer<>();
    while (source.hasNext()) {
      buffer.add(source.next());
    }
    items = (T[]) buffer.asArray(Object[]::new);
    Arrays.sort(items, comparator);
  }
}
