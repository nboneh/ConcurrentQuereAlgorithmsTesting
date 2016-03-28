package com.concurrency.queue;
import static org.junit.Assert.*;

import org.junit.Test;



public class QueueTest {
	@Test
	public void testSequential() {
		SequentialQueue<Integer> sequentialQueue = new SequentialQueue<Integer>();
		assertEquals(testQueue(sequentialQueue), true);
	}
	
	public boolean testQueue(Queue<?> queue){
		return false;
	}

}
