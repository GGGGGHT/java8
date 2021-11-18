package com.ggggght.learningjava8.jmx;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 管理线程池
 */
public interface ThreadPoolManagementMXBean {

	/**
	 * Retrieves the value of the read-only attribute PoolName; all web requests execute
	 * in a thread pool called "Default Executor"
	 * @return thread pool name
	 */
	public String getPoolName();

	/**
	 * Retrieves the value of the read-only attribute ActiveThreads, which is the number
	 * of active threads in the pool
	 * @return active thread count
	 */
	public int getActiveThreads(String name);

	/**
	 * Retrieves the value of the read-only attribute PoolSize, which is the total number
	 * of threads in the pool, including both active and inactive threads.
	 * @return thread pool size
	 */
	public int getPoolSize(String name);

}
