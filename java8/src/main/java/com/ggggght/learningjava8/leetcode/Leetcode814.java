package com.ggggght.learningjava8.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Queue;

/**
 * 给你二叉树的根结点 root ，此外树的每个结点的值要么是 0 ，要么是 1 。
 * <p>
 * 返回移除了所有不包含 1 的子树的原二叉树。
 * <p>
 * 节点 node 的子树为 node 本身加上所有 node 的后代。
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,null,0,0,1]
 * 输出：[1,null,0,null,1]
 * 解释：
 * 只有红色节点满足条件“所有不包含 1 的子树”。 右图为返回的答案。
 * <p>
 * 示例 2：
 * <p>
 * 输入：root = [1,0,1,0,0,0,1]
 * 输出：[1,null,1,null,1]
 * <p>
 * 示例 3：
 * <p>
 * 输入：root = [1,1,0,1,1,0,1,0]
 * 输出：[1,1,0,1,1,null,1]
 * <p>
 * 提示：
 * <p>
 * 树中节点的数目在范围 [1, 200] 内
 * Node.val 为 0 或 1
 * <p>
 * Related Topics 树 深度优先搜索 二叉树 👍 196 👎 0
 */
public class Leetcode814 {
  public static void main(String[] args) {
    TreeNode head = new TreeNode(0);
    head.right = new TreeNode(0);
    head.right.left = new TreeNode(1);
    head.right.right = new TreeNode(1);
    head.right.left.right = new TreeNode(1);
    head.right.right.right = new TreeNode(1);
    // head.left = new TreeNode(1);
    // head.right = new TreeNode(0);
    // head.left.left = new TreeNode(1);
    // head.left.right = new TreeNode(1);
    // head.right.left = new TreeNode(0);
    // head.right.right = new TreeNode(1);
    // head.left.left.left = new TreeNode(0);

    var arr = new Leetcode814().pruneTree(head);
    System.out.println(arr);
  }

  public TreeNode pruneTree(TreeNode root) {
    if (root == null) return root;
    isLeafNodeAndNo1(root);
    return root.left == null && root.right == null && root.val == 0 ? null : root;
  }

  public boolean isLeafNodeAndNo1(TreeNode root) {

    if (root.left != null) {
      var t = isLeafNodeAndNo1(root.left);
      if (t) {
        root.left = null;
      }
    }

    if (root.right != null) {
      boolean t = isLeafNodeAndNo1(root.right);
      if (t) {
        root.right = null;
      }
    }

    // 什么时候需要剪枝 当是子节点  并且val = 0时
    if (root.val == 0 && root.left == null && root.right == null) {
      return true;
    }

    return false;
  }
}