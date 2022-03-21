package com.ggggght.learningjava8.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
/**
 <p>给定一个二叉搜索树 <code>root</code> 和一个目标结果 <code>k</code>，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 <code>true</code>。</p>

 <p>&nbsp;</p>

 <p><strong>示例 1：</strong></p>
 <img alt="" src="https://assets.leetcode.com/uploads/2020/09/21/sum_tree_1.jpg" style="height: 229px; width: 400px;" />
 <pre>
 <strong>输入:</strong> root = [5,3,6,2,4,null,7], k = 9
 <strong>输出:</strong> true
 </pre>

 <p><strong>示例 2：</strong></p>
 <img alt="" src="https://assets.leetcode.com/uploads/2020/09/21/sum_tree_2.jpg" style="height: 229px; width: 400px;" />
 <pre>
 <strong>输入:</strong> root = [5,3,6,2,4,null,7], k = 28
 <strong>输出:</strong> false
 </pre>

 <p>&nbsp;</p>

 <p><strong>提示:</strong></p>

 <ul>
 <li>二叉树的节点个数的范围是&nbsp;&nbsp;<code>[1, 10<sup>4</sup>]</code>.</li>
 <li><code>-10<sup>4</sup>&nbsp;&lt;= Node.val &lt;= 10<sup>4</sup></code></li>
 <li><code>root</code>&nbsp;为二叉搜索树</li>
 <li><code>-10<sup>5</sup>&nbsp;&lt;= k &lt;= 10<sup>5</sup></code></li>
 </ul>
 <div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>广度优先搜索</li><li>二叉搜索树</li><li>哈希表</li><li>双指针</li><li>二叉树</li></div></div><br><div><li>👍 354</li><li>👎 0</li></div>
 */

public class Leetcode653 {
  public static void main(String[] args) {
    TreeNode treeNode = new TreeNode(5);
    treeNode.right = new TreeNode(6);
    treeNode.right.right = new TreeNode(7);
    treeNode.left= new TreeNode(3);
    treeNode.left.left= new TreeNode(2);
    treeNode.left.right= new TreeNode(4);
    findTarget(treeNode, 9);
    findTarget(treeNode, 19);
  }

  public static boolean findTarget(TreeNode root, int k) {
    Deque<TreeNode> ld = new ArrayDeque<>(), rd = new ArrayDeque<>();
    TreeNode temp = root;
    while (temp != null) {
      ld.addLast(temp);
      temp = temp.left;
    }
    temp = root;
    while (temp != null) {
      rd.addLast(temp);
      temp = temp.right;
    }
    TreeNode l = ld.peekLast(), r = rd.peekLast();
    while (l.val < r.val) {
      int t = l.val + r.val;
      if (t == k) return true;
      if (t < k) {
        l = getNext(ld, true);
      } else {
        r = getNext(rd, false);
      }
    }
    return false;
  }

  static TreeNode getNext(Deque<TreeNode> d, boolean isLeft) {
    TreeNode node = isLeft ? d.pollLast().right : d.pollLast().left;
    while (node != null) {
      d.addLast(node);
      node = isLeft ? node.left : node.right;
    }
    return d.peekLast();
  }
}
