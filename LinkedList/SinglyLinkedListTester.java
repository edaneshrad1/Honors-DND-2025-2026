public class SinglyLinkedListTester<E> {
    public static void main(String[] args) {
        SinglyLinkedList<String> myList = new SinglyLinkedList<>();
        // myList.add("Evan");
        // myList.add("Nelson");
        // myList.add("Jonah");
        // myList.add("Mr. Theiss");
        // for(int i = 0; i < 10000000; i++) {
        //     myList.add("Bobby");
        // }
        System.out.println("Testing toString Method");
        System.out.println(myList.toString());

        System.out.println();

        System.out.println("Testing size Method");
        System.out.println("Size = 4: " + myList.size());

        System.out.println();

        System.out.println("Testing contains Method");
        System.out.println("Thomas = False: " + myList.contains("Thomas"));
        System.out.println("Evan = True: " + myList.contains("Evan"));
        System.out.println("Nelson = True: " + myList.contains("Nelson"));
        System.out.println("Mr. Theiss = True: " + myList.contains("Mr. Theiss"));

        System.out.println();

        System.out.println("Testing indexOf Method");
        System.out.println("Evan = 0: " + myList.indexOf("Evan"));
        System.out.println("Nelson = 1: " + myList.indexOf("Nelson"));
        System.out.println("Mr. Theiss = 3: " + myList.indexOf("Mr. Theiss"));
        System.out.println("Thomas = -1: " + myList.indexOf("Thomas"));

        System.out.println();

        // System.out.println("Texting remove(index) method");
        // System.out.println("Before: " + myList.toString());
        // System.out.println("removing 3 = Mr. Theiss: " + myList.remove(3));
        // System.out.println("After: " + myList.toString());

        System.out.println();

        // System.out.println("Testing remove(object) method");
        // System.out.println("Before: " + myList.toString());
        // System.out.println("removing Mr. Theiss: true: " + myList.remove("Mr. Theiss"));
        // System.out.println("After: " + myList.toString());

        System.out.println();

        // System.out.println("Testing get method");
        // System.out.println("Evan: " + myList.get(0));
        // System.out.println("Nelson: " + myList.get(1));
        // System.out.println("Mr. Theiss: " + myList.get(3));

        System.out.println();

        System.out.println("Testing set method");
        // System.out.println("Before: " + myList.toString());
        // System.out.println("Evan -> Joey: " + myList.set(0, "Joey"));
        // System.out.println("Nelson -> Jerry: " + myList.set(1, "Jerry"));
        // System.out.println("Mr. Theiss -> Bob: " + myList.set(3, "Bob"));
        // System.out.println(myList.toString());

        System.out.println();

        System.out.println("Testing add index method");
        System.out.println("Before: " + myList.toString());
        // myList.add(1, "James");
        // myList.add(3, "Xander");
        myList.add(0, "Waller");
        myList.add(6, "Mr. Stout");
        System.out.println(myList.toString());
    }
}
