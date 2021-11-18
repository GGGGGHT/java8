package com.ggggght.learningjava8.threadPool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("all")
public class CountDownLatchTest {

	public static void main(String[] args) throws InterruptedException {
		int thread_count = 10;
		CountDownLatch latch = new CountDownLatch(thread_count);
		AtomicInteger atomicInteger = new AtomicInteger(0);
		ThreadPoolExecutor executor = new ThreadPoolExecutor(thread_count, thread_count, 0L, TimeUnit.SECONDS,
				new LinkedBlockingDeque<>());
		for (int i = 0; i < thread_count; i++) {
			executor.execute(() -> {
				// incrementAndGet是如何实现的
				// 先调用 unsafe.getAndAddInt(this, valueOffset, 1) + 1;
				// unsafe.getAndAddInt()是调用的cas实现
				// cas是native方法 所以是在hotsopt的C++代码实现
				// native是使用汇编来实现
				// 如果是多核CPU 使用的是 lock cmpxchgq指令来实现
				atomicInteger.incrementAndGet();
				latch.countDown();
			});
		}

		latch.await();

		System.out.println(atomicInteger);
		executor.shutdown();
	}

}
