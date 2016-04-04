package com.concurrency.queue;

public class ArrayQueue<T> implements Queue<T> {
	 int range;
	  Bin<T>[] pqueue; 
	  public ArrayQueue(int range) {
	    this.range = range;
	    pqueue = (Bin<T>[])new Bin[range]; 
	    for (int i = 0; i < pqueue.length; i++){
	      pqueue[i] = new Bin<T>();
	    }
	  }
	  
	  @Override
	  public void add(T item, int key) {
	    pqueue[key].put(item);
	  }
	  
	  @Override
	  public T removeMin() throws EmptyQueueException{
	    for (int i = 0; i < range; i++) { 
	       T item = pqueue[i].get();
	       if (item != null) {
	         return item;
	       }
	    }
	    throw new EmptyQueueException(); 
	  }

}
