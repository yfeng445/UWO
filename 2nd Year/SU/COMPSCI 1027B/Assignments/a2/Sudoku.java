/**
 * This class takes data from a 2D array to a array contains linked lists
 * @author 56111
 * @ID 251113989
 * 27 June 2021
 * @param <T>
 */
public class Sudoku<T> {
	private LinearNode<Integer>[] board;
	
	/**
	 * constructor of the class
	 * @param sudoku
	 */
	public Sudoku(int[][] sudoku) {
		board = new LinearNode[9];
		for(int i = 0; i<sudoku.length; i++) {
			LinearNode<Integer> front = new LinearNode<Integer>();
			front.setElement(sudoku[i][0]);
			board[i] = front;
			for(int j = 0; j<sudoku[0].length; j++) {
				front.setElement(sudoku[i][j]);
				front.setNext(new LinearNode<Integer>());
				front = front.getNext();
			}
		}
	}
	
	/**
	 * return true if the column is valid
	 * @param x
	 * @return
	 */
	public boolean isValidCol(int x) {
		UniqueArray array = new UniqueArray();
		if(x<1||x>9) {
			return false;
		}
		else {
			// get to the given column
			for(int j = 0; j<9;j++) {
				LinearNode<Integer> curr = board[j];
				for(int i = 0; i<x-1;i++){
					curr = curr.getNext();
				}
				if(curr.getElement()>9||curr.getElement()<1) { // false if the column has incorrect data
					return false;
				}
				array.addItem(curr.getElement());
			}
			if(!(array.getNumElements()==9)) { // false if the column have same element
				return false;
			}
			else {
				return true;
			}
		}
	}
	
	/**
	 * return true if the row is valid
	 * @param y
	 * @return
	 */
	public boolean isValidRow(int y) {
		UniqueArray array = new UniqueArray();
		if(y<1||y>9) {
			return false;
		}
		else {
			int i = y-1;
			LinearNode<Integer> curr = board[i];
			while(!(curr.getNext()==null)) {
				if(curr.getElement()>9||curr.getElement()<1) { // false if the data is incorrect
					return false;
				}
				array.addItem(curr.getElement());
				curr = curr.getNext();
			}
			array.addItem(curr.getElement());
			if(!(array.getNumElements()==9)) { // false if the row has same element
				return false;
			}
			else {
				return true;
			}
		}
	}
	
	/**
	 * return true if the box is valid
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isValidBox(int x, int y) {
		if(x<1||x>7||y<1||y>7) {
			return false;
		}
		else {
			UniqueArray array = new UniqueArray();
			for(int i = x-1; i<x+2;i++) { // loop from top left of the box to the bottom right of the box
				LinearNode<Integer> curr = board[i];
				for(int j = 0; j<y-1;j++) {
					curr = curr.getNext();
				}
				for(int j = 0; j<3;j++) { // false if the data is invalid
					if(curr.getElement()>9||curr.getElement()<1) {
						return false;
					}
					array.addItem(curr.getElement());
					curr = curr.getNext();
				}
			}
			if(!(array.getNumElements()==9)) { // false if the box has same element
				return false;
			}
			else {
				return true;
			}
		}
	}
	
	/**
	 * return true if the solution is valid
	 * @return
	 */
	public boolean isValidSolution() {
		boolean valid = true;
		for(int i = 1; i<10;i++) { // 9 rows
			valid = valid&&isValidRow(i);
		}
		for(int i = 1; i<10;i++) { // 9 columns
			valid = valid&&isValidCol(i);
		}
		for(int i = 1;i<8;i = i+3) { // 9 boxes
			for(int j = 1;j<8;j = j+3) {
				valid = valid&&isValidBox(i,j);
			}
		}
		return valid;
	}
	
	/**
	 * return the sudoku in specific way
	 */
	public String toString(){
		String returnString = "";
		for(int i = 0; i<8;i++) {
			LinearNode<Integer> curr = board[i];
			for(int j = 0; j<8;j++) {
				returnString = returnString+curr.getElement()+"  ";
				curr = curr.getNext();
			}
			returnString = returnString+curr.getElement()+"\n";
		}
		LinearNode<Integer> curr = board[8];
		for(int j = 0; j<8;j++) {
			returnString = returnString+curr.getElement()+"  ";
			curr = curr.getNext();
		}
		returnString = returnString+curr.getElement();
		return returnString;
	}

}
