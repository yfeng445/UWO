package simple.tree;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import simple.tree.bt.Tree;
import simple.tree.bt.model.Element;

import java.util.List;

public class TreeTest {

    private Logger logger = Logger.getLogger(TreeTest.class);

    @Test
    public void test1() {
        Element[] elements = new Element[] {
                new Element(1),
                new Element(2),
                new Element(3),
                new Element(4),
                new Element(5),
                new Element(6),
                new Element(7),
                new Element(8),
                null,
                new Element(10),
        };

        Tree tree = new Tree();
        tree.build(elements);

        Assert.assertNotNull(tree);
    }

    @Test
    public void test2() {
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

        Tree tree = new Tree();
        tree.build(elements);

        Assert.assertNotNull(tree);
    }

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

        Tree tree = new Tree();
        tree.build(elements);

        logElements(tree.preorderTraversal(), "前序遍历");
    }

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

        Tree tree = new Tree();
        tree.build(elements);

        logElements(tree.inorderTraversal(), "中序遍历");
    }

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

        Tree tree = new Tree();
        tree.build(elements);

        logElements(tree.postorderTraversal(), "后序遍历");
    }

    @Test
    public void test6() {
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

        Tree tree = new Tree();
        tree.build(elements);

        logElements(tree.levelorderTraversal(), "层序遍历");
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
