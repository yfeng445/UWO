/**
 * This class creates a new building with given width and length
 * @author Yulun Feng
 * @ID 251113989
 * 21 June 2021
 */
public class Building {
	
	private String Symbol;
	private int width;
	private int length;
	
	/**
	 * Constructor of the class
	 * @param Symbol
	 * @param width
	 * @param length
	 */
	public Building(String Symbol, int width,int length) {
		this.Symbol = Symbol;
		this.width = width;
		this.length = length;
	}
	
	/**
	 * return width of the city
	 * @return width
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * return length of the city
	 * @return length
	 */
	public int getLength() {
		return length;
	}
	
	/**
	 * return true if all blocks in this building is valid to add in the city
	 * @param city 
	 * @param x 
	 * @param y 
	 * @return 
	 */
	public boolean isValidPlacement(Building[][] city, int x, int y) {
		for(int i = 0; i<width; i++) {
			for(int j = 0; j<length;j++) {
				if(x<0||y<0) {
					return false;
				}
				if(y+j>city.length-1) {
					return false;
				}
				if(x+i>city[0].length-1){
					return false;
				}
				if(!(city[y+j][x+i]==null)) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * override toString() method and return symbol of the building
	 */
	public String toString() {
		return Symbol;
	}
}
