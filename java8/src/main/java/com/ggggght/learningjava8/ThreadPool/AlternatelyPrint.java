package com.ggggght.learningjava8.threadPool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 创建两个线程 使他们交替输出 如:t1线程输出1 t2线程输出A 使其结果如1A2B3C...
 */
@SuppressWarnings("all")
public class AlternatelyPrint {

	private static Thread t1;

	private static Thread t2;

	private final static Object o = new Object();

	enum ThreadRunState {

		T1, T2

	}

	static volatile ThreadRunState state = ThreadRunState.T1;

	/**
	 * 使用LookSupport锁
	 */
	public static void print1() {
		t1 = new Thread(() -> {
			for (int i = 0; i < 26; i++) {
				System.out.println(i);
				LockSupport.unpark(t2);
				LockSupport.park();
			}
		});

		t2 = new Thread(() -> {
			for (int i = 65; i < 90; i++) {
				LockSupport.unpark(t1);
				System.out.println((char) i);
				LockSupport.park();
			}
		});

		t1.start();
		t2.start();
		try {

			TimeUnit.SECONDS.sleep(1);
		}
		catch (Exception e) {
		}
		System.exit(1);

	}

	/**
	 * 使用cas来完成
	 */
	public static void print2() {
		t1 = new Thread(() -> {
			for (int i = 0; i < 26; i++) {
				while (state != ThreadRunState.T1) {
				}
				System.out.println(i);
				state = ThreadRunState.T2;
			}
		});

		t2 = new Thread(() -> {
			for (int i = 65; i < 90; i++) {
				while (state != ThreadRunState.T2) {
				}
				System.out.println((char) i);
				state = ThreadRunState.T1;
			}
		});

		t1.start();
		t2.start();
	}

	/**
	 * 使用sync notifyAll wait来实现
	 */
	public static void print3() {
		t1 = new Thread(() -> {
			synchronized (o) {
				for (int i = 0; i < 26; i++) {
					System.out.println(i);
					try {
						o.notifyAll();
						o.wait();
					}
					catch (Exception ex) {
					}
				}
				o.notifyAll();
			}
		});

		t2 = new Thread(() -> {
			synchronized (o) {
				for (int i = 65; i < 90; i++) {
					System.out.println((char) i);
					try {
						o.notifyAll();
						o.wait();
					}
					catch (Exception ex) {
					}
				}
				o.notifyAll();
			}
		});

		t1.start();
		t2.start();
	}

	/**
	 * 要使t2线程先执行的方法 如此输出的结果会是A1B2C3D4.. 使用CountDownlatch
	 */
	private static final CountDownLatch cdl = new CountDownLatch(1);

	public static void print4() {
		t1 = new Thread(() -> {
			try {
				cdl.await();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (o) {
				for (int i = 0; i <= 26; i++) {
					System.out.println(i);
					try {
						o.notifyAll();
						o.wait();
					}
					catch (Exception ex) {
					}
				}
				o.notifyAll();
			}
		});

		t2 = new Thread(() -> {
			cdl.countDown();
			synchronized (o) {
				for (int i = 65; i <= 90; i++) {
					System.out.println((char) i);
					try {
						o.notifyAll();
						o.wait();
					}
					catch (Exception ex) {
					}
				}
				o.notifyAll();
			}
		});

		t1.start();
		t2.start();
	}

	/**
	 * 使用ReentrantLock来实现功能
	 */
	private static final ReentrantLock lock = new ReentrantLock();

	public static void print5() {
		// 这里的condition不可以理解为条件 而应该理解为队列
		Condition condition = lock.newCondition();
		t1 = new Thread(() -> {
			try {
				lock.lock();
				for (int i = 0; i < 27; i++) {
					System.out.println(i);
					condition.signal();
					condition.await();
				}
				condition.signal();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				lock.unlock();
			}
		});

		t2 = new Thread(() -> {
			try {
				lock.lock();
				for (int i = 65; i < 91; i++) {
					System.out.println((char) i);
					condition.signal();
					condition.await();
				}
				condition.signal();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				lock.unlock();
			}
		});

		t1.start();
		t2.start();
	}

	public static void main(String[] args) {
		print5();
	}

}
