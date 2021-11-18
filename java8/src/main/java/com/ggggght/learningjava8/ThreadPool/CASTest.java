package com.ggggght.learningjava8.ThreadPool;

//import sun.nio.ch.ThreadPool;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author ght
 * @Desc 学习CAS内容
 * @date 2020-04-14 16:12 问题: - CAS 是什么？为什么要用到 CAS？ 答: CAS,Compare And
 * Swap,即比较并交换.Java中的同步组件中大量使用了CAS实现了Java多线程的并发操作.整个AQS同步组件等都是以CAS来实现的.
 *
 * - CAS 和 synchronized的区别 答:
 * sync是线程获取锁是一种悲观锁策略,即假设每一次执行临界区代码会产生冲突,所以当前线程获取到锁之后会阻塞其他线程获取该锁.
 * CAS(无锁操作)是一种乐观锁策略,它假设所有线程访问共享资源的时候不会出现冲突,所以出现冲突时就不会阻塞其他的操作,而是重试当前操作直到没有冲突为止
 *
 * - CAS 原子操作的原理 答: 使用一个期望值和一个变量的当前值进行比较，如果当前变量的值与我们期望的值相等，就使用一个新值替换当前变量的值。
 *
 * - CAS 有哪些问题？ 答: CAS可能会造成ABA问题 CAS 需要检查操作值有没有发生改变，如果没有发生改变则更新。但是存在这样一种情况：如果一个值原来是 A，变成了
 * B，然后又变成了 A，那么在 CAS 检查的时候会认为没有改变，但是实质上它已经发生了改变，这就是 ABA 问题。
 * 解决方案可以沿袭数据库中常用的乐观锁方式，添加一个版本号可以解决。原来的变化路径 A->B->A 就变成了 1A->2B->3A。 在 java 1.5 后的 atomic
 * 包中提供了 AtomicStampedReference 来解决 ABA 问题，解决思路就是这样的。
 *
 * - Java 中利用 CAS 的原子操作有哪些，如何使用？ 答:
 * 在J.U.C.atomic包下有许多原子类.有对包装类型操作的如:AtomicInteger,AtomicLong等.也有对引用类型操作的如AtomicReference
 *
 * 总结: CAS 即比较和替换，可以高效的解决原子性问题。 CAS
 * 原子操作原理：使用一个期望值和一个变量的当前值进行比较，如果当前变量的值与我们期望的值相等，就使用一个新值替换当前变量的值。 Java 中的 CAS：atomic
 * 包下原子操作类，如 AtomicInteger 常用于修饰共享变量来保证原子性。
 *
 * 通过如下程序可以发现:
 * Atomic的效率比sync要高.因为CAS不加锁，而synchronized是要加锁的，有可能它要去操作系统申请重量级锁，所以synchronized效率偏低，在这种情形下效率偏低
 * LongAdder的效率要比Atomic要高.因为LongAdder采用的是锁分段机制类似于分段锁的概念。在它内部，会把一个值放到一个数组里，比如说数组长度是4，最开始是0，1000个线程，250个线程锁在第一个数租元素里，以此类推，每一个都往上递增算出来结果在加到一起。
 */

@SuppressWarnings("all")
public class CASTest {

	static long count2 = 0L;
	static AtomicLong count1 = new AtomicLong(0L);
	static LongAdder count3 = new LongAdder();

	public static void main(String[] args) throws Exception {
		Thread[] threads = new Thread[1000];

		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(() -> {
				for (int k = 0; k < 100000; k++)
					count1.incrementAndGet();
			});
		}

		Instant start = Instant.now();

		for (Thread t : threads)
			t.start();

		for (Thread t : threads)
			t.join();

		// TimeUnit.SECONDS.sleep(10);
		Instant end = Instant.now();

		System.out.println("Atomic: " + count1.get() + " time " + Duration.between(start, end).getSeconds());
		// -----------------------------------------------------------
		Object lock = new Object();

		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new Runnable() {
				@Override
				public void run() {

					for (int k = 0; k < 100000; k++)
						synchronized (lock) {
							count2++;
						}
				}
			});
		}

		start = Instant.now();

		for (Thread t : threads)
			t.start();

		for (Thread t : threads)
			t.join();

		end = Instant.now();

		System.out.println("Sync: " + count2 + " time " + Duration.between(start, end).getSeconds());

		// ----------------------------------
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(() -> {
				for (int k = 0; k < 100000; k++)
					count3.increment();
			});
		}

		start = Instant.now();

		for (Thread t : threads)
			t.start();

		for (Thread t : threads)
			t.join();

		end = Instant.now();

		// TimeUnit.SECONDS.sleep(10);

		System.out.println("LongAdder: " + count1.longValue() + " time " + Duration.between(start, end).getSeconds());

	}

	static void microSleep(int m) {
		try {
			TimeUnit.MICROSECONDS.sleep(m);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*
	 * public static void main(String[] args) {
	 *//*
		 * for (int i = 0; i < EXECUTOR.getCorePoolSize(); i++) { EXECUTOR.submit(() -> {
		 * integer.incrementAndGet(); }); }
		 *//*
			
			*//*
				 * try { TimeUnit.SECONDS.sleep(1); System.out.println("integer: " +
				 * integer.get()); } catch (Exception ex) { } finally {
				 * EXECUTOR.shutdown(); }
				 *//*
					 * LongAdder longAdder = new LongAdder(); }
					 */

}
