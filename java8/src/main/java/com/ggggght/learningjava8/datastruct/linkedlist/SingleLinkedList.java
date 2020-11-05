package com.ggggght.learningjava8.datastruct.linkedlist;

//import javafx.beans.binding.When;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;

import java.util.Stack;

/**
 * @author ght
 * @Desc
 * @date 2020-01-02 1:22 PM
 */

@SuppressWarnings("all")
public class SingleLinkedList {
    // 头节点
    private static final HeroNode head = new HeroNode(0, "", "");

    /**
     * 判断当前链表是否为空
     * @return
     */
    public static boolean isEmpty() {
        return head.next == null;
    }

    /**
     * 判断当前节点是否存在下一个节点
     *
     * @param node
     * @return
     */
    private static boolean hasNext(HeroNode node) {
        return !(null == node || node.next == null);
    }

    /**
     * 尾插法
     *
     * @param node
     */
    public static void addTail(HeroNode node) {
        HeroNode cur = head;
        // 先找到最后一个节点
        while (hasNext(cur)) {
            cur = cur.next;
        }
        cur.next = node;
    }

    /**
     * 头插法
     *
     * @param node
     */
    public static void addHead(HeroNode node) {
        node.next = head.next;
        head.next = node;
    }

    /**
     * 将节点添加到链表的指定位置 即根据编号来添加
     *
     * @param node
     */
    public static void addTo(HeroNode node) {
        // 如果当前链表为空 则直接插入到第一个位置
        if (isEmpty()) {
            head.next = node;
            return;
        }

        HeroNode tmp = head;
        while (true) {
            if (!hasNext(tmp)) {
                tmp.next = node;
                break;
            }

            if (tmp.next.no > node.no) {
                node.next = tmp.next;
                tmp.next = node;
                return;
            } else if (tmp.next.no == node.no) {
                System.out.println("no已经存在");
                return;
            }
            tmp = tmp.next;
        }
    }

    /**
     * 打印单链表
     */
    public static void show() {
        if (isEmpty()) {
            return;
        }

        HeroNode tmp = head;
        while (hasNext(tmp)) {
            System.out.println(tmp + "--->");
            tmp = tmp.next;
        }
        System.out.println(tmp);
    }

    /**
     * 根据no来修改节点的信息
     *
     * @param args
     */
    public void update(HeroNode node) {
        if (isEmpty()) {
            throw new RuntimeException("没有节点,无法修改");
        }

        HeroNode tmp = head;
        while (null != tmp.next && null != tmp.next.next) {
            if (tmp.next.no == node.no) {
                node.next = tmp.next.next;
                tmp.next = node;
                return;
            }
            tmp = tmp.next;
        }
        throw new RuntimeException("没有节点...,无法修改");
    }

    /**
     * 获取链表中的有效节点的个数(如果带头节点需要去掉头节点)
     *
     * @return
     */
    public static int size() {
        if (isEmpty()) {
            return 0;
        }

        int count = 0;
        HeroNode tmp = head.next;
        while (null != tmp) {
            ++count;
            tmp = tmp.next;
        }

        return count;
    }

    /**
     * 根据no来删除指定节点
     *
     * @param args
     */
    public void remove(int no) {
        if (isEmpty()) {
            throw new RuntimeException("当前node为空,无法删除");
        }

        HeroNode tmp = head;
        // tmp.next == null 即循环到链表尾部
        while (null != tmp.next) {
            // 找到了待删除节点的前一个节点
            if (tmp.next.no == no) {
                if (tmp.next.next != null) {
                    tmp.next = tmp.next.next;
                    return;
                } else {
                    tmp.next = null;
                    return;
                }
            }
            tmp = tmp.next;
        }

        throw new RuntimeException("不存在为" + no + "的节点");
    }

