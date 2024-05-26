/**/

import java.util.*;

public class Dictionary implements DictionaryADT{
	
	Data[] Hashtable = new Data[9973];	//9973 is the biggest prime number smaller than 10000
	int count = 0;	//counts the times of collision
		
	public Dictionary(int size) {
		this.Hashtable = new Data[size];
	}
	
	/**
	 * 
	 * @param datakey
	 * @return hashkey the hash value of the given hash key
	 */
	public int Hashfunction(String datakey) {
		int hashkey = 0;
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		char[] Datakey = datakey.toCharArray();
		for(int j = 0;j<datakey.length();j++) {
			for(int i = 0;i<alphabet.length;i++) {
				if(alphabet[i] == Datakey[j]) {
					hashkey += i*j+i*(j+1)+(i+1)*j;
				}
			}
		}
 		return hashkey;

	}

	
	/**
	 * 
	 * @param record the given record
	 * @return count whether there's collision
	 * @return 
	 */
	public int put(Data record) throws DuplicatedKeyException{		
		//avoid case that there'e already record in the dictionary
		if(Hashtable[Hashfunction(record.getKey())] != null){
			count+=1;
			throw new DuplicatedKeyException("DuplicatedKeyException");
		}
		Hashtable[Hashfunction(record.getKey())] = record;
		return count;		
	}
	
	
	/**
	 * 
	 * @param key key value
	 */
	public void remove(String key) throws InexistentKeyException {
		if(Hashtable[Hashfunction(key)] != null)
		{
			Hashtable[Hashfunction(key)] = null;
		}
		else
		{
			throw new InexistentKeyException("Inexistent Key");
		}
	}
	
	
	/**
	 * @param key key value
	 * @return Hashfunction(key) data stored in the hashtable
	 */
	public Data get(String key) {
		return Hashtable[Hashfunction(key)];
	}
	
	
	/**
	 * @return count number of the item in the hash table
	 */
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
