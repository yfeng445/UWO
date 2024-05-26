package simple.tree.ht;

import simple.tree.ht.model.INode;
import simple.tree.ht.model.LeafNode;
import simple.tree.ht.model.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 哈夫曼树
 * (NOTE: 这个示例中，传入的数组中，不允许出现重复数字)
 *
 * @Author: Q-WHai
 * @Date: Created in 15:48 2019/04/26
 */
public class HuffmanTree {

    private INode root;
    private Map<Integer, String> map = new HashMap<>();

    public void build(int[] arr) {
        sort(arr);
        INode[] nodes = box(arr);

        for (int i = 1; i < nodes.length; i++) {
            insert(nodes, i);
        }

        initHuffmanCode();
    }

    public int getWPL() {
        return getWPL(root, 0);
    }

    public String getHuffmanCode(int x) {
        return map.get(x);
    }

    private int getWPL(INode node, int depth) {
        if (node instanceof LeafNode) {
            return node.getWk() * depth;
        }

        return getWPL(((TreeNode)node).getLeft(), depth + 1) + getWPL(((TreeNode)node).getRight(), depth + 1);
    }

    private void insert(INode[] nodes, int i) {
        TreeNode parent = new TreeNode(nodes[i - 1].getWk() + nodes[i].getWk());
        INode left = nodes[i - 1].getWk() < nodes[i].getWk() ? nodes[i - 1] : nodes[i];
        INode right = nodes[i - 1].getWk() < nodes[i].getWk() ? nodes[i] : nodes[i - 1];

        parent.setLeft(left);
        parent.setRight(right);

        root = parent;

        for (int j = i; j < nodes.length; j++) {
            if (nodes[j].getWk() < root.getWk()) {
                nodes[j - 1] = nodes[j];
                nodes[j] = root;
                continue;
            }

            break;
        }
    }

    private void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    arr[i] = arr[i] + arr[j];
                    arr[j] = arr[i] - arr[j];
                    arr[i] = arr[i] - arr[j];
                }
            }
        }
    }

    private INode[] box(int[] arr) {
        INode[] nodes = new INode[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nodes[i] = new LeafNode(arr[i]);
        }

        return nodes;
    }

    private void initHuffmanCode() {
        initHuffmanCode(root, "");
    }

    private void initHuffmanCode(INode node, String prefix) {
        if (node instanceof LeafNode)
            map.put(node.getWk(), prefix);
        else {
            initHuffmanCode(((TreeNode)node).getLeft(), String.format("%s%d", prefix, 0));
            initHuffmanCode(((TreeNode)node).getRight(), String.format("%s%d", prefix, 1));
        }
    }
}
