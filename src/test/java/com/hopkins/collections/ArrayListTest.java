package com.hopkins.collections;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Before;
import org.junit.Test;

public class ArrayListTest {
  private ArrayList<String> list;

  @Before
  public void setUp() {
    list = new ArrayList<>();
  }

  @Test
  public void init() {
    list = new ArrayList<>();
    assertThat(list.isEmpty()).isTrue();
    assertThat(list.capacity()).isEqualTo(10 /* defaultCapacity */);

    list = new ArrayList<>(50);
    assertThat(list.isEmpty()).isTrue();
    assertThat(list.capacity()).isEqualTo(50);

    list = new ArrayList<>(Arrays.asList("zero", "one"));
    assertThat(list.isEmpty()).isFalse();
    assertThat(list.size()).isEqualTo(2);
    assertThat(list.get(0)).isEqualTo("zero");
    assertThat(list.get(1)).isEqualTo("one");
  }

  @Test
  public void add() {
    assertThat(list.add("a")).isTrue();
    assertThat(list.isEmpty()).isFalse();
  }

  @Test
  public void add_withIndex() {
    list.add("a");
    list.add("b");
    list.add(0, "c");
    list.add(0, "d");
    list.add(2, "e");
    list.add(5, "f");

    assertThat(list.toArray()).asList()
        .containsExactly("d", "c", "e", "a", "b", "f")
        .inOrder();
  }

  @Test
  public void addAll() {
    assertThat(list.addAll(Arrays.asList("a", "b"))).isTrue();
    assertThat(list.isEmpty()).isFalse();
    assertThat(list.toArray()).asList().containsExactly("a", "b").inOrder();
  }

  @Test
  public void addAll_withIndex() {
    list.addAll(Arrays.asList("a", "b", "c"));
    list.addAll(0, Arrays.asList("c", "d", "e"));
    list.addAll(1, Arrays.asList("f", "g"));
    assertThat(list.toArray()).asList()
        .containsExactly("c", "f", "g", "d", "e", "a", "b", "c")
        .inOrder();
  }

  @Test
  public void size_returnsZero() {
    assertThat(list.size()).isEqualTo(0);
  }

  @Test
  public void size_afterAdd_returnsSize() {
    list.add("one");
    list.add("two");

    assertThat(list.size()).isEqualTo(2);
  }

  @Test
  public void size_afterClear_returnsZero() {
    list.add("one");
    list.clear();

    assertThat(list.size()).isEqualTo(0);
  }

  @Test
  public void isEmpty_returnsTrue() {
    assertThat(list.isEmpty()).isTrue();
  }

  @Test
  public void isEmpty_afterAdd_returnsFalse() {
    list.add("one");
    assertThat(list.isEmpty()).isFalse();
  }

  @Test
  public void contains_returnsFalse() {
    assertThat(list.contains("test")).isFalse();
  }

  @Test
  public void contains_afterAdd_returnsTrue() {
    String item = "in list";
    list.add(item);

    assertThat(list.contains(item)).isTrue();
  }

  @Test
  public void contains_afterManyAdds_returnsTrue() {
    String item = "in list";
    list.add("here");
    list.add(item);
    list.add("we");

    assertThat(list.contains(item)).isTrue();
  }

  @Test
  public void contains_withMultiple_returnsTrueForAll() {
    String[] items = new String[]{"one", "two", "three", "four"};
    for (String item : items) {
      list.add(item);
    }

    for (String item : items) {
      assertThat(list.contains(item)).isTrue();
    }
  }

