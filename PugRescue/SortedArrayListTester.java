public class SortedArrayListTester {
    public static void main(String[] args) {
         SortedArrayList<Integer> myList = new SortedArrayList<Integer>();
         myList.add(1);
         myList.add(2);
         myList.add(3);
         myList.add(4);
         myList.add(55);
         myList.add(11);

        //  myList.remove((Integer) 3);

        System.out.println(myList.contains((Integer) 33));

         for (int i = 0; i< myList.size(); i++) {
            System.out.println(myList.get(i));
         }

         System.out.println();

         System.out.println(myList.min());

         System.out.println();

         System.out.println(myList.max());
    }
}
