package com.ggggght.learningjava8.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 代码生成的位置  target/generated-sources/annotations/.../{Class_name}_{Method_name}.java
 */
@SuppressWarnings("all")
public class PSTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(PSTest.class);
	
	// @Benchmark
    public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
				.include(PS.class.getSimpleName())
				.forks(1)
				.build();
		
		new Runner(opt).run();
	}
	
	/**
	 * 测试吞吐量
	 * @throws InterruptedException
	 */
	@Benchmark
	@BenchmarkMode(Mode.Throughput)
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void measureThroughput() throws InterruptedException {
		TimeUnit.MILLISECONDS.sleep(100);
	}
	
	/**
	 * 测试平均执行时间
	 * @throws InterruptedException
	 */
	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MICROSECONDS)
	public void measureAvgTime() throws InterruptedException {
		// TimeUnit.MILLISECONDS.sleep(100);
		// System.out.println("Hello world");
	}
	
	/**
	 * 对执行时间进行采样 不测试总运行时间
	 * JMH还尝试自动调整采样频率
	 * @throws InterruptedException
	 */
	@Benchmark
	@BenchmarkMode(Mode.SampleTime)
	@OutputTimeUnit(TimeUnit.MICROSECONDS)
	public void measureSamples() throws InterruptedException {
		TimeUnit.MILLISECONDS.sleep(100);
	}
	
	@Benchmark
	public void measureUnshared(ThreadState state) {
		// All benchmark threads will call in this method.
		//
		// However, since ThreadState is the Scope.Thread, each thread
		// will have it's own copy of the state, and this benchmark
		// will measure unshared case.
		state.x++;
	}
	
	@Benchmark
	public void measureShared(BenchmarkState state) {
		// All benchmark threads will call in this method.
		//
		// Since BenchmarkState is the Scope.Benchmark, all threads
		// will share the state instance, and we will end up measuring
		// shared case.
		state.x++;
	}
	
	@State(Scope.Thread)
	public static class ThreadState {
		volatile double x = Math.PI;
	}
	
	@State(Scope.Benchmark)
	public static class BenchmarkState {
		volatile double x = Math.PI;
	}
}
