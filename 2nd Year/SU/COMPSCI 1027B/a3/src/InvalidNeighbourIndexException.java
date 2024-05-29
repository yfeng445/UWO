/**
 * Thrown when access is attempted outside the valid cell neighbor index range (0-5).
 * @author CS1027
 *
 */
public class InvalidNeighbourIndexException extends ArrayIndexOutOfBoundsException{

	public InvalidNeighbourIndexException(int i){
		super("Invalid index for hexagon neighbor:" + i + "  Must be 0-5 inclusive");
	}
}
