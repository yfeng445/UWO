import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/* This class contains methods to open a text file, read a line of 
   the file, and read an integer value from the file.              */
public class MyFileReader {
	private String buffer = null;
	private BufferedReader in = null;
	
	/* Open the file whose name was give as parameter */
	public MyFileReader(String fileName) {
		try {
			in = new BufferedReader(new FileReader(fileName));
			buffer = in.readLine();  // Store first line
		}
		catch (IOException e) {
			System.out.println("Cannot read file \'"+fileName+"\'");
			System.exit(0);
		}
	}
	
	/* Returns true if the end of the file has been reached; 
	   it returns false otherwise.                           */
	public boolean endOfFile() {
		if (buffer == null) return true;
		else return false;
	}
	
	/* Reads a line from the file. */
	public String readString() {
		String line = buffer;
		if (buffer == null) {
			System.out.println("Error. The end of file was reached, so another string cannot be read from it");
			return null;
		}
		try {
			buffer = in.readLine();
		}
		catch (IOException e) {
			System.out.println("Error. Cannot read from file");
			System.exit(0);
		}
		return line;
	}
	
	/* Reads an integer from the file. Returns -1 if no integer could be read. */
	public int readInt() {
		String line = readString();
		if (line == null) return -1;
		else return Integer.parseInt(line);
	}
	
	/* Reads an double from the file. Returns -1.0 if no integer could be read. */
	public double readDouble() {
		String line = readString();
		if (line == null) return -1.0;
		else return Double.parseDouble(line);
	}
}