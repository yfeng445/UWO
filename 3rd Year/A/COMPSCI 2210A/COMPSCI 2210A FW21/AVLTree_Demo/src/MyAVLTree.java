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
     * ����������
     */
    public AVLNode<E> insert(E x, AVLNode<E> t) {
        if (t == null) {
            return new AVLNode<E>(x);
        }
        //�ȱȽ� �ǲ���߻��ǲ��ұ�
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {//�嵽��������
            t.left = insert(x, t.left);
            //����֮��Ҫ�ж��Ƿ������ƽ�⣬��Ϊ���������������
            // ֻ���������Ż����ƽ�⣬���������ĸ߼�ȥ�������ĸ�
            if (height(t.left) - height(t.right) == 2) {
                //�������2��˵��ƽ�ⱻ�����ˣ���Ҫ���е������Ϳ�ѡ��ʲô��������
                if (x.compareTo(t.left.element) < 0) {
                    //���xС��t����������ֵ����ôx�ᱻ�嵽t�����������������ϣ�����LL ������ת������
                    t = rightRotate(t);
                } else {
                    //���x����t����������ֵ����ᱻ�嵽t�����������������ϣ�����LR����������ת������ת��������
                    t = leftAndRightRotate(t);
                }
            }
        } else if (compareResult > 0) {//�嵽�������ϣ��߼�������һ����
            t.right = insert(x, t.right);
            if (height(t.right) - height(t.left) == 2) {
                if (x.compareTo(t.right.element) > 0) {
                    t = leftRotate(t);
                } else {
                    t = rightAndLeftRotate(t);
                }
            }
        } else {
            //�Ѿ������ֵ��
        }
        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;
    }

    /**
     * ɾ������
     */
    private AVLNode<E> remove(E x, AVLNode<E> t) {
        if (t == null)
            return null;
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            t.left = remove(x, t.left);
            //����֮����֤�������Ƿ�ƽ��
            if (t.right != null) {        //��������Ϊ�գ���һ����ƽ��ģ���ʱ�������൱�Ը��ڵ�������Ϊ1, ����ֻ�����������ǿ����
                if (t.left == null) {     //��������ɾ����Ϊ�գ�����Ҫ�ж�������
                    if (height(t.right) - t.height == 2) {
                        AVLNode<E> k = t.right;
                        if (k.right != null) {        //���������ڣ��������������ת
                            t = leftRotate(t);
                        } else {                      //���������������˫��ת
                            t = rightAndLeftRotate(t);
                        }
                    }
                }
                if (t.left!=null){                  //�����ж����������ĸ߶Ȳ�
                    //����������Ҳ���ܲ�ƽ�⣬����ƽ�����������ٿ�������
                    AVLNode<E> k = t.left;
                    //ɾ������Ĭ��������������С�ڵ㲹ɾ���Ľڵ�
                    //k���������߶Ȳ�����k��������
                    if (k.right != null) {
                        if (height(k.left) - height(k.right) == 2) {
                            AVLNode<E> m = k.left;
                            if (m.left != null) {     //���������ڣ��������������ת
                                k = rightRotate(k);
                            } else {                      //���������������˫��ת
                                k = leftAndRightRotate(k);
                            }
                        }
                    } else {
                        if (height(k.left) - k.height == 2) {
                            AVLNode<E> m = k.left;
                            if (m.left != null) {     //���������ڣ��������������ת
                                k = rightRotate(k);
                            } else {                      //���������������˫��ת
                                k = leftAndRightRotate(k);
                            }
                        }
                    }
                    if (height(t.right) - height(t.left) == 2) {
                        //����������һ����ƽ��ģ�����ʧ��Ļ�����ת���Խ������
                        t = leftRotate(t);
                    }
                }
            }
            //����֮�����heightֵ
            t.height = Math.max(height(t.left), height(t.right)) + 1;
        } else if (compareResult > 0) {
            t.right = remove(x, t.right);
            //������֤�����Ƿ�ƽ��
            if (t.left != null) {         //��������Ϊ�գ���һ����ƽ��ģ���ʱ�������൱�Ը��ڵ�������Ϊ1
                t = balanceChild(t);
            }
            //����֮�����heightֵ
            t.height = Math.max(height(t.left), height(t.right)) + 1;
        } else if (t.left != null && t.right != null) {
            //Ĭ����������������С���ݴ���ýڵ�����ݲ��ݹ��ɾ���Ǹ��ڵ�
            AVLNode<E> min = t.right;
            while (min.left != null) {
                min = min.left;
            }
//            t.element = findMin(t.right).element;
            t.element = min.element;
            t.right = remove(t.element, t.right);
            t = balanceChild(t);
            //����֮�����heightֵ
            t.height = Math.max(height(t.left), height(t.right)) + 1;
        } else {
            t = (t.left != null) ? t.left : t.right;
        }
        return t;
    }

    private AVLNode<E> balanceChild(AVLNode<E> t) {
        if (t.right == null) {        //��������ɾ����Ϊ�գ���ֻ���ж�����������ĸ߶Ȳ�
            if (height(t.left) - t.height == 2) {
                AVLNode<E> k = t.left;
                if (k.left != null) {
                    t = rightRotate(t);
                } else {
                    t = leftAndRightRotate(t);
                }
            }
        } else {              //��������ɾ����ǿգ����ж����������ĸ߶Ȳ�
            //����������Ҳ���ܲ�ƽ�⣬����ƽ�����������ٿ�������
            AVLNode<E> k = t.right;
            //ɾ������Ĭ��������������С�ڵ㣨���󣩲�ɾ���Ľڵ�

            if (k.left != null) {
                if (height(k.right) - height(k.left) == 2) {
                    AVLNode<E> m = k.right;
                    if (m.right != null) {        //���������ڣ��������������ת
                        k = leftRotate(k);
                    } else {                      //���������������˫��ת
                        k = rightAndLeftRotate(k);
                    }
                }
            } else {
                if (height(k.right) - k.height == 2) {
                    AVLNode<E> m = k.right;
                    if (m.right != null) {        //���������ڣ��������������ת
                        k = leftRotate(k);
                    } else {                      //���������������˫��ת
                        k = rightAndLeftRotate(k);
                    }
                }
            }
            //����������һ����ƽ��ģ�����ʧ��Ļ�����ת���Խ������
            if (height(t.left) - height(t.right) == 2) {
                t = rightRotate(t);
            }
        }
        return t;
    }


    /**
     * ����ת
     *
     * @param t ��Ҫ��������
     * @return ���������
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
     * ����ת
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
     * ������������
     */
    private AVLNode<E> leftAndRightRotate(AVLNode<E> t) {
        t.left = leftRotate(t.left);
        return rightRotate(t);
    }

    /**
     * ������������
     */
    private AVLNode<E> rightAndLeftRotate(AVLNode<E> t) {
        t.right = rightRotate(t.right);
        return leftRotate(t);
    }


    /**
     * ��ȡָ�����ĸ߶�
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