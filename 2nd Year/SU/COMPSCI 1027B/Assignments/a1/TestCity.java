
public class TestCity {

	public static void main(String[] args) {
		
		City city;
		boolean res;

		city = new City(5, 5);
		res = city.addStructure(-1, 2, new Building("b", 2, 2));
	
		// Feel free to use this line for any of the tests to see the city layout (assuming you completed the toString() method in City.java)
		//System.out.println(city);
		
		if (!res) {
			System.out.println("Test 1 Passed");
		} else {
			System.out.println("Test 1 Failed");
		}

		// ------------------------------------------------
		
		res = city.addStructure(1, -1, new Building("b", 2, 2));
		
		if (!res) {
			System.out.println("Test 2 Passed");
		} else {
			System.out.println("Test 2 Failed");
		}

		// ------------------------------------------------

		res = city.addStructure(3, 0, new Building("b", 3, 3));
		
		if (!res) {
			System.out.println("Test 3 Passed");
		} else {
			System.out.println("Test 3 Failed");
		}
		
		// ------------------------------------------------

		res = city.addStructure(0, 3, new Building("b", 3, 3));
		
		if (!res) {
			System.out.println("Test 4 Passed");
		} else {
			System.out.println("Test 4 Failed");
		}
		
		// ------------------------------------------------

		res = city.addStructure(1, 0, new Building("x", 2, 3));

		if (res) {
			System.out.println("Test 5 Passed");
		} else {
			System.out.println("Test 5 Failed");
		}
		
		
		// ------------------------------------------------

		res = city.toString().equals(".  x  x  .  .  \n.  x  x  .  .  \n.  x  x  .  .  \n.  .  .  .  .  \n.  .  .  .  .  ");
		
		if (res) {
			System.out.println("Test 6 Passed");
		} else {
			System.out.println("Test 6 Failed");
		}

		// ------------------------------------------------

		res = city.addStructure(4, 2, new Building("o", 1, 2));

		if (res) {
			System.out.println("Test 7 Passed");
		} else {
			System.out.println("Test 7 Failed");
		}
		
		// ------------------------------------------------
		
		res = city.addStructure(0, 0, new Building("f", 2, 2));

		if (!res) {
			System.out.println("Test 8 Passed");
		} else {
			System.out.println("Test 8 Failed");
		}
		
		// ------------------------------------------------
		
		res = city.addStructure(0, 3, new Building("g", 5, 2));

		if (!res) {
			System.out.println("Test 9 Passed");
		} else {
			System.out.println("Test 9 Failed");
		}
		
		// ------------------------------------------------
		
		res = city.toString().equals(".  x  x  .  .  \n.  x  x  .  .  \n.  x  x  .  o  \n.  .  .  .  o  \n.  .  .  .  .  ");

		if (res) {
			System.out.println("Test 10 Passed");
		} else {
			System.out.println("Test 10 Failed");
		}

	}

}
