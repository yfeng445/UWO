import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.Random;
import java.util.Scanner;

public class PlayChasingGame extends JFrame implements KeyListener {
	private static final long serialVersionUID = 1;
	private static final String ROCK = "rock";
	private static final String CHOP = "scissors";
	private static final String EMPTY = "empty";
	private static final String APPLE = "apple";
	private static final String SNAKE = "snake";
	private static final String HEAD = "head";
	private static final int OUT = 0;
	private static final int HIT_ROCK = 1;
	private static final int OVERLAP = 2;
	private static final int OK = 3;
	private static final int EAT_APPLE = 4;
	private static final int HIT_SCISSORS = 5;
	private static final int POS_HEAD = 0;
	private static final int SLEEP_TIME = 1000;
	private static final int DELAY_STEP = 100;
	private final int MIN_DELAY = 100;   // Minimum amount of time in milliseconds, before
										 // computer moves snake

	private static JButton [][] gameDisplay;    // Game board displayed on the screen   
	private static Container contentPane;
	private static ImageIcon emptyTile, appleTile, horSnakeLinkedTile, verSnakeLinkedTile,
					leftUpTile, leftDownTile, rightUpTile, rightDownTile, rightHeadTile, 
					leftHeadTile, upHeadTile, downHeadTile, rightDeadTile, leftDeadTile,
					upDeadTile, downDeadTile, rockTile, chopTile, chaserTile, killChaserTile;
	
	private int delay = 400; // Initial delay
	private boolean toWait = true;
	private static int slowing = 3;
	private static int numSteps = 0;
	private static int board_width;
    private static int board_length;    
	private static int tile_width;
	private static int tile_length;
	private static BoardGameLinked board;
    private static SnakeLinked theSnake;
	private static int applesEaten = 0;
	private static int applesInBoard = 0;
	private static boolean gameEnded = false;
	private static String direction = "";
	
	private static String[][] matrix;
	
	private static final int UP = 0;
	private static final int RIGHT = 1;
	private static final int DOWN = 2;
	private static final int LEFT = 3;
	private static final int maxChasers = 10;	
	private static Chaser[] chasers = new Chaser[maxChasers];;
	private static int numChasers;
	private static final int NEW_CHASER = 15;
	
	/* Constructor. Creates a panel to represent the game board and destroys
       the panel when its window is closed.                                 */
	/* -------------------------------------------- */
	public PlayChasingGame(String boardFile)
	/* -------------------------------------------- */
	{
		contentPane = getContentPane();
		
		/* Read the input file containing the objects on the board */
		initBoard(boardFile);
		
		setSize(10*tile_length,5*tile_width);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		addWindowListener(new WindowAdapter( ) {
			public void windowClosing(WindowEvent event) {
				System.exit( 0 );
			}                
		}); 
		//contentPane.addKeyListener(this);
		contentPane.setFocusable(true);
		contentPane.requestFocusInWindow();
		setKeyBindings();
	}

