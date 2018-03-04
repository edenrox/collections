/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hopkins.collections;

import java.lang.reflect.Array;
import java.util.Objects;

/**
 * @author ian_000
 */
final class MapEntrySet<K, V> implements Set<Map.Entry<K, V>> {
  private final Map<K, V> map;
  private final MapEntryIteratorFactory<K, V> factory;

  MapEntrySet(Map<K, V> map, MapEntryIteratorFactory<K, V> factory) {
    if (map == null || factory == null) {
      throw new NullPointerException();
    }
    this.map = map;
    this.factory = factory;
  }

  @Override
  public boolean add(Map.Entry<K, V> item) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean addAll(Collection<? extends Map.Entry<K, V>> c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void clear() {
    map.clear();
  }

  @Override
  public boolean contains(Object item) {
    if (item instanceof Map.Entry) {
      Map.Entry<?, ?> entry = (Map.Entry<?, ?>) item;
      if (map.containsKey(entry.getKey())) {
        return Objects.equals(entry.getValue(), map.get(entry.getKey()));
      }
    }
    return false;
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
    return map.isEmpty();
  }

  @Override
  public Iterator<Map.Entry<K, V>> iterator() {
    return factory.newIterator();
  }

  @Override
  public boolean remove(Object item) {
    if (item instanceof Map.Entry) {
      Map.Entry<?, ?> entry = (Map.Entry<?, ?>) item;
      if (map.containsKey(entry.getKey())) {
        map.remove(entry.getKey());
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    Iterator<?> iter = c.iterator();
    boolean isChanged = false;
    while (iter.hasNext()) {
      isChanged |= remove(iter.next());
    }
    return isChanged;
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    Iterator<Map.Entry<K, V>> iter = iterator();
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
    Iterator<Map.Entry<K, V>> iter = iterator();
    while (iter.hasNext()) {
      array[index++] = iter.next();
    }

    return array;
  }

  @Override
  public Map.Entry<K, V>[] toArray(Map.Entry<K, V>[] a) {
    if (a.length < map.size()) {
      a = (Map.Entry<K, V>[]) Array.newInstance(a.getClass().getComponentType(), map.size());
    }

    int index = 0;
    Iterator<Map.Entry<K, V>> iter = iterator();
    while (iter.hasNext()) {
      a[index++] = iter.next();
    }

    if (a.length > map.size()) {
      a[map.size()] = null;
    }
    return a;
  }
}
