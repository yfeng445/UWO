/**
 * This class implement the node
 * @author Yulun Feng
 * @ID 251113989
 * @Date Dec 4, 2021
 *
 */

public class Node {

	private int name;
	private boolean marked;
	
	
	/**
	 * Constructor of the class
	 * @param nodeName
	 */
	public Node(int nodeName) {
		this.name = nodeName;
		this.marked = false;
	}
	
	
	/**
	 * mark the node
	 * @param mark
	 */
	public void setMark(boolean mark) {
		this.marked = mark;
	}
	
	
	/**
	 * return true if the node is marked
	 * @return
	 */
	public boolean getMark() {
		return this.marked;
	}
	
	
	/**
	 * return the name of the node
	 * @return
	 */
	public int getName() {
		return this.name;
	}
	
	
	/**
	 * return true if two nodes have same name
	 * @param otherNode
	 * @return
	 */
	public boolean equals(Node otherNode) {
		return this.name==otherNode.getName();
	}
}
