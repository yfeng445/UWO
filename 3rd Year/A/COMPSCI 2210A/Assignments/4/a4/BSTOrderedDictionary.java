import java.util.*;

/**
 * This file implements BETOrderedDictionary and define methods in it
 * @author Yulun Feng
 * @ID 251113989
 * @Date Nov 19, 2021
 *
 */

public class BSTOrderedDictionary implements BSTOrderedDictionaryADT{
	
	private BSTNode root;
	private int numInternalNodes;
	
	/**
	 * Constructor of the class
	 */
	public BSTOrderedDictionary() {
		root = new BSTNode();
		numInternalNodes = 0;
	}
	
	
	/**
	 * return root of the tree
	 */
	public BSTNode getRoot() {
		return root;
	}
	
	
	/**
	 * return number of internal nodes
	 */
	public int getNumInternalNodes() {
		return numInternalNodes;
	}
	
	
	/**
	 * return MultimediaItem list store in the node having given key
	 */
	public ArrayList<MultimediaItem> get(BSTNode r, String key) {
		if(r.isLeaf()){
			return null;
		}
		else {
			if(key.compareTo(r.getData().getName())==0) {
				return r.getData().getMedia();
			}
			else if(key.compareTo(r.getData().getName())>0) {
				return get(r.getRightChild(), key);
			}
			else if(key.compareTo(r.getData().getName())<0) {
				return get(r.getLeftChild(), key);
			}
		}
		return null;
	}
	
	
	/**
	 * put a node into the tree
	 */
	public void put(BSTNode r, String name, String content, int type) {
		NodeData newData = new NodeData(name);
		newData.add(new MultimediaItem(content, type));
		if(r.isLeaf()) {
			r.setData(newData);
			r.setLeftChild(new BSTNode(r,null,null,null));
			r.setRightChild(new BSTNode(r,null,null,null));
			numInternalNodes++;
		}
		else {
			if(r.getData().getName().compareTo(name)==0) { 				
				r.data.add(new MultimediaItem(content, type));
			}
			else if(name.compareTo(r.getData().getName())>0) { //new name has larger value
				put(r.getRightChild(),name, content, type);
			}
			else if(name.compareTo(r.getData().getName())<0) {
				put(r.getLeftChild(),name, content, type);
			}
		}
	}
	
	
	/**
	 * remove a node from the tree
	 */
	public void remove(BSTNode r, String key) throws DictionaryException{	
		if(r.isLeaf()) {
			throw new DictionaryException(key);
		}
		else {
			if(key.compareTo(r.getData().getName())<0) { // looking for smaller value, go left
				remove(r.getLeftChild(), key);
			}
			else if(key.compareTo(r.getData().getName())>0) { // looking for larger value, go right
				remove(r.getRightChild(), key);
			}
			else { // find
				r.getData().getMedia().remove(r.getData().getMedia().size()-1);//
				if(r.getData().getMedia().isEmpty()) { // remove the node if the media list is empty
					BSTNode p = r.getParent();
					if(!r.getLeftChild().isLeaf()&&!r.getRightChild().isLeaf()) { // both children are not leaf
						r.setData(smallest(r.getRightChild()));
						r = r.getRightChild();
						while(!r.getLeftChild().isLeaf()) {
							r = r.getLeftChild();
						}
						remove(r,r.getData().getName());
					}
					else if(!(r.getLeftChild().isLeaf())||!(r.getRightChild().isLeaf())) { // one child
						BSTNode c = new BSTNode();
						if(r.getLeftChild().isLeaf()) {
							c = r.getRightChild();
						}
						if(r.getRightChild().isLeaf()) {
							c = r.getLeftChild();
						}
						if(p==null) { // remove root of the tree
							this.root=c;
						}
						else {
							if(p.getLeftChild().equals(r)) {
								p.setLeftChild(c);
								c.setParent(p);
							}
							if(p.getRightChild().equals(r)) {
								p.setRightChild(c);
								c.setParent(p);
							}
						}
						numInternalNodes--;
					}
					else {// no children
						if(p.getLeftChild().equals(r)) {
							p.setLeftChild(new BSTNode());
						}
						else {
							p.setRightChild(new BSTNode());
						}
						numInternalNodes--;
					}
				}
			}	
		}
	}
	
	
	
