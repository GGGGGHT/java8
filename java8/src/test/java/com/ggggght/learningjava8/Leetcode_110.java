package com.ggggght.learningjava8;

/**
 * @desc: com.ggggght.learningjava8
 * @date: 2021/3/26 16:55
 * @author: ggggght
 */
public class Leetcode_110 {

	public boolean isBalanced(TreeNode root) {
		if (root == null) {
			return true;
		}

		return Math.abs(getHeight(root.left) - getHeight(root.right)) <= 1 && isBalanced(root.left)
				&& isBalanced(root.right);
	}

	private int getHeight(TreeNode root) {
		if (root == null) {
			return 0;
		}

		return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
	}

	public static void main(String[] args) {

		TreeNode head = new TreeNode(2, null,
				new TreeNode(3, null, new TreeNode(4, null, new TreeNode(5, null, new TreeNode(6)))));
		System.out.println(new Leetcode_111().minDepth(head));

		// [1,2,2,3,null,null,3,4,null,null,4] //
		TreeNode root = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(2);
		TreeNode node4 = new TreeNode(3);
		TreeNode node5 = new TreeNode(3);
		TreeNode node6 = new TreeNode(4);
		TreeNode node7 = new TreeNode(4);
		root.left = node2;
		root.right = node3;

		node2.left = node4;

		node3.right = node5;

		node4.left = node6;

		node5.right = node7;

		// root.right = new TreeNode(2, null, new TreeNode(3));
		System.out.println(new Leetcode_110().isBalanced(root));

	}

}
