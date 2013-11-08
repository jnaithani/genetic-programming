package data;

public class BinaryTree {

	BinaryNode root = null;

	/**
	 * Generate the Tree from root Nodes Create a new empty tree.
	 */
	public BinaryTree(String rootValue) {
		root = new BinaryNode(rootValue);

	}

	/**
	 * Check if the tree is empty
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * External method that uses BinaryNode (Root) to get height
	 * 
	 * @return
	 */
	public int height() {
		return height(root);
	}

	/**
	 * 
	 * @param treeRoot
	 * @return
	 */
	private int height(BinaryNode treeRoot) {
		if (treeRoot == null)
			return 0;
		else
			return 1 + Math.max(height(treeRoot.left), height(treeRoot.right));
	}

	/**
	 * 
	 * @return
	 */
	public int size() {
		// Note: Since the size (# of nodes) of a tree is one more than the
		// size of the root's left and right subtrees, a recursive
		// method is needed with a BinaryTreeNode parameter. Therefore,
		// this method will be implemented by calling a private size
		// method.

		return size(root);
	}

	/**
	 * 
	 * @param treeRoot
	 * @return
	 */
	private int size(BinaryNode treeRoot) {
		if (treeRoot == null)
			return 0;
		else
			return 1 + size(treeRoot.left) + size(treeRoot.right);
	}

	/**
	 * Add a node using a string value
	 * 
	 * @param value
	 */
	public void add(String value) {
		if (this.isEmpty())
			root = new BinaryNode(value);
		else
			add(value, root);
	}

	/**
	 * 
	 * @param value
	 * @param treeRoot
	 */
	private void add(String value, BinaryNode treeRoot) {
		if (treeRoot.left == null)
			treeRoot.left = new BinaryNode(value);
		else if (treeRoot.right == null)
			treeRoot.right = new BinaryNode(value);
		else if (size(treeRoot.left) <= size(treeRoot.right))
			add(value, treeRoot.left);
		else
			add(value, treeRoot.right);
	}

	/**
	 * 
	 * @param value
	 * @param root
	 */
	public String remove(String value, BinaryNode root) {

		String data = root.getItem();
		if (root.left == null && root.right == null)
			root = null;
		else
			removeNode(root);

		return data;
	}

	/**
	 * 
	 * @param treeRoot
	 */
	private void removeNode(BinaryNode treeRoot) {
		if (treeRoot.left != null) {
			treeRoot.setItem(treeRoot.left.getItem());
			if (treeRoot.left.left == null && treeRoot.left.right == null)
				treeRoot.left = null; // swap here for mutation using a random
										// node
			else
				removeNode(root.left);
		} else {
			treeRoot.setItem(treeRoot.right.getItem());
			if (treeRoot.right.left == null && treeRoot.right.right == null)
				treeRoot.right = null; // swap here
			else
				removeNode(treeRoot.right);
		}
	}
}
