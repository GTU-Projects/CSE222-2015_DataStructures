import java.io.Serializable;
import java.util.*;

/**
 * Created by hmenn on 4/13/16.
 */
public class HMBinaryPQ<E extends Comparable<E>> implements IntHMPriorityQueue<E> , Serializable {

	// Data Fields
	/** The ArrayList to hold the data. */
	private BinaryTree<E> theData;

	private int size;

	/** An optional reference to a Comparator object. */
	Comparator< E > comparator = null;


	/** Creates a heap-based priority queue with the specified initial
	 capacity that orders its elements according to the specified
	 comparator.
	 @param comp The comparator used to order this priority queue
	 @throws IllegalArgumentException if cap is less than 1
	 */
	public HMBinaryPQ(Comparator < E > comp) {
		theData = new BinarySearchTree<>();
		size=0;
		comparator = comp;
	}

	/**
	 * This method add an item to binary search priority queue
	 * @param item item to add
	 * @return status of operation
	 */
	public boolean enqueue(E item) {
		return add(item);
	}

	private boolean add(E item) {
		theData.root = add(theData.root, item);
		return addReturn;
	}

	// helper field
	protected boolean addReturn;

	/**
	 * This method token from binary search queue class.
	 * @param localRoot
	 * @param item
	 * @return
	 */
	private BinaryTree.Node<E> add(BinaryTree.Node<E> localRoot, E item) {
		if (localRoot == null) {
			// item is not in the tree � insert it.
			addReturn = true;
			return new BinaryTree.Node<E>(item);
		} else if (item.compareTo(localRoot.data) == 0) {
			// item is equal to localRoot.data
			addReturn = false;
			return localRoot;
		} else if (item.compareTo(localRoot.data) < 0) {
			// item is less than localRoot.data
			localRoot.left = add(localRoot.left, item);
			return localRoot;
		} else {
			// item is greater than localRoot.data
			localRoot.right = add(localRoot.right, item);
			return localRoot;
		}
	}

	/** Remove an item from the priority queue
	 pre: The ArrayList theData is in heap order.
	 post: Removed smallest item, theData is in heap order.
	 @return The item with the smallest priority value or null if empty.
	 */
	public E dequeue() {
		throw new UnsupportedOperationException("Unsupperoted dequeue method for HMBinaryPriorityQueue.");
	}

	public boolean isEmpty(){
		return size()==0;
	}

	public int size() {
		return size();
	}
}
