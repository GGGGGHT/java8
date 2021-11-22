package com.ggggght.learningjava8.ThreadPool;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;
import org.springframework.ui.context.support.ResourceBundleThemeSource;

/**
 * @author admin
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {
  private final long[] numbers;
  private final int start;
  private final int end;

  public static final long THRESHOLD = 10_000;

  public ForkJoinSumCalculator(long[] numbers) {
    this(numbers, 0, numbers.length);
  }

  public ForkJoinSumCalculator(long[] numbers, int start, int end) {
    this.numbers = numbers;
    this.start = start;
    this.end = end;
  }

  @Override protected Long compute() {
    int length = end - start;
    if(length <= THRESHOLD) return computeSequentially();

    ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
    // 对子任务调用fork()可以把它排进ForkJoinPool.
    leftTask.fork();
    ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
    // 直接对其中一个调用compute()效率更高.因为可以使其中一个子任务重用同一线程,从而避免在线程池中多分配一个任务造成的开销
    Long rightResult = rightTask.compute();

    // 对一个任务调用join方法会阻塞调用方,直到该任务做出结果.
    return leftTask.join() + rightResult;
  }

  private long computeSequentially() {
    return Arrays.stream(numbers, start, end).sum();
  }
}
