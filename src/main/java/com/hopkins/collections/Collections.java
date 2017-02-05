package com.hopkins.collections;

import java.util.Objects;
import java.util.Random;

/** Utility class for working with {@link Collection}s. */
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
    
    /**
     * Returns the index of the specified key in the provided list.  Uses binary
     * search, so the list must be sorted.
     */
    public static <T> int binarySearch(List<? extends Comparable<? super T>> list, T key) {
        return binarySearch((List<T>) list, key, ComparableComparator.INSTANCE);
    }
    
    /**
     * Returns the index of the specified key in the provided list.  Uses binary
     * search, so the list must be sorted.  Uses the specified comparator to
     * navigate the list.
     */
    public static <T> int binarySearch(List<T> list, T key, Comparator<? super T> c) {
        return binarySearch(list, key, 0, list.size(), c);
    }
    
    private static <T> int binarySearch(List<T> list, T key, int fromIndex, int toIndex, Comparator<? super T> comparator) {
        if (fromIndex >= toIndex) {
            return List.INDEX_NOT_FOUND;
        }
        int middleIndex = fromIndex + (toIndex - fromIndex) / 2;
        T item = list.get(middleIndex);
        if (item == null && key == null) {
            return middleIndex;
        } else if (item != null && item.equals(key)) {
            return middleIndex;
        }
        if (comparator.compare(key, item) < 0) {
            return binarySearch(list, key, fromIndex, middleIndex, comparator);
        } else {
            return binarySearch(list, key, middleIndex + 1, toIndex, comparator);
        }
    }
    
    public static <T> void copy(List<? super T> dest, List<? extends T> src) {
        if (dest.size() < src.size()) {
            throw new IndexOutOfBoundsException();
        }
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
    
    /** 
     * Returns the number of elements in the specified collection that is equal 
     * to {@code item}. 
     */
    public static int frequency(Collection<?> c, Object item) {
        int frequency = 0;
        Iterator<?> iter = c.iterator();
        while (iter.hasNext()) {
            if (Objects.equals(item, iter.next())) {
                frequency++;
            }
        }
        return frequency;
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
    
    public static <T> boolean replaceAll(List<T> list, T oldValue, T newValue) {
        boolean hasChanged = false;
        ListIterator<T> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            if (Objects.equals(oldValue, listIterator.next())) {
                listIterator.set(newValue);
                hasChanged = true;
            }
        }
        return hasChanged;
    }
    
    public static <T> void shuffle(List<T> list) {
        shuffle(list, new Random());
    }
    
    public static <T> void shuffle(List<T> list, Random rnd) {
        for (int i = list.size() - 1; i > 0; i--) {
            int j = rnd.nextInt(i);
            swap(list, i, j);
        }
    }
    
    /** 
     * Returns an immutable {@link List} containing only the specified item.
     */
    public static <T> List<T> singletonList(T item) {
        return unmodifiableList(FixedSizeList.singletonList(item));
    }
    
    /** Returns an immutable {@link Set} containing only the specified item. */
    public static <T> Set<T> singleton(T item) {
        return new SingletonSet<>(item);
    }
    
    /** 
     * Returns an immutable {@link Map} containing only the specified key/value 
     * pair. 
     */
    public static <K, V> Map<K, V> singletonMap(K key, V value) {
        return new SingletonMap<>(key, value);
    }
    
    /** 
     * Sort the specified {@link List} according to the ordering of the items.  
     * 
     * <p>Note: the items must implement {@link Comparable}.
     */
    public static <T extends Comparable<? super T>> void sort(List<T> list) {
        sort(list, ComparableComparator.INSTANCE);
    }
    
    /** 
     * Sort the specified {@link List}, ordering items using the specified 
     * {@link Comparator}.
     */
    public static <T> void sort(List<T> list, Comparator<? super T> c) {
        quickSort(list, 0, list.size(), c);
    }
    
    private static <T> void quickSort(List<T> list, int fromIndex, int toIndex, Comparator<? super T> c) {
        int size = toIndex - fromIndex;
        if (size < 2) {
            // Lists of size 0/1 are sorted by definition
            return;
        }
        if (size < 10) {
            // Use selection sort for small lists
            selectionSort(list, fromIndex, toIndex, c);
            return;
        }
        int pivotIndex = fromIndex + (int) Math.floor(Math.random() * size);
        T pivot = list.get(pivotIndex);
        int minListEndIndex = fromIndex;
        swap(list, pivotIndex, fromIndex);
        for (int i = fromIndex + 1; i < toIndex; i++) {
            T item = list.get(i);
            if (c.compare(pivot, item) > 0) {
                minListEndIndex++;
                swap(list, minListEndIndex, i);
            }
        }
        swap(list, fromIndex, minListEndIndex);
        quickSort(list, fromIndex, minListEndIndex, c);
        quickSort(list, minListEndIndex + 1, toIndex, c);
    }
    
    static <T> void selectionSort(List<T> list, int fromIndex, int toIndex, Comparator<? super T> c) {
        int size = toIndex - fromIndex;
        if (size < 2) {
            // Lists of size 0/1 are sorted by definition
            return;
        }
        for (int i = fromIndex; i < toIndex; i++) {
            T minItem = null;
            int minIndex = -1;
            for (int j = i; j < toIndex; j++) {
                T item = list.get(j);
                if (minItem == null || c.compare(minItem, item) > 0) {
                    minIndex = j;
                    minItem = item;
                }
            }
            swap(list, i, minIndex);
        }
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
