import static org.junit.Assert.*;

import org.junit.Test;

import com.concurrency.queue.Queue;
import com.concurrency.queue.SequentialQueue;


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
