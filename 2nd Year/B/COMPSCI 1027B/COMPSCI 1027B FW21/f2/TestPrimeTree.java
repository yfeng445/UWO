
public class TestPrimeTree {
	
	
public static void main(String[] args) {
		

		// TEST 1: CHECK PRIME CHECKER
	
	
		if(PrimeTree.primeCheck(41) == true && PrimeTree.primeCheck(42) == false) {
			System.out.println("Test 1 Passed");
		}
		else {
			System.out.println("Test 1 Failed");
		}
	
		// TEST 2 & 3 SET UP. CONSTRUCT TREE WITH NODES.
		Node rootNode =new Node(4);
		Node node20=new Node(2);
		Node node10=new Node(1);
		Node node30=new Node(3);
		Node node60=new Node(12);
		Node node50=new Node(5);
		Node node70=new Node(7);
 
		rootNode.leftNode=node20;
		rootNode.rightNode=node60;
 
		node20.leftNode=node30;
		node20.rightNode=node10;
 
		node60.leftNode=node50;
		node60.rightNode=node70;
		
		// TEST 2: CHECK IF TREE WITH LARGEST PRIME IDENTIFIED
		int result2 = PrimeTree.treePrimeCheck(node20);
		int result1 = PrimeTree.treePrimeCheck(rootNode);
		
		if (result1 == 1 && result2 == 0) {
			System.out.println("Test 2 Passed");
		}
		else {
			System.out.println("Test 2 Failed");
		}
		
		
		// TEST 3: RUN TREE PRIMIFY AND THEN CHECK OUTPUT.
		String s = PrimeTree.treePrimify(rootNode.leftNode,PrimeTree.globalLargest);
		if (s.equals("753") && PrimeTree.treePrimeCheck(rootNode) == 0) {
			System.out.println("Test 3 Passed");
		}
		else {
			System.out.println("Test 3 Failed");
		}
		
		
	}
	

}
