package com.concurrency.queue;
import static org.junit.Assert.*;

import org.junit.Test;


public class QueueTest {
	private static final int NUM_OF_THREADS = 512;
	@Test
	public void testSequential() {
		Queue<Integer> sequentialQueue = new SequentialQueue<Integer>();
		try{
			testQueueSequentially(sequentialQueue);
		} catch (EmptyQueueException e) {
			fail("Expected non empty queue, sequential ");
		}
	}
	
	@Test
	public void testArrayQueue(){
		testConcurrentQueue(new ArrayQueue<Integer>(NUM_OF_THREADS+10));
	}
	
	@Test
	public void testTreeQueue(){
		 testConcurrentQueue(new TreeQueue<Integer>( (int)(Math.log(NUM_OF_THREADS) / Math.log(2) +1)));
	}
	
	@Test
	public void testHeapQueue(){
		testConcurrentQueue(new HeapQueue<Integer>(NUM_OF_THREADS));
	}
	
	@Test
	public void testSkipListQueue(){
		 testConcurrentQueue( new SkipListQueue<Integer>());
	}
	
	public void testConcurrentQueue(Queue<Integer> queue){
		try{
			testQueueSequentially(queue);
		} catch (EmptyQueueException e) {
			fail("Expected non empty queue, sequential ");
		}
		
		try {
			testQueueConcurrently(queue);
		} catch (EmptyQueueException e) {
			fail("Expected non empty queue,concurrent ");
		} catch (InterruptedException e) {
			fail("Threads were interrupted ");
		}
	}

	public void testQueueSequentially(Queue<Integer> queue) throws EmptyQueueException{
		try {
			queue.removeMin();
			fail("Empty queue did not throw exception");
		} catch (EmptyQueueException e) {
			//Queue should be empty so good
		}
		queue.add(5,1);
		if(queue.removeMin() != 5)
			fail("Queue did not return 5");
		
		queue.add(6,4);
		queue.add(9,3);
		queue.add(3,2);

		if(queue.removeMin() != 3)
			fail("Queue did not return 3");

		if(queue.removeMin() != 9)
			fail("Queue did not return 9");

		if(queue.removeMin() != 6)
			fail("Queue did not return 6");
		try {
			queue.removeMin();
			fail("Expected empty queue");
		} catch (EmptyQueueException e) {
			//Queue should be empty so good
		}
	}
	
	abstract class TestThread implements Runnable{
		boolean threadPassed;
		 int index;
		
		TestThread(int index){
			this.index = index;
			threadPassed = true;
		}
	}

	public boolean testQueueConcurrently(Queue<Integer> queue) throws EmptyQueueException, InterruptedException{
		//Have to make a lot of threads otherwise passes a sequential queue
		//Watch out can still pass sometimes by luck, but only guaranteed to pass if concurrently correct
		int numOfThreads = NUM_OF_THREADS;
		Thread threads[] = new Thread[NUM_OF_THREADS];
		
		int midIndex = (numOfThreads/2);
		TestThread rMid = null;
		for(int i = 0; i < numOfThreads; i++){
			//Create loop
			TestThread r = new TestThread(i){
				@Override
				public void run() {
					if(index == midIndex){
						//Thread in the middle enques lowest score checks that remove min 
						//returns that value
						queue.add(150, 0);
						try{
							if(queue.removeMin() !=150 ){
								threadPassed = false;
							}
						} catch (EmptyQueueException e) {
							threadPassed = false;
						}
					}
					//All threads add normal values
					queue.add(index,index+1);
				}
			};
			Thread t = new Thread(r);
			threads[i] = t;
			if(i == midIndex){
				rMid = r;
			}
		}
		for(int i = 0; i < numOfThreads; i++){
			//start loop
			threads[i].start();
		}
		
		for(int i = 0; i < numOfThreads; i++){
			//wait loop
			threads[i].join();
		}
		
		if(!rMid.threadPassed ){
			fail("Middle thread test failed");
		}

		//Testing that all threads added to the queue correctly
		for(int i = 0; i < numOfThreads; i++){
			if( i != queue.removeMin())
				fail("Excpect value: " + i);
		}

		try {
			queue.removeMin();
			fail("Expected empty queue");
		} catch (EmptyQueueException e) {
			//Queue should be empty so good
		}

		return true;
	}

}
