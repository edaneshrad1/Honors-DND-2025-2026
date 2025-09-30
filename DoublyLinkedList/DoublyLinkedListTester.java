public class DoublyLinkedListTester {
    public static void main(String[] args) {
        DoublyLinkedList myList = new DoublyLinkedList();
        myList.add(Nucleotide.A);
        myList.add(Nucleotide.C);
        myList.add(Nucleotide.G);
        myList.add(Nucleotide.T);
        System.out.println(myList.toString());

        System.out.println();

        System.out.println(myList.isEmpty());
    }
}
