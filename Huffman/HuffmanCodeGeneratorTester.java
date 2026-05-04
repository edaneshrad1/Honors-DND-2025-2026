public class HuffmanCodeGeneratorTester {
    public static void main(String[] args) {
        HuffmanCodeGenerator huffy = new HuffmanCodeGenerator("text.txt");
        huffy.makeCodeFile("text.txt");
    }
}
