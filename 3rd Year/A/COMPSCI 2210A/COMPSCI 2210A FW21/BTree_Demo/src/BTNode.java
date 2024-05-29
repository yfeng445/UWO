package container.symboltable.BTree;

public class BTNode<K extends Comparable<K>, V> {

	// 构成B树的最小度数
	public final static int MIN_DEGREE = 3;
	// 除根节点外，每个结点中总键数的下限
	public final static int LOWER_BOUND_KEYNUM = MIN_DEGREE - 1;
	// 包含根节点外，每个结点中总键数的上限
	public final static int UPPER_BOUND_KEYNUM = (MIN_DEGREE * 2) - 1;

	protected boolean mIsLeaf;// 标记此节点是否为叶子结点
	protected int mCurrentKeyNum;// 此节点的键数量计数器
	protected BTKeyValue<K, V>[] mKeys;// 用于存键值对的数组
	protected BTNode<K, V>[] mChildren;// 用于存子结点的数组

	/**
	 * 构造函数
	 */
	@SuppressWarnings("unchecked")
	public BTNode() {
		mIsLeaf = true;
		mCurrentKeyNum = 0;
		mKeys = new BTKeyValue[UPPER_BOUND_KEYNUM];
		mChildren = new BTNode[UPPER_BOUND_KEYNUM + 1];
	}

	protected static BTNode<?, ?> getChildNodeAtIndex(BTNode<?, ?> btNode, int keyIdx, int nDirection) {
		if (btNode.mIsLeaf) {
			return null;
		}
		keyIdx += nDirection;
		if ((keyIdx < 0) || (keyIdx > btNode.mCurrentKeyNum)) {
			throw new IllegalArgumentException();
		}

		return btNode.mChildren[keyIdx];
	}

	/**
	 * 返回btNode节点中位于keyIdx位上的键左边的子结点
	 * @param btNode
	 * @param keyIdx
	 * @return
	 */
	protected static BTNode<?, ?> getLeftChildAtIndex(BTNode<?, ?> btNode, int keyIdx) {
		return getChildNodeAtIndex(btNode, keyIdx, 0);
	}

	/**
	 * 返回btNode节点中位于keyIdx位上的键右边的子结点
	 * @param btNode
	 * @param keyIdx
	 * @return
	 */
	protected static BTNode<?, ?> getRightChildAtIndex(BTNode<?, ?> btNode, int keyIdx) {
		return getChildNodeAtIndex(btNode, keyIdx, 1);
	}

	/**
	 * @param parentNode
	 * @param keyIdx
	 * @return 返回父结点的keyIdx位上的子结点的左兄弟结点
	 */
	protected static BTNode<?, ?> getLeftSiblingAtIndex(BTNode<?, ?> parentNode, int keyIdx) {
		return getChildNodeAtIndex(parentNode, keyIdx, -1);
	}

	/**
	 * 
	 * @param parentNode
	 * @param keyIdx
	 * @return	返回父结点的keyIdx位上的子结点的右兄弟结点
	 */
	protected static BTNode<?, ?> getRightSiblingAtIndex(BTNode<?, ?> parentNode, int keyIdx) {
		return getChildNodeAtIndex(parentNode, keyIdx, 1);
	}
	
	
	/**
	 * 判断父结点的keyIdx位上的子结点是否存在左兄弟结点
	 * @param parentNode
	 * @param keyIdx
	 * @return
	 */
	protected static boolean hasLeftSiblingAtIndex(BTNode<?, ?> parentNode, int keyIdx) {
		if (keyIdx - 1 < 0) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 判断父结点的keyIdx位上的子结点是否存在右兄弟结点
	 * @param parentNode
	 * @param keyIdx
	 * @return
	 */
	protected static boolean hasRightSiblingAtIndex(BTNode<?, ?> parentNode, int keyIdx) {
		if (keyIdx + 1 > parentNode.mCurrentKeyNum) {
			return false;
		} else {
			return true;
		}
	}
}
