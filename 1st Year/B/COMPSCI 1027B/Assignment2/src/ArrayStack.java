
public class ArrayStack<T> implements ArrayStackADT<T> {
	//stack: 先进后出 后进先出
	
	private T[] stack;
		// T type: 任意数据类型 String， int, ect...
		//但是一个array只能保存一种数据类型
	private int top;
	private int initialCapacity;
	private int sizeIncrease;
	private int sizeDecrease;
	private int n;
	
	public ArrayStack(int initialCap, int sizeInc, int sizeDec) {
		stack = (T[])(new Object[initialCap]); //创建一个大小为initialcap的array
		initialCapacity = initialCap;
		sizeIncrease = sizeInc;
		sizeDecrease = sizeDec;
		//把这三个变量变成全局变量
		
		top = -1;
		n = top+1;
	}
	
	public void puch(T dataItem) {
		if (n == stack.length) {
			//top+1 为stack里保存东西的数量
			//stack.length表示stack长度，即stack的储存容量
			//相等即意味stack内存已满，需要增加内存
			T[] newStack = (T[])(new Object[stack.length+sizeIncrease]);
			//创建一个新的array并复制原有array内容
			//长度改为length+addlength
			for (int i = 0; i<stack.length;i++) {
				newStack[i] = stack[i];
			}
			stack = newStack;
			//替换为新的stack
		}			
			stack[n] = dataItem;
			top+=1;
	}
	
	public T pop() throws EmptyStackException{
		if (n == 0) {
			//判定stack是否为空
			//如果为空，应出现
			throw EmptyStackException();
		}
		T result = stack[top];//备份删除的内容
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
		//没有备份就无法return了	
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
