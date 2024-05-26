package simple.tree.avl.adjust;

import org.apache.log4j.Logger;
import simple.tree.avl.AVLTree;
import simple.tree.avl.model.TreeNode;

/**
 * @description  调节LL平衡的策略
 * @Author       Q-WHai
 * @date         2019/4/5 12:42
 */
public class AdjustLL extends AdjustImpl {

    @Override
    public void adjust(AVLTree tree, TreeNode node) {
        logger.info("正在进行 LL 失衡修正");

        rotateLeft(tree, node);
        resetHeight(node);
        resetBF(node);
    }
}
