import java.util.LinkedList;
import java.util.Queue;

public class PrimeTree {
	
	static int globalLargest = 0;
	
	public static boolean primeCheck(int candidateNum) {
		for(int i=2;i<candidateNum;i++)
		{
			if(candidateNum % i == 0)
			{
				return false;
			}
		}
		return true;
	}
	
	
	public static int treePrimeCheck(Node rootNode) {
		int lv = Integer.MIN_VALUE;
		lv = primeRecurse(rootNode.leftNode, lv);
		int rv = Integer.MIN_VALUE;
		rv = primeRecurse(rootNode.rightNode, rv);
		if(lv >= rv)
			return 0;
		return 1;
	}
	
	public static int primeRecurse(Node rootNode, int largest) {
		if(rootNode == null)
			return largest;
		if(primeCheck(rootNode.element))
		{
			if(rootNode.element > largest)
			{
				largest = rootNode.element;
			}
		}
		largest = primeRecurse(rootNode.leftNode, largest);
		largest = primeRecurse(rootNode.rightNode, largest);
		if(globalLargest < largest)
			globalLargest = largest;
		return largest;
		
		
	}
	
	/**
	 * For reference only, must be modified to suit the question.
	 public static void levelOrder(Node startNode, int modifier) {
		Queue<Node> queue=new LinkedList<Node>();
		queue.add(startNode);
		while(!queue.isEmpty())
		{
			Node tempNode=queue.poll();
			
			if(tempNode.leftNode!=null)
				queue.add(tempNode.leftNode);
			if(tempNode.rightNode!=null)
				queue.add(tempNode.rightNode);
			
		}
	}

	 */
	
	public static String treePrimify(Node rootNode, int largestPrime) {
		
		//You are welcome to change this code, but it's provided for reference.
		String output = "";
		output += globalLargest;
		for(int i=globalLargest - 1;i>=0;i--)
			if(primeCheck(i))
			{
				output += i;
				break;
			}
		Queue<Node> queue=new LinkedList<Node>();
		queue.add(rootNode);
		boolean three = false;
		while(!queue.isEmpty())
		{
			Node tempNode=queue.poll();
			int x = tempNode.element;
			if(primeCheck(x) && x >= 3)
			{
				output += x;
			}
			if(x == 3)
				three = true;
			tempNode.element = globalLargest;
			if(tempNode.leftNode!=null)
				queue.add(tempNode.leftNode);
			if(tempNode.rightNode!=null)
				queue.add(tempNode.rightNode);
		}
		
		
		
		return output;
	}

}


