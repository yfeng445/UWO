import java.util.*;

/**
 * This class implement the graph
 * @author Yulun Feng
 * @ID 251113989
 * @Date Dec 4, 2021
 *
 */

public class Graph {
	
	private ArrayList<Node> Nodes;
	private ArrayList<ArrayList<Edge>> Edges;
	
	
	/**
	 * Constructor of the class
	 * @param n
	 */
	public Graph(int n) {
		this.Edges = new ArrayList<ArrayList<Edge>>();
		this.Nodes = new ArrayList<Node>(n);
		for(int i = 0; i<n; i++) {
			Nodes.add(new Node(i));
		}
	}
	
	
	/**
	 * insert a new edge into the graph
	 * @param u
	 * @param v
	 * @param edgeType
	 * @throws GraphException
	 */
	public void insertEdge(Node u, Node v, int edgeType) throws GraphException{
		// Using an adjacency list to implement the graph
		Edge newEdge = new Edge(u,v,edgeType);
		if(u==null||v==null||u.equals(v)){
			throw new GraphException(null);
		}		
		// both end point nodes exist
		boolean uexist = false, vexist = false;
		for(int i =0; i<Nodes.size();i++) {
			if(u.getName()==Nodes.get(i).getName()) {
				uexist = true;
			}
			if(v.getName()==Nodes.get(i).getName()) {
				vexist = true;
			}
		}
		if(!uexist||!vexist) {
			throw new GraphException(null);
		}
		// add edge
		boolean added = false;
		for(int i = 0; i<Edges.size();i++) {
			if(u.getName()==Edges.get(i).get(0).firstEndpoint().getName()) { //already has one end, adds to the array list
				Edges.get(i).add(newEdge);
				added = true;
			}
		}
		if(!added) {
			Edges.add(new ArrayList<Edge>());
			Edges.get(Edges.size()-1).add(newEdge);
		}
	}
	
	
	/**
	 * get the node with name
	 * @param name
	 * @return
	 * @throws GraphException
	 */
	public Node getNode(int name) throws GraphException {
		for(int i = 0; i<Nodes.size();i++) {
			if(Nodes.get(i).getName()==name) {
				return Nodes.get(i);
			}
		}
		throw new GraphException(null);
	}
	
	
	/**
	 * return a list storing edges connect to the node
	 * @param u
	 * @return
	 */
	public ArrayList<Edge> incidentEdges(Node u) {
		ArrayList<Edge> lst = new ArrayList<Edge>();
		for(int i = 0; i<Edges.size();i++) {
			for(int j = 0; j<Edges.get(i).size();j++) {
				if(Edges.get(i).get(j).firstEndpoint().equals(u)||Edges.get(i).get(j).secondEndpoint().equals(u)) {
					lst.add(Edges.get(i).get(j));
				}
			}
		}
		if (lst.size()==0) return null;
		else return lst;
	}
	
	
	/**
	 * return an edge with given edges, throws exception if such edge does not exist
	 * @param u
	 * @param v
	 * @return
	 * @throws GraphException
	 */
	public Edge getEdge(Node u, Node v) throws GraphException {
		for(int i = 0; i<Edges.size();i++) {
			if(Edges.get(i).get(0).firstEndpoint().equals(u)||Edges.get(i).get(0).firstEndpoint().equals(v)) {
				for(int j = 0; j<Edges.get(i).size();j++) {
					if(Edges.get(i).get(j).equals(new Edge(u,v,0))) {
						return Edges.get(i).get(j);
					}
				}				
			}
		}
		throw new GraphException(null);
	}

	
	/**
	 * return true if two nodes are adjacent
	 * @param u
	 * @param v
	 * @return
	 * @throws GraphException
	 */
	public boolean areAdjacent(Node u, Node v) throws GraphException {
		if(u==null||v==null||u.equals(v)) {
			throw new GraphException(null);
		}
		for(int i = 0; i<Edges.size(); i++) {
			if(Edges.get(i).get(0).firstEndpoint().equals(u)||Edges.get(i).get(0).firstEndpoint().equals(v)) { // one of the nodes is in the list
				for(int j = 0; j<Edges.get(i).size();j++) {
					if(Edges.get(i).get(j).secondEndpoint().equals(u)||Edges.get(i).get(j).secondEndpoint().equals(v)) {
						return true;
					}				
				}
			}		
		}
		return false;
	}
	
}
