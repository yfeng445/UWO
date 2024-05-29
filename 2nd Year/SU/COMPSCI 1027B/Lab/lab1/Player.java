
public class Player {
	
	private String name;
	private String position;
	private int jerseyNum;
	
	
	/*
	 This is the constructor so we will be
	 initializing the member variables here
	 */
	public Player(String name, String position, int jerseyNum) {
		this.name = name;
		this.position = position;
		this.jerseyNum = jerseyNum;
	}
	
	public String getName() {
		//Get the player's name.
		return name;
	}
	
	public String getPosition() {
		return position;
	}
	
	public int getJerseyNum() {
		return jerseyNum;
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	
	public void setPosition(String newPosition) {
		this.position = newPosition;
	}
	
	public void setJerseyNum(int newJerseyNum) {
		this.jerseyNum=  newJerseyNum;
	}
	
	public String toString() {
		return this.name+": #"+this.jerseyNum;
	}
	
	public boolean equals(Player other) {
		if(this.name.equals(other.name)&&this.jerseyNum==other.jerseyNum) {
			return true;
		}else {
			return false;
		}
	}
}
