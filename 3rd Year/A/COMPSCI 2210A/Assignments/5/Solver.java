import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 * This class provides method to implement a labyrinth and find out the way from entrance to the exit.
 * @author Yulun Feng
 * @ID 251113989
 * @Date Dec 4, 2021
 *
 */

public class Solver {
	
	private Graph labyrinth;
	private int S; // size of the board
	private int W; // number of rooms in each row
	private int L; // number of rooms in each column
	private int K1; //number of blast bombs
	private int K2; //number of melt bombs
	private Node exit;
	private Node entrance;
	private char[][] matrix;
	private Stack<Node> path = new Stack<Node>();
	private Stack itemUse = new Stack();
	
	
	/**
	 * Constructor of the class, read a file and turn the data in the file into a graph
	 * @param inputFile
	 * @throws LabyrinthException
	 */
	public Solver(String inputFile) throws LabyrinthException {
		try {	
			// Read file
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			S = Integer.valueOf(br.readLine());
			W = Integer.valueOf(br.readLine());
			L = Integer.valueOf(br.readLine());
			K1 = Integer.valueOf(br.readLine());
			K2 = Integer.valueOf(br.readLine());
			this.matrix = new char[L*2-1][W*2-1];
			this.labyrinth = new Graph(W*L);
			
			// Add the file into a matrix
			int x = 0;
			String line = br.readLine();
			while(!(line==null)) {
				char[] Char = line.toCharArray();
				for(int i = 0; i<Char.length; i++) {
					matrix[x][i] = Char[i];	
				}	
				x+=1;
				line = br.readLine();
			}
			
			//adding the matrix to the graph
			for(int i = 0; i<matrix.length; i++) {
				for(int j = 0; j<matrix[0].length;j++) {
					if(matrix[i][j]=='e') {
						this.entrance = labyrinth.getNode(i/2*(matrix[0].length+1)/2+j/2);
					}
					if(matrix[i][j]=='x') {
						this.exit = labyrinth.getNode(i/2*(matrix[0].length+1)/2+j/2);
					}
					if(matrix[i][j]=='-') {
						labyrinth.insertEdge(labyrinth.getNode(i/2*(matrix[0].length+1)/2+j/2), labyrinth.getNode(i/2*(matrix[0].length+1)/2+j/2+1), 1);
					}
					if(matrix[i][j]=='b') {
						labyrinth.insertEdge(labyrinth.getNode(i/2*(matrix[0].length+1)/2+j/2), labyrinth.getNode(i/2*(matrix[0].length+1)/2+j/2+1), 2);
					}
					if(matrix[i][j]=='r') {
						labyrinth.insertEdge(labyrinth.getNode(i/2*(matrix[0].length+1)/2+j/2), labyrinth.getNode(i/2*(matrix[0].length+1)/2+j/2+1), 3);
					}
					if(matrix[i][j]=='m') {
						labyrinth.insertEdge(labyrinth.getNode(i/2*(matrix[0].length+1)/2+j/2), labyrinth.getNode(i/2*(matrix[0].length+1)/2+j/2+1), 4);
					}
					if(matrix[i][j]=='|') {
						labyrinth.insertEdge(labyrinth.getNode((i/2)*(matrix[0].length+1)/2+j/2), labyrinth.getNode((i/2+1)*(matrix[0].length+1)/2+j/2),1);
					}
					if(matrix[i][j]=='B') {
						labyrinth.insertEdge(labyrinth.getNode((i/2)*(matrix[0].length+1)/2+j/2), labyrinth.getNode((i/2+1)*(matrix[0].length+1)/2+j/2),2);
					}
					if(matrix[i][j]=='R') {
						labyrinth.insertEdge(labyrinth.getNode((i/2)*(matrix[0].length+1)/2+j/2), labyrinth.getNode((i/2+1)*(matrix[0].length+1)/2+j/2),3);
					}
					if(matrix[i][j]=='M') {
						labyrinth.insertEdge(labyrinth.getNode((i/2)*(matrix[0].length+1)/2+j/2), labyrinth.getNode((i/2+1)*(matrix[0].length+1)/2+j/2),4);
					}						
				}
			}
		}
		catch (Exception e){
			throw new LabyrinthException(inputFile);
		}
	}
	
	
	/**
	 * return the graph
	 * @return graph
	 * @throws LabyrinthException
	 */
	public Graph getGraph() throws LabyrinthException{
		if(labyrinth==null) {
			throw new LabyrinthException(null);
		}
		else {
			return labyrinth;			
		}
	}
	
	
	/**
	 * return an iterator storing nodes in the path
	 * @return an iterator storing nodes in the path if the path is found, otherwise return null
	 * @throws GraphException
	 */
	public Iterator solve() throws GraphException {
		if(pth(this.entrance, this.exit)) {
			return this.path.iterator();
		}
		else {
			return null;
		}
		
	}

	
	/**
	 * this private method find the path and put nodes into a stack
	 * @param entrance 
	 * @param exit
	 * @return true if the path is found, false otherwise
	 * @throws GraphException
	 */
	private boolean pth(Node entrance, Node exit) throws GraphException {
		entrance.setMark(true);
		path.push(entrance);
		//exit found
		if(entrance.equals(exit)) {
			return true;
		}
		
		//the exit still not found
		ArrayList<Edge> lst = labyrinth.incidentEdges(entrance);
		Node otherSide;
		Edge edge;
		for(int i = 0; i<lst.size(); i++) {
			edge = lst.get(i);
			if(edge.firstEndpoint().equals(entrance)) {
				otherSide = edge.secondEndpoint();
			}
			else{
				otherSide = edge.firstEndpoint();
			}
			if(!otherSide.getMark()) {
				if(edge.getType()==1) { //corridor, no item needed
					if(pth(otherSide, exit)) return true;					
				}
				else { //need to use item to get through
					if(edge.getType()==2&&K1>0) { //brick wall
						K1-=1;
						itemUse.push(edge);
						itemUse.push(3);
						edge.setType(1);
						if(pth(otherSide, exit)) return true;	
					}
					else if(edge.getType()==3&&K2>1) { //rock wall
						K2-=2;
						itemUse.push(edge);
						itemUse.push(3);
						edge.setType(1);
						if(pth(otherSide, exit)) return true;	
					}
					else if(edge.getType()==4&&K2>0) { //metal wall
						K2-=1;
						itemUse.push(edge);
						itemUse.push(4);
						edge.setType(1);
						if(pth(otherSide, exit)) return true;	
					}
				}
			}
		}
		Node returnFrom = path.pop();
		//if the wrong path has a wall and item used...
		if(!path.isEmpty()) {
			Node curr = path.peek();
			if(!itemUse.isEmpty()) {
				if(new Edge(returnFrom, curr,0).equals(itemUse.peek())) {
					itemUse.pop();
					labyrinth.getEdge(returnFrom, curr).setType((int) itemUse.pop());
				}			
			}			
		}
		return false;
	}

}

















