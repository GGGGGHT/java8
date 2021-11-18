package com.ggggght.learningjava8;

/**
 * @desc:
 * @date: 2021/3/26 17:20
 * @author: ggggght
 */
public class Leetcode_111 {

	public static void main(String[] args) {
		// TreeNode head = new TreeNode(2, null, new TreeNode(3, null, new TreeNode(4,
		// null, new TreeNode(5, null, new TreeNode(6)))));
		// [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
		// 5
		// 4 8
		// 11 n 13 4
		// 7 2 1
		TreeNode root = new TreeNode(5);
		TreeNode node1 = new TreeNode(4);
		TreeNode node2 = new TreeNode(8);
		TreeNode node3 = new TreeNode(11);
		TreeNode node4 = new TreeNode(13);
		TreeNode node5 = new TreeNode(4);
		TreeNode node6 = new TreeNode(7);
		TreeNode node7 = new TreeNode(2);
		TreeNode node8 = new TreeNode(1);
		root.left = node1;
		root.right = node2;
		node1.left = node3;
		node2.left = node4;
		node2.right = node5;
		node3.left = node6;
		node3.right = node7;
		node5.left = node8;
		System.out.println(new Leetcode_111().hasPathSum(root, 22));
	}

	public int minDepth(TreeNode root) {
		if (null == root) {
			return 0;
		}

		int min = 1;
		min += Integer.min(getHeight(root.left), getHeight(root.right));

		return min;
	}

	private int getHeight(TreeNode root) {
		if (root == null) {
			return 0;
		}

		return Math.min(getHeight(root.left), getHeight(root.right)) + 1;
	}

	public boolean hasPathSum(TreeNode root, int targetSum) {
		return hasPathSum(targetSum - root.val, root.left) || hasPathSum(targetSum - root.val, root.right);
	}

	private boolean hasPathSum(int sum, TreeNode root) {
		if (root == null && sum == 0) {
			return true;
		}

		return hasPathSum(sum - root.val, root.left) || hasPathSum(sum - root.val, root.right);
	}

}
