package simple.tree.ht.model;

/**
 * @Author: Q-WHai
 * @Date: Created in 15:47 2019/04/26
 */
public interface INode {

    int getWk();
    void setWk(int wk);

    void setParent(INode parent);
    INode getParent();
}
