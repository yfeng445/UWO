package simple.tree;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import simple.tree.tbt.TBTree;
import simple.tree.tbt.model.Element;

import java.util.List;

/**
 * @Author: Q-WHai
 * @Date: Created in 10:38 2019/04/08
 */
public class TBTreeTest {

    private Logger logger = Logger.getLogger(TBTreeTest.class);

    // 测试TBTree创建
    @Test
    public void test1() {
        Element[] elements = new Element[] {
                new Element(1),
                new Element(2),
                new Element(3),
                null,
                null,
                new Element(6),
                new Element(7),
                new Element(8),
                null,
                new Element(10),
        };

        TBTree tree = new TBTree();
        tree.build(elements);

        Assert.assertNotNull(tree);
    }

    // 测试前序线索化
    @Test
    public void test2() {
        Element[] elements = new Element[] {
                new Element(1),
                new Element(2),
                new Element(3),
                new Element(4),
                new Element(5),
                null,
                new Element(6),
                null,
                null,
                new Element(7),
                new Element(8)
        };

        TBTree tree = new TBTree();
        tree.build(elements);

        tree.preorderThreading();
        logElements(tree.preorderTraversal(), "前序遍历");
    }

    // 测试中序线索化
    @Test
    public void test3() {
        Element[] elements = new Element[] {
                new Element(1),
                new Element(2),
                new Element(3),
                new Element(4),
                new Element(5),
                null,
                new Element(6),
                null,
                null,
                new Element(7),
                new Element(8)
        };

        TBTree tree = new TBTree();
        tree.build(elements);

        tree.inorderThreading();

        Assert.assertNotNull(tree);
        logElements(tree.inorderTraversal(), "中序遍历");
    }

    // 测试后序线索化
    @Test
    public void test4() {
        Element[] elements = new Element[] {
                new Element(1),
                new Element(2),
                new Element(3),
                new Element(4),
                new Element(5),
                null,
                new Element(6),
                null,
                null,
                new Element(7),
                new Element(8)
        };

        TBTree tree = new TBTree();
        tree.build(elements);

        tree.postorderThreading();
        logElements(tree.postorderTraversal(), "后序遍历"); // TODO
    }

    // 测试层序线索化
    @Test
    public void test5() {
        Element[] elements = new Element[] {
                new Element(1),
                new Element(2),
                new Element(3),
                new Element(4),
                new Element(5),
                null,
                new Element(6),
                null,
                null,
                new Element(7),
                new Element(8)
        };

        TBTree tree = new TBTree();
        tree.build(elements);

        tree.levelorderThreading();

        Assert.assertNotNull(tree);
    }

    private void logElements(List<Element> elements, String hints) {
        if (null == elements) return;
        StringBuilder sb = new StringBuilder();
        for (Element e : elements) {
            if (null == e) continue;
            sb.append(e.getVal());
            sb.append(" ");
        }

        logger.info(String.format("%s: %s", hints, sb.toString()));
    }
}
