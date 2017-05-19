package Hmenn_Part1;

import java.util.Arrays;

/**
 * Created by hmenn on 4/26/16.
 */
public class ArrayListHM<E> implements IArrayListHM<E> {

	private static final int FIRST_CAPACITY = 10;
	private E[] data;
	private int size;
	private int capacity;

	public ArrayListHM(){
		size=0;
		data = (E []) new Object [FIRST_CAPACITY];
		capacity = FIRST_CAPACITY;

	}

	public int size(){
		return size;
	}

	/**
	 * Get value on the index.
	 * @param index index
	 * @return value on the index
	 */
	public E get(int index){
		if(index<0 || index>=size)
			throw new ArrayIndexOutOfBoundsException("Index : "+index+" invalid to get.");
		else
			return data[index];
	}

	/**
	 * This method sets data on spesified index with new item
	 * @param index index of data which will change
	 * @param item new value of data on that index
	 * @return old value of data on that index
	 */
	public E set(int index,E item){
		if(index<0 ||  index>=size){
			throw new ArrayIndexOutOfBoundsException("Index : "+index+" invalid to set.");
		}else{
			E returnValue = data[index];
			data[index]=item;
			return returnValue;
		}
	}

	/**
	 * This method add new item at the end of list.
	 * @param item item to add
	 * @return allways true
	 */
	public boolean add(E item){

		if(size == capacity)
			reallocate();
		data[size]= item;
		++size;
		return true;
	}


	/**
	 * This method resize the dynamic array capacity on memory
	 */
	private void reallocate(){
		capacity *=2;
		this.data = Arrays.copyOf(this.data,capacity);
	}

	public E remove(int index){
		if(index<0 || index>=size)
			throw new ArrayIndexOutOfBoundsException("Index : "+index+" invalid to remove.");

		E returnValue = data[index];
		for(int i=index;i<size()-1;++i){
			data[i]=data[i+1];
		}
		--size;
		return returnValue;
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();

		for(int i=0;i<size();++i){
			sb.append(data[i]+"\n");
		}
		return sb.toString();
	}

	public boolean isEmpty(){
		return 0==size();
	}

}
