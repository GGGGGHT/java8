package com.ggggght.learningjava8.cache;

import com.github.benmanes.caffeine.cache.AsyncCache;
import com.github.benmanes.caffeine.cache.AsyncCacheLoader;
import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.google.common.graph.Graph;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

/**
 * 学习caffeine
 */
public class LearnCaffeine {

    @Test
    public void loadByManual() {
        // @formatter:off
        Cache<Integer, String> cache = Caffeine.newBuilder()
                                              .expireAfterWrite(10, TimeUnit.MINUTES)
                                              .maximumSize(10_000)
                                              .build();
        // @formatter:on
        var key = 3;
        // 查找一个缓存元素， 没有查找到的时候返回null
        String value = cache.getIfPresent(key);
        // 查找缓存，如果缓存不存在则生成缓存元素,  如果无法生成则返回null
        if (Objects.isNull(value)) {
            value = cache.get(key, k -> toValue(key));
            // 添加或者更新一个缓存元素
            cache.put(key, value);
        }
        // 移除一个缓存元素
        cache.invalidate(key);
    }

    /**
     * {@link LoadingCache} 是{@link Cache} 附加上 {@link CacheLoader}能力之后的缓存实现。
     * 通过 {@link LoadingCache#getAll} 可以达到批量查找缓存的目的。 默认情况下，在getAll 方法中，将会对每个不存在对应缓存的key调用一次{@link LoadingCache#getAll}来生成缓存元素。
     * 在批量检索比单个查找更有效率的场景下，你可以覆盖并开发{@link CacheLoader#loadAll} 方法来使你的缓存更有效率。
     */
    @Test
    public void loadByAutomatic() {
        // @formatter:off
        LoadingCache<Integer, String> cache = Caffeine.newBuilder()
                                                 .maximumSize(10_000)
                                                 .expireAfterWrite(10, TimeUnit.MINUTES)
                                                 .build(this::toValue);
        // @formatter:on

        // 查找缓存，如果缓存不存在则生成缓存元素,  如果无法生成则返回null
        var key = 1;
        String s = cache.get(key);
        // 批量查找缓存，如果缓存不存在则生成缓存元素
        List<Integer> keys = Stream.of(1, 2, 3).collect(Collectors.toList());
        cache.getAll(keys);
    }

    /**
     * {@link AsyncCache} 是 {@link Cache} 的一个变体，{@link AsyncCache}提供了在{@link Executor}上生成缓存元素并返回 {@link CompletableFuture}的能力。这给出了在当前流行的响应式编程模型中利用缓存的能力。
     * <p>
     * {@code synchronous()}方法给 {@link Cache}提供了阻塞直到异步缓存生成完毕的能力。
     * <p>
     * 可以使用 {@code AsyncCache.asMap()}所暴露出来的{@link ConcurrentMap}的方法对缓存进行操作。
     * <p>
     * 默认的线程池实现是 ForkJoinPool.commonPool() ，也可以通过覆盖并实现 {@code Caffeine.executor(Executor)}方法来自定义你的线程池选择。
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void loadByManualAsync() throws ExecutionException, InterruptedException {
        // @formatter:off
        AsyncCache<Integer, String> cache = Caffeine.newBuilder()
                                                      .maximumSize(10_000)
                                                      .expireAfterWrite(10, TimeUnit.MINUTES)
                                                      .buildAsync(this::toValue);
        // @formatter:on
        var key = 1;
        // 查找一个缓存元素， 没有查找到的时候返回null
        CompletableFuture<String> value = cache.getIfPresent(key);
        System.out.println(value);
        // 查找缓存元素，如果不存在，则异步生成
        value = cache.get(key, k -> toValue(key));
        System.out.println(value.get());
        System.out.println("cache.getIfPresent(key) = " + cache.getIfPresent(key).get());
        cache.put(key, value);
        cache.synchronous().invalidate(key);
    }

    /**
     * {@link AsyncLoadingCache} 是 {@link AsyncCache} 加上 {@link AsyncCacheLoader} 能力的实现。
     *
     * 在需要同步的方式去生成缓存元素的时候，CacheLoader是合适的选择。而在异步生成缓存的场景下， AsyncCacheLoader则是更合适的选择并且它会返回一个 CompletableFuture。
     *
     * 通过 getAll可以达到批量查找缓存的目的。 默认情况下，在getAll 方法中，将会对每个不存在对应缓存的key调用一次 AsyncCacheLoader.asyncLoad 来生成缓存元素。 在批量检索比单个查找更有效率的场景下，可以覆盖并开发AsyncCacheLoader.asyncLoadAll 方法来使缓存更有效率。
     *
     * 值得注意的是，你可以通过实现一个 AsyncCacheLoader.asyncLoadAll并在其中为没有在参数中请求的key也生成对应的缓存元素。打个比方，如果对应某个key生成的缓存元素与包含这个key的一组集合剩余的key所对应的元素一致，那么在asyncLoadAll中也可以同时加载剩下的key对应的元素到缓存当中。
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void loadByAutomaticAsync() throws ExecutionException, InterruptedException {
        // @formatter:off
        AsyncLoadingCache<Integer, String> cache = Caffeine.newBuilder()
                                                      .maximumSize(10_000)
                                                      .expireAfterWrite(10, TimeUnit.MINUTES)
                                                      .buildAsync(this::toValue);
        // @formatter:on
        var key = 1;
        // 查找缓存元素，如果不存在，则异步生成
        CompletableFuture<String> value = cache.get(key);
        System.out.println(value.get());
        // 批量查找缓存元素，如果其不存在，将会异步进行生成
        CompletableFuture<Map<Integer, String>> values = cache.getAll(Stream.of(1, 2, 3).collect(Collectors.toList()));
        System.out.println(values.get());
    }
    public String toValue(Integer key) {
        return key.toString();
    }
}