    /**
     * 查找单链表中倒数第K个节点 k是指链表的索引(即从0开始)不包括头节点
     * 比如说有5个节点 需要倒数第5个 即是第1个节点
     *
     * @param args
     */
    public static HeroNode getKNode(int k) {
        if (k > size() - 1 || k < 0 || isEmpty()) {
            throw new RuntimeException("OutOfBoundException:" + k);
        }

        int i = size() - k + 1;
        HeroNode tmp = head;
        while (i != 0) {
            --i;
            tmp = tmp.next;
        }
        return tmp;
    }

    /**
     * 腾讯笔试题:反转单链表
     * 反转一个单链表
     * 0 -> 1 -> 2 -> 3
     * 0 -> 3 -> 2 -> 1
     *
     * @param node
     * @return
     */
    public static HeroNode reverseNode(HeroNode node) {
        if (isEmpty()) {
            return null;
        }

        HeroNode cur = node.next;
        // 指向当前节点的下一个节点
        HeroNode next = null;
        HeroNode reverse = new HeroNode();
        while (null != cur) {
            // 先暂存当前节点的下一个节点
            next = cur.next;
            // cur.next = reverse.next;
            // reverse.next = cur;
            // cur = next;
            cur.next = reverse.next;
            reverse.next = cur;
            cur = next;
        }
        head.next = reverse.next;

        return head;
    }

    /**
     * 通过递归来反转链表
     *
     * @param node
     * @return
     */
    public static HeroNode reverseNodeRecurive(HeroNode node) { //node是当前节点

        if (node.next == null) {
            return node;
        }

        // 先反转之后的节点
        HeroNode newNode = reverseNodeRecurive(node.next);

        node.next.next = node;
        node.next = null;
        return newNode;
    }

    /**
     * 给定一个链表的头结点 head,以及两个整数 from 和 to ,在链表上把第 from 个节点和第 to 个节点这一部分进行翻转。
     * 例如：
     * 给定如下链表，from = 2, to = 4 head-->5-->4-->3-->2-->1 将其翻转后，链表变成 head-->5--->2-->3-->4-->1
     */
    public static void reverseRegion(HeroNode node, int fromIndex, int toIndex) {
        if (fromIndex > toIndex || toIndex > size()) {
            throw new IllegalArgumentException("给定的参数不正确");
        }

        HeroNode from = null;               // from 结点
        HeroNode to = null;                 // to 结点
        HeroNode fromPre = null;            // from-1结点
        HeroNode toNext = null;             // to+1结点
        HeroNode tmp = node.next;             // to+1结点
        // 从from到to则需要找到from-1到to+1的节点
        int curIndex = 1;      // 头结点的index为1
        while (tmp != null) {
            if (curIndex == fromIndex - 1) {
                fromPre = tmp;
            } else if (curIndex == fromIndex) {
                from = tmp;
            } else if (curIndex == toIndex) {
                to = tmp;
            } else if (curIndex == toIndex + 1) {
                toNext = tmp;
            }
            tmp = tmp.next;
            curIndex++;
        }

        if (from == null || to == null) {
            // from 或 to 都超过尾结点不翻转
            throw new RuntimeException("不符合条件");
        }
        // 反转节点
        HeroNode pre = from;
        HeroNode cur = pre.next;

        while (toNext != cur) {
            HeroNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        // 将 from-1 节点指向 to 结点（如果从 head 的后继结点开始翻转，则需要重新设置 head 的后继结点），
        // 将 from 结点指向 to + 1 结点
        if (null != fromPre) {
            fromPre.next = to;
        } else {
            head.next = to;
        }
        from.next = toNext;
    }

    /**
     * 百度笔试题: 逆序打印单链表 要求不破坏原来的数据结构
     * 思路:借助于栈来实现操作
     *
     * @param args
     */
    public static void reversePrint(HeroNode node) {
        if (isEmpty()) {
            System.out.println("空链表,无需打印");
            return;
        }

        // 创建一个用来存储所有节点的栈
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = node.next;
        while (null != cur) {
            stack.push(cur);
            cur = cur.next;
        }

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }

    }

