package com.hopkins.collections;

final class RandomAccessListIterator<E> implements ListIterator<E> {
    private final List<E> list;
    private int index;
    private int indexToRemove = -1;
    
    RandomAccessListIterator(List<E> list) {
        if (list == null) {
            throw new NullPointerException();
        }
        this.list = list;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < list.size();
    }

    @Override
    public boolean hasPrevious() {
        return index > 0;
    }

    @Override
    public E next() {
        indexToRemove = index;
        return list.get(index++);
    }
    
    public E previous() {
        index--;
        indexToRemove = index;
        return list.get(index--);
    }

    @Override
    public int nextIndex() {
        return index;
    }

    @Override
    public int previousIndex() {
        return index - 1;
    }

    @Override
    public void add(E item) {
        list.add(index, item);
        index++;
    }
    
    @Override
    public void set(E item) {
        if (indexToRemove < 0) {
            throw new IllegalStateException();
        }
        list.set(indexToRemove, item);
    }

    @Override
    public void remove() {
        if (indexToRemove < 0) {
            throw new IllegalStateException();
        }
        list.remove(indexToRemove);
        index--;
        indexToRemove = -1;
    }
}
