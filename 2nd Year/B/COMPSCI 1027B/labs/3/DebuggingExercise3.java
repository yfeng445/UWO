public class DebuggingExercise3 {
	private static int var1;
	private static MyObject obj1;
	
	private static void method1 (int var1) {
		var1 = 10;
	}
	
	private static void method2 (MyObject obj2) {
		MyObject obj1 = new MyObject("joe");
		
		if (obj1 == obj2) 
			var1 = 20;
		else var1 = -20;
		
		obj2 = new MyObject("john");
	}
	
	public static void main(String[] args) {
		var1 = 2;
		obj1 = new MyObject("joe");

		for (int i = 1; i < 2; ++i) {
			int var1 = i;
		}
		
		//i = 8;
		
		method2(obj1);
		
		// What are the values of var1 and obj1.name?
		
		method1(var1);
			
		// What are the values of var1 and obj1.name? 

	}
}
