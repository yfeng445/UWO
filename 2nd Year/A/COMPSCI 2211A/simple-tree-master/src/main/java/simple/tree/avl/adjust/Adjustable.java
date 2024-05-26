package simple.tree.avl.adjust;

import simple.tree.avl.AVLTree;
import simple.tree.avl.model.TreeNode;

/**
 * 调整AVL树的公共接口
 * 所有旋转调节的策略都需要具备调节的能力
 */
public interface Adjustable {

    /**
     * 调节AVL树的平衡
     *
     * @param tree
     *      平衡二叉树
     * @param node
     *      失衡的节点
     */
    void adjust(AVLTree tree, TreeNode node);
}
