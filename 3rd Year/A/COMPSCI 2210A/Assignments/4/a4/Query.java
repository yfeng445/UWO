import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This file provides methods for users to get access and adjust data in tree
 * @author Yulun Feng
 * @ID 251113989
 * @Date Nov 19, 2021
 *
 */


public class Query {
	
	/**
	 * This method returns the BSTNode with given key
	 * @param r root of the tree/subtree
	 * @param key 
	 * @return BSTNode with given key
	 */
	private static BSTNode getNode(BSTNode r, String key) {
		if(r.isLeaf()) {
			return r;
		}
		else {
			if(r.getData().getName().compareTo(key)>0) {
				return getNode(r.getLeftChild(),key);
			}
			else if(r.getData().getName().compareTo(key)<0){
				return getNode(r.getRightChild(),key);
			}
			else {
				return r;
			}
		}
	}
	
	
	/**
	 * This method provides operations to access the tree
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		StringReader fileName = new StringReader();
		String input = fileName.read("Please enter file name: ");
		BSTOrderedDictionary dict = new BSTOrderedDictionary();			
		BSTNode r = dict.getRoot();
		BufferedReader in;
	   //input file data
		try {
			in = new BufferedReader(new FileReader("src/"+input));
		    String key = in.readLine();
		    String s = in.readLine();
		    while(!(key==null||s==null)) {
			    if(s.contains(".wav")||s.contains(".mid")){
			   		dict.put(r, key, s, 2);
			   	}
			   	else if(s.contains(".gif")) {
			   		dict.put(r, key, s, 3);
		    	}
		    	else if(s.contains(".html")) {
		    		dict.put(r, key, s, 4);
			    }
			   	else {
			   		dict.put(r, key, s, 1);
			   	}
			   	key = in.readLine();
		    	s = in.readLine();
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		// request for user command
		String cmd = "";
		while(!cmd.equals("end")) {
			try {
				//process input command
				cmd = new StringReader().read("Enter next command: ").toLowerCase();
				String[] keys = cmd.split(" ");
				
				// get k
				if(cmd.contains("get")) {
					String key = keys[1];
					if(dict.get(r, key)==null) {
						System.out.println("Key "+key+" is not in the ordered dictionary.");
					}
					else {	
						String str = "[\""+key+"\", <";
						for(int i = 0; i<dict.get(r, key).size();i++) {
							MultimediaItem D = dict.get(r, key).get(i);
							if(D.getType()==1) {
								System.out.println(D.getContent());
							}
							if(D.getType()==2) {
								SoundPlayer player= new SoundPlayer();
								player.play("src/"+D.getContent());
							}
							if(D.getType()==3) {
							    PictureViewer viewer = new PictureViewer();
								viewer.show("src/"+D.getContent());
							}
							if(D.getType()==4) {
								ShowHTML shower = new ShowHTML();
								shower.show("src/"+D.getContent());
							}
							str=str+"(\""+D.getContent()+"\", "+D.getType()+"),";
						}
						System.out.println(str.substring(0, str.length()-1)+">]");
					}
				}
				
				//remove k
				else if(cmd.contains("remove")) {
					String key = keys[1];
					if(dict.get(r, key)==null) {
						System.out.println("No record in the ordered dictionary has key "+key+".");
					}
					else {
						dict.remove(r, key);
					}
				}
				
				// add k c t
				else if(cmd.contains("add")) {
					dict.put(r, keys[1], keys[2], Integer.valueOf(keys[3]));
				}
				
				//next k d
				else if(cmd.contains("next")) {
					String str = "";
					int count = 0;
					if(!(getNode(r, keys[1]).isLeaf())) { 
						str+=getNode(r, keys[1]).getData().getName()+" ";	
					}
					else {//The node is not exist and the pointer is currently on a leaf node
						getNode(r, keys[1]).setData(new NodeData(keys[1])); //store key value	
						getNode(r, keys[1]).setLeftChild(new BSTNode());
						getNode(r, keys[1]).setRightChild(new BSTNode());
					}					
					NodeData data = dict.successor(r, keys[1]);
					while(count<Integer.valueOf(keys[2])) {	
						if(data==null) { //No successor left
							if(count == 0) {
								System.out.println("There is no any key larger or equal to set.");
							}
							break;
						}
						else {
							BSTNode succ = getNode(r,data.getName());							
							str+=succ.getData().getName()+" ";
							data = dict.successor(r, data.getName());
							count++;
						}
					}
					System.out.println(str);
				}
				
				// prev k d
				else if(cmd.contains("prev")) {
					String str = "";
					int count = 0;
					if(!(getNode(r, keys[1]).isLeaf())) { 
						str+=getNode(r, keys[1]).getData().getName()+" ";	
					}
					else {//The node is not exist and the pointer is currently on a leaf node
						getNode(r, keys[1]).setData(new NodeData(keys[1])); //store key value	
						getNode(r, keys[1]).setLeftChild(new BSTNode());
						getNode(r, keys[1]).setRightChild(new BSTNode());
					}					
					NodeData data = dict.predecessor(r, keys[1]);
					while(count<Integer.valueOf(keys[2])) {	
						if(data==null) { //No successor left
							if(count == 0) {
								System.out.println("There is no any key smaller or equal to set.");
							}
							break;
						}
						else {
							BSTNode pred = getNode(r,data.getName());							
							str+=pred.getData().getName()+" ";
							data = dict.predecessor(r, data.getName());
							count++;
						}
					}
					System.out.println(str);
				}
				
				//first
				else if(cmd.equals("first")) {
					if(r.isLeaf()){
						System.out.println("The ordered dictionary is empty");
					}
					else {
						System.out.println(dict.smallest(r).getName());						
					}
				}
				
				//last
				else if(cmd.contains("last")) {
					if(r.isLeaf()) {
						System.out.println("The ordered dictionary is empty");
					}
					else {
						System.out.println(dict.largest(r).getName());
					}
				}
				
				//size
				else if(cmd.contains("size")) {
					if(dict.getNumInternalNodes()==0) {
						System.out.println("There is not any key in the ordered dictionary");
					}
					else if(dict.getNumInternalNodes()==1) {
						System.out.println("There is 1 key in the dictinoary");
					}
					else {
						System.out.println("There are "+dict.getNumInternalNodes()+" keys in the dictionary");						
					}
				}
				
				//end
				else if(cmd.equals("end")) {
					System.out.println("System terminated.");					
					break;
				}
				
				//other inputs are considered invalid
				else {
					System.out.println("Invalid command");
				}					
			}
		catch(Exception e) {//dealing with cases that have correct command but incorrect args
				System.out.println("Invalid command");
			}
		}
	}
}
