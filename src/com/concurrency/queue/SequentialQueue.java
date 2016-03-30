package com.concurrency.queue;


import java.util.ArrayList;
import java.util.List;


public class SequentialQueue<T> implements Queue<T> {
	//Just a basic squential list queue implementation
	private List<Node<T>> queue;
	
	public SequentialQueue(){
		queue = new ArrayList<Node<T>>();
	}
	
	@Override
	public void add(T element, int score) {
		synchronized(this){
		queue.add(new Node<T>(element, score));
		}
	}

	@Override
	public T removeMin() throws EmptyQueueException{
		synchronized(this){
		if(queue.isEmpty())
			throw new EmptyQueueException();
		
		int index = 0;
		int lowestScore = Integer.MAX_VALUE;
		for(int i = 0; i < queue.size(); i++){
			Node<T> node = queue.get(i);
			if(lowestScore > node.score){
				index = i;
				lowestScore = node.score;
			}
		}
		
		Node<T> node = queue.get(index);
		queue.remove(index);
		return node.value;
		}
	}

}
