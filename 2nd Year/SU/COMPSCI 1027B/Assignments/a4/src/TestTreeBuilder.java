import java.util.Iterator;

public class TestTreeBuilder {

	public static void main(String[] args) {
		String[] data;
		LinkedBinaryTree<String> tree;
		BinaryTreeNode<String> root;
		Iterator<String> iter;
		String str;
		TreeBuilder<String> tb;

		// --------------- Test 1 ---------------

		boolean test1Success = false;

		data = new String[] {"A","B","C","D","E","F","G"};
		tb = new TreeBuilder<String>(data);
		tree = tb.getTree();

		str = "";
		iter = tree.iteratorInOrder();
		while (iter.hasNext()) { str += iter.next(); }
		System.out.println(str);
		if (str.equals("DBEAFCG")) {
			test1Success = true;
		}

		if (test1Success) {
			System.out.println("Test 1 Passed");
		} else {
			System.out.println("Test 1 Failed");
		}


		// --------------- Test 2 ---------------

		boolean test2Success = false;

		data = new String[] {"A","B","C","D","E","F","G","H","I"};
		tb = new TreeBuilder<String>(data);
		tree = tb.getTree();

		str = "";
		iter = tree.iteratorInOrder();
		while (iter.hasNext()) { str += iter.next(); }
		System.out.println(str);
		if (str.equals("HDIBEAFCG")) {
			test2Success = true;
		}

		if (test2Success) {
			System.out.println("Test 2 Passed");
		} else {
			System.out.println("Test 2 Failed");
		}

		// --------------- Test 3 ---------------

		boolean test3Success = false;

		data = new String[] {"A","B",null,"C",null,"D","E",null,null,"F","G"};
		tb = new TreeBuilder<String>(data);
		tree = tb.getTree();

		str = "";
		iter = tree.iteratorInOrder();
		while (iter.hasNext()) { str += iter.next(); }
		System.out.println(str);
		if (str.equals("DCFEGBA")) {
			test3Success = true;
		}

		if (test3Success) {
			System.out.println("Test 3 Passed");
		} else {
			System.out.println("Test 3 Failed");
		}


		// --------------- Test 4 ---------------

		boolean test4Success = false;

		data = new String[] {"A",null,"B","C",null};
		tb = new TreeBuilder<String>(data);
		tree = tb.getTree();

		try {
			root = tree.getRoot();
			if (root.getData().equals("A") && root.getLeft() == null && root.getRight().getData().equals("B")
					&& root.getRight().getLeft().getData().equals("C") && root.getRight().getRight() == null) {
				test4Success = true;
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			test4Success = false;
		}

		if (test4Success) {
			System.out.println("Test 4 Passed");
		} else {
			System.out.println("Test 4 Failed");
		}

		// --------------- Test 5 ---------------

		boolean test5Success = false;

		Integer[] iData = new Integer[] {5,9,18,null,null,3,10};
		
		TreeBuilder<Integer> builder = new TreeBuilder<Integer>(iData);
		LinkedBinaryTree<Integer> iTree = builder.getTree();
		BinaryTreeNode<Integer> iRoot;
		
		try {
			iRoot = iTree.getRoot();
			if (iRoot.getData() == 5 && iRoot.getLeft().getData() == 9 && iRoot.getRight().getData() == 18
					&& iRoot.getLeft().getLeft() == null && iRoot.getLeft().getRight() == null &&
					iRoot.getRight().getLeft().getData() == 3 && iRoot.getRight().getRight().getData() == 10) {
				test5Success = true;
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			test5Success = false;
		}

		if (test5Success) {
			System.out.println("Test 5 Passed");
		} else {
			System.out.println("Test 5 Failed");
		}

	}

}