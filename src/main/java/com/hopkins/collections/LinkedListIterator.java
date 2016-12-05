package com.hopkins.collections;

import com.hopkins.collections.LinkedList.Node;

final class LinkedListIterator<E> implements Iterator<E> {
    private final LinkedList<E> list;
    private Node<E> node;
    private Node<E> nodeToRemove;
    
    LinkedListIterator(LinkedList<E> list, Node<E> head) {
        this.list = list;
        this.node = head;
    }

    @Override
    public boolean hasNext() {
        return node != null;
    }

    @Override
    public E next() {
        E element = node.element;
        nodeToRemove = node;
        node = node.next;
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
}
