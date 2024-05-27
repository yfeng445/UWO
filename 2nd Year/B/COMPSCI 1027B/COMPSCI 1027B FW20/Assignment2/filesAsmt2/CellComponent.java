
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import java.util.Random;

/**
 * A six sided cell in a hexagonal grid.
 *
 * @author keang, cs1027
 *
 *
 */

public class CellComponent extends JComponent {
	private static final long serialVersionUID = 4865976127980106774L;
	private int scale = 8;  // Images will have size (scale-1)/scale of the tile size
	private String imgInitial = "start.jpg";
	private String imgBlock = "block1.jpg";
	private String imgCross = "cross.jpg";
	private String imgCrossPushed = "crossPushed.jpg";
	private String imgCrossPopped = "crossPopped.jpg";
	private String imgRroad = "rroad.jpg";
	private String imgRroadPushed = "rroadPushed.jpg";
	private String imgRroadPopped = "rroadPopped.jpg";
	private String imgUroad = "uroad.jpg";
	private String imgUroadPushed = "uroadPushed.jpg";
	private String imgUroadPopped = "uroadPopped.jpg";	
	private String imgLroad = "lroad.jpg";
	private String imgLroadPushed = "lroadPushed.jpg";
	private String imgLroadPopped = "lroadPopped.jpg";
	private String imgDroad = "droad.jpg";
	private String imgDroadPushed = "droadPushed.jpg";
	private String imgDroadPopped = "droadPopped.jpg";
	private String customer = "customer.jpg";
	private String customerPushed = "customerPushed.jpg";

	private Polygon hexagon = new Polygon(); // Hexagonal cell

	private final int nPoints = 4;
	private int[] hexX = new int[nPoints]; // x coordinates of the vertices of
											// the hexagon
	private int[] hexY = new int[nPoints]; // y coordinates of the vertices of
											// the hexagon

	private Color defaultColor; // Default color for the hexagonal cells

	/**
	 * This method checks if the hexagon contains the given point.
	 * 
	 * @param p
	 *            point
	 * @return true or false
	 */
	@Override
	public boolean contains(Point p) {
		return hexagon.contains(p);
	}

	/**
	 * This method checks if the hexagon contains the given point.
	 * 
	 * @param x
	 *            x coordinate of the point
	 * @param y
	 *            y coordinate of the point
	 * @return true or false
	 */
	@Override
	public boolean contains(int x, int y) {
		return hexagon.contains(x, y);
	}

	/**
	 * This method sets the size at which the hexagon will be displayed on the
	 * screen
	 * 
	 * @param d
	 *            size of the rectangle
	 */
	@Override
	public void setSize(Dimension d) {
		super.setSize(d);
		calculateCoords();
	}

	/**
	 * This method sets the size at which the hexagon will be displayed on the
	 * screen
	 * 
	 * @param w
	 *            width of the rectangle
	 * @param h
	 *            height of the rectangle
	 */
	@Override
	public void setSize(int w, int h) {
		super.setSize(w, h);
		calculateCoords();
	}

