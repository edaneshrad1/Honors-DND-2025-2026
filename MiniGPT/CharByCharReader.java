import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CharByCharReader {
    public static void main(String[] args) {
        // Example: Reading from a file. You could also use InputStreamReader 
        // for console input.
        try (BufferedReader reader = new BufferedReader(new FileReader("thegreatgatsby.txt"))) {
            int charAsInt;
            // Read until the end of the stream (-1 is returned)
            while ((charAsInt = reader.read()) != -1) {
                // Cast the integer value to a character
                char character = (char) charAsInt;
                System.out.print(character);
            }
        } catch (IOException e) {
            System.err.println("An I/O error occurred: " + e.getMessage());
        }
    }
}