package com.hopkins.collections;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Before;
import org.junit.Test;

public class SingletonSetTest {
  private SingletonSet<String> set;

  @Before
  public void setup() {
    set = new SingletonSet("a");
  }

  @Test
  public void contains() {
    assertThat(set.contains("a")).isTrue();
    assertThat(set.contains("b")).isFalse();
  }

  @Test
  public void containsAll() {
    assertThat(set.containsAll(Arrays.asList("a", "a", "a"))).isTrue();
    assertThat(set.containsAll(Arrays.asList("b", "c", "a"))).isFalse();
  }

  @Test
  public void isEmpty() {
    assertThat(set.isEmpty()).isFalse();
  }

  @Test
  public void size() {
    assertThat(set.size()).isEqualTo(1);
  }

  @Test
  public void toArray() {
    assertThat(set.toArray()).asList().containsExactly("a");

    assertThat(set.toArray(new String[0])).asList().containsExactly("a");

    String[] array = new String[]{"b", "b", "b"};
    set.toArray(array);
    assertThat(array).asList().containsExactly("a", null, "b").inOrder();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void add() {
    set.add("b");
  }
}
