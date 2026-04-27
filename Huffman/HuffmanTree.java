//we'll use this class to create and represent the Huffman Tree made of Huffman Nodes

public class HuffmanTree {
    private HuffmanNode root;

    public HuffmanTree() {
        this.root = null;
    }

    public HuffmanTree(HuffmanNode root) {
        this.root = root;
    }

    public HuffmanNode getRoot() {
        return root;
    }

    public void setRoot(HuffmanNode root) {
        this.root = root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    //method that sets the binaryDefinition of every node in the tree
    public void setBinaryDefinitions() {
        setBinaryDefinitionsHelper(root, "");
    }

    //helper for the above method
    private void setBinaryDefinitionsHelper(HuffmanNode currentNode, String currentCode) {

        //if currentNode is null just return
        if (currentNode == null) {
            return;
        }

        //set the binaryDefinition of currentNode to currentCode
        currentNode.setBinaryDefinition(currentCode);

        //call this helper on (currentNode.getChildLeft, currentCode + "0")
        setBinaryDefinitionsHelper(currentNode.getChildLeft(), currentCode + "0");

        //call this helper on (currentNode.getChildRight, currentCode + "1")
        setBinaryDefinitionsHelper(currentNode.getChildRight(), currentCode + "1");
    }
}
