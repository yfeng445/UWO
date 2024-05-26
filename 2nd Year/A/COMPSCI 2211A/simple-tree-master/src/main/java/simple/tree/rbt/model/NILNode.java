package simple.tree.rbt.model;

/**
 * @Author: Q-WHai
 * @Date: Created in 15:44 2019/04/09
 */
public class NILNode implements INode {

    private TreeNode parent;
    private NodeSkin skin;

    public NILNode() {
        skin = NodeSkin.Black;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public NodeSkin getSkin() {
        return skin;
    }

    public void setSkin(NodeSkin skin) {
        this.skin = skin;
    }
}
