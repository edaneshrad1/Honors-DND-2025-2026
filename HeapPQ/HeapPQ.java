
public class HeapPQ<E extends Comparable<E>> implements MyPriorityQueue<E> {

	private E[] heap;
	private int objectCount;

	public HeapPQ() {
		this.heap = (E[]) new Comparable[3];
		this.objectCount = 0;
	}

	// Returns the number of elements in the priority queue
	public int size() {
		return objectCount;
	}

	// DO NOT CHANGE MY JANKY TOSTRING!!!!!
	public String toString() {
		StringBuffer stringbuf = new StringBuffer("[");
		for (int i = 0; i < objectCount; i++) {
			stringbuf.append(heap[i]);
			if (i < objectCount - 1)
				stringbuf.append(", ");
		}
		stringbuf.append("]\nor alternatively,\n");

		for (int rowLength = 1, j = 0; j < objectCount; rowLength *= 2) {
			for (int i = 0; i < rowLength && j < objectCount; i++, j++) {
				stringbuf.append(heap[j] + " ");
			}
			stringbuf.append("\n");
			if (j < objectCount) {
				for (int i = 0; i < Math.min(objectCount - j, rowLength * 2); i++) {
					if (i % 2 == 0)
						stringbuf.append("/");
					else
						stringbuf.append("\\ ");
				}
				stringbuf.append("\n");
			}
		}
		return stringbuf.toString();
	}

	// Doubles the size of the heap array
	private void increaseCapacity() {
		E[] heapArray = (E[]) new Comparable[heap.length * 2];
		for (int i = 0; i < heap.length; i++) {
			heapArray[i] = heap[i];
		}
		this.heap = heapArray;
	}

	// Returns the index of the "parent" of index i
	private int parent(int i) {
		return (i + 1) / 2 - 1;
	}

	// Returns the index of the *smaller child* of index i
	private int smallerChild(int i) {
		int childLeft = (i + 1) * 2;
		int childRight = (i + 1) * 2 - 1;
		if (heap[childLeft].compareTo(heap[childRight]) < 0) {
			return childLeft;
		} else {
			return childRight;
		}
	}

	//Returns the index of the larger child of index i
	private int largerChild(int i) {
		int childLeft = (i + 1) * 2;
		int childRight = (i + 1) * 2 - 1;
		if (heap[childLeft].compareTo(heap[childRight]) > 0) {
			return childLeft;
		} else {
			return childRight;
		}
	}

	// Swaps the contents of indices i and j
	private void swap(int i, int j) {
		E iValue = heap[i];
		E jValue = heap[j];
		heap[i] = jValue;
		heap[j] = iValue;
	}

	// Bubbles the element at index i upwards until the heap properties hold again.
	private void bubbleUp(int i) {
		//compare element at i with its parent
		//if element at i is greater than that of its parent swap them
			//call bubbleUp on the parent
		//if not return

		if (heap[i].compareTo(heap[parent(i)]) > 0) {
			swap(i, parent(i));
			bubbleUp(parent(i));
		} else {
			return;
		}
	}

	// Bubbles the element at index i downwards until the heap properties hold again.
	private void bubbleDown(int i) {
		//compare element at i with its larger child
		//if element at i is less than that of its larger child swap them
			//call bubbleDown on the larger child
		//if not return
		if (heap[i].compareTo(heap[largerChild(i)]) < 0) {
			swap(i, largerChild(i));
			bubbleDown(largerChild(i));
		} else {
			return;
		}
	}

	@Override
	public void add(E obj) {
		//if array is full resize it
		if (heap.length == objectCount) {
			increaseCapacity();
		}
		//make the first available index of heap obj
		heap[objectCount] = obj;

		//bubble up from that index
		bubbleUp(objectCount);
		
		//add to objectCount
		objectCount++;
	}

	@Override
	public E removeMin() {
		//store first element as removed
		E removed = heap[0];

		//swap top/first and last elements
		swap(0, objectCount);

		//make the last element null
		heap[objectCount] = null;

		//call bubbleDown on the top element
		bubbleDown(0);

		//subtract from objectCount
		objectCount--;

		return removed;
	}

	@Override
	public E peek() {
		return heap[0];
	}

	@Override
	public boolean isEmpty() {
		return objectCount == 0;
	}

}
