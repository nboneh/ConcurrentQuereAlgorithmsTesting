package com.concurrency.queue;

import java.util.ArrayList;
import java.util.List;

public class SequentialQueue<T> implements Queue<T> {
	//Just a basic squential list queue implementation
	private List<T> queue;
	
	public SequentialQueue(){
		queue = new ArrayList<T>();
	}
	@Override
	public void enqueue(T element) {
		queue.add(element);
	}

	@Override
	public T deque() throws EmptyQueueException{
		if(queue.size() <1)
			throw new EmptyQueueException();
		T popElem = queue.get(0);
		queue.remove(0);
		return popElem;
	}

}
