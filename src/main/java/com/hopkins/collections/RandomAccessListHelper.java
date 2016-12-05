package com.hopkins.collections;

final class RandomAccessListHelper {

    private RandomAccessListHelper() { }
    
    static void checkIndex(int index, int size) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "index: " + index + " size: " + size);
        }
    }
    
}
