import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class Map extends JFrame {

	private static final long serialVersionUID = 1;

	private static Container contentPane;
	
	private final static int frame_length = 1100;
	private final static int frame_height = 881;
	private final static int map_length = 1000;
	private final static int map_height = 781;

	private static int ICON_WIDTH = 16;
	private static int ICON_HEIGHT = 37;
	
	private JLayeredPane mainPanel;

	/* Constructor. Creates a panel to represent the map and destroys
	       the panel when its window is closed.                                 */
	/* -------------------------------------------- */
	public Map() {
	/* -------------------------------------------- */
		super("Map of Canada");

		// Initialize panels.
		contentPane = getContentPane();
		mainPanel = new JLayeredPane();

		// Load Canada map.
		ImageIcon icon = new ImageIcon("canada.jpg");
		Image img = icon.getImage();
		Image scaledImage = img.getScaledInstance(map_length,map_height, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(scaledImage);
		JLabel canadaMap = new JLabel(icon);
		canadaMap.setSize(map_length, map_height);
		canadaMap.setLocation(50, 30);
		mainPanel.add(canadaMap);

		addWindowListener(new WindowAdapter( ) {
			public void windowClosing(WindowEvent event) {
				System.exit(0);
			}                
		}); 

		// Main panel properties.
		setSize(frame_length, frame_height);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		contentPane.setBackground(new Color(249, 249, 249));
		contentPane.add(mainPanel);
		contentPane.setFocusable(true);
		contentPane.requestFocusInWindow();
		revalidate();
	}

	/**
	 * Refresh the GUI.
	 */
	public void refresh () {
		revalidate();
		repaint();
	}
	
	/**
	 * Add a city marker to the map.
	 * @param city
	 */
	public void addCity (City city) {
		JLabel marker = new JLabel(city.getMarker());
		marker.setLocation(city.getX() - (ICON_WIDTH/2), city.getY() - ICON_HEIGHT);
		marker.setSize(ICON_WIDTH, ICON_HEIGHT);
		marker.setName(city.getName());
		marker.setToolTipText(city.getName());
		mainPanel.add(marker, 0);
		refresh();
	}

}
