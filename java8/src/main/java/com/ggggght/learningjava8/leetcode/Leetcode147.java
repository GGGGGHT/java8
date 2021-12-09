package com.ggggght.learningjava8.leetcode;

/**
 * <p>对链表进行插入排序。</p>
 *
 * <p><img alt="" src="https://upload.wikimedia.org/wikipedia/commons/0/0f/Insertion-sort-example-300px.gif"><br>
 * <small>插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。<br>
 * 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。</small></p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>插入排序算法：</strong></p>
 *
 * <ol>
 * <li>插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。</li>
 * <li>每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。</li>
 * <li>重复直到所有输入数据插入完为止。</li>
 * </ol>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre><strong>输入:</strong> 4-&gt;2-&gt;1-&gt;3
 * <strong>输出:</strong> 1-&gt;2-&gt;3-&gt;4
 * </pre>
 *
 * <p><strong>示例&nbsp;2：</strong></p>
 *
 * <pre><strong>输入:</strong> -1-&gt;5-&gt;3-&gt;4-&gt;0
 * <strong>输出:</strong> -1-&gt;0-&gt;3-&gt;4-&gt;5
 * </pre>
 * <div><div>Related Topics</div><div><li>链表</li><li>排序</li></div></div><br><div><li>👍 454</li><li>👎 0</li></div>
 */

public class Leetcode147 {
  public static void main(String[] args) {
    Leetcode147 leetcode147 = new Leetcode147();
    ListNode head = new ListNode(1);
    head.next = new ListNode(1);
    // head.next.next = new ListNode(1);
    // head.next.next.next = new ListNode(3);
    head = leetcode147.insertionSortList(head);
    System.out.println(head);
  }

  public ListNode insertionSortList(ListNode head) {
    if (null == head) return head;
    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    ListNode cur = dummy.next;
    while(cur != null) {
      ListNode curPre = dummy;
      ListNode insertPre = dummy;
      ListNode insertPosition = dummy.next;
      while (curPre.next != cur) {
        curPre = curPre.next;
      }

      while (insertPosition!= null && insertPosition.val < cur.val) {
        insertPosition = insertPosition.next;
        insertPre = insertPre.next;
      }

      if (cur != insertPosition) {
        ListNode next = cur.next;
        curPre.next = next;
        cur.next = insertPosition;
        insertPre.next = cur;
        cur = next;
      } else {
        cur = cur.next;
      }

    }
    return dummy.next;
  }
}
