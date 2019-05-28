package com.hopkins.collections.stream;

import com.hopkins.collections.List;
import com.hopkins.collections.Map;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class CollectorsTest {

    private Stream<String> names = Stream.of("anna", "bob", "bj", "sarah", "stanislov", "zola");

    @Test
    public void counting() {
        long result = names.collect(Collectors.counting());

        assertThat(result).isEqualTo(6);
    }

    @Test
    public void counting_withEmpty() {
        long result = Stream.empty().collect(Collectors.counting());

        assertThat(result).isEqualTo(0);
    }

    @Test
    public void joining() {
        String result = names.collect(Collectors.joining());

        assertThat(result).isEqualTo("annabobbjsarahstanislovzola");
    }

    @Test
    public void joining_withDelimiter() {
        String result = names.collect(Collectors.joining("; "));

        assertThat(result).isEqualTo("anna; bob; bj; sarah; stanislov; zola");
    }

    @Test
    public void joining_withDelimiter_withEmpty() {
        String result = Stream.<String>empty().collect(Collectors.joining("; "));

        assertThat(result).isEmpty();
    }

    @Test
    public void joining_withDelimiterPrefixAndSuffix() {
        String result = names.collect(Collectors.joining(", ", "array = [", "];"));

        assertThat(result).isEqualTo("array = [anna, bob, bj, sarah, stanislov, zola];");
    }

    @Test
    public void toList() {
        List<String> result = names.collect(Collectors.toList());

        assertThat(result.toArray()).asList()
                .containsExactly("anna", "bob", "bj", "sarah", "stanislov", "zola")
                .inOrder();
    }

    @Test
    public void groupingBy() {
        Map<String, List<String>> result = names.collect(Collectors.groupingBy(item -> item.substring(0, 1)));

        assertThat(result.keySet().toArray()).asList().containsExactly("a", "b", "s", "z");
        assertThat(result.get("a").toArray()).asList().containsExactly("anna");
        assertThat(result.get("b").toArray()).asList().containsExactly("bob", "bj");
        assertThat(result.get("s").toArray()).asList().containsExactly("sarah", "stanislov");
        assertThat(result.get("z").toArray()).asList().containsExactly("zola");
    }
}
