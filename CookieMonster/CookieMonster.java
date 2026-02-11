import java.io.*;
import java.util.*;

// Queue language:
// poll = remove
// offer = add

// You are allowed (and expected!) to use either Java's ArrayDeque or LinkedList class to make
// stacks and queues


public class CookieMonster {

	private int[][] cookieGrid;
	private int numRows;
	private int numCols;

	// Constructs a CookieMonster from a file with format:
	// numRows numCols
	// <<rest of the grid, with spaces in between the numbers>>
	public CookieMonster(String fileName) {
		int row = 0;
		int col = 0;
		try {
			Scanner input = new Scanner(new File(fileName));

			numRows = input.nextInt();
			numCols = input.nextInt();
			cookieGrid = new int[numRows][numCols];

			for (row = 0; row < numRows; row++)
				for (col = 0; col < numCols; col++)
					cookieGrid[row][col] = input.nextInt();

			input.close();
		} catch (Exception e) {
			System.out.print("Error creating maze: " + e.toString());
			System.out.println("Error occurred at row: " + row + ", col: " + col);
		}

	}

	public CookieMonster(int[][] cookieGrid) {
		this.cookieGrid = cookieGrid;
		this.numRows = cookieGrid.length;
		this.numCols = cookieGrid[0].length;
	}

