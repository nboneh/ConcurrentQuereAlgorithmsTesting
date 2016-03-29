package com.concurrency.queue;
import static org.junit.Assert.*;

import org.junit.Test;



public class QueueTest {
	@Test
	public void testSequential() {
		Queue<Integer> sequentialQueue = new SequentialQueue<Integer>();
		try {
			assertEquals(testQueueSequentially(sequentialQueue), true);
		} catch (EmptyQueueException e) {
			fail("Failed Sequential Test");
		}
	}
	
	public boolean testQueueSequentially(Queue<Integer> queue) throws EmptyQueueException{
		try {
			queue.removeMin();
			return false;
		} catch (EmptyQueueException e) {
			//Queue should be empty so good
		}
		queue.add(5,1);
		if(queue.removeMin() != 5)
			return false;
		
		queue.add(6,4);
		queue.add(9,3);
		queue.add(3,2);
		
		if(queue.removeMin() != 3)
			return false;
		
		if(queue.removeMin() != 9)
			return false;
		
		if(queue.removeMin() != 6)
			return false;
		
		try {
			queue.removeMin();
			return false;
		} catch (EmptyQueueException e) {
			//Queue should be empty so good
		}
		
		return true;
	}

}
