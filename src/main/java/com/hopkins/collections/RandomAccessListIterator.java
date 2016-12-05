package com.hopkins.collections;

final class RandomAccessListIterator<E> implements Iterator<E> {
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
    public E next() {
        indexToRemove = index;
        return list.get(index++);
    }

    @Override
    public void remove() {
        if (indexToRemove < 0) {
            throw new IllegalStateException();
        }
        list.remove(indexToRemove);
        indexToRemove = -1;
    }
}
