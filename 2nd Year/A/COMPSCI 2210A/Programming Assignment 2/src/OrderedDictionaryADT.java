public interface OrderedDictionaryADT {
    /* Ordered Dictionary ADT */

    /* Returns the DataItem object with the Key as k, or it returns null if such 
       a DataItem is not in the dictionary. */
    public DataItem get (Key k);

    /* Inserts the DataItem d into the ordered dictionary. It throws a DictionaryException 
       if a DataItem with the same Key attribute as d is already in the dictionary. */
    public void put (DataItem d) throws DictionaryException;

    /*  Removes the DataItem with the same Key attribute as k from the dictionary. It throws a 
        DictionaryException if such a DataItem is not in the dictionary. */
    public void remove (Key k) throws DictionaryException;

    /* Returns the successor of k (the DataItem from the ordered dictionary 
       with smallest key larger than k); it returns null if the given key has
       no successor. Note that the given key k DOES NOT need to be in the dictionary. */
    public DataItem successor (Key k);

    /* Returns the predecessor of k (the DataItem from the ordered dictionary 
       with largest key smaller than k; it returns null if the given key has 
       no predecessor. Note that the given key k DOES NOT need to be in the dictionary.  */
    public DataItem predecessor (Key k);

    /* Returns the DataItem with smallest key in the ordered dictionary. 
       Returns null if the dictionary is empty.  */
    public DataItem smallest ();

    /* Returns the DataItem with largest key in the ordered dictionary. 
       Returns null if the dictionary is empty.  */
    public DataItem largest ();
}
		


