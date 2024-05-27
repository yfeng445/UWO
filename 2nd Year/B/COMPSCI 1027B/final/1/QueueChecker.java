
public class QueueChecker<T extends Comparable<? super T>> {
	
	private LinkedQueue<T> queue;
	
	public QueueChecker(LinkedQueue<T> queue) {
		this.queue = queue;
	}

	public boolean checkDescending() {
		int size = this.queue.size();
		T last = this.queue.dequeue();
		this.queue.enqueue(last);
		boolean result = true;
		for(int i=1;i<size;i++)
		{
			T cur = this.queue.dequeue();
			this.queue.enqueue(cur);
			if(last.compareTo(cur) <= 0)
				result = false;
			last = cur;
		}
		return result;
	}

	public int checkOrdered() {
		if(checkDescending())
			return 2;
		if(checkAscending())
			return 1;
		return 0;
	}

	public boolean checkAscending() {
		int size = this.queue.size();
		T last = this.queue.dequeue();
		this.queue.enqueue(last);
		boolean result = true;
		for(int i=1;i<size;i++)
		{
			T cur = this.queue.dequeue();
			this.queue.enqueue(cur);
			if(last.compareTo(cur) >= 0)
				result = false;
			last = cur;
		}
		return result;
	}
	
	
}
