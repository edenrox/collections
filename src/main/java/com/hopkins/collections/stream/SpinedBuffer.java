package com.hopkins.collections.stream;

import com.hopkins.collections.Arrays;

import java.util.function.IntFunction;

final class SpinedBuffer<T> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final int DEFAULT_SPINE_CAPACITY = 8;
    private static final int MAX_CHUNK_SIZE = 1 << 16;

    private int size;
    private T[][] spineArray;
    private int spineIndex;

    private T[] curChunk;
    private int elementIndex;

    public SpinedBuffer() {
        this(DEFAULT_CAPACITY);
    }

    public SpinedBuffer(int initialCapacity) {
        curChunk = (T[]) new Object[initialCapacity];
    }

    public void add(T item) {
        ensureHasCapacity();
        curChunk[elementIndex] = item;
        elementIndex++;
        size++;
    }

    public int size() {
        return size;
    }

    private int capacity() {
        if (spineIndex == 0) {
            return curChunk.length;
        } else {
            int capacity = 0;
            for (int i = 0; i < spineIndex; i++) {
                capacity += spineArray[i].length;
            }
            capacity += curChunk.length;
            return capacity;
        }
    }

    private void ensureHasCapacity() {
        int existingCapacity = capacity();
        if (size < existingCapacity) {
            return;
        }
        if (spineIndex == 0) {
            // Inflate the spine array
            spineArray = (T[][]) new Object[DEFAULT_SPINE_CAPACITY][];
        } else if (spineIndex == spineArray.length) {
            // Expand the spine array
            spineArray = Arrays.copyOf(spineArray, spineArray.length * 2);
        }
        int newChunkCapacity = Math.min(curChunk.length * 2, MAX_CHUNK_SIZE);

        // Copy curChunk into spineArray
        spineArray[spineIndex++] = curChunk;

        // Allocate a new chunk
        curChunk = (T[]) new Object[newChunkCapacity];
        elementIndex = 0;
    }

    public void clear() {
        elementIndex = 0;
        size = 0;
        if (spineIndex != 0) {
            curChunk = spineArray[0];
            spineArray = null;
            spineIndex = 0;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = 0; i < spineIndex; i++) {
            if (index < spineArray[i].length) {
                return spineArray[i][index];
            } else {
                index -= spineArray[i].length;
            }
        }
        return curChunk[index];
    }

    public <A> A[] asArray(IntFunction<A[]> generator) {
        A[] destArray = generator.apply(size());
        if (spineIndex == 0) {
            System.arraycopy(curChunk, 0, destArray, 0, size);
        } else {
            int index = 0;
            for (int i = 0; i < spineIndex; i++) {
                System.arraycopy(spineArray[i], 0, destArray, index, spineArray[i].length);
                index += spineArray[i].length;
            }
            System.arraycopy(curChunk, 0, destArray, index, elementIndex);
        }
        return destArray;
    }
}
