
public class TestCompressedArray {

	public static void main(String[] args) {

		
		double[][] origArray1 = new double[][] {
			new double[] {15.2, 43.6, 28.2, 35.0, 95.5},
			new double[] {29.5, 88.3, 72.1, 44.4, 37.4},
			new double[] {10.8, 16.4, 74.6, 91.7, 36.2},
			new double[] {87.8, 12.2, 63.7, 19.6, 73.1},
			new double[] {13.9, 25.0, 77.4, 97.4, 30.5},
		};
		
		CompressedArray arr1 = new CompressedArray(origArray1);

		// ---------------------------------------------------------
		// Test 1 - getLength() on a CompressedArray object
		// ---------------------------------------------------------
		
		if (arr1.getLength() == 10) {
			System.out.println("Test 1 Passed");
		} else {
			System.out.println("Test 1 Failed");
		}

		// ---------------------------------------------------------
		// Test 2 - toString() method on a CompressedArray
		// ---------------------------------------------------------
		
		String soln = "\n   29.50\n   10.80   16.40\n   87.80   12.20   63.70\n   13.90   25.00   77.40   97.40\n";

		if (arr1.toString().equals(soln)) {
			System.out.println("Test 2 Passed");
		} else {
			System.out.println("Test 2 Failed");
		}

		double[][] origArray2 = new double[][] {
			new double[] {5.8, 10.4, -4.7, 13.7},
			new double[] {10.4, 7.1, -19.3, 4.8},
			new double[] {-4.7, -19.3, 5.1, 17.2},
			new double[] {13.7, 4.8, 17.2, -6.7}
		};

		double[][] origArray3 = new double[][] {
			new double[] {5.8, 12.3, -11.6, 21.3},
			new double[] {10.4, 7.1, 0.2, 35.0},
			new double[] {-4.7, -19.3, 5.1, 18.4},
			new double[] {13.7, 4.8, 17.2, -6.7}
		};
		
		double[][] origArray4 = new double[][] {
			new double[] {10.4, 7.1, 0.2, 35.0},
			new double[] {-4.7, -19.3, 5.1, 18.4},
			new double[] {13.7, 4.8, 17.2, -6.7},
			new double[] {5.8, 12.3, -11.6, 21.3},
		};

		CompressedArray arr2 = new CompressedArray(origArray2);
		CompressedArray arr3 = new CompressedArray(origArray3);
		CompressedArray arr4 = new CompressedArray(origArray4);

		// ---------------------------------------------------------
		// Test 3 - getLength() on more CompressedArray objects
		// ---------------------------------------------------------
		
		if (arr2.getLength() == 6 && arr3.getLength() == 6 && arr4.getLength() == 6) {
			System.out.println("Test 3 Passed");
		} else {
			System.out.println("Test 3 Failed");
		}
		
		// ---------------------------------------------------------
		// Test 4 - equals() between two CompressedArray objects
		// ---------------------------------------------------------
		
		if (!arr2.equals(arr4) && !arr3.equals(arr4)) {
			System.out.println("Test 4 Passed");
		} else {
			System.out.println("Test 4 Failed");
		}
		
		// ---------------------------------------------------------
		// Test 5 - equals() between two CompressedArray objects
		// ---------------------------------------------------------

		if (arr2.equals(arr3)) {
			System.out.println("Test 5 Passed");
		} else {
			System.out.println("Test 5 Failed");
		}

	}
}
