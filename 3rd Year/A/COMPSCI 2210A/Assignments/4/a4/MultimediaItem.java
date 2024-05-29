
/**
 * This class implements MultimediaItem stored in NodeData
 * @author Yulun Feng
 * @ID 251113989
 * @Date Nov 19, 2021
 *
 */


public class MultimediaItem {
	
	private String content;
	private int type;
	
	
	/**
	 * Constructor of the class
	 * @param newContent
	 * @param newType
	 */
	public MultimediaItem(String newContent, int newType) {
		this.content=  newContent;
		this.type = newType;
	}
	
	
	/**
	 * return content in the item
	 * @return
	 */
	public String getContent() {
		return content;
	}
	
	
	/**
	 * return type of the item
	 * @return
	 */
	public int getType() {
		return type;
	}
}
