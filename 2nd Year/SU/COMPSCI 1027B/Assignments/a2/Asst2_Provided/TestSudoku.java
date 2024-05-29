
public class TestSudoku {

	public static void main(String[] args) {

		int[][] a1 = new int[][] {
			new int[] {2, 5, 3, 6, 7, 9, 1, 8, 4},
			new int[] {6, 4, 9, 5, 8, 1, 7, 2, 3},
			new int[] {7, 8, 1, 2, 3, 4, 9, 5, 6},
			new int[] {8, 6, 4, 7, 5, 3, 2, 9, 1},
			new int[] {5, 9, 2, 8, 1, 6, 3, 4, 7},
			new int[] {1, 3, 7, 9, 4, 2, 8, 6, 5},
			new int[] {3, 2, 5, 1, 6, 8, 4, 7, 9},
			new int[] {9, 1, 6, 4, 2, 7, 5, 3, 8},
			new int[] {4, 7, 8, 3, 9, 5, 6, 1, 2}
		};
		
		int[][] a2 = new int[][] {
			new int[] {9, 8, 7, 6, 5, 4, 3, 2, 1},
			new int[] {6, 5, 4, 3, 2, 1, 9, 8, 7},
			new int[] {3, 2, 1, 9, 8, 7, 6, 5, 4},
			new int[] {4, 3, 2, 1, 9, 8, 7, 6, 5},
			new int[] {7, 6, 5, 4, 3, 2, 1, 9, 8},
			new int[] {1, 9, 8, 7, 6, 5, 4, 3, 2},
			new int[] {8, 7, 6, 5, 4, 3, 2, 1, 9},
			new int[] {2, 1, 9, 8, 7, 6, 5, 4, 3},
			new int[] {5, 4, 3, 2, 1, 9, 8, 7, 6}
		};
		
		int[][] a3 = new int[][] {
			new int[] {7, 2, 9, 8, 6, 1, 4, 5, 3},
			new int[] {8, 4, 3, 9, 5, 7, 1, 2, 6},
			new int[] {5, 1, 6, 3, 2, 4, 7, 8, 9},
			new int[] {9, 6, 4, 1, 7, 2, 8, 3, 5},
			new int[] {1, 3, 5, 4, 8, 10, 2, 6, 7},
			new int[] {2, 7, 8, 5, 3, 6, 9, 4, 1},
			new int[] {6, 5, 1, 7, 4, 8, 3, 9, 2},
			new int[] {3, 0, 7, 2, 9, 5, 6, 1, 4},
			new int[] {4, 9, 2, 6, 1, 3, 5, 7, 8}
		};
		
		int[][] a4 = new int[][] {
			new int[] {6, 2, 5, 8, 9, 1, 3, 4, 7},
			new int[] {1, 8, 3, 2, 7, 4, 9, 5, 6},
			new int[] {3, 9, 4, 6, 5, 2, 7, 1, 8},
			new int[] {9, 1, 6, 3, 2, 8, 5, 7, 4},
			new int[] {7, 4, 9, 1, 6, 3, 2, 8, 5},
			new int[] {2, 3, 7, 8, 1, 5, 4, 9, 6},
			new int[] {8, 5, 2, 9, 3, 6, 7, 4, 1},
			new int[] {4, 7, 8, 5, 2, 1, 3, 6, 2},
			new int[] {5, 6, 1, 2, 7, 4, 8, 9, 3}
		};
		
		boolean res = false;
		
		Sudoku s1 = new Sudoku(a1);
		Sudoku s2 = new Sudoku(a2);
		Sudoku s3 = new Sudoku(a3);
		Sudoku s4 = new Sudoku(a4);

		// ------------------------------------------------
		
		res = s1.isValidRow(1) && s1.isValidRow(5) && s1.isValidRow(9);

		if (res) {
			System.out.println("Test 1 Passed");
		} else {
			System.out.println("Test 1 Failed");
		}

		// ------------------------------------------------

		res = s1.isValidCol(1) && s1.isValidCol(5) && s1.isValidCol(9);
		
		if (res) {
			System.out.println("Test 2 Passed");
		} else {
			System.out.println("Test 2 Failed");
		}

		// ------------------------------------------------
		
		res = s1.isValidBox(1, 1) && s1.isValidBox(4, 4) && s1.isValidBox(7, 7);
		
		if (res) {
			System.out.println("Test 3 Passed");
		} else {
			System.out.println("Test 3 Failed");
		}

		// ------------------------------------------------
		
		res = s1.isValidSolution() && s2.isValidSolution();
		
		if (res) {
			System.out.println("Test 4 Passed");
		} else {
			System.out.println("Test 4 Failed");
		}

		// ------------------------------------------------
		
		String str = "2  5  3  6  7  9  1  8  4\n6  4  9  5  8  1  7  2  3\n7  8  1  2  3  4  9  5  6\n8  6  4  7  5  3  2  9  1\n";
		str += "5  9  2  8  1  6  3  4  7\n1  3  7  9  4  2  8  6  5\n3  2  5  1  6  8  4  7  9\n9  1  6  4  2  7  5  3  8\n4  7  8  3  9  5  6  1  2";
		res = s1.toString().equals(str);
		
		if (res) {
			System.out.println("Test 5 Passed");
		} else {
			System.out.println("Test 5 Failed");
		}

		// ------------------------------------------------
		
		res = s3.isValidRow(4) && !s3.isValidRow(5) && !s3.isValidRow(8);
		
		if (res) {
			System.out.println("Test 6 Passed");
		} else {
			System.out.println("Test 6 Failed");
		}

		// ------------------------------------------------
		
		res = !s3.isValidCol(2) && s3.isValidCol(4) && !s3.isValidCol(6);
		
		if (res) {
			System.out.println("Test 7 Passed");
		} else {
			System.out.println("Test 7 Failed");
		}

		// ------------------------------------------------

		res = s3.isValidBox(1, 7) && !s3.isValidBox(7, 1) && !s3.isValidBox(4, 4); 

		if (res) {
			System.out.println("Test 8 Passed");
		} else {
			System.out.println("Test 8 Failed");
		}

		// ------------------------------------------------
		
		res = !s3.isValidSolution();
		
		if (res) {
			System.out.println("Test 9 Passed");
		} else {
			System.out.println("Test 9 Failed");
		}

		// ------------------------------------------------
		
		res = !s4.isValidSolution();
		
		if (res) {
			System.out.println("Test 10 Passed");
		} else {
			System.out.println("Test 10 Failed");
		}

		// ------------------------------------------------
		
	}

}
