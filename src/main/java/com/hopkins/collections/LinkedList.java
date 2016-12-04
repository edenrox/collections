package com.hopkins.collections;

import java.lang.reflect.Array;

/** A double linked list. */
public class LinkedList<E> implements List<E>, Queue<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;
    
    public LinkedList() { }
    
    public LinkedList(Collection<? extends E> c) {
        addAll(c);
    }
    
    @Override
    public boolean add(E element) {
        addLast(element);
        return true;
    }
    
    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean addAll(Collection<? extends E> c) {
        Iterator<? extends E> iter = c.iterator();
        while (iter.hasNext()) {
            add(iter.next());
        }
        return true;
    }
    
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }
    
    public void addFirst(E element) {
        Node<E> node = new Node(element);
        size++;
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head = node;
        }
    }
    
    public void addLast(E element) {
        Node<E> node = new Node(element);
        size++;
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            node.prev = tail;
            tail = node;
        }
    }
    
    @Override
    public void clear() {
        head = null;
        tail = null;
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

    @Override
    public E element() {
        return getFirst();
    }
    
    @Override
    public E get(int index) {
        Node<E> node = nodeAt(index);
        return node.element;
    }
    
    public E getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return head.element;
    }
    
    public E getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return tail.element;
    }
    
    @Override
    public int indexOf(Object element) {
        Node<E> cur = head;
        int index = 0;
        while (cur != null) {
            if (cur.element == null) {
                if (element == null) {
                    return index;
                }
            } else if (cur.element.equals(element)) {
                return index;
            }
            cur = cur.next;
            index++;
        }
        return INDEX_NOT_FOUND;
    }
    
    @Override
    public boolean isEmpty() {
        return head == null;
    }
    
    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator<>(head);
    }
    
    @Override
    public int lastIndexOf(Object element) {
        Node<E> cur = tail;
        int index = size - 1;
        while (cur != null) {
            if ((cur.element == null && element == null)
                || (cur.element != null && cur.element.equals(element))) {
                return index;
            }
            cur = cur.prev;
            index--;
        }
        return INDEX_NOT_FOUND;
    }

    @Override
    public boolean offer(E e) {
        return add(e);
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return getFirst();
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        return removeFirst();
    }
    
    @Override
    public E remove(int index) {
        Node<E> node = nodeAt(index);
        removeNode(node);
        return node.element;
    }

    @Override
    public boolean remove(Object o) {
        Node<E> cur = head;
        while (cur != null) {
            if ((cur.element == null && o == null)
                || (cur.element != null && cur.element.equals(o))) {
                removeNode(cur);
                return true;
            }
        }
        return false;
    }

    @Override
    public E remove() {
        return removeFirst();
    }
    
    public E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        E element = head.element;
        removeNode(head);
        return element;
    }
    
    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        E element = tail.element;
        removeNode(tail);
        return element;
    }
    
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean hasChanged = false;
        Iterator<?> iter = c.iterator();
        while (iter.hasNext()) {
            hasChanged = hasChanged || remove(iter.next());
        }
        return hasChanged;
    }
    
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean hasChanged = false;
        Node<E> cur = head;
        while (cur != null) {
            Node<E> next = cur.next;
            if (!c.contains(cur.element)) {
                removeNode(cur);
                hasChanged = true;
            }
            cur = next;
        }
        return hasChanged;
    }
    
    private void removeNode(Node<E> node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        }
        if (head == node) {
            head = node.next;
        }
        if (tail == node) {
            tail = node.prev;
        }
        size--;
    }
    
    @Override
    public E set(int index, E element) {
        Node<E> node = nodeAt(index);
        E oldElement = node.element;
        node.element = element;
        return oldElement;
    }
    
    @Override
    public int size() {
        return size;
    }
    
    private Node<E> nodeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> cur = head;
        int i = 0;
        while (cur != null) {
            if (i == index) {
                return cur;
            }
            cur = cur.next;
            index++;
        }
        throw new IllegalStateException("Node structure corrupt");
    }
    
    @Override
    public Object[] toArray() {
        Object[] a = new Object[size];
        Node<E> cur = head;
        int index = 0;
        while (cur != null) {
            a[index] = cur.element;
            cur = cur.next;
            index++;
        }
        return a;
    }
    
    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            a = (T[]) Array.newInstance(a.getClass().getComponentType(), size);
        }
        Node<E> cur = head;
        int index = 0;
        while (cur != null) {
            a[index] = (T) cur.element;
            cur = cur.next;
            index++;
        }
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LinkedList {")
                .append("size: ")
                .append(size)
                .append(", elements: [");
        Node<E> cur = head;
        while (cur != null) {
            if (cur != head) {
                sb.append(", ");
            }
            sb.append(cur.element);
            cur = cur.next;
        }
        sb.append("}");
        return sb.toString();
    }
    
    /** A double linked list node. */
    static final class Node<E> {
        Node<E> prev;
        Node<E> next;
        E element;
        
        public Node(E element) {
            this.element = element;
        }
    }
}
