import java.util.EmptyStackException;

public class ArrayStack<T> implements ArrayStackADT<T> {
	
	private T[] stack;
	private int top = 0;
	
	public static String sequence;
	
	
	public ArrayStack(){
		stack = (T[])(new Object[14]);
	}
	
	public ArrayStack(int initialCapacity) {
		stack = (T[])(new Object[initialCapacity]);
	}
	
	public void push(T dataItem) {
		if(top == stack.length) {
			expandCapacity();
		}
		
		stack[top] = dataItem;
		top++;
	}

	
	public T pop() throws EmptyStackException {
		if(isEmpty()) {
			System.out.println("FFFFFFFFFFFFFFIND AN ERROR");
			throw new EmptyStackException();
		}
		top--;
		T topItem = stack[top];
		stack[top] = null;
		return topItem;
	}

	
	public T peek() throws EmptyStackException {
		if(isEmpty()) {
			throw new EmptyStackException();
		}
		return stack[top-1];
	}

	
	public boolean isEmpty() {
		return (top == 0);
	}

	
	public int size() {
		return top;
	}
	
	
	public int length() {
		return stack.length;
	}
	
	
	public String toString() {
		String result = "Stack:\n";
		for (int i = 0;i<top;i++) {
			result = result+stack[i].toString()+"\n";
		}
		return result;
	}
	
	public void expandCapacity() {
		T[] larger = (T[])(new Object[stack.length*2]);
		for(int i = 0;i<stack.length;i++) {
			larger[i] = stack[i];
		}
		stack = larger;
		
	}

}
