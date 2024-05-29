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
	 * ����ָ��������Ӧ��ֵ
	 * 
	 * @param key
	 * @return �������ڣ��򷵻ؼ�����Ӧ��ֵ�����������ڣ����׳��쳣��
	 */
	public V search(K key) {

		checkKey(key);

		BTNode<K, V> currentNode = mRoot;

		// ��������ÿһ�����ܴ洢key�Ľ��
		while (currentNode != null) {
			int possibleIdx = binarySearch(mRoot, key);
			BTKeyValue<K, V> possibleKeyValue = currentNode.mKeys[possibleIdx];
			// �ж϶��ֲ��ҷ���λ����������Ԫ���Ƿ�Ϊ���ҵ�Ԫ�أ������򷵻���ֵ���粻�ǣ����������һ�����ܵĽ���в���
			if (possibleIdx < currentNode.mCurrentKeyNum && key.compareTo(possibleKeyValue.mKey) == 0) {
				return possibleKeyValue.mValue;
			} else {
				currentNode = currentNode.mChildren[possibleIdx];
			}
		}
		return null;
	}

	/**
	 * �ö��ֲ��ҷ����ҽ���м���λ�ã����ҵ����ؼ���λ�ã���û�ҵ����򷵻ؼ�Ӧ�ò����λ��
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
	 * ����-ֵ�Բ��뵽BTree�ṹ��
	 * 
	 * @param key   ��������Ϊnull
	 * @param value
	 */
	public void insert(K key, V value) {

		checkKey(key);

		if (mRoot == null) {
			mRoot = createNode();
		}
		// ʹ�õݹ�ķ�������-ֵ�Բ��뵽BTree�ṹ��
		mRoot = insert(mRoot, key, value);

	}

	/**
	 * �ݹ���뷽��
	 * 
	 * @param x     Ҫ���뵽�Ľ��
	 * @param key
	 * @param value
	 * @return
	 */
	private BTNode<K, V> insert(BTNode<K, V> x, K key, V value) {

		// 1.�����жϴ˽ڵ��Ƿ��Ѿ�Ϊ�����������򽫴˽ڵ����
		if (x.mCurrentKeyNum == BTNode.UPPER_BOUND_KEYNUM) {
			x = split(x);
		}
		// 2.��û�����Ľ����м�ֵ�ԵĲ��ң��ҳ����ܵļ�ֵ���������Ϳ��ܵļ�ֵ��
		int possibleIdx = binarySearch(x, key);
		/*
		 * ���ڵ�һ��������ȷ����ǰ�ڵ�Ϊ������㣬�ʲ��õ�������Խ������(��Ȼ���룬���˽����������Ҫ����ļ����ڴ˽ڵ������м���
		 * ��possibleIdx��ֵ�����UPPER_BOUND_KEYNUM�������Խ��)
		 */
		BTKeyValue<K, V> possibleKeyValue = x.mKeys[possibleIdx];
		/*
		 * 3.�жϿ��ܵļ�ֵ���еļ��Ƿ���Ҫ����ļ���ͬ����Ҫ����ļ����ڵ�ǰ��������еļ�ʱ��possibleKeyValueȡֵΪx.mKeys[x.
		 * mCurrentKeyNum]Ϊnull����Ҫ�ж�possibleKeyValue��ֵ�Ƿ�Ϊ�գ��Է�ֹ��ָ���쳣��
		 * �����ͬ��ֱ���滻��ǰֵΪ����ֵ�������ص�ǰ���(���ڸ���)
		 */
		if (possibleKeyValue != null && key.compareTo(possibleKeyValue.mKey) == 0) {
			possibleKeyValue.mValue = value;
			return x;
		}
		/*
		 * 4.��ǰ�ڵ�ΪҶ�ӽڵ�ʱ��ֱ�Ӳ��루��������ǰ�߽����˵�ǰ����Ƿ�Ϊ�����жϣ���������Ӧ�Ĵ����ʵ��˲������ֵ�Ժ󣬴˽ڵ����Ϊ�����Ҳ��������
		 * ��ǰ��㲻ΪҶ�ӽ��ʱ���ݹ鵽��һ�����ܵĽ�����Ѱ�ҡ�����
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
			 * 4.3�жϵ����صĽ���еļ�ֵ������Ϊ1ʱ��˵�����صĽ�㾭���˷��ѣ�����Ҫ����ϲ�����ǰ�ڵ��У�ͬ�����ϲ��󣬵�ǰ������Ϊ����
			 */
			if (t.mCurrentKeyNum == 1) {
				// 4.3.1�ƶ���ǰ�ڵ��еļ�ֵ��ΪҪ�ϲ��ļ�ֵ���ڳ��ط���������
				for (int i = x.mCurrentKeyNum; i > possibleIdx; i--) {
					x.mKeys[i] = x.mKeys[i - 1];
				}
				x.mKeys[possibleIdx] = t.mKeys[0];
				// 4.3.2�ƶ���ǰ�ڵ��е��ӽ��ΪҪ�ϲ����ӽ���ڳ��ط���������
				for (int i = x.mCurrentKeyNum + 1; i > possibleIdx + 1; i--) {
					x.mChildren[i] = x.mChildren[i - 1];
				}
				x.mChildren[possibleIdx] = t.mChildren[0];
				x.mChildren[possibleIdx + 1] = t.mChildren[1];
				// 4.3.3���µ�ǰ���ļ�ֵ�Լ�����
				x.mCurrentKeyNum++;
			}
		}
		return x;
	}

	/**
	 * �������x����Ϊ����һ����ֵ�Եĸ����������ӽ�㣬�����ظ���������
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
		// �ݹ��ɾ����key
		mRoot = deleteKey(mRoot, key);
		return v;
	}

	/**
	 * @param x
	 * @param key
	 * @return
	 */
	private BTNode<K, V> deleteKey(BTNode<K, V> x, K key) {

		// 1.��ȡҪɾ���ļ����ܴ��ڵ�ǰ����ϵ�����λ��
		int possibleIdx = binarySearch(x, key);

		// 2.���ݵ�ǰ����Ƿ�ΪҶ�ӽ����������
		if (x.mIsLeaf == true) {
			// 2.1��ǰ���ΪҶ�ӽڵ�

			if (possibleIdx < x.mCurrentKeyNum && key.compareTo(x.mKeys[possibleIdx].mKey) == 0) {
				// 2.1.1�ж��ڵ�ǰ�����possible����λ���ϵļ��Ƿ���Ҫɾ���ļ���ȣ�ǰ����possible����С�ڵ�ǰ�ڵ�����������������ֿ�ָ���쳣��
				// �����ȣ���ֱ��ɾ���˼������򣬴˼����������У������κβ���

				for (int i = possibleIdx; i < x.mCurrentKeyNum - 1; i++) {
					x.mKeys[i] = x.mKeys[i + 1];
				}
				x.mKeys[x.mCurrentKeyNum - 1] = null;
				x.mCurrentKeyNum--;
				mSize--;
			}
		} else {
			// 2.2��ǰ��㲻Ϊ�ӽ��
			if (possibleIdx < x.mCurrentKeyNum && key.compareTo(x.mKeys[possibleIdx].mKey) == 0) {
				// 2.2.1�ж��ڵ�ǰ�����possible����λ���ϵļ��Ƿ���Ҫɾ���ļ���ȣ�ǰ����possible����С�ڵ�ǰ�ڵ�����������������ֿ�ָ���쳣��
				// �����������possible���������ӽ��������滻Ҫɾ���ļ�

				// 1����סpossilbe�������ӽ�������
				BTKeyValue<K, V> keyNeedToSwim = maxKey(x.mChildren[possibleIdx]);

				// 2����1���м�ס�ļ�ɾ��
				x = deleteKey(x, keyNeedToSwim.mKey);

				// 3����key�滻Ϊ��ס�ļ�
				possibleIdx = binarySearch(x, key);
				x.mKeys[possibleIdx] = keyNeedToSwim;

			} else {
				// 2.2.2
				// ������������ݹ����possible�������ӽ����ɾ����key

				// �ݹ�ɾ��
				BTNode<K, V> t = deleteKey(x.mChildren[possibleIdx], key);

				// ���ɾ���󷵻ؽ���״̬����������������>=��Ͷ���-1����������ת��ϲ�
				if (t.mCurrentKeyNum < BTNode.LOWER_BOUND_KEYNUM) {
					// �����������>=��Ͷ���-1

					// �жϷ��ؽ����ֵܽ���״�������ֵܽ��ļ�����>��Ͷ���-1������ת�����ֵܽ�����������ֵܽ��ļ�����<=��Ͷ���-1�������ֵܽ��ϲ�
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

					// �ж�ɾ�����ڵ��Ƿ�û�м����ڣ���û�У��򽫸��ڵ����¸�ֵ
					if (x == mRoot && x.mCurrentKeyNum == 0) {
						x = x.mChildren[0];
					}
				}
			}
		}
		return x;
	}

	/**
	 * �ϲ��������λ��possibleIdx��possibleIdx+1��������㣨�ɴ˿ɼ�����ִ�����ϲ�������Һϲ�ͬ��������
	 * 
	 * @param x
	 * @param possibleIdx
	 * @return
	 */
	private BTNode<K, V> rightMerge(BTNode<K, V> x, int possibleIdx) {
		return leftMerge(x, possibleIdx + 1);
	}

	/**
	 * �ϲ��������λ��possibleIdx��possibleIdx-1���������
	 * 
	 * @param x
	 * @param possibleIdx
	 * @return
	 */
	private BTNode<K, V> leftMerge(BTNode<K, V> x, int possibleIdx) {
		// ��ȡ��ڵ�
		BTNode<K, V> leftNode = x.mChildren[possibleIdx - 1];
		// ��ȡ�����Ҫ�ϲ�����ڵ�ļ�ֵ��
		BTKeyValue<K, V> topKey = x.mKeys[possibleIdx - 1];
		// ��ȡ��Ҫ�ϲ��Ľ��
		BTNode<K, V> possibleNode = x.mChildren[possibleIdx];
		// ��������ȡ�ļ�ֵ�Է�����ڵ�
		leftNode.mKeys[leftNode.mCurrentKeyNum] = topKey;
		// ����Ҫ�ϲ��Ľ��ļ�ֵ��ȫ��������ڵ�
		for (int i = 0; i < possibleNode.mCurrentKeyNum; i++) {
			leftNode.mKeys[i + leftNode.mCurrentKeyNum + 1] = possibleNode.mKeys[i];
		}
		// ͬ�����������Ҳ�ƹ���
		for (int i = 0; i < possibleNode.mCurrentKeyNum + 1; i++) {
			leftNode.mChildren[i + leftNode.mCurrentKeyNum + 1] = possibleNode.mChildren[i];
		}
		// ������ڵ�ļ�ֵ�Լ�����
		leftNode.mCurrentKeyNum += 1 + possibleNode.mCurrentKeyNum;
		// ���¸����
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
	 * ���ҽ���һ����ֵ�Թ���
	 * 
	 * @param x
	 * @param possibleIdx
	 * @return
	 */
	private BTNode<K, V> rightRotate(BTNode<K, V> x, int possibleIdx) {

		// ��ȡ�ҽڵ���ҽڵ�����С�ļ�ֵ��
		BTNode<K, V> rightNode = x.mChildren[possibleIdx + 1];
		BTKeyValue<K, V> rightKey = rightNode.mKeys[0];
		// ��ȡ�ҽڵ�����С�Ľ��
		BTNode<K, V> rightFirstNode = rightNode.mChildren[0];
		// ��ȡ����㽻��λ�õļ�ֵ��
		BTKeyValue<K, V> topKey = x.mKeys[possibleIdx];
		// ��ȡ�貹���ֵ�ԵĽڵ㣬��������㽻��λ�õļ�ֵ�Լӵ��˽ڵ�����λ
		BTNode<K, V> possibleNode = x.mChildren[possibleIdx];
		possibleNode.mKeys[possibleNode.mCurrentKeyNum] = topKey;
		// ���ҽڵ�����С�Ľ����ӵ��˽ڵ�
		possibleNode.mChildren[possibleNode.mCurrentKeyNum + 1] = rightFirstNode;
		possibleNode.mCurrentKeyNum++;

		// ����������߼�ֵ�Ե�λ�������ҽڵ�����ļ�ֵ��
		x.mKeys[possibleIdx] = rightKey;
		// ���ҽڵ�����ļ�ֵ�Ժ���С������ҽڵ���ɾ��
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
	 * ��
	 * 
	 * @param x           �����
	 * @param possibleIdx ��Ҫ�����ֵ�Ե��ӽ�������
	 * @return
	 */
	private BTNode<K, V> leftRotate(BTNode<K, V> x, int possibleIdx) {

		// ��ȡ��ڵ����ڵ������ļ�ֵ��
		BTNode<K, V> leftNode = x.mChildren[possibleIdx - 1];
		BTKeyValue<K, V> leftKey = leftNode.mKeys[leftNode.mCurrentKeyNum - 1];
		// ��ȡ��ڵ������Ľ��
		BTNode<K, V> leftLastNode = leftNode.mChildren[leftNode.mCurrentKeyNum];
		// ��ȡ����㽻��λ�õļ�ֵ��
		BTKeyValue<K, V> topKey = x.mKeys[possibleIdx - 1];
		// ��ȡ�貹���ֵ�ԵĽڵ㣬���ƶ����еļ�ֵ�Խ����λ�ճ��������������Ӹ���㽻�������ļ�ֵ��
		BTNode<K, V> possibleNode = x.mChildren[possibleIdx];
		for (int i = possibleNode.mCurrentKeyNum; i > 0; i--) {
			possibleNode.mKeys[i] = possibleNode.mKeys[i - 1];
		}
		// ͬ��Դ˽ڵ���ӽ��
		for (int i = possibleNode.mCurrentKeyNum + 1; i > 0; i--) {
			possibleNode.mChildren[i] = possibleNode.mChildren[i - 1];
		}
		// ����ֵ�Ժ�������������ӣ�������������������1
		possibleNode.mKeys[0] = topKey;
		possibleNode.mChildren[0] = leftLastNode;
		possibleNode.mCurrentKeyNum++;
		// ����������߼�ֵ�Ե�λ��������ڵ�����ļ�ֵ��
		x.mKeys[possibleIdx - 1] = leftKey;
		// ����ڵ�����ļ�ֵ�Ժ��ӽ������ڵ���ɾ��
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
