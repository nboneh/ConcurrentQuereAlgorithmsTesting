package com.concurrency.queue;

import java.util.Random;

public class PeformanceTest {
	static int[] numOfThreads = {4,8,16,32,64};
	static int[] numOfOpreationsPerThread = {10,50, 100, 500, 1000,5000};
	static String[] queues = {"Array", "Tree", "Heap", "Skip"};


	public static void main(String[] args) throws InterruptedException{
		System.out.print("Queue\t\t");

		//Printing headers
		for(int nthreads : numOfThreads ){
			for(int nops : numOfOpreationsPerThread){
				System.out.print(nthreads + "," + nops + "\t" );
			}
		}

		//Performing test
		System.out.println();
		for(String queueName : queues){
			System.out.print(queueName +"\t\t");
			for(int nthreads : numOfThreads ){
				for(int nops : numOfOpreationsPerThread){
					int capacity = nops * nthreads;
					Queue<Integer> queue = null;
					switch(queueName){
					case "Array":
						queue = new ArrayQueue<Integer>(capacity);
						break;
					case "Tree":
						queue = new TreeQueue<Integer>( (int)(Math.log(capacity) / Math.log(2)) + 1);
						break;
					case "Heap":
						queue = new HeapQueue<Integer>((int)capacity + 1);
						break; 
					case "Skip":
						queue = new SkipListQueue<Integer>();
						break; 
					}

					Thread[] threads = new Thread[nthreads];
					//Creating threads
					final Queue<Integer> threadQueue = queue;
					//Running add threads 
					for( int i = 0; i < nthreads; i++){
						final int threadNum = i;
						threads[i] = new Thread(  new Runnable(){
			
							@Override
							public void run() {
								 Random rand = new Random();
								for(int i = 0; i < nops; i++){
										threadQueue.add(rand.nextInt(capacity),threadNum*nops + i);
								}
							}

						});
					}
	

					long tic = System.currentTimeMillis();
					for(Thread t : threads){
						t.start();
					}

					for(Thread t : threads){
						t.join();
					}
					


					System.out.print((System.currentTimeMillis() - tic)/1000.0+ "\t");


				}
			}
			System.out.println();
		}

	}
}
