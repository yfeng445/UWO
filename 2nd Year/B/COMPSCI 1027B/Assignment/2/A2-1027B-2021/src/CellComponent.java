import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class CellComponent extends JComponent  {
	
	
	private int scale = 8;  // Images will have size (scale-1)/scale of the tile size
	private String imgInitial = "initial.jpg";
	private String imgBlock = "block.jpg";
	private String imgOmni = "omni.jpg";
	private String imgOmniPushed = "omni_pushed.jpg";
	private String imgOmniPopped = "omni_popped.jpg";
	private String imgHoriz = "horizontal.jpg";
	private String imgHorizPushed = "horizontal_pushed.jpg";
	private String imgHorizPopped = "horizontal_popped.jpg";
	private String imgVert = "vertical.jpg";
	private String imgVertPushed = "vertical_pushed.jpg";
	private String imgVertPopped = "vertical_popped.jpg";
	private String imgTARGET = "TARGET.jpg";
	private String imgTARGETPushed = "TARGET_pushed.jpg";
	private String imgTARGETPopped = "TARGET_popped.jpg";

	private Polygon cellPoly = new Polygon(); // Square bounds of cell

	private final int nPoints = 4;
	private int[] polyX = new int[nPoints]; // x coordinates of the vertices of the cell
	private int[] polyY = new int[nPoints]; // y coordinates of the vertices of the cell
	
	private Color defaultColor; // Default color for the cell
	
	
	
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
	 * Draws the different types of map cells on the screen
	 * 
	 * @param g
	 *            Graphics object used to draw the cells on the screen
	 */
	@Override
	protected void paintComponent(Graphics g) {
		CellColours palette = new CellColours();
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
		if (c == CellColours.BLOCK) {
			try {
				// Block of buildings
				g2d.setColor(new Color(102, 107, 114));
				g2d.fillPolygon(cellPoly);
				Image img = new ImageIcon(imgBlock).getImage();
				g2d.drawImage(img, 0, 0, width, width, null);
				displayed = true;
			} catch (Exception e) {
				System.out.println("Error opening file "+imgBlock);
			}
		} else if (c == CellColours.TARGET_PROCESSED || c == CellColours.INITIAL_PROCESSED
				|| c == CellColours.INITIAL_POPPED || c == CellColours.TARGET_POPPED) {
			// Initial and destination cells when they have been reached
			displayed = true;
			Image img;
			if (c == CellColours.TARGET_PROCESSED || c == CellColours.INITIAL_PROCESSED)
				gp = new GradientPaint(polyX[nPoints-1], polyY[nPoints-1], palette.initialGradient(CellColours.IN_STACK), polyX[1], polyY[1],
						palette.gradientColor(CellColours.IN_STACK), true);
			else
				gp = new GradientPaint(polyX[nPoints-1], polyY[nPoints-1], palette.initialGradient(c), polyX[1], polyY[1],
						palette.gradientColor(c), true);
			g2d.setPaint(gp);
			g2d.fillPolygon(cellPoly);
			if (c == CellColours.TARGET_PROCESSED) {
				img = new ImageIcon(imgTARGETPushed).getImage();
				//g2d.drawImage(img, width / 5, height / 5, 3 * width / 5, 3 * height / 5, null);
				//g2d.drawImage(img, width / scale, height / scale, (scale-2) * width / scale, (scale-2) * height / scale, null);
				g2d.drawImage(img, 0, 0, width, width, null);
			} else if (c == CellColours.TARGET_POPPED) {
				img = new ImageIcon(imgTARGETPopped).getImage();
				g2d.drawImage(img,  0,  0,  width,  width,  null);
			} else {
				img = new ImageIcon(imgInitial).getImage();
				//g2d.drawImage(img, width / scale, height / scale, (scale-2) * width / scale, (scale-2) * height / scale, null);
				g2d.drawImage(img, 0, 0, width, width, null);
			}
			displayed = true;
		} else if (c == CellColours.TARGET) {
			// Destination cell. The drone has not yet arrived here.
			Image img;
			gp = new GradientPaint(polyX[nPoints-1], polyY[nPoints-1], palette.initialGradient(c), polyX[1], polyY[1],
					palette.gradientColor(c), true);
			g2d.setPaint(gp);
			g2d.fillPolygon(cellPoly);
			img = new ImageIcon(imgTARGET).getImage();
			//g2d.drawImage(img, width / 5, height / 5, 3 * width / 5, 3 * height / 5, null);
			g2d.drawImage(img, 0, 0, width, width, null);

			displayed = true;
		} else if (c == CellColours.HORIZ_PATH || c == CellColours.HORIZ_PATH_PUSHED || c == CellColours.HORIZ_PATH_POPPED) {
			// ODD_SWITCH cells: inital state, when they are visited for the first
			// time,
			// and when the program decides not to include them in the path
			Image img;
			if (c == CellColours.HORIZ_PATH) {
				g2d.setColor(new Color(197, 197, 197));
				g2d.fillPolygon(cellPoly);
				img = new ImageIcon(imgHoriz).getImage();
			} else if (c == CellColours.HORIZ_PATH_PUSHED) {
				g2d.setColor(new Color(162, 211, 218));
				g2d.fillPolygon(cellPoly);
				img = new ImageIcon(imgHorizPushed).getImage();
			} else {
				g2d.setColor(new Color(179, 181, 195));
				g2d.fillPolygon(cellPoly);
				img = new ImageIcon(imgHorizPopped).getImage();
			}
			g2d.drawImage(img, 0, 0, width, width, null);
			displayed = true;
		} else if (c == CellColours.VERT_PATH || c == CellColours.VERT_PATH_PUSHED
				|| c == CellColours.VERT_PATH_POPPED) {
			// High altitude cells: initial state, when they are visited for the
			// first time,
			// and when the program decides not to include them in the path
			Image img;
			if (c == CellColours.VERT_PATH) {
				g2d.setColor(new Color(83, 81, 82));
				g2d.fillPolygon(cellPoly);
				img = new ImageIcon(imgVert).getImage();
			} else if (c == CellColours.VERT_PATH_PUSHED) {
				g2d.setColor(new Color(106, 131, 136));
				g2d.fillPolygon(cellPoly);
				img = new ImageIcon(imgVertPushed).getImage();
			} else {
				g2d.setColor(new Color(111, 111, 121));
				g2d.fillPolygon(cellPoly);
				img = new ImageIcon(imgVertPopped).getImage();
			}
			g2d.drawImage(img, 0, 0, width, width, null);
			displayed = true;
		}
		else if (c == CellColours.OMNI_SWITCH || c == CellColours.CROSS_PATH_PUSHED
				|| c == CellColours.CROSS_PATH_POPPED) {
			// High altitude cells: initial state, when they are visited for the
			// first time,
			// and when the program decides not to include them in the path
			Image img;
			if (c == CellColours.OMNI_SWITCH) {
				g2d.setColor(new Color(83, 81, 82));
				g2d.fillPolygon(cellPoly);
				img = new ImageIcon(imgOmni).getImage();
			} else if (c == CellColours.CROSS_PATH_PUSHED) {
				g2d.setColor(new Color(106, 131, 136));
				g2d.fillPolygon(cellPoly);
				img = new ImageIcon(imgOmniPushed).getImage();
			} else {
				g2d.setColor(new Color(111, 111, 121));
				g2d.fillPolygon(cellPoly);
				img = new ImageIcon(imgOmniPopped).getImage();
			}
			g2d.drawImage(img, 0, 0, width, width, null);
			displayed = true;

		}
		/*
		if (!displayed) {
			gp = new GradientPaint(hexX[nPoints-1], hexY[nPoints-1], palette.initialGradient(c), hexX[1], hexY[1],
					palette.gradientColor(c), true);
			g2d.setPaint(gp);
			g2d.fillPolygon(hexagon);
			if (c == CellColors.IN_STACK)
				drawDrone(g2d, width / 4, width / 2);
		}
		*/
		g2d.setPaint(defaultColor);

	}
	
}
