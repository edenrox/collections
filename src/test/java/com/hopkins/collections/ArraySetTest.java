package com.hopkins.collections;

import static com.google.common.truth.Truth.assertThat;
import org.junit.Before;
import org.junit.Test;

public class ArraySetTest {
    
    private ArraySet<String> set;
    
    @Before
    public void setup() {
        set = new ArraySet<>();
    }
    
    @Test
    public void init_deduplicates() {
        set = new ArraySet<>(Arrays.asList("one", "two", "one"));
        
        assertThat(set.toArray()).asList()
                .containsExactly("one", "two");
    }
    
    @Test
    public void size_afterInit_isZero() {
        assertThat(set.size()).isEqualTo(0);
    }
    
    @Test
    public void size_afterAdd_increases() {
        set.add("one");
        assertThat(set.size()).isEqualTo(1);
        
        set.add("two");
        assertThat(set.size()).isEqualTo(2);
    }
    
    @Test
    public void add_withDuplicate_returnsFalse() {
        assertThat(set.add("one")).isTrue();
        assertThat(set.add("one")).isFalse();
    }
    
    @Test
    public void add_withDuplicate_doesNotIncreaseSize() {
        set.add("one");
        set.add("one");
        
        assertThat(set.size()).isEqualTo(1);
    }
    
    @Test
    public void addAll_addsItems() {
        List<String> items = Arrays.asList("one", "two", "three");
        assertThat(set.addAll(items)).isTrue();
        
        assertThat(set.containsAll(items)).isTrue();
    }
    
    @Test
    public void contains() {
        set.add("one");
        set.add("two");
        
        assertThat(set.contains("one")).isTrue();
        assertThat(set.contains("two")).isTrue();
        assertThat(set.contains("three")).isFalse();
    }
}
