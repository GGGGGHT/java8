package com.ggggght.learningjava8.lock;



import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author ght
 * @Desc  学习锁的一些相关内容
 * @date 2019-12-27 10:42 AM
 */

@SuppressWarnings("all")
public class LearnLock {


    public  static void lockSupportTest() {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                if (5 == i) {
                    LockSupport.park();
                }

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        try {
            TimeUnit.SECONDS.sleep(5);
            LockSupport.unpark(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        /*Thread t = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                if(i == 5) {
                    //调用LockSupport的park()方法阻塞当前线程t
                    LockSupport.park();
                }
                if(i == 8){
                    //调用LockSupport的park()方法阻塞当前线程t
                    LockSupport.park();
                }

                try {
                    //使当前线程t休眠1秒
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //启动当前线程t
        t.start();
        //唤醒线程t
        LockSupport.unpark(t);*/
    }
}
