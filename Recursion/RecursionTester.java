public class RecursionTester {
    public static void main(String[] args) {
        ListNode third = new ListNode("yoyoyo", null);
        ListNode second = new ListNode("whaddup", third);
        ListNode head = new ListNode("head is here", second);

        String[][] matrix = {{"normal", "infected", "vaccinated", "infected"},
                {"normal", "infected", "normal", "normal"},
                {"vaccinated", "vaccinated", "normal", "infected"},
                {"normal", "infected", "vaccinated", "normal"}};
                
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

        Recursion.printSubsets("abc");
    }
}
