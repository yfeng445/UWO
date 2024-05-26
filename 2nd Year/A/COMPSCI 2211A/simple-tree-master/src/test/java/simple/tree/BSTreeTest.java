package simple.tree;

import org.junit.Assert;
import org.junit.Test;
import simple.tree.bst.BSTree;
import simple.tree.bst.model.Element;
import simple.tree.bst.model.TreeNode;

/**
 * @Author: Q-WHai
 * @Date: Created in 16:23 2019/04/04
 */
public class BSTreeTest {

    @Test
    public void test1() {
        Element[] elements = new Element[] {
                new Element(8),
                new Element(3),
                new Element(10),
                new Element(1),
                new Element(6),
                new Element(14),
                new Element(4),
                new Element(7),
                new Element(13)
        };

        BSTree tree = new BSTree();
        tree.build(elements);

        Assert.assertNotNull(tree);
    }

    @Test
    public void test2() {
        Element[] elements = new Element[] {
                new Element(8),
                new Element(3),
                new Element(10),
                new Element(1),
                new Element(6),
                new Element(14),
                new Element(4),
                new Element(7),
                new Element(13)
        };

        BSTree tree = new BSTree();
        tree.build(elements);

        TreeNode node = tree.find(13);
        Assert.assertNotNull(node);
        Assert.assertEquals(13, node.getVal());
    }

    @Test
    public void test3() {
        Element[] elements = new Element[] {
                new Element(8),
                new Element(3),
                new Element(10),
                new Element(1),
                new Element(6),
                new Element(14),
                new Element(4),
                new Element(7),
                new Element(13)
        };

        BSTree tree = new BSTree();
        tree.build(elements);

        TreeNode node = tree.find(5);
        Assert.assertNull(node);
    }

    @Test
    public void test4() {
        Element[] elements = new Element[] {
                new Element(8),
                new Element(3),
                new Element(10),
                new Element(1),
                new Element(6),
                new Element(14),
                new Element(4),
                new Element(7),
                new Element(13)
        };

        BSTree tree = new BSTree();
        tree.build(elements);

        tree.remove(1);
    }
}
