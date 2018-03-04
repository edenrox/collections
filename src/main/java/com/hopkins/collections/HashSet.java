package com.hopkins.collections;

/**
 * A {@link Set} which is backed by a {@link HashMap}.
 */
public class HashSet<T> implements Set<T> {
  private final HashMap<T, Object> map;

  public HashSet() {
    map = new HashMap<>();
  }

  public HashSet(int initialCapacity) {
    map = new HashMap<>(initialCapacity);
  }

  public HashSet(int initialCapacity, float loadFactor) {
    map = new HashMap<>(initialCapacity, loadFactor);
  }

  public HashSet(Collection<? extends T> c) {
    this();
    addAll(c);
  }

  @Override
  public boolean add(T item) {
    if (map.containsKey(item)) {
      return false;
    }

    map.put(item, null /* value */);
    return true;
  }

  @Override
  public boolean addAll(Collection<? extends T> c) {
    boolean isChanged = false;
    Iterator<? extends T> iter = c.iterator();
    while (iter.hasNext()) {
      isChanged |= add(iter.next());
    }
    return isChanged;
  }

  @Override
  public void clear() {
    map.clear();
  }

  @Override
  public boolean contains(Object item) {
    return map.containsKey(item);
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return map.keySet().containsAll(c);
  }

  @Override
  public boolean isEmpty() {
    return map.isEmpty();
  }

  @Override
  public Iterator<T> iterator() {
    return map.keySet().iterator();
  }

  @Override
  public boolean remove(Object item) {
    if (!map.containsKey(item)) {
      return false;
    }
    map.remove(item);
    return true;
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    return map.keySet().removeAll(c);
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    return map.keySet().retainAll(c);
  }

  @Override
  public int size() {
    return map.size();
  }

  @Override
  public Object[] toArray() {
    return map.keySet().toArray();
  }

  @Override
  public T[] toArray(T[] a) {
    return map.keySet().toArray(a);
  }
}
