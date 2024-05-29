import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class tt1 {

	public static void main(String[] args) throws LabyrinthException, GraphException, InterruptedException, NumberFormatException, IOException {
		// TODO Auto-generated method stub
		Solver program = new Solver("lab8");		
		try {
			Iterator solution = program.solve();
		} catch (GraphException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