	// You may find it VERY helpful to write this helper method. Or not!
	private boolean validPoint(int row, int col) {
		if (cookieGrid[row][col] != -1) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * RECURSIVELY calculates the route which grants the most cookies. Returns the maximum number of
	 * cookies attainable.
	 */
	public int recursiveCookies() {
		if (numRows == 1 && numCols == 1) {
			if (cookieGrid[0][0] == -1) {
				return 0;
			} else {
				return cookieGrid[0][0];
			}
		} else {
			return recursiveCookies(0, 0);
		}
	}

	// Returns the maximum number of cookies edible starting from (and including)
	// cookieGrid[row][col]
	public int recursiveCookies(int row, int col) {
		int maxCookies = 0;
		// if you're at the bottom right of the matrix add that value
		if (row == numRows - 1 && col == numCols - 1) {
			return cookieGrid[row][col];
		} else {
			// store the two paths you can go to from your current position
			int[] paths = new int[2];

			// paths[0] is the max for the right path
			// paths[1] is the max for the down path

			// if the square to the right is a bomb or doesn't exist paths[0] is invalid
			if (col == numCols - 1 || cookieGrid[row][col + 1] == -1) {
				paths[0] = -1;
			} else {
				// the right path is equal to the cookies to the right + the max path from there
				paths[0] = recursiveCookies(row, col + 1);
			}

			// if the square below is a bomb or doesn't exist paths[1] is invalid
			if (row == numRows - 1 || cookieGrid[row + 1][col] == -1) {
				paths[1] = -1;
			} else {
				// the down path is equal to the cookies below + the max path from there
				paths[1] = recursiveCookies(row + 1, col);
			}

			if (paths[0] > paths[1]) {
				maxCookies += paths[0];
			} else {
				maxCookies += paths[1];
			}
		}
		return maxCookies + cookieGrid[row][col];
	}


	/*
	 * Calculate which route grants the most cookies using a QUEUE. Returns the maximum number of
	 * cookies attainable.
	 */
	/* From any given position, always add the path right before adding the path down */
	public int queueCookies() {
		// send an orphan to the top left
		// make a new queue of orphans
		// make a new list of the maxes
		// get the right and down orphans
		// add them to the queue (add the right one first)

		int max = 0;

		// edge case: if the grid has only one square
		if (numRows == 1 && numCols == 1) {
			// if its a bomb return 0
			if (cookieGrid[0][0] == -1) {
				return 0;
			}
			// if it's not a bomb return the cookies on that square
			else {
				return cookieGrid[0][0];
			}
		}

		// make a queue of orphans
		Queue<OrphanScout> orphans = new ArrayDeque<OrphanScout>();

		// make a new orphan at the start
		OrphanScout orphy = new OrphanScout(0, 0, 0);

		// offer the start orphan's right and down into the queue assuming they exist
		if (getOrphanRight(orphy) != null) {
			orphans.offer(getOrphanRight(orphy));
		}

		if (getOrphanDown(orphy) != null) {
			orphans.offer(getOrphanDown(orphy));
		}

		while (!orphans.isEmpty()) {
			// instantiate the current orphan as the orphan at the front of the queue
			OrphanScout currentOrphan = orphans.peek();
			// poll that orphan
			orphans.poll();
			int row = currentOrphan.getEndingRow();
			int col = currentOrphan.getEndingCol();

			// if the current orphan goes to a bomb and dies :(
			if (cookieGrid[row][col] == -1) {
				// compare only the cookies discovered with max
				if (currentOrphan.getCookiesDiscovered() > max) {
					max = currentOrphan.getCookiesDiscovered();
				}
				continue;
			}

			// if the orphan is at the bottom right compare its cookies discovered + current cookies
			// with max
			if (row == numRows - 1 && col == numCols - 1) {
				if (currentOrphan.getCookiesDiscovered() + cookieGrid[row][col] > max) {
					max = currentOrphan.getCookiesDiscovered() + cookieGrid[row][col];
				}
				continue;
			}

			// get the right and down orphans for the current orphan
			OrphanScout orphanRight = getOrphanRight(currentOrphan);
			OrphanScout orphanDown = getOrphanDown(currentOrphan);

			// offer the right and down orphans to the queue assuming they exist
			if (orphanRight != null) {
				orphans.offer(orphanRight);
			}

			if (orphanDown != null) {
				orphans.offer(orphanDown);
			}
		}

		return max;
	}


	/*
	 * Calculate which route grants the most cookies using a stack. Returns the maximum number of
	 * cookies attainable.
	 */
	/* From any given position, always add the path right before adding the path down */
	public int stackCookies() {
		// CODE THIS

		// send orphan to top left (or just any square)
		// get that orphans cookies
		// if that orphan comes back
		// create two new orphans: right and down
		// add them to a stack (add the down orphan first)
		// send the right orphan in add its result to a list and pop it from the stack
		// when an orphan doesn't come back add -1 to the list and send the next orphan in

		int max = 0;

		// edge case: if the grid has only one square
		if (numRows == 1 && numCols == 1) {
			// if its a bomb return 0
			if (cookieGrid[0][0] == -1) {
				return 0;
			}
			// if it's not a bomb return the cookies on that square
			else {
				return cookieGrid[0][0];
			}
		}

		// make a stack of orphans
		Stack<OrphanScout> orphans = new Stack<OrphanScout>();

		// make a new orphan at the start
		OrphanScout orphy = new OrphanScout(0, 0, 0);

		// push the start orphan's right and down onto the stack assuming they exist
		if (getOrphanDown(orphy) != null) {
			orphans.push(getOrphanDown(orphy));
		}

		if (getOrphanRight(orphy) != null) {
			orphans.push(getOrphanRight(orphy));
		}

		while (!orphans.empty()) {
			// instantiate the current orphan as the orphan at the top of the stack
			OrphanScout currentOrphan = orphans.peek();
			// pop that orphan
			orphans.pop();
			int row = currentOrphan.getEndingRow();
			int col = currentOrphan.getEndingCol();

			// if the current orphan goes to a bomb and dies :(
			if (cookieGrid[row][col] == -1) {
				// compare only the cookies discovered with max
				if (currentOrphan.getCookiesDiscovered() > max) {
					max = currentOrphan.getCookiesDiscovered();
				}
				continue;
			}

			// if the orphan is at the bottom right compare its cookies discovered + current cookies
			// with max
			if (row == numRows - 1 && col == numCols - 1) {
				if (currentOrphan.getCookiesDiscovered() + cookieGrid[row][col] > max) {
					max = currentOrphan.getCookiesDiscovered() + cookieGrid[row][col];
				}
				continue;
			}

			// get the right and down orphans for the current orphan
			OrphanScout orphanRight = getOrphanRight(currentOrphan);
			OrphanScout orphanDown = getOrphanDown(currentOrphan);

			// push the right and down orphans onto the stack assuming they exist
			if (orphanDown != null) {
				orphans.push(orphanDown);
			}

			if (orphanRight != null) {
				orphans.push(orphanRight);
			}
		}

		return max;
	}

	public OrphanScout getOrphanRight(OrphanScout orphy) {
		if (orphy.getEndingCol() < numCols - 1) {
			int endRow = orphy.getEndingRow();
			int endCol = orphy.getEndingCol() + 1;
			int currentCookies = cookieGrid[orphy.getEndingRow()][orphy.getEndingCol()];
			return new OrphanScout(endRow, endCol, orphy.getCookiesDiscovered() + currentCookies);
		} else {
			return null;
		}
	}

	public OrphanScout getOrphanDown(OrphanScout orphy) {
		if (orphy.getEndingRow() < numRows - 1) {
			int endRow = orphy.getEndingRow() + 1;
			int endCol = orphy.getEndingCol();
			int currentCookies = cookieGrid[orphy.getEndingRow()][orphy.getEndingCol()];
			return new OrphanScout(endRow, endCol, orphy.getCookiesDiscovered() + currentCookies);
		} else {
			return null;
		}
	}
}
