
public class ReversibleArray<T> {
	
	private T[] array;
	private int count;
	
	public ReversibleArray(T[] array){
		this.array = array;
		count = array.length;
	}
	
	public String toString() {
		String returnString = "";
		for(int i = 0;i<count;i++) {
			if(!(i==count-1)) {
				returnString = returnString+array[i]+", ";
			}
			else {
				returnString = returnString+array[i];
			}
		}
		return returnString;
	}
	
	public void reverse() {
		T temp;
		if(count%2==1) {
			for(int i = 0;i<(count-1)/2;i++) {
				temp = array[i];
				array[i] = array[count-1-i];
				array[count-1-i] = temp;
			}
		}
		else {
			for(int i = 0;i<count/2;i++) {
				temp = array[i];
				array[i] = array[count-1-i];
				array[count-1-i] = temp;
			}
		}
	}

}
