package com.hopkins.collections;

import java.util.Objects;

public class HashMap<K, V> implements Map<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    
    private final float loadFactor;
    private final MapEntryIteratorFactory<K, V> iteratorFactory = new MapEntryIteratorFactory<K, V>() {
        @Override
        public Iterator<Map.Entry<K, V>> newIterator() {
            return new HashMapEntryIterator(table);
        }
    };
    
    private HashMapEntry[] table;
    private int size;
    
    /** Create a new HashMap with default initial capacity and load factor. */
    public HashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }
    
    /** 
     * Create a new HashMap with the specified initial capacity and the default
     * load factor. 
     */
    public HashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }
    
    /** 
     * Create a new HashMap with the specified initial capacity and load factor. 
     */
    public HashMap(int initialCapacity, float loadFactor) {
        this.table = new HashMapEntry[initialCapacity];
        this.loadFactor = loadFactor;
    }
    
    /** Create a new HashMap with contents from the specified {@link Map}. */
    public HashMap(Map<? extends K, ? extends V> m) {
        this();
        putAll(m);
    }
    
    /** Returns the capacity of the underlying array. */
    int capacity() {
        return table.length;
    }

    @Override
    public void clear() {
        if (isEmpty()) {
            return;
        }
        size = 0;
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
    }

    @Override
    public boolean containsKey(Object key) {
        int position = (Objects.hashCode(key) & 0x7fffffff) % table.length;
        HashMapEntry<K, V> entry = table[position];
        while (entry != null) {
            if (Objects.equals(entry.key, key)) {
                return true;
            }
            entry = entry.next;
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (HashMapEntry<K, V> entry : table) {
            while (entry != null) {
                if (Objects.equals(entry.value, value)) {
                    return true;
                }
                entry = entry.next;
            }
        }
        return false;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return new MapEntrySet<>(this, iteratorFactory);
    }

    @Override
    public V get(Object key) {
        int position = Objects.hashCode(key) % table.length;
        HashMapEntry<K, V> entry = table[position];
        while (entry != null) {
            if (Objects.equals(entry.key, key)) {
                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Set<K> keySet() {
        return new MapKeySet<>(this, iteratorFactory);
    }

    @Override
    public V put(K key, V value) {
        ensureCapacity(size + 1);
        
        int position = Objects.hashCode(key) % table.length;
        HashMapEntry<K, V> entry = table[position];
        HashMapEntry<K, V> last = null;
        while (entry != null) {
            if (Objects.equals(entry.key, key)) {
                V oldValue = entry.value;
                entry.value = value;
                return oldValue;
            }
            last = entry;
            entry = entry.next;
        }
        if (last == null) {
            table[position] = new HashMapEntry<>(key, value);
        } else {
            last.next = new HashMapEntry<>(key, value);
        }
        size++;
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        ensureCapacity(size + map.size());
        
        Iterator<?> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<K, V> entry = (Map.Entry<K, V>) iter.next();
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public V remove(Object key) {
        int position = Objects.hashCode(key) % table.length;
        HashMapEntry<K, V> entry = table[position];
        HashMapEntry<K, V> last = null;
        while (entry != null) {
            if (Objects.equals(entry.key, key)) {
               if (last == null) {
                   table[position] = entry.next;
               } else {
                   last.next = entry.next;
               }
               size--;
               return entry.value;
            }
            last = entry;
            entry = entry.next;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Collection<V> values() {
        return new MapValueCollection<>(this, iteratorFactory);
    }
    
    private void ensureCapacity(int newSize) {
        if (newSize <= table.length * loadFactor) {
            return;
        }
        int newCapacity = Math.max(table.length * 2, newSize);
        HashMapEntry[] newTable = new HashMapEntry[newCapacity];
        
        for (HashMapEntry<K, V> entry : table) {
            while (entry != null) {
                HashMapEntry<K, V> next = entry.next;
                int newPosition = Objects.hashCode(entry.key) % newCapacity;
                
                entry.next = newTable[newPosition];
                newTable[newPosition] = entry;
                
                entry = next;
            }
        }
        
        this.table = newTable;
    }
    
    static final class HashMapEntry<K, V> implements Entry<K, V> {
        final K key;
        V value;
        HashMapEntry<K, V> next;
        
        HashMapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

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
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }
}
