package com.ggggght.learningjava8.guava;

import com.google.common.base.Function;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multiset;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class CollectionUtils {

	/**
	 * @Desc: guava的集合类都没有无参的构造方法 只能通过工厂去构建对象
	 */
	@Test
	public void constructorTest() {
		// traditional constructor
		List<String> list = new ArrayList<>();
		// use guava
		List<Object> list1 = Lists.newArrayList();
		Map<Object, Object> map = Maps.newLinkedHashMap();
		// create list with capacity
		ArrayList<Object> list2 = Lists.newArrayListWithCapacity(100);
		Multiset<String> multiset = HashMultiset.create();
		Function<Instant, Boolean> isAfter = Instant.now()::isAfter;

	}

}
