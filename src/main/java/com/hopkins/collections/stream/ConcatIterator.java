package com.hopkins.collections.stream;

import com.hopkins.collections.Iterator;

final class ConcatIterator<T> implements Iterator<T> {
  private final Iterator<T> first;
  private final Iterator<T> second;

  ConcatIterator(Iterator<T> first, Iterator<T> second) {
    this.first = first;
    this.second = second;
  }

  @Override
  public boolean hasNext() {
    return first.hasNext() || second.hasNext();
  }

  @Override
  public T next() {
    return first.hasNext() ? first.next() : second.next();
  }
}
