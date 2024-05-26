/**
 * This class read the file and create the game board
 * @author Yulun Feng
 */
import java.io.*;
public class BoardGame{	
	int board_length;
	int board_width;
	Snake theSnake;
	String[][] matrix;
	int row;
	int col;
	
	/**
	 * Constructor creates a game board with the given board file name 
	 * @param board_length length of the board
	 * @param board_width width of the board
	 */
	public BoardGame(String boardFile) {
		MyFileReader in = new MyFileReader(boardFile);
		int tile_length = in.readInt();
		int tile_width = in.readInt();
		board_length = in.readInt();
		board_width = in.readInt();
		
		row = in.readInt();
		col = in.readInt();
		matrix = new String[board_width][board_length];
		theSnake = new Snake(row,col);
		for(int i = 0;i<board_width;i++)
			for (int j = 0;j<board_length;j++)
				matrix[i][j] = "empty";	
		while(in.endOfFile() == false) {
			row = in.readInt();
			col = in.readInt();
			String type = in.readString();
			matrix[row][col] = type;
		}
	}
	
	/**
	 * Accessor method to get the object in the board
	 * @return position of the object
	 */
	public String getObject(int row,int col) {
		return matrix[row][col];
	}
	
	/**
	 * Accessor method to place a new object in the board
	 */
	public void setObject(int row,int col,String newObject) {
		matrix[row][col] = newObject;
	}
	
	/**
	 * Accessor method to return the snake information
	 */
	public Snake getSnake() {
		return theSnake;	
	}
	
	/**
	 * Accessor method to set a new snake
	 */
	public void setSnake(Snake newSnake) {
		theSnake = newSnake;
	}
	
	/**
	 * Accessor method to return board length
	 */
	public int getLength() {
		return board_length;
	}
	
	/**
	 * Accessor method to return board width
	 */
	public int getWidth() {
		return board_width;
	}
	
	/**
	 * Accessor method to return the object in the board
	 */
	public String getType(int row,int col) {
		return matrix[row][col];
	}
}



