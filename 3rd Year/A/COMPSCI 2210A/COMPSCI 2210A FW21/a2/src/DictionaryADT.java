public interface DictionaryADT 
{
    /* Inserts in the dictionary the Layout object referenced by 'data' in the dictionary. Throws
       a DictionaryException if the key attribute of 'data' is already in the dictionary  */
    public int put(Layout data) throws DictionaryException;

    /* Removes from the dictionary the Layout object with the same key attribute as parameter 'boardLayout'. 
       Throws a DictionaryException if there is no Layout object with key attribute equal to 'boardLayout'  
       in the dictionary.                                                                           */    
    public void remove(String boardLayout) throws DictionaryException;

    /* Returns the score in the Layout object with the same kay attribute as 'boardLayout'; returns 
       -1 if no Layout object in the dictionary has this key. */
    public int getScore(String boardLayout);

}
