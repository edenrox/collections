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
}