	/* --------------------------------- */
	private void setKeyBindings() {
	/* --------------------------------- */
		int flag = JComponent.WHEN_IN_FOCUSED_WINDOW;
		Action terminate = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.exit(0);
			}
		};
		Action faster = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				if (delay > MIN_DELAY)
					delay -= DELAY_STEP;
			}
		};		
		Action slower = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				delay += DELAY_STEP;
			}
		};		
		Action debug = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				printDebugInfo();
			}
		};		
		Action pause = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				toWait = true;
			}
		};	
		Action moveUp = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				if(gameEnded) return;
				direction = "up";
				if (toWait) toWait = false;
			}
		};
		Action moveDown = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				if (gameEnded) return;
				direction = "down";
				if (toWait) toWait = false;				
			}
		};
		Action moveRight = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				if (gameEnded) return;
				direction = "right";
				if (toWait) toWait = false;				
			}
		};
		Action moveLeft = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				if (gameEnded) return;
				direction = "left";
				if (toWait) toWait = false;				
			}
		};		
		//GameButton listener = (GameButton)gameDisplay[0][0];
		JButton listener = gameDisplay[0][0];
		listener.getInputMap(flag).put(KeyStroke.getKeyStroke("X"),"terminate");
		listener.getActionMap().put("terminate",terminate);
		listener.getInputMap(flag).put(KeyStroke.getKeyStroke("F"),"faster");
		listener.getActionMap().put("faster",faster);
		listener.getInputMap(flag).put(KeyStroke.getKeyStroke("S"),"slower");
		listener.getActionMap().put("slower",slower);
		listener.getInputMap(flag).put(KeyStroke.getKeyStroke("D"),"debug");
		listener.getActionMap().put("debug",debug);
		listener.getInputMap(flag).put(KeyStroke.getKeyStroke("P"),"pause");
		listener.getActionMap().put("pause",pause);	
		listener.getInputMap(flag).put(KeyStroke.getKeyStroke("UP"),"up");
		listener.getActionMap().put("up",moveUp);
		listener.getInputMap(flag).put(KeyStroke.getKeyStroke("DOWN"),"down");
		listener.getActionMap().put("down",moveDown);
		listener.getInputMap(flag).put(KeyStroke.getKeyStroke("RIGHT"),"right");
		listener.getActionMap().put("right",moveRight);
		listener.getInputMap(flag).put(KeyStroke.getKeyStroke("LEFT"),"left");
		listener.getActionMap().put("left",moveLeft);		
	}
		
		/* Process the key that was entered by the user */
	/* ======================================= */
	public void keyPressed(KeyEvent e) {
	/* ======================================= */
		int result, row, col;
		int c = e.getKeyCode();

		if (c == KeyEvent.VK_X) { // Exit program
			dispose();
			System.exit(0);
		} else if (gameEnded) return;
		else if (c == KeyEvent.VK_F) { // Move snake faster
			if (delay > MIN_DELAY)
				delay -= DELAY_STEP;
		} else if (c == KeyEvent.VK_S) // Move snake slower
					delay += DELAY_STEP;
		else if (c == KeyEvent.VK_D) // Debug
				printDebugInfo();
		else if (c == KeyEvent.VK_P) // Pause
				toWait = true;
		else {
			if (c == KeyEvent.VK_UP) direction = "up";
			else if (c == KeyEvent.VK_DOWN) direction = "down";
			else if (c == KeyEvent.VK_LEFT) direction = "left";
			else if (c == KeyEvent.VK_RIGHT) direction = "right";
			if (toWait) toWait = false; // Start the game if it is suspended
			//executeMove();
		}
	}

	/* Draw the objects placed on the board */
	/* ======================================= */
	private static void initBoard(String boardFile) {
	/* ======================================= */
		try {
			int row, col;
			String type;
			BufferedReader in = new BufferedReader(new FileReader(boardFile));
			String line;
			
			tile_length = Integer.parseInt(in.readLine());
			tile_width = Integer.parseInt(in.readLine());
			board_length = Integer.parseInt(in.readLine());
			board_width = Integer.parseInt(in.readLine());

			createTiles();
		
			contentPane.setLayout(new GridLayout(board_width,board_length));  			
			gameDisplay = new JButton[board_width][board_length];
			
			/* Board is represented as a grid of clickable buttons */
			for(int i = 0; i < board_width; i++)
				for(int j = 0; j < board_length; j++) {
					gameDisplay[i][j] = new JButton("",emptyTile);
					gameDisplay[i][j].setEnabled(true);
					contentPane.add(gameDisplay[i][j]);
			}			
			row = Integer.parseInt(in.readLine());
			col = Integer.parseInt(in.readLine());
			setTile(row,col,"head");
			
			line = in.readLine();
			while (line != null) {
				row = Integer.parseInt(line);
				col = Integer.parseInt(in.readLine());
				type = in.readLine();
				setTile(row,col,type);
				if (type.equals("apple")) ++applesInBoard;
				line = in.readLine();
			}
		}
		catch (IOException e) {
			System.out.println("Cannot read file "+boardFile);
		}
		catch (Exception e) {
			System.out.println("Invalid format of board file "+boardFile);
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}
	
	/* To run the program type: java PlayGame boardFile xCoordSnakeLinked yCoordSnakeLinked                  */
	/* -------------------------------------------- */
	public static void main(String [] args)
	/* -------------------------------------------- */
	{
		String boardFile;
		int steps = 0;
		
		/* Check that the number of arguments is the correct one */
		if (args.length != 1) {
			System.out.print("enter name of board file: ");
			Scanner in = new Scanner(System.in);
			boardFile = in.next();
		}
		else boardFile = args[0];
		
		/* Create the game board and start the game */
		PlayChasingGame display = new PlayChasingGame(boardFile);
		matrix = new String[board_width][board_length];
		board = new BoardGameLinked(boardFile);
		theSnake = board.getSnakeLinked();
		
		// Play the game
		while (true) {
			//display.waitStep();
			display.waitStep();
			++steps;
			if (steps == NEW_CHASER) {
				steps = 0;
				if (numChasers < maxChasers) {
					chasers[numChasers] = new Chaser(board,numChasers,chasers,display);
					++numChasers;
				}
			}				
		}
	}
	
	/* ================== */
	public void waitStep() {
	/* ================== */
		try {
			if (toWait) {
				/* Game is in pause mode */
				while (toWait)
					Thread.sleep(SLEEP_TIME);
			} else if (delay > 0) {
				Thread.sleep(delay);
				++numSteps;
				executeMove();
				if (numSteps == slowing) {
					moveChasers();
					numSteps = 0;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/* Move all the chasers */
	/* ========================= */
	private void moveChasers() {
	/* ========================= */		
		String[] directions = {"up", "right", "down", "left"};
		int result;
		Position pos;
		for (int i = 0; i < numChasers; ++i) {
			pos = new Position(chasers[i].getRow(), chasers[i].getCol());
			result = checkMove(directions[chasers[i].getDir()], board, pos);
			switch (result) {
				case (OUT):
				case (HIT_ROCK):
				case (EAT_APPLE):
				case (HIT_SCISSORS): // Change direction of chaser
					changeDirection(board,pos,i,true);
					break;
				case (OK):
					changeDirection(board,pos,i,false);
					break;
				case (OVERLAP): 
					killSnakeLinked(direction);
					Position newPos = newHeadPosition(pos,directions[chasers[i].getDir()]);
					redrawTile(pos.getRow(),pos.getCol(),emptyTile);
					redrawTile(newPos.getRow(),newPos.getCol(),killChaserTile);
			}
		}
	}
	

	/* Change direction of the chaser, making sure that in the new direction it will not overlap
	   any objects placed on the board.                                                           */
	/* =============================================================================== */
	private void changeDirection(BoardGameLinked board,Position pos, int chaserIndex, boolean changeDir) {
	/* =============================================================================== */		
		String[] direction = {"up", "right", "down", "left"};
		int result, row, col, j, dir;
		Random randgen = new Random();
		
		// Check if direction needs to be changed
		if (chasers[chaserIndex].getCount() == chasers[chaserIndex].getSteps()) {
			chasers[chaserIndex].setCount(0);
			changeDir = true;
		}
		else chasers[chaserIndex].setCount(chasers[chaserIndex].getCount()+1);
		
		if (changeDir) dir = randgen.nextInt(4);
		else dir = chasers[chaserIndex].getDir();
		row = pos.getRow();
		col = pos.getCol();		
		
		// Check all directions until finding one where the chaser will not overlap anything
		for (int i = 0; i < 4; ++i) {
			result = checkMove(direction[dir],board,pos);
			if (result == OK) {
				// Compute new position of chaser
				if (dir == UP) row--;
				else if (dir == DOWN) row++;
				else if (dir == RIGHT) col++;
				else col--;
				
				// Check for intersections with other chasers
				for (j = 0; j < numChasers; ++j)
					if (j != chaserIndex && chasers[j].getRow() == row && chasers[j].getCol() == col)
						break;
				if (j == numChasers) { // No overlap
					chasers[chaserIndex].setDir(dir);
					redrawTile(pos.getRow(),pos.getCol(),emptyTile);
					redrawTile(row,col,chaserTile);
					chasers[chaserIndex].setRow(row);
					chasers[chaserIndex].setCol(col);
					return;
				}
			}
			dir = (dir + 1) % 4;
		}
		// Do not move chaser if it cannot be moved without intersecting an object
	}
	
	/* ================== */
	private void executeMove() {
	/* ================== */		
		int result = checkMove(direction,board,theSnake.getPosition(0));
		switch (result) {
				case (OUT): 
				case (HIT_ROCK):
				case (OVERLAP): 
				    killSnakeLinked(direction);
				    break;
				case (EAT_APPLE): 
				    eatApple(direction);
				    break;
				case (OK): 
				    redrawSnakeLinked(direction);
				    break;
			    case (HIT_SCISSORS): 
				    if (theSnake.getLength() == 1) cutKillSnakeLinked(direction);
				    else cutSnakeLinked(direction);
				    break;
		}		
	}

	/* Returns
	   0: if the move brings the snake out of the board
	   1: if the move causes the snake to hit a rock
	   2: if the move causes the snake to overlap itself
	   3: if the move does not cause a collision
	   4: if the move causes the snake to eat an apple
	   5: if the move causes the snake to overlap scissors */
	/* ============================================================================ */ 	   
	private int checkMove(String direction, BoardGameLinked board, Position pos) {
	/* ============================================================================ */ 		
		Position newPos = newHeadPosition(pos,direction);
		int row = newPos.getRow();
		int col = newPos.getCol();

		// Check if new position is out of the board
		if (row < 0 || col < 0) return 0;
		else if (row >= board.getWidth() || col >= board.getLength()) return 0;
		else if (board.getObject(row,col).equals("rock")) return 1;
		else if (board.getObject(row,col).equals("empty")) {
				if (theSnake.snakePosition(newPos)) return 2;
				else return 3;
		}
		else if (board.getObject(row,col).equals("apple")) return 4;
		else if (board.getObject(row,col).equals("scissors")) return 5;
		return -1;
	}
	
	/* ========================== */ 
	private void killSnakeLinked(String direction) {
	/* ========================== */ 		
		Position head = theSnake.getPosition(0);
		switch(direction) {
		case ("right"): redrawTile(head.getRow(),head.getCol(),rightDeadTile);
						break;
		case ("left"): redrawTile(head.getRow(),head.getCol(),leftDeadTile);
						break;
		case ("up"): redrawTile(head.getRow(),head.getCol(),upDeadTile);
						break;
		case ("down"): redrawTile(head.getRow(),head.getCol(),downDeadTile);
						break;	
		}						
	    System.out.println("SnakeLinked is dead :(");
		System.out.println("Apples eaten = "+applesEaten);
	    toWait = true;
		gameEnded = true;
	}

	/* ========================== */ 
	private void cutKillSnakeLinked(String direction) {
	/* ========================== */ 	
		Position oldHead = theSnake.getPosition(POS_HEAD);
		redrawTile(oldHead.getRow(),oldHead.getCol(),emptyTile);
		Position head = newHeadPosition(theSnake.getPosition(POS_HEAD),direction);
		switch(direction) {
		case ("right"): redrawTile(head.getRow(),head.getCol(),rightDeadTile);
						break;
		case ("left"): redrawTile(head.getRow(),head.getCol(),leftDeadTile);
						break;
		case ("up"): redrawTile(head.getRow(),head.getCol(),upDeadTile);
						break;
		case ("down"): redrawTile(head.getRow(),head.getCol(),downDeadTile);
						break;	
		}						
	    System.out.println("SnakeLinked is dead :(");
		System.out.println("Apples eaten = "+applesEaten);
	    toWait = true;
		gameEnded = true;
	}

	/* ======================================================= */ 
    public void redrawTile(int row, int col, ImageIcon icon) {
	/* ======================================================= */ 		
	    gameDisplay[row][col].setIcon(icon);
	    gameDisplay[row][col].paint(gameDisplay[row][col].getGraphics());
	}


	/* ===================================== */ 
    private void cutSnakeLinked(String direction) {
	/* ===================================== */ 		
	    Position prevTail = theSnake.getPosition(theSnake.getLength()-1);
	    theSnake.shrink();
	    Position newHead = newHeadPosition(theSnake.getPosition(POS_HEAD),direction);
		board.setObject(newHead.getRow(),newHead.getCol(),"empty");		// Erase scissors tile
	    redrawTile(prevTail.getRow(),prevTail.getCol(),emptyTile);
	    redrawHead(newHead.getRow(),newHead.getCol(),direction);
	    Position head = theSnake.getPosition(POS_HEAD);
	    if (theSnake.getLength() == 1) {
			redrawTile(head.getRow(), head.getCol(),emptyTile);
			theSnake.moveSnakeLinked(direction);
		}
	    else {
			Position tail = theSnake.getPosition(theSnake.getLength()-1);
			theSnake.moveSnakeLinked(direction);
			redrawTile(tail.getRow(),tail.getCol(),emptyTile);
			redrawNeck();
	    }

	}

	/* ===================================== */ 
    private void eatApple(String direction) {
	/* ===================================== */ 		
	    Position prevHead = theSnake.getPosition(POS_HEAD);
	    theSnake.grow(direction);
	    Position head = theSnake.getPosition(POS_HEAD);
	    redrawNeck();
	    redrawHead(head.getRow(),head.getCol(),direction);
		board.setObject(head.getRow(),head.getCol(),"empty");
		++applesEaten;
		--applesInBoard;
		if (applesInBoard == 0) putApple();
	}
	
	/* ======================== */
	private void putApple() {
	/* ======================== */
		int rowHead = theSnake.getPosition(0).getRow();
		int colHead = theSnake.getPosition(0).getCol();
		int row, col;
		int iter = 0;
		boolean getApple = true;
		Random randGen = new Random();
		while (true) {
			row = randGen.nextInt(board_width);
			col = randGen.nextInt(board_length);
			if ((row != rowHead) && (col != colHead) && board.getObject(row,col).equals("empty")
				&& !theSnake.snakePosition(new Position(row,col))) {
				if (getApple) {
					board.setObject(row,col,"apple");
					redrawTile(row,col,appleTile);
					applesInBoard = 1;
					if (randGen.nextInt(board_width) == 0)
						getApple = false; // Create scissors
					else return;
				} 
				else { // generating scissors
					board.setObject(row,col,"scissors");
					redrawTile(row,col,chopTile);
					return;
				}
			}
			if (++iter > 10000) {
				System.out.println("Error. Something is wrong with methods getObject or setObject");
				System.exit(0);
			}
		}
	}
	
	/* ======================================== */ 	
    private void redrawSnakeLinked(String direction) {
	/* ======================================== */ 		
	    Position prevTail = theSnake.getPosition(theSnake.getLength()-1);
	    Position prevHead = theSnake.getPosition(POS_HEAD);
	    int row = prevTail.getRow();
	    int col = prevTail.getCol();
		redrawTile(row,col,emptyTile); // Erase the tail of the snake
				
	    theSnake.moveSnakeLinked(direction);

	    // Change the tile where the head was previously to a tile of the
	    // body of the snake
	    if (theSnake.getLength() > 1) 
			redrawNeck();

	    // Draw the new head
	    Position newHead = newHeadPosition(prevHead,direction);
	    redrawHead(newHead.getRow(),newHead.getCol(),direction);
	}

	/* ==================================================== */ 
	private void redrawHead(int row, int col, String direction) {
	/* ===================================================== */ 		
		if (direction.equals("right")) 
			redrawTile(row,col,rightHeadTile);
		else if (direction.equals("left"))
			redrawTile(row,col,leftHeadTile);	
		else if (direction.equals("up"))
			redrawTile(row,col,upHeadTile);	
		else 
			redrawTile(row,col,downHeadTile);			
	}

	/* =================== */ 
	private void redrawNeck() {
	/* =================== */ 		
		int rowNeck = 0, colNeck = 0;
		Position head, neck = null, body = null;
		int length = theSnake.getLength();		
		head = theSnake.getPosition(0);
		if (length > 1) {
			neck = theSnake.getPosition(1);
			rowNeck = neck.getRow();
			colNeck = neck.getCol();
		}
		if (length > 2) body = theSnake.getPosition(2);

		if (length == 2) {
			if (head.getRow() == neck.getRow()) // Horizontal snake
				redrawTile(neck.getRow(),neck.getCol(),horSnakeLinkedTile);
				else redrawTile(neck.getRow(),neck.getCol(),verSnakeLinkedTile);
		}
		else if(length > 2) {
			if (head.getRow() == body.getRow()) // Horizontal snake
				redrawTile(rowNeck,colNeck,horSnakeLinkedTile);
			else if (head.getCol() == body.getCol()) // Vertical snake
					redrawTile(rowNeck,colNeck,verSnakeLinkedTile);
			else if (head.getRow() < body.getRow()) // Neck bending up or from down
					if (head.getCol() < body.getCol()) // Neck bending left or snake from right
						if (head.getRow() == rowNeck)  
							redrawTile(rowNeck,colNeck,leftDownTile);
						else redrawTile(rowNeck,colNeck,rightUpTile);
					else if (head.getRow() == rowNeck)
							redrawTile(rowNeck,colNeck,rightDownTile);
						else redrawTile(rowNeck,colNeck,leftUpTile);
			else // Neck bending down
				if (head.getCol() < body.getCol()) 
					if (rowNeck == head.getRow())
						redrawTile(rowNeck,colNeck,leftUpTile);
					else redrawTile(rowNeck,colNeck,rightDownTile);
				else if (rowNeck == head.getRow())
						redrawTile(rowNeck,colNeck,rightUpTile);
					else redrawTile(rowNeck,colNeck,leftDownTile);
		}
	}
	
	/* ================================================================ */
    private Position newHeadPosition(Position head, String direction) {
	/* ================================================================ */
	    int row, col;
	    row = head.getRow();
	    col = head.getCol();
	    if (direction.equals("up")) --row; 
	    else if (direction.equals("down")) ++row;
	    else if (direction.equals("right")) ++col;
	    else --col;
	    return new Position(row,col);
	}

	/* ======================================= */
	public void keyTyped(KeyEvent e) {
	/* ======================================= */

	}

	/* ======================================= */
	public void keyReleased(KeyEvent ke) {
	/* ======================================= */
	}

	/* Create an icon object from the given image file to be stored
	   as a tile in the game board.                                   */
	/* ================================================== */ 	   
	public static ImageIcon makeTile(String imageFile) {
	/* ================================================== */ 		
		ImageIcon icon = new ImageIcon(imageFile);
		Image img = icon.getImage();
		Image scaledImage = img.getScaledInstance(tile_length,tile_width,
							  java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(scaledImage);
		return icon;
	}
	
	/* Set a tile of the given type in the position of the game board given
	by the first two parameters.                                            */
	/* ======================================================= */ 	
	private static void setTile(int row, int col, String type) {
	/* ======================================================= */ 
		if (gameDisplay[row][col] == null) System.out.println("Entry empty");
		
		if (type.equals(ROCK)) 
			gameDisplay[row][col].setIcon(rockTile);
		else if(type.equals(CHOP))
			gameDisplay[row][col].setIcon(chopTile);
		else if(type.equals(SNAKE))
			gameDisplay[row][col].setIcon(horSnakeLinkedTile);
		else if(type.equals(HEAD))
			gameDisplay[row][col].setIcon(rightHeadTile);
		else if(type.equals(APPLE))
			gameDisplay[row][col].setIcon(appleTile);	
		else if(type.equals(EMPTY))
			gameDisplay[row][col].setIcon(emptyTile);		
			
		//gameDisplay[row][col].paint(gameDisplay[row][col].getGraphics());	
	}

	/* ========================= */ 
	private static void createTiles() {
	/* ========================= */ 		
			emptyTile = makeTile("empty.gif");
			appleTile = makeTile("apple.gif");
			horSnakeLinkedTile = makeTile("hsnake.gif");
			verSnakeLinkedTile = makeTile("vsnake.gif");
			leftUpTile = makeTile("leftup.gif");
			rightUpTile = makeTile("rightup.gif");
			leftDownTile = makeTile("leftdown.gif");
			rightDownTile = makeTile("rightdown.gif");
			rightDeadTile = makeTile("rightd.gif");
			leftDeadTile = makeTile("leftd.gif");
			upDeadTile = makeTile("upd.gif");
			downDeadTile = makeTile("downd.gif");			
			rightHeadTile = makeTile("rhead.gif");
			leftHeadTile = makeTile("lhead.gif");
			upHeadTile = makeTile("uhead.gif");
			downHeadTile = makeTile("dhead.gif");			
			rockTile = makeTile("rock.gif");
			chopTile = makeTile("chop.gif");	
			chaserTile = makeTile("eagle.jpg");
			killChaserTile = makeTile("eaglek.jpg");
	}

	/* ============================== */
	public ImageIcon getEagleIcon() {
	/* ============================== */		
		return chaserTile;
	}
	
	/* ============================== */
	private void printDebugInfo() {
	/* ============================== */
		System.out.println("Board (without the snake):");
		for (int i = 0; i < board_width; ++i) {
			System.out.print("Row "+i+": ");
			for (int j = 0; j < board_length; ++j) {
				System.out.print(board.getObject(i,j));
				if (j < board_length - 1) System.out.print(", ");
				else System.out.println("");
			}
		}
		System.out.println("SnakeLinked\'s body:");
		for (int i = 0; i < theSnake.getLength(); ++i) {
			System.out.print("("+theSnake.getPosition(i).getRow()+","+theSnake.getPosition(i).getCol()+")");
			if (i < theSnake.getLength() - 1) System.out.print(", ");
			else System.out.println("");
		}
	}
}