    /**
     * 合并两个有序的单链表,要求合并后的结果依然有序
     * 例如:
     * n1: 1 -> 2  -> 4
     * n2: 2 -> 3  -> 5
     * res: 1 -> 2 -> 2 -> 3 -> 4 -> 5
     *
     * @param args
     */
    public static HeroNode merge(HeroNode n1, HeroNode n2) {
        HeroNode cur1 = n1.next;
        HeroNode cur2 = n2.next;
        HeroNode next1;
        HeroNode next2;
        HeroNode merge = new HeroNode();
        while (cur1 != null || cur2 != null) {
            next1 = cur1.next;
            next2 = cur2.next;
            cur1.next = merge.next;
            merge.next = cur1;
            cur1 = next1;
        }

        return null;
    }

    /**
     * Google 面试题：给定单向链表的头指针和一个节点指针，定义一个函数在 O(1) 内删除这个节点
     *
     * @param args
     */
    public void remove(HeroNode n1) {
        if (n1.next == null) {
            HeroNode tmp = head.next;
            while (null != tmp) {
                tmp = tmp.next;
            }
        }
    }

    /**
     * 给出一个链表，每 k 个节点一组进行翻转，并返回翻转后的链表。k 是一个正整数，它的值小于或等于链表的长度。
     * 如果节点总数不是 k 的整数倍，那么将最后剩余节点保持原有顺序。
     * 示例 : 给定这个链表：head-->1->2->3->4->5
     * 当 k = 2 时，应当返回: head-->2->1->4->3->5
     * 当 k = 3 时，应当返回: head-->3->2->1->4->5
     *
     * @param args
     */
    public static void groupReverse(HeroNode node, int k) {
        HeroNode tmp = head.next;
        int step = 0;               // 计数，用来找出首结点和尾结点

        HeroNode startK = null;         // k个一组链表中的头结点
        HeroNode startKPre = head;      // k个一组链表头结点的前置结点
        HeroNode endK;                  // k个一组链表中的尾结点
        while (tmp != null) {
            // tmp 的下一个节点,因为由于翻转，tmp 的后继结点会变,要提前保存
            HeroNode tmpNext = tmp.next;
            if (step == 0) {
                // k 个一组链表区间的头结点
                startK = tmp;
                step++;
            } else if (step == k - 1) {
                // 此时找到了 k 个一组链表区间的尾结点（endK）,对这段链表用迭代进行翻转
                endK = tmp;
                HeroNode pre = startK;
                HeroNode cur = startK.next;
                if (cur == null) {
                    break;
                }
                HeroNode endKNext = endK.next;
                while (cur != endKNext) {
                    HeroNode next = cur.next;
                    cur.next = pre;
                    pre = cur;
                    cur = next;
                }
                // 翻转后此时 endK 和 startK 分别是是 k 个一组链表中的首尾结点
                startKPre.next = endK;
                startK.next = endKNext;

                // 当前的 k 个一组翻转完了，开始下一个 k 个一组的翻转
                startKPre = startK;
                step = 0;
            } else {
                step++;
            }
            tmp = tmpNext;
        }
    }

    /**
     * 给定一个链表的头结点 head,以及两个整数 from 和 to ,在链表上把第 from 个节点和第 to 个节点这一部分进行翻转。
     * 例如：给定如下链表，from = 2, to = 4 head-->5-->4-->3-->2-->1 将其翻转后，链表变成 head-->5--->2-->3-->4-->1
     *
     * @param args
     */
    public static void reverse(HeroNode node,int start, int end) {
        if (start > end) {
            throw new IllegalArgumentException();
        }

        HeroNode startpre = null;
        HeroNode endNext = null;
        HeroNode tmp = node.next;
        HeroNode startNode = null;
        int count = 1;

        // 获取到了开始之前的和结束之前的
        while (tmp != null) {
            if (count+1 == start) {
                startpre = tmp;
            } else if (count == end + 1) {
                endNext = tmp;
            }
            tmp = tmp.next;
            count++;
        }
        startNode = startpre.next;
        // 从这时候开始循环
        HeroNode temp = startpre.next;
        HeroNode reverse = new HeroNode();
        while (temp != endNext) {
            HeroNode n = temp.next;
            temp.next = reverse.next;
            reverse.next = temp;
            temp = n;
        }
        startpre.next = reverse.next;
        startNode.next = endNext;
    }

