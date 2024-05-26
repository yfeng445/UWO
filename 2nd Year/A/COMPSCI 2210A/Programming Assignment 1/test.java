import java.util.*;

public class test {
	
	public static void main(String args[]){
	
		Dictionary dict = new Dictionary(9887);
//		System.out.print(dict.numDataItems());
	//	System.out.print("\n");
		
		try {
	        dict.put(new Data("answer", 42,1));
		} catch (DuplicatedKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("getPart \n");
		System.out.print(dict.get("answer").level);
		System.out.print("\n");
		
	}
}