/**
 * @author CS1027
 *
 *         Defines the interface for the ordered list
 */

public interface SortedListADT<T> {
	/**
	 * Adds one CellData object storing the given dataItem and value to the  
	 * ordered list. The list must be sorted after the insertion.
	 * 
	 * @param  dataItem and value to be added to the ordered list
	 */
	public void insert(T dataItem, int value);

	/**
	 * Removes and returns the data item of the ordered list with smallest
	 * associated value. Throws an EmptyListException if the ordered list is empty.
	 * 
	 * @return T data item in the ordered list with smallest associated value
	 */
	public T getSmallest() throws EmptyListException;

	/**
	 * Changes the value associated to the given dataItem to the specified new value. Throws an  
	 * InvalidDataItemException if no object of the list stores the given dataItem.
	 * 
	 * @param dataItem whose value is to be changed
	 * @param newValue new value for this dataItem
	 */
	public void changeValue(T dataItem, int newValue) throws InvalidDataItemException;

	/**
	 * Returns the value of the given dataItem. Throws an InvalidDataItemException if
	 * no CellData object of the ordered list stores the given dataItem. This method
	 * must be recursive.
	 * 
	 * @param dataItem whose value is being sought
	 * @return value of this dataItem
	 */
	public int getValue(T dataItem) throws InvalidDataItemException;
	
	/**
	 * Returns true if this ordered list is empty.
	 * 
	 * @return true if this ordered list is empty, false otherwise
	 */
	public boolean isEmpty();

	/**
	 * Returns the number of data items in this ordered list.
	 * 
	 * @return int number of data items in this ordered list
	 */
	public int size();

	/**
	 * Returns the value of front.
	 * 
	 * @return int front
	 */
	public int getFront();

	/**
	 * Returns the value of rear.
	 * 
	 * @return int rear
	 */
	public int getRear();	
}
