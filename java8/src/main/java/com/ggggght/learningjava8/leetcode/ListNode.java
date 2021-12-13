package com.ggggght.learningjava8.leetcode;

public class ListNode {
  public int val;
  public ListNode next;

  ListNode(int x) {
    val = x;
  }

  @Override public String toString() {
    return "ListNode{" +
        "val=" + val +
        ", next=" + next +
        '}';
  }

  public static ListNode buildList(Integer... integers) {
    ListNode head = new ListNode(-1);
    for (Integer integer : integers) {
      ListNode cur = head;
      while (cur.next != null) {
        cur = cur.next;
      }
      cur.next = new ListNode(integer);
    }

    return head.next;
  }
}