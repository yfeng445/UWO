package simple.tree.bt;

import simple.tree.bt.model.INode;
import simple.tree.bt.model.NILNode;
import simple.tree.bt.model.TreeNode;
import simple.tree.bt.model.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 二叉树
 *
 * @Author: Q-WHai
 * @Date: Created in 09:54 2019/04/03
 */
public class Tree {

    private INode root;

    public Tree() { }

    /**
     * 二叉树构建
     * @param input
     *      用于构建二叉树的输入数组
     */
    public void build(Element[] input) {
        clear();
        nodesQueue = new ArrayBlockingQueue<>(input.length);
        for (Element e : input) {
            insert(null == e ? new NILNode() : new TreeNode(e.getVal()));
        }

        while (!nodesQueue.isEmpty()) nodesQueue.poll();
    }

    /**
     * 前序遍历二叉树
     * @return
     *      遍历结果
     */
    public List<Element> preorderTraversal() {
        List<Element> list = new ArrayList<>(); // 保存结果list
        preorder(root, list);
        return list;
    }

    private void preorder(INode node, List<Element> elements) {
        if (null == node) return;

        if (node instanceof TreeNode) {
            elements.add(new Element(((TreeNode)node).getVal()));
            preorder(((TreeNode) node).getLeft(), elements);
            preorder(((TreeNode) node).getRight(), elements);
        } else if (node instanceof NILNode) {
            elements.add(null);
        }
    }

    /**
     * 中序遍历二叉树
     * @return
     *      遍历结果
     */
    public List<Element> inorderTraversal() {
        List<Element> list = new ArrayList<>();
        inorder(root, list);
        return list;
    }

    private void inorder(INode node, List<Element> elements) {
        if (null == node) return;

        if (node instanceof TreeNode) {
            inorder(((TreeNode) node).getLeft(), elements);
            elements.add(new Element(((TreeNode)node).getVal()));
            inorder(((TreeNode) node).getRight(), elements);
        } else if (node instanceof NILNode) {
            elements.add(null);
        }
    }

    /**
     * 后序遍历二叉树
     * @return
     *      遍历结果
     */
    public List<Element> postorderTraversal() {
        List<Element> list = new ArrayList<>();
        postorder(root, list);
        return list;
    }

    private void postorder(INode node, List<Element> elements) {
        if (null == node) return;

        if (node instanceof TreeNode) {
            postorder(((TreeNode) node).getLeft(), elements);
            postorder(((TreeNode) node).getRight(), elements);
            elements.add(new Element(((TreeNode)node).getVal()));
        } else if (node instanceof NILNode) {
            elements.add(null);
        }
    }

    /**
     * 层序遍历二叉树
     * @return
     *      遍历结果
     */
    public List<Element> levelorderTraversal() {
        List<Element> list = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayBlockingQueue<>(10);
        levelorder(root, list, queue);
        return list;
    }

    private void levelorder(INode node, List<Element> elements, Queue<TreeNode> queue) {
        if (null == node) return;

        if (node instanceof NILNode) {
            elements.add(null);
        } else if (node instanceof TreeNode) {
            elements.add(new Element(((TreeNode)node).getVal()));

            INode left = ((TreeNode) node).getLeft();
            INode right = ((TreeNode) node).getRight();

            if (left instanceof TreeNode) queue.add((TreeNode)left);
            if (right instanceof TreeNode) queue.add((TreeNode)right);

            levelorder(queue.poll(), elements, queue);
        }
    }

    private void clear() {
        root = null;
    }

    private Queue<TreeNode> nodesQueue;
    private void insert(INode node) {
        if (null == root) {
            root = node;
            if (node instanceof TreeNode) nodesQueue.add((TreeNode)root);
            return;
        }

        TreeNode n1 = nodesQueue.peek();
        if (null == n1.getLeft()) {
            n1.setLeft(node);
            if (n1.getLeft() instanceof TreeNode) nodesQueue.add((TreeNode)n1.getLeft());
        } else if (null == n1.getRight()) {
            n1.setRight(node);
            if (n1.getRight() instanceof TreeNode) nodesQueue.add((TreeNode)n1.getRight());
            nodesQueue.poll();
        }
    }
}