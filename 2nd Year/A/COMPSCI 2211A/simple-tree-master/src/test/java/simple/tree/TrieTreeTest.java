package simple.tree;

import org.junit.Assert;
import org.junit.Test;
import simple.tree.trie.TrieTree;

/**
 * @Author: Q-WHai
 * @Date: Created in 14:20 2019/04/30
 */
public class TrieTreeTest {

    @Test
    public void test1() {
        TrieTree tree = new TrieTree();
        tree.insert("hello");
        tree.insert("helo");
        tree.insert("healo");
        tree.insert("health");
        tree.insert("world");

        Assert.assertTrue(tree.exist("hello"));
        Assert.assertFalse(tree.exist("abc"));
        Assert.assertTrue(tree.exist("world"));
    }
}
