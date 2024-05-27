/**
 * represent a data in list
 * @author  
 * @param <T> 
 */
public class CellData<T> {

    /**
     * data member
     */
    private T id;
    private int value;

    public CellData(T theId, int theValue) {
        id = theId;
        value = theValue;
    }

    public String toString() {
        return "" + getValue();
    }

    /**
     * @return the id
     */
    public T getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(T id) {
        this.id = id;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }

}
