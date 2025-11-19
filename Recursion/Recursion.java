import java.util.ArrayList;

public class Recursion {

	// Prints the value of every node in the singly linked list with the given head,
	// but in reverse
	public static void printListInReverse(ListNode head) {
		if (head.getNext() != null) {
			printListInReverse(head.getNext());
		}
		System.out.println(head.getValue().toString());
	}

	// For the given 2D array of Strings, replaces the String at index[r][c]
	// with "infected" unless the String is "vaccinated";
	// also, any Strings they are orthogonally adjacent to index[r][c]
	// that are not "vaccinated" will also be infected, and any adjacent to
	// them as well etc.
	// Infecting someone who is already infected has no effect
	// Trying to infect outside the confines of the grid also has no effect
	// Precondition: grid has no null entries
	public static void infect(String[][] grid, int r, int c) {
		if (r < 0 || r >= grid.length) {
			return;
		}
		if (c < 0 || c >= grid[r].length) {
			return;
		}
		if (grid[r][c].toString().equals("vaccinated")
				|| grid[r][c].toString().equals("infected")) {
			return;
		}
		grid[r][c] = "infected";
		infect(grid, r + 1, c);
		infect(grid, r, c + 1);
		infect(grid, r, c - 1);
		infect(grid, r - 1, c);
	}

	// How many subsets are there of the numbers 1...n
	// that don't contain any consecutive integers?
	// e.g. for n = 4, the subsets are {}, {1}, {2}, {3}, {4},
	// {1, 3}, {1, 4}, {2, 4}
	// The other subsets of 1,2,3,4 that DO contain consecutive integers are
	// {1,2}, {2,3}, {3,4}, {1,2,3}, {1,2,4}, {1,3,4}, {1,2,3,4}
	// Precondition: n > 0
	public static long countNonConsecutiveSubsets(int n) {
		long returnValue = 0;
		if (n == 1) {
			returnValue += 2;
		} else if (n == 2) {
			returnValue += 3;
		} else {
			returnValue += (countNonConsecutiveSubsets(n - 1) + countNonConsecutiveSubsets(n - 2));
		}
		return returnValue;
	}

	// A kid at the bottom of the stairs can jump up 1, 2, or 3 stairs at a time.
	// How many different ways can they jump up n stairs?
	// Jumping 1-1-2 is considered different than jumping 1-2-1
	// Precondition: n > 0
	public static long countWaysToJumpUpStairs(int n) {
		long returnValue = 0;
		if (n == 1) {
			returnValue += 1;
		} else if (n == 2) {
			returnValue += 2;
		} else if (n == 3) {
			returnValue += 4;
		} else {
			returnValue += (countWaysToJumpUpStairs(n - 1) + countWaysToJumpUpStairs(n - 2)
					+ countWaysToJumpUpStairs(n - 3));
		}
		return returnValue;
	}

	// Everything above this line does NOT require a recursive helper method
	// ----------------------------------
	// Everything below this line requires a recursive helper method
	// Any recursive helper method you write MUST have a comment describing:
	// 1) what the helper method does/returns
	// 2) your description must include role of each parameter in the helper method

	// Prints all the subsets of str on separate lines
	// You may assume that str has no repeated characters
	// For example, subsets("abc") would print out "", "a", "b", "c", "ab", "ac",
	// "bc", "abc"
	// Order is your choice

	// This method sucks so much I want to blow up my computer
	// Helper that returns an arrayList of all subsets of the input string.
	// str - the input string (no repeated characters)
	public static ArrayList<String> printSubsetsHelper(String str) {
		ArrayList<String> returnList = new ArrayList<String>();
		if (str.length() == 0) {
			returnList.add("");
			return returnList;
		}
		ArrayList<String> restSubsets = new ArrayList<String>();
		char first = str.charAt(0);
		String rest = str.substring(1);
		restSubsets.addAll(printSubsetsHelper(rest));
		for (int i = 0; i < restSubsets.size(); i++) {
			returnList.add(restSubsets.get(i));
			returnList.add(first + restSubsets.get(i));
		}
		return returnList;
	}

	// "aghv"
	// "ghv"
	// a + "ghv"
	// "" "v" "h" "v"
	// a + subsets("gh")
	// a + "hv", a + "h", a + "v", a + ""
	// "g" "gv" "gh"
	public static void printSubsets(String str) {
		ArrayList<String> subsets = printSubsetsHelper(str);
		for (int i = 0; i < subsets.size(); i++) {
			System.out.println(subsets.get(i));
		}
	}

	// List contains a single String to start.
	// Prints all the permutations of str on separate lines
	// You may assume that str has no repeated characters
	// For example, permute("abc") could print out "abc", "acb", "bac", "bca",
	// "cab", "cba"
	// Order is your choice

