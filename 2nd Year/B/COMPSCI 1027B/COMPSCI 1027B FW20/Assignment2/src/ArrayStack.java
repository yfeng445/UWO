
public class ArrayStack<T> implements ArrayStackADT<T> {
	//stack: �Ƚ���� ����ȳ�
	
	private T[] stack;
		// T type: ������������ String�� int, ect...
		//����һ��arrayֻ�ܱ���һ����������
	private int top;
	private int initialCapacity;
	private int sizeIncrease;
	private int sizeDecrease;
	private int n;
	
	public ArrayStack(int initialCap, int sizeInc, int sizeDec) {
		stack = (T[])(new Object[initialCap]); //����һ����СΪinitialcap��array
		initialCapacity = initialCap;
		sizeIncrease = sizeInc;
		sizeDecrease = sizeDec;
		//���������������ȫ�ֱ���
		
		top = -1;
		n = top+1;
	}
	
	public void puch(T dataItem) {
		if (n == stack.length) {
			//top+1 Ϊstack�ﱣ�涫��������
			//stack.length��ʾstack���ȣ���stack�Ĵ�������
			//��ȼ���ζstack�ڴ���������Ҫ�����ڴ�
			T[] newStack = (T[])(new Object[stack.length+sizeIncrease]);
			//����һ���µ�array������ԭ��array����
			//���ȸ�Ϊlength+addlength
			for (int i = 0; i<stack.length;i++) {
				newStack[i] = stack[i];
			}
			stack = newStack;
			//�滻Ϊ�µ�stack
		}			
			stack[n] = dataItem;
			top+=1;
	}
	
	public T pop() throws EmptyStackException{
		if (n == 0) {
			//�ж�stack�Ƿ�Ϊ��
			//���Ϊ�գ�Ӧ����
			throw EmptyStackException();
		}
		T result = stack[top];//����ɾ��������
		stack[top] = null;
		top-=1;
		if (n<stack.length/4 && stack.length<initialCapacity) {
			T[] newStack = (T[])(new Object[stack.length-sizeDecrease]);
			for (int i = 0; i<stack.length; i++) {
				newStack[i] = stack[i];
			}
			stack = newStack;
		}
		return result;
		//û�б��ݾ��޷�return��	
	}
	
	 public T peek() throws EmptyStackException{
		 return stack[n];
	 }
	 
	 public boolean isEmpty() {
		 if(n == 0) return true;
		 else return false; 
	 }
	 
	 public int size() {
		 return n;
	 }
	 
	 public int length() {
		 return stack.length;
	 }
	 
	 public String toString() {
		 String output = "Stack: ";
		 for (int i=0; i<n; i++) {
			 output +=stack[i].toString();
			 if (i !=n-1) {
				 output += ", ";
			 }
		 }
		 return output;
	 }
	
}
