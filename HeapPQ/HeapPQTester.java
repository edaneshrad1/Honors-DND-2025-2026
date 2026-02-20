public class HeapPQTester {
    public static void main(String[] args) {
        HeapPQ<Integer> heap = new HeapPQ<>();
        heap.add(1);
        heap.removeMin();
        System.out.println(heap.toString());
    }
}
