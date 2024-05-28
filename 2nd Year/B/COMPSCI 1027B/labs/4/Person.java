/**
 * Class that represents a person with attributes name, email address 
 * @author CS1027
 *
 */
public class Person {
	
	/* Attribute declarations */
	private String lastName;	// last name
	private String firstName;	// first name
	
	/**
	 * Constructor initializes the person's name and email address
	 */
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;		
	}

	/**
	 * getName method returns the person's full name
	 * @return first name followed by last name, blank separated
	 */
	public String getName () {
		return firstName + " " + lastName;
	}

	/**
	 * toString method returns a string representation of the person
	 * @return string with first name and last name, email address 
	 */
	public String toString() {
		String s = firstName + " " + lastName;
		return s;
	}
	
}