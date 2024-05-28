/**
 * Build a linked list of integers from 1 to 10
 * 
 * @author CS1027
 */
public class BuildLinkedList {

	/*
	 * Print the information stored in all the nodes of the list whose first node is
	 * referenced by front
	 */
	private static void printList(LinearNode<Integer> front) {
		LinearNode<Integer> current = front;
		while(!(front.getNext() == null)) {
			System.out.println(front.getElement());
			front = front.getNext();
		}
		System.out.println(front.getElement());
		
	}

	public static void main(String[] args) {

		// create a linked list that holds 1, 2, ..., 10
		// by starting at 10 and adding each node at head of list

		LinearNode<Integer> front = null; // create empty linked list
		LinearNode<Integer> intNode;

		for (int i = 233; i >= 1; i--) {
			// create a new node for i
			intNode = new LinearNode<Integer>(new Integer(i));
			// add it at the head of the linked list
			intNode.setNext(front);
			front = intNode;
		}

		printList(front);
	}

}
