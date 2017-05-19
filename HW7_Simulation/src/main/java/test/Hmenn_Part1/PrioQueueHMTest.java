package test.Hmenn_Part1;

import Hmenn_Part1.PrioQueueHM;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;

/**
 * PrioQueueHM Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Apr 30, 2016</pre>
 */
public class PrioQueueHMTest {

	// create priority queue
	PrioQueueHM<String> prio = new PrioQueueHM<>(new Comparator<String>() {
		@Override
		public int compare(String s, String t1) {
			return s.compareTo(t1);
		}
	});


	/**
	 * Method: size()
	 */
	@Test
	public void testSize() throws Exception {

		assertEquals(0, prio.size());

		prio.enqueue("hmen");

		assertEquals(1, prio.size());

	}

	/**
	 * Method: isEmpty()
	 */
	@Test
	public void testIsEmpty() throws Exception {
		PrioQueueHM<String> prio = new PrioQueueHM<>(new Comparator<String>() {
			@Override
			public int compare(String s, String t1) {
				return s.compareTo(t1);
			}
		});

		// empty
		assertEquals(true, prio.isEmpty());
		// add element
		prio.enqueue("menn");
		// not empy
		assertEquals(false, prio.isEmpty());

	}

	/**
	 * Method: enqueue(E newItem)
	 */
	@Test
	public void testEnqueue() throws Exception {

		// add new item
		assertEquals(true, prio.enqueue("ha"));

		//add null element
		assertEquals(false, prio.enqueue(null));

	}

	/**
	 * Method: dequeue()
	 */
	@Test
	public void testDequeue() throws Exception {

		prio.enqueue("hmenn");
		prio.enqueue("test");

		assertEquals("hmenn",prio.dequeue()); // remove first
		assertEquals("test",prio.dequeue()); // remove second
		assertEquals(null,prio.dequeue()); // remove nullllllllll

	}

	/**
	 * Method: peek()
	 */
	@Test
	public void testPeek() throws Exception {

		assertEquals(null,prio.peek()); // if empty

		// control head
		prio.enqueue("testtt");
		assertEquals("testtt",prio.peek());

		// control head
		prio.enqueue("testtt2");
		assertEquals("testtt",prio.peek());


	}


	/**
	 * Method: swap(int index1, int index2)
	 */
	@Test
	public void testSwap() throws Exception {

		prio.enqueue("elem1");
		prio.enqueue("elem2");

		// add elemetns
		assertEquals("elem1",prio.peek());

		prio.swap(0,1);

		//change head of queue
		assertEquals("elem2",prio.peek());
	}

} 
