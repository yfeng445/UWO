
public class Board implements BoardADT{
	
	private int n;
	private char[][] theBoard;
	public Board(int board_size, int empty_positions, int max_levels) {
		this.n = board_size;
		char[][] theBoard = new char[board_size][board_size];
		for(int i = 0; i<theBoard.length;i++) {
			for(int j = 0; j<theBoard.length;j++) {
				theBoard[i][j]='e';
			}
		}
	}
	
	public Dictionary makeDictionary() {
		return new Dictionary(n);
	}
	
	public int repeatedLayout(Dictionary dict) {
		String s = "";
		for(int i = 0;i<theBoard.length;i++) {
			for(int j = 0; j<theBoard[0].length;j++) {
				s+=theBoard[i][j];
			}
		}
		int score = dict.getScore(s);
		if(score==-1) {
			return -1;
		}
		else {
			return score;
		}
	}

	public void storeLayout(Dictionary dict, int score) {
		String s = "";
		for(int i = 0;i<theBoard.length;i++) {
			for(int j = 0; j<theBoard[0].length;j++) {
				s+=theBoard[i][j];
			}
		}
		try {
			dict.put(new Layout(s, dict.getScore(s)));
		} catch (DictionaryException e) {
			e.printStackTrace();
		}
	}
	
	public void saveTile(int row, int col, char symbol) {
		theBoard[row][col] = symbol;
	}
	
	public boolean positionIsEmpty(int row, int col) {
		return(theBoard[row][col]=='e');
	}
	
	public boolean isComputerTile(int row, int col) {
		return(theBoard[row][col]=='c');
	}
	
	public boolean isHumanTile(int row, int col) {
		return(theBoard[row][col]=='h');
	}
	
	public boolean winner(char symbol) {
		boolean winner = false;
		for(int i = 0; i<theBoard.length; i++) { // rows
			boolean r = true;
			char c0 = theBoard[i][0];
			for(int j = 1;j<theBoard[0].length;j++) {
				r = r&&(c0==theBoard[i][j]); // the row is valid only if all the characters are the same;
			}
			winner = winner||r; // winner is valid if one of the row is valid;
		}
		for(int j = 1;j<theBoard[0].length;j++) { // columns
			boolean c = true;
			char c0 = theBoard[j][0];
			for(int i = 0; i<theBoard.length; i++) {
				c = c&&(c0==theBoard[i][j]); // the column is valid only if all the characters are the same;
			}
			winner = winner||c; // winner is valid if one of the column is valid;
		}
		for(int i = 0; i<theBoard.length;i++) { // upper left to lower right diagonal;
			
		}
		
		return winner;
	}
}
