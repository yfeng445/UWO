import java.util.Iterator;

/**
 * This file implement methods records matches, giving the winning team
 * @author Yulun Feng
 * @ID 251113989
 * June 22, 2021 
 */
public class Playoffs {
	
	private LinkedBinaryTree<String> tree;
	private HockeySeries[] standings;
	
	
	/**
	 * Constructor of the class, creating a tree
	 */
	public Playoffs() {
		String[] array = new String[31];
		for(int i = 0;i<15;i++) {
			array[i] = "TBD "+i;
		}
		MyFileReader file = new MyFileReader("teams.txt");
		for(int i = 15;i<array.length;i++) {
			array[i] = file.readString();
		}
		standings = new HockeySeries[15];
		int j = 0;
		for(int i = 15;i<array.length;i+=2) {
			standings[j] = new HockeySeries(array[i],array[i+1],0,0);
			j++;
		}
		TreeBuilder buildTree = new TreeBuilder(array);
		this.tree = buildTree.getTree();
	}
	
	
	/**
	 * return the tree
	 * @return
	 */
	public LinkedBinaryTree<String> getTree(){
		return tree;
	}
	
	
	/**
	 * return the standing array
	 * @return
	 */
	public HockeySeries[] getStandings() {
		return standings;
	}
	
	
	/**
	 * Taking in two team's information and return the winning team if one team reach 4 wins
	 * @param TeamA
	 * @param TeamB
	 * @param winA
	 * @param winB
	 * @return 
	 */
	public String updateStandings(String TeamA,String TeamB,int winA,int winB) {
		for(int i = 0; i<standings.length;i++) {
			if(!(standings[i]==null)) {
				if(standings[i].getTeamA().equals(TeamA)&&standings[i].getTeamB().equals(TeamB)) {
					if(winA>winB) {
						standings[i].incrementWins(1, 0);						
					}
					else if(winA<winB) {
						standings[i].incrementWins(0, 1);
					}
					
					if(standings[i].getTeamAWins()==4){
						return standings[i].getTeamA();
					}
					else if(standings[i].getTeamBWins()==4) {
						return standings[i].getTeamB();
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * take the score file number and update the round, adding the winning team to the parent node
	 * @param i score file number
	 */
	public void updateRound(int i) {
		MyFileReader reader = new MyFileReader("scores"+i+".txt");
		String dataString = ""; // creating an array which have no content, however it is not null
		while(!(dataString==null)) {
			dataString = reader.readString();
			if(!(dataString==null)) {
				String[] data = dataString.split(",");
				String result = updateStandings(data[0],data[1],Integer.valueOf(data[2]),Integer.valueOf(data[3]));
				if(!(result==null)){
					if(tree.contains(tree.getRoot(),result)){
						findParent(data[0],data[1]).setData(result);
						}				
				}
			}
		}
	}
	
	
	/**
	 * find the parent node of two nodes
	 * @param A
	 * @param B
	 * @return
	 */
	public BinaryTreeNode<String> findParent(String A, String B){
		LinkedQueue parentQueue = new LinkedQueue();
		parentQueue.enqueue(tree.getRoot());
		while(!parentQueue.isEmpty()) {
			BinaryTreeNode curr = (BinaryTreeNode) parentQueue.dequeue();
			//found
			if((curr.getLeft().getData().equals(A)&&curr.getRight().getData().equals(B))||
				(curr.getRight().getData().equals(A)&&curr.getLeft().getData().equals(B))){
				return curr;
			}
			//not found
			else {
				//add the child of this node into the queue if it has two children
				if(!(curr.getLeft().getLeft()==null)&&!(curr.getLeft().getRight()==null)) {
					parentQueue.enqueue(curr.getLeft());
				}
				if(!(curr.getRight().getLeft()==null)&&!(curr.getRight().getRight()==null)) {
					parentQueue.enqueue(curr.getRight());
				}
			}
		}
		return null;
	}
	
	
	
	
	
	/**
	 * This method adds the new series to the standings array before a new round begins. It does this using an iterator
	 * from the tree and skipping over the nodes that are still unknown until it gets to the nodes from the new round.
	 * It then takes two teams at a time from the iterator and creates a new series in the standings array for those
	 * two teams. The series standings (number of wins for each team) are set to 0 by default. 
	 */
	public void addNewStandings (int numSkips, int sIndex, int eIndex) {
		Iterator<String> iter = tree.iteratorLevelOrder();
		int i;
		String team1, team2;
		for (i = 0; i < numSkips; i++) {
			iter.next();
		}
		for (i = sIndex; i <= eIndex; i++) {
			team1 = iter.next();
			team2 = iter.next();
			standings[i] = new HockeySeries(team1, team2, 0, 0);
		}
	}
	
	/**
	 * This method simply prints out the standings table in a cleanly formatted table structure.
	 */
	public void printStandings () {
		String str;
		for (int k = 0; k < standings.length; k++) {
			if (standings[k] != null) {
				str = String.format("%-15s\t%-15s\t%3d-%d", standings[k].getTeamA(), standings[k].getTeamB(), standings[k].getTeamAWins(), standings[k].getTeamBWins());
				System.out.println(str);
			}
		}
	}
	
	
	public static void main(String[] args) {
		Playoffs pl = new Playoffs();
		pl.updateRound(1);

		// Uncomment each pair of lines when you are ready to run the subsequent rounds. 
		
		//pl.addNewStandings(7, 8, 11); // Ensure you execute this line before calling updateRound(2).
		//pl.updateRound(2);

		
		//pl.addNewStandings(3, 12, 13); // Ensure you execute this line before calling updateRound(3).
		//pl.updateRound(3);

		
		//pl.addNewStandings(1, 14, 14); // Ensure you execute this line before calling updateRound(4).
		//pl.updateRound(4);
	}

}
