package com.hopkins.collections;

/**
 * An implementation of {@link Set} that is empty and unmodifiable.
 */
final class EmptySet<T> implements Set<T> {

  @Override
  public boolean add(T item) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean addAll(Collection<? extends T> c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void clear() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean contains(Object item) {
    return false;
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return c.isEmpty();
  }

  @Override
  public boolean isEmpty() {
    return true;
  }

  @Override
  public Iterator<T> iterator() {
    return Collections.emptyIterator();
  }

  @Override
  public boolean remove(Object item) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public int size() {
    return 0;
  }

  @Override
  public Object[] toArray() {
    return new Object[0];
  }

  @Override
  public T[] toArray(T[] a) {
    if (a.length > 0) {
      a[0] = null;
    }
    return a;
  }
}
