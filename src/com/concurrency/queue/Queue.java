package com.concurrency.queue;

public interface  Queue<T> {
	
	 public void add(T element, int score);

	 public T removeMin() throws EmptyQueueException;
}