
public class TestCircularArray {

	public static void main(String[] args) {
		OrderedCircularArray<String> list = new OrderedCircularArray<String>();
		boolean testPassed;
		String s;

		// Test 1: size, isEmpty, getSmallest
		// --------------------------------
		testPassed = true;
		try {
			if (!list.isEmpty())
				testPassed = false;
			if (list.size() != 0)
				testPassed = false;
			s = list.getSmallest();
			System.out.println("Test 1 failed");
		} catch (EmptyListException e) {
			if (testPassed)
				System.out.println("Test 1 passed");
			else
				System.out.println("Test 1 failed");
		} catch (Exception e) {
			System.out.println("Test 1 failed");
		}

		// Test 2: insert, getSmallest.
		// -----------------------------------
		testPassed = true;
		list.insert("data1", 1);
		try {
			list.changeValue("data1", 2);
			s = list.getSmallest();
			if (!s.equals("data1"))
				testPassed = false;
		} catch (Exception e) {
			testPassed = false;
		}

		try {
			list.changeValue("data0", 1);
			testPassed = false;
		} catch (InvalidDataItemException e) {
		} catch (Exception e) {
			testPassed = false;
		}

		if (testPassed)
			System.out.println("Test 2 passed");
		else
			System.out.println("Test 2 failed");
		
		// Test 3: insert, getValue.
		// -----------------------------------
		testPassed = true;
		list = new OrderedCircularArray<String>();
		try {
			for (int i = 0; i < 5; ++i) 
				list.insert("data"+i,i);
			if (list.getValue("data2") != 2) testPassed = false;
			int res = list.getValue("data5");
			testPassed = false;
		} catch(InvalidDataItemException e) {
		} catch (Exception e) {
			testPassed = false;
		}

		if (testPassed)
			System.out.println("Test 3 passed");
		else
			System.out.println("Test 3 failed");		

		// Test 4: insert, getFront, getRear, size
		// -----------------------------------
		testPassed = true;
		list = new OrderedCircularArray<String>();
		try {
			for (int i = 5; i > 0; --i) 
				list.insert("data"+i,i);
			if (list.getFront() != 1) testPassed = false;
			if (list.getRear() != 0) testPassed = false;
			
			for (int i = 5; i < 9; ++i)
				list.insert("data"+i,i);
			if (list.getFront() != 1) testPassed = false;
			if (list.getRear() != 9) testPassed = false; 
			if (list.size() != 9) testPassed = false;
		} catch (Exception e) {
			testPassed = false;
		}

		if (testPassed)
			System.out.println("Test 4 passed");
		else
			System.out.println("Test 4 failed");		
		
		// Test 5: insert, remove, getValue
		// -----------------------------------
		testPassed = true;
		int res;
		list = new OrderedCircularArray<String>();
		try {
			list.insert("data1",8);
			list.insert("data2",3);
			list.insert("data3",2);
			list.insert("data4",5);
			list.insert("data5",4);
			list.insert("data6",6);
			list.remove("data4");
			if (list.getValue("data6") != 6) testPassed = false;
			res = list.getValue("data4");
			} catch (InvalidDataItemException e) {
		} catch (Exception e) {
			testPassed = false;
		}
		try {
			list.remove("data3");
			if (list.getValue("data4") != 5) testPassed = false;
			res = list.getValue("data3");
		} catch (InvalidDataItemException e) {
		} catch (Exception e) {
			testPassed = false;
		}

		if (testPassed)
			System.out.println("Test 5 passed");
		else
			System.out.println("Test 5 failed");

		// Test 6: insert, remove, getSmallest, getFront, getRear
		// -----------------------------------
		testPassed = true;
		list = new OrderedCircularArray<String>();
		try {
			list.insert("data1",8);
			list.insert("data2",3);
			list.insert("data3",2);
			list.insert("data4",5);
			list.insert("data5",4);
			if (!list.getSmallest().equals("data3")) testPassed = false;
			list.remove("data5");		
			if (list.getFront() != 2) testPassed = false;
			if (list.getRear() != 4) testPassed = false;
			list.insert("data6",10);
			list.insert("data7",9);
			list.insert("data8",7);
			if (list.getFront() != 2) testPassed = false;
			if (list.getRear() != 7) testPassed = false;
			} catch (InvalidDataItemException e) {
		} catch (Exception e) {
			testPassed = false;
		}

		if (testPassed)
			System.out.println("Test 6 passed");
		else
			System.out.println("Test 6 failed");		
		
		// Test 7: insert, getSmallest, size
		// ---------------------------
		list = new OrderedCircularArray<String>();
		testPassed = true;
		try {
			for (int i = 1000; i > 700; --i)
				list.insert("data" + i, i);
			for (int i = 400; i <= 700; ++i)
				list.insert("data" + i, i);
			for (int i = 399; i > 0; --i)
				list.insert("data" + i, i);

			for (int i = 1; i <= 20; ++i) {
				s = list.getSmallest();
				if (!s.equals("data" + i))
					testPassed = false;
			}

			if (list.size() != 980)
				testPassed = false;
			
		} catch (Exception e) {
			testPassed = false;
		}

		if (testPassed)
			System.out.println("Test 7 passed");
		else
			System.out.println("Test 7 failed");

		// Test 8: insert, changeValue
		// ----------------------
		list = new OrderedCircularArray<String>();
		testPassed = true;
		try {
			for (int i = 1000; i > 700; --i)
				list.insert("data" + i, i);
			for (int i = 400; i <= 700; ++i)
				list.insert("data" + i, i);
			for (int i = 399; i > 0; --i)
				list.insert("data" + i, i);

			for (int i = 1; i <= 20; ++i) {
				list.changeValue("data" + i, 100 + i);
			}

			s = list.getSmallest();
			if (!s.equals("data21"))
				testPassed = false;
		} catch (Exception e) {
			testPassed = false;
		}

		try {
			list.changeValue("data0", 0);
			testPassed = false;
		} catch (InvalidDataItemException e) {
		}

		if (testPassed)
			System.out.println("Test 8 passed");
		else
			System.out.println("Test 8 failed");
	}
}
