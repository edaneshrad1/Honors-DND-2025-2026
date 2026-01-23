public class MyStackTester {
    public static void main(String[] args) {
        MyStack s = new MyStack<String>();
        s.push("Derp");
        System.out.println(s.peek());
        s.push("hi");
        s.pop();
        System.out.println(s.peek());
    }
}
