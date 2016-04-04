package com.concurrency.queue;

public class Stack<T>{
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
			// Don't change top if there is nothing there
			if (value != null) top = oldTop.next;
			return value;
		}
	}
	
}// end of stack class