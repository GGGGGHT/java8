package com.ggggght.learningjava8;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.locks.ReentrantLock;
import org.junit.jupiter.api.Test;

// @SpringBootTest
class Learningjava8ApplicationTests {

	private ReentrantLock lock = new ReentrantLock(true);

	@Test
	void contextLoads() {
		ForkJoinPool pool = new ForkJoinPool();
		Instant start = Instant.now();
		Long invoke = pool.invoke(new ForkJoinCalculate(0, 100000000000l));
		Instant end = Instant.now();

		System.out.println("耗费的时间为: " + Duration.between(start, end).getNano());
		System.out.println(invoke);
	}

	@Test
	void test() {
		Instant start = Instant.now();
		long sum = 0L;

		for (long i = 0L; i <= 100000000000l; i++) {
			sum += i;
		}
		Instant end = Instant.now();
		System.out.println("耗费的时间为: " + Duration.between(start, end).getNano());
		System.out.println(sum);
	}

	@Test
	void test2() {
		Instant start = Instant.now();
		// long sum = LongStream.range(0, 100000000000l)
		// .parallel()
		// .sum();
		Instant end = Instant.now();
		System.out.println(Duration.between(start, end).toMillis());
		System.out.println(Long.MAX_VALUE);
		// System.out.println(sum);
	}

}

class ForkJoinCalculate extends RecursiveTask<Long> {

	private long start;

	private long end;

	private static final long THRESHOLD = 10000l;

	public ForkJoinCalculate(long start, long end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected Long compute() {
		long length = end - start;

		if (length <= THRESHOLD) {
			long sum = 0;
			for (long i = start; i <= end; i++) {
				sum += i;
			}
			return sum;
		}
		else {
			long mid = ((end - start) >> 1) + start;
			ForkJoinCalculate left = new ForkJoinCalculate(start, mid);
			left.fork();
			ForkJoinCalculate right = new ForkJoinCalculate(mid + 1, end);
			right.fork();
			return left.join() + right.join();
		}
	}

}
