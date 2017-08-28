package com.hopkins.collections;

/** 
 * An implementation of {@link List} that wraps an underlying {@link List} and
 * does not allow modification.
 */
final class UnmodifiableList<E> implements List<E> {
    private final List<E> list;
    
    UnmodifiableList(List<E> list) {
        if (list == null) {
            throw new NullPointerException();
        }
        this.list = list;
    }
    
    @Override
    public boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public int indexOf(Object item) {
        return list.indexOf(item);
    }
    
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Iterator<E> iterator() {
        return listIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new UnmodifiableListIterator<>(list.listIterator());
    }

    @Override
    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    @Override
    public E remove(int index) {
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
    public E set(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return Collections.unmodifiableList(list.subList(fromIndex, toIndex));
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public E[] toArray(E[] a) {
        return list.toArray(a);
    }
}
