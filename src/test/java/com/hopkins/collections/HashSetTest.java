package com.hopkins.collections;

import static com.google.common.truth.Truth.assertThat;
import org.junit.Before;
import org.junit.Test;

public class HashSetTest {
    
    private HashSet<String> set;
    
    @Before
    public void setup() {
        set = new HashSet<>();
        set.add("one");
        set.add("two");
    }
    
    @Test
    public void init() {
        set = new HashSet<>(Arrays.asList("a", "b", "c"));
        assertThat(set.size()).isEqualTo(3);
        assertThat(set.contains("a")).isTrue();
    }
    
    @Test
    public void isEmpty() {
        assertThat(set.isEmpty()).isFalse();    
        assertThat(new HashSet<String>().isEmpty()).isTrue();
    }
    
    @Test
    public void size() {
        assertThat(set.size()).isEqualTo(2);
        set.add("three");
        assertThat(set.size()).isEqualTo(3);
    }
    
    @Test
    public void contains() {
        assertThat(set.contains("one")).isTrue();
        assertThat(set.contains("two")).isTrue();
        assertThat(set.contains("three")).isFalse();
    }
    
    @Test
    public void add() {
        assertThat(set.add("one")).isFalse();
        assertThat(set.add("three")).isTrue();
    }
    
    @Test
    public void addAll() {
        set.addAll(Arrays.asList("three", "four", "five"));
        
        assertThat(set.size()).isEqualTo(5);
        assertThat(set.contains("four")).isTrue();
    }
    
    @Test
    public void containsAll() {
        set.addAll(Arrays.asList("three", "four", "five"));
        
        assertThat(set.containsAll(Arrays.asList("one", "five"))).isTrue();
        assertThat(set.containsAll(Arrays.asList("six", "one"))).isFalse();
    }
    
    @Test
    public void clear() {
        set.clear();
        assertThat(set.isEmpty()).isTrue();
        assertThat(set.size()).isEqualTo(0);
        
        set.addAll(Arrays.asList("a", "b", "c"));
        set.clear();
        assertThat(set.isEmpty()).isTrue();
    }
    
    @Test
    public void remove() {
        assertThat(set.remove("one")).isTrue();
        
        assertThat(set.contains("one")).isFalse();
        assertThat(set.size()).isEqualTo(1);
        
        assertThat(set.remove("one")).isFalse();
    }
    
    @Test
    public void removeAll() {
        assertThat(set.removeAll(Arrays.asList("a", "b"))).isFalse();
        assertThat(set.removeAll(Arrays.asList("one", "five"))).isTrue();
        assertThat(set.size()).isEqualTo(1);
    }
    
    @Test
    public void iterator() {
        Iterator<String> iterator = set.iterator();
        
        assertThat(iterator.hasNext()).isTrue();
        iterator.next();
        assertThat(iterator.hasNext()).isTrue();
        iterator.next();
        assertThat(iterator.hasNext()).isFalse();
    }
    
    @Test
    public void toArray() {
        assertThat(set.toArray()).asList().containsExactly("one", "two");
        
        String[] array = new String[] {"a","b","c","d"};
        set.toArray(array);
        assertThat(array[2]).isNull();
    }
}
