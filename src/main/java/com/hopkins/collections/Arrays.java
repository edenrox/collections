package com.hopkins.collections;

import java.lang.reflect.Array;

/**
 * Utility class for working with arrays.
 */
public class Arrays {

  private Arrays() {
  }

  /**
   * Returns a new fixed-size {@link List} that is backed by the specified
   * array.
   */
  public static <T> List<T> asList(T... elements) {
    return new FixedSizeList<>(elements);
  }

  public static int binarySearch(Object[] array, Object key) {
    return binarySearch(array, 0, array.length, key);
  }

  public static int binarySearch(Object[] array, int fromIndex, int toIndex, Object key) {
    return binarySearch(array, 0, array.length, key, ComparableComparator.INSTANCE);
  }

  public static <T> int binarySearch(T[] array, T key, Comparator<? super T> comparator) {
    return binarySearch(array, 0, array.length, key, comparator);
  }

  public static <T> int binarySearch(T[] array, int fromIndex, int toIndex, T key, Comparator<? super T> comparator) {
    if (fromIndex >= toIndex) {
      return List.INDEX_NOT_FOUND;
    }
    int middleIndex = fromIndex + (toIndex - fromIndex) / 2;
    T item = array[middleIndex];
    if (item == null && key == null) {
      return middleIndex;
    } else if (item != null && item.equals(key)) {
      return middleIndex;
    }
    if (comparator.compare(key, item) < 0) {
      return binarySearch(array, fromIndex, middleIndex, key, comparator);
    } else {
      return binarySearch(array, middleIndex + 1, toIndex, key, comparator);
    }
  }

  public static <T> T[] copyOf(T[] original, int newLength) {
    return copyOfRange(original, 0, newLength);
  }

  public static <T> T[] copyOfRange(T[] original, int fromIndex, int toIndex) {
    if (fromIndex < 0 || fromIndex >= original.length) {
      throw new IndexOutOfBoundsException();
    }
    if (fromIndex > toIndex) {
      throw new IllegalArgumentException();
    }
    int newLength = toIndex - fromIndex;
    T[] newArray = (T[]) Array.newInstance(original.getClass().getComponentType(), newLength);
    int copyLength = Math.min(newLength, original.length - fromIndex);
    System.arraycopy(original, fromIndex, newArray, 0, copyLength);
    return newArray;
  }

  public static void fill(Object[] array, Object value) {
    fill(array, 0, array.length, value);
  }

  public static void fill(Object[] array, int fromIndex, int toIndex, Object value) {
    if (fromIndex < 0 || fromIndex > array.length || toIndex > array.length) {
      throw new IndexOutOfBoundsException();
    }
    if (fromIndex > toIndex) {
      throw new IllegalArgumentException();
    }
    for (int i = fromIndex; i < toIndex; i++) {
      array[i] = value;
    }
  }

  /**
   * Returns a {@link String} representation of the specified array of
   * {@link Object}s.
   */
  public static String toString(Object[] a) {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (int i = 0; i < a.length; i++) {
      if (i > 0) {
        sb.append(", ");
      }
      sb.append(a[i]);
    }
    sb.append("]");
    return sb.toString();
  }
}
