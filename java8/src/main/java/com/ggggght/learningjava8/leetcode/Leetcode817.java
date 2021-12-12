package com.ggggght.learningjava8.leetcode;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>给定链表头结点&nbsp;<code>head</code>，该链表上的每个结点都有一个 <strong>唯一的整型值</strong> 。</p>
 *
 * <p>同时给定列表&nbsp;<code>G</code>，该列表是上述链表中整型值的一个子集。</p>
 *
 * <p>返回列表&nbsp;<code>G</code>&nbsp;中组件的个数，这里对组件的定义为：链表中一段最长连续结点的值（该值必须在列表&nbsp;<code>G</code>&nbsp;中）构成的集合。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例&nbsp;1：</strong></p>
 *
 * <pre><strong>输入:</strong>
 * head: 0-&gt;1-&gt;2-&gt;3
 * G = [0, 1, 3]
 * <strong>输出:</strong> 2
 * <strong>解释:</strong>
 * 链表中,0 和 1 是相连接的，且 G 中不包含 2，所以 [0, 1] 是 G 的一个组件，同理 [3] 也是一个组件，故返回 2。</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre><strong>输入:</strong>
 * head: 0-&gt;1-&gt;2-&gt;3-&gt;4
 * G = [0, 3, 1, 4]
 * <strong>输出:</strong> 2
 * <strong>解释:</strong>
 * 链表中，0 和 1 是相连接的，3 和 4 是相连接的，所以 [0, 1] 和 [3, 4] 是两个组件，故返回 2。</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>如果&nbsp;<code>N</code>&nbsp;是给定链表&nbsp;<code>head</code>&nbsp;的长度，<code>1 &lt;= N &lt;= 10000</code>。</li>
 * <li>链表中每个结点的值所在范围为&nbsp;<code>[0, N - 1]</code>。</li>
 * <li><code>1 &lt;= G.length &lt;= 10000</code></li>
 * <li><code>G</code> 是链表中所有结点的值的一个子集.</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>哈希表</li><li>链表</li></div></div><br><div><li>👍 88</li><li>👎 0</li></div>
 */

public class Leetcode817 {
  public static void main(String[] args) {
    ListNode head = new ListNode(0);
    head.next = new ListNode(3);
    head.next.next = new ListNode(2);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(1);
    // head.next = new ListNode();
    Leetcode817 leetcode817 = new Leetcode817();
    System.out.println(leetcode817.numComponents(head, new int[] {3, 0, 2}));
  }

  public int numComponents(ListNode head, int[] nums) {
    Set<Integer> set = new HashSet<>();
    for (int num : nums) {
      set.add(num);
    }

    ListNode cur = head;
    int res = 0;
    while (cur != null) {
      if(set.contains(cur.val) && (cur.next == null || !set.contains(cur.next.val))) res++;
      cur = cur.next;
    }

    return res;
  }
}