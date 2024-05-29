/**
 * This class inheritance building class and build a new skyscraper
 * @author Yulun Feng
 * @ID 251113989
 * 21 June 2021
 */
public class Skyscraper extends Building{
	
	private int height;
	
	/**
	 * constructor of the class
	 * @param symbol
	 * @param width
	 * @param length
	 * @param height
	 */
	public Skyscraper(String symbol, int width, int length, int height) {
		super(symbol, width, length);
		this.height = height;
	}
	
	/**
	 * override the isValidPlacement method, return true if the building is valid
	 */
	public boolean isValidPlacement(Building[][] building, int x, int y) {
		if(height>10||height==10||super.getLength()*super.getWidth()>height) {
			return false;
		}
		if(super.isValidPlacement(building, x, y)==false) {
			return false;
		}
		return true;
	}
	
	/**
	 * override toString and return height of the building
	 */
	public String toString() {
		return String.valueOf(height);
	}
}
