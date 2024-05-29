
public class TestSpecialList {
	
	public static String printList (SpecialList sl) {
		LinearNode<Integer> curr = sl.getFront();
		String str = "";
		while (curr != null) {
			str += curr.getElement() + "  ";
			curr = curr.getNext();
		}
		return str;
	}

	public static void main(String[] args) {
		SpecialList sl;
		
		Integer x = new Integer(3);
		Integer y = new Integer(3);
		
		System.out.println(x == y);
		
		// Test 1
		
		sl = new SpecialList(0);
		sl.addToList(4);
		sl.addToList(2);
		sl.addToList(-5);
		sl.addToList(-1);
		sl.addToList(-7);
		sl.addToList(3);
		String s1 = printList(sl);
		
		if (s1.equals("-7  -1  -5  4  2  3  ")) {
			System.out.println("Test 1 Passed");
		} else {
			System.out.println("Test 1 Failed");
		}

		
		// Test 2
		
		sl = new SpecialList(8);
		sl.addToList(5);
		sl.addToList(13);
		sl.addToList(25);
		sl.addToList(41);
		sl.addToList(12);
		sl.addToList(17);
		sl.addToList(7);
		sl.addToList(0);
		sl.addToList(24);
		sl.addToList(3);
		
		String s2 = printList(sl);
		
		if (s2.equals("3  0  7  5  13  25  41  12  17  24  ")) {
			System.out.println("Test 2 Passed");
		} else {
			System.out.println("Test 2 Failed");
		}
		
		
		// Test 3
		
		sl = new SpecialList(5);
		sl.addToList(1);
		sl.addToList(9);
		sl.addToList(6);
		sl.addToList(3);
		sl.addToList(11);
		sl.addToList(15);
		sl.addToList(2);
		sl.addToList(-5);
		
		String s3 = printList(sl);
		
		if (s3.equals("-5  2  3  1  9  6  11  15  ")) {
			System.out.println("Test 3 Passed");
		} else {
			System.out.println("Test 3 Failed");
		}


	}

}
