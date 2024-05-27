
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;

/**
 * This layout manager is based on java.awt.GridLayout
 *
 * The <code>GridLayout</code> class is a layout manager that lays out a
 * container's components in a hexagonal grid. The container is divided into
 * equal-sized hexagons, and one component is placed in each hexagon.
 *
 * @author keang
 *
 */
public class CellLayout implements LayoutManager, java.io.Serializable {
	private static final long serialVersionUID = -858342723067286796L;
	private int numNeighbours = 4;  // Number of neighbouring cells of each cell
	/**
	 * This is the gap (in pixels) which specifies the space between components.
	 * They can be changed at any time. This should be a non-negative integer.
	 *
	 * @serial
	 */
	int cgap;
	/**
	 * This is the number of rows specified for the grid. The number of rows can
	 * be changed at any time. This should be a non negative integer, where '0'
	 * means 'any number' meaning that the number of Rows in that dimension
	 * depends on the other dimension.
	 *
	 * @serial
	 * @see #getRows()
	 * @see #setRows(int)
	 */
	int rows;
	/**
	 * This is the number of columns specified for the grid. The number of
	 * columns can be changed at any time. This should be a non negative
	 * integer, where '0' means 'any number' meaning that the number of Columns
	 * in that dimension depends on the other dimension.
	 *
	 * @serial
	 * @see #getColumns()
	 * @see #setColumns(int)
	 */
	int cols;

	/**
	 * Creates a grid layout with a default of one column per component, in a
	 * single row.
	 * 
	 * @since JDK1.1
	 */
	public CellLayout() {
		this(1, 0, 0);
	}

	/**
	 * Creates a grid layout with the specified number of rows and columns. All
	 * components in the layout are given equal size.
	 * <p>
	 * One, but not both, of <code>rows</code> and <code>cols</code> can be
	 * zero, which means that any number of objects can be placed in a row or in
	 * a column.
	 * 
	 * @param r
	 *            the rows, with the value zero meaning any number of rows.
	 * @param c
	 *            the columns, with the value zero meaning any number of
	 *            columns.
	 */
	public CellLayout(int r, int c) {
		this(r, c, 0);
	}

	/**
	 * Creates a grid layout with the specified number of rows and columns. All
	 * components in the layout are given equal size.
	 * <p>
	 * In addition, the gap between components is set to the specified value.
	 * <p>
	 * One, but not both, of <code>rows</code> and <code>cols</code> can be
	 * zero, which means that any number of objects can be placed in a row or in
	 * a column.
	 * <p>
	 * All <code>GridLayout</code> constructors defer to this one.
	 * 
	 * @param r
	 *            the rows, with the value zero meaning any number of rows
	 * @param c
	 *            the columns, with the value zero meaning any number of columns
	 * @param hgap
	 *            the gap around the component
	 * @exception IllegalArgumentException
	 *                if the value of both <code>rows</code> and
	 *                <code>cols</code> is set to zero
	 */
	public CellLayout(int r, int c, int hgap) {
		if ((r == 0) && (c == 0)) {
			throw new IllegalArgumentException("rows and cols cannot both be zero");
		}
		this.rows = r;
		this.cols = c;
		this.cgap = hgap;
	}

	/**
	 * Gets the number of rows in this layout.
	 * 
	 * @return the number of rows in this layout
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * Sets the number of rows in this layout to the specified value.
	 * 
	 * @param r
	 *            the number of rows in this layout
	 * @exception IllegalArgumentException
	 *                if the value of both <code>rows</code> and
	 *                <code>cols</code> is set to zero
	 */
	public void setRows(int r) {
		if ((r == 0) && (this.cols == 0)) {
			throw new IllegalArgumentException("rows and cols cannot both be zero");
		}
		this.rows = r;
	}

	/**
	 * Gets the number of columns in this layout.
	 * 
	 * @return the number of columns in this layout
	 */
	public int getColumns() {
		return cols;
	}

