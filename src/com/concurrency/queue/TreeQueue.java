package com.concurrency.queue;

import java.util.ArrayList;
import java.util.List;

public class TreeQueue<T> implements Queue<T> {
	int range;
	List<TreeNode> leaves;
	TreeNode root;
	
	class TreeNode<T> {
		int counter;
		TreeNode parent, right, left;
		Stack<T> stack;
		
		public TreeNode(){
			parent = null;
			right = null;
			left = null;
		}
		
		public boolean isLeaf(){
			return (right==null);
		}
	}
	
	public TreeQueue(int logRange){
		range = range;
		
		leaves = new ArrayList<TreeNode>(range);
		root = new TreeNode();
		root.counter = 0;
		// create tree with a layer of range # of leafs
		// all counters set to 0 in beginning
	}
	
	@Override
	public void add(T element, int score) {
		TreeNode node = leaves.get(score);
		node.stack.push(element);
		while(node != root){
			TreeNode parent = node.parent;
			if (node == parent.left){
				parent.counter++;
			}
			node = parent;
		}
	}

	@Override
	public T removeMin() throws EmptyQueueException {
		TreeNode node = root;
		while(!node.isLeaf()){
			node.counter--;
			if((node.counter++) > 0){
				node = node.left;
			}
			else {
				node = node.right;
			}
		}
		T value = (T) node.stack.pop();
		if (value != null) return value;
		else throw new EmptyQueueException();
	}
	
}