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
		this.head = null;
		this.tail = this.head;
		nodeCount = 0;
		for (int i = 0; i < values.length; i++) {
			add((E) values[i]);
		}
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
	// O(1)
	public int size() {
		return nodeCount;
	}

	// Returns true if this list contains an element equal to obj;
	// otherwise returns false.
	// O(n)
	public boolean contains(E obj) {
		if (nodeCount == 0) {
			return false;
		}
		if (obj == null) {
			for (ListNode<E> inspected = this.head; inspected != this.tail; inspected =
					inspected.getNext()) {
				if (inspected.getValue() == null) {
					return true;
				}
			}
			if (this.tail.getValue() == null) {
				return true;
			}
		}
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
	// O(n)
	public int indexOf(E obj) {
		if (nodeCount == 0) {
			return -1;
		}
		int index = 0;
		if (obj == null) {
			for (ListNode<E> inspected = this.head; inspected != this.tail; inspected =
					inspected.getNext()) {
				if (inspected.getValue() == null) {
					return index;
				}
				index++;
			}
			if (this.tail.getValue() == null) {
				return index++;
			}
		}
		for (ListNode<E> inspected = this.head; inspected != this.tail; inspected =
				inspected.getNext()) {
			if (inspected.getValue().equals(obj)) {
				return index;
			}
			index++;
		}
		if (this.tail.getValue().equals(obj)) {
			return index++;
		}
		return -1;
	}

	// Adds obj to this collection. Returns true if successful;
	// otherwise returns false.
	// O(1)
	public boolean add(E obj) {
		ListNode<E> added = new ListNode<E>(obj);
		if (head == null) {
			head = added;
			tail = added;
		} else {
			tail.setNext(added);
			tail = added;
		}
		nodeCount++;
		return true;
	}

	// Removes the first element that is equal to obj, if any.
	// Returns true if successful; otherwise returns false.
	// O(n)
	public boolean remove(E obj) {
		if (nodeCount == 0) {
			return false;
		}
		int index = 0;
		if (obj == null) {
			for (ListNode<E> inspected = this.head; inspected != this.tail; inspected =
					inspected.getNext()) {
				if (inspected.getValue() == null) {
					remove(index);
					return true;
				}
				index++;
			}
			if (this.tail == null) {
				remove(index++);
			}
		}
		for (ListNode<E> inspected = this.head; inspected != this.tail; inspected =
				inspected.getNext()) {
			if (inspected.getValue().equals(obj)) {
				remove(index);
				return true;
			}
			index++;
		}
		if (this.tail.getValue().equals(obj)) {
			remove(index + 1);
			return true;
		}
		return false;
	}

	// // Returns the i-th element.
	// O(n)
	public E get(int i) {
		if (i < 0 || i >= nodeCount) {
			throw new IndexOutOfBoundsException("index is out of bounds");
		}
		if (i == 0) {
			return head.getValue();
		}
		int index = 0;
		for (ListNode<E> inspected = this.head; inspected != this.tail; inspected =
				inspected.getNext()) {
			if (index == i) {
				return inspected.getValue();
			}
			index++;
		}
		return tail.getValue();
	}

	// // Replaces the i-th element with obj and returns the old value.
	public E set(int i, Object obj) {
		if (i < 0 || i >= nodeCount) {
			throw new IndexOutOfBoundsException("index is out of bounds");
		}

		ListNode<E> inspected = head;
		int index = 0;
		while (index < i) {
			inspected = inspected.getNext();
			index++;
		}
		E oldValue = inspected.getValue();
		inspected.setValue((E) obj);
		return oldValue;
	}

	// // Inserts obj to become the i-th element. Increments the size
	// // of the list by one.
	public void add(int i, Object obj) {
		if (i < 0 || i >= nodeCount) {
			throw new IndexOutOfBoundsException("index is out of bounds");
		}

		if (i == 0) {
			ListNode<E> inspected = head;
			ListNode<E> nodeToAdd = new ListNode<E>((E) obj);
			head = nodeToAdd;
			nodeToAdd.setNext(inspected);
			nodeCount++;
			return;
		}

		int index = 0;
		ListNode<E> inspected = head;
		while (index < i - 1) {
			inspected = inspected.getNext();
			index++;
		}
		ListNode<E> movedNode = inspected.getNext();
		inspected.setNext(new ListNode<E>((E) obj));
		inspected.getNext().setNext(movedNode);
		nodeCount++;
	}

	// // Removes the i-th element and returns its value.
	// // Decrements the size of the list by one.
	// O(n)
	public E remove(int i) {
		if (i < 0 || i > nodeCount) {
			throw new IndexOutOfBoundsException("index is out of bounds");
		}
		E tailValue = this.tail.getValue();
		// if i is 0 make the head the next node
		if (i == 0) {
			E returnValue = head.getValue();
			head = head.getNext();
			nodeCount--;
			return returnValue;
		}

		int index = 0;
		ListNode<E> nextInspected = null;
		for (ListNode<E> inspected = this.head; inspected != this.tail; inspected =
				inspected.getNext()) {
			// if the node at i is the tail make the node before it the tail
			if (inspected.getNext() == tail) {
				inspected.setNext(null);
				tail = inspected;
				nodeCount--;
				break;
			}
			nextInspected = inspected.getNext();
			if (index == i - 1) {
				// find the node right before i
				E returnValue = inspected.getNext().getValue();
				// return the value at node i
				inspected.setNext(nextInspected.getNext());
				// set the next node for the node before i to be the node after i
				nodeCount--;
				return returnValue;
			}
			index++;
		}

		return tailValue;
	}

	// Returns a string representation of this list exactly like that for MyArrayList.
	public String toString() {
		StringBuilder returnString = new StringBuilder();
		returnString.append('[');
		if (nodeCount == 0) {
			returnString.append(']');
			return returnString.toString();
		}
		for (ListNode<E> inspected = this.head; inspected.getNext() != null; inspected =
				inspected.getNext()) {
			// starts with the first node at the head; goes through the list until the next node
			// doesn't exist (tail); moves to the next node
			returnString.append(inspected.getValue());
			// adds the value of the node to the string
			if (inspected.getNext() != null) {
				returnString.append(", ");
			}
		}
		returnString.append(this.tail.getValue());
		returnString.append(']');
		return returnString.toString();
	}


}
