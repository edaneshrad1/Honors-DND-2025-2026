import java.util.ArrayList;

public class MiniGPTTester {
    public static void main(String[] args) {
        MiniGPT myGPT = new MiniGPT("thegreatgatsby.txt", 15);
        // ArrayList<Character> book = myGPT.getBook();
        // System.out.println(book.get(book.size() - 5));

        myGPT.generateText("thegreatgatsby.txt", 111119);
    }

}
