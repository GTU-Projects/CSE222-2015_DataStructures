import java.io.Serializable;
import java.util.Comparator;
import java.util.Vector;

/**
 * Created by hmenn on 4/13/16.
 */
public class HMVectorPQ<E> implements IntHMPriorityQueue<E>, Serializable {

	/*private Comparator<E> comparator;
	private Vector<E> data;

	public HMVectorPQ(int size, Comparator<E> comparator) {
		data = new Vector<>(size);
		this.comparator = comparator;
	}

	public int size() {
		return data.size();
	}

	public boolean enqueue(E item) {
		return data.add(item);
	}


	public E dequeue() {
		int minumumID=0;
		E minimum = data.get(minumumID);

		for (int i = 1; i < size(); i++) {
			Comparable<E> val = (Comparable<E>) data.get(i);
			if (val.compareTo(minimum) < 0) {
				minimum = (E) val;
				minumumID = i;
			}
		}
		data.remove(minumumID);
		return minimum;
	}

	public boolean isEmpty() {
		return data.isEmpty(); //olaki size eksiye dustu dikkat et
	}*/

	// Data Fields
	/**
	 * The ArrayList to hold the data.
	 */
	private Vector<E> theData;

	/**
	 * An optional reference to a Comparator object.
	 */
	Comparator<E> comparator = null;


	/**
	 * Creates a heap-based priority queue with the specified initial
	 * capacity that orders its elements according to the specified
	 * comparator.
	 *
	 * @param comp The comparator used to order this priority queue
	 * @throws IllegalArgumentException if cap is less than 1
	 */
	public HMVectorPQ(Comparator<E> comp) {
		theData = new Vector<>();
		comparator = comp;
	}

	/**
	 * Insert an item into the priority queue.
	 * pre: The ArrayList theData is in heap order.
	 * post: The item is in the priority queue and
	 * theData is in heap order.
	 *
	 * @param item The item to be inserted
	 * @throws NullPointerException if the item to be inserted is null.
	 */
	public boolean enqueue(E item) {
		// Add the item to the heap.
		theData.add(item);
		// child is newly inserted item.
		int child = theData.size() - 1;
		int parent = (child - 1) / 2; // Find childs parent.
		// Reheap
		while (parent >= 0 && compare(theData.get(parent),
				theData.get(child)) > 0) {
			swap(parent, child);
			child = parent;
			parent = (child - 1) / 2;
		}
		return true;
	}

	/**
	 * Remove an item from the priority queue
	 * pre: The ArrayList theData is in heap order.
	 * post: Removed smallest item, theData is in heap order.
	 *
	 * @return The item with the smallest priority value or null if empty.
	 */
	public E dequeue() {
		if (isEmpty()) {
			return null;
		}
		// Save the top of the heap.
		E result = theData.get(0);
		// If only one item then remove it.
		if (theData.size() == 1) {
			theData.remove(0);
			return result;
		}
	/* Remove the last item from the ArrayList and place it into
       the first position. */
		theData.set(0, theData.remove(theData.size() - 1));
		// The parent starts at the top.
		int parent = 0;
		while (true) {
			int leftChild = 2 * parent + 1;
			if (leftChild >= theData.size()) {
				break; // Out of heap.
			}
			int rightChild = leftChild + 1;
			int minChild = leftChild; // Assume leftChild is smaller.
			// See whether rightChild is smaller.
			if (rightChild < theData.size()
					&& compare(theData.get(leftChild),
					theData.get(rightChild)) > 0) {
				minChild = rightChild;
			}
			// assert: minChild is the index of the smaller child.
			// Move smaller child up heap if necessary.
			if (compare(theData.get(parent),
					theData.get(minChild)) > 0) {
				swap(parent, minChild);
				parent = minChild;
			} else { // Heap property is restored.
				break;
			}
		}
		return result;
	}

	/**
	 * Compare two items using either a Comparator objects compare method
	 * or their natural ordering using method compareTo.
	 * pre: If comparator is null, left and right implement Comparable<E>.
	 *
	 * @param left  One item
	 * @param right The other item
	 * @return Negative int if left less than right,
	 * 0 if left equals right,
	 * positive int if left > right
	 * @throws ClassCastException if items are not Comparable
	 */
	private int compare(E left, E right) {
		if (comparator != null) { // A Comparator is defined.
			return comparator.compare(left, right);
		} else { // Use lefts compareTo method.
			return ((Comparable<E>) left).compareTo(right);
		}
	}

	/****
	 * BEGIN EXERCISE
	 ****/
	public void swap(int a, int b) {
		E first = theData.get(a);
		E second = theData.get(b);
		theData.set(a, second);
		theData.set(b, first);
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public int size() {
		return theData.size();
	}


}
