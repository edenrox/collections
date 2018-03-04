package com.hopkins.collections;

public interface Map<K, V> {
  /**
   * Remove all items from the {@link Map}.
   */
  void clear();

  /**
   * Returns {@code true} if the Map contains the specified key.
   */
  boolean containsKey(Object key);

  /**
   * Returns {@code true} if the Map contains the specified value.
   */
  boolean containsValue(Object value);

  Set<Map.Entry<K, V>> entrySet();

  V get(Object key);

  /**
   * Returns {@code true} if the Map is empty.
   */
  boolean isEmpty();

  /**
   * Returns a {@link Set} of the keys currently stored in the Map.
   */
  Set<K> keySet();

  /**
   * Puts a key-value pair into the Map.
   */
  V put(K key, V value);

  /**
   * Put all the key-value pair in the specified Map into this Map.
   */
  void putAll(Map<? extends K, ? extends V> map);

  /**
   * Remove the entry with the specified key.
   */
  V remove(Object key);

  /**
   * Returns the number of entries currently stored in the Map.
   */
  int size();

  /**
   * Returns a {@link Collection} of the values in the Map.
   */
  Collection<V> values();

  public interface Entry<K, V> {
    K getKey();

    V getValue();

    V setValue(V value);
  }
}
