/**
 * This builds a tree
 * @author Yulun Feng
 * @ID 251113989
 * June 22, 2021 
 */
public class TreeBuilder<T> {
	private LinkedBinaryTree tree;
	
	/**
	 * Constructor of the class, building a tree in level order
	 * @param array
	 */
	public TreeBuilder(T[] array) {
		LinkedQueue dataQueue = new LinkedQueue();
		LinkedQueue parentQueue = new LinkedQueue();
		for(int i = 0; i<array.length;i++) {
				dataQueue.enqueue(array[i]);				
		}
		BinaryTreeNode root = new BinaryTreeNode(dataQueue.dequeue());
		tree = new LinkedBinaryTree(root);
		BinaryTreeNode curr = root;
		parentQueue.enqueue(root);
		
		while(!dataQueue.isEmpty()) {
			curr = (BinaryTreeNode) parentQueue.dequeue();
			if(!(curr.getData()==null)) {
				BinaryTreeNode a = new BinaryTreeNode(dataQueue.dequeue());
				if(!(a.getData()==null)) {
					curr.setLeft((BinaryTreeNode) a);
					parentQueue.enqueue(a);
				}
			}
			if(!(curr.getData()==null)) {
				BinaryTreeNode b = new BinaryTreeNode(dataQueue.dequeue());	
				if(!(b.getData()==null)) {
					curr.setRight((BinaryTreeNode) b);
					parentQueue.enqueue(b);
				}				
			}
		}
	}
	
	/**
	 * return tree
	 * @return
	 */
	public LinkedBinaryTree getTree() {
		return tree;
	}
}
