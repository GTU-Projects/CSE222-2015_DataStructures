package Hmenn_Part1;

/**
 * It's a arraylist interface like JavaAPI
 * Every array has size-set-get-add-remove and emoty method.
 * Who implments this interface, have to write all above methods
 * Created by hmenn on 4/29/16.
 */
public interface IArrayListHM<E> {


	/**
	 *  returns size of arraylist */
	int size();

	/**
	 * return specified elements in index
	 * @param index index of element
	 * @return element in index
	 */
	E get(int index);

	/**
	 * sets elements in index with new one
	 * @param index index
	 * @param newValue new value
	 * @return old value
	 */
	E set(int index,E newValue);

	/**
	 * adds an element end of list
	 * @param value element to add
	 * @return returns truee
	 */
	boolean add(E value);

	/**
	 * This method removes and item in specified index
	 * @param index index
	 * @return if remove returns old value, otherwise null
	 */
	E remove(int index);

	/**
	 * This method checks is list empty or not
	 * @return true if empty
	 */
	boolean isEmpty();


}
