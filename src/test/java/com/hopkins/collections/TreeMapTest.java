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
    public void init() {
//        map.put("one", 1);
//        map.put("two", 2);
//        map.put("three", 3);
//        
//        TreeMap<String, Integer> map2 = new TreeMap<>(map);
//        
//        map.remove("one");
//        
//        assertThat(map2.size()).isEqualTo(3);
//        assertThat(map2.containsKey("one")).isTrue();
//        assertThat(map2.containsKey("three")).isTrue();
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
    public void put_remove() {
        assertThat(map.remove("one")).isNull();
        
        map.put("one", 1);
        
        assertThat(map.remove("two")).isNull();
        assertThat(map.remove("one")).isEqualTo(1);
        assertThat(map.isEmpty()).isTrue();
        
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        int value = map.remove("a");
        assertThat(value).isEqualTo(1);
        assertThat(map.size()).isEqualTo(2);
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
    public void containsValue() {
        assertThat(map.containsValue(1)).isFalse();
        
        map.put("one", 1);
        assertThat(map.containsValue(1)).isTrue();
        assertThat(map.containsValue(2)).isFalse();
        
        map.put("two", 2);
        map.put("three", 3);
        
        assertThat(map.containsValue(2)).isTrue();
        assertThat(map.containsValue(3)).isTrue();
        assertThat(map.containsValue(5)).isFalse();
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
