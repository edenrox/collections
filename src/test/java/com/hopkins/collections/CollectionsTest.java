package com.hopkins.collections;

import static com.google.common.truth.Truth.assertThat;
import org.junit.Test;

public class CollectionsTest {
    
    @Test
    public void reverse_reversesList() {
        List<String> list = Arrays.asList("one", "two", "three", "four", "five");
        Collections.reverse(list);
        
        assertThat(list.toArray()).asList()
                .containsExactly("five", "four", "three", "two", "one")
                .inOrder();
    }
    
    @Test
    public void addAll_addsAll() {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, "one", "two", "three");
        
        assertThat(list.toArray()).asList()
                .containsExactly("one", "two", "three")
                .inOrder();
    }
    
    @Test
    public void emptyIterator() {
        assertThat(Collections.emptyIterator().hasNext()).isFalse();
    }
    
    @Test(expected = NoSuchElementException.class)
    public void emptyIterator_next_throws() {
        Collections.emptyIterator().next();
    }
    
    @Test
    public void emptySet() {
        Set<String> emptySet = Collections.emptySet();
        assertThat(emptySet.isEmpty()).isTrue();
        assertThat(emptySet.size()).isEqualTo(0);
        assertThat(emptySet.contains("a")).isFalse();
        assertThat(emptySet.containsAll(Arrays.asList("a", "b"))).isFalse();
        assertThat(emptySet.iterator().hasNext()).isFalse();
        assertThat(emptySet.toArray()).hasLength(0);
    }
    
    @Test
    public void emptyMap() {
        Map<String, Integer> emptyMap = Collections.emptyMap();
        assertThat(emptyMap.isEmpty()).isTrue();
        assertThat(emptyMap.size()).isEqualTo(0);
        assertThat(emptyMap.containsKey("test")).isFalse();
        assertThat(emptyMap.entrySet().isEmpty()).isTrue();
        assertThat(emptyMap.keySet().isEmpty()).isTrue();
        assertThat(emptyMap.values().isEmpty()).isTrue();
        assertThat(emptyMap.get("test")).isNull();
    }
    
    @Test
    public void fill_setsAllElements() {
        List<String> list = Arrays.asList("one", "two", "three", "four", "five");
        Collections.fill(list, "baba");
        
        assertThat(list.toArray()).asList()
                .containsExactly("baba", "baba", "baba", "baba", "baba");
    }
    
    @Test
    public void reverseOrder_returnsOppositeOfComparable() {
        assertThat("a".compareTo("b")).isLessThan(0);
        assertThat(Collections.reverseOrder().compare("a", "b"))
                .isGreaterThan(0);
    }
    
    @Test
    public void singletonList_get_returnsItem() {
        List<String> list = Collections.singletonList("test");
        
        assertThat(list.size()).isEqualTo(1);
        assertThat(list.get(0)).isEqualTo("test");
    }
    
    @Test(expected = UnsupportedOperationException.class)
    public void singletonList_set_throws() {
        Collections.singletonList("test").set(0, "bob");
    }
    
    @Test
    public void swap() {
        List<String> list = Arrays.asList("a", "b", "c", "d", "e");
        
        Collections.swap(list, 0, 0);
        assertThat(list.get(0)).isEqualTo("a");
        
        Collections.swap(list, 0, 3);
        assertThat(list.get(0)).isEqualTo("d");
        assertThat(list.get(3)).isEqualTo("a");
    }
    
    @Test
    public void selectionSort() {
        List<String> list = Arrays.asList("e", "z", "c", "g", "a", "y", "h", "l");
        Collections.selectionSort(list, 0, list.size(), ComparableComparator.INSTANCE);
        
        assertThat(list.toArray()).asList()
                .containsExactly("a", "c", "e", "g", "h", "l", "y", "z")
                .inOrder();
        
        list = Arrays.asList("a", "c", "e", "g", "h", "l", "y", "z");
        Collections.selectionSort(list, 0, list.size(), ComparableComparator.INSTANCE);
        
        assertThat(list.toArray()).asList()
                .containsExactly("a", "c", "e", "g", "h", "l", "y", "z")
                .inOrder();
    }
    
    @Test
    public void sort() {
        List<String> list = new ArrayList<>(100);
        
        // Build a random list of 100 Strings
        for (int i = 0; i < 100; i++) {
            list.add(String.valueOf('a' + (char) Math.floor(26 * Math.random())));
        }
        
        // Sort the list
        Collections.sort(list);
        
        String lastItem = list.get(0);
        for (int i = 1; i < 100; i++) {
            String item = list.get(i);
            assertThat(lastItem).isAtMost(item);
            lastItem = item;
        }
    }
}
