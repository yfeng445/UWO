import java.util.*;

/* Program for testing the Graph methods. */
public class TestGraph {

    public static void main (String[] args) {

        final int CORRIDOR = 1;
        final int BRICK = 2;
        final int ROCK = 3;
        final int METAL = 4;
        
        /* Exceptions testing */

	System.out.println("");
	System.out.println("======================================================");
	System.out.println("TestGraph");
	System.out.println("======================================================");
	System.out.println("");

	Graph G = new Graph (1);
	Node u = new Node(0), v = new Node (1), u1 = new Node(0);
	Edge uv;
	ArrayList<Edge> neighbours;

	try {
	    G.insertEdge(u,v,BRICK);
	    System.out.println("    Test 1 failed: Method insertEdge must throw an exception when");
	    System.out.println("           trying to insert and invalid edge.");
	    u = G.getNode(5);
	    System.out.println("   Test 1 failed: Method getNode must throw an exception when");
	    System.out.println("          trying to access inexistent node.");
	    uv = G.getEdge(u,v);
	    System.out.println("    Test 1 failed: Method getEdge must throw an exception when");
	    System.out.println("           trying to access an invalid edge!");
	    neighbours = G.incidentEdges(v);
	    System.out.println("    Test 1 failed: Method incidentEdges must throw an exception when");
	    System.out.println("           trying to access an invalid node!");
	    boolean adjacent = G.areAdjacent(u,v);
	    System.out.println("    Test 1 failed: Method areAdjacent must throw an exception when");
	    System.out.println("           trying to access an invalid node!");
	}
	catch (GraphException e){
	    Edge e1 = new Edge(u,v,1);
	    Edge e2 = new Edge (v,u1,2);
	    if (u.equals(u1) && !u.equals(v) && e1.equals(e2))
	         System.out.println("    Test 1 passed");
	    else System.out.println("    Test 1 failed: Method equals in class Node or Edge is incorrect");
	}


        /* Create a graph with 9 nodes and 11 edges. Query the graph and check that
           all edges are stored correctly                                           */

        /* Degree of current node */
        int degree;
        int node1, node2;

        int numNodes = 14;

        /* Set of nodes of the graph */
        Node[] V = new Node[numNodes];

        /* Degrees pf the nodes in test graph */
        int NodeDegree[] = {4, 3, 2, 3, 6, 3, 4, 6, 3, 6, 1, 1, 1, 1 };

        /* Adjacency matrix for test graph */
        int M[][] = {{0, 2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 0
                     {2, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0}, // 1
                     {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 2
                     {1, 0, 0, 0, 1, 0, 0, 2, 0, 0, 0, 0, 0, 0}, // 3
                     {1, 1, 1, 1, 0, 1, 2, 0, 0, 0, 0, 0, 0, 0}, // 4
                     {0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0}, // 5
                     {0, 0, 0, 0, 2, 1, 0, 1, 2, 0, 0, 0, 0, 0}, // 6
                     {0, 1, 0, 2, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0}, // 7
                     {0, 0, 0, 0, 0, 0, 2, 1, 0, 1, 0, 0, 0, 0}, // 8
                     {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 2, 1, 1, 1}, // 9
                     {0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0}, // 10
                     {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, // 11
                     {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, // 12
                     {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}  //13
                    };

	int type;
	boolean failed;
	int i = 0, j = 0;

        G = new Graph(numNodes);

	failed = false;
        try {
	    /* Get all nodes of the graph */
	    for (i = 0; i < numNodes; ++i) {
		V[i] = G.getNode(i);
		if (V[i].getName() != i) failed = true;
	    }

	    V[2].setMark(true);
	    if (V[2].getMark() == false) failed = true;

	    if (!failed) System.out.println("    Test 2 passed");
	    else  System.out.println("    Test 2 failed");
	}
	catch (GraphException e) {
		System.out.println("    Test 2 failed");
	}

	failed = false;
	try {
	/* Insert the edges */
	for (i = 0; i < numNodes; ++i)
	    for (j = 0; j < i; ++j)
		if (M[i][j] == 1) G.insertEdge(V[i],V[j],BRICK);
		else if (M[i][j] == 2) G.insertEdge(V[i],V[j],CORRIDOR);
	}
	catch (GraphException e) {
	    System.out.println("    Test 3 failed");
	    failed = true;
	}
	if (!failed) System.out.println("    Test 3 passed");

	failed = false;
	try {
	    for (i = 0; i < numNodes; ++i)
		for (j = 0; j < i; ++j) {
		    if (M[i][j] != 0) {
			uv = G.getEdge(V[i],V[j]);
			type = uv.getType();
			if ((M[i][j] == 1 && type != BRICK) ||
			    (M[i][j] == 2 && type != CORRIDOR)) failed = true;
			u = uv.firstEndpoint();
			if ((u.getName() != i) && u.getName() != j) failed = true;
			v = uv.secondEndpoint();
			if ((v.getName() != i) && v.getName() != j) failed = true;
			if (u.getName() == v.getName()) failed = true;
		    }
		}
	    if (!failed) System.out.println("    Test 4 passed");
	    else  System.out.println("    Test 4 failed");
	}
	catch (GraphException e) {
	    System.out.println("    Test 4 failed");
	}

	failed = false;
	try {
	    for (i = 0; i < numNodes; ++i)
		for (j = 0; j < i; ++j) 
		    if (M[i][j] != 0) {
			if (!G.areAdjacent(V[i],V[j]) || !G.areAdjacent(V[j],V[i]))
			    failed = true;
		    }
	    if (!failed)
		System.out.println("    Test 5 passed");
	    else System.out.println("    Test 5 failed");
	}
	catch (GraphException e) {
	    System.out.println("    Test 5 failed");
	}

        try {
            for (i = 0; i < numNodes; ++i) {
                u = G.getNode(i);
                neighbours = G.incidentEdges(u);
                degree = 0;
                int count = 0;
                while (count < neighbours.size()) {
                    uv = (Edge)neighbours.get(count++);
                    ++degree;
                    node1 = uv.firstEndpoint().getName();
                    node2 = uv.secondEndpoint().getName();

                    if (M[node1][node2] == 0) {
                        System.out.println("         Error: There should not be an edge between");
                        System.out.println("                 nodes "+node1+" and "+node2);
                        failed = true;
                    }
                    else if ((uv.getType() == BRICK) && (M[node1][node2] != 1)) {
                        System.out.println("         Error: There should not be an edge of type brick wall between");
                        System.out.println("                nodes "+node1+" and "+node2);
                        failed = true;
                    }
                    else if ((uv.getType() == CORRIDOR) &&(M[node1][node2] != 2)) {
                        System.out.println("         Error:There should not be an edge of type corridor between");
                        System.out.println("               nodes "+node1+" and "+node2);
                        failed = true;
                    }
                }
                if (degree != NodeDegree[i]) {
                    System.out.println("         Error:The degree of node "+i+" should be "+NodeDegree[i]+", not "+degree);
                    failed = true;
                }
            }

	    if (!failed) System.out.println("    Test 6 passed");
	    else  System.out.println("    Test 6 failed");

        }
        catch(GraphException e) {
	    System.out.println("    Test 6 failed");
        }

    }
}


