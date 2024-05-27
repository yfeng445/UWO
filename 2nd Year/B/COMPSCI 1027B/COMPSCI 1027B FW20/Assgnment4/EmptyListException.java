/**
 * @author CS1027
 *
 *  Exception thrown when the priority queue is empty
 */

public class EmptyListException extends RuntimeException {
  /**
   * Sets up this exception with an appropriate message.
   * @param message String representing the error encountered
   */
  public EmptyListException (String message) {
    super (message);
  }
}
