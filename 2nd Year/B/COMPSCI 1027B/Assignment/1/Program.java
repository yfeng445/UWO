/**
 * 
 * @author Yulun Feng
 * @studentID 251113989
 * @date Feb 8,2021
 *
 */
public class Program {
	
	private int cityCount = 0;
	private City[] cityArray;
	private CompressedArray array;

	
	/**
	 * Constructor of the class
	 * @param filename file to open
	 * @param showMap whether to show the map GUI
	 */
	public Program(String filename, boolean showMap) {

		cityArray = new City[3];
		MyFileReader reader = new MyFileReader(filename); 
		reader.readString(); //pass the first line of each files
		int position = 0;
		
		while(!(reader.endOfFile())) { //go through the whole file
			
			if(position == cityArray.length-1) {	// extend the array if needed
				extendCapacity();
			}
			
			// put data into each city object in the cityArray
			String cityName = reader.readString();
			int x = reader.readInt();
			int y = reader.readInt();
			cityArray[position] = new City(cityName,x,y);
			position+=1;
			cityCount+=1;
			}
		
		if(showMap == true) {
			Map map = new Map();
			for(int i = 0;i<cityCount;i++) {
				map.addCity(cityArray[i]);
			}
		}

	}
	
	
	
	/**
	 * extend the capacity of the array if needed
	 */
	private void extendCapacity() {
		City[] newArray = new City[cityArray.length+3];
		for(int i = 0;i<cityArray.length;i++) {
			newArray[i] = cityArray[i];
		}
		cityArray = newArray;
	}
	
	
	
	/**
	 * return the cityArray
	 * @return cityArray
	 */
	public City[] getCityList() {
		return cityArray;
	}
	
	
	
	/**
	 * calculate distance between two cities
	 * @param a city a
	 * @param b city b
	 * @return dist distance
	 */
	public double distBetweenCities(City a, City b) {
		double dist = Math.sqrt((a.getX()-b.getX())*(a.getX()-b.getX())+(a.getY()-b.getY())*(a.getY()-b.getY()));
		return dist;
	}
	
	
	
	/**
	 * put distance into array and then put into a compressedArray
	 */
	public void compareDistances() {
		double[][] twoD_array = new double[cityCount][cityCount];
		for(int i = 0;i<cityCount;i++) {
			for(int j = 0;j<cityCount;j++) {
				twoD_array[i][j] = distBetweenCities(cityArray[i],cityArray[j]);
			}
		}
		this.array = new CompressedArray(twoD_array);
	}
	
	
	/**
	 * return compressedArray containing city information
	 * @return array
	 */
	public CompressedArray getArray() {	
		return array;
	}
}
