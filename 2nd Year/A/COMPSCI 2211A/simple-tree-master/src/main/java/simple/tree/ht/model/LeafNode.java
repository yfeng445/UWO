package simple.tree.ht.model;

/**
 * @Author: Q-WHai
 * @Date: Created in 15:51 2019/04/26
 */
public class LeafNode implements INode {

    private int wk;
    private INode parent;

    public LeafNode(int wk) {
        this.wk = wk;
    }

    public int getWk() {
        return wk;
    }

    public void setWk(int wk) {
        this.wk = wk;
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
        return String.format("[%d]", getWk());
    }
}
