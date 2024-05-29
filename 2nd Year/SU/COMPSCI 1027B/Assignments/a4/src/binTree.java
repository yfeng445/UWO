
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
		BinNode root = new BinNode(a[0]);  // ���ڵ�
		BinNode current = null;
		Integer value = null;
		
		//���򴴽�������
		LinkedList<BinNode> queue = new LinkedList<BinNode>(); 
		queue.offer(root);
		while(i<a.length) {
			current = queue.poll();//���������Ƴ�����ȡ��һ���ڵ�
			value = a[i++];
			if(value!=null) {
				BinNode left =new BinNode(value);
				current.setLeftNode(left);//������ǰ�ڵ������
				queue.offer(left); // ������β�� �������
			}
			value=a[i++];
			if(value!=null) {
				BinNode right =new BinNode(value);
				current.setRightNode(right);//������ǰ�ڵ���Һ���
				queue.offer(right);// ������β�� �Һ������
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
				System.out.println("�ڵ�"+current.val+"��������"+current.getLeftNode().val);
			}else {
				System.out.println("�ڵ�"+current.val+"û������");
			}
			if(current.getRightNode()!=null) {
				queue.offer(current.getRightNode());
				System.out.println("�ڵ�"+current.val+"���Һ�����"+current.getRightNode().val);
			}else {
				System.out.println("�ڵ�"+current.val+"û���Һ���");
			}
		}
		return 1;
	}
}
	
