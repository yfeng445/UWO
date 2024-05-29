/**
 * Sep 24, 2021
 * @author Yulun Feng
 * @ID 251113989
 *
 */
public class MostTimes {
		
    public int most_times(int[] A, int n){
    // Input: Array A storing n  integer values
    // Output: The value that appears the largest number of times in $A$. If several values appear
    //         in $A$ the largest number of times, the algorithm must return the smalles among these
    //         values.
    	int most = 0;
    	int counter = 0;
    	for(int i = 0; i<A.length;i++) {
    		int currCount = 0;
    		for(int j = 0;j<A.length;j++) {
    			if(A[i]==A[j]) {
    				currCount++;
    			}
    		}
    		if(currCount>counter) {
    			most = A[i];
    			counter = currCount;
    		}
    		else if(currCount==counter&&A[i]<most) {
    			most = A[i];
    		}
    	}
    	return most;
    }
}
