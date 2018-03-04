package com.hopkins.collections;

/**
 *
 */
final class MapKeySetIterator<T> implements Iterator<T> {
  private final Iterator<Map.Entry<T, ?>> entryIterator;

  MapKeySetIterator(Iterator<Map.Entry<T, ?>> entryIterator) {
    if (entryIterator == null) {
      throw new NullPointerException();
    }
    this.entryIterator = entryIterator;
  }

  @Override
  public boolean hasNext() {
    return entryIterator.hasNext();
  }

  @Override
  public T next() {
    return entryIterator.next().getKey();
  }

  @Override
  public void remove() {
    entryIterator.remove();
  }
}
