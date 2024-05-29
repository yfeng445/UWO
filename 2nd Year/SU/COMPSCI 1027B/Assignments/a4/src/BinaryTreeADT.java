/**
 * BinaryTreeADT defines the interface to a binary tree data structure.
 *
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 8/19/08
 */
import java.util.Iterator;

public interface BinaryTreeADT<T> 
{
   /** 
    * Returns a reference to the root element 
    *
    * @return               a reference to the root
    */
   public T getDataRoot ();

   /**
    * Returns a reference to root node
    *
    * @return                           a reference to the root node
    */
   public BinaryTreeNode<T> getRoot();    
   
   /** 
    * Returns true if this binary tree is empty and false otherwise.
    *
    * @return  true if this binary tree is empty
    */
   public boolean isEmpty();

   /** 
    * Returns the number of nodes in the binary tree with the specified root.
    *
    * @return  the integer number of nodes in this tree
   */
   public int size(BinaryTreeNode<T> r);

   /**
    * Returns true if the tree with root r contains an element that matches the
    * specified target element and false otherwise.
    *
    * @param r							root r of a binary tree
    * @param targetElement              the element being sought in this tree
    * @return                           true if the element in is this tree
    * @throws ElementNotFoundException  if an element not found exception occurs
    */
   public boolean contains (BinaryTreeNode<T> r, T targetElement);

   /**  
    * Returns the string representation of the binary tree.
    *
    * @return  a string representation of the binary tree
    */
   public String toString();

   /**  
    * Performs an inorder traversal on this binary tree by calling an 
    * overloaded, recursive inorder method that starts with the root. 
    *
    * @return  an iterator over the elements of this binary tree
    */
   public Iterator<T> iteratorInOrder();

   /**  
    * Performs a preorder traversal on this binary tree by calling an 
    * overloaded, recursive preorder method that starts with the root. 
    *
    * @return  an iterator over the elements of this binary tree
    */
   public Iterator<T> iteratorPreOrder();

   /**  
    * Performs a postorder traversal on this binary tree by calling an 
    * overloaded, recursive postorder method that starts with the root. 
    *
    * @return  an iterator over the elements of this binary tree
    */
   public Iterator<T> iteratorPostOrder();

   /**  
    * Performs a levelorder traversal on the binary tree, using a queue.
    *
    * @return  an iterator over the elements of this binary tree
    */
   public Iterator<T> iteratorLevelOrder();
}

