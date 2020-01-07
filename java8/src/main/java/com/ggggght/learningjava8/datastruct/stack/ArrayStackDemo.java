package com.ggggght.learningjava8.datastruct.stack;


import java.util.Arrays;

/**
 * @author ght
 * @Desc 使用数组来模拟栈
 * 使用top来表示栈顶 初始化为-1
 * 1. 当有数据加入到栈时, stack[++top] = data;
 * 2. 当要出栈时,弹出栈顶的信息 同时top--即 data = stack[top--]
 * @date 2020-01-05 1:21 PM
 */

@SuppressWarnings("all")
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.show();
        int pop = stack.pop();
        System.out.println("弹出了栈顶的元素:" + pop);
        stack.push(5);
        stack.show();
    }
}

class ArrayStack {
    /*即一个栈最大的空间*/
    private int maxSize;
    /*数组模拟栈 数据存在该数组中*/
    private int[] stack;
    /*表示栈顶*/
    private int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    /**
     * 判断该栈是否已满
     * @return
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 判断栈是否为空
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 压入数据到栈顶
     * @param val
     */
    public void push(int val) {
        if (isFull()) {
            throw new RuntimeException("栈已满,无法再存入数据");
        }

        stack[++top] = val;
    }

    /**
     * 弹出栈顶的数据
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }

        return stack[top--];
    }

    /**
     * 遍历栈的数据 需要从栈顶开始显示
     */
    public void show() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }

        Arrays.stream(stack).forEach(System.out::println);
        /*for (int i = top; i > -1; i--) {
            System.out.printf("stack[%d]=%d \t",i,stack[i]);
        }*/
    }
}
