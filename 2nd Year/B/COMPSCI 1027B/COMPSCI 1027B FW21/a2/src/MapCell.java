import java.awt.Color;


public class MapCell extends CellComponent {

	private CellType type; // Stores the current type of this cell. This value changes as the cells in the map are marked
	private CellType originalType; // Type initially assigned to this cell
	private boolean isStart; // Is this the starting cell?
	private boolean isTarget; // Is this a jewel cell?
	private MapCell[] neighbors; // Stores the cells neighboring THIS one
	private int numNeighbours = 4;
	private int timeDelay; // Time that the program waits before moving to an adjacent cell
	
	private int identifier;
	
	public static enum CellType {
		BLOCK, INITIAL, TARGET, CROSS_PATH, INITIAL_PROCESSED, TARGET_PROCESSED, INITIAL_POPPED, IN_STACK, OUT_STACK, HORIZ_PATH, VERT_PATH
	};
	
	
	public MapCell (CellType cType, int delay, int identifier) {
		type = cType;
		originalType = cType;
		timeDelay = delay;
		this.isStart = (cType == CellType.INITIAL);
		this.isTarget = (cType == CellType.TARGET);
		
		// set the initial color based on the initial type
		this.setColor(this.type);
		// allocate space for the neighbor array
		this.neighbors = new MapCell[numNeighbours];
		
		this.identifier = identifier;
	}
	
	
	
	
	
	
	/**
	 * Set the neighbor for this cell using the neighbor index.
	 * 
	 * The index for the neighbor indicates which side of the square this new
	 * neighbor is on: 0-3 inclusive.
	 * 
	 * @param neighbor
	 *            The new cell neighbor
	 * @param i
	 *            The index specifying which side this neighbor is on (0-3 inclusive)
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
	 * Set the colour/image of the cell in the visual JFrame based on its type and stack status.
	 * 
	 * 
	 * @param type
	 *            The cell's type from the enum made at the top of this class.
	 *
	 */
	
	
	private void setColor(CellType t) {
		switch (t) {
		case BLOCK:
			this.setBackground(CellColours.BLOCK);
			break;
		case INITIAL:
			this.setBackground(CellColours.INITIAL);
			break;
		case TARGET:
			this.setBackground(CellColours.TARGET);
			break;
		case CROSS_PATH:
			this.setBackground(CellColours.OMNI_SWITCH);
			break;
		case TARGET_PROCESSED:
			this.setBackground(CellColours.TARGET_PROCESSED);
			break;
		case INITIAL_PROCESSED:
			this.setBackground(CellColours.INITIAL_PROCESSED);
			break;
		case INITIAL_POPPED:
			this.setBackground(CellColours.INITIAL_POPPED);
			break;
		case IN_STACK:
			if (originalType == CellType.VERT_PATH)
				setBackground(CellColours.VERT_PATH_PUSHED);
			else if (originalType == CellType.HORIZ_PATH)
				setBackground(CellColours.HORIZ_PATH_PUSHED);
			else if (originalType == CellType.CROSS_PATH)
				setBackground(CellColours.CROSS_PATH_PUSHED);
			else if (originalType == CellType.INITIAL)
				this.setBackground(CellColours.INITIAL_PROCESSED);
			else if (originalType == CellType.TARGET)
				this.setBackground(CellColours.TARGET_PROCESSED);
			else
				setBackground(CellColours.IN_STACK);
			break;
		case OUT_STACK:
			if (originalType == CellType.VERT_PATH)
				setBackground(CellColours.VERT_PATH_POPPED);
			else if (originalType == CellType.HORIZ_PATH)
				setBackground(CellColours.HORIZ_PATH_POPPED);
			else if (originalType == CellType.CROSS_PATH)
				setBackground(CellColours.CROSS_PATH_POPPED);
			else if (originalType == CellType.INITIAL)
				setBackground(CellColours.INITIAL_POPPED);
			else if (originalType == CellType.TARGET)
				setBackground(CellColours.TARGET_POPPED);
			else
				setBackground(CellColours.OUT_STACK);
			break;
		case VERT_PATH:
			this.setBackground(CellColours.VERT_PATH);
			break;
		case HORIZ_PATH:
			this.setBackground(CellColours.HORIZ_PATH);
			break;
		default:
			this.setBackground(CellColours.BLOCK);
			break;
		}
		this.setForeground(Color.BLACK);
	}
	
	
	
	
	
	
	/**
	 * This method checks if the current cell is a block/wall.
	 * 
	 * @return true if this is a cell representing a block/wall, false otherwise.
	 */
	public boolean isBlackHole() {
		return type == CellType.BLOCK;
	}

	/**
	 * This method checks if the current cell is an cross-path.
	 * 
	 * @return true if this cell is a cross-path, false otherwise.
	 */
	public boolean isCrossPath() {
		return originalType == CellType.CROSS_PATH;
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
	
	public boolean isMarkedInStack() {
		return type == CellType.IN_STACK;
	}
	
	public boolean isMarkedOutStack() {
		return type == CellType.OUT_STACK;
	}

	/**
	 * This method checks if the current cell is a vertical path.
	 * 
	 * @return true if this is a vertical path, false otherwise.
	 */
	public boolean isVerticalPath() {
		return originalType == CellType.VERT_PATH;
	}
	
		/**
	 * This method checks if the current cell is a horizontal path.
	 * 
	 * @return true if this is a horizontal path, false otherwise.
	 */
	public boolean isHorizontalPath() {
		return originalType == CellType.HORIZ_PATH;
	}

	/**
	 * This method checks if the current cell is the initial cell.
	 * 
	 * @return true if this is the initial cell, false otherwise.
	 */
	public boolean isStart() {
		return this.isStart;
	}

	/**
	 * This method checks if the current cell is the destination.
	 * 
	 * @return true if this is the destination cell, false otherwise.
	 */
	public boolean isTarget() {
		return this.isTarget;
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
	 * This method marks a jewel cell and updates the cell's colour
	 */
	public void markTarget() {
		this.type = CellType.TARGET_PROCESSED;
		this.setColor(this.type);
		reDraw();
	}

	/**
	 * This method marks the starting cell as the initial cell and updates the cell's
	 * colour
	 */
	public void markInitial() {
		this.type = CellType.INITIAL_PROCESSED;
		this.setColor(this.type);
		reDraw();
	}

	public int getIdentifier() {
		
		return this.identifier;
		
	}
	
	
	
	
}
