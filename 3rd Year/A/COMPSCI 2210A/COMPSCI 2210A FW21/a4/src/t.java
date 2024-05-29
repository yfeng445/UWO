import java.util.Arrays;

public class t {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BSTOrderedDictionary d = new BSTOrderedDictionary();
		d.put(d.getRoot(), "s", null, 0);
		d.put(d.getRoot(), "ss", null, 0);
		System.out.println(d.successor(d.getRoot(), "ss")==null);
	}

}
