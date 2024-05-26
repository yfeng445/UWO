import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.io.*;
import java.util.*;

public class PlayGame extends JFrame {
	static final long serialVersionUID = 0;
	private final char COMPUTER = 'o';
	private final char HUMAN = 'b';
	private final char BLOCKED = 'd';
	private final char EMPTY = 'g';

    private final int MIN_DELAY = 400;   // Minimum amount of time in milliseconds, before
                                         // computer shows its move
    private final int THINKING = 10000;  // Number of moves considered by the computer before
                                         // it prints a '.' for the user to know the program
                                         // is still working
    private final int COMPUTER_LOSES = 0;
    private final int HUMAN_LOSES = 3;

	private JButton[][] gameDisplay;/* Game board */
	private Evaluate t;
	private Dictionary configurations;
	private int numBlockedPositions; /* Number of blocked positions */
	
	private ClickHandler handler;

        private int numCalls = 0;
        private boolean isThinking = false;

        // Game parameters
        private int rowsBoard;            // Number of rows of the game board
        private int colsBoard;            // Number of columns of the game board
        private int maxLevel;             // Maximum level of the game tree to explore
        private int adjacentToWin;        // Number of symbols in adjacent positions needed to win

	/*
	 * Constructor. Creates a panel to represent the game board and destroys the
	 * panel when its window is closed.
	 */
	public PlayGame(String fileName) {
		Container c = getContentPane();

		// Read input file
		try {
		    BufferedReader inFile = new BufferedReader(new FileReader(fileName)); 
		    String line;

		    rowsBoard = Integer.parseInt(inFile.readLine());
		    colsBoard = Integer.parseInt(inFile.readLine());
		    adjacentToWin = Integer.parseInt(inFile.readLine());
		    maxLevel = Integer.parseInt(inFile.readLine());

		    c.setLayout(new GridLayout(rowsBoard, colsBoard));
		    gameDisplay = new JButton[rowsBoard][colsBoard];
		    Icon emptySquare = new ImageIcon("empty.gif");
		    handler = new ClickHandler(rowsBoard,colsBoard);

		    /* Board is represented as a grid of clickable buttons */
		    for (int i = 0; i < rowsBoard; i++)
			for (int j = 0; j < colsBoard; j++) {
				gameDisplay[i][j] = new JButton("", emptySquare);
				gameDisplay[i][j].setEnabled(true);
				add(gameDisplay[i][j]);
				gameDisplay[i][j].addActionListener(handler);
			}

		    t = new Evaluate(rowsBoard, colsBoard, adjacentToWin, maxLevel); /* Student code */

		    for (int row = 0; row < rowsBoard; ++row) {
			line = inFile.readLine();
			for (int col = 0; col < colsBoard; ++col)
			    if (line.charAt(col) == BLOCKED) {
				gameDisplay[row][col].setIcon(new ImageIcon("blocked.gif"));
				gameDisplay[row][col].paint(gameDisplay[row][col].getGraphics());
				t.storePlay(row, col, BLOCKED);
			    }
		    }
		}
		catch (IOException e) { 
			System.out.println("Error opening file. " + e.getMessage());
			System.exit(0);
		}
		catch (Exception e) {
			System.out.println("Error in input file: "+e.getMessage());
			System.exit(0);
		}
	}

