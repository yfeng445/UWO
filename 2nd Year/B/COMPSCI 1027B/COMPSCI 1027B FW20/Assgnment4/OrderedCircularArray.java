
/**
 * a circular queue.
 *
 * @author
 * @param <T>
 */
public class OrderedCircularArray<T> implements SortedListADT<T> {

//    public static void main(String args[]) {
//        OrderedCircularArray<String> c = new OrderedCircularArray();
//
//        c.insert("1", 1);
//        c.insert("8", 8);
//        c.insert("5", 5);
//        c.insert("6", 6);
//
//        c.insert("2", 2);
//
//        c.insert("12", 12);
//        c.insert("22", 22);
//        c.insert("8", 8);
//        c.insert("9", 9);
//        c.remove("6");
//
//    }
    // 
    /**
     * This array will store the data items of the ordered list
     */
    private CellData[] list;

    /**
     * This variable stores the position of the first data item in the list
     * initialized to 1.
     */
    private int front;

    /**
     * This is the index of the last data item in the ordered list. the largest
     * value
     */
    private int rear;

    /**
     * The value of this variable is equal to the number of data items in the
     * list.
     */
    private int count;

    public OrderedCircularArray() {
        list = new CellData[5];
        front = 1;
        rear = 0;
        count = 0;
    }

    /**
     * remove the element
     *
     * @param id
     * @throws InvalidDataItemException
     */
    public void remove(T id) throws InvalidDataItemException {

        int len = list.length;

        CellData[] table = new CellData[len];
        for (int i = 0; i < count; i++) {
            table[i] = list[(i + front) % len];
        }
        /////////////////////////

        int m = 0;
        for (m = 0; m < count; m++) {

            if (table[m].getId().equals(id)) {
                break;
            }

        }
        if (m == count) {
            throw new InvalidDataItemException("ID NOT FOUND");
        }

        for (; m < count - 1; m++) {
            table[m] = table[m + 1];
        }
        count--;

        ////////////////////////////
        for (int i = 0; i < count; i++) {
            list[(i + front) % len] = table[i];

        }
        rear = (front + count - 1 + len) % len;

    }

    /**
     * double the size
     */
    private void resize() {
        int len = list.length;
        CellData[] table = new CellData[len];
        for (int i = 0; i < count; i++) {
            table[i] = list[(i + front) % len];
        }
        /////////////////////////
        len *= 2;
        CellData[] newList = new CellData[len];

        ////////////////////////////
        for (int i = 0; i < count; i++) {
            newList[(i + front) % len] = table[i];

        }
        rear = (front + count - 1 + len) % len;
        list = newList;
    }

    /**
     * add new element
     *
     * @param dataItem
     * @param value
     */
    @Override
    public void insert(T dataItem, int value) {

        if (count >= list.length) {
            resize();
        }

        int len = list.length;

        CellData[] table = new CellData[len];
        for (int i = 0; i < count; i++) {
            table[i] = list[(i + front) % len];
        }
        /////////////////////////

        int m = 0;
        for (m = 0; m < count; m++) {
            if (value <= table[m].getValue()) {
                break;
            }
        }

        for (int k = count - 1; k >= m; k--) {
            table[k + 1] = table[k];
        }
        table[m] = new CellData(dataItem, value);
        count++;

        ////////////////////////////
        for (int i = 0; i < count; i++) {
            list[(i + front) % len] = table[i];

        }
        rear = (front + count - 1 + len) % len;
    }

    /**
     * remove the smallest element
     *
     * @return
     * @throws EmptyListException
     */
    @Override
    public T getSmallest() throws EmptyListException {
        if (isEmpty()) {
            throw new EmptyListException("Empty List");
        }
        T ret = (T) list[front].getId();
        front = (front + 1) % list.length;
        count--;
        return ret;
    }

    /**
     * change the value
     *
     * @param dataItem
     * @param newValue
     * @throws InvalidDataItemException
     */
    @Override
    public void changeValue(T dataItem, int newValue) throws InvalidDataItemException {
        this.remove(dataItem);
        this.insert(dataItem, newValue);
    }

    @Override
    public int getValue(T dataItem) throws InvalidDataItemException {
        int len = list.length;
        for (int i = 0; i < count; i++) {
            if (list[(i + front) % len].getId().equals(dataItem)) {
                return list[(i + front) % len].getValue();
            }
        }
        throw new InvalidDataItemException("ID NOT FOUND");
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public int getFront() {
        return front;
    }

    @Override
    public int getRear() {
        return rear;
    }

}
