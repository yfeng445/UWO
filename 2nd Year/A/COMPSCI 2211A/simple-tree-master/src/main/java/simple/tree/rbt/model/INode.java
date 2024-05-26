package simple.tree.rbt.model;

/**
 * @Author: Q-WHai
 * @Date: Created in 15:44 2019/04/09
 */
public interface INode {

    TreeNode getParent();
    void setParent(TreeNode parent);

    NodeSkin getSkin();
    void setSkin(NodeSkin skin);
}
