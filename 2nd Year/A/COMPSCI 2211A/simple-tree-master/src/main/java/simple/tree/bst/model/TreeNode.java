package simple.tree.bst.model;

/**
 * @Author: Q-WHai
 * @Date: Created in 16:21 2019/04/04
 */
public class TreeNode implements INode {

    private int val;
    private TreeNode left;
    private TreeNode right;
    private TreeNode parent;

    public TreeNode(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }
}
