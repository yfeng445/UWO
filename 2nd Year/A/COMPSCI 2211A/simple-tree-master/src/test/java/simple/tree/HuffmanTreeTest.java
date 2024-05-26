package simple.tree;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import simple.tree.ht.HuffmanTree;

/**
 * @Author: Q-WHai
 * @Date: Created in 15:24 2019/04/26
 */
public class HuffmanTreeTest {

    private final Logger logger = Logger.getLogger(HuffmanTreeTest.class);

    @Test
    public void test1() {
        int[] arr = new int[] {
                5, 8, 4, 11, 9, 13
        };

        HuffmanTree tree = new HuffmanTree();
        tree.build(arr);

        Assert.assertEquals(126, tree.getWPL());
    }

    @Test
    public void test2() {
        int[] arr = new int[] {
                5, 8, 4, 11, 9, 13
        };

        HuffmanTree tree = new HuffmanTree();
        tree.build(arr);

        for (int i = 0; i < arr.length; i++) {
            logger.info(String.format("arr[%d]: val = %d, hc = %s", i, arr[i], tree.getHuffmanCode(arr[i])));
        }
    }
}
