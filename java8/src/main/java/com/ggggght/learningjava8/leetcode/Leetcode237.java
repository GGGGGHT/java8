package com.ggggght.learningjava8.leetcode;

/**
 * There is a singly-linked list head and we want to delete a node node in it.
 * <p>
 * You are given the node to be deleted node. You will not be given access to the first node of head.
 * <p>
 * All the values of the linked list are unique, and it is guaranteed that the given node node is not the last node in the linked list.
 * <p>
 * Delete the given node. Note that by deleting the node, we do not mean removing it from memory. We mean:
 * <p>
 * The value of the given node should not exist in the linked list.
 * The number of nodes in the linked list should decrease by one.
 * All the values before node should be in the same order.
 * All the values after node should be in the same order.
 * Custom testing:
 * <p>
 * For the input, you should provide the entire linked list head and the node to be given node. node should not be the last node of the list and should be an actual node in the list.
 * We will build the linked list and pass the node to your function.
 * The output will be the entire list after calling your function.
 */
public class Leetcode237 {

    /**
     * low
     *
     * @param node
     */
    public static void deleteNode(ListNode node) {
        ListNode cur = node.next;
        while (cur.next != null) {
            node.val = cur.val;
            node = node.next;
            cur = cur.next;
        }
        node.val = cur.val;
        node.next = null;
    }

    public static void deleteNode2(ListNode node) {
        node.val = node.next.val;
        ListNode tmp = node.next;
        node.next = node.next.next;
        tmp.next = null;
    }
}
