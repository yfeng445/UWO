package simple.tree.rbt;

import simple.tree.rbt.model.*;

/**
 * 红黑树
 *
 * 性质一：节点是红色或者是黑色；
 * 性质二：根节点是黑色；
 * 性质三：每个叶节点（NIL或空节点）是黑色；
 * 性质四：每个红色节点的两个子节点都是黑色的（也就是说不存在两个连续的红色节点）；
 * 性质五：从任一节点到其没个叶节点的所有路径都包含相同数目的黑色节点；
 *
 * @Author: Q-WHai
 * @Date: Created in 14:53 2019/04/09
 */
public class RBTree {

    // private NILNode tnil; // TODO 后期重构时使用（移除所有叶子节点，并将原指向叶子节点的节点重新指向此哨兵节点）

    private TreeNode root;

    // ---------------------------- 以下为对外开放接口 ----------------------------

    public RBTree() { }

    /**
     * 构建一棵红黑树
     *
     * @param elements
     *      源数据
     */
    public void build(Element[] elements) {
        clear();
        for (Element e : elements) {
            insert(e);
        }
    }

    /**
     * 向现有红黑树中添加一个新节点
     *
     * @param element
     *      源数据
     */
    public void insert(Element element) {
        if (null == element) return;
        insert(new TreeNode(element.getVal()));
    }

    /**
     * 获取某一节点的所有祖先节点
     *
     * @param element
     *      节点边界
     * @return
     *      所有祖先节点
     */
    public Element[] progenitors(Element element) {
        // TODO
        return null;
    }

    /**
     * 获取某一节点的所有后继节点
     *
     * @param element
     *      节点边界
     * @return
     *      所有后继节点
     */
    public Element[] successors(Element element) {
        // TODO
        return null;
    }

    /**
     * 获取最小值节点
     *
     * @return
     *      最小值节点元素
     */
    public Element minimum() {
        // TODO
        return null;
    }

    /**
     * 获取最大值节点
     *
     * @return
     *      最大值节点元素
     */
    public Element maximum() {
        // TODO
        return null;
    }

    /**
     * 移除一个节点
     *
     * @param element
     *      待移除节点元素
     */
    public void remove(Element element) {
        // TODO
    }

    // ---------------------------- 以下为内部逻辑实现 ----------------------------

    private void clear() {
        root = null;
    }

    private void insert(TreeNode node) {
        TreeNode y = null;
        INode x = root;
        while (x instanceof TreeNode) {
            y = (TreeNode) x;
            if (node.getVal() < ((TreeNode)x).getVal())
                x = ((TreeNode)x).getLeft();
            else
                x = ((TreeNode)x).getRight();
        }

        node.setParent(y);
        if (null == y) {
            root = node;
            node.setSkin(NodeSkin.Black);
        } else {
            if (node.getVal() < y.getVal()) {
                y.setLeft(node);
            } else {
                y.setRight(node);
            }
            node.setSkin(NodeSkin.Red);
        }
        adjust(node);
    }

    private void adjust(TreeNode x) {
        while (null != x.getParent() && NodeSkin.Red == x.getParent().getSkin()) {
            if (null == x.getParent().getParent()) continue;
            if (x.getParent() == x.getParent().getParent().getLeft()) {
                INode right = x.getParent().getParent().getRight();
                if (NodeSkin.Red == right.getSkin()) {
                    x.getParent().setSkin(NodeSkin.Black);
                    right.setSkin(NodeSkin.Black);
                    x.getParent().getParent().setSkin(NodeSkin.Red);
                    x = x.getParent().getParent();
                } else {
                    if (x == x.getParent().getRight()) {
                        x = x.getParent();
                        leftRotate(x);
                    }

                    x.getParent().setSkin(NodeSkin.Black);
                    x.getParent().getParent().setSkin(NodeSkin.Red);
                    rightRotate(x.getParent().getParent());
                }
            } else {
                INode left = x.getParent().getParent().getLeft();
                if (NodeSkin.Red == left.getSkin()) {
                    x.getParent().setSkin(NodeSkin.Black);
                    left.setSkin(NodeSkin.Black);
                    x.getParent().getParent().setSkin(NodeSkin.Red);
                    x = x.getParent().getParent();
                } else {
                    if (x == x.getParent().getLeft()) {
                        x = x.getParent();
                        rightRotate(x);
                    }

                    x.getParent().setSkin(NodeSkin.Black);
                    x.getParent().getParent().setSkin(NodeSkin.Red);
                    leftRotate(x.getParent().getParent());
                }
            }
        }

        root.setSkin(NodeSkin.Black);
    }

    private void leftRotate(TreeNode node) {
        INode right = node.getRight();
        node.setRight(((TreeNode) right).getLeft()) ;
        ((TreeNode) right).getLeft().setParent(node);
        right.setParent(node.getParent());
        if (null == node.getParent()) {
            root = (TreeNode) right;
        } else {
            if (node == node.getParent().getLeft()) {
                node.getParent().setLeft(right);
            } else {
                node.getParent().setRight(right);
            }
        }

        ((TreeNode) right).setLeft(node);
        node.setParent((TreeNode) right);
    }

    private void rightRotate(TreeNode node) {
        INode left = node.getLeft();
        node.setLeft(((TreeNode) left).getRight());
        ((TreeNode) left).getRight().setParent(node);
        left.setParent(node.getParent());

        if (null == node.getParent()) {
            root = (TreeNode) left;
        } else {
            if (node == node.getParent().getRight()) {
                node.getParent().setRight(left);
            } else {
                node.getParent().setLeft(left);
            }
        }

        ((TreeNode) left).setRight(node);
        node.setParent((TreeNode) left);
    }
}
