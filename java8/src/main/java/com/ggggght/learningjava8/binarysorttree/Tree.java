package com.ggggght.learningjava8.binarysorttree;

import lombok.ToString;

import java.util.*;

/**
 * 判断一个树是否是满二叉树
 */
public class Tree {

	public static class Node {

		public int value;

		public Node left;

		public Node right;

		public Node(int data) {
			this.value = data;
		}

		@Override
		public String toString() {
			return "Node{" + "value=" + value + '}';
		}

	}

	/**
	 * 获取一个树的高度
	 * @param head
	 * @return
	 */
	public static int getHeight(Node head) {
		if (null == head)
			return 0;

		return Math.max(getHeight(head.left), getHeight(head.right)) + 1;
	}

	public static int getQuantity(Node head) {
		if (null == head) {
			return 0;
		}

		return getQuantity(head.left) + getQuantity(head.right) + 1;
	}

	public static boolean isFull(Node head) {
		final int h = getHeight(head);
		final int n = getQuantity(head);

		return (1 << h) - 1 == n;
	}

	/**
	 * 判断一个树是否是完全二叉树
	 * @param head
	 * @return
	 */
	public static boolean isComplete(Node head) {
		// 进行宽度优先遍历
		final LinkedList<Node> queue = new LinkedList<>();
		queue.add(head);
		// 是否遇到过左右两个孩子不双全的节点
		boolean leaf = false;
		while (!queue.isEmpty()) {
			final Node curNode = queue.poll();
			Node l = curNode.left;
			Node r = curNode.right;
			// 如果遇到了不双全的节点之后,又发现当前节点不是叶子节点
			if (leaf && (l == null && r == null) || (l == null && r != null)) {
				return false;
			}

			if (l != null) {
				queue.add(l);
			}

			if (r != null) {
				queue.add(r);
			}

			if (l == null || r == null) {
				leaf = true;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		Node head = new Node(0);
		Node node1 = new Node(1);
		Node node2 = new Node(2);
		Node node3 = new Node(3);
		Node node4 = new Node(4);
		Node node5 = new Node(5);
		head.left = node1;
		head.right = node2;
		node1.left = node3;
		node1.right = node4;
		node2.left = node5;
		getCommon(head, node2, node1);
		getCommon2(head, node2, node1);
	}

	/**
	 * 判断一个树是否是满二叉树 需要向左右节点取三个信息
	 * <ol>
	 * <li>当前节点是否是满二叉树
	 * <li>当前节点是否是完全二叉树
	 * <li>当前树的高度
	 * </ol>
	 */
	public static class Info {

		private boolean isFull;

		private boolean isCBT;

		private int height;

		public Info(boolean isFull, boolean isCBT, int height) {
			this.isFull = isFull;
			this.isCBT = isCBT;
			this.height = height;
		}

	}

	public static Info process(Node head) {
		if (null == head) {
			return new Info(true, true, 0);
		}
		final Info leftInfo = process(head.left);
		final Info rightInfo = process(head.right);
		final int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
		boolean isCBT = false;

		// 如果是满二叉树 则一定是完全二叉树
		if (isFull) {
			isCBT = true;
		}
		else {
			if (leftInfo.isCBT && rightInfo.isCBT) {
				if (rightInfo.isFull && leftInfo.height - rightInfo.height == 1) {
					isCBT = true;
				}

				if (leftInfo.isFull && rightInfo.isFull && leftInfo.height - rightInfo.height == 1) {
					isCBT = true;
				}

				if (leftInfo.isFull && leftInfo.height == rightInfo.height) {
					isCBT = true;
				}
			}
		}

		return new Info(isFull, isCBT, height);
	}

	/**
	 * 找到n1与n2的公共父节点
	 * @param head
	 * @param n1
	 * @param n2
	 */
	public static void getCommon(Node head, Node n1, Node n2) {
		Map<Node, Node> map = new HashMap<>(16);
		map.put(head, null);
		fillPut(head, map);
		HashSet<Node> set = new HashSet<>(map.size());
		// 依次将n1的所有父节点加入到set中

		set.add(map.get(n1));
		Node cur = n1;
		while ((cur = map.get(cur)) != null) {
			set.add(cur);
		}

		cur = n2;
		while (!set.contains(cur)) {
			cur = map.get(cur);
		}
		System.out.println(cur);
	}

	public static void fillPut(Node head, Map<Node, Node> map) {
		if (null != head) {
			if (null != head.left) {
				map.put(head.left, head);
				fillPut(head.left, map);
			}

			if (null != head.right) {
				map.put(head.right, head);
				fillPut(head.right, map);
			}
		}
	}

	/**
	 * 使用递归找到公共的父节点
	 * @param head
	 * @param n1
	 * @param n2
	 */
	public static void getCommon2(Node head, Node n1, Node n2) {
		System.out.println(process(head, n1, n2).ans);
	}

	private static CommonInfo process(Node head, Node o1, Node o2) {
		if (null == head) {
			return new CommonInfo(null, false, false);
		}
		final CommonInfo leftInfo = process(head.left, o1, o2);
		final CommonInfo rightInfo = process(head.right, o1, o2);
		boolean findO1 = head == o1 || leftInfo.findO1 || rightInfo.findO1;
		boolean findO2 = head == o2 || leftInfo.findO2 || rightInfo.findO2;

		Node ans = null;
		if (leftInfo.ans != null) {
			ans = leftInfo.ans;
		}

		if (rightInfo.ans != null) {
			ans = rightInfo.ans;
		}

		if (ans == null && findO1 && findO2) {
			ans = head;
		}
		return new CommonInfo(ans, findO1, findO2);
	}

	private static class CommonInfo {

		public Node ans;

		public boolean findO1;

		public boolean findO2;

		public CommonInfo(Node ans, boolean findO1, boolean findO2) {
			this.ans = ans;
			this.findO1 = findO1;
			this.findO2 = findO2;
		}

	}

}
