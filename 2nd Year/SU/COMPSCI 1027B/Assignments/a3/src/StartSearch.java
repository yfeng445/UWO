/**
 * This class defines an method picking best match cell and add the cell into the path 
 * @author Yulun Feng
 * @ID 251113989
 * July 12, 2021
 */
public class StartSearch {
	
	private Map map;
	
	
	/**
	 * Constructor of the class, taking the input String to create a map
	 * @param var
	 */
	public StartSearch(String var){
		try {
			map = new Map(var);
		}
		catch(Exception e) {
		}
	}
	
	
	
	
	/**
	 * return the best cell in the neighbor
	 * @param cell the given cell
	 * @return nextCell the return cell 
	 * @throws InvalidNeighbourIndexException if the Neighbor is invalid
	 */
	public MapCell bestCell(MapCell cell) throws InvalidNeighbourIndexException{
		MapCell nextCell;
		MapCell currCell = cell;
		try { //Covid>exit||start>donut>>crossPath>verticalPath||horizontalPath>nullCell
			for(int i = 0; i<4;i++) {//Return Null is the cell is orthogonally adjacent to a COVID cell
				if(!(cell.getNeighbour(i)==null)) { // null if the neighbor cell is covid
					nextCell = cell.getNeighbour(i);
					if(nextCell.isCovid()) {
					return null;
					}				
				}
			}
			if(currCell.isStart()||currCell.isDonut()||currCell.isCrossPath()) { // "+" cells
				for(int i = 0;i<4;i++) {
					if(!(cell.getNeighbour(i)==null)) { // Exit
						nextCell = cell.getNeighbour(i);
						if(nextCell.isExit()&&(!nextCell.isMarked())) {
							return nextCell;
						}
					}
				}
				for(int i = 0;i<4;i++) {
					if(!(cell.getNeighbour(i)==null)) { // Donut cells
						nextCell = cell.getNeighbour(i);
						if(nextCell.isDonut()&&(!nextCell.isMarked())) {
							return nextCell;
						}
					}
				}
				for(int i = 0;i<4;i++) {
					if(!(cell.getNeighbour(i)==null)) {// crossPaths
						nextCell = cell.getNeighbour(i);
						if(nextCell.isCrossPath()&&(!nextCell.isMarked())) { 
							return nextCell;
						}
					}
				}
				for(int i = 0; i<4;i++) {
					if(!(cell.getNeighbour(i)==null)) { // vertical or horizontal paths
						nextCell = cell.getNeighbour(i);
						if(i==0&&nextCell.isVerticalPath()&&(!nextCell.isMarked())){
							return nextCell;
						}
						else if(i==1&&nextCell.isHorizontalPath()&&(!nextCell.isMarked())){
							return nextCell;
						}
						else if(i==2&&nextCell.isVerticalPath()&&(!nextCell.isMarked())){
							return nextCell;
						}
						else if(i==3&&nextCell.isHorizontalPath()&&(!nextCell.isMarked())){
							return nextCell;
						}
					}
				}
			}
			
			if(currCell.isVerticalPath()) { // "|" cells
				for(int i = 0;i<4;i = i+2) {
					nextCell = cell.getNeighbour(i);
					if(nextCell==null) { // return null if the cell is at the dead end
					}
					else if(nextCell.isExit()&&(!nextCell.isMarked())) { // exit
						return nextCell;
					}
				}
				for(int i = 0;i<4;i = i+2) {
					nextCell = cell.getNeighbour(i);
					if(nextCell==null) {	
					}
					else if(nextCell.isDonut()&&(!nextCell.isMarked())) { // Donut
						return nextCell;
					}
				}
				for(int i = 0;i<4;i = i+2) {
					nextCell = cell.getNeighbour(i);
					if(nextCell==null) {	
					}
					else if(nextCell.isCrossPath()&&(!nextCell.isMarked())) { // CrossPath
						return nextCell;
					}
				}
				for(int i = 0;i<4;i = i+2) {
					nextCell = cell.getNeighbour(i);
					if(nextCell==null) {	
					}
					else if(nextCell.isVerticalPath()&&(!nextCell.isMarked())) { // Vertical cells could not connect to Horizontal path
						return nextCell;
					}
				}
			}
			
			if(currCell.isHorizontalPath()) { // "-" cells
				for(int i = 1;i<4;i = i+2) {
					nextCell = cell.getNeighbour(i);
					if(nextCell==null) {
						
					}
					else if(nextCell.isExit()&&(!nextCell.isMarked())) { // Exit
						return nextCell;
					}
				}
				for(int i = 1;i<4;i = i+2) {
					nextCell = cell.getNeighbour(i);
					if(nextCell==null) {	
					}
					else if(nextCell.isDonut()&&(!nextCell.isMarked())) {
						return nextCell;
					}
				}
				for(int i = 1;i<4;i = i+2) {
					nextCell = cell.getNeighbour(i);
					if(nextCell==null) {	
					}
					else if(nextCell.isCrossPath()&&(!nextCell.isMarked())) {
						return nextCell;
					}
				}
				for(int i = 1;i<4;i = i+2) {
					nextCell = cell.getNeighbour(i);
					if(nextCell==null) {	
					}
					else if(nextCell.isHorizontalPath()&&(!nextCell.isMarked())) {
						return nextCell;
					}
				}
			}
			return null;			
		}
		catch(InvalidNeighbourIndexException e) {
			throw e;
		}
	}
	
	
	
	/**
	 * return the path and the final energy in a certain way
	 * @return actionString string that record the path and the energy
	 */
	public String findPath() {
		ArrayStack stack = new ArrayStack();
		String actionString = "";
		boolean found = false;
		MapCell startCell = map.getStart();
		stack.push(startCell);
		actionString = actionString+startCell.toString()+"-";	
		startCell.markInStack();		
		int AP = 10;
		while(!stack.isEmpty()&&found==false) {
			if(stack.peek()==null) {
				stack.pop();
			}
			//System.out.println(stack);
			MapCell nextCell = bestCell((MapCell)stack.peek());	
			if(!(nextCell==null)&&AP>0) {
				if(nextCell.isExit()||nextCell.isStart()) {
					found = true;
					AP--;
					return actionString+"E"+AP;
				}	
				actionString = actionString+nextCell.toString()+"-";	
				if(nextCell.isDonut()) {
					AP+=3;
				}
				stack.push(nextCell);
				nextCell.markInStack();
				AP--;
			}
			else {
				MapCell topCell = (MapCell) stack.pop();
				if(!(stack.size()==0)){
					actionString = actionString+stack.peek().toString()+"-";
				}
				topCell.markOutStack();
				if(topCell.isDonut()) {
					AP-=3;
					AP++;
				}
				else {
					if(!topCell.isStart()) {
						AP++;
					}
				}
			
			}
		}
		while(!stack.isEmpty()) {
			MapCell topCell = (MapCell)stack.pop();
			topCell.markOutStack();
		}
		return actionString+"E"+AP;
	}
	
	//the main function that test the map in imported or not at the beginning of the program
	/*
	public static void main(String[] args){
		if(args.length<1) {
			System.out.println("You must provide the name of the input file");
		}
		String mapFile = args[0];
		try {
			StartSearch ss = new StartSearch(mapFile);
		} catch (Exception e) {
		}
	}
	*/
	
}
