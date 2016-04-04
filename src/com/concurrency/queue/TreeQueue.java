package com.concurrency.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TreeQueue<T> implements Queue<T> {
	int range;
	  
	  List<TreeNode> leaves; // array of tree leaves
	  TreeNode root;     // root of tree
	  public TreeQueue(int logRange) {
	    range = (1 << logRange);
	    leaves = new ArrayList<TreeNode>(range);
	    root = buildTree(logRange, 0);
	  }
	  
	  TreeNode buildTree(int height, int slot) {
	    TreeNode root = new TreeNode();
	    root.counter = new AtomicInteger(0);
	    if (height == 0) { // leaf node?
	      root.bin = new Bin<T>();
	      leaves.add(slot, root);
	    } else {
	      root.left = buildTree(height - 1, 2 * slot);
	      root.right = buildTree(height - 1, (2 * slot) + 1);
	      root.left.parent = root.right.parent = root;
	    }
	    return root;
	  }
	  
	  @Override
	  public void add(T item, int priority) {
	    TreeNode node = leaves.get(priority);
	    node.bin.put(item);
	    while(node != root) {
	      TreeNode parent = node.parent;
	      if (node == parent.left) { // increment if ascending from left
	        parent.counter.getAndIncrement();
	      }
	      node = parent;
	    }
	  }
	  
	  @Override
	  public T removeMin()  throws EmptyQueueException{
	    TreeNode node = root;
	    while(!node.isLeaf()) {
	      if (node.counter.getAndDecrement() > 0 ) {
	        node = node.left;
	      } else {
	        node = node.right;
	      }
	    }
	    if(node.bin.get() == null)
	    	throw new EmptyQueueException();
	    return node.bin.get(); // if null pqueue is empty
	  }
	  public class TreeNode {
	    AtomicInteger counter;    // bounded counter
	    TreeNode parent;    // reference to parent
	    TreeNode right;     // right child
	    TreeNode left;      // left child
	    Bin<T> bin;         // non-null for leaf
	    public boolean isLeaf() {
	      return right == null;
	    }
	  }
}