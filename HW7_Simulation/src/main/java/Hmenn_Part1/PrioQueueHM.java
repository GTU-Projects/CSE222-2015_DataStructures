package Hmenn_Part1;

import java.util.Comparator;

/**
 * This class contains Priority queue implementation
 * Created by hmenn on 4/26/16.
 */
public class PrioQueueHM<E> implements IPriorQueueHM<E> {

	public ArrayListHM<E> data; // element of queueu
	private Comparator<E> comp; // comparator

	/**
	 * Return size of queue
	 * @return size of queueu
	 */
	public int size(){
		return data.size(); // delegation
	}

	/**
	 * Checks queue is empty or not
	 * @return true if empty, otherwise false
	 */
	public boolean isEmpty(){
		return data.isEmpty();
	}


	/**
	 * Contructor
	 * @param comp comparator
	 */
	public PrioQueueHM(Comparator<E> comp) {
		if(null == comp)
			throw new NullPointerException("Invalid/NULL comparator.");
		data = new ArrayListHM<>();
		this.comp = comp;
	}


	/**
	 * This method comparet two element
	 * @param left first element
	 * @param right second element
	 * @return status of compare operation l>r = 1 ; l==r = 0 ; l<r = -1
	 */
	private int compare(E left, E right) {
		if(comp == null)
			throw new NullPointerException("Invalid Comparator : NULL.");
		return comp.compare(left,right);
	}

	/**
	 * ###############################################
	 * THIS METHOD ACTUALLY PRIVE. MAKED PUBLIC VISIBLE FOR JUNIT
	 * ###############################################
	 * This method swap two element in list
	 * @param index1 index of first element
	 * @param index2 index of second element
	 */
	public void swap(int index1, int index2){
		E first=data.get(index1);
		E second = data.get(index2);
		data.set(index1,second);
		data.set(index2,first);
	}

	/**
	 * This method inserts elemnt to queue
	 * @param newItem element to add
	 * @return if add return true, otherwise false
	 */
	public boolean enqueue(E newItem){

		if(newItem == null)
			return false;

		data.add(newItem);

		int child = size()-1;
		int parent = (child -1)/2;

		// compare and insert right place
		while(parent>0 && compare(data.get(parent),data.get(child))>0){
			swap(parent,child);
			child=parent;
			parent=(child-1)/2;
		}

		return true;
	}

	/**
	 * This method removes first element from queueu
	 * @return removed element
	 */
	public E dequeue(){
		if(size()==0)
			return null;

		// first element
		E deleted = data.get(0);
		if(1 == size()){
			return data.remove(0);
		}

		// transport last element to head
		data.set(0,data.remove(this.size()-1));

		int parent=0;
		int leftChild=0;
		int rightChild=0;
		int minChild=0;
		while(true){

			leftChild =2*parent+1;

			if(leftChild>=size())
				break;

			rightChild=leftChild+1;
			minChild = leftChild;

			// find min between two child
			if(rightChild < size() && compare(data.get(leftChild),data.get(rightChild))>0){
				minChild=rightChild;
			}

			// compare with parent and sort
			if(compare(data.get(parent),data.get(minChild))>0){
				swap(parent,minChild);
				parent=minChild;
			}else break; // sorted

		}

		return deleted;
	}

	/**
	 * This method returns first element of queue, Not removes element
	 * @return if any returns first element
	 */
	public E peek(){

		if(isEmpty())
			return null;
		else return data.get(0);
	}

}
