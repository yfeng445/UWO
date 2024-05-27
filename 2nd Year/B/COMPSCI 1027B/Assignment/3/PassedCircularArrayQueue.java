
public class CircularArrayQueue<T> {
	
	private int front,rear,count;
	private T[] Queue;
	private final int DEFAULT_CAPACITY = 20;
	
	public CircularArrayQueue(){
		front = 1;
		rear = DEFAULT_CAPACITY;
		count = 0;
		Queue = (T[])(new Object[DEFAULT_CAPACITY]);
		}
	
	public CircularArrayQueue(int initialCapacity) {
		front = 1;
		rear = initialCapacity;
		count = 0;
		Queue = (T[])(new Object[initialCapacity]);
	}
	
	public void enqueue(T element) {
		if(count==Queue.length) expandCapacity();
		Queue[count] = element;
		rear = (rear+1)%Queue.length;
		count++;
	}
	
	public T dequeue() throws EmptyCollectionException{
		if(isEmpty()) throw new EmptyCollectionException("Empty Queue");
		
	    T result = Queue[front-1];
	    Queue[front-1] = null;
	    front = (front+1) % Queue.length;
	    
	    count--;
	    
	    return result;
		
	}
	
	public T first() throws EmptyCollectionException{
		if(isEmpty()) {
			throw new EmptyCollectionException("Empty Queue");
		}
		else {
			return Queue[0];
		}
	}
	
	public int size() {
		return count;
	}
	
	public boolean isEmpty() {
		return count==0;
	}
	
	public Integer getFront() {
		return front;
	}
	
	public Integer getRear() {
		return rear;
	}
	
	public int getLength() {
		return Queue.length;
	}
	
	public String toString() {
		String returnString = "QUEUE: ";
		if(isEmpty()) {
			return "The queue is empty";
		}
		else {
			for(int i = 0;i<count-1;i++) {
				returnString = returnString+Queue[i]+", ";
			}
			returnString =returnString+Queue[count-1]+".";
			return returnString;
		}

	}
	
	private void expandCapacity() {
		T[] newQueue = (T[])(new Object[Queue.length+20]);
		for(int i = 0;i<count;i++) {
			newQueue[i] = Queue[i];
		}
		Queue = newQueue;
		rear = count;
	}

/*    private void expandCapacity(){
        T[] larger = (T[])(new Object[Queue.length+20]);
    
        for (int i = 0; i < count; i++){
            larger[i] = Queue[front];
            front = (front + 1) % Queue.length;
        }
        front = 0;
        rear = count;
        Queue = larger;
    }*/
}
