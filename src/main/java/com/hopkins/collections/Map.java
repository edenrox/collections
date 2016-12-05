package com.hopkins.collections;

public interface Map<K,V> {
    /** Remove all items from the {@link Map}. */
    void clear();
    
    /** Returns {@code true} if the Map contains the specified key. */
    boolean containsKey(Object key);
    
    /** Returns {@code true} if the Map contains the specified value. */
    boolean containsValue(Object value);
    
    Set<Map.Entry<K,V>> entrySet();
    
    V get(Object key);
    
    boolean isEmpty();
    
    Set<K> keySet();
    
    V put(K key, V value);
    
    void putAll(Map<? extends K, ? extends V> m);
    
    V remove(Object key);
    
    int size();
    
    Collection<V> values();
    
    public static interface Entry<K, V> {
        K getKey();
        
        V getValue();
        
        V setValue(V value);
    }
}
