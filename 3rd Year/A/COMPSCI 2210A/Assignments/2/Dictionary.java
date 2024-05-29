import java.util.*;
public class Dictionary implements DictionaryADT{
	// largest prime number under 10000 is 9973
	private Node[] T;
	public Dictionary(int size) {
		T = new Node[size];
	}
	
	private int h(String str) {
		int output = 0;
		char[] arr = str.toCharArray();
		for(int i = 0;i<arr.length;i++) {
			int pv = (int) Math.pow(41, i)%T.length;
			int cv =(int)arr[i];
			output = (output+cv*pv)%T.length;
		}
		return output;
	}
	
	public int put(Layout data) throws DictionaryException{
		int pos = h(data.getBoardLayout());
		Node inputNode = new Node(data);
		if(T[pos]==null) {
			T[pos] = inputNode;
			return 0;
		}
		else {
			Node head = T[pos];
			if(head.getData().getBoardLayout().equals(data.getBoardLayout())) {
				throw new DictionaryException(null);
			}
			while(!(head.getNext()==null)){
				head = head.getNext(); // get to the end of the linkedList;
				if(head.getData().getBoardLayout().equals(data.getBoardLayout())) {
					throw new DictionaryException(null);
				}
			}
			head.setNext(inputNode);
			return 1; // a collision produced
		}
	}
	
	public void remove(String boardLayout) throws DictionaryException{
		if(T[h(boardLayout)]==null) { // case 1: empty position 
			throw new DictionaryException(boardLayout);
		}
		else if(T[h(boardLayout)].getNext()==null){ // case 2: position with no collision
			T[h(boardLayout)] = null;
		}
		else { // case 3: position with collision
			Node head = T[h(boardLayout)];
			if(head.getData().getBoardLayout().equals(boardLayout)) { // case 3.1: the node is the first one -> set a new head node
				T[h(boardLayout)] = head.getNext();
			}
			else {
				while(!(head.getNext().getData().getBoardLayout().equals(boardLayout))) { // case 3.2: the node is not the first one
					head = head.getNext();
				}	
				head.setNext(head.getNext().getNext());
			}
		}
	}
	
	public int getScore(String boardLayout) {
		if(T[h(boardLayout)]==null) {
			return -1;
		}
		else {
			Node head = T[h(boardLayout)];
			int score = head.getData().getScore();
			while(!(head.getNext()==null)) {
				if(score<head.getNext().getData().getScore()) {
					score=head.getNext().getData().getScore();
				}
				head = head.getNext();
			}
			return score;
		}		
	}
}
