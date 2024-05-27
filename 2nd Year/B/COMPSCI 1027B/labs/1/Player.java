/**
 * create a player
 * @author Yulun Feng
 * Student ID: 251113989
 * 
 */
public class Player {
	
	private String name;
	private String position;
	private int jerseyNum;
	
	/* This is the constructor so we will be 
	 * initializing the member variables here */
	public Player(String name, String position, int jerseyNum) {
		this.name = name;
		this.position = position;
		this.jerseyNum = jerseyNum;		
	}

	/**
	 * return the name
	 * @return name
	 */
	public String getName() {
		//Get the palyer's name.
		return this.name;
	}
	
	/**
	 * return the position
	 * @return position
	 */
	public String getposition() {
		//Get the palyer's position.
		return this.position;
	}
	
	/**
	 * return jersey number
	 * @return jerseyNum
	 */
	public int getJerseyNum() {
		//Get the palyer's jersey number.
		return this.jerseyNum;
	}
	
	/**
	 * set the name
	 * @param newName
	 */
	public void setName(String newName) {
		this.name = newName;
	}
	
	/**
	 * set the position
	 * @param newPosition
	 */
	public void setPositino(String newPosition) {
		this.position = newPosition;
	}
	
	/**
	 * set the jersey Number
	 * @param newJerseyNum
	 */
	public void setJerseyNum(int newJerseyNum) {
		this.jerseyNum = newJerseyNum;
	}
	
	/**
	 * print the name and jersey number in a specific way
	 */
	public String toString() {
		return this.name + ": #" + this.jerseyNum;
	}
	
	/**
	 * determine whether two player are a same person
	 * @param other
	 * @return true if they are same
	 */
	public boolean equal(Player other) {
		if (this.name.equals(other.name)) {
			return true;
		}
		else {
			return false;
		}
	}
}
