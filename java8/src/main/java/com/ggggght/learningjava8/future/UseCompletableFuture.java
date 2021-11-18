package com.ggggght.learningjava8.future;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class UseCompletableFuture {

	@Test
	public void completableFutureTest() throws ExecutionException, InterruptedException {
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello world");

		System.out.println(future.get());
	}

	/**
	 * 组合两个future
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	@Test
	public void combineTest() throws ExecutionException, InterruptedException {
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			System.out.println(Thread.currentThread().getName());
			return "hello";
		}).thenCombineAsync(CompletableFuture.supplyAsync(() -> {
			System.out.println(Thread.currentThread().getName());

			return "world";
		}), (s1, s2) -> s1 + " " + s2);

		System.out.println(future.get());
	}

	/**
	 * 使用supplyAsync的结果
	 */
	@Test
	public void acceptTest() {
		CompletableFuture.supplyAsync(() -> "hello").thenAccept(System.out::println);
	}

	/**
	 * 使用supplyAsync的结果
	 */
	@Test
	public void applyTest() {
		CompletableFuture.supplyAsync(() -> "hello").thenApply(t -> t + " world").thenAccept(System.out::println);
	}

	// @Test
	// public void test() {
	// CompletableFuture.supplyAsync(() -> "hello").handle((a, b) -> {
	// System.out.println("a = " + a);
	// System.out.println("b = " + b);
	// return a + b;
	// }).thenAccept(System.out::println);
	// }
	@Test
	public void anyOfTest() throws ExecutionException, InterruptedException {
		Random random = new Random();

		CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(10_000 + random.nextInt(1000));
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}

			return 100;
		});

		CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(10_000 + random.nextInt(1000));
			}
			catch (Exception e) {
				e.printStackTrace();
			}

			return "abc";
		});

		CompletableFuture<Object> f = CompletableFuture.anyOf(integerCompletableFuture, stringCompletableFuture);
		System.out.println(f.get());
	}

	@Test
	public void allOfTest() {
		Random random = new Random();

		CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(10_000 + random.nextInt(1000));
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}

			return 100;
		});

		CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(10_000 + random.nextInt(1000));
			}
			catch (Exception e) {
				e.printStackTrace();
			}

			return "abc";
		});

		CompletableFuture<Void> f = CompletableFuture.allOf(integerCompletableFuture, stringCompletableFuture);
	}

}
