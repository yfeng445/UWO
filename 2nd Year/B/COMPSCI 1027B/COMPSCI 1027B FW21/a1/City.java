/**
 * This class creates city object and methods in city
 * @author Yulun Feng
 * @studentID 251113989
 * @date Feb 8,2021
 *
 */
public class City {
	
	private String name;
	private int x;
	private int y;
	private CityMarker marker;
	
	
	/**
	 * Constructor of the class
	 * @param name
	 * @param x
	 * @param y
	 */
	public City(String name, int x, int y) {
		this.name = name;
		this.x = x;
		this.y = y;
		CityMarker marker = null;
		this.marker = marker;
	}
	
	
	/**
	 * return name of the city
	 * @return name 
	 */
	public String getName() {
		return name;
	}
	
	
	/**
	 * return x coordinate of the city
	 * @return x
	 */
	public int getX() {
		return x;
	}
	
	
	/**
	 * return y coordinate of the city
	 * @return y
	 */
	public int getY() {
		return y;
	}
	
	
	/**
	 * return the city marker
	 * @return marker
	 */
	public CityMarker getMarker() {
		return marker;
	}
	
	
	/**
	 * set the city a name
	 * @param newName
	 */
	public void setName(String newName) {
		name = newName;
	}
	
	
	/**
	 * set the city x coordinate
	 * @param newX
	 */
	public void setX(String newX) {
		x = Integer.parseInt(newX);
	}
	
	
	
	/**
	 * set the city y coordinate
	 * @param newY
	 */
	public void setY(String newY) {
		y = Integer.parseInt(newY);
	}
	
	
	
	/**
	 * set the city marker
	 * @param newMarker
	 */
	public void setMarker(CityMarker newMarker) {
		marker = newMarker;
	}
	
	
	/**
	 * return name of the city
	 * @return name
	 */
	public String toString() {
		return name;
	}
}