	/**
	 * remove a type of multimediaItem in the node
	 */
	public void remove(BSTNode r, String key, int type) throws DictionaryException{	
		if(r.isLeaf()) {
			throw new DictionaryException(key);
		}
		else {
			if(key.compareTo(r.getData().getName())<0) { // looking for smaller value, go left
				remove(r.getLeftChild(), key, type);
			}
			else if(key.compareTo(r.getData().getName())>0) { // looking for larger value, go right
				remove(r.getRightChild(), key, type);
			}
			else { // find
				int i = 0;
				while(i<r.getData().getMedia().size()) {
					if(r.getData().getMedia().get(i).getType()==type) {
						r.getData().getMedia().remove(i);
					}
					else {
						i++;
					}
				}
				if(r.getData().getMedia().size()==0) { // remove the node if the media list is empty
					BSTNode p = r.getParent();
					if(r.getLeftChild().isLeaf()||r.getRightChild().isLeaf()) { // one child
						BSTNode c = new BSTNode();
						if(r.getLeftChild().isLeaf()) {
							c = r.getRightChild();
						}
						if(r.getRightChild().isLeaf()) {
							c = r.getLeftChild();
						}
						if(p==null) { // remove root of the tree
							this.root=c;
						}
						else {
							if(p.getLeftChild().equals(r)) {
								p.setLeftChild(c);
								c.setParent(p);
							}
							if(p.getRightChild().equals(r)) {
								p.setRightChild(c);
								c.setParent(p);
							}
						}
					}
					else { // two children
						r.setData(smallest(r.getRightChild()));
						remove(r,smallest(r.getRightChild()).getName());
					}
				numInternalNodes--;					
				}
			}	
		}
	}	
	
	
	
	/**
	 * return node data from the smallest node in the tree
	 */
	public NodeData smallest(BSTNode r) {
		if(r.isLeaf()) {
			return null;
		}
		else {
			BSTNode p = r;
			while(!(p.isLeaf())){
				p = p.getLeftChild();
			}
			return(p.getParent().getData());
		}
	}
	
	
	
	/**
	 * return node data from the largest node in the tree
	 */
	public NodeData largest(BSTNode r) {
		if(r.isLeaf()) {
			return null;
		}
		else {
			BSTNode p = r;
			while(!(p.isLeaf())){
				p = p.getRightChild();
			}
			return(p.getParent().getData());
		}
	}	
	
	
	/**
	 * return node data from successor of the given key 
	 */
	public NodeData successor(BSTNode r, String key) {
		if(r.isLeaf()||r==null) {
			return null;
		}
		else {
			if(key.compareTo(r.getData().getName())<0) {
				return successor(r.getLeftChild(), key);
			}
			else if(key.compareTo(r.getData().getName())>0) {
				return successor(r.getRightChild(), key);
			}
			else { // find
				if(!(r.getRightChild().isLeaf())) {
					return smallest(r.getRightChild());
				}
				else {
					BSTNode p = r.getParent();
					while(!(p==null)&&r.equals(p.getRightChild())) {
						r = p;
						p = p.getParent();
					}
					if(p==null) {
						return null;
					}
					else {
						return p.getData();						
					}

				}
			}			
		}
	}
	
	
	/**
	 * return node data from predecessor of the given key
	 */
	public NodeData predecessor(BSTNode r, String key) {
		if(r.isLeaf()) {
			return null;
		}
		else {
			if(key.compareTo(r.getData().getName())<0) {
				return predecessor(r.getLeftChild(), key);
			}
			else if(key.compareTo(r.getData().getName())>0) {
				return predecessor(r.getRightChild(), key);
			}
			else { // find
				if(!(r.getLeftChild().isLeaf())) {
					return largest(r.getLeftChild());
				}
				else {
					BSTNode p = r.getParent();
					while(!(p==null)&&r.equals(p.getLeftChild())) {
						r = p;
						p = p.getParent();
					}
					return p.getData();
				}
			}			
		}
	}
}
