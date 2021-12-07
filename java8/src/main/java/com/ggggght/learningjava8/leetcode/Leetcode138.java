package com.ggggght.learningjava8.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>给你一个长度为 <code>n</code> 的链表，每个节点包含一个额外增加的随机指针 <code>random</code> ，该指针可以指向链表中的任何节点或空节点。</p>
 *
 * <p>构造这个链表的 <strong><a href="https://baike.baidu.com/item/深拷贝/22785317?fr=aladdin" target="_blank">深拷贝</a></strong>。 深拷贝应该正好由 <code>n</code> 个 <strong>全新</strong> 节点组成，其中每个新节点的值都设为其对应的原节点的值。新节点的 <code>next</code> 指针和 <code>random</code> 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。<strong>复制链表中的指针都不应指向原链表中的节点 </strong>。</p>
 *
 * <p>例如，如果原链表中有 <code>X</code> 和 <code>Y</code> 两个节点，其中 <code>X.random --> Y</code> 。那么在复制链表中对应的两个节点 <code>x</code> 和 <code>y</code> ，同样有 <code>x.random --> y</code> 。</p>
 *
 * <p>返回复制链表的头节点。</p>
 *
 * <p>用一个由 <code>n</code> 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 <code>[val, random_index]</code> 表示：</p>
 *
 * <ul>
 * <li><code>val</code>：一个表示 <code>Node.val</code> 的整数。</li>
 * <li><code>random_index</code>：随机指针指向的节点索引（范围从 <code>0</code> 到 <code>n-1</code>）；如果不指向任何节点，则为  <code>null</code> 。</li>
 * </ul>
 *
 * <p>你的代码 <strong>只</strong> 接受原链表的头节点 <code>head</code> 作为传入参数。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/09/e1.png" style="height: 138px; width: 680px;" /></p>
 *
 * <pre>
 * <strong>输入：</strong>head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * <strong>输出：</strong>[[7,null],[13,0],[11,4],[10,2],[1,0]]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/09/e2.png" style="height: 111px; width: 680px;" /></p>
 *
 * <pre>
 * <strong>输入：</strong>head = [[1,1],[2,1]]
 * <strong>输出：</strong>[[1,1],[2,1]]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <p><strong><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/09/e3.png" style="height: 119px; width: 680px;" /></strong></p>
 *
 * <pre>
 * <strong>输入：</strong>head = [[3,null],[3,0],[3,null]]
 * <strong>输出：</strong>[[3,null],[3,0],[3,null]]
 * </pre>
 *
 * <p><strong>示例 4：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>head = []
 * <strong>输出：</strong>[]
 * <strong>解释：</strong>给定的链表为空（空指针），因此返回 null。
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 <= n <= 1000</code></li>
 * <li><code>-10000 <= Node.val <= 10000</code></li>
 * <li><code>Node.random</code> 为空（null）或指向链表中的节点。</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>哈希表</li><li>链表</li></div></div><br><div><li>👍 782</li><li>👎 0</li></div>
 */

class Node {
  int val;
  Node next;
  Node random;

  public Node(int val) {
    this.val = val;
    this.next = null;
    this.random = null;
  }

  @Override public String toString() {
    return "Node{" +
        "val=" + val +
        "next=" + next +
        // ", random=" + random +
        '}';
  }
}

public class Leetcode138 {
  public static void main(String[] args) {
    Leetcode138 leetcode138 = new Leetcode138();
    Node head = new Node(7);
    Node node13 = new Node(13);
    Node node11 = new Node(11);
    Node node10 = new Node(10);
    Node node1 = new Node(1);
    head.next = node13;
    node13.next = node11;
    node13.random = head;
    node11.next = node10;
    node11.random = node1;
    node10.next = node1;
    node10.random = node11;
    node1.random = head;
    Node node = leetcode138.copyRandomList2(head);
    System.out.println(node);
  }

  /**
   * 使用map存储复制后的节点
   *
   * @param head
   * @return
   */
  public Node copyRandomList(Node head) {
    if (head == null) return head;

    Map<Node, Node> copy = new HashMap<>();
    Node tmp = head;
    while (tmp != null) {
      copy.put(tmp, new Node(tmp.val));
      tmp = tmp.next;
    }

    tmp = head;
    while (tmp != null) {
      copy.get(tmp).next = copy.get(tmp.next);
      copy.get(tmp).random = copy.get(tmp.random);
      tmp = tmp.next;
    }

    return copy.get(head);
  }

  /**
   * 使用有限变量来存储
   * 1. 首先将每个节点的拷贝都放在其后面 eg 1->2->3 --> 1->1->2->2->3->3
   * 2. 将所有拷贝的节点的random都赋值
   * 3. 分割链表
   * @param head
   * @return
   */
  public Node copyRandomList2(Node head) {
    Node cur = head;
    Node next = null;
    while (cur != null) {
      next = cur.next;
      cur.next = new Node(cur.val);
      cur.next.next = next;
      cur = next;
    }

    cur = head;
    Node curCopy = null;
    while (cur != null) {
      next = cur.next.next;
      curCopy = cur.next;
      curCopy.random = cur.random != null ? cur.random.next : null;
      cur = next;
    }

    cur = head;
    Node res = head.next;
    while (cur != null) {
      next = cur.next.next;
      curCopy = cur.next;
      cur.next = next;
      curCopy.next = next != null ? next.next : null;
      cur = next;
    }

    return res;
  }
}
