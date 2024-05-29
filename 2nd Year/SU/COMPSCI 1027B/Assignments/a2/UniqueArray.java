/**
 * This class creates an array that has different elements
 * @author Yulun feng
 * @ID 251113989
 * 27 June 2021
 * @param <T>
 */
public class UniqueArray<T>  {
	
	private T[] array;
	private int num;
	
	/**
	 * constructor fo the class
	 */
	public UniqueArray() {
		array =  (T[])new Object[5];
		num = 0;
	}
	
	/**
	 * extends capacity of the array by 5 spots
	 */
	private void expandCapacity() {
		num+=5;
		T[] newArray = (T[])new Object[array.length+5];
		for(int i = 0; i<getNumElements();i++) {
			newArray[i] = array[i];
		}
		this.array = newArray;
	}
	
	/**
	 * add a new Item
	 * @param element
	 */
	public void addItem(T element) {
		boolean find = false;
		for(int i = 0; i<getNumElements(); i++) {
			if(array[i].equals(element)) {
				find = true;
			}
		}
		if(!find) {
			if(!(array[array.length-1]==null)) {
				expandCapacity();
			}
			array[getNumElements()]=element;
		}
	}
	
	/**
	 * return length of the array
	 * @return
	 */
	public int getLength() {
		return array.length;
	}
	
	/**
	 * return number of the element in this array
	 * @return
	 */
	public int getNumElements() {
		int number = 0;
		if(!(array[array.length-1]==null)) {
			return array.length;
		}
		while(!(array[number]==null)) {
			number++;
		}
		return number;
	}
	
	/**
	 * return elements in the array in a specific way
	 */
	public String toString() {
		String returnString = "";
		if(array[0]==null) {
			return "";
		}
		for(int i = 0; i<array.length;i++) {
			if(!(array[i]==null)) {
				returnString = returnString+array[i]+", ";
			}
		}
		returnString = returnString.substring(0, returnString.length()-2);
		return returnString;
	}
	
}
