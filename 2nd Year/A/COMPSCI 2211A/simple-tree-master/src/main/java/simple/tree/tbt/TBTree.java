package simple.tree.tbt;

import simple.tree.tbt.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 线索二叉树
 *
 * @Author: Q-WHai
 * @Date: Created in 10:06 2019/04/08
 */
public class TBTree {

    private INode root;

    public TBTree() { }

    /**
     * 构建二叉树
     *
     * @param elements
     *      输入数据
     */
    public void build(Element[] elements) {
        clear();
        nodesQueue = new ArrayBlockingQueue<>(elements.length);
        for (Element e : elements) {
            insert(null == e ? new NILNode() : new TreeNode(e.getVal()));
        }

        while (!nodesQueue.isEmpty()) nodesQueue.poll();
    }

    private INode lastNode = null;
    /**
     * 前序线索化二叉树
     */
    public void preorderThreading() {
        preorderThreading(root);
    }

    /**
     * 中序线索化二叉树
     */
    public void inorderThreading() {
        inorderThreading(root);
    }

    /**
     * 后序线索化二叉树
     */
    public void postorderThreading() {
        postorderThreading(root);
    }

    /**
     * 层序线索化二叉树
     */
    public void levelorderThreading() {
        levelorderThreading(root, null);
    }

    /**
     * 前序遍历线索二叉树
     *
     * @return
     *      遍历结果
     */
    public List<Element> preorderTraversal() {
        List<Element> list = new ArrayList<>();
        preorder(root, list);

        return list;
    }

    /**
     * 中序遍历线索二叉树
     *
     * @return
     *      遍历结果
     */
    public List<Element> inorderTraversal() {
        List<Element> list = new ArrayList<>();

        INode node = root;
        while (null != node) {
            node = findLeftmostNode(node);
            list.add(new Element(((TreeNode)node).getVal()));
            while (node.getRtag()) {
                node = node.getRight();
                list.add(new Element(((TreeNode)node).getVal()));
            }

            node = node.getRight();
        }

        return list;
    }

    /**
     * 后序遍历线索二叉树
     *
     * @return
     *      遍历结果
     */
    public List<Element> postorderTraversal() {
        List<Element> list = new ArrayList<>();

        INode node = root;
        INode last = null;
        while (root != last) {
            if (null == node) continue;

            // 找到最左端节点
            while (!node.getLtag()) {
                node = node.getLeft();
            }

            // 一直遍历后继节点
            while (null != node && node.getRtag()) {
                list.add(new Element(((TreeNode)node).getVal()));
                last = node;
                node = node.getRight();
            }

            if (null == node) continue;

            // Note: [node.getRight() == last]这步是关键，这是处理从左子树到右子树的关键所在
            while (root != last && node.getRight() == last) {
                list.add(new Element(((TreeNode)node).getVal()));
                last = node;
                if (last != root) {
                    node = node.getParent();
                }
            }

            if (!node.getRtag()) {
                node = node.getRight();
            }
        }

        return list;
    }

    private void preorderThreading(INode node) {
        if (null == node || node instanceof NILNode) return;

        INode left = node.getLeft(); // * 此步是当重置node的左节点后，影响后续的递归参数

        setForedrive(node); // 设置前驱节点
        setForedriveRight(node); // 设置前驱节点的后继节点
        lastNode = node;

        preorderThreading(left);
        preorderThreading(node.getRight());
    }

    private void inorderThreading(INode node) {
        if (null == node || node instanceof NILNode) return;

        inorderThreading(node.getLeft());

        setForedrive(node); // 设置前驱节点
        setForedriveRight(node); // 设置前驱节点的后继节点
        lastNode = node;

        inorderThreading(node.getRight());
    }

    private void postorderThreading(INode node) {
        if (null == node || node instanceof NILNode) return;

        postorderThreading(node.getLeft());
        postorderThreading(node.getRight());

        setForedrive(node); // 设置前驱节点
        setForedriveRight(node); // 设置前驱节点的后继节点
        lastNode = node;
    }

    private void setForedrive(INode node) {
        if (null == node.getLeft() || node.getLeft() instanceof NILNode) {
            node.setLtag(true);
            node.setLeft(lastNode);
        }
    }

    private void setForedriveRight(INode node) {
        if (null != lastNode && (null == lastNode.getRight() || lastNode.getRight() instanceof NILNode)) {
            lastNode.setRtag(true);
            lastNode.setRight(node);
        }
    }

    private void levelorderThreading(INode node, INode lastNode) {
        if (null == node) return;

        if (null == node.getLeft() || node.getLeft() instanceof NILNode) {
            // 设置前驱节点
            node.setLtag(true);
            node.setLeft(lastNode);
        } else {
            nodesQueue.add(node.getLeft());
        }

        if (null == node.getRight() || node.getRight() instanceof NILNode) {
            // 设置后继节点
            node.setRtag(true);
            node.setRight(nodesQueue.peek());
        } else {
            nodesQueue.add(node.getRight());
        }

        levelorderThreading(nodesQueue.poll(), node);
    }

    private void preorder(INode node, List<Element> elements) {
        if (null == node || node instanceof NILNode) return;

        elements.add(new Element(((TreeNode)node).getVal()));

        if (!node.getLtag()) {
            preorder(node.getLeft(), elements);
        } else {
            preorder(node.getRight(), elements);
        }
    }

    private INode findLeftmostNode(INode node) {
        while (null != node.getLeft() && !node.getLtag()) {
            node = node.getLeft();
        }

        return node;
    }

    private void clear() {
        root = null;
    }

    private Queue<INode> nodesQueue;
    private void insert(INode node) {
        if (null == node) return;

        if (null == root) {
            root = node;
            if (node instanceof TreeNode) nodesQueue.add(root);
            return;
        }

        INode n1 = nodesQueue.peek();
        if (null == n1.getLeft()) {
            n1.setLeft(node);
            node.setParent(n1);

            if (n1.getLeft() instanceof TreeNode) nodesQueue.add(n1.getLeft());
        } else if (null == n1.getRight()) {
            n1.setRight(node);
            node.setParent(n1);

            if (n1.getRight() instanceof TreeNode) nodesQueue.add(n1.getRight());
            nodesQueue.poll();
        }
    }
}
