import java.util.Iterator;

public class TestPlayoffs {

	public static void main(String[] args) {
		
		Playoffs pl = new Playoffs();
		
		HockeySeries[] st;
		LinkedBinaryTree<String> tr = pl.getTree();
		BinaryTreeNode<String> node;
		Iterator<String> iter;

		
		
		iter = tr.iteratorLevelOrder();
		String tmp;
		String actStr = "";
		String expStr = "";
		for (int i = 0; i < 15; i++) {
			expStr += "TBD " + i + " ";
		}
		expStr += "Avalanche Blues Golden Knights Wild Maple Leafs Canadiens Oilers Jets Penguins ";
		expStr += "Islanders Capitals Bruins Hurricanes Predators Panthers Lightning ";
		while (iter.hasNext()) {
			tmp = iter.next();
			actStr += tmp + " ";
		}

		// Test 1
		
		if (actStr.equals(expStr)) {
			System.out.println("Test 1 Passed");
		} else {
			System.out.println("Test 1 Failed");
		}
		
		// Test 2
		
		node = pl.findParent("Avalanche", "Blues");
		if (node != null && node.getData().equals("TBD 7")) {
			System.out.println("Test 2 Passed");
		} else {
			System.out.println("Test 2 Failed");
		}

		// Test 3
		
		node = pl.findParent("Ducks", "Red Wings");
		if (node == null) {
			System.out.println("Test 3 Passed");
		} else {
			System.out.println("Test 3 Failed");
		}
		
		pl.updateRound(1);

		// Test 4
		
		st = pl.getStandings();
		if (st[2].getTeamA().equals("Maple Leafs") && st[2].getTeamB().equals("Canadiens") && st[2].getTeamAWins() == 3 && st[2].getTeamBWins() == 4) {
			System.out.println("Test 4 Passed");
		} else {
			System.out.println("Test 4 Failed");
		}
		
		// Test 5
		if (st[6].getTeamA().equals("Hurricanes") && st[6].getTeamB().equals("Predators") && st[6].getTeamAWins() == 4 && st[6].getTeamBWins() == 2) {
			System.out.println("Test 5 Passed");
		} else {
			System.out.println("Test 5 Failed");
		}

		pl.addNewStandings(7, 8, 11);
		pl.updateRound(2);

		// Test 6
		if (st[9].getTeamA().equals("Canadiens") && st[9].getTeamB().equals("Jets") && st[9].getTeamAWins() == 4 && st[9].getTeamBWins() == 0) {
			System.out.println("Test 6 Passed");
		} else {
			System.out.println("Test 6 Failed");
		}

		// Test 7
		
		node = pl.findParent("Golden Knights", "Canadiens");
		if (node != null && node.getData().equals("TBD 1")) {
			System.out.println("Test 7 Passed");
		} else {
			System.out.println("Test 7 Failed");
		}		

		pl.addNewStandings(3, 12, 13);
		pl.updateRound(3);

		// Test 8

		String res = pl.updateStandings("Golden Knights", "Canadiens", 1, 0); // Giving an extra win to the Golden Knights.
		pl.updateStandings("Golden Knights", "Canadiens", -1, 0); // Removing the additional win for Golden Knights.

		if (res.equals("Canadiens")) {
			System.out.println("Test 8 Passed");
		} else {
			System.out.println("Test 8 Failed");
		}

		// Test 9

		iter = tr.iteratorLevelOrder();
		tmp = "";
		actStr = "";
		expStr = "TBD 0 ";
		expStr += "Canadiens Lightning Golden Knights Canadiens Islanders Lightning Avalanche ";
		expStr += "Golden Knights Canadiens Jets Islanders Bruins Hurricanes Lightning ";
		expStr += "Avalanche Blues Golden Knights Wild Maple Leafs Canadiens Oilers Jets Penguins ";
		expStr += "Islanders Capitals Bruins Hurricanes Predators Panthers Lightning ";
		while (iter.hasNext()) {
			tmp = iter.next();
			actStr += tmp + " ";
		}

		if (actStr.equals(expStr)) {
			System.out.println("Test 9 Passed");
		} else {
			System.out.println("Test 9 Failed");
		}		

		// Test 10

		pl.addNewStandings(1, 14, 14);
		pl.updateRound(4);

		if (tr.getDataRoot().equals("Lightning")) {
			System.out.println("Test 10 Passed");
		} else {
			System.out.println("Test 10 Failed");
		}
		
	}

}
