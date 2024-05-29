
public class TestSuperStack {
	
	
	public static void main(String[] args) {
		
		
		//Test 1. This test checks to see whether the undo function works.
		
		SuperStack s = new SuperStack();
		
		s.addItem("Action A");
		s.addItem("Action B");
		s.undo();
		if (s.toString(true).equals("[Action A]")) {
			System.out.println("Test 1 Passed");
		}
		else {
			System.out.println("Test 1 Failed");
			System.out.println("Received: " + s.toString(true) + " and expected [Action A]");
		}
		
		
		//Test 2. This test checks to see whether the redo function works.
		SuperStack r = new SuperStack();
		r.addItem("Action A");
		r.addItem("Action B");
		
		
		r.undo();	
		r.redo();
		
		
		
		if (r.toString(true).equals("[Action A, Action B]")) {
			System.out.println("Test 2 Passed");
		}
		else {
			System.out.println("Test 2 Failed");
			System.out.println("Received: " + r.toString(true) + " and expected [Action A, Action B]");
		}
		
		//Test 3. Checks to see whether the toString for all stacks works as specified AND the previous two work.
		
		SuperStack q = new SuperStack();
		
		q.addItem("Action A");
		q.addItem("Action B");
		q.addItem("Action C");
		
		q.popItem();
		q.undo();
		q.redo();
		
		String x = q.toString(false);
		
		if(x.equals("[Action A, Action B]\n" + "[Pop, Pop, Pop, Action C, Push]\n" + "[]")) {
			System.out.println("Test 3 Passed");
		}
		else {
			System.out.println("Test 3 Failed with value given of " + x + " but a result of:\n" + "[Action A, Action B]\n" + "[Pop, Pop, Pop, Action C, Push]\n" + "[]" + "\nWas expected");
		}
		
		
		
		
	}

}
