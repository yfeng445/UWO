/**
 * This class create a compressedArray that stores the distance between cities
 * @author Yulun Feng
 * @studentID 251113989
 * @date Feb 8,2021
 *
 */
public class CompressedArray {

	private int origArraySize;
	private double[] array;
	private int pos = 0;
	
	
	/**
	 * Constructor of the class, convert origArray into a compressedArray
	 * @param origArray
	 */
	public CompressedArray(double[][] origArray){
		origArraySize = origArray[0].length;
		array = new double[origArray[0].length*(origArray[0].length-1)/2];
		//putting elements into the new array
		for(int j = 1;j<origArray[0].length;j++) {
			for(int i = 0; i<j; i++) {
				array[pos] = origArray[j][i];
				pos = pos+1;
			}
		}
	}
	
	
	/**
	 * return array length
	 * @return array.length
	 */
	public int getLength() {
		return array.length;
	}
	
	
	
	/**
	 * return an element at the given position
	 * @param pos position of the element
	 * @return array[pos] the element
	 */
	public double getElement(int pos) {
		return array[pos];
	}
	
	
	/**
	 * determine whether two CompressedArray are equal or not
	 * @param another another array
	 * @return equal the result
	 */
	public boolean equals(CompressedArray another) {
		boolean equal = true;
		if(!(origArraySize == another.origArraySize)) {
			equal = false;
			return equal;
		}
		for(int i = 0;i<array.length;i++) {
			if(!(array[i] == another.array[i])) {
				equal = false;
			}
		}
		return equal;
	}
	
	
	
	/**
	 * return the CompressedArray in under-left triangle form
	 */
	public String toString(){
		String returnString = "\n";
		int con = 0;
		int pos = 0;
		for(int i = 0; i<origArraySize-1;i++) {
			for(int j = 0; j<=con;j++) {
				returnString = returnString+String.format("%8.2f", array[pos+j]);
			}
			con+=1;
			pos=pos+con;
			returnString = returnString+ "\n";
		}
		return returnString;
	}
}
