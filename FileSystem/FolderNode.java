import java.util.ArrayList;
import java.util.List;

/**
 * Represents a directory in the file system tree. A directory can contain other directories and
 * files as children.
 */
public class FolderNode extends FileSystemNode {

    private List<FileSystemNode> children;

    public FolderNode(String name, FolderNode parent) {
        super(name, parent);
        this.children = new ArrayList<>();
    }


    @Override
    public boolean isFolder() {
        return true;
    }

    /**
     * Returns a list view of the children contained directly inside this directory. Modifying the
     * returned list is not required to be supported.
     */
    public List<FileSystemNode> getChildren() {
        List<FileSystemNode> defendedChildren = children;
        return defendedChildren;
    }

    /**
     * Searches the children of this directory for a node whose name matches the input. Only direct
     * children are considered, not deeper descendants.
     */
    public FileSystemNode getChildByName(String childName) {
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i).getName().equals(childName)) {
                return children.get(i);
            }
        }
        return null;
    }

    /**
     * Creates a new file directly inside this directory with the given name and size. If a child
     * with the same name already exists, no file is created and false is returned. Otherwise the
     * new file is added and true is returned.
     */
    public boolean addFile(String fileName, int size) {
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i).getName().equals(fileName)) {
                return false;
            }
        }
        children.add(new FileNode(fileName, this, size));
        return true;
    }

    /**
     * Creates a new subdirectory directly inside this directory with the given name. If a child
     * with the same name already exists, no folder is created and false is returned. Otherwise the
     * new folder is added and true is returned.
     */
    public boolean addFolder(String folderName) {
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i).getName().equals(folderName)) {
                return false;
            }
        }
        children.add(new FolderNode(folderName, this));
        return true;
    }

    /**
     * Searches this directory and all of its descendants for nodes whose name matches the input.
     * When a match is found, its full path can be printed by the caller using toString().
     */
    public boolean containsNameRecursive(String searchName) {
        if (containsChild(searchName)) {
            return true;
        } else if (!containsChild(searchName) && !containsFolder()) {
            return false;
        } else if (!containsChild(searchName) && containsFolder()) {
            for (int i = 0; i < children.size(); i++) {
                if (children.get(i).isFolder()) {
                    FolderNode folder1 = (FolderNode) (children.get(i));
                    if (folder1.containsNameRecursive(searchName)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // Checks weather a child with name = childName is within a specific folder
    public boolean containsChild(String childName) {
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i).getName().equals(childName)) {
                return true;
            }
        }
        return false;
    }

    // Checks if the folder we are in has a folder node in it
    public boolean containsFolder() {
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i).isFolder()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getHeight() {
        List<FolderNode> folders = findFolders();
        if (folders.size() == 0) {
            return 1;
        } else {
            List<Integer> heights = new ArrayList();
            for (int i = 0; i < folders.size(); i++) {
                heights.add(folders.get(i).getHeight());
            }
             return 1+ findMax(heights);
        }
    }

    // returns a list of all the folders within a folder
    public List<FolderNode> findFolders() {
        List<FolderNode> folders = new ArrayList<>();
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i).isFolder()) {
                folders.add((FolderNode) (children.get(i)));
            }
        }
        return folders;
    }

    //finds the max value within an array of integers
    public Integer findMax(List<Integer> ints) {
        int max = 0;
        for (int i = 0; i < ints.size(); i++) {
            if (ints.get(i) > max) {
                max = ints.get(i);
            }
        }
        return max;
    }

    @Override
    public int getSize() {
        int total = 0;
        for (int i = 0; i < children.size(); i++) {
            FileSystemNode child = children.get(i);
            total += child.getSize();
        }

        return total;
    }


    @Override
    public int getTotalNodeCount() {
        int count = 1;
        for (int i = 0; i < children.size(); i++) {
            FileSystemNode child = children.get(i);
            count += child.getTotalNodeCount();
        }

        return count;
    }
}
