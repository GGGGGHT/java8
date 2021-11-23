package com.ggggght.learningjava8.guava;

import com.google.common.base.CharMatcher;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableList;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;

@SuppressWarnings("all")
public class GuavaTest {

	@Test
	public void test() throws ExecutionException {

		LoadingCache<String, String> cache = CacheBuilder.newBuilder().maximumSize(1000)
				.build(new CacheLoader<String, String>() {
					@Override
					public String load(String key) throws Exception {
						return key;
					}
				}); // look Ma, no CacheLoader

		cache.get("1");
		cache.get("2");
		cache.get("3");
		cache.asMap().entrySet().forEach(e -> System.out.printf("%s -> %s\r\n", e.getKey(), e.getValue()));
		cache.invalidateAll(ImmutableList.of("1", "2"));
		System.out.println();
		cache.asMap().entrySet().forEach(e -> System.out.printf("%s -> %s\r\n", e.getKey(), e.getValue()));
	}

	private String str(String s) {
		return s;
	}

	@Test
	public void t() {
		Function<String, Integer> lengthFunction = new Function<String, Integer>() {
			public Integer apply(String string) {
				return string.length();
			}
		};
		Predicate<String> allCaps = new Predicate<String>() {
			public boolean apply(String string) {
				return CharMatcher.javaUpperCase().matchesAllOf(string);
			}
		};
		HashFunction hf = Hashing.crc32();
		// dp[index][rest] = dp[index+1][rest] + dp[index][rest - n]
	}

	@Test
	public void classLoader() {
		System.out.println(GuavaTest.class.getClassLoader());
	}

}
