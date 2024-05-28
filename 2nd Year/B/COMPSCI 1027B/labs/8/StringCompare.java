
public class StringCompare {
	
	public static void main(String[] args) {
		String s1, s2, s3, s4;

		s1 = new String("hello");
		s2 = new String("hello");
		// Test 1
		if (s1 == s2) System.out.println("Test 1: true");
		else System.out.println("Test 1: false");

		s3 = "hello";
		s4 = "hello";
		// Test 2
		if (s3 == s4) System.out.println("Test 2: true");
		else System.out.println("Test2: false");	

		s1 = new String("hello");
		s3 = "hello";
		// Test 3
		if (s1 == s3) System.out.println("Test 3: true");
		else System.out.println("Test 3: false");	

		// Test 4
		if (s1.equals(s2)) System.out.println ("Test 4: true");
		else System.out.println("Test 4: false");

		// Test 5
		if (s3.equals(s4)) System.out.println ("Test 5: true");
		else System.out.println("Test 5: false");

		// Test 6
		if (s1.equals(s3)) System.out.println ("Test 6: true");
		else System.out.println("Test 6: false");
	}
	
}
