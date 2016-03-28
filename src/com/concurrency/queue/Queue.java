package com.concurrency.queue;

import java.util.ArrayList;
import java.util.List;

public abstract class Queue<T> {
	
	List<T> queue;
	
	Queue(){
		queue = new ArrayList<T>();
	}

	abstract void enqueue(T element);
	
	abstract T deque();
}
