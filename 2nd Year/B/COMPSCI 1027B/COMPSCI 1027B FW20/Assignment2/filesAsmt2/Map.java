import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The Map class creates a window that shows a hexagon-tile based Map.
 * <p>
 * 
 * The Map is built from a file with the following specifications:
 * <ul>
 * <li>The first line has the number of rows and columns and an optional size of
 * each cell in pixels</li>
 * 
 * <li>Each subsequent line (there will be the same number of lines as rows)
 * contains a string with the characters 'F', 'W', 'T', 'H', 'N', 'I', 'C'
 * separated by spaces. (Note: because this Map is based on hexagons, each
 * alternating row is offset from the left side by half a hexagon, indicated by
 * a space in the input file)
 * </ul>
 * 
 * @author CS1027
 *
 */
public class Map extends JFrame {

	// Serialization UID
	private static final long serialVersionUID = 1L;

	/**
	 * Default time delay when repainting the Map to reflect MapCell changes
	 */
	public static final int DEFAULT_TIME_DELAY = 250;

	// Attributes
	private int timeDelay;
	MapCell start;
	private int numNeighbours = 4;

	/**
	 * Constructor to build a Graphical Map with hexagonal tiles from a file
	 * containing a Map specification
	 * 
	 * @param inFile
	 *            The name of the input file
	 * @throws InvalidMapException
	 *             An invalid character was found in the input file
	 * @throws FileNotFoundException
	 *             Inexistent file
	 * @throws IOException
	 *             An error occurred when trying to access the file.
	 */
	public Map(String inFile) throws InvalidMapException, FileNotFoundException, IOException {
		// set up GUI aspects of the Map component
		super("Map");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel p = new JPanel();

		Color back = new Color(102, 107, 114);
		p.setBackground(back);

		// Get monitor resolution
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;

		// set up the file reader and skip the first line
		BufferedReader in;
		String line = "";
		in = new BufferedReader(new FileReader(inFile));
		line = in.readLine(); // Ignore first line
		line = in.readLine();

		// Tokenize the first line to get the row and column
		StringTokenizer lineTokens = new StringTokenizer(line);
		// First line is the number of rows then the number of columns
		int row = Integer.parseInt(lineTokens.nextToken());
		timeDelay = 1000 - 40 * row; // delay inversely proportional to number
										// of Hexagons

		int col = Integer.parseInt(lineTokens.nextToken());

		int cellSize = screenHeight / (row + 2);
		if (lineTokens.hasMoreTokens()) {
			timeDelay = Integer.parseInt(lineTokens.nextToken());

			if (lineTokens.hasMoreTokens()) {
				cellSize = Integer.parseInt(lineTokens.nextToken());
				if (cellSize > (screenHeight / (row + 2)))
					cellSize = screenHeight / (row + 2);
			}

		}

		// To build the Map we will make temporary use of a 2D array
		// Once built, the hexagons themselves know all of their neighbors, so
		// we do not need the 2D array anymore.
		// Add a row and col of nulls around the "edges" of the builder matrix
		// (+2's)
		// This will greatly simplify the neighbor building process below
		MapCell[][] hexMapBuilder = new MapCell[row + 2][col + 2];

		// HexLayout will arrange the Hexagons in the window
		p.setLayout(new CellLayout(row, col, 2));

		for (int r = 1; r < row + 1; r++) {
			line = in.readLine();
			lineTokens = new StringTokenizer(line);
			// for each token on the line (col in the Map)
			for (int c = 1; c < col + 1; c++) {

				// read the token and generate the hexagon type
				char token = lineTokens.nextToken().charAt(0);
				switch (token) {
				case 'B':
					hexMapBuilder[r][c] = new MapCell(MapCell.CellType.BLOCK, timeDelay);
					break;
				case 'S':
					hexMapBuilder[r][c] = new MapCell(MapCell.CellType.INITIAL, timeDelay);
					this.start = hexMapBuilder[r][c];
					break;
				case 'E':
					hexMapBuilder[r][c] = new MapCell(MapCell.CellType.CUSTOMER, timeDelay);
					break;
				case 'C':
					hexMapBuilder[r][c] = new MapCell(MapCell.CellType.CROSSING, timeDelay);
					break;
				case 'R':
					hexMapBuilder[r][c] = new MapCell(MapCell.CellType.RIGHT_ROAD, timeDelay);
					break;
				case 'U':
					hexMapBuilder[r][c] = new MapCell(MapCell.CellType.UP_ROAD, timeDelay);
					break;
				case 'L':
					hexMapBuilder[r][c] = new MapCell(MapCell.CellType.LEFT_ROAD, timeDelay);
					break;
				case 'D':
					hexMapBuilder[r][c] = new MapCell(MapCell.CellType.DOWN_ROAD, timeDelay);
					break;					
				default:
					throw new InvalidMapException(token);
				}

				// add to the GUI layout
				p.add(hexMapBuilder[r][c]);
			} // end for cols
		} // end for rows

		// go through the 2D matrix again and build the neighbors
		int offset = 0;
		for (int r = 1; r < row + 1; r++) {
			for (int c = 1; c < col + 1; c++) {
				if (numNeighbours == 6) {
					// on even rows(insert from left side) need to add one to the
					// upper and lower neighbors
					// on odd, do not add anything (offset should be 0)
					offset = 1 - r % 2;

					// set the neighbors for this hexagon in the builder
					hexMapBuilder[r][c].setNeighbour(hexMapBuilder[r - 1][c + offset], 0);
					hexMapBuilder[r][c].setNeighbour(hexMapBuilder[r][c + 1], 1);
					hexMapBuilder[r][c].setNeighbour(hexMapBuilder[r + 1][c + offset], 2);
					hexMapBuilder[r][c].setNeighbour(hexMapBuilder[r + 1][c - 1 + offset], 3);
					hexMapBuilder[r][c].setNeighbour(hexMapBuilder[r][c - 1], 4);
					hexMapBuilder[r][c].setNeighbour(hexMapBuilder[r - 1][c - 1 + offset], 5);
				}
				else if (numNeighbours == 4) {
					offset = 0;
					hexMapBuilder[r][c].setNeighbour(hexMapBuilder[r - 1][c + offset], 0);
					hexMapBuilder[r][c].setNeighbour(hexMapBuilder[r][c + 1], 1);
					hexMapBuilder[r][c].setNeighbour(hexMapBuilder[r + 1][c + offset], 2);
					hexMapBuilder[r][c].setNeighbour(hexMapBuilder[r][c - 1 + offset], 3);
				}
			} // end for cols
		} // end for rows

		// close the file
		in.close();

		// set up the GUI window
		this.add(p);
		this.pack();
		this.setSize(cellSize * row, cellSize * col);
		this.setVisible(true);
	}

