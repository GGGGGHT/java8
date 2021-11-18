package com.ggggght.learningjava8.threadPool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("all")
public class ForkJoinPoolTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ForkJoinPoolTest.class);

	public static void main(String[] args) throws InterruptedException {
		ForkJoinPool pool = new ForkJoinPool();
		pool.submit(() -> System.out.println("hello world"));
		pool.shutdown();
		pool.awaitTermination(5, TimeUnit.SECONDS);
	}

}
