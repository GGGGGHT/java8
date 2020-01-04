package com.ggggght.learningjava8.datastruct.solution;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ght
 * @Desc 解决约瑟夫问题
 * @date 2020-01-04 8:01 PM
 * 1. 首先构造一个带头尾节点的环型链表
 */

@SuppressWarnings("all")
public class Joseph {

    public static void main(String[] args) {
        CircleSingleLinkedList k = new CircleSingleLinkedList();
        k.addBoy(10);
        k.show();
        k.pop(3);
    }

}

@SuppressWarnings("all")
class CircleSingleLinkedList {
    /*创建一个first节点,当前没有编号*/
    private Boy first = new Boy(-1);

    /**
     * @param nums 根据nums的值去添加指定个数的节点
     */
    public void addBoy(int nums) {
        if (nums < 1) {
            throw new IllegalArgumentException(nums + "不能小于1");
        }

        Boy curBoy = null;
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                // 形成环
                first.setNext(first);
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    /**
     * 遍历输出环型链表
     */
    public void show() {
        if (first == null) {
            System.out.println("null~~");
            return;
        }

        Boy tmp = first;
        do {
            System.out.println(tmp);
            tmp = tmp.getNext();
        } while (tmp != first);
    }

    /**
     * @param i 根据i产生一个出队编号的序列
     */
    public void pop(int i) {
        int start = 1;

        Boy helper = first;
        Boy cur = first;

        while (helper.getNext() != first) {
            helper = helper.getNext();
        }

        while (cur.getNext() != cur) {
            // 出队
            if (start+1 == i) {
                // 前一个节点指向下一个节点
                helper = helper.getNext();
                System.out.println(cur.getNext().getNo() + "chu dui~~");
                cur.setNext(cur.getNext().getNext());
                start = 0;
                continue;
            }
            cur = cur.getNext();
            helper = helper.getNext();
            start++;
        }
        System.out.println(cur.getNo() + "chu dui~~");
    }
}

/**
 * 创建一个Boy类,表示一个节点
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}