package com.hopkins.collections;

public class SparseArray<E> {
  private static final int[] EMPTY_KEYS = new int[0];
  private static final Object[] EMPTY_VALUES = new Object[0];
  private static final int DEFAULT_CAPACITY = 20;

  private int[] keys;
  private Object[] values;
  private int size;

  public SparseArray() {
    this(0);
  }

  public SparseArray(int initialCapacity) {
    if (initialCapacity == 0) {
      keys = EMPTY_KEYS;
      values = EMPTY_VALUES;
      size = 0;
    } else {
      keys = new int[initialCapacity];
      values = new Object[initialCapacity];
      size = 0;
    }
  }

  private void ensureCapacity(int minCapacity) {
    if (keys.length >= minCapacity) {
      return;
    }
    // Figure out the new capacity
    int newCapacity = Math.max(Math.max(minCapacity, keys.length * 2), DEFAULT_CAPACITY);
    int[] oldKeys = keys;
    Object[] oldValues = values;
    // Allocate new arrays
    keys = new int[newCapacity];
    values = new Object[newCapacity];
    // Copy into new arrays
    System.arraycopy(oldKeys, 0, keys, 0, size);
    System.arraycopy(oldValues, 0, values, 0, size);
  }

  public void clear() {
    keys = EMPTY_KEYS;
    values = EMPTY_VALUES;
    size = 0;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int keyAt(int index) {
    RandomAccessListHelper.checkIndex(index, size);
    return keys[index];
  }

  public E valueAt(int index) {
    RandomAccessListHelper.checkIndex(index, size);
    return (E) values[index];
  }

  public E get(int key) {
    return get(key, null);
  }

  public E get(int key, E valueIfNotFound) {
    int index = indexOfKey(key);
    if (index == -1) {
      return valueIfNotFound;
    } else {
      return (E) values[index];
    }
  }

  public int indexOfKey(int key) {
    int index = findBestIndex(key);
    if (index < size && key == keys[index]) {
      return index;
    }
    // Not found
    return -1;
  }

  public int indexOfValue(E value) {
    if (value == null) {
      for (int i = 0; i < size; i++) {
        if (values[i] == null) {
          return i;
        }
      }
    } else {
      for (int i = 0; i < size; i++) {
        if (value.equals(values[i])) {
          return i;
        }
      }
    }
    return -1;
  }

  public void put(int key, E value) {
    int index = findBestIndex(key);
    if (index < size && keys[index] == key) {
      values[index] = value;
    } else {
      ensureCapacity(size + 1);
      int numToShift = size - index;
      System.arraycopy(keys, index, keys, index + 1, numToShift);
      System.arraycopy(values, index, values, index + 1, numToShift);
      keys[index] = key;
      values[index] = value;
      size++;
    }
  }

  private int findBestIndex(int key) {
    if (isEmpty()) {
      return 0;
    }
    int min = 0;
    int max = size;
    int mid;
    while (min < max) {
      mid = min + (max - min) / 2;
      if (key == keys[mid]) {
        return mid;
      } else if (key < keys[mid]) {
        max = mid;
      } else {
        min = mid + 1;
      }
    }
    return min;
  }

  public void remove(int key) {
    int index = indexOfKey(key);
    if (index != -1) {
      removeAt(index);
    }
  }

  public void removeAt(int index) {
    RandomAccessListHelper.checkIndex(index, size);

    int numToShift = size - index - 1;
    System.arraycopy(keys, index + 1, keys, index, numToShift);
    System.arraycopy(values, index + 1, values, index, numToShift);
    size--;
  }

  public void setValueAt(int index, E value) {
    RandomAccessListHelper.checkIndex(index, size);
    values[index] = value;
  }

  public int size() {
    return size;
  }
}
