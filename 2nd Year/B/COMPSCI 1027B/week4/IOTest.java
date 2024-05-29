import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class IOTest {
	
	public IOTest () {
		
	}
	
	public void readFile () {
		try {
			FileReader file = new FileReader("movies.txt");
			BufferedReader br = new BufferedReader(file);
			
			String line = br.readLine();
			
			while (line != null) {
				System.out.println(line);
				line = br.readLine();
			}
			
			br.close();
			
		} catch (Exception e) {
			System.out.println("Unable to load file.");
		}
	}
	
	public void writeFile () {
		try {
			FileWriter file = new FileWriter("tvshows.txt");
			BufferedWriter bw = new BufferedWriter(file);
			
			bw.write("Seinfeld\n");
			bw.write("Breaking Bad\n");
			bw.write("The Simpsons\n");
			bw.write("Family Guy\n");
			
			bw.close();
			
		} catch (Exception e) {
			System.out.println("Unable to load/create file.");
		}
	}

	public static void main(String[] args) {
		IOTest test = new IOTest();
		test.readFile();
		test.writeFile();
		
		if (args.length == 1) {
			// Do something.
			int x = Integer.parseInt(args[0]);
			System.out.println("x = " + x);
		} else {
			// Do something else.
		}
	}

}
