package com.ggggght.learningjava8.guava;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class GuavaStrings {

	@Test
	public void joinerTest() {
		Joiner joiner = Joiner.on("; ").useForNull("null");
		String join = joiner.join("hello", null, "world", "ght", " ");
		System.out.println(join);
	}

	@Test
	public void splitterTest() {
		System.out.println("Arrays.toString(\",a,b,,\".split(\",\")) = " + Arrays.toString(",a,b,,".split(",")));

		Iterable<String> split = Splitter.on(',').trimResults()
				// .omitEmptyStrings()
				.split(",a,b,,");
		split.forEach(i -> System.out.printf("%s->", i));
		System.out.println();

		Splitter splitter = Splitter.fixedLength(3);
		splitter.split("12345678").forEach(System.out::println);
	}

}
