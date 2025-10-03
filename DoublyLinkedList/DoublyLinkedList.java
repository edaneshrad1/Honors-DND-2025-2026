
public class DoublyLinkedList {
	// Implements a circular doubly-linked list.

	private final ListNode2<Nucleotide> SENTINEL = new ListNode2<Nucleotide>(null);
	private int nodeCount;

	// Constructor: creates an empty list
	public DoublyLinkedList() {
		nodeCount = 0;
		SENTINEL.setNext(SENTINEL);
		SENTINEL.setPrevious(SENTINEL);
	}

	// Constructor: creates a list that contains
	// all elements from the array values, in the same order
	public DoublyLinkedList(Nucleotide[] values) {
		nodeCount = 0;
		SENTINEL.setNext(SENTINEL);
		SENTINEL.setPrevious(SENTINEL);
		for (int i = 0; i < values.length; i++) {
			add(values[i]);
		}
	}

	public ListNode2<Nucleotide> getSentinel() {
		return SENTINEL;
	}

	public ListNode2<Nucleotide> getHead() {
		return SENTINEL.getNext();
	}

	public ListNode2<Nucleotide> getTail() {
		return SENTINEL.getPrevious();
	}


	// Returns true if this list is empty; otherwise returns false.
	public boolean isEmpty() {
		if (nodeCount == 0) {
			return true;
		}
		return false;
	}

	// Returns the number of elements in this list.
	public int size() {
		return nodeCount;
	}

	// Returns true if this list contains an element equal to obj;
	// otherwise returns false.
	public boolean contains(Nucleotide obj) {
		for (ListNode2<Nucleotide> inspected =
				SENTINEL.getNext(); inspected != SENTINEL; inspected = inspected.getNext()) {
			if (inspected.getValue().equals(obj)) {
				return true;
			}
		}
		return false;
	}

	// Returns the index of the first element in equal to obj;
	// if not found, returns -1.
	public int indexOf(Nucleotide obj) {
		int index = 0;
		for (ListNode2<Nucleotide> inspected =
				SENTINEL.getNext(); inspected != SENTINEL; inspected = inspected.getNext()) {
			if (inspected.getValue().equals(obj)) {
				return index;
			}
			index++;
		}
		return -1;
	}

	// Adds obj to this collection. Returns true if successful;
	// otherwise returns false.
	public boolean add(Nucleotide obj) {
		ListNode2<Nucleotide> added = new ListNode2<Nucleotide>(obj);
		ListNode2<Nucleotide> lastNode = this.SENTINEL.getPrevious();
		if (nodeCount == 0) {
			SENTINEL.setNext(added);
			added.setNext(SENTINEL);
			added.setPrevious(SENTINEL);
			SENTINEL.setPrevious(added);
			nodeCount++;
			return true;
		}
		lastNode.setNext(added);
		added.setNext(SENTINEL);
		added.setPrevious(lastNode);
		SENTINEL.setPrevious(added);
		nodeCount++;
		return true;
	}

	// Removes the first element that is equal to obj, if any.
	// Returns true if successful; otherwise returns false.
	public boolean remove(Nucleotide obj) {
		int index = 0;
		for (ListNode2<Nucleotide> inspected =
				SENTINEL.getNext(); inspected != SENTINEL; inspected = inspected.getNext()) {
					if (inspected.getValue().equals(obj)) {
						remove(index);
						nodeCount--;
						return true;
					}
					index++;
		}
		return false;
	}

	// Returns the i-th element.
	public Nucleotide get(int i) {
		int index = 0;
		ListNode2<Nucleotide> inspected = SENTINEL.getNext();
		while (index < i) {
			inspected = inspected.getNext();
			index++;
		}
		return inspected.getValue();
	}

	// Replaces the i-th element with obj and returns the old value.
	public Nucleotide set(int i, Nucleotide obj) {
		int index = 0;
		ListNode2<Nucleotide> inspected = SENTINEL.getNext();
		while (index < i) {
			inspected = inspected.getNext();
			index++;
		}
		Nucleotide returnValue = inspected.getValue();
		inspected.setValue(obj);
		return returnValue;
	}

	// Inserts obj to become the i-th element. Increments the size
	// of the list by one.
	public void add(int i, Nucleotide obj) {
		int index = 0;
		ListNode2<Nucleotide> inspected = SENTINEL.getNext();
		ListNode2<Nucleotide> addedNode = new ListNode2<Nucleotide>(obj);
		if (i == 0) {
			SENTINEL.setNext(addedNode);
			addedNode.setPrevious(SENTINEL);
			addedNode.setNext(inspected);
			inspected.setPrevious(addedNode);
		}
		while (index < i) {
			inspected = inspected.getNext();
			index++;
		}
		ListNode2<Nucleotide> nextNode = inspected.getNext();
		inspected.setNext(addedNode);
		addedNode.setPrevious(inspected);
		addedNode.setNext(nextNode);
		nextNode.setPrevious(addedNode);
	}

	// Removes the i-th element and returns its value.
	// Decrements the size of the list by one.
	public Nucleotide remove(int i) {
		int index = 0;
		ListNode2<Nucleotide> inspected = SENTINEL.getNext();
		while (index < i) {
			inspected = inspected.getNext();
			index++;
		}
		ListNode2<Nucleotide> previousNode = inspected.getPrevious();
		ListNode2<Nucleotide> nextNode = inspected.getNext();
		previousNode.setNext(nextNode);
		nextNode.setPrevious(previousNode);
		nodeCount--;
		return inspected.getValue();
	}

	// Returns a string representation of this list exactly like that for MyArrayList.
	public String toString() {
		StringBuilder returnString = new StringBuilder();
		returnString.append('[');
		for (ListNode2<Nucleotide> inspected =
				SENTINEL.getNext(); inspected != SENTINEL; inspected = inspected.getNext()) {
			if (inspected != SENTINEL.getPrevious()) {
				returnString.append(inspected.getValue());
				returnString.append(", ");
			}
			if (inspected == SENTINEL.getPrevious()) {
				returnString.append(inspected.getValue());
			}
		}
		returnString.append(']');
		return returnString.toString();
	}

	// Like question 7 on the SinglyLinkedList test:
	// Add a "segment" (another list) onto the end of this list
	public void addSegmentToEnd(DoublyLinkedList seg) {

	}

	// Like question 8 on the SinglyLinkedList test:
	// You are to remove the next 16 nodes from the list, after the node nodeBefore.
	// (on the test these nodes were assumed to contain CCCCCCCCGGGGGGGG, but here
	// you do not need to assume or check for that)
	public void removeCCCCCCCCGGGGGGGG(ListNode2<Nucleotide> nodeBefore) {

	}

	// Like question 9 on the SinglyLinkedList test:
	// You are to find and delete the first instance of seg in the list.
	// If seg is not in the list, return false, otherwise return true.
	public boolean deleteSegment(DoublyLinkedList seg) {

	}

	// Like question 10 on the SinglyLinkedList test:
	// Delete the last three nodes in the list
	// If there are not enough nodes, return false
	public boolean deleteLastThree() {

	}

	// Like question 11 on the SinglyLinkedList test:
	// Replaces every node containing "A" with three nodes containing "T" "A" "C"
	public void replaceEveryAWithTAC() {

	}

}
