package simple.tree.avl.model;

public class TreeNode implements INode {

    private int val;             // 节点数值
    private TreeNode left;       // 左子节点
    private TreeNode right;      // 右子节点
    private TreeNode parent;     // 父节点

    private int height;          // 当前节点高度
    private int bf;              // 平衡因子

    public TreeNode(int val) {
        this.val = val;
        setHeight(0);
        setBf(0);
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

    public int getHeight() {
        return height;
    }

    public int getLeftHeight() {
        return null == left ? -1 : left.getHeight();
    }

    public int getRightHeight() {
        return null == right ? -1 : right.getHeight();
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getBf() {
        return bf;
    }

    public void setBf(int bf) {
        this.bf = bf;
    }

    public void resetBf() {
        int hl = null == getLeft() ? 0 : getLeft().getHeight() + 1;
        int hr = null == getRight() ? 0 : getRight().getHeight() + 1;
        setBf(hl - hr);
    }

    @Override
    public String toString() {
        return String.format("[%d: h=%d, bf=%d]", getVal(), getHeight(), getBf());
    }
}
