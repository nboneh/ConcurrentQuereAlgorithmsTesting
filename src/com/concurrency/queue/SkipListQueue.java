package com.concurrency.queue;

import com.concurrency.queue.PrioritySkipList.Node;

public class SkipListQueue<T> implements Queue<T>{
  PrioritySkipList<T> skiplist;
  public SkipListQueue() {
    skiplist = new PrioritySkipList<T>();
  }
  
  @Override
  public void add(T item, int priority)  {
    Node<T> node = (Node<T>)new Node(item, priority);
    skiplist.add(node);
  }

  @Override
  public T removeMin() throws EmptyQueueException{
    Node<T> node = skiplist.findAndMarkMin();
    if (node != null) {
      skiplist.remove(node);
      return node.item;
    } else{
       throw new EmptyQueueException();
    }
  }
  
}