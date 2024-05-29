public class TestDict {

	/*
	 ** Test program for the Dictionary class.
	 ** This program WILL NOT COMPILE if your insert or remove
	 ** methods do not throw a DictionaryException.
	 */

	public static void main(String[] args) {
		Dictionary dict = new Dictionary(9887);
		Layout pair;
		boolean[] test = new boolean[11];
		int i,j;

		if (args.length == 0) 
			for (i = 0; i < 11; ++i) test[i] = true;  // Perform all tests
		else {

			if (args[0].equals("?")) {
				System.out.println("Usage: java TestDict, or java TestDict n1 n2 n3 ... ");
				System.out.println("where each ni has value 1 - 10; with the second invocation");
				System.out.println("only the specified tests will be run");
				System.exit(0);
			}

			for (i = 0; i < 11; ++i) test[i] = false;
			for (i = 0; i < args.length; ++i) {
				j = Integer.parseInt(args[i]);
				if (j >= 1 && j <= 10) test[j] = true; // Perform only specified tests
			}
		}

		// Test 1: insert a data item in the dictionary.
		// Should not throw an exception.

		if (test[1])
			try {
				dict.put(new Layout("answer", 42));
				System.out.println("   Test 1 succeeded");
			}
		catch (DictionaryException e) {
			System.out.println("***Test 1 failed");
		}

		// Test 2: try to insert another data item with the same key.
		// Should throw an exception.
		if (test[2])
			try {
				dict.put(new Layout("answer", 56));
				System.out.println("***Test 2 failed");
			} 
		catch (DictionaryException e) {
			System.out.println("   Test 2 succeeded");
		}

		// Test 3: find a key in the table.
		if (test[3])
			//System.out.println(dict.getScore("answer"));
			if (dict.getScore("answer") == -1) 
				
				System.out.println("***Test 3 failed");
			else System.out.println("   Test 3 succeeded");

		// Test 4: look for an inexistent key
		if (test[4])
			if (dict.getScore("chicken") != -1) {
				System.out.println(dict.getScore("chicken"));
				System.out.println("***Test 4 failed");				
			}

			else System.out.println("   Test 4 succeeded");

		// Test 5: try to delete a nonexistent entry.
		// Should throw an exception.
		if (test[5])
			try {
				dict.remove("chicken");
				System.out.println("***Test 5 failed");
			} 
		catch (DictionaryException e) {
			System.out.println("   Test 5 succeeded");
		}

		// Test 6: delete an actual entry.
		// Should not throw an exception.
		if (test[6])
			try {
				dict.remove("answer");
				System.out.println("   Test 6 succeeded");
			} 
		catch (DictionaryException e) {
			System.out.println("***Test 6 failed");
		}

		int collisions = 0;
		String s;


		// Test 7: insert 10000 different values into the Dictionary
		if (test[7])
			try {
				for (i = 0; i < 10000; ++i) {
					s = (new Integer(i)).toString();
					for (j = 0; j < 5; ++j) s += s;
					collisions += dict.put(new Layout(s,i));
				}
				System.out.println("   Test 7 succeeded");
			} 
		catch (DictionaryException e) {
			System.out.println("***Test 7 failed");
		}


		// Test 8: check that all inserted values are in the Dictionary
		boolean pass = true;
		if (test[8]) {
			for (i = 0; i < 10000; ++i) {
				s = (new Integer(i)).toString();
				for (j = 0; j < 5; ++j) s += s;
				if (dict.getScore(s) == -1) {
					System.out.println("***Test 8 failed");
					pass = false;
					break;
				}
			}
			if (pass) System.out.println("   Test 8 succeeded");
		}

		// Test 9: Remove the first 1000 data items and verify that the rest
		// are in the dictionary 
		pass = true;
		if (test[9])
			try {
				for (i = 0; i < 1000; ++i) {
					s = (new Integer(i)).toString();
					for (j = 0; j < 5; ++j) s += s;
					dict.remove(s);
				}

				for (i = 1000; i < 10000; ++i) {
					s = (new Integer(i)).toString();
					for (j = 0; j < 5; ++j) s += s;
					if (dict.getScore(s) == -1) {
						System.out.println("***Test 9 failed");
						pass = false;
						break;
					}
				}
				if (pass) System.out.println("   Test 9 succeeded");
			}
		catch (DictionaryException e) {
			System.out.println("***Test 9 failed");
		}



		//Test 10: Number of collisions
		if (test[10])
			if (collisions >= 5000) {
				System.out.println("***Test 10 failed");
				System.out.println("Too many collisions: "+collisions);
			}
			else  
				System.out.println(collisions);
				System.out.println("   Test 10 succeeded");

	}
}
