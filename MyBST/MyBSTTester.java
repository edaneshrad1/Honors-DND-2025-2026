public class MyBSTTester {
    public static void main(String[] args) {
        MyBST list = new MyBST<Integer>();
        list.add(2);
        list.add(1);
        list.add(3);
        list.add(5);
        list.add(9);
        list.add(4);
        list.add(0);
        list.add(-1);

        // BinaryNode<Integer> node = list.getRoot().getRight().getRight();
        // System.out.println(node.getParent());
        // list.remove(9);
        System.out.println(list.toString());

        System.out.println(list.min());
        System.out.println(list.max());

        System.out.println(list.contains(92));
    }
}
