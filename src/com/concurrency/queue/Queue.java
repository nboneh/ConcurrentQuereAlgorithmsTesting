package com.concurrency.queue;

public interface  Queue<T> {
	public class Node<T> {
		 T value;
		 int score;
		 
		 Node(T value, int score){
			this.value = value;
			this.score = score;
		 }
	}
	 public void add(T element, int score);

	 public T removeMin() throws EmptyQueueException;
}