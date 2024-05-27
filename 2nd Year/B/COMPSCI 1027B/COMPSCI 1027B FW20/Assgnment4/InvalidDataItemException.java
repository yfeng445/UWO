/**
 * @author CS1027
 *
 *  Exception thrown when a specified element is not in the priority queue
 */

public class InvalidDataItemException extends RuntimeException {
  /**
   * Sets up this exception with an appropriate message.
   * @param message String representing the error encountered
   */
  public InvalidDataItemException (String message) {
    super (message);
  }
}
