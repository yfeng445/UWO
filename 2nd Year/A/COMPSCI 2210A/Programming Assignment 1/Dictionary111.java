import java.util.*;

public class Dictionary111 implements DictionaryADT{
	
	Data[][] Hashtable = new Data[9973][];	//9973 is the biggest prime number smaller than 10000
		
	public Dictionary111(int size) {
		this.Hashtable = new Data[size][10000];
	}
	
	
	//Hash Function1
	public int HashFunction1(String datakey) {
		int hashkey = 0;
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
		char[] Datakey = datakey.toCharArray();
		for(int j = 0;j<datakey.length();j++) {
			for(int i = 0;i<alphabet.length;i++) {
				if(alphabet[i] == Datakey[j]) {
					hashkey += (i*j+(1+i)*j+(1+j)*i+(1+i)*(1+j));
				}
			}
		}
		System.out.print("HushFunction1: \n");
		System.out.print(hashkey);
		System.out.print("\n");
 		return hashkey;		
	}
	
	//Hash Function2
	public int HashFunction2(String datakey) {
		int hashkey = 0;
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
		char[] Datakey = datakey.toCharArray();
		for(int j = 0;j<datakey.length();j++) {
			for(int i = 0;i<36;i++) {
				if(alphabet[i] == Datakey[j]) {
					hashkey += (i+3*j);
				}
			}
		}
		System.out.print("Hashfunction2: \n");
		System.out.print(hashkey);
		System.out.print("\n");
 		return hashkey;		
	}
	
	
	
	public int put(Data record) throws DuplicatedKeyException{		
		int count = 0;
		//avoid case that there'e already record in the dictionary
		if(Hashtable[HashFunction1(record.getKey())][0] != null){
			count=1; 
			if(Hashtable[HashFunction1(record.getKey())][HashFunction2(record.getKey())].equals(Hashtable[HashFunction1(record.getKey())][0])) {
				System.out.print("Exception[key][0]");
				throw new DuplicatedKeyException("DuplicatedKeyException");
			}
			else if(Hashtable[HashFunction1(record.getKey())][HashFunction2(record.getKey())] != null){
				System.out.print("Exception[key][key]");
				throw new DuplicatedKeyException("DuplicatedKeyException");
			}
		}
		Hashtable[HashFunction1(record.getKey())][0] = record;
		return count;		
	}
	
	
	
	public void remove(String key) throws InexistentKeyException {
		if(Hashtable[HashFunction1(key)] != null)
		{
			if(numDataItems() == 1){
				Hashtable[HashFunction1(key)][0] = null;
			}
			else {
				Hashtable[HashFunction1(key)][HashFunction2(key)] = null;
			}
		}
		else
		{
			throw new InexistentKeyException("Inexistent Key");
		}
	}
	
	
	
	public Data get(String key) {
		int single = 1;
		for(int i = 0;i<Hashtable.length;i++) {
			if(!(Hashtable[HashFunction1(key)][i].equals(null))) {
				single = 0;
			}
		}
		if(single == 1){
			return Hashtable[HashFunction1(key)][0];
		}
		else {
			return Hashtable[HashFunction1(key)][HashFunction2(key)];
		}
	}
	
	
	
	public int numDataItems(){
		int count = 0;
		for(int i = 0;i<Hashtable.length;i++) {
			if(!(Hashtable[i]).equals(null)) {
				count+=1;
			}
		}
		return count;
	}


}
