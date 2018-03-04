package com.hopkins.collections;

import java.lang.reflect.Array;

/**
 * @author ian_000
 */
final class MapValueCollection<T> implements Collection<T> {
  private final Map<?, T> map;
  private final MapEntryIteratorFactory<?, T> factory;

  MapValueCollection(Map<?, T> map, MapEntryIteratorFactory<?, T> factory) {
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
  public boolean contains(Object item) {
    return map.containsValue(item);
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean isEmpty() {
    return map.isEmpty();
  }

  @Override
  public Iterator<T> iterator() {
    return new MapValueIterator(factory.newIterator());
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
