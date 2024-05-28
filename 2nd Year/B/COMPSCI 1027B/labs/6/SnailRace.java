public class SnailRace {
	
	private Snail[] participants;
	public static int raceLength = 50;
	
	public SnailRace () {
		participants = new Snail[3];

		participants[0] = new Snail(new int[] {1, 0, 1, 2, 1, 0});
		participants[1] = new Snail(new int[] {2, 1, 1, 0});
		participants[2] = new Snail(new int[] {0, 0, 3});

		try {
			startRace();
		} catch (Exception e) {
			System.out.println("An error occurred.");
		}

	}
	
	public void startRace () throws InterruptedException {
		
		boolean racing = true;
		
		while (racing) {
			
			for (int i = 0; i < participants.length; i++) {
				participants[i].move();
			}
			
			for (int i = 0; i < participants.length; i++) {
				if (participants[i].getPosition() == raceLength) {
					racing = false;
				}
			}
			
			display();
		
			// Pause briefly to simulate the timing of the race.
			Thread.sleep(500);
			
		}
	}
	
	public void display () {
		for (int i = 0; i < participants.length; i++) {
			participants[i].display();
		}
		System.out.println("\n\n\n\n");
	}
	
	
	public static void main (String[] args) {
		new SnailRace();
	}
	

}
