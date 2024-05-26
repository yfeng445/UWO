/**
 * This class provides methods about the snake.
 * @author Yulun Feng
 */
public class Snake {

	private int snakeLength = 1;
	private Position[] snakeBody;
	
	/**
	 * Constructor creates a snake position with the given row and column
	 * @param snakeBody an array that contains the position of the snake
	 * @param snakeLength the length of the snake
	 */
	public Snake(int row, int col) {
		snakeBody = new Position[5];
		snakeBody[0] = new Position(row, col);	
	}
	
	/**
	 * Accessor method to get length of the snake
	 * @return length of the snake
	 */
	public int getLength() {
		return snakeLength;
	}
	
	/**
	 * Accessor method to get the position of the snake
	 * @return position of the snake
	 */
	public Position getPosition(int index) {
		if(index< 0||index>= snakeLength) {
			return null;
		}	
		else return snakeBody[index];
	}
	
	/**
	 * Accessor method to decrease the snake body by 1
	 */
	public void shrink() {
		snakeLength -=1;
	}
	
	/**
	 * Accessor method to identify if one position is in the array snakeBody or not
	 * @return wether the position is in the array or not
	 */
	public boolean snakePosition(Position pos) {
		boolean find = false;
		for(int i = 0;i<snakeLength;i++) {
			if(snakeBody[i].equals(pos)) {
				find = true;
			}
		}
		return find;
	}
	
	/**
	 * Accessor method to get a new head position of the snake
	 * @return new head position of the snake
	 */
	public Position newHeadPosition(String direction) {
		if(direction.equals("right")) {
			Position newPosition = new Position(snakeBody[0].getRow(), snakeBody[0].getCol()+1);
			return newPosition;
		}
		else if(direction.equals("left")) {
			Position newPosition = new Position(snakeBody[0].getRow(), snakeBody[0].getCol()-1);
			return newPosition;
		}
		
		else if(direction.equals("up")) {
			Position newPosition = new Position(snakeBody[0].getRow()-1, snakeBody[0].getCol());
			return newPosition;
		}
		else if(direction.equals("down")){
			Position newPosition = new Position(snakeBody[0].getRow()+1, snakeBody[0].getCol());
			return newPosition;
		}
		return null;
	}
	
	/**
	 * Accessor method to move the snake body
	 */
	public void moveSnake(String direction) {
		for(int i = snakeLength - 1; i > 0; i--) {
			snakeBody[i] = snakeBody[i-1];
		}
		snakeBody[0] = this.newHeadPosition(direction);	
	}
	
	/**
	 * Accessor method to increase the length of the snake
	 */
	public void grow(String direction) {
		for(int i = snakeLength; i > 0; i--) {
			snakeBody[i] = snakeBody[i-1];
		}
		snakeLength++;
		snakeBody[0] = this.newHeadPosition(direction);
		if(snakeLength == snakeBody.length) {
			this.increaseArraysize();
		}
	}
	
	/**
	 * Accessor method to increase array size
	 */
	public void increaseArraysize() {
		Position[] tempArray = new Position[snakeLength*2];
		for(int i = 0; i < snakeLength; i++) {
			tempArray[i] = snakeBody[i];
		}
		snakeBody = tempArray;
	}
}
