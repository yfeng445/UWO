import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

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
	private Stack<Node> stackPath = new Stack<Node>();
	private Queue<Node> queuePath = new LinkedList<Node>();
	private Stack itemUse = new Stack();

	public Solver(String inputFile) throws Exception {
		try {	
			// Read file
			BufferedReader br = new BufferedReader(new FileReader("src/"+inputFile));
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
						labyrinth.insertEdge(labyrinth.getNode(i/2*(matrix[0].length+1)/2+j/2+1), labyrinth.getNode(i/2*(matrix[0].length+1)/2+j/2), 1);
						
					}
					if(matrix[i][j]=='b') {
						labyrinth.insertEdge(labyrinth.getNode(i/2*(matrix[0].length+1)/2+j/2), labyrinth.getNode(i/2*(matrix[0].length+1)/2+j/2+1), 2);
						labyrinth.insertEdge(labyrinth.getNode(i/2*(matrix[0].length+1)/2+j/2+1), labyrinth.getNode(i/2*(matrix[0].length+1)/2+j/2), 2);
					}
					if(matrix[i][j]=='r') {
						labyrinth.insertEdge(labyrinth.getNode(i/2*(matrix[0].length+1)/2+j/2), labyrinth.getNode(i/2*(matrix[0].length+1)/2+j/2+1), 3);
						labyrinth.insertEdge(labyrinth.getNode(i/2*(matrix[0].length+1)/2+j/2+1), labyrinth.getNode(i/2*(matrix[0].length+1)/2+j/2), 3);
					}
					if(matrix[i][j]=='m') {
						labyrinth.insertEdge(labyrinth.getNode(i/2*(matrix[0].length+1)/2+j/2), labyrinth.getNode(i/2*(matrix[0].length+1)/2+j/2+1), 4);
						labyrinth.insertEdge(labyrinth.getNode(i/2*(matrix[0].length+1)/2+j/2+1), labyrinth.getNode(i/2*(matrix[0].length+1)/2+j/2), 4);
					}
					if(matrix[i][j]=='|') {
						labyrinth.insertEdge(labyrinth.getNode((i/2)*(matrix[0].length+1)/2+j/2), labyrinth.getNode((i/2+1)*(matrix[0].length+1)/2+j/2),1);
						labyrinth.insertEdge(labyrinth.getNode((i/2+1)*(matrix[0].length+1)/2+j/2),labyrinth.getNode((i/2)*(matrix[0].length+1)/2+j/2), 1);
					}
					if(matrix[i][j]=='B') {
						labyrinth.insertEdge(labyrinth.getNode((i/2)*(matrix[0].length+1)/2+j/2), labyrinth.getNode((i/2+1)*(matrix[0].length+1)/2+j/2),2);
						labyrinth.insertEdge(labyrinth.getNode((i/2+1)*(matrix[0].length+1)/2+j/2),labyrinth.getNode((i/2)*(matrix[0].length+1)/2+j/2), 2);
						
					}
					if(matrix[i][j]=='R') {
						labyrinth.insertEdge(labyrinth.getNode((i/2)*(matrix[0].length+1)/2+j/2), labyrinth.getNode((i/2+1)*(matrix[0].length+1)/2+j/2),3);
						labyrinth.insertEdge(labyrinth.getNode((i/2+1)*(matrix[0].length+1)/2+j/2),labyrinth.getNode((i/2)*(matrix[0].length+1)/2+j/2), 3);
					}
					if(matrix[i][j]=='M') {
						labyrinth.insertEdge(labyrinth.getNode((i/2)*(matrix[0].length+1)/2+j/2), labyrinth.getNode((i/2+1)*(matrix[0].length+1)/2+j/2),4);
						labyrinth.insertEdge(labyrinth.getNode((i/2+1)*(matrix[0].length+1)/2+j/2),labyrinth.getNode((i/2)*(matrix[0].length+1)/2+j/2), 4);
					}						
				}
			}
		}
		catch (Exception e){
			throw new Exception();
		}
	}

	public Graph getGraph() throws LabyrinthException{
		if(labyrinth==null) {
			throw new LabyrinthException(null);
		}
		else {
			return labyrinth;			
		}
	}
	
	public Iterator solveDFS() throws GraphException {
		if(DFS(this.entrance, this.exit)) {
			return this.stackPath.iterator();
		}
		else {
			return null;
		}
	}
	
	public Iterator solveBFS() throws GraphException {
		//System.out.println(this.exit.getName());
		if(BFS(this.entrance, this.exit)) {
			return this.queuePath.iterator();
		}
		else {
			return null;
		}
	}

	private boolean BFS(Node entrance, Node exit) throws GraphException{
		if(!queuePath.isEmpty()) {
			if(queuePath.peek().equals(exit)) {
				return true;
			}				
		}
		entrance.setMark(true);
		queuePath.add(entrance);
		printBFS();	
		while(!queuePath.isEmpty()) {
			ArrayList<Edge> lst = labyrinth.incidentEdges(queuePath.poll());
			for(int i = 0; i<lst.size(); i++) {
				if(lst.get(i).firstEndpoint().equals(entrance)&&!lst.get(i).secondEndpoint().getMark()) {
					queuePath.add(lst.get(i).secondEndpoint());	
				}
				else if(lst.get(i).secondEndpoint().equals(entrance)&&!lst.get(i).firstEndpoint().getMark()){
					queuePath.add(lst.get(i).firstEndpoint());
				}
			}
			BFS(queuePath.poll(),exit);
		}		
		return false;
	}
	
	
	private boolean DFS(Node entrance, Node exit) throws GraphException {
		entrance.setMark(true);
		stackPath.push(entrance);
		printDFS();
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
					if(DFS(otherSide, exit)) return true;					
				}
				else { //need to use item to get through
					if(edge.getType()==2&&K1>0) { //brick wall
						K1-=1;
						itemUse.push(edge);
						itemUse.push(3);
						edge.setType(1);
						if(DFS(otherSide, exit)) return true;	
					}
					else if(edge.getType()==3&&K2>1) { //rock wall
						K2-=2;
						itemUse.push(edge);
						itemUse.push(3);
						edge.setType(1);
						if(DFS(otherSide, exit)) return true;	
					}
					else if(edge.getType()==4&&K2>0) { //metal wall
						K2-=1;
						itemUse.push(edge);
						itemUse.push(4);
						edge.setType(1);
						if(DFS(otherSide, exit)) return true;	
					}
				}
			}
		}
		Node returnFrom = stackPath.pop();
		//if the wrong path has a wall and item used...
		if(!stackPath.isEmpty()) {
			Node curr = stackPath.peek();
			if(!itemUse.isEmpty()) {
				if(new Edge(returnFrom, curr,0).equals(itemUse.peek())) {
					itemUse.pop();
					labyrinth.getEdge(returnFrom, curr).setType((int) itemUse.pop());
				}			
			}			
		}
		return false;
	}
	
	private void printDFS() {
		String str = "PATH: ";
		Stack<Node> stk = new Stack<Node>();
		while(!stackPath.isEmpty()) {
			str+=(stackPath.peek().getName()+", ");
			stk.push(stackPath.pop());
		}
		while(!stk.isEmpty()) {
			stackPath.push(stk.pop());
		}
		System.out.println(str.substring(0, str.length()-2)+".");
	}
	
	private void printBFS() {
		String str = "PATH: ";
		LinkedList<Node> lst = new LinkedList<Node>();
		while(!queuePath.isEmpty()) {
			str+=(queuePath.peek().getName()+", ");
			lst.add(queuePath.poll());
		}
		while(!lst.isEmpty()) {
			queuePath.add(lst.poll());
		}
		System.out.println(str.substring(0, str.length()-2)+".");
	}

}

















