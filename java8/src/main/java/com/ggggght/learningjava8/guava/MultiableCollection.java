package com.ggggght.learningjava8.guava;

import com.google.common.collect.*;
import org.junit.Test;

import java.util.*;

/**
 * @desc: 可变的集合
 */
public class MultiableCollection {

	/**
	 * @method: count(E) 统计E元素在set中出现的次数
	 * @method: elementSet() 返回MultiSet<>
	 * @method: entrySet() 返回MultiSet<Entry<>>
	 * @method: add(E,int) 添加int个e元素到set中
	 * @method: setCount(E,int) 设置set中e元素的个数为int
	 * @method: remove(E,int) 从set中移除E元素int次 当remove的个数大于当前存在的个数时 直接全部移除
	 * @method: count() 统计所有元素出现的总次数 如果想统计元素种类 使用 <b>multiset.entrySet().size()</b>
	 *
	 * setCount(elem,0) 等同于remove(elem)
	 */
	@Test
	public void mulitsetTest() {
		Multiset<String> multiset = HashMultiset.create();
		multiset.add("a");
		multiset.add("a");
		multiset.add("a");
		multiset.add("a");
		multiset.add("a");
		multiset.add("a");
		multiset.add("a");
		multiset.add("b");
		multiset.add("b");
		multiset.add("b");
		multiset.add("b");
		multiset.add("b");
		multiset.entrySet().forEach(i -> System.out.println(i.getElement() + " --> " + i.getCount()));
		multiset.elementSet().forEach(i -> System.out.println(i + " --> " + multiset.count(i)));
		System.out.println("multiset.size() = " + multiset.size()); // 12
		multiset.setCount("a", 2);
		System.out.println("multiset.count(\"a\") = " + multiset.count("a")); // 2
		System.out.println("multiset.size() = " + multiset.size()); // 7
		multiset.add("c", 1);
		System.out.println("multiset.count(\"c\") = " + multiset.count("c"));
		System.out.println("multiset.size() = " + multiset.size()); // 8
		multiset.remove("a", 1);
		System.out.println("multiset.count(\"a\") = " + multiset.count("a")); // 2
		System.out.println("multiset.size() = " + multiset.size()); // 7
		multiset.remove("a", 5);
		System.out.println("multiset.count(\"a\") = " + multiset.count("a")); // 2
		System.out.println("multiset.size() = " + multiset.size()); // 7
		System.out.println("multiset.entrySet().size() = " + multiset.entrySet().size());
	}

	/**
	 * @mothod: subMultiset(开始位置，是否包含开始，结束位置，是否包含结束)
	 */
	@Test
	public void sortedMultiSetTest() {
		SortedMultiset<String> sortedMultiset = TreeMultiset.create();
		for (int i = 65; i < 67 + 24; i++) {
			sortedMultiset.add(String.valueOf((char) i));
		}
		System.out.println("sortedMultiset.subMultiset(\"A\", BoundType.CLOSED, \"C\", BoundType.CLOSED).size() = "
				+ sortedMultiset.subMultiset("A", BoundType.CLOSED, "C", BoundType.CLOSED).size());
	}

	/**
	 * @Desc: 创建multiset的方式
	 */
	@Test
	public void createMultimapTest() {
		// 创建ListMultimap 使用自然排序(类型于TreeMap)做为key arraylist做为value创建map
		ListMultimap<String, Integer> listMap = MultimapBuilder.treeKeys().arrayListValues().build();
		SetMultimap<Integer, Integer> hashMap = MultimapBuilder.hashKeys().hashSetValues().build();
	}

	/**
	 * @method: get(key) 如果map是ListMultimap 则返回的结果是list 如果是SetMultimap 则返回结果是set
	 * @method: clear()
	 * @method: containsKey(key) Multimap.containsKey(key) is true if and only if there
	 * are any elements associated with the specified key. In particular, if a key k was
	 * previously associated with one or more values which have since been removed from
	 * the multimap, Multimap.containsKey(k) will return false. 当key有元素时才返回true
	 * 调用完clear()后就返回false
	 */
	@Test
	public void multimapTest() {
		Multimap<String, String> map = HashMultimap.create();
		map.put("a", "a");
		map.put("a", "b");
		map.put("a", "c");
		map.put("a", "d");
		map.put("b", "a");
		map.put("b", "b");
		map.put("b", "c");
		map.put("b", "d");
		map.put("c", "c");
		map.asMap().forEach((key, value) -> System.out.println(key + " --> " + value));

		System.out.println("map.get(\"c\") = " + map.get("c"));
		System.out.println("map.get(\"d\") = " + map.get("d"));
		System.out.println("map.get(\"a\") = " + map.get("a"));
		Collection<String> a = map.get("a");
		a.clear();
		System.out.println("map.get(\"a\") = " + map.get("a"));
		System.out.println("map.containsKey(\"a\") = " + map.containsKey("a"));
		a.add("1");
		a.add("2");
		System.out.println("map.get(\"a\") = " + map.get("a"));
		System.out.println("map.containsKey(\"a\") = " + map.containsKey("a"));
		map.remove("a", "1");
		System.out.println("map.get(\"a\") = " + map.get("a"));

		map.entries().forEach(i -> {
			System.out.println(i.getKey() + " -> " + i.getValue());
		});

		System.out.println("map.keys() = " + map.keys());
		System.out.println("map.values() = " + map.values());
	}

	/**
	 * @desc: BiMap<K,V> 使用inverse() 可以转成 BiMap<V,K>
	 */
	@Test
	public void biMapTest() {
		BiMap<String, Integer> map = HashBiMap.create();
		map.put("hello", 1);
		map.put("world", 2);
		map.put("ght", 3);
		map.put("gggg", 4);
		map.forEach((key, value) -> System.out.println(key + " --> " + value));
		BiMap<Integer, String> inverse = map.inverse();
		inverse.forEach((key, value) -> System.out.println(key + " --> " + value));
	}

	/**
	 * @desc: classToInstanceMap 可以根据类型来存储不同类型的对象
	 */
	@Test
	public void classToInstanceMapTest() {
		ClassToInstanceMap<Object> clazzMap = MutableClassToInstanceMap.create();
		clazzMap.putInstance(Integer.class, 1);
		clazzMap.putInstance(HashMap.class, new HashMap());
		clazzMap.putInstance(String.class, "flying");
		System.out.println("clazzMap.getInstance(Integer.class) = " + clazzMap.getInstance(Integer.class));
		System.out.println("clazzMap.getInstance(String.class) = " + clazzMap.getInstance(String.class));
	}

	@Test
	public void rangeSetTest() {
		RangeSet<Integer> rangeSet = TreeRangeSet.create();
		rangeSet.add(Range.closed(1, 10)); // {[1, 10]}
		rangeSet.add(Range.closedOpen(11, 15)); // disconnected range: {[1, 10], [11, 15)}
		rangeSet.add(Range.closedOpen(15, 20)); // connected range; {[1, 10], [11, 20)}
		rangeSet.add(Range.openClosed(0, 0)); // empty range; {[1, 10], [11, 20)}
		rangeSet.remove(Range.open(5, 10)); // splits [1, 10]; {[1, 5], [10, 10], [11,
											// 20)}

		System.out.println(rangeSet.asRanges());
	}

}
