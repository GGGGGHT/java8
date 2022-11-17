package com.ggggght.learningjava8.ThreadPool;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.wildfly.common.annotation.NotNull;

/**
 * @author ght
 * @Desc
 * @date 2019-12-26 10:32 AM
 * <p>
 * ThreadPoolExecutor的执行流程 1. 提交任务 2. 首先判断工作线程是否小于核心线程数 如果小于核心线程数 直接进行addWorker()
 * 在addWorker方法中会判断线程池的运行状态，如果线程池已停止运行则返回false 3. 如果当前线程数已大于核心线程数 则需要判断阻塞队列是否已满 如果队列未满
 * 则将任务放入阻塞队列中 再次判断线程池是否是运行状态 如果线程池已停止运行，则将放入队列的任务移除，并拒绝该任务 4. 再次调用addWorker方法
 * 使用非核心线程去执行任务 如果添加失败 则调用reject 拒绝该任务
 */

@SuppressWarnings("all")
public class LearnThread {

	private static final int COREPOOLSIZE = 10;

	private static final int MAXPOOLSIZE = 10;

	private static final long KEEPALIVETIME = 0L;

	private static final BlockingQueue WORKINGQUEUE = new LinkedBlockingDeque();

	/**
	 * 问题：现有一个线程池，参数corePoolSize = 5，maximumPoolSize = 10，BlockingQueue阻塞队列长度为5，
	 * 此时有4个任务同时进来 1. 线程池会创建几条线程？ 2. 如果4个任务还没处理完，这时又同时进来2个任务，问：线程池又会创建几条线程还是不会创建？ 3.
	 * 如果前面6个任务还是没有处理完，这时又同时进来5个任务，问：线程池又会创建几条线程还是不会创建？
	 */
	@Test
	public void threadCreateTest() {
		ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 10, 0L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(5),
				Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());

		for (int i = 0; i < 4; i++) {
			pool.execute(() -> {
				try {
					TimeUnit.SECONDS.sleep(5);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
		// 有4个任务同时进来 线程池会创建几条线程？
		// 答: 4个任务同时进来 会创建4个线程来去执行任务
		System.out.println("4 个任务同时进来时创建的线程数为: " + pool.getActiveCount());

		for (int i = 0; i < 2; i++) {
			pool.execute(() -> {
				try {
					TimeUnit.SECONDS.sleep(5);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
		// 如果4个任务还没处理完，这时又同时进来2个任务，问：线程池又会创建几条线程还是不会创建？
		// 答:线程池会创建1个线程去执行任务
		System.out.println("4 个任务未执行完成,又进来2个任务后创建的线程数为: " + pool.getActiveCount());
		System.out.println("此时队列的容量为: " + pool.getQueue().size());
		// 如果前面6个任务还是没有处理完，这时又同时进来5个任务，问：线程池又会创建几条线程还是不会创建？
		// 答:线程池会创建一个新的临时线程来执行
		for (int i = 0; i < 5; i++) {
			pool.execute(() -> {
				try {
					TimeUnit.SECONDS.sleep(5);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
		System.out.println("又同时进来5个任务后创建的线程数量为: " + pool.getActiveCount());
		System.out.println("此时队列的容量为: " + pool.getQueue().size());
	}

	@Test
	public void test() {
		List<Integer> nums = new ArrayList<>();
		Random r = new Random();
		for (int i = 0; i < 10000; i++)
			nums.add(1000000 + r.nextInt(1000000));

		// System.out.println(nums);

		Instant start = Instant.now();
		nums.forEach(v -> isPrime(v));
		Instant end = Instant.now();
		System.out.println("expend time: " + Duration.between(start, end).getNano());

		// 使用parallel stream api

		start = Instant.now();
		nums.parallelStream().forEach(LearnThread::isPrime);
		end = start = Instant.now();
		System.out.println("expend time: " + Duration.between(start, end).getNano());

	}

	static boolean isPrime(int num) {
		for (int i = 2; i <= num / 2; i++) {
			if (num % i == 0)
				return false;
		}
		return true;
	}

	@Test
	public void test1() {
		ThreadPoolExecutor pool = new ThreadPoolExecutor(COREPOOLSIZE, MAXPOOLSIZE, KEEPALIVETIME, TimeUnit.SECONDS,
				WORKINGQUEUE);
	}

	/**
	 * 添加任务时线程池已停止
	 * @throws InterruptedException
	 */
	@Test
	public void test2() throws InterruptedException {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1));

		executor.execute(() -> {
			try {
				executor.shutdown();
			}
			catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println(" Thread.currentThread().getName() = " + Thread.currentThread().getName() + " 执行完成");
		});

		Thread.sleep(500);
		executor.execute(() -> {
			System.out.println(" Thread.currentThread().getName() = " + Thread.currentThread().getName() + " 2执行完成");
		});

		executor.awaitTermination(3, TimeUnit.SECONDS);
	}

}

class TestCallable {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		final ThreadDemo threadDemo = new ThreadDemo();
		FutureTask<Integer> futureTask = new FutureTask<Integer>(threadDemo);
		new Thread(futureTask).start();
		final Optional<Integer> value = Optional.ofNullable(futureTask.get());
		value.ifPresent(System.out::print);
	}

}

class ThreadDemo implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		int sum = 0;
		for (int i = 0; i < 100; i++) {
			sum += i;
		}
		return sum;
	}

	public void catchException() throws InterruptedException {
		ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.HOURS, new LinkedBlockingDeque<>(),
				new ThreadFactory() {
					@Override
					public Thread newThread(@NotNull Runnable r) {
						Thread thread = new Thread(r);
						thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
							@Override
							public void uncaughtException(Thread t, Throwable e) {
								System.out.println(e);
							}
						});
						return thread;
					}
				});

		poolExecutor.execute(() -> {
			int i = 1 / 0;
		});

		poolExecutor.shutdown();
		poolExecutor.awaitTermination(1L, TimeUnit.MINUTES);
	}

}
