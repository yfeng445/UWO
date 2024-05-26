import java.io.*;

public class TestDict {

    public static void main(String[] args) {
	final String TEXT = "definition";
	OrderedDictionary dictionary = new OrderedDictionary();
	DataItem rec;
	Key key;

	String words[] = {"homework", "course", "class", "computer", "four"};
	String definitions[] = {"Very enjoyable work that students need to complete outside the classroom.",
				"A series of talks or lessons. For example, CS210.",
				"A set of students taught together,",
				"An electronic machine frequently used by Computer Science students.",
				"One more than three"};
	Key[] keys = new Key[5];
	DataItem[] records = new DataItem[5];
	
	for (int i = 0; i < 5; ++i) {
		keys[i] = new Key(words[i],TEXT);
		records[i] = new DataItem(keys[i],definitions[i]);
	}

	// Insert one word and then find it
	try {
	    dictionary.put(records[0]);
	    rec = dictionary.get(keys[0]);
	    if ((rec.getContent()).compareTo(definitions[0]) == 0)
			System.out.println("Test 1 passed");
	    else System.out.println("Test 1 failed");
	}
	catch(Exception e) {
	    System.out.println("Test 1 failed");
	}

	// Try to find an inexistent word
	try {
	    rec = dictionary.get(keys[1]);
	    if (rec == null) System.out.println("Test 2 passed");
	    else System.out.println("Test 2 failed");
	}
	catch(Exception e) {
	    System.out.println("Test 2 failed");
	}

	// Try to insert the same word again
	try {
	    dictionary.put(records[0]);
	    System.out.println("Test 3 failed");
	}
	catch(DictionaryException e) {
	    System.out.println("Test 3 passed");
	}
	catch (Exception e) {
	    System.out.println("Test 3 failed");
	}

	// Insert and remove a word
	try {
	    dictionary.put(records[1]);
	    dictionary.remove(keys[1]);
	    rec = dictionary.get(keys[1]);
	    if (rec == null) System.out.println("Test 4 passed");
	    else System.out.println("Test 4 failed");
	}
	catch(DictionaryException e) {
	    System.out.println("Test 4 failed");
	}

	// Remove a word not in the dictionary
	try {
	    dictionary.remove(keys[3]);
	    System.out.println("Test 5 failed");
	}
	catch(DictionaryException e) {
	    System.out.println("Test 5 passed");
	}
	catch (Exception e) {
	    System.out.println("Test 5 failed");
	}

	// Insert 5 words in the dictionary and test the successor method
	try {
	    dictionary.remove(keys[0]);
	    dictionary.put(records[1]);
	    dictionary.put(records[0]);
	    for (int i = 2; i < 5; ++i)
			dictionary.put(records[i]);

	    rec = dictionary.successor(keys[3]);
	    if ((rec.getKey().getName()).compareTo(words[1]) == 0)
			System.out.println("Test 6 passed");
	    else System.out.println("Test 6 failed");
	}
	catch (Exception e) {
	    System.out.println("Test 6 failed");
	}

	// Test the successor method
	try {
	    rec = dictionary.successor(keys[2]);
	    if ((rec.getKey().getName()).compareTo(words[3]) == 0)
			System.out.println("Test 7 passed");
	    else System.out.println("Test 7 failed");
	}
	catch (Exception e) {
	    System.out.println("Test 7 failed");
	}

	//Test the predecessor method
	try {
	    rec = dictionary.predecessor(keys[0]);
	    if ((rec.getKey().getName()).compareTo(words[4]) == 0)
			System.out.println("Test 8 passed");
	    else System.out.println("Test 8 failed");
	}
	catch (Exception e) {
	    System.out.println("Test 8 failed");
	}

	// Test the predecessor method
	try {
	    rec = dictionary.predecessor(keys[4]);
	    if ((rec.getKey().getName()).compareTo(words[1]) == 0)
			System.out.println("Test 9 passed");
	    else System.out.println("Test 9 failed");
	}
	catch (Exception e) {
	    System.out.println("Test 9 failed");
	}

        // Create a new empty dictionary

	dictionary = new OrderedDictionary();

	try {

	    // Insert a large number of words in the dictionary
	    BufferedReader in = new BufferedReader(new FileReader("large.txt"));
	    String word = in.readLine();
	    String definition;
	    boolean test10 = true;

	    try {
		while (word != null) {
		    try {
				definition = in.readLine();
				dictionary.put(new DataItem(new Key(word,TEXT),definition));
				word = in.readLine();
		    }
		    catch (Exception e) {
			test10 = false;
		    }
		}

		if (test10) {
		    // Now, try to find the word "practic"
		    rec = dictionary.get(new Key("practic",TEXT));
		    if ((rec.getContent()).indexOf("Artful; deceitful; skillful.") == -1)
				System.out.println("Test 10 failed");
		    else System.out.println("Test 10 passed");
		}
		else System.out.println("Test 10 failed");
	    }
	    catch (Exception e) {
			System.out.println("Test 10 failed");
	    }

	    // Try to find a word that is not in the dictionary
	    try {
			rec = dictionary.get(new Key("schnell",TEXT));
			if (rec != null) System.out.println("Test 11 failed");
			else System.out.println("Test 11 passed");
	    }
	    catch (Exception e) {
			System.out.println("Test 11 failed");
	    }

	    // Test the remove method
	    try {
			dictionary.remove(new Key("practic",TEXT));
			rec = dictionary.get(new Key("practic",TEXT));
			if (rec == null) System.out.println("Test 12 passed");
			else System.out.println("Test 12 failed");
	    }
	    catch (Exception e) {
			System.out.println("Test 12 failed");
	    }

	    // Try to insert a word that is already in the dictionary
	    try {
			dictionary.put(new DataItem(new Key("pool",TEXT),"Body of water"));
			System.out.println("Test 13 failed");
	    }
	    catch(DictionaryException e) {
			System.out.println("Test 13 passed");
	    }
	    catch (Exception e) {
		System.out.println("Test 13 failed");
	    }

	    // Test the predecessor method
	    try {
			rec = dictionary.predecessor(new Key("pony",TEXT));
			if ((rec.getKey().getName()).compareTo("ponvolant") == 0) 
				System.out.println("Test 14 passed");
			else System.out.println("Test 14 failed");
	    }
	    catch (Exception e) {
			System.out.println("Test 13 failed");
	    }

	    // Test the successor method
	    try {
			rec = dictionary.successor(new Key("reel",TEXT));
			if ((rec.getKey().getName()).compareTo("reem") == 0)
				System.out.println("Test 15 passed");
			else System.out.println("Test 15 failed");
	    }
	    catch (Exception e) {
			System.out.println("Test 15 failed");
	    }

	    // Test removing a word and the using successor
	    try {
			dictionary.remove(new Key("langate",TEXT));
			rec = dictionary.successor(new Key("land",TEXT));
			if ((rec.getKey().getName()).compareTo("laniary") == 0)
				System.out.println("Test 16 passed");
			else System.out.println("Test 16 failed");
	    }
	    catch (Exception e) {
			System.out.println("Test 16 failed");
	    }
	}
	catch (IOException e) {
	    System.out.println("Cannot open file: large.txt");
	}
    }
}