	/**
	 * Method will return a reference to the inital cell
	 * 
	 * @return A reference to the initial cell
	 */
	public MapCell getStart() {
		return this.start;
	}

	/**
	 * Get the current time delay used when repainting the Map to reflect
	 * changes made to the map cells
	 * 
	 * @return the timeDelay
	 */
	public int getTimeDelay() {
		return timeDelay;
	}

	/**
	 * Set the amount of time to wait when repainting the Map to reflect changes
	 * made to the cells
	 * 
	 * @param timeDelay
	 *            the timeDelay to set
	 */
	public void setTimeDelay(int timeDelay) {
		this.timeDelay = timeDelay;
	}

	@Override
	/**
	 * This method will update the Map to reflect any changes to the cells.
	 * The method includes a time delay, which can be changed
	 * with the setDelay method.
	 */
	public void repaint() {
		try {
			Thread.sleep(this.timeDelay);
		} catch (Exception e) {
			System.err.println("Error while issuing time delay\n" + e.getMessage());
		}
		super.repaint();
	}


	public static void main (String[] args) {
		Map myMap;
		int numNeighbours = 4;

		if (args.length < 1) {
			System.out.println("You need to specify the name of the input file");
			System.out.println("From the terminal type: java Map input_file");
			System.exit(0);
		}
		
		try {
			myMap = new Map(args[0]);
			Path thePath = new Path(myMap);
			thePath.findPath();
		} catch (Exception e) {
			System.out.println("Error reading input file.");
			System.out.println("Exception thrown by the virtual machine: " + e.getMessage());
		}
	}
}
