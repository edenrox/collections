package com.hopkins.collections.stream;

import com.hopkins.collections.Comparator;
import com.hopkins.collections.List;
import com.hopkins.collections.Optional;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class StreamTest {

    @Test
    public void filter() {
        String[] names =
                Stream.of("stan", "martin", "steve", "stanislov", "sarah", "moira", "smeagol")
                    .filter(item -> item.startsWith("m"))
                    .toArray(String[]::new);

        assertThat(names).asList().containsExactly("martin", "moira");
    }

    @Test
    public void filterAndMap() {
        String[] names =
                Stream.of("stan", "martin", "steve", "stanislov", "sarah", "moira", "smeagol")
                        .filter(item -> item.startsWith("m"))
                        .map(item -> "Name: " + item.toUpperCase())
                        .toArray(String[]::new);

        assertThat(names).asList().containsExactly("Name: MARTIN", "Name: MOIRA");
    }

    @Test
    public void sorted() {
        String[] names =
            Stream.of("Zola", "Hank", "Bob", "Melanie", "Roger")
                .sorted()
                .toArray(String[]::new);

        assertThat(names).asList().containsExactly("Bob", "Hank", "Melanie", "Roger", "Zola").inOrder();
    }

    @Test
    public void filterAndSorted() {
        String[] names =
                Stream.of("Zola", "Hank", "Bob", "Melanie", "Roger")
                        .filter(item -> !item.startsWith("R"))
                        .sorted(Comparator.reversedOrder())
                        .toArray(String[]::new);

        assertThat(names).asList().containsExactly( "Zola", "Melanie", "Hank", "Bob").inOrder();
    }

    @Test
    public void mapAndDistinct() {
        String[] letters =
            Stream.of("Bob", "Bethany", "Carl", "Anna", "Albrecht")
                .map(item -> item.substring(0, 1))
                .distinct()
                .map(String::toLowerCase)
                .toArray(String[]::new);
        assertThat(letters).asList().containsNoDuplicates();
        assertThat(letters).asList().containsExactly("a", "b", "c");
    }

    @Test
    public void limitAndSkip() {
        String[] letters =
            Stream.of("a", "b", "c", "d", "e", "f", "g", "h")
                .skip(2)
                .limit(3)
                .toArray(String[]::new);
        assertThat(letters).asList().containsExactly("c", "d", "e");
    }

    @Test
    public void count() {
        long count = Stream.of("a", "b", "c", "d", "e").count();

        assertThat(count).isEqualTo(5);
    }

    @Test
    public void count_withEmpty_returnsZero() {
        long count = Stream.empty().count();

        assertThat(count).isEqualTo(0);
    }

    @Test
    public void findFirst_returnsFirst() {
        Optional<String> first = Stream.of("a", "b").findFirst();

        assertThat(first.isPresent()).isTrue();
        assertThat(first.get()).isEqualTo("a");
    }

    @Test
    public void findFirst_withEmpty_returnsNotPresent() {
        Optional<String> first = Stream.<String>empty().findFirst();

        assertThat(first.isPresent()).isFalse();
    }

    @Test
    public void allMatch() {
        assertThat(Stream.of("anna", "arthur").allMatch(item -> item.startsWith("a"))).isTrue();
        assertThat(Stream.of("anna", "arthur").allMatch(item -> item.endsWith("a"))).isFalse();
    }

    @Test
    public void noneMatch() {
        assertThat(Stream.of("anna", "arthur").noneMatch(item -> item.startsWith("b"))).isTrue();
        assertThat(Stream.of("anna", "bobby").noneMatch(item -> item.endsWith("b"))).isFalse();
    }

    @Test
    public void allMatch_withEmpty_returnsTrue() {
        assertThat(Stream.<String>empty().allMatch(item -> item.startsWith("a"))).isTrue();
    }
}
