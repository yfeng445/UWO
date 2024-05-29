public class DebuggingExercise1 {

	public static void main(String[] args) {
		int[][] testArray = new int[5][6];

		for (int i = 0; i < 5; i++)
			for (int j = 1; j <= 6; j++)
				testArray[i][j] = (i + 1) * j;

		printArray(testArray);
	}

	private static void printArray(int[][] array) {
		for (int i = 0; i < array.length; ++i)
			for (int j = 0; j < array[0].length; ++j) {
				System.out.print(array[i][j]);
				if (j < array[0].length - 1)
					System.out.print(", ");
				else
					System.out.print("\n");
			}
	}
}
