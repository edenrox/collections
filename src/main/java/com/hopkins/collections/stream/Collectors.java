package com.hopkins.collections.stream;

import com.hopkins.collections.*;

import java.util.function.Function;
import java.util.function.Supplier;

public final class Collectors {
    private static final Collector TO_LIST = Collector.of(ArrayList::new, List::add);
    private static final Collector TO_SET = Collector.of(HashSet::new, Set::add);
    private static final Collector JOINING =
            Collector.of(StringBuilder::new, StringBuilder::append, StringBuilder::toString);

    private Collectors() {}

    public static <T> Collector<T, List<T>, List<T>> toList() {
        return TO_LIST;
    }

    public static <T> Collector<T, Collection<T>, Collection<T>> toCollection(Supplier<Collection<T>> supplier) {
        return Collector.of(supplier, Collection::add);
    }

    public static <T> Collector<T, Set<T>, Set<T>> toSet() {
        return TO_SET;
    }

    public static Collector<String, StringBuilder, String> joining() {
        return JOINING;
    }

    public static Collector<String, StringBuilder, String> joining(String delimiter) {
        return Collector.of(StringBuilder::new, StringBuilder::append, StringBuilder::toString);
    }

    public static <T, S> Collector<T, Map<S, List<T>>, Map<S, List<T>>> groupingBy(Function<T, S> groupMapper) {
        return Collector.of(
                HashMap::new,
                (Map<S, List<T>> result, T item) -> {
                    S key = groupMapper.apply(item);
                    if (!result.containsKey(key)) {
                        result.put(key, new ArrayList<>());
                    }
                    result.get(key).add(item);
                });
    }

    public static <T> Collector<T, Long[], Long> counting() {
        return Collector.of(
                () -> new Long[1],
                (arr, item) -> arr[0]++,
                (arr) -> arr[0]);
    }
}
