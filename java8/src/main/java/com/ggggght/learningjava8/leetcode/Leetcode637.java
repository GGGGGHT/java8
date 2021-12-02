package com.ggggght.learningjava8.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * <pre>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * </pre>
 * 输出：[3, 14.5, 11]
 * 解释：
 * 第 0 层的平均值是 3 ,  第1层是 14.5 , 第2层是 11 。因此返回 [3, 14.5, 11] 。
 * <p>
 * 提示：
 * <p>
 * <p>
 * 节点值的范围在32位有符号整数范围内。
 * <p>
 * Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 301 👎 0
 * <p>
 * <p>
 * leetcode submit region begin(Prohibit modification and deletion
 */
public class Leetcode637 {
  public static void main(String[] args) {
    Leetcode637 solution = new Leetcode637();
    TreeNode head = new TreeNode(3);
    head.left = new TreeNode(9);
    head.right = new TreeNode(20);
    head.right.left = new TreeNode(15);
    head.right.right = new TreeNode(7);
    List<Double> res = solution.averageOfLevels(head);
    System.out.println(res);
  }

  public List<Double> averageOfLevels(TreeNode root) {
    Queue<TreeNode> queue = new ArrayDeque<>();
    List<Double> res = new ArrayList<>();
    TreeNode last = root;
    TreeNode nLast = null;
    queue.offer(root);
    int i = 0;
    double v = 0;
    res.add((double) root.val);
    while (!queue.isEmpty()) {
      TreeNode tmp = queue.poll();
      if (tmp.left != null) {
        queue.offer(tmp.left);
        nLast = tmp.left;
        v += tmp.left.val;
        i++;
      }
      if (tmp.right != null) {
        queue.offer(tmp.right);
        nLast = tmp.right;
        v += tmp.right.val;
        i++;
      }

      if (tmp == last && !queue.isEmpty()) {
        last = nLast;
        if(i != 0)
          res.add(v / i);
        i = 0;
        v = 0;
      }
    }
    return res;
  }
}
