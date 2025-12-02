import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class RecursionTester {
    public static void main(String[] args) {
        ListNode third = new ListNode("yoyoyo", null);
        ListNode second = new ListNode("whaddup", third);
        ListNode head = new ListNode("head is here", second);

        String[][] matrix = {{"normal", "infected", "vaccinated", "infected"},
                {"normal", "infected", "normal", "normal"},
                {"vaccinated", "vaccinated", "normal", "infected"},
                {"normal", "infected", "vaccinated", "normal"}};


        int[] sortedInts1 = {2, 3, 6, 12, 33};
        int[] sortedInts2 = {1, 4, 9, 22};

        int[] unsortedInts1 = {0, 7, 18, 2, 1, 20, 5, 8, 16, 3, 22 };
        // Call my method with the 'head'
        // Recursion.printListInReverse(head);

        // Recursion.infect(matrix, 0, 1);
        // for (int row = 0; row < matrix.length; row++) {
        // for (int col = 0; col < matrix[row].length; col++) {
        // System.out.print(matrix[row][col] + ", ");
        // }
        // System.out.println();
        // }

        // System.out.println(Recursion.countNonConsecutiveSubsets(22));

        // System.out.println(Recursion.countWaysToJumpUpStairs(5));

        // Recursion.printSubsets("hum");

        // Recursion.printPermutations("hum");

        // Recursion.quickSort(unsortedInts1);

        // Recursion.mergeSort(unsortedInts1);
        // for (int i = 0; i < unsortedInts1.length; i++) {
        //     System.out.println(unsortedInts1[i]);
        // }

        // Recursion.quickSortHelper(unsortedInts1);

        // int[] ints = Recursion.merge(sortedInts1, sortedInts2);
        // for (int i = 0; i < ints.length; i++) {
        // System.out.print(ints[i] + ", ");
        // }

        // int[] sortedInts = Recursion.sort(unsortedInts1);
        // for (int i = 0; i < sortedInts.length; i++) {
        // System.out.println(sortedInts[i]);
        // }

        // for (int i = 0; i < unsortedInts1.length; i++) {
        // System.out.println(unsortedInts1[i]);
        // }

        // Recursion.solveHanoi(8);

        int[] times = {2, 7, 9, 10, 12, 18, 22, 23, 28, 33};
        int[] points = {5, 8, 9, 11, 22, 11, 32, 15, 6692, 8};
        System.out.println(Recursion.scavHunt(times, points));

        // Recursion.quickSort(unsortedInts1);
    }
}
