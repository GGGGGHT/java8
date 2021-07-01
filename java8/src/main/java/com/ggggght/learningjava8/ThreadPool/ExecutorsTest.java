package com.ggggght.learningjava8.threadPool;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author 75685
 * @desc 对ThreadPool进行扩展
 */
public class ExecutorsTest {
	public static void main(String[] args) {
	
	}
}

class MyTreadPool extends ThreadPoolExecutor {
	private static final Logger logger = LoggerFactory.getLogger(MyTreadPool.class);
	private static final LongAdder LONG_ADDER = new LongAdder();
	private Instant start;
	
	/**
	 * Creates a new {@code ThreadPoolExecutor} with the given initial
	 * parameters.
	 *
	 * @param corePoolSize    the number of threads to keep in the pool, even
	 *                        if they are idle, unless {@code allowCoreThreadTimeOut} is set
	 * @param maximumPoolSize the maximum number of threads to allow in the
	 *                        pool
	 * @param keepAliveTime   when the number of threads is greater than
	 *                        the core, this is the maximum time that excess idle threads
	 *                        will wait for new tasks before terminating.
	 * @param unit            the time unit for the {@code keepAliveTime} argument
	 * @param workQueue       the queue to use for holding tasks before they are
	 *                        executed.  This queue will hold only the {@code Runnable}
	 *                        tasks submitted by the {@code execute} method.
	 * @param threadFactory   the factory to use when the executor
	 *                        creates a new thread
	 * @param handler         the handler to use when execution is blocked
	 *                        because the thread bounds and queue capacities are reached
	 * @throws IllegalArgumentException if one of the following holds:<br>
	 *                                  {@code corePoolSize < 0}<br>
	 *                                  {@code keepAliveTime < 0}<br>
	 *                                  {@code maximumPoolSize <= 0}<br>
	 *                                  {@code maximumPoolSize < corePoolSize}
	 * @throws NullPointerException     if {@code workQueue}
	 *                                  or {@code threadFactory} or {@code handler} is null
	 */
	public MyTreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, new MyThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
	}
	
	
	/**
	 * Method invoked prior to executing the given Runnable in the
	 * given thread.  This method is invoked by thread {@code t} that
	 * will execute task {@code r}, and may be used to re-initialize
	 * ThreadLocals, or to perform logging.
	 *
	 * <p>This implementation does nothing, but may be customized in
	 * subclasses. Note: To properly nest multiple overridings, subclasses
	 * should generally invoke {@code super.beforeExecute} at the end of
	 * this method.
	 *
	 * @param t the thread that will run task {@code r}
	 * @param r the task that will be executed
	 */
	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		super.beforeExecute(t, r);
		logger.info("Current Thread %s start %s.", t.getName(), r);
		start = Instant.now();
	}
	
	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		if (Objects.nonNull(t) && t instanceof RuntimeException) {
			logger.error(t.getMessage());
		}
		Instant end = Instant.now();
		logger.info("%s duration %f", r, Duration.between(start, end).getNano());
		super.afterExecute(r, t);
	}
	
	private static class MyThreadFactory implements ThreadFactory {
		private static final String DEFAULT_NAME = "TEST";
		private static final LongAdder LONG_ADDER = new LongAdder();
		
		/**
		 * Constructs a new {@code Thread}.  Implementations may also initialize
		 * priority, name, daemon status, {@code ThreadGroup}, etc.
		 *
		 * @param r a runnable to be executed by new thread instance
		 * @return constructed thread, or {@code null} if the request to
		 * create a thread is rejected
		 */
		@Override
		public Thread newThread(Runnable r) {
			return newThread(r, DEFAULT_NAME);
		}
		
		public Thread newThread(Runnable r, String name) {
			LONG_ADDER.increment();
			return new Thread(r, name + "-" + LONG_ADDER.longValue());
		}
	}
}