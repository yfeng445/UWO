
public class QueueCheckerTest {

	public static void main(String[] args) {

		LinkedQueue<String> strQueue = new LinkedQueue<String>();
		strQueue.enqueue("Alabama");
		strQueue.enqueue("California");
		strQueue.enqueue("Florida");
		strQueue.enqueue("Hawaii");
		strQueue.enqueue("Illinois");
		strQueue.enqueue("New York");
		strQueue.enqueue("Texas");
		QueueChecker<String> strQC = new QueueChecker<String>(strQueue);
		
		LinkedQueue<Integer> intQueue = new LinkedQueue<Integer>();
		intQueue.enqueue(42);
		intQueue.enqueue(30);
		intQueue.enqueue(24);
		intQueue.enqueue(17);
		intQueue.enqueue(13);
		intQueue.enqueue(9);
		intQueue.enqueue(4);
		QueueChecker<Integer> intQC = new QueueChecker<Integer>(intQueue);
		
		LinkedQueue<Double> dblQueue = new LinkedQueue<Double>();
		dblQueue.enqueue(7.4);
		dblQueue.enqueue(9.3);
		dblQueue.enqueue(14.0);
		dblQueue.enqueue(20.3);
		dblQueue.enqueue(12.6);
		dblQueue.enqueue(23.2);
		dblQueue.enqueue(3.5);
		QueueChecker<Double> dblQC = new QueueChecker<Double>(dblQueue);
		
		boolean t1, t2, t3;
		
		// checkAscending

		t1 = strQC.checkAscending() == true;
		t2 = intQC.checkAscending() == false;
		t3 = dblQC.checkAscending() == false;
		
		if (t1 && t2 && t3) {
			System.out.println("Test 1 Passed");
		} else {
			System.out.println("Test 1 Failed");
		}
		
		// checkDescending
		
		t1 = strQC.checkDescending() == false;
		t2 = intQC.checkDescending() == true;
		t3 = dblQC.checkDescending() == false;
		
		if (t1 && t2 && t3) {
			System.out.println("Test 2 Passed");
		} else {
			System.out.println("Test 2 Failed");
		}
		
		// checkOrdered
		
		t1 = strQC.checkOrdered() == 1;
		t2 = intQC.checkOrdered() == 2;
		t3 = dblQC.checkOrdered() == 0;
		
		if (t1 && t2 && t3) {
			System.out.println("Test 3 Passed");
		} else {
			System.out.println("Test 3 Failed");
		}

	}

}
