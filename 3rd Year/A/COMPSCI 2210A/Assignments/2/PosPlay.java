/* This class represents a possible play and its associated score */
public class PosPlay {
	private int row, col; /* Row and column of the play */
	private int score;    /* play's score               */  

	public PosPlay(int v, int r, int c) {
		row = r;
		col = c;
		score = v;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public int getScore() {
		return score;
	}
}