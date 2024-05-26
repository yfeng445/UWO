
public class TestAsmt1 {

	public static void main(String[] args) {
		boolean testPassed = true;
		BoardGame board = null;
		Snake theSnake = null;
		String[][] matrix = {{"empty", "empty", "apple", "empty"},
								{"empty", "rock", "empty", "apple"},
								{"scissors", "empty", "empty", "empty"}};

		// Test 1. Test methods from class Position
		try {
			Position pos = new Position(5,7);
			if ((pos.getRow() != 5) || (pos.getCol() != 7)) testPassed = false;
			pos.setRow(1);
			pos.setCol(2);
			if ((pos.getRow() != 1) || (pos.getCol() != 2)) testPassed = false;
			Position pos2 = new Position(1,2);
			Position pos3 = new Position(1,3);
			
			if (!pos.equals(pos2)) testPassed = false;
			if (pos.equals(pos3)) testPassed = false;
			if (testPassed)
				System.out.println("Test 1 passed");
			else
				System.out.println("Test 1 failed");
		} catch (Exception e) {
			System.out.println("Test 1 failed");
		}

		// Test 2. Constructor for class BoardGame
		try {
			testPassed = true;
			board = new BoardGame("testboard.txt");
			// Check that the contents of the file testboard.txt was read correctly
			for (int i = 0; i < 3; ++i)
				for (int j = 0; j < 4; ++j)
					if (!board.getObject(i,j).equals(matrix[i][j])) testPassed = false;

			if (testPassed)
				System.out.println("Test 2 passed");
			else
				System.out.println("Test 2 failed");
		} catch (Exception e) {
			System.out.println("Test 2 failed");
		}

		// Test 3. Test methods setObject, get Length, and getWidth of class GameBoard
		try {
			testPassed = true;
			board.setObject(2,3,"scissors");
			String s = board.getObject(2,3);
			if (!s.equals("scissors")) testPassed = false;
			if (board.getLength() != 4 || board.getWidth() != 3) testPassed = false;
			if (testPassed)
				System.out.println("Test 3 passed");
			else
				System.out.println("Test 3 failed");
		} catch (Exception e) {
			System.out.println("Test 3 failed");
		}

		// Test 4. Test method getSnake of class GameBoard and methods getLength and snakePosition of class Snake
		try {
			testPassed = true;
			Position pos;
			theSnake = board.getSnake();
			if (theSnake.getLength() != 1) testPassed = false;
			pos = theSnake.getPosition(0);
			if (pos.getRow() != 2 || pos.getCol() != 2) testPassed = false;
			if (testPassed)
				System.out.println("Test 4 passed");
			else
				System.out.println("Test 4 failed");
		} catch (Exception e) {
			System.out.println("Test 4 failed");
		}
		
		// Test 5. Test method Snake, getPosition, shrink
//		try {
			testPassed = true;
			theSnake = new Snake(4,7);
			if (!theSnake.getPosition(0).equals(new Position(4,7))) testPassed = false;
			if (theSnake.getPosition(-1) != null || theSnake.getPosition(1) != null)
				testPassed = false;
			theSnake.shrink();
			if (theSnake.getLength() != 0) testPassed = false;
			if (testPassed)
				System.out.println("Test 5 passed");
			else
				System.out.println("Test 5 failed");
//		} catch (Exception e) {
//			System.out.println(e);
//			System.out.println("Test 5 failed");
//		}
		
		// Test 6. Test newHeadPosition, snakePosition
//		try {
			testPassed = true;
			theSnake = new Snake(1,1);
			Position up = new Position(0,1);
			Position down = new Position(2,1);
			Position right = new Position(1,2);
			Position left = new Position(1,0);
			if (!theSnake.newHeadPosition("up").equals(up)) {
				testPassed = false;
				System.out.println("a");
			}
			if (!theSnake.newHeadPosition("down").equals(down)) testPassed = false;
			if (!theSnake.newHeadPosition("left").equals(left)) testPassed = false;
			if (!theSnake.newHeadPosition("right").equals(right)) testPassed = false;
			if (!theSnake.snakePosition(new Position(1,1))) testPassed = false;
			if (theSnake.snakePosition(new Position(0,1))) testPassed = false;
			if (testPassed)
				System.out.println("Test 6 passed");
			else
				System.out.println("Test 6 failed");
//		} catch (Exception e) {
//			System.out.println(e);
//			System.out.println("Test 6 failed");
//		}
		
		// Test 7. Test method moveSnake
		try {
			testPassed = true;
			theSnake = new Snake(2,2);
			theSnake.moveSnake("right");
			if (!theSnake.snakePosition(new Position(2,3))) {
				testPassed = false;
				System.out.println("a");
			}
			if (theSnake.snakePosition(new Position(2,2))) testPassed = false;
			theSnake.moveSnake("up");
			if (!theSnake.snakePosition(new Position(1,3))) testPassed = false;
			if (theSnake.getLength() != 1) testPassed = false;
			theSnake.moveSnake("left");
			if (!theSnake.getPosition(0).equals(new Position(1,2))) testPassed = false;
			theSnake.moveSnake("down");
			if (!theSnake.getPosition(0).equals(new Position(2,2))) testPassed = false;			
			if (testPassed)
				System.out.println("Test 7 passed");
			else
				System.out.println("Test 7 failed");
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Test 7 failed");
		}
		
		// Test grow 
		try {
			testPassed = true;
			theSnake = new Snake(1,1);
			theSnake.grow("right");
			theSnake.grow("down");
			theSnake.grow("down");
			theSnake.grow("left");
			if (theSnake.getLength() != 5) testPassed = false;
			if (!theSnake.snakePosition(new Position(1,2))) {
				testPassed = false;
				System.out.println("a");
			}
			if (!theSnake.snakePosition(new Position(2,2))) testPassed = false;
			if (!theSnake.snakePosition(new Position(3,2))) testPassed = false;
			if (!theSnake.snakePosition(new Position(3,1))) testPassed = false;			
			if (testPassed)
				System.out.println("Test 8 passed");
			else
				System.out.println("Test 8 failed");
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Test 8 failed");
		}
		
		// Test increaseArraySize, grow 
		try {
			testPassed = true;
			theSnake.grow("up");	
			if (theSnake.getLength() != 6) testPassed = false;
			theSnake.grow("left");
			if (!theSnake.getPosition(0).equals(new Position(2,0))) testPassed = false;
			if (testPassed)
				System.out.println("Test 9 passed");
			else
				System.out.println("Test 9 failed");
		} catch (Exception e) {
			System.out.println("Test 9 failed");
		}
		
		// Test moveSnake 
		try {
			testPassed = true;
			theSnake.moveSnake("down");
			theSnake.moveSnake("down");
			theSnake.moveSnake("right");
			if (!theSnake.getPosition(0).equals(new Position(4,1))) testPassed = false;
			if (!theSnake.getPosition(1).equals(new Position(4,0))) testPassed = false;
			if (!theSnake.getPosition(2).equals(new Position(3,0))) testPassed = false;
			if (!theSnake.getPosition(3).equals(new Position(2,0))) testPassed = false;
			if (!theSnake.getPosition(4).equals(new Position(2,1))) testPassed = false;
			if (!theSnake.getPosition(5).equals(new Position(3,1))) testPassed = false;
			if (!theSnake.getPosition(6).equals(new Position(3,2))) testPassed = false;
			if (testPassed)
				System.out.println("Test 10 passed");
			else
				System.out.println("Test 10 failed");
		} catch (Exception e) {
			System.out.println("Test 10 failed");
		}
	}

}
