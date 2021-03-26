package com.ggggght.learningjava8.ThreadPool;

import org.junit.Test;
import org.omg.PortableInterceptor.INACTIVE;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author ght
 * @Desc
 * @date 2019-12-26 10:32 AM
 */

@SuppressWarnings("all")
public class LearnThread {
    private static final int COREPOOLSIZE = 10;
    private static final int MAXPOOLSIZE = 10;
    private static final long KEEPALIVETIME = 0L;
    private static final BlockingQueue WORKINGQUEUE = new LinkedBlockingDeque();

    /**
     * 问题：现有一个线程池，参数corePoolSize = 5，maximumPoolSize = 10，BlockingQueue阻塞队列长度为5，
     * 此时有4个任务同时进来
     * 1. 线程池会创建几条线程？
     * 2. 如果4个任务还没处理完，这时又同时进来2个任务，问：线程池又会创建几条线程还是不会创建？
     * 3. 如果前面6个任务还是没有处理完，这时又同时进来5个任务，问：线程池又会创建几条线程还是不会创建？
     */
    @Test
    public void threadCreateTest() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(5,
                10,
                0L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(5),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());


        for (int i = 0; i < 4; i++) {
            pool.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (Exception e) {
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
                } catch (Exception e) {
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
                } catch (Exception e) {
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
        for (int i = 0; i < 10000; i++) nums.add(1000000 + r.nextInt(1000000));

        //System.out.println(nums);

        Instant start = Instant.now();
        nums.forEach(v -> isPrime(v));
        Instant end = Instant.now();
        System.out.println("expend time: " + Duration.between(start, end).getNano());

        //使用parallel stream api

        start = Instant.now();
        nums.parallelStream().forEach(LearnThread::isPrime);
        end = start = Instant.now();
        System.out.println("expend time: " + Duration.between(start, end).getNano());

    }

    static boolean isPrime(int num) {
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    @Test
    public void test1() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(COREPOOLSIZE,
                MAXPOOLSIZE,
                KEEPALIVETIME,
                TimeUnit.SECONDS,
                WORKINGQUEUE);
    }
}