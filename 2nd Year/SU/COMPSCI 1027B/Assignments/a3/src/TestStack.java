
public class TestStack {

	public static void main(String[] args) {

		ArrayStack<String> sStack = new ArrayStack<String>();

		if (sStack.isEmpty() && sStack.size() == 0 && sStack.getTop() == 9) {
			System.out.println("Test 1 Passed");
		} else {
			System.out.println("Test 1 Failed");
		}

		// ------------------------------------------------

		if (sStack.toString().equals("The stack is empty.")) {
			System.out.println("Test 2 Passed");
		} else {
			System.out.println("Test 2 Failed");
		}

		// ------------------------------------------------

		sStack.push("Orange");
		sStack.push("Watermelon");
		sStack.push("Mango");
		sStack.push("Strawberry");
		sStack.push("Kiwi");
	//	System.out.println(sStack.toString());
/*		System.out.println(sStack.size());
		System.out.println(sStack.getLength());
		System.out.println(sStack.getTop());*/
		if (sStack.size() == 5 && sStack.getLength() == 10 && sStack.getTop() == 4) {// top = size()-1
			System.out.println("Test 3 Passed");
		} else {
			System.out.println("Test 3 Failed");
		}

		// ------------------------------------------------
//		System.out.println(sStack.toString());
		if (sStack.toString().equals("Stack: Kiwi, Strawberry, Mango, Watermelon, Orange.")) {
			System.out.println("Test 4 Passed");
		} else {
			System.out.println("Test 4 Failed");
		}

		// ------------------------------------------------

		String res1 = sStack.peek();
	//	System.out.println(sStack.toString());
		String res2 = sStack.pop();
	//	System.out.println(sStack.toString());
		String res3 = sStack.peek();
	//	System.out.println(res3);
	//	System.out.println(sStack.toString());
		String res4 = sStack.pop();
	//	System.out.println(res4);
		//System.out.println(sStack.toString());
		//System.out.println(sStack.size());
	//	System.out.println(sStack.getTop());

		if (res1.equals("Kiwi") && res2.equals("Kiwi") && res3.equals("Strawberry") && res4.equals("Strawberry") 
				&& sStack.size() == 3 && sStack.getTop() == 6) {// top = 9-3
			System.out.println("Test 5 Passed");
		} else {
			System.out.println("Test 5 Failed");
		}

		// ------------------------------------------------

		for (int i = 0; i < 3; i++) {
			sStack.pop();
		}

		if (sStack.isEmpty() && sStack.isEmpty() && sStack.getTop() == 9) {
			System.out.println("Test 6 Passed");
		} else {
			System.out.println("Test 6 Failed");
		}

		// ------------------------------------------------
//		System.out.println(sStack.toString());
		try {
			sStack.pop();
			sStack.peek();
		} catch (StackException e) {
			System.out.println("Test 7 Passed");
		} catch (Exception e) {
			System.out.println("Test 7 Failed");
		}

		// ------------------------------------------------

		sStack = new ArrayStack<String>(21);
		
		if (sStack.isEmpty() && sStack.getLength() == 21 && sStack.getTop() == 20) {
			System.out.println("Test 8 Passed");
		} else {
			System.out.println("Test 8 Failed");
		}

		// ------------------------------------------------

		for (int i = 100; i < 137; i++) {
			sStack.push(String.valueOf(i));
		}

		if (sStack.size() == 37 && sStack.getLength() == 41) {
			System.out.println("Test 9 Passed");
		} else {
			System.out.println("Test 9 Failed");
		}

		// ------------------------------------------------

		for (int i = 0; i < 29; i++) {
			sStack.pop();
		}
		
		if (sStack.toString().equals("Stack: 107, 106, 105, 104, 103, 102, 101, 100.")) {
			System.out.println("Test 10 Passed");
		} else {
			System.out.println("Test 10 Failed");
		}

	}

}
