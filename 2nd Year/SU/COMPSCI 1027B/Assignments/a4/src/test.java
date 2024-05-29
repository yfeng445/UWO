
public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] t = new String[16];
		MyFileReader r = new MyFileReader("teams.txt");
		for(int i = 0; i<t.length;i++) {
			t[i] = r.readString();
			System.out.println(t[i]);
			i++;
		}
		//System.out.println(t);
	}

}
