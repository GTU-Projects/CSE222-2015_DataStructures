package com.hasanmen;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

/**
 * ###############################################################
 * BU KLASS DERS KITABINDAN ALINMISTIR -- TESTLERI YAPILAMAYCAKTIR
 * ################################################################
 * Created by hmenn on 3/20/16.
 */
public class HMStack<E> implements HMStackInterface<E> {

	private List< E > stack;

	/** Construct an empty stack using an ArrayList as the
	 container. */
	public HMStack() {
		stack = new ArrayList < E > ();
	}

	/** Push an object onto the stack.
	 post: The object is at the top of the stack.
	 @param obj The object to be pushed
	 @return The object pushed
	 */
	public E push(E obj) {
		stack.add(obj);
		return obj;
	}

	/** Peek at the top object on the stack.
	 @return The top object on the stack
	 @throws EmptyStackException if the stack is empty
	 */
	public E peek() {
		if (empty()) {
			throw new EmptyStackException();
		}
		return stack.get(stack.size() - 1);
	}

	/** Pop the top object off the stack.
	 post: The object at the top of the stack is removed.
	 @return The top object, which is removed
	 @throws EmptyStackException if the stack is empty
	 */
	public E pop() {
		if (empty()) {
			throw new EmptyStackException();
		}
		return stack.remove(stack.size() - 1);
	}

	/** See whether the stack is empty.
	 @return true if the stack is empty
	 */
	public boolean empty() {
		return stack.size() == 0;
	}
}
