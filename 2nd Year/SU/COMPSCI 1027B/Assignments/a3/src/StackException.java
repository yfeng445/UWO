public class StackException extends RuntimeException {

	/**
	 * Sets up this exception with an appropriate message.
	 * 
	 * @param collection String representing the name of the collection
	 */
	public StackException(String collection) {

		super("The " + collection + " is empty.");

	} // end EmptyStackException

} // end EmptyStackException