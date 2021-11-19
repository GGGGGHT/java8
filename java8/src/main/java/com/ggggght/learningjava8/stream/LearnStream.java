package com.ggggght.learningjava8.stream;

import com.ggggght.learningjava8.po.User;
import com.google.common.base.Preconditions;
import org.apache.commons.math3.stat.descriptive.summary.Sum;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * @author ght
 * @Desc 学习stream的一些常用的方法
 * @date 2019-12-20 12:17 PM
 * <p>
 * Java 8引入了全新的Stream API。这里的Stream和I/O流不同，它更像具有Iterable的集合类，但行为和集合类又有所不同。
 * <p>
 * stream是对集合对象功能的增强，它专注于对集合对象进行各种非常便利、高效的聚合操作，或者大批量数据操作。
 * <p>
 * stream有中间操作和终端操作 一个stream pipeline操作中可以有任意多的中间操作 但是只可以有一个终端操作
 * <p>
 * 如何判断一个操作是终端操作还是中间操作: 查看流的返回结果 当操作返回Stream时则为中间操作,否则是终端操作
 */

@SuppressWarnings("all")
public class LearnStream {

	private static List<User> userList = null;

	/**
	 * @target: 为userList填充数据
	 */
	@Before
	public void doSomeThingBefore() {
		userList = new ArrayList<>(5);
		userList.add(new User("zs", 19, "男"));
		userList.add(new User("ls", 20, "女"));
		userList.add(new User("ww", 21, "男"));
		userList.add(new User("cl", 22, "女"));
		userList.add(new User("lq", 23, "男"));
	}

	/**
	 * @target: 学习用来创建stream的几种方式
	 */
	@Test
	public void createTest() {
		// 1. 可以通过Collection系列集合提供的stream() 或 parallelStream()来获取
		List<String> list = new ArrayList<>();
		Stream<String> listStream = list.stream();
		Set<String> set = new HashSet<>();
		Stream<String> setStream = set.parallelStream();

		// 2. 通过Arrays.stream()获取数组流
		int[] arr = new int[3];
		IntStream arrStream = Arrays.stream(arr);

		// 3. 通过Stream.of()来获取流
		Stream<String> stream = Stream.of("1", "2", "3");

		// 4. 创建无限流 迭代
		Stream<Integer> iterate = Stream.iterate(0, (x) -> x + 2);
		Stream<Double> generate = Stream.generate(() -> Math.random());
	}

	/**
	 * @target: 过滤出userList中所有的男性成员并输出
	 */
	@Test
	public void filterTest() {
		userList.stream().filter(user -> Objects.equals("男", user.getSex())).forEach(System.out::println);
	}

