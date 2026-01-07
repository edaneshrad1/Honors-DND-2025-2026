public class SortedArrayList<E extends Comparable<E>> extends MyArrayList<E>{

	//Compare to:
	//a.compareTo(b)
	//if a > b, returns a posotive number
	//if a < b, returns a negative number
	//if a = b, returns 0


	@Override
	public boolean contains(E obj) {
		int foundIndex = binarySearch(this, obj);
		if (foundIndex == -1) {
			return false;
		} else {
			return true;
		}
	}

	public  int binarySearch(SortedArrayList<E> arr, E key) {
        return binarySearchRecursiveHelper(arr, key, 0, arr.size() - 1);
    }

    /**
     * Implements the binary search algorithm to find and return the index of a given element in a
     * sorted array if it is between low and high, or -1 if it is not found between low and high.
     */
    public  int binarySearchRecursiveHelper(SortedArrayList<E> arr, E key, int low, int high) {
        if (low > high) {
            return -1; // Element not found
        }
        int mid = low + (high - low) / 2;
        if (arr.get(mid).compareTo(key) == 0) {
            return mid; // Element found
        }
        if (arr.get(mid).compareTo(key) > 0) {
            return binarySearchRecursiveHelper(arr, key, low, mid - 1); // Search left half
        } else {
            return binarySearchRecursiveHelper(arr, key, mid + 1, high); // Search right half
        }
    }
	
	//May not contain more than one of the same object
	@Override
	public boolean add(E obj) {
		int indexChange = 0;
		for (int i = 0; i < size(); i++) {
			if (get(i).compareTo(obj) > 0) {
				indexChange = i;
				add(indexChange, obj);
				return true;
			}
		}
		super.add(obj);
		return true;
	}
	
	@Override
	public boolean remove(E obj) {
		for (int i = 0; i <= size(); i++) {
			if (get(i).equals(obj)) {
				super.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public E min() {
		//gets the first & smallest object
		E min = get(0);
		return min;
	}
	
	public E max() {
		//gets the last & largest object
		E max = get(size() - 1);
		return max;
	}
}
