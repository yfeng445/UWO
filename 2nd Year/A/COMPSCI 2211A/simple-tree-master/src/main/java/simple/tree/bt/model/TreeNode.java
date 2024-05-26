package simple.tree.bt.model;

/**
 * @Author: Q-WHai
 * @Date: Created in 09:54 2019/04/03
 */
public class TreeNode implements INode {

    private int val;
    private INode left;
    private INode right;

    public TreeNode(int x) {
        val = x;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public INode getLeft() {
        return left;
    }

    public void setLeft(INode left) {
        this.left = left;
    }

    public INode getRight() {
        return right;
    }

    public void setRight(INode right) {
        this.right = right;
    }
}
