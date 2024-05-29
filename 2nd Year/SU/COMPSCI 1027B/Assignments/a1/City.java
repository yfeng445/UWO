/**
 * This class creates a new building with given width and length
 * @author Yulun Feng
 * @ID 251113989
 * 21 June 2021
 */
public class City {
	
	private int width;
	private int length;
	private Building[][] city;
	
	/**
	 * constructor of the class
	 * @param width
	 * @param length
	 */
	public City(int width, int length) {
		this.width = width;
		this.length = length;
		city = new Building[length][width];
	}
	
	/**
	 * add a structure on the city after determined the building is valid to built or not
	 * @param x
	 * @param y
	 * @param building
	 * @return true if the building is added successfully
	 */
	public boolean addStructure(int x, int y, Building building) {
		if(building.isValidPlacement(city, x, y)) {
			for(int i = 0; i<building.getWidth();i++) {
				for(int j = 0; j<building.getLength();j++) {
					city[y+j][x+i]= building;
				}
			}
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * override toString method and return a 2D array representing the city
	 */
	public String toString() {
		String returnString = "";
		for(int j = 0; j<length;j++) {
			for(int i = 0;i<width;i++) {
				if(city[j][i]==null) {
					returnString = returnString+".  ";
				}
				else {
					returnString = returnString+city[j][i].toString()+"  ";
				}			
			}
			returnString = returnString+"\n";
		}
		return returnString.substring(0, returnString.length()-1);
	}
	
	}
