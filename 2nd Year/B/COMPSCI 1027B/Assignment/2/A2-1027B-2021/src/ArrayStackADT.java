import java.util.EmptyStackException;

/**
 *  @author CS1027
 *
 *  Defines the interface to a stack data structure.
 */

public interface ArrayStackADT<T>
{
  /**  Adds one element to the top of this stack. 
   *   @param dataItem data item to be pushed onto stack
   */
  public void push (T dataItem);
  
  /**  Removes and returns the top element from this stack. 
   *   @return T data item removed from the top of the stack
   */
  public T pop() throws EmptyStackException;

  /**  Returns without removing the top element of this stack. 
   *   @return T data item on top of the stack
   */
  public T peek() throws EmptyStackException;
  
  /**  Returns true if this stack contains no elements. 
   *   @return true if the stack is empty; false otherwise 
   */
  public boolean isEmpty();

  /**  Returns the number of data items in this stack. 
   *   @return int number of data items in this stack
   */
  public int size();

  /**  Returns a string representation of this stack. 
   *   @return String representation of this stack
   */
  public String toString();
}
