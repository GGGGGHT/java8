package com.ggggght.learningjava8.leetcode;


/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class Leetcode143 {
  public static void main(String[] args) {
    Leetcode143 solution = new Leetcode143();
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    // head.next.next.next.next = new ListNode(5);
    // head.next.next.next.next.next = new ListNode(2);
    solution.reorderList(head);
  }

  public void reorderList(ListNode head) {
    var len = 1;
    var tail = head;
    while (tail.next != null) {
      tail = tail.next;
      len++;
    }

    // tail指向最后的一个节点
    var mid = ((len & 1) == 0) ? len >> 1 : (len >> 1) + 1;
    var tmp = head;
    for (int i = 1; i < mid; i++) {
      tmp = tmp.next;
    }
    var midP = tmp;
    tmp = tmp.next;
    // 从tmp之后翻转链表
    ListNode newNode = null;
    while (tmp != null) {
      var next = tmp.next;
      tmp.next = newNode;
      newNode = tmp;
      tmp = next;
    }

    midP.next = newNode;

    var s = head;
    while (head != midP) {
      // 下一个要插入的位置
      var nextInsertPosition = head.next;
      var needAdd = midP.next;
      midP.next = midP.next.next;
      needAdd.next = nextInsertPosition;
      head.next = needAdd;
      head = nextInsertPosition;
    }
  }
}

