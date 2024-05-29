import java.util.*;
import java.io.*;

public class draftSolver {
	
	
	public static void main(String[] args) throws IOException, GraphException {
		BufferedReader br = new BufferedReader(new FileReader("src/lab1"));
		int S = Integer.valueOf(br.readLine());
		int W = Integer.valueOf(br.readLine());
		int L = Integer.valueOf(br.readLine());
		int K1 = Integer.valueOf(br.readLine());
		int K2 = Integer.valueOf(br.readLine());
		Node exit;
		Node entrance;
		
		char[][] matrix = new char[L*2-1][W*2-1];
		Graph labyrinth = new Graph(W*L);
		
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
		
		for(int i = 0; i<matrix.length; i++) {
			for(int j = 0; j<matrix[0].length;j++) {
				System.out.print(matrix[i][j]);
				if(matrix[i][j]=='e') {
					entrance = labyrinth.getNode(i/2*(matrix[0].length+1)/2+j/2);
				}
				if(matrix[i][j]=='x') {
					exit = labyrinth.getNode(i/2*(matrix[0].length+1)/2+j/2);
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
			System.out.println("");
		}
		
		
		
		
		/*
		for(int i = 0; i<matrix.length;i++) {
			for(int j = 0; j<matrix[0].length;j++) {
				System.out.print(matrix[i][j]);
			}
			System.out.println("");
		}
		*/
		

		System.out.println("dummy"); //b
	}

}
