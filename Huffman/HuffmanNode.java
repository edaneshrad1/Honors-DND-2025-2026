// This class creates a HuffmanNode object to put into the heap and allow me to sort the hashmap
// This class has the following private instance variables:
// char char: represents a character in the hashmap
// int frequency: the frequency of the node
// HeapNode parent: the node's parent
// HeapNode childLeft: the node's left child
// HeapNode childRight: the node's right child
// String binaryDefinition: the current node written with the tree binary thing

public class HuffmanNode implements Comparable {
    private char c;
    private int frequency;
    private HuffmanNode parent;
    private HuffmanNode childLeft;
    private HuffmanNode childRight;
    private String binaryDefinition;

    // tree node constructor
    public HuffmanNode(char c, int frequency, HuffmanNode parent, HuffmanNode childLeft,
            HuffmanNode childRight, String binaryDefinition) {
        this.c = c;
        this.frequency = frequency;
        this.parent = parent;
        this.childLeft = childLeft;
        this.childRight = childRight;
        this.binaryDefinition = binaryDefinition;
    }

    // heap node constructor
    public HuffmanNode(char c, int frequency) {
        this.c = c;
        this.frequency = frequency;
        this.parent = null;
        this.childLeft = null;
        this.childRight = null;
        this.binaryDefinition = "";
    }

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public HuffmanNode getParent() {
        return parent;
    }

    public void setParent(HuffmanNode parent) {
        this.parent = parent;
    }

    public HuffmanNode getChildLeft() {
        return childLeft;
    }

    public void setChildLeft(HuffmanNode childLeft) {
        this.childLeft = childLeft;
    }

    public HuffmanNode getChildRight() {
        return childRight;
    }

    public void setChildRight(HuffmanNode childRight) {
        this.childRight = childRight;
    }

    public String getBinaryDefinition() {
        return binaryDefinition;
    }

    public void setBinaryDefinition(String binaryDefinition) {
        this.binaryDefinition = binaryDefinition;
    }

    public boolean isLeaf() {
        if (childLeft == null && childRight == null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isRoot() {
        if (parent == null) {
            return true;
        } else {
            return false;
        }
    }

    // method to compare the frequencies of two HuffmanNodes
    public int compareTo(Object other) {
        HuffmanNode otherNode = (HuffmanNode) other;
        if (this.getFrequency() > otherNode.getFrequency()) {
            return 1;
        } else if (this.getFrequency() < otherNode.getFrequency()) {
            return -1;
        } else {
            return 0;
        }
    }
}
