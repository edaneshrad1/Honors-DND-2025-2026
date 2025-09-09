/*
 * See ArrayList documentation here:
 * http://docs.oracle.com/javase/7/docs/api/java/util/ArrayList.html
 */

/*
 * Your indexed functions should throw IndexOutOfBoundsException if index is invalid!
 */

public class MyArrayList<E> {

	/* Internal Object counter */
	protected int objectCount;

	/* Internal Object array */
	protected E[] internalArray;

	/* Constructor: Create it with whatever capacity you want? */
	@SuppressWarnings("unchecked")
	public MyArrayList() {
		this.internalArray = (E[]) new Object[100];
	}

	/* Constructor with initial capacity */
	@SuppressWarnings("unchecked")
	public MyArrayList(int initialCapacity) {
		this.internalArray = (E[]) new Object[initialCapacity];
	}

	/* Return the number of active slots in the array list */

	// O(1)
	public int size() {
		// change to object count instead of length
		return objectCount;
	}

	/* Are there zero objects in the array list? */

	// O(1)
	public boolean isEmpty() {
		if (objectCount == 0) {
			return true;
		} else {
			return false;
		}
	}

	/* Get the index-th object in the list. */

	// O(1)
	public E get(int index) {
		if (index < 0 || index >= objectCount) {
			throw new IndexOutOfBoundsException("index is out of bounds");
		}
		return internalArray[index];
	}

	/* Replace the object at index with obj. returns object that was replaced. */

	// O(1)
	public E set(int index, E obj) {
		E origionalObject = internalArray[index];
		internalArray[index] = obj;
		return origionalObject;
	}

	/*
	 * Returns true if this list contains an element equal to obj; // otherwise returns false.
	 */

	// O(n)
	public boolean contains(E obj) {
		for (int i = 0; i < objectCount; i++) {
			if (internalArray[i].equals(obj)) {
				return true;
			}
		}
		return false;
	}

	/* Insert an object at index */

	// O(1)
	// O(n) if resizing is needed
	@SuppressWarnings("unchecked")
	public void add(int index, E obj) {
		if (index < 0 || index > objectCount) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + objectCount);
		}
		if (objectCount >= internalArray.length) {
			E[] newArray = (E[]) new Object[internalArray.length * 2];
			for (int i = 0; i < internalArray.length; i++) {
				newArray[i] = internalArray[i];
			}
			internalArray = newArray;
		}
		for (int i = objectCount; i > index; i--) {
			internalArray[i] = internalArray[i - 1];
		}
		internalArray[index] = obj;
		objectCount++;
	}

	/* Add an object to the end of the list; returns true */

	// O(1)
	// O(n) if resizing is needed
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
		// add(objectCount, obj);
	}

	// /* Remove the object at index and shift. Returns removed object. */

	// O(n)
	public E remove(int index) {
		E specialObject = internalArray[index];
		for (int i = index; i < objectCount - 1; i++) {
			internalArray[i] = internalArray[i + 1];
		}
		objectCount--;
		return specialObject;
	}

	// /* Removes the first occurrence of the specified element from this list,
	// * if it is present. If the list does not contain the element, it is unchanged.
	// * More formally, removes the element with the lowest index i such that
	// * (o==null ? get(i)==null : o.equals(get(i))) (if such an element exists).
	// * Returns true if this list contained the specified element (or equivalently,
	// * if this list changed as a result of the call). */

	// O(n)
	public boolean remove(E obj) {
		for (int i = 0; i < objectCount; i++) {
			if (internalArray[i].equals(obj)) {
				remove(i);
				return true;
			}
		}
		return false;
	}


	// /* For testing; your string should output as "[X, X, X, X, ...]" where X, X, X, X, ... are
	// the elements in the ArrayList.
	// * If the array is empty, it should return "[]". If there is one element, "[X]", etc.
	// * Elements are separated by a comma and a space. */

	// O(n)
	// but could become worse if object has instance variables that are arrays
	public String toString() {
		// String returnString = "[";
		// for (int i = 0; i < objectCount; i++) {
		// 	returnString += internalArray[i].toString();
		// 	if (i != objectCount - 1) {
		// 		returnString += ", ";
		// 	}
		// }
		// returnString += "]";
		// return returnString;
		StringBuilder returnString = new StringBuilder();
		returnString.append('[');
		for (int i = 0; i < objectCount; i++) {
			returnString.append(internalArray[i].toString());
			if (i != objectCount - 1) {
				returnString.append(", ");
			}
		}
		returnString.append(']');
		return returnString.toString();
	}
}
