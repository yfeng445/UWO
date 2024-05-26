package simple.tree.avl.adjust;

import org.apache.log4j.Logger;
import simple.tree.avl.AVLTree;
import simple.tree.avl.model.TreeNode;

/**
 * @Description TODO
 * @Author      Q-WHai
 * @Date        2019/4/5 18:21
 */
public class AdjustImpl implements Adjustable {

    Logger logger = Logger.getLogger(AdjustLL.class);

    @Override
    public void adjust(AVLTree tree, TreeNode node) {
    }

    void rotateLeft(AVLTree tree, TreeNode node) {
        TreeNode left = node.getLeft();
        if (tree.root == node) {
            tree.root = left;
        }

        if (null != node.getParent()) {
            node.getParent().setLeft(left);
        }

        left.setParent(node.getParent());
        node.setLeft(left.getRight());
        left.setRight(node);
        node.setParent(left);
    }

    void rotateRight(AVLTree tree, TreeNode node) {
        TreeNode right = node.getRight();
        if (tree.root == node) {
            tree.root = right;
        }

        if (null != node.getParent()) {
            node.getParent().setRight(right);
        }

        right.setParent(node.getParent());
        node.setRight(right.getLeft());
        right.setLeft(node);
        node.setParent(right);
    }

    void resetHeight(TreeNode node) {
        while (null != node) {
            node.setHeight(Math.max(node.getLeftHeight(), node.getRightHeight()) + 1);
            node = node.getParent();
        }
    }

    void resetBF(TreeNode node) {
        while (null != node) {
            node.resetBf();
            node = node.getParent();
        }
    }
}
