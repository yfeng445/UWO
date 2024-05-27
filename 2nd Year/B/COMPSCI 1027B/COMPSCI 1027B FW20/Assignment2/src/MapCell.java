import java.awt.Color;

/**
 * This class represents a cell used to make up a Map object.
 * Each cell has a type and is drawn in a different colour.
 * Cells know about their neighbors (if they are set using setNeighbor method).
 * The neighbors of a cell are accessed by an index 0, 1, 2, ... 
 * The neighbours of a cell are indexed as follows: the 0 index is for the top
 * neighbour. Indexes for the other neighbours progress incrementally clockwise 
 * from 0 to 3. Eg.
 *        0 
 *       ---
 *    3 |   | 1
 *       ---
 *        2 
 * @author CS1027
 *
 */
public class MapCell extends CellComponent {
	private static final long serialVersionUID = 4865976127980106774L;

	// enum to represent available hexagon cell types
	public static enum CellType {
		BLOCK, INITIAL, CUSTOMER, CROSSING, INITIAL_PROCESSED, CUSTOMER_PROCESSED, INITIAL_POPPED, IN_STACK, OUT_STACK,  RIGHT_ROAD, UP_ROAD, LEFT_ROAD, DOWN_ROAD
	};

	private CellType type; // Stores the current type of this cell. This value
							// changes as the cells in the map are marked
	private CellType originalType; // Type initially assigned to this cell
	private boolean isTheStart; // Is this the initial cell with the power station?
	private boolean isEnd; // Is this the customer cell?
	private MapCell[] neighbors; // Stores the cells neighbouring THIS one
	private int timeDelay; // Time that the program waits before moving to an adjacent cell
	private int numNeighbours = 4;

	/**
	 * Create a hexagonal cell of the specified type
	 * 
	 * @param t
	 *            the CellType to create
	 * @param delay
	 *            time that the program wairts before moving to next cell
	 */
	public MapCell(CellType t, int delay) {
		timeDelay = delay;
		this.type = t;
		this.originalType = t;
		this.isTheStart = (t == CellType.INITIAL);
		this.isEnd = (t == CellType.CUSTOMER);

		// set the initial color based on the initial type
		this.setColor(this.type);
		// allocate space for the neighbor array
		this.neighbors = new MapCell[numNeighbours];
	}

	/**
	 * Set the neighbor for this cell using the neighbor index.
	 * 
	 * The index for the neighbor indicates which side of the cell this new
	 * neighbor is on: 0-3 inclusive.
	 * 
	 * @param neighbor
	 *            The new cell neighbor
	 * @param i
	 *            The index specifying which side this neighbor is on (0-3
	 *            inclusive)
	 * @throws InvalidNeighbourIndexException
	 *             When an index is specified that is not 0-3.
	 */
	public void setNeighbour(MapCell neighbor, int i) throws InvalidNeighbourIndexException {
		if (0 <= i && i < numNeighbours)
			this.neighbors[i] = neighbor;
		else
			throw new InvalidNeighbourIndexException(i);
	}

	/**
	 * Returns the neighbor for this cell using the neighbor index.
	 * 
	 * The index for the neighbor indicates in which side of the cell the
	 * neighbor is: 0-3.
	 * 
	 * @param i
	 *            The index of the neighbor
	 * @return The cell that is on the i-th side of the current cell, or null if
	 *         no neighbor
	 * @throws InvalidNeighbourIndexException
	 *             When an index is specified that is not 0-3.
	 */
	public MapCell getNeighbour(int i) throws InvalidNeighbourIndexException {
		if (0 <= i && i < numNeighbours)
			return this.neighbors[i];
		else
			throw new InvalidNeighbourIndexException(i);
	}

	/**
	 * This method checks if the current cell is a blockof buildings.
	 * 
	 * @return true if this is a cell representing a block of buildings, false otherwise.
	 */
	public boolean isBlock() {
		return type == CellType.BLOCK;
	}

	/**
	 * This method checks if the current cell is an omni switch.
	 * 
	 * @return true if this cell is an omni switch, false otherwise.
	 */
	public boolean isRoadCrossing() {
		return originalType == CellType.CROSSING;
	}

	/**
	 * This method checks if the current cell has been marked as in stack or out
	 * of stack.
	 * 
	 * @return true if this cell has been marked as in stack or out of stack,
	 *         false otherwise.
	 */

	public boolean isMarked() {
		return (type == CellType.IN_STACK) || (type == CellType.OUT_STACK);
	}

	/**
	 * This method checks if the current cell is a road going up.
	 * 
	 * @return true if this is a road going up, false otherwise.
	 */
	public boolean isUpRoad() {
		return originalType == CellType.UP_ROAD;
	}
	
		/**
	 * This method checks if the current cell is a road going to the right.
	 * 
	 * @return true if this is a road going to the right, false otherwise.
	 */
	public boolean isRightRoad() {
		return originalType == CellType.RIGHT_ROAD;
	}
	
		/**
	 * This method checks if the current cell is a road going down.
	 * 
	 * @return true if this is a road going down, false otherwise.
	 */
	public boolean isDownRoad() {
		return originalType == CellType.DOWN_ROAD;
	}
	
		/**
	 * This method checks if the current cell is a road going left.
	 * 
	 * @return true if this is a road going left, false otherwise.
	 */
	public boolean isLeftRoad() {
		return originalType == CellType.LEFT_ROAD;
	}	

	/**
	 * This method checks if the current cell is the initial cell.
	 * 
	 * @return true if this is the initial cell, false otherwise.
	 */
	public boolean isStart() {
		return this.isTheStart;
	}

