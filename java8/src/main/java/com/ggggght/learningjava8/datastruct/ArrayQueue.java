package com.ggggght.learningjava8.datastruct;

import java.util.Arrays;

/**
 * @author ght
 * @Desc 使用数组来模拟队列
 * @date 2019-12-31 7:42 PM
 * 数组模拟队列存在的问题: 不能重复使用空间
 */

@SuppressWarnings("all")
public class ArrayQueue {
    // 表示数组的最大容量
    private int maxSize;
    // 指向队首
    private int front;
    // 队列尾
    private int rear;
    // 该数据用于存放数据,模拟队列
    private int[] arr;

    public ArrayQueue() {
        this(16);
    }

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        // 指向队列头部(指向的是第一个数据之前的位置)
        front = -1;
        // 指向队列尾部(指向的是最后一个数据的位置)
        rear = -1;
    }

    /* 判断队列是否已满 */
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    /* 判断队列是否为空 */
    public boolean isEmpty() {
        return front == rear;
    }

    /* 添加数据到队列中 */
    public void add(int val) {
        if (isFull()) {
            throw new RuntimeException("当前队列已满,不能再添加数据了..");
        }

        arr[++rear] = val;
    }

    /* 弹出第一个元素 */
    public int remove() {
        // 判断是否为空
        if (isEmpty()) {
            throw new RuntimeException("当前队列是空的,不能获取数据");
        }
        return arr[front++];
    }

    /* 格式化输出数组 */
    public void show() {
        if (isEmpty()) {
            System.out.println("队列为空...");
            return;
        }
        Arrays.stream(arr).forEach(i -> System.out.printf("arr[%d]=%d\n,", i, arr[i]));
    }

    /* 查看队首的元素 */
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("当前队列是空的,不能获取数据");
        }

        return arr[front + 1];
    }

    public static void main(String[] args) {
        ReuseArrayQueue queue = new ReuseArrayQueue(3);
        queue.add(1);
        queue.add(2);
        // queue.add(3);
        queue.show();
    }
}


@SuppressWarnings("all")
class ReuseArrayQueue extends ArrayQueue {
    // 表示数组的最大容量
    private static int maxSize;
    // 指向队首
    private static int front;
    // 队列尾
    private static int rear;
    // 该数据用于存放数据,模拟队列
    private int[] arr;

    public ReuseArrayQueue() {
        super();
    }

    public ReuseArrayQueue(int maxSize) {
        this.arr = new int[maxSize];
        this.maxSize = maxSize;
        front = rear = 0;
    }


    /* 判断队列是否已满 */
    @Override
    public boolean isFull() {
        return (rear + 1) % maxSize == 0;
    }

    /* 判断队列是否为空 */
    @Override
    public boolean isEmpty() {
        return front == rear;
    }

    /* 添加数据到队列中 */
    public void add(int val) {
        if (isFull()) {
            throw new RuntimeException("当前队列已满,不能再添加数据了..");
        }

        arr[rear] = val;
        this.rear = ++rear % maxSize;
    }

    /* 弹出第一个元素 */
    public int remove() {
        // 判断是否为空
        if (isEmpty()) {
            throw new RuntimeException("当前队列是空的,不能获取数据");
        }

        // 先将front保存
        int res = arr[front];
        // 将front后移
        front = ++front % maxSize;
        return res;
    }

    /* 格式化输出元素 */
    @Override
    public void show() {
        int real = (this.rear + this.maxSize - this.front) % this.maxSize;
        for (int i = front ; i < front + real; i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }
}

