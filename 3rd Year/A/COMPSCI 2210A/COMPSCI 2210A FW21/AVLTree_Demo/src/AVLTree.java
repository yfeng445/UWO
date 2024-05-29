
public class AVLTree<T> {
	
	private AVLNode<T> root;
	
	public AVLTree(AVLNode<T> r) {
		this.root = r;
	}
	
	public AVLTree() {
		this.root = null;
	}
	
	public void put(AVLNode<T> r, AVLNode<T> n) {
		if(r==null) {
			AVLNode<T> left = new AVLNode<T>();
			left.setParent(r);
			AVLNode<T> right = new AVLNode<T>();
			right.setParent(r);
			r.setElement(n.getElement());
			r.setLeft(left);
			r.setRight(right);
		}
		else {
			if(r.getElement().hashCode()<n.getElement().hashCode()) {
				put(r.getLeft(),n);
				if(r.getLeft().getHeight()-r.getRight().getHeight()>1) {
					if(n.getElement().hashCode()-r.getElement().hashCode()<0) {
						
					}
				}
				
				
				
				
			}
			else if(r.getElement().hashCode()>n.getElement().hashCode()) {
				put(r.getRight(),n);
			}
			
			
			
		}
	}
	
	
	
	public void setRoot(AVLNode<T> newRoot) {
		this.root = newRoot;
	}
	
}
