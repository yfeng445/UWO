
public class TestLab {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Player p1  = new Player("Yulun Feng","defence",70);
		System.out.println(p1.getName());
		System.out.println(p1.getPosition());
		System.out.println(p1.getJerseyNum());
		
		p1.setName("YF"); // YF
		p1.setPosition("attack"); // attack
		p1.setJerseyNum(0); // 0
		
		System.out.println(p1);
		
		Player p2 = new Player("YF", "attack",0);
		
		if(p1.equals(p2)) {
			System.out.println("Same player");
		}else {
			System.out.println("Different player");
		}
	}

}
