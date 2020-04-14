package com.ggggght.learningjava8.ThreadPool;

/**
 * @author ght
 * @Desc 学习synchronized关键字的使用
 * @date 2020-04-14 11:23
 * synchronized的用法
 * 1. 普通同步方法,锁的是当前实例对象
 * 2. 静态同步方法,锁的是当前类的class对象
 * 3. 同步方法块,锁的是括号里的对象
 *
 * 对于同步方法,JVM采用ACC_SYNCHRONIZED标志符来实现同步
 * 对于同步代码块,采用monitorenter和monitorexit来实现同步
 *
 */

@SuppressWarnings("all")
public class SynchorizedTest {
    private static int count;

    // 1.实例方法
    public synchronized void lockMethod(int value){
        count += value;
    }

    // 2.实例方法中的同步块 (等价于1)
    public void lockBlock(int value){
        synchronized(this){
            count += value;
        }
    }

    // 3.静态方法
    public static synchronized void staticLockMethod(int value){
        count += value;
    }

    // 4.静态方法中的同步块 (等价于3)
    public static void staticLockBlock(int value){
        // 问题: 当在静态方法中使用同步代码块时synchronized(SynchorizedTest.class)可否替换为synchronized(this)
        // 答: 不可以 因为在静态方法可以不通过对象来调用.而synchroized锁的必须是对象 所以此处不可以替换为this
        // 问题: SynchorizedTest这个类是否是单例的
        // 答: 如果是在同一个ClassLoader空间那它一定是单例.不是同一个类加载器就不是了，不同的类加载器互相之间也不能访问。
        synchronized(SynchorizedTest.class){
            count += value;
        }
    }

    public static void main(String[] args) {

    }
}
