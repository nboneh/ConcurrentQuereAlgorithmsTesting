package com.concurrency.queue;

public interface  Queue<T> {

	 public void enqueue(T element);

	 public T deque() throws EmptyQueueException;
}
