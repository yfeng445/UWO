package simple.tree;

import org.apache.log4j.Logger;
import org.junit.Test;
import simple.tree.rbt.RBTree;
import simple.tree.rbt.model.Element;

/**
 * @Author: Q-WHai
 * @Date: Created in 10:28 2019/04/11
 */
public class RBTreeTest {

    private Logger logger = Logger.getLogger(RBTreeTest.class);

    // 测试红黑树构建
    @Test
    public void test1() {
        Element[] elements = new Element[] {
                new Element(13),
                new Element(17),
                new Element(8),
                new Element(1),
                new Element(25),
                new Element(15),
                new Element(11),
                new Element(6),
                new Element(22),
                new Element(27)
        };

        RBTree tree = new RBTree();
        tree.build(elements);

        logger.info(tree);
    }

    // 测试红黑树的变色及旋转
    @Test
    public void test2() {
        RBTree tree = new RBTree();

        tree.insert(new Element(1));
        tree.insert(new Element(2));
        tree.insert(new Element(3));
        tree.insert(new Element(4));
        tree.insert(new Element(5));
        tree.insert(new Element(6));
        tree.insert(new Element(7));
        tree.insert(new Element(8));
        tree.insert(new Element(9));
        tree.insert(new Element(10));
        tree.insert(new Element(11));
        tree.insert(new Element(12));
        tree.insert(new Element(13));
        tree.insert(new Element(14));
        tree.insert(new Element(15));
        tree.insert(new Element(16));
        tree.insert(new Element(17));
        tree.insert(new Element(18));
        tree.insert(new Element(19));
        tree.insert(new Element(20));

        logger.info(tree);
    }

    // 测试获取祖先节点
    @Test
    public void test3() {
        Element[] elements = new Element[] {
                new Element(13),
                new Element(17),
                new Element(8),
                new Element(1),
                new Element(25),
                new Element(15),
                new Element(11),
                new Element(6),
                new Element(22),
                new Element(27),
                new Element(21),
        };

        RBTree tree = new RBTree();
        tree.build(elements);

        Element[] es = tree.progenitors(new Element(25)); // TODO
        logger.info(es);
    }

    // 测试获取后继节点
    @Test
    public void test4() {
        Element[] elements = new Element[] {
                new Element(13),
                new Element(17),
                new Element(8),
                new Element(1),
                new Element(25),
                new Element(15),
                new Element(11),
                new Element(6),
                new Element(22),
                new Element(27),
                new Element(21),
        };

        RBTree tree = new RBTree();
        tree.build(elements);

        Element[] es = tree.successors(new Element(25)); // TODO
        logger.info(es);
    }

    // 测试获取最小值节点
    @Test
    public void test5() {
        Element[] elements = new Element[] {
                new Element(13),
                new Element(17),
                new Element(8),
                new Element(1),
                new Element(25),
                new Element(15),
                new Element(11),
                new Element(6),
                new Element(22),
                new Element(27),
                new Element(21),
        };

        RBTree tree = new RBTree();
        tree.build(elements);

        Element min = tree.minimum(); // TODO
        logger.info(min);
    }

    // 测试获取最大值节点
    @Test
    public void test6() {
        Element[] elements = new Element[] {
                new Element(13),
                new Element(17),
                new Element(8),
                new Element(1),
                new Element(25),
                new Element(15),
                new Element(11),
                new Element(6),
                new Element(22),
                new Element(27),
                new Element(21),
        };

        RBTree tree = new RBTree();
        tree.build(elements);

        Element max = tree.maximum(); // TODO
        logger.info(max);
    }

    // 测试删除节点
    @Test
    public void test7() {
        Element[] elements = new Element[] {
                new Element(13),
                new Element(17),
                new Element(8),
                new Element(1),
                new Element(25),
                new Element(15),
                new Element(11),
                new Element(6),
                new Element(22),
                new Element(27),
                new Element(21),
        };

        RBTree tree = new RBTree();
        tree.build(elements);

        tree.remove(new Element(8)); // TODO
        logger.info(tree);
    }
}
