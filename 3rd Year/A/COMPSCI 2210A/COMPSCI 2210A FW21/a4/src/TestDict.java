import java.io.*;
import java.util.ArrayList;

public class TestDict {

  public static void main(String[] args) {
	final int TEXT = 1;
	final int AUDIO = 2;
	final int IMAGE = 3;
	final int HTML = 4;
	BSTOrderedDictionary dictionary = new BSTOrderedDictionary();
	BSTNode root = dictionary.getRoot();
	ArrayList<MultimediaItem> list;
	NodeData data;

	String keys[] = {"homework", "course", "class", "computer", "four"};
	String content[] = {"Very enjoyable work that students need to complete outside the classroom.",
				"A series of talks or lessons. For example, CS210.",
				"A set of students taught together,",
				"An electronic machine frequently used by Computer Science students.",
				"One more than three",
				"homework.html",
				"Electronic device used to play video games",
				"computer.gif",
				"computer.wav"};
	int type[] = {TEXT, TEXT, TEXT, TEXT, TEXT, HTML, TEXT, IMAGE, AUDIO};
	
	boolean alltests = true;
	boolean passed = true;
	int test = -1;
	if (args.length > 0) {
		test = Integer.parseInt(args[0]);
		alltests = false;
	}

	// Insert one key and then find it
	if (alltests || test == 1 || test == 3 || test == 6 || test == 7 || test == 8 || test == 9)
	try {
	    dictionary.put(root,keys[0],content[0],type[0]);
	    root = dictionary.getRoot();
	    list = dictionary.get(root,keys[0]);
	    if (!(list.get(0).getContent()).equals(content[0])) passed = false;
	    BSTNode r = dictionary.getRoot();
	    if (!r.getData().getName().equals(keys[0])) passed = false;
	    
	    if (passed) System.out.println("Test 1 passed");
	    else System.out.println("Test 1 failed");
	}
	catch(Exception e) {
	    System.out.println("Test 1 failed");
	    System.out.println(e.getMessage());	    
	}

	// Try to find an inexistent key
	if (alltests || test == 2)
	try {
	    passed = true;
	    list = dictionary.get(root,keys[1]);
	    if (list != null) passed = false;
	    if (dictionary.getNumInternalNodes() != 1) passed = false;
	    if (passed) System.out.println("Test 2 passed");
	    else System.out.println("Test 2 failed");
	}
	catch(Exception e) {
	    System.out.println("Test 2 failed");
	    System.out.println(e.getMessage());	    
	}

	// Insert the same key again
	if (alltests || test == 3)
	try {
	    passed = true;
	    dictionary.put(root,keys[0],content[5],type[5]);
	    root = dictionary.getRoot();
	    list = dictionary.get(root,keys[0]);
	    if (list.size() != 2) passed = false;
	    if (dictionary.getNumInternalNodes() != 1) passed = false;
	    if (passed) System.out.println("Test 3 passed");
	    else System.out.println("Test 3 failed");
	}
	catch (Exception e) {
	    System.out.println("Test 3 failed");
	    System.out.println(e.getMessage());	    
	}

	// Insert and remove a key
	if (alltests || test == 4)
	try {
		passed = true;
	    dictionary.put(root,keys[1],content[1],type[1]);
	    root = dictionary.getRoot();
	    dictionary.remove(root,keys[1]);
	    root = dictionary.getRoot();
	    list = dictionary.get(root,keys[1]);
	    if (list != null) passed = false;
		
		dictionary.put(root,keys[1],content[1],type[1]);
		dictionary.put(root,keys[2],content[2],type[2]);
		dictionary.put(root,keys[3],content[3],type[3]);
		dictionary.put(root,keys[3],content[7],type[7]);
		dictionary.put(root,keys[3],content[6],type[6]);
		dictionary.remove(root,keys[3],type[3]);
		
		list = dictionary.get(root,keys[3]);
		if (list.size() != 1) passed = false;
		if (list.get(0).getType() != IMAGE) passed = false;
		
		if (passed) {
			System.out.println("Test 4 passed");
			System.out.println("------------");
		}
	    else System.out.println("Test 4 failed");
	}
	catch(DictionaryException e) {
	    System.out.println("Test 4 failed");
	}

	// Remove a key not in the dictionary
	if (alltests || test == 5)
	try {
		passed = true;
		dictionary = new BSTOrderedDictionary();
		root = dictionary.getRoot();
	    dictionary.put(root,keys[1],content[1],type[1]);
	    root = dictionary.getRoot();	    	    
	    dictionary.put(root,keys[0],content[0],type[0]);
	    	    
	    for (int i = 2; i < 5; ++i) 
			dictionary.put(root,keys[i],content[i],type[i]);	

		dictionary.remove(root,keys[2]);
		if (!root.getLeftChild().getData().getName().equals("computer")) passed = false;
		dictionary.remove(root,keys[1]);
		String k = root.getData().getName();
		if (!k.equals("computer") && !k.equals("four")) passed = false;
	    dictionary.remove(root,keys[2]);
	    System.out.println("Test 5 failed");
	}
	catch(DictionaryException e) {
	    if (passed) System.out.println("Test 5 passed");
		else System.out.println("Test 5 failed");
	}
	catch (Exception e) {
	    System.out.println("Test 5 failed");
	    System.out.println(e.getMessage());	    
	}

	// Insert 5 words in the dictionary and test the successor method
	if (alltests || test == 6 || test == 7 || test == 8 || test == 9)
	try {
	    passed = true;
		dictionary = new BSTOrderedDictionary();
		root = dictionary.getRoot();
	    dictionary.put(root,keys[1],content[1],type[1]);
	    root = dictionary.getRoot();	    	    
	    dictionary.put(root,keys[0],content[0],type[0]);
	    	    
	    for (int i = 2; i < 5; ++i) 
			dictionary.put(root,keys[i],content[i],type[i]);


	    root = dictionary.getRoot();
	    
	    data = dictionary.successor(root,keys[3]);
	    if ((data.getName()).compareTo(keys[1]) == 0)
	    if (dictionary.getNumInternalNodes() != 5) passed = false;
	    
	    if (passed) System.out.println("Test 6 passed");
	    else System.out.println("Test 6 failed");
	}
	catch (Exception e) {
	    System.out.println("Test 6 failed");
	    System.out.println(e.getMessage());
	}

	// Test the successor and smallest methods
	if (alltests || test == 7)
	try {
	    passed = true;
	    data = dictionary.successor(root,keys[2]);
	    if ((data.getName()).compareTo(keys[3]) != 0) passed = false;
	    
	    data = dictionary.smallest(root);
	    if ((data.getName()).compareTo(keys[2]) != 0) passed = false;
	    if (passed) System.out.println("Test 7 passed");
	    else System.out.println("Test 7 failed");
	}
	catch (Exception e) {
	    System.out.println("Test 7 failed");
	}

	//Test the predecessor and largst methods
	if (alltests || test == 8)
	try {
	    passed = true;
	    data = dictionary.predecessor(root,keys[0]);
	    if ((data.getName()).compareTo(keys[4]) != 0) passed = false;
	    data = dictionary.largest(root);
	    if ((data.getName()).compareTo(keys[0]) != 0) passed = false;
	    
	    if (passed) System.out.println("Test 8 passed");
	    else System.out.println("Test 8 failed");
	}
	catch (Exception e) {
	    System.out.println("Test 8 failed");
	}

	// Test the predecessor method
	if (alltests || test == 9)
	try {
	    data = dictionary.predecessor(root,keys[4]);
	    if ((data.getName()).compareTo(keys[1]) == 0)
			System.out.println("Test 9 passed");
	    else System.out.println("Test 9 failed");
	}
	catch (Exception e) {
	    System.out.println("Test 9 failed");
	}

        // Create a new empty dictionary

	dictionary = new BSTOrderedDictionary();

	try {

	    // Insert a large number of words in the dictionary
	    BufferedReader in = new BufferedReader(new FileReader("src/large.txt"));
	    String word = in.readLine();
	    String definition;
	    boolean test10 = true;

		if (alltests || test >= 10)
			try {
				while (word != null) {
					try {
						definition = in.readLine();
						dictionary.put(root,word,definition,TEXT); 
						word = in.readLine();
					}
					catch (Exception e) {
						test10 = false;
					}
				}

				if (test10) {
					// Now, try to find the word "practic"
					list = dictionary.get(root,"practic");
					if ((list.get(0).getContent()).indexOf("Artful; deceitful; skillful.") == -1)
						System.out.println("Test 10 failed");
					else System.out.println("Test 10 passed");
				}
				else System.out.println("Test 10 failed");
			}
			catch (Exception e) {
				System.out.println("Test 10 failed");
			}

		if (alltests || test == 11)
			// Try to find a word that is not in the dictionary and test smallest
			try {
			        passed = true;
				list = dictionary.get(root,"schnell");
				if (list != null) passed = false;
				data = dictionary.smallest(root);
				if (data.getName().compareTo("aaronical") != 0) passed = false;
				
				if (!passed) System.out.println("Test 11 failed");
				else System.out.println("Test 11 passed");
			}
			catch (Exception e) {
				System.out.println("Test 11 failed");
			}

		if (alltests || test == 12)
			// Test the remove method
			try {
				dictionary.remove(root,"practic");
				list = dictionary.get(root,"practic");
				if (list == null) System.out.println("Test 12 passed");
				else System.out.println("Test 12 failed");
			}
			catch (Exception e) {
				System.out.println("Test 12 failed");
			}
	    
	    if (alltests || test == 13)
			// Try to insert a word that is already in the dictionary and test largest
			try {
			        passed = true;
				data = dictionary.largest(root);
				if (data.getName().compareTo("zythum") != 0) passed = false;			
				dictionary.put(root,"pool","Bpool.gif",3);
				System.out.println("Test 13 passed");
			}
			catch (Exception e) {
				System.out.println("Test 13 failed");
			}

		if (alltests || test == 14)
			// Test the predecessor method
			try {
				data = dictionary.predecessor(root,"pony");
				if ((data.getName()).compareTo("ponvolant") == 0) 
					System.out.println("Test 14 passed");
				else System.out.println("Test 14 failed");
			}
			catch (Exception e) {
				System.out.println("Test 13 failed");
			}

		if (alltests || test == 15)
			// Test the successor method
			try {
				data = dictionary.successor(root,"reel");
				if ((data.getName()).compareTo("reem") == 0)
					System.out.println("Test 15 passed");
				else System.out.println("Test 15 failed");
			}
			catch (Exception e) {
				System.out.println("Test 15 failed");
			}

		if (alltests || test == 16)
			// Test removing a word and then using successor
			try {
				dictionary.remove(root,"langate");
				data = dictionary.successor(root,"land");
				if ((data.getName()).compareTo("laniary") == 0)
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