	/**
	 * Sets the number of columns in this layout to the specified value. Setting
	 * the number of columns has no affect on the layout if the number of rows
	 * specified by a constructor or by the <tt>setRows</tt> method is non-zero.
	 * In that case, the number of columns displayed in the layout is determined
	 * by the total number of components and the number of rows specified.
	 * 
	 * @param c
	 *            the number of columns in this layout
	 * @exception IllegalArgumentException
	 *                if the value of both <code>rows</code> and
	 *                <code>cols</code> is set to zero
	 */
	public void setColumns(int c) {
		if ((c == 0) && (this.rows == 0)) {
			throw new IllegalArgumentException("rows and cols cannot both be zero");
		}
		this.cols = c;
	}

	/**
	 * Gets the gap between components.
	 * 
	 * @return the gap between components
	 */
	public int getGap() {
		return cgap;
	}

	/**
	 * Sets the gap between components to the specified value.
	 * 
	 * @param gap
	 *            the gap between components
	 */
	public void setGap(int gap) {
		this.cgap = gap;
	}

	/**
	 * Adds the specified component with the specified name to the layout.
	 * 
	 * @param name
	 *            the name of the component
	 * @param comp
	 *            the component to be added
	 */
	public void addLayoutComponent(String name, Component comp) {
		// do nothing
	}

	/**
	 * Removes the specified component from the layout.
	 * 
	 * @param comp
	 *            the component to be removed
	 */
	public void removeLayoutComponent(Component comp) {
		// do nothing
	}

	/**
	 * Determines the preferred size of the container argument using this grid
	 * layout.
	 * <p>
	 * The preferred width of a grid layout is the largest preferred width of
	 * all of the components in the container times the number of columns, plus
	 * the horizontal padding times the number of columns minus one, plus the
	 * left and right insets of the target container, plus the width of half a
	 * component if there is more than one row.
	 * <p>
	 * The preferred height of a grid layout is the largest preferred height of
	 * all of the components in the container plus three quarters of the largest
	 * minimum height of all of the components in the container times the number
	 * of rows greater than one, plus the vertical padding times the number of
	 * rows minus one, plus the top and bottom insets of the target container.
	 *
	 * @param parent
	 *            the container in which to do the layout
	 * @return the preferred dimensions to lay out the subcomponents of the
	 *         specified container
	 * @see java.awt.GridLayout#minimumLayoutSize
	 * @see java.awt.Container#getPreferredSize()
	 */
	public Dimension preferredLayoutSize(Container parent) {
		synchronized (parent.getTreeLock()) {
			Insets insets = parent.getInsets();
			int ncomponents = parent.getComponentCount();
			int nrows = rows;
			int ncols = cols;

			if (nrows > 0) {
				ncols = (ncomponents + nrows - 1) / nrows;
			} else {
				nrows = (ncomponents + ncols - 1) / ncols;
			}

			int w = 0;
			int h = 0;
			for (int i = 0; i < ncomponents; i++) {
				Component comp = parent.getComponent(i);
				Dimension d = comp.getPreferredSize();
				if (w < d.width) {
					w = d.width;
				}
				if (h < d.height) {
					h = d.height;
				}
			}

			int dx = insets.left + insets.right + ncols * w + (ncols - 1) * cgap;
			int dy = insets.top + insets.bottom + nrows * h + (nrows - 1) * cgap;

			if (nrows > 1) {
				dx = dx + (int) (w * 0.5f);

				dy /= nrows;
				dy = dy + (int) (dy * (nrows - 1) * 0.75f);
			}

			return new Dimension(dx, dy);
		}
	}

