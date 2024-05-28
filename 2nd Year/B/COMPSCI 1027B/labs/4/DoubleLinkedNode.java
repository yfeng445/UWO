public class DoubleLinkedNode<E> {

	private DoubleLinkedNode<E> next;
	private DoubleLinkedNode<E> previous;
	private E element;

	public DoubleLinkedNode(){
		next = null;
		previous = null;
		element = null;
	}

	public DoubleLinkedNode (E elem){
		next = null;
		previous = null;
		element = elem;
	}

	public DoubleLinkedNode<E> getNext(){
		return next;
	}

	public DoubleLinkedNode<E> getPrevious(){
		return previous;
	}

	public void setNext (DoubleLinkedNode<E> node){
		next = node;
	}

	public void setPrevious (DoubleLinkedNode<E> node){
		previous = node;
	}

	public E getElement(){
		return element;
	}

	public void setElement (E elem){
		element = elem;
	}

}

