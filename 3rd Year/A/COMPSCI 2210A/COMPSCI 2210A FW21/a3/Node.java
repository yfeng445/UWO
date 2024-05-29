public class Node {
	private Node[] children; // Childern of this node
	private int value;
	
	public Node (int val) {
		children = null;
		value = val;
	}
	
	public Node(Node[] c) {
		children = c;
	}
	
	public Node (int val, Node[] c) {
		value = val;
		children = c;
	}
	
	public Node[] getChildren() {
		return children;
	}
	
	public int numChildren() {
		if (children == null) return 0;
		else return children.length;
	}
	
	public int getValue() {
		return value;
	}
	
	public boolean isLeaf() {
		return children == null;
	}
	
	/* Change the child in the specified position by the first parameter to the
	   node specified in the second parameter.                                   */ 
	public void setChild(int index, Node newChild) {
		children[index] = newChild;
	}

	/* Returns the child in the specified position by the parameter */
	public Node getChild(int index) {
		return children[index];
	}
}
