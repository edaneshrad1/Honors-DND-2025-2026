import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles interactive navigation of the file system This class reads commands from standard input,
 * interprets them, and invokes operations on the current directory node.
 */
public class Navigator {

    private final FileSystemTree fileSystem;
    private FolderNode currentDirectory;
    private boolean shouldExit;

    /**
     * Constructs a navigator for a given file system tree. The starting location is the root
     * directory.
     */
    public Navigator(FileSystemTree fst) {
        this.fileSystem = fst;
        this.currentDirectory = fst.getRoot();
    }

    /**
     * Starts a command loop that repeatedly reads a line of input, interprets it as a command with
     * arguments, and executes it until a request to terminate is received.
     */
    public void run() {
        shouldExit = false;
        Scanner kb = new Scanner(System.in);

        while (!shouldExit) {
            // Prompt can be customized to show current path if desired.
            String line = kb.nextLine();
            processUserInputString(line);
        }

        kb.close();
    }

    /**
     * Changes the current directory based on a single path argument. Behavior should mirror typical
     * Unix shells: - "." refers to the current directory (no change). - ".." moves to the parent
     * directory (if one exists). - Paths starting with "/" are interpreted from the root directory.
     * - Other paths are interpreted relative to the current directory.
     */
    private void cd(String[] args) {
        if (args.length == 0) {
            return;
        } else {
            ArrayList<String> directories = wordFinder(args);
            if (args[0].charAt(0) == '/') {
                this.currentDirectory = fileSystem.getRoot();
                for (int i = 0; i < directories.size(); i++) {
                    if (directories.get(i).equals(".")) {
                        continue;
                    } else if (directories.get(i).equals("..")) {
                        if (this.currentDirectory == fileSystem.getRoot()) {
                            continue;
                        } else {
                            this.currentDirectory = this.currentDirectory.getParent();
                        }
                    } else {
                        if (currentDirectory.getChildByName(directories.get(i)) != null
                                && currentDirectory.getChildByName(directories.get(i)).isFolder()) {
                            this.currentDirectory = (FolderNode) (currentDirectory
                                    .getChildByName(directories.get(i)));
                        } else {
                            System.out.println("No such directory exists");
                            return;
                        }
                    }
                }
            } else {
                this.currentDirectory = currentDirectory;
                for (int i = 0; i < directories.size(); i++) {
                    if (directories.get(i).equals(".")) {
                        continue;
                    } else if (directories.get(i).equals("..")) {
                        if (this.currentDirectory == fileSystem.getRoot()) {
                            continue;
                        } else {
                            this.currentDirectory = this.currentDirectory.getParent();
                            continue;
                        }
                    } else {
                        if (currentDirectory.getChildByName(directories.get(i)) != null
                                && currentDirectory.getChildByName(directories.get(i)).isFolder()) {
                            this.currentDirectory = (FolderNode) (currentDirectory
                                    .getChildByName(directories.get(i)));
                        } else {
                            System.out.println("No such directory exists");
                            return;
                        }
                    }
                }
            }
        }
    }

    // Helper that returns a list of the seperate directories in args
    public static ArrayList<String> wordFinder(String[] args) {
        if (args.length == 0) {
            return new ArrayList<String>();
        }
        ArrayList<String> returnList = new ArrayList<>();
        String searchString = args[0];
        int startIndex = 0;
        if (searchString.charAt(startIndex) == '/') {
            startIndex += 1;
        }
        for (int i = startIndex; i < searchString.length(); i++) {
            if (searchString.charAt(i) == '/') {
                String addedWord = searchString.substring(startIndex, i++);
                returnList.add(addedWord);
                startIndex = i++;
            }
        }
        String finalWord =
                searchString.substring(searchString.lastIndexOf('/') + 1, searchString.length());
        returnList.add(finalWord);
        return returnList;
    }

    /**
     * Lists all items contained directly in the current directory. Output formatting can mirror
     * typical file system listings.
     */
    private void ls(String[] args) {
        for (int i = 0; i < currentDirectory.getChildren().size(); i++) {
            if (currentDirectory.getChildren().get(i).isFolder()) {
                System.out.println(currentDirectory.getChildren().get(i).getName() + "/");
            } else {
                System.out.println(currentDirectory.getChildren().get(i).getName());
            }
        }
    }

    /**
     * Creates a new directory inside the current directory using the provided name.
     */
    private void mkdir(String[] args) {
        currentDirectory.addFolder(args[0]);
    }

