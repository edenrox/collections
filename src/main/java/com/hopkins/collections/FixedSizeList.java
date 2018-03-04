package com.hopkins.collections;

import java.lang.reflect.Array;
import java.util.Objects;

/** A fixed size {@link List} implementation backed by an Array. */
final class FixedSizeList<T> implements List<T>, RandomAccess {
    
    static <E> FixedSizeList<E> singletonList(E item) {
        E[] array = (E[]) Array.newInstance(item.getClass(), 1);
        array[0] = item;
        return new FixedSizeList<>(array);
    }
    
    private final T[] data;
    
    FixedSizeList(T[] data) {
        if (data == null) {
            throw new NullPointerException();
        }
        this.data = data;
    }
    
    @Override
    public boolean add(T e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != INDEX_NOT_FOUND;
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
    public T get(int index) {
        return data[index];
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < data.length; i++) {
            if (Objects.equals(data[i], o)) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }
    
    @Override
    public boolean isEmpty() {
        return data.length == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return listIterator();
    }

    @Override
    public ListIterator<T> listIterator() {
        return new RandomAccessListIterator<>(this);
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = data.length - 1; i >= 0; i--) {
            if (Objects.equals(data[i], o)) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
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
    public T set(int index, T element) {
        T item = data[index];
        data[index] = element;
        return item;
    }

    @Override
    public int size() {
        return data.length;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return new RandomAccessSubList<>(this, fromIndex, toIndex);
    }

    @Override
    public Object[] toArray() {
        return data;
    }

    @Override
    public T[] toArray(T[] a) {
        if (a.length < data.length) {
            a = (T[]) Array.newInstance(a.getClass().getComponentType(), data.length);
        }
        System.arraycopy(data, 0, a, 0, data.length);
        if (a.length > data.length) {
            a[data.length] = null;
        }
        return a;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("List {size")
                .append(size())
                .append(", elements: ")
                .append(Arrays.toString(data))
                .append("}");
        return sb.toString();
    }
}
