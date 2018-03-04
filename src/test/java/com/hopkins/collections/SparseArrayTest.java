package com.hopkins.collections;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class SparseArrayTest {
  private final int KEY = 12;
  private final String VALUE = "test";

  private SparseArray<String> empty;
  private SparseArray<String> single;

  @Before
  public void setup() {
    empty = new SparseArray<>(0);
    single = new SparseArray<>(1);
    single.put(KEY, VALUE);
  }

  @Test
  public void isEmpty_withEmpty_returnsTrue() {
    assertThat(empty.isEmpty()).isTrue();
  }

  @Test
  public void isEmpty_withNonEmpty_returnsFalse() {
    assertThat(single.isEmpty()).isFalse();
  }

  @Test
  public void isEmpty_afterClear_isTrue() {
    single.clear();

    assertThat(single.isEmpty()).isTrue();
  }

  @Test
  public void get_withExistingKey_returnsValue() {
    assertThat(single.get(KEY)).isEqualTo(VALUE);
  }

  @Test
  public void get_withNonExistingKey_returnsNull() {
    assertThat(single.get(1)).isNull();
  }

  @Test
  public void put_withExistingKey_doesNotIncreaseSize() {
    single.put(KEY, "other value");

    assertThat(single.size()).isEqualTo(1);
  }

  @Test
  public void put_insertsInOrder() {
    empty.put(1, "one");
    empty.put(3, "three");
    empty.put(5, "five");
    empty.put(2, "two");
    empty.put(0, "zero");
    empty.put(4, "four");

    assertThat(empty.getKeys()).asList().containsExactly(0, 1, 2, 3, 4, 5).inOrder();
    assertThat(empty.getValues()).asList().containsExactly("zero", "one", "two", "three", "four", "five").inOrder();
  }

  @Test
  public void remove_withExistingKey_decrementsSize() {
    single.remove(KEY);

    assertThat(single.size()).isEqualTo(0);
  }

  @Test
  public void remove_withNonExistingKey_doesNotChangeSize() {
    single.remove(4);

    assertThat(single.size()).isEqualTo(1);
  }

  @Test
  public void putAndRemove() {
    empty.put(1, "one");
    empty.put(3, "three");
    empty.put(5, "five");
    empty.remove(3);
    empty.put(0, "zero");
    empty.remove(1);

    assertThat(empty.getKeys()).asList().containsExactly(0, 5).inOrder();
    assertThat(empty.getValues()).asList().containsExactly("zero", "five").inOrder();
  }
}
