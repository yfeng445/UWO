
public class Evaluate {
	
	int boardRows = 0;
	int boardColumns = 0;
	int tilesNeed = 0;
	int maxLevels = 0;
	char[][] gameBoard;

	/**
	 * constructor of the class
	 * @param boardRows rows on the board
	 * @param boardColumns columns on the board
	 * @param tilesNeed tiles needed
	 * @param maxLevels level of the function runs
	 */
	public Evaluate(int boardRows, int boardColumns, int tilesNeed, int maxLevels) {
		gameBoard = new char[boardRows][boardColumns];
		this.boardRows = boardRows;
		this.boardColumns = boardColumns;
		this.tilesNeed = tilesNeed;
		this.maxLevels = maxLevels;
	}
	
	/**
	 * create a new dictionary
	 * @return dic a new dictionary
	 */
	public Dictionary createDictionary() {
		Dictionary dic = new Dictionary(9973);		
		return dic;
	}
	
	/**
	 * show the configuration of the dictionary
	 * @param dict dictionary
	 * @return dict.Hashtable[i] data stored in the dictionary
	 */
	public Data repeatedConfig(Dictionary dict) {
		String board = "";
		StringBuffer sb = new StringBuffer();		
		for(int i = 0;i<boardRows;i++) {
			for(int j = 0;j<boardColumns;j++) {
				sb.append(board).insert(board.length(),gameBoard[boardRows][boardColumns]);
				board = sb.toString();
			}
		}
		System.out.print(board);
		for(int i = 0;i<dict.Hashtable.length;i++) {
			if (board.equals(dict.Hashtable[i].key)){
				return dict.Hashtable[i];
			}
		}
		return null;
	}
	
	/**
	 * insert a new configuration
	 * @param dict
	 * @param score
	 * @param level
	 */
	public void insertConfig(Dictionary dict, int score,int level) {
		String board = "";
		StringBuffer sb = new StringBuffer();		
		for(int i = 0;i<boardRows;i++) {
			for(int j = 0;j<boardColumns;j++) {
				sb.append(board).insert(board.length(),gameBoard[boardRows][boardColumns]);
				board = sb.toString();
			}
		}
		System.out.print(board);
		Data newData = null;
		newData.key = board;
		newData.level = level;
		newData.score = score;
		dict.Hashtable[dict.Hashfunction(board)] = newData;
	}
	
	/**
	 * store the tile
	 * @param row row of the tile
	 * @param col column of the tile
	 * @param symbol tile color
	 */
	public void storePlay(int row, int col, char symbol) {
		gameBoard[row][col] = symbol;
	}
	
	/**
	 * determine whether the square is empty or not
	 * @param row row of the square
	 * @param col column of the square
	 * @return true if the square is empty
	 */
	public boolean squareIsEmpty(int row, int col) {
		if(gameBoard[row][col] == 'g') {
			return true;
		}
		return false;
	}
	
	/**
	 * determine whether the tile is belong to computer
	 * @param row row of the tile
	 * @param col column of the tile
	 * @return true if the tile belongs to computer
	 */
	public boolean tileOfComputer(int row, int col) {
		if(gameBoard[row][col] == 'o') {
			return true;
		}
		return false;
	}
	
	/**
	 * determine whether the tile is belong to human
	 * @param row row of the tile
	 * @param col column of the tile
	 * @return true if the tile belongs to human
	 */
	public boolean tileOfHuman(int row, int col) {
		if(gameBoard[row][col] == 'b') {
			return true;
		}
		return false;
	}
	
	/**
	 * determine whether a given symbol wins
	 * @param symbol the given symbol
	 * @return true if the symbol wins
	 */
	public boolean wins(char symbol) {
		for(int i = 1;i<boardRows-1;i++) {
			for(int j = 1;j<boardColumns-1;j++) {
				if(gameBoard[i][j] == symbol) {		
					if(gameBoard[i][j] == gameBoard[i-1][j]) {		// - - -
						if(gameBoard[i][j] == gameBoard[i+1][j]) {
							return true;
						}
					}
					else if(gameBoard[i][j] == gameBoard[i][j-1]) {		// |||
						if(gameBoard[i][j] == gameBoard[i+1][j]) {
							return true;
						}
					}
					else if(gameBoard[i][j] == gameBoard[i-1][j-1]) {		// \\\
						if(gameBoard[i][j] == gameBoard[i+1][j+1]) {
							return true;
						}
					}
					else if(gameBoard[i][j] == gameBoard[i+1][j-1]) {		// ///
						if(gameBoard[i][j] == gameBoard[i-1][j+1]) {
							return true;
						}
					}
				}			
			}
		}	
		return false;
	}
	
	/**
	 * determine whether the game is draw
	 * @return true if the game is draw
	 */
	public boolean isDraw() {
		for(int i = 1;i<boardRows-1;i++) {
			for(int j = 1;j<boardColumns-1;j++) {
				if((gameBoard[i][j]) == '\0') {		//'\0' stands for an empty position
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * return a score
	 * @return value stands for different outpuf of the game
	 */
	public int evalBoard() {
		if(wins('b')) {
			return 0;
		}
		if(wins('o')) {
			return 3;
		}
		if(isDraw()) {
			return 2;
		}
		else {
			return 1;
		}
	}
}
