package com.hopkins.collections;

public class Arrays {
    
    public static final <T> List<T> asList(T... elements) {
        return new FixedSizeList<>(elements);
    }
    
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
