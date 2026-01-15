// Implements a BST with BinaryNode nodes

public class MyBST<E extends Comparable<E>> {

	private BinaryNode<E> root; // holds the root of this BST

	// Constructor: creates an empty BST.
	public MyBST() {
		root = null;
	}

	public BinaryNode<E> getRoot() {
		return root;
	}

	public int getHeight() {
		return root.getHeight();
	}

	// Returns true if this BST contains value; otherwise returns false.
	public boolean contains(E value) {
		if (this.root == null) {
			return false;
		}
		// check if root is value
		if (root.getValue() == value) {
			return true;
		}
		BinaryNode<E> node = root;
		// itterate through tree and search for a node with value
		// this while loop will not check leaf nodes
		while (node.hasLeft() || node.hasRight()) {
			// if you find the node YAY!
			if (value.compareTo(node.getValue()) == 0) {
				return true;
				// if value is greater than node.getValue continue itterating through
				// node.getRight
			} else if (value.compareTo(node.getValue()) > 0) {
				if (node.getRight() == null) {
					return false;
				}
				node = node.getRight();
				continue;
			} else {
				// if value is less than node.getValue continue itterating through node.getLeft
				if (node.getLeft() == null) {
					return false;
				}
				node = node.getLeft();
				continue;
			}
		}
		// if you end on a leaf node check if node.getValue == value
		if (value.compareTo(node.getValue()) == 0) {
			return true;
		}
		return false;
	}

	// Adds value to this BST, unless this tree already holds value.
	// Returns true if value has been added; otherwise returns false.
	public boolean add(E value) {
		if (contains(value)) {
			return false;
		}
		// if you have a tree with no nodes make the root a new node with value
		if (root == null) {
			root = new BinaryNode<E>(value);
			root.setHeight(0);
			return true;
		}

		BinaryNode<E> node = root;
		BinaryNode<E> addedNode = new BinaryNode<E>(value);
		// find the parent of the node you want to add
		// in this while loop a node will not be added if its parent is a leaf
		while (node.hasLeft() || node.hasRight()) {
			if (value.compareTo(node.getValue()) > 0) {
				// if value is greater than node.getValue and node has no right make
				// node.getRight value
				if (!node.hasRight()) {
					node.setRight(addedNode);
					addedNode.setParent(node);
					addedNode.setHeight(node.getHeight() + 1);
					return true;
				} else {
					// if node has a right continue itterating
					node = node.getRight();
					continue;
				}
			} else {
				// if value is less than node.getValue and node has no left make node.getLeft
				// value
				if (!node.hasLeft()) {
					node.setLeft(addedNode);
					addedNode.setParent(node);
					addedNode.setHeight(node.getHeight() + 1);
					return true;
					// if node has a left continue itterating
				} else {
					node = node.getLeft();
					continue;
				}
			}
		}
		// if node is a leaf and value > node.getValue make node.getRight value
		if (value.compareTo(node.getValue()) > 0) {
			node.setRight(addedNode);
			addedNode.setParent(node);
			addedNode.setHeight(node.getHeight() + 1);
			return true;
		} else {
			// if node is a leaf and value < node.getValue make node.getLeft value
			node.setLeft(addedNode);
			addedNode.setParent(node);
			addedNode.setHeight(node.getHeight() + 1);
			return true;
		}
	}

