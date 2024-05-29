
/**
 * This class implements BSTNode and provides methods
 * @author Yulun Feng
 * @ID 251113989
 * @Date Nov 19, 2021
 *
 */

public class BSTNode {
	
	private BSTNode parent;
	private BSTNode leftChild;
	private BSTNode rightChild;
	NodeData data;
	
	
	/**
	 * Constructor of the class if it is a leaf node
	 */
	public BSTNode(){
		parent = null;
		leftChild = null;
		rightChild = null;
		data = null;
	}
	
	
	/**
	 * Constructor of the class if it is an internal node
	 * @param newParent 
	 * @param newLeftChild
	 * @param newRightChild
	 * @param newData
	 */
	public BSTNode(BSTNode newParent, BSTNode newLeftChild, BSTNode newRightChild, NodeData newData) {
		this.parent = newParent;
		this.leftChild = newLeftChild;
		this.rightChild = newRightChild;
		this.data = newData;
	}
	
	
	/**
	 * return parent of the node
	 * @return
	 */
	public BSTNode getParent() {
		return parent;
	}
	
	
	/**
	 * return left child of the node
	 * @return
	 */
	public BSTNode getLeftChild() {
		return leftChild;
	}
	
	
	/**
	 * return right child of the node
	 * @return
	 */
	public BSTNode getRightChild() {
		return rightChild;
	}
	
	
	/**
	 * return data stored in the node
	 * @return
	 */
	public NodeData getData() {
		return data;
	}
	
	
	/**
	 * set a new parent
	 * @param newParent
	 */
	public void setParent(BSTNode newParent) {
		this.parent = newParent;
	}
	
	
	/**
	 * set a new leftChild
	 * @param newLeftChild
	 */
	public void setLeftChild(BSTNode newLeftChild) {
		this.leftChild = newLeftChild;
	}
	
	
	/**
	 * set a new rightChild
	 * @param newRightChild
	 */
	public void setRightChild(BSTNode newRightChild) {
		this.rightChild = newRightChild;
	}
	
	
	/**
	 * set new data
	 * @param data
	 */
	public void setData(NodeData data) {
		this.data = data;
	}
	
	
	/**
	 * return true if the node is a leaf node, false otherwise
	 * @return
	 */
	public boolean isLeaf() {
		return (getRightChild()==null&&getLeftChild()==null);
	}
}
