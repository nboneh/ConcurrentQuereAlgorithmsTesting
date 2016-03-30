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
	
	class Stack<T>{
		Node top;
		
		class Node{
			public T value;
			public Node next;
			Node(T value){
				this.value = value;
				next = null;
			}
		} // end of Node class
		
		public Stack(){
			top = new Node(null);
		}
		
		public void push(T value){
			Node oldTop = top;
			synchronized(oldTop){
				Node node = new Node(value);
				node.next = oldTop;
				top = node;
			}
			return;
		}
		
		public T pop(){
			Node oldTop = top;
			synchronized(oldTop){
				T value = oldTop.value;
				if (value != null) top = oldTop.next;  // Don't change top if there is nothing there
				return value;
			}
		}
		
	}// end of stack class
	
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