	// Removes value from this BST. Returns true if value has been
	// found and removed; otherwise returns false.
	// If removing a node with two children: replace it with the
	// largest node in the right subtree
	public boolean remove(E value) {
		if (!contains(value)) {
			return false;
		}
		// if the root node has no kids
		if (!root.hasLeft() && !root.hasRight()) {
			// make the root null
			root = null;
			return true;
		}

		// node is the node being removed
		BinaryNode<E> node = root;
		// go through tree until you reach the node with value
		while (value.compareTo(node.getValue()) != 0) {
			if (value.compareTo(node.getValue()) > 0) {
				node = node.getRight();
			} else if (value.compareTo(node.getValue()) < 0) {
				node = node.getLeft();
			}
		}

		BinaryNode<E> parent = node.getParent();

		// if node has no kids and is on the right of parent make parent.getRigth null
		if (!node.hasLeft() && !node.hasRight()
				&& node.getValue().compareTo(parent.getValue()) > 0) {
			parent.setRight(null);
			return true;
		}

		// if node has no kids and is on the left of parent make parent.getLeft null
		if (!node.hasLeft() && !node.hasRight()
				&& node.getValue().compareTo(parent.getValue()) < 0) {
			parent.setLeft(null);
			return true;
		}

		// if node has two children or only has a right child:
		if ((node.hasLeft() && node.hasRight()) || (!node.hasLeft() && node.hasRight())) {
			// find smallest node in right subtree
			BinaryNode<E> smallestRight = minFromNode(node.getRight());
			// make the value of node the value of smallestRight
			node.setValue(smallestRight.getValue());

			// get rid of smallestRight:
			// if smallestRight is one generation below node
			if (smallestRight.getHeight() - node.getHeight() == 1) {
				// make node.getRight() smallestRight.getRight()
				// make smallestRight.getRight's parent node
				node.setRight(smallestRight.getRight());
				if (smallestRight.getRight() != null) {
					smallestRight.getRight().setParent(node);
				}

			} else {
				// make the left of smallestRight's parent the right of smallestRight
				// b/c smallestRight will never have a left
				smallestRight.getParent().setLeft(smallestRight.getRight());
				// if smallestRight's right node isn't null
				if (smallestRight.getRight() != null) {
					// make smallestRight.getRight's parent smallestRight.getParent
					smallestRight.getRight().setParent(smallestRight.getParent());
				}
			}
			return true;
		}

		// if node only has a left child:
		if (node.hasLeft() && !node.hasRight()) {
			// find the largest node in the left subtree
			BinaryNode<E> largestLeft = maxFromNode(node.getLeft());
			// make the value of node the value of largestLeft
			node.setValue(largestLeft.getValue());

			// get rid of largestLeft:
			// if largestLeft is one generation below node
			if (largestLeft.getHeight() - node.getHeight() == 1) {
				// make node.getLeft() largestLeft.getLeft()
				node.setLeft(largestLeft.getLeft());
				if (largestLeft.getLeft() != null) {
					largestLeft.getLeft().setParent(node);
				}
			} else {
				// make the right of largestLeft's parent the left of largestLeft
				// b/c largestLeft will never have a right
				largestLeft.getParent().setRight(largestLeft.getLeft());
				if (largestLeft.getLeft() != null) {
					largestLeft.getLeft().setParent(largestLeft.getParent());
				}
			}
			return true;
		}

		return false;
	}

	// Returns the minimum in the tree
	public E min() {
		BinaryNode<E> node = root;
		// go to the left-most node in the tree and return it
		while (node.hasLeft()) {
			node = node.getLeft();
		}
		return node.getValue();
	}

	// method that returns the min from a specific node
	public BinaryNode<E> minFromNode(BinaryNode<E> node) {
		while (node.hasLeft()) {
			node = node.getLeft();
		}
		return node;
	}

	// Returns the maximum in the tree.
	public E max() {
		BinaryNode<E> node = root;
		// go to the right-most node in the tree and return it
		while (node.hasRight()) {
			node = node.getRight();
		}
		return node.getValue();
	}

	// method that returns the max from a specific node
	public BinaryNode<E> maxFromNode(BinaryNode<E> node) {
		while (node.hasRight()) {
			node = node.getRight();
		}
		return node;
	}

	// Returns a bracket-surrounded, comma separated list of the contents of the
	// nodes, in order
	// e.g. [Apple, Cranberry, Durian, Mango]
	public String toString() {
		if (root == null) {
			return "[]";
		}
		String str = "";
		str += "[";
		str += stringEverything(root);
		str += "]";
		return str;
	}

	public String stringEverything(BinaryNode<E> node) {
		String str = "";
		// if node has a left tell lil recursion slave to add everything on the left to
		// str
		if (node.hasLeft()) {
			str += stringEverything(node.getLeft()) + ", ";
		}
		str += node;
		// if node has a right tell lil recursion slave to add everything on the right
		// to str
		if (node.hasRight()) {
			str += ", " + stringEverything(node.getRight());
		}
		return str;
	}
}
