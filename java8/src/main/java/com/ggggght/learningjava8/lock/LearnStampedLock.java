package com.ggggght.learningjava8.lock;

import java.util.concurrent.locks.StampedLock;

/**
 * @Desc: 学习使用jdk8提供的StampedLock
 */
public class LearnStampedLock {
    private double x, y;
    private final StampedLock sl = new StampedLock();

    void move(double deltaX, double deltaY) {
        // 使用写锁 独占操作
        long stamp = sl.writeLock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            sl.unlock(stamp);
        }
    }

    /**
     * @Desc 在方法 distanceFromOrigin 中，首先，通
     * 过 tryOptimisticRead 方法获取乐观读标记；然后从主内存中加载点的坐标值 (x,y)；
     * 而后通过 StampedLock 的 validate 方法校验锁状态，判断坐标点 (x,y) 从主内存加
     * 载到线程工作内存过程中，主内存的值是否已被其他线程通过 move 方法修改，如果
     * validate 返回值为 true，证明 (x, y) 的值未被修改，可参与后续计算；否则，需加悲
     * 观读锁，再次从主内存加载 (x,y) 的最新值，然后再进行距离计算。其中，校验锁状
     * 态这步操作至关重要，需要判断锁状态是否发生改变，从而判断之前 copy 到线程工
     * 作内存中的值是否与主内存的值存在不一致。
     * @return
     */
    double distanceFromOrigin() {
        // 获取乐观锁
        long stamp = sl.tryOptimisticRead();
        // 拷贝变量到工作内存
        double currentX = x, currentY = y;
        // 检验锁的状态,判断是否数据不一致
        if (!sl.validate(stamp)) {
            // 获取悲观读锁
             stamp = sl.writeLock();
             try {
                 // 拷贝变量到工作内存
                 currentX = x;
                 currentY = y;
             }finally {
                 // 释放悲观读锁
                 sl.unlock(stamp);
             }
        }
        // 本地计算
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }
}
