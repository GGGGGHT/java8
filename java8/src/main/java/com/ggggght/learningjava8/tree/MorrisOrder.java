package com.ggggght.learningjava8.tree;

import java.util.Objects;

/**
 * @desc: 使用莫里斯遍历进行二叉树的前中后序遍历
 *        1
 *       / \
 *      2   3
 *     / \ / \
 *    4  5 6  7
 * morris序的cur 走向 1 2 4 2 5 1 3 6 3 7
 * 前序为 1 2 4 5 3 6 7 所以需要在第一次的时候打印
 * 中序为 4 2 5 1 6 3 7 所以应该在第二次路过时打印
 * 后序为 4 5 2 6 7 3 1
 */
public class MorrisOrder {
    public static void main(String[] args) {
        Hero node1 = new Hero(1, "zs");
        Hero node2 = new Hero(2, "ls");
        Hero node3 = new Hero(3, "ww");
        Hero node4 = new Hero(4, "zl");
        Hero node5 = new Hero(5, "tq");
        Hero node6 = new Hero(6, "xx");
        Hero node7 = new Hero(7, "zz");

        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        node3.setRight(node7);
        System.out.println("前序遍历开始： ");
        preOrder(node1);
        System.out.println("中序遍历开始： ");
        inOrder(node1);
        System.out.println("后序遍历开始： ");
        postOrder(node1);
    }

    /**
     * 使用morris序进行前序遍历
     * @param head
     */
    private static void preOrder(Hero head) {
        Hero cur = head;
        Hero mostRight;

        while (cur != null) {
            mostRight = cur.getLeft();
            // 左节点不为空
            if (mostRight != null) {
                // 获取到左子树上最右的节点
                while (Objects.nonNull(mostRight.getRight()) && mostRight.getRight() != cur) {
                    mostRight = mostRight.getRight();
                }

                if (Objects.isNull(mostRight.getRight())) {
                    System.out.println(cur);
                    mostRight.setRight(cur);
                    cur = cur.getLeft();
                    continue;
                }

                if (cur == mostRight.getRight()) {
                    mostRight.setRight(null);
                }

            } else {
                System.out.println(cur);
            }

            cur = cur.getRight();
        }
    }

    /**
     * 中序遍历
     * @param head
     */
    private static void inOrder(Hero head) {
        Hero cur = head;
        Hero mostRight;

        while (cur != null) {
            mostRight = cur.getLeft();
            // 左节点不为空
            if (mostRight != null) {
                // 获取到左子树上最右的节点
                while (Objects.nonNull(mostRight.getRight()) && mostRight.getRight() != cur) {
                    mostRight = mostRight.getRight();
                }

                if (Objects.isNull(mostRight.getRight())) {
                    mostRight.setRight(cur);
                    cur = cur.getLeft();
                    continue;
                }

                if (cur == mostRight.getRight()) {
                    System.out.println(cur);
                    mostRight.setRight(null);
                }

            } else {
                System.out.println(cur);
            }

            cur = cur.getRight();
        }
    }

    /**
     * 后序遍历 左节点
     * @param head
     */
    private static void postOrder(Hero head) {
        Hero cur = head;
        Hero mostRight;

        while (Objects.nonNull(cur)) {
            mostRight = cur.getLeft();

            if (Objects.nonNull(mostRight)) {
                while (Objects.nonNull(mostRight.getRight()) && cur != mostRight.getRight()) {
                    mostRight = mostRight.getRight();
                }

                if (Objects.isNull(mostRight.getRight())) {
                    mostRight.setRight(cur);
                    cur = cur.getLeft();
                    continue;
                }

                if (cur == mostRight.getRight()) {
                    mostRight.setRight(null);
                    printEdge(cur.getLeft());
                }

            }
            cur = cur.getRight();
        }
        printEdge(head);
    }

    private static void printEdge(Hero head) {
        // 反转链表后进行打印
        Hero tail = reverseNode(head);
        Hero cur = tail;

        while (Objects.nonNull(cur)) {
            System.out.println(cur);
            cur = cur.getRight();
        }
        // 将链表反转成之前的样式
        reverseNode(tail);
    }
    /**
     * 对以head为开始的所有右节点进行逆序
     * @param head
     */
    private static Hero reverseNode(Hero head) {
        Hero next = head;
        Hero pre = null;
        while (Objects.nonNull(head)) {
            next = head.getRight();
            head.setRight(pre);
            pre = head;
            head = next;
        }

        return pre;
    }
}
