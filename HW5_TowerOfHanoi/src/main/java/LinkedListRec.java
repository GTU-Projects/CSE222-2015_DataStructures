/**
 * BU CLASS BUYUK COGUNLUKLA KOFFMAN OGRETMEN KODLARINDAN ALINMISTIR
 * A recursive linked list class with recursive methods.
 *
 * @author Koffman and Wolfgang redegigned by hmenn
 */

public class LinkedListRec<E> {

	/**
	 * The list head
	 */
	private Node<E> head;

	/**
	 * A Node is the building block for a single-linked list.
	 */
	private static class Node<E> {
		// Data Fields
		/**
		 * The reference to the data.
		 */
		private E data;

		/**
		 * The reference to the next node.
		 */
		private Node next;

		// Constructors

		/**
		 * Creates a new node with a null next field.
		 *
		 * @param dataItem The data stored
		 */
		private Node(E dataItem) {
			data = dataItem;
			next = null;
		}
	} //end class Node


	/**
	 * Returns the string representation of a list.
	 *
	 * @param head The head of the current list
	 * @return The state of the current list
	 */
	private String toString(Node<E> head) {
		if (head == null)
			return "";
		else {
			String out = head.data.toString();

			if (head.next != null) {
				out += " -> " + toString(head.next);
			}
			return out;
		}

	}

	/**
	 * Wrapper method for returning the string representation of a list.
	 *
	 * @return The string representation of the list
	 */
	public String toString() {
		return toString(head);
	}


	/**
	 * Adds a new node to the end of a list.
	 *
	 * @param head The head of the current list
	 * @param data The data for the new node
	 */
	private void add(Node<E> head, E data) {
		// If the list has just one element, add to it.
		if (head.next == null)
			head.next = new Node<E>(data);
		else
			add(head.next, data); // Add to rest of list.
	}

	/**
	 * Wrapper method for adding a new node to the end of a list.
	 *
	 * @param data The data for the new node
	 */
	public void add(E data) {
		if (head == null)
			head = new Node<E>(data); // List has 1 node.
		else
			add(head, data);
	}

	/**
	 * Removes a node from a list.
	 * post: The first occurrence of outData is removed.
	 *
	 * @param head    The head of the current list
	 * @param prev    The predecessor of the list head
	 * @param outData The data to be removed
	 * @return true if the item is removed
	 * and false otherwise
	 */
	public boolean remove(Node<E> head, Node<E> prev, E outData) {
		if (prev == null) { // bos bir listeyse direk cik
			return false;
		}

		if (prev.data.equals(outData)) { // prevde ise headi guncelle
			this.head = head;
			if (head == null) // eger tek eleman var ise return 1
				return true;
			remove(head.next, head, outData);
			return true;  // devami varsa devam et
		}

		if (head == null) { // eger prevde degise heade bak ama ondan once head gecerli olmali
			return false;
		}

		// ustte head kontrol edilmeseydi null durumunda exception atardi
		if (head.data.equals(outData)) {
			prev.next = head.next;
			if (prev.next == null) // head den sildikten sonra recursive listede tek eleman kaldiysa prev.next null olur
				return true;
			else {
				remove(prev.next, prev, outData);
			}
			return true;
		}
		return remove(head.next, head, outData); // eslesme yoksa siradaki elemanlara bak
	}


	/**
	 * Wrapper method for removing a node (in LinkedListRec).
	 * post: The first occurrence of outData is removed.
	 *
	 * @param outData The data to be removed
	 * @return true if the item is removed,
	 * and false otherwise
	 */
	public boolean remove(E outData) {
		if (head == null || outData == null) // in this conditions method wont work
			return false;
		else {
			return remove(head.next, head, outData);
		}
	}


}