
public class TestInheritance {

	public static void main(String[] args) {
		
		City city = new City(10, 6);
		boolean res;
		String str;
		
		// Feel free to use this line for any of the tests to see the city layout (assuming you completed the toString() method in City.java)
		//System.out.println(city);

		res = city.addStructure(1, 4, new Marina("p", 2, 3));

		if (!res) {
			System.out.println("Test 1 Passed");
		} else {
			System.out.println("Test 1 Failed");
		}
		
		// ------------------------------------------------
		
		res = city.addStructure(8, 1, new Marina("q", 3, 3));

		if (!res) {
			System.out.println("Test 2 Passed");
		} else {
			System.out.println("Test 2 Failed");
		}
		
		// ------------------------------------------------
		
		res = city.addStructure(3, 0, new Marina("m", 2, 3));

		if (res) {
			System.out.println("Test 3 Passed");
		} else {
			System.out.println("Test 3 Failed");
		}
		
		// ------------------------------------------------
		
		res = city.addStructure(4, 0, new Marina("n", 2, 1));

		if (!res) {
			System.out.println("Test 4 Passed");
		} else {
			System.out.println("Test 4 Failed");
		}
		
		// ------------------------------------------------

		city.addStructure(7, 1, new Marina("n", 3, 4));
		str = ".  .  .  m  m  .  .  .  .  .  \n.  .  .  m  m  .  .  n  n  n  \n.  .  .  m  m  .  .  n  n  n  \n";
		str += ".  .  .  .  .  .  .  n  n  n  \n.  .  .  .  .  .  .  n  n  n  \n.  .  .  .  .  .  .  .  .  .  ";
		res = city.toString().equals(str);

		if (res) {
			System.out.println("Test 5 Passed");
		} else {
			System.out.println("Test 5 Failed");
		}

		// ------------------------------------------------

		res = city.addStructure(0, 3, new Skyscraper("s", 3, 3, 7));

		if (!res) {
			System.out.println("Test 6 Passed");
		} else {
			System.out.println("Test 6 Failed");
		}
		
		// ------------------------------------------------
		
		res = city.addStructure(0, 3, new Skyscraper("s", 3, 3, 10));

		if (!res) {
			System.out.println("Test 7 Passed");
		} else {
			System.out.println("Test 7 Failed");
		}
		
		// ------------------------------------------------
		
		res = city.addStructure(0, 3, new Skyscraper("s", 2, 2, 5));

		if (res) {
			System.out.println("Test 8 Passed");
		} else {
			System.out.println("Test 8 Failed");
		}
		
		// ------------------------------------------------
		
		res = city.addStructure(1, 3, new Skyscraper("s", 2, 1, 5));

		if (!res) {
			System.out.println("Test 9 Passed");
		} else {
			System.out.println("Test 9 Failed");
		}
		
		// ------------------------------------------------
		
		city.addStructure(4, 3, new Skyscraper("s", 3, 2, 8));
		str = ".  .  .  m  m  .  .  .  .  .  \n.  .  .  m  m  .  .  n  n  n  \n.  .  .  m  m  .  .  n  n  n  \n";
		str += "5  5  .  .  8  8  8  n  n  n  \n5  5  .  .  8  8  8  n  n  n  \n.  .  .  .  .  .  .  .  .  .  ";
		res = city.toString().equals(str);

		if (res) {
			System.out.println("Test 10 Passed");
		} else {
			System.out.println("Test 10 Failed");
		}
		
		
	}

}
