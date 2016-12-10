package com.hopkins.collections;

public class Arrays {
    
    private Arrays() { }
    
    /** 
     * Returns a new fixed-size {@link List} that is backed by the specified 
     * array. 
     */
    public static final <T> List<T> asList(T... elements) {
        return new FixedSizeList<>(elements);
    }
    
    /** 
     * Returns a {@link String} representation of the specified array of 
     * {@link Object}s. 
     */
    public static final String toString(Object[] a) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < a.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(a[i]);
        }
        sb.append("]");
        return sb.toString();
    }
}
