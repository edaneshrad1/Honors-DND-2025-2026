public class DoublyLinkedListTester {
    public static void main(String[] args) {
        DoublyLinkedList myList = new DoublyLinkedList();
        myList.add(Nucleotide.A);
        myList.add(Nucleotide.C);
        myList.add(Nucleotide.G);
        myList.add(Nucleotide.T);

        System.out.println(myList.toString());

        System.out.println();

        System.out.println("Testing isEmpty");
        System.out.println(myList.isEmpty());

        System.out.println();

        System.out.println("Testing contains");
        System.out.println(myList.contains(Nucleotide.T));

        System.out.println();

        System.out.println("Testing size");
        System.out.println(myList.size());

        System.out.println();

        System.out.println("Testing indexOf");
        System.out.println(myList.indexOf(Nucleotide.T));

        System.out.println();

        System.out.println("Testing remove index");
        // myList.remove(3);
        // System.out.println(myList.toString());

        System.out.println();

        System.out.println("Testing remove object");
        // myList.remove(Nucleotide.A);
        // System.out.println(myList.toString());

        System.out.println();

        System.out.println("Testing get method");
        System.out.println(myList.get(3));

        System.out.println();

        System.out.println("Testing set method");
        // System.out.println(myList.set(3, Nucleotide.A));
        // System.out.println(myList.toString());

        System.out.println("Testind add index method");
        myList.add(0, Nucleotide.T);
        System.out.println(myList.toString());
    }
}