	/*
	 * To run the program type: java PlayGame size to_win, where size is the size
	 * of the board and to_win is the number of symbols in line needed to win
	 * the game.
	 */
	public static void main(String[] args) {


		/* Check that the number of arguments is the correct one */
		if (args.length < 1) {
			System.out.println("Usage: java PlayGame input_file");
			System.exit(0);
		}

		/* Create the game board and start the game */
		JFrame f = new PlayGame(args[0]);

		f.setSize(((PlayGame)f).colsBoard * 100, ((PlayGame)f).rowsBoard * 100);
		f.setVisible(true);
		
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				System.exit(0);
			}
		});
		
		if (Math.random() > 0.5) 
			((PlayGame)f).handler.displayComputerPlay();
	}

	/*
	 * Panel to represent the game board. It contians methods for detecting the
	 * position selected by the human player.
	 */

	private class ClickHandler implements ActionListener {
	    private int boardRows, boardCols;
		private boolean game_ended = false;

		/* Constructor. Save board size in instance variable */
	    public ClickHandler(int rows, int cols) {
			boardRows = rows;
			boardCols = cols;
		}

		/*
		 * When the user has selected a play, this method is invoked to process
		 * the selected play
		 */
		public void actionPerformed(ActionEvent event) {
		    if (event.getSource() instanceof JButton) { // Some position of the board was selected

				int row = -1, col = -1;

				if (game_ended)
					System.exit(0);
				/* Find out which position was selected by the player */
				for (int i = 0; i < boardRows; i++) {
					for (int j = 0; j < boardCols; j++)
						if (event.getSource() == gameDisplay[i][j]) {
							row = i;
							col = j;
							break;
						}
					if (row != -1) break;
				}

				if (t.squareIsEmpty(row, col)) {
					/* Valid play, mark it on the board */
					gameDisplay[row][col].setIcon(new ImageIcon("human.gif"));
					gameDisplay[row][col].paint(gameDisplay[row][col].getGraphics());

					t.storePlay(row, col, HUMAN);
					if (t.wins(HUMAN)) endGame("Human wins");
					else {
						if (t.isDraw())
							endGame("Game is a draw");
						else 
							displayComputerPlay();
					}
				} else
					System.out.println("Invalid play");

			}
		}
		
	    /* Displays the play made by the computer. If the computer takes a long time
               a message is displayed so the humanplayer knows that the program is still working */
		private void displayComputerPlay() {
			PosPlay pos;
			
			Date d = new Date();
			long start = d.getTime();
			numCalls = 0;
			isThinking = false;

			pos = computerPlay(COMPUTER, -1, 4, 0);

			if (isThinking) System.out.println("");
			long end = d.getTime();
			try {
			    if (end-start < MIN_DELAY) 
				    Thread.sleep(MIN_DELAY-end+start);
			}
			catch (Exception e) {
				System.out.println("Something is wrong with timer");
			}

			t.storePlay(pos.getRow(), pos.getCol(), COMPUTER);
			gameDisplay[pos.getRow()][pos.getCol()].setIcon(new ImageIcon("computer.gif"));
			if (t.wins(COMPUTER))
				endGame("Computer wins");
			else if (t.isDraw())
				endGame("Game is a draw");
		}

		/* Explore the game tree and choose the best move for the computer */
		private PosPlay computerPlay(char symbol, int highest_score, int lowest_score, int level) {

			char opponent; // Opponent symbol
			PosPlay reply; // Opponent best reply

			int bestRow = -1;
			int bestColumn = -1; // Position of best play
			int bestLevel = -1;

			int value;
			Data lookupVal;

			if (level == 0) /* Create new hash table */
				configurations = t.createDictionary();

			if (symbol == COMPUTER) {
				opponent = HUMAN;
				value = -1;
			} else {
				opponent = COMPUTER;
				value = 4;
			}

			if (++numCalls == THINKING) {
			    System.out.print("Please wait ..");
			    isThinking = true;
			}
			else if ((numCalls % THINKING) == 0) System.out.print(".");

			// Check if the next move wins or loses the game. This step is added to speed-up the algorithm
			if (level == 0) {
				for (int r = 0; r < boardRows; ++r)
					for (int c = 0; c < boardCols; ++c) 
						if (t.squareIsEmpty(r,c)) {
							t.storePlay(r,c,symbol);
							if (t.wins(symbol)) {
								t.storePlay(r,c,EMPTY);
								return new PosPlay(HUMAN_LOSES,r,c,0);
							}
							t.storePlay(r,c,EMPTY);
						}
				for (int r = 0; r < boardRows; ++r)
					for (int c = 0; c < boardCols; ++c) 
						if (t.squareIsEmpty(r,c)) {
							t.storePlay(r,c,HUMAN);
							if (t.wins(HUMAN)) {
								t.storePlay(r,c,EMPTY);
								return new PosPlay(COMPUTER_LOSES,r,c,0);
							}
							t.storePlay(r,c,EMPTY);
						}
			}
					
					
			// Scan entries of the game board in random order
			int row, column;
			row = (int)(Math.random() * boardRows);

			for (int r = 0; r < boardRows; r++) {
				column = (int)(Math.random() * boardCols);
				for (int c = 0; c < boardCols; c++) {
					if (t.squareIsEmpty(row, column)) { // Empty position
						t.storePlay(row, column, symbol); // Store next play
						if (t.wins(symbol) || t.isDraw() || (level >= maxLevel))
							// Game ending situation or max number of levels
							// reached
						    reply = new PosPlay(t.evalBoard(), row, column, level);
						else {
							lookupVal = t.repeatedConfig(configurations);
							if (lookupVal != null)
							    reply = new PosPlay(lookupVal.getScore(), row, column, lookupVal.getLevel());
							else {
								reply = computerPlay(opponent, highest_score, lowest_score, level + 1);
								if (t.repeatedConfig(configurations) == null)
								    t.insertConfig(configurations, reply.getScore(), reply.getLevel());
							}
						}
						t.storePlay(row, column, EMPTY);

						if ((symbol == COMPUTER && reply.getScore() > value)
								|| (symbol == HUMAN && reply.getScore() < value)) {
							bestRow = row;
							bestColumn = column;
							value = reply.getScore();
							bestLevel = reply.getLevel();

							/* Alpha/beta cut */
							if (symbol == COMPUTER && value > highest_score)
								highest_score = value;
							else if (symbol == HUMAN && value < lowest_score)
								lowest_score = value;

							if (highest_score >= lowest_score)
							        return new PosPlay(value, bestRow, bestColumn, bestLevel);
						}
						else if ((symbol == COMPUTER && reply.getScore() == COMPUTER_LOSES && reply.getLevel() > bestLevel)) {
							bestRow = row;
							bestColumn = column;
							bestLevel = reply.getLevel();
							value = reply.getScore();
						}

					}
					column = (column + 1) % boardCols;
				}
				row = (row + 1) % boardRows;
			}
			return new PosPlay(value, bestRow, bestColumn, bestLevel);
		}

		/* Prompt the user for a key to terminate the game */
		private void endGame(String mssg) {
			System.out.println(mssg);
			System.out.println("");
			System.out.println("Click on board to terminate game");
			game_ended = true;
		}

	}
}