  @Test
  public void get_afterAdd_returnsItem() {
    list.add("zero");
    list.add("one");
    list.add("two");

    assertThat(list.get(0)).isEqualTo("zero");
    assertThat(list.get(1)).isEqualTo("one");
    assertThat(list.get(2)).isEqualTo("two");
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void get_badIndex_throws() {
    list.get(0);
  }

  @Test
  public void get_afterSet_returnsNewItem() {
    list.add("one");
    list.add("two");

    assertThat(list.get(0)).isEqualTo("one");

    list.set(0, "blarg");

    assertThat(list.get(0)).isEqualTo("blarg");
  }

  @Test
  public void indexOf_returnsIndex() {
    list.addAll(Arrays.asList("zero", "one", "two", "three", "four"));

    assertThat(list.indexOf("zero")).isEqualTo(0);
  }

  @Test
  public void indexOf_notExists_returnsNegativeOne() {
    assertThat(list.indexOf("five")).isEqualTo(-1);

    list.addAll(Arrays.asList("zero", "one", "two", "three", "four"));
    assertThat(list.indexOf("five")).isEqualTo(-1);
  }

  @Test
  public void indexOf_withMultiple_returnsFirstOffset() {
    list.addAll(Arrays.asList("zero", "one", "zero", "one"));
    assertThat(list.indexOf("zero")).isEqualTo(0);
    assertThat(list.indexOf("one")).isEqualTo(1);
  }

  @Test
  public void lastIndexOf_withMultiple_returnsLastOffset() {
    list.addAll(Arrays.asList("zero", "one", "zero", "one"));
    assertThat(list.lastIndexOf("zero")).isEqualTo(2);
    assertThat(list.lastIndexOf("one")).isEqualTo(3);
  }

  @Test
  public void lastIndexOf_notExist_returnsNotFound() {
    list.addAll(Arrays.asList("a", "b", "c"));
    assertThat(list.lastIndexOf("d")).isEqualTo(List.INDEX_NOT_FOUND);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void remove_invalidIndex_throws() {
    list.remove(5);
  }

  @Test
  public void remove_returnsItem() {
    list.add("one");

    assertThat(list.remove(0)).isEqualTo("one");
  }

  @Test
  public void remove_inMiddle_removes() {
    list.addAll(Arrays.asList("one", "two", "three"));

    list.remove("two");
    assertThat(list.toArray()).asList()
        .containsExactly("one", "three")
        .inOrder();
  }

  @Test
  public void remove_atEnd_removes() {
    list.add("one");
    list.add("two");
    list.add("three");

    list.remove(2);

    assertThat(list.size()).isEqualTo(2);
    assertThat(list.get(1)).isEqualTo("two");
  }

  @Test
  public void remove_nonExistantItem_returnsFalse() {
    list.add("one");
    list.add("two");

    assertThat(list.remove("three")).isFalse();
  }

  @Test
  public void remove_existingItem_returnsTrueAndDecrementsSize() {
    list.addAll(Arrays.asList("one", "two"));

    assertThat(list.remove("two")).isTrue();
    assertThat(list.size()).isEqualTo(1);
  }

  @Test
  public void removeAll() {
    list.addAll(Arrays.asList("a", "b", "c", "d"));
    assertThat(list.removeAll(Arrays.asList("b", "c", "e"))).isTrue();
    assertThat(list.size()).isEqualTo(2);
    assertThat(list.containsAll(Arrays.asList("b", "c")));

    assertThat(list.removeAll(Arrays.asList("f", "g"))).isFalse();
  }

  @Test
  public void toArray() {
    list.add("one");
    list.add("two");

    assertThat(list.toArray()).asList()
        .containsExactly("one", "two")
        .inOrder();
  }

  @Test
  public void toArray_withEmptyArray_returnsArray() {
    list.add("one");
    list.add("two");

    String[] array = list.toArray(new String[0]);
    assertThat(array).hasLength(2);
    assertThat(array).asList().containsExactly("one", "two").inOrder();
  }

  @Test
  public void toArray_withLongArray_populatesArrayWithNull() {
    String[] output = new String[]{"a", "b", "c", "d"};
    list.add("one");
    list.add("two");

    String[] array = list.toArray(output);
    assertThat(array).isSameAs(output);
    assertThat(array).asList()
        .containsExactly("one", "two", null, "d")
        .inOrder();
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
  public void testTrimToSize() {
    list.add("a");
    list.trimToSize();
    assertThat(list.capacity()).isEqualTo(1);

    list.trimToSize();
  }

  @Test
  public void ensureCapacity() {
    list = new ArrayList(4 /* initialCapacity */);
    assertThat(list.capacity()).isEqualTo(4);

    list.ensureCapacity(6);
    assertThat(list.capacity()).isGreaterThan(5);

    list.ensureCapacity(20);
    assertThat(list.capacity()).isEqualTo(20);
  }

  @Test
  public void testToString() {
    list.addAll(Arrays.asList("one", "two", "three"));
    list.trimToSize();

    assertThat(list.toString())
        .isEqualTo("ArrayList {capacity: 3, size: 3, elements: "
            + "[one, two, three]}");
  }
}
