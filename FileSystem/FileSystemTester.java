import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Small manual tester for the solution file-system implementation.
 *
 * This does NOT use JUnit. It just runs a few operations and prints out
 * what it's doing plus the observed behavior.or
 *
 * And also assumes (based on our design):
 *  - FileSystemTree#getRoot() returns a non-null FolderNode
 *  - FolderNode has addFolder(String) and addFile(String, int) that return boolean
 *  - FileSystemNode has getDepth(), getHeight(), getSize(), getTotalNodeCount()
 *  - Navigator has processUserInputString(String) which prints results to System.out
 */
public class FileSystemTester {

    public static void main(String[] args) {

        // 1. Construct a tree and check root
        // FileSystemTree tree = new FileSystemTree();
        // FolderNode root = tree.getRoot();

        // if (root == null) {
        //     System.out.println("[FAIL] Root is null. FileSystemTree.getRoot() must return a non-null root folder.");
        //     return;
        // } else {
        //     System.out.println("[PASS] Root is non-null.");
        // }

        // System.out.println("Root toString(): " + root.toString());
        // System.out.println("Expected at root: '/' (or equivalent)");

        // // 2. Build a small structure under root
        // System.out.println("\n=== Building tree structure under root ===");
        // boolean addedDocs = root.addFolder("docs");
        // boolean addedSrc = root.addFolder("src");
        // boolean addedMainJava = root.addFile("main.java", 120);
        // boolean addedReadme = root.addFile("README.md", 80);

        // int depthRoot = root.getDepth();
        // int heightRoot = root.getHeight();
        // int sizeRoot = root.getSize();
        // int totalNodesRoot = root.getTotalNodeCount();

        // //testing wordFinder method
        // String[] testArray = new String[1];
        // testArray[0] = "/./../desktop/computerScience";
        // ArrayList<String> list = Navigator.wordFinder(testArray);
        // System.out.println(list);



            /** Build a simple pre-populated student tree for listing/search tests. */
        FileSystemTree t = new FileSystemTree();
        FolderNode rootNode = t.getRoot();
        rootNode.addFolder("docs");
        rootNode.addFolder("bin");
        rootNode.addFile("notes.txt", 10);
        FolderNode docs = (FolderNode) rootNode.getChildByName("docs");
        docs.addFile("readme.txt", 100);
        docs.addFolder("projects");
        FolderNode projects = (FolderNode) docs.getChildByName("projects");
        projects.addFile("a.java", 50);

        Navigator nav = new Navigator(t);
        nav.processUserInputString("tree");
        
    }
}