package com.hopkins.collections;

public final class Collections {
    public static final List EMPTY_LIST = 
            unmodifiableList(new FixedSizeList<>(new Object[0]));
    public static final Map EMPTY_MAP = new EmptyMap();
    public static final Set EMPTY_SET = new EmptySet();
    public static final Iterator EMPTY_ITERATOR = new EmptyIterator();
    
    private static final Comparator REVERSE_COMPARATOR = 
            new ReverseComparator(ComparableComparator.INSTANCE);
    
    public static <T> boolean addAll(Collection<? super T> c, T... elements) {
        boolean success = true;
        for (T item : elements) {
            success = success && c.add(item);
        }
        return success;
    }
    
    public static <T> void copy(List<? super T> dest, List<? extends T> src) {
        for (int i = 0; i < src.size(); i++) {
            dest.set(i, src.get(i));
        }
    }
    
    /** Returns a non-modifiable {@link Iterator} which is empty. */
    public static <T> Iterator<T> emptyIterator() {
        return (Iterator<T>) EMPTY_ITERATOR;
    }
    
    /** Returns a non-modifiable {@link List} which is empty. */
    public static <T> List<T> emptyList() {
        return (List<T>) EMPTY_LIST;
    }
    
    public static <K, V> Map<K, V> emptyMap() {
        return (Map<K, V>) EMPTY_MAP;
    }
    
    public static <T> Set<T> emptySet() {
        return (Set<T>) EMPTY_SET;
    }
    
    /** 
     * Set all positions in the specified {@link List} with the specified item. 
     */
    public static <T> void fill(List<? super T> list, T item) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, item);
        }
    }
    
    public static void reverse(List<?> list) {
        int size = list.size();
        for (int i = 0; i < size / 2; i++) {
            int j = size - i - 1;
            swap(list, i, j);
        }
    }
    
    /** Returns a {@link Comparator} that is the reverse of the */
    public static <T> Comparator<T> reverseOrder() {
        return REVERSE_COMPARATOR;
    }
    
    public static <T> Comparator<T> reverseOrder(Comparator<T> comparator) {
        return new ReverseComparator<>(comparator);
    }
    
    public static <T> T max(Collection<T> c) {
        return max(c, (Comparator<T>) ComparableComparator.INSTANCE);
    }
    
    public static <T> T max(Collection<T> c, Comparator<T> comparator) {
        T max = null;
        Iterator<T> iter = c.iterator();
        while (iter.hasNext()) {
            T item = iter.next();
            if (max == null || comparator.compare(max, item) < 0) {
                max = item;
            }
        }
        return max;
    }
    
    public static <T> T min(Collection<T> c) {
        return min(c, (Comparator<T>) ComparableComparator.INSTANCE);
    }
    
    public static <T> T min(Collection<T> c, Comparator<T> comparator) {
        return max(c, reverseOrder(comparator));
    }
    
    /** 
     * Returns a non-modifiable {@link List} containing only the specified item. 
     */
    public static <T> List<T> singletonList(T item) {
        return unmodifiableList(FixedSizeList.singletonList(item));
    }
    
    public static <T> Set<T> singleton(T item) {
        Set<T> set = new ArraySet<>();
        set.add(item);
        return unmodifiableSet(set);
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
    
    public static <K, V> Map<K, V> unmodifiableMap(Map<K, V> map) {
        return new UnmodifiableMap<>(map);
    }
    
    public static <T> Set<T> unmodifiableSet(Set<T> set) {
        return new UnmodifiableSet<>(set);
    }
    
    private Collections() { }
}
