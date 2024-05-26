import java.util.*;

public class DictionaryOld implements DictionaryADT{
	
	Data[] Hashtable = new Data[9973];	//9973 is the biggest prime number smaller than 10000
	int count = 0;	//counts the times of collision
		
	public DictionaryOld(int size) {
		this.Hashtable = new Data[size];
	}
	
	
	//Hash Function
	public int Hashfunction(String datakey) {
		int hashkey = 0;
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		char[] Datakey = datakey.toCharArray();
		for(int j = 0;j<datakey.length();j++) {
			for(int i = 0;i<26;i++) {
				if(alphabet[i] == Datakey[j]) {
					hashkey += i*j+i*(j+1)+(i+1)*j;
				}
			}
		}
 		return hashkey;		
	}

	
	
	public int put(Data record) throws DuplicatedKeyException{		
		//avoid case that there'e already record in the dictionary
		if(!(Hashtable[Hashfunction(record.key)].equals(null))){
			count+=1;
			throw new DuplicatedKeyException("DuplicatedKeyException");
		}
		return count;		
	}
	
	
	
	public void remove(String key) throws InexistentKeyException {
		if(Hashtable[Hashfunction(key)].equals(null)){
			throw new InexistentKeyException("InexistentKeyException");
		}
	}
	
	
	
	public Data get(String key) {
		return Hashtable[Hashfunction(key)];
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
