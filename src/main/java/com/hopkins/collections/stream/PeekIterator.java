package com.hopkins.collections.stream;

import com.hopkins.collections.Iterator;

import java.util.function.Consumer;

final class PeekIterator<T> implements Iterator<T> {
  private final Iterator<T> source;
  private final Consumer<T> consumer;

  PeekIterator(Iterator<T> source, Consumer<T> consumer) {
    this.source = source;
    this.consumer = consumer;
  }

  @Override
  public boolean hasNext() {
    return source.hasNext();
  }

  @Override
  public T next() {
    T item = source.next();
    consumer.accept(item);
    return item;
  }
}
