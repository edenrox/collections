package com.hopkins.collections;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Before;
import org.junit.Test;

public class ArraysTest {

  private List<String> list;

  @Before
  public void setup() {
    list = Arrays.asList("one", "two", "three");
  }

  @Test
  public void isEmpty() {
    assertThat(Arrays.asList().isEmpty()).isTrue();
    assertThat(list.isEmpty()).isFalse();
  }

  @Test
  public void size_returnsSize() {
    assertThat(list.size()).isEqualTo(3);

    assertThat(Arrays.asList("one").size()).isEqualTo(1);
  }

  @Test
  public void get_returnsItem() {
    assertThat(list.get(0)).isEqualTo("one");
    assertThat(list.get(1)).isEqualTo("two");

    assertThat(Arrays.asList("bloom").get(0)).isEqualTo("bloom");
  }

  @Test
  public void set_updatesItem() {
    list.set(0, "butter");
    assertThat(list.get(0)).isEqualTo("butter");
  }

  @Test
  public void set_returnsOldItem() {
    assertThat(list.set(0, "butter")).isEqualTo("one");
  }

  @Test(expected = UnsupportedOperationException.class)
  public void add_throws() {
    list.add("four");
  }

  @Test(expected = UnsupportedOperationException.class)
  public void clear_throws() {
    list.clear();
  }

  @Test
  public void toString_returnsExpected() {
    String actual = Arrays.toString(new String[]{"one", "two", "three"});
    assertThat(actual).isEqualTo("[one, two, three]");
  }

  @Test
  public void contains() {
    assertThat(list.contains("one")).isTrue();
    assertThat(list.contains("none")).isFalse();
  }

  @Test
  public void containsAll() {
    assertThat(list.containsAll(Arrays.asList("one", "three"))).isTrue();
    assertThat(list.containsAll(Arrays.asList("none", "three"))).isFalse();
  }

  @Test
  public void indexOf() {
    assertThat(list.indexOf("one")).isEqualTo(0);
    assertThat(list.indexOf("three")).isEqualTo(2);
  }

  @Test
  public void lastIndexOf() {
    list = Arrays.asList("zero", "one", "zero", "one", "zero");
    assertThat(list.lastIndexOf("one")).isEqualTo(3);
    assertThat(list.lastIndexOf("two")).isEqualTo(-1);
    assertThat(list.lastIndexOf("zero")).isEqualTo(4);
  }

  @Test
  public void copyOf_truncates() {
    String[] array = new String[]{"a", "b", "c"};
    assertThat(Arrays.copyOf(array, 2)).asList()
        .containsExactly("a", "b").inOrder();

    assertThat(Arrays.copyOf(array, 1)).asList()
        .containsExactly("a").inOrder();
  }

  @Test
  public void copyOf_padsEnd() {
    String[] array = new String[]{"a", "b", "c"};
    assertThat(Arrays.copyOf(array, 5)).asList()
        .containsExactly("a", "b", "c", null, null).inOrder();
  }

  @Test
  public void copyOfRange() {
    String[] array = new String[]{"a", "b", "c"};
    assertThat(Arrays.copyOfRange(array, 1, 3)).asList()
        .containsExactly("b", "c").inOrder();

    assertThat(Arrays.copyOfRange(array, 2, 5)).asList()
        .containsExactly("c", null, null).inOrder();
  }

  @Test
  public void fill() {
    String[] array = new String[]{"a", "b", "c"};
    Arrays.fill(array, "z");
    assertThat(array).asList().containsExactly("z", "z", "z");
  }

  @Test
  public void fill_withRange() {
    String[] array = new String[]{"a", "b", "c"};
    Arrays.fill(array, 1, 2, "z");
    assertThat(array).asList().containsExactly("a", "z", "c");

    Arrays.fill(array, 1, 3, "f");
    assertThat(array).asList().containsExactly("a", "f", "f");
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void fill_withInvalidRange_throws() {
    String[] array = new String[]{"a", "b", "c"};
    Arrays.fill(array, -1, 2, "z");
  }

  @Test
  public void binarySearch() {
    String[] array = new String[]{"a", "b", "c", "d", "e", "f", "g"};

    assertThat(Arrays.binarySearch(array, "a")).isEqualTo(0);
    assertThat(Arrays.binarySearch(array, "b")).isEqualTo(1);
    assertThat(Arrays.binarySearch(array, "d")).isEqualTo(3);
    assertThat(Arrays.binarySearch(array, "g")).isEqualTo(6);
    assertThat(Arrays.binarySearch(array, "z")).isEqualTo(-1);
    assertThat(Arrays.binarySearch(array, "A")).isEqualTo(-1);
  }

  @Test
  public void binarySearch_withReversComparator() {
    String[] array = new String[]{"g", "f", "e", "d", "c", "b", "a"};

    assertThat(Arrays.binarySearch(array, "a", Collections.reverseOrder()))
        .isEqualTo(6);
    assertThat(Arrays.binarySearch(array, "b", Collections.reverseOrder()))
        .isEqualTo(5);
    assertThat(Arrays.binarySearch(array, "d", Collections.reverseOrder()))
        .isEqualTo(3);
    assertThat(Arrays.binarySearch(array, "g", Collections.reverseOrder()))
        .isEqualTo(0);
    assertThat(Arrays.binarySearch(array, "z", Collections.reverseOrder()))
        .isEqualTo(-1);
    assertThat(Arrays.binarySearch(array, "A", Collections.reverseOrder()))
        .isEqualTo(-1);
  }
}
