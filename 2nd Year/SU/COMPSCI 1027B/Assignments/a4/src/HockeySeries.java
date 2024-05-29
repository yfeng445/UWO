/**
 * This file 
 * @author 56111
 *
 */
public class HockeySeries {

	private String teamA;
	private String teamB;
	private int winsA;
	private int winsB;
	
	public HockeySeries (String teamA, String teamB, int winsA, int winsB) {
		this.teamA = teamA;
		this.teamB = teamB;
		this.winsA = winsA;
		this.winsB = winsB;
	}
	
	public String getTeamA () {
		return teamA;
	}
	
	public String getTeamB () {
		return teamB;
	}
	
	public int getTeamAWins () {
		return winsA;
	}
	
	public int getTeamBWins () {
		return winsB;
	}
	
	public void incrementWins (int winsA, int winsB) {
		this.winsA += winsA;
		this.winsB += winsB;
	}

}
