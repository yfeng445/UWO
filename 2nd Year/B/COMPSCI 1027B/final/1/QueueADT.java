/**
 * QueueADT defines the interface to a queue collection.
 *
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 8/12/08
 */

public interface QueueADT<T>{

   public void enqueue (T element);

   public T dequeue();

   public T first();

   public boolean isEmpty();

   public int size();

   public String toString();
}
