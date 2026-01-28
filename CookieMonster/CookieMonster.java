import java.io.*;
import java.util.*;
import java.util.Stack;
import java.util.Queue;

//Queue language:
//poll = remove
//offer = add

// You are allowed (and expected!) to use either Java's ArrayDeque or LinkedList class to make stacks and queues


public class CookieMonster {

    private int [][] cookieGrid;
    private int numRows;
    private int numCols;
    
    //Constructs a CookieMonster from a file with format:
    //numRows numCols
    //<<rest of the grid, with spaces in between the numbers>>
    public CookieMonster(String fileName) {
		int row = 0;
		int col = 0;
		try
		{
			Scanner input = new Scanner(new File(fileName));

			numRows    = input.nextInt();  
			numCols    = input.nextInt();
			cookieGrid = new int[numRows][numCols];

			for (row = 0; row < numRows; row++) 
				for (col = 0; col < numCols; col++)
					cookieGrid[row][col] = input.nextInt();
			
			input.close();
		}
		catch (Exception e)
		{
			System.out.print("Error creating maze: " + e.toString());
			System.out.println("Error occurred at row: " + row + ", col: " + col);
		}

    }

    public CookieMonster(int [][] cookieGrid) {
        this.cookieGrid = cookieGrid;
        this.numRows    = cookieGrid.length;
        this.numCols    = cookieGrid[0].length;
    }

    //You may find it VERY helpful to write this helper method.  Or not!
	private boolean validPoint(int row, int col) {
		if (cookieGrid[row][col] != -1) {
			return true;
		} else {
			return false;
		}
	}
	
	/* RECURSIVELY calculates the route which grants the most cookies.
	 * Returns the maximum number of cookies attainable. */
	public int recursiveCookies() {
		return recursiveCookies(0,0);	
	}	
	
	// Returns the maximum number of cookies edible starting from (and including) cookieGrid[row][col]
	public int recursiveCookies(int row, int col) {
		//start by finding the max cookies starting at cookieGrid[numRows - 1][numCols - 1]

		//Base case: if you are at cookieGrid[numRows - 1][numCols - 1] return cookieGrid[numRows - 1][numCols - 1]
		//Each grid square has two paths: if you next move is to go down or if your next move is to go right

		//Make a new variable: int totalCookies = cookieGrid[row][col]

		//Store both paths and their respective max cookies
		//If the immediate next square of a path is a bomb or if it's on the very bottom row or far righ colum make its value -1
			//i.e if you're at cookieGrid[row][col] and cookieGrid[row][col + 1] is a bomb make path right's value -1
		//Compare the two paths and return which is greater

		//The actual recursion:
		//path right += recursiveCookies(row, col + 1)

		if (row == numRows - 1 && col == numCols - 1) {
			return cookieGrid[numRows - 1][numCols - 1];
		}
		//paths[0] is the down path
		//paths[1] is the right path
		int[] paths = new int[2];
		paths[0] = cookieGrid[row + 1][col];
		paths[1] = cookieGrid[row][col + 1];

		if (!validPoint(row + 1, col) || row == numRows - 1) {
			paths[0] = -1;
		}

		if (!validPoint(row, col + 1) || col == numCols - 1) {
			paths[1] = -1;
		}

		

		//CODE THIS
		return 0;
	}
	

	/* Calculate which route grants the most cookies using a QUEUE.
	 * Returns the maximum number of cookies attainable. */
    /* From any given position, always add the path right before adding the path down */
    public int queueCookies() {
		//CODE THIS
		return 0;
    }

    
    /* Calculate which route grants the most cookies using a stack.
 	 * Returns the maximum number of cookies attainable. */
    /* From any given position, always add the path right before adding the path down */
    public int stackCookies() {
		//CODE THIS
		return 0;
    }

}
