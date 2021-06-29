package com.ggggght.learningjava8.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class UseUnSafe {
	static final Unsafe UNSAFE;
	static final Long A_PTR;

	static volatile int a;

	static {
		try {
			Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
			theUnsafe.setAccessible(true);
			UNSAFE = (Unsafe) theUnsafe.get(null);
			A_PTR = UNSAFE.staticFieldOffset(UseUnSafe.class.getDeclaredField("a"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	static void inc() {
		for (; ; ) {
			int t = a;
			if (UNSAFE.compareAndSwapInt(UseUnSafe.class, A_PTR, t, ++t)) {
				break;
			}

			Thread.yield();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		// new ThreadPoolExecutor(10, 10, 60L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(30));
		ExecutorService executor = Executors.newCachedThreadPool();
		System.out.println("a = " + a);
		Instant start = Instant.now();
		for (int i = 0; i < 100000; i++) {
			executor.execute(UseUnSafe::inc);
		}

		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.MINUTES);
		System.out.println("a = " + a);
		Instant end = Instant.now();
		// 433 431 576 689 422
		System.out.println(Duration.between(start, end).toMillis());
	}
}
