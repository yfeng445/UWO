package simple.tree.avl.adjust;

import simple.tree.avl.AVLTree;
import simple.tree.avl.model.TreeNode;

/**
 * @Description     调节LR平衡的策略
 * @Author          Q-WHai
 * @Date            2019/4/5 12:43
 */
public class AdjustLR extends AdjustImpl {

    @Override
    public void adjust(AVLTree tree, TreeNode node) {
        logger.info("正在进行 LR 失衡修正");

        lr(tree, node);
    }

    private void lr(AVLTree tree, TreeNode node) {
        TreeNode left = node.getLeft();
        rotateSubRight(tree, left);
        resetHeight(left);
        resetBF(left);

        rotateLeft(tree, node);
        resetHeight(node);
        resetBF(node);
    }

    private void rotateSubRight(AVLTree tree, TreeNode node) {
        TreeNode right = node.getRight();

        if (null != node.getParent()) {
            node.getParent().setLeft(right);
        }

        right.setParent(node.getParent());
        node.setRight(right.getLeft());
        right.setLeft(node);
        node.setParent(right);
    }
}
