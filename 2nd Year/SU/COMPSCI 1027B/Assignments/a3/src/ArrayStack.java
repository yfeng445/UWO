/**
 * This class defines arrayStack and its methods 
 * @author Yulun Feng
 * @ID 251113989
 * July 12, 2021
 */

public class ArrayStack<T> {
	
	private T[] array;
	private int top;
	
	/**
	 * constructor of the class
	 */
	public ArrayStack(){
		array = (T[]) new Object[10];
		top = 9;
	}
	
	/**
	 * Another constructor of the class
	 * @param top top index of the class
	 */
	public ArrayStack(int top) {
		array = (T[]) new Object[top];
		this.top = top-1;
	}
	
	/**
	 * push an item into the stack and increment the top index
	 * @param item
	 */
	public void push(T item) {
		// increase size by 5 if the array is full
		if(size()==array.length) {
			T[] newArray = (T[])new Object[array.length+5];
			for(int i = 0; i<array.length;i++) {
				newArray[i] = array[i];
			}
			array = newArray;
		}
		array[size()] = item;
		top=getTop();
	}
	
	/**
	 * return number of the items in the stack
	 * @return
	 */
	public int size() {
		int i = 0;
		while(!(array[i]==null)) {
			i++;
			if(i==array.length) {
				return array.length;
			}
		}
		return i;
	}
	
	/**
	 * pop out the top item from the stack and return it
	 * @return item the item at the top
	 * @throws StackException
	 */
	public T pop() throws StackException{
		if(isEmpty()){
			throw new StackException("array");
		}
			T item = array[size()-1];
			array[size()-1] = null;
			return item;
	}
	
	/**
	 * return the top item of the stack without remving it
	 * @return the top item
	 * @throws StackException
	 */
	public T peek() throws StackException{
		if(isEmpty()) {
			throw new StackException("array");
		}
	return array[size()-1];	
	}
	
	/**
	 * return true if the stack is empty
	 */
	public boolean isEmpty() {
		return(array[0]==null);
	}
	
	/**
	 * return length of the stack
	 * @return
	 */
	public int getLength() {
		return array.length;
	}
	
	/**
	 * return the top index of the stack
	 * @return top index
	 */
	public int getTop() {
		return array.length-size()-1;
	}
	
	/**
	 * return the stack contents in a certain way
	 */
	public String toString() {
		if(size()==0) {
			return "The stack is empty.";
		}
		else{
			String returnString = "Stack: ";
			for(int i = size()-1;i>-1;i--) {
				returnString = returnString+array[i]+", ";
			}
			return returnString.substring(0, returnString.length()-2)+".";
		}
	}
}
