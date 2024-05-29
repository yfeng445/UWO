public class TestOdd {

	public static void main(String[] args) {
		// Create the tree in the first figure of the assignment
		Node[] level1 = new Node[3];  // Children of the root
		Node[] level21 = new Node[3]; // Children of the first child of the root
		Node[] level22 = new Node[4]; // Children of the second child of the root
		Node[] level23 = new Node[1]; // Children of the third child of the root
		Node[] level3 = new Node[2];  // Nodes at the last level of the tree
		level3[0] = new Node(5);
		level3[1] = new Node(5);
		for (int i = 0; i < 3; ++i) level21[i] = new Node(4); 
		level22[0] = new Node(1);
		level22[1] = new Node(1);
		level22[2] = new Node(1,level3);
		level22[3] = new Node(8);
		level23[0] = new Node(6);
		level1[0] = new Node(7,level21);
		level1[1] = new Node(7, level22);
		level1[2] = new Node(7,level23);
		Node r = new Node(3,level1);
		
		Odd prog = new Odd();
		int answer = prog.numOdd(r);
		if (answer != 3)
		       System.out.println("Incorrect output: "+answer);
		else System.out.println("Correct output");
	}
}
