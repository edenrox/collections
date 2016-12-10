package com.hopkins.collections;

import java.lang.reflect.Array;
import java.util.Objects;

/** 
 * An implementation of a {@link List} backed by an array that is resized 
 * needed.
 */
public class ArrayList<E> implements List<E>, RandomAccess {
    private static final int DEFAULT_CAPACITY = 10;

    private Object[] data;
    private int size;

    /** 
     * Create a new, empty {@link ArrayList} with the default capacity (10). 
     */
    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /** Create a new, empty {@link ArrayList} with the specified capacity. */
    public ArrayList(int capacity) {
        data = new Object[capacity];
    }
    
    public ArrayList(Collection<? extends E> c) {
        this(Math.max(DEFAULT_CAPACITY, c.size()));
        addAll(c);
    }

    @Override
    public boolean add(E element) {
        ensureCapacity(size+1);
        data[size] = element;
        size++;
        return true;
    }

    @Override
    public void add(int index, E element) {
        RandomAccessListHelper.checkIndex(index, size + 1);
        ensureCapacity(size+1);
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        size++;
    }
    
    @Override
    public boolean addAll(Collection<? extends E> c) {
        ensureCapacity(size + c.size());
        Iterator<? extends E> iter = c.iterator();
        while (iter.hasNext()) {
            add(iter.next());
        }
        return true;
    }
    
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        RandomAccessListHelper.checkIndex(index, size + 1);
        ensureCapacity(size + c.size());
        System.arraycopy(data, index, data, index + c.size(), size - index);
        Iterator<? extends E> iter = c.iterator();
        while (iter.hasNext()) {
            data[index] = iter.next();
            index++;
        }
        size += c.size();
        return true;
    }
    
    protected int capacity() {
        return data.length;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public boolean contains(Object element) {
        return indexOf(element) != INDEX_NOT_FOUND;
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

    public void ensureCapacity(int minCapacity) {
        if (minCapacity <= data.length) {
            return;
        }

        int newCapacity = Math.max(data.length * 2, minCapacity);
        Object[] newData = new Object[newCapacity];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    @Override
    public E get(int index) {
        RandomAccessListHelper.checkIndex(index, size);
        return (E) data[index];
    }

    @Override
    public int indexOf(Object item) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(item, data[i])) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    @Override
    public Iterator<E> iterator() {
        return new RandomAccessListIterator<>(this);
    }

    @Override
    public int lastIndexOf(Object item) {
        for (int i = size-1; i >= 0; i--) {
            if (Objects.equals(item, data[i])) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    @Override
    public E remove(int index) {
        RandomAccessListHelper.checkIndex(index, size);
        Object removed = data[index];
        System.arraycopy(data, index + 1, data, index, size - index);
        size--;
        return (E) removed;
    }

    @Override
    public boolean remove(Object item) {
        int index = indexOf(item);
        if (index == INDEX_NOT_FOUND) {
            return false;
        } else {
            remove(index);
            return true;
        }
    }

    @Override
    public boolean removeAll(Collection<?> items) {
        boolean hasChanged = false;
        Iterator<?> iter = items.iterator();
        while (iter.hasNext()) {
            hasChanged = hasChanged || remove(iter.next());
        }
        return hasChanged;
    }
    
    @Override
    public boolean retainAll(Collection<?> items) {
        boolean hasChanged = false;
        for (int i = size - 1; size >= 0; i--) {
            if (!items.contains(data[i])) {
                remove(i);
                hasChanged = true;
            }
        }
        
        return hasChanged;
    }

    @Override
    public E set(int index, E element) {
        RandomAccessListHelper.checkIndex(index, size);
        Object item = data[index];
        data[index] = element;
        return (E) item;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        System.arraycopy(data, 0, array, 0, size);
        return array;
    }
    
    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            a = (T[]) Array.newInstance(a.getClass().getComponentType(), size);
        }
        System.arraycopy(data, 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    public void trimToSize() {
        if (data.length == size) {
            return;
        }
        Object[] newData = new Object[size];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;       
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ArrayList {")
                .append("capacity: ")
                .append(data.length)
                .append(", size: ")
                .append(size)
                .append(", elements: [");
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(data[i]);
        }
        sb.append("]}");
        return sb.toString();
    }
}
