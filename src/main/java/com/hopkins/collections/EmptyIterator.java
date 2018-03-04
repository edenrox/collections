package com.hopkins.collections;

/**
 * An implementation of {@link Iterator} that is empty and unmodifiable.
 */
final class EmptyIterator<T> implements Iterator<T> {

  @Override
  public boolean hasNext() {
    return false;
  }

  @Override
  public T next() {
    throw new NoSuchElementException();
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }
}