	// This method is stupid, I hate it, more than printSubsets, this one sucks so
	// much more
	// Returns a String array list of every permutation of str
	// str is the input string
	public static ArrayList<String> printPermutationsHelper(String str) {
		ArrayList<String> returnList = new ArrayList<String>();
		if (str.length() == 2) {
			returnList.add(str);
			returnList.add("" + str.charAt(1) + str.charAt(0));
			return returnList;
		}
		ArrayList<String> restPermutations = new ArrayList<String>();
		char first = str.charAt(0);
		String rest = str.substring(1);
		restPermutations.addAll(printPermutationsHelper(rest));
		for (int i = 0; i < restPermutations.size(); i++) {
			for (int j = 0; j <= restPermutations.get(i).length(); j++) {
				String addedString =
						printPermutationsHelperHelper(restPermutations.get(i), j, first);
				returnList.add(addedString);
			}
		}
		return returnList;
	}

	// This helper method inserts a char at a specific index in a string
	// str is the string
	// i is the index its being inserted at
	// ch is the char being inserted
	public static String printPermutationsHelperHelper(String str, int i, char ch) {
		String returnString = str.substring(0, i) + ch + str.substring(i, str.length());
		return returnString;
	}

	public static void printPermutations(String str) {
		ArrayList<String> permutations = printPermutationsHelper(str);
		for (int i = 0; i < permutations.size(); i++) {
			System.out.println(permutations.get(i));
		}
	}

	// A helper method that splits the origional array into a sub array from index
	// left to right
	// arr is the origional arrray
	public static int[] sort(int[] arr) {
		if (arr.length <= 1) {
			return arr;
		}
		int midIndex = arr.length / 2;
		int[] arrLeft = new int[midIndex];
		for (int i = 0; i < arrLeft.length; i++) {
			arrLeft[i] = arr[i];
		}
		int[] arrRight = new int[arr.length - midIndex];
		for (int i = 0; i < arrRight.length; i++) {
			arrRight[i] = arr[midIndex + i];
		}
		int[] sortedLeft = sort(arrLeft);
		int[] sortedRight = sort(arrRight);
		return merge(sortedLeft, sortedRight);
	}

	// method that takes two sorted arrays and merges them into one sorted array
	// returning that sorted array
	// arrLeft is one of the two arrays being merged and arrRight is the other
	public static int[] merge(int[] arrLeft, int[] arrRight) {
		int[] returnArray = new int[arrLeft.length + arrRight.length];
		int leftIndex = 0;
		int rightIndex = 0;
		for (int i = 0; i < returnArray.length; i++) {
			if (leftIndex >= arrLeft.length) {
				returnArray[i] = arrRight[rightIndex];
				rightIndex++;
			} else if (rightIndex >= arrRight.length) {
				returnArray[i] = arrLeft[leftIndex];
				leftIndex++;
			} else {
				int difference = arrLeft[leftIndex] - arrRight[rightIndex];
				if (difference < 0) {
					returnArray[i] = arrLeft[leftIndex];
					leftIndex++;
				} else {
					returnArray[i] = arrRight[rightIndex];
					rightIndex++;
				}
			}
		}
		return returnArray;
	}

	public static void mergeSort(int[] ints) {
		int[] sortedInts = sort(ints);
		for (int i = 0; i < ints.length; i++) {
			ints[i] = sortedInts[i];
		}
	}

	// Performs a quickSort on the given array of ints
	// Use the middle element (index n/2) as the pivot
	// Precondition: you may assume there are NO duplicates!!!
	// Yo Mr. Theiss, this method is the biggest abomination to the existence of humanity, I swear
	public static void quickSort(int[] ints) {
		int[] sortedInts = quickSortHelper(ints);
		for (int i = 0; i < ints.length; i++) {
			ints[i] = sortedInts[i];
		}
	}

	// A helper method that returns a sorted ArrayList of the elements of ints
	// ints is the origional array being sorted
	public static int[] quickSortHelper(int[] ints) {
		if (ints.length <= 1) {
			return ints;
		}

		int pivot = ints[ints.length / 2];

		int lessCount = 0;
		int greaterCount = 0;

		for (int i = 0; i < ints.length; i++) {
			if (ints[i] < pivot) {
				lessCount++;
			} else if (ints[i] > pivot) {
				greaterCount++;
			}
		}

		int[] left = new int[lessCount];
		int[] right = new int[greaterCount];

		int leftIndex = 0;
		int rightIndex = 0;

		for (int i = 0; i < ints.length; i++) {
			if (ints[i] < pivot) {
				left[leftIndex] = ints[i];
				leftIndex++;
			} else if (ints[i] > pivot) {
				right[rightIndex] = ints[i];
				rightIndex++;
			}
		}

		int[] leftSorted = quickSortHelper(left);
		int[] rightSorted = quickSortHelper(right);
		int[] finalSorted = new int[leftSorted.length + rightSorted.length + 1];

		int index = 0;

		for (int i = 0; i < leftSorted.length; i++) {
			finalSorted[index] = leftSorted[i];
			index++;
		}

		finalSorted[index] = pivot;
		index++;

		for (int i = 0; i < rightSorted.length; i++) {
			finalSorted[index] = rightSorted[i];
			index++;
		}
		return finalSorted;
	}

