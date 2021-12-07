package com.ggggght.learningjava8.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <p>给你二叉树的根结点 <code>root</code> ，请你将它展开为一个单链表：</p>
 *
 * <ul>
 * <li>展开后的单链表应该同样使用 <code>TreeNode</code> ，其中 <code>right</code> 子指针指向链表中下一个结点，而左子指针始终为 <code>null</code> 。</li>
 * <li>展开后的单链表应该与二叉树 <a href="https://baike.baidu.com/item/%E5%85%88%E5%BA%8F%E9%81%8D%E5%8E%86/6442839?fr=aladdin" target="_blank"><strong>先序遍历</strong></a> 顺序相同。</li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/01/14/flaten.jpg" style="width: 500px; height: 226px;" />
 * <pre>
 * <strong>输入：</strong>root = [1,2,5,3,4,null,6]
 * <strong>输出：</strong>[1,null,2,null,3,null,4,null,5,null,6]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>root = []
 * <strong>输出：</strong>[]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>root = [0]
 * <strong>输出：</strong>[0]
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>树中结点数在范围 <code>[0, 2000]</code> 内</li>
 * <li><code>-100 <= Node.val <= 100</code></li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>进阶：</strong>你可以使用原地算法（<code>O(1)</code> 额外空间）展开这棵树吗？</p>
 * <div><div>Related Topics</div><div><li>栈</li><li>树</li><li>深度优先搜索</li><li>链表</li><li>二叉树</li></div></div><br><div><li>👍 987</li><li>👎 0</li></div>
 */

public class Leetcode114 {
  public static void main(String[] args) {
    Leetcode114 leetcode114 = new Leetcode114();
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.left.left = new TreeNode(3);
    root.left.right = new TreeNode(4);
    root.right = new TreeNode(5);
    root.right.right = new TreeNode(6);

    leetcode114.flatten(root);
  }

  public void flatten(TreeNode root) {
    if (root == null) return;
    Deque<TreeNode> queue = new ArrayDeque<>();
    Deque<TreeNode> second = new ArrayDeque<>();
    queue.add(root);
    second.add(root);
    recursive(queue, second);
    second.pollFirst();
    root.left = null;
    root.right = null;
    TreeNode tmp = root;

    while (!second.isEmpty()) {
      TreeNode treeNode = second.pollFirst();
      treeNode.left = null;
      treeNode.right = null;
      while (tmp.right != null) {
        tmp = tmp.right;
      }
      tmp.right = treeNode;
    }
  }

  /**
   * 使用两个链表保存 第一个链表用来先序遍历 第二个链表用来保存先序遍历时的节点 第一个链表可以替换成栈
   * @param queue
   * @param second
   */
  private void recursive(Deque<TreeNode> queue, Deque<TreeNode> second) {
    if (queue.isEmpty()) return;

    TreeNode left = queue.peekLast().left;
    if (left != null) {
      queue.add(left);
      second.add(left);
      recursive(queue, second);
    }

    TreeNode right = queue.peekLast().right;
    if (right != null) {
      queue.add(right);
      second.add(right);
      recursive(queue, second);
    }

    queue.pollLast();
  }
}
