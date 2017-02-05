package com.hopkins.collections;

import com.hopkins.collections.HashMap.HashMapEntry;

/**
 * An {@link Iterator} over the entries in a {@link HashMap}.
 */
final class HashMapEntryIterator<K, V> implements Iterator<Map.Entry<K, V>> {
    private final HashMap<K, V> map;
    private final HashMapEntry<K, V>[] table;
    private int index = -1;
    private K keyToRemove = null;
    private HashMapEntry<K, V> next = null;
    
    public HashMapEntryIterator(HashMap<K, V> map, HashMapEntry<K, V>[] table) {
        this.map = map;
        this.table = table;
        moveToNext();
    }
    
    private void moveToNext() {
        if (next != null) {
            keyToRemove = next.key;
        }
        if (next != null && next.next != null) {
            next = next.next;
            return;
        }
        index++;
        while (table[index] == null) {
            index++;
            if (index >= table.length) {
                next = null;
                return;
            }
        }
        next = table[index];
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public Map.Entry<K, V> next() {
        if (next == null) {
            throw new NoSuchElementException();
        }
        HashMapEntry<K, V> entry = next;
        moveToNext();
        return entry;
    }

    @Override
    public void remove() {
        if (keyToRemove == null) {
            throw new NoSuchElementException();
        }
        map.remove(keyToRemove);
        keyToRemove = null;
    }
}
