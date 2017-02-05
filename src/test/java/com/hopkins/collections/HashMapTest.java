package com.hopkins.collections;

import static com.google.common.truth.Truth.assertThat;
import org.junit.Before;
import org.junit.Test;

public class HashMapTest {
    
    private HashMap<String, Integer> map;
    
    @Before
    public void setup() {
        map = new HashMap<>();
    }
    
    @Test
    public void init_withMap() {
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        
        HashMap<String, Integer> map2 = new HashMap<>(map);
        map.remove("one");
        
        assertThat(map2.containsKey("one")).isTrue();
        assertThat(map2.containsKey("three")).isTrue();
        assertThat(map2.containsKey("five")).isFalse();
        assertThat(map2.get("two")).isEqualTo(2);
    }
    
    @Test
    public void size_afterInit() {
        assertThat(map.size()).isEqualTo(0);
    }
    
    @Test
    public void size_afterPut_increases() {
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        
        assertThat(map.size()).isEqualTo(3);
    }
    
    @Test
    public void size_afterPutSameKey_doesNotIncrease() {
        map.put("one", 1);
        map.put("one", 2);
        map.put("one", 3);
        
        assertThat(map.size()).isEqualTo(1);
    }
    
    @Test
    public void size_afterClear_returnsZero() {
        map.put("one", 1);
        map.clear();
        
        assertThat(map.size()).isEqualTo(0);
    }
    
    @Test
    public void isEmpty_afterInit_isTrue() {
        assertThat(map.isEmpty()).isTrue();
    }
    
    @Test
    public void isEmpty_afterPut_isFalse() {
        map.put("one", 1);
        assertThat(map.isEmpty()).isFalse();
    }
    
    @Test
    public void get_afterPut_returnsValue() {
        map.put("one", 1);
        map.put("two", 2);
        
        assertThat(map.get("one")).isEqualTo(1);
        assertThat(map.get("two")).isEqualTo(2);
    }
    
    @Test
    public void get_afterMultiplePut_returnsLastValue() {
        map.put("one", 1);
        map.put("one", 2);
        
        assertThat(map.get("one")).isEqualTo(2);
    }
    
    @Test
    public void put_returnsOldValue() {
        assertThat(map.put("one", 1)).isNull();
        assertThat(map.put("one", 2)).isEqualTo(1);
    }
    
    @Test
    public void get_afterInit_returnsNull() {
        assertThat(map.get("one")).isNull();
    }
    
    @Test
    public void containsKey_afterPut() {
        map.put("one", 1);
        map.put("two", 2);
        
        assertThat(map.containsKey("one")).isTrue();
        assertThat(map.containsKey("two")).isTrue();
        assertThat(map.containsKey("three")).isFalse();
    }
    
    @Test
    public void containsValue() {
        map.put("one", 1);
        map.put("two", 2);
        
        assertThat(map.containsValue(1)).isTrue();
        assertThat(map.containsValue(2)).isTrue();
        assertThat(map.containsValue(3)).isFalse();
    }
    
    @Test
    public void remove_returnsValue() {
        map.put("one", 1);
        map.put("two", 2);
        
        assertThat(map.remove("one")).isEqualTo(1);
        assertThat(map.remove("two")).isEqualTo(2);
        assertThat(map.remove("three")).isNull();
    }
    
    @Test
    public void init_withHighLoadFactor_works() {
        map = new HashMap<>(2 /* initialCapacity */, 4.0F /* loadFactor */);
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        
        assertThat(map.size()).isEqualTo(4);
        assertThat(map.capacity()).isEqualTo(2);
        assertThat(map.get("one")).isEqualTo(1);
        assertThat(map.get("two")).isEqualTo(2);
        assertThat(map.get("three")).isEqualTo(3);
        assertThat(map.get("four")).isEqualTo(4);
        
        assertThat(map.containsKey("one")).isTrue();
        assertThat(map.containsKey("five")).isFalse();
    }
    
    @Test
    public void capacity_afterInit_returnsDefault() {
        assertThat(map.capacity()).isEqualTo(16);
    }
    
    @Test
    public void capacity_afterPut_increases() {
        map = new HashMap<>(2 /* initialCapacity */);
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 2);
        
        assertThat(map.capacity()).isEqualTo(4);
        assertThat(map.size()).isEqualTo(3);
        assertThat(map.get("one")).isEqualTo(1);
        assertThat(map.get("two")).isEqualTo(2);
    }
    
    @Test
    public void keySet_containsKey() {
        map = new HashMap<>(2 /* initialCapacity */);
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 2);
        
        assertThat(map.keySet().contains("one")).isTrue();
        assertThat(map.keySet().contains("bobert")).isFalse();
        assertThat(map.keySet().contains("two")).isTrue();
        assertThat(map.keySet().containsAll(Arrays.asList("one", "two", "three"))).isTrue();
        assertThat(map.keySet().containsAll(Arrays.asList("one", "butter"))).isFalse();
    }
    
    @Test
    public void values_toArray() {
        map = new HashMap<>(2 /* initialCapacity */);
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        
        assertThat(map.values().toArray()).asList().containsExactly(1, 2, 3);
    }
    
    @Test
    public void clear_whenEmpty() {
        map.clear();
        assertThat(map.isEmpty()).isTrue();
    }
    
    @Test
    public void entrySetValue() {
        map.put("one", 1);
        assertThat(map.get("one")).isEqualTo(1);
        
        Map.Entry<String, Integer> entry = map.entrySet().iterator().next();
        entry.setValue(2);
        
        assertThat(map.get("one")).isEqualTo(2);
    }
}
