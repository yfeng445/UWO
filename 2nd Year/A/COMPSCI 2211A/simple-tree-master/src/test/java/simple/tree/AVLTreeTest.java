package simple.tree;

import org.junit.Assert;
import org.junit.Test;
import simple.tree.avl.AVLTree;
import simple.tree.avl.model.Element;

/**
 * @Author:     Q-WHai
 * @Date:       Created in 18:32 2019/04/04
 */
public class AVLTreeTest {

    // 测试 LL
    @Test
    public void test1() {
        Element[] elements = new Element[] {
                new Element(7),
                new Element(6),
                new Element(5),
                new Element(4),
                new Element(3),
                new Element(2),
                new Element(1)
        };

        AVLTree tree = new AVLTree();
        tree.build(elements);

        Assert.assertNotNull(tree);
    }

    // 测试 RR
    @Test
    public void test2() {
        Element[] elements = new Element[] {
                new Element(1),
                new Element(2),
                new Element(3),
                new Element(4),
                new Element(5),
                new Element(6),
                new Element(7)
        };

        AVLTree tree = new AVLTree();
        tree.build(elements);

        Assert.assertNotNull(tree);
    }

    // 测试 LR
    @Test
    public void test3() {
        Element[] elements = new Element[] {
                new Element(7),
                new Element(4),
                new Element(5)
        };

        AVLTree tree = new AVLTree();
        tree.build(elements);

        Assert.assertNotNull(tree);
    }

    // 测试 LL -> LR
    @Test
    public void test4() {
        Element[] elements = new Element[] {
                new Element(11),
                new Element(8),
                new Element(7),
                new Element(3),
                new Element(5),
        };

        AVLTree tree = new AVLTree();
        tree.build(elements);

        Assert.assertNotNull(tree);
    }

    // 测试 RL
    @Test
    public void test5() {
        Element[] elements = new Element[] {
                new Element(3),
                new Element(8),
                new Element(7)
        };

        AVLTree tree = new AVLTree();
        tree.build(elements);

        Assert.assertNotNull(tree);
    }

    @Test
    public void test6() {
        Element[] elements = new Element[] {
                new Element(2),
                new Element(4),
                new Element(1),
                new Element(3),
                new Element(5),
                new Element(6),
        };

        AVLTree tree = new AVLTree();
        tree.build(elements);

        Assert.assertNotNull(tree);
    }
}
