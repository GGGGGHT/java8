package com.ggggght.learningjava8.leetcode;

import java.util.Stack;

/**
 * <p>给你两个 <strong>非空 </strong>链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。</p>
 *
 * <p>你可以假设除了数字 0 之外，这两个数字都不会以零开头。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例1：</strong></p>
 *
 * <p><img alt="" src="https://pic.leetcode-cn.com/1626420025-fZfzMX-image.png" style="width: 302px; " /></p>
 *
 * <pre>
 * <strong>输入：</strong>l1 = [7,2,4,3], l2 = [5,6,4]
 * <strong>输出：</strong>[7,8,0,7]
 * </pre>
 *
 * <p><strong>示例2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>l1 = [2,4,3], l2 = [5,6,4]
 * <strong>输出：</strong>[8,0,7]
 * </pre>
 *
 * <p><strong>示例3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>l1 = [0], l2 = [0]
 * <strong>输出：</strong>[0]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>链表的长度范围为<code> [1, 100]</code></li>
 * <li><code>0 &lt;= node.val &lt;= 9</code></li>
 * <li>输入数据保证链表代表的数字无前导 0</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>进阶：</strong>如果输入链表不能翻转该如何解决？</p>
 * <div><div>Related Topics</div><div><li>栈</li><li>链表</li><li>数学</li></div></div><br><div><li>👍 452</li><li>👎 0</li></div>
 */
public class Leetcode445 {

  public static void main(String[] args) {
    ListNode listNode1 = ListNode.buildList(7,2,4,3);
    ListNode listNode2 = ListNode.buildList(5,6,4);
    ListNode listNode = addTwoNumbers(listNode1, listNode2);
    System.out.println(listNode);
    listNode1 = ListNode.buildList(7,2,4,3);
    listNode2 = ListNode.buildList(5,6,4);
    System.out.println(addTwoNumbers2(listNode1, listNode2));
    // ListNode rev = reverseList(listNode);
    // System.out.println(rev);
  }

  /**
   * use stack
   * @param l1
   * @param l2
   * @return
   */
  public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {

    Stack<ListNode> stack1 = new Stack<>();
    Stack<ListNode> stack2 = new Stack<>();
    while (l1 != null) {
      stack1.push(l1);
      l1 = l1.next;
    }

    while (l2 != null) {
      stack2.push(l2);
      l2 = l2.next;
    }

    Stack<ListNode> stack3 = new Stack<>();
    int carry = 0;
    while (!stack1.isEmpty() && !stack2.isEmpty()) {
      ListNode pop1 = stack1.pop();
      ListNode pop2 = stack2.pop();

      var c = (pop1.val + pop2.val) + carry;
      carry =  c / 10;

      stack3.push(new ListNode(c % 10));
    }

    while (!stack1.isEmpty()) {
      stack3.push(stack1.pop());
    }
    while (!stack2.isEmpty()) {
      stack3.push(stack2.pop());
    }

    ListNode dummy = new ListNode(-1);
    ListNode t = dummy;
    while (!stack3.isEmpty()) {
      dummy.next = stack3.pop();
      dummy = dummy.next;
    }
    return t.next;
  }
  public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    if (l1 == null || l2 == null) {
      return l1 == null ? l2 : l1;
    }

    // 翻转链表
    ListNode revl1 = reverseList(l1);
    ListNode revl2 = reverseList(l2);
    ListNode cur1 = revl1;
    ListNode cur2 = revl2;
    ListNode dummy = new ListNode(-1);
    ListNode cur = dummy;

    int jin = 0;
    while (cur1 != null && cur2 != null) {
      int t = cur1.val + cur2.val + jin;
      ListNode tmpNode;
      if (t >= 10) {
        tmpNode = new ListNode(t % 10);
        jin = 1;
      } else {
        tmpNode = new ListNode(t);
        jin = 0;
      }
      cur.next = tmpNode;
      cur = cur.next;
      cur1 = cur1.next;
      cur2 = cur2.next;
    }

    while (cur1 != null) {
      int t = cur1.val + jin;
      ListNode tmpNode;
      if (t >= 10) {
        tmpNode = new ListNode(t % 10);
        jin = 1;
      } else {
        jin = 0;
        tmpNode = new ListNode(t);
      }
      cur.next = tmpNode;
      cur = cur.next;
      cur1 = cur1.next;
    }

    while (cur2 != null) {
      int t = cur2.val + jin;
      ListNode tmpNode;
      if (t >= 10) {
        tmpNode = new ListNode(t % 10);
        jin = 1;
      } else {
        jin = 0;
        tmpNode = new ListNode(t);
      }
      cur.next = tmpNode;
      cur = cur.next;
      cur2 = cur2.next;
    }

    if(jin == 1) {
      cur.next = new ListNode(1);
    }

    return reverseList(dummy.next);
  }

  private static ListNode reverseList(ListNode head) {
    ListNode newHead = null;
    ListNode next;
    while (head != null) {
      next = head.next;
      head.next = newHead;
      newHead = head;
      head = next;
    }
    return newHead;
  }
}
