package simple.tree.tbt.model;

/**
 * @Author: Q-WHai
 * @Date: Created in 10:21 2019/04/08
 */
public interface INode {

    boolean getLtag();
    void setLtag(boolean ltag);

    boolean getRtag();
    void setRtag(boolean rtag);

    INode getLeft();
    void setLeft(INode left);

    INode getRight();
    void setRight(INode right);

    INode getParent();
    void setParent(INode parent);
}
