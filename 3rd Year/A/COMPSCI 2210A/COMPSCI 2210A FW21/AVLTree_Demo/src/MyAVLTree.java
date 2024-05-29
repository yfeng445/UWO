public class MyAVLTree<E extends Comparable<E>> {
    private AVLNode root;

    public MyAVLTree() {
        this.root = null;
    }

    public void insert(E x) {
        root = insert(x, root);
    }

    public void remove(E x) {
        remove(x, root);
    }

    public int height() {
        return height(root);
    }

    /**
     * 插入新数据
     */
    public AVLNode<E> insert(E x, AVLNode<E> t) {
        if (t == null) {
            return new AVLNode<E>(x);
        }
        //先比较 是插左边还是插右边
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {//插到左子树上
            t.left = insert(x, t.left);
            //插入之后要判断是否打破了平衡，因为插入的是左子树，
            // 只有左子树才会打破平衡，用左子树的高减去右子树的高
            if (height(t.left) - height(t.right) == 2) {
                //如果等于2，说明平衡被打破了，需要进行调整。就看选择什么方法调整
                if (x.compareTo(t.left.element) < 0) {
                    //如果x小于t的左子树的值，那么x会被插到t的左子树的左子树上，符合LL 用右旋转调整。
                    t = rightRotate(t);
                } else {
                    //如果x大于t的左子树的值，则会被插到t的左子树的右子树上，符合LR，用先左旋转后右旋转来矫正。
                    t = leftAndRightRotate(t);
                }
            }
        } else if (compareResult > 0) {//插到右子树上，逻辑和上面一样。
            t.right = insert(x, t.right);
            if (height(t.right) - height(t.left) == 2) {
                if (x.compareTo(t.right.element) > 0) {
                    t = leftRotate(t);
                } else {
                    t = rightAndLeftRotate(t);
                }
            }
        } else {
            //已经有这个值了
        }
        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;
    }

    /**
     * 删除数据
     */
    private AVLNode<E> remove(E x, AVLNode<E> t) {
        if (t == null)
            return null;
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            t.left = remove(x, t.left);
            //完了之后验证该子树是否平衡
            if (t.right != null) {        //若右子树为空，则一定是平衡的，此时左子树相当对父节点深度最多为1, 所以只考虑右子树非空情况
                if (t.left == null) {     //若左子树删除后为空，则需要判断右子树
                    if (height(t.right) - t.height == 2) {
                        AVLNode<E> k = t.right;
                        if (k.right != null) {        //右子树存在，按正常情况单旋转
                            t = leftRotate(t);
                        } else {                      //否则是右左情况，双旋转
                            t = rightAndLeftRotate(t);
                        }
                    }
                }
                if (t.left!=null){                  //否则判断左右子树的高度差
                    //左子树自身也可能不平衡，故先平衡左子树，再考虑整体
                    AVLNode<E> k = t.left;
                    //删除操作默认用右子树上最小节点补删除的节点
                    //k的左子树高度不低于k的右子树
                    if (k.right != null) {
                        if (height(k.left) - height(k.right) == 2) {
                            AVLNode<E> m = k.left;
                            if (m.left != null) {     //左子树存在，按正常情况单旋转
                                k = rightRotate(k);
                            } else {                      //否则是左右情况，双旋转
                                k = leftAndRightRotate(k);
                            }
                        }
                    } else {
                        if (height(k.left) - k.height == 2) {
                            AVLNode<E> m = k.left;
                            if (m.left != null) {     //左子树存在，按正常情况单旋转
                                k = rightRotate(k);
                            } else {                      //否则是左右情况，双旋转
                                k = leftAndRightRotate(k);
                            }
                        }
                    }
                    if (height(t.right) - height(t.left) == 2) {
                        //右子树自身一定是平衡的，左右失衡的话单旋转可以解决问题
                        t = leftRotate(t);
                    }
                }
            }
            //完了之后更新height值
            t.height = Math.max(height(t.left), height(t.right)) + 1;
        } else if (compareResult > 0) {
            t.right = remove(x, t.right);
            //下面验证子树是否平衡
            if (t.left != null) {         //若左子树为空，则一定是平衡的，此时右子树相当对父节点深度最多为1
                t = balanceChild(t);
            }
            //完了之后更新height值
            t.height = Math.max(height(t.left), height(t.right)) + 1;
        } else if (t.left != null && t.right != null) {
            //默认用其右子树的最小数据代替该节点的数据并递归的删除那个节点
            AVLNode<E> min = t.right;
            while (min.left != null) {
                min = min.left;
            }
//            t.element = findMin(t.right).element;
            t.element = min.element;
            t.right = remove(t.element, t.right);
            t = balanceChild(t);
            //完了之后更新height值
            t.height = Math.max(height(t.left), height(t.right)) + 1;
        } else {
            t = (t.left != null) ? t.left : t.right;
        }
        return t;
    }

    private AVLNode<E> balanceChild(AVLNode<E> t) {
        if (t.right == null) {        //若右子树删除后为空，则只需判断左子树与根的高度差
            if (height(t.left) - t.height == 2) {
                AVLNode<E> k = t.left;
                if (k.left != null) {
                    t = rightRotate(t);
                } else {
                    t = leftAndRightRotate(t);
                }
            }
        } else {              //若右子树删除后非空，则判断左右子树的高度差
            //右子树自身也可能不平衡，故先平衡右子树，再考虑整体
            AVLNode<E> k = t.right;
            //删除操作默认用右子树上最小节点（靠左）补删除的节点

            if (k.left != null) {
                if (height(k.right) - height(k.left) == 2) {
                    AVLNode<E> m = k.right;
                    if (m.right != null) {        //右子树存在，按正常情况单旋转
                        k = leftRotate(k);
                    } else {                      //否则是右左情况，双旋转
                        k = rightAndLeftRotate(k);
                    }
                }
            } else {
                if (height(k.right) - k.height == 2) {
                    AVLNode<E> m = k.right;
                    if (m.right != null) {        //右子树存在，按正常情况单旋转
                        k = leftRotate(k);
                    } else {                      //否则是右左情况，双旋转
                        k = rightAndLeftRotate(k);
                    }
                }
            }
            //左子树自身一定是平衡的，左右失衡的话单旋转可以解决问题
            if (height(t.left) - height(t.right) == 2) {
                t = rightRotate(t);
            }
        }
        return t;
    }


    /**
     * 右旋转
     *
     * @param t 需要调整的树
     * @return 调整后的树
     */
    private AVLNode<E> rightRotate(AVLNode<E> t) {
        AVLNode newTree = t.left;
        t.left = newTree.right;
        newTree.right = t;
        t.height = Math.max(height(t.left), height(t.right)) + 1;
        newTree.height = Math.max(height(newTree.left), height(newTree.right)) + 1;
        return newTree;
    }

    /**
     * 左旋转
     */
    private AVLNode<E> leftRotate(AVLNode t) {
        AVLNode<E> newTree = t.right;
        t.right = newTree.left;
        newTree.left = t;
        t.height = Math.max(height(t.left), height(t.right)) + 1;
        newTree.height = Math.max(height(newTree.left), height(newTree.right)) + 1;
        return newTree;
    }

    /**
     * 先左旋后右旋
     */
    private AVLNode<E> leftAndRightRotate(AVLNode<E> t) {
        t.left = leftRotate(t.left);
        return rightRotate(t);
    }

    /**
     * 先右旋后左旋
     */
    private AVLNode<E> rightAndLeftRotate(AVLNode<E> t) {
        t.right = rightRotate(t.right);
        return leftRotate(t);
    }


    /**
     * 获取指定树的高度
     */
    private int height(AVLNode<E> t) {
        return t == null ? -1 : t.height;
    }

    public void printTree() {
        printTree(root);
    }

    private void printTree(AVLNode<E> tree) {
        if (tree == null) {
            return;
        }
        System.out.print(tree.element + " ");
        printTree(tree.left);
        printTree(tree.right);

    }

    private static class AVLNode<E> {
        E element;
        AVLNode<E> left;
        AVLNode<E> right;
        int height;

        public AVLNode(E element) {
            this(element, null, null);
        }

        public AVLNode(E element, AVLNode<E> left, AVLNode<E> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }
    }
}