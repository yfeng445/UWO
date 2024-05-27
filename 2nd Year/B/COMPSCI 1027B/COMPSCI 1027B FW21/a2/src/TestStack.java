
public class TestStack {

	private static int[] readTests(String[] args, int numTests) {
		int[] tests = new int[numTests];
		int value;
		
		if (args.length == 0) 
			for (int i = 0; i < numTests; ++i) tests[i] = i + 1;
		else 
			for (int i = 0; i < args.length; ++i) {
				value = Integer.parseInt(args[i]);
				if (value >= 1 && value <= numTests)
					tests[value-1] = value;
				else System.out.println("ERROR: Test "+value+ "does not exist");
			}
		return tests;
	}
	
	public static void main(String[] args) {
		boolean testPassed;
		Integer val = null;
		int[] tests = readTests(args,7);

		ArrayStack<Integer> s = new ArrayStack<Integer>(30);
		System.out.println("TESTS FOR CLASS ArrayStack");
		System.out.println("==========================\n");

		// Test 1. Test push and peek on a stack with few elements
		if (tests[0] == 1 || tests[1] == 2 || tests[2] == 3) {
			try {
				for (int i = 0; i < 11; ++i)
					s.push(Integer.valueOf(i));

				val = s.peek();
				if ((val.intValue() == 10) && (s.size() == 11))
					testPassed = true;
				else testPassed = false;

			} catch (Exception e) {testPassed = false;}
			if (testPassed)
				System.out.println("Test 1 passed: push() and peek()");
			else
				System.out.println("Test 1 failed: push() and peek()");

			if (tests[1] == 2 || tests[2] == 3) {
				// Test 2. Test pop on a stack with few elements.
				try {
					testPassed = true;
					for (int i = 0; i < 5; ++i)
						val = s.pop();
					if ((val.intValue() == 6) && !s.isEmpty()) 
						testPassed = true;
					else testPassed = false;

				} catch (Exception e) {testPassed = false;}
				if (testPassed)
					System.out.println("Test 2 passed: pop()");
				else
					System.out.println("Test 2 failed: pop()");
			}

			if (tests[2] == 3) {
				// Test 3. Pop on an empty stack
				try {
					for (int i = 0; i < 7; ++i)
						val = s.pop();
					testPassed = false;

				} catch (EmptyStackException e) {testPassed = true;} 
				catch (Exception e) {testPassed = false;}
				if (testPassed)
					System.out.println("Test 3 passed: pop() empty");
				else
					System.out.println("Test 3 failed: pop() empty");
			}
		}

		if (tests[3] == 4) {
			// Test 4. Test that size of stack is increased correctly
			s = new ArrayStack<Integer>(25);

			try {
				testPassed = true;
				for (int i = 0; i < 226; ++i) {
					s.push(Integer.valueOf(i));
					if ((i == 25) && (s.length() != 35)) {
						testPassed = false;
						break;
					}
					else if ((i == 75) && (s.length() != 110)) {
						testPassed = false;
						break;
					}
					else if ((i == 225) && (s.length() != 440)) {
						testPassed = false;
						break;
					}
				}
			} catch (Exception e) {testPassed = false; System.out.println(e);}
			
//			System.out.println(s.length());

			if (testPassed)
				System.out.println("Test 4 passed: increase stack");
			else System.out.println("Test 4 failed: increase stack");
		}

		if (tests[4] == 5) {
			// Test 5. Test that the size of the stack is decreased correctly.
			s = new ArrayStack<Integer>(200);
			int result;
			try {
				testPassed = true;
				for (int i = 0; i <= 25; ++i)
					s.push(Integer.valueOf(i)); 
	
				for (int i = 25; i > 1; --i) {
					
					result = s.pop();
					if ((i == 24) && (s.length() != 50)) {
						System.out.println("19: "+s.length());
						testPassed = false;
						break;
					}
					else if ((i == 3) && (s.length() != 14)) {
						System.out.println("9: "+s.length());
						testPassed = false;
						break;
					}
				}
				if (s.length() != 14) testPassed = false;
			} catch (Exception e) {testPassed = false;}

			if (!testPassed)
				System.out.println("Test 5 failed: decrease stack");
			else System.out.println("Test 5 passed: decrease stack");
		}

		if (tests[5] == 6) {
			// Test 6. Test push, pop, size
			testPassed = true;
			try {
				s = new ArrayStack<Integer>();
				for (int i = 0; i < 990; ++i)
					s.push(Integer.valueOf(i));

				if (s.size() != 990)
					testPassed = false;
				for (int i = 989; i >= 0; --i) {
					val = s.pop();
					if (val.intValue() != i) {
						testPassed = false;
						break;
					}
				}

			} catch (Exception e) {testPassed = false;}
			if (testPassed)
				System.out.println("Test 6 passed: push(), pop(), size()");
			else
				System.out.println("Test 6 failed: push(), pop(), size()");
		}

		if (tests[6] == 7) {
			// Test 7. Test toString
			s = new ArrayStack<Integer>(3);
			testPassed = true;
			try {
				for (int i = 0; i < 3; ++i)
					s.push(Integer.valueOf(i));
				String out = s.toString();
					if (out.equals("Stack: 0, 1, 2")) testPassed = true;
		    else testPassed = false;
			}
			catch(Exception e) {testPassed = false;}
			if (testPassed) 
				System.out.println("Test 7 passed: toString()");
			else {
				
				System.out.println("Test 7 failed: toString()");
				System.out.println("Expected Result: Stack: 0, 1, 2");
				System.out.println("Actual Result: " + s.toString());
				
			}	
		}
	}
}