// Implements a singly-linked list.
// For every method the 4 edge cases are: What is the list has nothing in it, when the list has
// exactly one thing in it, what if the thing you are messing with is at begining or end

public class SinglyLinkedList<E> {
	private ListNode<E> head;
	private ListNode<E> tail;
	private int nodeCount;

	// Constructor: creates an empty list
	public SinglyLinkedList() {}

	// Constructor: creates a list that contains
	// all elements from the array values, in the same order
	public SinglyLinkedList(Object[] values) {
		this.head = new ListNode<E>(null);
		this.tail = this.head;
		nodeCount = 0;
	}

	public ListNode<E> getHead() {
		return head;
	}

	public ListNode<E> getTail() {
		return tail;
	}

	// Returns true if this list is empty; otherwise returns false.
	public boolean isEmpty() {
		return (nodeCount == 0);
	}

	// Returns the number of elements in this list.
	public int size() {
		return nodeCount;
	}

	// Returns true if this list contains an element equal to obj;
	// otherwise returns false.
	public boolean contains(E obj) {
		for (ListNode<E> inspected = this.head; inspected != this.tail; inspected =
				inspected.getNext()) {
			// finds first node at head; itterates through while this node isn't the tail; moves
			// inspected to the next node
			if (inspected.getValue().equals(obj)) {
				// checks if the current expected value is obj
				return true;
			}
		}
		if (this.tail.getValue().equals(obj)) {
			return true;
			// checks if the tail node is obj
		}
		return false;
	}

	// Returns the index of the first element in equal to obj;
	// if not found, returns -1.
	public int indexOf(E obj) {
		ListNode<E> inspected = this.head;
		while (inspected != this.tail) {
			if (inspected.getValue().equals(obj)) {

			}
			inspected = inspected.getNext();
		}
		if (this.tail.getValue().equals(obj)) {

		}
		return -1;
	}

	// Adds obj to this collection. Returns true if successful;
	// otherwise returns false.
	public boolean add(E obj) {
		nodeCount++;
		// Update the node count to account for the added node
		ListNode<E> added = new ListNode(obj);
		// make a new node to add at the end
		this.tail.setNext(added);
		// sets the next for the tail as the new node
		this.tail = added;
		// turns the new end node into the tail
		return true;
	}

	// Removes the first element that is equal to obj, if any.
	// Returns true if successful; otherwise returns false.
	public boolean remove(E obj) {

	}

	// Returns the i-th element.
	public E get(int i) {}

	// Replaces the i-th element with obj and returns the old value.
	public E set(int i, Object obj) {}

	// Inserts obj to become the i-th element. Increments the size
	// of the list by one.
	public void add(int i, Object obj) {}

	// Removes the i-th element and returns its value.
	// Decrements the size of the list by one.
	public E remove(int i) {

	}

	// Returns a string representation of this list exactly like that for MyArrayList.
	public String toString() {
		StringBuilder returnString = new StringBuilder();
		returnString.append('[');
		for (ListNode<E> inspected = this.head; inspected.getNext() != null; inspected =
				inspected.getNext()) {
			// starts with the first node at the head; goes through the list until the next node
			// doesn't exist (tail); moves to the next node
			returnString.append(inspected.getValue());
			//adds the value of the node to the string
			if (inspected.getNext() != null) {
				returnString.append(", ");
			}
		}
		returnString.append(']');
		return returnString.toString();
	}


}
