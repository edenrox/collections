package com.hopkins.collections;

import java.util.Objects;

/**
 * An immutable {@link Map} which contains only a single key/value pair.
 */
final class SingletonMap<K, V> implements Map<K, V> {
  private final K key;
  private final V value;

  SingletonMap(K key, V value) {
    this.key = key;
    this.value = value;
  }

  @Override
  public void clear() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean containsKey(Object key) {
    return Objects.equals(this.key, key);
  }

  @Override
  public boolean containsValue(Object value) {
    return Objects.equals(this.value, value);
  }

  @Override
  public Set<Entry<K, V>> entrySet() {
    return new SingletonSet<Entry<K, V>>(new SingletonEntry());
  }

  @Override
  public V get(Object key) {
    if (Objects.equals(this.key, key)) {
      return value;
    } else {
      return null;
    }
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public Set<K> keySet() {
    return new SingletonSet<>(key);
  }

  @Override
  public V put(K key, V value) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void putAll(Map<? extends K, ? extends V> map) {
    throw new UnsupportedOperationException();
  }

  @Override
  public V remove(Object key) {
    throw new UnsupportedOperationException();
  }

  @Override
  public int size() {
    return 1;
  }

  @Override
  public Collection<V> values() {
    return new SingletonSet<>(value);
  }

  final class SingletonEntry implements Entry<K, V> {
    @Override
    public K getKey() {
      return key;
    }

    @Override
    public V getValue() {
      return value;
    }

    @Override
    public V setValue(V value) {
      throw new UnsupportedOperationException();
    }
  }
}
