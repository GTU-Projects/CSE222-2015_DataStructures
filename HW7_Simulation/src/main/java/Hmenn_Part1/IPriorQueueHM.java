package Hmenn_Part1;

/**
 * This is priority queue imterface
 * All class have to implement all methods.
 * A priority queue has this attributes minumum.
 *
 * Created by hmenn on 4/26/16.
 */
public interface IPriorQueueHM<E> {

	/**
	 * adds element in priority queueu
	 * @param newItem element to add
	 * @return if add return true, ptherwise false
	 */
	boolean enqueue(E newItem);

	/**
	 * This method removes an item form preoirt queue
	 * @return removed element
	 */
	E dequeue();


	/**
	 * This method checks queue is empty or not
	 * @return if queue is empty return true, otherwise false
	 */
	boolean isEmpty();

	/**
	 * TThis method return first element on queueu
	 * NOt remove element from queueu
	 * @return first element in queue
	 */
	E peek();

	/**
	 * This method return size of qeueu
	 * @return number of elements in queueu
	 */
	int size();
}
