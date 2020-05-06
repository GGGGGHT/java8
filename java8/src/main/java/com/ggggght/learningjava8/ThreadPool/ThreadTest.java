package com.ggggght.learningjava8.ThreadPool;

import sun.misc.Unsafe;

/**
 * @author ght
 * @Desc 关于线程
 * @date 2020-04-14 15:05
 *
 * 线程的几种状态:
 * - NEW
 * - RUNNABLE
 * - BLOCKED
 * - WAITING
 * - TIMED_WAITING
 * - TERMINATED
 *
 * 启动线程的方式:
 * 1. 继承Thread类
 * 2. 实现Runnable方法
 */

@SuppressWarnings("all")
public class ThreadTest {
    public static void main(String[] args) {
        // 启动线程的方式
        // 1.继承Thread类
        MyThread thread = new MyThread();
        thread.start();
        // 2.实现Runnable接口
        Thread t1 = new Thread(new MyRunnable(),"Runnable_Thread");
        t1.start();
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("继承Thread类来启动线程");
    }
}

class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("实现Runnable接口来启动线程");
    }
}
