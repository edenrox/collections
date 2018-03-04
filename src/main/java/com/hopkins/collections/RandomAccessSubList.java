package com.hopkins.collections;

import java.util.Objects;

final class RandomAccessSubList<T> implements List<T>, RandomAccess {

  private final List<T> list;
  private int fromIndex;
  private int toIndex;

  RandomAccessSubList(List<T> list, int fromIndex, int toIndex) {
    if (list == null) {
      throw new NullPointerException();
    }
    this.list = list;
    this.fromIndex = fromIndex;
    this.toIndex = toIndex;
  }

  @Override
  public boolean add(T item) {
    list.add(toIndex, item);
    toIndex++;
    return true;
  }

  @Override
  public void add(int index, T element) {
    list.add(fromIndex + index, element);
    toIndex++;
  }

  @Override
  public boolean addAll(Collection<? extends T> c) {
    list.addAll(toIndex, c);
    toIndex += c.size();
    return true;
  }

  @Override
  public boolean addAll(int index, Collection<? extends T> c) {
    list.addAll(toIndex + index, c);
    toIndex += c.size();
    return true;
  }

  @Override
  public T get(int index) {
    return list.get(fromIndex + index);
  }

  @Override
  public int indexOf(Object o) {
    for (int i = 0; i < size(); i++) {
      if (Objects.equals(o, get(i))) {
        return i;
      }
    }
    return INDEX_NOT_FOUND;
  }

  @Override
  public int lastIndexOf(Object o) {
    for (int i = size() - 1; i >= 0; i--) {
      if (Objects.equals(o, get(i))) {
        return i;
      }
    }
    return INDEX_NOT_FOUND;
  }

  @Override
  public ListIterator<T> listIterator() {
    return new RandomAccessListIterator<>(this);
  }

  @Override
  public T remove(int index) {
    T item = list.remove(fromIndex + index);
    toIndex--;
    return item;
  }

  @Override
  public T set(int index, T element) {
    return list.set(fromIndex + index, element);
  }

  @Override
  public List<T> subList(int fromIndex, int toIndex) {
    return new RandomAccessSubList<>(list, this.fromIndex + fromIndex, this.fromIndex + toIndex);
  }

  @Override
  public void clear() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean contains(Object item) {
    return indexOf(item) != INDEX_NOT_FOUND;
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
    return fromIndex == toIndex;
  }

  @Override
  public Iterator<T> iterator() {
    return listIterator();
  }

  @Override
  public boolean remove(Object item) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public int size() {
    return toIndex - fromIndex;
  }

  @Override
  public Object[] toArray() {
    Object[] array = new Object[toIndex - fromIndex];
    for (int i = 0; i < array.length; i++) {
      array[i] = get(i);
    }
    return array;
  }

  @Override
  public T[] toArray(T[] a) {
    throw new UnsupportedOperationException();
  }
}
