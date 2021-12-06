package com.ggggght.learningjava8.leetcode;

/**
 * <p>给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。</p>
 *
 * <p>本题中，一个高度平衡二叉树是指一个二叉树<em>每个节点&nbsp;</em>的左右两个子树的高度差的绝对值不超过 1。</p>
 *
 * <p><strong>示例:</strong></p>
 *
 * <pre>给定的有序链表： [-10, -3, 0, 5, 9],
 *
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 *
 * 0
 * / \
 * -3   9
 * /   /
 * -10  5
 * </pre>
 * <div><div>Related Topics</div><div><li>树</li><li>二叉搜索树</li><li>链表</li><li>分治</li><li>二叉树</li></div></div><br><div><li>👍 629</li><li>👎 0</li></div>
 */

public class Leetcode109 {
  public TreeNode sortedListToBST(ListNode head) {
    if (head == null) return null;
    int size = 1;
    // 统计链表长度
    ListNode tmp = head;
    while (tmp.next != null) {
      tmp = tmp.next;
      size++;
    }
    if (size == 1) return new TreeNode(tmp.val);
    ListNode tail = tmp;
    int mid = (size >> 1) + 1;
    tmp = head;
    while (--mid > 1) {
      tmp = tmp.next;
    }

    ListNode pre = tmp;
    ListNode midNode = pre.next;

    // tmp前面的是left  tmp后面的是right
    TreeNode res = new TreeNode(midNode.val);
    res.left = buildTree(head, pre);
    if (midNode != tail) {
      res.right = buildTree(midNode.next, tail);
    }
    return res;
  }

  /**
   * 思路 升序链表 可以看做二叉树的中序遍历
   * 分别构建其左子节点和右节点
   *
   * @param start
   * @param end
   * @return
   */
  private TreeNode buildTree(ListNode start, ListNode end) {
    // 节子节点
    if (start == end) {
      return new TreeNode(start.val);
    }

    int size = 1;
    ListNode tmp = start;
    while (tmp.next != end) {
      tmp = tmp.next;
      size++;
    }
    size++;

    int mid = (size >> 1) + 1;
    tmp = start;
    while (--mid > 1) {
      tmp = tmp.next;
    }

    ListNode pre = tmp;
    ListNode midNode = pre.next;
    TreeNode res = new TreeNode(midNode.val);
    res.left = buildTree(start, pre);
    if (midNode != end) {
      res.right = buildTree(midNode.next, end);
    }
    return res;
  }
}
