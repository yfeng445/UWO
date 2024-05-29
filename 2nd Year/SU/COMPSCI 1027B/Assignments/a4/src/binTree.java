
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;

class BinNode{
	Integer val;
	BinNode leftNode;
	BinNode rightNode;
	public BinNode(Integer val) {
		this.val = val;
	}
	public BinNode() {
	}

	public Integer getVal() {
		return val;
	}
	public void setVal(int val) {
		this.val = val;
	}
	public BinNode getLeftNode() {
		return leftNode;
	}
	public void setLeftNode(BinNode leftNode) {
		this.leftNode = leftNode;
	}
	public BinNode getRightNode() {
		return rightNode;
	}
	public void setRightNode(BinNode rightNode) {
		this.rightNode = rightNode;
	}
	
}

public class binTree {
	public static void main(String[] args) {
		Integer[] a = {3,9,20,null,null,15,7,null,null,null,null};
		int i=1;
		BinNode root = new BinNode(a[0]);  // 根节点
		BinNode current = null;
		Integer value = null;
		
		//层序创建二叉树
		LinkedList<BinNode> queue = new LinkedList<BinNode>(); 
		queue.offer(root);
		while(i<a.length) {
			current = queue.poll();//从链表中移除并获取第一个节点
			value = a[i++];
			if(value!=null) {
				BinNode left =new BinNode(value);
				current.setLeftNode(left);//创建当前节点的左孩子
				queue.offer(left); // 在链表尾部 左孩子入队
			}
			value=a[i++];
			if(value!=null) {
				BinNode right =new BinNode(value);
				current.setRightNode(right);//创建当前节点的右孩子
				queue.offer(right);// 在链表尾部 右孩子入队
			}
			
		}
		levelIetrator(root);
		
	}
	public static int levelIetrator(BinNode  root) {
		if(root==null) {
			return -1;
		}
		Queue<BinNode> queue = new LinkedList<BinNode>();
		BinNode current = null;
		queue.offer(root);
		while(!queue.isEmpty()) {
			current = queue.poll();
			if(current.getLeftNode()!=null) {
				queue.offer(current.getLeftNode());
				System.out.println("节点"+current.val+"的左孩子是"+current.getLeftNode().val);
			}else {
				System.out.println("节点"+current.val+"没有左孩子");
			}
			if(current.getRightNode()!=null) {
				queue.offer(current.getRightNode());
				System.out.println("节点"+current.val+"的右孩子是"+current.getRightNode().val);
			}else {
				System.out.println("节点"+current.val+"没有右孩子");
			}
		}
		return 1;
	}
}
	
