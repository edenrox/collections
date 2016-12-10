package com.hopkins.collections;

/** 
 * An implementation of {@link Map} that is empty and unmodifiable.
 */
final class EmptyMap<K, V> implements Map<K, V> {

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return Collections.emptySet();
    }

    @Override
    public V get(Object key) {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public Set<K> keySet() {
        return Collections.emptySet();
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
        return 0;
    }

    @Override
    public Collection<V> values() {
        return Collections.EMPTY_SET;
    }
}
