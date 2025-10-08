public class DoublyLinkedListTester {
    public static void main(String[] args) {
        DoublyLinkedList myList = new DoublyLinkedList();
        // myList.add(Nucleotide.A);
        // myList.add(Nucleotide.C);
        // myList.add(Nucleotide.G);
        // myList.add(Nucleotide.T);
        // myList.add(Nucleotide.A);
        // myList.add(Nucleotide.T);
        // myList.add(Nucleotide.A);

        DoublyLinkedList myList2 = new DoublyLinkedList();
        myList2.add(Nucleotide.T);
        myList2.add(Nucleotide.A);
        myList2.add(Nucleotide.C);
        myList2.add(Nucleotide.G);

        DoublyLinkedList myList3 = new DoublyLinkedList();
        myList3.add(Nucleotide.G);
        myList3.add(Nucleotide.T);
        myList3.add(Nucleotide.T);

        // System.out.println(myList.toString());

        // System.out.println();

        // System.out.println("Testing isEmpty");
        // System.out.println(myList.isEmpty());

        // System.out.println();

        // System.out.println("Testing contains");
        // System.out.println(myList.contains(Nucleotide.T));

        // System.out.println();

        // System.out.println("Testing size");
        // System.out.println(myList.size());

        // System.out.println();

        // System.out.println("Testing indexOf");
        // System.out.println(myList.indexOf(Nucleotide.T));

        // System.out.println();

        // System.out.println("Testing remove index");
        // // myList.remove(3);
        // // System.out.println(myList.toString());

        // System.out.println();

        // System.out.println("Testing remove object");
        // // myList.remove(Nucleotide.A);
        // // System.out.println(myList.toString());

        // System.out.println();

        // System.out.println("Testing get method");
        // System.out.println(myList.get(3));

        // System.out.println();

        // System.out.println("Testing set method");
        // // System.out.println(myList.set(3, Nucleotide.A));
        // // System.out.println(myList.toString());

        System.out.println("Testind add index method");
        myList.add(0, Nucleotide.T);
        myList.add(0, Nucleotide.A);
        myList.add(1, Nucleotide.G);
        myList.add(2, Nucleotide.C);
        System.out.println(myList.toString());

        // System.out.println();

        // System.out.println("Testing add segment to end");
        // myList.addSegmentToEnd(myList2);
        // System.out.println(myList.toString());

        // System.out.println();

        // System.out.println("Testing delete segment");
        // System.out.println(myList.deleteSegment(myList3));
        // myList.deleteSegment(myList3);
        // System.out.println(myList.toString());

        // System.out.println();

        // System.out.println("Testing delete last three");
        // System.out.println(myList.deleteLastThree());
        // myList.deleteLastThree();
        // System.out.println(myList.toString());

        // System.out.println();

        // System.out.println("Testing replace every A with TAC");
        // System.out.println(myList.toString());
        // myList.replaceEveryAWithTAC();
        // System.out.println(myList.toString());
    }
}