	/**
	 * This method checks if the current cell is the destination.
	 * 
	 * @return true if this is the destination cell, false otherwise.
	 */
	public boolean isDestination() {
		return this.isEnd;
	}

	/**
	 * This method re-draws the current hexagonal cell
	 */
	private void reDraw() {
		try {
			Thread.sleep(timeDelay);
		} catch (Exception e) {
			System.err.println("Error while issuing time delay\n" + e.getMessage());
		}
		super.repaint();
	}

	/**
	 * This method marks the cell as in-stack and updates the cell's colour
	 */
	public void markInStack() {
		type = CellType.IN_STACK;
		setColor(type);
		reDraw();
	}

	/**
	 * This method marks the cell as popped and updates the cell's colour
	 */
	public void markOutStack() {
		type = CellType.OUT_STACK;
		setColor(this.type);
		reDraw();
	}

	/**
	 * This method marks the destination cell and updates the cell's colour
	 */
	public void markCustomer() {
		this.type = CellType.CUSTOMER_PROCESSED;
		this.setColor(this.type);
		reDraw();
	}

	/**
	 * This method marks the power station cell as the initial cell and updates the cell's
	 * colour
	 */
	public void markInitial() {
		this.type = CellType.INITIAL_PROCESSED;
		this.setColor(this.type);
		reDraw();
	}

	/**
	 * Helper method to set the current cell color based on the type of cell.
	 * 
	 * @param t
	 *            The type of the cell; used to set the color
	 */
	private void setColor(CellType t) {
		switch (t) {
		case BLOCK:
			this.setBackground(CellColors.BLOCK);
			break;
		case INITIAL:
			this.setBackground(CellColors.INITIAL);
			break;
		case CUSTOMER:
			this.setBackground(CellColors.CUSTOMER);
			break;
		case CROSSING:
			this.setBackground(CellColors.CROSSING);
			break;
		case CUSTOMER_PROCESSED:
			this.setBackground(CellColors.CUSTOMER_PROCESSED);
			break;
		case INITIAL_PROCESSED:
			this.setBackground(CellColors.INITIAL_PROCESSED);
			break;
		case INITIAL_POPPED:
			this.setBackground(CellColors.INITIAL_POPPED);
			break;
		case IN_STACK:
			if (originalType == CellType.UP_ROAD)
				setBackground(CellColors.UP_ROAD_PUSHED);
			else if (originalType == CellType.RIGHT_ROAD)
				setBackground(CellColors.RIGHT_ROAD_PUSHED);
			else if (originalType == CellType.LEFT_ROAD)
				setBackground(CellColors.LEFT_ROAD_PUSHED);
			else if (originalType == CellType.DOWN_ROAD)
				setBackground(CellColors.DOWN_ROAD_PUSHED);			
			else if (originalType == CellType.CROSSING)
				setBackground(CellColors.CROSSING_PUSHED);
			else if (originalType == CellType.INITIAL)
				this.setBackground(CellColors.INITIAL_PROCESSED);
			else if (originalType == CellType.CUSTOMER)
				this.setBackground(CellColors.CUSTOMER_PROCESSED);
			else
				setBackground(CellColors.IN_STACK);
			break;
		case OUT_STACK:
			if (originalType == CellType.UP_ROAD)
				setBackground(CellColors.UP_ROAD_POPPED);
			else if (originalType == CellType.RIGHT_ROAD)
				setBackground(CellColors.RIGHT_ROAD_POPPED);
			else if (originalType == CellType.LEFT_ROAD)
				setBackground(CellColors.LEFT_ROAD_POPPED);
			else if (originalType == CellType.DOWN_ROAD)
				setBackground(CellColors.DOWN_ROAD_POPPED);			
			else if (originalType == CellType.CROSSING)
				setBackground(CellColors.CROSSING_POPPED);
			else if (originalType == CellType.INITIAL)
				setBackground(CellColors.INITIAL_POPPED);
			else
				setBackground(CellColors.OUT_STACK);
			break;
		case UP_ROAD:
			this.setBackground(CellColors.UP_ROAD);
			break;
		case RIGHT_ROAD:
			this.setBackground(CellColors.RIGHT_ROAD);
			break;
		case DOWN_ROAD:
			this.setBackground(CellColors.DOWN_ROAD);
			break;
		case LEFT_ROAD:
			this.setBackground(CellColors.LEFT_ROAD);
			break;			
		default:
			this.setBackground(CellColors.BLOCK);
			break;
		}
		this.setForeground(Color.BLACK);
	}

	/**
	 * Gets a String representation of a cell.
	 * 
	 * @return String representation of the cell.
	 */
	public String toString() {
		if (type == CellType.BLOCK)
			return "Block of buildings";
		else if (type == CellType.INITIAL || type == CellType.INITIAL_PROCESSED || type == CellType.INITIAL_POPPED
				|| originalType == CellType.INITIAL)
			return "Starting location";
		else if (type == CellType.CUSTOMER || type == CellType.CUSTOMER_PROCESSED || originalType == CellType.CUSTOMER)
			return "Destination";
		else if (type == CellType.CROSSING || originalType == CellType.CROSSING)
			return "Crossing";
		else if (type == CellType.UP_ROAD || originalType == CellType.UP_ROAD)
			return "Up road";
		else if (type == CellType.RIGHT_ROAD || originalType == CellType.RIGHT_ROAD)
			return "Right road";
		else if (type == CellType.DOWN_ROAD || originalType == CellType.UP_ROAD)
			return "Down road";
		else if (type == CellType.LEFT_ROAD || originalType == CellType.RIGHT_ROAD)
			return "Left road";		
		else
			return "";
	}
}