package com.hopkins.collections;

import com.hopkins.collections.LinkedList.Node;

final class LinkedListIterator<E> implements Iterator<E> {
    private Node<E> node;
    
    LinkedListIterator(Node<E> node) {
        this.node = node;
    }

    @Override
    public boolean hasNext() {
        return node != null;
    }

    @Override
    public E next() {
        E element = node.element;
        node = node.next;
        return element;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
