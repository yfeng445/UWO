/**
 * This class implement edges in the graph
 * @author Yulun Feng
 * @ID 251113989
 * @Date Dec 4, 2021
 *
 */

public class Edge {
	
	private Node firstEndpoint, secondEndpoint;
	private int type;
	
	/**
	 * Constructor of the class
	 * @param u
	 * @param v
	 * @param edgeType
	 */
	public Edge(Node u, Node v, int edgeType) {
		this.firstEndpoint = u;
		this.secondEndpoint = v;
		this.type = edgeType;
	}
	
	
	/**
	 * return the first end point of the edge
	 * @return
	 */
	public Node firstEndpoint() {
		return this.firstEndpoint;
	}
	
	
	/**
	 * return the second end point of the edge
	 * @return
	 */
	public Node secondEndpoint() {
		return this.secondEndpoint;
	}
	
	
	/**
	 * return type of the wall
	 * @return
	 */
	public int getType() {
		return this.type;
	}
	
	
	/**
	 * set type of the wall
	 * @param newType
	 */
	public void setType(int newType) {
		this.type = newType;
	}
	
	
	/**
	 * return true if two edges having same end points
	 */
	public boolean equals(Edge otherEdge) {
		return((this.firstEndpoint.getName()==otherEdge.firstEndpoint.getName()&&this.secondEndpoint.getName()==otherEdge.secondEndpoint.getName())||
				(this.secondEndpoint.getName()==otherEdge.firstEndpoint.getName()&&this.firstEndpoint.getName()==otherEdge.secondEndpoint.getName()));
	}
}

