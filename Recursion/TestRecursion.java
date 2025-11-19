import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class TestRecursion {
	private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private static final PrintStream originalOut = System.out;

	@BeforeAll
	public static void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

	@AfterAll
	public static void restoreStreams() {
		System.setOut(originalOut);
	}

	@Test
	@DisplayName("[5] Test countNonConsecutiveSubsets.")
	public void testCountNonConsecSubsets() {
		int n = 5;
		assertEquals(13, Recursion.countNonConsecutiveSubsets(n));
		n = 7;
		assertEquals(34, Recursion.countNonConsecutiveSubsets(n));
		n = 12;
		assertEquals(377, Recursion.countNonConsecutiveSubsets(n));
	}

	@Test
	@DisplayName("[5] Test countWaysToJumpUpStairs.")
	public void testStairs() {
		int n = 5;
		assertEquals(13, Recursion.countWaysToJumpUpStairs(n));
		n = 7;
		assertEquals(44, Recursion.countWaysToJumpUpStairs(n));
		n = 10;
		assertEquals(274, Recursion.countWaysToJumpUpStairs(n));
	}

	@Test
	@DisplayName("[5] Test printListInReverse.")
	public void testPrintListInReverse() {
		ListNode head = new ListNode("Anglerfish");
		ListNode node = head;
		for (int i = 0; i < 5; i++) {
			node.setNext(new ListNode("" + i + i * i));
			node = node.getNext();
		}
		String solution = "416\n39\n24\n11\n00\nAnglerfish\n";
		outContent.reset();
		Recursion.printListInReverse(head);
		assertEquals(solution, outContent.toString());
		outContent.reset();
	}

	@Test
	@DisplayName("[5] Test infect.")
	public void testInfect() {
		String[][] grid = { { "----------", "vaccinated", "----------", "----------", "----------" },
				{ "vaccinated", "----------", "----------", "----------", "----------" },
				{ "----------", "----------", "vaccinated", "vaccinated", "vaccinated" },
				{ "----------", "----------", "----------", "vaccinated", "----------" } };
		Recursion.infect(grid, 1, 2);

		String[][] gridSol = { { "----------", "vaccinated", "infected", "infected", "infected" },
				{ "vaccinated", "infected", "infected", "infected", "infected" },
				{ "infected", "infected", "vaccinated", "vaccinated", "vaccinated" },
				{ "infected", "infected", "infected", "vaccinated", "----------" } };

		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[0].length; c++) {
				assertEquals(gridSol[r][c], grid[r][c]);
			}
		}

	}

	@Test
	@DisplayName("[5] Test printPermutations.")
	public void testPrintPermutations() {
		String str = "hum";

		ArrayList<String> solList = new ArrayList<String>();
		solList.add("hum");
		solList.add("hmu");
		solList.add("uhm");
		solList.add("umh");
		solList.add("mhu");
		solList.add("muh");

		Collections.sort(solList);
		outContent.reset();
		Recursion.printPermutations(str);
		String student = outContent.toString();
		Scanner studentScanner = new Scanner(student);
		ArrayList<String> studentList = new ArrayList<String>();
		while (studentScanner.hasNext()) {
			studentList.add(studentScanner.nextLine());
		}
		studentScanner.close();
		Collections.sort(studentList);
		for (int i = 0; i < solList.size(); i++) {
			assertEquals(solList.get(i), studentList.get(i));
		}
		outContent.reset();
	}

	@Test
	@DisplayName("[5] Test printSubsets.")
	public void testPrintSubsets() {
		String str = "hum";
		ArrayList<String> solList = new ArrayList<String>();
		solList.add("");
		solList.add("m");
		solList.add("u");
		solList.add("h");
		solList.add("um");
		solList.add("hm");
		solList.add("hu");
		solList.add("hum");

		sortStrings(solList);
		Collections.sort(solList);
		outContent.reset();
		Recursion.printSubsets(str);
		String student = outContent.toString();
		Scanner studentScanner = new Scanner(student);
		ArrayList<String> studentList = new ArrayList<String>();
		while (studentScanner.hasNextLine()) {
			studentList.add(studentScanner.nextLine());
		}
		studentScanner.close();
		sortStrings(studentList);
		Collections.sort(studentList);
		for (int i = 0; i < solList.size(); i++) {
			assertEquals(solList.get(i), studentList.get(i));
		}
		outContent.reset();
	}

	private static void sortStrings(ArrayList<String> list) {
		for (int i = 0; i < list.size(); i++) {
			String s = list.get(i);
			char[] chars = s.toCharArray();
			Arrays.sort(chars);
			list.set(i, new String(chars));
		}
	}

	@Test
	@DisplayName("[5] Test mergeSort.")
	public void testMergeSort() {
		int[] arr = { 3, 7, 18, 2, 1, 20, 5, 8, 16, 0 };
		Recursion.mergeSort(arr);
		int[] arr2 = { 0, 1, 2, 3, 5, 7, 8, 16, 18, 20 };
		for (int i = 0; i < arr.length; i++) {
			assertEquals(arr2[i], arr[i]);
		}

	}

	@Test
	@DisplayName("[5] Test quickSort.")
	public void testQuickSort() {
		int[] arr = { 3, 7, 18, 2, 1, 20, 5, 8, 16, 0 };
		Recursion.quickSort(arr);
		int[] arr2 = { 0, 1, 2, 3, 5, 7, 8, 16, 18, 20 };
		for (int i = 0; i < arr.length; i++) {
			assertEquals(arr2[i], arr[i]);
		}

	}

	@Test
	@DisplayName("[5] Test solveHanoi.")
	public void testSolveHanoi() {
		int n = 3;
		String solution = "0 -> 2\n"
				+ "0 -> 1\n"
				+ "2 -> 1\n"
				+ "0 -> 2\n"
				+ "1 -> 0\n"
				+ "1 -> 2\n"
				+ "0 -> 2\n";
		outContent.reset();
		Recursion.solveHanoi(n);
		assertEquals(solution, outContent.toString());
		outContent.reset();
	}

	@Test
	@DisplayName("[5] Test scavHunt.")
	public void testScavHunt() {
		int[] times = { 6, 7, 12, 14, 16, 18, 20, 22, 23, 25 };
		int[] points = { 5, 6, 5, 1, 3, 4, 7, 4, 5, 3 };
		assertEquals(21, Recursion.scavHunt(times, points));
	}

}