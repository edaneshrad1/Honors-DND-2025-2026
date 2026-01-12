public class MyBSTTester {
    public static void main(String[] args) {
        MyBST list = new MyBST<Integer>();
        list.add(2);
        list.add(1);
        list.add(8);
        list.add(4);
        list.add(6);
        list.add(0);
        list.add(5);

        System.out.println("Before:");
        System.out.println(list.toString());

        list.remove(4);
        System.out.println("After:");
        System.out.println(list.toString());

        // System.out.println(list.min());
        // System.out.println(list.max());

        // System.out.println(list.contains(92));
    }
}
