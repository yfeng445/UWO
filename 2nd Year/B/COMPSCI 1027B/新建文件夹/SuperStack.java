import java.util.Stack;


public class SuperStack {
	
	private Stack<String> defaultStack;
	private Stack<String> undoStack;
	private Stack<String> redoStack;
	
	
	public SuperStack() {
		
		defaultStack = new Stack<String>();
		undoStack = new Stack<String>();
		redoStack = new Stack<String>();
		
	}
	
	public void addItem(String item) {
		
		defaultStack.push(item);
		undoStack.push("Pop");
		
	}
	
	public String popItem() {
		
		String s = defaultStack.pop();
		
		undoStack.push(s);
		undoStack.push("Push");
		return s;
		
	}
	
	public void undo() {
		if(undoStack.isEmpty()) return;
		if(undoStack.peek().equals("Push")) {
			redoStack.push(undoStack.pop());
			defaultStack.pop();
		}
		else if(undoStack.peek().equals("Pop")) {
			redoStack.push(undoStack.pop());
			defaultStack.push();
		}
		
		
	}
	
	public void redo() {
		if(redoStack.isEmpty()) return;
		
		//TO BE DONE
		
	}
	
	public String toString(boolean defaultOnly) {
		
		if(defaultOnly) {
			return defaultStack.toString();
		}
		else {
			//TO BE DONE
		}
		
	}
	
	
	

}
