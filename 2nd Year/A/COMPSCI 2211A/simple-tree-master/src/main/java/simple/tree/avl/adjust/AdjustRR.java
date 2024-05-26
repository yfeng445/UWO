package simple.tree.avl.adjust;

import simple.tree.avl.AVLTree;
import simple.tree.avl.model.TreeNode;

/**
 * @Description 调节RR平衡的策略
 * @Author      Q-WHai
 * @Date        2019/4/5 12:44
 */
public class AdjustRR extends AdjustImpl {

    @Override
    public void adjust(AVLTree tree, TreeNode node) {
        logger.info("正在进行 RR 失衡修正");

        rotateRight(tree, node);
        resetHeight(node);
        resetBF(node);
    }
}
