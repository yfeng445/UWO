import java.util.ArrayList;
import java.util.List;

class Node
{
    private DataItem data;
    private Node parent;
    private Node left;
    private Node right;
    public Node(DataItem item,Node p,Node le,Node ri)
    {
        data = new DataItem(item.getKey(),item.getContent());
        left = le;
        right = ri;
        parent = p;
    }
    public DataItem getData()
    {
        return data;
    }
    public void setData(DataItem item)
    {
        data = item;
    }
    public Node getLeft()
    {
        return left;
    }
    public Node getRight()
    {
        return right;
    }
    public Node getParent()
    {
        return parent;
    }
    public void setParent(Node p)
    {
        parent = p;
    }
    public void setLeft(Node le)
    {
        left = le;
    }
    public void setRight(Node ri)
    {
        right = ri;
    }
}

class BinarySearchTree
{
    private Node root;
    private List<String> names;
    public BinarySearchTree()
    {
        root = null;
        names = new ArrayList<>();
    }
    public List<String> getOrderNames()
    {
        names.clear();
        OrderTraveral(root);
        return names;
    }
    public void OrderTraveral(Node node)
    {
        if(node == null)
            return;
        OrderTraveral(node.getLeft());
        names.add(node.getData().getKey().getName());
        OrderTraveral(node.getRight());
    }
    public Node getRoot()
    {
        return root;
    }
    public Node serachValue(Node node,Key k)
    {
        if(node == null || node.getData().getKey().compareTo(k) == 0)
            return node;
        if(node.getData().getKey().compareTo(k) < 0)
            return serachValue(node.getRight(),k);
        else
            return serachValue(node.getLeft(),k);
    }
    public Node getMinNode(Node node)
    {
        Node curr = node;
        while(curr.getLeft() != null)
            curr = curr.getLeft();
        return curr;
    }
    public Node getMaxNode(Node node)
    {
        Node curr = node;
        while(curr.getRight() != null)
            curr = curr.getRight();
        return curr;
    }
    public Node getSuccessorNode(Node node)
    {
        if(node == null)
            return null;
        if(node.getRight() != null)
            return getMinNode(node.getRight());
        Node p = node.getParent();
        while(p != null && node.getData().getKey().compareTo(p.getRight().getData().getKey()) == 0)
        {
            node = p;
            p = p.getParent();
        }
        return p;
    }
    public Node getPredecessorNode(Node node)
    {
        if(node == null)
            return null;
        if(node.getLeft() != null)
            return getMaxNode(node.getLeft());
        Node p = node.getParent();
        while(p != null && node.getData().getKey().compareTo(p.getLeft().getData().getKey()) == 0)
        {
            node = p;
            p = p.getParent();
        }
        return p;
    }
    public boolean insertNode(DataItem item)
    {
        if(root == null)
        {
            root = new Node(item,null,null,null);
            return true;
        }
        Node curr = root;
        Node preCurr = root;
        while(curr != null)
        {
            preCurr = curr;
            if(item.getKey().compareTo(curr.getData().getKey()) > 0)
                curr = curr.getRight();
            else if (item.getKey().compareTo(curr.getData().getKey()) < 0)
                curr = curr.getLeft();
            else
                return false;
        }
        Node newNode = new Node(item,preCurr,null,null);
        if(item.getKey().compareTo(preCurr.getData().getKey()) > 0)
            preCurr.setRight(newNode);
        else
            preCurr.setLeft(newNode);
        return true;
    }
    public boolean deleteNode(Key k)
    {
        Node node = serachValue(root,k);
        if(node == null)
            return false;
        if(node.getLeft() == null && node.getRight() == null && root.getLeft() == null && root.getRight() == null)
        {
            root = null;
            return true;
        }
        if(node.getLeft() == null && node.getRight() == null)
        {
            if(node.getParent().getLeft() != null && node.getParent().getLeft().getData().getKey().compareTo(k) == 0)
                node.getParent().setLeft(null);
            else
                node.getParent().setRight(null);
        }
        else if(node.getLeft() == null && node.getRight() != null)
        {
            if(node.getParent().getLeft().getData().getKey().compareTo(k) == 0)
                node.getParent().setLeft(node.getRight());
            else
                node.getParent().setRight(node.getRight());
        }
        else if(node.getLeft() != null && node.getRight() == null)
        {
            if(node.getParent().getLeft().getData().getKey().compareTo(k) == 0)
                node.getParent().setLeft(node.getLeft());
            else
                node.getParent().setRight(node.getLeft());
            return true;
        }
        else
        {
            Node snode = getSuccessorNode(node);
            node.setData(snode.getData());
            if(snode.getParent().getLeft().getData().getKey().compareTo(snode.getData().getKey()) == 0)
            {
                snode.getParent().setLeft(snode.getRight());
            }
            else
            {
                snode.getParent().setRight(snode.getRight());
            }
        }
        return true;
    }
}
public class OrderedDictionary implements OrderedDictionaryADT
{
    private BinarySearchTree dataTree;
    public OrderedDictionary()
    {
        dataTree = new BinarySearchTree();
    }
    public List<String> getNames()
    {
        return dataTree.getOrderNames();
    }
    @Override
    public DataItem get(Key k) {
        Node node = dataTree.serachValue(dataTree.getRoot(),k);
        if(node != null)
            return node.getData();
        else
            return null;
    }

    @Override
    public void put(DataItem d) throws DictionaryException {
        if(!dataTree.insertNode(d))
        {
            throw new DictionaryException("Insert Failuee,Data Item existence");
        }
    }

    @Override
    public void remove(Key k) throws DictionaryException {
        if(!dataTree.deleteNode(k))
        {
            throw new DictionaryException("No record in the ordered dictionary has key ("+k.getName()+","+k.getKind()+").");
        }
    }

    @Override
    public DataItem successor(Key k) {
        Node node = dataTree.serachValue(dataTree.getRoot(),k);
        Node ret = dataTree.getSuccessorNode(node);
        if(ret != null)
        {
            return ret.getData();
        }
        else
        {
            return null;
        }
    }

    @Override
    public DataItem predecessor(Key k) {
        Node node = dataTree.serachValue(dataTree.getRoot(),k);
        Node ret = dataTree.getPredecessorNode(node);
        if(ret != null)
        {
            return ret.getData();
        }
        else
        {
            return null;
        }
    }

    @Override
    public DataItem smallest() {
        Node node = dataTree.getMinNode(dataTree.getRoot());
        if(node != null)
            return node.getData();
        else
            return null;
    }

    @Override
    public DataItem largest() {
        Node node = dataTree.getMaxNode(dataTree.getRoot());
        if(node != null)
            return node.getData();
        else
            return null;
    }
}