    /**
     * Creates a new file inside the current directory with a given name and size.
     */
    private void touch(String[] args) {
        currentDirectory.addFile(args[0], Integer.parseInt(args[1]));
    }

    /**
     * Searches the current directory and its descendants for nodes with a given name and prints
     * their paths.
     */
    private void find(String[] args) {
        findHelper(currentDirectory, args[0]);
    }

    // helper that finds the folderNode with name in the current directory and prints its path
    // folder is the current directory and name is the name of the folder
    private void findHelper(FolderNode folder, String name) {
        List<FolderNode> folders = folder.findFolders();
        for (int i = 0; i < folder.getChildren().size(); i++) {
            if (folder.getChildren().get(i).getName().equals(name)) {
                System.out.println(folder.getChildren().get(i).toString());
            }
        }
        for (int i = 0; i < folders.size(); i++) {
            findHelper(folders.get(i), name);
        }
    }

    /**
     * Prints the absolute path of the current directory, from the root to this node.
     */
    private void pwd(String[] args) {
        System.out.println(currentDirectory.toString());
    }

    /**
     * Displays the contents of the current directory as a tree, optionally respecting flags or
     * depth limits if provided by the arguments.
     */
    private void tree(String[] args) {
        printTree(currentDirectory, "");
    }

    private void printTree(FolderNode folder, String prefix) {
        List<FileSystemNode> kids = folder.getChildren();
        for (int i = 0; i < kids.size(); i++) {
            FileSystemNode child = kids.get(i);
            int lastIndex = kids.size() - 1;
            boolean isLastChild = (i == lastIndex);

            String branch = "";
            if (isLastChild) {
                branch = "|---";
            } else {
                branch = "|---";
            }

            String name = "";
            if (child.isFolder()) {
                name = child.getName();
            } else {
                name = child.getName();
            }

            System.out.println(prefix + branch + name);

            if (child.isFolder()) {
                String nextPrefix = "";
                if (isLastChild) {
                    nextPrefix = prefix + "    ";
                } else {
                    nextPrefix = prefix + "|   ";
                }
                printTree((FolderNode) child, nextPrefix);
            }
        }
    }

    /**
     * Prints how many nodes (files and folders) exist in the current directory and all of its
     * subdirectories.
     */
    private void count(String[] args) {
        System.out.println(currentDirectory.getTotalNodeCount() - 1);
    }

    /**
     * Prints the total size of all files reachable from the current directory.
     */
    private void size(String[] args) {
        System.out.println(currentDirectory.getSize());
    }

    /**
     * Prints the depth of the current directory, defined as the number of edges from the root
     * directory down to this directory.
     */
    private void depth(String[] args) {
        System.out.println(currentDirectory.getDepth());
    }

    /**
     * Prints the height of the current directory, defined as the longest downward distance from
     * this directory to any file or subdirectory beneath it. An empty directory has value 0.
     */
    private void height(String[] args) {
        System.out.println(currentDirectory.getHeight());
    }

    /**
     * Signals that the interactive loop should terminate after the current command.
     */
    private void quit(String[] args) {
        shouldExit = true;
    }

    /**
     * Interprets a line of user input by splitting it into a command and arguments, then forwarding
     * control to the appropriate helper method.
     *
     * Example inputs and how they are interpreted: "ls" -> command: "ls" args: []
     *
     * "mkdir docs" -> command: "mkdir" args: ["docs"]
     *
     * "touch notes.txt 100" -> command: "touch" args: ["notes.txt", "100"]
     *
     * "cd .." -> command: "cd" args: [".."]
     */
    public void processUserInputString(String line) {
        if (line == null || line.trim().isEmpty()) {
            return;
        }

        String[] parts = line.trim().split("\\s+");
        String command = parts[0];
        String[] args = new String[parts.length - 1];
        System.arraycopy(parts, 1, args, 0, args.length);

        switch (command) {
            case "cd":
                cd(args);
                break;
            case "ls":
                ls(args);
                break;
            case "mkdir":
                mkdir(args);
                break;
            case "touch":
                touch(args);
                break;
            case "find":
                find(args);
                break;
            case "pwd":
                pwd(args);
                break;
            case "tree":
                tree(args);
                break;
            case "count":
                count(args);
                break;
            case "size":
                size(args);
                break;
            case "depth":
                depth(args);
                break;
            case "height":
                height(args);
                break;
            case "quit":
                quit(args);
                break;
            default:
                // Unknown commands can be reported back to the user.
                System.out.println("Unrecognized command: " + command);
        }
    }
}
