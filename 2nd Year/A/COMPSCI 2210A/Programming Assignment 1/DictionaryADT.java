
public interface DictionaryADT {
	
    public int put (Data record) throws DuplicatedKeyException;

    public void remove (String key) throws InexistentKeyException;

    public Data get (String key);

    public int numDataItems();
}
