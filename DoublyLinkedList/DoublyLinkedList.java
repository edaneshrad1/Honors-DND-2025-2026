import java.util.List;

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
		if (nodeCount == 0) {
			return false;
		}
		if (obj == null) {
			for (ListNode2<Nucleotide> inspected =
					SENTINEL.getNext(); inspected != SENTINEL; inspected = inspected.getNext()) {
				if (inspected.getValue() == null) {
					return true;
				}
			}
		}
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
		if (nodeCount == 0) {
			return -1;
		}
		int index = 0;
		if (obj == null) {
			for (ListNode2<Nucleotide> inspected =
					SENTINEL.getNext(); inspected != SENTINEL; inspected = inspected.getNext()) {
				if (inspected.getValue() == null) {
					return index;
				}
				index++;
			}
		}
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
		if (nodeCount == 0) {
			return false;
		}
		int index = 0;
		if (obj == null) {
			for (ListNode2<Nucleotide> inspected =
					SENTINEL.getNext(); inspected != SENTINEL; inspected = inspected.getNext()) {
				if (inspected.getValue() == null) {
					remove(index);
					return true;
				}
				index++;
			}
		}
		for (ListNode2<Nucleotide> inspected =
				SENTINEL.getNext(); inspected != SENTINEL; inspected = inspected.getNext()) {
			if (inspected.getValue().equals(obj)) {
				remove(index);
				return true;
			}
			index++;
		}
		return false;
	}

	// Returns the i-th element.
	public Nucleotide get(int i) {
		if (i < 0 || i >= nodeCount) {
			throw new IndexOutOfBoundsException("Index is out of bounds");
		}
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
		if (i < 0 || i >= nodeCount) {
			throw new IndexOutOfBoundsException("Index is out of bounds");
		}
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
		if (nodeCount == 0 && i == 0) {
			add(obj);
			nodeCount++;
			return;
		}
		if (i < 0 || i >= nodeCount) {
			throw new IndexOutOfBoundsException("Index is out of bounds");
		}
		ListNode2<Nucleotide> addedNode = new ListNode2<Nucleotide>(obj);
		ListNode2<Nucleotide> inspected = SENTINEL;
		// Start at SENTINEL bc it makes it less awkward when trying to add at 0
		for (int index = 0; index < i; index++) {
			// go get the node at i
			inspected = inspected.getNext();
		}
		ListNode2<Nucleotide> nextNode = inspected.getNext();
		inspected.setNext(addedNode);
		addedNode.setPrevious(inspected);
		addedNode.setNext(nextNode);
		nextNode.setPrevious(addedNode);
		nodeCount++;
	}

	// Removes the i-th element and returns its value.
	// Decrements the size of the list by one.
	public Nucleotide remove(int i) {
		if (i < 0 || i >= nodeCount) {
			throw new IndexOutOfBoundsException("Index is out of bounds");
		}
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
		ListNode2<Nucleotide> lastNode = SENTINEL.getPrevious();
		ListNode2<Nucleotide> firstNodeOfSeg = seg.getHead();
		ListNode2<Nucleotide> lastNodeOfSeg = seg.getTail();
		if (nodeCount == 0) {
			SENTINEL.setNext(firstNodeOfSeg);
			firstNodeOfSeg.setPrevious(SENTINEL);
			lastNodeOfSeg.setNext(SENTINEL);
			SENTINEL.setPrevious(lastNodeOfSeg);
		}
		lastNode.setNext(firstNodeOfSeg);
		firstNodeOfSeg.setPrevious(lastNode);
		lastNodeOfSeg.setNext(SENTINEL);
		SENTINEL.setPrevious(lastNodeOfSeg);
	}

	// Like question 8 on the SinglyLinkedList test:
	// You are to remove the next 16 nodes from the list, after the node nodeBefore.
	// (on the test these nodes were assumed to contain CCCCCCCCGGGGGGGG, but here
	// you do not need to assume or check for that)
	public void removeCCCCCCCCGGGGGGGG(ListNode2<Nucleotide> nodeBefore) {
		int index = 0;
		for (ListNode2<Nucleotide> runner = nodeBefore; runner != SENTINEL; runner =
				runner.getNext()) {
			index++;
		}
		if (nodeCount < 16 || index < 16) {
			throw new IllegalArgumentException("List size is not big enough");
		}
		ListNode2<Nucleotide> inspected = nodeBefore.getNext();
		for (int i = 0; i < 16; i++) {
			inspected = inspected.getNext();
		}
		nodeBefore.setNext(inspected);
		inspected.setPrevious(nodeBefore);
		nodeCount -= 16;
	}

	// Like question 9 on the SinglyLinkedList test:
	// You are to find and delete the first instance of seg in the list.
	// If seg is not in the list, return false, otherwise return true.
	public boolean deleteSegment(DoublyLinkedList seg) {
		ListNode2<Nucleotide> inspected = SENTINEL.getNext();
		while (inspected != SENTINEL) {
			ListNode2<Nucleotide> runner = inspected;
			ListNode2<Nucleotide> segNode = seg.getHead();
			int matched = 0;
			while (segNode != seg.getSentinel() && runner != SENTINEL
					&& runner.getValue().equals(segNode.getValue())) {
				runner = runner.getNext();
				segNode = segNode.getNext();
				matched++;
			}
			if (matched == seg.size()) {
				ListNode2<Nucleotide> before = inspected.getPrevious();
				ListNode2<Nucleotide> after = runner;
				before.setNext(after);
				after.setPrevious(before);
				nodeCount -= seg.size();
				return true;
			}
			inspected = inspected.getNext();
		}
		return false;
	}

	// Like question 10 on the SinglyLinkedList test:
	// Delete the last three nodes in the list
	// If there are not enough nodes, return false
	public boolean deleteLastThree() {
		if (nodeCount < 3) {
			return false;
		}
		ListNode2<Nucleotide> fourthToLast =
				SENTINEL.getPrevious().getPrevious().getPrevious().getPrevious();
		fourthToLast.setNext(SENTINEL);
		SENTINEL.setPrevious(fourthToLast);
		nodeCount -= 3;
		return true;
	}

	// Like question 11 on the SinglyLinkedList test:
	// Replaces every node containing "A" with three nodes containing "T" "A" "C"
	public void replaceEveryAWithTAC() {
		ListNode2<Nucleotide> inspected = SENTINEL.getNext();
		while (inspected != SENTINEL) {
			ListNode2<Nucleotide> beforeInspected = inspected.getPrevious();
			ListNode2<Nucleotide> afterInspected = inspected.getNext();
			if (inspected.getValue().equals(Nucleotide.A)) {
				DoublyLinkedList addedSeg = new DoublyLinkedList();
				addedSeg.add(Nucleotide.T);
				addedSeg.add(Nucleotide.A);
				addedSeg.add(Nucleotide.C);
				beforeInspected.setNext(addedSeg.getHead());
				addedSeg.getHead().setPrevious(beforeInspected);
				addedSeg.getTail().setNext(afterInspected);
				afterInspected.setPrevious(addedSeg.getTail());
			}
			inspected = inspected.getNext();
		}
	}

}
