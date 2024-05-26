package simple.tree.ht.model;

/**
 * @Author: Q-WHai
 * @Date: Created in 15:47 2019/04/26
 */
public class TreeNode implements INode {

    private int wk;
    private INode left;
    private INode right;
    private INode parent;

    public TreeNode(int wk) {
        this.wk = wk;
    }

    public int getWk() {
        return wk;
    }

    public void setWk(int wk) {
        this.wk = wk;
    }

    public INode getLeft() {
        return left;
    }

    public void setLeft(INode left) {
        this.left = left;
        this.left.setParent(this);
    }

    public INode getRight() {
        return right;
    }

    public void setRight(INode right) {
        this.right = right;
        this.right.setParent(this);
    }

    @Override
    public INode getParent() {
        return parent;
    }

    @Override
    public void setParent(INode parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return String.format("(%d)", getWk());
    }
}
