/**public class Snake {
	int snakeLength;
	int len = 1;
	Position[] snakeBody = new Position[len];

	public Snake(int row, int col) {
		snakeLength = len;
		Position object = new Position(row,col);
		snakeBody[0] = object;
	}
	public int getLength() {
		return snakeLength;
	}
	Position getPosition(int index){
		if(index<0||index>=len) {
			return null;
		}
		else return snakeBody [index];		
	}
	public void shrink() {
		snakeLength-=1;
	}
	public boolean snakePosition(Position pos) {
		boolean find = false;
		for(int i = 0;i<len;i++) {
		if(snakeBody[i].equals(pos)) {
			find = true;			
		}
		else find = false;}
		return find;
	}
	
	public Position newHeadPosition(String direction) {
		Position P = getPosition(0);
		int r = P.getRow();
		int c = P.getCol();
		if (direction == "up") {
			r-=1;
		}
		if (direction == "down") {
			r+=1;
		}
		if (direction == "left") {
			c-=1;
		}
		if (direction == "right") {
			c+=1;
		}
		return new Position(r,c);
	}
	public void moveSnake(String direction) {
		if (len == 1){
			snakeBody[0] = newHeadPosition(direction);
		}
		if (len>=2){
			for (int i = len-2;i>=0;i--)	{
				snakeBody[i+1] = snakeBody[i];
					}
		}	
	}
	void grow(String direction) {
		if (snakeLength == len) {
			increaseArraySize();
			Position newHead = newHeadPosition(direction);

		for (int i = len;i>=0;--i)	{
			snakeBody[i] = snakeBody[i-1];
			}
		len++;
		snakeBody[0] = newHead;
		}	
	}
	void increaseArraySize() {
		len = len*2;
	}
}
*/