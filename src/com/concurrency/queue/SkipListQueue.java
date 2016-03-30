package com.concurrency.queue;

import com.concurrency.queue.PrioritySkipList.Node;

public class SkipListQueue<T> implements Queue<T> {
	PrioritySkipList<T> skiplist;
	public SkipListQueue() {
		skiplist = new PrioritySkipList<T>();
	}
	public void add(T item, int score) {;
		skiplist.add(item,score);
	}
	public T removeMin() throws EmptyQueueException {
		Node<T> node = skiplist.findAndMarkMin();
		if (node != null) {
			skiplist.remove(node.item);
			return node.item;
		} else{
			throw new EmptyQueueException();
		}
	}
}