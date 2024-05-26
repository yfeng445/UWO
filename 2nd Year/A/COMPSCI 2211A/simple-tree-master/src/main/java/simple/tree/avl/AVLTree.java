package simple.tree.avl;

import simple.tree.avl.adjust.*;
import simple.tree.avl.model.RotateType;
import simple.tree.avl.model.TreeNode;
import simple.tree.avl.model.Element;

import java.util.HashMap;
import java.util.Map;

/**
 * 平衡二叉树
 *
 * @Author:     Q-WHai
 * @Date:       Created in 18:31 2019/04/04
 */
public class AVLTree {

    public TreeNode root;
    private Map<RotateType, Adjustable> adjustableMap = new HashMap<>();

    public AVLTree()
    {
        adjustableMap.put(RotateType.LL, new AdjustLL());
        adjustableMap.put(RotateType.LR, new AdjustLR());
        adjustableMap.put(RotateType.RL, new AdjustRL());
        adjustableMap.put(RotateType.RR, new AdjustRR());
    }

    /**
     * 构建AVL树
     * @param elements  输入数据
     */
    public void build(Element[] elements) {
        clear();
        for (Element element : elements) {
            insert(new TreeNode(element.getVal()));
        }
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
        if (node.getVal() == parent.getVal()) return;

        if (node.getVal() < parent.getVal()) { // 向左子树插入数据
            if (null == parent.getLeft()) {
                parent.setLeft(node);
                node.setParent(parent);

                resetHeight(parent);
                resetBF(parent);

                TreeNode imbanceNode = findImbanceNode(node);
                adjust(imbanceNode);
            } else {
                insert(node, parent.getLeft());
            }
        } else { // 向右子树插入数据
            if (null == parent.getRight()) {
                parent.setRight(node);
                node.setParent(parent);

                resetHeight(parent);
                resetBF(parent);

                TreeNode imbanceNode = findImbanceNode(node);
                adjust(imbanceNode);
            } else {
                insert(node, parent.getRight());
            }
        }
    }

    private void adjust(TreeNode node) {
        if (null == node) return;

        RotateType state = judgeState(node);
        if (RotateType.Balance == state) return;

        adjust(node, adjustableMap.get(state));
    }

    private void adjust(TreeNode node, Adjustable adjustable) {
        adjustable.adjust(this, node);
    }

    private void resetHeight(TreeNode node) {
        while (null != node) {

            node.setHeight(Math.max(node.getLeftHeight(), node.getRightHeight()) + 1);
            node = node.getParent();
        }
    }

    // 重置失衡因子 (bf = 左子树高度 - 右子树高度)
    private void resetBF(TreeNode node) {
        while (null != node) {
            node.resetBf();
            node = node.getParent();
        }
    }

    // 找到失衡节点
    private TreeNode findImbanceNode(TreeNode node) {
        while (null != node) {
            if (2 == node.getBf() || -2 == node.getBf()) return node;
            node = node.getParent();
        }

        return null;
    }

    // 确认失衡状态 (LL/LR/RL/RR)
    private RotateType judgeState(TreeNode node) {
        if (2 == node.getBf()) {
            if (null != node.getLeft()) return 1 == node.getLeft().getBf() ? RotateType.LL : -1 == node.getLeft().getBf() ? RotateType.LR : RotateType.Balance;
        }

        if (-2 == node.getBf()) {
            if (null != node.getRight()) return 1 == node.getRight().getBf() ? RotateType.RL : -1 == node.getRight().getBf() ? RotateType.RR : RotateType.Balance;
        }

        return RotateType.Balance;
    }

    private void clear() {
        root = null;
    }
}
