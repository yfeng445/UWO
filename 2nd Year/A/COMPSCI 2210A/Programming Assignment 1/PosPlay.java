/* This class represents a possible play and its associated score */
public class PosPlay {
    private int row, col; /* Row and column of the play */
    private int score;    /* Play's score               */  
    private int level;    /* Play's level */

    public PosPlay(int v, int r, int c, int l) {
	row = r;
	col = c;
	score = v;
	level = l;
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

    public int getLevel() {
	return level;
    }
}