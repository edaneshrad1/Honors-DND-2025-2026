/* See ArrayList documentation here:
 * http://docs.oracle.com/javase/7/docs/api/java/util/ArrayList.html
 */

/*
 * Your indexed functions should throw IndexOutOfBoundsException if index is invalid!
 */

public class MyArrayList<E> {

	/* Internal Object counter */
	protected int objectCount;

	/* Internal Object array */
	protected E [] internalArray;

	/* Constructor: Create it with whatever capacity you want? */
	@SuppressWarnings("unchecked")
	public MyArrayList() {
		this.internalArray = (E[])new Object[100];
	}

	/* Constructor with initial capacity */
	@SuppressWarnings("unchecked")
	public MyArrayList(int initialCapacity){
		this.internalArray = (E[])new Object[initialCapacity];
	}

	/* Return the number of active slots in the array list */
	public int size() {
		//change to object count instead of length
		return internalArray.length;
	}

	/* Are there zero objects in the array list? */
	// public boolean isEmpty() {
	// 	/* ---- YOUR CODE HERE ---- */
	// }

	/* Get the index-th object in the list. */
	public E get(int index) {
		if (index < 0 || index >= internalArray.length) {
			throw new IllegalArgumentException("index is out of bounds");
		}
		return internalArray[index];
	}

	/* Replace the object at index with obj.  returns object that was replaced. */
	public E set(int index, E obj) {
		E origionalObject = internalArray[index];
		internalArray[index] = obj;
		return origionalObject;
	}

	/* Returns true if this list contains an element equal to obj;
	//  otherwise returns false. */
	// public boolean contains(E obj) {
	// 	/* ---- YOUR CODE HERE ---- */
	// }

	/* Insert an object at index */
	@SuppressWarnings("unchecked")
	public void add(int index, E obj) {
		/* ---- YOUR CODE HERE ---- */
	}

	/* Add an object to the end of the list; returns true */
	@SuppressWarnings("unchecked")
	public boolean add(E obj) {
		if (objectCount >= internalArray.length) {
			E[] newArray = (E[]) new Object[internalArray.length * 2];
			for (int i = 0; i < internalArray.length; i++) {
				newArray[i] = internalArray[i];
			}
			internalArray = newArray;
		}
		internalArray[objectCount] = obj;
		objectCount++;
		return true;
	}

	// /* Remove the object at index and shift.  Returns removed object. */
	// public E remove(int index) {
	// 	/* ---- YOUR CODE HERE ---- */
	// }

	// /* Removes the first occurrence of the specified element from this list, 
	//  * if it is present. If the list does not contain the element, it is unchanged. 
	//  * More formally, removes the element with the lowest index i such that
	//  * (o==null ? get(i)==null : o.equals(get(i))) (if such an element exists). 
	//  * Returns true if this list contained the specified element (or equivalently, 
	//  * if this list changed as a result of the call). */
	// public boolean remove(E obj) {
	// 	/* ---- YOUR CODE HERE ---- */
	// }


	// /* For testing; your string should output as "[X, X, X, X, ...]" where X, X, X, X, ... are the elements in the ArrayList.
	//  * If the array is empty, it should return "[]".  If there is one element, "[X]", etc.
	//  * Elements are separated by a comma and a space. */
	// public String toString() {
	// 	/* ---- YOUR CODE HERE ---- */
	// }

}