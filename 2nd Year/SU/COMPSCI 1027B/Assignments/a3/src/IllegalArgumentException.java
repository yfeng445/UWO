/**
 * @author CS1027
 *
 *  Represents the situation in which no command line argument was provided
 */

public class IllegalArgumentException extends RuntimeException
{
  /**
   * Sets up this exception with an appropriate message.
   * @param message explains the error that threw the exception
   */
  public IllegalArgumentException (String message)
  {
    super (message);
  }
}
