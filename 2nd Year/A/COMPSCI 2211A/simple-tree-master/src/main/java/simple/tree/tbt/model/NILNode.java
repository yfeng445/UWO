package simple.tree.tbt.model;

/**
 * @Author: Q-WHai
 * @Date: Created in 11:15 2019/04/08
 */
public class NILNode implements INode {

    private boolean ltag = false; // false: 指向左孩子/true: 指向前驱节点
    private boolean rtag = false; // false: 指向右孩子/true: 指向后驱节点

    private INode left;
    private INode right;
    private INode parent;

    public boolean getLtag() {
        return ltag;
    }

    public void setLtag(boolean ltag) {
        this.ltag = ltag;
    }

    public boolean getRtag() {
        return rtag;
    }

    public void setRtag(boolean rtag) {
        this.rtag = rtag;
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

    public INode getParent() {
        return parent;
    }

    public void setParent(INode parent) {
        this.parent = parent;
    }
}