	/**
	 * Determines the minimum size of the container argument using this grid
	 * layout.
	 * <p>
	 * The minimum width of a grid layout is the largest minimum width of all of
	 * the components in the container times the number of columns, plus the
	 * horizontal padding times the number of columns minus one, plus the left
	 * and right insets of the target container, plus the width of half a
	 * component if there is more than one row.
	 * <p>
	 * The minimum height of a grid layout is the largest minimum height of all
	 * of the components in the container plus three quarters of the largest
	 * minimum height of all of the components in the container times the number
	 * of rows greater than one, plus the vertical padding times the number of
	 * rows minus one, plus the top and bottom insets of the target container.
	 *
	 * @param parent
	 *            the container in which to do the layout
	 * @return the minimum dimensions needed to lay out the subcomponents of the
	 *         specified container
	 * @see java.awt.GridLayout#preferredLayoutSize
	 * @see java.awt.Container#doLayout
	 */
	public Dimension minimumLayoutSize(Container parent) {
		synchronized (parent.getTreeLock()) {
			Insets insets = parent.getInsets();
			int ncomponents = parent.getComponentCount();
			int nrows = rows;
			int ncols = cols;

			if (nrows > 0) {
				ncols = (ncomponents + nrows - 1) / nrows;
			} else {
				nrows = (ncomponents + ncols - 1) / ncols;
			}
			int w = 0;
			int h = 0;
			for (int i = 0; i < ncomponents; i++) {
				Component comp = parent.getComponent(i);
				Dimension d = comp.getMinimumSize();
				if (w < d.width) {
					w = d.width;
				}
				if (h < d.height) {
					h = d.height;
				}
			}

			int dx = insets.left + insets.right + ncols * w + (ncols - 1) * cgap;
			int dy = insets.top + insets.bottom + nrows * h + (nrows - 1) * cgap;

			if (nrows > 1) {
				dx = dx + (int) (w * 0.5f);

				dy /= nrows;
				dy = dy + (int) (dy * (nrows - 1) * 0.75f);
			}

			return new Dimension(dx, dy);
		}
	}

	/**
	 * Lays out the specified container using this layout.
	 * <p>
	 * This method reshapes the components in the specified target container in
	 * order to satisfy the constraints of the <code>GridLayout</code> object.
	 * <p>
	 * The grid layout manager determines the size of individual components by
	 * dividing the free space in the container into equal-sized portions
	 * according to the number of rows and columns in the layout. The
	 * container's free space equals the container's size minus any insets and
	 * any specified horizontal or vertical gap. All components in a grid layout
	 * are given the same size.
	 *
	 * @param parent
	 *            the container in which to do the layout
	 * @see java.awt.Container
	 * @see java.awt.Container#doLayout
	 */
	public void layoutContainer(Container parent) {
		synchronized (parent.getTreeLock()) {
			Insets insets = parent.getInsets();
			int ncomponents = parent.getComponentCount();
			int nrows = rows;
			int ncols = cols;

			if (ncomponents == 0) {
				return;
			}
			if (nrows > 0) {
				ncols = (ncomponents + nrows - 1) / nrows;
			} else {
				nrows = (ncomponents + ncols - 1) / ncols;
			}

			int w = parent.getWidth() - (insets.left + insets.right);
			int h = parent.getHeight() - (insets.top + insets.bottom);

			w = (int) ((w - (ncols - 1) * cgap) / (ncols + (nrows > 1 ? 0.5f : 0.0f)));

			float effectiveRows;
			int xoffset, yoffset;
			
			if (numNeighbours == 4) effectiveRows = nrows; 
			else effectiveRows = 1 + ((nrows - 1) * 0.75f);
			h = (int) ((h - (nrows - 1) * cgap) / effectiveRows);

			if (numNeighbours == 4) {
				xoffset = 0; //(w + cgap) / 2;
				yoffset = h; //(int) (h * 0.75f);
			}
			else {
				xoffset = (w + cgap) / 2;
				yoffset = (int) (h * 0.75f);
			}
			
			boolean staggeredRow = false;

			for (int r = 0, y = insets.top; r < nrows; r++, y += yoffset + cgap) {
				int offset = 0;

				if (staggeredRow)
					offset = xoffset;

				for (int c = 0, x = insets.left; c < ncols; c++, x += w + cgap) {
					int i = r * ncols + c;

					if (i < ncomponents) {
						parent.getComponent(i).setBounds(x + offset, y, w, h);
					}

				}

				staggeredRow = !staggeredRow;
			}
		}
	}

	/**
	 * Returns the string representation of this grid layout's values.
	 * 
	 * @return a string representation of this grid layout
	 */
	public String toString() {
		return getClass().getName() + "[gap=" + cgap + ",rows=" + rows + ",cols=" + cols + "]";
	}

}