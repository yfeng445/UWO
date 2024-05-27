
import java.util.Random;

public class EvenOddSort {
	
	
	public static int[] oddSortedExtract (int[] array) {
		
		int n = 0;
		for(int i=0;i<array.length;i++)
		{
			if(array[i] % 2 == 1)
			{
				n++;
			}
		}
		int odds[] = new int[n];
		n = 0;
		for(int i=0;i<array.length;i++)
		{
			if(array[i] % 2 == 1)
			{
				odds[n++] = array[i];
			}
		}
		
		int holder;
		Random rand = new Random();
		
		while(true)
		{
			boolean sorted = true;
			for(int i=0;i<n - 1;i++)
			{
				if(odds[i] > odds[i + 1])
				{
					sorted = false;
				}
			}
			if(sorted)
				return odds;

			int randomNum1, randomNum2;
			
			//Example random code.
			randomNum1 = rand.nextInt(n);
			randomNum2 = rand.nextInt(n);
			
			holder = odds[randomNum1];
			odds[randomNum1] = odds[randomNum2];
			odds[randomNum2] = holder;
		}
		
		
		
	}
	
	public static int[] evenSortedExtract (int[] array) {
		

		int n = 0;
		for(int i=0;i<array.length;i++)
		{
			if(array[i] % 2 == 0)
			{
				n++;
			}
		}
		int evens[] = new int[n];
		n = 0;
		for(int i=0;i<array.length;i++)
		{
			if(array[i] % 2 == 0)
			{
				evens[n++] = array[i];
			}
		}
		
		int holder;
		Random rand = new Random();
		
		while(true)
		{
			boolean sorted = true;
			for(int i=0;i<n - 1;i++)
			{
				if(evens[i] > evens[i + 1])
				{
					sorted = false;
				}
			}
			if(sorted)
				return evens;

			int randomNum1, randomNum2;
			
			//Example random code.
			randomNum1 = rand.nextInt(n);
			randomNum2 = rand.nextInt(n);
			
			holder = evens[randomNum1];
			evens[randomNum1] = evens[randomNum2];
			evens[randomNum2] = holder;
		}
		

	}
	
	public static int[] mergeArrays(int[] evenArray, int[] oddArray) {
		
		int res[] = new int[evenArray.length + oddArray.length];
		int en = 0;
		int on = 0;
		int n = 0;
		
		while(true)
		{
			if(en >= evenArray.length && on >= oddArray.length)
				break;
			if(en >= evenArray.length)
				res[n++] = oddArray[on++];
			else if(on >= oddArray.length)
				res[n++] = evenArray[en++];
			else if(evenArray[en] <= oddArray[on])
				res[n++] = evenArray[en++];
			else
				res[n++] = oddArray[on++];
		}
		return res;
	}
	
	
	

}
