public class RecursionTester {
    public static void main(String[] args) {
        ListNode third = new ListNode("yoyoyo", null);
        ListNode second = new ListNode("whaddup", third);
        ListNode head = new ListNode("head is here", second);

        //Call my method with the 'head'
        Recursion.printListInReverse(head);
    }
}