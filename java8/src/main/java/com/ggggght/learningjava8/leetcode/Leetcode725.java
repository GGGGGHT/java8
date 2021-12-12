package com.ggggght.learningjava8.leetcode;

/**
 * <p>给你一个头结点为 <code>head</code> 的单链表和一个整数 <code>k</code> ，请你设计一个算法将链表分隔为 <code>k</code> 个连续的部分。</p>
 *
 * <p>每部分的长度应该尽可能的相等：任意两部分的长度差距不能超过 1 。这可能会导致有些部分为 null 。</p>
 *
 * <p>这 <code>k</code> 个部分应该按照在链表中出现的顺序排列，并且排在前面的部分的长度应该大于或等于排在后面的长度。</p>
 *
 * <p>返回一个由上述 <code>k</code> 部分组成的数组。</p>
 * &nbsp;
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/06/13/split1-lc.jpg" style="width: 400px; height: 134px;" />
 * <pre>
 * <strong>输入：</strong>head = [1,2,3], k = 5
 * <strong>输出：</strong>[[1],[2],[3],[],[]]
 * <strong>解释：</strong>
 * 第一个元素 output[0] 为 output[0].val = 1 ，output[0].next = null 。
 * 最后一个元素 output[4] 为 null ，但它作为 ListNode 的字符串表示是 [] 。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/06/13/split2-lc.jpg" style="width: 600px; height: 60px;" />
 * <pre>
 * <strong>输入：</strong>head = [1,2,3,4,5,6,7,8,9,10], k = 3
 * <strong>输出：</strong>[[1,2,3,4],[5,6,7],[8,9,10]]
 * <strong>解释：</strong>
 * 输入被分成了几个连续的部分，并且每部分的长度相差不超过 1 。前面部分的长度大于等于后面部分的长度。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>链表中节点的数目在范围 <code>[0, 1000]</code></li>
 * <li><code>0 &lt;= Node.val &lt;= 1000</code></li>
 * <li><code>1 &lt;= k &lt;= 50</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>链表</li></div></div><br><div><li>👍 235</li><li>👎 0</li></div>
 */

public class Leetcode725 {
  public ListNode[] splitListToParts(ListNode head, int k) {
    if (k == 1) {
      ListNode[] res = new ListNode[1];
      res[0] = head;
      return res;
    }
    if (head == null) {
      return new ListNode[k];
    }

    // save per node's head
    ListNode[] res = new ListNode[k];
    int len = 1;
    ListNode cur = head;
    while (cur.next != null) {
      cur = cur.next;
      len++;
    }
    // cur is last non null position
    // l represent pre length  eg: k = 5 len = 3
    // eg: len = 10 k = 3 , per length = 3  mod = 1 then the first len = 3 + 1
    int l = len / k;
    int mod = len % k;
    cur = head;
    if (l != 0) {
      // per length > 0
      for (int t = 0; t < k; t++) {
        res[t] = cur;
        int m = 1;
        while (m < l && cur != null) {
          cur = cur.next;
          m++;
        }
        if (mod != 0) {
          cur = cur.next;
          mod--;
        }
        ListNode next = cur.next;
        cur.next = null;
        cur = next;
      }
    } else {
      for (int i = 0; i < mod; i++) {
        ListNode next = cur.next;
        cur.next = null;
        res[i] = cur;
        cur = next;
      }
    }
    return res;
  }
}
