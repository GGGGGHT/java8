package com.ggggght.learningjava8.guava;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.Service;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.jupiter.api.Test;

/**
 * @desc: guava concurrency 原始的{@link Future} 代表了一个异步计算的结果 可能已经或可能尚未完成生成结果的计算。
 * 未来可以作为进行中计算的句柄，是服务向我们提供结果的承诺。 {@link ListenableFuture} 可以注册一个回调函数，会在计算执行结束后被立即执行
 * 可以通过{@method addListener} 与Future相同 只有在get时 才会将异常输出 {@link Service} 代表了操作的状态
 */
public class GuavaConcurrency {

	@Test
	public void concurrencyTest() throws ExecutionException, InterruptedException {
		ListenableFuture<Object> future = null;
		// future.addListener();
		ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
		ListenableFuture<Integer> explosion = service.submit(GuavaConcurrency::get);
		Futures.addCallback(explosion, new FutureCallback<Integer>() {
			@Override
			public void onSuccess(@Nullable Integer result) {
				System.out.println(result + " ...");
			}

			@Override
			public void onFailure(Throwable t) {
				System.out.println("error");
				System.out.println(t.getCause());
			}
		}, service);

		System.out.println("explosion.get() = " + explosion.get());
	}

	/**
	 * 使用{@link ListenableFuture} 的最重要原因是可以拥有复杂的异步操作链。 避免嵌套使用Future
	 * https://github.com/google/guava/wiki/ListenableFutureExplained
	 */
	@Test
	public void nestedFutureTest() {
		// avoid this
		// executorService.submit(new Callable<ListenableFuture<Integer>() {
		// @Override
		// public ListenableFuture<Integer> call() {
		// return otherExecutorService.submit(otherCallable);
		// }
		// });
	}

	@Test
	public void serviceManagerTest() {
		// ServiceManager manager = new ServiceManager();
	}

	private static Integer get() {
		int i = 1 / 0;
		return 1;
	}

}
