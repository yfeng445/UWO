package container.symboltable.BTree;

import container.queue.Queue;

/**
 * @author Herry
 *
 * @param <K>
 * @param <V>
 */
public class BTree<K extends Comparable<K>, V> {

	private BTNode<K, V> mRoot = null;
	private long mSize = 0l;

	/**
	 * @return
	 */
	public BTNode<K, V> getRootNode() {
		return mRoot;
	}

	/**
	 * @return
	 */
	public long size() {
		return mSize;
	}

	/**
	 * 
	 */
	public void clear() {
		mSize = 0l;
		mRoot = null;
	}

	/**
	 * @return
	 */
	private BTNode<K, V> createNode() {
		BTNode<K, V> btNode = new BTNode<>();
		btNode.mIsLeaf = true;
		btNode.mCurrentKeyNum = 0;
		return btNode;
	}

	/**
	 * @param key
	 */
	private void checkKey(K key) {
		if (key == null) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * 查找指定键所对应的值
	 * 
	 * @param key
	 * @return 若键存在，则返回键所对应的值。若键不存在，则抛出异常。
	 */
	public V search(K key) {

		checkKey(key);

		BTNode<K, V> currentNode = mRoot;

		// 迭代查找每一个可能存储key的结点
		while (currentNode != null) {
			int possibleIdx = binarySearch(mRoot, key);
			BTKeyValue<K, V> possibleKeyValue = currentNode.mKeys[possibleIdx];
			// 判断二分查找返回位置索引处的元素是否为查找的元素，若是则返回其值，如不是，则迭代到下一个可能的结点中查找
			if (possibleIdx < currentNode.mCurrentKeyNum && key.compareTo(possibleKeyValue.mKey) == 0) {
				return possibleKeyValue.mValue;
			} else {
				currentNode = currentNode.mChildren[possibleIdx];
			}
		}
		return null;
	}

	/**
	 * 用二分查找法查找结点中键的位置，若找到返回键的位置，若没找到，则返回键应该插入的位置
	 * 
	 * @param btNode
	 * @param key
	 * @return
	 */
	private int binarySearch(BTNode<K, V> btNode, K key) {
		BTKeyValue<K, V>[] keys = btNode.mKeys;
		int lo = 0;
		int hi = btNode.mCurrentKeyNum - 1;
		while (lo <= hi) {
			int mid = (hi - lo) / 2 + lo;
			int cmp = key.compareTo(keys[mid].mKey);
			if (cmp == 0) {
				return mid;
			} else if (cmp > 0) {
				lo = mid + 1;
			} else if (cmp < 0) {
				hi = mid - 1;
			}
		}
		return lo;
	}

	/**
	 * 将键-值对插入到BTree结构中
	 * 
	 * @param key   键不允许为null
	 * @param value
	 */
	public void insert(K key, V value) {

		checkKey(key);

		if (mRoot == null) {
			mRoot = createNode();
		}
		// 使用递归的方法将键-值对插入到BTree结构中
		mRoot = insert(mRoot, key, value);

	}

	/**
	 * 递归插入方法
	 * 
	 * @param x     要插入到的结点
	 * @param key
	 * @param value
	 * @return
	 */
	private BTNode<K, V> insert(BTNode<K, V> x, K key, V value) {

		// 1.首先判断此节点是否已经为满，若满，则将此节点分裂
		if (x.mCurrentKeyNum == BTNode.UPPER_BOUND_KEYNUM) {
			x = split(x);
		}
		// 2.对没有满的结点进行键值对的查找，找出可能的键值对索引，和可能的键值对
		int possibleIdx = binarySearch(x, key);
		/*
		 * 由于第一步操作会确定当前节点为非满结点，故不用担心数组越界问题(不然试想，当此结点已满，且要插入的键大于此节点中所有键，
		 * 故possibleIdx的值会等于UPPER_BOUND_KEYNUM，故造成越界)
		 */
		BTKeyValue<K, V> possibleKeyValue = x.mKeys[possibleIdx];
		/*
		 * 3.判断可能的键值对中的键是否与要插入的键相同（当要插入的键大于当前结点中所有的键时，possibleKeyValue取值为x.mKeys[x.
		 * mCurrentKeyNum]为null，故要判断possibleKeyValue的值是否为空，以防止空指针异常）
		 * 如果相同则直接替换当前值为插入值，并返回当前结点(用于更新)
		 */
		if (possibleKeyValue != null && key.compareTo(possibleKeyValue.mKey) == 0) {
			possibleKeyValue.mValue = value;
			return x;
		}
		/*
		 * 4.当前节点为叶子节点时，直接插入（由于在最前边进行了当前结点是否为满的判断，并做了相应的处理，故到此步插入键值对后，此节点最多为满，且不会溢出）
		 * 当前结点不为叶子结点时，递归到下一个可能的结点继续寻找、插入
		 */
		if (x.mIsLeaf) {
			// 4.1
			for (int i = x.mCurrentKeyNum; i > possibleIdx; i--) {
				x.mKeys[i] = x.mKeys[i - 1];
			}
			x.mKeys[possibleIdx] = new BTKeyValue<>(key, value);
			x.mCurrentKeyNum++;
			mSize++;
		} else {
			// 4.2
			BTNode<K, V> t = insert(x.mChildren[possibleIdx], key, value);
			/*
			 * 4.3判断当返回的结点中的键值对数量为1时，说明返回的结点经过了分裂，故需要将其合并到当前节点中（同上理，合并后，当前结点最多为满）
			 */
			if (t.mCurrentKeyNum == 1) {
				// 4.3.1移动当前节点中的键值对为要合并的键值对腾出地方，并存入
				for (int i = x.mCurrentKeyNum; i > possibleIdx; i--) {
					x.mKeys[i] = x.mKeys[i - 1];
				}
				x.mKeys[possibleIdx] = t.mKeys[0];
				// 4.3.2移动当前节点中的子结点为要合并的子结点腾出地方，并存入
				for (int i = x.mCurrentKeyNum + 1; i > possibleIdx + 1; i--) {
					x.mChildren[i] = x.mChildren[i - 1];
				}
				x.mChildren[possibleIdx] = t.mChildren[0];
				x.mChildren[possibleIdx + 1] = t.mChildren[1];
				// 4.3.3更新当前结点的键值对计数器
				x.mCurrentKeyNum++;
			}
		}
		return x;
	}

	/**
	 * 将满结点x分裂为含有一个键值对的父结点和两个子结点，并返回父结点的链接
	 * 
	 * @param x
	 * @return
	 */
	private BTNode<K, V> split(BTNode<K, V> x) {
		int mid = x.mCurrentKeyNum / 2;

		BTNode<K, V> left = new BTNode<>();
		for (int i = 0; i < mid; i++) {
			left.mKeys[i] = x.mKeys[i];
			left.mChildren[i] = x.mChildren[i];
		}
		left.mChildren[mid] = x.mChildren[mid];
		left.mIsLeaf = x.mIsLeaf;
		left.mCurrentKeyNum = mid;

		BTNode<K, V> right = new BTNode<>();
		for (int i = mid + 1; i < x.mCurrentKeyNum; i++) {
			right.mKeys[i - mid - 1] = x.mKeys[i];
			right.mChildren[i - mid - 1] = x.mChildren[i];
		}
		right.mChildren[x.mCurrentKeyNum - mid - 1] = x.mChildren[x.mCurrentKeyNum];
		right.mIsLeaf = x.mIsLeaf;
		right.mCurrentKeyNum = x.mCurrentKeyNum - mid - 1;

		BTNode<K, V> top = new BTNode<>();
		top.mCurrentKeyNum = 1;
		top.mIsLeaf = false;
		top.mKeys[0] = x.mKeys[mid];
		top.mChildren[0] = left;
		top.mChildren[1] = right;
		return top;
	}

	/**
	 * @return
	 */
	public BTKeyValue<K, V> minKey() {
		return minKey(mRoot);
	}

	/**
	 * @param x
	 * @return
	 */
	private BTKeyValue<K, V> minKey(BTNode<K, V> x) {
		if (x == null) {
			return null;
		}
		if (x.mChildren[0] != null) {
			return minKey(x.mChildren[0]);
		} else {
			return x.mKeys[0];
		}
	}

	/**
	 * @return
	 */
	public BTKeyValue<K, V> maxKey() {
		return maxKey(mRoot);
	}

	/**
	 * @param x
	 * @return
	 */
	private BTKeyValue<K, V> maxKey(BTNode<K, V> x) {
		if (x == null) {
			return null;
		}
		if (x.mChildren[x.mCurrentKeyNum] != null) {
			return maxKey(x.mChildren[x.mCurrentKeyNum]);
		} else {
			return x.mKeys[x.mCurrentKeyNum - 1];
		}
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public V deleteKey(K key) {

		checkKey(key);

		V v = search(key);
		// 递归的删除键key
		mRoot = deleteKey(mRoot, key);
		return v;
	}

	/**
	 * @param x
	 * @param key
	 * @return
	 */
	private BTNode<K, V> deleteKey(BTNode<K, V> x, K key) {

		// 1.获取要删除的键可能处在当前结点上的索引位置
		int possibleIdx = binarySearch(x, key);

		// 2.根据当前结点是否为叶子结点分情况讨论
		if (x.mIsLeaf == true) {
			// 2.1当前结点为叶子节点

			if (possibleIdx < x.mCurrentKeyNum && key.compareTo(x.mKeys[possibleIdx].mKey) == 0) {
				// 2.1.1判断在当前结点上possible索引位置上的键是否与要删除的键相等（前提是possible索引小于当前节点键的数量，负责会出现空指针异常）
				// 如果相等，则直接删除此键，否则，此键不存在树中，不做任何操作

				for (int i = possibleIdx; i < x.mCurrentKeyNum - 1; i++) {
					x.mKeys[i] = x.mKeys[i + 1];
				}
				x.mKeys[x.mCurrentKeyNum - 1] = null;
				x.mCurrentKeyNum--;
				mSize--;
			}
		} else {
			// 2.2当前结点不为子结点
			if (possibleIdx < x.mCurrentKeyNum && key.compareTo(x.mKeys[possibleIdx].mKey) == 0) {
				// 2.2.1判断在当前结点上possible索引位置上的键是否与要删除的键相等（前提是possible索引小于当前节点键的数量，负责会出现空指针异常）
				// 如果成立，用possible索引处的子结点的最大键替换要删除的键

				// 1）记住possilbe索引处子结点的最大键
				BTKeyValue<K, V> keyNeedToSwim = maxKey(x.mChildren[possibleIdx]);

				// 2）将1）中记住的键删除
				x = deleteKey(x, keyNeedToSwim.mKey);

				// 3）将key替换为记住的键
				possibleIdx = binarySearch(x, key);
				x.mKeys[possibleIdx] = keyNeedToSwim;

			} else {
				// 2.2.2
				// 如果不成立，递归的在possible索引处子结点上删除键key

				// 递归删除
				BTNode<K, V> t = deleteKey(x.mChildren[possibleIdx], key);

				// 检测删除后返回结点的状态，如果不满足键数量>=最低度数-1，则酌情旋转或合并
				if (t.mCurrentKeyNum < BTNode.LOWER_BOUND_KEYNUM) {
					// 不满足键数量>=最低度数-1

					// 判断返回结点的兄弟结点的状况，若兄弟结点的键数量>最低度数-1，则旋转（向兄弟结点借键），若兄弟结点的键数量<=最低度数-1，则与兄弟结点合并
					if (BTNode.hasLeftSiblingAtIndex(x, possibleIdx)) {
						if (BTNode.getLeftSiblingAtIndex(x, possibleIdx).mCurrentKeyNum > BTNode.LOWER_BOUND_KEYNUM) {
							leftRotate(x, possibleIdx);
						} else {
							leftMerge(x, possibleIdx);
						}
					} else if (BTNode.hasRightSiblingAtIndex(x, possibleIdx)) {
						if (BTNode.getRightSiblingAtIndex(x, possibleIdx).mCurrentKeyNum > BTNode.LOWER_BOUND_KEYNUM) {
							rightRotate(x, possibleIdx);
						} else {
							rightMerge(x, possibleIdx);
						}
					}

					// 判断删完后根节点是否没有键存在，若没有，则将根节点重新赋值
					if (x == mRoot && x.mCurrentKeyNum == 0) {
						x = x.mChildren[0];
					}
				}
			}
		}
		return x;
	}

	/**
	 * 合并父结点中位于possibleIdx和possibleIdx+1处的俩结点（由此可见可用执行做合并来完成右合并同样的任务）
	 * 
	 * @param x
	 * @param possibleIdx
	 * @return
	 */
	private BTNode<K, V> rightMerge(BTNode<K, V> x, int possibleIdx) {
		return leftMerge(x, possibleIdx + 1);
	}

	/**
	 * 合并父结点中位于possibleIdx和possibleIdx-1处的俩结点
	 * 
	 * @param x
	 * @param possibleIdx
	 * @return
	 */
	private BTNode<K, V> leftMerge(BTNode<K, V> x, int possibleIdx) {
		// 获取左节点
		BTNode<K, V> leftNode = x.mChildren[possibleIdx - 1];
		// 获取父结点要合并到左节点的键值对
		BTKeyValue<K, V> topKey = x.mKeys[possibleIdx - 1];
		// 获取需要合并的结点
		BTNode<K, V> possibleNode = x.mChildren[possibleIdx];
		// 将父结点获取的键值对放入左节点
		leftNode.mKeys[leftNode.mCurrentKeyNum] = topKey;
		// 将需要合并的结点的键值对全部放入左节点
		for (int i = 0; i < possibleNode.mCurrentKeyNum; i++) {
			leftNode.mKeys[i + leftNode.mCurrentKeyNum + 1] = possibleNode.mKeys[i];
		}
		// 同理，将结点链接也移过来
		for (int i = 0; i < possibleNode.mCurrentKeyNum + 1; i++) {
			leftNode.mChildren[i + leftNode.mCurrentKeyNum + 1] = possibleNode.mChildren[i];
		}
		// 更新左节点的键值对计数器
		leftNode.mCurrentKeyNum += 1 + possibleNode.mCurrentKeyNum;
		// 更新父结点
		for (int i = possibleIdx; i < x.mCurrentKeyNum; i++) {
			x.mKeys[i - 1] = x.mKeys[i];
		}
		x.mKeys[x.mCurrentKeyNum - 1] = null;
		for (int i = possibleIdx; i < x.mCurrentKeyNum; i++) {
			x.mChildren[i] = x.mChildren[i + 1];
		}
		x.mChildren[x.mCurrentKeyNum] = null;
		x.mCurrentKeyNum--;
//		System.out.println("leftMerge executed");
		return x;
	}

	/**
	 * 从右结点借一个键值对过来
	 * 
	 * @param x
	 * @param possibleIdx
	 * @return
	 */
	private BTNode<K, V> rightRotate(BTNode<K, V> x, int possibleIdx) {

		// 获取右节点和右节点中最小的键值对
		BTNode<K, V> rightNode = x.mChildren[possibleIdx + 1];
		BTKeyValue<K, V> rightKey = rightNode.mKeys[0];
		// 获取右节点中最小的结点
		BTNode<K, V> rightFirstNode = rightNode.mChildren[0];
		// 获取父结点交换位置的键值对
		BTKeyValue<K, V> topKey = x.mKeys[possibleIdx];
		// 获取需补齐键值对的节点，并将父结点交换位置的键值对加到此节点的最高位
		BTNode<K, V> possibleNode = x.mChildren[possibleIdx];
		possibleNode.mKeys[possibleNode.mCurrentKeyNum] = topKey;
		// 将右节点中最小的结点添加到此节点
		possibleNode.mChildren[possibleNode.mCurrentKeyNum + 1] = rightFirstNode;
		possibleNode.mCurrentKeyNum++;

		// 将父结点拿走键值对的位置填上右节点提出的键值对
		x.mKeys[possibleIdx] = rightKey;
		// 将右节点提出的键值对和最小结点在右节点中删除
		for (int i = 1; i < rightNode.mCurrentKeyNum; i++) {
			rightNode.mKeys[i - 1] = rightNode.mKeys[i];
		}
		rightNode.mKeys[rightNode.mCurrentKeyNum - 1] = null;
		for (int i = 1; i < rightNode.mCurrentKeyNum + 1; i++) {
			rightNode.mChildren[i - 1] = rightNode.mChildren[i];
		}
		rightNode.mChildren[rightNode.mCurrentKeyNum] = null;
		rightNode.mCurrentKeyNum--;
//		System.out.println("rightRotate executed");
		return x;
	}

	/**
	 * ‘
	 * 
	 * @param x           父结点
	 * @param possibleIdx 需要补充键值对的子结点的索引
	 * @return
	 */
	private BTNode<K, V> leftRotate(BTNode<K, V> x, int possibleIdx) {

		// 获取左节点和左节点中最大的键值对
		BTNode<K, V> leftNode = x.mChildren[possibleIdx - 1];
		BTKeyValue<K, V> leftKey = leftNode.mKeys[leftNode.mCurrentKeyNum - 1];
		// 获取左节点中最大的结点
		BTNode<K, V> leftLastNode = leftNode.mChildren[leftNode.mCurrentKeyNum];
		// 获取父结点交换位置的键值对
		BTKeyValue<K, V> topKey = x.mKeys[possibleIdx - 1];
		// 获取需补齐键值对的节点，并移动其中的键值对将最低位空出来：以用来填充从父结点交换过来的键值对
		BTNode<K, V> possibleNode = x.mChildren[possibleIdx];
		for (int i = possibleNode.mCurrentKeyNum; i > 0; i--) {
			possibleNode.mKeys[i] = possibleNode.mKeys[i - 1];
		}
		// 同理对此节点的子结点
		for (int i = possibleNode.mCurrentKeyNum + 1; i > 0; i--) {
			possibleNode.mChildren[i] = possibleNode.mChildren[i - 1];
		}
		// 填充键值对和其带过来的链接，并将键数量计数器加1
		possibleNode.mKeys[0] = topKey;
		possibleNode.mChildren[0] = leftLastNode;
		possibleNode.mCurrentKeyNum++;
		// 将父结点拿走键值对的位置填上左节点提出的键值对
		x.mKeys[possibleIdx - 1] = leftKey;
		// 将左节点提出的键值对和子结点在左节点中删除
		leftNode.mKeys[leftNode.mCurrentKeyNum - 1] = null;
		leftNode.mChildren[leftNode.mCurrentKeyNum] = null;
		leftNode.mCurrentKeyNum--;
//		System.out.println("leftRotate executed");
		return x;
	}

	public static void main(String[] args) {
		BTree<Integer, String> bt = new BTree<>();
		for (int i = 1; i <= 56; i++) {
			bt.insert(i, "");
		}
		System.out.println("insert completed");
		System.out.println("size before delete:" + bt.size());
		bt.deleteKey(27);
		bt.deleteKey(42);
		System.out.println("size after delete:" + bt.size());
		Queue<BTNode<Integer, String>> queue = new Queue<>();

		queue.enqueue(bt.getRootNode());
		while (!queue.isEmpty()) {
			BTNode<Integer, String> btn = queue.dequeue();
			for (int i = 0; i < btn.mCurrentKeyNum; i++) {
				System.out.print(btn.mKeys[i].mKey + " ");
			}
			System.out.println();
			if (!btn.mIsLeaf) {
				for (int i = 0; i <= btn.mCurrentKeyNum; i++) {
					queue.enqueue(btn.mChildren[i]);
				}
			}
		}
	}
}
