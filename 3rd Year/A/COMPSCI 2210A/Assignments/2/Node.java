
public class Node {
	
	private Layout data;
	private Node next;
	
	public Node() {
		next = null;
		data = null;
	}
	
	public Node(Layout data) {
		this.data = data;
		next = null;
	}
	
	public Layout getData() {
		return data;
	}
	
	public Node getNext() {
		return next;
	}
	
	public void setData(Layout newData) {
		this.data = newData;
	}
	
	public void setNext(Node node) {
		this.next = node;
	}
}
