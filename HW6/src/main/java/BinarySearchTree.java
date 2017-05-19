import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * A class to represent a binary search tree.
 *
 * @author Koffman and Wolfgang
 */
public class BinarySearchTree<E extends Comparable<E>>
		extends BinaryTree<E> {
	// Data Fields
	/**
	 * Return value from the public add method.
	 */
	protected boolean addReturn;
	/**
	 * Return value from the public delete method.
	 */
	protected E deleteReturn;

	/**
	 * Starter method add.
	 *
	 * @param item The object being inserted
	 * @return true if the object is inserted, false
	 * if the object already exists in the tree
	 * Comparable interface.
	 */

	public boolean add(E item) {
		root = add(root, item);
		return addReturn;
	}

	public E delete(E target) {
		root = delete(root, target);
		return deleteReturn;
	}

	/**
	 * Recursive delete method.
	 *
	 * @param localRoot The root of the current subtree
	 * @param item      The item to be deleted
	 * @return The modified local root that does not contain
	 * the item
	 * @post The item is not in the tree;
	 * deleteReturn is equal to the deleted item
	 * as it was stored in the tree or null
	 * if the item was not found.
	 */
	private Node<E> delete(Node<E> localRoot, E item) {
		if (localRoot == null) {
			// item is not in the tree.
			deleteReturn = null;
			return localRoot;
		}

		// Search for item to delete.
		int compResult = item.compareTo(localRoot.data);
		if (compResult < 0) {
			// item is smaller than localRoot.data.
			localRoot.left = delete(localRoot.left, item);
			return localRoot;
		} else if (compResult > 0) {
			// item is larger than localRoot.data.
			localRoot.right = delete(localRoot.right, item);
			return localRoot;
		} else {
			// item is at local root.
			deleteReturn = localRoot.data;
			if (localRoot.left == null) {
				// If there is no left child, return right child
				// which can also be null.
				return localRoot.right;
			} else if (localRoot.right == null) {
				// If there is no right child, return left child.
				return localRoot.left;
			} else {
				// Node being deleted has 2 children, replace the data
				// with inorder predecessor.
				if (localRoot.left.right == null) {
					// The left child has no right child.
					// Replace the data with the data in the
					// left child.
					localRoot.data = localRoot.left.data;
					// Replace the left child with its left child.
					localRoot.left = localRoot.left.left;
					return localRoot;
				} else {
					// Search for the inorder predecessor (ip) and
					// replace deleted node's data with ip.
					localRoot.data = findLargestChild(localRoot.left);
					return localRoot;
				}
			}
		}
	}

	/**
	 * Find the node that is the
	 * inorder predecessor and replace it
	 * with its left child (if any).
	 *
	 * @param parent The parent of possible inorder
	 *               predecessor (ip)
	 * @return The data in the ip
	 * @post The inorder predecessor is removed from the tree.
	 */
	private E findLargestChild(Node<E> parent) {
		// If the right child has no right child, it is
		// the inorder predecessor.
		if (parent.right.right == null) {
			E returnValue = parent.right.data;
			parent.right = parent.right.left;
			return returnValue;
		} else {
			return findLargestChild(parent.right);
		}
	}

	/**
	 * Recursive add method.
	 *
	 * @param localRoot The local root of the subtree
	 * @param item      The object to be inserted
	 * @return The new local root that now contains the
	 * inserted item
	 * @post The data field addReturn is set true if the item is added to
	 * the tree, false if the item is already in the tree.
	 */
	private Node<E> add(Node<E> localRoot, E item) {
		if (localRoot == null) {
			// item is not in the tree � insert it.
			addReturn = true;
			return new Node<E>(item);
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

/* RECURSIVE YAPILINCA STACK OVERFLOW VERIYORRRR
	public void addNodeIterative(E data) {

		if (root == null) {
			root = new Node<>(data);
		} else {

			Node<E> ref = root;

			while (ref != null) {
				if (ref.data.compareTo(data) > 0) {
					if (ref.left == null)
						ref.left = new Node<>(data);
					ref = ref.left;
				} else if (ref.data.compareTo(data) < 0) {
					if (ref.right == null)
						ref.right = new Node<>(data);
					ref = ref.right;
				} else ref = null;
			}
		}
	}*/


	/* ################### ITERATOR ###############*/
	/* Ref1: 	https://www.cs.cmu.edu/~adamchik/15-121/lectures/Trees/code/BST.java
	   Ref2:	http://n00tc0d3r.blogspot.com.tr/2013/08/implement-iterator-for-binarytree-i-in.html
	   Ref3:	http://stackoverflow.com/questions/4581576/implementing-an-iterator-over-a-binary-search-tree */

	/**
	 * This method creates tree iterator.
	 * @return tree iterator
	 */
	public Iterator<E> iterator() {
		return new BinaryIterator();
	}

	/**
	 * This iterator traverse tree inorder.
	 */
	private class BinaryIterator implements Iterator<E> {
		// store left tree
		protected Stack<Node<E>> leftChilds = new Stack<>();

		//constructor
		public BinaryIterator() {
			if (root != null) {
				collectLeftChilds(root);
			}
		}

		/**
		 * This methods adds all left childs in stack.
		 * @param cur parent node
		 */
		private void collectLeftChilds(Node<E> cur) {
			while (cur != null) {
				leftChilds.push(cur);
				cur = cur.left;
			}
		}

		/**
		 * This method check is iterator can traverse
		 * @return if empty return true, other wise false
		 */
		public boolean hasNext() {
			return !leftChilds.isEmpty();
		}

		/**
		 * This method returns next item. Works on ascending order.
		 * @return next item
		 */
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException("There is no elements on tree.");
			}
			Node<E> result = leftChilds.pop();
			collectLeftChilds(result.right);

			return result.data;
		}
	}
}

