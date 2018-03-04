package com.hopkins.collections;

/**
 *
 */
final class MapValueIterator<T> implements Iterator<T> {
  private final Iterator<Map.Entry<?, T>> iterator;

  MapValueIterator(Iterator<Map.Entry<?, T>> iterator) {
    if (iterator == null) {
      throw new NullPointerException();
    }
    this.iterator = iterator;
  }

  @Override
  public boolean hasNext() {
    return iterator.hasNext();
  }

  @Override
  public T next() {
    return iterator.next().getValue();
  }

  @Override
  public void remove() {
    iterator.remove();
  }
}
