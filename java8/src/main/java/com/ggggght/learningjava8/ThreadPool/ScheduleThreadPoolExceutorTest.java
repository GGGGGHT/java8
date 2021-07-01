package com.ggggght.learningjava8.threadPool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * scheduleAtFixedRate只依赖于任务的执行时间,不包含任务本身执行的时间
 * scheduleWithFixedDelay 依赖于任务的执行时间
 * <pre>
 *     任务本身两秒 延迟两秒
 *     scheduleAtFixedRate
 *     0---------- ---------- ---------- ---------- ---------- ----------6
 *     ^                    ^                     ^                      ^
 *     | 任务开始的时间       | 第一次执行完的时间      | 第二次执行完的时间      | 第二次执行完的时间
 *
 *   scheduleWithFixedDelay
 *       0---------- ---------- ---------- ---------- ---------- ----------6
 *       ^                    ^                                          ^
 *       | 任务开始的时间        | 第一次执行完的时间                          | 第二次执行完的时间
 *
 * </pre>
 *
 * lock.lock() 与 lock.lockInterruptibly()的区别 lockInterruptibly可以被打断, 以可响应中断的方式加锁等待
 */
@SuppressWarnings("all")
public class ScheduleThreadPoolExceutorTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleThreadPoolExceutorTest.class);
    
    public static void main(String[] args) throws InterruptedException {
        final ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(1);
       /* final Instant start = Instant.now();
        poolExecutor.schedule(() -> {
            System.out.println("hello world");
            final Instant end = Instant.now();
            System.out.println("Duration.between(start,end).getSeconds() = " + Duration.between(start, end).getSeconds());
        },3, TimeUnit.SECONDS);*/
    
        // 每次延迟两秒去执行
        // Wed Jun 30 08:44:29 CST 2021
        // Wed Jun 30 08:44:31 CST 2021
        // Wed Jun 30 08:44:33 CST 2021
        /*poolExecutor.scheduleAtFixedRate(() -> {
            System.out.println(new Date());
            try {
                TimeUnit.SECONDS.sleep(2L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 0L, 2L, TimeUnit.SECONDS);*/
    
        // 包含任务自身执行的时间  根据任务执行完后的时间去计算出下一次任务的执行时间
        // Wed Jun 30 08:45:12 CST 2021
        // Wed Jun 30 08:45:16 CST 2021
        // Wed Jun 30 08:45:20 CST 2021
        poolExecutor.scheduleWithFixedDelay(() -> {
            System.out.println(new Date());
            try {
                TimeUnit.SECONDS.sleep(2L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 0L, 2L, TimeUnit.SECONDS);
        // poolExecutor.shutdown();
        // poolExecutor.awaitTermination(5, TimeUnit.SECONDS);
    }
}
