package com.hopkins.collections.stream;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class SpinedBufferTest {

    @Test
    public void isEmpty() {
        SpinedBuffer<String> buffer = new SpinedBuffer<>();
        assertThat(buffer.isEmpty()).isTrue();

        buffer.add("one");
        assertThat(buffer.isEmpty()).isFalse();

        buffer.clear();
        assertThat(buffer.isEmpty()).isTrue();
    }

    @Test
    public void get() {
        SpinedBuffer<String> buffer = new SpinedBuffer<>();

        buffer.add("zero");
        buffer.add("one");
        buffer.add("two");

        assertThat(buffer.get(0)).isEqualTo("zero");
        assertThat(buffer.get(1)).isEqualTo("one");
        assertThat(buffer.get(2)).isEqualTo("two");
    }

    @Test
    public void largeBuffer() {
        SpinedBuffer<String> buffer = new SpinedBuffer<>();
        for (int i = 0; i < 100; i++) {
            buffer.add("item " + i);
        }
        assertThat(buffer.size()).isEqualTo(100);

        assertThat(buffer.get(0)).isEqualTo("item 0");
        assertThat(buffer.get(25)).isEqualTo("item 25");
        assertThat(buffer.get(99)).isEqualTo("item 99");
    }

    @Test
    public void asArray() {
        SpinedBuffer<String> buffer = new SpinedBuffer<>();
        buffer.add("one");
        buffer.add("two");
        buffer.add("three");

        assertThat(buffer.asArray(String[]::new)).asList().containsExactly("one", "two", "three").inOrder();
    }

    @Test
    public void asArray_withLargeBuffer() {
        int numItems = 1000;
        SpinedBuffer<String> buffer = new SpinedBuffer<>();
        String[] expected = new String[numItems];
        for (int i = 0; i < numItems; i++) {
            String item = "item " + i;
            buffer.add(item);
            expected[i] = item;
        }

        String[] array = buffer.asArray(String[]::new);

        assertThat(array).isEqualTo(expected);
    }
}