	/**
	 * Bounds the hexagon within a rectangle
	 * 
	 * @param x
	 *            x coordinate of the upper left corner of the enclosing
	 *            rectangle
	 * @param y
	 *            y coordinate of the upper left cornet of the enclosing
	 *            rectangle
	 * @param width
	 *            width of the enclosing rectangle
	 * @param height
	 *            height of the enclosing rectange
	 */
	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		calculateCoords();
	}

	/**
	 * Bounds the hexagon within a rectangle
	 * 
	 * @param r
	 *            enclosing rectangle
	 */
	@Override
	public void setBounds(Rectangle r) {
		super.setBounds(r);
		calculateCoords();
	}

	/**
	 * Processes a mouse click
	 * 
	 * @param e
	 *            MouseEvent object representing mouse's click
	 */
	@Override
	protected void processMouseEvent(MouseEvent e) {
		if (contains(e.getPoint()))
			super.processMouseEvent(e);
	}

	/**
	 * Calculates the coordinates of the hexagon on the screen
	 */
	private void calculateCoords() {
		int w = getWidth() - 1;
		int h = getHeight() - 1;

		int ratioh = (int) (h * .25);
		int ratiow = (int) (w * 0.25);

		agressiveCoords(w, h, ratioh, ratiow);

		hexagon = new Polygon(hexX, hexY, nPoints);
	}

	private void agressiveCoords(int w, int h, int ratioh, int ratiow) {
		hexX[0] = 0;
		hexY[0] = 0;

		hexX[1] = w;
		hexY[1] = 0;

		hexX[2] = w;
		hexY[2] = h;

		hexX[3] = 0;
		hexY[3] = h;
	}

	/**
	 * Draws the different types of map cells on the screen
	 * 
	 * @param g
	 *            Graphics object used to draw the cells on the screen
	 */
	@Override
	protected void paintComponent(Graphics g) {
		CellColors palette = new CellColors();
		Color c = getBackground();
		Graphics2D g2d = (Graphics2D) g;
		defaultColor = (Color) g2d.getPaint();
		boolean displayed = false;
		GradientPaint gp;
		int width = getWidth();
		int height = getHeight();

		/*
		 * Each map cell has a background color and some of them have an icon in
		 * them. Background color and icon are selected below
		 */
		if (c == CellColors.BLOCK) {
			try {
				// Block of buildings
				g2d.setColor(new Color(102, 107, 114));
				g2d.fillPolygon(hexagon);
				Random rand = new Random();
				imgBlock = "block"+rand.nextInt(4)+".jpg";
				Image img = new ImageIcon(imgBlock).getImage();
				g2d.drawImage(img, 0, 0, width, width, null);
				displayed = true;
			} catch (Exception e) {
				System.out.println("Error opening file "+imgBlock);
			}
		} else if (c == CellColors.CUSTOMER_PROCESSED || c == CellColors.INITIAL_PROCESSED
				|| c == CellColors.INITIAL_POPPED) {
			// Initial and destination cells when they have been reached
			displayed = true;
			Image img;
			if (c == CellColors.CUSTOMER_PROCESSED || c == CellColors.INITIAL_PROCESSED)
				gp = new GradientPaint(hexX[nPoints-1], hexY[nPoints-1], palette.initialGradient(CellColors.IN_STACK), hexX[1], hexY[1],
						palette.gradientColor(CellColors.IN_STACK), true);
			else
				gp = new GradientPaint(hexX[nPoints-1], hexY[nPoints-1], palette.initialGradient(c), hexX[1], hexY[1],
						palette.gradientColor(c), true);
			g2d.setPaint(gp);
			g2d.fillPolygon(hexagon);
			if (c == CellColors.CUSTOMER_PROCESSED) {
				img = new ImageIcon(customerPushed).getImage();
				g2d.drawImage(img, width / 5, height / 5, 3 * width / 5, 3 * height / 5, null);
			} else {
				img = new ImageIcon(imgInitial).getImage();
				g2d.drawImage(img, width / scale, height / scale, (scale-2) * width / scale, (scale-2) * height / scale, null);
			}
			displayed = true;
		} else if (c == CellColors.CUSTOMER) {
			// Destination cell. The drone has not yet arrived here.
			Image img;
			gp = new GradientPaint(hexX[nPoints-1], hexY[nPoints-1], palette.initialGradient(c), hexX[1], hexY[1],
					palette.gradientColor(c), true);
			g2d.setPaint(gp);
			g2d.fillPolygon(hexagon);
			img = new ImageIcon(customer).getImage();
			g2d.drawImage(img, width / 5, height / 5, 3 * width / 5, 3 * height / 5, null);

			displayed = true;
		} else if (c == CellColors.RIGHT_ROAD || c == CellColors.RIGHT_ROAD_PUSHED || c == CellColors.RIGHT_ROAD_POPPED) {
			// RIGHT_ROAD cells: inital state, when they are visited for the first
			// time,
			// and when the program decides not to include them in the path
			Image img;
			if (c == CellColors.RIGHT_ROAD) {
				g2d.setColor(new Color(197, 197, 197));
				g2d.fillPolygon(hexagon);
				img = new ImageIcon(imgRroad).getImage();
			} else if (c == CellColors.RIGHT_ROAD_PUSHED) {
				g2d.setColor(new Color(162, 211, 218));
				g2d.fillPolygon(hexagon);
				img = new ImageIcon(imgRroadPushed).getImage();
			} else {
				g2d.setColor(new Color(179, 181, 195));
				g2d.fillPolygon(hexagon);
				img = new ImageIcon(imgRroadPopped).getImage();
			}
			g2d.drawImage(img, 0, 0, width, width, null);
			displayed = true;
		} else if (c == CellColors.LEFT_ROAD || c == CellColors.LEFT_ROAD_PUSHED || c == CellColors.LEFT_ROAD_POPPED) {
			// RIGHT_ROAD cells: inital state, when they are visited for the first
			// time,
			// and when the program decides not to include them in the path
			Image img;
			if (c == CellColors.LEFT_ROAD) {
				g2d.setColor(new Color(197, 197, 197));
				g2d.fillPolygon(hexagon);
				img = new ImageIcon(imgLroad).getImage();
			} else if (c == CellColors.LEFT_ROAD_PUSHED) {
				g2d.setColor(new Color(162, 211, 218));
				g2d.fillPolygon(hexagon);
				img = new ImageIcon(imgLroadPushed).getImage();
			} else {
				g2d.setColor(new Color(179, 181, 195));
				g2d.fillPolygon(hexagon);
				img = new ImageIcon(imgLroadPopped).getImage();
			}
			g2d.drawImage(img, 0, 0, width, width, null);
			displayed = true;			
		} else if (c == CellColors.UP_ROAD || c == CellColors.UP_ROAD_PUSHED
				|| c == CellColors.UP_ROAD_POPPED) {
			// High altitude cells: initial state, when they are visited for the
			// first time,
			// and when the program decides not to include them in the path
			Image img;
			if (c == CellColors.UP_ROAD) {
				g2d.setColor(new Color(83, 81, 82));
				g2d.fillPolygon(hexagon);
				img = new ImageIcon(imgUroad).getImage();
			} else if (c == CellColors.UP_ROAD_PUSHED) {
				g2d.setColor(new Color(106, 131, 136));
				g2d.fillPolygon(hexagon);
				img = new ImageIcon(imgUroadPushed).getImage();
			} else {
				g2d.setColor(new Color(111, 111, 121));
				g2d.fillPolygon(hexagon);
				img = new ImageIcon(imgUroadPopped).getImage();
			}
			g2d.drawImage(img, 0, 0, width, width, null);
			displayed = true;
		} else if (c == CellColors.DOWN_ROAD || c == CellColors.DOWN_ROAD_PUSHED
				|| c == CellColors.DOWN_ROAD_POPPED) {
			// High altitude cells: initial state, when they are visited for the
			// first time,
			// and when the program decides not to include them in the path
			Image img;
			if (c == CellColors.DOWN_ROAD) {
				g2d.setColor(new Color(83, 81, 82));
				g2d.fillPolygon(hexagon);
				img = new ImageIcon(imgDroad).getImage();
			} else if (c == CellColors.DOWN_ROAD_PUSHED) {
				g2d.setColor(new Color(106, 131, 136));
				g2d.fillPolygon(hexagon);
				img = new ImageIcon(imgDroadPushed).getImage();
			} else {
				g2d.setColor(new Color(111, 111, 121));
				g2d.fillPolygon(hexagon);
				img = new ImageIcon(imgDroadPopped).getImage();
			}
			g2d.drawImage(img, 0, 0, width, width, null);
			displayed = true;			
		}
		else if (c == CellColors.CROSSING || c == CellColors.CROSSING_PUSHED
				|| c == CellColors.CROSSING_POPPED) {
			// High altitude cells: initial state, when they are visited for the
			// first time,
			// and when the program decides not to include them in the path
			Image img;
			if (c == CellColors.CROSSING) {
				g2d.setColor(new Color(83, 81, 82));
				g2d.fillPolygon(hexagon);
				img = new ImageIcon(imgCross).getImage();
			} else if (c == CellColors.CROSSING_PUSHED) {
				g2d.setColor(new Color(106, 131, 136));
				g2d.fillPolygon(hexagon);
				img = new ImageIcon(imgCrossPushed).getImage();
			} else {
				g2d.setColor(new Color(111, 111, 121));
				g2d.fillPolygon(hexagon);
				img = new ImageIcon(imgCrossPopped).getImage();
			}
			g2d.drawImage(img, 0, 0, width, width, null);
			displayed = true;

		}
		
		if (!displayed) {
			gp = new GradientPaint(hexX[nPoints-1], hexY[nPoints-1], palette.initialGradient(c), hexX[1], hexY[1],
					palette.gradientColor(c), true);
			g2d.setPaint(gp);
			g2d.fillPolygon(hexagon);
		}
		g2d.setPaint(defaultColor);

	}

	@Override
	protected void paintBorder(Graphics g) {
		// do not paint a border
	}

}
