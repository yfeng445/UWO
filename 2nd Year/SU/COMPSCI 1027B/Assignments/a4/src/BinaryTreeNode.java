/**
 * BinaryTreeNode represents a node in a binary tree with a left and 
 * right child.
 * 
 * @author Dr. Lewis
 * @author Dr. Chase
 * @author: CS1027
 * @version 1.0, 8/19/08
 */

public class BinaryTreeNode<T> {
	private T dataItem;
	private BinaryTreeNode<T> left, right;

	/**
	 * Creates a new tree node with the specified data.
	 *
	 * @param obj  the element that will become a part of the new tree node
	 */
	BinaryTreeNode (T theData) {
		dataItem = theData;
		left = null;
		right = null;
	}

	public T getData () {
		return dataItem;
	}

	public BinaryTreeNode<T> getLeft () {
		return left;
	}

	public BinaryTreeNode<T> getRight () {
		return right;
	}

	public void setData (T newData) {
		dataItem = newData;
	}

	public void setLeft (BinaryTreeNode<T> newLeft) {
		left = newLeft;
	}

	public void setRight (BinaryTreeNode<T> newRight) {
		right = newRight;
	}


}

