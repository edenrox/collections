package com.hopkins.collections;

import static com.google.common.truth.Truth.assertThat;
import org.junit.Before;
import org.junit.Test;

public class TreeMapTest {
    
    private TreeMap<String, Integer> map;
    
    @Before
    public void setup() {
        map = new TreeMap<>();
    }
    
    @Test
    public void size() {
        assertThat(map.size()).isEqualTo(0);
        
        map.put("one", 1);
        assertThat(map.size()).isEqualTo(1);
        
        map.put("two", 2);
        assertThat(map.size()).isEqualTo(2);
        
        map.clear();
        assertThat(map.size()).isEqualTo(0);
    }
    
    @Test
    public void put_returnsOldValue() {
        assertThat(map.put("one", 1)).isNull();
        assertThat(map.put("two", 2)).isNull();
        
        int value = map.put("two", 3);
        assertThat(value).isEqualTo(2);
    }
    
    @Test
    public void containsKey() {
        assertThat(map.containsKey("one")).isFalse();
        
        map.put("one", 1);
        assertThat(map.containsKey("one")).isTrue();
        assertThat(map.containsKey("two")).isFalse();
        
        map.put("two", 2);
        map.put("three", 3);
        
        assertThat(map.containsKey("two")).isTrue();
        assertThat(map.containsKey("five")).isFalse();
    }
    
    @Test
    public void debugString() {
        System.err.println("[[ Empty TreeMap ]]");
        System.err.println(map.debugString());
        
        map.put("a", 1);
        System.err.println("\n[[ Single Item TreeMap ]]");
        System.err.println(map.debugString());
        
        map.put("b", 2);
        System.err.println("\n[[ Two Item TreeMap ]]");
        System.err.println(map.debugString());
        
        map.put("d", 4);
        System.err.println("\n[[ Three Item TreeMap ]]");
        System.err.println(map.debugString());
        
        map.put("c", 3);
        System.err.println("\n[[ Four Item TreeMap ]]");
        System.err.println(map.debugString());
    }
}
