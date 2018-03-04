package com.hopkins.collections;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Before;
import org.junit.Test;

public class TreeSetTest {

  private TreeSet<String> set;

  @Before
  public void setup() {
    set = new TreeSet<>();
    set.addAll(Arrays.asList("a", "b", "c", "d", "e"));
  }

  @Test
  public void init_withCollection() {
    set = new TreeSet<>(Arrays.asList("a", "b", "c", "d", "e"));
    assertThat(set.size()).isEqualTo(5);

    set = new TreeSet<>(Collections.<String>emptySet());
    assertThat(set.isEmpty()).isTrue();
  }

  @Test
  public void add() {
    assertThat(set.add("f")).isTrue();
    assertThat(set.add("f")).isFalse();
  }

  @Test
  public void addAll() {
    assertThat(set.addAll(Arrays.asList("a", "b"))).isFalse();
    assertThat(set.addAll(Arrays.asList("a", "f"))).isTrue();
    assertThat(set.contains("f")).isTrue();

    assertThat(set.addAll(Arrays.asList("g", "h", "f"))).isTrue();
    assertThat(set.contains("g")).isTrue();
    assertThat(set.contains("h")).isTrue();
  }

  @Test
  public void clear() {
    set.clear();
    assertThat(set.isEmpty()).isTrue();
    assertThat(set.size()).isEqualTo(0);
  }

  @Test
  public void contains() {
    assertThat(set.contains("a")).isTrue();
    assertThat(set.contains("f")).isFalse();
  }

  @Test
  public void containsAll() {
    assertThat(set.containsAll(Arrays.asList("a"))).isTrue();
    assertThat(set.containsAll(Arrays.asList("a", "b", "c"))).isTrue();
    assertThat(set.containsAll(Arrays.asList("f", "b", "c"))).isFalse();
    assertThat(set.containsAll(Arrays.asList("f"))).isFalse();
  }

  @Test
  public void toArray() {
    assertThat(set.toArray()).asList()
        .containsExactly("a", "b", "c", "d", "e");

    assertThat(set.toArray(new String[0])).asList()
        .containsExactly("a", "b", "c", "d", "e");
  }
}
