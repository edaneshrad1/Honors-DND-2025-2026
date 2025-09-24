public class SinglyLinkedListTester<E> {
    public static void main(String[] args) {
        SinglyLinkedList<String> myList = new SinglyLinkedList<>();
        myList.add("Evan");
        myList.add("Nelson");
        myList.add("Jonah");
        myList.add("Mr. Theiss");
        System.out.println("Testing toString Method");
        System.out.println(myList.toString());

        System.out.println();

        System.out.println("Testing size Method");
        System.out.println(myList.size());

        System.out.println();

        System.out.println("Testing contains Method");
        System.out.println("Thomas = False: " + myList.contains("Thomas"));
        System.out.println("Evan = True: " + myList.contains("Evan"));

        System.out.println();

        System.out.println("Testing indexOf Method");
        System.out.println("Evan = 0: " + myList.indexOf("Evan"));
        System.out.println("Nelson = 1: " + myList.indexOf("Nelson"));
        System.out.println("Mr. Theiss = 3: " + myList.indexOf("Mr. Theiss"));
        System.out.println("Thomas = -1: " + myList.indexOf("Thomas"));

        System.out.println();

        System.out.println("Texting remove(index) method");
        // System.out.println("removing 1ts = Nelson: " + myList.remove(1));
        // System.out.println(myList.toString());

        System.out.println();

        System.out.println("Testing remove(object) method");
        // System.out.println("removing Thomas: false: " + myList.remove("Thomas"));
        // System.out.println(myList.toString());

        System.out.println();

        System.out.println("Testing get method");
        System.out.println("Evan: " + myList.get(0));
        System.out.println("Nelson: " + myList.get(1));
        System.out.println("Mr. Theiss: " + myList.get(3));
    }
}
