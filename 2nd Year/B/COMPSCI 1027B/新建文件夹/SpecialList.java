
public class SpecialList {

	private LinearNode<Integer> front;
	private int d;

	public SpecialList(int d) {
		this.d = d;
		front = null;
		
	}
	
	public LinearNode<Integer> getFront() {
		return front;
	}
	
	public void addToList(Integer element) {
		LinearNode<Integer> curr = new LinearNode<Integer>(element);
		if(front == null) {
			curr.setNext(front);
			front = curr;
		}

		else {
			if(element<d) {
				curr.setNext(front);
				front = curr;		
			}
			else if(element>d){
				while(front.getNext()!=null) {
					front = front.getNext();
				}
				front.setNext(curr);
				front = curr;
			}
			else {}
		}

	}
}
