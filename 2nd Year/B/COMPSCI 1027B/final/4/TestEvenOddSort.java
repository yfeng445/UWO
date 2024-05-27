
public class TestEvenOddSort {
	
	
public static void main(String[] args) {
		
		int[] arr = {3,5,1,2,5,7,9,11,2,2,2,3,4};
		int[] arr2 = {11,9,1,3,4,4,3,2,1,9,8,7};
		
		
		// Test 1: Odd Sorted Extract Test
		int[] oddArr = EvenOddSort.oddSortedExtract(arr);
		int[] oddArr2 = EvenOddSort.oddSortedExtract(arr2);
		
		String output1 = "";
		String output2 = "";
		
		for (int x = 0; x < oddArr.length; x++) {
			output1 += oddArr[x];
			output1 += " ";
		}
		
		for (int x = 0; x < oddArr2.length; x++) {
			output2 += oddArr2[x];
			output2 += " ";
		}
		
		if(output1.equals("1 3 3 5 5 7 9 11 ") && output2.equals("1 1 3 3 7 9 9 11 ")){
			System.out.println("Test 1 Passed");
		}
		
		//System.out.println(output1 + "\n" + output2);
		
		//Test 2 Even Sorted Extract Test
		
	    int [] evenArr = EvenOddSort.evenSortedExtract(arr);
	    int [] evenArr2 = EvenOddSort.evenSortedExtract(arr2);
	    
	    output1 = "";
	    output2 = "";
	    
	    for (int x = 0; x < evenArr.length; x++) {
			output1 += evenArr[x];
			output1 += " ";
		}
		
		for (int x = 0; x < evenArr2.length; x++) {
			output2 += evenArr2[x];
			output2 += " ";
		}
		
		if(output1.equals("2 2 2 2 4 ") && output2.equals("2 4 4 8 ")){
			System.out.println("Test 2 Passed");
		}
		
		//System.out.println(output1 + "\n" + output2);
	    
	    //System.out.println("");
	    
	    // Test 3 Merge Array Test
		
	    int[] testArr = EvenOddSort.mergeArrays(oddArr,evenArr);
	    int[] testArr2 = EvenOddSort.mergeArrays(oddArr2, evenArr2);
	    String mergeString = "";
	    String mergeString2 = "";
	    
	    for (int x = 0; x < testArr.length; x++) {
			mergeString += testArr[x];
			mergeString += " ";
		}
	    for (int x = 0; x < testArr2.length; x++) {
			mergeString2 += testArr2[x];
			mergeString2 += " ";
		}
	    
	    //System.out.println(mergeString);
	    //System.out.println(mergeString2);
	    
	    if(mergeString.equals("1 2 2 2 2 3 3 4 5 5 7 9 11 ") && mergeString2.equals("1 1 2 3 3 4 4 7 8 9 9 11 ")) {
	    	System.out.println("Test 3 Passed");
	    }
		
	}
	

}
