public class Exceptions {

	public static void method1 (int[] P, int x) {

		System.out.print("A");

		try {
			System.out.print("B");
			
			P[x] = 5;
			//method2(P, x);
			
			System.out.print("C");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.print("D");
		} catch (Exception e) {
			System.out.print("E");
		
			
		} finally {
			System.out.print("F");
		}
		
		System.out.print("G");
		
	}
	
	public static void method2 (int[] P, int x) {
		try {
			P[x] = 5;
		} catch (Exception e) {
			System.out.print("X");
		}
	}

	public static void main(String[] args) {

		// i)
		method1(new int[7], 9);

		System.out.println();

		// ii)
		method1(null, 5);

		System.out.println();

		// iii)
		method1(new int[7], 3);

	}

}
