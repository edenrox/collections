package com.hopkins.collections;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Before;
import org.junit.Test;

public class RandomAccessListIteratorTest {
  private ListIterator<String> emptyListIterator;
  private ListIterator<String> listIterator;

  @Before
  public void setup() {
    emptyListIterator = Arrays.<String>asList().listIterator();
    listIterator = Arrays.asList("one", "two", "three").listIterator();
  }

  @Test(expected = NullPointerException.class)
  public void init_withNull_throws() {
    new RandomAccessListIterator<>(null);
  }

  @Test
  public void hasPrevious() {
    assertThat(emptyListIterator.hasPrevious()).isFalse();
    assertThat(listIterator.hasPrevious()).isFalse();

    listIterator.next();
    assertThat(listIterator.hasPrevious()).isTrue();
  }

  @Test
  public void hasNext() {
    assertThat(emptyListIterator.hasNext()).isFalse();
    assertThat(listIterator.hasNext()).isTrue();

    listIterator.next();
    assertThat(listIterator.hasPrevious()).isTrue();
  }

  @Test(expected = NoSuchElementException.class)
  public void previous_withEmpty_throws() {
    emptyListIterator.previous();
  }

  @Test
  public void previous() {
    listIterator.next();
    listIterator.next();
    assertThat(listIterator.previous()).isEqualTo("two");
    assertThat(listIterator.previous()).isEqualTo("one");
  }

  @Test(expected = NoSuchElementException.class)
  public void next_withEmpty_throws() {
    emptyListIterator.next();
  }

  @Test
  public void next_returnsNextItem() {
    assertThat(listIterator.next()).isEqualTo("one");
    assertThat(listIterator.next()).isEqualTo("two");
    assertThat(listIterator.next()).isEqualTo("three");
  }

  @Test
  public void nextIndex() {
    assertThat(listIterator.nextIndex()).isEqualTo(0);
    listIterator.next();
    assertThat(listIterator.nextIndex()).isEqualTo(1);
  }

  @Test
  public void previousIndex() {
    assertThat(listIterator.previousIndex()).isEqualTo(-1);
    listIterator.next();
    assertThat(listIterator.previousIndex()).isEqualTo(0);
  }

  @Test
  public void set() {
    listIterator.next();
    listIterator.set("bob");
    assertThat(listIterator.previous()).isEqualTo("bob");
  }
}
