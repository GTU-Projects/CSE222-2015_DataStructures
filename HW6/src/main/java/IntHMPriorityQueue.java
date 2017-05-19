/**
 *
 * This interface will use for priprity queue implementation contract.
 *
 * Created by hmenn on 4/13/16.
 */
public interface IntHMPriorityQueue<E> {


	/**
	 * This method adds item to queue
	 * @param item item to add
	 * @return status of adding
	 */
	boolean enqueue(E item);

	/**
	 * This method remove little element from queue.
	 * @return deleting element
	 */
	E dequeue();

	/**
	 * This method check statuf of queue
	 * @return if empty return true, otherwise return false
	 */
	boolean isEmpty();

	/**
	 * This method retursn size of queueu
	 * @return size ıf queue
	 */
	int size();
}