	/**
	 * @target: 1. 过滤出userList中所有大于20岁以上的男性做为一个集合 2. 遍历集合并输出
	 */
	@Test
	public void filterTest1() {
		List<User> filterList = userList.stream()
				.filter(user -> Objects.equals(user.getSex(), "男") && user.getAge() > 20).collect(toList());
		// for (Iterator<User> iterator = filterList.iterator(); iterator.hasNext();) {
		// System.out.println(iterator.next());
		// }
		Iterator<User> iterator = filterList.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

	/**
	 * @target: 1. 获取出用户所有的名字做为一个集合 2. 输出所有的成员的名字
	 */
	@Test
	public void mapTest() {
		// 以下三种方式等价
		// List<String> collect = userList.stream().map(user ->
		// user.getName()).collect(toList());
		// List<String> collect = userList.stream().map(User::getName).collect(toList());
		List collect = userList.stream().map(user -> {
			return user.getName();
		}).collect(toList());
		System.out.println(collect);
	}

	/**
	 * collect在流中生成列表，map，等常用的数据结构,也可以生成自定义的结构
	 *
	 * @target: 获取出用户所有的名字做为一个list
	 */
	@Test
	public void collectListTest() {
		List<String> collect = userList.stream().map(User::getName).collect(toList());
		System.out.println(collect);
	}

	/**
	 * collect在流中生成列表，map，等常用的数据结构,也可以生成自定义的结构
	 *
	 * @target: 获取出用户所有的名字做为一个list
	 */
	@Test
	public void collectSetTest() {
		Set<String> collect = userList.stream().map(User::getName).collect(toSet());
		System.out.println(collect);
	}

	/**
	 * collect在流中生成列表，map，等常用的数据结构,也可以生成自定义的结构
	 *
	 * @target: 获取出用户所有的名字做为map的K, 年龄作为map的V
	 */
	@Test
	public void collectMapTest() {
		Map<String, Integer> collect = userList.stream().collect(toMap(User::getName, User::getAge));
		System.out.println(collect);
	}

	/**
	 * @target: 获取出所有用户的名字 生成一个Treeset 需要注意的是如果生成TreeSet必须要实现一个Comparable接口
	 * 如未实现该接口则会抛java.lang.ClassCastException: com.ggggght.learningjava8.stream.User
	 * cannot be cast to java.lang.Comparable
	 */
	@Test
	public void collectTreeSetTest() {
		TreeSet collect = userList.stream().collect(Collectors.toCollection(TreeSet::new));
		System.out.println(collect);
	}

	/**
	 * @target: 为所有的男生与女生分组
	 */
	@Test
	public void groupTest() {
		// 执行完该操作后所有的用户都在一个map中 此时key为false的是女生组 key为true的是男生组
		Map<Boolean, List<User>> users = userList.stream()
				.collect(groupingBy(user -> Objects.equals(user.getSex(), "男")));

		// 获取出女生
		List<User> females = users.get(false);
		for (User female : females) {
			System.out.println(female);
		}

		// 获取出男生
		List<User> males = users.get(true);
		males.stream().forEach(System.out::println);

		// 多级分组
	}

	/**
	 * @target: 列出所有的用户名 以'<'开始,以'>'结束,每个中间以'-'分割
	 */
	@Test
	public void joinTest() {
		String collect = userList.stream().map(User::getName).collect(joining("-", "<", ">"));
		System.out.println(collect);
	}

	/**
	 * @target: 过滤出所有的偶数, 要求只能出现一次
	 */
	@Test
	public void distinctTest() {
		List<Integer> list = Arrays.asList(1, 2, 3, 3, 2, 1, 4);
		// 使用distinct来去重
		list.stream().filter(i -> i % 2 == 0).distinct().forEach(System.out::println);
		// 或者封装到一个set中
		// Set<Integer> collect = list.stream().filter(i -> i % 2 == 0).collect(toSet());
		// System.out.println(collect);
	}

	/**
	 * @target: 过滤出年龄大于20岁的用户 只取前两个
	 */
	@Test
	public void limitTest() {
		userList.stream().filter(u -> u.getAge() > 20).limit(2).forEach(System.out::println);
	}

	/**
	 * @target 过滤出年龄大于20岁的用户 跳过第一个 取后面的
	 */
	@Test
	public void skipTest() {
		userList.stream().filter(u -> u.getAge() > 20).skip(1).forEach(System.out::println);
	}

	/**
	 * @target 过滤出年龄大于20岁的用户 跳过第一个后从取剩下的中取第一个
	 */
	@Test
	public void limitAndKkipTest() {
		userList.stream().filter(u -> u.getAge() > 20).skip(1).limit(1).forEach(System.out::println);
	}

	/**
	 * @target 查询所有的用户中是否存在有用户名为'ww'的 不存在则返回false, 存在则返回ture
	 */
	@Test
	public void anyMatchTest() {
		boolean ww = userList.stream().anyMatch(u -> Objects.equals(u.getName(), "ww"));
		System.out.println(ww);
	}

	/**
	 * @target 查询所有的用户中是否不存在有用户名为'tt'的 不存在则返回ture, 存在则返回false
	 */
	@Test
	public void noneMatchTest() {
		boolean tt = userList.stream().noneMatch(u -> Objects.equals(u.getName(), "tt"));
		System.out.println(tt);
	}

	/**
	 * @target 用来查询所有的用户的年龄是否都已成年 即大于等于18岁 如果都大于则返回true,否则返回false
	 */
	@Test
	public void allMatchTest() {
		boolean b = userList.stream().allMatch(u -> u.getAge() >= 18);
		System.out.println(b);
	}

	/**
	 * @target 用来取出所有用户的平均年龄
	 */
	@Test
	public void reduceTest() {
		/*
		 * Optional<Integer> reduce =
		 * userList.stream().map(User::getAge).reduce(Integer::sum);
		 * System.out.println(reduce.get() / userList.size());
		 */
		// Double avg = userList.stream().collect(averagingInt(User::getAge));
		// System.out.println(avg);

		List<Integer> list = List.of(1, 2, 3);
		list.stream().reduce(0, Integer::sum);
		// Integer res = list.stream().reduce(0, (i, j) -> i + j);
		// Optional<Integer> res = list.stream().reduce((i, j) -> i + j);
		// 无初始值
		Optional<Integer> res = list.stream().reduce(Integer::sum);
		Preconditions.checkState(res.isPresent() && res.get() == 6);

		// 获取最大 最小值
		Optional<Integer> max = list.stream().reduce(Integer::max);
		Optional<Integer> min = list.stream().reduce(Integer::min);
	}

	/**
	 * @target 用来统计所有大于20岁的男性的个数
	 */
	@Test
	public void countTest() {
		long count = userList.stream().filter(u -> u.getAge() > 20).filter(u -> Objects.equals(u.getSex(), "男"))
				.count();
		System.out.println(count);
	}

	@Test
	public void test() {
		// Consumer<String> con = System.out::print;
		// TreeSet<Integer> set = new TreeSet<>(Integer::compare);
		// con.accept("a");
		// User user = new User("ght",30,"man");
		// Supplier<String> supplier = () -> user.getSex();
		// String s = supplier.get();
		//
		// Supplier<Integer> integerSupplier = user::getAge;
		// System.out.println(integerSupplier.get());

		// Function<T, R> 函数型接口传入T 返回R
		// Stream.of("aaa", "bbb", "ccc");
		// List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
		// Stream<Stream<Character>> streamStream = list.stream()
		// .map(String::toUpperCase)
		// .map(LearnStream::filterCharacter);
		// streamStream.forEach((s) -> s.forEach(System.out::println));
		Optional<User> max = userList.stream().max((e1, e2) -> Double.compare(e1.getAge(), e2.getAge()));
		Optional<Integer> min = userList.stream().map(User::getAge).min(Integer::compare);
		System.out.println(max.get());
		System.out.println(min.get());
	}

	/**
	 * @target 给定一个数组如[1, 2, 3, 4, 5] 要求返回他们的平方构成的数组
	 */
	@Test
	public void squarTest() {
		int[] arr = { 1, 2, 3, 4, 5 };
		Arrays.stream(arr).map(i -> i * i).forEach(System.out::println);
	}

	public static Stream<Character> filterCharacter(String string) {
		List<Character> list = new ArrayList<>(string.length());

		for (char c : string.toCharArray()) {
			list.add(c);
		}
		return list.stream();
	}

	/**
	 * peek在流的每个元素恢复运行之前 ，插入执行一个动作
	 */
	@Test
	public void peekTest() {
		List<Integer> nums = Arrays.asList(5, 2, 1, 6, 10);
		List<Integer> res = nums.stream().peek(i -> System.out.println("from stream: " + i)).map(i -> i + 17)
				.peek(i -> System.out.println("after map: " + i)).filter(i -> i % 2 == 0)
				.peek(i -> System.out.println("after filter: " + i)).limit(3)
				.peek(i -> System.out.println("after filter: " + i)).collect(toList());

		res.forEach(System.out::println);
	}

	@Test
	public void flatMapTest() {
		List<Integer> l1 = List.of(1, 2, 3);
		List<Integer> l2 = List.of(3, 4);

		List<int[]> res = l1.stream().flatMap(i -> l2.stream().map(j -> new int[] { i, j })).collect(toList());

		res.stream().map(Arrays::toString).forEach(System.out::println);

		List<int[]> _3 = res.stream().filter(i -> {
			return 0 == Arrays.stream(i).sum() % 3;
		}).collect(toList());

		Preconditions.checkState(_3.size() == 2);
	}

	/**
	 * 使用IntStream, DoubleStream longStream 避免了暗含自动装箱折箱的成本
	 */
	@Test
	public void intstreamTest() {
		// mapToInt 返回 IntStream
		int sum = userList.stream().mapToInt(User::getAge).sum();
		IntStream intStream = userList.stream().mapToInt(User::getAge);
		// 将数值流转换为Stream
		Stream<Integer> boxed = intStream.boxed();
		// int max = intStream.max().orElse(1);
		// Stream<int[]> res = IntStream.rangeClosed(1, 100).boxed()
		// .flatMap(a -> IntStream.rangeClosed(a, 100).filter(b -> Math.sqrt(a * a + b *
		// b) % 1 == 0)
		// .mapToObj(b -> new int[] { a, b, (int) Math.sqrt(a * a + b * b) }))
		// .limit(3);
		Stream<double[]> res = IntStream.rangeClosed(1, 100).boxed()
				.flatMap(a -> IntStream.rangeClosed(a, 100)
						.mapToObj(b -> new double[] { a, b, Math.sqrt(a * a + b * b) }).filter(d -> d[2] % 1 == 0))
				.limit(3);

		res.forEach(i -> System.out.println(Arrays.toString(i)));
		System.out.println("IntStream.rangeClosed(0,10).count() = "
				+ IntStream.rangeClosed(0, 10).boxed().collect(summingInt(i -> i)));

		IntSummaryStatistics collect = IntStream.range(0, 10).boxed().collect(summarizingInt(i -> i));
		System.out.println("collect = " + collect);
		collect.getSum();
		collect.getAverage();

		Integer result = userList.stream().collect(reducing(0, User::getAge, Integer::sum));
	}

	@Test
	public void getMaxByGrouping() {
		// 根据性别取出每个组年龄最大的用户
		Map<String, User> collect = userList.stream().collect(groupingBy(User::getSex,
				collectingAndThen(maxBy(Comparator.comparingInt(User::getAge)), Optional::get)));

		// Preconditions.checkState(collect.size() == 2);
		collect.entrySet()
				.stream()
				.map(entry -> entry.getKey() + " -> " + entry.getValue())
				.forEach(System.out::println);

		// 根据性别求年龄的平均值
		Map<String, Double> users = userList.stream().collect(groupingBy(User::getSex, averagingInt(User::getAge)));
		users.entrySet().forEach(e -> System.out.println(e.getKey() + " -> " + e.getValue()));
	}

}
