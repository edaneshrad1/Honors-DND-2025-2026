// the Decoder takes a file that has been compressed with the Encoder and decompresses it using the
// same Rosetta Stone file created by the same Generator.

// In the constructor it should first read in the codes and put them into a data structure so that
// it knows how to decode each character. Then this method should decode the sequence of bits from
// inputFile using those codes, and put the decoded output into a new file whose name is inputFile
// with the “.huf” removed.

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileReader;

public class HuffmanDecoder {

    private HashMap<String, Character> codeMap;

    // reads the information from the codeFile and puts it into a data structure
    public HuffmanDecoder(String codeFile) {
        this.codeMap = new HashMap<String, Character>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(codeFile));
            String line = br.readLine();

            // Start at -1 so lineNumber represents the character ASCII value
            int lineNumber = -1;

            // go through every line in codeFile
            while (line != null) {
                lineNumber++;

                // if there is nothing on that line just skip it
                if (line.isEmpty()) {
                    continue;
                }

                // if there is something on that line put(line, (char)line#) into the HashMap
                else {
                    codeMap.put(line, (char) lineNumber);
                }

                line = br.readLine();
            }

            br.close();


        } catch (IOException e) {
            System.out.println("BAD");
        }
    }

    // return whether or not the given string (which we may assume as a precondition is 0s and 1s)
    // is a single code
    public boolean isCode(String binary) {
        return codeMap.containsKey(binary);
    }

    // can assume as a precondition that the string is a single code, and should return the char
    // associated with that code
    public char decodeChar(String binary) {
        return codeMap.get(binary);
    }

    // takes as input encodedFile and decodes all the 0s and 1s, outputting the final results in
    // encodedFile. When it reaches the EOF character, it does not write that, and simply knows that
    // it’s time to stop writing
    public void decodeFileFromHuffmanCodes(String encodedFile, String decodedFile) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(encodedFile));
            PrintWriter pw = new PrintWriter(decodedFile);

            //instantiate the current code as an empty String
            String currentCode = "";

            //read the first char / bit from encodedFile
            int currentInt = br.read();

            //go through every bit in encodedFile
            while (currentInt != -1) {
                //turn currentInt into a 0 or 1
                char currentBit = (char) currentInt;

                //append that bit to current code
                currentCode += currentBit;

                //if currentCode is a valid huffmanCode: isCode(currentCode)
                if (isCode(currentCode)) {

                    //decodedChar = decodeChar(currentCode)
                    char decodedChar = decodeChar(currentCode);

                    //if currentCode is the EOF STOP and don't write it in the decodedFile
                    if (decodedChar == (char) 26) {
                        br.close();
                        pw.close();
                        return;
                    } 
                    
                    //if currentCode isn't the EOF write it into decodedFile
                    else {
                        pw.print(decodedChar);

                        //reinstantiate currentCode as an empty String
                        currentCode = "";
                    }
                }

                currentInt = br.read();
            }
            
            br.close();
            pw.close();
        } catch(IOException e) {
            System.out.println("BAD");
        }
    }
}
