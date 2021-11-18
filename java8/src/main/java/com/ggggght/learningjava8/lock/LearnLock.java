package com.ggggght.learningjava8.lock;

import java.io.IOException;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import org.junit.Test;

/**
 * @author ght
 * @Desc 学习锁的一些相关内容
 * @date 2019-12-27 10:42 AM
 */

@SuppressWarnings("all")
public class LearnLock {

	public static void lockSupportTest() {
		Thread t = new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				System.out.println(i);
				if (5 == i) {
					LockSupport.park();
				}

				try {
					TimeUnit.SECONDS.sleep(1);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
		try {
			TimeUnit.SECONDS.sleep(5);
			LockSupport.unpark(t);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		/*
		 * Thread t = new Thread(()->{ for (int i = 0; i < 10; i++) {
		 * System.out.println(i); if(i == 5) { //调用LockSupport的park()方法阻塞当前线程t
		 * LockSupport.park(); } if(i == 8){ //调用LockSupport的park()方法阻塞当前线程t
		 * LockSupport.park(); }
		 *
		 * try { //使当前线程t休眠1秒 TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e)
		 * { e.printStackTrace(); } } }); //启动当前线程t t.start(); //唤醒线程t
		 * LockSupport.unpark(t);
		 */
	}

	@Test
	public void semaphoreTest() throws InterruptedException, IOException {
		Semaphore semaphore = new Semaphore(3);

		for (int i = 0; i < 5; i++) {
			new Thread(() -> {
				try {
					semaphore.acquire();
					Thread.sleep(1000);
					System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				finally {
					// 必须release 不然后续线程无法获得令牌
					semaphore.release();
				}
			}).start();
		}
	}

	@Test
	public void exchangeTest() throws IOException {
		Exchanger<Object> exchanger = new Exchanger<>();

		new Thread(() -> {
			String str = "T1";
			Object res = null;
			try {
				res = exchanger.exchange(str);
			}
			catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println(Thread.currentThread().getName() + " received: " + res);
		}, "t1").start();

		new Thread(() -> {
			String str = "T2";
			Object res = null;
			try {
				res = exchanger.exchange(str);
			}
			catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println(Thread.currentThread().getName() + " received: " + res);
		}, "t2").start();
	}

	@Test
	public void reentrantLockTest() {
		ReentrantLock reentrantLock = new ReentrantLock();

		reentrantLock.lock();
		try {
			System.out.println("hello");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			reentrantLock.unlock();
		}
	}

}
