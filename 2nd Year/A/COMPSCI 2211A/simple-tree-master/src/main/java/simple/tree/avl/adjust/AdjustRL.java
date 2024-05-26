package simple.tree.avl.adjust;

import simple.tree.avl.AVLTree;
import simple.tree.avl.model.TreeNode;

/**
 * @Description     调节RL平衡的策略
 * @Author          Q-WHai
 * @Date            2019/4/5 12:43
 */
public class AdjustRL extends AdjustImpl {

    @Override
    public void adjust(AVLTree tree, TreeNode node) {
        logger.info("正在进行 RL 失衡修正");

        rl(tree, node);
    }

    private void rl(AVLTree tree, TreeNode node) {
        // TODO
        TreeNode right = node.getRight();
        rotateSubLeft(right);
        resetHeight(right);
        resetBF(right);

        rotateRight(tree, node);
        resetHeight(node);
        resetBF(node);
    }

    private void rotateSubLeft(TreeNode node) {
        // TODO
        TreeNode left = node.getLeft();

        if (null != node.getParent()) {
            node.getParent().setRight(left);
        }

        left.setParent(node.getParent());
        node.setLeft(left.getRight());
        left.setRight(node);
        node.setParent(left);
    }
}
