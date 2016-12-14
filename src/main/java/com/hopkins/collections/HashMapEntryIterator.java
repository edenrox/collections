package com.hopkins.collections;

import com.hopkins.collections.HashMap.HashMapEntry;

/**
 *
 */
final class HashMapEntryIterator<K, V> implements Iterator<Map.Entry<K, V>> {
    private final HashMapEntry[] table;
    private int index = -1;
    private HashMapEntry next = null;
    
    public HashMapEntryIterator(HashMapEntry[] table) {
        this.table = table;
        moveToNext();
    }
    
    private void moveToNext() {
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
        HashMapEntry entry = next;
        moveToNext();
        return (Map.Entry<K, V>) entry;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
