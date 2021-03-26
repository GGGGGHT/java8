package com.ggggght.learningjava8;

/**
 * @desc: com.ggggght.learningjava8
 * @date: 2021/3/26 16:54
 * @author: ggggght
 */

public class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode() {
	}

	TreeNode(int val) {
		this.val = val;
	}

	TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}

