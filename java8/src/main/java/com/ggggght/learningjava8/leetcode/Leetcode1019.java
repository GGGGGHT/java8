package com.ggggght.learningjava8.leetcode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * <p>给出一个以头节点&nbsp;<code>head</code>&nbsp;作为第一个节点的链表。链表中的节点分别编号为：<code>node_1, node_2, node_3, ...</code> 。</p>
 *
 * <p>每个节点都可能有下一个更大值（<em>next larger</em> <strong>value</strong>）：对于&nbsp;<code>node_i</code>，如果其&nbsp;<code>next_larger(node_i)</code>&nbsp;是&nbsp;<code>node_j.val</code>，那么就有&nbsp;<code>j &gt; i</code>&nbsp;且&nbsp;&nbsp;<code>node_j.val &gt; node_i.val</code>，而&nbsp;<code>j</code>&nbsp;是可能的选项中最小的那个。如果不存在这样的&nbsp;<code>j</code>，那么下一个更大值为&nbsp;<code>0</code>&nbsp;。</p>
 *
 * <p>返回整数答案数组&nbsp;<code>answer</code>，其中&nbsp;<code>answer[i] = next_larger(node_{i+1})</code>&nbsp;。</p>
 *
 * <p><strong><em>注意：</em></strong>在下面的示例中，诸如 <code>[2,1,5]</code> 这样的<strong>输入</strong>（不是输出）是链表的序列化表示，其头节点的值为&nbsp;2，第二个节点值为 1，第三个节点值为&nbsp;5 。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre><strong>输入：</strong>[2,1,5]
 * <strong>输出：</strong>[5,5,0]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre><strong>输入：</strong>[2,7,4,3,5]
 * <strong>输出：</strong>[7,0,5,5,0]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre><strong>输入：</strong>[1,7,5,1,9,2,5,1]
 * <strong>输出：</strong>[7,9,9,9,0,5,0,0]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ol>
 * <li>对于链表中的每个节点，<code>1 &lt;= node.val&nbsp;&lt;= 10^9</code></li>
 * <li>给定列表的长度在 <code>[0, 10000]</code>&nbsp;范围内</li>
 * </ol>
 * <div><div>Related Topics</div><div><li>栈</li><li>数组</li><li>链表</li><li>单调栈</li></div></div><br><div><li>👍 179</li><li>👎 0</li></div>
 */

public class Leetcode1019 {
  public static void main(String[] args) {
    Leetcode1019 leetcode1019 = new Leetcode1019();
    ListNode head = new ListNode(3);
    head.next = new ListNode(3);
    // head.next.next = new ListNode(4);
    // head.next.next.next = new ListNode(3);
    // head.next.next.next.next = new ListNode(5);
    // head.next.next.next.next.next = new ListNode(4);
    int[] ints = leetcode1019.nextLargerNodes(head);
    System.out.println(Arrays.toString(ints));
  }

  public int[] nextLargerNodes(ListNode head) {
    ArrayList<Integer> list = new ArrayList<>();
    ListNode cur = head;
    while (cur != null) {
      list.add(cur.val);
      cur = cur.next;
    }
    // 保存当前
    int[] res = new int[list.size()];
    for (int i = 1; i < list.size(); i++) {
      // 当前值
      Integer current = list.get(i);
      // 上一个值
      Integer previous = list.get(i - 1);
      if (current > previous) {
        res[i - 1] = current;
      } else {
        // 当前值比上一个值小
        // 向后查找找到第一个比当前值大的
        int t = i;
        while (t < list.size() && list.get(t) <= previous) {
          t++;
        }
        res[i - 1] = t >= list.size() ? 0 : list.get(t);
      }
    }
    return res;
  }
}

