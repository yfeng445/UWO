package simple.tree.trie;

import simple.tree.trie.model.INode;
import simple.tree.trie.model.RootNode;

/**
 * 字典树
 *
 * @Author: Q-WHai
 * @Date: Created in 14:20 2019/04/30
 */
public class TrieTree {

    private RootNode root = new RootNode();

    public TrieTree() { }

    public void insert(String word) {
        char[] chars = word.toCharArray();
        INode lastNode = root;
        for (int i = 0; i < chars.length; i++) {
            if (!lastNode.contains(chars[i])) {
                lastNode.add(chars[i]);
            }

            lastNode = lastNode.getNode(chars[i]);
        }
    }

    public boolean exist(String word) {
        char[] chars = word.toCharArray();
        INode lastNode = root;
        for (char c : chars) {
            if (lastNode.contains(c)) {
                lastNode = lastNode.getNode(c);
                continue;
            }
            return false;
        }

        return true;
    }
}
