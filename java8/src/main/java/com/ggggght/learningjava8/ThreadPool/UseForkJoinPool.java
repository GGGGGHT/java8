package com.ggggght.learningjava8.ThreadPool;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("all")
public class UseForkJoinPool {

  private static final Logger LOGGER = LoggerFactory.getLogger(
      UseForkJoinPool.class);

  @Benchmark
  public void avg() throws InterruptedException {
    long[] numbers = LongStream.range(1, 100_000_000).toArray();
    ForkJoinSumCalculator task = new ForkJoinSumCalculator(numbers);

    Long res = new ForkJoinPool().invoke(task);
    System.out.println("res = " + res);
  }

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
        .include(UseForkJoinPool.class.getSimpleName())
        .forks(1)
        .build();

    new Runner(opt).run();
  }

}


