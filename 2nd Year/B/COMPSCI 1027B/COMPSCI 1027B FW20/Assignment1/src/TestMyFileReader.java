import java.util.Scanner;

public class TestMyFileReader {

    public static void main (String[] args) {
	MyFileReader in;
	String line;
	Scanner input = new Scanner(System.in);
	System.out.print("Enter file name: ");
	String fileName = input.next();

	System.out.println("\n\nContent of file "+fileName+":\n");
	in = new MyFileReader(fileName);

        // The first line of the file must contain only one integer value
	int value = in.readInt();
	System.out.println(value);
	while (in.endOfFile() == false) {
	    line = in.readString();
	    System.out.println(line);
	}
    }
}