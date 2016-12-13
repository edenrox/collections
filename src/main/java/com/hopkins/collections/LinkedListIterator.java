package com.hopkins.collections;

import com.hopkins.collections.LinkedList.Node;

final class LinkedListIterator<E> implements ListIterator<E> {
    private final LinkedList<E> list;
    private Node<E> node;
    private Node<E> nodeToRemove;
    private int index;
    
    LinkedListIterator(LinkedList<E> list, Node<E> head) {
        this.list = list;
        this.node = head;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return node != null;
    }

    @Override
    public boolean hasPrevious() {
        return node != null && node.prev != null;
    }

    @Override
    public E next() {
        E element = node.element;
        nodeToRemove = node;
        node = node.next;
        index++;
        return element;
    }

    @Override
    public E previous() {
        E element = node.prev.element;
        nodeToRemove = node;
        node = node.prev;
        index--;
        return element;
    }

    @Override
    public void remove() {
        if (nodeToRemove == null) {
            throw new IllegalStateException();
        }
        list.removeNode(nodeToRemove);
        nodeToRemove = null;
    }

    @Override
    public void set(E item) {
        if (nodeToRemove == null) {
            throw new IllegalStateException();
        }
        nodeToRemove.element = item;
    }

    @Override
    public void add(E item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int nextIndex() {
        return index;
    }

    @Override
    public int previousIndex() {
        return index - 1;
    }
}
