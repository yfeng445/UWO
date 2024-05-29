import java.util.*;

public interface GraphADT {
  // The description of these methods is given in the assignment

  public void insertEdge(Node nodeu, Node nodev, int type) throws GraphException;

  public Node getNode(int u) throws GraphException;

  public ArrayList<Edge> incidentEdges(Node u) throws GraphException;

  public Edge getEdge(Node u, Node v) throws GraphException;

  public boolean areAdjacent(Node u, Node v) throws GraphException;
}
