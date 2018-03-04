package com.hopkins.collections;

/**
 * An implementation of {@link Iterator} that wraps an underlying
 * {@link Iterator} and does not allow modification.
 */
final class UnmodifiableIterator<T> implements Iterator<T> {
  private final Iterator<T> iterator;

  public UnmodifiableIterator(Iterator<T> iterator) {
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
    return iterator.next();
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }
}
