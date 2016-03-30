package com.concurrency.queue;

public class ArrayQueue<T> implements Queue<T> {
	int range;
	Stack<T>[] queue;
	
	public ArrayQueue(int range){
		this.range = range;
		queue = (Stack<T>[])new Stack[range];
		for(int i=0; i<range; i++){
			queue[i] = new Stack();
		}
	}
	
	@Override
	public void add(T element, int score) {
		queue[score].push(element);
	}

	@Override
	public T removeMin() throws EmptyQueueException{
		for (int i=0; i<range; i++){
			T item = queue[i].pop();
			if (item != null) return item;
		}
		throw new EmptyQueueException();
	}
}
