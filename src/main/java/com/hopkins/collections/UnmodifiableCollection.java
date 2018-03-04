package com.hopkins.collections;

/**
 * An implementation of {@link Collection} that wraps an underlying
 * {@link Collection} and does not allow modification.
 */
final class UnmodifiableCollection<T> implements Collection<T> {
  private final Collection<T> collection;

  UnmodifiableCollection(Collection<T> collection) {
    if (collection == null) {
      throw new NullPointerException();
    }
    this.collection = collection;
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
    return collection.contains(item);
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return collection.containsAll(c);
  }

  @Override
  public boolean isEmpty() {
    return collection.isEmpty();
  }

  @Override
  public Iterator<T> iterator() {
    return new UnmodifiableIterator<>(collection.iterator());
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
    return collection.size();
  }

  @Override
  public Object[] toArray() {
    return collection.toArray();
  }

  @Override
  public T[] toArray(T[] a) {
    return collection.toArray(a);
  }
}
