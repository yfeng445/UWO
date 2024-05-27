import java.io.*;
import java.util.Scanner;

/**
 * Class to open a file and either read information from it or write information
 * to it. If a file is opened in "read" mode we can only read information from
 * it. Otherwise, we can only write information to it.
 *
 */
public class Convert {

	// Method readChar() returns this value when the end of the file has been
	// reached
	public static final char EOF = 0;

	private static BufferedReader in; // This variable points to the input file
	// private BufferedWriter out; // This variable points to the output file
	private static BufferedWriter out;


	/**
	 * Checks that the specifed name corresponds to an existing file and if so
	 * it opens the file
	 * 
	 * @param name
	 *            name of the input file
	 */
	public static void main(String[] args) {
		String name = "map";
		while (name != "") {
		try {
			File file;
			String line;
			Scanner sc = new Scanner(System.in);
			System.out.println("File name: ");
			name = sc.nextLine();
			if (name.equals("")) break;
			
			file = new File(name);

			if (!file.exists()) {
				System.out.println("File " + name + " does not exist.");
				continue; // end program
			}
			if (!(file.isFile() && file.canRead())) {
				System.out.println("file " + file.getName() + " cannot be read from.");
				continue;
			}
			in = new BufferedReader(new FileReader(name)); // Get ready to read from the

			out = new BufferedWriter(new FileWriter("map"+name.substring(7)));
			System.out.println("map"+name.substring(7));
			out.write ("First line: #rows, #columns, optional delay, optional chamber size. Other lines: F-free, N-noFly, W-tower, T-thief, H-highAlitude, I-Initial, C-Customer");
			out.newLine();
			line = in.readLine(); // Ignore first line
			line = in.readLine(); // Copy second line
			out.write(line);
			out.newLine();
			String buffer;
			// Convert remaining lines
			while ((line = in.readLine()) != null) {
				buffer = "";
				for (int i = 0; i < line.length(); ++i) {
					if (line.charAt(i) == ' ') buffer += ' ';
					else
						switch (line.charAt(i)) {
							case 'W': buffer += 'N'; break;
							case 'S': buffer += 'I'; break;
							case 'U': buffer += 'F'; break;
							case 'E': buffer += 'C'; break;
							case 'L': buffer += 'T'; break;
							case 'C': buffer += 'H'; break;							
							case 'D': buffer += 'W'; break;								
						}
				}
				out.write(buffer);
				out.newLine();
			}
			out.close();
			in.close();

		} catch (IOException e) {
			System.out.println("Error opening file " + name);
		}
		}
	}
}
