
public class TestUniqueArray {

	public static void main(String[] args) {
		UniqueArray<String> sua = new UniqueArray<String>();

		if (sua.getLength() == 5 && sua.getNumElements() == 0) {
			System.out.println("Test 1 Passed");
		} else {
			System.out.println("Test 1 Failed");
		}
		
		// ------------------------------------------------
		
		sua.addItem("Canada");
		sua.addItem("Brazil");
		
		if (sua.getLength() == 5 && sua.getNumElements() == 2) {
			System.out.println("Test 2 Passed");
		} else {
			System.out.println("Test 2 Failed");
		}
		
		// ------------------------------------------------
		
		if (sua.toString().equals("Canada, Brazil")) {
			System.out.println("Test 3 Passed");
		} else {
			System.out.println("Test 3 Failed");
		}
		
		// ------------------------------------------------
		
		sua.addItem("India");
		sua.addItem("Germany");
		sua.addItem("China");
		sua.addItem("Ukraine");
		sua.addItem("Brazil");
		sua.addItem("Canada");
		
		if (sua.getLength() == 10 && sua.getNumElements() == 6) {
			System.out.println("Test 4 Passed");
		} else {
			System.out.println("Test 4 Failed");
		}
		
		// ------------------------------------------------
		
		if (sua.toString().equals("Canada, Brazil, India, Germany, China, Ukraine")) {
			System.out.println("Test 5 Passed");
		} else {
			System.out.println("Test 5 Failed");
		}
		
		// ------------------------------------------------

		UniqueArray<Integer> iua = new UniqueArray<Integer>();

		if (iua.toString().equals("") && iua.getLength() == 5) {
			System.out.println("Test 6 Passed");
		} else {
			System.out.println("Test 6 Failed");
		}
		
		// ------------------------------------------------
		
		iua.addItem(17);
		iua.addItem(17);
		iua.addItem(17);
		iua.addItem(17);
		iua.addItem(17);
		iua.addItem(17);
		
		if (iua.getLength() == 5 && iua.getNumElements() == 1) {
			System.out.println("Test 7 Passed");
		} else {
			System.out.println("Test 7 Failed");
		}
		
		// ------------------------------------------------
		
		if (iua.toString().equals("17")) {
			System.out.println("Test 8 Passed");
		} else {
			System.out.println("Test 8 Failed");
		}
		
		// ------------------------------------------------
		
		iua.addItem(6);
		iua.addItem(12);
		iua.addItem(22);

		if (iua.getLength() == 5 && iua.getNumElements() == 4) {
			System.out.println("Test 9 Passed");
		} else {
			System.out.println("Test 9 Failed");
		}
		
		// ------------------------------------------------
		
		iua.addItem(15);
		iua.addItem(12);
		iua.addItem(20);
		iua.addItem(31);
		iua.addItem(17);
		iua.addItem(3);
		iua.addItem(29);
		iua.addItem(42);
		iua.addItem(18);

		if (iua.getLength() == 15 && iua.toString().equals("17, 6, 12, 22, 15, 20, 31, 3, 29, 42, 18")) {
			System.out.println("Test 10 Passed");
		} else {
			System.out.println("Test 10 Failed");
		}
		
		// ------------------------------------------------
	}

}
