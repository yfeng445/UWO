package simple.tree.bst;

import simple.tree.bst.model.Element;
import simple.tree.bst.model.TreeNode;

/**
 * 二叉搜索树
 *
 * @Author:     Q-WHai
 * @Date:       Created in 16:15 2019/04/04
 */
public class BSTree {

    private TreeNode root;

    public BSTree() { }

    /**
     * 构建二叉搜索树
     *
     * @param elements  输入数据
     */
    public void build(Element[] elements) {
        clear();
        for (Element e : elements) {
            insert(new TreeNode(e.getVal()));
        }
    }

    /**
     * 查找数值x
     *
     * @param x     待查找数值
     * @return      对应节点
     */
    public TreeNode find(int x) {
        return find(x, root);
    }

    /**
     * 移除数值为x的节点
     *
     * @param x     待移除数值
     */
    public void remove(int x) {
        // TODO
    }

    private void clear() {
        root = null;
    }

    private void insert(TreeNode node) {
        if (null == node) return;

        if (null == root) {
            root = node;
        } else {
            insert(node, root);
        }
    }

    private void insert(TreeNode node, TreeNode parent) {
        if (null == node) return;

        if (parent.getVal() > node.getVal()) {
            if (null == parent.getLeft()) {
                parent.setLeft(node);
                node.setParent(parent);
            }
            else insert(node, parent.getLeft());
        } else if (parent.getVal() < node.getVal()) {
            if (null == parent.getRight()) {
                parent.setRight(node);
                node.setParent(parent);
            }
            else insert(node, parent.getRight());
        }
    }

    private TreeNode find(int x, TreeNode node) {
        if (x == node.getVal()) return node;
        if (x < node.getVal() && null == node.getLeft()) return null;
        if (x < node.getVal()) return find(x, node.getLeft());
        return null == node.getRight() ? null : find(x, node.getRight());
    }
}
