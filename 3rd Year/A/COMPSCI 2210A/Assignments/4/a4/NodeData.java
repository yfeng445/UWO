import java.util.*;

/**
 * This class implements NodeData and methods 
 * @author Yulun Feng
 * @ID 251113989
 * @Data Nov 19, 2021
 */

public class NodeData {
	
	private String name;
	private ArrayList<MultimediaItem> media;
	
	
	/**
	 * Constructor of the class
	 */
	public NodeData(String newName) {
		this.name = newName;
		media = new ArrayList<MultimediaItem>();
	}
	
	
	/**
	 * add a new item to the list
	 * @param newItem
	 */
	public void add(MultimediaItem newItem) {
		media.add(newItem);
	}
	
	
	/**
	 * return key name in the Node
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	
	/**
	 * return media stored in the list stored in the Node
	 * @return
	 */
	public ArrayList<MultimediaItem> getMedia(){
		return media;
	}
	
}
