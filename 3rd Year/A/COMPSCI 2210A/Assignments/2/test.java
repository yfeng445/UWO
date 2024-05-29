
public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//	System.out.println((737*Math.pow(31,9))%9887);
		//System.out.println((797*Math.pow(31,9))%9887);
	//	System.out.println(((int)Math.pow(31,15)*177)%9887);
		
		
		System.out.println(h("abcdefghijklhgnbtfgiyhdgjkgjkytjykgfgjyjikjghfhgfgfcjyhfkgyuyktffr5"));
	}
	
	
	public static int h(String str) {
		int output = 0;
		char[] arr = str.toCharArray();
		for(int i = 0;i<arr.length;i++) {
			int pv = (int) Math.pow(41, i)%9887;
			int cv =(int)arr[i];
			output = (output+cv*pv)%9987;
			System.out.println(output);
			}
		return output;
	}
}
/*
97
3507
5265
961
2624
7473
7473
7473
7473
7473
 * */
