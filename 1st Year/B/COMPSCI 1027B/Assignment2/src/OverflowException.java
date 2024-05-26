/**
 * @author CS1027
 *
 *  Represents the situation in which the stack is full
 */

public class OverflowException extends RuntimeException
{
  /**
   * Sets up this exception with an appropriate message.
   * @param message String describing the error that threw the exception
   */
  public OverflowException (String message)
  {
    super (message);
  }
}
