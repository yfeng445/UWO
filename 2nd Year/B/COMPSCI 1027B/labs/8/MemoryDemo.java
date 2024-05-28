public class MemoryDemo {
	
	public static void m1(int x) {
		
		x = 3;
		System.out.println("m1 sets x to 3");
		
	} //end m1
	
	public static void m2(int[] arr) {
		
		arr[0] = 3;
		System.out.println("m2 sets arr[0] to 3");
		
	} //end m2

	public static void main(String[] args) {
		
		int x = 5;
		System.out.println("Before: " + x);
		m1(x);
		System.out.println("After: " + x);
		
		int[] arr = new int[1];
		arr[0] = 5;
		System.out.println("\nBefore: " + arr[0]);
		m2(arr);
		System.out.println("After: " + arr[0] + "\n");
		
	} //end main
	
} //end MemoryDemo