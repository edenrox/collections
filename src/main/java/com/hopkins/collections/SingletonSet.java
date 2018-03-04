package com.hopkins.collections;

import java.lang.reflect.Array;
import java.util.Objects;

/**
 * A {@link Set} with only a single item.
 */
final class SingletonSet<T> implements Set<T> {
  private final T data;

  SingletonSet(T item) {
    this.data = item;
  }

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
    return Objects.equals(item, data);
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    Iterator<?> iter = c.iterator();
    while (iter.hasNext()) {
      if (!contains(iter.next())) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public Iterator<T> iterator() {
    return new SingletonIterator<>(data);
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
    return 1;
  }

  @Override
  public Object[] toArray() {
    return new Object[]{data};
  }

  @Override
  public T[] toArray(T[] a) {
    if (a.length < 1) {
      a = (T[]) Array.newInstance(a.getClass().getComponentType(), 1);
    }
    a[0] = data;
    if (a.length > 1) {
      a[1] = null;
    }
    return a;
  }
}
