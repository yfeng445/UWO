public class TestDict {

  /*
  ** Test program for the Dictionary class.
  */

  public static void main(String[] args) {
    Dictionary dict = new Dictionary(9887);
    Data pair;
    boolean[] test = new boolean[11];
    int i,j;

    if (args.length == 0) 
	for (i = 0; i < 11; ++i) test[i] = true;
    else {
	for (i = 0; i < 11; ++i) test[i] = false;
	try {
	    for (i = 0; i < args.length; ++i) {
		j = Integer.parseInt(args[i]);
		if (j >= 1 && j <= 10) test[j] = true;
	    }
	} catch (NumberFormatException e) {
	    System.out.println("Usage: java TestDict, or java TestDict n1 n2 n3 ... ");
	    System.out.println("where each ni has value 1 - 10. If a list of values is");
	    System.out.println("specified, only those tests and their dependencies will be run.");
	    System.exit(0);
	}
    }

    // Test 1: insert one data item in the dictionary.
    // Should not throw an exception.

    if (test[1] || test[3] || test[6])
       try {
         dict.put(new Data("answer", 42,1));
         System.out.println("   Test 1 succeeded");
       } catch (DuplicatedKeyException e) {
         System.out.println("***Test 1 failed");
       }

    // Test 2: try to insert another data item with the same key.
    // Should throw an exception.
    if (test[2])
       try {
         dict.put(new Data("answer", 56,1));
         System.out.println("***Test 2 failed");
       } catch (DuplicatedKeyException e) {
         System.out.println("   Test 2 succeeded");
       }

    // Test 3: find a key in the table.
    if (test[3])
       if (dict.get("answer") == null) 
          System.out.println("***Test 3 failed");
       else System.out.println("   Test 3 succeeded");

    // Test 4: look for an inexistent key
    if (test[4])
       if (dict.get("chicken") != null) 
          System.out.println("***Test 4 failed");
       else System.out.println("   Test 4 succeeded");

    // Test 5: try to delete a nonexistent entry.
    // Should throw an exception.
    if (test[5])
       try {
         dict.remove("chicken");
         System.out.println("***Test 5 failed");
       } catch (InexistentKeyException e) {
         System.out.println("   Test 5 succeeded");
       }

    // Test 6: delete an actual entry.
    // Should not throw an exception.
    if (test[6])
       try {
         dict.remove("answer");
         System.out.println("   Test 6 succeeded");
       } catch (Exception e) {
         System.out.println("***Test 6 failed");
       }

    int collisions = 0;
    String s;

    // Test 7: insert 10000 different values into the Dictionary
    if (test[7])
       try {
	   for (i = 0; i < 10000; ++i) {
	       s = Integer.toString(i);
	       for (j = 0; j < 5; ++j) s += s;
	       collisions += dict.put(new Data(s,i,i));
	   }
         System.out.println("   Test 7 succeeded");
       } catch (Exception e) {
         System.out.println("***Test 7 failed");
       }


    // Test 8: check that all these values are in the Dictionary
    boolean pass = true;
    if (test[8]) {
       for (i = 0; i < 10000; ++i) {
			s = Integer.toString(i);		   
			for (j = 0; j < 5; ++j) s += s;
			if (dict.get(s) == null) {
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
	       s = Integer.toString(i);
	       for (j = 0; j < 5; ++j) s += s;
	       dict.remove(s);
	   }

	   for (i = 1000; i < 10000; ++i) {
	       s = Integer.toString(i);		   
	       for (j = 0; j < 5; ++j) s += s;
	       if (dict.get(s) == null) {
		   System.out.println("***Test 9 failed");
		   pass = false;
		   break;
	       }
	   }
	   if (pass) System.out.println("   Test 9 succeeded");
       }
       catch (Exception e) {
	   System.out.println("***Test 9 failed");
       }

    //Test 10: Number of collisions
    if (test[10])
       if (collisions >= 6000) {
	   System.out.println("***Test 10 failed");
	   System.out.println("Too many collisions: "+collisions);
       }
       else  System.out.println("   Test 10 succeeded");
  }
}
