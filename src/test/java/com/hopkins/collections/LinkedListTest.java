package com.hopkins.collections;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {

  private LinkedList<String> list;

  @Before
  public void setup() {
    list = new LinkedList<>();
  }

  @Test
  public void init() {
    list = new LinkedList<>();
    assertThat(list.size()).isEqualTo(0);

    list = new LinkedList<>(Arrays.asList("one", "two", "three"));
    assertThat(list.size()).isEqualTo(3);
    assertThat(list.getFirst()).isEqualTo("one");
    assertThat(list.getLast()).isEqualTo("three");
  }

  @Test
  public void isEmpty() {
    assertThat(list.isEmpty()).isTrue();

    list.add("test");
    assertThat(list.isEmpty()).isFalse();

    list.clear();
    assertThat(list.isEmpty()).isTrue();
  }

  @Test
  public void getFirst_afterAdd_returnsFirst() {
    list.addFirst("first");
    assertThat(list.getFirst()).isEqualTo("first");

    list.addLast("second");
    assertThat(list.getFirst()).isEqualTo("first");

    list.addFirst("more first");
    assertThat(list.getFirst()).isEqualTo("more first");
  }

  @Test(expected = NoSuchElementException.class)
  public void getFirst_whenEmpty_throws() {
    list.getFirst();
  }

  @Test
  public void peek_whenEmpty_returnsNull() {
    assertThat(list.peek()).isNull();
  }

  @Test
  public void peek_returnsFirst() {
    list.add("one");
    list.add("two");
    assertThat(list.peek()).isEqualTo("one");
  }

  @Test(expected = NoSuchElementException.class)
  public void getLast_whenEmpty_throws() {
    list.getFirst();
  }

  @Test
  public void iterator() {
    list.addAll(Arrays.asList("one", "two", "three"));

    Iterator<String> iter = list.iterator();
    assertThat(iter.hasNext()).isTrue();
    assertThat(iter.next()).isEqualTo("one");
    assertThat(iter.next()).isEqualTo("two");
    assertThat(iter.next()).isEqualTo("three");
    assertThat(iter.hasNext()).isFalse();
  }

  @Test
  public void indexOf() {
    list.addAll(Arrays.asList("zero", "one", "two", "three", "zero"));

    assertThat(list.indexOf("two")).isEqualTo(2);
    assertThat(list.indexOf("five")).isEqualTo(-1);
    assertThat(list.indexOf("zero")).isEqualTo(0);
  }

  @Test
  public void lastIndexOf() {
    list.addAll(Arrays.asList("zero", "one", "two", "three", "zero"));

    assertThat(list.lastIndexOf("two")).isEqualTo(2);
    assertThat(list.lastIndexOf("five")).isEqualTo(-1);
    assertThat(list.lastIndexOf("zero")).isEqualTo(4);
  }

  @Test
  public void removeFirst() {
    list.addAll(Arrays.asList("zero", "one", "two", "three"));

    list.removeFirst();
    assertThat(list.size()).isEqualTo(3);
    assertThat(list.element()).isEqualTo("one");

    assertThat(list.removeFirst()).isEqualTo("one");
    assertThat(list.size()).isEqualTo(2);
  }

  @Test
  public void removeLast() {
    list.addAll(Arrays.asList("zero", "one", "two", "three"));

    list.removeLast();
    assertThat(list.size()).isEqualTo(3);
    assertThat(list.getFirst()).isEqualTo("zero");
    assertThat(list.getLast()).isEqualTo("two");

    assertThat(list.removeLast()).isEqualTo("two");
    assertThat(list.size()).isEqualTo(2);
    assertThat(list.getLast()).isEqualTo("one");
  }

  @Test
  public void toArray() {
    list.addAll(Arrays.asList("zero", "one", "two", "three"));

    Object[] array = list.toArray();
    assertThat(array).asList().hasSize(4);
    assertThat(array).asList()
        .containsExactly("zero", "one", "two", "three")
        .inOrder();
  }

  @Test(expected = NoSuchElementException.class)
  public void removeFirst_whenEmpty_throws() {
    list.removeFirst();
  }

  @Test
  public void contains() {
    list.addAll(Arrays.asList("one", "two"));
    assertThat(list.contains("one")).isTrue();
    assertThat(list.contains("none")).isFalse();
  }

  @Test
  public void containsAll() {
    list.addAll(Arrays.asList("one", "two", "three"));
    assertThat(list.containsAll(Arrays.asList("one", "three"))).isTrue();
    assertThat(list.containsAll(Arrays.asList("none", "three"))).isFalse();
  }
}
