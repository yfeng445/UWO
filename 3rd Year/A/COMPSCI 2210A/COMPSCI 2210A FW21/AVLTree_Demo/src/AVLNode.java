
public class AVLNode<T> {
	private T element;
	private AVLNode<T> leftChild;
	private AVLNode<T> rightChild;
	private AVLNode<T> parent;
	int height;
	
	public AVLNode(){
		this.element = null;
		this.leftChild = null;
		this.rightChild = null;
		this.height = (Integer)null;
	}
	
	public AVLNode(T element, AVLNode<T> left, AVLNode<T> right, int height) {
		this.element = element;
		this.leftChild = left;
		this.rightChild =right;
		this.height = height;
	}
	
	public T getElement() {
		return this.element;
	}
	
	public AVLNode<T> getLeft() {
		return this.leftChild;
	}
	
	public AVLNode<T> getRight() {
		return this.rightChild;
	}
	
	public AVLNode<T> getParent() {
		return this.parent;
	}
	
	public int getHeight() {
		if(parent==null) {
			return 1;
		}
		else {
			return parent.getHeight()+1;
		}
	}
	
	public void setElement(T newElem) {
		this.element = newElem;
	}
	
	public void setLeft(AVLNode<T> newLeft) {
		this.leftChild = newLeft;
	}
	
	public void setRight(AVLNode<T> newRight) {
		this.rightChild = newRight;
	}
	
	public void setParent(AVLNode<T> newParent) {
		this.parent = newParent;
	}
	
	public boolean isLeaf() {
		return (leftChild==null&&rightChild==null);
	}
}
