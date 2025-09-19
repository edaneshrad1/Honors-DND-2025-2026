// Implements a singly-linked list.


public class SinglyLinkedList<E> {
	private ListNode<E> head;
	private ListNode<E> tail;
	private int nodeCount;

	// Constructor: creates an empty list
	public SinglyLinkedList() {
	}

	// Constructor: creates a list that contains
	// all elements from the array values, in the same order
	public SinglyLinkedList(Object[] values) {
	}
	
	public ListNode<E> getHead() {
		return head;
	}
	
	public ListNode<E> getTail() {
		return tail;
	}

	// Returns true if this list is empty; otherwise returns false.
	public boolean isEmpty() {
	}

	// Returns the number of elements in this list.
	public int size() {
	}

	// Returns true if this list contains an element equal to obj;
	// otherwise returns false.
	public boolean contains(E obj) {
	}

	// Returns the index of the first element in equal to obj;
	// if not found, returns -1.
	public int indexOf(E obj) {
	}

	// Adds obj to this collection.  Returns true if successful;
	// otherwise returns false.
	public boolean add(E obj) {
	}

	// Removes the first element that is equal to obj, if any.
	// Returns true if successful; otherwise returns false.
	public boolean remove(E obj) {
		
	}

	// Returns the i-th element.               
	public E get(int i) {
	}

	// Replaces the i-th element with obj and returns the old value.
	public E set(int i, Object obj) {
	}

	// Inserts obj to become the i-th element. Increments the size
	// of the list by one.
	public void add(int i, Object obj) {
	}

	// Removes the i-th element and returns its value.
	// Decrements the size of the list by one.
	public E remove(int i) {
		
	}

	// Returns a string representation of this list exactly like that for MyArrayList.
	public String toString() {


	}
	

}
