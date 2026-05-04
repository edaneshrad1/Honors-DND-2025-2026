// The most important method in HuffmanEncoder is:
// public void encodeFile(String fileToCompress)

// In the constructor it should first read in the codes and put them into a data structure so that
// it knows how to encode each character. Then this method should encode every char from inputFile
// using those codes, and put the output into a new file whose name is inputFile + “.huf”.

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.io.PrintWriter;

public class HuffmanEncoder {

    private HashMap<Character, String> codeMap;

    // reads the information from the codeFile and puts it into a data structure
    public HuffmanEncoder(String codeFile) {

        this.codeMap = new HashMap<Character, String>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(codeFile));
            String line = br.readLine();

            // Start at -1 so lineNumber represents the character ASCII value
            int lineNumber = -1;

            // go through every line of codeFile
            while (line != null) {
                lineNumber++;
                // if there's nothing on that line just skip it
                if (line.isEmpty()) {
                    continue;
                }

                // if there is something on that line put (char) (line#), whatever binary string is
                // on that line into a HashMap
                else {
                    codeMap.put((char) lineNumber, line);
                }

                line = br.readLine();
            }

            br.close();

        } catch (IOException e) {
            System.out.println("BAD");
        }
    }

    // for a given input char, returns the String of 0s and 1s that it encodes into
    public String encodeChar(char input) {
        if (codeMap.containsKey(input)) {
            return codeMap.get(input);
        } else {
            return "";
        }
    }

    // takes a fileToCompress and encodes every character, putting the final results in encodedFile.
    // After its final character, it should then encode an EOF (char 26), and add its code to the
    // end of encodedFile.
    // *Finally, you need to pad with 0’s until the total number of 0s and 1s you’ve written is a
    // multiple of 8.*
    public void encodeFileToHuffmanCodes(String fileToCompress, String encodedFile) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileToCompress));
            PrintWriter pw = new PrintWriter(encodedFile);

            // instantiate total bits written
            int totalBitsWritten = 0;

            // read the first character from the file
            int currentInt = br.read();

            // while character isn't EOF (i.e br.read != -1)
            while (currentInt != -1) {

                // convert the read value to a char
                char currentChar = (char) currentInt;

                // get that char's binary definition from codeMap
                String code = codeMap.get(currentChar);

                // write that char's binary definition into the encoded file
                pw.write(code);

                // totalBitsWritten += code.length
                totalBitsWritten += code.length();

                // Read the next char
                currentInt = br.read();
            }

            // instantiate EOF character (26)
            char EOF = (char) 26;

            // get the EOF HuffmanCode
            String EOFCode = codeMap.get(EOF);

            // totalBitsWritten += length of EOF code
            totalBitsWritten += EOFCode.length();

            // while totalBitsWritten % 8 != 0 add a 0 to the end of the encoded file
            while (totalBitsWritten % 8 != 0) {
                pw.print("0");
                totalBitsWritten++;
            }

            br.close();
            pw.close();
        } catch (IOException e) {
            System.out.println("BAD");
        }
    }
}
