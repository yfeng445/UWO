/**
 * Testing the Player class
 * @author Yulun Feng
 * Student ID: 251113989
 *
 */
public class TestLab {

	static Player p1 = new Player("Yulun Feng","defence",7);
	static Player p2 = new Player("bwaaaa","attack",0);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println(p1.getName());
		System.out.println(p1.getposition());
		System.out.println(p1.getJerseyNum());
		
		p1.setJerseyNum(0);
		p1.setName("bwaaaa");
		p1.setPositino("attack");
		
		System.out.println(p1.getName());
		System.out.println(p1.getposition());
		System.out.println(p1.getJerseyNum());
		
		System.out.print(p1);
		
		p1.toString();
		System.out.println("");
		
		if (p1.equals(p2)) {
			System.out.println("Same player");
			} else {
			System.out.println("Different player");
			}
	}

}
