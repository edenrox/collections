package com.hopkins.collections;

import java.lang.reflect.Array;

/**
 * @author ian_000
 */
final class MapKeySet<T> implements Set<T> {
  private final Map<T, ?> map;
  private final MapEntryIteratorFactory<T, ?> factory;

  MapKeySet(Map<T, ?> map, MapEntryIteratorFactory<T, ?> factory) {
    if (map == null || factory == null) {
      throw new NullPointerException();
    }
    this.map = map;
    this.factory = factory;
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
    map.clear();
  }

  @Override
  public boolean remove(Object item) {
    if (!map.containsKey(item)) {
      return false;
    }
    map.remove(item);
    return true;
  }

  @Override
  public boolean contains(Object item) {
    return map.containsKey(item);
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    Iterator<?> iter = c.iterator();
    while (iter.hasNext()) {
      if (!map.containsKey(iter.next())) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean isEmpty() {
    return map.isEmpty();
  }

  @Override
  public Iterator<T> iterator() {
    return new MapKeySetIterator(factory.newIterator());
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    Iterator<?> iter = c.iterator();
    boolean isChanged = false;
    while (iter.hasNext()) {
      Object item = iter.next();
      if (map.containsKey(item)) {
        map.remove(item);
        isChanged = true;
      }
    }
    return isChanged;
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    Iterator<T> iter = iterator();
    boolean isChanged = false;
    while (iter.hasNext()) {
      if (!c.contains(iter.next())) {
        iter.remove();
        isChanged = true;
      }
    }
    return isChanged;
  }

  @Override
  public int size() {
    return map.size();
  }

  @Override
  public Object[] toArray() {
    Object[] array = new Object[map.size()];

    int index = 0;
    Iterator<T> iter = iterator();
    while (iter.hasNext()) {
      array[index++] = iter.next();
    }

    return array;
  }

  @Override
  public T[] toArray(T[] a) {
    if (a.length < map.size()) {
      a = (T[]) Array.newInstance(a.getClass().getComponentType(), map.size());
    }

    int index = 0;
    Iterator<T> iter = iterator();
    while (iter.hasNext()) {
      a[index++] = iter.next();
    }

    if (a.length > map.size()) {
      a[map.size()] = null;
    }
    return a;
  }
}
