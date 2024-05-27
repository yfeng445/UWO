
public class RecListTest {
	
	public static void main(String[] args) {
		
		DoubleNode<String>[] nodes = createDLL(new String[] {"Peach", "Apple", "Banana", "Grape", "Mango"});
		DoubleNode<String> front = nodes[0];
		DoubleNode<String> rear = nodes[1];
		RecList rl = new RecList(front, rear);
		
		DoubleNode<String> temp;
		
		boolean t1, t2, t3;
		
		t1 = t2 = t3 = false;
		
		// recForward

		try {
			temp = rl.recForward(front, "Mango");
			t1 = temp == rear;
			temp = rl.recForward(front.getNext().getNext(), "Apple");
			t2 = temp == null;
			temp = rl.recForward(front, "Plum");
			t3 = temp == null;
		} catch (NullPointerException e) {
			t1 = t2 = t3 = false;
		}

		if (t1 && t2 && t3) {
			System.out.println("Test 1 Passed");
		} else {
			System.out.println("Test 1 Failed");
		}
		
		// recBackward
		
		try {
			temp = rl.recBackward(rear, "Banana");
			t1 = temp == rear.getPrevious().getPrevious();
			temp = rl.recBackward(rear.getPrevious(), "Mango");
			t2 = temp == null;
			temp = rl.recBackward(rear, "Plum");
			t3 = temp == null;
		} catch (NullPointerException e) {
			t1 = t2 = t3 = false;
		}
		
		if (t1 && t2 && t3) {
			System.out.println("Test 2 Passed");
		} else {
			System.out.println("Test 2 Failed");
		}
		
		// traverse

		Object[] temp2;
		temp2 = rl.traverse("Banana");
		t1 = (DoubleNode)temp2[0] == front.getNext().getNext() && (Integer)temp2[1] == 1;
		temp2 = rl.traverse("Apple");
		t2 = (DoubleNode)temp2[0] == front.getNext() && (Integer)temp2[1] == -1;
		temp2 = rl.traverse("Plum");
		t3 = (DoubleNode)temp2[0] == null && (Integer)temp2[1] == 1;
		
		if (t1 && t2 && t3) {
			System.out.println("Test 3 Passed");
		} else {
			System.out.println("Test 3 Failed");
		}

	}
	
	public static DoubleNode<String>[] createDLL (String[] values) {
		
		DoubleNode<String> n1 = new DoubleNode<String>(values[0]);
		DoubleNode<String> prev = n1;
		DoubleNode<String> node = null;
		
		for (int i = 1; i < values.length; i++) {
			node = new DoubleNode<String>(values[i]);
			prev.setNext(node);
			node.setPrevious(prev);
			prev = node;
		}

		return new DoubleNode[] {n1, node};
	}

}
