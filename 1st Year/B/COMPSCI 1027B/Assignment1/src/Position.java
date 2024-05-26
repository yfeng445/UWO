/**
 * This class provides positions of all parts of the snake
 * @author Yulun Feng
 */
public class Position {
	int positionRow;
	int positionColumn;
	
	/**
	 * Constructor creates a position
	 * @param positionRow row of a position in the board
	 * @param positionColumn column of a position in the board
	 */
	public Position(int row, int col) {
		positionRow = row;
		positionColumn = col;
	}
	
	/**
	 * Accessor method to get the row of a position
	 * @return positionRow row of a position
	 */
	int getRow() {
		return positionRow;
	}
	
	/**
	 * Accessor method to get the column of a position
	 * @return positionColumn column of a position
	 */
	int getCol(){
		return positionColumn;
	}
	
	/**
	 * Accessor method to set a new row of a position
	 */
	void setRow(int newRow) {
		positionRow = newRow;
	}
	
	/**
	 * Accessor method to set a new column of a position
	 */
	void setCol(int newColumn) {
		positionColumn = newColumn;
	}
	
	/**
	 * Accessor method to identify wether two position are the same
	 * @return boolean of the comparison
	 */
	boolean equals(Position otherPosition) {
		return (positionRow == otherPosition.getRow()&&positionColumn == otherPosition.getCol());
		}
	}
