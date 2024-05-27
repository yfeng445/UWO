public class DoubleNode<E> {

	private DoubleNode<E> next;
	private DoubleNode<E> previous;
	private E element;

	public DoubleNode(){
		next = null;
		previous = null;
		element = null;
	}

	public DoubleNode (E elem){
		next = null;
		previous = null;
		element = elem;
	}

	public DoubleNode<E> getNext(){
		return next;
	}

	public DoubleNode<E> getPrevious(){
		return previous;
	}

	public void setNext (DoubleNode<E> node){
		next = node;
	}

	public void setPrevious (DoubleNode<E> node){
		previous = node;
	}

	public E getElement(){
		return element;
	}

	public void setElement (E elem){
		element = elem;
	}
	
	public String toString () {
		return element.toString();
	}

}

