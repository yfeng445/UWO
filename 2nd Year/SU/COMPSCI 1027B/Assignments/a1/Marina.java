/**
 * This class inheritance building class and build a new marina 
 * @author Yulun Feng
 * @ID 251113989
 * 21 June 2021
 */
public class Marina extends Building{
	
	/**
	 * constructor fo the class
	 * @param symbol
	 * @param width
	 * @param height
	 */
	public Marina(String symbol,int width, int height) {
		super(symbol, width,height);
	}
	
	/**
	 * override isValidPlacement method
	 */
	public boolean isValidPlacement(Building[][] city, int x, int y) {
		if(super.isValidPlacement(city, x, y)==false){
			return false;
		}
		else{
			for(int i = 0; i<super.getWidth(); i++) {
				for(int j = 0; j<super.getLength();j++) {
					if(x==0||y==0) {
						return true;
					}
					if(x+i+1== city[0].length||y+j+1==city.length) {
						return true;
					}
				}
			}
			return false;
		}	
	}

}
