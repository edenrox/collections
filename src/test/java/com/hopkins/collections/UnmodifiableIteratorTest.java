package com.hopkins.collections;

import static com.google.common.truth.Truth.assertThat;
import org.junit.Before;
import org.junit.Test;

public class UnmodifiableIteratorTest {
    
    private List<String> items;
    private UnmodifiableIterator<String> iterator;
    
    @Before
    public void setUp() {
        items = new ArrayList<>(Arrays.asList("one", "two", "three"));
        iterator = new UnmodifiableIterator<>(items.iterator());
    }
    
    @Test(expected = NullPointerException.class)
    public void init_withNull_throws() {
        iterator = new UnmodifiableIterator<>(null);
    }
    
    @Test
    public void hasNext() {
        assertThat(iterator.hasNext()).isTrue();
        iterator.next();
        assertThat(iterator.hasNext()).isTrue();
        iterator.next();
        iterator.next();
        assertThat(iterator.hasNext()).isFalse();
    }
    
    @Test
    public void hasNext_withEmpty() {
        iterator = new UnmodifiableIterator<>(Collections.<String>emptyIterator());
        assertThat(iterator.hasNext()).isFalse();
    }
    
    @Test
    public void next() {
        assertThat(iterator.next()).isEqualTo("one");
        assertThat(iterator.next()).isEqualTo("two");
        assertThat(iterator.next()).isEqualTo("three");
    }
    
    @Test(expected = UnsupportedOperationException.class)
    public void remove() {
        iterator.next();
        iterator.remove();
    }
}
