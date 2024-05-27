import java.util.Random;
import javax.swing.*;

/** This class represents the eagle objects that chase the snake around the board */

public class Chaser {
	public final int UP = 0;
	public final int RIGHT = 1;
	public final int DOWN = 2;
	public final int LEFT = 3;
	
	private ImageIcon chaserTile;
	
	private int row;
	private int col;
	private int direction;
	private int steps;
	private int count;
	
	/* =========================================================================== */	
	public Chaser(BoardGameLinked board, int numChasers, Chaser[] chasers, PlayChasingGame display) {
	/* =========================================================================== */		
		chaserTile = display.getEagleIcon();	
		Position pos = posChaser(board,numChasers,chasers,display);
		row = pos.getRow();
		col = pos.getCol();
		Random randGen = new Random();
		direction = randGen.nextInt(4);
		steps = randGen.nextInt(5)+5;   // Chaser will change direction after this many steps
		count = 0; // Number of steps that this chaser has taken since last direction change
	}
	
	/**
	   @param: number of chasers, array or chasers, position of current chaser
	   @return: true if this chases overlap other chases; false otherwise
	 */
	 
	/* =========================================================================== */	
	private boolean overlapChaser(int numChasers, Chaser[] chasers, int row, int col) {
	/* =========================================================================== */		
		for (int i = 0; i < numChasers; ++i)
			if (chasers[i].getRow() == row && chasers[i].getCol() == col) return true;
		return false;
	}
	
	/* Determine a direction and position where the current chaser can be placed so it does not
	   overlap any objects placed on the board                                                  */
	/* =========================================================================== */
	private Position posChaser(BoardGameLinked board, int numChasers, Chaser[] chasers, PlayChasingGame display) {
	/* ============================================================================ */
		SnakeLinked theSnake = board.getSnakeLinked();
		int rowHead = theSnake.getPosition(0).getRow();
		int colHead = theSnake.getPosition(0).getCol();
		int row, col;
		int iter = 0;
		Random randGen = new Random();
		while (true) {
			row = randGen.nextInt(board.getWidth());
			col = randGen.nextInt(board.getLength());
			if ((row != rowHead) && (col != colHead) && board.getObject(row,col).equals("empty")
				&& !theSnake.snakePosition(new Position(row,col)) && !overlapChaser(numChasers,chasers,row,col)) {
				display.redrawTile(row,col,chaserTile);
				return new Position(row,col);
			}
			if (++iter > 10000) {
				System.out.println("Error. Something is wrong with methods getObject or setObject");
				System.exit(0);
			}
		}
	}
	
	public int getRow() { 
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	/* GEt the direction in which the chaser is moving */
	public int getDir() {
		return direction;
	}
	
	public void setDir(int newDir) {
		direction = newDir;
	}
	
	public void setRow(int newRow) {
		row = newRow;
	}
	
	public void setCol(int newCol) {
		col = newCol;
	}
	
	/* get the number of steps that the chaser has taken */
	public int getCount() {
		return count;
	}
	
	public void setCount(int newCount) {
		count = newCount;
	}
	
	public void incCount() {
		++count;
	}	
	
	/* get the number of steps needed before the chaser changes direction */
	public int getSteps() {
		return steps;
	}
}