	// Prints a sequence of moves (one on each line)
	// to complete a Towers of Hanoi problem:
	// disks start on tower 0 and must end on tower 2.
	// The towers are number 0, 1, 2, and each move should be of
	// the form "1 -> 2", meaning "take the top disk of tower 1 and
	// put it on tower 2" etc.
	public static void solveHanoi(int startingDisks) {
		if (startingDisks == 1) {
			System.out.println("" + 0 + " -> " + 2);
			return;
		}
		solveHanoiHelper(startingDisks, 0, 2);
	}

	public static void solveHanoiHelper(int startingDisks, int start, int destination) {
		int free = 0;
		if (!(start == 2 || destination == 2)) {
			free = 2;
		} else if (!(start == 1 || destination == 1)) {
			free = 1;
		}
		if (startingDisks == 2) {
			System.out.println("" + start + " -> " + free);
			System.out.println("" + start + " -> " + destination);
			System.out.println("" + free + " -> " + destination);
		} else {
			solveHanoiHelper(startingDisks - 1, start, free);
			System.out.println("" + start + " -> " + destination);
			solveHanoiHelper(startingDisks - 1, free, destination);
		}
	}

	// You are partaking in a scavenger hunt!
	// You've gotten a secret map to find many of the more difficult
	// items, but they are only available at VERY specific times at
	// specific places. You have an array, times[], that lists at which
	// MINUTE an item is available. Times is sorted in ascending order.
	// Items in the ScavHunt are worth varying numbers of points.
	// You also have an array, points[], same length as times[],
	// that lists how many points each of the corresponding items is worth.
	// Problem is: to get from one location to the other takes 5 minutes,
	// so if there is an item, for example, available at time 23 and another
	// at time 27, it's just not possible for you to make it to both: you'll
	// have to choose!
	// (but you COULD make it from a place at time 23 to another at time 28)
	// Write a method that returns the maximum POINTS you can get.
	// For example, if times = [3, 7, 9]
	// and points = [10, 15, 10]
	// Then the best possible result is getting the item at time 3 and the one at
	// time 9
	// for a total of 20 points, so it would return 20.
	public static int scavHunt(int[] times, int[] points) {
		return findMaxReward(times, points, 0);
	}

	// Method that returns the max reward after the current index
	// times is the array of different times
	// points is the array of differents points corresponding with the time
	// index is the index of times we are starting from
	public static int findMaxReward(int[] times, int[] points, int index) {
		if (index >= times.length) {
			return 0;
		}

		int skip = findMaxReward(times, points, index + 1);
		// skip is the points you get if you skip at index

		int[] possibleNextTimes = findPossibleNextTimes(times, index);
		int[] possibleNextPoints = new int[possibleNextTimes.length];

		if (possibleNextTimes.length > 0) {
			int start = index + 1;
			while (start < times.length && times[start] != possibleNextTimes[0]) {
				start++;
			}

			for (int i = 0; i < possibleNextTimes.length; i++) {
				possibleNextPoints[i] = points[start + i];
			}
		}

		int take = points[index] + findMaxReward(possibleNextTimes, possibleNextPoints, 0);
		// take is the points you get if you take at index

		return Math.max(skip, take);
	}

	// returns an array of ints with all the possible next times after choosing the
	// points at index
	// index is the time you chose the points at
	// times is the array of different times
	public static int[] findPossibleNextTimes(int[] times, int index) {
		if (index >= times.length - 1) {
			return new int[0];
		}

		int change = 2; // change is the amount of indices in times after index ie. one after index,
						// change = 1; two after index, change = 2; etc

		if (times[index + 1] - times[index] >= 5) {
			int[] nextTimes = new int[times.length - index - 1];
			for (int i = 0; i < nextTimes.length; i++) {
				nextTimes[i] = times[index + i + 1];
			}
			return nextTimes;
		} else {
			while (index + change < times.length && times[index + change] - times[index] < 5) {
				change++;
			}
			if (index + change >= times.length) {
				return new int[0];
			}
			int[] nextTimes = new int[times.length - index - change];
			for (int i = 0; i < nextTimes.length; i++) {
				nextTimes[i] = times[index + i + change];
			}
			return nextTimes;
		}
	}
}
