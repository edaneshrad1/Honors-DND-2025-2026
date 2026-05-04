// HuffmanCodeGenerator’s job is to create a file that lists a Huffman code. This file is the
// “Rosetta Stone” that the
// Encoder and Decoder will be using to do their jobs: the Encoder takes a file and compresses it
// using the Rosetta Stone
// file created by the Generator, and the Decoder takes a file that has been compressed with the
// Encoder and decompresses
// it using the same Rosetta Stone file created by the same Generator.

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.io.PrintWriter;

public class HuffmanCodeGenerator {

    // HashMap that stores the character frequencies
    private HashMap<Character, Integer> map;
    private ArrayList<Character> charList;
    private HashMap<Character, String> codeMap;

    // instantiate the EOF with a pseudo-char value
    private static final char pseudoEOF = 26;

    // Constructor: puts the relevant information from the frequencyFile (i.e. the character
    // frequencies) into a data
    // structure.
    public HuffmanCodeGenerator(String frequencyFile) {
        map = new HashMap<Character, Integer>();
        charList = new ArrayList<Character>();
        codeMap = new HashMap<Character, String>();

        // go through the frequency file
        try (BufferedReader br = new BufferedReader(new FileReader(frequencyFile))) {
            while (br.ready()) {

                // if the current char isn't in the map add it with a new key and a value of one and
                // add it to charList
                Character currentChar = (char) (br.read());
                if (!map.containsKey(currentChar)) {
                    map.put(currentChar, 1);
                    charList.add(currentChar);
                }

                // if the current char is already in the map add one to its value
                else {
                    map.put(currentChar, map.get(currentChar) + 1);
                }

            }

            // put the pseudoEOF char into the map and add it to charList
            map.put(pseudoEOF, 1);
            charList.add(pseudoEOF);


        } catch (IOException e) {
            System.err.println("An I/O error occurred: " + e.getMessage());
        }

        HuffmanTree tree = makeTree();
        tree.setBinaryDefinitions();
        makeCodeMap(tree.getRoot());
    }

    // The most important method in HuffmanCodeGenerator (I will often refer to it just as
    // “Generator”) is the method:
    public void makeCodeFile(String codeFile) {
        try {

            // make a new PW using codeFile
            PrintWriter pw = new PrintWriter(codeFile);

            // loop from 0 to 256
            for (int i = 0; i < 256; i++) {

                // if codeMap does have a key with ASCII value i print codeMap.get((char) i) then go
                // to the next line
                if (codeMap.containsKey((char) i)) {
                    pw.println(codeMap.get((char) i));
                }

                // if codeMap doesn't have a key with ASCII value i just go to a new line
                else {
                    pw.println();
                }

            }

            // print the EOF PseudoChar on the last line
            pw.println(codeMap.get(pseudoEOF));

            // close the PW
            pw.close();
        } catch (IOException e) {
            System.out.println("BAD");
        }
    }

    public int getFrequency(char c) {
        if (map.containsKey(c)) {
            return map.get(c);
        } else {
            return 0;
        }
    }

    public HashMap<Character, Integer> getMap() {
        return map;
    }

    public void setMap(HashMap<Character, Integer> map) {
        this.map = map;
    }

    public ArrayList<Character> getCharList() {
        return charList;
    }

    public void setCharList(ArrayList<Character> charList) {
        this.charList = charList;
    }

    public HashMap<Character, String> getCodeMap() {
        return codeMap;
    }

    public void setCodeMap(HashMap<Character, String> codeMap) {
        this.codeMap = codeMap;
    }

    // method that makes a min heap used to make the Huffman Tree
    public PriorityQueue<HuffmanNode> makeHeap() {
        PriorityQueue<HuffmanNode> heap = new PriorityQueue<HuffmanNode>();

        // loop through charList
        for (int i = 0; i < charList.size(); i++) {

            // make a new HuffmanNode with c = charList.get(i) and frequency = map.get(c)
            char c = charList.get(i);
            int frequency = map.get(c);
            HuffmanNode addedNode = new HuffmanNode(c, frequency);

            // add that node to heap
            heap.add(addedNode);
        }

        // return heap
        return heap;
    }

    // method that makes the Huffman Tree
    public HuffmanTree makeTree() {
        PriorityQueue<HuffmanNode> heap = makeHeap();

        // while heap has more than one node
        while (heap.size() > 1) {

            // remove the smallest frequency node and call it nodeLeft
            HuffmanNode nodeLeft = heap.remove();

            // remove the next smallest frequency node and call it nodeRight
            HuffmanNode nodeRight = heap.remove();

            int combinedFrequency = nodeLeft.getFrequency() + nodeRight.getFrequency();

            // create a new dummy parent node
            // c = \0
            // frequency = nodeLeft.getFrequency() + nodeRight.getFrequency()
            HuffmanNode parent = new HuffmanNode('\0', combinedFrequency);

            // set the children of the parent node
            parent.setChildLeft(nodeLeft);
            parent.setChildRight(nodeRight);

            // set the parent of the two children nodes
            nodeLeft.setParent(parent);
            nodeRight.setParent(parent);

            // add the parent node back to the heap
            heap.add(parent);
        }

        // once broken out of the while loop remove the last node from heap
        // this is the root node
        HuffmanNode root = heap.remove();

        // create a HuffmanTree with that root
        // return that Huffman Tree
        return new HuffmanTree(root);
    }

    // method to make the code map with all the binary definitions and shit
    // this method only takes in leaf nodes because only leaf nodes have a character value
    private void makeCodeMap(HuffmanNode currentNode) {
        // if currentNode is null just return
        if (currentNode == null) {
            return;
        }

        // if currentNode is a leaf put its character and binaryDefinition into codeMap and return
        if (currentNode.isLeaf()) {
            codeMap.put(currentNode.getC(), currentNode.getBinaryDefinition());
            return;
        }

        // if currentNode isn't a leaf call this method on both its children
        else {
            makeCodeMap(currentNode.getChildLeft());
            makeCodeMap(currentNode.getChildRight());
        }
    }

    public String getCode(char c) {
        if (codeMap.containsKey(c)) {
            return codeMap.get(c);
        } else {
            return "";
        }
    }


    // To make the Huffman Tree we must:
    // Create a min heap with every key-value pair in map
    // For each individual heap node:
    // c is the char (key)
    // frequency is map.get(c) (value)
    // Take the two smallest nodes from the heap and add them to a tree
    // create a new tree node with the combined frequencies of the two nodes from the heap
    // the parent of those two heap nodes is the new tree node I just made
    // the smaller heap node is the parent's left child
    // the larger heap node is the parent's right child
    // Remove those two heap nodes from the heap
    // add the new tree parent node do the heap
    // repeat this whole scheme until the heap is empty

}
