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

public class Map extends JFrame {
	
	private int timeDelay = 0;
	private MapCell startCell;
	private int numNeighbours = 4;


	public Map (String mapFile) throws InvalidMapException, FileNotFoundException, IOException {
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
		in = new BufferedReader(new FileReader(mapFile));
		line = in.readLine(); // Ignore first line
		line = in.readLine();

		// Tokenize the first line to get the row and column
		StringTokenizer lineTokens = new StringTokenizer(line);
		
		// First line is the number of rows then the number of columns
		int row = Integer.parseInt(lineTokens.nextToken());
		int col = Integer.parseInt(lineTokens.nextToken());

		int cellSize = screenHeight / (row + 2);

		if (lineTokens.hasMoreTokens()) {

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
		MapCell[][] mapBuilder = new MapCell[row + 2][col + 2];

		// HexLayout will arrange the Hexagons in the window
		p.setLayout(new CellLayout(row, col, 2));

		int i = 0;
		for (int r = 1; r < row + 1; r++) {
			line = in.readLine();
			lineTokens = new StringTokenizer(line);
			// for each token on the line (col in the Map)
			for (int c = 1; c < col + 1; c++) {
				// Read the token and generate the cell type
				char token = lineTokens.nextToken().charAt(0);
				//System.out.println(token);
				switch (token) {
				case 'W': // wall
					mapBuilder[r][c] = new MapCell(i,MapCell.CellType.BLOCK, timeDelay);
					break;
				case 'S': // start
					mapBuilder[r][c] = new MapCell(i,MapCell.CellType.INITIAL, timeDelay);
					startCell = mapBuilder[r][c];
					break;
				case 'E': // exit
					mapBuilder[r][c] = new MapCell(i,MapCell.CellType.EXIT, timeDelay);
					break;
				case 'C': // cross-path (both directions)
					mapBuilder[r][c] = new MapCell(i,MapCell.CellType.CROSS_PATH, timeDelay);
					break;
				case 'H': // horizontal path
					mapBuilder[r][c] = new MapCell(i,MapCell.CellType.HORIZ_PATH, timeDelay);
					break;
				case 'V': // vertical path
					mapBuilder[r][c] = new MapCell(i,MapCell.CellType.VERT_PATH, timeDelay);
					break;
				case 'X': // covid
					mapBuilder[r][c] = new MapCell(i,MapCell.CellType.COVID, timeDelay);
					break;
				case 'D': // donut
					mapBuilder[r][c] = new MapCell(i,MapCell.CellType.DONUT, timeDelay);
					break;
				default:
					throw new InvalidMapException(token);
				}

				// add to the GUI layout
				p.add(mapBuilder[r][c]);
				i++;
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
					mapBuilder[r][c].setNeighbour(mapBuilder[r - 1][c + offset], 0);
					mapBuilder[r][c].setNeighbour(mapBuilder[r][c + 1], 1);
					mapBuilder[r][c].setNeighbour(mapBuilder[r + 1][c + offset], 2);
					mapBuilder[r][c].setNeighbour(mapBuilder[r + 1][c - 1 + offset], 3);
					mapBuilder[r][c].setNeighbour(mapBuilder[r][c - 1], 4);
					mapBuilder[r][c].setNeighbour(mapBuilder[r - 1][c - 1 + offset], 5);
				}
				else if (numNeighbours == 4) {
					// set the neighbors for this square in the builder
					offset = 0;
					mapBuilder[r][c].setNeighbour(mapBuilder[r - 1][c + offset], 0);
					mapBuilder[r][c].setNeighbour(mapBuilder[r][c + 1], 1);
					mapBuilder[r][c].setNeighbour(mapBuilder[r + 1][c + offset], 2);
					mapBuilder[r][c].setNeighbour(mapBuilder[r][c - 1 + offset], 3);
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
	
	
	public void setDelay (int delay) {
		timeDelay = delay;
	}
	
	public int getDelay () {
		return timeDelay;
	}
	

	public MapCell getStart() {
		return startCell;
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
	
}
