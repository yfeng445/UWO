package simple.tree.trie.model;

/**
 * @Author: Q-WHai
 * @Date: Created in 14:51 2019/04/30
 */
public interface INode {

    void add(Character element);
    boolean contains(Character element);
    TreeNode getNode(Character element);
}
