package simple.tree.trie.model;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: Q-WHai
 * @Date: Created in 14:51 2019/04/30
 */
public class RootNode implements INode {

    private Set<TreeNode> children = new HashSet<>();

    public void add(Character element) {
        if (contains(element)) return;
        children.add(new TreeNode(element));
    }

    public boolean contains(Character element) {
        for (TreeNode node : children) {
            if (node.getElement() == element) return true;
        }

        return false;
    }

    public TreeNode getNode(Character element) {
        for (TreeNode node : children) {
            if (node.getElement() == element) return node;
        }

        return null;
    }
}
