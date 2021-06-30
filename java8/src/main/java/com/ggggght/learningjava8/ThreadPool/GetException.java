package com.ggggght.learningjava8.ThreadPool;

import java.util.concurrent.*;

/**
 * 如何拿到ThreadPoolExecutor中submit提交后的异常
 */
public class GetException {
	static ExecutorService pool = Executors.newFixedThreadPool(2);

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		// normal();
		// catchException
		// useFutureTask();
		myThreadPoolExecutor myThreadPoolExecutor = new myThreadPoolExecutor(1, 1, 0, TimeUnit.MINUTES, new LinkedBlockingDeque<>());
		myThreadPoolExecutor.execute(() -> {
			int i = 1 / 0;
		});
		myThreadPoolExecutor.shutdown();
		pool.shutdown();
	}

	/**
	 * 最原始的情况 捕获不到算术异常
	 */
	public static void normal() {
		pool.submit(() -> {
			int i = 1 / 0;
			System.out.println("hello world");
		});

	}

	/**
	 * 手动捕获异常
	 */
	public static void catchException() {
		pool.submit(() -> {
			try {
				int i = 1 / 0;
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public static void useFutureTask() throws ExecutionException, InterruptedException {
		FutureTask<Void> futureTask = new FutureTask<>(() -> {
			int i = 1 / 0;
			return null;
		});

		pool.submit(futureTask);
		futureTask.get();
	}

	static class myThreadPoolExecutor extends ThreadPoolExecutor {

		public myThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
			super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		}

		@Override
		protected void afterExecute(Runnable r, Throwable t) {
			super.afterExecute(r, t);

			if (null != t) {
				t.printStackTrace();
			}
		}
	}

}
