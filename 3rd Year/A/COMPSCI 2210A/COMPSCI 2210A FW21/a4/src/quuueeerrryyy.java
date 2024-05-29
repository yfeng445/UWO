import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class quuueeerrryyy {
	
	public static void main(String[] args) {
			StringReader fileName = new StringReader();
			String input = fileName.read("Please enter file name: ");
			BSTOrderedDictionary dict = new BSTOrderedDictionary();
			BSTNode r = dict.getRoot();
		    BufferedReader in;
		    //input file data
			try {
				in = new BufferedReader(new FileReader("src/"+input));
			    String key = in.readLine();
			    String s = in.readLine();
			    while(!(key==null||s==null)) {
			    	if(s.contains(".wav")||s.contains(".mid")){
			    		dict.put(r, key, s, 2);
			    	}
			    	else if(s.contains(".gif")) {
			    		dict.put(r, key, s, 3);
			    	}
			    	else if(s.contains(".html")) {
			    		dict.put(r, key, s, 4);
			    	}
			    	else {
			    		dict.put(r, key, s, 1);
			    	}
			    	key = in.readLine();
			    	s = in.readLine();
			    }
			} catch (IOException e) {
				e.printStackTrace();
			}
			String key = new StringReader().read("Enter something: ");
			System.out.println(dict.getNumInternalNodes());
			System.out.println(dict.get(r, key).get(0).getType());
	}
}