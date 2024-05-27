import java.awt.Color;
/**
* This class contains all the colors used by the map and map cells.
* <p>
* @author CS1027
*/
public class CellColors {
	// Color definitions
	public static final Color BLOCK = Color.BLACK;
	public static final Color INITIAL = new Color(250, 250, 0);
	public static final Color CUSTOMER = Color.BLUE;
	public static final Color UNVISITED = new Color(100, 255, 100);
	public static final Color IN_QUEUE = Color.CYAN;
	public static final Color CUSTOMER_PROCESSED = new Color(0, 190, 190);
	public static final Color CUSTOMER_OUT_LIST = new Color(55, 255, 255);
	public static final Color INITIAL_PROCESSED = new Color(200, 255, 255);
	public static final Color INITIAL_OUT_LIST = new Color(50, 255, 255);
	public static final Color OUT_QUEUE = Color.LIGHT_GRAY;
	public static final Color RIGHT_ROAD = Color.RED;
	public static final Color UP_ROAD = Color.PINK;
	public static final Color RIGHT_ROAD_IN_LIST = Color.RED.darker();
	public static final Color UP_ROAD_IN_LIST = Color.PINK.darker();
	public static final Color RIGHT_ROAD_OUT_LIST = Color.RED.brighter();
	public static final Color UP_ROAD_OUT_LIST = Color.PINK.brighter();
	public static final Color LEFT_ROAD = new Color(60,60,60);
	public static final Color DOWN_ROAD = new Color(70,70,70);
	public static final Color LEFT_ROAD_IN_LIST = new Color(80,80,80);
	public static final Color DOWN_ROAD_IN_LIST = new Color(90,90,90);
	public static final Color LEFT_ROAD_OUT_LIST = new Color(110,110,110);
	public static final Color DOWN_ROAD_OUT_LIST = new Color(130,130,130);
	public static final Color CROSSING_IN_LIST = Color.GREEN.darker();
	public static final Color CROSSING_OUT_LIST = Color.GREEN.brighter();
	public static final Color CROSSING = Color.GREEN;	
	public static final Color UP_ROAD_PATH = new Color(255, 123, 100);
	public static final Color DOWN_ROAD_PATH = new Color(255, 122, 100);
	public static final Color RIGHT_ROAD_PATH = new Color(255, 124, 100);
	public static final Color LEFT_ROAD_PATH = new Color(255, 125, 100);
	public static final Color CROSSING_PATH = new Color(255, 124, 99);
	public static final Color INITIAL_PATH = new Color(255, 100, 98);
	public static final Color CUSTOMER_PATH = new Color(255, 100, 97);
	public static final Color PATH = new Color(255, 100, 96);
	
	/**
	* Selects the final color in a gradient texture.
	*
	* @param c The cell name of the color
	* @return The color for the gradient
	*/
	public Color gradientColor(Color c) {
		if (c == BLOCK)
			return Color.DARK_GRAY.brighter();
		else if (c == IN_QUEUE)
			return c.darker().darker();
		else if (c == CUSTOMER_PROCESSED)
			return new Color(0, 0, 180);
		else if (c == INITIAL_PROCESSED)
			return Color.CYAN.darker();
		else if (c == INITIAL_OUT_LIST)
			return Color.GRAY;
		return c.darker();
	}

		/**
	* Selects the initial color in a gradient texture.
	*
	* @param c The cell name of the color
	* @return The color for the gradient
	*/
	public Color initialGradient(Color c) {
		if (c == CUSTOMER_PROCESSED)
			return new Color(150, 250, 250);
		else if (c == INITIAL_PROCESSED)
			return new Color(250, 250, 80);
		else if (c == INITIAL_OUT_LIST)
			return new Color(250, 250, 80);
		else if (c == CUSTOMER)
			return new Color(110, 110, 250);
		else
			return c;
	}
}
