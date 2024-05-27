
public class Test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		double[][] origArray1 = new double[][] {
			new double[] {15.2, 43.6, 28.2, 35.0, 95.5},
			new double[] {29.5, 88.3, 72.1, 44.4, 37.4},
			new double[] {10.8, 16.4, 74.6, 91.7, 36.2},
			new double[] {87.8, 12.2, 63.7, 19.6, 73.1},
			new double[] {13.9, 25.0, 77.4, 97.4, 30.5},
		};
		
		CompressedArray arr1 = new CompressedArray(origArray1);
		String soln = "\n   29.50\n   10.80   16.40\n   87.80   12.20   63.70\n   13.90   25.00   77.40   97.40\n";
		System.out.println(soln);
		System.out.print(arr1.getElement(9));
		System.out.println("------------------------------------------------------");
		System.out.println(arr1.toString());
		
	}

}
