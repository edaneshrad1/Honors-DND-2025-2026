import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class ReadLines {
     public static List<String> readAllLines() {
       List<String> names = new ArrayList<String>();
        try {


        	  // Each element is one line from the file
        	  Path p = Paths.get("NameOfYourFile.txt");
   return (List<String>)Files.readAllLines(p);
       } catch (Exception e) {
	System.out.println("Couldn't read file");
       }
      return names;
    }
}
