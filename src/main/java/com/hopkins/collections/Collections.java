package com.hopkins.collections;

public final class Collections {
    public static final List EMPTY_LIST = 
            unmodifiableList(new FixedSizeList<>(new Object[0]));
    
    public static final <T> boolean addAll(Collection<? super T> c, T... elements) {
        boolean success = true;
        for (T item : elements) {
            success = success && c.add(item);
        }
        return success;
    }
    
    /** Returns a non-modifiable {@link Iterator} which is empty. */
    public static final <T> Iterator<T> emptyIterator() {
        return Collections.<T>emptyList().iterator();
    }
    
    /** Returns a non-modifiable {@link List} which is empty. */
    public static final <T> List<T> emptyList() {
        return (List<T>) EMPTY_LIST;
    }
    
    /** 
     * Set all positions in the specified {@link List} with the specified item. 
     */
    public static <T> void fill(List<? super T> list, T item) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, item);
        }
    }
    
    /** 
     * Returns a non-modifiable {@link List} containing only the specified item. 
     */
    public static <T> List<T> singletonList(T item) {
        return unmodifiableList(FixedSizeList.singletonList(item));
    }
    
    public static <T extends Comparable<? super T>> void sort(List<T> list) {
        sort(list, ComparableComparator.INSTANCE);
    }
    
    public static <T> void sort(List<T> list, Comparator<? super T> c) {
        // TODO(edenrox)
    }
    
    public static void swap(List<?> list, int i, int j) {
        if (i == j) {
            return;
        }
        List utList = (List) list;
        Object o = utList.get(i);
        utList.set(i, utList.get(j));
        utList.set(j, o);
    }
    
    public static <T> List<T> unmodifiableList(List<T> list) {
        return new UnmodifiableList<>(list);
    }
    
    private Collections() { }
}
