
public class LinearNode {
	
	private Layout data;
	private LinearNode next;
	private int value;
	
	public LinearNode() {
		next = null;
		data = null;
		value = 0;
	}
	
	public LinearNode(Layout data, int value) {
		this.data = data;
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public Layout getData() {
		return data;
	}
	
	public LinearNode getNext() {
		return next;
	}
	
	
	public void setValue(int newValue) {
		this.value = newValue;
	}
	
	public void setData(Layout newData) {
		this.data = newData;
	}
	
	public void setNext(LinearNode node) {
		this.next = node;
	}
}
