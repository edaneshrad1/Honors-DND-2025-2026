import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class RLECompression {

    // Creates a compressed version with fileName + ".rle.bw"
    public static void compress(String fileName) throws IOException {
        bwTransform(fileName);
        encode(fileName + ".bw");
    }

    // Decompresses a .rle.bw file
    public static void decompress(String fileName) throws IOException {
        decode(fileName);
        invertBWTransform(fileName.substring(0, fileName.length() - 4));
    }

    // Encodes the contents of a file using the RLE compression scheme:
    // single characters are left alone, and runs of 2+ characters are encoded as
    // that letter twice, followed by the length of the run, cast as a char
    public static void encode(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        PrintWriter pw = new PrintWriter(fileName + ".rle");

        char previousChar = (char) br.read();
        int count = 1;

        //instantiate an int that counts the total chars written
        //add to this whenever we write a char
        int totalCharsWritten = 0;

        //instantiate an int that counts the total chars read
        //add to this whenever we read a char
        int totalCharsRead = 1;
        

        while (br.ready()) {

            char c = (char) br.read();
            totalCharsRead++;

            if (c == previousChar) {
                char nextChar = (char) br.read();
                //only add to charsRead if nextChar exists
                if (Character.getNumericValue(nextChar) != -1) {
                    totalCharsRead++;
                }
                count++;
                
                while (nextChar == c) {
                    count++;
                    nextChar = (char) br.read();
                    totalCharsRead++;
                }
                pw.write("" + c + c);
                pw.write((char) count + 48);
                totalCharsWritten += count;
                previousChar = nextChar;
            } else {
                pw.write(previousChar);
                totalCharsWritten++;
                previousChar = c;
            }

            count = 1;
        }

        //if the total chars written isn't equal to the chars read write previousChar
        if (totalCharsWritten != totalCharsRead) {
            pw.write(previousChar);
        }

        br.close();
        pw.close();
    }

    // Decodes the above scheme
    public static void decode(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        PrintWriter pw = new PrintWriter(fileName.substring(0, fileName.length() - 4));

        char previousChar = (char) br.read();

        //instantiate an int that counts the total chars written
        //add to this whenever we write a char
        int totalCharsWritten = 0;

        //instantiate an int that counts the total chars read
        //add to this whenever we read a char
        int totalCharsRead = 1;

        while (br.ready()) {
            char c = (char) br.read();
            totalCharsRead++;
            if (c == previousChar) {
                char nextChar = (char) br.read();

                //only add to charsRead if nextChar exists
                if (Character.getNumericValue(nextChar) != -1) {
                    totalCharsRead++;
                }

                int count = nextChar - '0';
                totalCharsWritten += 3;
                for (int i = 0; i < count; i++) {
                    pw.write(c);
                }
                previousChar = (char) br.read();
            } else {
                pw.write(previousChar);
                totalCharsWritten++;
                previousChar = c;
            }
            
        }

        //if the total chars written isn't equal to the chars read write previousChar
        if (totalCharsWritten != totalCharsRead) {
            pw.write(previousChar);
        }

        br.close();
        pw.close();
    }

    public static void bwTransform(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        // Add a null character at the beginning, as a
        // "beginning of file" character
        StringBuilder originalText = new StringBuilder("" + '\0');

        while (br.ready()) {
            char c = (char) br.read();
            originalText.append(c);
        }
        br.close();

        String[] rotations = new String[originalText.length()];
        String text = originalText.toString();
        rotations[0] = originalText.toString();
        // TO-DO
        // Now do the Burrows-Wheeler Transform

        //Build the rotations
        for (int i = 1; i < text.length(); i++) {
            //the origional text from i to the end + origional text from 0 to i
            rotations[i] = text.substring(i) + text.substring(0, i);
        }

        //Sort the rotations in alphabetical order
        Arrays.sort(rotations);

        //Take the last char from each index of the sorted rotations and append it to a StringBuilder
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < rotations.length; i++) {
            output.append(rotations[i].charAt(text.length() - 1));
        }

        // And then write the transformation into a file
        PrintWriter pw = new PrintWriter(fileName + ".bw");
        pw.write(output.toString());
        pw.close();
    }

    public static void invertBWTransform(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        StringBuilder originalText = new StringBuilder();

        while (br.ready()) {
            char c = (char) br.read();
            originalText.append(c);
        }
        br.close();

        StringBuilder[] reconstructions = new StringBuilder[originalText.length()];
        for (int i = 0; i < reconstructions.length; i++) {
            reconstructions[i] = new StringBuilder("" + originalText.charAt(i));
        }

        //loop from 0 to reconstructions.length
        for (int i = 0; i < reconstructions.length - 1; i++) {

            //sort reconstructions
            Arrays.sort(reconstructions);

            //loop from 0 to reconstructions.length
            for (int j = 0; j < reconstructions.length; j++) {

                //insert origionalText.charAt(j) at zero for reconstructions[j]
                reconstructions[j].insert(0, originalText.charAt(j));
            }
            
        }
            

        // TO-DO
        // Now undo the Burrows-Wheeler transform

        String origional = "";

        //loop through reconstructions
        for (int i = 0; i < reconstructions.length; i++) {

            origional = reconstructions[i].toString();
            //check the starting char of every index
            if (reconstructions[i].charAt(reconstructions[i].length() - 1) == '\0') {
                //once you find the char starting with the end marker break
                break;
            }
            
        }
        
        //output is that string from 1 to the end
        String output = origional.substring(0, origional.length() - 1);


        // TO-DO
        // And write the appropriate reconstruction into the file, without the null char
        PrintWriter pw = new PrintWriter(fileName.substring(0, fileName.length() - 3));
        pw.write(output);
        pw.close();
    }
}