    /**
     * 给定一个单链表，设计一个算法实现链表向右旋转 K 个位置。
     * 举例：给定 head->1->2->3->4->5->NULL, K=3,
     * 右旋后即为 head->3->4->5-->1->2->NULL
     */
    public static void reverseK(HeroNode node, int k) {
        // 先需要找到第K个位置
        HeroNode kNode = node;
        HeroNode KNodeCur = node;
        while (k > 0) {
            KNodeCur = kNode = kNode.next;
            k--;
        }
        HeroNode headNext = node.next;
        node.next = kNode;
        while (kNode.next != null) {
            kNode = kNode.next;
        }
        kNode.next = headNext;
        while (kNode.next != KNodeCur) {
            kNode = kNode.next;
        }
        kNode.next = null;
    }


    public static void main(String[] args) {
        HeroNode heroNode1 = new HeroNode(1, "zs", "");
        HeroNode heroNode2 = new HeroNode(2, "ls", "");
        HeroNode heroNode3 = new HeroNode(3, "ww", "");
        HeroNode heroNode4 = new HeroNode(4, "zl", "");
        HeroNode heroNode5 = new HeroNode(5, "tq", "");
        SingleLinkedList list = new SingleLinkedList();
        /*list.addTail(heroNode3);
        list.addTail(heroNode1);
        list.addTail(heroNode4);
        list.addTail(heroNode5);
        list.addTail(heroNode2);*/
        addHead(heroNode1);
        addHead(heroNode2);
        addHead(heroNode3);
        addHead(heroNode4);
        addHead(heroNode5); // 5 -> 4 -> 3 -> 2 -> 1
        System.out.println("----添加完成后-------");
        show();
        System.out.println();
        reverse(head, 2, 4);
        show();
        System.out.println();
        /*groupReverse(head, 2);// 0 --> 5 --> 4 --> 3 --> 2 --> 1  期望 0 --> 4 --> 5 --> 2 --> 3 --> 1
        System.out.println("----K反转后-------");
        show();*/
        /* reverseRegion(head, 2, 4); // 0 --> 5 --> 4 --> 3 --> 2 --> 1  期望 0 --> 5 --> 2 --> 3 --> 4 --> 1
        System.out.println("----逆向一段后-------");
        show();*/
        /*HeroNode heroNode = reverseNodeRecurive(head.next);
        head.next = heroNode;
        System.out.println("----递归逆序后-------");
        show();*/

        /*heroNode5 = new HeroNode(2, "2", "222");
        list.update(heroNode5);
        System.out.println("----更新完成后------");
        show();
        list.remove(1);
        System.out.println("----删除1完成后------");
        show();
        list.remove(5);
        System.out.println("----删除5完成后------");
        show();*/
        /*System.out.println("有效节点的个数为:"+ list.size());
        System.out.println("倒数第1个节点为" +  getKNode(1));// HeroNode{no=5, name='tq', nickName=''}
        System.out.println("倒数第3个节点为" +  getKNode(3));// HeroNode{no=3, name='ww', nickName=''}--->*/
        /*reverseNode(head);
        System.out.println("----反转完成后-------");
        show();*/
        /*System.out.println("逆序打印单链表");
        reversePrint(head);*/
    }
}

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
@SuppressWarnings("all")
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    /* 指向下一个结点的指针 */
    public HeroNode next;

    public HeroNode() {
    }

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}