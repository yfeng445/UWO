
public class Data {
	
	String key;
	int score;
	int level;
	int value;
	
	/**
	 * 
	 * @param key key value
	 * @param Score situation of the game
	 * @param level
	 */
	public Data(String key, int Score, int level) {
		this.key = key;
		this.score = score;
		this.level = level;
	}
	
	/**
	 * 
	 * @return key
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * 
	 * @return score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * 
	 * @return level
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * 
	 * @return value
	 */
	public int getValue() {
		return value;
	}
	
}
